package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persistência do Estado.
 * @author Vinícius Velasco
 *
 */
public class EstadoDAO {

private Connection pv_co_connection;
	/**
	 * Método construtor da classe que inicia a conexão com banco de dados
	 */
	public EstadoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca todos os estados.
	 * @param estados
	 */
	public void pb_vd_BuscaEstado(ArrayList<Estado> estados){
		String sql = "select idEstado, nomeEstado from estado";
		Estado.pb_ar_sc_estados.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Estado> pr_ar_estados = new ArrayList<Estado>();
			while(rs.next()){
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				
				//secoes.add(secao);
				pr_ar_estados.add(estado);
			}
			
			Estado.pb_ar_sc_estados.addAll(pr_ar_estados);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

}
	/**
	 * Método que busca todos os estados por nome
	 * @param estados
	 * @param buscaEstado
	 */
	public void pb_vd_BuscaEstadoPorNome(ArrayList<Estado> estados, Estado buscaEstado){
		String sql = "select idEstado, nomeEstado from estado where nomeEstado like ?";
		Estado.pb_ar_sc_estados.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%"+buscaEstado.getPv_st_NomeEstado()+"%");
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Estado> pr_ar_estados = new ArrayList<Estado>();
			while(rs.next()){
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				
				//secoes.add(secao);
				pr_ar_estados.add(estado);
			}
			
			Estado.pb_ar_sc_estados.addAll(pr_ar_estados);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

}
	/**
	 * Método que insere o estado
	 * @param estado
	 */
	public void pb_vd_InserirEstado(Estado estado){
		String sql = "insert into estado(nomeEstado) values (?)";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			stmt.setString(1, estado.getPv_st_NomeEstado());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

	}
	
	/**
	 * Método de alteração do estado.
	 * @param estado
	 */
	public void pb_vd_AlterarEstado(Estado estado){
		String sql = "UPDATE estado SET nomeEstado = ? where idEstado = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			stmt.setString(1, estado.getPv_st_NomeEstado());
			stmt.setInt(2, estado.getPv_in_IDEstado());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

	}
	
	/**
	 * Método que deleta um estado.
	 * @param estado
	 */
	public void pb_vd_DeletarEstado(Estado estado){
		String sql = "DELETE from estado where idEstado = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			stmt.setInt(1, estado.getPv_in_IDEstado());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

	}
	/**
	 * Método que verifica se o estado está associado com cidade.
	 * @param estado
	 * @return retorna true se está associado e false se não está.
	 */
	public boolean pb_vd_VerificaAssociacaoEstado(Estado estado){
		String sql = "select * from cidade where idEstadoReferente  = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, estado.getPv_in_IDEstado());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				return true;
			}
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		return false;

	}
}
