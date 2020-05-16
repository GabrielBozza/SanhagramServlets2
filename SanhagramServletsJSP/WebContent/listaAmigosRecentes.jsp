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
<%
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
                        <div class="textocorpo" id="nomeusuario"><%=request.getSession().getAttribute("usuAutenticado")%></div>
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
 							
 							<th><a id="verconversa" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getSession().getAttribute("usuAutenticado")%>&destinatario=<%=s%>"><%=s%></a>
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

            <div class="nomeconversatop2" id="topuser"><%=request.getSession().getAttribute("usuAutenticado")%></div>
           
        </div>
        <%if(request.getSession().getAttribute("usuAutenticado").equals("admin")){ %>
        	<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
        <%}else{ %>
        	<jsp:include page="cabecalho.jsp"></jsp:include>
        <%}%>	
        <div></div>

        <div class="mid2" id="chat">
        <br><br>
            <form autocomplete="off" action="enviar_mensagem.jsp" method="post">
            	<input type="hidden" id="remetente" name="remetente" value=<%=request.getSession().getAttribute("usuAutenticado")%>>
            	<input class = "textarea" placeholder = "Destinatario" type="text" id="destinatario" name="destinatario" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:-80px;color:black;" required> 
                <br><input class = "textarea" type="text" id="texto_mensagem" placeholder="Mensagem" name="texto_mensagem" style="font-family:Helvetica;background:#deddd9;width:50%;text-align:center;margin-left:-80px;color:black;" required>
                <br><input type="submit" value="ENVIAR" style="font-family:Helvetica;background:#deddd9;text-align:center;margin-left:-70px;border-radius:6px;border-width:0px;width:90px;height:35px;cursor:pointer;">
            </form>
        </div>
    </div>
</body>

</html>