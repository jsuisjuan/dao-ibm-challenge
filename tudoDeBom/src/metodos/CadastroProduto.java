package metodos;
import java.util.Scanner;

import connection.connectionBancoDeDados;

public class CadastroProduto {
	
	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nome;
		int opcao, idCategoria, idProduto, estoque;
		double preco;
		sintaxe.conectar();
		boolean disponibilidade, descontoFlag, remedioFlag;
		do {
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Adicionar produto");
			System.out.println("2 - Atualizar produto");
			System.out.println("3 - Deletar produto");
			System.out.println("4 - Lista de produtos");
			System.out.println("5 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite o ID da categoria");
				idCategoria = sc.nextInt();
				System.out.println("Digite o Nome do produto");
				nome = sc.next();
				System.out.println("Digite a quantidade do ESTOQUE do produto");
				estoque = sc.nextInt();
				System.out.println("Digite o PREÇO do produto");
				preco = sc.nextDouble();
				System.out.println("Digite TRUE ou FALSE se o produto esta DISPONIVEL");
				disponibilidade = sc.nextBoolean();
				System.out.println("Digite TRUE ou FALSE se o produto esta com Desconto se for um item qualquer");
				descontoFlag = sc.nextBoolean();
				System.out.println("Digite TRUE ou FALSE se o produto for um remedio generico");
				remedioFlag =sc.nextBoolean();
				adicionarProduto(idCategoria, nome, estoque, preco, disponibilidade, descontoFlag, remedioFlag);
				break;
			case 2:
				System.out.println("Digite o ID da categoria");
				idCategoria = sc.nextInt();
				System.out.println("Digite o Nome do produto");
				nome = sc.next();
				System.out.println("Digite a quantidade do ESTOQUE do produto");
				estoque = sc.nextInt();
				System.out.println("Digite o PREÇO do produto");
				preco = sc.nextDouble();
				System.out.println("Digite TRUE ou FALSE se o produto esta DISPONIVEL");
				disponibilidade = sc.nextBoolean();
				System.out.println("Digite TRUE ou FALSE se o produto esta com Desconto se for um item qualquer");
				descontoFlag = sc.nextBoolean();
				System.out.println("Digite TRUE ou FALSE se o produto for um remedio generico");
				remedioFlag =sc.nextBoolean();
				System.out.println("Digite o ID do produto");
				idProduto = sc.nextInt();
				atualizarProduto(idCategoria, nome, estoque, preco, disponibilidade, descontoFlag, remedioFlag, idProduto);
				break;
			case 3:
				System.out.println("Digite o id do produto para deletar");
				idProduto = sc.nextInt();
				deletarProduto(idProduto);
				break;
			case 4:
				System.out.println("Produtos Cadastrados:");
				listarProdutos();
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
	
	public static void adicionarProduto(int id, String nome, int estoque, double preco, boolean disponibilidade,
			boolean descontoFlag, boolean remedioFlag) {
		if (sintaxe.estadoConectado()) {
			//sintaxe.adicionarProduto(id, nome, estoque, preco, disponibilidade, descontoFlag, remedioFlag);
			System.out.println("Produto Adicionado!");
		} else {
			System.out.println("Erro ao Adicionar o produto");
		}
	}
	
	public static void atualizarProduto(int categoriaId, String nome, int estoque, double preco, boolean disponibilidade,
			boolean descontoFlag, boolean remedioFlag, int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.atualizarProduto(categoriaId, nome, estoque, preco, disponibilidade, descontoFlag, remedioFlag, id);
			System.out.println("Produto Atualizado!");
		} else {
			System.out.println("Erro ao Atualizar o produto");
		}
	}
	
	public static void deletarProduto(int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.deletarCategoria(id);
			System.out.println("Produto Deletado!");

		} else {
			System.out.println("Erro ao deletar o produto");
		}
	}
	
	public static void listarProdutos() {
		if (sintaxe.estadoConectado()) {
			sintaxe.listarProdutos();
		} else {
			System.out.println("Erro ao listar produtos");
		}
	}
	
	
}