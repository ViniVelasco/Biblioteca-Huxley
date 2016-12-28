package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Persistência do Estado Civil.
 * @author Vinícius Velasco
 *
 */
public class EstadoCivilDAO {
	
private Connection pv_co_connection;
	/**
	 * Método construtor da classe que inicia a conexão com banco de dados.
	 */
	public EstadoCivilDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca todos os estado civis.
	 * @param estadosCivis
	 */
	public void pb_vd_BuscaEstadoCivil(ArrayList<EstadoCivil> estadosCivis){
		String sql = "select idEstadoCivil, descricao from estadocivil";
		EstadoCivil.pb_ar_sc_estadoCivil.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<EstadoCivil> pr_ar_Estados = new ArrayList<EstadoCivil>();
			while(rs.next()){
				EstadoCivil estado = new EstadoCivil();
				estado.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estado.setPv_st_Descricao(rs.getString("descricao"));
				
				pr_ar_Estados.add(estado);
			}
			EstadoCivil.pb_ar_sc_estadoCivil.addAll(pr_ar_Estados);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

}

}
