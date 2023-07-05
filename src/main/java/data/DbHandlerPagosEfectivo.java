package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHandlerPagosEfectivo extends DbHandler{

	public DbHandlerPagosEfectivo() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void addCashPayment (Integer idPedido) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into pagoefectivo (idPedido) values (?)");
			stmt.setInt(1, idPedido);
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
