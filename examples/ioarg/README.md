### Example.java shows how to use the IOArg extension. 

It serializes directly to and from classes using [Gson](http://code.google.com/p/google-gson/) <br />

<b>To run the example</b> (as best i've managed to figure it) 

```bash

# compile the jar
ant jar

# compile the example
javac -classpath jar/socketio.jar examples/ioarg/Example.java 

# run the example
java -classpath .:libs/*.jar:jar/socketio.jar examples/ioarg/Example

```

Which will fail...  (It has no server to talk to)

<b>To run a server for it to talk to</b> 

Assuming you have [nodejs](http://nodejs.org/)

```bash

mkdir node_modules
npm install coffee-script socket.io
./node_modules/


```

