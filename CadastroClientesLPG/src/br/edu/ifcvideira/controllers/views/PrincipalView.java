package br.edu.ifcvideira.controllers.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PrincipalView extends JFrame {
	String nome;
	public void usuario(String usuario) {
		nome = usuario;
	}
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView frame = new PrincipalView();
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
	public PrincipalView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setTitle("RC - Comandas");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1374, 744);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setForeground(SystemColor.inactiveCaptionBorder);
		desktopPane.setBounds(4, 682, 1362, -682);
		contentPane.add(desktopPane);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalView.class.getResource("/br/edu/ifcvideira/imgs/46505757_2195855797337455_6094008068410966016_n.png")));
		lblNewLabel.setBounds(0, 2, 1391, 669);
		contentPane.add(lblNewLabel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.text);
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		mnCadastrar.setFont(new Font("Arial", Font.BOLD, 13));
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaView vv = new VendaView();
				vv.setVisible(true);
			}
		});
		mnCadastrar.add(mntmVendas);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraProdutos cp = new CadastraProdutos();
				cp.setVisible(true);
			}
		});
		mnCadastrar.add(mntmProduto);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraCategoria cc = new CadastraCategoria();
				cc.setVisible(true);
			
			}
		});
		mnCadastrar.add(mntmCategoria);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		mnRelatrio.setFont(new Font("Arial", Font.BOLD, 13));
		menuBar.add(mnRelatrio);
		
		
		
		
		
		
	}
}



