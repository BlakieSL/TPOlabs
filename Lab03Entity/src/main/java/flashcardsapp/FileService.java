package flashcardsapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

@Service
public class FileService {
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
                Random random = new Random();
                Entry entry = new Entry(random.nextInt(),data[0], data[1], data[2]);
                entries.add(entry);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return  entries;
    }

}
