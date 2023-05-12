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
		db.updatePassword(12345678, "admin");
		DbHandlerRetiroSucursal db1 = new DbHandlerRetiroSucursal();
		db1.selectRetirosSucursalPendientes();
		System.out.println(db1.selectRetirosSucursalPendientes());
}
}



