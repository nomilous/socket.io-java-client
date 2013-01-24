
package examples.ioarg;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import io.socket.IOArg;
import io.socket.IODefaultCallback;
import io.socket.IOEvent;

class Knowledge implements IOArgs {
    private UnKnown[] unknownUnknowns;
    protected UnKnown[] knownUnknowns;
    public Known[] knownKnowns;
    public Knowledge() {
        unknownUnknowns = new UnKnown[] {
            new UnKnown(" fullblown unknown"),
            new UnKnown(" pre-owned unknown"),
            new UnKnown(" home-grown unknown"),
            new UnKnown(" big-boned known"),
        };
        knownUnknowns = new UnKnown[] {
            new UnKnown(" de-throned unknown"),
            new UnKnown(" flyblown known"),
            new UnKnown(" well-toned known"),
            new UnKnown(" freshly mown known"),
            new UnKnown(" softly thrown known")
        };
        knownKnowns = new Known[] {
            new Known(" re-zoned unknown"),
            new Known(" well shown")
        };
    }
}

class Known implements IOArg{
    protected String type;
    public Known() {}
    public Known(String type) {
        this.type = "A" + type + " known.";
    }
}

class UnKnown extends Known {
    public UnKnown(String type) {
        this.type = "A" + type + " unknown.";
    }
}

class Clone {
    private String thing;
    private String colour;
    private int count;
    public String toString() { 
        return String.format(
            "tally is %d %s %s", count, colour, thing
        ); 
    }
}

class OperatingInstructions {
    private String instruction1;
    private String instruction2;
    public String toString() { 
        return String.format(
            "%s and %s", instruction1, instruction2
        ); 
    }
}


class Example extends IODefaultCallback {

    private SocketIO socket;

    public static void main(String[] args) {
        try {
            new Example();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Example() throws Exception {
        socket = new SocketIO();

        socket.connect("http://127.0.0.1:3001/", this);

        //
        // Pass Object instances to socket.io emitter
        // These objects should implement
        // 

        socket.emit("event:sent:to:server", new Knowledge(), new Known(" garden") );
        
    }

    // made these optional
    //
    // @Override
    // public void onMessage(JSONObject json, IOAcknowledge ack) {
    //     try {
    //         System.out.println("Server said:" + json.toString(2));
    //     } catch (JSONException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // @Override
    // public void onMessage(String data, IOAcknowledge ack) {
    //     System.out.println("Server said: " + data);
    // }
    //

    @Override
    public void onError(SocketIOException socketIOException) {
        System.out.println("an Error occured");
        socketIOException.printStackTrace();
    }

    @Override
    public void onDisconnect() {
        System.out.println("Connection terminated.");
    }

    @Override
    public void onConnect() {
        System.out.println("Connection established");
    }

    @Override
    public void on(String event, IOAcknowledge ack, Object... args) {

        //
        // pending: cast the inbound json to specified class
        //

        System.out.println("Server triggered event '" + event + "'" + args.toString());

        for( int i = 0; i < args.length; i++ ) {

            System.out.println("     with arg'" + args[i].getClass());

        }
    }

}
