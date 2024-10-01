package com.example.flashcardsapp;

public class Entry {
    String polish;
    String english;
    String german;
    Entry(String english, String german, String polish){
        this.polish = polish;
        this.english = english;
        this.german = german;
    }
    public String getPolish(){
        return this.polish;
    }
    public String getEnglish(){
        return this.english;
    }
    public String getGerman(){
        return this.german;
    }
}
