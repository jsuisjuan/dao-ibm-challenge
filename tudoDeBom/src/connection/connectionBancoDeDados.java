package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class connectionBancoDeDados {

	

	public Connection conectar() {
		Connection conn = null;
		
		try {
			String servidor = "jdbc:mysql://localhost/tudodebombd?user=root&password=Carvalho.2004";
			conn = DriverManager.getConnection(servidor);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de Conexao: " + e.getMessage());
		}
		
		return conn;
	}

	

	
	
	
	/*

	public void criarPedido(String endereco, int idCliente) {
		try {

			// linha de execução da sintaxe de insert em SQL
			String query = "INSERT INTO pedido (endereco_entrega, cliente_id) values ('" + endereco
					+ "', '" + idCliente + "');";
			System.out.println(query);
			this.statement.execute(query);
		} catch (Exception e) {
			System.out.println("Erro criar pedido: " + e.getMessage());
		}
	}

	public void atualizarPedido(String endereco, int id) {
		try {

			String query = "UPDATE pedido SET endereco_entrega = '" + endereco + "' WHERE id = '" + id + "';";
			System.out.println(query);
			this.statement.execute(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar categoria: " + e.getMessage());
		}
	}

	public void deletarPedido(int id) {
		try {
			String query = "DELETE FROM pedido WHERE id = '" + id + "' ;";
			String query2 = "SELECT * FROM item_pedido WHERE pedido_id = '"+id+"' ;";
			this.resulSet = this.statement.executeQuery(query2);
			//obrigatorio
			this.statement = this.connection.createStatement();
			
			while(this.resulSet.next()) {
				int idProduto = resulSet.getInt("produto_id");
				int quantidade = resulSet.getInt("quantidade");
				
				String query3 = "UPDATE produto SET estoque = estoque + '"+quantidade+"' WHERE id = '"+idProduto+"';";
				this.statement.execute(query3);
			}
			
			
			System.out.println(query);
			this.statement.execute(query);
		} catch (Exception e) {
			System.out.println("Erro ao deletar o id = " + id + e.getMessage());
		}
	}

	public void listarPedido() {

		try {
			// tratando a conexão do nosso retorno do select
			// o mysql não é case sensitive
			String query = "SELECT * FROM 	pedido";
			this.resulSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();

			// criando um laço de repetição para retornan os registros da nossa tabela
			while (this.resulSet.next()) {
				String id = resulSet.getString("id");
				String endereco = resulSet.getString("endereco_entrega");
				Double totalPedido = resulSet.getDouble("total_pedido");
				String idCliente = resulSet.getString("cliente_id");

				System.out.println("id: " + id + ", endereco_entrega: " + endereco + ", total_pedido: " + totalPedido
						+ " ,cliente_id: " + idCliente);
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());

		}
	}

	public void addItemPedidoPedido(int pedidoId, int produtoId, int quantidade) {
		try {
			// linha de execução da sintaxe de insert em SQL

			String query3 = "select * from produto where id = '" + produtoId + "'";
			this.resulSet = this.statement.executeQuery(query3);
			this.statement = this.connection.createStatement();
			double precoUnitario = 0;
			int estoque = 0;
			boolean disponivel = false;
			boolean remedioFlag = false;
			boolean descontoFlag = false;
			if (resulSet.next()) {
				precoUnitario = resulSet.getDouble("preco");
				estoque = resulSet.getInt("estoque");
				disponivel = resulSet.getBoolean("disponibilidade");
				remedioFlag = resulSet.getBoolean("remedio_flag");
				descontoFlag = resulSet.getBoolean("desconto_flag");
			}

			if (estoque > 0 && disponivel) {

				if (remedioFlag == true || descontoFlag == true) {
					String query = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade, sub_total) values ('"
							+ pedidoId + "', '" + produtoId + "', '" + quantidade + "', '"
							+ (quantidade * (precoUnitario -(precoUnitario * 0.2))) + "');";
					this.statement.execute(query);
					System.out.println(query);
					System.out.println("Descontado");
				} else {
					String query = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade, sub_total) values ('"
							+ pedidoId + "', '" + produtoId + "', '" + quantidade + "', '"
							+ (quantidade * precoUnitario) + "');";
					this.statement.execute(query);
					System.out.println(query);
					System.out.println("Sem Desconto");
				}

				// Atualiza o estoque
				String query2 = "UPDATE produto SET estoque = estoque - '" + quantidade + "' WHERE id = '" + produtoId
						+ "';";
				
				double subTotal = 0;
				String query6 = "SELECT * from  item_pedido WHERE pedido_id = '"+pedidoId+"';";
				this.resulSet = this.statement.executeQuery(query6);
				if(resulSet.next()) {
					 subTotal = resulSet.getDouble("sub_total");
				}

				// busca o preco total da entidade pedido
				String query4 = "select total_pedido from pedido where id = '" + pedidoId + "';";
				this.resulSet = this.statement.executeQuery(query4);
				double totalPedido = 0.0;
				if (resulSet.next()) {
					totalPedido = resulSet.getDouble("total_pedido");
				}
//				double qtd = Double.valueOf(quantidade);
//				double subTotal = (qtd * precoUnitario);
//				System.out.println(qtd + " x "+ precoUnitario + " = " + "subtotal -> "+ subTotal);
				String query5 = "UPDATE pedido SET total_pedido = '"+totalPedido+"' + '" + subTotal
						+ "'  WHERE id = '" + pedidoId + "';";

				System.out.println("QUERY5 " + query5);
//				System.out.println(query);
//				this.statement.execute(query);
				this.statement.execute(query2);
				this.statement.execute(query5);
				System.out.println("Adicionado");
				System.out.println(disponivel);
			} else {
				System.out.println("Produto com estoque 0");
			}

		} catch (Exception e) {
			System.out.println("Erro ao adicionar item pedido: " + e.getMessage());
		}
	}

	public void atualizarItemPedido(int pedidoId, int produtoId, int quantidade, double subTotal, int idItemPedido) {
		try {

			String query = "UPDATE item_pedido SET pedido_id = '" + pedidoId + "', produto_id = '" + produtoId
					+ "', quantidade = '" + quantidade + "', sub_total = '" + subTotal + "'  WHERE id = '"
					+ idItemPedido + "';";

			System.out.println(query);
			this.statement.execute(query);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar Item Pedido: " + e.getMessage());
		}
	}

	public void deletarItemPedido(int id, int pedidoId) {
		try {
			String query = "DELETE FROM item_pedido WHERE id = '" + id + "' AND pedido_id = '"+pedidoId+"';";
			String query3 = "SELECT *	FROM item_pedido WHERE id = '"+id+"';" ;
			this.resulSet = this.statement.executeQuery(query3);
			this.statement = this.connection.createStatement();
			int produtoId = 0;
			int quantidade = 0;
			System.out.println(query);
			
			
			if (this.resulSet.next()) {
				 produtoId = resulSet.getInt("produto_id");
				 quantidade = resulSet.getInt("quantidade");
//				this.statement = this.connection.createStatement();
//				this.resulSet = this.statement.executeQuery(query2);
//				System.out.println(query2);
			}
			String query2 = "UPDATE produto SET estoque = estoque + '"+quantidade+"' where id = '"+produtoId+"';";
//			this.resulSet = this.statement.executeQuery(query2);

			this.statement.executeUpdate(query2);
			this.statement.execute(query);
		} catch (Exception e) {
			System.out.println("Erro ao deletar o id = " + id + e.getMessage());
		}
	}

	public void listaItensPedido() {

		try {
			// tratando a conexão do nosso retorno do select
			// o mysql não é case sensitive
			String query = "SELECT * FROM 	item_pedido";
			this.resulSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();

			// criando um laço de repetição para retornan os registros da nossa tabela
			while (this.resulSet.next()) {
				int id = resulSet.getInt("id");
				int pedidoId = resulSet.getInt("pedido_id");
				int produtoId = resulSet.getInt("produto_id");
				int quantidade = resulSet.getInt("quantidade");
				double subTotal = resulSet.getDouble("sub_total");

				System.out.println("id: " + id + ", pedido_id: " + pedidoId + ", produto_id: " + produtoId
						+ " ,quantidade: " + quantidade + ", sub_total: " + subTotal);
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());

		}
	}

	public void listaCategoriasProdutos(String categoria) {

		try {

			PreparedStatement pst = this.connection.prepareStatement(
					"select c.tipo_categoria, p.nome, p.estoque from categoria c inner join produto p on p.categoria_id = c.id where c.tipo_categoria like '%"
							+ categoria + "%';");

			String query = "select c.tipo_categoria, p.nome, p.estoque from categoria c inner join produto p on p.categoria_id = c.id where c.tipo_categoria like '%"
					+ categoria + "%';";
			this.resulSet = this.statement.executeQuery(query);
			this.resulSet = pst.executeQuery();
			this.statement = this.connection.createStatement();

			while (this.resulSet.next()) {
				String tipoCategoria = resulSet.getString("c.tipo_categoria");
				String nomeProduto = resulSet.getString("p.nome");
				int estoqueProduto = resulSet.getInt("p.estoque");
				System.out.println(
						"Categoria: " + tipoCategoria + " | Produto: " + nomeProduto + " | Estoque: " + estoqueProduto);
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}
*/
}