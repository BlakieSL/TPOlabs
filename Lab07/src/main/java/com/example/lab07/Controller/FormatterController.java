package com.example.lab07.Controller;

import com.example.lab07.Model.Code;
import com.example.lab07.Service.FormatterService;
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
    public String showForm() {
        return "formatter";
    }

    @PostMapping
    public String formatCode(Model model,
                             @RequestParam("text") String text,
                             @RequestParam("id") String id,
                             @RequestParam("time") long time) {
        try {
            String output = formatterService.format(text);
            boolean saved = formatterService.addCode(id,text,time);
            if(saved) {
                model.addAttribute("input", text);
                model.addAttribute("output", output);
                return "formatter";
            }else{
                model.addAttribute("error","you have already formatted this ID");
                return "formatter";
            }
        } catch (Exception e) {
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
}