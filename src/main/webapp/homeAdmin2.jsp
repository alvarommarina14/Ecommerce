<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    LinkedList<Producto> prods = (LinkedList<Producto>) request.getAttribute("prods");
%>
<script>
$(document).ready(function() {
	  // Show subnav when clicking on "Proveedores"
	  $('a[href="#"]').click(function(e) {
	    e.preventDefault();
	    if ($(this).text() === "Proveedores") {
	      $('#subnav').slideDown();
	    }
	  });

	  // Hide subnav when clicking outside of it
	  $(document).click(function(e) {
	    if ($(e.target).closest('#subnav').length === 0) {
	      $('#subnav').slideUp();
	    }
	  });
	});
</script>
</head>
<body>
<header style="background-color: #505050;">
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="width: 50%;">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Home</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Proveedores
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Proveedor 1</a></li>
              <li><a class="dropdown-item" href="#">Proveedor 2</a></li>
              <li><a class="dropdown-item" href="#">Proveedor 3</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Categorias</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Clientes</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <nav id="subnav" class="navbar navbar-expand-lg bg-light" style="width: 50%; display: none;">
    <div class="container-fluid">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="#">Subproveedor 1</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Subproveedor 2</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Subproveedor 3</a>
        </li>
      </ul>
    </div>
  </nav>
</header>
<script>
  const proveedoresLink = document.getElementById("proveedores-link");
  const proveedoresNavbar = document.getElementById("proveedores-navbar");

  proveedoresLink.addEventListener("click", () => {
    proveedoresNavbar.style.display = "block";
  });
</script>
<main>
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Supermarket</h1>
        <p class="lead text-muted">Productos de calidad a la mano</p>
        <p>
        <form action="busquedaProducto" method="post">
        	<input type="text" class="descripcion-busqueda" name="descripcion" size="30">
        	<button type="submit" class="btn btn-primary" name="product-search" style="border-color: #d5931a;display: block;background-color: #d5931a;margin: 20px auto auto auto;">Buscar</button>
        	<input type="hidden" name="idpedid" value="0" size="30">
			<input type="hidden" name="lineasCompra" value="0" size="30">
        </form>
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


