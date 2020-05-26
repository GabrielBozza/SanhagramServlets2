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
<title>Mensagens Salvas</title>
</head>
<body>
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
	<%
		List<String> listaAmigos = (List<String>) request.getAttribute("listaAmigos");
		List<Mensagem> listaResultado = (List<Mensagem>) request.getAttribute("lista");
	%>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
							<a href="UsuarioControlador?acao=pagInicial&dispositivo=desktop"
								style="font-weight: 500; font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"><%=request.getSession().getAttribute("usuAutenticado")%></a>
						</div>
					</div>

					<%
						for (String s : listaAmigos) {
					%>

					<a
						href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>&dispositivo=desktop"
						onclick="voltarModoNormal()">
						<div class="conversa"
							style="background: #d2f7f3; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); height: 60px;">
							<div class="imgconversa">
								<img class="iconegrupo" style="border-radius: 40px;"
									src=".\img\avatar1.jpg" alt="Otario">
							</div>
							<div class="textocorpo" id="nomeusuario">
								<a id="verconversa"
									style="font-weight: 500; font-size: 18px; color: black; font-family: Helvetica; text-decoration: none;"
									href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>&dispositivo=desktop"
									onclick="voltarModoNormal()"><%=s%></a>
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

	<div class="div2"">


		<%
			if (request.getSession().getAttribute("usuAutenticado").equals("admin")) {
		%>
		<div id="EspacoVazio"
			style="margin-top: 0px; margin-bottom: 8px; paddin: 5px; float: left; height: 15px; width: 100%;"></div>
		<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
		<%
			} else {
		%>
		<div id="EspacoVazio"
			style="margin-top: 0px; margin-bottom: 8px; paddin: 5px; float: left; height: 15px; width: 100%;"></div>
		<jsp:include page="cabecalho.jsp"></jsp:include>
		<%
			}
		%>
		<div class="top2" id="header" style="margin-top: 0px;">

			<div class="imgconversatop2">
				<img class="conversatop2" src=".\img\img4.png" alt="Otario">
			</div>

			<div class="nomeconversatop2" id="titulo"><%=request.getSession().getAttribute("usuAutenticado")%></div>

		</div>
		<div class="mid2" id="chat"
			style="height: 100%; background: #8ee6de; overflow-y: scroll; overflow-x: hidden;">

			<br>

			<%
				for (Mensagem m : listaResultado) {
			%>

			<br>
			<div style="width: 500px; margin-left: 370px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);background: #5b99fc;border-radius:13px;">
				<div style="float: right; margin-top: 35px; margin-right: 3px;">
					<a href="#aaa"
						style="visibility: visible; font-family: Helvetica; font-size: 22px; font-style: normal; color: white; text-decoration: none;"><i
						class="fa fa-fw fa-paper-plane"></i></a> <a class="marcador"
						href="UsuarioControlador?remetente=<%=m.getRemetente()%>&destinatario=<%=m.getDestinatario()%>&acao=exmsgm&idmensagem=<%=m.getIdmensagem()%>&dispositivo=desktop"
						style="font-size: 20px; visibility: hidden; font-family: Helvetica; font-style: normal; color: red; text-decoration: none;"><i
						class="fa fa-fw fa-trash"></i></a>
				</div>
				<div id="BolhaMensagem"
					style="width: 400px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); overflow: hidden; margin-top: 20px; margin-right: 5px; float: right; text-align: center; border: 0px; border-radius: 15px; background: #1ed985; padding: 8px;">
					<a
						style="padding-left: 12px; padding-right: 2px; padding-top: 2px; font-size: 13px; font-family: Helvetica; font-weight: bold; text-align: right; float: right; color: #1976f7;">Rascunho</a>
					<a
						style="font-weight: 600; padding-left: 2px; padding-top: 2px; float: left; font-size: 11px; font-family: Helvetica; color: black;"><%=m.getData_envio().substring(8, 10)%>/<%=m.getData_envio().substring(5, 7)%>/<%=m.getData_envio().substring(0, 4)%></a><br>
					<a style="font-family: Helvetica; margin-left: 0px; color: white;padding:3px;"><%=m.getTexto_mensagem()%></a>
				</div>
				<form autocomplete="off" action="UsuarioControlador?acao=enviar&dispositivo=desktop"
					method="post">
					<input type="hidden" id="remetente" name="remetente"
						value=<%=request.getSession().getAttribute("usuAutenticado")%>>
					<input type="hidden" id="texto_mensagem" name="texto_mensagem"
						value='<%=m.getTexto_mensagem()%>'> 
					<input class="textarea" type="text" id="destinatario" name="destinatario"
						placeholder="Destinatário" 
						style="margin-top:8px;margin-left:-38px;width:66%;height: 20px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); font-family: Helvetica; background: #ffffff; text-align: center; color: black;"
						required> <input type="submit" value="ENVIAR"
						style="width: 65px; height: 35px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); background-image: linear-gradient(to bottom right, #2AF598, #08B3E5); font-weight: bold; font-family: Helvetica; text-align: center; border-radius: 13px; border-width: 0px; cursor: pointer; color: white;">
				</form>
			</div>
			<%
				}
			%>
			<button onclick="modoApagar();"
				style="font-size:20px;border: none; outline: none; position: fixed; bottom: 76.5%; right: 76.7%; width: 40px; height: 40px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); background-image: linear-gradient(to bottom right, #2AF598, #08B3E5); font-weight: bold; font-family: Helvetica; text-align: center; border-radius: 40px; border-width: 0px; cursor: pointer; color: white;">
				<i class="fa fa-fw fa-trash"></i>
			</button>
			<div id="EspacoEmBranco"
				style="margin-top: 20px; paddin: 5px; float: left; height: 180px; width: 100%;"></div>
			<script>
				if (localStorage.getItem("modoApagarMensagens") == "apagar") {
					var x = document.getElementsByClassName('marcador');
					var i;
					for (i = 0; i < x.length; i++) {
						x[i].style.visibility = "visible";
					}
				}
			</script>
			<script>
				document.getElementById("chat").scroll(0,
						document.getElementById("chat").scrollHeight);//VAI PARA A ULTIMA MENSAGEM DA CONVERSA AO CARREGAR A PAGINA
				function voltarModoNormal() {
					if (localStorage.getItem("modoApagarMensagens") == "apagar") {
						localStorage
								.setItem("modoApagarMensagens", "naoApagar");
					}
				}

				function modoApagar() {
					var x = document.getElementsByClassName('marcador');
					var i;
					if (x[0].style.visibility == "visible") {
						localStorage
								.setItem("modoApagarMensagens", "naoApagar");
						for (i = 0; i < x.length; i++) {
							x[i].style.visibility = "hidden";
						}
					} else {
						localStorage.setItem("modoApagarMensagens", "apagar");
						for (i = 0; i < x.length; i++) {
							x[i].style.visibility = "visible";
						}
					}
				}
			</script>
			<form autocomplete="off" action="UsuarioControlador?acao=enviar&dispositivo=desktop"
				method="post">
				<input type="hidden" id="remetente" name="remetente"
					value=<%=request.getSession().getAttribute("usuAutenticado")%>>
				<input type="hidden" id="destinatario" name="destinatario"
					value="ADefinirUsuario"> <br>
				<br> <br>
				<input class="textarea" type="text" id="texto_mensagem"
					placeholder="Mensagem" name="texto_mensagem"
					style="position: fixed; width: 70%; bottom: -50px; right: 150px; height: 35px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); font-family: Helvetica; background: #ffffff; text-align: center; color: black;"
					required> <input type="submit" value="SALVAR"
					style="position: fixed; bottom: 12px; right: 50px; width: 90px; height: 50px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); background-image: linear-gradient(to bottom right, #2AF598, #08B3E5); font-weight: bold; font-family: Helvetica; text-align: center; border-radius: 13px; border-width: 0px; cursor: pointer; color: white;">
			</form>
			<br> <br>
		</div>
	</div>
</body>

</html>