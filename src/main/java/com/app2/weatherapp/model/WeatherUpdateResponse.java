package com.app2.weatherapp.model;

import java.util.List;

public class WeatherUpdateResponse {
    private Main main;
    private Wind wind;
    private List<Weather> weather;
    private String dt_txt; // for timestamp

    // Getters and Setters
    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public String getDt_txt() { return dt_txt; }
    public void setDt_txt(String dt_txt) { this.dt_txt = dt_txt; }

    // Inner classes for Main, Wind, and Weather
    public static class Main {
        private double temp;
        private double feelsLike;
        private int humidity;

        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }

        public double getFeelsLike() { return feelsLike; }
        public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    public static class Wind {
        private double speed;

        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }
    }

    public static class Weather {
        private String description;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}

