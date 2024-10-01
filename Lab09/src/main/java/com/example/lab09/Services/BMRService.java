package com.example.lab09.Services;

import com.example.lab09.Models.BMR;
import org.springframework.stereotype.Service;

@Service
public class BMRService {
    public BMR calculateBMR(String gender, double weight, double height, int age){
        double bmr = calc(gender, weight, height, age);
        return new BMR(gender, weight, height, age,(int)bmr);
    }
    public Double calc(String gender, double weight, double height, int age){
        double bmr = 0;
        if(gender.equals("man")){
            bmr = 88.362 + (13.397*weight) + (4.799*height) - (5.677*age);
        }else if(gender.equals("woman")){
            bmr = 447.593 + (9.247*weight) + (3.098*height) - (4.330*age);
        }
        return bmr;
    }
}
