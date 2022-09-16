package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Proveedor;

public class DbHandlerProveedores {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "matias";
	private String password = "1234";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandlerProveedores() {
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

	public void nuevoProv(String nombre, String cuil, String nroTelefono, String tipoTelefono) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"insert into Proveedor (nombre, cuil, nroTelefono, tipoTelefono) values (?,?,?,?)");/*
																										 * please ver si
																										 * esta bien
																										 * este query
																										 */
			stmt.setString(1, nombre);
			stmt.setString(2, cuil);
			stmt.setString(3, nroTelefono);
			stmt.setString(4, tipoTelefono);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
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
	}

	public void modProv(int id, String nombre, String cuil, String nroTelefono, String tipoTelefono) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"update proveedor set nombre = ?, cuil = ?, nroTelefono = ?, tipoTelefono = ? where idProveedor = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, cuil);
			stmt.setString(3, nroTelefono);
			stmt.setString(4, tipoTelefono);
			stmt.setInt(5, id);
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

	public void deleteProv(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from proveedor where idProveedor = ?");
			stmt.setInt(1, id);
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

	public Proveedor selectProveedor(int id) { // Devuelve todos los proveedores, filtrado por el
		// nombre
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Proveedor> proveedores = new LinkedList<Proveedor>();

			stmt = conn.prepareStatement("select * from proveedor where idProveedor = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				Proveedor p = new Proveedor();

				p.setId(rs.getInt("idProveedor"));
				p.setCuil(rs.getString("cuil"));
				p.setEmail(rs.getString("email"));
				p.setTipoTelefono(rs.getString("tipoTelefono"));
				p.setNroTelefono(rs.getString("nroTelefono"));
				p.setNombre(rs.getString("nombre"));

				return p;
			}

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
		return null;

	}

	public LinkedList<Proveedor> selectProveedor() { // Devuelve todos los proveedores
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Proveedor> proveedores = new LinkedList<Proveedor>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from proveedor");

			while (rs != null && rs.next()) {
				Proveedor p = new Proveedor();

				p.setId(rs.getInt("idProveedor"));
				p.setNombre(rs.getString("nombre"));
				p.setCuil(rs.getString("cuil"));
				p.setEmail(rs.getString("email"));
				p.setTipoTelefono(rs.getString("tipoTelefono"));
				p.setNroTelefono(rs.getString("nroTelefono"));
				p.setNombre(rs.getString("nombre"));

				proveedores.add(p);
			}
			return proveedores;

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
