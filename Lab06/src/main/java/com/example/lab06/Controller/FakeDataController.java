package com.example.lab06.Controller;

import org.springframework.ui.Model;
import com.example.lab06.FakeDataService;
import com.example.lab06.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller

public class FakeDataController {

    private final FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String handleFormAndData(@RequestParam(required = false) Integer amount,
                                    @RequestParam(required = false) String language,
                                    @RequestParam(required = false) List<String> additionalInformation,
                                    Model model) {
        model.addAttribute("languages", List.of("en", "de", "ca",
                "pl", "ru", "he", "hu", "it", "pt", "uk"));
        if (amount != null && language != null) {

            Set<String> addition = new HashSet<>();
            if (additionalInformation != null) {
                addition.addAll(additionalInformation);
            }
            List<Person> personList = fakeDataService.generatePersonList(amount, language, addition);
            model.addAttribute("personList", personList);

        }
        return "file1";
    }
}
