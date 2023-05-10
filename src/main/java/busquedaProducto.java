import java.io.IOException;




import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Categoria;
import Entities.LineaCompra;
import Entities.Producto;
import Entities.Provincia;
import data.DbHandlerCategorias;
import data.DbHandlerProductos;
import data.DbHandlerProvincias;
import data.DbHandlerPedidos;
import data.DbHandlerLineaPedido;
import data.DbHandlerLocalidades;
/**
 * 
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
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		DbHandlerPedidos dbPedido = new DbHandlerPedidos();
		DbHandlerLineaPedido dbLineaPedido = new DbHandlerLineaPedido();
		
		String buscar = request.getParameter("descripcion");
		String cartOrder = request.getParameter("order");
		String idCat = request.getParameter("catId");
		
		LinkedList<Producto> productos = new LinkedList<Producto>();
		LinkedList<Producto> prodsdeLista = new LinkedList<Producto>();

		
		if(buscar != null && buscar != "") {
			productos =  dbProductos.selectProductoByDescripcion(buscar);
		} else if(idCat != null) {
			productos = dbProductos.selectProductByCategory(Integer.parseInt(idCat));
			buscar = "";
		}
		String pedidoCreado = (String) session.getAttribute("pedidoCreado");
		Integer idPedido = (Integer) session.getAttribute("idPedido");
		LinkedList<LineaCompra> listaLC = (LinkedList<LineaCompra>) session.getAttribute("listaLC");
		System.out.println("pedido Creado "+ pedidoCreado);
		System.out.println("idPedido "+ idPedido);
		System.out.println("listaLC "+ listaLC);
		if(pedidoCreado == null) {
			pedidoCreado = "creado";
			idPedido = dbPedido.getLastId()+1;
			listaLC = new LinkedList<LineaCompra>();
			System.out.println("entre al if e inicialice las variables, ahora pedidoCreado = "+pedidoCreado+" idPedido = "+idPedido+" listaLC = "+listaLC);
			session.setAttribute("idPedido", idPedido);
			session.setAttribute("pedidoCreado", pedidoCreado);
			session.setAttribute("listaLC", listaLC);
		}
		if(cartOrder != null && cartOrder.equalsIgnoreCase("add-to-cart")){
					System.out.println("entre al add to cart");
					Integer prod = Integer.parseInt(request.getParameter("p-id"));
					Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
					String descripcion = request.getParameter("p-descripcion");
					Double precio = Double.parseDouble(request.getParameter("precio"));
					System.out.println("id Pedido = "+idPedido+" idProducto = "+prod+" cantidad = "+cantidad);
					LineaCompra lc = new LineaCompra();
					lc.setIdProducto(prod);
					lc.setNroPedido(idPedido);
					lc.setCantidad(cantidad);
					lc.setPrecio(precio);
					lc.setDescripcion(descripcion);
					listaLC.add(lc);
//					dbLineaPedido.addLineaPedido(idPedido, prod, cantidad, precio, descripcion);
				}
//		prodsdeLista = dbLineaPedido.selectProductosList(idPedido);
		
		if(listaLC != null) {
//		System.out.println("primer elemento "+listaLC.getFirst());
		if(productos != null && listaLC.size() > 0) {
		for(LineaCompra lc: listaLC){ 
			for(Producto p: productos) {
				if(lc.getIdProducto() == p.getId()) {
					productos.remove(p);
					break;
				}
			}
      	} 
		}}
		request.setAttribute("productos", productos);
		request.setAttribute("idCat", idCat);
		request.setAttribute("descripcion", buscar);
		request.setAttribute("idPedido", idPedido);
		request.getRequestDispatcher("busquedaProducto.jsp").forward(request, response);
	}
}

	
	


