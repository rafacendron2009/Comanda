package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.beans.Pessoa;
import br.edu.ifcvideira.utils.Conexao;

public class Comanda_ClienteDAO {
	public void ComandaCliente(Comanda com, Pessoa ps) throws SQLException, Exception {
		try {
			String sql = "INSERT comanda_has_cliente (comanda_idcomanda,cliente_idcliente) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, com.getComanda());
			sqlPrep.setInt(2, ps.getCodigo());
			sqlPrep.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deletarProdutosClientes(int comanda) throws Exception {
		try {
			String sql = "DELETE FROM comanda_has_cliente WHERE comanda_idcomanda ="+comanda+"";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}