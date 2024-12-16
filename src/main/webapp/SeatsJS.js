




flatpickr("#selectedDate", {
    dateFormat: "Y-m-d", 
});


document.getElementById("bookingForm").addEventListener("submit", function (event) {
    var selectedDate = document.getElementById("selectedDate").value;

    // Validate if a valid date is selected
    if (!selectedDate) {
        event.preventDefault();
        alert('Please select a valid date.');
    } else {
        // Assign the selected date to the hidden input field (for submission)
        document.getElementById("hiddenSelectedDate").value = selectedDate;
    }
});




// Function to handle scroll effects
function handleScrollEffects() {
    const scrollPosition = window.scrollY;
    const pageHeight = document.documentElement.scrollHeight - window.innerHeight;
    const scrollMidpoint = pageHeight / 2; 
    const poster = document.querySelector('.movie-thumbnail');
    const seats = document.querySelectorAll('.seats label');
    const tvScreen = document.querySelector('.tv-screen');  

    
    if (poster && tvScreen) {
        
        const rotateX = Math.max(-35, Math.min(-scrollPosition / 3.5, 35));  

       
        tvScreen.style.transform = `perspective(1500px) rotateX(${rotateX}deg) rotateY(0deg)`;
        poster.style.transform = `perspective(1500px) rotateX(${rotateX}deg) rotateY(0deg)`;
    }

    
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


//  handle scroll effect on seat buttons 
document.addEventListener('scroll', function () {
   
    const seatLabels = document.querySelectorAll('label');

    
    let scrollPosition = window.scrollY;

    
    if (scrollPosition > 100) {  
        seatLabels.forEach(label => {
            label.classList.add('scroll-effect');
        });
    } else {
        seatLabels.forEach(label => {
            label.classList.remove('scroll-effect');
        });
    }
});








let selectedDate = ""; // store selected date


document.addEventListener("DOMContentLoaded", function () {
    const datePicker = flatpickr("#selectedDate", {
        dateFormat: "Y-m-d",
        defaultDate: "today",
        onClose: function (selectedDates, dateStr) {
            selectedDate = dateStr; 
            fetchData();

        }
    });

    
    window.showDatePicker = function () {
        datePicker.open(); 
    };
});


function fetchData() {
    console.log("test")
    if (!selectedDate) {
        alert("Please select a date first!");
        return;
    }

    
    fetch("RetrieveTicketsByDateServlet?selectedDate=" + selectedDate)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                var ele = document.getElementById("seating-area-wrap");

                let combinedData = "";
                const seatSet = new Set(data); 
                for (let i = 1; i <= 40; i++) {
                    const seatId = "S" + i;
                    const isBooked = seatSet.has(seatId) ? "booked" : ""; 

                    combinedData += '<input type="checkbox" id="seat' + i + '" name="selectedSeats" value="' + seatId + '" class="seat ' + isBooked + '"><label for="seat' + i + '">' + seatId + '</label>';
                }

                ele.innerHTML = combinedData;

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

            })
            .catch(error => {
                console.error('Error:', error);
                alert("An error occurred while fetching data.");
            });
}

    