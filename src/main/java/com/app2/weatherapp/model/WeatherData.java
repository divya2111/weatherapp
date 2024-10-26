package com.app2.weatherapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key in the database

    private String city;
    private double temp;
    private double feelsLike;
    private String mainCondition;
    private String description; 
    private int humidity; 
    private double windSpeed; 
    private long timestamp;
	private long lastupdate;
	

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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

    public void setMainCondition(String main) {
        this.mainCondition = main;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(long lastupdate) {
		this.lastupdate = lastupdate;
	}
}
