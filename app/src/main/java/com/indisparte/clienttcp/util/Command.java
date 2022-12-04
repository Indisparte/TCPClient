package com.indisparte.clienttcp.util;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public enum Command {
    SET_USERNAME('u'), // usage: u [username]
    NEW_HOLE('h'),
    HOLE_LIST('l'),
    THRESHOLD('t'),
    EXIT('e');

    public final char value;

    Command(char command) {
        this.value = command;
    }
}
