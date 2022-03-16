<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Categoria"%>
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

LinkedList<Categoria> categorias = (LinkedList<Categoria>) request.getAttribute("categorias");

Categoria categoria = (Categoria) request.getAttribute("categoria");
int id = 0;
String descripcion ="";
if(categoria!=null){
	id = categoria.getId();
	descripcion = categoria.getDescripcion();
}

%>
</head>
<body>

<form id="form" action="listaCategorias" method="post" class="form">
		<table class="paleBlueRows">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Categoria c : categorias) {
				%>
				<tr>
					<td><%=c.getDescripcion()%></td>
					<td><button type="submit" name="order" value="mod-<%=c.getId()%>" class="astext" >Modificar</button></td>
					<td><button type="submit" name="order" value="del-<%=c.getId()%>" class="astext">Eliminar</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</form>
		<form id="form" action="listaCategorias" method="post">
		<input type="hidden" name="id" value="<%=id%>">
		
		<table>
			<tbody>
					<tr>
					<td>Descripcion:</td>
					<td><input type="text" name="desc" value="<%=descripcion%>"></td>
				</tr>
				
				</tr>
			</tbody>
		</table>
		
		<button type="submit" name="order" value="add">Agregar</button>
		<button type="submit" name="order" value="modify">Modificar</button>
		<button type="submit" name="order" value="volver">Volver</button>
		<br>
		</form>
		

</body>
</html>