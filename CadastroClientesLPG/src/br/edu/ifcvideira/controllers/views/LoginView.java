package br.edu.ifcvideira.controllers.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import br.edu.ifcvideira.DAOs.UsuarioDao;
import br.edu.ifcvideira.beans.Usuario;


public class LoginView extends JFrame {

	Usuario user = new Usuario();
	UsuarioDao userDao = new UsuarioDao();
	PrincipalView menu = new PrincipalView();
	CadastraUsuarioView Cadastra = new CadastraUsuarioView();
	boolean log;
	
	private JPanel contentPane;
	private JTextField tfuser;
	private JTextField tfsenha;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginView() {
		setTitle("RC comandas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\rafac\\Desktop\\logo rafa.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 485, 295);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tfuser = new JTextField();
		tfuser.setHorizontalAlignment(SwingConstants.CENTER);
		tfuser.setBounds(184, 50, 275, 31);
		contentPane.add(tfuser);
		tfuser.setColumns(10);
		
		tfsenha = new JPasswordField();
		tfsenha.setHorizontalAlignment(SwingConstants.CENTER);
		tfsenha.setToolTipText("");
		tfsenha.setBounds(184, 126, 275, 31);
		contentPane.add(tfsenha);
		tfsenha.setColumns(10);
		
		JButton btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				if(tfuser.getText().equals("") || tfsenha.getText().equals("")) {
					 JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
				try {
					log = userDao.verifica(tfuser.getText(),tfsenha.getText());
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
					if(log == true) {
						menu.usuario(tfuser.getText());;
						JOptionPane.showMessageDialog(null,tfuser.getText().toUpperCase()+ " seja bem vindo!", "Boas vindas", JOptionPane.INFORMATION_MESSAGE);
						
						
						menu.setVisible(true);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Login ou Senha inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
						tfuser.setText("");
						tfsenha.setText("");
					}
					
				}
			}
		});
		btEntrar.setBounds(218, 170, 125, 23);
		contentPane.add(btEntrar);
		
		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastra.setVisible(true);
				setVisible(false);
			}
		});
		btCadastrar.setBounds(344, 170, 115, 23);
		contentPane.add(btCadastrar);
		
		JLabel lblUsario = new JLabel("Us\u00FAario");
		lblUsario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsario.setBounds(295, 11, 59, 31);
		contentPane.add(lblUsario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(295, 108, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblRcSoftwares = new JLabel("RC- Softwares");
		lblRcSoftwares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRcSoftwares.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblRcSoftwares.setBounds(271, 231, 78, 14);
		contentPane.add(lblRcSoftwares);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/br/edu/ifcvideira/imgs/rafael.jpg")));
		lblNewLabel.setBounds(0, 0, 184, 256);
		contentPane.add(lblNewLabel);
	}
}
