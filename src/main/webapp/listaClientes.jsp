<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Cliente"%>
<%@page import="Entities.Localidad"%>
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
        if(l.getCodPostal() == cliente.getLocalidad().getCodPostal()) {
            Collections.swap(localidades, 0, i);
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
<h5> Estos son los clientes que tiene en su supermercado</h5>

<form id="form" action="listaClientes" method="post">
		<table class="table" style="margin: auto; background-color: white">
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
					<td><%=c.getProvincia().getNombre()%></td>
					<td><%=c.getFechaNac()%></td>
					<td><%=c.getEmail()%></td>
					<td><button type="submit" name="order" value="mod-<%=c.getNroDocumento()%>" class="end-purchase nomar" >Modificar</button></td>
					<td><button type="submit" name="order" value="del-<%=c.getNroDocumento()%>" class="end-purchase nomar">Eliminar</button></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</form>

		<form id="form" action="listaClientes" method="post">
		
		
		<table class="table" style="margin: auto; background-color: white">
			<tbody>
			
					<tr>
					<td>DNI</td>
					<td><input name="dni" class="indni" value="<%=dni%>" ></td>
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
					<td>Telefono</td>
					<td><input type="text" name="telefono" value="<%=telefono%>"></td>
				</tr>
				<tr>
					<td>Dirección</td>
					<td><input type="text" name="direccion" value="<%=direccion%>"></td>
				</tr>
				<tr>
					<td>Localidad</td>
					<td><select name="loc">
							<%for (Localidad l: localidades){ %>
							<option value="<%=l.getCodPostal()%>"><%=l.getNombre()%></option>
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
				<tr>
					<td>Contraseña:</td>
					<td><input type="password" name="pw" value="" required></td>
				</tr>
				<tr>
				<td colspan="2">
				<button type="submit" name="order" value="add" class="end-purchase nomar" style="display: inline;">Agregar</button>
				<button type="submit" name="order" value="modify" class="end-purchase nomar" style="display: inline;">Modificar</button>
				</tr>
			</tbody>
		</table>
<!-- 		<button type="submit" name="order" value="add">Agregar</button> -->
<!-- 		<button type="submit" name="order" value="modify">Modificar</button> -->
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