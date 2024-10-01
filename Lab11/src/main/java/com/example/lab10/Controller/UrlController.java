package com.example.lab10.Controller;

import com.example.lab10.Model.UrlRequestDTO;
import com.example.lab10.Model.UrlResponseDTO;
import com.example.lab10.Service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Optional;

@Controller
public class UrlController {
    private final UrlService urlService;
    private final LocaleResolver localeResolver = new SessionLocaleResolver();

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("request", new UrlRequestDTO());
        return "index";
    }
    @GetMapping("/changeLocale")
    public String changeLocale(@RequestParam("choice") String choice, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Changing locale to: " + choice);
        Locale locale = Locale.forLanguageTag(choice);
        localeResolver.setLocale(request, response, locale);
        System.out.println("New locale: " + localeResolver.resolveLocale(request));
        return "redirect:/";
    }
    @PostMapping("/create")
    public String createUrl(@Valid @ModelAttribute("request") UrlRequestDTO urlRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "index";
        }
        UrlResponseDTO urlResponseDTO = urlService.saveUrl(urlRequestDTO);
        return "redirect:/created/" + urlResponseDTO.getUrlId();
    }
    @GetMapping("/created/{urlId}")
    public String checkUrl(@PathVariable String urlId, Model model){
        Optional<UrlResponseDTO> urlResponseDTO = urlService.getUrlById(urlId);
        if(!urlResponseDTO.isPresent()){
            return "redirect:/";
        }
        model.addAttribute("response", urlResponseDTO.get());
        model.addAttribute("request", new UrlRequestDTO());
        return "created";
    }

    @PostMapping("/created/{urlId}/update")
    public String updateUrl(@PathVariable String urlId, @Valid @ModelAttribute("request") UrlRequestDTO urlRequestDTO,BindingResult bindingResult, Model model){
        Optional<UrlResponseDTO> urlResponseDTO = urlService.getUrlById(urlId);
        if(!urlResponseDTO.isPresent()){
            return "redirect:/";
        }
        model.addAttribute("response", urlResponseDTO.get());
        if(bindingResult.hasErrors()){
            return "created";
        }
        if (urlRequestDTO.getPassword().equals("") || urlRequestDTO.getPassword() == null || !urlService.checkCorrectPassword(urlId, urlRequestDTO.getPassword())) {
            return "created";
        }

        urlService.updateUrl(urlRequestDTO, urlId);
        return "redirect:/created/"+urlId;
    }
    @PostMapping("/created/{urlId}/delete")
    public String deleteUrl(@PathVariable String urlId, @Valid @ModelAttribute("request") UrlRequestDTO urlRequestDTO,BindingResult bindingResult, Model model) {
        Optional<UrlResponseDTO> urlResponseDTO = urlService.getUrlById(urlId);
        if(!urlResponseDTO.isPresent()){
            return "redirect:/";
        }
        model.addAttribute("response", urlResponseDTO.get());
        if(bindingResult.hasErrors()){
            return "created";
        }
        if (urlRequestDTO.getPassword() == null || !urlService.checkCorrectPassword(urlId, urlRequestDTO.getPassword())) {
            return "created";
        }
        urlService.deleteUrl(urlId);
        return "redirect:/";
    }
}
