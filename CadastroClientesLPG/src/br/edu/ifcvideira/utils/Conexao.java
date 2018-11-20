package br.edu.ifcvideira.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
		//conex�o MySQL
		public static Connection conectar() throws Exception {
			try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/rcsoftware?user=root&password=root");
					return conexao;
			}catch(ClassNotFoundException | SQLException e){
	            throw new Exception(e.getMessage());
	        }
	}
		
}
//conex�o Postgres
//private final static String driver = "org.postgresql.Driver";
//private final static String usuario = "postgres";
//private final static String senha = "1234";
//private final static String host = "localhost";
//private final static String porta = "5432";
//private final static String banco = "db_Cliente";
//private final static String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco;
//private static Connection conexao = null;
//    
//public static Connection conectar(){
//	 try {
//		 Class.forName(driver);
//		 conexao = DriverManager.getConnection(url, usuario, senha);
//		 System.out.println("Conex�o efetuada com sucesso");
//       
//	 } catch (Exception ex) {
//		 ex.printStackTrace();
//	 }
//	return conexao; 
//}
//
//public void fechar() {
//	try {
//		conexao.close();
//		System.out.println("Conex�o encerrada");
//	} 
//        
//	catch (SQLException e) {
//		e.printStackTrace();
//	}
//}	
//
	