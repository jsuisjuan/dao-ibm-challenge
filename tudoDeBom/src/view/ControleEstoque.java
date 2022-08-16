package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connection.ProdutoDAO;
import objetos.Produto;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ControleEstoque {
	
	JFrame frame = new JFrame();
	JLabel lblControleDeEstoque = new JLabel("CONTROLE DE ESTOQUE");
	private JTextField tfNomeProduto;
	private JTextField tfPreco;
	private JTextField tfEstoque;
	private JTable table;
	DefaultTableModel model;
	private JTextField tfCategoria;
	private JTextField tfCodigo;
	
	
	
	ControleEstoque() {
		
		lblControleDeEstoque.setBounds(10,11,223,50);
		lblControleDeEstoque.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		frame.getContentPane().add(lblControleDeEstoque);
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(690,490);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(10, 72, 264, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbCodigo = new JLabel("C\u00F3digo: ");
		lbCodigo.setBounds(10, 16, 49, 14);
		panel.add(lbCodigo);
		
		JLabel lbNomeProduto = new JLabel("Nome: ");
		lbNomeProduto.setBounds(10, 41, 49, 14);
		panel.add(lbNomeProduto);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o: ");
		lblPreo.setBounds(10, 66, 49, 14);
		panel.add(lblPreo);
		
		JLabel lbEstoque = new JLabel("Estoque: ");
		lbEstoque.setBounds(10, 116, 71, 14);
		panel.add(lbEstoque);
		
		JLabel lbCategoria = new JLabel("Categoria: ");
		lbCategoria.setBounds(10, 91, 71, 14);
		panel.add(lbCategoria);
		
		JCheckBox cbDisponibilidade = new JCheckBox("Disponibilidade");
		cbDisponibilidade.setBounds(10, 158, 110, 23);
		panel.add(cbDisponibilidade);
		
		JCheckBox cbRemedio = new JCheckBox("Rem\u00E9dio");
		cbRemedio.setBounds(10, 184, 110, 23);
		panel.add(cbRemedio);
		
		JCheckBox cbGenerico = new JCheckBox("Gen\u00E9rico");
		cbGenerico.setBounds(10, 210, 110, 23);
		panel.add(cbGenerico);
		
		
		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(80, 11, 150, 20);
		panel.add(tfCodigo);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(80, 38, 150, 20);
		panel.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(80, 63, 150, 20);
		panel.add(tfPreco);
		
		tfEstoque = new JTextField();
		tfEstoque.setColumns(10);
		tfEstoque.setBounds(80, 113, 150, 20);
		panel.add(tfEstoque);
		
		tfCategoria = new JTextField();
		tfCategoria.setColumns(10);
		tfCategoria.setBounds(80, 89, 150, 20);
		panel.add(tfCategoria);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 72, 382, 367);
		frame.getContentPane().add(scrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				tfCodigo.setText(model.getValueAt(i, 0).toString());
				tfNomeProduto.setText(model.getValueAt(i, 1).toString());
				tfEstoque.setText(model.getValueAt(i, 2).toString());
				tfPreco.setText(model.getValueAt(i, 3).toString());
				tfCategoria.setText(model.getValueAt(i, 4).toString());
				
				String disponibilidade = model.getValueAt(i, 5).toString();
				String remedio = model.getValueAt(i, 6).toString();
				String generico = model.getValueAt(i, 7).toString();
				
				if(disponibilidade == "true") {
					cbDisponibilidade.setSelected(true);
				} else {
					cbDisponibilidade.setSelected(false);
				}
				
				if(remedio == "true") {
					cbRemedio.setSelected(true);
				} else {
					cbRemedio.setSelected(false);
				}
				
				if(remedio == "true") {
					cbRemedio.setSelected(true);
				} else {
					cbRemedio.setSelected(false);
				}
				
				if(generico == "true") {
					cbGenerico.setSelected(true);
				} else {
					cbGenerico.setSelected(false);
				}
			}
		});
		
		model = new DefaultTableModel();
		Object[] column = {"Código", "Nome", "Estoque", "Preço", "Categoria", "Disponibilidade", "Remédio", "Genérico"};
		final Object[] row = new Object[8];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		frame.setVisible(true);
				
		listarProdutos();
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfNomeProduto.getText().equals("") || tfPreco.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor, insira todas as informações!");
				} else {
					int estoque;
					double preco;
					String nome, categoria;
					boolean disponibilidade, remedio, generico;
					
					nome = tfNomeProduto.getText();
					estoque = Integer.parseInt(tfEstoque.getText());
					preco = Double.parseDouble(tfPreco.getText());
					categoria = tfCategoria.getText();
					
					if (tfEstoque.getText().equals("")) {
						row[1] = "0";
					}

					if (cbDisponibilidade.isSelected()) {
						disponibilidade = true;
					} else {
						disponibilidade = false;
					}
					
					if (cbRemedio.isSelected()) {
						remedio = true;
					} else {
						remedio = false;
					}
					
					if (cbGenerico.isSelected()) {
						generico = true;
					} else {
						generico = false;
					}
					
					Produto objetoProduto = new Produto();
					
					objetoProduto.setNome(nome);
					objetoProduto.setEstoque(estoque);
					objetoProduto.setPreco(preco);
					objetoProduto.setCategoria(categoria);
					objetoProduto.setDisponibilidade(disponibilidade);
					objetoProduto.setRemedio(remedio);
					objetoProduto.setGenerico(generico);
					
					ProdutoDAO objetoProdutoDAO = new ProdutoDAO();
					objetoProdutoDAO.cadastrarProduto(objetoProduto);
					listarProdutos();
					
					tfCodigo.setText("");
					tfNomeProduto.setText("");
					tfPreco.setText("");
					tfEstoque.setText("");
					tfCategoria.setText("");
					cbDisponibilidade.setSelected(false);
					cbRemedio.setSelected(false);
					cbGenerico.setSelected(false);
					JOptionPane.showMessageDialog(null, "Cadastro do produto realizado com sucesso!");
				}
			}
		});
		btnCadastrar.setBounds(10, 292, 96, 23);
		panel.add(btnCadastrar);
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id, estoque;
				double preco;
				String nome, categoria;
				boolean disponibilidade, remedio, generico;
				
				id = Integer.parseInt(tfCodigo.getText());
				nome = tfNomeProduto.getText();
				estoque = Integer.parseInt(tfEstoque.getText());
				preco = Double.parseDouble(tfPreco.getText());
				categoria = tfCategoria.getText();

				if (cbDisponibilidade.isSelected()) {
					disponibilidade = true;
				} else {
					disponibilidade = false;
				}
				
				if (cbRemedio.isSelected()) {
					remedio = true;
				} else {
					remedio = false;
				}
				
				if (cbGenerico.isSelected()) {
					generico = true;
				} else {
					generico = false;
				}
				
				Produto objetoProduto = new Produto();
				
				objetoProduto.setId(id);
				objetoProduto.setNome(nome);
				objetoProduto.setEstoque(estoque);
				objetoProduto.setPreco(preco);
				objetoProduto.setCategoria(categoria);
				objetoProduto.setDisponibilidade(disponibilidade);
				objetoProduto.setRemedio(remedio);
				objetoProduto.setGenerico(generico);
				
				ProdutoDAO objetoProdutoDAO = new ProdutoDAO();
				objetoProdutoDAO.atualizarProduto(objetoProduto);
				listarProdutos();
				
				tfCodigo.setText("");
				tfNomeProduto.setText("");
				tfPreco.setText("");
				tfEstoque.setText("");
				tfCategoria.setText("");
				cbDisponibilidade.setSelected(false);
				cbRemedio.setSelected(false);
				cbGenerico.setSelected(false);
				JOptionPane.showMessageDialog(null, "Atualização do produto realizado com sucesso!");
			}
		});
		btnAtualizar.setBounds(109, 292, 104, 23);
		panel.add(btnAtualizar);
		
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				
				id = Integer.parseInt(tfCodigo.getText());
				
				Produto objetoProduto = new Produto();
				
				objetoProduto.setId(id);
				
				ProdutoDAO objetoProdutoDAO = new ProdutoDAO();
				objetoProdutoDAO.removerProduto(objetoProduto);
				listarProdutos();
				
				tfCodigo.setText("");
				tfNomeProduto.setText("");
				tfPreco.setText("");
				tfEstoque.setText("");
				tfCategoria.setText("");
				cbDisponibilidade.setSelected(false);
				cbRemedio.setSelected(false);
				cbGenerico.setSelected(false);
				JOptionPane.showMessageDialog(null, "Remoção do produto realizado com sucesso!");
			}
		});
		btnRemover.setBounds(10, 326, 96, 23);
		panel.add(btnRemover);
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCodigo.setText("");
				tfNomeProduto.setText("");
				tfPreco.setText("");
				tfEstoque.setText("");
				tfCategoria.setText("");
				cbRemedio.setSelected(false);
				cbGenerico.setSelected(false);
			}
		});
		btnLimpar.setBounds(109, 326, 104, 23);
		panel.add(btnLimpar);
		
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		btnNewButton.setBounds(10, 258, 203, 23);
		panel.add(btnNewButton);
	}
	
	private void listarProdutos() {
		try {
			ProdutoDAO objetoProdutoDAO = new ProdutoDAO();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			ArrayList<Produto> lista = objetoProdutoDAO.listarProduto();
			
			for (int num = 0; num < lista.size(); num++) {
				model.addRow(new Object[] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getEstoque(),
						lista.get(num).getPreco(),
						lista.get(num).getCategoria(),
						lista.get(num).isDisponibilidade(),
						lista.get(num).isRemedio(),
						lista.get(num).isGenerico()
				});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Listar valores : " + e);
		}
	}
}
