package org.example;

public class PersonAgeDataGroups {
    private final int SIZE = 16;
    private Entry<Integer, MyArrayList<Person>>[] table = new Entry[SIZE];

    /* Сложность с точки зрения О натации будет составлять О(1), так как всего различных возрастов учеников 13,
       соответственно index всегда будет иметь униколькое значение, а значит в каждом Buckets Entry
       будет лежать только один список Person */
    public void addPerson(Person person){
        int index = person.getAge() % SIZE;
        if (table[index] == null) {
            Entry entry = new Entry(person.getAge(), new MyArrayList<Person>());
            table[index] = entry;
        }
        table[index].getValue().add(person);
    }

    public MyArrayList<Person> getPersons(int age) {
        int index = age % SIZE;
        return table[index] != null ? table[index].getValue() : null;
    }
}
