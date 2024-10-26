package com.app2.weatherapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
	import java.util.Date;

	@Entity
	@Table(name = "daily_weather_summary")
	public class WeatherSummary {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @JsonIgnore
	    private Long id;
	    
	    @JsonIgnore
	    private String city;
	 // Specify that date should be stored as a date
	    @JsonIgnore
	    private String date;

	    private double avgTemp;
	    private double maxTemp;
	    private double minTemp;
	    private String dominantCondition;
	    
	    public WeatherSummary(double avgTemp, double maxTemp, double minTemp, String dominantCondition) {
	        this.avgTemp = avgTemp;
	        this.maxTemp = maxTemp;
	        this.minTemp = minTemp;
	        this.dominantCondition = dominantCondition;
	    }
	    public WeatherSummary() {
	        // Default constructor (can initialize with default values if needed)
	    }

	    // Getters and Setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }

	    public double getAvgTemp() {
	        return Math.round(avgTemp * 100.0) / 100.0;
	    }

	    public void setAvgTemp(double averageTemp) {
	        this.avgTemp = averageTemp-273.15;
	    }

	    public double getMaxTemp() {
	        return Math.round(maxTemp * 100.0) / 100.0 ;
	    }

	    public void setMaxTemp(double maxTemp) {
	        this.maxTemp = maxTemp-273.15;
	    }

	    public double getMinTemp() {
	        return Math.round(minTemp * 100.0) / 100.0 ;
	    }

	    public void setMinTemp(double minTemp) {
	        this.minTemp = minTemp-273.15;
	    }

	    public String getDominantCondition() {
	        return dominantCondition;
	    }

	    public void setDominantCondition(String dominantCondition) {
	        this.dominantCondition = dominantCondition;
	    }
	  
	    
}
