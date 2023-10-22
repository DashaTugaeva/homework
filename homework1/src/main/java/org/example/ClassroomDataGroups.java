package org.example;


public class ClassroomDataGroups {

    private final int SIZE = 16;
    private Entry<Integer, MyArrayList<Person>>[] table = new Entry[SIZE];

    /* Сложность с точки зрения О натации будет составлять О(1), так как всего групп 12,
       соответственно index всегда будет иметь униколькое значение, а значит в каждом Buckets Entry
       будет лежать только один список Person */

    public void addPerson(Person person){
        int index = person.getGroup() % SIZE;
        if (table[index] == null) {
            Entry entry = new Entry(person.getGroup(), new MyArrayList<Person>());
            table[index] = entry;
        }
        table[index].getValue().add(person);
    }

    public MyArrayList<Person> getPersons(int groupNum) {
        int index = groupNum % SIZE;
        return table[index] != null ? table[index].getValue() : null;
    }

}
