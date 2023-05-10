<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="Entities.Cliente"%>
<%@page import="Entities.Localidad"%>
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
    String[] fecha = cliente.getFechaNac().split("-");
    String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    Integer cont = 1;
    LinkedList<Localidad> localidades = (LinkedList<Localidad>) request.getAttribute("localidades");
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
          <a class="nav-link active" aria-current="page" href="#">Carrito</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Mi cuenta</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Salir</a>
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
	<h5 style="width: 100%;"> Editar datos </h5>	
		<div class="payment-container" style="margin: auto;">
  <div class="payment-containers">
  <form action="editProfile" method="post">
  <label class="payment-label">Numero de documento</label>
  <input class="ep-input" name="dni" value=<%=cliente.getNroDocumento() %> required>
  <label class="payment-label">Nombre y Apellido</label>
  <input class="ep-input" name="nombre" value=<%=cliente.getNombreApellido() %>  required>
  <label class="payment-label">Email</label>
  <input class="ep-input" name="email" type="email" value=<%=cliente.getEmail() %>  required>
  <label class="payment-label">Fecha Nacimiento</label>
    <input class="ep-input" name="dia" style="width: 9vw;" value="<%=fecha[2] %>"  required>
 <select name="meses" class="payment-input" style="width: 189px; height: 30px;">
	<%for(String month: months){ %>
	 <option value="<%=cont %>" <% if(cont == Integer.parseInt(fecha[1])){ %> selected <% } %>> <%= month %></option>
	 <%
	cont++;
	}
	 %>
</select>

  <input class="ep-input" name="anio" style="width: 9vw;" value=<%=fecha[0] %>  required>
   <label class="payment-label">Telefono</label>
  <input class="ep-input" name="telefono" value=<%=cliente.getNroTelefono() %>  required>

  <label class="payment-label">Dirección</label>
    <input class="ep-input" name="direccion" value="<%=cliente.getDireccion() %>"  required>
    <label class="payment-label">Localidad</label>
       <select name="localidad" class="payment-input" style="width: 189px; height: 30px;">
	<%for(Localidad l: localidades){ %>
	 <option value=<%=l.getCodPostal() %> <% if(cliente.getLocalidad().getCodPostal() == l.getCodPostal()){ %> selected <% } %>> <%= l.getNombre() %></option>
	 <%
	}
	 %>
</select>
	<button type="button" class="end-purchase" style="border: 0px;background-color: white;margin: 0;color: #0095ff;" id="showButton">Cambiar contraseña</button>
  <label class="payment-label pw hidden" for="passwordLabel">Contraseña</label>
  <input class="ep-input pw hidden" name="pw" type="password" value="" id="passwordLabel" name="password">
  <label class="payment-label pw hidden" for="newPasswordLabel">Nueva contraseña</label>
  <input class="ep-input pw hidden" name="newpw" type="password" value="" id="newPasswordLabel" name="newPassword">
  <label class="payment-label pw hidden" for="confirmPasswordLabel">Confirme nueva contraseña</label>
  <input class="ep-input pw hidden" name="confpw" type="password" value="" id="confirmPasswordLabel" name="confirmPassword">
<script>
  var showButton = document.getElementById("showButton");
  var labels = document.querySelectorAll(".payment-label.pw");
  var inputs = document.querySelectorAll(".ep-input.pw");

  showButton.addEventListener("click", function() {
    // Toggle the display of labels
    labels.forEach(function(label) {
      if (label.style.display === "block") {
        label.style.display = "none";
      } else {
        label.style.display = "block";
      }
    });

    // Toggle the display and required attribute of inputs
    inputs.forEach(function(input) {
      if (input.style.display === "block") {
        input.style.display = "none";
        input.removeAttribute("required");
      } else {
        input.style.display = "block";
        input.setAttribute("required", "true");
      }
    });
  });
</script>
  <input type="hidden" name="edit" value="edit">
  
 <button type="submit" name="fin" class="end-purchase">Guardar perfil</button>
</form>
  </div>
  
  
  </div>
  </div>
  </div>

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


