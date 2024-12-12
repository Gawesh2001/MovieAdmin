package com.mycompany.movieadmin;

import java.sql.Connection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gawes
 */
@WebServlet(urlPatterns = {"/addMovie"})
public class AddMovie extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "2001"; 
    private static final String INSERT_QUERY = "INSERT INTO addmovie (movieName, movieCategory, releaseDate, showingDateTime, movieThumbnail, movieDescription, youtube, duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form data
        String movieName = request.getParameter("movieName");
        String movieCategory = request.getParameter("movieCategory");
        String releaseDate = request.getParameter("releaseDate");
        String showingDateTime = request.getParameter("showingDateTime");
        String movieThumbnail = request.getParameter("movieThumbnail");
        String movieDescription = request.getParameter("movieDescription");
        
        // Handle the youtube field, set default if empty
        String youtube = request.getParameter("youtube");
        if (youtube == null || youtube.isEmpty()) {
            youtube = "default_url_or_empty_string"; // Provide a default value
        }
        
        String duration = request.getParameter("duration");
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare SQL insert statement
            pstmt = conn.prepareStatement(INSERT_QUERY);

            // Set parameter values
            pstmt.setString(1, movieName);
            pstmt.setString(2, movieCategory);
            pstmt.setString(3, releaseDate);
            pstmt.setString(4, showingDateTime);
            pstmt.setString(5, movieThumbnail);
            pstmt.setString(6, movieDescription);
            pstmt.setString(7, youtube);
            pstmt.setString(8, duration);

            // Execute SQL statement
            int rowsInserted = pstmt.executeUpdate();
            response.setContentType("text/plain");

            if (rowsInserted > 0) {
                response.getWriter().write("Movie added successfully!");
            } else {
                response.getWriter().write("Failed to add movie.");
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            // Handle exceptions
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                response.getWriter().write("Error closing resources: " + ex.getMessage());
            }
        }
    }
}
