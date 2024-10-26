package com.app2.weatherapp.model;

import javax.persistence.*;

@Entity
@Table(name = "alert_thresholds")
public class AlertThreshold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private double temperatureThreshold;
    private boolean alertActive;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getTemperatureThreshold() {
		return temperatureThreshold;
	}
	public void setTemperatureThreshold(double temperatureThreshold) {
		this.temperatureThreshold = temperatureThreshold;
	}
	public boolean isAlertActive() {
		return alertActive;
	}
	public void setAlertActive(boolean alertActive) {
		this.alertActive = alertActive;
	}

    // Getters and Setters
}
