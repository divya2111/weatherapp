<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Weather Summary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        select, input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }
        button {
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .weather-summary {
            margin-top: 20px;
        }
        .chart {
            display: flex;
            align-items: center;
            margin-top: 5px;
        }
        .bar {
            height: 20px;
            background-color: #007bff;
            transition: width 0.3s;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Daily Weather Summary</h1>

        <!-- Form for selecting city and number of days -->
        <form action="javascript:void(0);" method="get" id="weatherForm" onsubmit="return fetchWeatherSummary();">
            <label for="citySelect">Select a city:</label>
            <select name="city" id="citySelect" required>
                <option value="">--Select City--</option>
                <option value="Delhi">Delhi</option>
                <option value="Mumbai">Mumbai</option>
                <option value="Chennai">Chennai</option>
                <option value="Bangalore">Bangalore</option>
                <option value="Kolkata">Kolkata</option>
                <option value="Hyderabad">Hyderabad</option>
            </select>
            <label for="days">No of Days:</label>
            <input type="number" id="days" name="days" placeholder="Number of Days" required min="1"/>
            <button id="getWeatherSummary" type="submit">Get Summary</button>
        </form>

        <script>
           document.getElementById("weatherForm").onsubmit = function (e) {
            e.preventDefault(); // Prevent the default form submission
            var selectedCity = document.getElementById("citySelect").value;
            var selectedDays = document.getElementById("days").value
            if (selectedCity === "" || selectedDays === "") {
                alert("Please select a city and days.");
                return;
            }
            // Redirect to the desired URL
            window.location.href = "/Summaryofweather.jsp/" + encodeURIComponent(selectedCity) +"/" + encodeURIComponent(selectedDays);
        };
            
        </script>

        <div class="weather-summary">
            <c:if test="${not empty weatherDataList}">
                <c:forEach var="weather" items="${weatherDataList}">
                    <div class="weather-item">
                        <h3>${weather.city}</h3>
                        <p>Temperature: ${weather.temperature} °C</p>
                        <p>Feels Like: ${weather.feelsLike} °C</p>
                        <p>Main Condition: ${weather.mainCondition}</p>
                        <p>Humidity: ${weather.humidity} %</p>
                        <p>Wind Speed: ${weather.windSpeed} m/s</p>
                        <p>Timestamp: ${weather.timestamp}</p>
                        <!-- Graphical Representation -->
                        <div class="chart">
                            <div class="bar" style="width: ${weather.temperature * 10}px;">${weather.temperature} °C</div>
                        </div>
                    </div>
                </c:forEach>
        <canvas id="weatherChart"></canvas>
            </c:if>
        </div>
    </div>
   
</body>
</html>
