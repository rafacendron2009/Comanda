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

import br.edu.ifcvideira.DAOs.UsuarioDAO;
import br.edu.ifcvideira.beans.Usuario;
import java.awt.Color;


public class LoginView extends JFrame {

	Usuario user = new Usuario();
	UsuarioDAO userDao = new UsuarioDAO();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 485, 295);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tfuser = new JTextField();
		tfuser.setHorizontalAlignment(SwingConstants.CENTER);
		tfuser.setBounds(88, 59, 275, 31);
		contentPane.add(tfuser);
		tfuser.setColumns(10);
		
		tfsenha = new JPasswordField();
		tfsenha.setHorizontalAlignment(SwingConstants.CENTER);
		tfsenha.setToolTipText("");
		tfsenha.setBounds(88, 126, 275, 31);
		contentPane.add(tfsenha);
		tfsenha.setColumns(10);
		
		JButton btEntrar = new JButton("ENTRAR");
		btEntrar.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btEntrar.setBounds(88, 168, 125, 31);
		contentPane.add(btEntrar);
		
		JButton btCadastrar = new JButton("CADASTRAR");
		btCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastra.setVisible(true);
				setVisible(false);
			}
		});
		btCadastrar.setBounds(237, 168, 126, 31);
		contentPane.add(btCadastrar);
		
		JLabel lblUsario = new JLabel("US\u00DAARIO");
		lblUsario.setForeground(new Color(255, 255, 255));
		lblUsario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsario.setBounds(180, 28, 97, 25);
		contentPane.add(lblUsario);
		
		JLabel lblSenha = new JLabel("SENHA\r\n");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSenha.setBounds(191, 101, 97, 25);
		contentPane.add(lblSenha);
		
		JLabel lblRcSoftwares = new JLabel("RC- Softwares");
		lblRcSoftwares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRcSoftwares.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblRcSoftwares.setBounds(199, 242, 78, 14);
		contentPane.add(lblRcSoftwares);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/br/edu/ifcvideira/imgs/engrenagem.png")));
		lblNewLabel.setBounds(-127, -400, 690, 883);
		contentPane.add(lblNewLabel);
	}
}
