package org.example.models;

import java.util.List;

public class StudyPlan {
    private Long id;
    private String name;
    private List<AcademicRecord> academicRecords;

    public StudyPlan(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public StudyPlan(String name) {
        this.name = name;
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
