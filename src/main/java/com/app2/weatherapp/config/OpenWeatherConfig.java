package com.app2.weatherapp.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenWeatherConfig {

	 @Value("${openweather.api.key:YOUR_DEFAULT_API_KEY}") // Default value if not specified
	    private String apiKey;

	    @Value("${openweather.api.url:https://api.openweathermap.org/data/2.5/weather}") // Default URL
	    private String apiUrl;

	    // Constructor for dependency injection
	    public OpenWeatherConfig(@Value("${openweather.api.key:YOUR_DEFAULT_API_KEY}") String apiKey,
	                             @Value("${openweather.api.url:https://api.openweathermap.org/data/2.5/weather}") String apiUrl) {
	        this.apiKey = apiKey;
	        this.apiUrl = apiUrl;
	    }

	    public String getApiKey() {
	        return apiKey;
	    }

	    public String getApiUrl() {
	        return apiUrl;
	    }
}
