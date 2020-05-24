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
<title>Alterações - Área admin</title>
</head>
<body>
	<script>
		document.body.style.zoom = (window.screen.width / 1536);
	</script>

	<%
		//List<Usuario> lista = usuDAO.buscarTodos(usu);
		List<String> listaAmigos = (List<String>) request.getAttribute("listaAmigos");
		List<Usuario> listaResultado = (List<Usuario>) request.getAttribute("lista");
	%>
	<script>
		localStorage.setItem("modoApagarMensagens", "naoApagar");
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

					<div class="conversa"
						style="height: 60px; background-image: linear-gradient(to bottom right, #2AF598, #08B3E5); box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
						<div class="imgconversa">
							<img class="iconegrupo" style="border-radius: 40px;"
								src=".\img\img4.png" alt="Otario">
						</div>
						<div class="textocorpo" id="nomeusuario">
							<a href="UsuarioControlador?acao=pagInicial"
								style="font-weight: 500; font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"><%=request.getSession().getAttribute("usuAutenticado")%></a>
						</div>
					</div>

					<%
						for (String s : listaAmigos) {
					%>

					<a
						href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>">
						<div class="conversa"
							style="height: 60px; background: #d2f7f3; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
							<div class="imgconversa">
								<img class="iconegrupo" style="border-radius: 40px;"
									src=".\img\avatar1.jpg" alt="Otario">
							</div>
							<div class="textocorpo" id="nomeusuario">
								<a id="verconversa"
									style="font-weight: 500; width: 100%; font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"
									href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>"><%=s%></a>
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

	<div class="div2">
		<div id="EspacoVazio"
			style="margin-top: 0px; margin-bottom: 8px; paddin: 5px; float: left; height: 15px; width: 100%;"></div>
		<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
		<div class="top2" id="header" style="margin-top: 0px;">

			<div class="imgconversatop2">
				<img class="conversatop2" src=".\img\img4.png" alt="Otario">
			</div>

			<div class="nomeconversatop2" id="topuser"><%=request.getSession().getAttribute("usuAutenticado")%></div>

		</div>
		<div class="mid2" id="chat"
			style="height: 100%; background: #8ee6de; overflow-y: scroll;">

			<%
				if (request.getAttribute("tipo").equals("lisusuarios")) {
			%>
			<table border="0"
				style="font-family: Helvetica; font-size: 19px; margin-left: 140px; margin-top: 30px; padding: 5px; border-radius: 13px; background: #ffffff; text-align: left; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
				<tr style="height: 35px; padding: 5px; text-align: center;">
					<th style="padding: 10px;">Nome</th>
					<th style="padding: 10px;">Email</th>
					<th style="padding: 10px;">Senha</th>
					<th style="padding: 10px;">Data de Nascimento</th>
					<th style="padding: 10px;"></th>
					<th style="padding: 10px;"></th>
				</tr>
				<%
					for (Usuario u : listaResultado) {
				%>
				<tr style="height: 25px; padding: 5px;">
					<td style="padding: 10px;"><%=u.getNome()%></td>
					<td style="padding: 10px;"><%=u.getEmail()%></td>
					<td style="padding: 10px;"><%=u.getSenha()%></td>
					<td style="padding: 10px;"><%=u.getDatanasc()%></td>
					<td style="padding: 10px;"><a
						href="UsuarioControlador?acao=ex&nomeusu=<%=u.getNome()%>"
						style="color: red; text-decoration: none;">Excluir</a>
					<td style="padding: 10px;"><a
						href="UsuarioControlador?acao=alt&flagGrupo=0&nomeusu=<%=u.getNome()%>"
						style="color: blue; text-decoration: none;">Alterar</a>
				</tr>
				<%
					}
				%>
			</table>

			<%
				} else{
			%>
						<table border="0"
				style="width:300px;font-family: Helvetica; font-size: 19px; margin-left: 500px; margin-top: 30px; padding: 5px; border-radius: 13px; background: #ffffff; text-align: left; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
				<caption style="margin-bottom:10px;font-weight:bold;font-family: Helvetica; font-size: 21px;">Grupos</caption>
				<tr style="height: 35px; padding: 5px; text-align: center;">
					<th style="padding: 10px;">Nome</th>
					<th style="padding: 10px;"></th>
				</tr>
				<%
					for (Usuario u : listaResultado) {
				%>
				<tr style="height: 25px; padding: 5px;">
					<td style="padding: 10px;"><%=u.getNome()%></td>
					<td style="padding: 10px;"><a
						href="UsuarioControlador?acao=alt&nomeusu=<%=u.getNome()%>&flagGrupo='1'"
						style="color: blue; text-decoration: none;">Alterar</a>
				</tr>
				<%
					}
				%>
			</table>
			
			<%} %>
			<br>
			<br>
		</div>
	</div>
</body>
</html>