<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./styles/style1.css">
    <meta charset="ISO-8859-1">
	<title>Página - Login</title>
</head>

<body background="./img/img.jpg">

    <div class="top1"></div>

    <div class="middle">

        <div class="top">
            <img class="displayed" src="./img/logoteste.jpeg" alt="Otario">
        </div>

        <div class="mid1">
            <input class="bt-face" type="submit" onclick="window.location.href = 'https://pt-br.facebook.com/'" value="LOGAR COM FACEBOOK">
        </div>

        <div class="bottom">

            <form autocomplete="off" action="autenticador" method="post">

                <div class="mid2">
                    <input class = "textarea" placeholder = "Nome de usuario" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nome de usuario'" type="text" id="Login" name="nome"> 
                    <input class = "textarea" placeholder = "Senha"  onfocus="this.placeholder = ''" onblur="this.placeholder = 'Senha'" type="password" id="Password" name="senha">
                </div>

                <div class="mid3">

                        <div class="txt1">REMEMBER ME</div>

                        <div class="switch__container">
                            <input id="switch-shadow" class="switch switch--shadow" type="checkbox" name="Remember">
                            <label for="switch-shadow"></label>
                        </div>

                </div>

                <div class="bottom1">
                    <input class="bt-login" type="submit" value="LOGIN ">
                </div>

            </form>

        </div>

        <div class="bottom2">
      
            <a class="text" href="./poo/register.html">SIGN UP</a>
            <a class="text" href="www.google.com">RESET PASSWORD</a>
            <a class="text" href="www.google.com">SETTINGS</a>
             
        </div>

    </div>
   
</body>
</html>