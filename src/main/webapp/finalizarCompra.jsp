<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="Entities.LineaCompra"%>
<%@page import="java.io.OutputStream" %>
<%@page import="java.util.Base64" %>
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

<script>
function handleCheckbox() {
  var deliveryCheckbox = document.getElementById("inlineCheckbox1");
  var branchCheckbox = document.getElementById("inlineCheckbox2");
  var cash = document.getElementById("inlineCheckbox3");
  var card = document.getElementById("inlineCheckbox4");

  deliveryCheckbox.onclick = function() {
    if (deliveryCheckbox.checked) {
      branchCheckbox.checked = false;
    }
  };

  branchCheckbox.onclick = function() {
    if (branchCheckbox.checked) {
      deliveryCheckbox.checked = false;
    }
  };
  
  cash.onclick = function() {
	    if (cash.checked) {
	      card.checked = false;
	    }
	  };

	  card.onclick = function() {
	    if (card.checked) {
	      cash.checked = false;
	    }
	  };
}

window.onload = handleCheckbox;
</script>
<script>
const calculaPrecioTotal = function() {
    var suma = 0;
    const a = document.getElementsByClassName("subtot");
    for (let i = 0; i < a.length; i++) {
        const element = parseFloat(a[i].innerHTML.substring(1));
        suma = suma + element;
        
    }
    return suma;
}

window.addEventListener("load", function(){
	let myParagraph = document.getElementById("precio-total");

	myParagraph.textContent = "Precio total: $"+calculaPrecioTotal();

	});

</script>

<script>
function checkTableRows(table, warningMessage){
	if(table[0].rows.length == 2){
		warningMessage.style.display = "block";
	}
}

window.addEventListener("load", function(){
	const table = document.getElementsByClassName("tablaProductos");
	const warningMessage = document.getElementById("warningMessage");
	checkTableRows(table, warningMessage);
	table[0].addEventListener("DOMNodeInserted", function(){
		checkTableRows(table, warningMessage);
	});
	table[0].addEventListener("DOMNodeRemoved", function(){
		checkTableRows(table, warningMessage);
	});
});
</script>
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
    LinkedList<LineaCompra> lc = (LinkedList<LineaCompra>) session.getAttribute("listaLC");
    Integer idPedido = (Integer) session.getAttribute("idPedido");
//     String pedido = (String) session.getAttribute("pedido");
    Integer dni = (Integer) session.getAttribute("dni");
    %>
 
</head>
<body>

<header style="background-color: #505050;">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="width: 50%;">
  <div class="container-fluid">
	    <a class="navbar-brand" href="/project_super_2502/home.jsp">Home</a>
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
	        	<input type="hidden" name="idpedid" value=<%=idPedido %> size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="1" size="30">
<%-- 				<input type="hidden" name="pedido" value=<%=pedido %>></input> --%>
            </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Comidas</button>
            	<input type="hidden" name="idpedid" value=<%=idPedido %> size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="2" size="30">
<%-- 				<input type="hidden" name="pedido" value=<%=pedido %>></input> --%>
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Cosmeticos</button>
            	<input type="hidden" name="idpedid" value=<%=idPedido %> size="30">
	        	<input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="3" size="30">
<%-- 				<input type="hidden" name="pedido" value=<%=pedido %>></input> --%>
		    </form>
            </li>
            <li>
             <form action="busquedaProducto" method="post">
            <button type="submit" class="dropdown-item">Tecnologia</button>
	            <input type="hidden" name="idpedid" value=<%=idPedido %> size="30">
		        <input type="hidden" name="dni" value=<%=dni %> size="30">
				<input type="hidden" name="lineasCompra" value="0" size="30">
				<input type="hidden" name="catId" value="4" size="30">
<%-- 				<input type="hidden" name="pedido" value=<%=pedido %>></input> --%>
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

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Supermarket</h1>
        <p class="lead text-muted">Productos de calidad a la mano</p>
        <p>
<!--         <form action="busquedaProducto" method="post"> -->
<%--         	<input type="text" name="descripcion" class="descripcion-busqueda" size="30" value=<%=descripcion %>> --%>
<!--         	<button type="submit" class="product-search" name="product-search">Buscar</button> -->
<%--         	<input type="text" name="idpedid" value=<%=idPedido %> size="30"> --%>
<%--         	<input type="text" name="pedido" value=<%=pedido %>></input> --%>
<%--     		<button type="submit" name="idPedido" value=<%= idPedido %>>elPedido</button> --%>
<!--         </form>         -->
      </div>
    </div>
  </section>
<%-- <h1><%productos.size(); %></h1> --%>
  <div class="album py-5 bg-light" id="tablaProds">
    <div class="container">
<div class="child1" style="text-align: center;">

				<%if(lc != null){ %>
				<form action="finalizarCompra" method="post">
				<table class="tablaProductos">
					<thead>
						<tr>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Cantidad</th>
							<th>Subtotal</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (LineaCompra l : lc) {
						%>
						<tr>
							<td><%=l.getDescripcion()%></td>
							<td>$<%=l.getPrecio()%></td>
							<td><%=l.getCantidad()%></td>
							<td class="subtot">$<%=l.getPrecio()*l.getCantidad()%></td>
							<td> 
							<button class="end-purchase" name="delete-item" value="delete"> Eliminar </button></td>
							<td style="border: 0px;"><input type="hidden" name="delete-prod" size="30" value="delete"> </td>
							<td style="border: 0px;"><input type="hidden" name="list-size" size="30" value=<%=lc.size()%>> </td>
							<td style="border: 0px;"><input type="hidden" name="idProducto" size="30" value=<%=l.getIdProducto()%>> </td>
							<td style="border: 0px;"><input type="hidden" name="id-pedido" size="30" value=<%=l.getNroPedido()%>> </td>
							<td style="border: 0px;"><input type="hidden" name="dni" size="30" value=<%=dni%>> </td>
						</tr>
						<%
						}
						%>
						
					</tbody>
				</table>
					<p class="lead text-muted" id="precio-total" style="font-size: 1rem;margin-top: 10px;font-weight: 500;">Precio total: $181</p>
							
				<p class="lead text-muted" id="warningMessage" style="color: red;text-align: center; font-size:1rem; display:none;">Para realizar la compra debe tener por lo menos un producto</p>
				<% } %>
				</form>

		</div>

      </div>
    </div>

				<div style="text-align: center;">
				    <form action="metodoPago" method="post" style="display: inline-block;">
				     	<div class="checkbox-container">
    					<div class="form-check form-check-inline">
        				<label style="font-weight: bold;"> Tipo de entrega: </label>
				  			<div class="form-check form-check-inline" style="margin-left: 1rem;">
				    			<input class="form-check-input" type="checkbox" name="checkbox-delivery" id="inlineCheckbox1" value="option1" checked>
				    			<label class="form-check-label" for="inlineCheckbox1">Entrega a domicilio</label>
				  			</div>
				  		<div class="form-check form-check-inline">
				    			<input class="form-check-input" type="checkbox" name="checkbox-branch" id="inlineCheckbox2" value="option2">
				    			<label class="form-check-label" for="inlineCheckbox2">Retiro por sucursal</label>
				  		</div>
						</div>
						<div class="form-check form-check-inline">
        				<label style="font-weight: bold;"> Forma de pago: </label>
				  			<div class="form-check form-check-inline" style="margin-left: 1rem;">
				    			<input class="form-check-input" type="checkbox" name="checkbox-cash" id="inlineCheckbox3" value="option3" checked>
				    			<label class="form-check-label" for="inlineCheckbox1">Efectivo</label>
				  			</div>
				  		<div class="form-check form-check-inline">
				    			<input class="form-check-input" type="checkbox" name="checkbox-card" id="inlineCheckbox4" value="option4">
				    			<label class="form-check-label" for="inlineCheckbox2">Tarjeta</label>
				  		</div>
						</div>
						</div>
						<input type="hidden" name="idPedido" value=<%=idPedido %> size="30">
						<button type="submit" name="fin" class="end-purchase" value="proceed" style="display: inline-block;">Realizar pago</button>
						<button type="submit" name="fin" class="end-purchase" value="end" style="display: inline-block;">Cancelar compra</button>
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


