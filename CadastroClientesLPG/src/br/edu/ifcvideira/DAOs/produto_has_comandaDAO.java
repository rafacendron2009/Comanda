package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Comanda;
import br.edu.ifcvideira.beans.Produto;
import br.edu.ifcvideira.utils.Conexao;

public class produto_has_comandaDAO {
	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);

	public void CadastraProdutoCliente(Comanda com, Produto prod) throws SQLException, Exception {
		try {
			String sql = "INSERT produto_has_comanda (produto_idproduto,comanda_idcomanda,valor,quantidade,data) VALUES (?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, prod.getCodigo());
			sqlPrep.setInt(2, com.getComanda());
			sqlPrep.setDouble(3, prod.getValor());
			sqlPrep.setInt(4,prod.getQuantidade() );
			sqlPrep.setTimestamp(5, prod.getData());
			sqlPrep.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deletarProdutosClientes(int comanda) throws Exception {
		try {
			String sql = "DELETE FROM produto_has_comanda WHERE comanda_idcomanda ="+comanda+"";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscaProdutosCliente(int comanda) throws SQLException, Exception {
		List<Object> produto = new ArrayList<Object>();
		try {
			String sql = "SELECT * from produto_has_comanda join produto ON produto_has_comanda.produto_idproduto = produto.idproduto where produto_has_comanda.comanda_idcomanda ="+comanda+"";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Object[] linha = {rs.getInt(1),rs.getString(7), rs.getDouble(3), rs.getInt(4), };
				produto.add(linha);
			}
			state.close();
			System.out.println(produto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return produto;
	}
	
	public void deletaritem(Produto prof) throws Exception {
		try {
			String sql = "DELETE FROM produto_has_comanda WHERE produto_idproduto ="+prof.getCodigo()+" and quantidade ="+prof.getQuantidade()+"";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}