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
import data.DbHandlerClientes;
import data.DbHandlerCategorias;
import data.DbHandlerProductos;
import data.DbHandlerProveedores;

/**
 * Servlet implementation class listaProveedores
 */
@WebServlet("/listaProveedores")
public class listaProveedores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaProveedores() {
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
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		DbHandlerCategorias dbCategorias = new DbHandlerCategorias();
		DbHandlerProveedores dbProveedores = new DbHandlerProveedores();
		String order = request.getParameter("order");
		Proveedor proveedor= null;
		if(order.equalsIgnoreCase("desc")) {
			
		}else if(order.equalsIgnoreCase("add")) {
			String nombre = request.getParameter("nombre");
			String cuil = request.getParameter("cuil");
			String NroTelefono = request.getParameter("nro");
			String tipotelefono = request.getParameter("tipo");
			dbProveedores.nuevoProv(nombre, cuil, NroTelefono, tipotelefono);
		}else if(order.split("-")[0].equalsIgnoreCase("del")) {
		int id = Integer.parseInt(order.split("-")[1]);
		dbProveedores.deleteProv(id);
		}else if(order.split("-")[0].equalsIgnoreCase("mod")){
			int id = Integer.parseInt(order.split("-")[1]);
			proveedor = dbProveedores.selectProveedor(id);
			
		}
		else if(order.equalsIgnoreCase("modify")){
			int id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String cuil = request.getParameter("cuil");
			String NroTelefono = request.getParameter("nro");
			String tipotelefono = request.getParameter("tipo");
			dbProveedores.modProv(id, nombre, cuil, NroTelefono, tipotelefono);
			
		}else if(order.equalsIgnoreCase("volver")) {
			LinkedList<Producto> productos =  dbProductos.selectProducto("desc");
			LinkedList<Categoria> categorias = dbCategorias.selectCategoria();
			LinkedList<Proveedor> proveedores = dbProveedores.selectProveedor();
			
			request.setAttribute("productos", productos);
			request.setAttribute("categorias", categorias);
			request.setAttribute("proveedores", proveedores);

			request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
		}
		
		request.setAttribute("proveedor", proveedor);
		

		LinkedList<Proveedor> proveedores = dbProveedores.selectProveedor();
		request.setAttribute("proveedores", proveedores);
		request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
	}

}
