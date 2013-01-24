package examples.IOEvent;

import io.socket.SocketIO;
import io.socket.IOEvent;
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import org.json.JSONObject;

class Palette {
    public String[] colours;
}

class Sheep {
    protected String colour; 
}

class Flock {
    private Sheep[] sheep;
}


class CountingSheep {

    public static void main(String[] args) {

        SocketIO socket = new SocketIO();

        socket.when(

            //
            // .when( name, class, class, ... ) 
            // 
            //    To register an IOEvent by name and
            //    argument signature. 
            //

            "server:sends:flock:of:sheep", 

            Flock.class,    /* server sends Flock as arg1 */
            Palette.class   /*        and Palette as arg2 */

        ).then( 

            //
            // .then( handler )
            //
            //    To assign the handler to receive the
            //    registered IOEvent 
            // 

            new IOEvent.Handler () {

                @Override
                public void handle( IOAcknowledge ack, Object... args ) {

                    //
                    // args contains the populated instances of the 
                    // classes as registered with .when()
                    //
                    

                }
            }
        );

        try {
            socket.connect("http://127.0.0.1:3001/", new IOCallback() { 

                //
                // The usual. Still required.
                //

                @Override public void onConnect() {}
                @Override public void onMessage(JSONObject json, IOAcknowledge ack) {}
                @Override public void onMessage(String data, IOAcknowledge ack) {}
                @Override public void on(String event, IOAcknowledge ack, Object... args) {}
                @Override public void onError(SocketIOException socketIOException) {}
                @Override public void onDisconnect() {}

            });

        } catch ( Exception x ) { System.out.print( "Exception: " + x.toString() ); }

    }

}

