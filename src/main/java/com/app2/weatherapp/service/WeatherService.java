package com.app2.weatherapp.service;

import com.app2.weatherapp.model.DailyWeatherSummary;
import com.app2.weatherapp.model.WeatherSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherService  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String API_KEY = "717dab77c221f08585a5a61be1792cbf"; // Replace with your actual API key
    private final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid=" + API_KEY;

    public WeatherSummary getWeatherSummary(String city) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Fetch weather data from OpenWeather API
        WeatherResponse response = restTemplate.getForObject(API_URL, WeatherResponse.class, city);

        if (response != null && response.getMain() != null) {
            // Extract relevant information from the API response
            double avgTemp = response.getMain().getTemp(); // Assuming this is in Kelvin
            double minTemp = response.getMain().getTemp_min();
            double maxTemp = response.getMain().getTemp_max();
            String dominantCondition = response.getWeather().get(0).getDescription(); // First weather condition

            // Create a WeatherSummary object
            WeatherSummary weatherSummary = new WeatherSummary(maxTemp, maxTemp, maxTemp, dominantCondition);
            weatherSummary.setAvgTemp(avgTemp);
            weatherSummary.setMinTemp(minTemp);
            weatherSummary.setMaxTemp(maxTemp);
            weatherSummary.setDominantCondition(dominantCondition);
            // Add other necessary properties and setters

            return weatherSummary;
        }
        return null; 
    }


}
