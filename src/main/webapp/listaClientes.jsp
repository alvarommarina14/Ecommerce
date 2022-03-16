<%@page import="java.util.LinkedList"%>
<%@page import="Entities.*"%>
<%@ page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="listaProductos.css" rel="stylesheet" type="text/css">
<title>Lista Clientes</title>
<% 
LinkedList<Cliente> clientes = (LinkedList<Cliente>) request.getAttribute("listCli");
LinkedList<Localidad> localidades = (LinkedList<Localidad>) request.getAttribute("localidades");
Cliente cliente = (Cliente) request.getAttribute("cliente");
String dni = "", nombre = "", telefono = "", direccion = "", email = "", tipodni = "", fecha = "";
if(cliente != null){
	dni = cliente.getNroDocumento();
	tipodni = cliente.getTipDocumento();
	nombre = cliente.getNombreApellido();
	telefono = cliente.getNroTelefono();
	email = cliente.getEmail();
	direccion = cliente.getDireccion();
	fecha = cliente.getFechaNac();
	int i = 0;
    for(Localidad l : localidades) {
        if(l.getId() == cliente.getLocalidad().getId()) {
            Collections.swap(localidades, 0, i);
            break;
        }
        i++;
    }
    }
%>
</head>
<body>
<form id="form" action="listaClientes" method="post" class="form">
		<table class="paleBlueRows">
			<thead>
				<tr>
					<th>Nombre y Apellido</th>
					<th>Tipo Documento</th>
					<th>nroDocumento</th>
					<th>Telefono</th>
					<th>Direccion</th>
					<th>Localidad</th>
					<th>Provincia</th>
					<th>Fecha Nacimiento</th>
					<th>email</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Cliente c : clientes) {
				%>
				<tr>
					<td><%=c.getNombreApellido()%></td>
					<td><%=c.getTipDocumento()%></td>
					<td><%=c.getNroDocumento()%></td>
					<td><%=c.getNroTelefono()%></td>
					<td><%=c.getDireccion()%></td>
					<td><%=c.getLocalidad().getNombre()%></td>
					<td><%=c.getLocalidad().getProvincia().getNombre()%></td>
					<td><%=c.getFechaNac()%></td>
					<td><%=c.getEmail()%></td>
					<td><button type="submit" name="order" value="mod-<%=c.getNroDocumento()%>" class="astext" >Modificar</button></td>
					<td><button type="submit" name="order" value="del-<%=c.getNroDocumento()%>" class="astext">Eliminar</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</form>
</form>
		<form id="form" action="listaClientes" method="post">
		
		
		<table>
			<tbody>
			
					<tr>
					<td>DNI</td>
					<td><input name="dni" class="indni" value="<%=dni%>" readonly=true></td>
				</tr>
				<tr>
					<td>Nombre y Apellido</td>
					<td><input type="text" name="nombre" value="<%=nombre%>"></td>
				</tr>

				<tr>
					<td>TipoDocumento</td>
					<td><input type="text" name="tipodni" value="<%=tipodni%>"></td>
				</tr>
				<tr>
					<td>Dirección</td>
					<td><input type="text" name="direccion" value="<%=direccion%>"></td>
				</tr>
				<tr>
					<td>Localidad</td>
					<td><select name="loc">
							<%for (Localidad l: localidades){ %>
							<option value="<%=l.getId()%>"><%=l.getNombre()%></option>
							<%}%>
					</select></td>
				</tr>

				<tr>
					<td>FechaNac:</td>
					<td><input type="date" name="fechanac" value="<%=fecha%>"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" value="<%=email%>"></td>
				</tr>
			</tbody>
		</table>
		<button type="submit" name="order" value="add">Agregar</button>
		<button type="submit" name="order" value="modify">Modificar</button>
	</form>
</body>
</html>