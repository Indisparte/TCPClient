package com.indisparte.clienttcp.data.network;

import androidx.annotation.NonNull;

/**
 * Helps the user create a well-formatted server command.
 *
 */
public class ServerCommand {
    /**
     * The set of commands accepted by the server
     */
    public enum CommandType {
        NEW_INT("i"),//usage: i [integer]
        LIST("l"),//usage: l
        MAX("m"),//usage: m
        EXIT("e");//usage: e

        public final String value;

        CommandType(final String command) {
            this.value = command;
        }
    }

    private final CommandType type;
    private final String[] query;

    /**
     * Creates a command for the server specifying the command and any parameters.
     *
     * @param type  The type of the accepted command, {@link CommandType}, can't be null
     * @param query The query attached to the command, can be null or empty
     */
    public ServerCommand(@NonNull CommandType type, String... query) {
        this.type = type;
        this.query = query;
    }

    public CommandType getType() {
        return type;
    }

    public String[] getQuery() {
        return query;
    }

    /**
     * Returns the command as a formatted string for sending on the socket.
     * The method is implemented to specify the command followed by the list of any parameters,
     * separated by a semicolon and enclosed in two square brackets.
     *
     * @return The command formatted as a request to the server, like this:
     * command <[param1;param2;...;paramN]>
     */
    public String getFormattedRequest() {
        String command = type.value;
        if (query != null && query.length > 0) {//some params
            if (query.length == 1) {
                command += "[" + query[0] + "]";//command [param]
            } else {
                command += "[" + String.join(";", query) + "]";//command [param1;...;paramN]
            }
        }
        return command;
    }
}
