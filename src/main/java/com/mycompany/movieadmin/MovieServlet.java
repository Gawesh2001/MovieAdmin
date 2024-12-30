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

@WebServlet(urlPatterns = {"/buyticket"})
public class MovieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001";
    private static final String SELECT_QUERY = "SELECT * FROM addmovie";

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
            out.println("<link rel=\"stylesheet\" href=\"/MovieAdmin/ticketBookingCss.css\">");
            out.println("<script>");
            out.println("function confirmDelete(movieId) {");
            out.println("    if (confirm('Are you sure you want to delete this movie?')) {");
            out.println("        const form = document.getElementById('deleteForm-' + movieId);");
            out.println("        form.submit();");
            out.println("    }");
            out.println("}");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<a href='javascript:history.back()'>");
            out.println("<button id='back-button' >");
            out.println("<svg xmlns='http://www.w3.org/2000/svg' height='30' viewBox='0 96 960 960' width='30' class='back-icon'>");
            out.println("<path d='M560 816 320 576l240-240 42 42-198 198 198 198-42 42Z' />");
            out.println("</svg>");
            out.println("</button>");
            out.println("</a>");
            out.println("<h1>BOOK A MOVIE</h1>");
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
                String timeframe = rs.getString("timeframe");
                
                

                out.println("<div class='movie-container'>");
                out.println("<img src='" + movieThumbnail + "' alt='" + movieName + "' class='movie-thumbnail'>");
                out.println("<h2>" + movieName + "</h2>");
                out.println("<p><strong>Category:</strong> " + movieCategory + "</p>");
                out.println("<p><strong>Release Date:</strong> " + releaseDate + "</p>");
                
                out.println("<form id='deleteForm-" + movieId + "' action='/MovieAdmin/viewmovies' method='POST' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + movieId + "'>");
                out.println("<button type='button' class='action-button' onclick='window.location.href=\"Seats.jsp?movieid=" + movieId + "&movieName=" + movieName + "&movieThumbnail=" + movieThumbnail + "&timeframe=" + timeframe + "\"'>Buy Ticket</button>");
                out.println("</form>");
                out.println("</div>");
            }

            if (!hasMovies) {
                out.println("<p>No movies available at the moment.</p>");
            }

            out.println("</div>"); 
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error: Unable to load movies. Please try again later.</p>");
        }
    }
}