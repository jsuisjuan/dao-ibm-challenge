package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connection.ClienteDAO;
import connection.PedidoDAO;
import connection.connectionBancoDeDados;
import objetos.Cliente;
import objetos.Pedido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTable table;
	DefaultTableModel model;
	private JTextField tfCodigo;
	public static connectionBancoDeDados sintaxe = new connectionBancoDeDados();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lbMarca = new JLabel("FARM\u00C1CIA TUDO DE BOM");
		lbMarca.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbMarca.setBounds(10, 32, 214, 14);
		contentPane.add(lbMarca);
		
		JLabel lbCodigo = new JLabel("C\u00F3digo: ");
		lbCodigo.setBounds(10, 84, 49, 14);
		contentPane.add(lbCodigo);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 114, 83, 19);
		contentPane.add(lbNome);
		
		JLabel lbCpf = new JLabel("CPF:");
		lbCpf.setBounds(10, 147, 40, 14);
		contentPane.add(lbCpf);
		
		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(56, 81, 155, 20);
		contentPane.add(tfCodigo);
		
		tfNome = new JTextField();
		tfNome.setBounds(56, 113, 155, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(56, 144, 155, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 83, 351, 368);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				int idCliente;
				
				tfCodigo.setText(model.getValueAt(i, 0).toString());
				tfNome.setText(model.getValueAt(i, 1).toString());
				tfCpf.setText(model.getValueAt(i, 2).toString());
				
				idCliente = Integer.parseInt(tfCodigo.getText());
				
				Pedido objetoPedido = new Pedido();
				objetoPedido.setClienteId(idCliente);
				
				PedidoDAO objetoPedidoDAO = new PedidoDAO();
				objetoPedidoDAO.pegarCliente(objetoPedido);
				
				Cliente objetoCliente = new Cliente();
				objetoCliente.setNome(tfNome.getText());
				
				objetoPedidoDAO.pegarNomeCliente(objetoCliente);
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"ID", "Nome", "CPF"};
		final Object[] row = new Object[3];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		listarClientes();
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfNome.getText().equals("") || tfCpf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor, insira todas as informações!");
				} else {
					String nome, cpf;
					
					nome = tfNome.getText();
					cpf = tfCpf.getText();
					
					Cliente objetoCliente = new Cliente();
					
					objetoCliente.setNome(nome);
					objetoCliente.setCpf(cpf);
					
					ClienteDAO objetoClienteDAO = new ClienteDAO();
					objetoClienteDAO.cadastrarCliente(objetoCliente);
					listarClientes();
					
					tfCodigo.setText("");
					tfNome.setText("");
					tfCpf.setText("");
					JOptionPane.showMessageDialog(null, "Cadastro do cliente realizado com sucesso!");
				}
			}
		});
		btnCadastrar.setBounds(10, 394, 96, 23);
		contentPane.add(btnCadastrar);
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id;
				String nome, cpf;
				
				id = Integer.parseInt(tfCodigo.getText());
				nome = tfNome.getText();
				cpf = tfCpf.getText();
				
				Cliente objetoCliente = new Cliente();
				
				objetoCliente.setId(id);
				objetoCliente.setNome(nome);
				objetoCliente.setCpf(cpf);
				
				ClienteDAO objetoClienteDAO = new ClienteDAO();
				objetoClienteDAO.atualizarCliente(objetoCliente);
				listarClientes();
				
				tfCodigo.setText("");
				tfNome.setText("");
				tfCpf.setText("");
				JOptionPane.showMessageDialog(null, "Atualização do cliente realizado com sucesso!");
			}
		});
		btnAtualizar.setBounds(128, 394, 96, 23);
		contentPane.add(btnAtualizar);
		
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				
				id = Integer.parseInt(tfCodigo.getText());
				
				Cliente objetoCliente = new Cliente();
				
				objetoCliente.setId(id);
				
				ClienteDAO objetoClienteDAO = new ClienteDAO();
				objetoClienteDAO.removerCliente(objetoCliente);
				listarClientes();
				
				tfCodigo.setText("");
				tfNome.setText("");
				tfCpf.setText("");
				JOptionPane.showMessageDialog(null, "Remoção do cliente realizado com sucesso!");
			}
		});
		btnRemover.setBounds(10, 428, 96, 23);
		contentPane.add(btnRemover);
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCodigo.setText("");
				tfNome.setText("");
				tfCpf.setText("");
			}
		});
		btnLimpar.setBounds(128, 428, 96, 23);
		contentPane.add(btnLimpar);
		
		
		
		JButton btnHistorico = new JButton("Hist\u00F3rico de Transa\u00E7\u00E3o");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoTransacoes historicoWindow = new HistoricoTransacoes();
			}
		});
		btnHistorico.setBounds(23, 188, 188, 23);
		contentPane.add(btnHistorico);
		
		JButton btnEstoque = new JButton("Controle de Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleEstoque controleWindow = new ControleEstoque();
			}
		});
		btnEstoque.setBounds(23, 222, 188, 23);
		contentPane.add(btnEstoque);
	}
		private void listarClientes() {
			try {
				ClienteDAO objetoClienteDAO = new ClienteDAO();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				ArrayList<Cliente> lista = objetoClienteDAO.listarCliente();
				
				for (int num = 0; num < lista.size(); num++) {
					model.addRow(new Object[] {
							lista.get(num).getId(),
							lista.get(num).getNome(),
							lista.get(num).getCpf(),
					});
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Listar valores : " + e);
			}
	}
	
	
}
