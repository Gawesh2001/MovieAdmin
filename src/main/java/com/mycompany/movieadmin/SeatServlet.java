package com.mycompany.movieadmin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/bookticket"})
public class SeatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001";
    private static final String MOVIE_QUERY = "SELECT movieid, movieName, movieThumbnail, movieDescription, timeframe FROM addmovie WHERE movieid = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        int movieId = Integer.parseInt(request.getParameter("movieid")); // Get the movie ID from the request

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error: Unable to load JDBC driver.</p>");
            return;
        }

        // Establish connection and fetch movie data
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(MOVIE_QUERY)) {

            pstmt.setInt(1, movieId); // Set the movie ID parameter in the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String movieName = rs.getString("movieName");
                String movieThumbnail = rs.getString("movieThumbnail");
                String movieDescription = rs.getString("movieDescription");
                String timeframe = rs.getString("timeframe");  // Get the timeframe for the movie

                // Set movie details as request attributes
                request.setAttribute("movieId", movieId);  // Set movieId as an attribute
                request.setAttribute("movieName", movieName);
                request.setAttribute("movieThumbnail", movieThumbnail);
                request.setAttribute("movieDescription", movieDescription);
                request.setAttribute("timeframe", timeframe);  // Set the timeframe

                // Forward the request to the JSP page
                request.getRequestDispatcher("/Seats.jsp").forward(request, response);
            } else {
                response.getWriter().println("<p>Movie not found.</p>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error: Unable to fetch movie data.</p>");
        }
    }
}
