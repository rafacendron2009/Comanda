package br.edu.ifcvideira.beans;



public class Cliente extends Pessoa  {
	private int codigo;
	private String telefone;
	private double valor;
	private String descricao;
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	//construtor vazio, sem necessidade de parâmetros
	public Cliente(){
		
	}
	
	//encapsulamento dos atributos
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	

}
