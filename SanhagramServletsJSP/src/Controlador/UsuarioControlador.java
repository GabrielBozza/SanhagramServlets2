package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String acao = request.getParameter("acao");// PARAMETRO PASSADO PELAS PAGINAS JSP A USUARIOCONTROLADOR PARA
													// INDICAR O QUE DESEJAM FAZER
		UsuarioDAO2 usuDAO = new UsuarioDAO2();// NOVO OBJETO USUARIODAO
		MensagemDAO mensagemDAO = new MensagemDAO();// NOVO OBJETO MENSAGEMDAO

		if (request.getSession().getAttribute("usuAutenticado") == null) {// USUARIO SEM AUTENTICACAO

			RequestDispatcher saida = request.getRequestDispatcher("login.jsp");
			saida.forward(request, response);

		} else {// USUARIO AUTENTICADO

			if (acao != null && acao.equals("lis")) {// ACAO LISTAR (3 POSSIBILIDADES - PARA 1-CADASTRO DE GRUPOS, 2-VER/ALTERAR USUARIOS, 3-VER/ALTERAR GRUPOS)

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");

				if (usuAutenticado.equals("admin")) {//SOMENTE SE O USUARIO AUTENTICADO FOR O ADMIN - AREA RESTRITA

					if (request.getParameter("grupo").equals("cadgrupo")) {//RETORNA PAG PARA CRIAR GRUPOS
						
						request.setAttribute("tipo", "cadgrupo");//ATRIBUTO PARA IDENTIFICAR A PAGINA
						
						List<Usuario> lista = usuDAO.buscarTodos();// LISTA COM TODOS OS USUARIOS
						request.setAttribute("lista", lista);
						
						RequestDispatcher saida = request.getRequestDispatcher("CadastroGrupo.jsp");
						saida.forward(request, response);
						
					} else if (request.getParameter("grupo").equals("lisusuarios")) {//RETORNA PAG COM UMA LISTA DE TODOS OS USU E OPCOES PARA EXCLUÍ-LOS E ALTERÁ-LOS
						
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
					
					response.sendRedirect("UsuarioControlador?acao=alt&nomeusu=" + nomeGrupo + "&flagGrupo='1'");//VOLTA PARA PAG DE ALTERAR O GRUPO
					
				} else {// USUARIO QUE SAIU POR CONTA PROPRIA DO GRUPO
					
					mensagemDAO.SairGrupo(usuAutenticado, nomeGrupo);
					response.sendRedirect("UsuarioControlador?acao=pagInicial");// SAI DO GRUPO E VAI PARA A PAG INICIAL--PERDE CONVERSAS DO GRUPO
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

					response.sendRedirect("UsuarioControlador?acao=alt&nomeusu=" + nomeGrupo + "&flagGrupo='1'");//VOLTA À PAG DE ALTERAR GRUPOS
					
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
						
						String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente=" + remetente;
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
					
					response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios");
					
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//FORMS CHAMAM O METODO POST PARA ESCREVER DADOS NO BD
		
		String acao = request.getParameter("acao");
		
		if (acao.equals("alterar")) {
			
			// String sid = request.getParameter("id");
			String snome = request.getParameter("nome");
			String semail = request.getParameter("email");
			String ssenha = request.getParameter("senha");
			String sdata = request.getParameter("data");

			// criando objeto usuario e atribuindo valores da tela--DO FORM - 'NAME' DO INPUT
			Usuario usuario = new Usuario();
			usuario.setNome(snome);
			usuario.setEmail(semail);
			usuario.setSenha(ssenha);
			usuario.setDatanasc(sdata);
			// usuario.setId(Integer.parseInt(sid));

			// criando um usuarioDAO PARA ACESSAR O BD
			UsuarioDAO2 usuDao = new UsuarioDAO2();
			
			// Salvando no banco de dados
			usuDao.alterar(usuario);
			
			response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios");
			
		} else if (acao.equals("enviar")) {

			String remetente = request.getParameter("remetente");
			String destinatario = request.getParameter("destinatario");
			String texto_mensagem = request.getParameter("texto_mensagem");
			
			//CRIA OBJETOS NECESSÁRIOS
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
								+ destinatario;
						response.sendRedirect(prox_pag);
						
					} else {
						
						if (destinatario.equals("ADefinirUsuario")) {
							
							mensagemDAO.enviar(mensagem);
							String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente=" + remetente;
							response.sendRedirect(prox_pag);
						} else {
							
							mensagemDAO.enviar(mensagem);
							String prox_pag = "UsuarioControlador?acao=lismsgm&remetente=" + remetente
									+ "&destinatario=" + destinatario;
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
					
					response.sendRedirect("UsuarioControlador?acao=lis&grupo=lisusuarios");
					
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

					response.sendRedirect("UsuarioControlador?acao=lis&grupo=grupo");
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

}
