package com.example.flashcardsapp;

import org.springframework.stereotype.Controller;

import java.util.Scanner;
@Controller
public class FlashcardsController {
    Scanner scanner;
    LogicToUser logic;
    UserToLogic ulogic;
    ProfileInit profileInit;
    FileService fl;

    FlashcardsController(FileService fl, ProfileInit profileInit){
      this.scanner = new Scanner(System.in);
      this.fl = fl;
      this.logic = new EntryRepository(fl.loadFromFile());
      this.ulogic = fl;
      this.profileInit = profileInit;
    }
    public void start() {
      boolean running = true;
      while (running) {
        System.out.println("Menu:");
        System.out.println("a) add new");
        System.out.println("b) display all ");
        System.out.println("c) test");
        System.out.println("d) exit");
        System.out.print("Enter option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a" -> addNewWord();
            case "b" -> displayAllWords();
            case "c" -> testUser();
            case "d" -> running = false;
            default -> System.out.println("error");
        }
      }
    }
    private void displayAllWords(){
        logic.displayEntries(profileInit);
    }
    private void addNewWord(){
        System.out.println("Enter word: first english, german and then polish");
        String english = scanner.nextLine();
        String german = scanner.nextLine();
        String polish = scanner.nextLine();
        ulogic.addEntry(new Entry(english,german,polish));
        logic.getEntries().add(new Entry(english,german,polish));
    }
    private void testUser(){
        Entry entry = logic.getRandomEntry();
        if(entry == null){
            System.out.println("there is no entries");
            return;
        }
        String english = entry.getEnglish().toLowerCase();
        String german = entry.getGerman().toLowerCase();
        String polish = entry.getPolish().toLowerCase();
        System.out.println("Your word is: " + polish +", translate it into english and then into german");
        String uEng = scanner.nextLine().trim().toLowerCase();
        String uGer = scanner.nextLine().trim().toLowerCase();
        if(uEng.equals(english) && uGer.equals(german)){
            System.out.println("u are correct");
        } else{
            System.out.println("incorrect");
        }
    }
}
