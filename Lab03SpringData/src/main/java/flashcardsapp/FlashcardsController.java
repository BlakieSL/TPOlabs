package flashcardsapp;

import flashcardsapp.Profiles.ProfileInit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class FlashcardsController {
    Scanner scanner;
    ProfileInit profileInit;
    FileService fl;
    Random random = new Random();
    SpringDataEntryRepository repository;

    FlashcardsController(FileService fl, ProfileInit profileInit, SpringDataEntryRepository repository){
      this.scanner = new Scanner(System.in);
      this.fl = fl;
      this.profileInit = profileInit;
      this.repository = repository;
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
    public void displayAllWords() {
        System.out.println("Do u wanna sort a list? 1 - yes, 2 - no");
        String choice1 = scanner.nextLine();

        if ("1".equals(choice1)) {
            System.out.println("By what language to sort? 1 - English, 2 - German, else polish");
            String choice2 = scanner.nextLine();
            Comparator<Entry> comparator;

            switch (choice2) {
                case "1" -> comparator = Comparator.comparing(Entry::getEnglish);
                case "2" -> comparator = Comparator.comparing(Entry::getGerman);
                default -> comparator = Comparator.comparing(Entry::getPolish);

            }
            System.out.println("Do u wanna sort ascending or descending? 1 - asc, 2 - desc");
            boolean ascending = "1".equals(scanner.nextLine());

            if (!ascending) {
                comparator = comparator.reversed();
            }

            ArrayList<Entry> entries = new ArrayList<>();
            repository.findAll().forEach(entries::add);
            entries.sort(comparator);

            for (Entry entry : entries) {
                System.out.println(profileInit.display(entry.getEnglish()) + "; " +
                        profileInit.display(entry.getGerman()) + "; " +
                        profileInit.display(entry.getPolish()));
            }
        } else {
            for (Entry entry : repository.findAll()) {
                System.out.println(profileInit.display(entry.getEnglish()) + "; " +
                        profileInit.display(entry.getGerman()) + "; " +
                        profileInit.display(entry.getPolish()));
            }
        }
    }
    private void addNewWord(){
        System.out.println("Enter word: first english, german and then polish");
        String english = scanner.nextLine();
        String german = scanner.nextLine();
        String polish = scanner.nextLine();
        repository.save(new Entry(random.nextInt(),english,german,polish));
    }
    private void testUser(){
        List<Entry> entries = (List<Entry>) repository.findAll();
        int index = new Random().nextInt(entries.size());
        Entry entry = entries.get(index);


        String english = entry.getEnglish();
        String german = entry.getGerman();
        String polish = entry.getPolish();
        System.out.println("Your word is: " + polish + ", translate it into English and then into German");
        String uEng = scanner.nextLine().trim().toLowerCase();
        String uGer = scanner.nextLine().trim().toLowerCase();

        if(uEng.equals(english) && uGer.equals(german)){
            System.out.println("You are correct.");
        } else {
            System.out.println("Incorrect.");
        }
    }
    private void searchForWord(){
        System.out.println("Enter id of word: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Entry> entryopt = repository.findById(id);
        Entry entry = entryopt.get();
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

        Optional<Entry> entryOpt = repository.findById(id);
        Entry entry = entryOpt.get();

        entry.setEnglish(eng);
        entry.setGerman(ger);
        entry.setPolish(pln);


        repository.save(entry);
    }
    private void deleteWord(){
        System.out.println("Enter id of word:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Entry> entryOpt = repository.findById(id);
        Entry entry = entryOpt.get();
        repository.delete(entry);

    }
}
