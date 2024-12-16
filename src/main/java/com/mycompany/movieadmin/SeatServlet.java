package com.mycompany.movieadmin;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BookSeatServlet")
public class SeatServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieName = request.getParameter("movieName");
        String movieId = request.getParameter("movieId");
        String timeFrame = request.getParameter("timeFrame");
        String[] selectedSeats = request.getParameterValues("selectedSeats");
        String totalPrice = request.getParameter("totalPrice");
        String selectedDate = request.getParameter("selectedDate");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        // Validate inputs
        if (selectedSeats == null || selectedSeats.length == 0) {
            response.getWriter().write("<script>alert('No seats selected. Please try again.'); window.history.back();</script>");
            return;
        }

        if (selectedDate == null || selectedDate.isEmpty() || email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty()) {
            response.getWriter().write("<script>alert('Please fill all required fields.'); window.history.back();</script>");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Generate a new unique ticket ID starting from 200000
            String ticketIdQuery = "SELECT MAX(ticket_id) AS max_ticket_id FROM ticketbookings";
            int newTicketId = 200000; // Default start value
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(ticketIdQuery)) {
                if (rs.next() && rs.getInt("max_ticket_id") >= 200000) {
                    newTicketId = rs.getInt("max_ticket_id") + 1;
                }
            }

            String sql = "INSERT INTO ticketbookings (movie_name, movie_id, timeframe, selected_date, selected_seats, total_price, u_email, u_no, ticket_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BigDecimal pricePerSeat = new BigDecimal(totalPrice).divide(new BigDecimal(selectedSeats.length), 2, BigDecimal.ROUND_HALF_UP);

            // Insert each seat booking into the database
            for (String seat : selectedSeats) {
                statement.setString(1, movieName);
                statement.setString(2, movieId);
                statement.setString(3, timeFrame);
                statement.setString(4, selectedDate);
                statement.setString(5, seat);
                statement.setBigDecimal(6, pricePerSeat);
                statement.setString(7, email);
                statement.setString(8, phoneNumber);
                statement.setInt(9, newTicketId); // Set the generated ticket ID
                statement.addBatch();
            }

            // Execute the batch insert
            int[] rowsInserted = statement.executeBatch();
            if (rowsInserted.length > 0) {
                response.getWriter().write("<script>alert('Booking successful for " + rowsInserted.length + " seats! Ticket ID: " + newTicketId + "'); window.location.href='index.html';</script>");
            } else {
                response.getWriter().write("<script>alert('Booking failed. Please try again.'); window.history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('An error occurred: " + e.getMessage() + "'); window.history.back();</script>");
        }
    }
}
