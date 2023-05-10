

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Cliente;
import Entities.LineaCompra;
import data.DbHandlerEnvioDomicilio;
import data.DbHandlerLineaPedido;
import data.DbHandlerRetiroSucursal;
import data.DbHandlerSucursales;
import data.DbHandlerPagosTarjeta;
import data.DbHandlerPedidos;
import data.DbHandlerProductos;
import data.DbHandlerPagosEfectivo;

/**
 * Servlet implementation class compraConfirmada
 */
@WebServlet("/compraConfirmada")
public class compraConfirmada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public compraConfirmada() {
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
		DbHandlerPedidos dbHandlerPedidos = new DbHandlerPedidos();
		DbHandlerProductos dbHandlerProductos = new DbHandlerProductos();
		DbHandlerLineaPedido dbHandlerLP = new DbHandlerLineaPedido();
		
		Integer idPedido = (Integer) session.getAttribute("idPedido");
		Cliente cli = (Cliente) session.getAttribute("cliente");
		Integer nroDoc = Integer.parseInt(cli.getNroDocumento());
		String payment = (String) session.getAttribute("payment");
		String withdraw = (String) session.getAttribute("withdraw");
		LinkedList<LineaCompra> listaLC = (LinkedList<LineaCompra>) session.getAttribute("listaLC");
		
		dbHandlerPedidos.addPedido(idPedido, nroDoc); // creates the order
		for(LineaCompra lc: listaLC) { // creates the order lines
			dbHandlerLP.addLineaPedido(idPedido, lc.getIdProducto(), lc.getCantidad(), lc.getPrecio(), lc.getDescripcion());
		}
		
		if(withdraw == "delivery") { //creates the delivery 
			DbHandlerEnvioDomicilio dbHandlerED = new DbHandlerEnvioDomicilio();
			String direccion = request.getParameter("address");
			dbHandlerED.addHomeDelivery(idPedido, direccion);
		}
		if(withdraw == "branch") { // creates the branch withdrawal
			DbHandlerRetiroSucursal dbHandlerRS = new DbHandlerRetiroSucursal();
			DbHandlerSucursales dbHandlerSucursal = new DbHandlerSucursales();
			String branchAddress = request.getParameter("branches");
			Integer idSucursal = dbHandlerSucursal.selectSucursalByAddress(branchAddress).getId();
			dbHandlerRS.addBranchWithdrawal(idPedido, idSucursal);	
		}
		if(payment == "cash") { // creates the cash payment
			DbHandlerPagosEfectivo dbPagoEfectivo = new DbHandlerPagosEfectivo();
			dbPagoEfectivo.addCashPayment(idPedido);
		}
		if(payment == "card") { // creates the card payment
			DbHandlerPagosTarjeta dbPagoTarjeta = new DbHandlerPagosTarjeta();
			String nroTarjeta = request.getParameter("credit-number");
			Integer CVV= Integer.parseInt(request.getParameter("CVV"));
			Integer mes = Integer.parseInt(request.getParameter("month"));
			Integer anio = Integer.parseInt(request.getParameter("year"));
			String tipoTarjeta = request.getParameter("brand");
			dbPagoTarjeta.addCardPayment(idPedido, nroTarjeta, CVV, mes, anio, tipoTarjeta);
		}
		
		dbHandlerPedidos.updateTotalCost(idPedido); // sets the total cost of the order
		
		for(LineaCompra lc: listaLC) { // updates the stock of every item
			dbHandlerProductos.updateStock(lc.getIdProducto(), lc.getCantidad());
		}
		
		session.removeAttribute("listaLC");
		session.removeAttribute("pedidoCreado");
		session.removeAttribute("idPedido");
		
		request.getRequestDispatcher("compraConfirmada.jsp").forward(request, response);
	}

}
