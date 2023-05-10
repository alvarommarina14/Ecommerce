package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Entities.Categoria;
import Entities.LineaCompra;
import Entities.Producto;
import Entities.Proveedor;

public class DbHandlerLineaPedido {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "admin";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;
	
	public DbHandlerLineaPedido() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

private Connection getConnection() {
	try {
		if (conn == null || conn.isClosed())
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + options, user,
					password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return conn;
}

private void releaseConnection() {
	try {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void addLineaPedido(Integer idPedido, Integer idProducto, Integer cantidad, Double precio, String descripcion) {

	PreparedStatement stmt = null;
	ResultSet keyRS = null;
	Connection conn = null;
//	int idprod;

	try {
		conn = this.getConnection();
		stmt = conn.prepareStatement(
				"insert into lineacompra(idPedido, idProducto, cantidad, precio, descripcion) values(?, ?, ?, ? ,?)");
		stmt.setInt(1, idPedido);
		stmt.setInt(2, idProducto);
		stmt.setInt(3, cantidad);
		stmt.setDouble(4, precio);
		stmt.setString(5, descripcion);
		stmt.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (keyRS != null)
				keyRS.close();
			if (stmt != null)
				stmt.close();
			this.releaseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public void deleteLineaProducto(Integer idPedido, Integer idProducto) {
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = this.getConnection();
		stmt = conn.prepareStatement("delete from lineacompra where idPedido = ? and idProducto = ?");
		stmt.setInt(1, idPedido);
		stmt.setInt(2, idProducto);
		stmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			this.releaseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public LinkedList<LineaCompra> selectProductos(Integer idPedido) {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn;
	LinkedList<LineaCompra> lineasCompra = new LinkedList<LineaCompra>();
	try {
		conn = this.getConnection();
		String query = "SELECT lc.idPedido, lc.idProducto, lc.cantidad, pr.descripcion, p.valor FROM lineacompra lc \r\n"
				+ "INNER JOIN producto pr on pr.idProducto = lc.idProducto \r\n"
				+ "INNER JOIN precio p on p.idProducto = pr.idProducto\r\n"
				+ "INNER JOIN (SELECT max(fechaDesde) as fecha, idProducto from precio group by idProducto) maxFechas on maxFechas.idProducto = p.idProducto and maxFechas.fecha = p.fechaDesde\r\n"
				+ "where lc.idPedido = ? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, idPedido);
		rs = stmt.executeQuery();

		while (rs != null && rs.next()) {
			LineaCompra lc = new LineaCompra();
			
			lc.setNroPedido(rs.getInt(1));
			lc.setIdProducto(rs.getInt(2));
			lc.setCantidad(rs.getInt(3));
			lc.setDescripcion(rs.getString(4));
			lc.setPrecio(rs.getDouble(5));
			lineasCompra.add(lc);
		}
		return lineasCompra;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			this.releaseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}}

	public LinkedList<Producto> selectProductosList(Integer idPedido) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;
		LinkedList<Producto> productos = new LinkedList<Producto>();
		try {
			conn = this.getConnection();
			String query = "SELECT lc.idPedido, lc.idProducto, lc.cantidad, pr.idProducto, pr.descripcion, pr.stock, cat.idCategoria, cat.descripcion, p.valor FROM lineacompra lc\r\n"
					+ "INNER JOIN producto pr on pr.idProducto = lc.idProducto\r\n"
					+ "INNER JOIN precio p on p.idProducto = pr.idProducto\r\n"
					+ "INNER JOIN (SELECT max(fechaDesde) as fecha, idProducto from precio group by idProducto) maxFechas on maxFechas.idProducto = p.idProducto and maxFechas.fecha = p.fechaDesde\r\n"
					+ "INNER JOIN categoria cat on cat.idCategoria = pr.idCategoria\r\n"
					+ "where lc.idPedido = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, idPedido);
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				Producto p = new Producto();
				Categoria cat = new Categoria();
				
				cat.setId(rs.getInt("cat.idCategoria"));
				cat.setDescripcion(rs.getString("cat.descripcion"));
				
				p.setId(rs.getInt("idProducto"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setStock(rs.getInt("stock"));
				p.setPrecio(rs.getDouble("p.valor"));
				p.setCategoria(cat);
				
				productos.add(p);
			}
			return productos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
}
