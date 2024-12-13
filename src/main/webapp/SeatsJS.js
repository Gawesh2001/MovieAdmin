const container = document.querySelector(".container");
const seats = document.querySelectorAll(".row .seat:not(.sold), .row .pseat"); // Include premium seats
const count = document.getElementById("count");
const total = document.getElementById("total");
const confirmButton = document.getElementById("confirm");
const timeButtons = document.querySelectorAll(".time-button");
const datePicker = document.getElementById("movie-date");
const selectedDateDisplay = document.getElementById("selected-date");

const normalSeatPrice = 1000; // Price for normal seats
const premiumSeatPrice = 2500; // Price for premium seats


let timeSelected = false;
// Handle time selection
timeButtons.forEach((button) => {
  button.addEventListener("click", () => {
    // Remove 'selected' class from all buttons
    timeButtons.forEach((btn) => btn.classList.remove("selected"));

    // Add 'selected' class to the clicked button
    button.classList.add("selected");

    // Update the selected time
    selectedTime = button.textContent;

    // Update the display text
    updateSelectedText();
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
    updateSelectedText();
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

  // Update the text fields for count and total
  count.innerText = selectedSeatsCount;
  total.innerText = totalPrice;
}


// Confirm booking and show the alert
confirmButton.addEventListener("click", () => {
  if (!timeSelected) {
    alert("Please select a time before confirming your booking.");
    return;
  }
  alert(`Booking confirmed for ${count.innerText} seat(s) at ${selectedTime}!`);
});

// Handle date selection
datePicker.addEventListener("change", function () {
  const selectedDate = this.value;
  selectedDateDisplay.textContent = `Selected Date: ${selectedDate}`;
  updateSelectedText();
});

// Scroll effect on movie poster
document.addEventListener("scroll", () => {
  const poster = document.querySelector(".movie-poster");

  if (poster) {
    // Get scroll position
    const scrollPosition = window.scrollY;

    // Calculate the rotation value based on scroll position
    const rotateX = Math.max(-65, -scrollPosition / 6.5); // RotateX up to -65 degrees (opposite direction)

    // Apply the new transform value to the movie poster
    poster.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(0deg)`;
  }
});

// Scroll effect on seats
document.addEventListener("scroll", () => {
  const seats = document.querySelectorAll(".seat, .pseat"); // Select all normal and premium seats

  // Get scroll position
  const scrollPosition = window.scrollY;

  // If scroll position is above 0, apply the light effect; otherwise, remove it
  if (scrollPosition > 0) {
    // Calculate how much the light effect should be applied to the top of the seat elements
    const maxShadowIntensity = 10; // Max intensity of the top-side shadow (adjust as needed)
    const shadowIntensity = Math.min(maxShadowIntensity, scrollPosition / 50); // Intensity grows as you scroll

    // Apply the dynamic box-shadow to all seat elements (top side only)
    seats.forEach((seat) => {
      seat.style.boxShadow = `
        inset -2px -2px 5px rgba(255, 255, 255, 0.2), 
        inset 2px 2px 5px rgba(0, 0, 0, 0.5),
        0 -${6 + shadowIntensity}px ${12 + shadowIntensity}px rgba(255, 255, 255, 0.6),  /* Light effect on top */
        0 -${10 + shadowIntensity}px ${15 + shadowIntensity}px rgba(0, 0, 0, 0.3)  /* Shadow effect on top */
      `;
    });
  } else {
    // Remove any applied shadow effect when the user is at the top (scroll position is 0)
    seats.forEach((seat) => {
      seat.style.boxShadow = `
        inset -2px -2px 5px rgba(255, 255, 255, 0.2), 
        inset 2px 2px 5px rgba(0, 0, 0, 0.5),
        0 4px 10px rgba(0, 0, 0, 0.4) /* Default shadow for seats without light effect */
      `;
    });
  }
});

document.getElementById("confirm").addEventListener("click", () => {
  // Get the selected seats, time, and date
  const selectedSeats = document.querySelectorAll(".row .seat.selected, .row .pseat.selected");
  const selectedTimeButton = document.querySelector(".time-button.selected");
  const selectedDate = document.getElementById("movie-date").value;

  // Initialize an empty array to store missing selections
  let missingSelections = [];

  // Check for missing selections and push corresponding messages to the array
  if (selectedSeats.length === 0) {
    missingSelections.push("seats");
  }
  if (!selectedTimeButton) {
    missingSelections.push("time slot");
  }
  if (!selectedDate) {
    missingSelections.push("date");
  }

  // If there are missing selections, display an alert
  if (missingSelections.length > 0) {
    alert(`Please select the following before confirming your booking: ${missingSelections.join(", ")}.`);
    return;
  }

  // If all validations pass, confirm the booking
  alert(`Booking confirmed for ${selectedSeats.length} seat(s) on ${selectedDate} at ${selectedTimeButton.textContent}!`);
});



// Initial updates
updateSelectedCount();
updateSelectedText();
