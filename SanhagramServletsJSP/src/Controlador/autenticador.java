package Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Usuario;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//CHAMADO NO LOGOUT
		HttpSession sessao = request.getSession(false);
		if(sessao!=null){
			sessao.invalidate();
			
		}
		response.sendRedirect("login.jsp");//LOGOUT
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//LOGIN REQUEST
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		Usuario usu = new Usuario();//CRIA UM NOVO USUARIO DE BEAN.USUARIO
		usu.setNome(nome);//SETA SEUS PARAMETROS
		usu.setSenha(senha);//SETA SEUS PARAMETROS
		
		UsuarioDAO2 usuDAO = new UsuarioDAO2();//CRIA UM OBJETO USUARIODAO PARA PODER OBTER INFOS DO BD COM RELACAO AO OBJETO USUARIO
		Usuario usuAutenticado = usuDAO.autenticacao(usu);//CHAMA A FUNCAO AUTENTICACAO DE USUARIODAO-->RETORNA NULL SE NAO ENCONTRAR O PAR USUARIO,SENHA CORRETO
		
		if(usuAutenticado != null){//ENCONTROU O PAR USUARIO,SENHA DADO E O LOGIN FOI BEM SUCEDIDO
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado.getNome());//PASSA COMO ATRIBUTO UM OBJETO USUARIO COM O NOME E SENHA FORNECIDOS E JA CHECADOS 
			//sessao.setMaxInactiveInterval(3000);
			if(usuAutenticado.getNome().equals("admin")){
				request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}else {
			response.sendRedirect("erroLogin.jsp");//PAGINA QUE INDICA QUE USUARIO E/OU SENHA ESTAO INCORRETOS
		}
	}

}
