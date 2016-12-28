package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe persistência de filme.
 * @author Vinícius Velasco
 *
 */
public class ExemplarFilmeDAO {

	private Connection pv_co_connection;
	
	/**
	 * Método construtor da classe que inicia a conexão com banco.
	 */
	public ExemplarFilmeDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}

	/**
	 * Método que cadastra um exemplar base.
	 * @param filme
	 */
	public void pb_vd_CadastrarExemplarBase(ExemplarFilme filme) {
		String pr_st_sql = "insert into exemplar (titulo, dataCadastro, quantidade, idioma, idTipoExemplar) values "
				+ "(?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, filme.getPv_st_Titulo());
			stmt.setString(2, filme.getPv_st_DataCadastro());
			stmt.setInt(3, filme.getPv_in_Quantidade());
			stmt.setString(4, filme.getPv_st_Idioma());
			stmt.setInt(5, filme.getPv_in_IdTipoExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	
	/**
	 * Método altera exemplar base.
	 * @param filme
	 */
	public void pb_vd_AlterarExemplarBase(ExemplarFilme filme) {
		String pr_st_sql = "UPDATE exemplar SET titulo = ?, quantidade = ?, idioma = ?, idTipoExemplar = ? where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, filme.getPv_st_Titulo());
			stmt.setInt(2, filme.getPv_in_Quantidade());
			stmt.setString(3, filme.getPv_st_Idioma());
			stmt.setInt(4, filme.getPv_in_IdTipoExemplar());
			stmt.setInt(5, filme.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}

	/**
	 * Método que insere um filme.
	 * @param filme
	 */
	public void pb_vd_CadastrarFilme(ExemplarFilme filme) {
		String pr_st_sql = "insert into exemplar_filme (idSecao, idExemplar, nomeDiretor, duracao, paisOrigem) values "
				+ "(?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setInt(1, filme.getPv_in_IDSecao());
			stmt.setInt(2, filme.getPv_in_IdExemplar());
			stmt.setString(3, filme.getPv_st_NomeDiretor());
			stmt.setString(4, filme.getPv_st_Duracao());
			stmt.setString(5, filme.getPv_st_pais_origem());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	/**
	 * Método que altera um filme.
	 * @param filme
	 */
	public void pb_vd_AlterarFilme(ExemplarFilme filme) {
		String pr_st_sql = "UPDATE exemplar_filme SET idSecao = ?, nomeDiretor = ?, duracao = ?, paisOrigem = ? where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setInt(1, filme.getPv_in_IDSecao());
			stmt.setString(2, filme.getPv_st_NomeDiretor());
			stmt.setString(3, filme.getPv_st_Duracao());
			stmt.setString(4, filme.getPv_st_pais_origem());
			stmt.setInt(5, filme.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	
	
	/**
	 * Método que busca o ID de um exemplar.
	 * @param filme
	 */
	public void pb_vd_buscaIdExemplarBase(ExemplarFilme filme) {

		String sql = "select idExemplar from exemplar where dataCadastro = " + "(?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, filme.getPv_st_DataCadastro());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				filme.setPv_in_IdExemplar(rs.getInt("idExemplar"));
			}

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
	}
	
	/**
	 * Método que busca filmes.
	 * @return retorna um Array List de filmes.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_buscarFilmes() {

		String sql = "select titulo, exemplar.IdExemplar, idTipoExemplar, idFilme, quantidade, idSecao, idioma, nomeDiretor, duracao, paisOrigem, quantidadeEmEmprestimo from exemplar INNER JOIN exemplar_filme ON exemplar.idExemplar = exemplar_filme.idExemplar order by titulo";

		try {
			;
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ExemplarFilme> filmes = new ArrayList<ExemplarFilme>();

			while (rs.next()) {
				
				ExemplarFilme filme = new ExemplarFilme();
				filme.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				filme.setPv_st_Titulo(rs.getString("titulo"));
				filme.setPv_in_IdTipoExemplar(rs.getInt("idTipoExemplar"));
				filme.setPv_in_IDFilme(rs.getInt("idFilme"));
				filme.setPv_in_Quantidade(rs.getInt("quantidade"));
				filme.setPv_in_IDSecao(rs.getInt("idSecao"));
				filme.setPv_st_Idioma(rs.getString("idioma"));
				filme.setPv_st_NomeDiretor(rs.getString("nomeDiretor"));
				filme.setPv_st_Duracao(rs.getString("duracao"));
				filme.setPv_st_pais_origem(rs.getString("paisOrigem"));
				filme.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				
				String sql2 = "select * from secao where idSecao = ?";
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt2.setInt(1, filme.getPv_in_IDSecao());
				ResultSet rs2 = stmt2.executeQuery();
				
				while (rs2.next()) {
					Secao secao = new Secao();
					secao.setPv_in_IdSecao(rs2.getInt("idSecao"));
					secao.setPv_st_Descricao(rs2.getString("descricao"));
					filme.secao = secao;
				}
				
				String sql3 = "select * from exemplar_tipo where id = ?";
				PreparedStatement stmt3 = pv_co_connection.prepareStatement(sql3);
				stmt3.setInt(1, filme.getPv_in_IdTipoExemplar());
				ResultSet rs3 = stmt3.executeQuery();

				while (rs3.next()) {
					ExemplarTipo et = new ExemplarTipo();
					et.setPv_in_IdTipo(rs3.getInt("id"));
					et.setPv_st_Descricao(rs3.getString("descricao"));
					filme.tipo = et;
				}
				
				filmes.add(filme);
			}
			
			return filmes;

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new RuntimeException(e);

		}
		return null;
	}
	
	/**
	 * Método que busca todos os filmes de acordo com a filtragem
	 * @param exemplar
	 * @return Array List de filmes.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_buscarFilmesFiltrado(ExemplarFilme exemplar) {

		String sql = "select titulo, exemplar.IdExemplar, idTipoExemplar, idFilme, quantidade, idSecao, idioma, nomeDiretor, duracao, paisOrigem, quantidadeEmEmprestimo from exemplar INNER JOIN exemplar_filme ON exemplar.idExemplar = exemplar_filme.idExemplar where idSecao = ? order by titulo";

		try {
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, exemplar.getPv_in_IDSecao());
			ResultSet rs = stmt.executeQuery();
			
			
			ArrayList<ExemplarFilme> filmes = new ArrayList<ExemplarFilme>();

			while (rs.next()) {
				
				ExemplarFilme filme = new ExemplarFilme();
				filme.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				filme.setPv_st_Titulo(rs.getString("titulo"));
				filme.setPv_in_IdTipoExemplar(rs.getInt("idTipoExemplar"));
				filme.setPv_in_IDFilme(rs.getInt("idFilme"));
				filme.setPv_in_Quantidade(rs.getInt("quantidade"));
				filme.setPv_in_IDSecao(rs.getInt("idSecao"));
				filme.setPv_st_Idioma(rs.getString("idioma"));
				filme.setPv_st_NomeDiretor(rs.getString("nomeDiretor"));
				filme.setPv_st_Duracao(rs.getString("duracao"));
				filme.setPv_st_pais_origem(rs.getString("paisOrigem"));
				filme.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				
				String sql2 = "select * from secao where idSecao = ?";
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt2.setInt(1, filme.getPv_in_IDSecao());
				ResultSet rs2 = stmt2.executeQuery();
				
				while (rs2.next()) {
					Secao secao = new Secao();
					secao.setPv_in_IdSecao(rs2.getInt("idSecao"));
					secao.setPv_st_Descricao(rs2.getString("descricao"));
					filme.secao = secao;
				}
				
				String sql3 = "select * from exemplar_tipo where id = ?";
				PreparedStatement stmt3 = pv_co_connection.prepareStatement(sql3);
				stmt3.setInt(1, filme.getPv_in_IdTipoExemplar());
				ResultSet rs3 = stmt3.executeQuery();

				while (rs3.next()) {
					ExemplarTipo et = new ExemplarTipo();
					et.setPv_in_IdTipo(rs3.getInt("id"));
					et.setPv_st_Descricao(rs3.getString("descricao"));
					filme.tipo = et;
				}
				
				filmes.add(filme);
			}
			
			return filmes;

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new RuntimeException(e);

		}
		return null;
	}
	
	/**
	 * Método que verifica empréstimos do filme.
	 * @param filme
	 * @return retorna true se tem empréstimos e false se não.
	 */
	public boolean pb_bo_VerificaEmprestimosFilme(ExemplarFilme filme) {
		String sql = "select * from emprestimo where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, filme.getPv_in_IdExemplar());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return true;
			}

			return false;
		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

		return false;

	}
	/**
	 * Método que apaga um exemplar base.
	 * @param filme
	 */
	public void pb_vd_ApagarExemplarBase(ExemplarFilme filme) {
		String sql = "DELETE FROM exemplar WHERE idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, filme.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	
	/**
	 * Método que apaga um filme
	 * @param filme
	 */
	public void pb_vd_ApagarFilme(ExemplarFilme filme) {
		String sql = "DELETE FROM exemplar_filme WHERE idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, filme.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	/**
	 * Método que verifica reservas
	 * @param filme
	 * @return true se tem reservas false se não tem
	 */
	public boolean pb_bo_VerificaReservas(ExemplarFilme filme) {
		String sql = "select * from reserva where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, filme.getPv_in_IdExemplar());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return true;
			}

			return false;
		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

		return false;

	}

}
