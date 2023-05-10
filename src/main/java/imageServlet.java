import java.io.IOException;

import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "imageServlet", urlPatterns = { "/imageServlet" })
public class imageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = 1;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpsuper?useLegacyDatetimeCode=false&serverTimezone=UTC", "matias", "1234");
        System.out.println(conn);
        // Query for the image data
        pstmt = conn.prepareStatement("SELECT p.foto as 'foto' FROM producto p WHERE p.idProducto=?");
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        System.out.println("aca llego");

        if (rs.next()) {
            // Retrieve the image data from the database
        	System.out.println("todo ok");
            Blob imageBlob = rs.getBlob("foto");
            byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

            // Set the content type and output stream
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();
            out.write(imageData);
            out.close();
        } else {
            // Image not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } finally {
        // Close the database resources
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    request.getRequestDispatcher("imageServlet.jsp").forward(request, response);
}
}
