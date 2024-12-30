<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Feedback Page</title>
    <link rel="stylesheet" href="Feedback.css"> 
</head>
<body>
    <header class="header">
        <h1>Feedback - Admin Dashboard</h1>
    </header>
    
    <h2>All User Feedback</h2>

    <%
        
        List<String[]> feedbackList = (List<String[]>) request.getAttribute("feedbackList");

        if (feedbackList == null || feedbackList.isEmpty()) {
    %>
        <p>No feedback available.</p>
    <%
        } else {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Feedback</th>
            </tr>
            <%
                for (String[] feedback : feedbackList) {
            %>
                <tr>
                    <td><%= feedback[0] %></td>
                    <td><%= feedback[1] %></td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        }
    %>
</body>
</html>
