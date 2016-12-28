package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;

/**
 * Classe de persistência da Cidade (Data Acess Object).
 * Contém métodos de inserção, alteração, exclusão e pesquisa de Cidade.
 * 
 * @author Vinícius Velasco
 */

public class CidadeDAO {
	
private Connection pv_co_connection;
	/**
	 * Método construtor de cidade que abre a conexão com o banco de dados.
	 */
	public CidadeDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca as cidades.
	 * @param cidades
	 * @param buscaCidade
	 */
	public void pb_vd_BuscaCidades(ArrayList<Cidade> cidades, Cidade buscaCidade){
		String sql = "select idCidade, nomeCidade from cidade where idEstadoReferente = ?";
		Cidade.pb_ar_sc_cidades.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, buscaCidade.getPv_in_IDEstadoReferente());
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Cidade> pr_ar_cidades = new ArrayList<Cidade>();
			while(rs.next()){
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				
				//secoes.add(secao);
				pr_ar_cidades.add(cidade);
			}
			Cidade.pb_ar_sc_cidades.addAll(pr_ar_cidades);
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		e.printStackTrace();
		//throw new RuntimeException(e);
		
	}
		
}
		/**
		 * Método que faz a inserção da cidade.
		 * @param cidade
		 */
		public void pb_vd_InserirCidade(Cidade cidade){
			String sql = "insert into cidade(nomeCidade, idEstadoReferente) values (?,?)";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				
				stmt.setString(1, cidade.getPv_st_NomeCidade());
				stmt.setInt(2, cidade.getPv_in_IDEstadoReferente());
				stmt.executeUpdate();
				stmt.close();
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}

}
		/**
		 * Método que altera a cidade
		 * @param cidade
		 */
		public void pb_vd_AlterarCidade(Cidade cidade){
			String sql = "UPDATE cidade SET nomeCidade = ?, idEstadoReferente = ? where idCidade = ?";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				
				stmt.setString(1, cidade.getPv_st_NomeCidade());
				stmt.setInt(2, cidade.getPv_in_IDEstadoReferente());
				stmt.setInt(3, cidade.getPv_in_IDCidade());
				stmt.executeUpdate();
				stmt.close();
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}

}
		/**
		 * Método que busca todas as cidades e retorna um ArrayList de cidades.
		 * @return retorna um ArrayList de cidades.
		 */
		public ArrayList pb_vd_BuscaTodasAsCidades(){
			String sql = "select idCidade, nomeCidade, nomeEstado, idEstado from cidade INNER JOIN estado ON cidade.idEstadoReferente = estado.idEstado;";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<Cidade> cidades = new ArrayList<Cidade>();
				
				while(rs.next()){
					Cidade cidade = new Cidade();
					cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
					cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
					Estado estado = new Estado();
					estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
					estado.setPv_in_IDEstado(rs.getInt("idEstado"));
					cidade.estadoReferente = estado;
					cidades.add(cidade);
				}
				
				return cidades;
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
			return null;
		}
		
		/**
		 * Métood que busca todas as cidades por nome e retorna um ArrayList de cidades
		 * @return retorna um ArrayList de cidades.
		 */
		public ArrayList pb_vd_BuscaTodasAsCidadesPorNome(Cidade buscaCidade){
			String sql = "select idCidade, nomeCidade, nomeEstado, idEstado from cidade INNER JOIN estado ON cidade.idEstadoReferente = estado.idEstado where nomeCidade like ?;";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				stmt.setString(1, "%"+buscaCidade.getPv_st_NomeCidade()+"%");
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<Cidade> cidades = new ArrayList<Cidade>();
				
				while(rs.next()){
					Cidade cidade = new Cidade();
					cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
					cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
					Estado estado = new Estado();
					estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
					estado.setPv_in_IDEstado(rs.getInt("idEstado"));
					cidade.estadoReferente = estado;
					cidades.add(cidade);
				}
				
				return cidades;
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
			return null;
		}
		
		/**
		 * Método que busca todas as cidades por nome do Estado e retorna um ArrayList
		 * @return retorna um ArrayList de cidades.
		 */
		public ArrayList pb_vd_BuscaTodasAsCidadesPorNomeEstado(Cidade buscaCidade){
			String sql = "select idCidade, nomeCidade, nomeEstado, idEstado from cidade INNER JOIN estado ON cidade.idEstadoReferente = estado.idEstado where nomeEstado like ?";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				stmt.setString(1, "%"+buscaCidade.estadoReferente.getPv_st_NomeEstado()+"%");
				ResultSet rs = stmt.executeQuery();
				
				ArrayList<Cidade> cidades = new ArrayList<Cidade>();
				
				while(rs.next()){
					Cidade cidade = new Cidade();
					cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
					cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
					Estado estado = new Estado();
					estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
					estado.setPv_in_IDEstado(rs.getInt("idEstado"));
					cidade.estadoReferente = estado;
					cidades.add(cidade);
				}
				
				return cidades;
				
		} catch(SQLException e){	
			e.printStackTrace();
			//throw new RuntimeException(e);
			
		}
			return null;
		}
		
		/**
		 * Método que deleta a cidade
		 * @param cidade
		 */
		public void pb_vd_DeletarCidade(Cidade cidade){
			String sql = "DELETE from cidade where IDCidade = ?";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				
				stmt.setInt(1, cidade.getPv_in_IDCidade());
				
				stmt.executeUpdate();
				stmt.close();
				
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
		}
		/**
		 * Método que verifica se a cidade está associada com algum usuário
		 * @param cidade
		 * @return true se está false se não
		 */
		public boolean pb_vd_VerificaAssociacaoCidade(Cidade cidade){
			String sql = "select * from funcionario where idCidade  = ?";
			String sql2 = "select * from cliente where idCidade  = ?";
			try{
				PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt.setInt(1, cidade.getPv_in_IDCidade());
				stmt2.setInt(1, cidade.getPv_in_IDCidade());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					return true;
				}
				
				ResultSet rs2 = stmt2.executeQuery();
				
				while(rs2.next()){
					return true;
				}
				rs2.close();
				stmt.close();
				rs.close();
				stmt.close();
				
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
			return false;

		}
}


