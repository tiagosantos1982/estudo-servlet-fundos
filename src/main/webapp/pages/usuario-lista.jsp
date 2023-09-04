<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	  <c:set var="req" value="${pageContext.request}" scope="request"/>
	  <c:set var="url" value="${req.requestURL}" scope="request"/>
	  <c:set var="uri" value="${req.requestURI}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listagem de Usuários</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">         
</head>
<body>

	<div class="container"> 
	  <h2>Usuários</h2>
	  <!--Search Form -->
		<span style="display:none;">
			path : &nbsp;<c:out value="${url}"/><br>
			path : &nbsp;<c:out value="${uri}"/><br>
			<c:out value="${req.getContextPath() }"/><BR>
			<c:out value="${req.getScheme() }"/><BR>
			<c:out value="${req.getServerPort()}"/><BR>
			<c:out value="${req.getServerName()}"/><BR>
			
		</span>
	  <form action="${req.getContextPath()}/usuario" method="get" id="seachUserForm" role="form" >
		<input type="hidden" id="searchAction" name="searchAction" value="searchByName"/>
		<div class="form-group col-xs-5">
		    <input type="text" name="userName" id="userName" class="form-control" required="true" 
		             placeholder="Type the Name of the user"/>                    
		</div>
		<button type="submit" class="btn btn-info" >
		    <span class="glyphicon glyphicon-search"></span> Search
		</button>
		<button type="button" class="btn btn-info" onClick="window.onSubmitFormAlterAction('reset')">
			<span class="glyphicon glyphicon glyphicon-refresh">Reset</span>
		</button>
		<button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#cadastroUsuario">
			<span class="glyphicon glyphicon glyphicon-refresh"></span> Cadastro
		</button>
		<br></br>
		<br></br>
	  </form>
	</div>
	
	<!--Employees List-->
	<div class="container">
	<form action="/usuario" method="post" id="usuarioForm" role="form" >              
	    <c:choose>
	        <c:when test="${not empty userList}">
	            <table  class="table table-striped">
	                <thead>
	                    <tr>
	                        <td>#</td>
	                        <td>Nome</td>
	                        <td>descrição</td>
	                        <td>E-mail</td>                                 
	                    </tr>
	                </thead>
	                <c:forEach var="user" items="${userList}">
	                    <c:set var="classSucess" value=""/>
	                    <c:if test ="${userID == user.id}">                        	
	                        <c:set var="classSucess" value="info"/>
	                    </c:if>
	                    <tr class="${classSucess}">
	                        <td>${user.id}</td>
	                        <td>${user.nome}</td>
	                        <td>${employee.descricao}</td>
	                        <td>${employee.email}</td>   
	                    </tr>
	                </c:forEach>               
	            </table>  
	        </c:when>                    
	        <c:otherwise>
	            <br>           
	            <div class="alert alert-info">
	                Nenhum cadastro de usuário encontrado!!!
	            </div>
	        </c:otherwise>
	    </c:choose>                        
	</form>
	
	<!-- ### Modal Cadastro de Usuário ### -->
	<!-- Modal -->
	<div class="modal fade" id="cadastroUsuario" tabindex="-1" role="dialog" 
	     aria-labelledby="cadastroUsuarioModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="cadastroUsuarioModalLabel">Cadastro</h5>
	        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<jsp:include page="novo-usuario.jsp"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>

	<script src="js/bootstrap.min.js"></script>
	<script>
		function onSubmitFormAlterAction(value)
		{
			value ??= "searchAction";
			let elemId = "searchAction";
			let formId = "seachUserForm";
			console.log(value);
			document.getElementById(elemId).value = value;  	
			document.getElementById(formId).submit();
		}
	</script> 
</body>
</html>