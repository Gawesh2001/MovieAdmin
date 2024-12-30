/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



document.querySelector('form').addEventListener('submit', function(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        event.preventDefault(); 
        alert('Passwords do not match!');
    }
});


function showAlertAndRedirect() {
    
    alert("Account created successfully!");

    
    window.location.href = "LoginPage.jsp";  
    
   
    return false;
}
