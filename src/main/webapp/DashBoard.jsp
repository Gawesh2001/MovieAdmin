<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="DashboardCss.css"/>
</head>



<body>

   

   
    <div class="sidebar">
        <div class="logo">
           
            <h2> <span class="menu-icon">&#9776;</span> Admin Panel</h2>
        </div>
        <ul class="menu">
            <li><a href="ticketBooking.jsp">Book A Movie</a></li>
            <li><a href="reports.html">Reports</a></li>
            <li><a href="javascript:void(0);" id="manageMoviesLink" onclick="toggleManageMovies()">Manage Movies</a></li>
            <li><a href="CancelTickets.jsp">Cancel Tickets</a></li>
            <li><a href="FeedbackJS.jsp">Feedback</a></li>
            <li><a href="LoginPage.jsp" id="logout"style="color: black; background-color: lightgray;" onmouseover="this.style.backgroundColor='red'; this.style.color='white';" onmouseout="this.style.backgroundColor='lightgray'; this.style.color='black';" onclick="confirmLogout()">Logout</a></li>
          
        </ul>
    </div>

  
    <div class="main-content">
        
      
        <div id="manageMoviesSection" style="display: none;">
            <div class="dashboard-container">
                <div class="dashboard-item" onclick="window.location.href='AddMovie.jsp';">
                    <h2>Add Movie</h2>
                    <p>Add new movies to the system.</p>
                </div>

                <div class="dashboard-item" onclick="window.location.href='ViewMovies.jsp';">
                    <h2>View Movies</h2>
                    <p>View and manage all listed movies.</p>
                </div>
                
                
            </div>
        </div>
    </div>

    <script src="DashBoardJS.js"></script>
</body>
</html>
