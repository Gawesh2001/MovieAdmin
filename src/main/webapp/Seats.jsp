<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Modern Movie Seat Booking</title>
  <style>
    /* General Reset */
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: "Poppins", sans-serif;
      background: linear-gradient(135deg, #1f1c2c, #928dab);
      color: #fff;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
    }

    h1, p {
      text-align: center;
      margin-bottom: 20px;
    }

    .movie-container {
      display: flex;
      align-items: flex-start;
      gap: 20px;
      margin-bottom: 20px;
    }

    .movie-poster {
      width: 180px;
      border-radius: 12px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
    }

    .movie-details {
      flex: 1;
    }

    select {
      background-color: #fff;
      color: #333;
      padding: 10px;
      border-radius: 5px;
      font-size: 16px;
      margin-top: 10px;
      outline: none;
      cursor: pointer;
      border: 1px solid #ddd;
      transition: box-shadow 0.2s ease-in-out;
    }

    select:focus {
      box-shadow: 0 0 10px rgba(255, 255, 255, 0.7);
    }

    .showcase {
      display: flex;
      justify-content: space-between;
      max-width: 400px;
      margin: 20px auto;
      gap: 10px;
    }

    .showcase li {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .showcase li small {
      font-size: 12px;
      margin-top: 5px;
    }

    .seat {
      background-color: #444451;
      height: 30px;
      width: 30px;
      margin: 5px;
      border-radius: 6px;
      transition: all 0.3s ease-in-out;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);
    }

    .seat.selected {
      background-color: #6feaf6;
    }

    .seat.sold {
      background-color: #ff6f61;
      cursor: not-allowed;
    }

    .seat:not(.sold):hover {
      transform: scale(1.2);
    }

    .container {
      display: grid;
      grid-template-rows: repeat(4, auto);
      gap: 10px;
      justify-content: center;
      align-items: center;
      margin-bottom: 20px;
    }

    .row {
      display: grid;
      grid-template-columns: repeat(12, 30px);
      gap: 8px;
      justify-content: center;
    }

    .aisle {
      width: 20px;
    }

    .screen {
      background: linear-gradient(90deg, #d8d8d8, #e6e6e6);
      height: 20px;
      width: 80%;
      margin: 15px auto;
      border-radius: 5px;
      box-shadow: 0 2px 10px rgba(255, 255, 255, 0.5);
    }

    button {
      background-color: #6c5ce7;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #a29bfe;
    }

    @media screen and (max-width: 600px) {
      .movie-container {
        flex-direction: column;
        align-items: center;
      }

      .movie-details {
        text-align: center;
      }

      .screen {
        width: 90%;
      }

      .row {
        grid-template-columns: repeat(8, 30px);
      }
    }
  </style>
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
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="aisle"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
      <div class="seat"></div>
    </div>
    <!-- Add more rows if necessary -->
  </div>

  <p class="text">
    You have selected <span id="count">0</span> seat(s) for a total price of RS.<span id="total">0</span>.
  </p>

  <button id="confirm">Confirm Booking</button>

  <script>
    const container = document.querySelector(".container");
    const seats = document.querySelectorAll(".row .seat:not(.sold)");
    const count = document.getElementById("count");
    const total = document.getElementById("total");
    const movieSelect = document.getElementById("movie");
    const confirmButton = document.getElementById("confirm");

    let ticketPrice = +movieSelect.value;

    container.addEventListener("click", (e) => {
      if (
        e.target.classList.contains("seat") &&
        !e.target.classList.contains("sold")
      ) {
        e.target.classList.toggle("selected");
        updateSelectedCount();
      }
    });

    movieSelect.addEventListener("change", (e) => {
      ticketPrice = +e.target.value;
      updateSelectedCount();
    });

    function updateSelectedCount() {
      const selectedSeats = document.querySelectorAll(".row .seat.selected");
      const selectedSeatsCount = selectedSeats.length;

      count.innerText = selectedSeatsCount;
      total.innerText = selectedSeatsCount * ticketPrice;
    }

    confirmButton.addEventListener("click", () => {
      alert(`Booking confirmed for ${count.innerText} seat(s)!`);
    });

    updateSelectedCount();
  </script>
</body>
</html>
