package br.edu.ifcvideira.controllers.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.CategoriaDAO;
import br.edu.ifcvideira.DAOs.ProdutoDAO;
import br.edu.ifcvideira.beans.Produto;

public class CadastraProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField tfDescricao;
	private JTextField tfvalor;
	private JTextField textPNome;
	private JTextField textPCodigo;
	private JTable table;
	private List<Object> produto = new ArrayList<Object>();
	

	Produto cl = new Produto();
	ProdutoDAO pdao = new ProdutoDAO();
	CategoriaDAO categoria = new CategoriaDAO();
	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);

	private JTextField textCodigo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraProdutos frame = new CadastraProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	public CadastraProdutos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastraProdutos.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTabela();
				limpar();
		     }
		});
		setTitle("Cadastro Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescreao = new JLabel("Descri\u00E7\u00E3o");
		lblDescreao.setForeground(Color.WHITE);
		lblDescreao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescreao.setBounds(10, 10, 59, 20);
		contentPane.add(lblDescreao);

		JComboBox cbCategoria = new JComboBox();
		try {
			cbCategoria.setModel(new DefaultComboBoxModel(categoria.BuscarTodos()));
		} catch (Exception e2) {

			e2.printStackTrace();
		}
		cbCategoria.setToolTipText("");
		cbCategoria.getModel();
		cbCategoria.setBounds(79, 73, 147, 20);
		contentPane.add(cbCategoria);

		tfDescricao = new JTextField();
		tfDescricao.setColumns(10);
		tfDescricao.setBounds(79, 11, 315, 20);
		contentPane.add(tfDescricao);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setForeground(Color.WHITE);
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setBounds(10, 41, 59, 20);
		contentPane.add(lblValor);

		tfvalor = new JTextField();
		tfvalor.setColumns(10);
		tfvalor.setBounds(79, 42, 147, 20);
		contentPane.add(tfvalor);

		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cl.setDescricao(tfDescricao.getText());
					cl.setValor(Double.parseDouble(tfvalor.getText()));
					cl.setCategoria(cbCategoria.getSelectedIndex()+1);
					pdao.CadastrarProduto(cl);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				atualizarTabela();
				limpar();
			}
		});
		cadastrar.setBackground(SystemColor.controlHighlight);
		cadastrar.setBounds(247, 65, 147, 21);
		contentPane.add(cadastrar);

		JButton alterar = new JButton("Alterar");
		alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					try {

						cl.setCodigo(Integer.parseInt(textCodigo.getText()));
						cl.setDescricao(tfDescricao.getText());
						cl.setValor(Double.parseDouble(tfvalor.getText()));
						cl.setCategoria(cbCategoria.getSelectedIndex()+1);

						pdao.AlterarProduto(cl);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					atualizarTabela();
					limpar();
				}

				else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		alterar.setBackground(SystemColor.controlHighlight);
		alterar.setBounds(247, 93, 147, 21);
		contentPane.add(alterar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setForeground(Color.WHITE);
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setBounds(10, 72, 59, 20);
		contentPane.add(lblCategoria);

		JButton limpar = new JButton("Limpar");
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		limpar.setBackground(SystemColor.controlHighlight);
		limpar.setBounds(247, 37, 147, 21);
		contentPane.add(limpar);

		JButton excluir = new JButton("Excluir");
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Object[] options3 = { "Excluir", "Cancelar" };
					if (JOptionPane.showOptionDialog(null,
							"Tem certeza que deseja excluir o registro:\n>   "
									+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
									+ table.getValueAt(table.getSelectedRow(), 1),
							null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3,
							options3[0]) == 0) {
						try {

							cl.setCodigo(Integer.parseInt(textCodigo.getText()));
							pdao.deletarProduto(cl);

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

						atualizarTabela();
						limpar();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		excluir.setBackground(SystemColor.controlHighlight);
		excluir.setBounds(247, 120, 147, 21);
		contentPane.add(excluir);

		JLabel label_5 = new JLabel("Busca:");
		label_5.setForeground(Color.WHITE);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(23, 158, 46, 20);
		contentPane.add(label_5);

		JLabel lblAlimentosCadastrados = new JLabel("Alimentos Cadastrados:");
		lblAlimentosCadastrados.setForeground(Color.WHITE);
		lblAlimentosCadastrados.setBounds(10, 257, 156, 20);
		contentPane.add(lblAlimentosCadastrados);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 275, 384, 159);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Descri\u00E7\u00E3o", "Valor", "Categoria" }));

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setBounds(10, 103, 59, 20);
		contentPane.add(lblCodigo);

		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(79, 104, 147, 20);
		contentPane.add(textCodigo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(10, 153, 384, 2);
		contentPane.add(separator_1);

		JLabel label_6 = new JLabel("C\u00F3digo:");
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(23, 186, 46, 20);
		contentPane.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);

		textPCodigo = new JTextField();
		textPCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textPCodigo.setBounds(79, 186, 315, 20);
		contentPane.add(textPCodigo);
		textPCodigo.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por codigo
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);

				if (textPCodigo.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter(textPCodigo.getText(), 0));
				}
			}
		});
		textPCodigo.setColumns(10);

		textPNome = new JTextField();
		textPNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textPNome.setBounds(79, 213, 315, 20);
		contentPane.add(textPNome);
		textPNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				// atualizar a tabela apenas com valores correspondentes aos digitados no campo
				// de busca por nome
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);

				if (textPNome.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + textPNome.getText(), 1));
				}

			}
		});
		textPNome.setColumns(10);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setForeground(Color.WHITE);
		lblDescrio.setBounds(10, 214, 59, 20);
		contentPane.add(lblDescrio);
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(10, 244, 384, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CadastraProdutos.class.getResource("/br/edu/ifcvideira/imgs/fundo.png")));
		lblNewLabel.setBounds(0, 0, 407, 484);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 387, 245);
		contentPane.add(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 387, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 245, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
	}

	public void setCamposFromTabela() {
		textCodigo.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		tfDescricao.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		tfvalor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		
	}

	public void limpar() {
		tfDescricao.setText(null);
		tfvalor.setText(null);
		try {
			textCodigo.setText(String.valueOf(pdao.RetornarProximoCodigoProduto()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void atualizarTabela() {
		try {
			produto = pdao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != produto.size(); x++) {
				model.addRow((Object[]) produto.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
}
