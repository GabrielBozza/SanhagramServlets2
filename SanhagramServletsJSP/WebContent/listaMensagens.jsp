<%@page import="java.util.List"%>
<%@page import="bean.Mensagem"%>
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
<title>Lista mensagens</title>
</head>
<body>
	<%
		//List<Usuario> lista = usuDAO.buscarTodos(usu);
		//Set refresh, autoload time as 5 seconds
		//response.setIntHeader("Refresh", 2);
		List<String> listaAmigos = (List<String>) request.getAttribute("listaAmigos");
		List<Mensagem> listaResultado = (List<Mensagem>) request.getAttribute("lista");
	%>
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

				<div class="bot12">
					<i id="estrelinha" class="far fa-star" onclick="selecionastarred()"></i>

					<div class="starred">
						Favoritos

						<div class="minimiza" onclick="minimizatela(this)">-</div>

					</div>
				</div>

				<div class="barralateral" id="conversas">

					<div class="conversa" style="background: #87f5e9;">
						<div class="imgconversa">
							<img class="iconegrupo" src=".\img\img4.png" alt="Otario">
						</div>
						<div class="textocorpo" id="nomeusuario">
							<a href="UsuarioControlador?acao=pagInicial"
								style="font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"><%=request.getSession().getAttribute("usuAutenticado")%></a>
						</div>
					</div>

					<%
						for (String s : listaAmigos) {
					%>

					<a
						href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>">
						<%if(s.equals(request.getAttribute("conversaAtual"))){ %>
						<div class="conversa" style="background: #1af0d8;">
						<%}else{ %>
						<div class="conversa" style="background: #d2f7f3;">
						<%}%>
							<div class="imgconversa">
								<img class="iconegrupo" src=".\img\img5.png" alt="Otario">
							</div>
							<div class="textocorpo" id="nomeusuario">
								<a id="verconversa"
									style="font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"
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

		<div class="top2" id="header">

			<div class="imgconversatop2">
				<img class="conversatop2" src=".\img\img5.png" alt="Otario">
			</div>

			<div class="nomeconversatop2" id="titulo"><%=request.getSession().getAttribute("destinatarioMsgm")%></div>

		</div>
		<%
			if (request.getSession().getAttribute("usuAutenticado").equals("admin")) {
		%>
		<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
		<%
			} else {
		%>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<%
			}
		%>

		<div class="mid2" id="chat">
			<br>
			<table border="0">
				<tr bgcolor="#f8fbfd">
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<%
					for (Mensagem m : listaResultado) {
				%>
				<tr>
					<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					<td style="color: #f8fbfd"><b id="destino"><%=m.getDestinatario()%></b>
						<%
							if (!m.getRemetente().equals(request.getSession().getAttribute("usuAutenticado"))) {
						%>
					<td><a id="fonte"
						style="font-family: Helvetica; font-style: normal; text-decoration: none; color: #0da1b5;"><%=m.getRemetente()%></a>
					<td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					<td
						style="font-family: Helvetica; font-style: normal; width: 200px;"><%=m.getTexto_mensagem()%></td>
					<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					<td><a></a> 
					<%
 						} else {
 					%>
					<td><a id="fonte"
						style="font-family: Helvetica; font-style: normal; text-decoration: none; color: #6ebf17;">Você</a>
					<td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					<td
						style="font-family: Helvetica; font-style: normal; width: 200px;"><%=m.getTexto_mensagem()%></td>
					<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
					<td><a
						href="UsuarioControlador?remetente=<%=m.getRemetente()%>&destinatario=<%=m.getDestinatario()%>&acao=exmsgm&idmensagem=<%=m.getIdmensagem()%>"
						style="font-family: Helvetica; font-style: normal; color: red; text-decoration: none;"
						id="excluir">X</a> 
					<%
 						}
 					%>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<%
					}
				%>

			</table>
			<br>

			<form autocomplete="off" action="UsuarioControlador?acao=enviar" method="post">
				<input type="hidden" id="remetente" name="remetente"
					value=<%=request.getSession().getAttribute("usuAutenticado")%>>
				<input type="hidden" id="destinatario" name="destinatario"
					value=<%=request.getSession().getAttribute("destinatarioMsgm")%>>
				<input class="textarea" type="text" id="texto_mensagem"
					placeholder="Mensagem" name="texto_mensagem"
					style="font-family: Helvetica; background: #deddd9; width: 50%; text-align: center; margin-left: -80px; color: black;"
					required> <br> <input type="submit" value="ENVIAR"
					style="font-family: Helvetica; background: #deddd9; text-align: center; margin-left: -70px; border-radius: 6px; border-width: 0px; width: 90px; height: 35px; cursor: pointer;">
			</form>
			<br> <br> <br>
		</div>
	</div>
</body>

</html>