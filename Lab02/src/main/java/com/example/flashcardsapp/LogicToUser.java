package com.example.flashcardsapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface LogicToUser {
     void displayEntries(ProfileInit init);
     Entry getRandomEntry();
     ArrayList<Entry> getEntries();
}
