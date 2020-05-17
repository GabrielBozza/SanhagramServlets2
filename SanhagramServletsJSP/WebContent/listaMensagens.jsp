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

					<div class="conversa" style="background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
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
						<div class="conversa" style="background-image: linear-gradient(to bottom right, #ABF1BC, #87CDF6);border:2px solid #2bffb5;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
						<%}else{ %>
						<div class="conversa" style="background: #d2f7f3;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
						<%}%>
							<div class="imgconversa">
								<img class="iconegrupo" src=".\img\avatar1.jpg" alt="Otario">
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

	<div class="div2" ">

		<div class="top2" id="header">

			<div class="imgconversatop2">
				<img class="conversatop2" src=".\img\avatar1.jpg" alt="Otario">
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

		<div class="mid2" id="chat" style="background:#8ee6de;overflow-y:scroll;overflow-x:hidden;">
			<br>

				<%
					for (Mensagem m : listaResultado) {
				%>

						<%
							if (!m.getRemetente().equals(request.getSession().getAttribute("usuAutenticado"))) {
						%>
							<br><div id="BolhaMensagem" style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);margin-top:20px;margin-left:20px;float:left;text-align:center;width:250px;height:60px;border:0px;border-radius:15px;background-image: linear-gradient(to bottom right, #48A9FE, #0BEEF9);padding:5px;">
								<a style="padding-left:2px;padding-top:2px;float:left;font-size:13px;font-family: Helvetica;font-weight: bold;text-align:left; color: #ffe5b0;"><%=m.getRemetente()%></a>
								<a style="padding-right:2px;padding-top:2px;float:right;font-size:10px;font-family: Helvetica;margin-left: 0px; color: #303030;"><%=m.getData_envio().substring(11,16)%></a><br>
								<a style="font-family: Helvetica;margin-left: 0px; color: #303030;"><%=m.getTexto_mensagem()%></a>
								
							</div>
							<div id="BolhaFantasma" style="margin-top:20px;margin-right:20px;float:right;text-align:center;width:850px;height:60px;border:0px;border-radius:15px;padding:5px;"></div>
					<%
 						} else {
 					%>
							<br><div id="BolhaMensagem" style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);overflow:hidden;margin-top:20px;margin-right:20px;float:right;text-align:center;width:250px;height:60px;border:0px;border-radius:15px;background-image: linear-gradient(to bottom right, #02d46e, #41fac6);padding:5px;">
								<a style="padding-right:2px;padding-top:2px;font-size:13px;font-family: Helvetica;font-weight: bold;text-align:right; float:right; color: #ffffff;">Você</a>
								<a style="padding-left:2px;padding-top:2px;float:left;font-size:10px;font-family: Helvetica;color: #303030;"><%=m.getData_envio().substring(11,16)%></a><br>
								<a style="width:100%;font-family: Helvetica;margin-left: 0px; color: #303030;"><%=m.getTexto_mensagem()%></a>
								<a class="MarcadorExcluir" href="UsuarioControlador?remetente=<%=m.getRemetente()%>&destinatario=<%=m.getDestinatario()%>&acao=exmsgm&idmensagem=<%=m.getIdmensagem()%>"
							style="visibility:visible;font-family: Helvetica; font-style: normal; color: red; text-decoration: none;"
							id="excluir">X</a>
							</div>
							<div id="BolhaFantasma" style="margin-top:20px;margin-left:20px;float:left;text-align:center;width:850px;height:60px;border:0px;border-radius:15px;padding:5px;"></div> 
					<%
 						}
 					%>
				<%
					}
				%>
				
				
			<script>
				if(localStorage.getItem("modoApagarMensagens")=="Naoapagar"){
					document.getElementsByClassName("MarcadorExcluir").style.visibility="hidden";
				}
			</script>

			<form autocomplete="off" action="UsuarioControlador?acao=enviar" method="post">
			
				<input type="hidden" id="remetente" name="remetente"
					value=<%=request.getSession().getAttribute("usuAutenticado")%>>
				<input type="hidden" id="destinatario" name="destinatario"
					value=<%=request.getSession().getAttribute("destinatarioMsgm")%>>
					<br><br>
				<br><input class="textarea" type="text" id="texto_mensagem"
					placeholder="Mensagem" name="texto_mensagem"
					style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);font-family: Helvetica; background: #ffffff; width: 85%; text-align: center; margin-left: -30px; color: black;margin-top:40px;margin-bottom:90px;"
					required> <input type="submit" value="ENVIAR"
					style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);margin-bottom:90px;background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);font-weight:bold;font-family: Helvetica;text-align: center; margin-left: 15px; border-radius: 13px; border-width: 0px; width: 90px; height: 35px; cursor: pointer;color:white;">
			</form>
			<br> <br>
		</div>
	</div>
</body>

</html>