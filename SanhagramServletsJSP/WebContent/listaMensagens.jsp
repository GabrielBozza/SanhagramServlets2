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
            <p class="ttop1">Boards</p>
        </div>

        <div class="bot1">

            <div class="bot11" id="style-2">
                <input class="textarea" placeholder="Find boards by name..." onfocus="this.placeholder = ''" onblur="this.placeholder = 'Find boards by name...'"
                    type="text" id="conversaslado" name="conversaslado" onkeyup="selecionatext(this)">

                <div class="bot12">
                    <i id="estrelinha" class="far fa-star" onclick="selecionastarred()"></i>

                    <div class="starred">Starred Boards

                        <div class="minimiza" onclick="minimizatela(this)">-</div>

                    </div>
                </div>

                <div class="barralateral" id="conversas">

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\teste1.jpg" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\teste1.jpg" alt="Otario">
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

                        <i id="estrelinha1" class="far fa-star estrelinha1 l" onclick="selecionastarred1(this)"></i>
                        <i class="fas fa-circle"></i>

                    </div>

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\teste2.jpg" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\teste2.jpg" alt="Otario">
                        </div>

                        <div class="textocorpo">Ricardo IME</div>

                        <div class="textocorpo1">IME inc.</div>

                        <i id="estrelinha1" class="far fa-star estrelinha1 l" onclick="selecionastarred1(this)"></i>
                        <i class="fas fa-circle"></i>

                    </div>

                    <div class="conversa" h="0" p="0">

                        <div class="imgconversa">
                            <img class="iconegrupo" src=".\img\teste2.jpg" alt="Otario">
                        </div>

                        <div class="corpoconversa desactive" onclick="mostrarconversa(this)">
                            <img class="iconegrupo1" src=".\img\teste2.jpg" alt="Otario">
                        </div>

                        <div class="textocorpo">Carlos IME</div>

                        <div class="textocorpo1">IME inc.</div>

                        <i id="estrelinha1" class="far fa-star estrelinha1 l" onclick="selecionastarred1(this)"></i>
                        <i class="fas fa-circle"></i>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <div class="div2">

        <div class="top2" id="header">

            <div class="imgconversatop2">
                <img class="conversatop2" src=".\img\teste1.jpg" alt="Otario">
            </div>

            <div class="nomeconversatop2" id="titulo">
                Roberta FGV
            </div>

            <i class="fas fa-plus"></i>

        </div>
        <jsp:include page="cabecalho.jsp"></jsp:include>
        
        
        <div class="mid2" id="chat">
        <br>
        	<table border="0">
 				<tr bgcolor="#ffffff">
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
				<th>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</th>
				<th style="color:#f8fbfd"><b id="destino"><%=m.getDestinatario()%></b>
 				<th><a id="fonte" style="font-family:Helvetica;font-style:normal;"><%=m.getRemetente()%></a>
 				<th>&emsp;&emsp;&emsp;&emsp;&emsp;</th>
 				<th style="font-family:Helvetica;font-style:normal;"><%=m.getTexto_mensagem() %></th>
 				<th>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</th>
 				<th><a href="UsuarioControlador?acao=exmsgm&idmensagem=<%=m.getIdmensagem() %>" style="font-family:Helvetica;font-style:normal;">Excluir</a>
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
 						document.getElementById('fonte').id="fonte1";
 					}
 					else if (document.getElementById('destino').innerHTML!=localStorage.getItem("nomeusuario")){
 						localStorage.setItem("destinatario",document.getElementById('destino').innerHTML);
 						document.getElementById('titulo').innerHTML=document.getElementById('destino').innerHTML;
 						document.getElementById('destino').id="destino1";
 					}
 				</script>
 				
			<%
				}
			%>
			</table>
			<br><br>
			
			    <form autocomplete="off" action="enviar_mensagem.jsp" method="post">
            	<input type="hidden" id="remetente" name="remetente">
            	<script> document.getElementById('remetente').value=localStorage.getItem("nomeusuario")</script>
            	<input type="hidden" id="destinatario" name="destinatario">
            	<script> document.getElementById('destinatario').value=localStorage.getItem("destinatario")</script>
                <input class = "textarea" type="text" id="texto_mensagem" name="texto_mensagem" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:250px;color:black;" required>
                <input type="submit" value="ENVIAR" style="font-family:Helvetica;background:#deddd9;text-align:center;margin-left:510px;border-radius:6px;border-width:0px;width:90px;height:35px;">
                <br><br><br>
        </div>
    </div>
</body>

</html>