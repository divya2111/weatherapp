package com.app2.weatherapp.service;

import com.app2.weatherapp.model.AlertThreshold;
import com.app2.weatherapp.repository.AlertThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertThresholdRepository alertThresholdRepository;

    public void addAlert(AlertThreshold alertThreshold) {
        alertThresholdRepository.save(alertThreshold);
    }

    public List<AlertThreshold> getAlertsByCity(String city) {
        return alertThresholdRepository.findByCity(city);
    }

	public List<AlertThreshold> getAllAlerts() {
		// TODO Auto-generated method stub
		return alertThresholdRepository.findAll();
	}
}