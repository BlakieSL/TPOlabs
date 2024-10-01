package com.example.flashcardsapp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Random;
@Repository
public class EntryRepository implements LogicToUser{
    ArrayList<Entry> entries;
    EntryRepository(ArrayList<Entry> entries) {
        this.entries = entries;
    }
    @Override
    public void displayEntries(ProfileInit init){
        if(entries.size()>0){
            for(Entry entry : entries){
                System.out.println(init.display(entry.getPolish()) +"; " +
                                   init.display(entry.getEnglish()) + "; " +
                                   init.display(entry.getGerman()));}
        }else{
            System.out.println("No entries");
        }
    }
    @Override
    public Entry getRandomEntry(){
        if (entries.isEmpty()) {
            return null;
        }
        int index = new Random().nextInt(entries.size());
        return entries.get(index);
    }

    @Override
    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
