package org.example;

public class PersonNameDataGroup {
    private final int SIZE = 50;
    private Entry<String, MyArrayList<Person>>[] table = new Entry[SIZE];

    /* Сложность будет составлять О(1), так как всего различных вариантов заглавных букв русского алфавита составляет 33,
       capacity = 50 гарантирует, что при переводе символа в значение ASCII таблицы index всегда будет иметь униколькое значение, а значит в каждом Buckets Entry
       будет лежать только один список Person */
    public void addPerson(Person person){

        int index = ((int) person.getName().toUpperCase().charAt(0)) % SIZE;
        if (table[index] == null) {
            Entry entry = new Entry(person.getName().toUpperCase().charAt(0), new MyArrayList<Person>());
            table[index] = entry;
        }
        table[index].getValue().add(person);
    }
    public MyArrayList<Person> getPersons(String firstlLetter) {
        int index = ((int) firstlLetter.toUpperCase().charAt(0)) % SIZE;
        return table[index] != null ? table[index].getValue() : null;
    }
}
