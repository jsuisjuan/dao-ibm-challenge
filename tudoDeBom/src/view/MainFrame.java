package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTable table;
	DefaultTableModel model;
	private JTextField tfId;
	
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
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 114, 83, 19);
		contentPane.add(lbNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(56, 113, 155, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lbCpf = new JLabel("CPF:");
		lbCpf.setBounds(10, 147, 40, 14);
		contentPane.add(lbCpf);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(56, 144, 155, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 83, 351, 368);
		contentPane.add(scrollPane);
		
		// tabela do cliente
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				tfId.setText(model.getValueAt(i, 0).toString());
				tfNome.setText(model.getValueAt(i, 1).toString());
				tfCpf.setText(model.getValueAt(i, 2).toString());
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"ID", "Nome", "CPF"};
		final Object[] row = new Object[3];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("") || tfNome.getText().equals("") || tfCpf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Porfavor, insira todas as informações!");
				} else {
					row[0] = tfId.getText();
					row[1] = tfNome.getText();
					row[2] = tfCpf.getText();
					model.addRow(row);
					
					tfId.setText("");
					tfNome.setText("");
					tfCpf.setText("");
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
				}
				
			}
		});
		btnCadastrar.setBounds(10, 394, 96, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.setValueAt(tfId.getText(), i, 0);
					model.setValueAt(tfNome.getText(), i, 1);
					model.setValueAt(tfCpf.getText(), i, 2);
					JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Porfavor, selecione uma tupla!");
				}
				
			}
		});
		btnAtualizar.setBounds(128, 394, 96, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.removeRow(i);
					
					tfId.setText("");
					tfNome.setText("");
					tfCpf.setText("");
					
					JOptionPane.showMessageDialog(null, "Remoção realizada com sucesso!");
					
				} else {
					JOptionPane.showMessageDialog(null, "Porfavor, seleciona uma tupla!");
				}
			}
		});
		btnRemover.setBounds(10, 428, 96, 23);
		contentPane.add(btnRemover);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfId.setText("");
				tfNome.setText("");
				tfCpf.setText("");
			}
		});
		btnLimpar.setBounds(128, 428, 96, 23);
		contentPane.add(btnLimpar);
		
		JLabel lbMarca = new JLabel("FARM\u00C1CIA TUDO DE BOM");
		lbMarca.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbMarca.setBounds(10, 32, 214, 14);
		contentPane.add(lbMarca);
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(56, 81, 155, 20);
		contentPane.add(tfId);
		
		JLabel lbId = new JLabel("ID: ");
		lbId.setBounds(10, 84, 49, 14);
		contentPane.add(lbId);
		
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
}
