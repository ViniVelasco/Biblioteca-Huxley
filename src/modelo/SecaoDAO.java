package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;


/**
 * Classe bean de seção, contendo todos seus atributos.
 * @author Vinicius Velasco
 *
 */
public class SecaoDAO {
	
private Connection pv_co_connection;
	
	public SecaoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	public void pb_vd_BuscaSecoes(ArrayList<Secao> secoes){
		String sql = "select idSecao, descricao from secao";
		Secao.pb_ar_sc_secoes.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Secao> pr_ar_secoes = new ArrayList<Secao>();
			while(rs.next()){
				Secao secao = new Secao();
				secao.setPv_in_IdSecao(rs.getInt("idSecao"));
				secao.setPv_st_Descricao(rs.getString("descricao"));
				

				pr_ar_secoes.add(secao);
			}
			Secao.pb_ar_sc_secoes.addAll(pr_ar_secoes);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}

 }
	
	public ArrayList pb_vd_BuscaSecoes(){
		String sql = "select idSecao, descricao from secao";
		Secao.pb_ar_sc_secoes.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Secao> pr_ar_secoes = new ArrayList<Secao>();
			while(rs.next()){
				Secao secao = new Secao();
				secao.setPv_in_IdSecao(rs.getInt("idSecao"));
				secao.setPv_st_Descricao(rs.getString("descricao"));
				

				pr_ar_secoes.add(secao);
			}
			rs.close();
			stmt.close();
			return pr_ar_secoes;
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		return null;

 }
	
	public ArrayList pb_vd_BuscaSecoesPorNome(Secao buscaSecao){
		String sql = "select idSecao, descricao from secao where descricao like ?";
		Secao.pb_ar_sc_secoes.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%"+buscaSecao.getPv_st_Descricao()+"%");
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Secao> pr_ar_secoes = new ArrayList<Secao>();
			while(rs.next()){
				Secao secao = new Secao();
				secao.setPv_in_IdSecao(rs.getInt("idSecao"));
				secao.setPv_st_Descricao(rs.getString("descricao"));
				

				pr_ar_secoes.add(secao);
			}
			rs.close();
			stmt.close();
			return pr_ar_secoes;
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		return null;

 }
	
	public void pb_vd_InserirSecao(Secao secao){
		String sql = "insert into secao(descricao) values (?)";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			stmt.setString(1, secao.getPv_st_Descricao());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
}
	public void pb_vd_AlterarSecao(Secao secao){
		String sql = "UPDATE secao SET descricao = ? where idSecao = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			stmt.setString(1, secao.getPv_st_Descricao());
			stmt.setInt(2, secao.getPv_in_IdSecao());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
}
	public boolean pb_vd_BuscaAssociacaoLivro(Secao secao){
		String sql = "SELECT * from exemplar_Livro where idSecao = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, secao.getPv_in_IdSecao());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				return true;
			}
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		return false;
}
	
	
	
	public boolean pb_vd_BuscaAssociacaoFilme(Secao secao){
		String sql = "SELECT * from exemplar_Filme where idSecao = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				
				return true;
				
			}
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		return false;
}
		
		public void pb_vd_DeletarSecao(Secao secao){
			String sql = "DELETE from secao WHERE idSecao = ?";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				
				stmt.setInt(1, secao.getPv_in_IdSecao());
				stmt.executeUpdate();
				stmt.close();
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}

}
	
}
	
