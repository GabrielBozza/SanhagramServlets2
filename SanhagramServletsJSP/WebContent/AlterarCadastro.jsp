<%@ page import="bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar</title>
</head>
<script>
function formatar(mascara, documento){
  var i = documento.value.length;
  var saida = mascara.substring(0,1);
  var texto = mascara.substring(i)
  
  if (texto.substring(0,1) != saida){
            documento.value += texto.substring(0,1);
  }
  
}
</script>
<body>
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<br>
<a href="UsuarioControlador?acao=pagInicial" style="margin-left:100px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-home"></i> Início</a>
<a id="MensagensRecentes" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lis&grupo=cadgrupo"><i class="fa fa-fw fa-pencil"></i> Criar Grupo</a>
<a href="UsuarioControlador?acao=lis&grupo=lisusuarios" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-user"></i> Usuários</a>
<a href="UsuarioControlador?acao=lis&grupo=lisgrupos" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-users"></i> Grupos</a>
<a href="UsuarioControlador?acao=cad" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"> <i class="fa fa-fw fa-save"></i> Cadastro</a>
<a href="autenticador" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-sign-out"></i> Sair</a><br><br>
	<%
		Usuario usu = (Usuario)request.getAttribute("usuario");
	%>
<form action="UsuarioControlador?acao=alterar" method="post" style="margin-top:60px;">
		<input type="hidden" name="acao" value="salvar">
		
		<label style="font-size:18px;font-family: Helvetica;margin-left:580px;">Nome:</label> <br>
		<input type="text" name="nome" value='<%=usu.getNome()%>' style="height:35px;font-size:18px;font-family: Helvetica; background: #deddd9; width: 20%; text-align: center; margin-left: 580px; color: black;border-radius: 6px; border-width: 0px;" readonly><br>
		<br><label style="font-size:18px;font-family: Helvetica;margin-left:580px;">Email:</label> <br>
		<input type="text" name="email" value='<%=usu.getEmail()%>' style="height:35px;font-size:18px;font-family: Helvetica; background: #deddd9; width: 20%; text-align: center; margin-left: 580px; color: black;border-radius: 6px; border-width: 0px;"><br>
		<br><label style="font-size:18px;font-family: Helvetica;margin-left:580px;">Senha:</label> <br>
		<input type="password" name="senha" value='<%=usu.getSenha()%>' style="height:35px;font-size:18px;font-family: Helvetica; background: #deddd9; width: 20%; text-align: center; margin-left: 580px; color: black;border-radius: 6px; border-width: 0px;"> <br>
		<br><label style="font-size:18px;font-family: Helvetica;margin-left:580px;">Data de Nascimento</label><br>
		<input type="text" name="data" OnKeyPress="formatar('##/##/####', this)" style="height:35px;font-size:18px;font-family: Helvetica; background: #deddd9; width: 20%; text-align: center; margin-left: 580px; color: black;border-radius: 6px; border-width: 0px;" required><br>
		
		<br><input type="submit" value="SALVAR" style="font-family: Helvetica; background: #deddd9; text-align: center; margin-left: 680px; border-radius: 6px; border-width: 0px; width: 90px; height: 35px; cursor: pointer;">
	</form>

</body>
</html>