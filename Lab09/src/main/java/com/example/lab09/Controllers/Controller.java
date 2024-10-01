package com.example.lab09.Controllers;

import com.example.lab09.Models.BMR;
import com.example.lab09.Services.BMIService;
import com.example.lab09.Services.BMRService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api/v1",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_PLAIN_VALUE
        })
public class Controller {
    private final BMIService bmiService;
    private final BMRService bmrService;
    public Controller(BMIService bmiService, BMRService bmrService){
        this.bmiService = bmiService;
        this.bmrService = bmrService;
    }
    @GetMapping("/BMI")
    public ResponseEntity<?> getBMI(@RequestParam Double weight,
                                    @RequestParam Double height,
                                    @RequestHeader(value = HttpHeaders.ACCEPT) String accept) {
        if (weight <= 0 || height <= 0) {
            return ResponseEntity.badRequest().body("Invalid data, weight and height parameters must be positive numbers.");
        }

        if (accept != null && accept.contains(MediaType.TEXT_PLAIN_VALUE)) {
            return ResponseEntity.ok(String.format("%.2f",bmiService.calc(weight, height)));
        }
        return ResponseEntity.ok(bmiService.calculateBMI(weight, height));
    }
    @GetMapping("/BMR/{gender}")
    ResponseEntity<?> getBMR(@PathVariable String gender,
                             @RequestParam Double weight,
                             @RequestParam Double height,
                             @RequestParam Integer age,
                             @RequestHeader(value = HttpHeaders.ACCEPT) String accept){

        if(weight<=0 || height<=0 || age<=0){
            return ResponseEntity.status(499).body("invalid data, weight, height and age para meters must be positive numbers");
        }
        if(!gender.equals("man") && !gender.equals("woman")){
            return ResponseEntity.badRequest().body("invalid gender data");
        }
        BMR bmrdto = bmrService.calculateBMR(gender,weight,height,age);
        if(accept != null && accept.contains(MediaType.TEXT_PLAIN_VALUE)){
            return ResponseEntity.ok(bmrdto.getBmr().toString() + "kcal");
        }
        return ResponseEntity.ok(bmrService.calculateBMR(gender,weight,height,age));
    }
}
