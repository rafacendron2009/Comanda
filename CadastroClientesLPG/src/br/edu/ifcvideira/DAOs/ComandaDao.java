package br.edu.ifcvideira.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.utils.Conexao;

public class ComandaDao {
	public void sitComanda(Comanda com) throws SQLException, Exception {
		try {
			String sql = "UPDATE comanda SET situacao=? WHERE idcomanda=?";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, com.getSituacao());
			sqlPrep.setInt(2, com.getComanda());
			sqlPrep.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public int verificaComanda(int com) throws SQLException, Exception {

		try {
			String sql = "SELECT * FROM comanda where situacao =" + com + "";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt(2));
				if (rs.getInt(2) == 1) {
					return 1;
				} else {
					System.out.println("ERRROO");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return 0;
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> categoria = new ArrayList<Object>();
		try {
			String sql = "SELECT comanda_idcomanda , cliente.nome,cliente.cpf FROM comanda_has_cliente JOIN comanda on comanda_idcomanda = idcomanda JOIN cliente on cliente_idcliente = idCliente order by comanda.idcomanda";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3)};
				categoria.add(linha);
			}
			state.close();
			System.out.println(categoria);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return categoria;
	}

}
