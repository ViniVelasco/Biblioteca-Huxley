package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;

/**
 * Classe de persist�ncia do tipo de funcion�rio.
 * @author Viniciusbras
 *
 */
public class FuncionarioTipoDAO {
	
private Connection pv_co_connection;
	/**
	 * M�todo construtor da classe que inicia  aconex�o com o banco.
	 */
	public FuncionarioTipoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * M�todo que busca todos os tipos de funcion�rio
	 * @param funcionario
	 */
	public void pb_vd_BuscaTiposCliente(ArrayList<FuncionarioTipo> funcionario){
		String sql = "select idTipoFuncionario, cargo from tipoFuncionario";
		FuncionarioTipo.pb_ar_sc_tipos.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<FuncionarioTipo> pr_ar_tipos = new ArrayList<FuncionarioTipo>();
			while(rs.next()){
				FuncionarioTipo tipo = new FuncionarioTipo();
				tipo.setPv_in_IDTipoFuncionario(rs.getInt("idTipoFuncionario"));
				tipo.setPv_st_Cargo(rs.getString("cargo"));
				
				pr_ar_tipos.add(tipo);
			}
			FuncionarioTipo.pb_ar_sc_tipos.addAll(pr_ar_tipos);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
		}	
	}
	
	

}
