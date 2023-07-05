package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHandlerPagosTarjeta extends DbHandler{
	
	public DbHandlerPagosTarjeta() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addCardPayment (Integer idPedido, String nroTarjeta, Integer CVV, Integer mes, Integer anio, String tipoTarjeta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into pagotarjeta (idPedido, nroTarjeta, CVV, mesVencimiento, anioVencimiento, tipoTarjeta) values (?,?,?,?,?,?)");
			stmt.setInt(1, idPedido);
			stmt.setString(2, nroTarjeta);
			stmt.setInt(3, CVV);
			stmt.setInt(4, mes);
			stmt.setInt(5, anio);
			stmt.setString(6, tipoTarjeta);
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
