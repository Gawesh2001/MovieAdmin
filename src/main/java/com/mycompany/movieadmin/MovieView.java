package com.mycompany.movieadmin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getMovies")
public class MovieView extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM addmovie";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieName(rs.getString("movieName"));
                movie.setMovieCategory(rs.getString("movieCategory"));
                movie.setReleaseDate(rs.getString("releaseDate"));
                movie.setShowingDateTime(rs.getString("showingDateTime"));
                movie.setMovieThumbnail(rs.getString("movieThumbnail"));
                movie.setMovieDescription(rs.getString("movieDescription"));
                movieList.add(movie);
            }

            request.setAttribute("movieList", movieList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("displayMovies.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }

    // Movie class to hold movie data
    public static class Movie {
        private String movieName;
        private String movieCategory;
        private String releaseDate;
        private String showingDateTime;
        private String movieThumbnail;
        private String movieDescription;

        // Getters and Setters
        public String getMovieName() { return movieName; }
        public void setMovieName(String movieName) { this.movieName = movieName; }

        public String getMovieCategory() { return movieCategory; }
        public void setMovieCategory(String movieCategory) { this.movieCategory = movieCategory; }

        public String getReleaseDate() { return releaseDate; }
        public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

        public String getShowingDateTime() { return showingDateTime; }
        public void setShowingDateTime(String showingDateTime) { this.showingDateTime = showingDateTime; }

        public String getMovieThumbnail() { return movieThumbnail; }
        public void setMovieThumbnail(String movieThumbnail) { this.movieThumbnail = movieThumbnail; }

        public String getMovieDescription() { return movieDescription; }
        public void setMovieDescription(String movieDescription) { this.movieDescription = movieDescription; }
    }
}
