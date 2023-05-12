<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="java.io.OutputStream" %>
<%@page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Supermercado</title>
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/">
<link href="./css/bootstrap.min.css" rel="stylesheet">

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
    </style>
<%
LinkedList<Producto> productos = (LinkedList<Producto>) request.getAttribute("productos");
LinkedList<Categoria> categorias = (LinkedList<Categoria>) request.getAttribute("categorias");
LinkedList<Proveedor> proveedores = (LinkedList<Proveedor>) request.getAttribute("proveedores");
Producto producto = (Producto) request.getAttribute("producto");
int id = 0;
String descripcion = "";
Double precio = 0.0;
int stock = 0;
if (producto != null) {
	id = producto.getId();
	descripcion = producto.getDescripcion();
	precio = producto.getPrecio();
	stock = producto.getStock();
	int i = 0;
	for (Categoria c : categorias) {
		if (c.getId() == producto.getCategoria().getId()) {
	Collections.swap(categorias, 0, i);
	break;
		}
		i++;
	}
	i = 0;
	for (Proveedor p : proveedores) {
		if (p.getId() == producto.getProveedor().getId()) {
	Collections.swap(proveedores, 0, i);
	break;
		}
		i++;
	}
}
%>
</head>
<body>
<header style="background-color: #505050;">
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="width: 50%;">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
    	
      <ul class="navbar-nav">
      <li class="nav-item dropdown">
              <li class="nav-item">
        <form action="homeAdmin" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Home</button>
        </form>
        </li>
        <li class="nav-item">
        
    <form action="listaProductos" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Productos</button>
    </form>
        </li>
        <li class="nav-item">
        <form action="listaClientes" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" style="background-color: inherit;border: 0;height: 100%;">Clientes</button>
        </form>
        </li>
        <li class="nav-item">
                 <form action="listaProveedores" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Proveedores</button>
        </form>
        </li>
        <li class="nav-item">
        <form action="listaCategorias" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Categorias</button>
        </form>
        </li>
        <li class="nav-item">
         	<form action="listaEnvios" method="post" style="height: 100%;">
          		<button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Envios</button>
        	</form>
        </li>
        <li class="nav-item">
         	<form action="listaRetiros" method="post" style="height: 100%;">
          		<button type="submit" class="nav-item" value="desc" name="order" style="background-color: inherit;border: 0;height: 100%;">Retiros</button>
        	</form>
        </li>
          <li class="nav-item">
          	<a class="nav-link" href="#">Sucursales</a>
          </li>
      </ul>
    </div>
  </div>
</nav>
	 
</header>


<div>
<section class="py-5 text-center container" style="max-width: 1920px;margin-top: 2vh;">
<h5> Estos son los productos que tiene en su supermercado</h5>

    <form id="form" action="listaProductos" method="post" class="form" style="display: inline-flex;">
				<table class="table" style="margin: auto; background-color: white">
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
							<td><button type="submit" name="order"
									value="mod-<%=p.getId()%>" class="end-purchase" style="margin:0px">Modificar</button></td>
							<td><button type="submit" name="order"
									value="del-<%=p.getId()%>" class="end-purchase" style="margin:0px" >Eliminar</button></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</form>
			
			

		<form id="form" action="listaProductos" method="post" style="display: inline-flex;">
								<input type="hidden" name="id" value="<%=id%>">
		
			<table class="table" style="margin: auto; background-color: white">
				<tbody>

						<tr>
							<td>Descripcion:</td>
							<td><input type="text" name="desc" value="<%=descripcion%>"></td>
						</tr>
						<tr>
							<td>Stock inicial:</td>
							<td><input type="text" name="stock" value="<%=stock%>"></td>
						</tr>

						<tr>
							<td>Precio:</td>
							<td><input type="text" name="precio" value="<%=precio%>"></td>
						</tr>
						<tr>
							<td>Categoria:</td>
							<td><select name="cat">
									<%
									for (Categoria c : categorias) {
									%>
									<option value="<%=c.getId()%>"><%=c.getDescripcion()%></option>
									<%
									}
									%>
							</select></td>
						</tr>

						<tr>
							<td>Proveedor:</td>
							<td><select name="prov">
									<%
									for (Proveedor c : proveedores) {
									%>
									<option value="<%=c.getId()%>"><%=c.getNombre()%></option>
									<%
									}
									%>
							</select></td>
						</tr>
						<tr>
							<td>
								<button type="submit" name="order" value="add" class="end-purchase" style="margin:0px">Agregar</button>
							</td>
							<td>
								<button type="submit" name="order" value="modify" class="end-purchase" style="margin:0px">Modificar</button>
								</td>
						</tr>


					<tr>
					<td>
						<input type="text" name="valor"
							placeholder="Ingrese el porcentaje">
							</td>
							<td>
						<button type="submit" name="order" value="updatePrices" class="end-purchase" style="margin:0px">Actualizar
							precios por inflacion</button>
							</td>

					</tr>
					<tr>
					<td>
						<button type="submit" name="order" value="addCat" class="end-purchase" style="margin:0px">Categorías</button>
						</td>
							<td>
						<button type="submit" name="order" value="addProv" class="end-purchase" style="margin:0px">Proovedores</button>
						</td>
					</tr>
				</tbody>
			</table>
			</form>

  </section>
  </div>
<footer class="text-muted footerAM">
  <div class="container">
    <p class="mb-1">Supermercado, SA.</p>
    <p class="mb-0">Desarrollado por Alvaro. 2023.</p>
  </div>
</footer>


</body>
</html>
