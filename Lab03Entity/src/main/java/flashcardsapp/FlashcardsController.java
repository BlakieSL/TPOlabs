package flashcardsapp;

import flashcardsapp.Profiles.ProfileInit;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.Scanner;
@Controller
public class FlashcardsController {
    Scanner scanner;
    ProfileInit profileInit;
    FileService fl;
    Random random = new Random();
    EntryRepo entryRepo;

    FlashcardsController(FileService fl, ProfileInit profileInit, EntryRepo entryRepo){
      this.scanner = new Scanner(System.in);
      this.fl = fl;
      this.profileInit = profileInit;
      this.entryRepo = entryRepo;
    }
    public void start() {
      boolean running = true;
      while (running) {
        System.out.println("Menu:");
        System.out.println("a) add new");
        System.out.println("b) display all ");
        System.out.println("c) test");
        System.out.println("d) search for word");
        System.out.println("e) delete word");
        System.out.println("f) update word");
        System.out.println("g) exit");
        System.out.print("Enter option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a" -> addNewWord();
            case "b" -> displayAllWords();
            case "c" -> testUser();
            case "d" -> searchForWord();
            case "e" -> deleteWord();
            case "f" -> updateEntry();
            case "g" -> running = false;
            default -> System.out.println("error");
        }
      }
    }
    private void displayAllWords(){
        entryRepo.displayEntries(profileInit, "polish", true);
        System.out.println("Do u wanna sort a list? 1 - yes, 2 - no");
        int choice1 = scanner.nextInt();
        scanner.nextLine();
        if(choice1 ==  1){
            System.out.println("Do u wanna sort asc or dsc? 1 - asc, 2 - desc" );
            boolean sortType = scanner.nextInt() == 1;//if 2 desc
            scanner.nextLine();
            String language="";
            System.out.println("By what language to sort? 1 - ang, 2 - ger, 3 - pln");
            int choice2 = scanner.nextInt();
            scanner.nextLine();
            switch (choice2) {
                case 1 -> language = "english";
                case 2 -> language = "german";
                case 3 -> language = "polish";
                default -> System.out.println("Invalid option, defaulting to Polish.");
            }
        entryRepo.displayEntries(profileInit,language,sortType);
        }
    }
    private void addNewWord(){
        System.out.println("Enter word: first english, german and then polish");
        String english = scanner.nextLine();
        String german = scanner.nextLine();
        String polish = scanner.nextLine();
        entryRepo.addEntry(new Entry(random.nextInt(),english,german,polish));
    }
    private void testUser(){
        Entry entry = entryRepo.getRandomEntry();
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
    private void searchForWord(){
        System.out.println("Enter id of word: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Entry entry = entryRepo.searchEntryById(id);
        System.out.println(entry.getEnglish() + " " + entry.getGerman() + " " + entry.getPolish());
    }
    private void updateEntry(){
        System.out.println("write id of entry u wanna update");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("write new eng, then german, then polish");
        String eng = scanner.nextLine();

        String ger = scanner.nextLine();

        String pln = scanner.nextLine();

        entryRepo.updateEntry(new Entry(id,eng,ger,pln));
    }
    private void deleteWord(){
        System.out.println("Enter id of word:");
        int id = scanner.nextInt();
        scanner.nextLine();
        entryRepo.deleteEntry(id);
    }
}
