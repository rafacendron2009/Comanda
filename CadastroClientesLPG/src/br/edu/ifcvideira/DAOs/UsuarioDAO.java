package br.edu.ifcvideira.DAOs;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Usuario;
import br.edu.ifcvideira.utils.Conexao;

public class UsuarioDAO {
	Usuario user  = new Usuario();
	public void CadUsuario(Usuario user) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO usuario (nome,cpf,email,senha,data) VALUES (?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, user.getNome());
			sqlPrep.setString(2, user.getCpf());
			sqlPrep.setString(3, user.getEmail());
			sqlPrep.setString(4, user.getSenha());
			sqlPrep.setTimestamp(5, user.getData());
			sqlPrep.execute();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	public boolean verifica(String usuario, String senha) throws SQLException, Exception{
		boolean a=false;
		try {
			String sql = "SELECT * FROM usuario where nome='"+usuario+"' and senha='"+senha+"'";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);	
			
			while(rs.next()) {
				 if(rs.getString("nome").equals(usuario) && rs.getString("senha").equals(senha) ) {
				 a = true;
				 }else {
					 System.out.println("ERRROO");
				 }
				  
				
			}
			
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		return a;
		
	}


}
