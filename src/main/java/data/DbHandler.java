package data;

import java.sql.*;
import java.util.LinkedList;

import Entities.*;

public class DbHandler {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "root";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandler() {
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

	

	

	
	

	public LinkedList<Localidad> selectLocalidades() { // Devuelve todos las categorias
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Localidad> localidades = new LinkedList<Localidad>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from localidad");

			while (rs != null && rs.next()) {
				Localidad l = new Localidad();

				l.setId(rs.getInt("codpostal"));
				l.setNombre(rs.getString("nombre"));

				localidades.add(l);
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

	
	

	public void nuevoCli(Cliente c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpsuper?user=root&password=admin");
			stmt = conn.prepareStatement("insert into cliente values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS); // chequear
																													// que
																													// sean
																													// 5
																													// ''?''
			stmt.setString(1, c.getDireccion());
			stmt.setString(2, c.getNroDocumento());
			stmt.setString(3, c.getFechaNac().toString());
			stmt.setString(4, c.getNombreApellido());
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
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void eliminaCli(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from cliente where nroDocumento = ?");
			stmt.setString(1, id);
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

	public void modificarCli(String dni, String tipodni, String nombre, String direccion, String codpostal, String email, String fecha) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE cliente set tipoDocumento = ?, nombreApellido = ?, direccion = ?, codpostal = ?, email = ?, fechaNacimiento = ? where (nroDocumento = ?);");
			stmt.setString(1, tipodni);
			stmt.setString(2, nombre);
			stmt.setString(3, direccion);
			stmt.setString(4, codpostal);
			stmt.setString(5, email);
			stmt.setString(6, fecha);
			stmt.setString(7, dni);
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

	public Cliente buscaCli(String dni) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			String query = "select c.nroDocumento, c.tipoDocumento, c.nombreApellido, c.email, c.fechaNacimiento, c.telefono, c.direccion, c.codPostal, p.nombre as 'provNombre', p.idProvincia, l.nombre as 'locNombre'"
					+ " from Cliente c " + " inner join localidad l on l.codPostal = c.codPostal "
					+ " inner join provincia p on p.idProvincia = l.idProvincia where nroDocumento = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, dni);

			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Cliente c = new Cliente();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
	
				p.setId(rs.getInt("idProvincia"));
				p.setNombre(rs.getString("provNombre"));
	
				l.setId(rs.getInt("codPostal"));
				l.setProvincia(p);
				l.setNombre(rs.getString("locNombre"));
	
				c.setNroDocumento(rs.getString("nroDocumento"));
				c.setTipDocumento(rs.getString("tipoDocumento"));
				c.setNombreApellido(rs.getString("nombreApellido"));
				c.setEmail(rs.getString("email"));
				c.setFechaNac(rs.getString("fechaNacimiento"));
				c.setNroTelefono(rs.getString("telefono"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(l);
				
				return c;
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

	public LinkedList<Cliente> selectCliente() { // Devuelve todos los clientes		
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			LinkedList<Cliente> clientes = new LinkedList<Cliente>();
			/*select c.nroDocumento, c.tipoDocumento, c.nombreApellido, c.email, c.fechaNacimiento, c.telefono, c.direccion, c.codPostal, p.nombre as 'provNombre', p.idProvincia, l.nombre as 'locNombre'"
					+ " from Cliente c " + " inner join localidad l on l.codPostal = c.codPostal "
					+ " inner join provincia p on p.idProvincia = l.idProvincia*/
			String query = "select c.nroDocumento, c.tipoDocumento, c.nombreApellido, c.email, c.fechaNacimiento, c.telefono, c.direccion, c.codPostal, p.nombre as 'provNombre', p.idProvincia, l.nombre as 'locNombre'\r\n"
					+ "from Cliente c inner join localidad l on l.codPostal = c.codPostal \r\n"
					+ "inner join provincia p on p.idProvincia = l.idProvincia";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs != null && rs.next()) {
				Cliente c = new Cliente();
				Provincia p = new Provincia();
				Localidad l = new Localidad();

				p.setId(rs.getInt("idProvincia"));
				p.setNombre(rs.getString("provNombre"));

				l.setId(rs.getInt("codPostal"));
				l.setProvincia(p);
				l.setNombre(rs.getString("locNombre"));

				c.setNroDocumento(rs.getString("nroDocumento"));
				c.setTipDocumento(rs.getString("tipoDocumento"));
				c.setNombreApellido(rs.getString("nombreApellido"));
				c.setEmail(rs.getString("email"));
				c.setFechaNac(rs.getString("fechaNacimiento"));
				c.setNroTelefono(rs.getString("telefono"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(l);
				clientes.add(c);
			}
			return clientes;

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

