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

	public void enviar(Mensagem mensagem) {//ENVIAR MENSAGEM CASO O DESTINATARIO EXISTA
		
		String sql ="INSERT INTO sanhagram.MENSAGENS (remetente, destinatario, texto_mensagem)\r\n" + 
		"SELECT ?,?,?\r\n" + 
		"WHERE EXISTS (SELECT NOME FROM sanhagram.USUARIO WHERE NOME = ?);";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, mensagem.getRemetente());//? 1
			preparador.setString(2, mensagem.getDestinatario());//? 2
			preparador.setString(3, mensagem.getTexto_mensagem());//? 3
			preparador.setString(4, mensagem.getDestinatario());//? 4
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Mensagem enviada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public void enviarParaGrupo(Mensagem mensagem) {//ENVIAR MENSAGEM CASO O DESTINATARIO EXISTA
		
		String sql ="INSERT INTO sanhagram.MENSAGENS (remetente, destinatario, texto_mensagem,flag_grupo)\r\n" + 
		"SELECT ?,?,?,1\r\n" + 
		"WHERE EXISTS (SELECT NOME FROM sanhagram.USUARIO WHERE NOME = ?);";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, mensagem.getRemetente());//? 1
			preparador.setString(2, mensagem.getDestinatario());//? 2
			preparador.setString(3, mensagem.getTexto_mensagem());//? 3
			preparador.setString(4, mensagem.getDestinatario());//? 4
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Mensagem enviada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public void deletar(Integer idmensagem) {//EXCLUI MENSAGEM DADO SEU ID
		
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
	
	public void UsuarioRemovido(String usuario) {//EXCLUI MENSAGEM DADO SEU ID
		
		String sql = "UPDATE MENSAGENS SET REMETENTE=? WHERE REMETENTE = ?";
		String sql2 = "UPDATE MENSAGENS SET DESTINATARIO=? WHERE DESTINATARIO = ?";
		String sql3 = "DELETE FROM MENSAGENS WHERE REMETENTE=? AND FLAG_GRUPO='1' AND TEXTO_MENSAGEM=''";//RETIRA AS REFERENCIAS DO USUARIO REMOVIDO AOS GRUPOS QUE PARTICIPAVA
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql3);
			preparador.setString(1, usuario);//? 1
		
			preparador.execute();
			preparador.close();
			
			System.out.println("Removido do grupo com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, usuario+"* (removido)");//? 1
			preparador.setString(2, usuario);//? 2
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Mensagem modificada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		try {
			PreparedStatement preparador2 = conexao.prepareStatement(sql2);
			preparador2.setString(1, usuario+"* (removido)");//? 1
			preparador2.setString(2, usuario);//? 2
			
			preparador2.execute();
			preparador2.close();
			System.out.println("Mensagem modificada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public String ChecaPertencimentoGrupo(String remetente,String nomegrupo) {//CHECA SE A MENSAGEM VAZIA - QUE INDICA QUE O USUARIO ESTA NO GRUPO AINDA EXISTE
		
		String sql = "SELECT * FROM MENSAGENS WHERE REMETENTE = ? AND DESTINATARIO = ? AND TEXTO_MENSAGEM='' ";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, remetente);//? 1
			preparador.setString(2, nomegrupo);//? 2
			ResultSet resultados = preparador.executeQuery();
			
			if(resultados.next()) {
				System.out.println("Pertence!");
				return "pertence";
			}
			else {
				System.out.println("Nao Pertence!");
				return "naopertence";
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return "erro";
		
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
				prox_mensagem.setData_envio(resultados.getString("data_envio"));
				prox_mensagem.setFlag_grupo(resultados.getInt("flag_grupo"));
				
				lista.add(prox_mensagem);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<Mensagem> buscarMensagensGrupo(String remetente, String destinatario) {//BUSCA E RETORNA TODAS AS MENSAGENS PARA O GRUPO A PARTIR DO MOMENTO QUE ELE FOI ADD E ORDENA POR DATA_ENVIO
		
		String sql="SELECT * FROM sanhagram.MENSAGENS WHERE DESTINATARIO=? AND IDMENSAGENS>(SELECT IDMENSAGENS FROM sanhagram.MENSAGENS WHERE DESTINATARIO=? AND REMETENTE=? AND TEXTO_MENSAGEM='') AND TEXTO_MENSAGEM!='' ORDER BY DATA_ENVIO";
		List<Mensagem> lista = new ArrayList<Mensagem>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, destinatario);//? 1
			preparador.setString(2, destinatario);//? 2
			preparador.setString(3, remetente);//? 3
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				Mensagem prox_mensagem = new Mensagem();
				
				prox_mensagem.setIdmensagem(resultados.getInt("idmensagens"));
				prox_mensagem.setRemetente(resultados.getString("remetente"));
				prox_mensagem.setDestinatario(resultados.getString("destinatario"));
				prox_mensagem.setTexto_mensagem(resultados.getString("texto_mensagem"));
				prox_mensagem.setData_envio(resultados.getString("data_envio"));
				prox_mensagem.setFlag_grupo(resultados.getInt("flag_grupo"));
				
				lista.add(prox_mensagem);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<String> buscarIntegrantesGrupo(String nomeGrupo) {//LISTA TODOS OS USUARIOS PERTENCENTES AO GRUPO
		
		String sql = "SELECT DISTINCT REMETENTE FROM MENSAGENS WHERE FLAG_GRUPO='1' AND DESTINATARIO=? AND TEXTO_MENSAGEM=''";//CHECA OS INTEGRANTES PERTENCENTES
		List<String> lista = new ArrayList<String>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, nomeGrupo);//? 1
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				lista.add(resultados.getString("remetente"));				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public String SairGrupo(String nomeUsuario, String nomeGrupo) {//REMOVE USUARIO DO GRUPO
		
		String sql = "DELETE FROM MENSAGENS WHERE REMETENTE = ? AND DESTINATARIO = ? AND TEXTO_MENSAGEM=''";//APAGA SOH A MENSAGEM REFERENCIA AO GRUPO-->MENSAGEM VAZIA
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, nomeUsuario);//? 1
			preparador.setString(2, nomeGrupo);//? 2
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Usuario "+nomeUsuario+" removido do grupo "+nomeGrupo+" com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		String sql2 = "INSERT INTO sanhagram.MENSAGENS (remetente, destinatario, texto_mensagem,flag_grupo)\r\n" + 
				"SELECT ?,?,?,1\r\n" + 
				"WHERE EXISTS (SELECT NOME FROM sanhagram.USUARIO WHERE NOME = ?);";
		
		try {
			PreparedStatement preparador2 = conexao.prepareStatement(sql2);
			preparador2.setString(1, nomeGrupo);//? 1
			preparador2.setString(2, nomeGrupo);//? 2
			preparador2.setString(3, nomeUsuario+" saiu do grupo");//? 3
			preparador2.setString(4, nomeGrupo);//? 4
			
			preparador2.execute();
			preparador2.close();
			
			System.out.println("Mensagem de saida do grupo enviada com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		String sql3 = "SELECT COUNT(*) FROM MENSAGENS WHERE destinatario=? AND flag_grupo=? AND texto_mensagem=?;";
		
		try {
			PreparedStatement preparador3 = conexao.prepareStatement(sql3);
			preparador3.setString(1, nomeGrupo);//? 1
			preparador3.setString(2, "1");//? 2
			preparador3.setString(3, "");//? 3

			ResultSet resultados = preparador3.executeQuery();

			if(resultados.next()) {
				if(resultados.getString("COUNT(*)").equals("0")) {//GRUPO FICOU VAZIO
					System.out.println("Grupo vazio!");
					
					String sql4 = "DELETE FROM MENSAGENS WHERE (destinatario=? OR remetente=?) AND flag_grupo=?;";
					
					try {
						PreparedStatement preparador4 = conexao.prepareStatement(sql4);
						preparador4.setString(1, nomeGrupo);//? 1
						preparador4.setString(2, nomeGrupo);//? 2
						preparador4.setString(3, "1");//? 3
						
						preparador4.execute();
						preparador4.close();
						
						System.out.println("Mensagens do grupo vazio excluídas com sucesso!");
						return "GRUPOVAZIO";
					}
					catch (SQLException e ){
						System.out.println("Erro - " + e.getMessage());
					}
				}
				else {
					System.out.println("Grupo com "+resultados.getString("COUNT(*)")+" usuário(s)");
					
					String sql5 = "DELETE FROM sanhagram.mensagens WHERE idmensagens IN (SELECT * FROM (SELECT idmensagens FROM sanhagram.mensagens WHERE (idmensagens < (SELECT MIN(idmensagens) FROM sanhagram.mensagens WHERE destinatario=? AND texto_mensagem=?)) AND destinatario=? AND flag_grupo=?) TMPTBL);";
					String sql6="SET SQL_SAFE_UPDATES = 0;";
					String sql7="SET SQL_SAFE_UPDATES = 1;";
					
					try {
						PreparedStatement preparador6 = conexao.prepareStatement(sql6);
						preparador6.execute();
						preparador6.close();
						
						PreparedStatement preparador5 = conexao.prepareStatement(sql5);
						preparador5.setString(1, nomeGrupo);//? 1
						preparador5.setString(2, "");//? 2
						preparador5.setString(3, nomeGrupo);//? 3
						preparador5.setString(4, "1");//? 4
						
						preparador5.execute();
						preparador5.close();
						
						PreparedStatement preparador7 = conexao.prepareStatement(sql7);
						preparador7.execute();
						preparador7.close();
						
						System.out.println("Mensagens inacessíveis do grupo excluídas com sucesso!");
					}
					catch (SQLException e ){
						System.out.println("Erro - " + e.getMessage());
					}
					return "GRUPONAOVAZIO";
				}
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return "GRUPONAOVAZIO";
		
	}
	
	public void ExcluirMensagensGrupoExcluido(String nomeGrupo) {
		
		String sql = "DELETE FROM sanhagram.mensagens WHERE (remetente=? OR destinatario=?) AND flag_grupo=?";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, nomeGrupo);//? 1
			preparador.setString(2, nomeGrupo);//? 3
			preparador.setString(3, "1");//? 4
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Todas as Mensagens do grupo foram excluídas com sucesso!");
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
	}
	
	public List<String> buscarRecentes(String destinatario) {//BUSCA E RETORNA OS AMIGOS QUE CONVERSARAM COM O USUARIO ORDENADOS DO MAIS RECENTE PARA O MAIS ANTIGO
		
		String sql = "SELECT DISTINCT amigo FROM (SELECT REMETENTE AS amigo,DATA_ENVIO FROM sanhagram.MENSAGENS \r\n" + 
				"				WHERE DESTINATARIO=?\r\n" + 
				"				UNION ALL\r\n" + 
				"				SELECT DESTINATARIO AS amigo,DATA_ENVIO FROM sanhagram.MENSAGENS\r\n" + 
				"				WHERE REMETENTE=? AND DESTINATARIO!='ADefinirUsuario' AND FLAG_GRUPO='0'\r\n" + 
				"                UNION ALL\r\n" + 
				"                SELECT DESTINATARIO AS amigo,DATA_ENVIO FROM sanhagram.MENSAGENS WHERE FLAG_GRUPO='1' AND DESTINATARIO IN (SELECT DESTINATARIO FROM sanhagram.MENSAGENS\r\n" + 
				"                WHERE REMETENTE=? AND FLAG_GRUPO='1' AND TEXTO_MENSAGEM='')\r\n" + 
				"                ORDER BY DATA_ENVIO DESC) AS amigo";
		List<String> lista = new ArrayList<String>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, destinatario);//? 1
			preparador.setString(2, destinatario);//? 1
			preparador.setString(3, destinatario);//? 1
			ResultSet resultados = preparador.executeQuery();
			
			while(resultados.next()){
				String prox_amigo = resultados.getString("amigo");
				lista.add(prox_amigo);				
			}
		}
		catch (SQLException e ){
			System.out.println("Erro - " + e.getMessage());
		}
		
		return lista;
		
	}

}
