package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Persistência do Tipo de Cliente.
 * @author Vinícius Velasco
 *
 */
public class ClienteTipoDAO {
	
private Connection pv_co_connection;
	/**
	 * Construtor da classe que abre a conexão com o banco.
	 */
	public ClienteTipoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca todos os tipos de cliente
	 * @param cliente
	 */
	public void pb_vd_BuscaTiposCliente(ArrayList<ClienteTipo> cliente){
		String sql = "select idTipo, descricao from tipocliente";
		ClienteTipo.pb_ar_sc_tipos.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<ClienteTipo> pr_ar_tipos = new ArrayList<ClienteTipo>();
			while(rs.next()){
				ClienteTipo clienteTipo = new ClienteTipo();
				clienteTipo.setPv_in_IDTipo(rs.getInt("idTipo"));
				clienteTipo.setPv_st_Descricao(rs.getString("descricao"));
				
				pr_ar_tipos.add(clienteTipo);
			}
			ClienteTipo.pb_ar_sc_tipos.addAll(pr_ar_tipos);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

}
	

}
