package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entities.Cliente;
import Entities.EnvioDomicilio;
import Entities.Pedido;

public class DbHandlerEnvioDomicilio {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "admin";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandlerEnvioDomicilio() {
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

	public LinkedList<EnvioDomicilio> selectEnvioDomicilio() { // Devuelve todos las EnvioDomicilio

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<EnvioDomicilio> EnvioDomicilio = new LinkedList<EnvioDomicilio>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from EnvioDomicilio");

			while (rs != null && rs.next()) {
				EnvioDomicilio c = new EnvioDomicilio();

				c.setId(rs.getInt("idEnvioDomicilio"));
				c.setDescripcion(rs.getString("descripcion"));

				EnvioDomicilio.add(c);
			}
			return EnvioDomicilio;

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

	public LinkedList<EnvioDomicilio> selectEnviosPendientes() { // Devuelve los envios a domicilio pendientes al dia de la fecha
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<EnvioDomicilio> envios = new LinkedList<EnvioDomicilio>();

			stmt = conn.prepareStatement("SELECT ed.idPedido, ed.fechaEstimadaEntrega, ed.fechaRealEntrega, "
					+ "p.fechaCompra, p.nroDoc, p.costoTotal, cli.nombreApellido, cli.direccion "
					+ "FROM enviodomicilio ed \r\n"
					+ "inner join pedido p on p.idPedido = ed.idPedido \r\n"
					+ "inner join cliente cli on cli.nroDocumento = p.nroDoc\r\n"
					+ "where fechaRealEntrega is null;");

			rs = stmt.executeQuery();

			while(rs != null && rs.next()) {
				EnvioDomicilio e = new EnvioDomicilio();
				Cliente cli = new Cliente();
				Pedido ped = new Pedido();
				
				ped.setId(rs.getInt("idpedido"));
				ped.setFecha(rs.getString("fechaCompra"));
				
				cli.setNroDocumento(rs.getString("nroDoc"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setNombreApellido(rs.getString("nombreApellido"));
				
				e.setCliente(cli);
				e.setPedido(ped);
				e.setCosto(rs.getDouble("costoTotal"));
				e.setFechaEntregaEstimada(rs.getString("fechaEstimadaEntrega"));
				e.setDireccionEnvio(rs.getString("direccion"));
				envios.add(e);
			}
			return envios;
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

	public void addHomeDelivery(Integer idPedido, String direccion) { //
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		long millis=System.currentTimeMillis()+604800000;  
		java.sql.Date fechaEstimada = new java.sql.Date(millis);  

		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("INSERT INTO enviodomicilio (idPedido, fechaEstimadaEntrega, domicilioEntrega) values (?,?,?)");
			stmt.setInt(1, idPedido);
			stmt.setDate(2, fechaEstimada);
			stmt.setString(3, direccion);
			stmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			try{if (rs != null)
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
