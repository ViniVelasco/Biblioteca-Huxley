package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com o banco de dados.
 * @author Vinícius Velasco
 *
 */
public class Conexao {

public static String status = null;
	/**
	 * Método que inicia a conexão
	 * @return retorna a conexão
	 */
	public static java.sql.Connection getConexao(){
		
		Connection co_pv_connection = null;
		
		try{
			
			String st_pr_driverName = "com.mysql.jdbc.Driver"; //com.mysql.jdbc.Driver
			Class.forName(st_pr_driverName); //Carrega e inicia um objeto da classe cujo nome é passado como parâmetro
			
			String st_pr_url = "jdbc:mysql://localhost/biblioteca?autoReconnect=true&useSSL=false"; //String url = "jdbc:mysql://localhost/cadastro_pessoas?autoReconnect=true&useSSL=false";
			String st_pr_username = "root";
			String st_pr_password = "";
			
			co_pv_connection = DriverManager.getConnection(st_pr_url, st_pr_username, st_pr_password);
			
			if(co_pv_connection != null){
				status = "Conectado com sucesso";
			} else{
				status = "Conexão falhou";
			}
			
			return co_pv_connection;
			
		} catch (ClassNotFoundException e){
			status = "O driver especificado não foi encontrado";
			return null;
		} catch (SQLException e){
			status = "Não foi possível conectar ao banco de dados";
			return null;
		}
		
	}
	
	/**
	 * Método que retorna o status da conexão
	 * @return status conexão
	 */
	public String st_pb_StatusConnection(){ //Retorna o estado da conexão
		
		return status;
		
	}
	
	/**
	 * Método que fecha a conexão
	 * @return true se fechou false se SQLException encontrada.
	 */
	public static boolean bo_pb_sc_FecharConexao(){
		
		try{
			Conexao.getConexao().close();
			return true;
		}catch(SQLException e){
			return false;
		}
		
	}
	
	/**
	 * Método que reinicia a conexão
	 * @return retorna a conexão
	 */
	public static java.sql.Connection co_pb_sc_ReiniciarConexao(){
		
		bo_pb_sc_FecharConexao();
		
		return Conexao.getConexao();
	}
	
	
}
