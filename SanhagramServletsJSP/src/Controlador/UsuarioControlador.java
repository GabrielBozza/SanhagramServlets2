package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.Usuario;
import jdbc.UsuarioDAO2;
import bean.Mensagem;
import jdbc.MensagemDAO;

/**
 * Servlet implementation class UsuarioControlador
 */
@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// AQUI RECEBE MENSAGENS DAS PAGINAS JSP E DECIDE O QUE FAZER

		Usuario usu = new Usuario();// NOVO OBJETO BEAN.USUARIO
		Mensagem mensagem = new Mensagem();// NOVO OBJETO BEAN.MENSAGEM
		
		String acao = request.getParameter("acao");// PARAMETRO PASSADO P/ INDICAR O QUE DESEJAM FAZER
		String dispositivo = request.getParameter("dispositivo");
		
		UsuarioDAO2 usuDAO = new UsuarioDAO2();// NOVO OBJETO USUARIODAO
		MensagemDAO mensagemDAO = new MensagemDAO();// NOVO OBJETO MENSAGEMDAO

		if (request.getSession().getAttribute("usuAutenticado") == null && dispositivo.equals("desktop")) {// USUARIO SEM AUTENTICACAO NA VERSAO WEB

			RequestDispatcher saida = request.getRequestDispatcher("login.jsp");
			saida.forward(request, response);

		} else if (dispositivo.equals("desktop")){////*******************************************************DESKTOP************************************************************

			if (acao != null && acao.equals("lis")) {// ACAO LISTAR (3 POSSIBILIDADES - PARA 1-CADASTRO DE GRUPOS, 2-VER/ALTERAR USUARIOS, 3-VER/ALTERAR GRUPOS)

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");

				if (usuAutenticado.equals("admin")) {//AREA RESTRITA AO ADMIN

					if (request.getParameter("grupo").equals("cadgrupo")) {//PAGINA PARA CRIAR GRUPOS
						
						request.setAttribute("tipo", "cadgrupo");//ATRIBUTO PARA IDENTIFICAR A PAGINA
						
						List<Usuario> lista = usuDAO.buscarTodos();// LISTA COM TODOS OS USUARIOS
						request.setAttribute("lista", lista);
						
						RequestDispatcher saida = request.getRequestDispatcher("CadastroGrupo.jsp");
						saida.forward(request, response);
						
					} else if (request.getParameter("grupo").equals("lisusuarios")) {//PAGINA COM UMA LISTA DE TODOS OS USU
						
						request.setAttribute("tipo", "lisusuarios");//ATRIBUTO PARA IDENTIFICAR A PAGINA
						
						List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);//LISTA PARA POPULAR BARRA DE CONVERSAS RECENTES
						request.setAttribute("listaAmigos", listaAmigos);
						
						List<Usuario> lista = usuDAO.buscarTodos();
						request.setAttribute("lista", lista);
						
						RequestDispatcher saida = request.getRequestDispatcher("listaUsuarios.jsp");
						saida.forward(request, response);

					} else {//RETORNA PAG COM UMA LISTA DE TODOS OS GRUPOS E OPÇÃO PARA ALTERÁ-LOS
						
						request.setAttribute("tipo", "lisgrupos");
						
						List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
						request.setAttribute("listaAmigos", listaAmigos);
						
						List<Usuario> lista = usuDAO.buscarGrupos();
						request.setAttribute("lista", lista);
						
						RequestDispatcher saida = request.getRequestDispatcher("listaUsuarios.jsp");
						saida.forward(request, response);
					}
					
				} else {//USUARIO AUTENTICADO NAO EH O ADMIN - AREA PROIBIDA
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("lismsgm")) {//RETORNA A CONVERSA ENTRE O REMETENTE E DESTINATARIO SELECIONADOS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				
				request.getSession().setAttribute("destinatarioMsgm", destinatario);//PASSA ADIANTE A INFO DA CONVERSA
																				
				if (!usuAutenticado.equals(remetente)) {//SE TENTAR MUDAR O PARAMETRO DO REMETENTE PARA OUTRO USUARIO(NAO AUTENTICADO)
					
					RequestDispatcher saida = request.getRequestDispatcher("AcessoProibido.jsp");
					saida.forward(request, response);
					
				} else {//REMETENTE EH O USUARIO AUTENTICADO-OK
					
					List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
					request.setAttribute("listaAmigos", listaAmigos);

					if (usuDAO.tipoUsuario(destinatario).equals("grupo")) {//EH UMA CONVERSA EM GRUPO
						
						List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
						request.setAttribute("lista", lista);
						
						request.setAttribute("conversaAtual", destinatario);//PASSA A INFO ADIANTE
						
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
						
					} else {
						
						List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
						request.setAttribute("lista", lista);
						
						request.setAttribute("conversaAtual", destinatario);
						
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
					}

				}

			} else if (acao != null && acao.equals("lismsgmSalvas")) {//LISTA AS MENSAGENS QUE O USARIO SALVOU PARA PODER MANDAR PARA QUEM QUISER DEPOIS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String remetente = request.getParameter("remetente");
				String destinatario = "ADefinirUsuario";//USUARIO AINDA INDEFINIDO--EH UM USUARIO NAO ACESSIVEL 
				request.getSession().setAttribute("destinatarioMsgm", destinatario);
				
				if (!usuAutenticado.equals(remetente)) {
					
					RequestDispatcher saida = request.getRequestDispatcher("AcessoProibido.jsp");
					saida.forward(request, response);
					
				} else {
					
					List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
					request.setAttribute("listaAmigos", listaAmigos);
					
					List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
					request.setAttribute("lista", lista);
					
					request.setAttribute("conversaAtual", destinatario);
					
					RequestDispatcher saida = request.getRequestDispatcher("MensagensSalvas.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("pagInicial")) {//VAI PARA HOME

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado);
				request.setAttribute("lista", lista);
				
				RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
				saida.forward(request, response);

			} else if (acao != null && acao.equals("salvarMensagem")) {//SALVAR UM RASCUNHO PARA ESCOLHER A QUEM ENVIAR DEPOIS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado);
				request.setAttribute("lista", lista);
				
				RequestDispatcher saida = request.getRequestDispatcher("SalvarMensagem.jsp");
				saida.forward(request, response);

			} else if (acao != null && acao.equals("SairGrupo")) {//USUARIO DECIDE SAIR DO GRUPO POR CONTA PRÓPRIA OU ADMIN O RETIRA

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String nomeGrupo = request.getParameter("nomeGrupo");
				
				if (usuAutenticado.equals("admin") && !request.getParameter("nomeUsuario").equals("admin")) {//SE EH O ADMIN RETIRANDO OUTRO USUARIO
					
					String nomeUsuario = request.getParameter("nomeUsuario");
					mensagemDAO.SairGrupo(nomeUsuario, nomeGrupo);// RETIRA USUARIO DO GRUPO
					
					response.sendRedirect("UsuarioControlador?acao=alt&nomeusu=" + nomeGrupo + "&flagGrupo='1'&dispositivo=desktop");//VOLTA PARA PAG DE ALTERAR O GRUPO
					
				} else {// USUARIO QUE SAIU POR CONTA PROPRIA DO GRUPO
					
					mensagemDAO.SairGrupo(usuAutenticado, nomeGrupo);
					response.sendRedirect("UsuarioControlador?acao=pagInicial&dispositivo=desktop");// SAI DO GRUPO E VAI PARA A PAG INICIAL--PERDE CONVERSAS DO GRUPO
				}

			} else if (acao != null && acao.equals("AdicionarAoGrupo")) {//ADMIN ALTERANDO GRUPOS ADD USUARIOS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String nomeGrupo = request.getParameter("nomeGrupo");
				
				if (usuAutenticado.equals("admin")) {//SOH ADMIN PODE ALTERAR E CRIAR GRUPOS
					
					String nomeUsuario = request.getParameter("nomeUsuario");

					try {// ENVIA MENSAGENS AO GRUPO INDICANDO A ENTRADA DE SEUS MEMBROS INDIVIDUALMENTE
							// E UMA MENSAGEM PARA INDICAR QUE O USUARIO PERTENCE AO GRUPO

						Mensagem mensagem1 = new Mensagem();
						Mensagem mensagem2 = new Mensagem();

						mensagem1.setRemetente(nomeUsuario);
						mensagem1.setDestinatario(nomeGrupo);
						mensagem1.setTexto_mensagem("");// MENSAGEM QUE MANTEM A REFERENCIA DO USUARIO AO GRUPO E NAO
														// APARECE
														// NO CHAT

						MensagemDAO mensagemDAO1 = new MensagemDAO();
						mensagemDAO1.enviarParaGrupo(mensagem1);

						mensagem2.setRemetente(nomeGrupo);
						mensagem2.setDestinatario(nomeGrupo);
						mensagem2.setTexto_mensagem(nomeUsuario + " entrou no grupo");//SOH PARA AVISAR PARA TODOS Q O USUARIO FOI ADD AO GRUPO

						MensagemDAO mensagemDAO2 = new MensagemDAO();
						mensagemDAO2.enviarParaGrupo(mensagem2);

					}

					catch (Exception e) {
						System.out.println(e);

					}

					response.sendRedirect("UsuarioControlador?acao=alt&nomeusu=" + nomeGrupo + "&flagGrupo='1'&dispositivo=desktop");//VOLTA À PAG DE ALTERAR GRUPOS
					
				} else {// AREA DO ADMIN SOMENTE
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("exmsgm")) {//EXCLUIR MENSAGEM- USUARIO EXCLUI A MENSAGEM Q MANDOU

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String id = request.getParameter("idmensagem");// PASSA O PARAMETRO DO ID DA MENSAGEM A SER EXCLUIDA
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				
				mensagemDAO.deletar(Integer.parseInt(id));

				List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);//BARRA LATERAL
				request.setAttribute("listaAmigos", listaAmigos);

				if (usuDAO.tipoUsuario(destinatario) == "grupo") {
					
					List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
					request.setAttribute("lista", lista);
					
					request.setAttribute("conversaAtual", destinatario);
					
					RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
					saida.forward(request, response);
					
				} else {

					if (destinatario.equals("ADefinirUsuario")) {//EXCLUIU MENSAGEM SALVA COMO RASCUNHO--VOLTA PARA PAG DE MENSAGENS SALVAS
						
						String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente=" + remetente+"&dispositivo=desktop";
						response.sendRedirect(prox_pag);
						
					} else {
						
						List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
						request.setAttribute("lista", lista);
						
						request.setAttribute("conversaAtual", destinatario);
						
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
					}
				}

			} else if (acao != null && acao.equals("ex")) {//EXCLUIR USUARIO--SOH ADMIN PODE

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				
				if (usuAutenticado.equals("admin")) {
					
					String nome = request.getParameter("nomeusu");
					usu.setNome(nome);
					
					usuDAO.deletar(usu);//DELETA USUARIO 
					
					mensagemDAO.UsuarioRemovido(nome);//MODIFICA SEU NOME NAS MENSAGENS PARA 'NOME* (REMOVIDO)'
					
					response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios&dispositivo=desktop");
					
				} else {//AREA RESTRITA AO ADMIN
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("alt")) {//ALTERAR DADOS DE UM USUARIO
				
				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				
				if (usuAutenticado.equals("admin")) {
					
					if (request.getParameter("flagGrupo").equals("0")) {//ALTERAR CADASTRO DE USUARIOS
						
						String nome = request.getParameter("nomeusu");
						
						Usuario usuario = usuDAO.buscarporNome(nome);
						request.setAttribute("usuario", usuario);
						
						request.getRequestDispatcher("AlterarCadastro.jsp").forward(request, response);
						
					} else {//ALTERAR QUEM PERTENCE A UM GRUPO
						
						List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
						request.setAttribute("listaAmigos", listaAmigos);

						List<Usuario> listaTodosUsuarios = usuDAO.buscarTodos();
						request.setAttribute("listaTodosUsuarios", listaTodosUsuarios);

						List<String> listaUsuariosGrupo = mensagemDAO.buscarIntegrantesGrupo(request.getParameter("nomeusu"));
						request.setAttribute("listaUsuariosGrupo", listaUsuariosGrupo);

						request.setAttribute("nomeGrupoAtual", request.getParameter("nomeusu"));

						RequestDispatcher saida = request.getRequestDispatcher("AlterarGrupo.jsp");
						saida.forward(request, response);

					}
				}
				
				else {
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("cad")) {//CADASTRAR USUARIO
				
				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				
				if (usuAutenticado.equals("admin")) {//SOH ADMIN PODE CADATRAR USUARIOS--REGRA DE NEGOCIO DO SANHAGRAM
					
					RequestDispatcher saida = request.getRequestDispatcher("cadastro.jsp");
					saida.forward(request, response);
					
				} else {
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}
			}
			else {//QUALQUER ACAO NAO ESPECIFICADA ACIMA
				
				RequestDispatcher saida = request.getRequestDispatcher("erroLogin.jsp");
				saida.forward(request, response);
			}
			
		}
		else if (dispositivo.equals("android")){//****************************************************ANDROID*************************************************************
			
			if(acao.equals("listarMsgm")){//-----------------------------------------------------------------OK-OK
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");

				if (usuDAO.tipoUsuario(destinatario).equals("grupo")) {//EH UMA CONVERSA EM GRUPO
						
					List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", remetente);
					json.put("DESTINATARIO", destinatario);
					json.put("MENSAGENS", lista);

					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
	
						
				} else {
						
					List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
				
					JSONObject json = new JSONObject();
					json.put("LOGIN", remetente);
					json.put("DESTINATARIO", destinatario);
					json.put("MENSAGENS", lista);

					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
						
				}
				
			}
			else if(acao.equals("listarConversas")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");
				List<String> lista = mensagemDAO.buscarRecentes(login);
				
				JSONObject json = new JSONObject();
				json.put("LOGIN", login);
				json.put("CONVERSAS", lista);
				
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.print(json.toString());
			}
			else if(acao.equals("excluirMsgm")){//---------------------------------------------------------------OK-TESTAR--TEM QUE SOH DEIXAR APAGAR AS PROPRIAS MSGNS
				
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				String idmensagem = request.getParameter("idmensagem");
				
				mensagemDAO.deletar(Integer.parseInt(idmensagem));
				
				if (usuDAO.tipoUsuario(destinatario).equals("grupo")) {//EH UMA CONVERSA EM GRUPO
					
					List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", remetente);
					json.put("DESTINATARIO", destinatario);
					json.put("MENSAGENS", lista);

					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
	
						
				} else {
						
					List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
				
					JSONObject json = new JSONObject();
					json.put("LOGIN", remetente);
					json.put("DESTINATARIO", destinatario);
					json.put("MENSAGENS", lista);

					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
						
				}
			
			}
			else if(acao.equals("listarMsgmSalvas")){//DESNECESSARIO--SOH FAZER DESTINATARIO ACIMA = ADefinirUsuario---------POSSIVELM EXCLUIR
				
				String remetente = request.getParameter("remetente");
				String destinatario = "ADefinirUsuario";
						
				List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
				
				JSONObject json = new JSONObject();
				json.put("LOGIN", remetente);
				json.put("DESTINATARIO", destinatario);
				json.put("MENSAGENS", lista);

				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.print(json.toString());
						
				
			}
			else if(acao.equals("listarUsuarios")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");

				if(login.equals("admin")) {
					
					List<Usuario> lista = usuDAO.buscarTodos();
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("USUARIOS", lista);
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
				
				}
			}
			else if(acao.equals("alterarCadastro")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");
				String nomeUsuario = request.getParameter("nomeUsuario");

				if(login.equals("admin")) {
					
					Usuario usuario = usuDAO.buscarporNome(nomeUsuario);
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("USUARIOALTERAR", usuario);
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
				
				}
			}
			else if(acao.equals("excluirUsuario")){//---------------------------------------------------------------OK-TESTAR
				
				String nome = request.getParameter("nomeusuario");
				String login = request.getParameter("login");
				
				if(login.equals("admin")) {
					
					usu.setNome(nome);
					usuDAO.deletar(usu);//DELETA USUARIO 
					mensagemDAO.UsuarioRemovido(nome);//MODIFICA SEU NOME NAS MENSAGENS PARA 'NOME* (REMOVIDO)'
					
					List<Usuario> lista = usuDAO.buscarTodos();
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("USUARIOS", lista);
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
				
				}
			}
			else if(acao.equals("listarGrupos")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");

				if(login.equals("admin")) {
					
					List<Usuario> lista = usuDAO.buscarGrupos();
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("GRUPOS", lista);
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
				
				}
			}
			else if(acao.equals("listarUsuariosDoGrupo")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");
				String nomeGrupo = request.getParameter("nomeGrupo");

				if(login.equals("admin")) {
					
					List<Usuario> listaTodosUsuariosObjeto = usuDAO.buscarTodos();

					List<String> listaUsuariosGrupo = mensagemDAO.buscarIntegrantesGrupo(nomeGrupo);
					
					List<String> listaTodosUsuarios = new ArrayList<String>();
					for(Usuario u : listaTodosUsuariosObjeto){
						listaTodosUsuarios.add(u.getNome());
					}
					
					Set<String> todosUsuarios = new HashSet<String>(listaTodosUsuarios);
					Set<String> usuariosDoGrupo = new HashSet<String>(listaUsuariosGrupo);
					todosUsuarios.removeAll(usuariosDoGrupo);//USUARIOS QUE AINDA NAO PERTENCEM AO GRUPO
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("GRUPO", nomeGrupo);
					json.put("USUARIOSDOGRUPO", listaUsuariosGrupo);
					json.put("USUARIOSFORADOGRUPO", todosUsuarios);					
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
				
				}
			}
			else if(acao.equals("removerDoGrupo")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");
				String nomeGrupo = request.getParameter("nomeGrupo");
				String nomeUsuario = request.getParameter("nomeUsuario");
				
				if (login.equals("admin")) {
					
					mensagemDAO.SairGrupo(nomeUsuario, nomeGrupo);// RETIRA USUARIO DO GRUPO
					
					List<Usuario> listaTodosUsuariosObjeto = usuDAO.buscarTodos();

					List<String> listaUsuariosGrupo = mensagemDAO.buscarIntegrantesGrupo(nomeGrupo);
					
					List<String> listaTodosUsuarios = new ArrayList<String>();
					for(Usuario u : listaTodosUsuariosObjeto){
						listaTodosUsuarios.add(u.getNome());
					}
					
					Set<String> todosUsuarios = new HashSet<String>(listaTodosUsuarios);
					Set<String> usuariosDoGrupo = new HashSet<String>(listaUsuariosGrupo);
					todosUsuarios.removeAll(usuariosDoGrupo);//USUARIOS QUE AINDA NAO PERTENCEM AO GRUPO
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("GRUPO", nomeGrupo);
					json.put("USUARIOSDOGRUPO", listaUsuariosGrupo);
					json.put("USUARIOSFORADOGRUPO", todosUsuarios);					
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
					
				} 
				
			}
			else if(acao.equals("adicionarAoGrupo")){//---------------------------------------------------------------OK-TESTAR
				
				String login = request.getParameter("login");
				String nomeGrupo = request.getParameter("nomeGrupo");
				String nomeUsuario = request.getParameter("nomeUsuario");
				
				if (login.equals("admin")) {
					
					try {
	
						Mensagem mensagem1 = new Mensagem();
						Mensagem mensagem2 = new Mensagem();
		
						mensagem1.setRemetente(nomeUsuario);
						mensagem1.setDestinatario(nomeGrupo);
						mensagem1.setTexto_mensagem("");
		
						MensagemDAO mensagemDAO1 = new MensagemDAO();
						mensagemDAO1.enviarParaGrupo(mensagem1);
		
						mensagem2.setRemetente(nomeGrupo);
						mensagem2.setDestinatario(nomeGrupo);
						mensagem2.setTexto_mensagem(nomeUsuario + " entrou no grupo");
		
						MensagemDAO mensagemDAO2 = new MensagemDAO();
						mensagemDAO2.enviarParaGrupo(mensagem2);
	
					}
	
					catch (Exception e) {
						System.out.println(e);
	
					}
					
					List<Usuario> listaTodosUsuariosObjeto = usuDAO.buscarTodos();

					List<String> listaUsuariosGrupo = mensagemDAO.buscarIntegrantesGrupo(nomeGrupo);
					
					List<String> listaTodosUsuarios = new ArrayList<String>();
					
					for(Usuario u : listaTodosUsuariosObjeto){
						
						listaTodosUsuarios.add(u.getNome());
						
					}
					
					Set<String> todosUsuarios = new HashSet<String>(listaTodosUsuarios);
					Set<String> usuariosDoGrupo = new HashSet<String>(listaUsuariosGrupo);
					todosUsuarios.removeAll(usuariosDoGrupo);//USUARIOS QUE AINDA NAO PERTENCEM AO GRUPO
					
					JSONObject json = new JSONObject();
					json.put("LOGIN", login);
					json.put("GRUPO", nomeGrupo);
					json.put("USUARIOSDOGRUPO", listaUsuariosGrupo);
					json.put("USUARIOSFORADOGRUPO", todosUsuarios);					
					
					PrintWriter pw = response.getWriter();
					pw.write(json.toString());
					pw.print(json.toString());
					
				}
				
			}
			else if(acao.equals("sairDoGrupo")){//---------------------------------------------------------------OK-TESTAR
				
				String nomeGrupo = request.getParameter("nomeGrupo");
				String login = request.getParameter("login");
				
				mensagemDAO.SairGrupo(login, nomeGrupo);
				
				List<String> lista = mensagemDAO.buscarRecentes(login);
				
				JSONObject json = new JSONObject();
				json.put("LOGIN", login);
				json.put("CONVERSAS", lista);
				
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.print(json.toString());
				
			}
			else{
				
				//ERRO
				
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)//METODO POST***************************************************POST*******************
			throws ServletException, IOException {//FORMS CHAMAM O METODO POST PARA ESCREVER DADOS NO BD
		
		String acao = request.getParameter("acao");
		String dispositivo = request.getParameter("dispositivo");
		
		if(dispositivo.equals("desktop")) {//*******************************************************DESKTOP**************************************************************
			
			if (acao.equals("alterar")) {
				
				String snome = request.getParameter("nome");
				String semail = request.getParameter("email");
				String ssenha = request.getParameter("senha");
				String sdata = request.getParameter("data");
	
				Usuario usuario = new Usuario();
				usuario.setNome(snome);
				usuario.setEmail(semail);
				usuario.setSenha(ssenha);
				usuario.setDatanasc(sdata);

				UsuarioDAO2 usuDao = new UsuarioDAO2();
				
				usuDao.alterar(usuario);
				
				response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios&dispositivo=desktop");
				
			} else if (acao.equals("enviar")) {
	
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				String texto_mensagem = request.getParameter("texto_mensagem");
				
				MensagemDAO mensagemDAO = new MensagemDAO();
				UsuarioDAO2 usuDAO = new UsuarioDAO2();
				Mensagem mensagem = new Mensagem();
	
				if ((usuDAO.tipoUsuario(destinatario).equals("grupo")
						&& mensagemDAO.ChecaPertencimentoGrupo(remetente, destinatario).equals("pertence"))
						|| (!usuDAO.tipoUsuario(destinatario).equals("grupo")
								&& usuDAO.ChecaExistenciaUsuario(destinatario).equals("existe"))
						|| (destinatario.equals("ADefinirUsuario"))) {//CONDICAO 1-SE FOR CONVERSA EM GRUPO E VC PERTENCE A ELE 2-SE FOR CONVERSA NORMAL E A PESSOA EXISTE
																	  //3-SE FOR PARA SALVAR UMA MENSAGEM--destinatario=ADefinirUsuario
	
					try {
						
						mensagem.setRemetente(remetente);
						mensagem.setDestinatario(destinatario);
						mensagem.setTexto_mensagem(texto_mensagem);
	
						if (usuDAO.tipoUsuario(destinatario).equals("grupo")) {
							
							mensagemDAO.enviarParaGrupo(mensagem);
							String prox_pag = "UsuarioControlador?acao=lismsgm&remetente=" + remetente + "&destinatario="
									+ destinatario+"&dispositivo=desktop";
							response.sendRedirect(prox_pag);
							
						} else {
							
							if (destinatario.equals("ADefinirUsuario")) {
								
								mensagemDAO.enviar(mensagem);
								String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente=" + remetente+"&dispositivo=desktop";
								response.sendRedirect(prox_pag);
							} else {
								
								mensagemDAO.enviar(mensagem);
								String prox_pag = "UsuarioControlador?acao=lismsgm&remetente=" + remetente
										+ "&destinatario=" + destinatario+"&dispositivo=desktop";
								response.sendRedirect(prox_pag);
	
							}
	
						}
					}
	
					catch (Exception e) {
						System.out.println(e);
	
					}
	
				} else {// USUARIO NAO EXISTE OU NAO ESTA CADASTRADO NO GRUPO
					
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}
			} else if (acao.equals("cadastrar")) {
				
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String data = request.getParameter("data");
	
				try {
	
					Usuario usuario = new Usuario();
					
					usuario.setNome(nome);
					usuario.setEmail(email);
					usuario.setSenha(senha);
					usuario.setDatanasc(data);
	
					UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
					
					String resultadocadastro = usuarioDAO.cadastro(usuario);
					
					if (resultadocadastro.equals("cadastradoComSucesso")) {//SE JAH NAO EXISTE UM GRUPO COM ESSE NOME
						
						response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios&dispositivo=desktop");
						
					} else {
						
						RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
						saida.forward(request, response);
					}
				}
	
				catch (Exception e) {
					System.out.println(e);
	
				}
				
			} else if (acao.equals("cadastrarGrupo")) {
				
				String nome_grupo = request.getParameter("nome_grupo");
				String integrantes_grupo = request.getParameter("listaNovoGrupo").substring(0,
						request.getParameter("listaNovoGrupo").length() - 1);// COMPLICADO PASSAR ARRAY DE JS PARA
																				// JAVA-->STRING COM DELIMITADORES
				String integrantes[] = integrantes_grupo.split("\\|");// separa os nomes dos usuarios (estao separados por '|')
	
				try {// CADASTRA O GRUPO EM SI COMO UM USUARIO APENAS COM NOME E FLAG_GRUPO=1
	
					Usuario usuario = new Usuario();
					usuario.setNome(nome_grupo);
	
					UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
					String resultadoCadastro = usuarioDAO.cadastroGrupo(usuario);
	
					if (resultadoCadastro.equals("cadastradoComSucesso")) {
	
						try {// ENVIA MENSAGENS AO GRUPO INDICANDO A ENTRADA DE SEUS MEMBROS INDIVIDUALMENTE
								// E UMA MENSAGEM PARA INDICAR QUE O USUARIO PERTENCE AO GRUPO
	
							for (String remetente : integrantes) {
								
								Mensagem mensagem = new Mensagem();
								Mensagem mensagem2 = new Mensagem();
	
								mensagem.setRemetente(remetente);
								mensagem.setDestinatario(nome_grupo);
								mensagem.setTexto_mensagem("");// MENSAGEM QUE MANTEM A REFERENCIA DO USUARIO AO GRUPO E NAO
																// APARECE
																// NO CHAT
	
								MensagemDAO mensagemDAO = new MensagemDAO();
								mensagemDAO.enviarParaGrupo(mensagem);
	
								mensagem2.setRemetente(nome_grupo);
								mensagem2.setDestinatario(nome_grupo);
								mensagem2.setTexto_mensagem(remetente + " entrou no grupo");
	
								MensagemDAO mensagemDAO2 = new MensagemDAO();
								mensagemDAO2.enviarParaGrupo(mensagem2);
	
							}
	
						}
	
						catch (Exception e) {
							System.out.println(e);
	
						}
	
						response.sendRedirect("UsuarioControlador?acao=lis&grupo=grupo&dispositivo=desktop");
					} else {
	
						RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
						saida.forward(request, response);
	
					}
	
				}
	
				catch (Exception e) {
					System.out.println(e);
	
				}
	
			}
		}
		else if (dispositivo.equals("android")) {//***********************************************ANDROID**************************************************************
			
			if(acao.equals("enviarMsgm")){
				
				String remetente = request.getParameter("remetente");//= login 
				String destinatario = request.getParameter("destinatario");
				String texto_mensagem = request.getParameter("texto_mensagem");
				
				MensagemDAO mensagemDAO = new MensagemDAO();
				UsuarioDAO2 usuDAO = new UsuarioDAO2();
				Mensagem mensagem = new Mensagem();
	
				if ((usuDAO.tipoUsuario(destinatario).equals("grupo")
						&& mensagemDAO.ChecaPertencimentoGrupo(remetente, destinatario).equals("pertence"))
						|| (!usuDAO.tipoUsuario(destinatario).equals("grupo")
								&& usuDAO.ChecaExistenciaUsuario(destinatario).equals("existe"))
						|| (destinatario.equals("ADefinirUsuario"))) {
	
					try {
						
						mensagem.setRemetente(remetente);
						mensagem.setDestinatario(destinatario);
						mensagem.setTexto_mensagem(texto_mensagem);
	
						if (usuDAO.tipoUsuario(destinatario).equals("grupo")) {
							
							mensagemDAO.enviarParaGrupo(mensagem);
							
							List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
							
							JSONObject json = new JSONObject();
							json.put("LOGIN", remetente);
							json.put("DESTINATARIO", destinatario);
							json.put("MENSAGENS", lista);

							PrintWriter pw = response.getWriter();
							pw.write(json.toString());
							pw.print(json.toString());
							
							
						} else {
								
							mensagemDAO.enviar(mensagem);
								
							List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
								
							JSONObject json = new JSONObject();
							json.put("LOGIN", remetente);
							json.put("DESTINATARIO", destinatario);
							json.put("MENSAGENS", lista);

							PrintWriter pw = response.getWriter();
							pw.write(json.toString());
							pw.print(json.toString());
	
						}
					}
	
					catch (Exception e) {
						System.out.println(e);
	
					}
	
				} else {// USUARIO NAO EXISTE OU NAO ESTA CADASTRADO NO GRUPO
					
					//ERRO
				}

			}
			else if(acao.equals("cadastrarUsuario")){
				
				String login = request.getParameter("login");
				
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String data = request.getParameter("data");
	
				if(login.equals("admin")) {
					
					try {
		
						Usuario usuario = new Usuario();
						
						usuario.setNome(nome);
						usuario.setEmail(email);
						usuario.setSenha(senha);
						usuario.setDatanasc(data);
		
						UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
						
						String resultadocadastro = usuarioDAO.cadastro(usuario);
						
						if (resultadocadastro.equals("cadastradoComSucesso")) {
						
							List<Usuario> lista = usuarioDAO.buscarTodos();
							
							JSONObject json = new JSONObject();
							json.put("LOGIN", login);
							json.put("USUARIOS", lista);
							
							PrintWriter pw = response.getWriter();
							pw.write(json.toString());
							pw.print(json.toString());
						
						} else {
						
							//ERRO
							
						}
					}
	
					catch (Exception e) {
						
						System.out.println(e);
	
					}

				}
				else {
					
					//ERRO
				
				}
			}
			else if(acao.equals("alterarUsuario")){
				
				String login = request.getParameter("login");
				
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String data = request.getParameter("data");
	
				Usuario usuario = new Usuario();
				
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setDatanasc(data);

				UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
				
				usuarioDAO.alterar(usuario);
				
				List<Usuario> lista = usuarioDAO.buscarTodos();
				
				JSONObject json = new JSONObject();
				json.put("LOGIN", login);
				json.put("USUARIOS", lista);
				
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.print(json.toString());
				
			}
			else if(acao.equals("criarGrupo")){
				
				String login = request.getParameter("login");
				
				String nomeGrupo = request.getParameter("nomeGrupo");
				String integrantesGrupo = request.getParameter("listaNovoGrupo").substring(0, request.getParameter("listaNovoGrupo").length() - 1);
				String integrantes[] = integrantesGrupo.split("\\|");// separa os nomes dos usuarios (estao separados por '|')
	
				try {// CADASTRA O GRUPO EM SI COMO UM USUARIO APENAS COM NOME E FLAG_GRUPO=1
	
					if(login.equals("admin")) {
					
						Usuario usuario = new Usuario();
						usuario.setNome(nomeGrupo);
						
						UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
						String resultadoCadastro = usuarioDAO.cadastroGrupo(usuario);
						
						if (resultadoCadastro.equals("cadastradoComSucesso")) {
		
							try {
		
								for (String remetente : integrantes) {
									
									Mensagem mensagem = new Mensagem();
									Mensagem mensagem2 = new Mensagem();
		
									mensagem.setRemetente(remetente);
									mensagem.setDestinatario(nomeGrupo);
									mensagem.setTexto_mensagem("");
		
									MensagemDAO mensagemDAO = new MensagemDAO();
									mensagemDAO.enviarParaGrupo(mensagem);
		
									mensagem2.setRemetente(nomeGrupo);
									mensagem2.setDestinatario(nomeGrupo);
									mensagem2.setTexto_mensagem(remetente + " entrou no grupo");
		
									MensagemDAO mensagemDAO2 = new MensagemDAO();
									mensagemDAO2.enviarParaGrupo(mensagem2);
		
								}
		
							}
		
							catch (Exception e) {
								System.out.println(e);
		
							}
								
							List<Usuario> lista = usuarioDAO.buscarGrupos();
								
							JSONObject json = new JSONObject();
							json.put("LOGIN", login);
							json.put("GRUPOS", lista);
								
							PrintWriter pw = response.getWriter();
							pw.write(json.toString());
							pw.print(json.toString());
							
						} else {
		
							//ERRO
		
						}
					}
	
				}
	
				catch (Exception e) {
					System.out.println(e);
	
				}
	
			}
			else{
				
				//ERRO
				
			}
			
		}
	}

}
