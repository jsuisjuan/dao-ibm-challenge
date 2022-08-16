package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetos.Produto;

public class ProdutoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();
	
	public void cadastrarProduto(Produto objproduto) {
		String query = "insert into produto (nome, estoque, preco, categoria, disponibilidade, generico, remedio) values (?, ?, ?, ?, ?, ?, ?)";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, objproduto.getNome());
			pstm.setInt(2, objproduto.getEstoque());
			pstm.setDouble(3, objproduto.getPreco());
			pstm.setString(4, objproduto.getCategoria());
			pstm.setBoolean(5, objproduto.isDisponibilidade());
			pstm.setBoolean(6, objproduto.isRemedio());
			pstm.setBoolean(7, objproduto.isGenerico());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Cadastrar: " + e);
		}
	}
	
	public ArrayList<Produto> listarProduto() {
		String query = "select * from produto";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Produto objetoProduto = new Produto();
				
				objetoProduto.setId(rs.getInt("id"));
				objetoProduto.setNome(rs.getString("nome"));
				objetoProduto.setEstoque(rs.getInt("estoque"));
				objetoProduto.setPreco(rs.getDouble("preco"));
				objetoProduto.setCategoria(rs.getString("categoria"));
				objetoProduto.setDisponibilidade(rs.getBoolean("disponibilidade"));
				objetoProduto.setRemedio(rs.getBoolean("remedio"));
				objetoProduto.setGenerico(rs.getBoolean("generico"));
				
				lista.add(objetoProduto);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Listar: " + e);
		}
		
		return lista;
	}
	
	public void atualizarProduto(Produto objproduto) {
		String query = "update produto set nome = ?, estoque = ?, preco = ?, categoria = ?, disponibilidade = ?, remedio = ?, generico = ? where id = ?";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, objproduto.getNome());
			pstm.setInt(2, objproduto.getEstoque());
			pstm.setDouble(3, objproduto.getPreco());
			pstm.setString(4, objproduto.getCategoria());
			pstm.setBoolean(5, objproduto.isDisponibilidade());
			pstm.setBoolean(6, objproduto.isRemedio());
			pstm.setBoolean(7, objproduto.isGenerico());
			pstm.setInt(8, objproduto.getId());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Atualizar: " + e);
		}
	}
	
	public void removerProduto(Produto objproduto) {
		String query = "delete from produto where id = ?";
		
		conn = new connectionBancoDeDados().conectar();
		
		try {
			
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, objproduto.getId());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Remover: " + e);
		}
	}
}
