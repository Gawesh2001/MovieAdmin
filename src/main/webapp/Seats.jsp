<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Seat Booking</title>
    <link rel="stylesheet" href="SeatCss.css">
</head>
<body>
    <%
        // Get query parameters from URL
        String movieId = request.getParameter("movieid");
        String movieName = request.getParameter("movieName");
        String movieThumbnail = request.getParameter("movieThumbnail");
        String timeframe = request.getParameter("timeframe");
    %>

    <div class="movie-info">
        <div class="movie-details">
            <h2><%= movieName %></h2>
            <p>Timeframe: <input type="text" value="<%= timeframe %>" readonly></p>
            
            <!-- Date Picker -->
            <div class="date-picker">
                <span for="date">Select Date:</span>
                <input type="text" id="selectedDate" name="selectedDate" required>
            </div>
        </div>
    </div>

    <!-- Movie Thumbnail -->
    <img src="<%= movieThumbnail %>" alt="Movie Thumbnail" class="movie-thumbnail">

    <form id="bookingForm" action="BookSeatServlet" method="POST">
        <input type="hidden" name="movieId" value="<%= movieId %>">
        <input type="hidden" name="movieName" value="<%= movieName %>">
        <input type="hidden" name="timeFrame" value="<%= timeframe %>">
        <input type="hidden" name="selectedDate" id="hiddenSelectedDate">

        <!-- Seating Area -->
        <div class="seating-area">
            <% for (int i = 1; i <= 40; i++) { %>
                <input type="checkbox" id="seat<%= i %>" name="selectedSeats" value="S<%= i %>" class="seat">
                <label for="seat<%= i %>">S<%= i %></label>
            <% } %>
        </div>
        
         <!-- Email and Phone Number -->
       <div class="user-info" style="text-align: center; margin-top: 20px;">
    <spane for="email" style="display: block; margin-bottom: 5px;">Email:</spane>
    <input type="email" id="email" name="email" required style="display: block; margin: 0 auto; padding: 5px; width: 50%;"><br><br>

    <spane for="phoneNumber" style="display: block; margin-bottom: 5px;">Phone Number:</spane>
    <input type="text" id="phoneNumber" name="phoneNumber" required style="display: block; margin: 0 auto; padding: 5px; width: 50%;">
</div>

        <!-- Total Price Information -->
        <div class="info">
            <span for="totalPrice">Total Price:</span>
            <input type="text" id="totalPrice" name="totalPrice" readonly>
        </div>

        <button type="submit">Book Seats</button>
    </form>

    <!-- Including Flatpickr for Date Picker -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

    <script>
        // Initialize Flatpickr to allow yyyy-mm-dd format
        flatpickr("#selectedDate", {
            dateFormat: "Y-m-d",  // yyyy-mm-dd format
        });

        // Before form submission, set the hidden input field with the selected date value
        document.getElementById("bookingForm").addEventListener("submit", function(event) {
            var selectedDate = document.getElementById("selectedDate").value;

            // Validate if a valid date is selected
            if (!selectedDate) {
                event.preventDefault();
                alert('Please select a valid date.');
            } else {
                // Assign the selected date to the hidden input field (for submission)
                document.getElementById("hiddenSelectedDate").value = selectedDate;
            }
        });
    </script>

    <!-- Include JavaScript for seat selection and price calculation -->
    <script src="SeatsJS.js"></script>
</body>
</html>
