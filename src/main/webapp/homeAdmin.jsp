<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Cliente"%>
<%@page import="Entities.EnvioDomicilio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
    </style>
        
<% 
    ArrayList<EnvioDomicilio> lista = (ArrayList<EnvioDomicilio>) request.getAttribute("lista");
	LinkedList<EnvioDomicilio> envios = new LinkedList<EnvioDomicilio>();
	Cliente cliente = (Cliente) session.getAttribute("cliente");
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

<main>

<section class="py-5 text-center container" id="ct-table" style="background-image: none;height: auto;">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Bienvenido de nuevo,</h1>
        <p class="lead text-muted"><%=cliente.getNombreApellido() %></p>
        <p class="lead text-muted">Estas son algunas entregas pendientes</p>
        <p>
        <table class="table" style="margin: auto; background-color: white">
  <thead>
    <tr>
      <th scope="col">idPedido</th>
      <th scope="col">DNI Cliente</th>
      <th scope="col">Nombre y Apellido</th>
      <th scope="col">Fecha de la compra</th>
      <th scope="col">Fecha estimada de entrega</th>
      <th scope="col">Domicilio</th>
      <th scope="col">Costo</th>
    </tr>
  </thead>
  <tbody>
  <%for(int i = 0; i<5; i++){ %>
    <tr>
      <th scope="row"><%=lista.get(i).getPedido().getId() %></th>
      <td><%= lista.get(i).getCliente().getNroDocumento()%></td>
      <td><%= lista.get(i).getCliente().getNombreApellido()%></td>
      <td><%= lista.get(i).getPedido().getFecha()%></td>
      <td><%= lista.get(i).getFechaEntregaEstimada() %></td>
      <td><%= lista.get(i).getCliente().getDireccion()%></td>
      <td><%= lista.get(i).getCosto()%></td>
    </tr> 
    <% } %>
  </tbody>
</table>
      </div>
    </div>
  </section>


</main>

<footer class="text-muted footerAM">
  <div class="container">
    <p class="mb-1">Supermercado, SA.</p>
    <p class="mb-0">Desarrollado por Alvaro. 2023.</p>
  </div>
</footer>
    
</body>
</html>


