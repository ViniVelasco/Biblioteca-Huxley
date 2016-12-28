package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persist�ncia do empr�stimo.
 * @author Vin�cius Velasco
 *
 */
public class EmprestimoDAO {
	
private Connection pv_co_connection;
	/**
	 * M�todo Construtor da Classe que abre a conex�o com o banco de dados.
	 */
	public EmprestimoDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * M�todo que faz a inser��o do empr�stimo.
	 * @param emprestimo
	 * @param cliente
	 * @param exemplar
	 * @param funcionario
	 */
	public void pb_vd_EfetuarEmprestimo(Emprestimo emprestimo, Cliente cliente, Exemplar exemplar, Funcionario funcionario) {
		String pr_st_sql = "insert into emprestimo (cpfFuncionario, CPFCliente, idExemplar, dataInicio, dataPrazo, status) values " + "(?, ?, ?, ?, ?, ?)";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, funcionario.getPv_st_CPF());
			stmt.setString(2, cliente.getPv_st_CPF());
			stmt.setInt(3, exemplar.getPv_in_IdExemplar());
			stmt.setDate(4, emprestimo.getPv_dt_DataEmprestimo());
			stmt.setDate(5, emprestimo.getPv_dt_DataPrazo());
			stmt.setString(6, "Em Aberto");
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}

	}
	
	/**
	 * M�todo de buscar empr�stimos
	 * @return retorna um Array List de empr�stimos
	 */
	public ArrayList pb_ar_BuscarEmprestimo() {
		String pr_st_sql = "select idEmprestimo, cpfFuncionario, cpfCliente, titulo, emprestimo.idExemplar, dataInicio, dataPrazo, emprestimo.`status`, multa, dataEntregue from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_CPFFuncionario(rs.getString("cpfFuncionario"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				emprestimo.setMulta(rs.getDouble("multa"));
				emprestimo.setPv_dt_DataEntrega(rs.getDate("dataEntregue"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;

	}
	/**
	 * M�todo que busca todos os empr�stimos (para o relat�rio)
	 * @return retorna um Array List de empr�stimos
	 */
	public ArrayList pb_ar_BuscarTodosEmprestimosRelatorio() {
		String pr_st_sql = "select idEmprestimo, nome, cpfCliente, titulo, dataInicio, dataPrazo, emprestimo.`status` from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar INNER JOIN cliente ON cliente.CPF = emprestimo.CPFCliente order by emprestimo.`status`";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_NomeCliente(rs.getString("nome"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;
	}
	
	/**
	 * M�todo que busca todos os empr�stimos em aberto (para o relat�rio).
	 * @return Array List de empr�stimos
	 */
	public ArrayList pb_ar_BuscarEmprestimosEmAbertoRelatorio() {
		String pr_st_sql = "select idEmprestimo, nome, cpfCliente, titulo, dataInicio, dataPrazo, emprestimo.`status` from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar INNER JOIN cliente ON cliente.CPF = emprestimo.CPFCliente where status like 'Em Aberto' order by emprestimo.`status`";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_NomeCliente(rs.getString("nome"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;
	}
	/**
	 * M�todo que busca todos os empr�stimos finalizados (para relat�rio).
	 * @return Array List de empr�stimos.
	 */
	public ArrayList pb_ar_BuscarEmprestimosFinalizadosRelatorio() {
		String pr_st_sql = "select idEmprestimo, nome, cpfCliente, titulo, dataInicio, dataPrazo, emprestimo.`status`, dataEntregue, Multa from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar INNER JOIN cliente ON cliente.CPF = emprestimo.CPFCliente where status like 'Finalizado' order by emprestimo.`status`";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_NomeCliente(rs.getString("nome"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				emprestimo.setPv_dt_DataEntrega(rs.getDate("dataEntregue"));
				emprestimo.setMulta(rs.getDouble("Multa"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;
	}
	/**
	 * M�todo que busca os empr�stimos por CPF do cliente.
	 * @param buscaEmprestimo
	 * @return Array List de empr�stimos.
	 */
	public ArrayList pb_ar_BuscarEmprestimoPorCPFCliente(Emprestimo buscaEmprestimo) {
		String pr_st_sql = "select idEmprestimo, cpfFuncionario, cpfCliente, titulo, emprestimo.idExemplar, dataInicio, dataPrazo, emprestimo.`status`, multa, dataEntregue from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar where cpfCliente like ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, "%"+buscaEmprestimo.getPv_st_CPFCliente()+"%");
			ResultSet rs = stmt.executeQuery();
			
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_CPFFuncionario(rs.getString("cpfFuncionario"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				emprestimo.setMulta(rs.getDouble("multa"));
				emprestimo.setPv_dt_DataEntrega(rs.getDate("dataEntregue"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;

	}
	/**
	 * M�todo que busca empr�stimos em aberto.
	 * @return Array List de empr�stimos.
	 */
	public ArrayList pb_ar_BuscarEmprestimoEmAberto() {
		String pr_st_sql = "select idEmprestimo, cpfFuncionario, cpfCliente, titulo, emprestimo.idExemplar, dataInicio, dataPrazo, emprestimo.`status`, multa, dataEntregue from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar where emprestimo.`status` like '%Em Aberto%'";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_CPFFuncionario(rs.getString("cpfFuncionario"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				emprestimo.setMulta(rs.getDouble("multa"));
				emprestimo.setPv_dt_DataEntrega(rs.getDate("dataEntregue"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;

	}
	/**
	 * M�todo que busca empr�stimos finalizados.
	 * @return Array List de empr�stimos.
	 */
	public ArrayList pb_ar_BuscarEmprestimoFinalizados() {
		String pr_st_sql = "select idEmprestimo, cpfFuncionario, cpfCliente, titulo, emprestimo.idExemplar, dataInicio, dataPrazo, emprestimo.`status`, multa, dataEntregue from emprestimo INNER JOIN exemplar ON exemplar.idExemplar = emprestimo.idExemplar where emprestimo.`status` like '%Finalizado%'";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_CPFFuncionario(rs.getString("cpfFuncionario"));
				emprestimo.setPv_st_NomeExemplar(rs.getString("titulo"));
				emprestimo.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				emprestimo.setMulta(rs.getDouble("multa"));
				emprestimo.setPv_dt_DataEntrega(rs.getDate("dataEntregue"));
				
				emprestimos.add(emprestimo);
				
			}
			
			stmt.close();
			return emprestimos;
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return null;

	}
	/**
	 * M�todo que verifica a quantidade em empr�stimo.
	 * @param exemplar
	 * @return retorna true caso seja maior ou igual que a do exemplar ou false caso seja menor.
	 */
	public boolean pb_vd_VerificaQuantidadeEmEmprestimo(Exemplar exemplar) {
		String pr_st_sql = "select quantidade, quantidadeEmEmprestimo from exemplar where idExemplar = ? ";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setInt(1, exemplar.getPv_in_IdExemplar());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				exemplar.setPv_in_QuantidadeEmEmprestimo(rs.getInt("quantidadeEmEmprestimo"));
				exemplar.setPv_in_Quantidade(rs.getInt("quantidade"));
				
			}
			
			if(exemplar.getPv_in_QuantidadeEmEmprestimo() >= exemplar.getPv_in_Quantidade()){
				return true;
			}
			
			rs.close();
			
			
			
			return false;

			
			
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
		return false;
	}
	/**
	 * M�todo que busca empr�stimos em aberto.
	 * @param emprestimos
	 */
	public void pb_vd_BuscaEmprestimosEmAberto(ArrayList<Emprestimo> emprestimos){
		String sql = "select * from emprestimo where status like '%em aberto%' order by cpfCliente ";
		emprestimos.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setPv_int_IDEmprestimo(rs.getInt("idEmprestimo"));
				emprestimo.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				emprestimo.setPv_st_CPFCliente(rs.getString("cpfCliente"));
				emprestimo.setPv_st_CPFFuncionario(rs.getString("CPFFuncionario"));
				emprestimo.setPv_dt_DataEmprestimo(rs.getDate("dataInicio"));
				emprestimo.setPv_dt_DataPrazo(rs.getDate("dataPrazo"));
				emprestimo.setPv_st_Status(rs.getString("status"));
				
				String sql2 = "select titulo from emprestimo INNER JOIN exemplar ON emprestimo.idExemplar = exemplar.idExemplar where emprestimo.idExemplar = ? && emprestimo.idEmprestimo = ?";
				PreparedStatement stmt2 = pv_co_connection.prepareStatement(sql2);
				stmt2.setInt(1, emprestimo.getPv_in_IDExemplar());
				stmt2.setInt(2, emprestimo.getPv_int_IDEmprestimo());
				
				ResultSet rs2 = stmt2.executeQuery();
				while(rs2.next()){
					emprestimo.setPv_st_NomeExemplar(rs2.getString("titulo"));
				}

				emprestimos.add(emprestimo);
				
			}

			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		//throw new RuntimeException(e);
		
	}
		
	}
	/**
	 * M�todo que efetua devolu��o dos livros.
	 * @param emprestimo
	 */
	public void pb_vd_EfetuarDevolucao(Emprestimo emprestimo){
		
		String sql = "UPDATE emprestimo SET status  = ?, dataEntregue = ?, multa = ? where idEmprestimo = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "Finalizado");
			stmt.setDate(2, emprestimo.getPv_dt_DataEntrega());
			stmt.setDouble(3, emprestimo.getMulta());
			stmt.setInt(4, emprestimo.getPv_int_IDEmprestimo());
			
			
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
