package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Categoria;

public class DbHandlerCategorias extends DbHandler{

	public DbHandlerCategorias() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Categoria> selectCategoria() { // Devuelve todos las categorias

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Categoria> categorias = new LinkedList<Categoria>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from categoria");

			while (rs != null && rs.next()) {
				Categoria c = new Categoria();

				c.setId(rs.getInt("idCategoria"));
				c.setDescripcion(rs.getString("descripcion"));

				categorias.add(c);
			}
			return categorias;

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

	public Categoria selectCategoria(int id) { // Devuelve todos las categorias, filtrado por
		// descripcion
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Categoria> categorias = new LinkedList<Categoria>();

			stmt = conn.prepareStatement("select * from categoria where idCategoria = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				Categoria c = new Categoria();

				c.setId(rs.getInt("idCategoria"));
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
			stmt = conn.prepareStatement("delete from categoria where idCategoria = ?");
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
			stmt = conn.prepareStatement("update categoria set descripcion = ? where idCategoria = ?");
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
	public void nuevaCat( String desc) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into Categoria (Descripcion) values (?)");
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
