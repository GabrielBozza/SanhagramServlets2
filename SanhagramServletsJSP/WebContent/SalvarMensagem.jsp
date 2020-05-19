<%@page import="java.util.List"%>
<%@page import="bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href=".\styles\style3.css">
<link href=".\styles\fontawesome-free-5.12.1-web\css\all.css"
	rel="stylesheet">
<script src=".\js\scriptelaprincip.js"></script>
<meta charset="ISO-8859-1">
<title>Página Inicial</title>
</head>

<body>
	<%
		List<String> listaResultado = (List<String>) request.getAttribute("lista");
	%>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
	<div class="div1">

		<div class="top1">
			<p class="ttop1">Conversas</p>
		</div>

		<div class="bot1">

			<div class="bot11" id="style-2">
				<input class="textarea" placeholder="Procurar..."
					onfocus="this.placeholder = ''"
					onblur="this.placeholder = 'Procurar...'" type="text"
					id="conversaslado" name="conversaslado"
					onkeyup="selecionatext(this)">

				<div class="barralateral" id="conversas">

					<div class="conversa" style="height:60px;background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
						<div class="imgconversa">
							<img class="iconegrupo" style="border-radius:40px;" src=".\img\img4.png" alt="Otario">
						</div>
						<div class="textocorpo" id="nomeusuario">
							<a href="UsuarioControlador?acao=pagInicial"
								style="font-weight:500;font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"><%=request.getSession().getAttribute("usuAutenticado")%></a>
						</div>
					</div>

					<%
						for (String s : listaResultado) {
					%>

					<a href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>">
						<div class="conversa" style="height:60px;background: #d2f7f3;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
							<div class="imgconversa">
								<img class="iconegrupo" style="border-radius:40px;" src=".\img\avatar1.jpg" alt="Otario">
							</div>
							<div class="textocorpo" id="nomeusuario">
								<a id="verconversa" style="font-weight:500;width:100%;font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;" href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>"><%=s%></a>
							</div>
						</div>
					</a>
					
					<%
						}
					%>

				</div>

			</div>

		</div>

	</div>

	<div class="div2" style="overflow:hidden;">


		<%
			if (request.getSession().getAttribute("usuAutenticado").equals("admin")) {
		%>
		<div id="EspacoVazio" style="margin-top:0px;margin-bottom:8px;paddin:5px;float:left;height:15px;width:100%;"></div>
		<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
		<%
			} else {
		%>
		<div id="EspacoVazio" style="margin-top:0px;margin-bottom:8px;paddin:5px;float:left;height:15px;width:100%;"></div>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<%
			}
		%>
		<div class="top2" id="header" style="margin-top:0px;">

			<div class="imgconversatop2">
				<img class="conversatop2" src=".\img\img4.png" alt="Otario">
			</div>

			<div class="nomeconversatop2" id="topuser"><%=request.getSession().getAttribute("usuAutenticado")%></div>

		</div>
		<div class="mid2" id="chat" style="padding-top:150px;background:#8ee6de;">
			<br> <br>
			<form autocomplete="off" action="UsuarioControlador?acao=enviar" method="post">
				<input type="hidden" id="remetente" name="remetente"
					value=<%=request.getSession().getAttribute("usuAutenticado")%>>
				<input type="hidden" id="destinatario" name="destinatario" value="ADefinirUsuario"> 
					<input class="textarea" type="text"
					id="texto_mensagem" placeholder="Mensagem" name="texto_mensagem"
					style="height:40px;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);font-family: Helvetica; background: #ffffff; width: 50%; text-align: center; margin-left: -80px; color: black;"
					required> <br> <input type="submit" value="SALVAR" onclick="alert('Mensagem Salva com Sucesso!')"
					style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);font-weight:bold;font-family: Helvetica;text-align: center; margin-left: -70px; border-radius: 13px; border-width: 0px; width: 90px; height: 50px; cursor: pointer;color:white;">
			</form>
		</div>

	</div>
</body>

</html>