package org.example;
import org.example.command.CommandBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CommandBuilder commandBuilder = new CommandBuilder();
        commandBuilder.getPersons();
        commandBuilder.getManual();

    }
}