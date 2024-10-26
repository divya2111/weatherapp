package com.app2.weatherapp.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherUpdateService {

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void updateDailyWeatherSummaries() {
        // Fetch data from OpenWeather API and save to the database
    }
}