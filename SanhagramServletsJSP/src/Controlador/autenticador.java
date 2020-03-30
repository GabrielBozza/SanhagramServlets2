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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		if(sessao!=null){
			sessao.invalidate();
			
		}
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setSenha(senha);
		
		UsuarioDAO2 usuDAO = new UsuarioDAO2();
		Usuario usuAutenticado = usuDAO.autenticacao(usu);
		
		if(usuAutenticado != null){
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			//sessao.setMaxInactiveInterval(3000);
			
				request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			response.sendRedirect("erroLogin.jsp");
		}
	}

}
