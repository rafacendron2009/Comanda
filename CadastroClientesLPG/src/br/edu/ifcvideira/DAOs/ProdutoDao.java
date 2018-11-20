package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Produto;
import br.edu.ifcvideira.utils.Conexao;

public class ProdutoDao {
	public void CadastrarProduto(Produto produto) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO produto (nome, valor,categoria_idcategoria) VALUES (?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, produto.getDescricao());
			sqlPrep.setDouble(contador++,produto.getValor());
			sqlPrep.setInt(contador++, produto.getCategoria());
			sqlPrep.execute();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProduto(Produto produto) throws Exception {
		try{
			String sql = "UPDATE produto SET nome=?, valor=?, categoria_idcategoria=? WHERE codigo=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, produto.getDescricao());
			sqlPrep.setDouble(contador++,produto.getValor());
			sqlPrep.setInt(contador++, produto.getCategoria());
			
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarProduto(Produto produto) throws Exception{
		try{
			String sql = "DELETE FROM produto WHERE idproduto=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, produto.getCodigo());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> cliente = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM produto";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), };
				cliente.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return cliente;
	}
	
	public int RetornarProximoCodigoProduto() throws Exception {
		try{
			String sql ="SELECT MAX(idproduto)+1 AS idproduto FROM produto";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("idproduto");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
}
