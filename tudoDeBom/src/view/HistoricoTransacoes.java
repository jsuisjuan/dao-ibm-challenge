package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoricoTransacoes {
	JFrame frame = new JFrame(); 
	JScrollPane scroll = new JScrollPane();
	JLabel lblHistricoDeTransaes = new JLabel("HIST\u00D3RICO DE TRANSA\u00C7\u00D5ES");
	DefaultTableModel model;
	private JTable table;
	
	/**
	 * Create the frame.
	 */
	HistoricoTransacoes() {
		lblHistricoDeTransaes.setBounds(20,21,281,50);
		lblHistricoDeTransaes.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		frame.getContentPane().add(lblHistricoDeTransaes);
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setBackground(UIManager.getColor("menu"));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(336,541);
		frame.getContentPane().setLayout(null);
		
		JLabel lbCliente = new JLabel("Cliente: ");
		lbCliente.setBounds(20, 82, 49, 14);
		frame.getContentPane().add(lbCliente);
		
		JLabel lbNomeCliente = new JLabel("Juan Carvalho Silva de Lima");
		lbNomeCliente.setBounds(79, 82, 204, 14);
		frame.getContentPane().add(lbNomeCliente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 148, 281, 325);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"Pedido", "Data", "Total"};
		final Object[] row = new Object[3];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Detalhamento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalhamentoHistorico detalhaHistorico = new DetalhamentoHistorico();
			}
		});
		btnNewButton.setBounds(20, 114, 126, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
}
