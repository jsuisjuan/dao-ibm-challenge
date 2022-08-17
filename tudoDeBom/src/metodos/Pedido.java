package metodos;

import java.util.Scanner;

import connection.connectionBancoDeDados;

public class Pedido {
	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();

//	public static void main(String[] args) {
//		Scanner sc  = new Scanner(System.in);
//		Scanner sc1  = new Scanner(System.in);
//		int opcao, idCliente, idPedido;
//		double valorTotal;
//		String endereco;
//		sintaxe.conectar();
//
//		do {
//			System.out.println("Escolha uma das opções: ");
//			System.out.println("1 - Criar pedido");
//			System.out.println("2 - Atualizar pedido");
//			System.out.println("3 - Deletar pedido");
//			System.out.println("4 - Listar pedidos");
//			System.out.println("5 - Sair");
//			opcao = sc.nextInt();
//			
//			switch (opcao) {
//			case 1:
////				sc.nextLine();
//				System.out.println("Digite o Endereco de entrega");
//				endereco = sc1.nextLine();	
//
//				System.out.println("Digite o ID do cliente");
//				idCliente = sc1.nextInt();
//
//				criarPedido(endereco, idCliente);
//				break;
//			case 2:
//				System.out.println("Digite o Enderco do pedido");
//				endereco = sc1.next();
//				System.out.println("Digte o ID do pedido que quer atualizar");
//				idPedido = sc1.nextInt();
//				atualizaPedido(endereco, idPedido);
//				break;
//			case 3:
//				System.out.println("Digite o ID do pedido para deletar");
//				idPedido = sc1.nextInt();
//				deletarPedido(idPedido);
//				break;
//			case 4:
//				System.out.println("Pedidos cadastrados:");
//				listarPedidos();
//				break;
//			case 5:
//				System.out.println("Saindo...");
//				break;
//			default:
//				System.out.println("Opção invalida");
//				break;
//			}
//
//		} while (opcao != 5);
//		sc.close();
//	}
//
//	public static void criarPedido(String endereco, int idClientee) {
//		if (sintaxe.estadoConectado()) {
//			sintaxe.criarPedido(endereco, idClientee);
//			System.out.println("Pedido Criado!");
//
//		} else {
//			System.out.println("Erro ao criar pedido");
//		}
//	}
//
//	public static void atualizaPedido(String endereco, int id) {
//		if (sintaxe.estadoConectado()) {
//			sintaxe.atualizarCategoria(endereco, id);
//			System.out.println("Pedido Atualizado!");
//
//		} else {
//			System.out.println("Erro ao atualizar o pedido");
//		}
//	}
//
//	public static void deletarPedido(int id) {
//		if (sintaxe.estadoConectado()) {
//			sintaxe.deletarPedido(id);
//			System.out.println("Pedido Deletado!");
//
//		} else {
//			System.out.println("Erro ao deletar o pedido");
//		}
//	}
//
//	public static void listarPedidos() {
//		if (sintaxe.estadoConectado()) {
//			sintaxe.listarPedido();
//		} else {
//			System.out.println("Erro ao listar pedidos");
//		}
//	}

}
