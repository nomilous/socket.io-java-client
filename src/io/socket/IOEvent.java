package io.socket;

import java.util.logging.Logger;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.Gson;

public class IOEvent {

    public static interface Handler {
        void handle(String event, IOAcknowledge ack, Object... args);
    }

    /** Marshals from Classes to JSON **/
    private Gson gson = new Gson();
    
    private Handler handler;
    private String event;
    protected Class[] argTypes;

    public IOEvent(String event, Class... argTypes) {
        this.event = event;
        this.argTypes = argTypes;
    }

    public IOEvent then(final Handler handler) {
        this.handler = handler;
        return this;
    }

    protected boolean process(JsonObject json, IOAcknowledge ack, Logger logger) {

        JsonArray jsonArgs = json.get("args").getAsJsonArray();
        Object[] args = new Object[jsonArgs.size()];

        int i = 0;
        Iterator<JsonElement> ii = jsonArgs.iterator();
        while( ii.hasNext() ) {

            Class klass = argTypes[i];
            args[i++] = gson.fromJson(ii.next(), klass);

        }

        handler.handle(event, ack, args);

        return true;
    }

}
