<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Monitoring System - Home</title>
    <style>
        /* Inline CSS for styling */
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 2000%;
            max-width: 1000px;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .link-button {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #224C98;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .link-button:hover {
            background-color: #87CEEB;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Weather Monitoring System</h2>
        <a href="weather.jsp" class="link-button">Current Weather</a>
       <!--   <a href="summary.jsp" class="link-button">Daily Summary</a>
        <a href="alert.jsp" class="link-button">Weather Alerts</a> -->
         <a href="Summaryofweather.jsp" class="link-button">Summary</a>
        
    </div>
</body> 
</html>