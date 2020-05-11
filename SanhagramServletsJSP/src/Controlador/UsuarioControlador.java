package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean .Usuario;
import jdbc.UsuarioDAO2;
import bean .Mensagem;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//AQUI RECEBE MENSAGENS DAS PAGINAS JSP E DECIDE O QUE FAZER

		Usuario usu = new Usuario();//NOVO OBJETO BEAN.USUARIO
		Mensagem mensagem = new Mensagem();//NOVO OBJETO BEAN.MENSAGEM
		String acao = request.getParameter("acao");//PARAMETRO PASSADO PELAS PAGINAS JSP A USUARIOCONTROLADOR PARA INDICAR O QUE DESEJAM FAZER
		UsuarioDAO2 usuDAO = new UsuarioDAO2();//NOVO OBJETO USUARIODAO
		MensagemDAO mensagemDAO = new MensagemDAO();//NOVO OBJETO MENSAGEMDAO
	
		if(acao != null && acao.equals("lis")){//ACAO=LISTAR TODOS OS USUARIOS CADASTRADOS
			
		List<Usuario> lista = usuDAO.buscarTodos(usu);//DAO FAZ A OPERACAO E RETORNA O RESULTADO
		request.setAttribute("lista", lista);
		RequestDispatcher saida = request.getRequestDispatcher("listaUsuarios.jsp");
		saida.forward(request, response);
		
		}else if(acao != null && acao.equals("lismsgm")){//ACAO=LISTAR AS MENSAGENS ENTRE UM PARAMETRO REMETENTE E UM PARAMETRO DESTINATARIO, PASSADOS JUNTO A ACAO
			
		String remetente = request.getParameter("remetente");
		String destinatario = request.getParameter("destinatario");
		List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente,destinatario);//DAO FAZ A OPERACAO E RETORNA O RESULTADO
		request.setAttribute("lista", lista);
		RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");
		saida.forward(request, response);
		
		}else if(acao != null && acao.equals("lisamigos")){//ACAO=LISTAR PESSOAS COM AS QUAIS CONVERSEI COM AS MAIS RECENTES ACIMA
			
		String destinatario = request.getParameter("destinatario");//PASSA O PARAMETRO DESTINATARIO TAMBEM
		List<String> lista = mensagemDAO.buscarRecentes(destinatario);//DAO FAZ A OPERACAO E RETORNA O RESULTADO
		request.setAttribute("lista", lista);
		RequestDispatcher saida = request.getRequestDispatcher("listaAmigosRecentes.jsp");
		saida.forward(request, response);
		
		}else if(acao != null && acao.equals("exmsgm")){//ACAO=EXCLUIR MENSAGEM
			
			String id = request.getParameter("idmensagem");//PASSA O PARAMETRO DO ID DA MENSAGEM A SER EXCLUIDA
			mensagemDAO.deletar(Integer.parseInt(id));//DAO FAZ A OPERACAO E RETORNA O RESULTADO
			String remetente = request.getParameter("remetente");
			String destinatario = request.getParameter("destinatario");
			List<Mensagem> lista = mensagemDAO.buscarMensagens(remetente,destinatario);
			request.setAttribute("lista", lista);
			RequestDispatcher saida = request.getRequestDispatcher("listaMensagens.jsp");//PARA VOLTAR A CONVERSA ONDE ESTAVA AO DELETAR A MSGM
			saida.forward(request, response);
			//response.sendRedirect("home.jsp");
			
		}else if(acao != null && acao.equals("ex")){//ACAO=EXCLUIR USUARIO
			String id = request.getParameter("id");
			usu.setId(Integer.parseInt(id));
			usuDAO.deletar(usu);//DAO FAZ A OPERACAO E RETORNA O RESULTADO
			response.sendRedirect("UsuarioControlador?acao=lis");
			
		}else if(acao != null && acao.equals("alt")){//ACAO=ALTERAR DADOS DE UM USUARIO
			String id = request.getParameter("id");
			Usuario usuario = usuDAO.buscarporID(Integer.parseInt(id));//DAO FAZ A OPERACAO E RETORNA O RESULTADO
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("AlterarCadastro.jsp").forward(request,response);
			//AlterarCad.jsp
		
		}else if(acao != null && acao.equals("cad")){//ACAO=CADASTRAR USUARIO
			RequestDispatcher saida = request.getRequestDispatcher("cadastro.jsp");
			saida.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//FORM DE ALTERAR CADASTRO CHAMA O METODO POST DE USUARIOCONTROLADOR
		// Capturando parametros da tela
		
				String sid = request.getParameter("id");
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
				usuario.setId(Integer.parseInt(sid));

				// criando um usuarioDAO
				UsuarioDAO2 usuDao = new UsuarioDAO2();
				// Salvando no banco de dados
				usuDao.alterar(usuario);

				response.sendRedirect("UsuarioControlador?acao=lis");
	}

}
