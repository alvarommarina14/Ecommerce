import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DbHandlerLineaPedido;
import data.DbHandlerProvincias;
import Entities.LineaCompra;
import Entities.Provincia;

/**
 * Servlet implementation class finalizarCompra
 */
@WebServlet(name = "finalizarCompra", urlPatterns = { "/finalizarCompra" })
public class finalizarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finalizarCompra() {
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
		Integer idPedido = (Integer) session.getAttribute("idPedido");
		DbHandlerLineaPedido db = new DbHandlerLineaPedido();
		LinkedList<LineaCompra> listaLC = (LinkedList<LineaCompra>) session.getAttribute("listaLC");
		String delete = request.getParameter("delete-prod");
		if(delete != null) { 
			Integer buySize = Integer.parseInt(request.getParameter("list-size"));
			if(buySize>1) {
				Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
				System.out.println("id producto "+ idProducto);
				db.deleteLineaProducto(idPedido, idProducto);
			}
		}
		request.getRequestDispatcher("finalizarCompra.jsp").forward(request, response);
	}

}
