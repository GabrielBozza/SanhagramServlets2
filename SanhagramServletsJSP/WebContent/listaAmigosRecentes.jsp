<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href=".\styles\style3.css">
    <link href=".\styles\fontawesome-free-5.12.1-web\css\all.css" rel="stylesheet">
    <script src=".\js\scriptelaprincip.js"></script>
	<meta charset="ISO-8859-1">
<title>Lista Amigos Recentes</title>
</head>
<body>
<script>localStorage.setItem("conversa","verconversa0");
 			localStorage.setItem("i","0");
 	</script>
<%

//List<Usuario> lista = usuDAO.buscarTodos(usu);
List<String> listaResultado = (List<String>)request.getAttribute("lista");
%>
    <div class="div1">

        <div class="top1">
            <p class="ttop1">Boards</p>
        </div>

        <div class="bot1">

            <div class="bot11" id="style-2">
                <input class="textarea" placeholder="Find boards by name..." onfocus="this.placeholder = ''" onblur="this.placeholder = 'Find boards by name...'"
                    type="text" id="conversaslado" name="conversaslado" onkeyup="selecionatext(this)">

                <div class="barralateral" id="conversas">

                    <div class="conversa">
                        <div class="textocorpo" id="nomeusuario"><% String a = request.getParameter("nome");
                        System.out.println(a);%><%=a%></div>
                        
                        <script>   
                        	if(document.getElementById('nomeusuario').innerHTML!='null'){//PERDEU A REFERENCIA AO USUARIO
                        		localStorage.setItem("nomeusuario",document.getElementById('nomeusuario').innerHTML);	
                        	}
                        	else{//PERDEU A REFERENCIA AO USUARIO
                        		document.getElementById('nomeusuario').innerHTML=localStorage.getItem("nomeusuario");
                        	}
                        </script>
                    </div>

					<div class="tabelaconversas">
						<table border="0">
 							<tr bgcolor="#ffffff">
 							<th>Ver conversa</th>
 							</tr>
						<%
							for(String s:listaResultado){
						%>
							<tr>
 							
 							<th><a id="verconversa"><%=s%></a>
 							<script>
 								localStorage.setItem("conversa",localStorage.getItem("conversa").substring(0,localStorage.getItem("conversa").length-1)+localStorage.getItem("i"));
 								document.getElementById("verconversa").id=localStorage.getItem("conversa");
 								document.getElementById(localStorage.getItem("conversa")).href="UsuarioControlador?acao=lismsgm&remetente="+localStorage.getItem('nomeusuario')+"&destinatario=<%=s%>";
 								localStorage.setItem("i",(parseInt(localStorage.getItem("i"))+1).toString());		
 							</script>
 							</tr>
						<%
							}
						%>
						</table>
					
					</div>

                </div>

            </div>

        </div>

    </div>

    <div class="div2">

        <div class="top2" id="header">

            <div class="nomeconversatop2" id="nomeconversa">
                Roberta
            </div>
        </div>
        <jsp:include page="cabecalho.jsp"></jsp:include>
        
        <div><br><br><br><br><br><br></div>

        <div class="bot2" id="escrever">
            <form autocomplete="off" action="enviar_mensagem.jsp" method="post">
            	<input type="hidden" id="remetente" name="remetente">
            	<script> document.getElementById('remetente').value=document.getElementById('nomeusuario').innerHTML</script>
            	<input class = "textarea" placeholder = "Destinatario" type="text" id="destinatario" name="destinatario" required> 
                <input class = "textarea" type="text" id="texto_mensagem" name="texto_mensagem" required>
                <input type="submit" value="ENVIAR ">
            </form>
        </div>
    </div>
</body>

</html>