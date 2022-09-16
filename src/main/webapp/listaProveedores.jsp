<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Proveedor"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="listaProductos.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<%
LinkedList<Proveedor> proveedores = (LinkedList<Proveedor>) request.getAttribute("proveedores");

Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");
int id = 0;
String nombre = "";
String cuil = "";
String nroTelefono = "", tipoTelefono = "";
if (proveedor != null) {
	id = proveedor.getId();
	nombre = proveedor.getNombre();
	cuil = proveedor.getCuil();
	nroTelefono = proveedor.getNroTelefono();
	tipoTelefono = proveedor.getTipoTelefono();
}
%>
</head>
<body>
<div class="Parent">

		<div class="child1">

	<form id="form" action="listaProveedores" method="post" class="form">
		<table class="paleBlueRows">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Cuil</th>
					<th>NroTelefono</th>
					<th>TipoTelefono</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Proveedor p : proveedores) {
				%>
				<tr>
					<td><%=p.getNombre()%></td>
					<td><%=p.getCuil()%></td>
					<td><%=p.getNroTelefono()%></td>
					<td><%=p.getTipoTelefono()%></td>
					<td><button type="submit" name="order"
							value="mod-<%=p.getId()%>" class="astext">Modificar</button></td>
					<td><button type="submit" name="order"
							value="del-<%=p.getId()%>" class="astext">Eliminar</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</form>
	
	</div>
		<div class="child2">
	<form id="form" action="listaProveedores" method="post">
		<input type="hidden" name="id" value="<%=id%>">

		<table class="tabla">
			<tbody>
				<tr>
					<td colspan=2>Nombre:</td>
					<td colspan=4><input type="text" name="nombre" value="<%=nombre%>"></td>
				</tr>
				<tr>
					<td colspan=2>Cuil:</td>
					<td colspan=4><input type="text" name="cuil" value="<%=cuil%>"></td>
				</tr>
				<tr>
					<td colspan=2>NroTelefono:</td>
					<td colspan=4><input type="text" name="nro" value="<%=nroTelefono%>"></td>
				</tr>
				<tr>
					<td colspan=2>TipoTelefono:</td>
					<td colspan=4><input type="text" name="tipo" value="<%=tipoTelefono%>"></td>

				</tr>
				<tr>
				<td colspan=2><button type="submit" name="order" value="add">Agregar</button></td>
				<td colspan=2><button type="submit" name="order" value="modify">Modificar</button></td>
				<td colspan=2><button type="submit" name="order" value="volver">Volver</button></td>
		</tr>
			</tbody>
		</table>

		
		<br>
	</form>
</div>

	</div>

</body>
</html>