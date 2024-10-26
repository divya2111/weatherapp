package com.app2.weatherapp.service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WeatherResponse {
	@JsonProperty("main")
    private Main main;

    @JsonProperty("weather")
    private List<Weather> weather;

    // Getters and setters
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}

class Main {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("temp_min")
    private double temp_min;

    @JsonProperty("temp_max")
    private double temp_max;

    // Getters and setters
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}

class Weather {
    @JsonProperty("description")
    private String description;

    // Getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
