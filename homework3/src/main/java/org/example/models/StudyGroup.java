package org.example.models;

import java.util.List;

public class StudyGroup {
    private Long id;
    private String name;

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudyGroup(List<Student> students) {
        this.students = students;
    }

    public StudyGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudyGroup(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public StudyGroup() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
