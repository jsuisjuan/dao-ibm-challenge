package metodos;
import java.util.Scanner;

import connection.connectionBancoDeDados;

public class CadastrarCategoria {

	
	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();
	public static void main(String[] args) {
		
		sintaxe.conectar();
		
		if(sintaxe.estadoConectado()) {
			System.out.println("ta conectado");
		} else {
			System.out.println("n ta conectado");
		}
		
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);

		int opcao, id;
		String nome, categoria;
		
		do {
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Adicionar categoria");
			System.out.println("2 - Atualizar categoria");
			System.out.println("3 - Deletar categoria");
			System.out.println("4 - Lista de categorias");
			System.out.println("5 - Listar produtos de acordo com a categoria");
			System.out.println("6 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite o NOME da categoria");
				nome = sc.next();
				adicionarCategoria(nome);
				break;
			case 2:
				System.out.println("Digite o Nome da categoria");
				nome = sc.next();
				System.out.println("Digte o ID da categoria que quer atualizar");
				id = sc.nextInt();
				atualizaCategoria(nome, id);
				break;
			case 3:
				System.out.println("Digite o ID da categoria para deletar");
				id = sc.nextInt();
				deletarCategoria(id);
				break;
			case 4:
				System.out.println("Categorias cadastradas:");
				listarCategorias();
				break;	
			case 5:
				System.out.println("Digite a categoria");
				categoria = sc1.nextLine();
				listarProdutosDeAcordoComACategoria(categoria);
				break;
			case 6:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}

		} while (opcao != 6);
		sc.close();
	}

	public static void adicionarCategoria(String nome) {
		if (sintaxe.estadoConectado()) {
			sintaxe.adicionarCategoria(nome);
			System.out.println("Categoria Adicionado!");

		} else {
			System.out.println("Erro ao Adicionar a categoria");
		}
	}

	public static void atualizaCategoria(String nome, int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.atualizarCategoria(nome, id);
			System.out.println("Categoria Atualizado!");

		} else {
			System.out.println("Erro ao atualizar a categoria");
		}
	}

	public static void deletarCategoria(int id) {
		if (sintaxe.estadoConectado()) {
			sintaxe.deletarCategoria(id);
			System.out.println("Categoria Deletado!");

		} else {
			System.out.println("Erro ao deletar a categoria");
		}
	}

	public static void listarCategorias() {
		if (sintaxe.estadoConectado()) {
			sintaxe.listarCategorias();
		} else {
			System.out.println("Erro ao listar categorias");
		}
	}
	
	public static void listarProdutosDeAcordoComACategoria(String categoria) {
		if (sintaxe.estadoConectado()) {
			sintaxe.listaCategoriasProdutos(categoria);
		} else {
			System.out.println("Erro ao listar produtos da categoria");
		}
	}

}