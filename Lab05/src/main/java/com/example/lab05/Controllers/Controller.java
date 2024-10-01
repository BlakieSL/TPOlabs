package com.example.lab05.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/current-time")
    @ResponseBody
    public String getTime( @RequestParam(required = false) String zone, @RequestParam(required = false) String format){

        if (format == null || format.isEmpty()) {
            format = "HH:mm:ss.SSSS YYYY/MM/DD";
        }
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(format);
        ZoneId zoneId;
        if(zone != null && !zone.isEmpty()) {
            try{
                zoneId = ZoneId.of(zone);
                System.out.println(zone);
                System.out.println(zoneId);
            }catch(DateTimeException e){
                zoneId = ZoneId.systemDefault();
                return dateTime.withZoneSameInstant(zoneId).format(myFormatObj) + "Error";
            }
        }else{
            zoneId = ZoneId.systemDefault();
        }
        String formattedDateTime = dateTime.withZoneSameInstant(zoneId).format(myFormatObj);
        return "<!DOCTYPE html>" +
                "<html><head><title>Current Time</title><style>" +
                "body { font-family: Arial; background-color: #ffffff; color: #000000; }" +
                "h1 { color: #F9003C; }" +
                "p { font-size: 20px; }" +
                "</style></head><body>" +
                "<h1>Current Time</h1>" +
                "<p>Current Time: " + formattedDateTime + "</p>" +
                "<p>Time Zone: " + zoneId.getId() + "</p>" +
                "</body></html>";
    }
}
