package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.RetiroSucursal;

public class DbHandlerRetiroSucursal extends DbHandler {

	public DbHandlerRetiroSucursal() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public LinkedList<RetiroSucursal> selectRetiroSucursal() { // Devuelve todos las RetiroSucursals

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

	public RetiroSucursal selectRetiroSucursal(int id) { // Devuelve todos las RetiroSucursals, filtrado por
		// descripcion
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

	public void deleteCat(int id) {
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

	public void modCat(int id, String desc) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("update RetiroSucursal set descripcion = ? where idRetiroSucursal = ?");
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
	public void addBranchWithdrawal(Integer idPedido, Integer idSucursal) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into retirosucursal (idPedido, idSucursal) values (?,?)");/* please ver si esta bien este query*/
			stmt.setInt(1, idPedido);
			stmt.setInt(2, idSucursal);
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
