package astrology.contact;

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

@WebServlet("/contact")
public class Contact extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String email_id = req.getParameter("emailid");
        String district = req.getParameter("district");
        String state = req.getParameter("state");
        String comments = req.getParameter("comments");
        Connection con = null;
        PreparedStatement ps = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/astrology", "root", "root");
            String query = "INSERT INTO contact (firstname, lastname, emailid, district, state, comments) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email_id);
            ps.setString(4, district);
            ps.setString(5, state);
            ps.setString(6, comments);
            ps.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
