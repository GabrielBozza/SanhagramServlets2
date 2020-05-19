<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acesso negado</title>
</head>
<body>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
<h1>Somente o admin tem permissão para efetuar essa operação!</h1>
<a href="UsuarioControlador?acao=pagInicial">Voltar para a página inicial</a>
<script>
alert("Algum erro ocorreu: Cadastro já existente, login incorreto ou outros erros!!!")
</script>
</body>
</html>