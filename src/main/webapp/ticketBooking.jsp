<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <!-- Displaying the movies dynamically using JSTL -->
        <c:forEach var="movie" items="${movies}">
            <div class="movie-container">
                <img src="${movie.movieThumbnail}" alt="${movie.movieName}" class="movie-thumbnail">
                <h2>${movie.movieName}</h2>
                <p><strong>Category:</strong> ${movie.movieCategory}</p>
                <p><strong>Release Date:</strong> ${movie.releaseDate}</p>
                <button class="book-button">Book Ticket</button>
            </div>
        </c:forEach>
    </div>

    <script src="ticketBooking.js"></script> <!-- Link to JavaScript file -->
</body>
</html>
