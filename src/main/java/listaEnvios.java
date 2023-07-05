

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Categoria;
import Entities.EnvioDomicilio;
import data.DbHandlerEnvioDomicilio;

/**
 * Servlet implementation class listaEnvios
 */
@WebServlet(name = "listaEnvios", urlPatterns = { "/listaEnvios" })
public class listaEnvios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaEnvios() {
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
		EnvioDomicilio envio = new EnvioDomicilio();
		String order = request.getParameter("order");
		if(order.equalsIgnoreCase("desc")) {
			
		}else if(order.split("-")[0].equalsIgnoreCase("del")) {
		int id = Integer.parseInt(order.split("-")[1]);
		db.deleteEnvio(id);
		}else if(order.split("-")[0].equalsIgnoreCase("mod")){
			int id = Integer.parseInt(order.split("-")[1]);
			envio = db.selectEnvioDomicilio(id);
		}
		else if(order.equalsIgnoreCase("modify")){
			int id = Integer.parseInt(request.getParameter("id"));
			String desc = request.getParameter("desc");
			
//			db.updateEnvio(id, fechaRealEntrega);
		}

		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listaEnvios.jsp").forward(request, response);
	}

}
