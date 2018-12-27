package br.edu.ifcvideira.controllers.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.CategoriaDAO;
import br.edu.ifcvideira.DAOs.ProdutoDAO;
import br.edu.ifcvideira.DAOs.produto_has_comandaDAO;
import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.beans.Produto;

public class CardapioView extends JFrame {
	int c = 1;
	private JPanel contentPane;
	CategoriaDAO categoria = new CategoriaDAO();
	ProdutoDAO pdao = new ProdutoDAO();
	produto_has_comandaDAO  prod_com = new produto_has_comandaDAO();
	private JTable table;
	private List<Object> produto = new ArrayList<Object>();
	int cod,quantidade;
	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	
	Produto pt = new Produto();
	Comanda cm = new Comanda();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardapioView frame = new CardapioView();
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
	public CardapioView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbCategoria = new JComboBox();

		cbCategoria.setBounds(10, 64, 227, 20);
		contentPane.add(cbCategoria);
		atualizarTabela();
		try {
			cbCategoria.setModel(new DefaultComboBoxModel(categoria.BuscarTodos()));

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 106, 564, 435);
			contentPane.add(scrollPane);

			table = new JTable();
			table.setCellSelectionEnabled(true);
			scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "COD", "Descri\u00E7\u00E3o", "Valor", "Quantidade" }));

			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(SystemColor.window);
			panel.setBounds(10, 11, 544, 42);
			contentPane.add(panel);

			JLabel lblNewLabel = new JLabel("PEDIDO");
			lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
			panel.add(lblNewLabel);

			JButton btnAdicionarItens = new JButton("Adicionar itens");
			btnAdicionarItens.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					for (int a=0; a < table.getRowCount() ; a++) {
						pt.setCodigo(Integer.parseInt(String.valueOf(table.getValueAt(a, 0))));
						pt.setValor(Double.parseDouble(String.valueOf(table.getValueAt(a, 2))));
						pt.setData(timestamp);
						quantidade = Integer.parseInt(String.valueOf(table.getValueAt(a, 3)));
						pt.setQuantidade(quantidade);
						
						if(quantidade <= 0) {}
						else {
							
							
							try {
								prod_com.CadastraProdutoCliente(cm, pt);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
						}
						
					}
					JOptionPane.showMessageDialog(null,"Pedidos efetuado com sucesso");
					atualizarTabela();
					
				}
			});
			btnAdicionarItens.setBackground(SystemColor.activeCaptionBorder);
			btnAdicionarItens.setBounds(266, 63, 119, 32);
			contentPane.add(btnAdicionarItens);
			atualizarTabela();
			cbCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c = cbCategoria.getSelectedIndex() + 1;
					atualizarTabela();
				}
			});

		} catch (Exception e2) {

			e2.printStackTrace();
		}

	}
	public void recebeComanda(int comanda) {
		cm.setComanda(Integer.parseInt(String.valueOf(comanda)));
		
	}
	
	public void atualizarTabela() {
		try {
			produto = pdao.buscaCategoriaSelecionada(c);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != produto.size(); x++) {
				model.addRow((Object[]) produto.get(x));
			}
		} catch (Exception e) {
		}
	}
}
