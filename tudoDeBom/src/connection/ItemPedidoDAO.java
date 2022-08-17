package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.ItemPedido;
import objetos.Pedido;
import objetos.Produto;

public class ItemPedidoDAO {

	Connection conn;
	PreparedStatement pstm;
	PreparedStatement pstm2;
	PreparedStatement pstm3;
	PreparedStatement pstm4;
	PreparedStatement pstm5;
	ResultSet rs;
	ArrayList<ItemPedido> lista = new ArrayList<>();
	public static int testee;

	public void adicionarItemPedido(ItemPedido objItemPedido) {
		String query = "insert into item_pedido(quantidade, pedido_id, produto_id) values (?, ?, ?)";

		String buscaEntProd = "select * from produto where id = '" + objItemPedido.getProdutoId() + "';";

		String buscaIdPedido = "select * from pedido where id = '" + objItemPedido.getPedidoId() + "';";

		conn = new connectionBancoDeDados().conectar();

		try {
			double subTotal = 0;
			int estoque = 0;
			boolean disponivel;
			pstm = conn.prepareStatement(query);
			pstm2 = conn.prepareStatement(buscaEntProd);
			pstm3 = conn.prepareStatement(buscaIdPedido);

			pstm.setInt(1, objItemPedido.getQuantidade());
			pstm.setInt(2, objItemPedido.getPedidoId());
			pstm.setInt(3, objItemPedido.getProdutoId());

			// Entidade Produto
			rs = pstm2.executeQuery();
			if (rs.next()) {
				objItemPedido.setPreco(rs.getDouble("preco"));
				estoque = rs.getInt("estoque");
				disponivel = rs.getBoolean("disponibilidade");
				
				objItemPedido.setSubTotal(objItemPedido.getQuantidade() * objItemPedido.getPreco());
				subTotal = objItemPedido.getSubTotal();

				Produto objProduto = new Produto();
				objProduto.setEstoque(rs.getInt("estoque") - objItemPedido.getQuantidade());

				String atualizaEstoque = "update produto set estoque = '"+objProduto.getEstoque()+"' - '" + objItemPedido.getQuantidade()
						+ "' where id = '" + objItemPedido.getProdutoId() + "';";
				pstm5 = conn.prepareStatement(atualizaEstoque);
				pstm5.execute();
				pstm5.close();
			}

			// Entidade Pedido
			rs = pstm3.executeQuery();
			if (rs.next()) {
				Pedido objPedido = new Pedido();
				objPedido.setTotalPedido(rs.getDouble("total_pedido"));
				String atualizaPedido = "UPDATE pedido set total_pedido = '" + objPedido.getTotalPedido() + "' + '"
						+ subTotal + "' where id = '" + objItemPedido.getPedidoId() + "';";
				pstm4 = conn.prepareStatement(atualizaPedido);
				pstm4.execute();
				pstm4.close();
			}

			pstm.execute();
			pstm.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro itemPedido: " + e);
		}

	}

	public ArrayList<ItemPedido> listarItemPedido() {
		// int mais = testee;

		// String query = "select * from item_pedido, pedido where
		// item_pedido.pedido_id=pedido.pedido_id and pedido.cliente_id=" + mais +"";
		String query = "select * from item_pedido";

		conn = new connectionBancoDeDados().conectar();

		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ItemPedido objpitemedido = new ItemPedido();

				objpitemedido.setId(rs.getInt("id"));
				objpitemedido.setQuantidade(rs.getInt("quantidade"));
				objpitemedido.setSubTotal(rs.getDouble("sub_total"));
				objpitemedido.setPedidoId(rs.getInt("pedido_id"));
				objpitemedido.setProdutoId(rs.getInt("produto_id"));

				lista.add(objpitemedido);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ItemPedidoDAO Listar: " + e);
		}

		return lista;
	}
}
