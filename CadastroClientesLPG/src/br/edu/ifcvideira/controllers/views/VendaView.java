package br.edu.ifcvideira.controllers.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.ComandaDAO;
import br.edu.ifcvideira.DAOs.Comanda_ClienteDAO;
import br.edu.ifcvideira.DAOs.VendaDAO;
import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.beans.Pessoa;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class VendaView extends JFrame {

	Pessoa pessoa = new Pessoa();
	Comanda cm = new Comanda();

	int cod;
	private JPanel contentPane;
	private JTable table;
	private JTextField tfNome;
	private JTextField tfCpf;
	List<Object> cliente = new ArrayList<Object>();
	ComandaDAO cdao = new ComandaDAO();
	Pessoa ps = new Pessoa();
	VendaDAO vd = new VendaDAO();
	Comanda_ClienteDAO ac = new Comanda_ClienteDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendaView frame = new VendaView();
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

	public VendaView() {
		setTitle("Cliente");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VendaView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 450, 455);
		contentPane = new JPanel();

		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 444, 58);
		contentPane.add(panel);

		JLabel lblCadastraCliente = new JLabel("Cliente");
		lblCadastraCliente.setForeground(Color.BLACK);
		lblCadastraCliente.setFont(new Font("Tahoma", Font.BOLD, 29));
		panel.add(lblCadastraCliente);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 146, 444, 9);
		contentPane.add(separator);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 155, 424, 260);
		contentPane.add(scrollPane);

		table = new JTable();

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "COMANDA", "NOME", "CPF" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(228);
		table.getColumnModel().getColumn(2).setPreferredWidth(153);
		scrollPane.setViewportView(table);
		atualizarTabela();
		JComboBox<String> cbComanda = new JComboBox<String>();
		cbComanda.setModel(new DefaultComboBoxModel<String>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "11", "12" }));
		cbComanda.setBounds(10, 81, 50, 20);
		contentPane.add(cbComanda);

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				String nome = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				int comanda = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
				ComandaView cv = new ComandaView();
				cv.recebeInf(nome.toUpperCase(), comanda);

				cv.setVisible(true);

			}
		});
		tfNome = new JTextField();
		tfNome.setBounds(80, 81, 211, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfCpf = new JTextField();
		tfCpf.setBounds(301, 81, 133, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);

		JLabel lblComanda = new JLabel("COMANDA");
		lblComanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComanda.setBounds(10, 57, 60, 29);
		contentPane.add(lblComanda);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(163, 57, 37, 29);
		contentPane.add(lblNome);

		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(353, 57, 37, 29);
		contentPane.add(lblNewLabel);

		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = Integer.parseInt((String) cbComanda.getSelectedItem());
				int sit = 10;
				try {
					sit = cdao.verificaComanda(a);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				if (sit == 1) {
					JOptionPane.showMessageDialog(null, "Esta comanda já esta associada a um cliente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (tfNome.getText().equals("") || tfCpf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						ps.setNome(tfNome.getText());
						ps.setCpf(tfCpf.getText());
						cm.setComanda(a);
						cm.setSituacao(1);

						try {
							cdao.sitComanda(cm);
							vd.Cadcliente(ps);
							ps.setCodigo(vd.COD(ps));
							ac.ComandaCliente(cm, ps);

						} catch (Exception e2) {
							e2.printStackTrace();
						}

						System.out.println(cliente);
						atualizarTabela();
					}
				}
			}
		});
		btnSalvar.setBounds(179, 112, 123, 23);
		contentPane.add(btnSalvar);

		JButton btnNewButton = new JButton("PESQUISAR CLIENTE");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 112, 159, 23);
		contentPane.add(btnNewButton);
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				atualizarTabela();
			}
		});

	}

	public void atualizarTabela() {
		try {
			cliente = cdao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != cliente.size(); x++) {
				model.addRow((Object[]) cliente.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void CamposFromTabela() {
		cm.setComanda(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
		ps.setNome(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		ps.setCpf(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));

	}

}
