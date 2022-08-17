package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import connection.PedidoDAO;
import connection.ProdutoDAO;
import objetos.Pedido;
import objetos.Produto;

public class CriarPedido {

	JFrame frame = new JFrame();
	JLabel lblCriarPedido = new JLabel("CRIAR PEDIDO");
	private JTextField tfEndereco;
	private JTextField tfIdCliente;
	private JTable table;
	DefaultTableModel model;
	private JTextField tfCodigo;

	CriarPedido() {

		lblCriarPedido.setBounds(10, 11, 223, 50);
		lblCriarPedido.setFont(new Font("Dialog", Font.PLAIN, 16));

		frame.getContentPane().add(lblCriarPedido);
		frame.getContentPane().setBackground(new Color(173, 216, 230));

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1155, 490);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(10, 72, 264, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lbCodigo = new JLabel("C\u00F3digo: ");
		lbCodigo.setBounds(10, 16, 49, 14);
		panel.add(lbCodigo);

		JLabel lbEndereco = new JLabel("Endere\u00E7o");
		lbEndereco.setBounds(10, 41, 49, 14);
		panel.add(lbEndereco);

		JLabel lblClienteId = new JLabel("ID Cliente:");
		lblClienteId.setBounds(10, 66, 61, 14);
		panel.add(lblClienteId);

		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(80, 11, 150, 20);
		panel.add(tfCodigo);

		tfEndereco = new JTextField();
		tfEndereco.setBounds(80, 38, 150, 20);
		panel.add(tfEndereco);
		tfEndereco.setColumns(10);

		tfIdCliente = new JTextField();
		tfIdCliente.setColumns(10);
		tfIdCliente.setBounds(80, 63, 150, 20);
		panel.add(tfIdCliente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 72, 845, 367);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();

				tfCodigo.setText(model.getValueAt(i, 0).toString());
				tfEndereco.setText(model.getValueAt(i, 1).toString());
				tfIdCliente.setText(model.getValueAt(i, 2).toString());

			}
		});

		model = new DefaultTableModel();
		Object[] column = { "Código", "Data Pedido", "Endereco", "Id Cliente", "Total Pedido" };
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		frame.setVisible(true);

		listarPedidos();

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfEndereco.getText().equals("") || tfIdCliente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor, insira todas as informações!");
				} else {
					int idCliente;
					String endereco;

					endereco = tfEndereco.getText();

					idCliente = Integer.parseInt(tfIdCliente.getText());

					Pedido objPedido = new Pedido();

					objPedido.setEnderecoEntrega(endereco);
			
					objPedido.setClienteId(idCliente);
					objPedido.setTotalPedido(0);
					objPedido.setDataPedido(new Date());

					PedidoDAO objetoPedidoDAO = new PedidoDAO();
					objetoPedidoDAO.cadastrarPedido(objPedido);
					listarPedidos();
					
					tfCodigo.setText("");
					tfEndereco.setText("");
					tfIdCliente.setText("");

					JOptionPane.showMessageDialog(null, "Cadastro do produto realizado com sucesso!");
				}
			}
		});
		btnCadastrar.setBounds(10, 292, 96, 23);
		panel.add(btnCadastrar);

		JButton btnListarPedidos = new JButton("Listar");
		btnListarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarPedidos();
			}

		});
		btnListarPedidos.setBounds(10, 258, 203, 23);
		panel.add(btnListarPedidos);
	}

	private void listarPedidos() {
		try {
			PedidoDAO objetoPedidoDAO = new PedidoDAO();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			ArrayList<Pedido> lista = objetoPedidoDAO.listarPedidos();

			for (int num = 0; num < lista.size(); num++) {
				model.addRow(new Object[] { lista.get(num).getId(), lista.get(num).getDataPedido(), lista.get(num).getEnderecoEntrega(), lista.get(num).getClienteId(), lista.get(num).getTotalPedido() });
			System.out.println(lista);
			
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Listar valores : " + e);
		}
	}
}