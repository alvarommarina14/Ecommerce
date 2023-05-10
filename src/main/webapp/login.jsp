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
    
</head>
<body>
 <div class="rectangle">
 <div class="rec"></div>
<div class="rec" style="background-color: white;opacity: 0.85;display: flex;justify-content: center;align-items: center;flex-direction: column;">
 <form action="home" method="post" style="width: 75%">
   <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto" style="
    width: 100%;">
        <h1 class="fw-light">Supermarket</h1>
        <p class="lead text-muted">Productos de calidad a la mano</p>
      </div>
    </div>
  </section>
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email</label>
    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
    <div id="emailHelp" class="form-text">Nunca compartas tu contraseña.</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Contraseña</label>
    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
  </div>
  <input value="0" type="hidden" name="login-attempts">
  <button type="submit" class="btn btn-primary" style="border-color: #d5931a; background-color: #d5931a">Ingresar</button>
</form>
<form action="homeAdmin" method="post" style="width: 75%">
  <button type="submit" class="btn btn-primary" style="border-color: #d5931a; background-color: #d5931a">Admin Login</button>
</form>
    </div>
 </div>




 






    








</body>
</html>

 






    




</body>
</html>


