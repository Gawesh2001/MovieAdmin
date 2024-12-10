<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancel Tickets</title>
    <link rel="stylesheet" href="DashboardCss.css">
</head>
<body>
    <div class="cancel-tickets-container">
        <h1>Cancel Tickets</h1>
        <form method="post">
            <div class="form-group">
                <label for="ticketId">Ticket ID:</label>
                <input type="text" id="ticketId" name="ticketId" placeholder="Enter ticket ID" required>
            </div>
            <button type="submit" class="cancel-btn">Cancel Ticket</button>
        </form>

       
    </div>
</body>
</html>
