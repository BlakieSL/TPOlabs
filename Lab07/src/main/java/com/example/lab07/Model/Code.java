package com.example.lab07.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Code implements Serializable {

    private String id;
    private String text;
    private LocalDateTime tillDate;

    public Code(String id,String text,  LocalDateTime tillDate) {
        this.text = text;
        this.id = id;
        this.tillDate = tillDate;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void setTillDate(LocalDateTime tillDate) {
        this.tillDate = tillDate;
    }

    public String getText() {
        return text;
    }


    public LocalDateTime getTillDate() {
        return tillDate;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
