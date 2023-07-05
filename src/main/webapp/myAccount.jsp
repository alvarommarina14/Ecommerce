<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="Entities.Cliente"%>
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
    Cliente cliente = (Cliente) session.getAttribute("cliente");
    String descripcion = "";
    %>
</head>
<body style="background-color: white;">
  <header style="background-color: #505050;">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="width: 50%;">
  <div class="container-fluid">
  		<form action="home" method="post">
	  		<a class="nav-item" href="./index.html" style="background-color: inherit;border: 0;height: 100%;color: black;">Home</a>
  		</form>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNavDropdown">
	    	
      	<ul class="navbar-nav">
<!--       	<li class="nav-item"> -->
<!--       	<form> -->
<!--       	<a class="nav-item" href="./index.html" style="background-color: inherit;border: 0;height: 100%;color: black;">Home</a> -->
<!--       	</form> -->
<!--       	</li> -->
      	<li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Categorias
          </a>
          <ul class="dropdown-menu">
            <li>
            <form action="busquedaProducto" method="post">
	        	<button type="submit" class="dropdown-item">Bebidas</button>
	        	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value="" size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="1" size="30">
            </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Comidas</button>
            	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value="" size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="2" size="30">
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Cosmeticos</button>
            	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value="" size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="3" size="30">
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Tecnologia</button>
	            <input type="hidden" name="idpedid" value="0" size="30">
		        <input type="hidden" name="dni" value="" size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="4" size="30">
			</form>
            </li>
          </ul>
        </li>
        <li class="nav-item">
         <form action="listaOfertas" method="post" style="height: 100%;">
            <button class="nav-item" type="submit" style="background-color: inherit;border: 0;height: 100%;">Ofertas</button>
	            <input type="hidden" name="idpedid" value="0" size="30">
		        <input type="hidden" name="dni" value="12345678" size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
		</form>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">Sucursales</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Ayuda</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Acerca de</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
	  
<nav class="navbar navbar-expand-lg bg-body-tertiary" style="flex-direction: row-reverse;width: 49%;display: inline-flex;">
 	<div class="container-fluid" style="text-align: right;width: auto;margin: 0;">
    <a class="navbar-brand" href="./login">Login</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
        	<form action="finalizarCompra" method="post" style="height: 100%;">
          		<button type="submit" class="nav-item" style="background-color: inherit;border: 0;height: 100%;">Carrito</button>
        	</form>
        </li>
        <li class="nav-item">
        <form action="myAccount" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" style="background-color: inherit;border: 0;height: 100%;">Mi cuenta</button>
        </form>
        </li>
        <li class="nav-item">
        <form action="logout" method="post" style="height: 100%;">
          <button type="submit" class="nav-item" style="background-color: inherit;border: 0;height: 100%;">Salir</button>
        </form>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown link
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>
<main>

  <div class="album py-5 bg-light">
    <div class="container">

      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
	<h5 style="width: 100%;margin-top: 5vh;"> Mis datos </h5>	
		<table class="table" style="width: 30vw;margin: auto;">
  
  <tbody>
      <tr class="tr-border-black">
      <td class="td-row-title">Numero de documento</td>
      <td><%=cliente.getNroDocumento() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Nombre y Apellido</td>
      <td><%=cliente.getNombreApellido() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Email</td>
      <td><%=cliente.getEmail() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Fecha Nacimiento</td>
      <td><%=cliente.getFechaNac() %></td>
    </tr>
     <tr class="tr-border-black">
      <td class="td-row-title">Telefono</td>
      <td><%=cliente.getNroTelefono() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Dirección</td>
      <td><%=cliente.getDireccion() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Codigo Postal</td>
      <td><%=cliente.getLocalidad().getCodPostal() %></td>
    </tr>
    <tr class="tr-border-black">
      <td class="td-row-title">Contraseña </td>
      <td>*******</td>
    </tr>
  </tbody>
</table>
       

     
      </div>
    </div>
    <form action="editProfile" method="post">
     	<button type="submit" name="fin" class="end-purchase">Editar perfil</button>
    </form>
  </div>
</main>

<footer class="text-muted footerAM">
  <div class="container">
    <p class="mb-1">Supermercado, SA.</p>
    <p class="mb-0">Desarrollado por Alvaro. 2022.</p>
  </div>
</footer>


    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
    
</body>
</html>


