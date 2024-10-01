package com.example.lab07.Service;

import com.example.lab07.Model.Code;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FormatterService {
    private final Map<String, Code> codes = new HashMap<>() {
    };


    public String format(String input) throws FormatterException {
        return new Formatter().formatSource(input);
    }

    public boolean addCode(String id, String text, long time){
        LocalDateTime now = LocalDateTime.now();
        Code code = new Code(id, text, now.plusMinutes(time));
        if (codes.containsKey(code.getId())) {
            return false;
        }
        codes.put(code.getId(), code);
        return true;
    }
    public Optional<Code> findById(String id){
        return  Optional.ofNullable(codes.get(id));
    }

    @Scheduled(fixedDelay = 1000)
    public void deleteExpired(){
        ArrayList<String> temp = new ArrayList<>();
        for (Map.Entry<String, Code> entry : codes.entrySet()) {
            if (entry.getValue().getTillDate().isBefore(LocalDateTime.now())) {
                temp.add(entry.getKey());
                System.out.println("removed");
            }
        }
        temp.forEach(codes::remove);
    }
}
