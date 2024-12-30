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

        String jdbcURL = "jdbc:mysql://localhost:3306/abccinema"; 
        String dbUser = "root"; 
        String dbPassword = "2001"; 

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            
            String sql = "SELECT feed_id, feedback FROM feedback ORDER BY created_at DESC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

          
            ArrayList<String[]> feedbackList = new ArrayList<>();
            while (resultSet.next()) {
                String[] feedback = new String[2];
                feedback[0] = String.valueOf(resultSet.getInt("feed_id")); 
                feedback[1] = resultSet.getString("feedback"); 
                feedbackList.add(feedback);
            }

      
            request.setAttribute("feedbackList", feedbackList);

          
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
