package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Pessoa;
import br.edu.ifcvideira.utils.Conexao;

public class VendaDao {

	public void Cadcliente(Pessoa user) throws SQLException, Exception {
		try {
			String sql = "INSERT INTO cliente (nome,cpf,date) VALUES (?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, user.getNome());
			sqlPrep.setString(2, user.getCpf());
			sqlPrep.setTimestamp(3, user.getData());
			sqlPrep.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public int COD(Pessoa user) throws SQLException, Exception {
		try {
			String sql = "Select idCliente from cliente where nome = ?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, user.getNome());
			sqlPrep.execute();
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("idCliente");
			} else {
				return 0;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return 0;

	}
}
