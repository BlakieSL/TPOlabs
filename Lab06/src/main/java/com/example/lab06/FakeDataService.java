package com.example.lab06;

import com.github.javafaker.Faker;

import java.util.*;

@org.springframework.stereotype.Service
public class FakeDataService {
    public List<Person> generatePersonList(int amount, String language, Set<String> additionalInformation){
    List<Person> personList = new ArrayList<Person>();
    Faker faker = new Faker(Locale.of(language));
    for(int i = 0; i < amount; i++){
        String name = faker.name().firstName();
        String surname =faker.name().lastName();
        String dateOfBirth = String.valueOf(faker.date().birthday());

        Person person = new Person(name, surname,dateOfBirth);

        if(additionalInformation != null) {
            Map<String, String> additional = new HashMap<>();
            for (String atribute : additionalInformation) {
                switch (atribute) {
                    case "address" -> {
                        String address = faker.address().fullAddress();
                        additional.put("address", address);
                    }
                    case "university" -> {
                        String university = faker.university().name();
                        additional.put("university", university);
                    }
                    case "country" -> {
                        String country = faker.country().name();
                        additional.put("countryOfOrigin", country);
                    }
                    case "animal" -> {
                        String animal = faker.animal().name();
                        additional.put("animal", animal);
                    }
                    case "artist" -> {
                        String artist = faker.artist().name();
                        additional.put("artist", artist);
                    }
                    case "book" -> {
                        String book = faker.book().title();
                        additional.put("book", book);
                    }
                    case "color" -> {
                        String color = faker.color().name();
                        additional.put("color", color);
                    }
                    case "food" -> {
                        String food = faker.food().dish();
                        additional.put("food", food);
                    }
                }
            }
            person.setAdditionalData(additional);
        }
        personList.add(person);
    }
    return personList;
    }
}
