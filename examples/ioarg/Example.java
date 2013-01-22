
package examples.ioarg;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import io.socket.IOArg;
import io.socket.IODefaultCallback;

class Knowledge extends IOArg {
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

class Known extends IOArg{
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

class Clone extends IOArg {
    private String description;
    private String type;
    private int count;
    public String toString() { 
        return String.format(

            "f(%s) = %d", type, count

        ); 
    }
}

class OperatingInstructions extends IOArg {
    public String instruction1;
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

        socket.when("server:sends:cloned:sheep", Clone.class, OperatingInstructions.class);


        //
        // socket.when("enterprise:beams:recon", Data.class, Spock.class, Kirk.class).then( new IOEvent() {
        // 
        //      public void handle( Data data, Spock spock, Kirk kirk ) { 
        //
        //           // 
        //           // possible >> ?
        //           // 
        // 
        //      }
        //
        // }) ;
        //


        socket.connect("http://127.0.0.1:3001/", this);

        //
        // Pass Object instances to socket.io emitter
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
