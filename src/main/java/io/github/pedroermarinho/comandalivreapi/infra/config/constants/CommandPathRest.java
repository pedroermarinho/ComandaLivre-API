package io.github.pedroermarinho.comandalivreapi.infra.config.constants;

public class CommandPathRest {

    private CommandPathRest() {
        throw new IllegalStateException("Utility class");
    }

    public static final String COMMAND = "/command";
    public static final String COMMAND_REGISTER = COMMAND;
    public static final String COMMAND_SEARCH = COMMAND;
    public static final String COMMAND_UPDATE = COMMAND;
    public static final String COMMAND_STATUS = COMMAND + "/disable";
}
