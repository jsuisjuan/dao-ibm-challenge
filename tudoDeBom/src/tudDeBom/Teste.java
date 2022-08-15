package tudDeBom;

public class Teste {

	public static void main(String[] args) {
		connectionBancoDeDados sintaxeBanco = new connectionBancoDeDados();
		sintaxeBanco.conectar();
		
		if(sintaxeBanco.estadoConectado()) {
			System.out.println("Conexao com o banco de dados foi com exito\n");
			sintaxeBanco.listaClientes();
		} else {
			System.out.println("Nao foi possivel conectar ao bancp de dados");
		}
		
	}
	
	
		
}
