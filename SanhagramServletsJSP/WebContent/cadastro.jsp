<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="./styles/style2.css">
    <script src="./js/scriptregister.js"></script>
</head>

<body>
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
	<a href="UsuarioControlador?acao=pagInicial" style="margin-left:20px;font-size:18px;color:white;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-home"></i> Início</a>
    <div class="middle">

        <div class="top">
            <img class="displayed" src="./img/logoteste.jpeg" alt="Otario">
        </div>
        
            <form action="UsuarioControlador?acao=cadastrar" method="post">
            <div class="mid2">
                <input class="textarea" placeholder="Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" type="text"
                    id="Email" name="email">
                <br><br><input class="textarea" placeholder="Senha" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'" type="password"
                    id="Password" name="senha">
                <br><br><input class="textarea" placeholder="Nome de usuario" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nome de usuario'"
                    type="text" id="Username" name="nome">
                <br><br><input class="textarea" placeholder="Data de nascimento" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Data de nascimento'"
                    type="text" name="data" OnKeyPress="formatar('##/##/####', this)">
              
                <br><br><input class="bt-login" type="submit" value="CADASTRAR">
     		</div>
            </form>

   

    </div>
   
</body>

</html>