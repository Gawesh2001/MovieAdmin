<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Modern Movie Seat Booking</title>
  <link rel="stylesheet" href="SeatCss.css" />
</head>
<body>
  <h1>Wicked - Movie Seat Booking</h1>
  <div class="movie-container">
    <img
      src="C:/Users/buddi/OneDrive/Desktop/wicked.jpg"
      alt="Movie Poster"
      class="movie-poster"
    />
    <div class="movie-details">
      <p>PVR One Galle Face Mall ? 22 Nov 24, 03:00 PM</p>
      <label for="movie">Select Ticket Type:</label>
      <select id="movie">
        <option value="1500">Basic (RS. 1500)</option>
        <option value="2000">Standard (RS. 2000)</option>
        <option value="2600">Premium (RS. 2600)</option>
      </select>
    </div>
  </div>

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

  <div class="screen"></div>
  
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
      
    <!-- Add more rows if necessary -->
  </div>

  <p class="text">
    You have selected <span id="count">0</span> seat(s) for a total price of RS.<span id="total">0</span>.
  </p>

  <button id="confirm">Confirm Booking</button>

  <script src="SeatsJS.js"></script>
</body>
</html>
