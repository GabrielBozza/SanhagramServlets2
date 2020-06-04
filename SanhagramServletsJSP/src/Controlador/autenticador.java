package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;

import bean.Usuario;
import jdbc.MensagemDAO;
import jdbc.UsuarioDAO2;

/**
 * Servlet implementation class autenticador
 */
@WebServlet("/autenticador")
public class autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public autenticador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// CHAMADO NO LOGOUT
		HttpSession sessao = request.getSession(false);
		if (sessao != null) {
			sessao.invalidate();

		}
		response.sendRedirect("login.jsp");// LOGOUT
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// LOGIN REQUEST
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");

		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setSenha(senha);

		UsuarioDAO2 usuDAO = new UsuarioDAO2();
		
		Usuario usuAutenticado = usuDAO.autenticacao(usu);// CHAMA A FUNCAO AUTENTICACAO DE USUARIODAO-->RETORNA NULL SE
															// NAO ENCONTRAR O PAR USUARIO,SENHA CORRETO

		if (usuAutenticado != null) {// ENCONTROU O PAR USUARIO,SENHA DADO E O LOGIN FOI BEM SUCEDIDO
			
			HttpSession sessao = request.getSession();
			
			sessao.setAttribute("usuAutenticado", usuAutenticado.getNome());// PASSA COMO ATRIBUTO UM OBJETO USUARIO COM
																			// O NOME E SENHA FORNECIDOS E JA CHECADOS
			MensagemDAO mensagemDAO = new MensagemDAO();
		
			if (usuAutenticado.getNome().equals("admin") && request.getParameter("dispositivo").equals("desktop")) {
				
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado.getNome());
				request.setAttribute("lista", lista);
				
				request.getRequestDispatcher("home.jsp").forward(request, response);
				
			} else if (!usuAutenticado.getNome().equals("admin") && request.getParameter("dispositivo").equals("desktop")){
				
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado.getNome());
				request.setAttribute("lista", lista);
				
				request.getRequestDispatcher("home.jsp").forward(request, response);
				
			}
			else {//android
				
				List<String> lista = mensagemDAO.buscarRecentes(usuAutenticado.getNome());
				String chave_usuario = usuDAO.GerarChaveUsuario(usuAutenticado.getNome());
				
				JSONObject json = new JSONObject();
				
				json.put("LOGIN", nome);
				json.put("CONVERSAS", lista);
				json.put("CHAVE_USUARIO", chave_usuario);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
				pw.print(json.toString());
				
			}
		} else {
			response.sendRedirect("erroLogin.jsp");// PAGINA QUE INDICA QUE USUARIO E/OU SENHA ESTAO INCORRETOS
		}
	}

}
