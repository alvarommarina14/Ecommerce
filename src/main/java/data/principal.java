package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entities.Localidad;
import Entities.Cliente;
import Entities.Producto;
import Entities.Proveedor;

public class principal {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbHandlerProductos db = new DbHandlerProductos();
		System.out.println("se creo el db");
		LinkedList<Producto> prods = db.selectProductoByDescripcion("o");
		System.out.println(prods);
		for(Producto p: prods) {
			System.out.println("id "+p.getId()+" descripcion: "+p.getDescripcion());
		}
		}
	}


