

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Cliente;
import Entities.EnvioDomicilio;
import Entities.Producto;
import data.DbHandlerClientes;
import data.DbHandlerEnvioDomicilio;
import data.DbHandlerProductos;

/**
 * Servlet implementation class home
 */
@WebServlet(name = "home", urlPatterns = { "/home" })
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		DbHandlerClientes db = new DbHandlerClientes();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Cliente cli = db.loginCliente(email, password);
		HttpSession session = request.getSession();
		String redirect = (String) session.getAttribute("shouldRedirect"); // checks that there is an order ready to proceed to payment
		Boolean redirectMyAcc = (Boolean) session.getAttribute("redirectMyAcc"); // checks if should redirect to My Account section after login
		
		if(cli == null) { // user doesn't exist or password is incorrect
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.getRequestDispatcher("loginFailed.jsp").forward(request, response);
		} else if(redirect == "yes"){
		session.setAttribute("cliente", cli);	
		request.setAttribute("password", password);
		request.getRequestDispatcher("metodoPago.jsp").forward(request, response);
		} else if(redirectMyAcc != null) {
			redirectMyAcc = false;
			session.setAttribute("cliente", cli);
			request.getRequestDispatcher("myAccount.jsp").forward(request, response);
		} else if(cli.getUser_type().toString().equals("USER")){ // verifies if the user is an admin
			session.setAttribute("cliente", cli);	
			request.getRequestDispatcher("index.html").forward(request, response);
		} else {
			DbHandlerEnvioDomicilio dbED = new DbHandlerEnvioDomicilio();
			ArrayList<EnvioDomicilio> lista = dbED.selectEnviosPendientes();
			
			request.setAttribute("lista", lista);
			session.setAttribute("cliente", cli);	
			request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
		}
	}

}
