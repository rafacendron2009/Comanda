	package br.edu.ifcvideira.controllers.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.ifcvideira.DAOs.UsuarioDAO;
import br.edu.ifcvideira.beans.Usuario;
import java.awt.Color;
import java.awt.SystemColor;

public class CadastraUsuarioView extends JFrame {
	
	Usuario user = new Usuario();
	UsuarioDAO userDAO = new UsuarioDAO();

	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmSenha;
	private JTextField tfEmail;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraUsuarioView frame = new CadastraUsuarioView();
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
	public CadastraUsuarioView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastraUsuarioView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setTitle("RC - Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 403, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 81, 48, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cadastro Us\u00FAario");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 102, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setBounds(10, 11, 367, 40);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(60, 83, 317, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 117, 33, 14);
		contentPane.add(lblCpf);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(60, 114, 317, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 183, 46, 14);
		contentPane.add(lblSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(60, 180, 317, 20);
		contentPane.add(pfSenha);
		
		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua senha:");
		lblConfirmeSuaSenha.setBounds(10, 214, 123, 14);
		contentPane.add(lblConfirmeSuaSenha);
		
		pfConfirmSenha = new JPasswordField();
		pfConfirmSenha.setBounds(128, 211, 249, 20);
		contentPane.add(pfConfirmSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String senha, confirmSenha;
				senha = String.valueOf(pfSenha.getPassword());
				confirmSenha = String.valueOf(pfConfirmSenha.getPassword());
				if(tfNome.getText().equals("")|| tfCpf.getText().equals("") || tfEmail.getText().equals("") ||senha.equals("")||confirmSenha.equals("") ) {
					 JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
				try {
				user.setNome(tfNome.getText().toLowerCase()); 
				user.setCpf(tfCpf.getText());
				user.setEmail(tfEmail.getText());
			   
				if(senha.equals(confirmSenha)){
					user.setSenha(senha);
					JOptionPane.showMessageDialog(null,"Usuario: "+ user.getNome().toUpperCase()+" cadastrado com sucesso");
					user.setData(timestamp);
					userDAO.CadUsuario(user);
					LoginView login = new LoginView();
					login.setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Senhas não conferem ");
					pfSenha.setText("");
					pfConfirmSenha.setText("");
				}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				}
			}
		});
		btnCadastrar.setBounds(10, 242, 176, 35);
		contentPane.add(btnCadastrar);
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText("");
				tfCpf.setText("");
				tfEmail.setText("");
				pfSenha.setText("");
				pfConfirmSenha.setText("");
			}
		});
		btnNewButton.setBounds(196, 242, 181, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 148, 46, 14);
		contentPane.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(60, 145, 317, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionText);
		panel.setBounds(0, 0, 387, 59);
		contentPane.add(panel);
	}
}
