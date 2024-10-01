package com.example.lab09.Services;

import com.example.lab09.Models.BMI;
import org.springframework.stereotype.Service;

@Service
public class BMIService {
    public Double calc(double weight, double height){
        return (weight/Math.pow(height/100, 2));
    }
    public BMI calculateBMI(double weight, double height){
        double bmi = calc(weight, height);
        String type = classify(bmi);
        return new BMI(weight, height, (int) bmi, type);
    }
    public String classify(double bmi){
        String type = "";
       if(bmi<16){
           type = "underweight (Severe thinness)";
       }else if(16<bmi && bmi<16.9){
            type = "Underweight (Moderate thinness)";
       }else if (17.0<bmi && bmi<18.4){
           type = "Underweight (Mild thinness)";
       }else if (18.5<bmi && bmi<24.9){
           type = "Normal";
       }else if(25.0<bmi && bmi<29.9){
           type = "Overweight (Pre-obese)";
       }else if(30.0<bmi && bmi<34.9){
           type = "Obese(|)";
       }else if (35.0<bmi && bmi<39.9){
           type = "Obese(||)";
       }else if(40.0<bmi){
           type = "Obese(|||)";
       }
       return type;
    }
}
