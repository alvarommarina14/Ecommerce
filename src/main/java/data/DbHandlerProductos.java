package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Categoria;
import Entities.Producto;
import Entities.Proveedor;

public class DbHandlerProductos {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String password = "root";
	private String db = "tpsuper";
	private String options = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
	// private String options="";
	private Connection conn = null;

	public DbHandlerProductos() {
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

	public LinkedList<Producto> selectProducto(String order) { // Devuelve todos los productos

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Producto> productos = new LinkedList<Producto>();
			stmt = conn.createStatement();
			String query = "select pr.valor, pr.idProducto, p.descripcion as 'prod-desc', stock, p.idCategoria as 'idCategoria', cat.descripcion as 'cat-desc',\r\n"
					+ " prov.idProveedor as 'idProveedor', Nombre, cuil, tipoTelefono, nroTelefono from producto p\r\n"
					+ "inner join proveedor prov on prov.idProveedor = p.idProveedor\r\n"
					+ "inner join categoria cat on cat.idCategoria = p.idCategoria\r\n"
					+ "inner join (select max(fechaDesde) as fec, idProducto from precio group by precio.idProducto) maxprec2 on p.idProducto = maxprec2.idProducto \r\n"
					+ "inner join precio  pr on pr.idProducto = p.idProducto and\r\n" + "pr.fechaDesde = maxprec2.fec";

			rs = stmt.executeQuery(query);
			while (rs != null && rs.next()) {
				Producto producto = new Producto();
				Categoria categoria = new Categoria();
				Proveedor proveedor = new Proveedor();

				categoria.setId(rs.getInt("idCategoria"));
				categoria.setDescripcion(rs.getString("cat-desc"));

				proveedor.setId(rs.getInt("idProveedor"));
				proveedor.setTipoTelefono(rs.getString("tipoTelefono"));
				proveedor.setNroTelefono(rs.getString("nroTelefono"));
				proveedor.setNombre(rs.getString("Nombre"));
				proveedor.setCuil(rs.getString("cuil"));

				producto.setId(rs.getInt("idProducto"));
				producto.setDescripcion(rs.getString("prod-desc"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("valor"));

				producto.setCategoria(categoria);
				producto.setProveedor(proveedor);

				productos.add(producto);
			}
			return productos;
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

	public Producto selectProducto(Producto prod) { // Devuelve un producto

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;

		try {
			conn = this.getConnection();
			LinkedList<Producto> productos = new LinkedList<Producto>();
			String query = "select precio.valor, max(fechaDesde), precio.idProducto, producto.descripcion as 'prod-desc', stock, producto.idCategoria as 'idCategoria', categoria.descripcion as 'cat-desc', proveedor.idProveedor as 'idProveedor', Nombre, cuil, tipoTelefono, nroTelefono\r\n"
					+ "from precio inner join producto on producto.idProducto = precio.idProducto\r\n"
					+ "inner join categoria on producto.idCategoria = categoria.idCategoria\r\n"
					+ "inner join proveedor on producto.idProveedor = proveedor.idProveedor\r\n"
					+ "where producto.idProducto = ? and precio.fechaDesde = (select max(fechaDesde) from precio p where p.idProducto = ?) group by precio.idProducto\r\n"
					+ "";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, prod.getId());
			stmt.setInt(2, prod.getId());
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				Categoria categoria = new Categoria();
				Proveedor proveedor = new Proveedor();

				categoria.setId(rs.getInt("idCategoria"));
				categoria.setDescripcion(rs.getString("cat-desc"));

				proveedor.setId(rs.getInt("idProveedor"));
				proveedor.setTipoTelefono(rs.getString("tipoTelefono"));
				proveedor.setNroTelefono(rs.getString("nroTelefono"));
				proveedor.setNombre(rs.getString("nombre"));
				proveedor.setCuil(rs.getString("cuil"));

				prod.setId(rs.getInt("idProducto"));
				prod.setDescripcion(rs.getString("prod-desc"));
				prod.setStock(rs.getInt("stock"));
				prod.setPrecio(rs.getDouble("precio.valor"));

				prod.setCategoria(categoria);
				prod.setProveedor(proveedor);

				productos.add(prod);
			}
			return prod;

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

	public void addProducto(Producto prod) {

		PreparedStatement stmt = null;
		ResultSet keyRS = null;
		Connection conn = null;
		int idprod;

		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"insert into producto(descripcion, stock, idCategoria, idProveedor) " + "values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, prod.getDescripcion());
			stmt.setInt(2, prod.getStock());
			stmt.setInt(3, prod.getCategoria().getId());
			stmt.setInt(4, prod.getProveedor().getId());

			stmt.executeUpdate();
			keyRS = stmt.getGeneratedKeys();

			if (keyRS != null && keyRS.next()) {
				idprod = keyRS.getInt(1);
				this.addPrecioNuevoProducto(prod.getPrecio(), idprod);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyRS != null)
					keyRS.close();
				if (stmt != null)
					stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void addPrecioNuevoProducto(double precio, int idprod) {

		PreparedStatement stmt = null;
		ResultSet keyRS = null;
		Connection conn = null;

		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into precio(valor, idProducto)\r\n" + "\r\n"
					+ "values(?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(2, idprod);
			stmt.setDouble(1, precio);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyRS != null)
					keyRS.close();
				if (stmt != null)
					stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addPrecio(double precio, int idprod) {

		PreparedStatement stmt = null;
		ResultSet keyRS = null;
		Connection conn = null;

		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into precio(valor, idProducto)\r\n" + "\r\n"
					+ "select IF(pr.valor*(30/100 + 1) < ? ,pr.valor*(30/100 + 1), ?), pr.idProducto from producto p\r\n"
					+ "inner join proveedor prov on prov.idProveedor = p.idProveedor\r\n"
					+ "inner join categoria cat on cat.idCategoria = p.idCategoria\r\n"
					+ "inner join (select max(fechaDesde) as fec, idProducto from precio where YEAR(fechaDesde) = (YEAR(current_date) - 1) and idProducto = ? group by precio.idProducto) maxprec2 on p.idProducto = maxprec2.idProducto \r\n"
					+ "inner join precio pr on pr.idProducto = p.idProducto and\r\n"
					+ "pr.fechaDesde = maxprec2.fec;\r\n" + "", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(3, idprod);
			stmt.setDouble(2, precio);
			stmt.setDouble(1, precio);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyRS != null)
					keyRS.close();
				if (stmt != null)
					stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Producto prod) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete from producto where idProducto = ?");
			stmt.setInt(1, prod.getId());
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

	public void modifyProduct(Producto prod) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE producto SET descripcion = ?, stock = ?, idProveedor = ?, idCategoria = ? WHERE (idProducto = ?);");
			stmt.setString(1, prod.getDescripcion());
			stmt.setInt(2, prod.getStock());
			stmt.setInt(3, prod.getProveedor().getId());
			stmt.setInt(4, prod.getCategoria().getId());
			stmt.setInt(5, prod.getId());
			stmt.executeUpdate();
			this.addPrecio(prod.getPrecio(), prod.getId());
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

	public void inflation(Double val) {
		LinkedList<Producto> prods = this.selectProducto("a");
		for (Producto p : prods) {
			this.addPrecio(p.getPrecio() * (val / 100 + 1), p.getId());
		}
	}
}
