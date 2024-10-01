package com.example.lab08.Controllers;

import com.example.lab08.Models.Code;
import com.example.lab08.Services.FormatterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class FormatterController {

    private final FormatterService formatterService;

    public FormatterController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("title", "FormatterHead");
        model.addAttribute("header", "Header");
        model.addAttribute("footer", "Footer");
        return "formatter";
    }

    @PostMapping
    public String handleCode(Model model,
                             @RequestParam("text") String text,
                             @RequestParam("action") String action,
                             @RequestParam("id") Optional<String> id,
                             @RequestParam("days") Optional<Integer> days,
                             @RequestParam("hours") Optional<Integer> hours,
                             @RequestParam("minutes") Optional<Integer> minutes,
                             @RequestParam("seconds") Optional<Integer> seconds) {
        try {
            String output = formatterService.format(text);
            model.addAttribute("input", text);
            model.addAttribute("output", output);
            model.addAttribute("header", "HeaderFormatted");
            model.addAttribute("footer", "FooterFormatted");
            if ("save".equals(action)) {
                long totalSeconds = days.orElse( 0) * 86400 + hours.orElse(0) * 3600 + minutes.orElse(0) * 60 + seconds.orElse(0);
                if (id.isPresent() && totalSeconds >= 10 && totalSeconds <= 7776000) {
                    boolean saved = formatterService.addCode(id.get(), output, totalSeconds);
                    if (saved) {
                        model.addAttribute("message", "code was saved, duration: " +totalSeconds);

                    } else {
                        model.addAttribute("error", "failed to save");
                    }
                } else {
                    model.addAttribute("error", "invalid time provided");
                }
            }
            return "formatter";
        }  catch (Exception e) {
            return "redirect:/error";
        }
    }
    @GetMapping("/findCode")
    public String findCodeID(@RequestParam("id") String id, Model model) {
        Optional<Code> code = formatterService.findById(id);
        if (code.isPresent()) {
            model.addAttribute("code", code.get().getText());
        } else {
            model.addAttribute("error", "there is no code with such id");
        }
        return "formatter";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}