package org.example.loader;

import org.example.model.Person;
import org.example.util.MyArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileDataLoader implements DataLoader {
    @Override
    public MyArrayList<Person> getPersons() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\IdeaProjects\\homework4\\students.csv"));
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
                    case 0:
                        name += data + " ";
                        break;
                    case 1: name += data;
                            break;
                    case 2:
                        person.setAge(Integer.parseInt(data));
                        break;
                    case 3:
                        person.setGroup(Integer.parseInt(data));
                        break;
                    case 4:
                        person.setPhysicsScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                    case 5:
                        person.setMathematicsScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                    case 6:
                        person.setRusScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                    case 7:
                        person.setLiteratureScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                    case 8:
                        person.setGeometryScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                    case 9:
                        person.setInformaticsScore(Integer.parseInt(data));
                        sumRating += Integer.parseInt(data);
                        break;
                }
                index++;
            }
            person.setName(name);
            person.setAverageScore(sumRating / 6.0);
            index = 0;
            persons.add(person, Person.class);
            count++;
        }
        reader.close();
        return persons;
    }
}

