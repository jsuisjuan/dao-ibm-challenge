package tudDeBom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class connectionBancoDeDados {

	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resulSet = null;

	public void conectar() {
		// Criação da variavel para conctar ao database
		String servidor = "jdbc:mysql://localhost/ibm";
		String usuario = "root";
		String senha = "root";
		String driver = "com.mysql.cj.jdbc.Driver";

		// Permite verificar o tratamento da exceção caso ok banco de dados
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

}
