package br.edu.ifcvideira.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.utils.Conexao;

public class CategoriaDAO {

	public void CadCategoria(String cat) throws SQLException, Exception {
		try {
			String sql = "INSERT INTO categoria (nome) VALUES (?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cat);
			sqlPrep.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public Object[] BuscarTodos() throws SQLException, Exception {
		ArrayList<String> vetor = new ArrayList<>();

		try {
			String sql = "SELECT * FROM categoria";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				vetor.add(rs.getString(2));

			}
			state.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		String[] listaVetor = vetor.toArray(new String[vetor.size()]);
		return listaVetor;
	}

	public int id(String nome) throws SQLException, Exception {
		int a = 0;
		try {
			String sql = "SELECT idcategoria FROM categoria where nome='" + nome + "'";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				a = rs.getInt(1);

			}
			state.close();

			System.out.println(a);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return a;
	}
}
