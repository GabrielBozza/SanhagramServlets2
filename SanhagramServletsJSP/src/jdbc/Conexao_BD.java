package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_BD {

	public static Connection getConnection() {

		Connection conexao = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanhagram?user=root&password=34Csgmdbzza13sjc");
			//System.out.println("Conexao estabelecida");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro Conexao " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro Driver " + e.getMessage());
		}
		return conexao;
	}
}
