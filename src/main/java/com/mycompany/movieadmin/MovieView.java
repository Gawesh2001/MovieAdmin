package com.mycompany.movieadmin;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = {"/viewmovies"})
public class MovieView extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001";
    private static final String SELECT_QUERY = "SELECT * FROM addmovie";
    private static final String UPDATE_QUERY = "UPDATE addmovie SET timeframe = ? WHERE movieid = ?";
    private static final String DELETE_QUERY = "DELETE FROM addmovie WHERE movieid = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

           
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>View Movies</title>");
            out.println("<link rel=\"stylesheet\" href=\"/MovieAdmin/ViewMoviesCss.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<a href='javascript:history.back()' class='back-button'>");
            out.println("<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='currentColor' class='size-6'>");
            out.println("<path fill-rule='evenodd' d='M7.72 12.53a.75.75 0 0 1 0-1.06l7.5-7.5a.75.75 0 1 1 1.06 1.06L9.31 12l6.97 6.97a.75.75 0 1 1-1.06 1.06l-7.5-7.5Z' clip-rule='evenodd' />");
            out.println("</svg>");
            out.println("</a>");
            out.println("<h1>Available Movies</h1>");
            out.println("</header>");

            out.println("<div class='movies-container'>");

            boolean hasMovies = false;
            while (rs.next()) {
                hasMovies = true;
                int movieId = rs.getInt("movieid");
                String movieName = rs.getString("movieName");
                String movieCategory = rs.getString("movieCategory");
                String releaseDate = rs.getString("releaseDate");
                String movieThumbnail = rs.getString("movieThumbnail");
                String existingTimeframe = rs.getString("timeframe"); // Get the existing timeframe

                out.println("<link rel='stylesheet' type='text/css' href='viewmovies.css'>");

                out.println("<div class='movie-container'>");
                out.println("<div class='movie-poster'>");
                out.println("<img src='" + movieThumbnail + "' alt='" + movieName + "'>");
                out.println("</div>");
                out.println("<div class='movie-info'>");
                out.println("<h3>" + movieName + "</h3>");
                out.println("<p class='category'>" + movieCategory + "</p>");
                out.println("<p class='release-date'>" + releaseDate + "</p>");

                // Update form
                out.println("<form action='/MovieAdmin/viewmovies' method='POST'>");
                out.println("<label for='timeframe-" + movieId + "'>Select Time Frame:</label>");
                out.println("<select name='timeframe' id='timeframe-" + movieId + "' required>");
                out.println("<option value='' disabled selected>Select Time Frame</option>");
                out.println("<option value='1.00 PM - 3.30 PM'" +
                        (existingTimeframe != null && existingTimeframe.equals("1.00 PM - 3.30 PM") ? " selected" : "") + ">1.00 PM - 3.30 PM</option>");
                out.println("<option value='4.00 PM - 6.30 PM'" +
                        (existingTimeframe != null && existingTimeframe.equals("4.00 PM - 6.30 PM") ? " selected" : "") + ">4.00 PM - 6.30 PM</option>");
                out.println("<option value='7.00 PM - 9.30 PM'" +
                        (existingTimeframe != null && existingTimeframe.equals("7.00 PM - 9.30 PM") ? " selected" : "") + ">7.00 PM - 9.30 PM</option>");
                out.println("</select>");
                out.println("<input type='hidden' name='movieid' value='" + movieId + "'>");

                
                out.println("<div class='button-container'>");
                out.println("<button type='submit' class='update-btn'>Update</button>");
                out.println("</form>");

                
                out.println("<form id='deleteForm-" + movieId + "' action='/MovieAdmin/viewmovies' method='POST' style='display: inline;'>");
                out.println("<input type='hidden' name='id' value='" + movieId + "'>");
                out.println("<button type='button' class='delete-btn' onclick='confirmDelete(" + movieId + ")'>Delete</button>");
                out.println("</form>");
                out.println("</div>"); // Close button-container

                out.println("</div>");
                out.println("</div>");

            }

            if (!hasMovies) {
                out.println("<p>No movies available at the moment.</p>");
            }

            out.println("</div>"); 
            out.println("<script>");
            out.println("function confirmDelete(movieId) {");
            out.println("    if (confirm('Are you sure you want to delete this movie?')) {");
            out.println("        const form = document.getElementById('deleteForm-' + movieId);");
            out.println("        form.submit();");
            out.println("    }");
            out.println("}");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error: Unable to load movies. Please try again later.</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String timeframe = request.getParameter("timeframe");
        String movieIdStr = request.getParameter("movieid");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (timeframe != null && movieIdStr != null) {
               
                try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {
                    int movieId = Integer.parseInt(movieIdStr);
                    pstmt.setString(1, timeframe);
                    pstmt.setInt(2, movieId);
                    int rowsUpdated = pstmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        out.println("<script>alert('Time frame updated successfully!'); window.location.href='/MovieAdmin/viewmovies';</script>");
                    } else {
                        out.println("<script>alert('Failed to update time frame.'); window.location.href='/MovieAdmin/viewmovies';</script>");
                    }
                }
            } else {
               
                int movieId = Integer.parseInt(request.getParameter("id"));
                try (PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {
                    pstmt.setInt(1, movieId);
                    int rowsDeleted = pstmt.executeUpdate();

                    if (rowsDeleted > 0) {
                        out.println("<script>alert('Movie deleted successfully!'); window.location.href='/MovieAdmin/viewmovies';</script>");
                    } else {
                        out.println("<script>alert('Failed to delete the movie.'); window.location.href='/MovieAdmin/viewmovies';</script>");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Error: Unable to process request. Please try again later.'); window.location.href='/MovieAdmin/viewmovies';</script>");
        }
    }
}
