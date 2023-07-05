import java.io.IOException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Cliente;
import Entities.Localidad;
import data.DbHandlerClientes;
import data.DbHandlerLocalidades;

/**
 * Servlet implementation class aa
 */
@WebServlet(name = "listaClientes", urlPatterns = { "/listaClientes" })
public class listaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaClientes() {
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
		DbHandlerClientes db = new DbHandlerClientes();
		DbHandlerLocalidades dbLocalidades = new DbHandlerLocalidades();
		String order = request.getParameter("order");
		Cliente cliente = null;
		if(order == null) {
			
		} else if(order.equalsIgnoreCase("add")) {
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String email = request.getParameter("email");
			String tipodni = request.getParameter("tipodni");
			String codpostal = request.getParameter("loc");
			String fecha = request.getParameter("fechanac");
			String telefono = request.getParameter("telefono");
			String tipotel = request.getParameter("tipotel");
			String pw = request.getParameter("pw");
			Localidad l = new Localidad();
			l.setCodPostal(Integer.parseInt(codpostal));
			Cliente cli = new Cliente(nombre, tipodni, dni, email, tipotel, telefono, direccion, fecha, l);
			db.addUser(cli, pw);

			
		}else if(order.split("-")[0].equalsIgnoreCase("del")) {
			String dni = order.split("-")[1];
			db.eliminaCli(dni);
			
		}else if(order.split("-")[0].equalsIgnoreCase("mod")){
			String dni = order.split("-")[1];
			cliente = db.buscaCli(dni);
			
		}else if(order.equalsIgnoreCase("modify")){
			Integer dni = Integer.parseInt(request.getParameter("dni"));
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String email = request.getParameter("email");
			String tipodni = request.getParameter("tipodni");
			Integer codpostal = Integer.parseInt(request.getParameter("loc"));
			String fecha = request.getParameter("fechanac");
			String pw = request.getParameter("pw");
			db.updateClientUser(dni, nombre, direccion, codpostal, email, fecha);
			db.updatePassword(dni, pw);
		}
		

		LinkedList<Cliente> clientes = db.selectCliente();
		LinkedList<Localidad> localidades = dbLocalidades.selectLocalidades();
		
		request.setAttribute("listCli", clientes);
		request.setAttribute("localidades", localidades);
		request.setAttribute("cliente", cliente);
		request.getRequestDispatcher("listaClientes.jsp").forward(request, response);
		
	}

}
