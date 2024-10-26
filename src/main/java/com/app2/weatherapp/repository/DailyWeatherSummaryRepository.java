package com.app2.weatherapp.repository;
import com.app2.weatherapp.model.DailyWeatherSummary;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherSummaryRepository extends JpaRepository<DailyWeatherSummary, Long> {
   List<DailyWeatherSummary> findByCityAndDate(String city, Date date);
   List<DailyWeatherSummary> findByDate(Date date); // New method to fetch summaries for all cities on a specific date
   List<DailyWeatherSummary> findByCity(String city);
}
