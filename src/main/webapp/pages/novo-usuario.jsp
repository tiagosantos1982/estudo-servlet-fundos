<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Novo Usuário</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

	<div class="container"> 
	  <h2>Usuários</h2>
	  <form action="usuario" method="post" role="form" data-toggle="validator">
		<c:if test="${empty action}">                        	
		    <c:set var="action" value="add"/>
		</c:if>
		<span>
			<c:out value="${action}"/>
		</span>
      	<input type="hidden" id="action"  name="action" value="${action}">
      	<input type="hidden" id="userId"  name="userId" value="${usuario.id}">
	  		<div class="form-floating mb-3">
        		<input type="text" class="form-control" id="floatingInput" placeholder="nome do usuário"
        		        name="nome" value="${usuario.nome}" required="true">
		    	<label for="floatingInput">Nome</label>
			</div>
	  		<div class="form-floating mb-3">
        		<input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
        		       name="email" value="${usuario.email}" required="true">
		    	<label for="floatingInput">Email address</label>
			</div>
			<div class="form-floating">
		    	<input type="text" class="form-control" id="floatingInput" placeholder="breve descrição"
		    	 name="descricao" value="${usuario.descricao}" required="true">
		    	<label for="floatingPassword">Descrição</label>
			</div>
        	<br></br>
        	<button type="submit" class="btn btn-primary  btn-md">Confirmar</button>
	  </form>
	</div>
	<script src="js/bootstrap.min.js"></script>     
</body>
</html>