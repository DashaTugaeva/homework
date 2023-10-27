package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileDataLoader implements DataLoader {
    @Override
    public MyArrayList<Person> getPersons() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/students.csv"));
        MyArrayList<Person> persons = new MyArrayList<>(Person.class);
        Scanner scanner = null;
        int index = 0;
        String line = reader.readLine();

        int count = 0;
        while ((line = reader.readLine()) != null) {
            Person person = new Person();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            int sumRating = 0;
            String name = "";
            while (scanner.hasNext()) {
                String data = scanner.next();
                switch (index) {
                    case 0 -> name += data + " ";
                    case 1 -> name += data;
                    case 2 -> person.setAge(Integer.parseInt(data));
                    case 3 -> person.setGroup(Integer.parseInt(data));
                    default -> sumRating += Integer.parseInt(data);
                }
                index++;
            }
            person.setName(name);
            person.setAverageRating(sumRating / 6.0);
            index = 0;
            persons.add(person, Person.class);
            count++;
        }
        reader.close();
        return persons;
    }
}

