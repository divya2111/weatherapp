package com.app2.weatherapp.comtroller;

import com.app2.weatherapp.model.DailyWeatherSummary;
import com.app2.weatherapp.model.WeatherData;
import com.app2.weatherapp.model.WeatherSummary;
import com.app2.weatherapp.service.WeatherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
//@RestController
//@RequestMapping("/weather")
public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	 public WeatherController(WeatherService weatherService) {
	        this.weatherService = weatherService;
	    }


    @Value("${openweather.api.key}") // Ensure this is correctly set in application.properties
    private String apiKey;

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";
    private static int idCounter = 0; // For generating sequential IDs
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/")
    public String home() {
        return "index"; // Assumes there's an index.jsp file in /WEB-INF/views/
    }

    @GetMapping("/weatherPage")
    public String showWeatherPage() {
        return "weather"; // This returns /WEB-INF/views/weather.jsp
    }

    @GetMapping("/summary")
    public String showSummaryPage() {
        return "summary"; // This returns /WEB-INF/views/summary.jsp
    }

    @GetMapping("/alerts")
    public String showAlertsPage() {
        return "alerts"; // This returns /WEB-INF/views/alerts.jsp
    }

    @GetMapping("/weather.jsp/{city}")
    public String getWeatherData(@PathVariable("city") String city, Model model) {
        if (city != null && !city.isEmpty()) {
            // Add city to the model
            model.addAttribute("city", city);

            // Fetch weather data for the selected city
            String url = String.format(WEATHER_URL, city + ",IN", apiKey);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                WeatherData weather = extractWeatherData(response.getBody(), city);
                model.addAttribute("weather", weather); // Pass weather data to the JSP
            } else {
                model.addAttribute("error", "Unable to fetch weather data for " + city);
            }
        } else {
            model.addAttribute("error", "City not selected");
        }

        return "weather"; // Return to weather.jsp with weather and city data
    }
    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherData> getWeather(@PathVariable String city) {
        // Correctly format the URL with the city and API key
        String url = String.format(WEATHER_URL, city + ",IN", apiKey);
        System.out.println("Fetching weather data from URL: " + url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response;

        try {
            response = restTemplate.getForEntity(url, Map.class);

            // Check if the response status code indicates success
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                WeatherData weather = extractWeatherData(response.getBody(), city);
                return ResponseEntity.ok(weather);
            } else {
                logErrorResponse(response);
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            System.err.println("Exception occurred while fetching weather data: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
   
    private void storeWeatherDataInDatabase(WeatherData weather) {
        String sql = "INSERT INTO weather_data (city, temp, feels_like, main_condition, description, humidity, wind_speed, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, weather.getCity(), weather.getTemp(), weather.getFeelsLike(),
                            weather.getMainCondition(), weather.getDescription(), 
                            weather.getHumidity(), weather.getWindSpeed(), weather.getTimestamp());
    }


    private WeatherData extractWeatherData(Map<String, Object> weatherApiResponse, String city) {
        WeatherData weather = new WeatherData();
        weather.setId(++idCounter);
        weather.setCity(city);

        // Extract main weather data
        Map<String, Object> main = (Map<String, Object>) weatherApiResponse.get("main");
        if (main != null) {
            weather.setTemp((Double) main.get("temp"));
            weather.setFeelsLike((Double) main.get("feels_like"));
            weather.setHumidity((Integer) main.get("humidity")); // New field for humidity
        }

        // Extract weather conditions
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) weatherApiResponse.get("weather");
        if (weatherList != null && !weatherList.isEmpty()) {
            Map<String, Object> weatherInfo = weatherList.get(0);
            weather.setMainCondition(weatherInfo.get("main").toString());
            weather.setDescription(weatherInfo.get("description").toString()); // New field for description
        }

        // Extract wind data
        Map<String, Object> wind = (Map<String, Object>) weatherApiResponse.get("wind");
        if (wind != null) {
            weather.setWindSpeed((Double) wind.get("speed")); // New field for wind speed
        }

        // Set the current timestamp
        weather.setTimestamp(System.currentTimeMillis() / 1000L); // Current Unix timestamp

        return weather;
    }
    
   /* @GetMapping("/summary.jsp/{city}")
    public String getSummaryByCity(@PathVariable String city, Model model) {
        if (city != null && !city.isEmpty()) {
            WeatherSummary weatherSummary = weatherService.getWeatherSummary(city);
            model.addAttribute("summary", weatherSummary); // Pass the weather data to the JSP
            model.addAttribute("city", city); // Pass the selected city to the JSP
        } else {
            model.addAttribute("error", "Please select a city to view the weather summary.");
        }
        return "summary"; // Return the summary.jsp page
    }*/
    
    @GetMapping("/summary.jsp/{city}")
    public String getWeatherSummary(@PathVariable String city, Model model) {
        // Fetch the weather data based on the city
        WeatherSummary weatherSummary = weatherService.getWeatherSummary(city);

        // Check if weatherSummary is null and handle the case
        if (weatherSummary == null) {
            model.addAttribute("error", "Weather data not found for city: " + city);
            return "error"; // Replace with your error JSP page if needed
        }

        // Add the weatherSummary to the model
        model.addAttribute("weatherSummary", weatherSummary);

        // Return the name of the JSP page to render
        return "summary";
    }
    @GetMapping("/dailySummary")
    @ResponseBody // This method can still be used for API calls
    public WeatherSummary getWeatherSummaryApi(@RequestParam String city) {
    	 WeatherSummary weatherSummary = weatherService.getWeatherSummary(city);
    	    
    	    // You can create a new object or modify the existing one as needed
    	 return new WeatherSummary(
    		        weatherSummary.getAvgTemp(),
    		        weatherSummary.getMaxTemp(),
    		        weatherSummary.getMinTemp(),
    		        weatherSummary.getDominantCondition()
    		    );       // return weatherService.getWeatherSummary(city);
    }

    private void logErrorResponse(ResponseEntity<Map> response) {
        System.err.println("Error fetching weather data. Status code: " + response.getStatusCode());
        if (response.getBody() != null) {
            System.err.println("Response body: " + response.getBody());
        }
    }
}
