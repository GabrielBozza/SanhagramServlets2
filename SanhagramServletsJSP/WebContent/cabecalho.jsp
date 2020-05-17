<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabeçalho</title>
</head>
<body>
<script>
	function modoApagar(){
		if(localStorage.getItem("modoApagarMensagens")=="apagar"){
			localStorage.setItem("modoApagarMensagens","apagar");
		}
		else{
			localStorage.setItem("modoApagarMensagens","apagar");
		}
	}
</script>
<br>
<a  href="UsuarioControlador?acao=pagInicial" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:60px;">Início</a>
<a id="ExcluirMensagens" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:430px;" href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=request.getAttribute("conversaAtual")%>" onclick="modoApagar()">Apagar mensagens</a>
<a href="autenticador" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:430px;">Sair</a><br><br>


</body>
</html>