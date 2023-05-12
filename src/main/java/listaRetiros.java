

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
import Entities.RetiroSucursal;
import data.DbHandlerEnvioDomicilio;
import data.DbHandlerRetiroSucursal;

/**
 * Servlet implementation class listaEnvios
 */
@WebServlet(name = "listaRetiros", urlPatterns = { "/listaRetiros" })
public class listaRetiros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaRetiros() {
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
		DbHandlerRetiroSucursal db = new DbHandlerRetiroSucursal();
		LinkedList<RetiroSucursal> lista = db.selectRetirosSucursalPendientes();
		RetiroSucursal rs = new RetiroSucursal();
		String order = request.getParameter("order");
		if(order.equalsIgnoreCase("desc")) {
			
		}else if(order.split("-")[0].equalsIgnoreCase("del")) {
		int id = Integer.parseInt(order.split("-")[1]);
		db.deleteRetiroSucursal(id);
		}else if(order.split("-")[0].equalsIgnoreCase("mod")){
			int id = Integer.parseInt(order.split("-")[1]);
			rs = db.selectRetiroSucursal(id);
		}
		else if(order.equalsIgnoreCase("modify")){
			Integer idPedido = Integer.parseInt(request.getParameter("idPedido"));
			Integer idSucursal = Integer.parseInt(request.getParameter("idSucursal"));
			String fecha = request.getParameter("desc");
			
			db.updateRetiroSucursal(idPedido, idSucursal, fecha);
		}

		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listaRetiros.jsp").forward(request, response);
	}

}
