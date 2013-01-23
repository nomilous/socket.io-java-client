package io.socket;

import java.util.logging.Logger;
import com.google.gson.JsonObject;

public class IOEvent {

    public static interface Handler {
        void handle(IOAcknowledge ack, Class... args);
    }

    protected Class[] args;
    private Handler callback;

    public IOEvent(Class... args) {
        this.args = args;
    }

    public IOEvent then(final Handler callback) {
        this.callback = callback;
        return this;
    }

    protected boolean process(JsonObject json, IOAcknowledge ack, Logger logger) {


        //
        // pending marshal to args
        //



        return false;
    }

}
