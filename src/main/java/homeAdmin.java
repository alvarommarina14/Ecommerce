

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DbHandlerEnvioDomicilio;
import Entities.EnvioDomicilio;
/**
 * Servlet implementation class homeAdmin
 */
@WebServlet(name = "homeAdmin", urlPatterns = { "/homeAdmin" })
public class homeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbHandlerEnvioDomicilio db = new DbHandlerEnvioDomicilio();
		ArrayList<EnvioDomicilio> lista = db.selectEnviosPendientes();
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
	}

}
