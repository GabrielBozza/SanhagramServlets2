<%@page import="java.util.List"%>
<%@page import="bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>

<%
	//List<Usuario> lista = usuDAO.buscarTodos(usu);
	List<Usuario> listaResultado = (List<Usuario>) request.getAttribute("lista");
%>
<script>
	localStorage.setItem("modoApagarMensagens", "naoApagar");
</script>
<table border="1"
	style="font-family: Helvetica; font-size: 18px; margin-left: 270px; margin-top: 60px; padding: 0px;">
	<tr bgcolor="#eaeaea" style="height: 35px; padding: 5px;">
		<th style="padding: 10px;">Nome</th>
		<th style="padding: 10px;">Email</th>
		<th style="padding: 10px;">Senha</th>
		<th style="padding: 10px;">Data de Nascimento</th>
		<th style="padding: 10px;">Excluir</th>
		<th style="padding: 10px;">Alterar</th>
	</tr>
	<%
		for (Usuario u : listaResultado) {
	%>
	<tr style="height: 25px; padding: 5px;">
		<th style="padding: 10px;"><%=u.getNome()%></th>
		<th style="padding: 10px;"><%=u.getEmail()%></th>
		<th style="padding: 10px;"><%=u.getSenha()%></th>
		<th style="padding: 10px;"><%=u.getDatanasc()%></th>
		<th style="padding: 10px;"><a
			href="UsuarioControlador?acao=ex&nomeusu=<%=u.getNome()%>"
			style="color: red; text-decoration: none;">Excluir</a>
		<th style="padding: 10px;"><a
			href="UsuarioControlador?acao=alt&nomeusu=<%=u.getNome()%>"
			style="color: blue; text-decoration: none;">Alterar</a>
	</tr>
	<%
		}
	%>
</table>
<br>
<div style="width:100%;">
	<div style="float:left;width:40%;">
		<table border="1" id="tabelaUsuarios"
		style="font-family: Helvetica; font-size: 18px;  margin-top: 60px; padding: 0px;">
			<tr bgcolor="#eaeaea" style="height: 35px; padding: 5px;">
			<th style="padding: 10px;">Nome</th>
			<th style="padding: 10px;">Email</th>
			<th style="padding: 10px;">Adicionar</th>
			</tr>
		<%
			for (Usuario u : listaResultado) {
		%>
			<tr style="height: 25px; padding: 5px;">
			<th style="padding: 10px;"><%=u.getNome()%></th>
			<th style="padding: 10px;"><%=u.getEmail()%></th>
			<th style="padding: 10px;"><a href="#ADDGRUPO"
		 onclick="addGrupo('<%=u.getNome()%>')"
			style="color: green; text-decoration: none;">+</a>
			</tr>
		<%
			}
		%>
		</table>
	</div>
	
	
	<div style="float:right;width:60%;height:550px;border-radius:13px;background:#8ee6de;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);">
	<div style="box-shadow: 2px 2px 5px rgba(0,0,0,0.2);background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);color:white;font-weight:600;font-family: Helvetica; font-size: 22px;text-align:center;margin-top: 10px;margin-bottom: 10px;padding:15px;width:95%;margin-left:7px;border-radius:13px;">Novo Grupo</div>
		<table id="tabelaNovoGrupo" border="0"
		style="color:black;font-family: Helvetica; font-size: 18px;  margin-top: 0px; margin-left:50px;padding: 0px;table-layout:fixed;width:560px;background:white;border-radius:13px;">
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
			<tr style=" padding: 5px;height:35px; ">
			<td style="width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			<td style="padding: 10px;width:140px;overflow:hidden;white-space:nowrap;text-align:center;"></td>
			</tr>
		</table>
			<form autocomplete="off" action="UsuarioControlador?acao=cadastrarGrupo" method="post">
				<input type="hidden" id="listaNovoGrupo" name="listaNovoGrupo" value="">			
				<br><input class="textarea" type="text" id="texto_mensagem"
					placeholder="Nome do grupo" name="nome_grupo"
					style="margin-left:50px;border:0px;border-radius:13px;position:relative; width: 78%;height:50px;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);font-size:16px;font-family: Helvetica; background: #ffffff;text-align: center;color: black;"
					required> <input type="submit" value="CRIAR"
					style="position:relative;width: 90px; height: 50px;box-shadow: 2px 2px 5px rgba(0,0,0,0.2);background-image: linear-gradient(to bottom right, #2AF598, #08B3E5);font-weight:bold;font-family: Helvetica;text-align: center;border-radius: 13px; border-width: 0px;cursor: pointer;color:white;"
					>
			</form>
	</div>
</div>
	<script>
	function addGrupo(novoUsuario) {
		var tablegrupo = document.getElementById("tabelaNovoGrupo");
		var tableusuarios = document.getElementById("tabelaUsuarios");
		
			for (var i = 0, row; row = tablegrupo.rows[i]; i++) {
			   	for (var j = 0, col; col = row.cells[j]; j++) {
			   		if(col.innerHTML==''){
			   			col.innerHTML=novoUsuario;
			   			document.getElementById("listaNovoGrupo").value=document.getElementById("listaNovoGrupo").value+novoUsuario+"|";
			   			i=8;//tabela vazia tem 9 linhas (0<=i<9)
			   			break;
			   		}
			   	}	  
			}
			
			
			for (var i = 0, row; row = tableusuarios.rows[i]; i++) {
			   	for (var j = 0, col; col = row.cells[j]; j++) {
			   		if(col.innerHTML==novoUsuario){
			   			row.cells[j+2].style.visibility="hidden";
			   		}
			   	}	  
			}
	}

	</script>
<br><br>
</body>
</html>