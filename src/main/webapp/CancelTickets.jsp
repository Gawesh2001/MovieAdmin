<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cancel Ticket</title>
    <link rel="stylesheet" href="CancelTicket.css">
</head>
<body>
    <!-- Header Section -->
    <header class="header">
        <h1>Cancel Ticket</h1>
    </header>

    <!-- Main Content Section -->
    <main>
        <div class="container">
            <h2>Cancel Tickets</h2>
            <div class="cancel-tickets-container">

                <!-- Ticket Cancellation Form -->
                <form method="get" action="CancelTicketServlet">
                    <div class="form-group">
                        <label for="ticketId">Ticket ID:</label>
                        <input type="text" id="ticketId" name="ticketId" placeholder="Enter ticket ID" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number:</label>
                        <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" 
                               pattern="[0-9]{10}" required>
                        <small>Format: 10 digits only</small>
                    </div>
                    <div class="form-group">
                        <label for="date">Date:</label>
                        <input type="date" id="date" name="date" required>
                    </div>
                    <button type="submit" class="cancel-btn">Cancel Ticket</button>
                </form>

                <!-- Success/Error Messages -->
                <div class="message">
                    <%
                        String message = (String) request.getAttribute("message");
                        if (message != null) {
                            out.print("<p>" + message + "</p>");
                        }
                    %>
                </div>

            </div>
        </div>
    </main>
</body>
</html>
