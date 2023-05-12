package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Sucursal;

public class DbHandlerSucursales {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "admin";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandlerSucursales() {
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

	public LinkedList<Sucursal> selectSucursal() { // Devuelve todas las Sucursales

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Sucursal> sucursales = new LinkedList<Sucursal>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Sucursal");

			while (rs != null && rs.next()) {
				Sucursal s = new Sucursal();

				s.setId(rs.getInt("id"));
				s.setDireccion(rs.getString("direccion"));
				s.setCodPostal(rs.getInt("codPostal"));

				sucursales.add(s);
			}
			return sucursales;

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

	public Sucursal selectSucursalByAddress(String address) { // Devuelve sucursal por direccion

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			String query = "select * from Sucursal s where s.direccion = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, address);
			
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				Sucursal s = new Sucursal();

				s.setId(rs.getInt("id"));
				s.setDireccion(rs.getString("direccion"));
				s.setCodPostal(rs.getInt("codPostal"));
				
				return s;
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

	public void deleteSucursal(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from Sucursal where idSucursal = ?");
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

	public void modSucursal(int id, String desc) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("update Sucursal set descripcion = ? where idSucursal = ?");
			stmt.setString(1, desc);
			stmt.setInt(2, id);
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
	public void nuevaSucursal( String desc) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into Sucursal (Descripcion) values (?)");
			stmt.setString(1, desc);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
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
