package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe persistência do livro.
 * @author Vinícius Velasco
 *
 */
public class ExemplarLivroDAO {

	private Connection pv_co_connection;
	/**
	 * Método construtor da classe que inicia conexão do banco de dados.
	 */
	public ExemplarLivroDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que insere exemplar base.
	 * @param livro
	 */
	public void pb_vd_CadastrarExemplarBase(ExemplarLivro livro) {
		String sql = "insert into exemplar (titulo, dataCadastro, quantidade, idioma, idTipoExemplar) values "
				+ "(?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, livro.getPv_st_Titulo());
			stmt.setString(2, livro.getPv_st_DataCadastro());
			stmt.setInt(3, livro.getPv_in_Quantidade());
			stmt.setString(4, livro.getPv_st_Idioma());
			stmt.setInt(5, livro.getPv_in_IdTipoExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	
	/**
	 * Método que apaga o exemplar base.
	 * @param livro
	 */
	public void pb_vd_ApagarExemplarBase(ExemplarLivro livro) {
		String sql = "DELETE FROM exemplar WHERE idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, livro.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	/**
	 * Método que apaga o lviro.
	 * @param livro
	 */
	public void pb_vd_ApagarLivro(ExemplarLivro livro) {
		String sql = "DELETE FROM exemplar_livro WHERE idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, livro.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	/**
	 * Método que verifica empréstimos do livro.
	 * @param livro
	 * @return retorna true se tem empréstimos e false se não tem empréstimos.
	 */
	public boolean pb_bo_VerificaEmprestimosLivro(ExemplarLivro livro) {
		String sql = "select * from emprestimo where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, livro.getPv_in_IdExemplar());
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
	 * Método que verifica se o livro tem reservas.
	 * @param livro
	 * @return retorna true se tem reservas e false se não tem reservas.
	 */
	public boolean pb_bo_VerificaReservas(ExemplarLivro livro) {
		String sql = "select * from reserva where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, livro.getPv_in_IdExemplar());
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
	 * Método que altera o exemplar base.
	 * @param livro
	 */
	public void pb_vd_AlterarExemplarBase(ExemplarLivro livro) {
		String sql = "UPDATE exemplar SET titulo = ?, quantidade = ?, idioma = ?, idTipoExemplar = ? where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, livro.getPv_st_Titulo());
			stmt.setInt(2, livro.getPv_in_Quantidade());
			stmt.setString(3, livro.getPv_st_Idioma());
			stmt.setInt(4, livro.getPv_in_IdTipoExemplar());
			stmt.setInt(5, livro.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}
	
	/**
	 * Método que busca id do exemplar base
	 * @param livro
	 */
	public void pb_vd_buscaIdExemplarBase(ExemplarLivro livro) {

		String sql = "select idExemplar from exemplar where dataCadastro = " + "(?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, livro.getPv_st_DataCadastro());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				livro.setPv_in_IdExemplar(rs.getInt("idExemplar"));
			}

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
	}

	/**
	 * Método que insre um livro
	 * @param livro
	 */
	public void pb_vd_CadastrarLivro(ExemplarLivro livro) {
		String sql = "insert into exemplar_livro (ISBN, idSecao, idExemplar, editora, autor) values "
				+ "(?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, livro.getPv_st_ISBN());
			stmt.setInt(2, livro.getPv_in_IDSecao());
			stmt.setInt(3, livro.getPv_in_IdExemplar());
			stmt.setString(4, livro.getPv_st_Editora());
			stmt.setString(5, livro.getPv_st_Autor());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}
	/**
	 * Método que altera um livro.
	 * @param livro
	 */
	public void pb_vd_AlterarLivro(ExemplarLivro livro) {
		String sql = "UPDATE exemplar_livro SET ISBN = ?, idSecao = ?, editora = ?, autor = ? where idExemplar = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, livro.getPv_st_ISBN());
			stmt.setInt(2, livro.getPv_in_IDSecao());
			stmt.setString(3, livro.getPv_st_Editora());
			stmt.setString(4, livro.getPv_st_Autor());
			stmt.setInt(5, livro.getPv_in_IdExemplar());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}

	}

	/**
	 * Método que busca um ISBN.
	 * @param ISBN
	 * @return retorna true se for encontrado e false se não for encontrado.
	 */
	public boolean pb_vd_buscaISBN(String ISBN) {

		String sql = "select ISBN from exemplar_livro where ISBN = " + "(?)";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, ISBN);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
			return false;

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
		return false;
	}
	
	/**
	 * Método que busca todos os livros.
	 * @return retorna um ArrayList de livros.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_buscarLivros() {

		String sql = "select titulo, exemplar.IdExemplar, idTipoExemplar, ISBN, quantidade, idSecao, idioma, autor, editora, quantidadeEmEmprestimo from exemplar INNER JOIN exemplar_livro ON exemplar.idExemplar = exemplar_livro.idExemplar order by titulo";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<ExemplarLivro> livros = new ArrayList<ExemplarLivro>();

			while (rs.next()) {

				ExemplarLivro livro = new ExemplarLivro();
				livro.setPv_st_Titulo(rs.getString("titulo"));
				livro.setPv_st_ISBN(rs.getString("ISBN"));
				livro.setPv_in_Quantidade(rs.getInt("quantidade"));
				livro.setPv_st_Idioma(rs.getString("idioma"));
				livro.setPv_st_Autor(rs.getString("autor"));
				livro.setPv_st_Editora(rs.getString("editora"));
				livro.setPv_in_IDSecao(rs.getInt("idSecao"));
				livro.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				livro.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				livro.setPv_in_IdTipoExemplar(rs.getInt("idTipoExemplar"));

				String sql2 = "select * from secao where idSecao = ?";
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt2.setInt(1, livro.getPv_in_IDSecao());
				ResultSet rs2 = stmt2.executeQuery();

				while (rs2.next()) {
					Secao secao = new Secao();
					secao.setPv_in_IdSecao(rs2.getInt("idSecao"));
					secao.setPv_st_Descricao(rs2.getString("descricao"));
					livro.secao = secao;
				}

				String sql3 = "select * from exemplar_tipo where id = ?";
				PreparedStatement stmt3 = pv_co_connection.prepareStatement(sql3);
				stmt3.setInt(1, livro.getPv_in_IdTipoExemplar());
				ResultSet rs3 = stmt3.executeQuery();

				while (rs3.next()) {
					ExemplarTipo et = new ExemplarTipo();
					et.setPv_in_IdTipo(rs3.getInt("id"));
					et.setPv_st_Descricao(rs3.getString("descricao"));
					livro.tipo = et;
				}

				livros.add(livro);

			}

			rs.close();
			stmt.close();
			return livros;

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return null;

	}
	
	/**
	 * Método que consulta todos os livros de acordo com a filtragem.
	 * @param exemplar
	 * @return Array List de livros.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_ConsultaLivrosFiltrados(ExemplarLivro exemplar) {

		String sql = "select exemplar.idExemplar, titulo, ISBN, quantidade, idSecao, idioma, autor, editora, quantidadeEmEmprestimo from exemplar INNER JOIN exemplar_livro ON exemplar.idExemplar = exemplar_livro.idExemplar where idSecao = ? order by titulo";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, exemplar.getPv_in_IDSecao());

			ResultSet rs = stmt.executeQuery();

			ArrayList<ExemplarLivro> livros = new ArrayList<ExemplarLivro>();

			while (rs.next()) {

				ExemplarLivro livro = new ExemplarLivro();
				livro.setPv_st_Titulo(rs.getString("titulo"));
				livro.setPv_st_ISBN(rs.getString("ISBN"));
				livro.setPv_in_Quantidade(rs.getInt("quantidade"));
				livro.setPv_st_Idioma(rs.getString("idioma"));
				livro.setPv_st_Autor(rs.getString("autor"));
				livro.setPv_st_Editora(rs.getString("editora"));
				livro.setPv_in_IDSecao(rs.getInt("idSecao"));
				livro.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				livro.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				
				String sql2 = "select * from secao where idSecao = ?";
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt2.setInt(1, livro.getPv_in_IDSecao());
				ResultSet rs2 = stmt2.executeQuery();

				while (rs2.next()) {
					Secao secao = new Secao();
					secao.setPv_in_IdSecao(rs2.getInt("idSecao"));
					secao.setPv_st_Descricao(rs2.getString("descricao"));
					livro.secao = secao;
				}

				String sql3 = "select * from exemplar_tipo where id = ?";
				PreparedStatement stmt3 = pv_co_connection.prepareStatement(sql3);
				stmt3.setInt(1, livro.getPv_in_IdTipoExemplar());
				ResultSet rs3 = stmt3.executeQuery();

				while (rs3.next()) {
					ExemplarTipo et = new ExemplarTipo();
					et.setPv_in_IdTipo(rs3.getInt("id"));
					et.setPv_st_Descricao(rs3.getString("descricao"));
					livro.tipo = et;
				}

				livros.add(livro);

			}

			rs.close();
			stmt.close();
			return livros;

		} catch (SQLException e) {

			// e.printStackTrace();

		}
		return null;

	}

}
