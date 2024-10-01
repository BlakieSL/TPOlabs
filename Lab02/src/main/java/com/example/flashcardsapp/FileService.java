package com.example.flashcardsapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
@Service
public class FileService implements UserToLogic {
    String filename;

    public FileService(@Value("${pl.edu.pja.tpo02.filename}") String filename){
        this.filename = filename;
    }
    public ArrayList<Entry> loadFromFile(){
        ArrayList<Entry> entries = new ArrayList<Entry>();
        String line;
        String[]data;

        try(BufferedReader reade = new BufferedReader(new FileReader(filename))){
            while ((line = reade.readLine()) != null){
                data = line.split(";");
                Entry entry = new Entry(data[0], data[1], data[2]);
                entries.add(entry);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return  entries;
    }
    @Override
    public void addEntry(Entry entry) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            out.println(entry.getPolish() + ";" + entry.getEnglish() + ";" + entry.getGerman());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
