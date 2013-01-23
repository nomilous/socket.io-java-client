package io.socket;

import java.util.HashMap;

class IOEventRouter { 

    private HashMap<String, IOEvent> map = new HashMap<String, IOEvent>();

    public IOEvent when(String event, Class... args) {

        IOEvent e = new IOEvent(args);
        IOEvent old = map.put(event, e);  

        //
        // old is the IOEvent that was previously mapped
        // at this address (or null)
        // 
        // may come in handy later
        //

        return e;  // to enable calling e.then() on the chain

    }

    public boolean handle(IOMessage message) {


        

        
        return false;
    }

    public IOEvent get(String event) {
        return (IOEvent) map.get(event);
    }

}
