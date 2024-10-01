package flashcardsapp;


import flashcardsapp.Profiles.ProfileInit;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Random;
@Repository
public class EntryRepo {
    private final EntityManager entityManager;
    public EntryRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public void addEntry(Entry entry){
        entityManager.persist(entry);
    }
    @Transactional
    public void displayEntries(ProfileInit init, String language, boolean ascending) {
        String order = ascending ? "ASC" : "DESC";
        String query = String.format("SELECT e FROM Entry e ORDER BY LOWER(e.%s) %s", language, order);

        List<Entry> entries = entityManager.createQuery(query, Entry.class).getResultList();
        for (Entry entry : entries) {
            System.out.println(init.display(entry.getEnglish()) + "; " +
                    init.display(entry.getGerman()) + "; " +
                    init.display(entry.getPolish()));
        }
    }
    @Transactional
    public Entry getRandomEntry() {
        List<Entry> entries = entityManager.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
        int index = new Random().nextInt(entries.size());
        return entries.get(index);
    }
    @Transactional(readOnly = true)
    public long countEntries() {
        return entityManager.createQuery("SELECT COUNT(e) FROM Entry e", Long.class).getSingleResult();
    }
    @Transactional
    public Entry searchEntryById(int id) {
        return entityManager.find(Entry.class, id);
    }
    @Transactional
    public void deleteEntry(int id) {
        Entry entry = entityManager.find(Entry.class, id);
        if (entry != null) {
            entityManager.remove(entry);
        }
    }
    @Transactional
    public void updateEntry(Entry updatedEntry) {
        Entry existingEntry = entityManager.find(Entry.class, updatedEntry.getId());
        if (existingEntry != null) {
            existingEntry.setEnglish(updatedEntry.getEnglish());
            existingEntry.setGerman(updatedEntry.getGerman());
            existingEntry.setPolish(updatedEntry.getPolish());
            entityManager.merge(existingEntry);
        }
    }
}
