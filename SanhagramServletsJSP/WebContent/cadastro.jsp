<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>
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
	<form action="salvarCadastro.jsp" method="post">
		<h3>Cadastro</h3>
		<br> 
		<label>Nome:</label><br> 
		<input type="text" name="nome"><br>
		<label>Email:</label><br> 
		<input type="text" name="email"><br> 
		<label>Senha:</label><br>
		<input type="password" name="senha"><br> 
		<label>Data de Nascimento</label><br> 
		<input type="text" name="data" OnKeyPress="formatar('##/##/####', this)"><br> 
		
		<input type="submit" value="ENVIAR">
	</form>
</body>
</html>