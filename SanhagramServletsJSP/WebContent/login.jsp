<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página - Login</title>
</head>
<body>
<fieldset>
<h1>Página de Login!</h1>
<form action="autenticador" method="post">
<label>Nome de usuário:</label><br>
<input type="text" name="nome"><br>
<label>Senha:</label><br>
<input type="password" name="senha"><br>
<input type="submit" value="Logar"><br>
</form>
</fieldset>
</body>
</html>