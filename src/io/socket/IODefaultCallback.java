package io.socket;

import org.json.JSONObject;

public abstract class IODefaultCallback

    //
    // Primarily to avoid having to import org.json.Stuff just 
    // to define interface seldom used interface handlers. 
    // 

    implements IOCallback {

        public void onMessage(String data, IOAcknowledge ack) {}
        public void onMessage(JSONObject json, IOAcknowledge ack) {}

    }
