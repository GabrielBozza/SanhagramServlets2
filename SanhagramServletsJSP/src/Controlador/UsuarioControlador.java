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

		if (request.getSession().getAttribute("usuAutenticado") == null) {
			RequestDispatcher saida = request.getRequestDispatcher("login.jsp");
			saida.forward(request, response);
		} else {
			if (acao != null && acao.equals("lis")) {// ACAO=LISTAR TODOS OS USUARIOS CADASTRADOS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				if (usuAutenticado.equals("admin")) {
					if (request.getParameter("grupo").equals("grupo")) {
						List<Usuario> lista = usuDAO.buscarTodos(usu);// DAO FAZ A OPERACAO E RETORNA O RESULTADO
						request.setAttribute("lista", lista);
						RequestDispatcher saida = request.getRequestDispatcher("CadastroGrupo.jsp");
						saida.forward(request, response);
					} else {
						List<Usuario> lista = usuDAO.buscarTodos(usu);// DAO FAZ A OPERACAO E RETORNA O RESULTADO
						request.setAttribute("lista", lista);
						RequestDispatcher saida = request.getRequestDispatcher("listaUsuarios.jsp");
						saida.forward(request, response);

					}
				} else {
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("lismsgm")) {// ACAO=LISTAR AS MENSAGENS ENTRE UM PARAMETRO REMETENTE
																// E
																// UM PARAMETRO DESTINATARIO, PASSADOS JUNTO A ACAO

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				request.getSession().setAttribute("destinatarioMsgm", destinatario);// VAI PASSAR ADIANTE A INFO DE QUAL
																					// EH
																					// A CONVERSA MOSTRADA
				if (!usuAutenticado.equals(remetente)) {
					RequestDispatcher saida = request.getRequestDispatcher("AcessoProibido.jsp");
					saida.forward(request, response);
				} else {
					List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
					request.setAttribute("listaAmigos", listaAmigos);

					if (usuDAO.tipoUsuario(destinatario) == "grupo") {
						List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
						request.setAttribute("lista", lista);
						request.setAttribute("conversaAtual", destinatario);
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
					} else {
						List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);// DAO FAZ A
																									// OPERACAO E
						// RETORNA O RESULTADO
						request.setAttribute("lista", lista);
						request.setAttribute("conversaAtual", destinatario);
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
					}

				}

			} else if (acao != null && acao.equals("lismsgmSalvas")) {

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String remetente = request.getParameter("remetente");
				String destinatario = "ADefinirUsuario";
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

			} else if (acao != null && acao.equals("lisamigos")) {// NAO ESTA MAIS SENDO USADA

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String destinatario = request.getParameter("destinatario");// PASSA O PARAMETRO DESTINATARIO TAMBEM
				if (!usuAutenticado.equals(destinatario)) {
					RequestDispatcher saida = request.getRequestDispatcher("AcessoProibido.jsp");
					saida.forward(request, response);
				} else {
					List<String> lista = mensagemDAO.buscarRecentes(destinatario);// DAO FAZ A OPERACAO E RETORNA O
																					// RESULTADO
					request.setAttribute("lista", lista);
					RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("pagInicial")) {// ACAO=LISTAR PESSOAS COM AS QUAIS CONVERSEI COM AS
																	// MAIS
				// RECENTES ACIMA

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				// String destinatario = request.getParameter("destinatario");// PASSA O
				// PARAMETRO DESTINATARIO TAMBEM
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado);
				request.setAttribute("lista", lista);
				RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
				saida.forward(request, response);

			} else if (acao != null && acao.equals("salvarMensagem")) {// ACAO=LISTAR PESSOAS COM AS QUAIS CONVERSEI COM
																		// AS

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado);
				request.setAttribute("lista", lista);
				RequestDispatcher saida = request.getRequestDispatcher("SalvarMensagem.jsp");
				saida.forward(request, response);

			} else if (acao != null && acao.equals("exmsgm")) {// ACAO=EXCLUIR MENSAGEM

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				String id = request.getParameter("idmensagem");// PASSA O PARAMETRO DO ID DA MENSAGEM A SER EXCLUIDA
				mensagemDAO.deletar(Integer.parseInt(id));// DAO FAZ A OPERACAO E RETORNA O RESULTADO
				String remetente = request.getParameter("remetente");
				String destinatario = request.getParameter("destinatario");
				List<String> listaAmigos = mensagemDAO.buscarRecentes(usuAutenticado);
				request.setAttribute("listaAmigos", listaAmigos);

				if (usuDAO.tipoUsuario(destinatario) == "grupo") {
					List<Mensagem> lista = mensagemDAO.buscarMensagensGrupo(remetente, destinatario);
					request.setAttribute("lista", lista);
					request.setAttribute("conversaAtual", destinatario);
					RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
					saida.forward(request, response);
				} else {
					
					if (destinatario.equals("ADefinirUsuario")) {
						String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente="+remetente;
						response.sendRedirect(prox_pag);
					} else {
						List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente, destinatario);
						request.setAttribute("lista", lista);
						request.setAttribute("conversaAtual", destinatario);
						RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
						saida.forward(request, response);
					}
				}

			} else if (acao != null && acao.equals("ex")) {// ACAO=EXCLUIR USUARIO

				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				if (usuAutenticado.equals("admin")) {
					String nome = request.getParameter("nomeusu");
					usu.setNome(nome);
					usuDAO.deletar(usu);// DAO FAZ A OPERACAO E RETORNA O RESULTADO
					mensagemDAO.UsuarioRemovido(nome);
					response.sendRedirect("UsuarioControlador?acao=lis&grupo=usuario");
				} else {
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("alt")) {// ACAO=ALTERAR DADOS DE UM USUARIO
				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				if (usuAutenticado.equals("admin")) {
					String nome = request.getParameter("nomeusu");
					Usuario usuario = usuDAO.buscarporNome(nome);// DAO FAZ A OPERACAO E RETORNA O RESULTADO
					request.setAttribute("usuario", usuario);
					request.getRequestDispatcher("AlterarCadastro.jsp").forward(request, response);
				}
				// AlterarCad.jsp
				else {
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}

			} else if (acao != null && acao.equals("cad")) {// ACAO=CADASTRAR USUARIO
				String usuAutenticado = (String) request.getSession().getAttribute("usuAutenticado");
				if (usuAutenticado.equals("admin")) {
					RequestDispatcher saida = request.getRequestDispatcher("cadastro.jsp");
					saida.forward(request, response);
				} else {
					RequestDispatcher saida = request.getRequestDispatcher("ErroAreaAdmin.jsp");
					saida.forward(request, response);
				}
			} else {
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
			throws ServletException, IOException {// FORM DE ALTERAR CADASTRO CHAMA O METODO POST DE USUARIOCONTROLADOR
		// Capturando parametros da tela
		String acao = request.getParameter("acao");
		if (acao.equals("alterar")) {
			// String sid = request.getParameter("id");
			String snome = request.getParameter("nome");
			String semail = request.getParameter("email");
			String ssenha = request.getParameter("senha");
			String sdata = request.getParameter("data");

			// criando objeto usuario e atribuindo valores da tela
			Usuario usuario = new Usuario();
			usuario.setNome(snome);
			usuario.setEmail(semail);
			usuario.setSenha(ssenha);
			usuario.setDatanasc(sdata);
			// usuario.setId(Integer.parseInt(sid));

			// criando um usuarioDAO
			UsuarioDAO2 usuDao = new UsuarioDAO2();
			// Salvando no banco de dados
			usuDao.alterar(usuario);
			response.sendRedirect("UsuarioControlador?acao=lis");
		} else if (acao.equals("enviar")) {

			String remetente = request.getParameter("remetente");
			String destinatario = request.getParameter("destinatario");
			String texto_mensagem = request.getParameter("texto_mensagem");
			MensagemDAO mensagemDAO = new MensagemDAO();
			UsuarioDAO2 usuDAO = new UsuarioDAO2();
			Mensagem mensagem = new Mensagem();

			if ((usuDAO.tipoUsuario(destinatario) == "grupo"
					&& mensagemDAO.ChecaPertencimentoGrupo(remetente, destinatario) == "pertence")
					|| (usuDAO.tipoUsuario(destinatario) != "grupo"
							&& usuDAO.ChecaExistenciaUsuario(destinatario) == "existe")
					|| (destinatario.equals("ADefinirUsuario"))) {

				try {
					mensagem.setRemetente(remetente);
					mensagem.setDestinatario(destinatario);
					mensagem.setTexto_mensagem(texto_mensagem);

					if (usuDAO.tipoUsuario(destinatario) == "grupo") {
						mensagemDAO.enviarParaGrupo(mensagem);
						String prox_pag = "UsuarioControlador?acao=lismsgm&remetente=" + remetente + "&destinatario="
								+ destinatario;
						response.sendRedirect(prox_pag);
					} else {
						if (destinatario.equals("ADefinirUsuario")) {
							mensagemDAO.enviar(mensagem);
							String prox_pag = "UsuarioControlador?acao=lismsgmSalvas&remetente="+remetente;
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
				usuarioDAO.cadastro(usuario);
				response.sendRedirect("UsuarioControlador?acao=lis&grupo=usuario");
			}

			catch (Exception e) {
				System.out.println(e);

			}
		} else if (acao.equals("cadastrarGrupo")) {
			String nome_grupo = request.getParameter("nome_grupo");
			String integrantes_grupo = request.getParameter("listaNovoGrupo").substring(0,
					request.getParameter("listaNovoGrupo").length() - 1);// COMPLICADO PASSAR ARRAY DE JS PARA
																			// JAVA-->STRING COM DELIMITADORES
			String integrantes[] = integrantes_grupo.split("\\|");

			try {

				Usuario usuario = new Usuario();
				usuario.setNome(nome_grupo);

				UsuarioDAO2 usuarioDAO = new UsuarioDAO2();
				usuarioDAO.cadastroGrupo(usuario);

			}

			catch (Exception e) {
				System.out.println(e);

			}

			try {

				for (String remetente : integrantes) {
					Mensagem mensagem = new Mensagem();
					mensagem.setRemetente(remetente);
					mensagem.setDestinatario(nome_grupo);
					mensagem.setTexto_mensagem(remetente + " entrou no grupo");

					MensagemDAO mensagemDAO = new MensagemDAO();
					mensagemDAO.enviarParaGrupo(mensagem);

				}

			}

			catch (Exception e) {
				System.out.println(e);

			}

			response.sendRedirect("UsuarioControlador?acao=lis&grupo=grupo");
		}
	}

}
