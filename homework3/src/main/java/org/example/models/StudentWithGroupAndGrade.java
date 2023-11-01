package org.example.models;

public class StudentWithGroupAndGrade {
    private String firstName;
    private String lastName;
    private Integer age;
    private String group;
    private Double averageGrade;

    @Override
    public String toString() {
        return  firstName + " " + lastName + ", " +
                age + " лет, " +
                group + " класс, " +
                "средний балл: " + String.format("%.2f", averageGrade);
    }

    public StudentWithGroupAndGrade(String firstName, String lastName, Integer age, String group, Double averageGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.group = group;
        this.averageGrade = averageGrade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
