

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Producto;
import data.DbHandlerProductos;

/**
 * Servlet implementation class buscaFotos
 */
@WebServlet(name = "buscaFotos", urlPatterns = { "/buscaFotos" })
public class buscaFotos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buscaFotos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		Integer id = Integer.parseInt(request.getParameter("idProd"));
		Producto p = dbProductos.selectProducto(id);
		response.setContentType("image/jpeg");
		response.setContentLength(p.getImageBytes().length);
		response.getOutputStream().write(p.getImageBytes());
		response.getOutputStream().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		Integer idProd = Integer.parseInt(request.getParameter("idProd"));
		System.out.println(idProd);
//		Producto p = dbProductos.selectProducto(3);
//		response.setContentType("image/jpeg");
//		response.setContentLength(p.getImageBytes().length);
//		response.getOutputStream().write(p.getImageBytes());
//		response.getOutputStream().flush();
//		request.getRequestDispatcher("buscaFotos.jsp").forward(request, response);
//	}

}}
