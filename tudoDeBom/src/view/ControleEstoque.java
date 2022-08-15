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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ControleEstoque {
	JFrame frame = new JFrame();
	JLabel lblControleDeEstoque = new JLabel("CONTROLE DE ESTOQUE");
	private JTextField tfNomeProduto;
	private JTextField tfPreco;
	private JTextField tfEstoque;
	private JTable table;
	DefaultTableModel model;
	
	ControleEstoque() {
		lblControleDeEstoque.setBounds(10,11,223,50);
		lblControleDeEstoque.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		frame.getContentPane().add(lblControleDeEstoque);
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(649,487);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(10, 72, 223, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox cbRemedio = new JCheckBox("Rem\u00E9dio");
		cbRemedio.setBounds(10, 112, 86, 23);
		panel.add(cbRemedio);
		
		JCheckBox cbGenerico = new JCheckBox("Gen\u00E9rico");
		cbGenerico.setBounds(127, 112, 86, 23);
		panel.add(cbGenerico);
		
		JLabel lbEstoque = new JLabel("Estoque: ");
		lbEstoque.setBounds(10, 91, 49, 14);
		panel.add(lbEstoque);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o: ");
		lblPreo.setBounds(10, 66, 49, 14);
		panel.add(lblPreo);
		
		JLabel lbNomeProduto = new JLabel("Nome: ");
		lbNomeProduto.setBounds(10, 41, 49, 14);
		panel.add(lbNomeProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(63, 38, 150, 20);
		panel.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(63, 63, 150, 20);
		panel.add(tfPreco);
		
		tfEstoque = new JTextField();
		tfEstoque.setColumns(10);
		tfEstoque.setBounds(63, 88, 150, 20);
		panel.add(tfEstoque);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(243, 72, 382, 367);
		frame.getContentPane().add(scrollPane);
		
		// tabela de controle de estoque
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfNomeProduto.setText(model.getValueAt(i, 0).toString());
				tfPreco.setText(model.getValueAt(i, 1).toString());
				tfEstoque.setText(model.getValueAt(i, 2).toString());
				String remedio = model.getValueAt(i, 3).toString();
				String generico = model.getValueAt(i, 4).toString();
				System.out.println(remedio);
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
		Object[] column = {"Nome", "Preço", "Estoque", "Remédio", "Genérico"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		frame.setVisible(true);
				
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNomeProduto.getText().equals("") || tfPreco.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor, insira todas as informações!");
				} else {
					row[0] = tfNomeProduto.getText();
					row[1] = tfPreco.getText();
					row[2] = tfEstoque.getText();
					
					if (tfEstoque.getText().equals("")) {
						row[2] = "0";
					}
					
					if (cbRemedio.isSelected()) {
						row[3] = "true";
					} else {
						row[3] = "false";
					}
					
					if (cbGenerico.isSelected()) {
						row[4] = "true";
					} else {
						row[4] = "false";
					}
					
					model.addRow(row);
					
					tfNomeProduto.setText("");
					tfPreco.setText("");
					tfEstoque.setText("");
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
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.setValueAt(tfNomeProduto.getText(), i, 0);
					model.setValueAt(tfPreco.getText(), i, 1);
					model.setValueAt(tfEstoque.getText(), i, 2);
					if (cbRemedio.isSelected()) {
						model.setValueAt("true", i, 3);
					} else {
						model.setValueAt("false", i, 3);
					}
					
					if (cbGenerico.isSelected()) {
						model.setValueAt("true", i, 4);
					} else {
						model.setValueAt("false", i, 4);
					}
					model.setValueAt(cbRemedio.isSelected(), i, 3);
					model.setValueAt(cbGenerico.isSelected(), i, 4);
					JOptionPane.showMessageDialog(null, "Atualização do produto realizada com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Porfavor, selecione uma tupla!");
				}
			}
		});
		btnAtualizar.setBounds(109, 292, 104, 23);
		panel.add(btnAtualizar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.removeRow(i);
					
					tfNomeProduto.setText("");
					tfPreco.setText("");
					tfEstoque.setText("");
					cbRemedio.setSelected(false);
					cbGenerico.setSelected(false);
					
					JOptionPane.showMessageDialog(null, "Remoção do produto realizada com sucesso!");
					
				} else {
					JOptionPane.showMessageDialog(null, "Porfavor, seleciona uma tupla!");
				}
			}
		});
		btnRemover.setBounds(10, 326, 96, 23);
		panel.add(btnRemover);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNomeProduto.setText("");
				tfPreco.setText("");
				tfEstoque.setText("");
				cbRemedio.setSelected(false);
				cbGenerico.setSelected(false);
			}
		});
		btnLimpar.setBounds(109, 326, 104, 23);
		panel.add(btnLimpar);
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//colocar os filtros
			}
		});
		btnNewButton.setBounds(10, 258, 203, 23);
		panel.add(btnNewButton);
		
		
		
	}
}
