package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bean.Usuario;

//CADA CLASSE DO BD TEM UM ARQ EM BEANS COM A SUA CLASSE E OUTRO NO JDBC (DAO-DATA ACCESS OBJECT) PARA RECUPERAR DADOS DO BD
public class UsuarioDAO2 {
	
	private Connection conexao = Conexao_BD.getConnection();
	
	public String cadastro(Usuario usuario) {
		
		String sql = "INSERT INTO USUARIO (nome, email, senha, datanasc) values (?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());//? 1
			preparador.setString(2, usuario.getEmail());//? 2
			preparador.setString(3, usuario.getSenha());//? 3
			preparador.setString(4, usuario.getDatanasc());//? 4
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			return "cadastradoComSucesso";
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
			return "Erro";
		}
		
	}
	
	public String ChecaExistenciaUsuario(String nome) {//EXCLUI MENSAGEM DADO SEU ID
		
		String sql = "SELECT * FROM USUARIO WHERE NOME = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, nome);//? 1
			ResultSet resultados = preparador.executeQuery();
			
			if(resultados.next()) {
				System.out.println("Existe!");
				return "existe";
			}
			else {
				System.out.println("Nao Existe!");
				return "naoexiste";
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return "erro";
		
	}
	
	public String cadastroGrupo(Usuario usuario) {
		
		String sql = "INSERT INTO USUARIO (nome, flag_grupo) values (?,'1')";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());//? 1
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			return("cadastradoComSucesso");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
			return("Erro");
		}
		
	}
	
	public void alterar(Usuario usuario) {
		
		String sql = "UPDATE USUARIO SET NOME = ?, EMAIL = ?, SENHA = ?, DATANASC = ?  WHERE NOME = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());//? 1
			preparador.setString(2, usuario.getEmail());//? 2
			preparador.setString(3, usuario.getSenha());//? 3
			preparador.setString(4, usuario.getDatanasc());//? 4
			preparador.setString(5, usuario.getNome());//? 5
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public void deletar(Usuario usuario) {
		
		String sql = "DELETE FROM USUARIO WHERE NOME = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());//? 1
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Removido com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public List<Usuario> buscarTodos() {//LISTA TODOS OS USUARIOS CADASTRADOS
		
		String sql = "SELECT * FROM USUARIO WHERE FLAG_GRUPO='0' AND NOME!='ADefinirUsuario'";
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				Usuario prox_usuario = new Usuario();
				
				prox_usuario.setNome(resultados.getString("nome"));
				prox_usuario.setEmail(resultados.getString("email"));
				prox_usuario.setSenha(resultados.getString("senha"));
				prox_usuario.setDatanasc(resultados.getString("datanasc"));
				
				lista.add(prox_usuario);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<Usuario> buscarGrupos() {//LISTA TODOS OS GRUPOS CADASTRADOS
		
		String sql = "SELECT * FROM USUARIO WHERE FLAG_GRUPO='1'";
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				Usuario prox_usuario = new Usuario();
				
				prox_usuario.setNome(resultados.getString("nome"));
				
				lista.add(prox_usuario);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public Usuario buscarporNome(String nome) {//BUSCA UM USUARIO DADO SEU ID
		
		Usuario usuRetorno = null;
		String sql = "SELECT * FROM USUARIO WHERE NOME = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, nome);
			
			ResultSet resultado = preparador.executeQuery();
		
			if (resultado.next()) {
				usuRetorno = new Usuario();

				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setEmail(resultado.getString("email"));
				usuRetorno.setSenha(resultado.getString("senha"));
				usuRetorno.setDatanasc(resultado.getString("datanasc"));
			}
			
			System.out.println("Encontrado com sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL:" + e.getMessage());
		}
		return usuRetorno;
	}
	
	public Usuario autenticacao(Usuario usuario) {//VERIFICA SE O USUARIO E SENHA ESTAO NO BD
		
		Usuario usuRetorno = null;//SETA COMO PADRAO O USURETORNO NULO CASO NAO ENCONTRE O USUARIO COM A SENHA DADA
		String sql = "SELECT * FROM USUARIO WHERE NOME = ? AND SENHA = ? AND FLAG_GRUPO='0' AND NOME !='ADefinirUsuario'";//GRUPOS NAO PODEM FAZER LOGIN
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()) {
				usuRetorno = new Usuario();

				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setEmail(resultado.getString("email"));
				usuRetorno.setSenha(resultado.getString("senha"));
				usuRetorno.setDatanasc(resultado.getString("datanasc"));
			}
			
			System.out.println("Encontrado com sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL:" + e.getMessage());
		}
		return usuRetorno;
	}
	
	public String tipoUsuario(String destinatario) {//VERIFICA SE EH UM USUARIO OU UM GRUPO
		String tipo = "";
		String sql = "SELECT FLAG_GRUPO FROM USUARIO WHERE NOME = ?";//GRUPOS NAO PODEM FAZER LOGIN
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, destinatario);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				if (resultado.getInt("flag_grupo")==1) {
					tipo = "grupo";
				}
				else {
					tipo= "usuario";
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL:" + e.getMessage());
		}
		return tipo;
	}
	
	public String GerarChaveUsuario(String nomeUSU) {
		
		String sql = "INSERT INTO CHAVE_USUARIO (chave, nomeUsuario) values (?,?)";

		UUID chave = UUID.randomUUID();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, chave.toString());//? 1
			preparador.setString(2, nomeUSU);//? 2
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Chave gerada com sucesso!");
			return chave.toString();
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
			return "Erro";
		}
		
	}
	
	public boolean ApagarChaveUsuario(String chave, String nomeUSU) {
		
		String sql = "DELETE FROM CHAVE_USUARIO WHERE chave=? AND nomeUsuario=?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, chave);//? 1
			preparador.setString(2, nomeUSU);//? 2
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Chave apagada com sucesso!");
			return true;
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
			return false;
		}
		
	}
	
	public boolean ChecaChaveUsuario(String chave, String nomeUSU) {
		
		String sql = "SELECT * FROM CHAVE_USUARIO WHERE chave=? AND nomeUsuario=?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, chave);//? 1
			preparador.setString(2, nomeUSU);//? 2
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
				
				System.out.println("Par (chave,usuario) encontrado com sucesso!");
				return true;

			}
			else {
				
				System.out.println("Erro - Par (chave, usuario) n�o encontrado!");
				return false;
				
			}
			
			}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
			return false;
		}
		
	}
	
}
