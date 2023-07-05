<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Cliente"%>
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
	Integer pedido = 0;
	Cliente cli = (Cliente) session.getAttribute("cliente");
	String dni = cli.getNroDocumento();
	String email = cli.getEmail();
	String password = (String) request.getAttribute("password");
	System.out.println("dni "+dni+" email "+email+" password "+password);
%>
</head>
<body>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
 <header style="background-color: #505050;">
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="width: 50%;">
  <div class="container-fluid">
  		<form action="home" method="post">
	  		<input type="hidden" name="dni" value=<%=dni %> size="30">
	  		<input type="hidden" name="email" value=<%=email %> size="30">
	  		<input type="hidden" name="password" value=<%=password %> size="30">
	  		<button class="nav-item" type="submit" style="background-color: inherit;border: 0;height: 100%;">Home</button>
  		</form>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNavDropdown">
	    	
      	<ul class="navbar-nav">
      	<li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Categorias
          </a>
          <ul class="dropdown-menu">
            <li>
            <form action="busquedaProducto" method="post">
	        	<button type="submit" class="dropdown-item">Bebidas</button>
	        	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="1" size="30">
            </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Comidas</button>
            	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="2" size="30">
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Cosmeticos</button>
            	<input type="hidden" name="idpedid" value="0" size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="3" size="30">
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Tecnologia</button>
	            <input type="hidden" name="idpedid" value="0" size="30">
		        <input type="hidden" name="dni" value=<%=dni %> size="30">
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
    <a class="navbar-brand" href="#">Navbar</a>
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
<div id="carouselExampleCaptions" class="carousel slide" style="width: 53%;margin: 3vh auto;box-shadow: 0 0 10px 2px rgba(0,0,0,0.5);">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  
  <div class="carousel-inner">

    <div class="carousel-item active">
      <img src="./images/technology.jpeg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="./images/food.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
           <img src="./images/cosmetics.jpg" height="" class="d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
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
        	<input type="hidden" name="dni" value=<%=dni %> size="30">
			<input type="hidden" name="lineasCompra" value="0" size="30">
        </form>
      </div>
    </div>
  </section>

<div class="row row-cols-1 row-cols-md-2 g-4" style="width: 60vw; margin: auto">
				<form action="busquedaProducto" method="post">
  						<div class="col">
    						<div class="card">
					            <input type="hidden" name="idpedid" value="0" size="30">
						        <input type="hidden" name="dni" value="12345678" size="30">
								<input type="hidden" name="lineasCompra" value="0" size="30">
								<input type="hidden" name="catId" value="4" size="30">
				    			<a href="">
				      			<img src="./images/apple-products.jpg" width="500px" class="card-img-top" alt="...">
				      			</a>
				      			<div class="card-body">
				       	 		<button type="submit" class="h5-like" style="border: 0px;background-color: white;">Tecnologia</button>
	      						</div>
	    					</div>
	  					</div>
  				</form>
  				
  				<form action="busquedaProducto" method="post">
  						<div class="col">
    						<div class="card">
					            <input type="hidden" name="idpedid" value="0" size="30">
						        <input type="hidden" name="dni" value="12345678" size="30">
								<input type="hidden" name="lineasCompra" value="0" size="30">
								<input type="hidden" name="catId" value="2" size="30">
				    			<a href="">
      							<img src="./images/food-products.png" width="500px" class="card-img-top" alt="...">
     							</a>
				      			<div class="card-body">
				       	 		<button type="submit" class="h5-like" style="border: 0px;background-color: white;">Comida</button>
	      						</div>
	    					</div>
	  					</div>
  				</form>
  				
  				<form action="busquedaProducto" method="post">
  						<div class="col">
    						<div class="card">
					            <input type="hidden" name="idpedid" value="0" size="30">
						        <input type="hidden" name="dni" value="12345678" size="30">
								<input type="hidden" name="lineasCompra" value="0" size="30">
								<input type="hidden" name="catId" value="3" size="30">
				    			<a href="">
							    <img src="./images/cosmetic-products.jpg" width="500px" class="card-img-top" alt="...">
							    </a>
				      			<div class="card-body">
				       	 		<button type="submit" class="h5-like" style="border: 0px;background-color: white;">Cosmeticos</button>
	      						</div>
	    					</div>
	  					</div>
  				</form>
  
  				<form action="busquedaProducto" method="post">
  						<div class="col">
    						<div class="card">
					            <input type="hidden" name="idpedid" value="0" size="30">
						        <input type="hidden" name="dni" value=<%=dni %> size="30">
								<input type="hidden" name="lineasCompra" value="0" size="30">
								<input type="hidden" name="catId" value="1" size="30">
				    			<a href="">
							    <img src="./images/drink-products.png" width="500px" class="card-img-top" alt="...">
							    </a>
								<div class="card-body">
				       	 		<button type="submit" class="h5-like" style="border: 0px;background-color: white;">Bebidas</button>
	      						</div>
	    					</div>
	  					</div>
  				</form>
    </div>


</main>

<footer class="text-muted footerAM">
  <div class="container">
    <p class="mb-1">Supermercado, SA.</p>
    <p class="mb-0">Desarrollado por Alvaro. 2023.</p>
  </div>
</footer>
    
</body>
</html>


