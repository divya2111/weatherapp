<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <html>
<head>
    <title>Manage Alerts</title>
</head>
<body>
    <h1>Manage Alerts</h1>
    <form action="addAlert" method="post">
        <label for="city">City:</label>
        <input type="text" id="city" name="city" required>
        <label for="temperature_threshold">Temperature Threshold (°C):</label>
        <input type="number" id="temperature_threshold" name="temperature_threshold" required>
        <input type="submit" value="Add Alert">
    </form>
    <h2>Existing Alerts</h2>
    <table border="1">
        <tr>
            <th>City</th>
            <th>Temperature Threshold (°C)</th>
            <th>Alert Active</th>
        </tr>
        <c:forEach var="alert" items="${alerts}">
            <tr>
                <td>${alert.city}</td>
                <td>${alert.temperature_threshold}</td>
                <td>${alert.alert_active ? 'Yes' : 'No'}</td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
