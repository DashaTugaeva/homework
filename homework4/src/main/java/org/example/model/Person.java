package org.example.model;

public class Person {
    private String name;
    private int age;
    private int group;
    private int physicsScore;
    private int mathematicsScore;
    private int rusScore;
    private int literatureScore;
    private int geometryScore;
    private int informaticsScore;
    private double averageScore;

    public int getPhysicsScore() {
        return physicsScore;
    }

    public void setPhysicsScore(int physicsScore) {
        this.physicsScore = physicsScore;
    }

    public int getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(int mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public int getRusScore() {
        return rusScore;
    }

    public void setRusScore(int rusScore) {
        this.rusScore = rusScore;
    }

    public int getLiteratureScore() {
        return literatureScore;
    }

    public void setLiteratureScore(int literatureScore) {
        this.literatureScore = literatureScore;
    }

    public int getGeometryScore() {
        return geometryScore;
    }

    public void setGeometryScore(int geometryScore) {
        this.geometryScore = geometryScore;
    }

    public int getInformaticsScore() {
        return informaticsScore;
    }

    public void setInformaticsScore(int informaticsScore) {
        this.informaticsScore = informaticsScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }



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


    @Override
    public String toString() {
        return String.format(String.format(
                 name + ", " +
                 age + " лет, " +
                 group + " класс, " +
                 "средний балл: " + String.format("%.1f",averageScore)));
    }
}
