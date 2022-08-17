package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import objetos.Produto;

public class ProdutoDAO {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();

	public void cadastrarProduto(Produto objproduto) {
		String query = "insert into produto (nome, estoque, preco, categoria, disponibilidade, generico, remedio, preco_descontado) values (?, ?, ?, ?, ?, ?, ?, ?)";

		conn = new connectionBancoDeDados().conectar();

		try {

			pstm = conn.prepareStatement(query);
//			rs = pstm.executeQuery();
//			if(rs.next()) {
//			Produto objProduto = new Produto();
//			objProduto.setPrecoDescontado(0);
//			}
			// pega os valores que o usuario digitou
			pstm.setString(1, objproduto.getNome());
			pstm.setInt(2, objproduto.getEstoque());
			pstm.setDouble(3, objproduto.getPreco());
			pstm.setString(4, objproduto.getCategoria());
			pstm.setBoolean(5, objproduto.isDisponibilidade());
			pstm.setBoolean(6, objproduto.isRemedio());
			pstm.setBoolean(7, objproduto.isGenerico());
			pstm.setDouble(8, objproduto.getPrecoDescontado());

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

			while (rs.next()) {
				Produto objetoProduto = new Produto();

				objetoProduto.setId(rs.getInt("id"));
				objetoProduto.setNome(rs.getString("nome"));
				objetoProduto.setEstoque(rs.getInt("estoque"));
				objetoProduto.setPreco(rs.getDouble("preco"));
				objetoProduto.setCategoria(rs.getString("categoria"));
				objetoProduto.setDisponibilidade(rs.getBoolean("disponibilidade"));
				objetoProduto.setRemedio(rs.getBoolean("remedio"));
				objetoProduto.setGenerico(rs.getBoolean("generico"));
				objetoProduto.setPrecoDescontado(rs.getDouble("preco_descontado"));

				lista.add(objetoProduto);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Listar: " + e);
		}

		return lista;
	}

	public void atualizarProduto(Produto objproduto) {
		String query = "update produto set nome = ?, estoque = ?, preco = ?, categoria = ?, disponibilidade = ?, remedio = ?, generico = ?, preco_descontado = ? where id = ?";

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
			pstm.setDouble(8, objproduto.getPrecoDescontado());
//			if(objproduto.isGenerico()) {
//				pstm.setDouble(8, objproduto.getPreco() + (objproduto.getPreco()* 0.2));
//			}else {
//				pstm.setDouble(8, 0);
//			}
			
			pstm.setInt(9, objproduto.getId());

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

	public ArrayList<Produto> listaProdutosDisponibilidade(boolean disponibilidades) {
		String query = "select * from produto where disponibilidade = '"+disponibilidades+"'";
		System.out.println(disponibilidades);
		conn = new connectionBancoDeDados().conectar();

		try {
			pstm = conn.prepareStatement(query);

			rs = pstm.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setEstoque(rs.getInt("estoque"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setDisponibilidade(rs.getBoolean("disponibilidade"));
				produto.setRemedio(rs.getBoolean("remedio"));
				produto.setGenerico(rs.getBoolean("generico"));
				produto.setPrecoDescontado(rs.getDouble("preco_descontado"));

				lista.add(produto);		
			}		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Listar: " + e);

		}
		return lista;
	}
	
	public ArrayList<Produto> listaProdutosRemedios(boolean remedio) {
		String query = "select * from produto where remedio = '"+remedio+"'";
		System.out.println(remedio);
		conn = new connectionBancoDeDados().conectar();

		try {
			pstm = conn.prepareStatement(query);

			rs = pstm.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setEstoque(rs.getInt("estoque"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setDisponibilidade(rs.getBoolean("disponibilidade"));
				produto.setRemedio(rs.getBoolean("remedio"));
				produto.setGenerico(rs.getBoolean("generico"));
				produto.setPrecoDescontado(rs.getDouble("preco_descontado"));
				
				lista.add(produto);		
			}		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Listar: " + e);

		}
		return lista;
	}
	
	public ArrayList<Produto> listaProdutosGenericos(boolean generico) {
		String query = "select * from produto where remedio = '"+generico+"'";
		System.out.println(generico);
		conn = new connectionBancoDeDados().conectar();

		try {
			pstm = conn.prepareStatement(query);

			rs = pstm.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setEstoque(rs.getInt("estoque"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setDisponibilidade(rs.getBoolean("disponibilidade"));
				produto.setRemedio(rs.getBoolean("remedio"));
				produto.setGenerico(rs.getBoolean("generico"));
				produto.setPrecoDescontado(rs.getDouble("preco_descontado"));

				lista.add(produto);		
			}		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Listar: " + e);

		}
		return lista;
	}
}
