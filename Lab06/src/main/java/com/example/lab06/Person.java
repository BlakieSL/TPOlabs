package com.example.lab06;

import java.util.Date;
import java.util.Map;

public class Person {
    private final String name;
    private final String surname;
    private final String dateBirth;
    private Map<String, String> additionalData;
    public Person(String name, String surname, String dateBirth) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }
    public void setAdditionalData(Map<String, String> additionalData) {
        this.additionalData = additionalData;
    }
}
