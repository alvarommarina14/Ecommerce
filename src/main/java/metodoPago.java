

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Cliente;
import Entities.Provincia;
import Entities.Sucursal;
import data.DbHandlerClientes;
import data.DbHandlerProvincias;
import data.DbHandlerSucursales;

/**
 * Servlet implementation class metodoPago
 */
@WebServlet(name = "metodoPago", urlPatterns = { "/metodoPago" })
public class metodoPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public metodoPago() {
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
		HttpSession session = request.getSession();
		DbHandlerProvincias dbProvincias = new DbHandlerProvincias();
		LinkedList<Provincia> provincias = dbProvincias.selectProvincia();
		DbHandlerSucursales dbSucursales = new DbHandlerSucursales();
		LinkedList<Sucursal> sucursales = dbSucursales.selectSucursal();
		for(Sucursal s: sucursales) {
			System.out.println(s.getDireccion());
		}
		session.setAttribute("provincias", provincias);
		session.setAttribute("sucursales", sucursales);
		
		String ch1 = request.getParameter("checkbox-delivery");
		String ch3 = request.getParameter("checkbox-cash");
		if(ch1 == null) {
			session.setAttribute("withdraw", "branch");
		} else {
			session.setAttribute("withdraw", "delivery");
		}
		if(ch3 == null) {
			session.setAttribute("payment", "card");
		} else {
			session.setAttribute("payment", "cash");
		}
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String option = request.getParameter("fin"); // button that either cancels the purchase or proceeds to payment
		System.out.println(option);
		if(cliente == null) {
			session.setAttribute("shouldRedirect", "yes"); // should redirect to "compraConfirmada"
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if(option.equals("proceed")) {
		System.out.println("entre al proceed");
		request.getRequestDispatcher("metodoPago.jsp").forward(request, response);
		} else if(option.equals("end")) {
		System.out.println("option = "+option);
		session.removeAttribute("listaLC");
		session.removeAttribute("pedidoCreado");
		request.getRequestDispatcher("index.html").forward(request, response);
	} 
}}
