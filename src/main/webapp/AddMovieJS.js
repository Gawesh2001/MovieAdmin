
const manageMoviesLink = document.getElementById('manageMovies');
const manageMoviesSection = document.getElementById('manageMoviesSection');


manageMoviesLink.addEventListener('click', function() {
 
    if (manageMoviesSection.style.display === 'none' || manageMoviesSection.style.display === '') {
        manageMoviesSection.style.display = 'block';  
    } else {
        manageMoviesSection.style.display = 'none';   
    }
});
function toggleManageMovies() {
    const manageMoviesSection = document.getElementById('manageMoviesSection');

 
    if (manageMoviesSection.style.display === "none" || manageMoviesSection.style.display === "") {
        manageMoviesSection.style.display = "block"; 
    } else {
        manageMoviesSection.style.display = "none"; 
    }
}

const menuBtn = document.querySelector('.menu-btn');
const sidebar = document.querySelector('.sidebar');


menuBtn.addEventListener('click', () => {
    sidebar.classList.toggle('hidden');
});

