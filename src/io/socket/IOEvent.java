package io.socket;

import java.util.logging.Logger;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;

public class IOEvent {

    public static interface Handler {
        void handle(IOAcknowledge ack, Object... args);
    }

    protected Class[] argTypes;
    private Handler callback;

    public IOEvent(Class... argTypes) {
        this.argTypes = argTypes;
    }

    public IOEvent then(final Handler callback) {
        this.callback = callback;
        return this;
    }

    protected boolean process(JsonObject json, IOAcknowledge ack, Logger logger) {

        JsonArray jsonArgs = json.get("args").getAsJsonArray();
        Object[] args = new Object[jsonArgs.size()];

        int i = 0;
        Iterator<JsonElement> ii = jsonArgs.iterator();
        while( ii.hasNext() ) {



            Class klass = argTypes[i++];
            System.out.println( ii.next().toString() + " <---> " + klass.toString() );



        }

        return false;
    }

}
