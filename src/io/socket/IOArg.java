/*
 * socket.io-java-client IOMessage.java
 *
 * Copyright (c) 2012, Enno Boland
 * socket.io-java-client is a implementation of the socket.io protocol in Java.
 * 
 * Copyright (c) 2013, Nomilous
 * extended to marshal directly to and from Classes extending IOPayload 
 * 
 * See LICENSE file for more information
 */

package io.socket;

import com.google.gson.Gson;

public class IOArg {}

class IOArgMarshal {

    public static String toJson( Gson gson, String event, IOArg... args ) {

        return "pending";

    }

}
