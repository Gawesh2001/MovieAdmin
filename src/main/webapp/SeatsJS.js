const container = document.querySelector(".container");
const seats = document.querySelectorAll(".row .seat:not(.sold), .row .pseat"); // Include premium seats
const count = document.getElementById("count");
const total = document.getElementById("total");
const confirmButton = document.getElementById("confirm");
const timeButtons = document.querySelectorAll(".time-button");

const normalSeatPrice = 1000; // Price for normal seats
const premiumSeatPrice = 2500; // Price for premium seats

let selectedTime = "";

// Handle time selection
timeButtons.forEach((button) => {
  button.addEventListener("click", () => {
    // Remove 'selected' class from all buttons
    timeButtons.forEach((btn) => btn.classList.remove("selected"));
    
    // Add 'selected' class to the clicked button
    button.classList.add("selected");
    
    // Update the selected time
    selectedTime = button.textContent;

    // Display the selected time in the text below
    const timeDisplay = document.querySelector(".text");
    timeDisplay.innerHTML = `You have selected <span id="count">${count.innerText}</span> seat(s) for a total price of RS.<span id="total">${total.innerText}</span>. Time is - ${selectedTime}.`;
  });
});

// Handle seat selection
container.addEventListener("click", (e) => {
  if (
    (e.target.classList.contains("seat") || e.target.classList.contains("pseat")) && // Include premium seats in the check
    !e.target.classList.contains("sold")
  ) {
    e.target.classList.toggle("selected");
    updateSelectedCount();
  }
});

// Update the seat count and total price
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".row .seat.selected, .row .pseat.selected");
  const selectedSeatsCount = selectedSeats.length;

  // Calculate the total price based on selected seat types
  let totalPrice = 0;
  selectedSeats.forEach((seat) => {
    if (seat.classList.contains("pseat")) {
      totalPrice += premiumSeatPrice; // Add premium price for premium seats
    } else {
      totalPrice += normalSeatPrice; // Add normal price for normal seats
    }
  });

  count.innerText = selectedSeatsCount;
  total.innerText = totalPrice;
}

// Confirm booking and show the alert
confirmButton.addEventListener("click", () => {
  alert(`Booking confirmed for ${count.innerText} seat(s)!`);
});

updateSelectedCount();
