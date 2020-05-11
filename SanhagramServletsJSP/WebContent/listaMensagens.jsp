<%@page import="java.util.List"%>
<%@page import="bean.Mensagem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href=".\styles\style3.css">
    <link href=".\styles\fontawesome-free-5.12.1-web\css\all.css" rel="stylesheet">
    <script src=".\js\scriptelaprincip.js"></script>
    <meta charset="ISO-8859-1">
	<title>Lista mensagens</title>
</head>
<body>
<%

//List<Usuario> lista = usuDAO.buscarTodos(usu);
List<Mensagem> listaResultado = (List<Mensagem>)request.getAttribute("lista");
%>
    <div class="div1">

        <div class="top1">
            <p class="ttop1">Conversas</p>
        </div>

        <div class="bot1">

            <div class="bot11" id="style-2">
                <input class="textarea" placeholder="Procurar..." onfocus="this.placeholder = ''" onblur="this.placeholder = 'Procurar...'"
                    type="text" id="conversaslado" name="conversaslado" onkeyup="selecionatext(this)">

                <div class="bot12">
                    <i id="estrelinha" class="far fa-star" onclick="selecionastarred()"></i>

                    <div class="starred">Favoritos

                        <div class="minimiza" onclick="minimizatela(this)">-</div>

                    </div>
                </div>

                <div class="barralateral" id="conversas">

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\img5.png" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\fundobranco.png" alt="Otario">
                        </div>

                        <div class="textocorpo" id="nomeusuario"><% String a = request.getParameter("nome");
                        System.out.println(a);%><%=a%></div>
                        
                        <script>   
                        	if(document.getElementById('nomeusuario').innerHTML!='null'){
                        		localStorage.setItem("nomeusuario",document.getElementById('nomeusuario').innerHTML);	
                        	}
                        	else{//PERDEU A REFERENCIA AO USUARIO
                        		document.getElementById('nomeusuario').innerHTML=localStorage.getItem("nomeusuario");
                        	}
                        </script>

                    </div>

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\img5.png" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\fundobranco.png" alt="Otario">
                        </div>

                        <div class="textocorpo">Ricardo IME</div>

                        <div class="textocorpo1"></div>

                    </div>

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\img5.png" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\fundobranco.png" alt="Otario">
                        </div>

                        <div class="textocorpo">Carlos IME</div>

                        <div class="textocorpo1"></div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <div class="div2">

        <div class="top2" id="header">

            <div class="imgconversatop2">
                <img class="conversatop2" src=".\img\img5.png" alt="Otario">
            </div>

            <div class="nomeconversatop2" id="titulo">
                Roberta FGV
            </div>

        </div>
        <jsp:include page="cabecalho.jsp"></jsp:include>
        
        
        <div class="mid2" id="chat">
        <br>
        	<table border="0">
 				<tr bgcolor="#f8fbfd">
 				<th></th>
 				<th></th>
 				<th></th>
 				<th></th>
 				<th></th>
 				<th></th>
 				<th></th>
 				</tr>
			<%
				for(Mensagem m:listaResultado){
			%>
				<tr>
				<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
				<td style="color:#f8fbfd"><b id="destino"><%=m.getDestinatario()%></b>
 				<td><a id="fonte" style="font-family:Helvetica;font-style:normal;text-decoration:none;"><%=m.getRemetente()%></a>
 				<td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>
 				<td style="font-family:Helvetica;font-style:normal;width:200px;"><%=m.getTexto_mensagem() %></td>
 				<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
 				<td><a href="UsuarioControlador?acao=exmsgm&idmensagem=<%=m.getIdmensagem() %>" style="font-family:Helvetica;font-style:normal;color:red;text-decoration:none;" id="excluir">X</a>
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
 				<tr></tr>
 				<tr></tr>
 				<tr></tr>
 				<script>
 				
 					if(document.getElementById('fonte').innerHTML!=localStorage.getItem("nomeusuario")){
 						localStorage.setItem("destinatario",document.getElementById('fonte').innerHTML);
 						document.getElementById('titulo').innerHTML=document.getElementById('fonte').innerHTML;
 						document.getElementById('excluir').innerHTML='';
 						document.getElementById('fonte').style["color"]="#0da1b5";
 						document.getElementById('excluir').id="excluir1";
 						document.getElementById('fonte').id="fonte1";
 					}
 					else{
 						localStorage.setItem("destinatario",document.getElementById('destino').innerHTML);
 						//document.getElementById('titulo').innerHTML=document.getElementById('destino').innerHTML;
 						document.getElementById('fonte').innerHTML='Você';
 						document.getElementById('fonte').style["color"]="#6ebf17";
 						document.getElementById('excluir').id="excluir1";
 						document.getElementById('destino').id="destino1";
 						document.getElementById('fonte').id="fonte1";
 					}

 				</script>
 				
			<%
				}
			%>
			</table>
			<br>
			
			    <form autocomplete="off" action="enviar_mensagem.jsp" method="post">
            	<input type="hidden" id="remetente" name="remetente">
            	<script> document.getElementById('remetente').value=localStorage.getItem("nomeusuario")</script>
            	<input type="hidden" id="destinatario" name="destinatario">
            	<script> document.getElementById('destinatario').value=localStorage.getItem("destinatario")</script>
                <input class = "textarea" type="text" id="texto_mensagem" name="texto_mensagem" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:-80px;color:black;" required>
                <br><input type="submit" value="ENVIAR" style="font-family:Helvetica;background:#deddd9;text-align:center;margin-left:-70px;border-radius:6px;border-width:0px;width:90px;height:35px;">
                <br><br><br>
        </div>
    </div>
</body>

</html>