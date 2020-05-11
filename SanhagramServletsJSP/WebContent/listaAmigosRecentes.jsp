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
            <p class="ttop1">Conversas</p>
        </div>

        <div class="bot1">

            <div class="bot11" id="style-2">
                <input class="textarea" placeholder="Procurar..." onfocus="this.placeholder = ''" onblur="this.placeholder = 'Procurar...'"
                    type="text" id="conversaslado" name="conversaslado" onkeyup="selecionatext(this)"><br><br>

                <div class="barralateral" id="conversas">

                    <div class="conversa">
                             <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\img4.png" alt="Otario">
                        </div>
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
 							<th>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</th>
 							</tr>
						<%
							for(String s:listaResultado){
						%>
							<tr bgcolor='#deddd9' height='45px'>
 							
 							<th><a id="verconversa" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><%=s%></a>
 							<script>
 								localStorage.setItem("conversa",localStorage.getItem("conversa").substring(0,localStorage.getItem("conversa").length-1)+localStorage.getItem("i"));
 								document.getElementById("verconversa").id=localStorage.getItem("conversa");
 								document.getElementById(localStorage.getItem("conversa")).href="UsuarioControlador?acao=lismsgm&remetente="+localStorage.getItem('nomeusuario')+"&destinatario=<%=s%>";
 								localStorage.setItem("i",(parseInt(localStorage.getItem("i"))+1).toString());		
 							</script>
 							</tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
 							<tr></tr>
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
            <div class="imgconversatop2">
                <img class="conversatop2" src=".\img\img4.png" alt="Otario">
            </div>

            <div class="nomeconversatop2" id="topuser">
                Roberta FGV
            </div>
            
            <script>                        	
            if(document.getElementById('nomeusuario').innerHTML!='null'){	
        		document.getElementById('topuser').innerHTML=document.getElementById('nomeusuario').innerHTML;
        	}
        	else{//PERDEU A REFERENCIA AO USUARIO
        		document.getElementById('topuser').innerHTML=localStorage.getItem("nomeusuario");
        	}</script>

        </div>
        <jsp:include page="cabecalho.jsp"></jsp:include>
        
        <div></div>

        <div class="mid2" id="chat">
        <br><br>
            <form autocomplete="off" action="enviar_mensagem.jsp" method="post">
            	<input type="hidden" id="remetente" name="remetente">
            	<script> document.getElementById('remetente').value=document.getElementById('nomeusuario').innerHTML</script>
            	<input class = "textarea" placeholder = "Destinatario" type="text" id="destinatario" name="destinatario" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:-80px;color:black;" required> 
                <br><input class = "textarea" type="text" id="texto_mensagem" name="texto_mensagem" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:-80px;color:black;" required>
                <br><input type="submit" value="ENVIAR" style="font-family:Helvetica;background:#deddd9;text-align:center;margin-left:-70px;border-radius:6px;border-width:0px;width:-70px;height:35px;">
            </form>
        </div>
    </div>
</body>

</html>