
package com.mycompany.movieadmin;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;

public class MovieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbURL = "jdbc:mysql://localhost:3306/abccinema";
        String dbUser = "root";
        String dbPassword = "2001";
        String sqlQuery = "SELECT * FROM addmovie";

        // List to hold movies as Maps
        List<Map<String, String>> movies = new ArrayList<>();

        try {
            // Loading database driver and establishing connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            // Collect the movie data into a List of Maps
            while (rs.next()) {
                Map<String, String> movie = new HashMap<>();
                movie.put("movieName", rs.getString("movieName"));
                movie.put("movieCategory", rs.getString("movieCategory"));
                movie.put("releaseDate", rs.getString("releaseDate"));
                movie.put("movieThumbnail", rs.getString("movieThumbnail"));

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
        }

        // Forward the movie list to the JSP
        request.setAttribute("movies", movies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookMovie.jsp");
        dispatcher.forward(request, response);
    }
}
