package flashcardsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Loader implements CommandLineRunner {
    private final SpringDataEntryRepository repository;
    private final FileService fileService;

    @Autowired
    public Loader(SpringDataEntryRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            System.out.println("loaded");
            repository.saveAll(fileService.loadFromFile());
        }
    }
}
