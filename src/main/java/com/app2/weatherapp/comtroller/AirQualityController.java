
package com.app2.weatherapp.comtroller;

import com.app2.weatherapp.model.AirQuality;
import com.app2.weatherapp.service.AirQualityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Summaryofweather.jsp")
public class AirQualityController {

    private final AirQualityService airQualityService;
    @Autowired
    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }
    
    @GetMapping("/{city}/{days}")
    public String getWeatherSummary(@PathVariable String city, @PathVariable int days, Model model) {
        List<AirQuality> weatherDataList = airQualityService.getWeatherUpdates(city, days);
        model.addAttribute("weatherDataList", weatherDataList);
        return "Summaryofweather"; // Return the JSP name without .jsp extension
    }
    
    @GetMapping("/api/weather/{city}/{days}") // Changed the path to an API endpoint
    @ResponseBody
    public List<AirQuality> getWeatherSummaryJson(@PathVariable String city, @PathVariable int days) {
        return airQualityService.getWeatherUpdates(city, days);
    }

}