<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./styles/style1.css">
    <meta charset="ISO-8859-1">
	<title>Página - Login</title>
</head>

<body>
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
    <div class="middle">

        <div class="top" >
            <img class="displayed" src="./img/logoteste.jpeg" alt="Otario">
        </div>

        <div class="bottom">

            <form autocomplete="off" action="autenticador" method="post">

                <div class="mid2">
                    <br><input class = "textarea" placeholder = "Nome de usuario" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nome de usuario'" type="text" id="Login" name="nome" required> 
                    <br><br><input class = "textarea" placeholder = "Senha"  onfocus="this.placeholder = ''" onblur="this.placeholder = 'Senha'" type="password" id="Password" name="senha" required>
                	<input class="bt-login" type="submit" value="LOGIN ">
                </div>

            </form>

        </div>

    </div>
   
</body>
</html>