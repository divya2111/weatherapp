package com.app2.weatherapp.service;

import com.app2.weatherapp.config.OpenWeatherConfig;
import com.app2.weatherapp.model.WeatherInfo;
import com.app2.weatherapp.model.WeatherS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceSummary {

	private final RestTemplate restTemplate;
    private final OpenWeatherConfig openWeatherConfig;// Replace with your actual API key.

    @Autowired
    public WeatherServiceSummary(RestTemplate restTemplate, OpenWeatherConfig openWeatherConfig) {
        this.restTemplate = restTemplate;
        this.openWeatherConfig = openWeatherConfig;
    }
    
    public List<WeatherS> getWeatherUpdates(String city, int days) {
        List<WeatherS> weatherList = new ArrayList<>();
        // API URL with query parameters for the city and API key
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, openWeatherConfig.getApiKey());
        
        try {
            // Make the API call and map the response to WeatherInfo
            WeatherInfo response = restTemplate.getForObject(url, WeatherInfo.class);

            if (response != null && response.getMain() != null && response.getWeather() != null && !response.getWeather().isEmpty()) {
                WeatherS weather = new WeatherS();
                weather.setCity(city);
                weather.setDay("Today"); // You can modify this if necessary
                weather.setTemperature(response.getMain().getTemp());
                weather.setHumidity(response.getMain().getHumidity());
                weather.setWindSpeed(response.getWind().getSpeed());
                weather.setCondition(response.getWeather().get(0).getDescription());
                weatherList.add(weather);
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching weather data for " + city + ": " + e.getMessage());
        }
        return weatherList;
    }
}
