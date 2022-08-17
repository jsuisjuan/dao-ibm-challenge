package metodos;

import java.util.Scanner;

import connection.connectionBancoDeDados;

public class TudoDeBom {

	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();

	public static void main(String[] args) {
		int id;
		String nome;
		String cpf;
		Scanner sc = new Scanner(System.in);
		int opcao;
		sintaxe.conectar();

		do {
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Adicionar cliente");
			System.out.println("2 - Atualizar Cliente");
			System.out.println("3 - Deletar Cliente");
			System.out.println("4 - Lista de Clientes");
			System.out.println("5 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite o NOME do cliente");
				nome = sc.next();
				System.out.println("Digite o CPF do cliente");
				cpf = sc.next();
				adicionarCliente(nome, cpf);
				break;
			case 2:
				System.out.println("Digite o nome do cliente");
				nome = sc.next();
				System.out.println("Digite o id do cliente");
				id = sc.nextInt();
				atualizaCliente(nome, id);
				break;
			case 3:
				System.out.println("Digite o id do cliente para deletar");
				id = sc.nextInt();
				deletarCliente(id);
				break;
			case 4:

				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção invalida");

				break;
			}
		} while (opcao != 5);
		sc.close();
	}

	public static void atualizaCliente(String nome, int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.atualizarCliente(nome, id);
			System.out.println("Cliente Atualizado!");

		} else {
			System.out.println("Erro ao atualizar o cliente");
		}
	}
	
	public static void deletarCliente(int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.deletarCliente(id);
			System.out.println("Cliente Deletado!");

		} else {
			System.out.println("Erro ao Deletar o cliente");
		}
	}
	
	public static void adicionarCliente(String nome, String cpf) {
		if (sintaxe.estadoConectado()) {
			sintaxe.adicionarCliente(nome, cpf);
			System.out.println("Cliente Adicionado!");

		} else {
			System.out.println("Erro ao Adicionar o cliente");
		}
	}
	
	
}