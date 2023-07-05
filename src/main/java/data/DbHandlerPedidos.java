package data;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entities.Categoria;
import Entities.Producto;

public class DbHandlerPedidos {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "admin";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;
	
	public DbHandlerPedidos() {
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
public Integer addPedido(Integer idPedido, Integer nroDoc) {

	PreparedStatement stmt = null;
	ResultSet keyRS = null;
	Connection conn = null;
	long millis=System.currentTimeMillis();  
	java.sql.Date date = new java.sql.Date(millis);   

	try {
		conn = this.getConnection();
		stmt = conn.prepareStatement(
				"insert into pedido(idPedido, fechaCompra, estado, nroDoc) values(?,?,?,?)");
		stmt.setInt(1, idPedido);
		stmt.setDate(2, date);
		stmt.setString(3, "pendiente");
		stmt.setInt(4, nroDoc);
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
	return idPedido;
}

public int getLastId() {
	Statement stmt = null;
	ResultSet rs = null;
	Connection conn;
	int lastid = 0;
	try {
		conn = this.getConnection();
		stmt = conn.createStatement();
		String query = "SELECT MAX(idPedido) as id from pedido";
		rs = stmt.executeQuery(query);

		if (rs != null && rs.next()) {
			lastid = rs.getInt("id");
		}
	} catch (SQLException e) {
		e.printStackTrace();
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
	return lastid;
}

public void updateTotalCost(Integer idPedido) {
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = this.getConnection();
		stmt = conn.prepareStatement(
				"UPDATE pedido p SET p.costoTotal = (select SUM(cantidad*precio) from lineacompra lc where lc.idPedido=?) where p.idPedido = ?");
		stmt.setInt(1, idPedido);
		stmt.setInt(2, idPedido);
		stmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			this.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

}

