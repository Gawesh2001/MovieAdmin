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

document.addEventListener("scroll", () => {
  const poster = document.querySelector(".movie-poster");

  if (poster) {
    // Get scroll position
    const scrollPosition = window.scrollY;

    // Log the scroll position for debugging
    console.log("Scroll Position:", scrollPosition);

    // Calculate the rotation value based on scroll position
    // The rotateX will go from 0deg to -65deg as you scroll down (rotating in the opposite direction)
    const rotateX = Math.max(-65, -scrollPosition / 6.5); // RotateX up to -65 degrees (opposite direction)

    // Apply the new transform value to the movie poster
    poster.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(-0deg)`; 
  } else {
    console.log("Movie poster not found.");
  }
});

document.addEventListener("scroll", () => {
  const seats = document.querySelectorAll(".seat, .pseat"); // Select all normal and premium seats

  // Get scroll position
  const scrollPosition = window.scrollY;

  // Log the scroll position for debugging
  console.log("Scroll Position:", scrollPosition);

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



updateSelectedCount();
