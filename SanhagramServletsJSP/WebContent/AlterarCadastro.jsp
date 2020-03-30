<%@ page import="bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar</title>
</head>
<script>
function formatar(mascara, documento){
  var i = documento.value.length;
  var saida = mascara.substring(0,1);
  var texto = mascara.substring(i)
  
  if (texto.substring(0,1) != saida){
            documento.value += texto.substring(0,1);
  }
  
}
</script>
<body>
<jsp:include page="cabecalho.jsp"></jsp:include>
	<%
		Usuario usu = (Usuario)request.getAttribute("usuario");
		System.out.println(usu.getId());
	%>
<form action="UsuarioControlador" method="post">
		<input type="hidden" name="acao" value="salvar">
		
		<label>ID:</label>
		<input size="5" type="text" name="id" value="<%=usu.getId()%>">
		<label>Nome:</label> <br>
		<input type="text" name="nome" value=<%=usu.getNome()%>>
		<label>Email:</label> <br>
		<input type="text" name="email" value="<%=usu.getEmail()%>"><br>
		<label>Senha:</label> <br>
		<input type="password" name="senha" value="<%=usu.getSenha()%>"> <br>
		<label>Data de Nascimento</label><br>
		<input type="text" name="data" OnKeyPress="formatar('##/##/####', this)"><BR>
		
		<input type="submit" value="SALVAR">
	</form>

</body>
</html>