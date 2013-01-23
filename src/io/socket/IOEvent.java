package io.socket;

public class IOEvent {

    public static interface Handler {
        void handle(IOAcknowledge ack, Class... args);
    }

    private Class[] args;
    private Handler callback;

    public IOEvent(Class... args) {
        this.args = args;
    }

    public IOEvent then(final Handler callback) {
        this.callback = callback;
        return this;
    }

}
