package com.example.lab08.Services;

import com.example.lab08.Models.Code;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

import java.util.Optional;

@Service
public class FormatterService {
    private final String storagePath = "data";

    public FormatterService() {
        new File(storagePath).mkdir();
    }

    public String format(String input) throws FormatterException {
        return new Formatter().formatSource(input);
    }

    public boolean addCode(String id, String text, long time) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        Code code = new Code(id, text, now.plusSeconds(time));
        if (new File(storagePath, id + ".ser").exists()) {
            return false;
        }
        saveCodeToFile(code);
        return true;
    }

    public Optional<Code> findById(String id) {
        return loadCodeFromFile(id);
    }
    private ObjectOutputStream createObjectOutputStream(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        return new ObjectOutputStream(fos);
    }

    private ObjectInputStream createObjectInputStream(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        return new ObjectInputStream(fis);
    }
    private void saveCodeToFile(Code code) throws IOException {
        File file = new File(storagePath, code.getId() + ".ser");
        try (ObjectOutputStream out = createObjectOutputStream(file)) {
            out.writeObject(code);
        }
    }


    private Optional<Code> loadCodeFromFile(String id) {
        File file = new File(storagePath, id + ".ser");
        if (file.exists()) {
            try (ObjectInputStream in = createObjectInputStream(file)) {
                Code code = (Code) in.readObject();
                return Optional.of(code);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
    @Scheduled(fixedDelay = 1000)
    public void deleteExpired() {
        File folder = new File(storagePath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                Code code = loadCodeFromFile(file.getName().replace(".ser", "")).orElse(null);
                if (code != null && code.getTillDate().isBefore(LocalDateTime.now())) {
                    file.delete();
                    System.out.println("Removed");
                }
            }
        }
    }

}