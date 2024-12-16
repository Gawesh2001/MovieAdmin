/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.movieadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import org.json.simple.JSONArray;

@WebServlet("/RetrieveTicketsByDateServlet")
public class RetrieveTicketsByDateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set response type
        response.setContentType("application/json;charset=UTF-8");
        
        // JDBC Connection Variables
        String url = "jdbc:mysql://localhost:3306/abccinema"; // Replace with your DB URL
        String username = "root"; // Replace with your DB username
        String password = "2001"; // Replace with your DB password
        
        // Get the selected date from request
        String selectedDate = request.getParameter("selectedDate");

        // SQL Query to retrieve data based on selected_date
       String query = "SELECT selected_seats FROM ticketbookings WHERE selected_date = ?";
    
    // Initialize JSON Array
    JSONArray seatsArray = new JSONArray();
    
    try (PrintWriter out = response.getWriter();
         Connection conn = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = conn.prepareStatement(query)) {

        // Set the parameter for the prepared statement
        ps.setString(1, selectedDate);
        
        ResultSet rs = ps.executeQuery();

        // Extract data from result set
        while (rs.next()) {
            // Add each seat name to the JSON array
            seatsArray.add(rs.getString("selected_seats"));
        }

        // Check if any data was found
        if (seatsArray.isEmpty()) {
            seatsArray.add("No data found for the selected date");
        }

        // Output JSON Array
        out.print(seatsArray.toString());
        out.flush(); // Flush the output stream to send the data

    } catch (SQLException e) {
        e.printStackTrace();
        response.getWriter().println("{\"error\":\"Database error: " + e.getMessage() + "\"}");
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("{\"error\":\"Error: " + e.getMessage() + "\"}");
    }
    }
}
