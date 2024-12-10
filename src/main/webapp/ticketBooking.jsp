
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book A Movie</title>
    <link rel="stylesheet" href="ticketBookingCss.css"> <!-- Link to external CSS -->
</head>
<body>
    <header>
        <h1 style="text-align:center;">Book A Movie</h1>
    </header>

    <div class="movies-container">
        <%-- Movie containers will be added here dynamically --%>
        <%
            String dbURL = "jdbc:mysql://localhost:3306/abccinema";
            String dbUser = "root";
            String dbPassword = "2001";
            String sqlQuery = "SELECT * FROM addmovie";

            try {
                // Loading database driver and establishing connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);

                // Loop through the result set and generate movie containers dynamically
                int count = 0; // To create rows with 4 containers each
                while (rs.next()) {
                    String movieName = rs.getString("movieName");
                    String movieCategory = rs.getString("movieCategory");
                    String releaseDate = rs.getString("releaseDate");
                    String movieThumbnail = rs.getString("movieThumbnail");
                    

                    // Start new row every 4 movies
                    if (count % 4 == 0 && count != 0) {
                        out.println("</div><div class='movies-container'>");
                    }

                    out.println("<div class='movie-container'>");
                    out.println("<img src='" + movieThumbnail + "' alt='" + movieName + "' class='movie-thumbnail'>");
                    out.println("<h2>" + movieName + "</h2>");
                    out.println("<p><strong>Category:</strong> " + movieCategory + "</p>");
                    out.println("<p><strong>Release Date:</strong> " + releaseDate + "</p>");
                    
                    out.println("<button class='book-button'>Book Ticket</button>");
                    out.println("</div>");
                    
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        %>
    </div>

    <script src="ticketBooking.js"></script> <!-- Link to JavaScript file -->
</body>
</html>
