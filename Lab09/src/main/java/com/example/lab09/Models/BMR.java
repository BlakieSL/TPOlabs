package com.example.lab09.Models;

public class BMR {
    private String gender;
    private Double weight;
    private Double height;
    private Integer age;
    private Integer bmr;

    public BMR(String gender, Double weight, Double height, Integer age, Integer bmr) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmr = bmr;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getBmr() {
        return bmr;
    }

    public void setBmr(Integer bmr) {
        this.bmr = bmr;
    }
}
