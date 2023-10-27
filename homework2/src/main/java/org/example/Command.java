package org.example;

public interface Command {
    void getAverageScore(DataGroup dataGroup, int group);
    void getExcellentStudents(DataGroup dataGroup);
    void getLastNameStudents(DataGroup dataGroup);
}
