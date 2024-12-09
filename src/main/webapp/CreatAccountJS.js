/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// JavaScript for client-side password matching validation
document.querySelector('form').addEventListener('submit', function(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        event.preventDefault(); // Prevent form submission
        alert('Passwords do not match!');
    }
});

// Function to show an alert box and then redirect to the login page
function showAlertAndRedirect() {
    // Show the alert box
    alert("Account created successfully!");

    // Redirect to the login page after the user clicks "OK" on the alert
    window.location.href = "LoginPage.jsp";  // Redirect to the login page
    
    // Return false to prevent the form from actually submitting (since we're handling the redirect)
    return false;
}
