<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Seat Booking</title>
        <link rel="stylesheet" href="SeatCss.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    </head>
    <body>
        <%
            
            String movieId = request.getParameter("movieid");
            String movieName = request.getParameter("movieName");
            String movieThumbnail = request.getParameter("movieThumbnail");
            String timeframe = request.getParameter("timeframe");
        %>

        <div class="movie-info">
            <div class="movie-details">
                <h2>Movie Name : <%= movieName%></h2>
                <p>Timeframe: <input type="text" value="<%= timeframe%>" readonly></p>

             
                <div class="date-picker">
                    <span for="date">Select Date:</span>
                   

                    
                    <input type="text" id="selectedDate" name="selectedDate" placeholder="Select Date" readonly required>

                  
                    <button onclick="showDatePicker()">Select Date</button>
                </div>






               

            </div>
        </div>

       
        <div class="tv-screen">
        <img src="<%= movieThumbnail%>" alt="Movie Thumbnail" class="movie-thumbnail">
        </div>
        <form id="bookingForm" action="BookSeatServlet" method="POST">
            <input type="hidden" name="movieId" value="<%= movieId%>">
            <input type="hidden" name="movieName" value="<%= movieName%>">
            <input type="hidden" name="timeFrame" value="<%= timeframe%>">
            <input type="hidden" name="selectedDate" id="hiddenSelectedDate">

          
            <div class="seating-area" id="seating-area-wrap">
                <% for (int i = 1; i <= 40; i++) {%>
                <input type="checkbox" id="seat<%= i%>" name="selectedSeats" value="S<%= i%>" class="seat">
                <label for="seat<%= i%>">S<%= i%></label>
                <% }%>
            </div>

            
            <div class="user-info" style="text-align: center; margin-top: 20px;">
                <spane for="email" style="display: block; margin-bottom: 5px;">Email:</spane>
                <input type="email" id="email" name="email" placeholder="Enter Your Email Here" required ><br><br>

                <spane for="phoneNumber" style="display: block; margin-bottom: 5px;">Phone Number:</spane>
                <input type="text" id="phoneNumber" name="phoneNumber"  placeholder="Enter Your Contact Number" required style="display: block; margin: 0 auto; padding: 5px; width: 50%;">
            </div>

           
            <div class="info">
                <span for="totalPrice">Total Price RS:</span>
                <input type="text" id="totalPrice" name="totalPrice" readonly>
            </div>

            <button type="submit onclick="location.href='payment.jsp';">Book Seats</button>
        </form>

      
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>











        <script src="SeatsJS.js"></script>
    </body>
</html>
