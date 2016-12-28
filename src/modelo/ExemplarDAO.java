package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persistência do Exemplar.
 * @author Vinícius Velasco
 *
 */
public class ExemplarDAO {
	private Connection pv_co_connection;
	
	/**
	 * Método construtor da classe que inicia a conexão com banco.
	 */
	public ExemplarDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}

	/**
	 * Método que busca todos os exemplares.
	 * @param exemplares
	 */
	public void pb_vd_BuscaExemplares(ArrayList<Exemplar> exemplares) {
		String sql = "select idExemplar, quantidade, quantidadeEmEmprestimo ,titulo, descricao from exemplar inner join exemplar_tipo on exemplar.idTipoExemplar = exemplar_tipo.id order by titulo";
		exemplares.clear();
		try {
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				exemplar.setPv_in_Quantidade(rs.getInt("quantidade"));
				exemplar.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				exemplar.setPv_st_Titulo(rs.getString("titulo"));
				exemplar.setPv_st_DescricaoTipoExemplar(rs.getString("descricao"));

				exemplares.add(exemplar);

				// secoes.add(secao);

			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
	}
	
	/**
	 * Método que busca todos os exemplares
	 * @return Array List de Exemplares
	 */
	public ArrayList pb_vd_BuscarExemplares() {
		String sql = "select idExemplar, quantidade, quantidadeEmEmprestimo ,titulo, descricao from exemplar inner join exemplar_tipo on exemplar.idTipoExemplar = exemplar_tipo.id order by titulo";
		try {
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
			while (rs.next()) {
				Exemplar exemplar = new Exemplar();
				exemplar.setPv_in_IdExemplar(rs.getInt("idExemplar"));
				exemplar.setPv_in_Quantidade(rs.getInt("quantidade"));
				exemplar.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				exemplar.setPv_st_Titulo(rs.getString("titulo"));
				exemplar.setPv_st_DescricaoTipoExemplar(rs.getString("descricao"));

				exemplares.add(exemplar);

				// secoes.add(secao);

			}
			rs.close();
			stmt.close();
			return exemplares;

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
		return null;
	}
	
	/**
	 * Método que adiciona um na quantidade em empréstimo
	 * @param exemplar
	 */
	public void pb_vd_AdicionaQuantidadeEmprestimo(Exemplar exemplar) {
		String sql = "select quantidadeEmEmprestimo from exemplar where idExemplar = ?";
		try {
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, exemplar.getPv_in_IdExemplar());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				exemplar.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
			}

			String sql2 = "UPDATE exemplar set quantidadeEmEmprestimo = ? where idExemplar = ?";
			PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
			stmt2.setInt(1, exemplar.getPv_in_QuantidadeEmEmprestimo() + 1);
			stmt2.setInt(2, exemplar.getPv_in_IdExemplar());
			stmt2.executeUpdate();

			rs.close();
			stmt.close();
			stmt2.close();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
	}

	/**
	 * Método que remove um do quantidade em empréstimo
	 * @param exemplar
	 */
	public void pb_vd_RemoveQuantidadeEmprestimo(Exemplar exemplar) {
		String sql = "select quantidadeEmEmprestimo from exemplar where idExemplar = ?";
		try {
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setInt(1, exemplar.getPv_in_IdExemplar());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				exemplar.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
			}

			String sql2 = "UPDATE exemplar set quantidadeEmEmprestimo = ? where idExemplar = ?";
			PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
			stmt2.setInt(1, exemplar.getPv_in_QuantidadeEmEmprestimo() - 1);
			stmt2.setInt(2, exemplar.getPv_in_IdExemplar());
			stmt2.executeUpdate();

			rs.close();
			stmt.close();
			stmt2.close();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}
	}

}
