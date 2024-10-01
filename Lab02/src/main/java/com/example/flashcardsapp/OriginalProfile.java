package com.example.flashcardsapp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Original")
public class OriginalProfile implements ProfileInit{
    @Override
    public String display(String word) {
        return word;
    }
}
