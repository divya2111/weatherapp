<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Daily Weather Summary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .summary {
            margin: 20px 0;
            padding: 10px;
            border-radius: 5px;
            background-color: #e9f5ff;
            box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
        }
        .summary p {
            margin: 5px 0;
            font-size: 16px;
            color: #555;
        }
        .error {
            color: red;
            font-weight: bold;
        }
        select, button {
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Daily Weather Summary</h1>

    <!-- Dropdown for selecting city -->
    <form id="cityForm">
        <label for="citySelect">Select a city:</label>
        <select name="city" id="citySelect">
            <option value="">--Select City--</option>
            <option value="Delhi">Delhi</option>
            <option value="Mumbai">Mumbai</option>
            <option value="Chennai">Chennai</option>
            <option value="Bangalore">Bangalore</option>
            <option value="Kolkata">Kolkata</option>
            <option value="Hyderabad">Hyderabad</option>
        </select>
        <button id="getWeather" type="button">Get Summary</button>
    </form>
    <div id="weatherSummary" class="summary">
        <h2>Weather Summary</h2>
        <p id="avgTemp"></p>
        <p id="maxTemp"></p>
        <p id="minTemp"></p>
        <p id="dominantCondition"></p>
    </div>
    

    <script>
     /*   // Handle form submission to redirect without query parameters
        document.getElementById('getWeather').addEventListener('click', function (e) {
            e.preventDefault(); // Prevent the default form submission
            var selectedCity = document.getElementById("citySelect").value;
            if (selectedCity === "") {
                alert("Please select a city.");
                return;
            }
            // Redirect to the desired URL
            window.location.href = "/summary.jsp/" + encodeURIComponent(selectedCity);
        });

        var city = document.getElementById('citySelect').value;
        if (city) {
            fetch('http://localhost:8080/dailySummary?city=' + encodeURIComponent(city))
                .then(response => response.json())
                .then(data => console.log(data))
                .catch(error => console.error('Error:', error));
        }*/
        // Handle form submission to fetch weather data
        document.getElementById('getWeather').addEventListener('click', function (e) {
            e.preventDefault(); // Prevent the default form submission
            var selectedCity = document.getElementById("citySelect").value;
            if (selectedCity === "") {
                alert("Please select a city.");
                return;
            }
            // Fetch the weather summary from the API
            fetch('http://localhost:8080/dailySummary?city=' + encodeURIComponent(selectedCity))
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    // Update the UI with the weather summary
                    document.getElementById("avgTemp").innerText = "Average Temperature: " + data.avgTemp.toFixed(2) + " °C";
                    document.getElementById("maxTemp").innerText = "Maximum Temperature: " + data.maxTemp.toFixed(2) + " °C";
                    document.getElementById("minTemp").innerText = "Minimum Temperature: " + data.minTemp.toFixed(2) + " °C";
                    document.getElementById("dominantCondition").innerText = "Dominant Condition: " + data.dominantCondition;

                    // Show the summary div
                    document.getElementById("weatherSummary").style.display = "block";
                })
                .catch(error => console.error('Error:', error));
        });
    </script>

    <!-- Display daily weather summary -->
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <a href="/">Back to Home</a>
</div>
</body>
</html>
