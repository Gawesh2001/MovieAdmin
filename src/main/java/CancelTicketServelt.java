import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CancelTicketServlet")
public class CancelTicketServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form data
        String ticketId = request.getParameter("ticketId");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String date = request.getParameter("date");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Step 1: Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abccinema", "root", "2001");

            // Step 2: Verify booking details
            String query = "SELECT * FROM ticketbookings WHERE booking_id = ? AND u_email = ? AND u_no = ? AND selected_date = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, ticketId.trim());
            pst.setString(2, email.trim());
            pst.setString(3, phone.trim());
            pst.setString(4, date.trim());

            rs = pst.executeQuery();

            if (rs.next()) {
                // Booking exists, proceed to cancel
                String deleteQuery = "DELETE FROM ticketbookings WHERE booking_id = ?";
                pst = conn.prepareStatement(deleteQuery);
                pst.setString(1, ticketId.trim());
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    request.setAttribute("message", "Ticket with ID " + ticketId + " canceled successfully!");
                } else {
                    request.setAttribute("message", "Failed to cancel the ticket. Please try again.");
                }
            } else {
                // No matching booking found
                request.setAttribute("message", "No booking found with the provided details.");
            }

        } catch (Exception e) {
            // Handle exceptions
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Forward response to JSP
        request.getRequestDispatcher("CancelTicket.jsp").forward(request, response);
    }
}
