package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Cliente;
import Entities.Pedido;
import Entities.RetiroSucursal;
import Entities.Sucursal;

public class DbHandlerRetiroSucursal extends DbHandler {

	public DbHandlerRetiroSucursal() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public LinkedList<RetiroSucursal> selectRetiroSucursal() { // Devuelve todos los retiros por sucursal

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<RetiroSucursal> RetiroSucursals = new LinkedList<RetiroSucursal>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from RetiroSucursal");

			while (rs != null && rs.next()) {
				RetiroSucursal c = new RetiroSucursal();

				c.setId(rs.getInt("idRetiroSucursal"));
				c.setDescripcion(rs.getString("descripcion"));

				RetiroSucursals.add(c);
			}
			return RetiroSucursals;

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

	public RetiroSucursal selectRetiroSucursal(int id) { // Devuelve un retiro por sucursal buscado por id
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<RetiroSucursal> RetiroSucursals = new LinkedList<RetiroSucursal>();

			stmt = conn.prepareStatement("select * from RetiroSucursal where idRetiroSucursal = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				RetiroSucursal c = new RetiroSucursal();

				c.setId(rs.getInt("idRetiroSucursal"));
				c.setDescripcion(rs.getString("descripcion"));

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
	
	public LinkedList<RetiroSucursal> selectRetirosSucursalPendientes() { // Devuelve todos los retiros sucursales pendientes

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<RetiroSucursal> RetiroSucursal = new LinkedList<RetiroSucursal>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT rs.idPedido, rs.fechaHoraRetiroEstimada, rs.idSucursal, s.direccion, p.fechaCompra, p.nroDoc, p.costoTotal, cli.nombreApellido, cli.direccion\r\n"
					+ "FROM retirosucursal rs\r\n"
					+ "inner join pedido p on p.idPedido = rs.idPedido \r\n"
					+ "inner join cliente cli on cli.nroDocumento = p.nroDoc\r\n"
					+ "inner join sucursal s on s.id = rs.idSucursal\r\n"
					+ "where fechaHoraRetiro is null;");

			while (rs != null && rs.next()) {
				RetiroSucursal c = new RetiroSucursal();
				Cliente cli = new Cliente();
				Pedido ped = new Pedido();
				Sucursal s = new Sucursal();
				
				s.setDireccion(rs.getString("direccion"));
				
				
				ped.setId(rs.getInt("idpedido"));
				ped.setFecha(rs.getString("fechaCompra"));
				
				cli.setNroDocumento(rs.getString("nroDoc"));
				cli.setDireccion(rs.getString("direccion"));
				cli.setNombreApellido(rs.getString("nombreApellido"));
				
				c.setId(rs.getInt("idSucursal"));
				c.setFechaRetiroEstimada(rs.getString("fechaHoraRetiroEstimada"));
				c.setCosto(rs.getDouble("costoTotal"));
				c.setCliente(cli);
				c.setPedido(ped);
				c.setSucursal(s);

				RetiroSucursal.add(c);
			}
			return RetiroSucursal;

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

	public void deleteRetiroSucursal(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from RetiroSucursal where idRetiroSucursal = ?");
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

	public void updateRetiroSucursal(Integer idPedido, Integer idSucursal, String fecha) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("update RetiroSucursal set idSucursal = ?, fechaHoraRetiro = ? where idPedido = ?");
			stmt.setInt(1, idSucursal);
			stmt.setString(2, fecha);
			stmt.setInt(3, idPedido);
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
	public void addBranchWithdrawal(Integer idPedido, Integer idSucursal) { 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		long millis=System.currentTimeMillis()+604800000;  
		java.sql.Date fechaEstimada = new java.sql.Date(millis);  
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into retirosucursal (idPedido, idSucursal, fechaHoraRetiroEstimada) values (?,?,?)");
			stmt.setInt(1, idPedido);
			stmt.setInt(2, idSucursal);
			stmt.setDate(3, fechaEstimada);
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
