<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="listaProductos.css" rel="stylesheet" type="text/css">

<title>Lista Productos</title>
<%
LinkedList<Producto> productos = (LinkedList<Producto>) request.getAttribute("productos");
LinkedList<Categoria> categorias = (LinkedList<Categoria>) request.getAttribute("categorias");
LinkedList<Proveedor> proveedores = (LinkedList<Proveedor>) request.getAttribute("proveedores");
Producto producto = (Producto) request.getAttribute("producto");
int id = 0;
String descripcion= "";
Double precio = 0.0;
int stock = 0;
if(producto != null){
	id = producto.getId();
	descripcion = producto.getDescripcion();
	precio = producto.getPrecio();
	stock = producto.getStock();
	int i = 0;
    for(Categoria c: categorias) {
        if(c.getId() == producto.getCategoria().getId()) {
            Collections.swap(categorias, 0, i);
            break;
        }
        i++;
    }
    i= 0;
    for(Proveedor p: proveedores) {
        if(p.getId() == producto.getProveedor().getId()) {
            Collections.swap(proveedores, 0, i);
            break;
        }
        i++;
    }
    }
	%>
</head>
<body>

<div class="Parent">

<div class="child1">


<form id="form" action="listaProductos" method="post" class="form">
		<table class="paleBlueRows">
			<thead>
				<tr>
					<th>Descripcion</th>
					<th>Precio</th>
					<th>Stock</th>
					<th>Categoria</th>
					<th>Proveedor</th>
					<th>Cuil</th>
					<th>TipoTelefono</th>
					<th>NroTelefono</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Producto p : productos) {
				%>
				<tr>
					<td><%=p.getDescripcion()%></td>
					<td><%=p.getPrecio()%></td>
					<td><%=p.getStock()%></td>
					<td><%=p.getCategoria().getDescripcion()%></td>
					<td><%=p.getProveedor().getNombre()%></td>
					<td><%=p.getProveedor().getCuil()%></td>
					<td><%=p.getProveedor().getTipoTelefono()%></td>
					<td><%=p.getProveedor().getNroTelefono()%></td>
					<td><button type="submit" name="order" value="mod-<%=p.getId()%>" class="astext" >Modificar</button></td>
					<td><button type="submit" name="order" value="del-<%=p.getId()%>" class="astext">Eliminar</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</form>
		</div>
		<div class="child2">
		<form id="form" action="listaProductos" method="post">
		<input type="hidden" name="id" value="<%=id%>">
		
		<table>
			<tbody>
					<tr>
					<td>Descripcion:</td>
					<td><input type="text" name="desc" value="<%=descripcion%>"></td>
				</tr>
				<tr>
					<td>Stock inicial:</td>
					<td><input type="text" name="stock" value ="<%=stock%>"></td>
				</tr>

				<tr>
					<td>Precio:</td>
					<td><input type="text" name="precio" value="<%=precio%>"></td>
				</tr>
				<tr>
					<td>Categoria:</td>
					<td><select name="cat">
							<%for (Categoria c: categorias){ %>
							<option value="<%=c.getId()%>"><%=c.getDescripcion()%></option>
							<%}%>
					</select></td>
				</tr>

				<tr>
					<td>Proveedor:</td>
					<td><select name="prov">
							<%for (Proveedor c: proveedores){ %>
							<option value="<%=c.getId()%>"><%=c.getNombre()%></option>
							<%}%>
					</select></td>
				</tr>
			</tbody>
		</table>
		
		<button type="submit" name="order" value="add">Agregar</button>
		<button type="submit" name="order" value="modify">Modificar</button>
		<br>
		</form>
		

	<form id="form" action="listaProductos" method="post" class="form">
		<input type="text" name="valor" placeholder="Ingrese el porcentaje de aumento (30% = 0.3)">
		<button type="submit" name="order" value="updatePrices">Actualizar precios por inflacion</button>
	</form>
	<form id="form" action="listaProductos" method="post" class="form">
	<button type="submit" name="order" value="addCat">Categorías</button>
		<button type="submit" name="order" value="addProv">Proovedores</button>
		<br>
	</form>
	</div>
	
</div>
</body>
</html>
