<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Movie Ticket Booking</title>
    <link rel="stylesheet" href="AddMovieCss.css">
</head>

<body>

    
    <header class="header">
        <h1>Add New Movie - Admin Dashboard</h1>
    </header>

   
    <main>
        <div class="container">
            <h2>Add Movie Details</h2>

           
            <form name="movieForm" action="addmovie" method="POST" class="form">
                <div class="form-group">
                    <label for="movieId">Movie ID</label>
                    <input type="text" id="movieId" name="movieId" readonly class="form-control">
                </div>

                <div class="form-group">
                    <label for="movieName">Movie Name</label>
                    <input type="text" id="movieName" name="movieName" placeholder="Enter movie name" required
                        class="form-control">
                </div>

                <div class="form-group">
                    <label for="movieCategory">Movie Category</label>
                    <input type="text" id="movieCategory" name="movieCategory" placeholder="Action, Adventure, Romance"
                        required class="form-control">
                </div>

                <div class="form-group">
                    <label for="releaseDate">Release Date</label>
                    <input type="date" id="releaseDate" name="releaseDate" required class="form-control">
                </div>

                <div class="form-group">
                    <label for="showingDateTime">Showing Date & Time</label>
                    <input type="datetime-local" id="showingDateTime" name="showingDateTime" required
                        class="form-control">
                </div>

                <div class="form-group">
                    <label for="movieThumbnail">Movie Thumbnail (URL)</label>
                    <input type="text" id="movieThumbnail" name="movieThumbnail" placeholder="Enter thumbnail URL"
                        required class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="trailer">Movie Trailer</label>
                    <input type="text" id="trailer" name="trailer" placeholder="Enter Trailer URL"
                        required class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="duration">Movie Duration</label>
                    <input type="text" id="duration" name="duration" placeholder="Enter the duration "
                        required class="form-control">
                </div>

                <div class="form-group">
                    <label for="movieDescription">Movie Description</label>
                    <textarea id="movieDescription" name="movieDescription" placeholder="Enter movie description" rows="4"
                        required class="form-control"></textarea>
                </div>

                <button type="submit" class="btn-submit">Add Movie</button>
            </form>

            <div class="back-button">
                <a href="DashBoard.jsp" class="btn-back">Back to Dashboard</a>
            </div>
        </div>

        <div id="messageBox" class="message-box" style="display: none;">
            <span id="messageContent"></span>
        </div>
    </main>

    
    <script src="AddMovieJS.js"></script>

</body>

</html>
