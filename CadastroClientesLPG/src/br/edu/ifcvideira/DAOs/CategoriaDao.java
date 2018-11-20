package br.edu.ifcvideira.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.utils.Conexao;

public class CategoriaDao {
	public void CadCategoria(String cat) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO categoria (nome) VALUES (?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cat);
			sqlPrep.execute();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	public List<Object> BuscarTodos() throws SQLException, Exception{
		List<Object> cat = new ArrayList<>();
		try {
			String sql = "SELECT nome FROM categoria";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				cat.add(rs.getString(1));
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return cat;
	}
}
