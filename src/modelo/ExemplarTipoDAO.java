package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Persistência da classe de exemplar tipo.
 * @author Vinícius Velasco
 *
 */
public class ExemplarTipoDAO {
	
	private Connection pv_co_connection;
	/**
	 * Método constutor da classe que inicia a conexão com o banco de dados.
	 */
	public ExemplarTipoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	/**
	 * Método que busca todos os tipos de exemplar
	 * @param tipos
	 */
	public void pb_vd_BuscaTipos(ArrayList<ExemplarTipo> tipos){
		String sql = "select id, descricao from exemplar_tipo";
		ExemplarTipo.pb_ar_sc_secoes.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<ExemplarTipo> pr_ar_tipos = new ArrayList<ExemplarTipo>();
			while(rs.next()){
				ExemplarTipo et = new ExemplarTipo();
				et.setPv_in_IdTipo(rs.getInt("id"));
				et.setPv_st_Descricao(rs.getString("descricao"));
				
				//secoes.add(secao);
				pr_ar_tipos.add(et);
			}
			ExemplarTipo.pb_ar_sc_secoes.addAll(pr_ar_tipos);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}


	}
}
