// Get elements by their ID
const manageMoviesLink = document.getElementById('manageMovies');
const manageMoviesSection = document.getElementById('manageMoviesSection');

// Add click event listener to the "Manage Movies" link
manageMoviesLink.addEventListener('click', function() {
    // Toggle the display of the Manage Movies section
    if (manageMoviesSection.style.display === 'none' || manageMoviesSection.style.display === '') {
        manageMoviesSection.style.display = 'block';  // Show section
    } else {
        manageMoviesSection.style.display = 'none';   // Hide section
    }
});
function toggleManageMovies() {
    const manageMoviesSection = document.getElementById('manageMoviesSection');

    // Toggle visibility of the "Manage Movies" section
    if (manageMoviesSection.style.display === "none" || manageMoviesSection.style.display === "") {
        manageMoviesSection.style.display = "block"; // Show the section
    } else {
        manageMoviesSection.style.display = "none"; // Hide the section
    }
}
// JavaScript to toggle the sidebar
const menuBtn = document.querySelector('.menu-btn');
const sidebar = document.querySelector('.sidebar');

// Toggle the 'hidden' class on the sidebar when the menu button is clicked
menuBtn.addEventListener('click', () => {
    sidebar.classList.toggle('hidden');
});

