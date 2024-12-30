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
        String url = "jdbc:mysql://localhost:3306/abccinema"; 
        String username = "root"; 
        String password = "2001"; 
        
   
        String selectedDate = request.getParameter("selectedDate");

       
       String query = "SELECT selected_seats FROM ticketbookings WHERE selected_date = ?";
    
  
    JSONArray seatsArray = new JSONArray();
    
    try (PrintWriter out = response.getWriter();
         Connection conn = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = conn.prepareStatement(query)) {

      
        ps.setString(1, selectedDate);
        
        ResultSet rs = ps.executeQuery();

        
        while (rs.next()) {
            
            seatsArray.add(rs.getString("selected_seats"));
        }

        
        if (seatsArray.isEmpty()) {
            seatsArray.add("No data found for the selected date");
        }

       
        out.print(seatsArray.toString());
        out.flush(); 

    } catch (SQLException e) {
        e.printStackTrace();
        response.getWriter().println("{\"error\":\"Database error: " + e.getMessage() + "\"}");
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("{\"error\":\"Error: " + e.getMessage() + "\"}");
    }
    }
}
