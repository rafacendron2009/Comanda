package br.edu.ifcvideira.controllers.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.ifcvideira.DAOs.CategoriaDAO;

public class CadastraCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	CategoriaDAO cat  = new CategoriaDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraCategoria frame = new CadastraCategoria();
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
	public CadastraCategoria() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastraCategoria.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setTitle("Cadastro Categoria\r\n");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 251, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 78, 215, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 11, 215, 45);
		contentPane.add(panel);
		
		JLabel lblCategoria = new JLabel("CATEGORIA:");
		panel.add(lblCategoria);
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cat.CadCategoria(tfNome.getText().toUpperCase());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, tfNome.getText().toUpperCase()+" cadastrado com sucesso");
			}
		});
		btnSalvar.setBounds(70, 109, 89, 23);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CadastraCategoria.class.getResource("/br/edu/ifcvideira/imgs/fundo.png")));
		lblNewLabel.setBounds(0, 0, 645, 383);
		contentPane.add(lblNewLabel);
	}
}
