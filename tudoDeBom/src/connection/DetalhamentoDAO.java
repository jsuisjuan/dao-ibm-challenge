package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.DetalhePedido;
import objetos.ItemPedido;
import objetos.Pedido;

public class DetalhamentoDAO {

	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<DetalhePedido> lista = new ArrayList<>();
	public static int idCliente;
	
	public static int pegarCliente(Pedido idcliente) {
		idCliente = idcliente.getClienteId();
		return idCliente;
	};
	
	public  ArrayList<DetalhePedido> detalhamentoPedido() {
		int clienteId = idCliente;
		String query = " select * from pedido inner join item_pedido on  pedido.pedido_id  = item_pedido.pedido_id inner join produto on produto.id = item_pedido.produto_id inner join cliente on cliente.id = pedido.cliente_id where pedido.pedido_id = '"+clienteId+"';";
	
		conn = new connectionBancoDeDados().conectar();

		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				DetalhePedido objItemPedido = new DetalhePedido();
				
				objItemPedido.setNomeProduto(rs.getString("produto.nome"));
				objItemPedido.setQuantidade(rs.getInt("quantidade"));
				objItemPedido.setPreco(rs.getDouble("produto.preco"));
				objItemPedido.setSubTotal(rs.getDouble("sub_total"));
				objItemPedido.setIdCliente(rs.getInt("cliente.id"));
				objItemPedido.setIdPedido(rs.getInt("pedido_id"));
//				objItemPedido.setTotal(rs.getDouble("total_pedido"));
				
				lista.add(objItemPedido);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DetalhamentoDAO erro: " + e);

		}
		return lista;
	
	}
}
