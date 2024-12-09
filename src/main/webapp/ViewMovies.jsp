<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie List</title>
    <link rel="stylesheet" href="ViewMoviesCss.css">
</head>
<body>

    <div class="container">
        <c:forEach var="movie" items="${movieList}" varStatus="status">
            <div class="movie-container">
                <img src="${movie.movieThumbnail}" alt="${movie.movieName}" class="movie-thumbnail">
                <h3 class="movie-title">${movie.movieName}</h3>
                <p class="movie-category">${movie.movieCategory}</p>
                <p class="movie-description">${movie.movieDescription}</p>
                <p class="movie-release-date">Release Date: ${movie.releaseDate}</p>
                <p class="movie-showing-time">Showing: ${movie.showingDateTime}</p>
            </div>

            <c:if test="${status.index % 4 == 3}"> <!-- Every 4th movie, create a new row -->
                <div class="clear"></div>
            </c:if>
        </c:forEach>
    </div>

</body>
</html>
