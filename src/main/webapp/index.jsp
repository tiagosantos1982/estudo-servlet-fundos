<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<!-- recupera caminho do contexto e configura uma variável com este valor -->
	<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
	<h2>Hello World!</h2>
	<jsp:forward page="/usuario"/>
</body>
</html>