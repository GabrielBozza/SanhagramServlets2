<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="jdbc.UsuarioDAO"%>
<%@ page import="beans.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salvar Cadastro</title>
</head>
<body>
	<%
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String data = request.getParameter("data");

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(format.parse(data).getTime());
			
			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setDatanasc(date);

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.cadastro(usuario);
		}

		catch (Exception e) {
			System.out.println(e);

		}
	%>
	<h2>Salvo com sucesso!</h2>
</body>
</html>