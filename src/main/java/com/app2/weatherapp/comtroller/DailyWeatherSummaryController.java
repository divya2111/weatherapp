package com.app2.weatherapp.comtroller;  // Corrected typo from "comtroller" to "controller"

import com.app2.weatherapp.model.DailyWeatherSummary;
import com.app2.weatherapp.repository.DailyWeatherSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DailyWeatherSummaryController {

    @Autowired
    private DailyWeatherSummaryRepository dailyWeatherSummaryRepository;

    // Define a constant date format for reuse
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  // Changed to dd-MM-yyyy

    /**
     * Retrieves daily weather summaries based on the specified city and date range.
     *
     * @param city      The city for which to retrieve summaries. (Required)
     * @param startDate The start date for the range. (Required)
     * @param endDate   The end date for the range. (Required)
     * @param date      The specific date for which to retrieve summaries. (Required)
     * @return A list of DailyWeatherSummary objects for the specific date within the range.
     */
    @GetMapping("/api/daily-summaries")
    public List<DailyWeatherSummary> getDailySummaries(
            @RequestParam String city, // Mandatory
            @RequestParam String startDate, // Mandatory
            @RequestParam String endDate, // Mandatory
            @RequestParam String date) { // Mandatory

        // Validate mandatory input parameters
        if (city == null || city.isEmpty() || startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty() || date == null || date.isEmpty()) {
            System.err.println("City, start date, end date, and specific date must be provided.");
            return List.of(); // Return an empty list if parameters are invalid
        }

        try {
            // Convert the date strings to Date objects
            Date parsedStartDate = dateFormat.parse(startDate);
            Date parsedEndDate = dateFormat.parse(endDate);
            Date parsedDate = dateFormat.parse(date);

            // Log the received parameters
            System.out.println("Received request for city: " + city + 
                               ", start date: " + parsedStartDate + 
                               ", end date: " + parsedEndDate + 
                               ", specific date: " + parsedDate);

            // Check if the specific date is within the start and end date range
            if (parsedDate.before(parsedStartDate) || parsedDate.after(parsedEndDate)) {
                System.err.println("Specific date is out of the range defined by start date and end date.");
                return List.of(); // Return an empty list if the date is not in the range
            }

            // Fetch summary for the specific date
            List<DailyWeatherSummary> summaries = dailyWeatherSummaryRepository.findByCityAndDate(city, parsedDate);

            // Log the result
            System.out.println("Fetched summaries: " + summaries);
            return summaries;
        } catch (ParseException e) {
            // Log the error for invalid date format
            System.err.println("Invalid date format: " + e.getMessage());
            return List.of(); // Return an empty list in case of parsing error
        } catch (Exception e) {
            // Handle any other exceptions
            System.err.println("Error fetching daily summaries: " + e.getMessage());
            return List.of(); // Return an empty list in case of an error
        }
    }
}