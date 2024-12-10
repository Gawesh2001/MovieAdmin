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