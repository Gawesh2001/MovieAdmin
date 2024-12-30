/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.movieadmin;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2001"; 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");  

       
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password); 

           
            int rowsAffected = pstmt.executeUpdate();

            
            if (rowsAffected > 0) {
                response.sendRedirect("LoginPage.jsp"); 
            } else {
                response.getWriter().println("<p>Error during registration. Please try again.</p>");
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            // Handle exceptions (e.g., SQLException, ClassNotFoundException)
            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
            }
        }
    }
}