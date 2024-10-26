package com.app2.weatherapp.repository;

import com.app2.weatherapp.model.AlertThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertThresholdRepository extends JpaRepository<AlertThreshold, Long> {
    List<AlertThreshold> findByCity(String city);
}
