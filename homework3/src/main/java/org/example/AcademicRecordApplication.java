package org.example;

import org.example.command.CommandBuilder;

import java.io.IOException;


public class AcademicRecordApplication {
    public static void main(String[] args) throws IOException {

//        Для загрузки данных из csv в БД
//        FileDataLoader fileDataLoader = new FileDataLoader();
//        fileDataLoader.saveData();

        CommandBuilder commandBuilder = new CommandBuilder();
        commandBuilder.getManual();

    }



}