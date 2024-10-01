package flashcardsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Loader implements CommandLineRunner {
    private final EntryRepo entryRepo;
    private final FileService fileService;

    @Autowired
    public Loader(EntryRepo entryRepo, FileService fileService) {
        this.entryRepo = entryRepo;
        this.fileService = fileService;
    }

    @Override
    public void run(String... args) {
        if (entryRepo.countEntries() == 0) {
            System.out.println("loaded");
            fileService.loadFromFile().forEach(entryRepo::addEntry);
        }
    }
}
