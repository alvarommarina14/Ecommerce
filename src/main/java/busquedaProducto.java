import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Categoria;
import Entities.Producto;
import data.DbHandlerCategorias;
import data.DbHandlerProductos;

/**
 * Servlet implementation class busquedaProducto
 */
@WebServlet(name = "busquedaProducto", urlPatterns = { "/busquedaProducto" })
public class busquedaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busquedaProducto() {
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
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		DbHandlerCategorias dbCategorias = new DbHandlerCategorias();
		String buscar = request.getParameter("descripcion");
		LinkedList<Producto> productos =  dbProductos.selectProductoByDescripcion(buscar);
		LinkedList<Categoria> categorias = dbCategorias.selectCategoria();
//		for(Producto p: productos) {
//			System.out.println(p.getDescripcion());
//		}
		request.setAttribute("productos", productos);
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher("busquedaProducto.jsp").forward(request, response);
	}

}
