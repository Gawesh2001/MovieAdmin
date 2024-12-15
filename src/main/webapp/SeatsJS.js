document.addEventListener('DOMContentLoaded', () => {
    const seats = document.querySelectorAll('input[type="checkbox"]');
    const totalPriceField = document.getElementById('totalPrice');
    const seatPrice = 1500; // Example price per seat

    seats.forEach(seat => {
        seat.addEventListener('change', () => {
            const selectedSeats = Array.from(seats)
                .filter(seat => seat.checked)
                .map(seat => seat.value);

            totalPriceField.value = selectedSeats.length * seatPrice;
        });
    });

    const form = document.getElementById('bookingForm');
    form.addEventListener('submit', (e) => {
        const selectedSeats = Array.from(seats).filter(seat => seat.checked);
        if (selectedSeats.length === 0) {
            e.preventDefault();
            alert('Please select at least one seat.');
        }
    });
});
// Function to handle scroll effects
function handleScrollEffects() {
    const scrollPosition = window.scrollY;
    const poster = document.querySelector('.movie-thumbnail');
    const seats = document.querySelectorAll('.seats label');

    // Apply rotation effect on the movie poster
    if (poster) {
        const rotateX = Math.max(-65, -scrollPosition / 6.5);
        poster.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(0deg)`;
    }

    // Apply shadow effect to seat labels based on scroll position
    seats.forEach(seat => {
        const shadowIntensity = Math.min(10, scrollPosition / 50);
        seat.style.boxShadow = scrollPosition > 0
            ? `inset -2px -2px 5px rgba(255, 255, 255, 0.2),
               inset 2px 2px 5px rgba(0, 0, 0, 0.5),
               0 -${6 + shadowIntensity}px ${12 + shadowIntensity}px rgba(255, 255, 255, 0.6),
               0 -${10 + shadowIntensity}px ${15 + shadowIntensity}px rgba(0, 0, 0, 0.3)`
            : `inset -2px -2px 5px rgba(255, 255, 255, 0.2),
               inset 2px 2px 5px rgba(0, 0, 0, 0.5),
               0 4px 10px rgba(0, 0, 0, 0.4)`;
    });
}

// Listen for scroll event
window.addEventListener('scroll', handleScrollEffects);


// JavaScript to handle scroll effect on seat buttons when scrolling the page
document.addEventListener('scroll', function() {
    // Select all seat labels (buttons)
    const seatLabels = document.querySelectorAll('label');

    // Check the scroll position
    let scrollPosition = window.scrollY;

    // If scrolled down a bit, add the light drop effect class to seat labels
    if (scrollPosition > 100) {  // You can adjust this value as needed
        seatLabels.forEach(label => {
            label.classList.add('scroll-effect');
        });
    } else {
        seatLabels.forEach(label => {
            label.classList.remove('scroll-effect');
        });
    }
});


