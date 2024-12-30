/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.movieadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/forgotPasswordServlet")
public class ForgetPassword extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abccinema";
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "2001"; 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user inputs
        String username = request.getParameter("username");
        String newPassword = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

           
            String checkEmailSql = "SELECT * FROM admin WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(checkEmailSql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
              
                String updatePasswordSql = "UPDATE admin SET password = ? WHERE username = ?";
                PreparedStatement updatePstmt = conn.prepareStatement(updatePasswordSql);
                updatePstmt.setString(1, newPassword);
                updatePstmt.setString(2, username);
                int rowsUpdated = updatePstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password changed successfully!');");
                    out.println("location='LoginPage.jsp';"); 
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Error updating password. Please try again.');");
                    out.println("location='ForgetPassword.jsp';");
                    out.println("</script>");
                }
            } else {
                // Email doesn't exist
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Entered email is incorrect!');");
                out.println("location='ForgetPassword.jsp';");
                out.println("</script>");
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('An error occurred while processing your request.');");
            out.println("location='ForgetPassword.jsp';");
            out.println("</script>");
        }
    }
}
