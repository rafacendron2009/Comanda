package br.edu.ifcvideira.controllers.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.ComandaDAO;
import br.edu.ifcvideira.DAOs.Comanda_ClienteDAO;
import br.edu.ifcvideira.DAOs.produto_has_comandaDAO;
import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.beans.Produto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComandaView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblCliente;
	private JTextField tfNome;
	private JTextField tfComanda;
	List<Object> produto = new ArrayList<Object>();
	int co, quantidade;
	int comanda;
	double valor, valorAtual = 0;
	private JButton btnPagar;
	private JTextField tfvalor;
	Comanda_ClienteDAO cc = new Comanda_ClienteDAO();
	Comanda cm = new Comanda();
	produto_has_comandaDAO pc = new produto_has_comandaDAO();
	ComandaDAO cdao = new ComandaDAO();
	Produto prod = new Produto();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComandaView() {
		setTitle("COMANDA\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComandaView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfvalor = new JTextField();
		tfvalor.setEditable(false);
		tfvalor.setHorizontalAlignment(SwingConstants.CENTER);
		tfvalor.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfvalor.setForeground(new Color(0, 128, 0));
		tfvalor.setBounds(418, 427, 97, 35);
		contentPane.add(tfvalor);
		tfvalor.setColumns(10);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfNome.setEditable(false);
		tfNome.setBounds(83, 24, 487, 26);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfComanda = new JTextField();
		tfComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfComanda.setEditable(false);
		tfComanda.setBounds(95, 74, 58, 26);
		contentPane.add(tfComanda);
		tfComanda.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 571, 278);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				prod.setCodigo(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
				prod.setQuantidade(Integer.parseInt((String.valueOf(table.getValueAt(table.getSelectedRow(),3)))));
				
				System.out.println(prod.getCodigo());
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COD", "Descri\u00E7\u00E3o", "Valor", "Quantidade"
			}
		));

		lblCliente = new JLabel("CLIENTE:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 21, 85, 29);
		contentPane.add(lblCliente);

		JLabel lblComanda = new JLabel("COMANDA");
		lblComanda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComanda.setBounds(10, 75, 70, 26);
		contentPane.add(lblComanda);

		JButton btnNewButton = new JButton("ADICIONAR ITEM\r\n");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardapioView card = new CardapioView();
				card.recebeComanda(Integer.parseInt(tfComanda.getText()));
				card.setVisible(true);
			}
		});
		btnNewButton.setBounds(176, 71, 140, 35);
		contentPane.add(btnNewButton);

		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela(co);
				atualizaValor();
			}
		});
		btnAtualizar.setBounds(338, 71, 126, 35);
		contentPane.add(btnAtualizar);
		btnPagar = new JButton("PAGAR");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.setComanda(co);
				cm.setSituacao(0);
				try {
					cc.deletarProdutosClientes(co);
					pc.deletarProdutosClientes(co);
					cdao.sitComanda(cm);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				JOptionPane.showMessageDialog(null,"Finaliazado com sucesso!");
				setVisible(false);
			}
		});
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setBackground(Color.BLACK);
		btnPagar.setBounds(24, 473, 108, 46);
		contentPane.add(btnPagar);

		JLabel lblTotal = new JLabel("TOTAL : ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal.setBounds(340, 435, 85, 14);
		contentPane.add(lblTotal);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					pc.deletaritem(prod);
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
				atualizarTabela(comanda);
				atualizaValor();
			}
			
		});
		btnExcluir.setBounds(481, 75, 89, 26);
		contentPane.add(btnExcluir);

	}

	

	public void recebeInf(String nome, int comanda) {
		tfNome.setText(nome);
		tfComanda.setText(String.valueOf(comanda));
		co = comanda;
		atualizarTabela(comanda);
		atualizaValor();
	}
	
	public void atualizaValor() {
		valorAtual = 0;
		for (int a = 0; a < table.getRowCount(); a++) {
			quantidade = Integer.parseInt(String.valueOf(table.getValueAt(a, 3)));
			valor = Double.parseDouble((String.valueOf(table.getValueAt(a, 2))));
			valorAtual = valorAtual + (quantidade * valor);
			tfvalor.setText(String.valueOf(valorAtual));
		}
	

	}

	public void atualizarTabela(int comanda) {
		System.out.println(comanda);
		try {
			produto = pc.buscaProdutosCliente(comanda);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != produto.size(); x++) {
				model.addRow((Object[]) produto.get(x));
			}
			if(produto.size()==0) {
				tfvalor.setText("0.0");
			}
			
		} catch (Exception e) {
		}
	}
}
