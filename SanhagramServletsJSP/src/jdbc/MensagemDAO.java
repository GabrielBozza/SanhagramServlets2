package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Mensagem;
import bean.Usuario;

public class MensagemDAO {
	
	private Connection conexao = Conexao_BD.getConnection();

	public void enviar(Mensagem mensagem) {
		
		String sql = "INSERT INTO MENSAGENS (remetente, destinatario, texto_mensagem) values (?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, mensagem.getRemetente());//? 1
			preparador.setString(2, mensagem.getDestinatario());//? 2
			preparador.setString(3, mensagem.getTexto_mensagem());//? 3
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Mensagem enviada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public void deletar(Integer idmensagem) {
		
		String sql = "DELETE FROM MENSAGENS WHERE IDMENSAGENS = ?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, idmensagem);//? 1
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Mensagem excluída com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public List<Mensagem> buscarMensagens(String remetente, String destinatario) {//BUSCA E RETORNA TODAS AS MENSAGENS ENTRE DUAS PESSOAS E ORDENA POR DATA_ENVIO
		
		String sql = "SELECT * FROM MENSAGENS WHERE ((REMETENTE=? AND DESTINATARIO=?) OR (REMETENTE=? AND DESTINATARIO=?)) ORDER BY DATA_ENVIO";
		List<Mensagem> lista = new ArrayList<Mensagem>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, remetente);//? 1
			preparador.setString(2, destinatario);//? 2
			preparador.setString(3, destinatario);//? 3
			preparador.setString(4, remetente);//? 4
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				Mensagem prox_mensagem = new Mensagem();
				
				prox_mensagem.setIdmensagem(resultados.getInt("idmensagens"));
				prox_mensagem.setRemetente(resultados.getString("remetente"));
				prox_mensagem.setDestinatario(resultados.getString("destinatario"));
				prox_mensagem.setTexto_mensagem(resultados.getString("texto_mensagem"));
				prox_mensagem.setData_envio(resultados.getDate("data_envio"));
				
				lista.add(prox_mensagem);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<String> buscarRecentes(Mensagem mensagem) {//BUSCA E RETORNA OS AMIGOS QUE CONVERSARAM COM O USUARIO ORDENADOS DO MAIS RECENTE PARA O MAIS ANTIGO
		
		String sql = "SELECT DISTINCT REMETENTE FROM MENSAGENS WHERE DESTINATARIO=? ORDER BY DATA_ENVIO";
		List<String> lista = new ArrayList<String>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, mensagem.getDestinatario());//? 1
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				String prox_amigo = resultados.getString("remetente");
				lista.add(prox_amigo);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}

}
