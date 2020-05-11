<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="./styles/style2.css">
    <script src="./js/scriptregister.js"></script>
</head>

<body background="./img/img2.jpg">

    <div class="top1"></div>

    <div class="middle">

        <div class="top">
            <img class="displayed" src="./img/logoteste.jpeg" alt="Otario">
        </div>

        <div class="mid2">
			<jsp:include page="cabecalho.jsp"></jsp:include>
			
            <form class="form2" action="salvarCadastro.jsp" method="post">
                <input class="textarea" placeholder="Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" type="text"
                    id="Email" name="email">
                <input class="textarea" placeholder="Senha" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'" type="password"
                    id="Password" name="senha">
                <input class="textarea" placeholder="Nome de usuario" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nome de usuario'"
                    type="text" id="Username" name="nome">
                <input class="textarea" placeholder="Data de nascimento" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Data de nascimento'"
                    type="text" name="data" OnKeyPress="formatar('##/##/####', this)">
              
                <input class="bt-login" type="submit" value="CADASTRE-SE">

            </form>

        </div>

    </div>
   
</body>

</html>