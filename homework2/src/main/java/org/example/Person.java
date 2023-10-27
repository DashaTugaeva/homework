package org.example;

public class Person {
    private String name;
    private int age;
    private int group;
    private double averageRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return String.format(String.format(
                 name + ", " +
                 age + " лет, " +
                 group + " класс, " +
                 "средний балл: " + String.format("%.1f",averageRating)));
    }
}
