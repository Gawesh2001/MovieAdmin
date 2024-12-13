<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Modern Movie Seat Booking</title>
  <link rel="stylesheet" href="SeatCss.css" />
</head>
<body>
  <!-- Display the movie name dynamically -->
  <h1>${param.movieName} - Movie Seat Booking</h1>
   <p>ABC Cinema Galle Face Mall</p>
   <p><strong>Description: All the Movies we are showing has the 100% authraization from the Movie owner </strong> ${param.movieDescription}</p>

   <!-- Display the Movie ID -->
  <h6>Movie ID- ${param.movieid}</h6>
  
  
     <ul class="showcase">
    <li>
      <div class="seat"></div>
      <small>Available</small>
    </li>
    <li>
      <div class="seat selected"></div>
      <small>Selected</small>
    </li>
    <li>
      <div class="seat sold"></div>
      <small>Sold</small>
    </li>
  </ul>
   
<div class="date-selection">
  <label for="movie-date">Select Date:</label>
  <input type="date" id="movie-date" class="date-picker" />
  <span id="selected-date" class="selected-date"></span>
</div>

<div class="time-selection">
    <label for="movie-time" style="font-size: 16px; margin-top: 10px; font-weight: bold;">Show Time:</label>

    <button id="movie-time" class="time-picker">${param.timeframe}</button>
    

</div>

   
   
  <div class="movie-container">
    <!-- Dynamically set the movie poster image -->
    <img
      src="${param.movieThumbnail}"
      alt="${param.movieName} Poster"
      class="movie-poster"
    />
    
  <div class="movie-details">
      
     
      
    </div>
  </div>

  <!-- Showcase for seat statuses -->


  <!-- Screen display -->
  <!-- <div class="screen"></div> -->

  <!-- Seat rows for booking -->
  <div class="container">
    <div class="row">
      <div class="seat">A01</div>
      <div class="seat">A02</div>
      <div class="seat">A03</div>
      <div class="seat">A04</div>
      <div class="seat">A05</div>
      <div class="aisle"></div>
      <div class="seat">A06</div>
      <div class="seat">A07</div>
      <div class="seat">A08</div>
      <div class="seat">A09</div>
    </div>

    <div class="row">
      <div class="seat">B01</div>
      <div class="seat">B02</div>
      <div class="seat">B03</div>
      <div class="seat">B04</div>
      <div class="seat">B05</div>
      <div class="seat">B06</div>
      <div class="seat">B07</div>
      <div class="aisle"></div>
      <div class="seat">B08</div>
      <div class="seat">B09</div>
      <div class="seat">B10</div>
      <div class="seat">B11</div>
      <div class="seat">B12</div>
      <div class="seat">B13</div>
    </div>

    <div class="row">
      <div class="seat">C01</div>
      <div class="seat">C02</div>
      <div class="seat">C03</div>
      <div class="seat">C04</div>
      <div class="seat">C05</div>
      <div class="seat">C06</div>
      <div class="seat">C07</div>
      <div class="seat">C08</div>
      <div class="seat">C09</div>
      <div class="aisle"></div>
      <div class="seat">C10</div>
      <div class="seat">C11</div>
      <div class="seat">C12</div>
      <div class="seat">C13</div>
      <div class="seat">C14</div>
      <div class="seat">C15</div>
      <div class="seat">C16</div>
      <div class="seat">C17</div>
    </div>

    <div class="row">
      <div class="pseat">P01</div>
      <div class="pseat">P02</div>
      <div class="pseat">P03</div>
      <div class="pseat">P04</div>
      <div class="pseat">P05</div>
      <div class="paisle"></div>
      <div class="pseat">P06</div>
      <div class="pseat">P07</div>
      <div class="pseat">P08</div>
      <div class="pseat">P09</div>
      <div class="pseat">P10</div>
    </div>
  </div>

  <!-- Seat selection summary -->
  <p class="text">
    You have selected <span id="count">0</span> seat(s) for a total price of RS.<span id="total">0</span>.
  </p>

  <!-- Confirm booking button -->
  <button id="confirm">Confirm Booking</button>

  <!-- Link to JavaScript for functionality -->
  <script src="SeatsJS.js"></script>
</body>
</html>
