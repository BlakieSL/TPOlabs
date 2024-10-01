package com.example.lab05.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @PostMapping("/convert")
    @ResponseBody
    public String convert(@RequestParam String number, @RequestParam int source, @RequestParam int target) {
        try {
            int value = Integer.parseInt(number, source);
            String binary = Integer.toString(value, 2);
            String decimal = Integer.toString(value, 10);
            String hex = Integer.toString(value, 16).toUpperCase();
            String octal = Integer.toString(value, 8);
            String converted = Integer.toString(value, target);

            return "<!DOCTYPE html><html><body>" +
                    "<h1>Conversion Result</h1>" +
                    "<p>Binary: " + binary + "</p>" +
                    "<p>Decimal: " + decimal + "</p>" +
                    "<p>Hexadecimal: " + hex + "</p>" +
                    "<p>Octal: " + octal + "</p>" +
                    "<p>Converted base: " + target + ", result: " + converted + "</p>" +
                    "</body></html>";

        } catch (NumberFormatException e) {
            return "<!DOCTYPE html><html><body>" +
                    "<h1>Error</h1>" +
                    "</body></html>";
        }
    }
}
