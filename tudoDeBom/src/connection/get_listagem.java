import java.lang.management.CompilationMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesII {
	
	public static void main(String[] args) {
		
		get("/", (request, response)->(
				
				String mensagem = "";
				Connection conexao = null;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conexao = DriveManager.getConnection("jdbc:mysql://localhost/ibm");
					ResultSet rs = conexao.createStatement().executeQuery("SELECT ID, NOME, CPF  FROM CLIENTE;");
					  
					while(rs.next()){
						mensagem += rs.getString("ID");
						mensagem += rs.getString("NOME");
						mensagem += rs.getString("CPF");
						
						mensagem += id + nome + cpf ;
						
					}
					
				} catch(ClassNotFoundException e) {
					System.out.println("Banco não encontrado");
					
				} catch(SQLException ex) {
					System.out.println("Ocorreu um erro no banco" + ex.getMessage());
					
				} finally {
					
					if(conexao != null) {
						conexao.close();
						
					}
				}
				 
				return mensagem;
			));
		
	}
}

8