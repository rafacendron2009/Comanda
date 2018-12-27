package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Cliente;
import br.edu.ifcvideira.utils.Conexao;

public class ClienteDAO{
	
	public void CadastrarCliente(Cliente cl) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO cliente (nome, cpf) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, cl.getNome());
			sqlPrep.setString(contador++, cl.getCpf());
			sqlPrep.execute();
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarCliente(Cliente cl) throws Exception {
		try{
			String sql = "UPDATE cliente SET nome=?, cpf=?, telefone=?, renda=? WHERE codigo=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, cl.getNome());
			sqlPrep.setString(contador++, cl.getCpf());
			sqlPrep.setString(contador++, cl.getTelefone());
		
			sqlPrep.setInt(contador++, cl.getCodigo());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCliente(Cliente cl) throws Exception{
		try{
			String sql = "DELETE FROM cliente WHERE codigo=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cl.getCodigo());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> cliente = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM cliente";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				cliente.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return cliente;
	}
	
	public int RetornarProximoCodigoCliente() throws Exception {
		try{
			String sql ="SELECT MAX(codigo)+1 AS codigo FROM cliente ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("codigo");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
}













