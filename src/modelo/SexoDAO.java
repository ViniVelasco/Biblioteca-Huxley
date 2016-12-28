package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persistência do sexo.
 * @author Vinícius Velasco
 *
 */
public class SexoDAO {
	
private Connection pv_co_connection;

	/**
	 * Método construtor da classe, que inicia a conexão com o banco de dados.
	 */
	public SexoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca todos os sexos.
	 * @param sexos
	 */
	public void pb_vd_BuscaSexo(ArrayList<Sexo> sexos){
		String sql = "select idSexo, nomeSexo from sexo";
		Sexo.pb_ar_sc_sexos.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Sexo> pr_ar_sexo = new ArrayList<Sexo>();
			while(rs.next()){
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				
				//secoes.add(secao);
				pr_ar_sexo.add(sexo);
			}
			Sexo.pb_ar_sc_sexos.addAll(pr_ar_sexo);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

}
	}
