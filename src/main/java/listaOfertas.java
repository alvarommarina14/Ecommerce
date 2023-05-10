

import java.io.IOException;
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
import data.DbHandlerCategorias;
import data.DbHandlerLineaPedido;
import data.DbHandlerPedidos;
import data.DbHandlerProductos;

/**
 * Servlet implementation class listaOfertas
 */
@WebServlet("/listaOfertas")
public class listaOfertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listaOfertas() {
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
		DbHandlerProductos dbProductos = new DbHandlerProductos();
		DbHandlerPedidos dbPedido = new DbHandlerPedidos();
		DbHandlerLineaPedido dbLineaPedido = new DbHandlerLineaPedido();
		
		String buscar = "";
		String cartOrder = request.getParameter("order");
		String idCat = request.getParameter("catId");
		
		LinkedList<Producto> productos = dbProductos.selectProductsOffers();
		LinkedList<Producto> prodsdeLista = new LinkedList<Producto>();

		String pedidoCreado = (String) session.getAttribute("pedidoCreado");
		Integer idPedido = (Integer) session.getAttribute("idPedido");
		LinkedList<LineaCompra> listaLC = (LinkedList<LineaCompra>) session.getAttribute("listaLC");
		if(pedidoCreado == null) {
			idPedido = dbPedido.getLastId()+1;
			listaLC = new LinkedList<LineaCompra>();
			session.setAttribute("idPedido", idPedido);
			session.setAttribute("pedidoCreado", pedidoCreado);
			session.setAttribute("listaLC", listaLC);
		}
		if(cartOrder != null && cartOrder.equalsIgnoreCase("add-to-cart")){
					Integer prod = Integer.parseInt(request.getParameter("p-id"));
					Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
					String descripcion = request.getParameter("p-descripcion");
					Double precio = Double.parseDouble(request.getParameter("precio"));
					System.out.println("id Pedido = "+idPedido+" idProducto = "+prod+" cantidad = "+cantidad);
					LineaCompra lc = new LineaCompra();
					lc.setIdProducto(prod);
					lc.setNroPedido(idPedido);
					lc.setCantidad(cantidad);
					listaLC.add(lc);
				}
		prodsdeLista = dbLineaPedido.selectProductosList(idPedido);
		if(prodsdeLista.size() > 0) {
		System.out.println("primer elemento "+prodsdeLista.getFirst());
		if(productos != null) {
		for(Producto pr: prodsdeLista){ 
			for(Producto p: productos) {
				if(pr.getId() == p.getId()) {
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
		request.getRequestDispatcher("listaOfertas.jsp").forward(request, response);
	}

}

