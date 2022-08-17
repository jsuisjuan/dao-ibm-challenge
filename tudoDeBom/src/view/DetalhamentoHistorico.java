package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connection.ClienteDAO;
import connection.DetalhamentoDAO;
import connection.ItemPedidoDAO;
import connection.PedidoDAO;
import objetos.Cliente;
import objetos.DetalhePedido;
import objetos.ItemPedido;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;

public class DetalhamentoHistorico {
	JFrame frame = new JFrame(); 
	
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;

	 DetalhamentoHistorico() {
		frame.setBounds(100, 100, 344, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane.setLayout(null);
		 
		JLabel lbDetalhamento = new JLabel("DETALHAMENTO");
		lbDetalhamento.setBounds(20, 23, 122, 14);
		contentPane.add(lbDetalhamento);
		 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 56, 288, 196);
		contentPane.add(scrollPane);
		 
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"Produto", "Quantidade", "Preço", "Sub-total"};
		final Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		detalhamentoPedido();
		
		
		JLabel lblNewLabel = new JLabel("Aten\u00E7\u00E3o: Medicamentos gen\u00E9ricos cont\u00EAm 20% de desconto.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(54, 41, 266, 14);
		contentPane.add(lblNewLabel);
		frame.setVisible(true);
	}
	 private void detalhamentoPedido() {
			try {
				DetalhamentoDAO objetoItemPedidoDAO = new DetalhamentoDAO();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				ArrayList<DetalhePedido> lista = objetoItemPedidoDAO.detalhamentoPedido();
				
				for (int num = 0; num < lista.size(); num++) {
					model.addRow(new Object[] {
							
							lista.get(num).getNomeProduto(),
							lista.get(num).getQuantidade(),
							lista.get(num).getPreco(),
							lista.get(num).getSubTotal(),
//							lista.get(num).getIdCliente(),
							
					});
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Listar valores : " + e);
			}
	 }

}
