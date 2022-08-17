package metodos;

import java.util.Scanner;

import connection.connectionBancoDeDados;

public class ItemPedido {
	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		Scanner sc1  = new Scanner(System.in);
		int opcao, idPedido, idProduto, quantidade, idItemPedido;
		double subtotal;
		sintaxe.conectar();
		
		do {
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Adicionar Item ao pedido");
			System.out.println("2 - Atualizar item pedido");
			System.out.println("3 - Deletar item pedido");
			System.out.println("4 - Listar itens pedidos");
			System.out.println("5 - Sair");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
//				sc.nextLine();
				System.out.println("Digite o id do pedido");
				idPedido = sc.nextInt();
				
				System.out.println("Digite o id do produto");
				idProduto = sc1.nextInt();

				System.out.println("Digite a quantidade");
				quantidade = sc1.nextInt();
				
				
				addItemPedido(idPedido, idProduto, quantidade);
				break;
			case 2:
				System.out.println("Digite o ID do pedido");
				idPedido = sc1.nextInt();
				System.out.println("Digte o ID do produto");
				idProduto = sc1.nextInt();
				System.out.println("Digite a quantidade");
				quantidade = sc1.nextInt();
				System.out.println("Digite o subTotal");
				subtotal = sc1.nextDouble();
				System.out.println("ID item Pedido");
				idItemPedido = sc1.nextInt();
				atualizarItemPedido(idPedido, idProduto, quantidade, subtotal, idItemPedido);
				break;
			case 3:
				System.out.println("Digite o ID do item pedido para deletar");
				idItemPedido = sc1.nextInt();
				System.out.println("Digite o ID do pedido");
				idPedido = sc1.nextInt();
				deletarItemPedido(idItemPedido, idPedido);
				break;
			case 4:
				System.out.println("Itens pedidos cadastrados:");
				listarItensPedido();
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
		} while (opcao != 5);
	}
	
	public static void addItemPedido(int pedidoId, int produtoId, int quantidade) {
		if (sintaxe.estadoConectado()) {
			sintaxe.addItemPedidoPedido(pedidoId, produtoId, quantidade);
		

		} else {
			System.out.println("Erro ao adicionar");
		}
	}
	
	public static void atualizarItemPedido(int pedidoId, int produtoId, int quantidade, double subTotal, int idItemPedido) {
		if (sintaxe.estadoConectado()) {
			sintaxe.atualizarItemPedido(pedidoId, produtoId, quantidade, subTotal, idItemPedido);
			System.out.println("Item pedido Atualizado!");

		} else {
			System.out.println("Erro ao atualizar o item pedido");
		}
	}
	
	public static void deletarItemPedido(int id, int pedidoId) {
		if (sintaxe.estadoConectado()) {
			sintaxe.deletarItemPedido(id, pedidoId);

		} else {
			System.out.println("Erro ao deletar o item pedido");
		}
	}
	
	public static void listarItensPedido() {
		if (sintaxe.estadoConectado()) {
			sintaxe.listaItensPedido();
		} else {
			System.out.println("Erro ao listar itens pedidos");
		}
	}

}