import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Categoria;
import Entities.Producto;
import Entities.Proveedor;
import data.DbHandler;
import data.DbHandlerCategorias;
import data.DbHandlerProductos;
import data.DbHandlerProveedores;

/**
 * Servlet implementation class servlet
 */
@WebServlet(name = "listaProductos", urlPatterns = { "/listaProductos" })
public class listaProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaProductos() {
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
		DbHandlerProveedores dbProveedor = new DbHandlerProveedores();
		
		String order = request.getParameter("order");
		Producto producto = null;
		if(order.equalsIgnoreCase("desc")) {
			
		}else if(order.equalsIgnoreCase("add")) {
			Producto prod = new Producto();
			prod.setDescripcion(request.getParameter("desc"));
			prod.setStock(Integer.parseInt(request.getParameter("stock")));
			
			prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
			Categoria cat = new Categoria();
			cat.setId(Integer.parseInt(request.getParameter("cat")));
			prod.setCategoria(cat);
			Proveedor prov = new Proveedor();
			prov.setId(Integer.parseInt(request.getParameter("prov")));
			prod.setProveedor(prov);
			
			dbProductos.addProducto(prod);
			
		}else if(order.split("-")[0].equalsIgnoreCase("del")) {
			Producto prod = new Producto();
			prod.setId(Integer.parseInt(order.split("-")[1]));
			dbProductos.delete(prod);
			
		}else if(order.split("-")[0].equalsIgnoreCase("mod")){
			Producto prod = new Producto();
			prod.setId(Integer.parseInt(order.split("-")[1]));
			producto = dbProductos.selectProducto(prod);		
		}else if(order.equalsIgnoreCase("modify")){
			
			Producto prod = new Producto();
			prod.setId(Integer.parseInt(request.getParameter("id")));
			prod.setDescripcion(request.getParameter("desc"));
			prod.setStock(Integer.parseInt(request.getParameter("stock")));
			
			prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
			Categoria cat = new Categoria();
			cat.setId(Integer.parseInt(request.getParameter("cat")));
			prod.setCategoria(cat);
			Proveedor prov = new Proveedor();
			prov.setId(Integer.parseInt(request.getParameter("prov")));
			prod.setProveedor(prov);
			
			dbProductos.modifyProduct(prod);
		}else if(order.equalsIgnoreCase("updatePrices")){
			Double valor = Double.parseDouble(request.getParameter("valor"));
			dbProductos.inflation(valor);
		}else if(order.equalsIgnoreCase("addCat")) {

			LinkedList<Categoria> categorias = dbCategorias.selectCategoria();
			request.setAttribute("categorias", categorias);
			request.getRequestDispatcher("listaCategorias.jsp").forward(request, response);
		}else if(order.equalsIgnoreCase("addProv")) {

			LinkedList<Proveedor> proveedores = dbProveedor.selectProveedor();
			request.setAttribute("proveedores", proveedores);
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
		
		LinkedList<Producto> productos =  dbProductos.selectProducto("desc");
		LinkedList<Categoria> categorias = dbCategorias.selectCategoria();
		LinkedList<Proveedor> proveedores = dbProveedor.selectProveedor();
		
		request.setAttribute("productos", productos);
		request.setAttribute("categorias", categorias);
		request.setAttribute("proveedores", proveedores);
		request.setAttribute("producto", producto);
	
		request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
	
	}

}
