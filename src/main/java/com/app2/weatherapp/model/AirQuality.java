package com.app2.weatherapp.model;

public class AirQuality {
    private String city;
    private double temperature;
    private double feelsLike;
    private String mainCondition;
    private int humidity;
    private double windSpeed;
    private String timestamp;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	public String getMainCondition() {
		return mainCondition;
	}
	public void setMainCondition(String mainCondition) {
		this.mainCondition = mainCondition;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

    // Getters and setters
}
