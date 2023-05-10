package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Categoria;
import Entities.Localidad;
import Entities.Producto;
import Entities.Proveedor;

public class DbHandlerLocalidades {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "admin";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandlerLocalidades() {
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
	
	public LinkedList<Localidad> selectLocalidad(Integer idProvincia) throws IOException { // Devuelve localidad por provincia

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Localidad> localidades = new LinkedList<Localidad>();
			stmt = conn.prepareStatement("SELECT codPostal, nombre, idProvincia FROM localidad l where l.idProvincia = ?");
			stmt.setInt(1, idProvincia);
			
			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Localidad localidad = new Localidad();
				
				localidad.setCodPostal(rs.getInt(1));
				localidad.setNombre(rs.getString(2));
				localidad.setIdProvincia(rs.getInt(3));
				
				localidades.add(localidad);
			}
			return localidades;
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
	
	public LinkedList<Localidad> selectLocalidad() throws IOException { // Devuelve todas las localidades

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Localidad> localidades = new LinkedList<Localidad>();
			stmt = conn.createStatement();
			String query = "SELECT codPostal, nombre, idProvincia FROM localidad";
			
			rs = stmt.executeQuery(query);
			while (rs != null && rs.next()) {
				Localidad localidad = new Localidad();
				
				localidad.setCodPostal(rs.getInt(1));
				localidad.setNombre(rs.getString(2));
				localidad.setIdProvincia(rs.getInt(3));
				
				localidades.add(localidad);
			}
			return localidades;
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