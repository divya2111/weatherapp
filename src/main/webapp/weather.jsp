<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Weather Monitoring</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            text-align: center;
        }
        .weather-info {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .error {
            color: red; /* Style for error messages */
        }
         .link-button {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color:#007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .link-button:hover {
            background-color: #87CEEB;
        }
    </style>
    <script>
        // Function to automatically refresh the page every 5 minutes (300000 ms)
        function autoRefresh() {
            setInterval(function() {
                location.reload();
            }, 300000);  // 300000 milliseconds = 5 minutes
        }

        // Call autoRefresh when the page loads
        window.onload = function() {
            autoRefresh();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Weather Monitoring System</h1>
    
    <!-- Dropdown for selecting city -->
    <form id="cityForm">
        <label for="citySelect">Select a city:</label>
        <select name="city" id="citySelect">
            <option value="">--Select City--</option>
            <option value="Delhi" <c:if test="${city == 'Delhi'}">selected</c:if>>Delhi</option>
            <option value="Mumbai" <c:if test="${city == 'Mumbai'}">selected</c:if>>Mumbai</option>
            <option value="Chennai" <c:if test="${city == 'Chennai'}">selected</c:if>>Chennai</option>
            <option value="Bangalore" <c:if test="${city == 'Bangalore'}">selected</c:if>>Bangalore</option>
            <option value="Kolkata" <c:if test="${city == 'Kolkata'}">selected</c:if>>Kolkata</option>
            <option value="Hyderabad" <c:if test="${city == 'Hyderabad'}">selected</c:if>>Hyderabad</option>
        </select>

        <!-- Button to trigger the redirection -->
        <br><br>
        <button class="link-button" type="submit">Get Weather Info</button>
    </form>

    <script>
        // Handle form submission to redirect without query parameters
        document.getElementById("cityForm").onsubmit = function (e) {
            e.preventDefault(); // Prevent the default form submission
            var selectedCity = document.getElementById("citySelect").value;
            if (selectedCity === "") {
                alert("Please select a city.");
                return;
            }
            // Redirect to the desired URL
            window.location.href = "/weather.jsp/" + encodeURIComponent(selectedCity);
        };
    </script>

    <!-- Weather information (hidden initially) -->
    <div class="weather-info" id="weatherInfo" >
        <c:if test="${not empty weather}">
            <h2>Weather for ${weather.city}</h2>
            <p>Temperature: ${weather.temp} °C</p>
            <p>Feels Like: ${weather.feelsLike} °C</p>
            <p>Main Condition: ${weather.mainCondition}</p>
            <p>Description: ${weather.description}</p>
            <p>Humidity: ${weather.humidity}%</p>
            <p>Wind Speed: ${weather.windSpeed} m/s</p>
            <p>Last Update: ${weather.timestamp}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        <br>
        <a href="/summary.jsp" class="link-button">Daily Summary</a>
        <a href="/" class="link-button">Back to Home</a>
    </div>
</div>

</body>
</html>
