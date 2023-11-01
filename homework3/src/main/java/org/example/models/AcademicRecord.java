package org.example.models;

public class AcademicRecord {
    private Long id;
    private Integer assessment;
    private Long studentId;
    private Long studyPlanId;

    public AcademicRecord(Long id, Integer assessment, Long studentId, Long studyPlanId) {
        this.id = id;
        this.assessment = assessment;
        this.studentId = studentId;
        this.studyPlanId = studyPlanId;
    }
    public AcademicRecord(Integer assessment, Long studentId, Long studyPlanId) {
        this.assessment = assessment;
        this.studentId = studentId;
        this.studyPlanId = studyPlanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getStudyPlanId() {
        return studyPlanId;
    }

}
