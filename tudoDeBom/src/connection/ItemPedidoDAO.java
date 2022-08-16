package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.ItemPedido;
import objetos.Pedido;

public class ItemPedidoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<ItemPedido> lista = new ArrayList<>();
	public static int testee;
	
	
	public ArrayList<ItemPedido> listarItemPedido() {
		//int mais = testee;
		
		//String query = "select * from item_pedido, pedido where item_pedido.pedido_id=pedido.pedido_id and pedido.cliente_id=" + mais +"";
		String query = "select * from item_pedido";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
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
	};
}
