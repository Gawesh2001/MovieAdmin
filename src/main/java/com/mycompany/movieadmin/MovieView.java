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

            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Start the response HTML
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>View Movies</title>");
            out.println("<link rel=\"stylesheet\" href=\"/MovieAdmin/ViewMoviesCss.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<header><h1>Available Movies</h1></header>");
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

                out.println("<div class='movie-container'>");
                out.println("<img src='" + movieThumbnail + "' alt='" + movieName + "' class='movie-thumbnail'>");
                out.println("<h2>" + movieName + "</h2>");
                out.println("<p><strong>Category:</strong> " + movieCategory + "</p>");
                out.println("<p><strong>Release Date:</strong> " + releaseDate + "</p>");

                // Dropdown for time frame selection
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
          
                out.println("<button type='submit' class='update-button'style='background-color: blue;  color: white;border: none; padding: 2px 3px; border-radius: 5px; cursor: pointer; font-size: 13px; transition: background-color 0.3s ease;' onmouseover='this.style.backgroundColor=\"darkblue\"' onmouseout='this.style.backgroundColor=\"blue\"'>Update</button>");
                //out.println("<button type='submit' class='update-button' style='background-color: blue;  color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-size: 16px; transition: background-color 0.3s ease;' onmouseover='this.style.backgroundColor=\"darkblue\"' onmouseout='this.style.backgroundColor=\"blue\"'>Update</button>");
                out.println("</form>");
                 
                // Delete button
                out.println("<form id='deleteForm-" + movieId + "' action='/MovieAdmin/viewmovies' method='POST' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + movieId + "'>");
                out.println("<button type='button' class='action-button' style='background-color: red; margin-top: 10px; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-size: 16px; transition: background-color 0.3s ease;' onclick='confirmDelete(" + movieId + ")'>Delete</button>");
                out.println("</form>");

                out.println("</div>");
            }

            if (!hasMovies) {
                out.println("<p>No movies available at the moment.</p>");
            }

            out.println("</div>"); // Close movies-container
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
                // Update movie time frame
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
                // Handle movie deletion
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
