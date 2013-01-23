package io.socket;

import java.util.logging.Logger;
import java.util.HashMap;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

class IOEventRouter { 

    /* A map of IOEvents registered with socketIO.when().then() **/ 
    private HashMap<String, IOEvent> map = new HashMap<String, IOEvent>();

    /** Marshals from Classes to JSON **/
    static final JsonParser jsonParser = new JsonParser();

    public IOEvent when(String event, Class... argTypes) {

        IOEvent e = new IOEvent(argTypes);
        IOEvent old = map.put(event, e); 

        //
        // old is the IOEvent that was previously mapped
        // at this address (or null)
        // 
        // may come in handy later
        //

        return e;  // to enable calling e.then() on the chain

    }

    public boolean handle(IOMessage message, IOAcknowledge ack, Logger logger) {

        JsonObject jobject = parse(message.getData(), logger);
        if( !jobject.has("name") )
            return false; // parse failed or no eventname in payload

        try {
            String eventName = jobject.get("name").getAsString();
            if( !map.containsKey(eventName) ) 
                return false; // no .when() for this event

            return map.get(eventName).process(jobject, ack, logger);

        } catch (Exception x) {
            logger.warning( x.toString() );
            return false;
        }
    }

    public IOEvent get(String event) {
        return (IOEvent) map.get(event);
    }

    private JsonObject parse(String json, Logger logger) {
        try { return jsonParser.parse(json).getAsJsonObject(); }
        catch (Exception x) {
            logger.warning("Malformated JSON received");
            return new JsonObject();
        }
    }
}
