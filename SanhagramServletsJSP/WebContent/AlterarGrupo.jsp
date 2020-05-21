<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.stream.Collectors"%>
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
<title>Insert title here</title>
</head>
<body>
	<script>
		document.body.style.zoom = (window.screen.width / 1536);
	</script>

	<%
		List<String> listaUsuariosGrupo = (List<String>) request.getAttribute("listaUsuariosGrupo");
		List<String> listaAmigos = (List<String>) request.getAttribute("listaAmigos");
		List<Usuario> listaTodosUsuariosObjeto = (List<Usuario>) request.getAttribute("listaTodosUsuarios");
		List<String> listaTodosUsuarios = new ArrayList<String>();
		for(Usuario u : listaTodosUsuariosObjeto){
			listaTodosUsuarios.add(u.getNome());
		}
		
		Set<String> todosUsuarios = new HashSet<String>(listaTodosUsuarios);
		Set<String> usuariosDoGrupo = new HashSet<String>(listaUsuariosGrupo);
		todosUsuarios.removeAll(usuariosDoGrupo);//RETORNA UMA LISTA SOH COM OS USUARIOS QUE AINDA NAO PERTENCEM AO GRUPO
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

			<table border="0"
				style="width:300px;font-family: Helvetica; font-size: 19px; margin-left: 500px; margin-top: 30px; padding: 5px; border-radius: 13px; background: #ffffff; text-align: center; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
				<caption style="margin-bottom:10px;font-weight:bold;font-family: Helvetica; font-size: 21px;">Grupo <%=request.getAttribute("nomeGrupoAtual")%></caption>
				<tr style="height: 35px; padding: 5px; text-align: center;">
					<th style="padding: 10px;">Nome</th>
					<th style="padding: 10px;"></th>
				</tr>
				<%
					for (String u : usuariosDoGrupo) {
				%>
				<tr style="height: 25px; padding: 5px;">
					<td style="padding: 10px;text-align:left;"><%=u%></td>
					<td style="padding: 10px;"><a onclick="feedbackRemoveuUsuario('<%=u%>','<%=request.getAttribute("nomeGrupoAtual")%>')"
						href="UsuarioControlador?acao=SairGrupo&nomeGrupo=<%=request.getAttribute("nomeGrupoAtual")%>&nomeUsuario=<%=u%>"
						style="color: red; text-decoration: none;">Remover</a>
				</tr>
				<%
					}
				%>
			</table>

			<table border="0"
				style="width:300px;font-family: Helvetica; font-size: 19px; margin-left: 500px; margin-top: 30px; padding: 5px; border-radius: 13px; background: #ffffff; text-align: center; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);">
				<tr style="height: 35px; padding: 5px; text-align: center;">
					<th style="padding: 10px;">Nome</th>
					<th style="padding: 10px;"></th>
				</tr>
				<%
					for (String u : todosUsuarios) {
				%>
				<tr style="height: 25px; padding: 5px;">
					<td style="padding: 10px; text-align:left;"><%=u%></td>
					<td style="padding: 10px;"><a onclick="feedbackAddUsuario('<%=u%>','<%=request.getAttribute("nomeGrupoAtual")%>')" href="UsuarioControlador?acao=AdicionarAoGrupo&nomeGrupo=<%=request.getAttribute("nomeGrupoAtual")%>&nomeUsuario=<%=u%>"
						style="color: blue; text-decoration: none;">Adicionar</a>
				</tr>
				<%
					}
				%>
			</table>
			
			<script>
				function feedbackAddUsuario(a,b){
					alert(a + " adicionado ao grupo "+b);
				}
				function feedbackRemoveuUsuario(a,b){
					alert(a + " removido do grupo "+b);
				}
			</script>

			<br> <br>
		</div>
	</div>
</body>
</html>