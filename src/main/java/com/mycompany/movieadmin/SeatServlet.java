import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SeatServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/abccinema";  // Change the database URL as per your setup
        String user = "root";  // Database username
        String password = "password";  // Database password

        // SQL query to fetch movie details
        String query = "SELECT movie_name, movie_thumbnail FROM addmovie WHERE movie_id = 1";  // Assuming movie_id 1 for demo

        try {
            // Step 1: Establish connection to the database
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 2: Create a Statement
            Statement stmt = con.createStatement();

            // Step 3: Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Step 4: Process the result
            if (rs.next()) {
                String movieName = rs.getString("movie_name");
                String movieThumbnail = rs.getString("movie_thumbnail");

                // Set movie details as request attributes
                request.setAttribute("movieName", movieName);
                request.setAttribute("movieThumbnail", movieThumbnail);
            }

            // Step 5: Forward to JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("movieBooking.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}
