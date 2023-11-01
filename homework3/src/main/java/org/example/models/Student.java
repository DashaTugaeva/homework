package org.example.models;

import java.util.List;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long studyGroupId;
    private List<AcademicRecord> academicRecords;

    public Student(Long id, String firstName, String lastName, Integer age, Long studyGroupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studyGroupId = studyGroupId;
    }

    public Student(String firstName, String lastName, Integer age, Long studyGroupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studyGroupId = studyGroupId;
    }
    public Student(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(Long studyGroupId) {
        this.studyGroupId = studyGroupId;
    }


    @Override
    public String toString() {
        return  firstName + " " +
                lastName + ", " +
                age + " лет";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
