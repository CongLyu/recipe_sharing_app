package main.java.utility;

import java.util.UUID;

public class StrIdGenerator {

    /**
     * return an unique string to help user entity generate an unique id.
     */
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
