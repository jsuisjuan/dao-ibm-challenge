package tudDeBom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class connectionBancoDeDados {

	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultSet = null;

	public void conectar() {
		// Criacao da variavel para conctar ao database
		String servidor = "jdbc:mysql://localhost/tudodebom";
		String usuario = "root";
		String senha = "Carvalho.2004";
		String driver = "com.mysql.cj.jdbc.Driver";

		// Permite verificar o tratamento da excecao caso ok banco de dados
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public boolean estadoConectado() {
		if (this.connection != null) {
			return true;
		} else {
			return false;
		}

	}
	
	public void listaClientes() {
		try {
			String query = "Select * from cliente";
			this.resultSet=this.statement.executeQuery(query);
			this.statement=this.connection.createStatement();
			
			while(this.resultSet.next()) {
				String meuId = resultSet.getString("id");
				String meuNome = resultSet.getString("nome");
				
				System.out.println("id: " + meuId + "\n" + "nome: " + meuNome);
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

}
