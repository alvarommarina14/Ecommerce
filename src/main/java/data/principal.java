package data;

import java.io.IOException;



import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.sql.Connection;

import java.util.Base64;


import javax.servlet.http.HttpServletResponse;

import org.mindrot.bcrypt.BCrypt;

import Entities.Localidad;
import Entities.Cliente;
import Entities.Producto;
import Entities.Proveedor;
import Entities.Provincia;
import Entities.Sucursal;
import data.DbHandlerProductos;



public class principal {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DbHandlerClientes db = new DbHandlerClientes();
//		Cliente cli = db.loginCliente("alvarito@gmail.com", "pass");
//		System.out.println(cli.getLocalidad().getNombre());
//		System.out.println(cli.getProvincia().getNombre());
//		DbHandlerProvincias dbProvincias = new DbHandlerProvincias();
//		LinkedList<Provincia> provincias = dbProvincias.selectProvincia();
//		DbHandlerSucursales dbSucursales = new DbHandlerSucursales();
//		LinkedList<Sucursal> sucursales = dbSucursales.selectSucursal();
//		for(Sucursal s: sucursales) {
//			System.out.println(s.getDireccion());
//		}
		
//		String salt = BCrypt.gensalt();
//		String password = BCrypt.hashpw("pw", salt);
//		System.out.println(salt);
//		System.out.println(password);
//	
//		
//		Boolean right = BCrypt.checkpw("pass", password);
//		
//		System.out.println(right);
//		String a = "1995-05-23";
//		String[] fecha = a.split("-");
//		System.out.println(fecha[0]);
//		db.modificarCli(12345678, "Alvaro Marina", "Junin 2332", 2000, "alvarito@gmail.com", "1995-06-24");
		db.updatePassword(12345678, "hola");
//		System.out.println(db.getSalt(12345678));
//		System.out.println(c.getNombreApellido());
}
}



