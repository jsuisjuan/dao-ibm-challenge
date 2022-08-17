package connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.Cliente;
import objetos.DetalhePedido;
import objetos.Pedido;

public class PedidoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Pedido> lista = new ArrayList<>();
	
	public static Pedido teste = new Pedido();
	public static int testee;
	public static String nome;
	public static double thisTotal;
	
	public void cadastrarPedido(Pedido objPedido) {
		String query = "insert into pedido (endereco_entrega, cliente_id, data_pedido) values (?, ?, ?)";
		conn = new connectionBancoDeDados().conectar();
		
		try {
		pstm = conn.prepareStatement(query);
		pstm.setString(1, objPedido.getEnderecoEntrega());
		pstm.setInt(2, objPedido.getClienteId());
		pstm.setDate(3, new Date(objPedido.getDataPedido().getTime()));
		
		pstm.execute();
		pstm.close();
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Pedido não cadastrou: " + e);
		}
	}
	
	public static int pegarCliente(Pedido idcliente) {
		testee = idcliente.getClienteId();
		return testee;
	};
	
	public static String pegarNomeCliente(Cliente idcliente) {
		nome = idcliente.getNome();
		return nome;		
	};
	
	public String pegarNome() {
		String mais = nome;
		return mais;
	};
	
	public ArrayList<Pedido> listarPedidoEspCliente() {
		int mais = testee;
		
		String query = "select * from pedido, cliente where pedido.cliente_id=cliente.id and pedido.cliente_id=" + mais +"";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Pedido objpedido = new Pedido();
				
				objpedido.setId(rs.getInt("pedido_id"));
				objpedido.setEnderecoEntrega(rs.getString("endereco_entrega"));
				objpedido.setDataPedido(rs.getDate("data_pedido"));
				objpedido.setTotalPedido(rs.getDouble("total_pedido"));
				objpedido.setClienteId(rs.getInt("cliente_id"));
				
				lista.add(objpedido);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "PedidoDAO Listar: " + e);
		}
		
		return lista;
	};
	
	public ArrayList<Pedido> listarPedidos() {
		
		
		String query = "select * from pedido;";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Pedido objpedido = new Pedido();
				
				objpedido.setId(rs.getInt("pedido_id"));
				objpedido.setEnderecoEntrega(rs.getString("endereco_entrega"));
				objpedido.setDataPedido(rs.getDate("data_pedido"));
				objpedido.setTotalPedido(rs.getDouble("total_pedido"));
				objpedido.setClienteId(rs.getInt("cliente_id"));
				
				lista.add(objpedido);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "PedidoDAO Listar: " + e);
		}
		
		return lista;
	};
	
	public String receberPedidoCliente(String lbNomeClientePedido) {
		String nomeCliente;
		String query = "select nome from pedido, cliente where pedido.cliente_id=cliente.id";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			nomeCliente = query;
			return nomeCliente;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "PedidoDAO Listar: " + e);
		}
		
		return null;
	};
	
	
}
