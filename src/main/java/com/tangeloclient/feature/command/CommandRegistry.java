package me.zeroX150.tutorialClient.feature.command;

import me.zeroX150.tutorialClient.feature.command.impl.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandRegistry {
    private static CommandRegistry INSTANCE = null;
    List<Command> COMMANDS = new ArrayList<>();
    public CommandRegistry() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Command registry already exists!");
        }
        INSTANCE = this;
        registerCommands();
    }

    void register(Command command) {
        COMMANDS.add(command);
    }

    void registerCommands() {
        register(new Test());
    }

    public Command getByName(String name) {
        for (Command command : COMMANDS) {
            if (command.getName().equalsIgnoreCase(name)) return command;
        }
        return null;
    }

    public Command getByAlias(String search) {
        for (Command command : COMMANDS) {
            if (Arrays.stream(command.aliases).anyMatch(s -> s.equalsIgnoreCase(search))) return command;
        }
        return null;
    }

    public static CommandRegistry getInstance() {
        if (INSTANCE == null) new CommandRegistry();
        return INSTANCE;
    }
}
