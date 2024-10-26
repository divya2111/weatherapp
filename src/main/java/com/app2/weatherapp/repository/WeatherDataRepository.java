package com.app2.weatherapp.repository;
import com.app2.weatherapp.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
 
}
