package flashcardsapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {
    @Id
    private int id;
    String polish;
    String english;
    String german;
    public Entry() {
    }
    Entry(int id, String english, String german, String polish){
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.german = german;
    }
    public String getPolish(){
        return this.polish.toLowerCase();
    }
    public String getEnglish(){
        return this.english.toLowerCase();
    }
    public String getGerman(){
        return this.german.toLowerCase();
    }
    public int getId(){
        return this.id;
    }
    public void setEnglish(String eng){
        this.english = eng;
    }
    public void setPolish(String pln){
        this.polish = pln;
    }
    public void setGerman(String ger){
        this.german = ger;
    }
}
