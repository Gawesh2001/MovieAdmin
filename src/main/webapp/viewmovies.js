// JavaScript code to dynamically load movies if needed (if you're using JS to load content)
function loadMovies() {
    const movieListContainer = document.querySelector('.movies-container');
    movieListContainer.innerHTML = ''; // Clear the container

    movies.forEach(movie => {
        const movieItem = document.createElement('div');
        movieItem.classList.add('movie-container');
        
        movieItem.innerHTML = `
            <img src="${movie.image}" alt="${movie.title}" class="movie-thumbnail">
            <div class="movie-details">
                <h3>${movie.title}</h3>
                <p>Genre: ${movie.genre}</p>
                <p>Duration: ${movie.duration}</p>
                <p>${movie.description}</p>
                <a href="${movie.detailsPage}" class="view-details-btn">View Details</a>
            </div>
        `;
        
        movieListContainer.appendChild(movieItem);
    });
}

document.addEventListener('DOMContentLoaded', loadMovies);
