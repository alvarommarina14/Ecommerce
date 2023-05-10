<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="java.util.Collections"%>
<%@page import="Entities.Proveedor"%>
<%@page import="Entities.Provincia"%>
<%@page import="Entities.Localidad"%>
<%@page import="Entities.Sucursal"%>
<%@page import="Entities.Cliente"%>
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
    LinkedList<Provincia> provincias = (LinkedList<Provincia>) session.getAttribute("provincias");
    LinkedList<Localidad> localidades = (LinkedList<Localidad>) session.getAttribute("localidades");
    LinkedList<Sucursal> sucursales = (LinkedList<Sucursal>) session.getAttribute("sucursales");
    String payment = (String) session.getAttribute("payment");
    String withdraw = (String) session.getAttribute("withdraw");
    Cliente cliente = (Cliente) session.getAttribute("cliente");
    Integer idPedido = (Integer) session.getAttribute("idPedido");
    %>
    
<!-- <!-- <script> --> 
<!-- // function setSelectedOption(selectTag, nameProv) { -->
<!-- // 	  var options = selectTag.options; -->
<!-- // 	  for (var i = 0; i < options.length; i++) { -->
<!-- // 	    if (options[i].value == comparisonVariable) { -->
<!-- // 	      options[i].selected = true; -->
<!-- // 	      break; -->
<!-- // 	    } -->
<!-- // 	  } -->
<!-- // 	} -->
	
<!-- // window.addEventListener("load", function(){ -->
<%-- var comparisonVariable = '<%=cliente.getProvincia().getNombre()%>';  --%>
<!-- // var selectTag = document.getElementsByName("provinces"); // The select tag element -->
<!-- // setSelectedOption(selectTag, comparisonVariable); // Call the function -->
<!-- // }); -->
<!-- <!-- </script> -->

</head>
<body>
 <header style="
    background-color: #505050;
">
  <nav class="navbar navbar-expand-lg bg-body-tertiary" style="
    width: 50%;
">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Home</a>
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
            <li><a class="dropdown-item" href="#">Bebidas</a></li>
            <li><a class="dropdown-item" href="#">Comidas</a></li>
            <li><a class="dropdown-item" href="#">Cosmeticos</a></li>
            <li><a class="dropdown-item" href="#">Tecnologia</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Ofertas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Sucursales</a>
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
            
 <form action="compraConfirmada" method="post">
        <div class="payment-container">
  <div class="payment-containers">
  <label class= "payment-label" >Nombre</label>
  <input class="payment-input" value="<%=cliente.getNombreApellido() %>" readonly required></input>
  <label class= "payment-label" >Email</label>
  <input class="payment-input" type="email" value="<%= cliente.getEmail() %>" readonly required></input>
  <label class= "payment-label" >DNI</label>
  <input class="payment-input" type="number" value=<%= cliente.getNroDocumento() %> readonly required></input>
  <label class= "payment-label">Telefono</label>
  <input class="payment-input" value="<%= cliente.getNroTelefono()%>" readonly required></input>
   <label class= "payment-label">Provincia</label>
  <input class="payment-input" value="<%= cliente.getProvincia().getNombre()%>" readonly required></input>
<!--   <label class= "payment-label">Provincia</label> -->
<!--   <select name="provinces" class="payment-input" style="width: 189px;height: 30px;"> -->
  
<%-- 	<%for(Provincia p: provincias){ %> --%>
<!-- 	 <option   -->
<%-- 	 	<%if (p.getNombre() == cliente.getProvincia().getNombre()) {%> --%>
<!-- 	  	selected  -->
<%-- 	  	<% } %> --%>
<%-- 	  	value="<%=p.getNombre() %>"><%=p.getNombre() %></option> --%>
<%-- 	 <% --%>
<!-- // 	} -->
<%-- 	 %> --%>
<!-- </select> -->

  <label class= "payment-label">Localidad</label>
    <input class="payment-input" value="<%= cliente.getLocalidad().getNombre()%>" readonly required></input>
  </div>
  
  <div class="payment-containers">
  <label class= "payment-label"  >Codigo postal</label>
  <input class="payment-input" value="<%= cliente.getLocalidad().getCodPostal()%>" readonly required></input>
  <%
  if(withdraw == "delivery"){ 
  %>
  <label for="payment-input" class= "payment-label" >Direccion de entrega</label>
  <input class="payment-input" name="address" id="address" value="<%= cliente.getDireccion()%>" ></input>
  <%}
  else if (withdraw == "branch"){ 
  %>
  <label class= "payment-label" >Sucursal</label>
  <select name="branches" class="payment-input" style="width: 189px; height: 30px;
">
	<%for(Sucursal s: sucursales){ %>
	 <option value="<%=s.getDireccion() %>"><%=s.getDireccion() %></option>
	 <%
	}
	 %>
</select>
  <%
  } 
  %>
  <label class= "payment-label">Fecha Nacimiento</label>
  <input class="payment-input" type="date" style="width: 189px;height: 30px;" value="<%= cliente.getFechaNac()%>" required></input>
  <%
  if (payment == "card"){ 
  %>
  
  <label class= "payment-label">Numero de tarjeta</label>
  <input class="payment-input" id="credit-card" name="credit-number" placeholder="XXXX XXXX XXXX XXXX" required></input>
<script>
  const creditCardInput = document.getElementById('credit-card');
  creditCardInput.addEventListener('input', function (event) {
    const input = event.target.value;
    const digitsOnly = input.replace(/[^0-9]/g, '');
    const formatted = formatCreditCardNumber(digitsOnly);
    event.target.value = formatted;

    const isValid = formatted.length === 19;
    if (!isValid) {
      creditCardInput.setCustomValidity("The credit card must have 16 characters");
    } else {
      creditCardInput.setCustomValidity(""); // Reset the custom validity if the input is valid
    }
  });

  function formatCreditCardNumber(value) {
    const maxLength = 16;
    const separator = ' ';
    const regex = new RegExp(`(.{1,4})`, 'g');
    const parts = value.slice(0, maxLength).match(regex) || [];
    return parts.join(separator);
  }
</script>

  <label class= "payment-label">Tipo de tarjeta</label>
  <select class ="payment-input" name="brand" style=" width: 189px; height: 30px;">
  <option value="visa">Visa</option>
  <option value="mastercard">Mastercard</option>
</select>
	<div>
  <label class="payment-label">CVV   Mes/Año vencimiento</label>
  <input class="payment-input" id="CVV" name="CVV" placeholder="XXX" type="number" pattern="[0-9]{3}" style="width: 35px;" required></input>
  <input class="payment-input" id="month" name="month" placeholder="XX" type="number" pattern="[1-12]{1}" style="width: 35px;" required></input>
  <input class="payment-input" id="year" name="year" placeholder="XXXX" type="number" pattern="(20)[0-9]{2}" style="width: 50px;" required></input>
  <script>
  const inputField = document.getElementById('year');
  const inputField2 = document.getElementById('CVV');
  const inputField1 = document.getElementById('month');
  
inputField.addEventListener('input', function() {
  const inputValue = parseInt(inputField.value);
  if (isNaN(inputValue) || inputValue < 2023 || inputValue > 2050) {
    inputField.setCustomValidity("Please enter a valid year between 2023 and 2050");
  } else {
    inputField.setCustomValidity("");
  }
});
inputField1.addEventListener('input', function() {
	  const inputValue1 = parseInt(inputField1.value);
	  if (isNaN(inputValue1) || inputValue1 < 1 || inputValue1 > 12) {
	    inputField1.setCustomValidity("Please enter a number between 1 and 12.");
	  } else {
	    inputField1.setCustomValidity("");
	  }
});
	  inputField2.addEventListener('input', function() {
		  const inputValue2 = inputField2.value;
		  if (!/^\d{3}$/.test(inputValue2)) {
		    inputField2.setCustomValidity("Please enter a three digit number.");
		  } else {
		    inputField2.setCustomValidity("");
		  }
		});

</script>
  </div>
  <%
  }
  %>
  </div>

  <input type="hidden" name="id-order" size="30" value=<%=idPedido%>>
  <button type="submit" name="fin" class="end-purchase">Confirmar compra</button>
  </div>
    </form>
  </div>
      </div>
  </section>



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