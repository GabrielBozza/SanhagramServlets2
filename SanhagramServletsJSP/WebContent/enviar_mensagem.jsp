<%@ page import="java.sql.Date"%>
<%@ page import="jdbc.MensagemDAO"%>
<%@ page import="bean.Mensagem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enviar mensagem</title>
</head>
<body>
	<%
		String remetente = request.getParameter("remetente");
		String destinatario = request.getParameter("destinatario");
		String texto_mensagem = request.getParameter("texto_mensagem");

		try {
			
			Mensagem mensagem = new Mensagem();
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);
			mensagem.setTexto_mensagem(texto_mensagem);

			MensagemDAO mensagemDAO = new MensagemDAO();
			mensagemDAO.enviar(mensagem);
		}

		catch (Exception e) {
			System.out.println(e);

		}
	%>
	<h2 style="font-family:Helvetica;text-align:center;">Enviada com sucesso!</h2>
	<a id="msgm" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:750px;">OK</a>
	<script>
		document.getElementById('msgm').href="UsuarioControlador?acao=lismsgm&remetente=<%= request.getParameter("remetente")%>"+"&destinatario=<%= request.getParameter("destinatario")%>";
	</script>

</body>
</html>