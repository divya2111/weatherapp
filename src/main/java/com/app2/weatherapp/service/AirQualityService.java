package com.app2.weatherapp.service;

import com.app2.weatherapp.model.AirQuality;
import com.app2.weatherapp.model.WeatherUpdateResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityService {

    private final RestTemplate restTemplate;
    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    public AirQualityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AirQuality> getWeatherUpdates(String city, int days) {
        List<AirQuality> weatherDataList = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
            WeatherUpdateResponse response = restTemplate.getForObject(url, WeatherUpdateResponse.class);
            if (response != null) {
                AirQuality weather = new AirQuality();
                weather.setCity(city);
                weather.setTemperature(response.getMain().getTemp());
                weather.setFeelsLike(response.getMain().getFeelsLike());
                weather.setMainCondition(response.getWeather().get(0).getDescription());
                weather.setHumidity(response.getMain().getHumidity());
                weather.setWindSpeed(response.getWind().getSpeed());
                weather.setTimestamp(response.getDt_txt());
                weatherDataList.add(weather);
            }
        }
        return weatherDataList;
    }

}
