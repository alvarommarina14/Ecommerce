package data;

import java.sql.*;

import java.util.LinkedList;

import org.mindrot.bcrypt.BCrypt;

import Entities.*;

public class DbHandlerClientes extends DbHandler{

	public DbHandlerClientes() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addUser(Cliente c, String pw) { //creates new user in the database
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String salt = BCrypt.gensalt();
		String hash = BCrypt.hashpw(pw, salt);
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpsuper?user=root&password=admin");
			stmt = conn.prepareStatement("insert into cliente (nroDocumento, tipoDocumento, nombreApellido, email, fechaNacimiento, telefono, direccion, codPostal, password, salt) values(?,?,?,?,?,?,?,?,?,?)"
					, Statement.RETURN_GENERATED_KEYS);
			if(!this.verifyCreated(Integer.parseInt(c.getNroDocumento()), c.getEmail())) {
			stmt.setInt(1, Integer.parseInt(c.getNroDocumento()));
			stmt.setString(2, c.getTipDocumento());
			stmt.setString(3, c.getNombreApellido());
			stmt.setString(4, c.getEmail());
			stmt.setString(5, c.getFechaNac().toString());
			stmt.setString(6, c.getNroTelefono());
			stmt.setString(7, c.getDireccion());
			stmt.setInt(8, c.getLocalidad().getCodPostal());
			stmt.setString(9, hash);
			stmt.setString(10, salt);
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			}

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

	public void updateClientUser(Integer dni, String nombre, String direccion, Integer codpostal, String email, String fecha) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE cliente set nombreApellido = ?, direccion = ?, codpostal = ?, email = ?, fechaNacimiento = ? where (nroDocumento = ?);");
			stmt.setString(1, nombre);
			stmt.setString(2, direccion);
			stmt.setInt(3, codpostal);
			stmt.setString(4, email);
			stmt.setString(5, fecha);
			stmt.setInt(6, dni);
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
	
				l.setCodPostal(rs.getInt("codPostal"));
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

				l.setCodPostal(rs.getInt("codPostal"));
				l.setNombre(rs.getString("locNombre"));

				c.setNroDocumento(rs.getString("nroDocumento"));
				c.setTipDocumento(rs.getString("tipoDocumento"));
				c.setNombreApellido(rs.getString("nombreApellido"));
				c.setEmail(rs.getString("email"));
				c.setFechaNac(rs.getString("fechaNacimiento"));
				c.setNroTelefono(rs.getString("telefono"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(l);
				c.setProvincia(p);
				
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

	public Cliente loginCliente (String email, String password) { // returns a client if it exists
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;
		Boolean verify = this.checkPassword(email, password);
		try {
			conn = this.getConnection();
			String query = "SELECT c.nroDocumento, c.nombreApellido, c.email, c.fechaNacimiento, c.telefono, c.direccion, c.codPostal, c.user_type, l.nombre, p.idProvincia, p.nombre as 'provNombre' from cliente c \r\n"
					+ "INNER JOIN localidad l on l.codPostal = c.codPostal \r\n"
					+ "INNER JOIN provincia p on p.idProvincia = l.idProvincia\r\n"
					+ "where c.email= ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);

			rs = stmt.executeQuery();
			if(rs != null && rs.next() && verify) {
				Cliente c = new Cliente();
				Localidad l = new Localidad();
				Provincia p = new Provincia();
				
				l.setCodPostal(rs.getInt("codPostal"));
				l.setIdProvincia(rs.getInt("idProvincia"));
				l.setNombre(rs.getString("nombre"));
				
				p.setId(rs.getInt("idProvincia"));
				p.setNombre(rs.getString("provNombre"));
				
				
				c.setNroDocumento(rs.getString("nroDocumento"));
				c.setNombreApellido(rs.getString("nombreApellido"));
				c.setEmail(rs.getString("email"));
				c.setFechaNac(rs.getString("fechaNacimiento"));
				c.setNroTelefono(rs.getString("telefono"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(l);
				c.setProvincia(p);
				if(rs.getString("user_type").equals("USER")) {
					c.setUser_type(User_type.USER);
				} else {
					c.setUser_type(User_type.ADMIN);
				}
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
	
	public Cliente selectClientByIdPedido(Integer idPedido) { //returns a client by an order ID
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			String query = "SELECT c.nroDocumento, c.nombreApellido, c.email, c.telefono, c.direccion, c.codPostal, c.fechaNacimiento, l.nombre as 'locNombre', prov.nombre as 'provNombre' from pedido p\r\n"
					+ "inner join cliente c on c.nroDocumento = p.nroDoc\r\n"
					+ "inner join localidad l on l.codPostal = c.codPostal\r\n"
					+ "inner join provincia prov on prov.idProvincia = l.idProvincia\r\n"
					+ "where p.idPedido = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, idPedido);

			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Cliente c = new Cliente();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
	
				p.setNombre(rs.getString("provNombre"));
	
				
				l.setCodPostal(rs.getInt("codPostal"));
				l.setNombre(rs.getString("locNombre"));
	
				c.setNroDocumento(rs.getString("nroDocumento"));
				c.setNombreApellido(rs.getString("nombreApellido"));
				c.setEmail(rs.getString("email"));
				c.setFechaNac(rs.getString("fechaNacimiento"));
				c.setNroTelefono(rs.getString("telefono"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(l);
				c.setProvincia(p);
				
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
	
	public boolean checkPassword(Integer dni, String password) { //checks that the password is correct for a given dni
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn;

			try {
				conn = this.getConnection();
				String query = "SELECT password, salt FROM cliente c where c.nroDocumento = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, dni);

				rs = stmt.executeQuery();
				if (rs != null && rs.next()) {
					Boolean verified = BCrypt.checkpw(password, rs.getString("password"));
					return verified;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
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
			return false;	
	}
	
	public boolean checkPassword(String email, String password) { //checks that the password is correct for a given email
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			String query = "SELECT password, salt FROM cliente c where c.email = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);

			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				Boolean verified = BCrypt.checkpw(password, rs.getString("password"));
				return verified;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
		return false;	
}
	
	public void updatePassword(Integer dni, String password) { //updates password of a client
		Connection conn = null;
		PreparedStatement stmt = null;
		String salt = this.getSalt(dni);
		String hashed = BCrypt.hashpw(password, salt);
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE cliente set password = ? where (nroDocumento = ?);");
			stmt.setInt(2, dni);
			stmt.setString(1, hashed);
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

	public String getSalt(Integer dni) { // generates salt for hashing a password
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"SELECT salt from cliente where cliente.nroDocumento = ?");
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()) {
				String salt = rs.getString("salt");
				return salt;
			}
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
		return null;
	}
	
	public Boolean verifyCreated(Integer dni, String email) { //verifies that an account is created
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn;
	Boolean verified = false;
	try {
		conn = this.getConnection();
		String query = "select * from Cliente c where nroDocumento = ? or email = ?";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, dni);
		stmt.setString(2, email);

		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
				verified = true;
				return verified;
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
	return verified;
}
}

