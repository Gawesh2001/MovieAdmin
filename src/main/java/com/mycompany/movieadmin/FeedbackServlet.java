import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/abccinema"; // Replace with your DB name
        String dbUser = "root"; // Replace with your username
        String dbPassword = "2001"; // Replace with your password

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Query to fetch feedback data
            String sql = "SELECT feed_id, feedback FROM feedback ORDER BY created_at DESC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // Store feedback data in a list
            ArrayList<String[]> feedbackList = new ArrayList<>();
            while (resultSet.next()) {
                String[] feedback = new String[2];
                feedback[0] = String.valueOf(resultSet.getInt("feed_id")); // Feedback ID
                feedback[1] = resultSet.getString("feedback"); // Feedback text
                feedbackList.add(feedback);
            }

            // Set feedback data in the request scope
            request.setAttribute("feedbackList", feedbackList);

            // Forward the request to the JSP page
            request.getRequestDispatcher("FeedbackJS.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
