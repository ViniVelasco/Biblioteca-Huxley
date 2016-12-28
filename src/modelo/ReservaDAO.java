package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dominio.Conexao;

/**
 * Classe de persistência de reserva.
 * @author Vinícius Velasco
 *
 */
public class ReservaDAO {
	
	private Connection pv_co_connection;
	
	/**
	 * Método construtor da classe que inicia a conexão com o banco de dados.
	 */
	public ReservaDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que busca todas as reservas do usuário
	 * @return ArrayList de reservas.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_ar_BuscarReservasDoUsuario(){
		String pr_st_sql = "select idReserva, CPF, reserva.idExemplar, titulo, status from reserva INNER JOIN exemplar ON exemplar.idExemplar = reserva.idExemplar where CPF like ? && status like '%Em Aberto%' ";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, Cliente.getPv_st_sc_CPF_Logado());
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()){
				
				Reserva reserva = new Reserva();
				reserva.setPv_in_IDReserva(rs.getInt("idReserva"));
				reserva.setPv_st_CPF(rs.getString("CPF"));
				reserva.setPv_st_NomeExemplar(rs.getString("titulo"));
				reserva.setPv_st_Status(rs.getString("status"));
				reserva.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				
				reservas.add(reserva);
				
			}
			
			return reservas;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return null;
	
	}
	/**
	 * Método que busca todas as reservas
	 * @return Array List de reservas
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_ar_BuscarReservas(){
		String pr_st_sql = "select idReserva, CPF, reserva.idExemplar, titulo, status from reserva INNER JOIN exemplar ON exemplar.idExemplar = reserva.idExemplar";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()){
				
				Reserva reserva = new Reserva();
				reserva.setPv_in_IDReserva(rs.getInt("idReserva"));
				reserva.setPv_st_CPF(rs.getString("CPF"));
				reserva.setPv_st_NomeExemplar(rs.getString("titulo"));
				reserva.setPv_st_Status(rs.getString("status"));
				reserva.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				
				reservas.add(reserva);
				
			}
			
			return reservas;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return null;
	
	}
	
	/**
	 * Método que efetua a reserva
	 * @param reserva
	 */
	@SuppressWarnings("rawtypes")
	public void pb_ar_EfetuarReserva(Reserva reserva){
		String pr_st_sql = "UPDATE reserva SET status = 'Finalizada' where idReserva = ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setInt(1, reserva.getPv_in_IDReserva());
			stmt.executeUpdate();
			stmt.close();
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}

	
	}
	
	/**
	 * Método que cancela a reserva
	 * @param reserva
	 */
	public void pb_vd_CancelarReserva(Reserva reserva){
		String pr_st_sql = "UPDATE reserva SET status = 'Cancelada' where idReserva = ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setInt(1, reserva.getPv_in_IDReserva());
			stmt.executeUpdate();
			stmt.close();
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}

	
	}
	/**
	 * Método que insere a reserva
	 * @param reserva
	 */
	public void pb_ar_Reservar(Reserva reserva){
		String pr_st_sql = "insert into reserva (CPF, idExemplar, Status) values (?,?,?)";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, reserva.getPv_st_CPF());
			stmt.setInt(2, reserva.getPv_in_IDExemplar());
			stmt.setString(3, reserva.getPv_st_Status());
			stmt.executeUpdate();
			stmt.close();
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}

	
	}
	
	/**
	 * Método reservas em aberto
	 * @return Array List de reservas
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_ar_BuscarReservasEmAberto(){
		String pr_st_sql = "select idReserva, CPF, reserva.idExemplar, titulo, status from reserva INNER JOIN exemplar ON exemplar.idExemplar = reserva.idExemplar where status like '%Em Aberto%'";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()){
				Reserva reserva = new Reserva();
				reserva.setPv_in_IDReserva(rs.getInt("idReserva"));
				reserva.setPv_st_CPF(rs.getString("CPF"));
				reserva.setPv_st_NomeExemplar(rs.getString("titulo"));
				reserva.setPv_st_Status(rs.getString("status"));
				reserva.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				
				reservas.add(reserva);
				
			}
			
			return reservas;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return null;
	
	}
	
	/**
	 * Método que busca reservas pro CPF
	 * @param buscaReserva
	 * @return Array List de Reservas
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_ar_BuscarReservasPorParteCPF(Reserva buscaReserva){
		String pr_st_sql = "select idReserva, CPF, reserva.idExemplar, titulo, status from reserva INNER JOIN exemplar ON exemplar.idExemplar = reserva.idExemplar where cpf like ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1,"%"+ buscaReserva.getPv_st_CPF() +"%");
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()){
				Reserva reserva = new Reserva();
				reserva.setPv_in_IDReserva(rs.getInt("idReserva"));
				reserva.setPv_st_CPF(rs.getString("CPF"));
				reserva.setPv_st_NomeExemplar(rs.getString("titulo"));
				reserva.setPv_st_Status(rs.getString("status"));
				reserva.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				
				reservas.add(reserva);
				
			}
			
			return reservas;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return null;
	
	}
	
	/**
	 * Método que busca reservas por parte do nome do usuário.
	 * @param buscaReserva
	 * @return Array List de reservas.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_ar_BuscarReservasPorParteNome(Reserva buscaReserva){
		String pr_st_sql = "select idReserva, CPF, reserva.idExemplar, titulo, status from reserva INNER JOIN exemplar ON exemplar.idExemplar = reserva.idExemplar where titulo like ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1,"%"+ buscaReserva.getPv_st_NomeExemplar() +"%");
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()){
				Reserva reserva = new Reserva();
				reserva.setPv_in_IDReserva(rs.getInt("idReserva"));
				reserva.setPv_st_CPF(rs.getString("CPF"));
				reserva.setPv_st_NomeExemplar(rs.getString("titulo"));
				reserva.setPv_st_Status(rs.getString("status"));
				reserva.setPv_in_IDExemplar(rs.getInt("idExemplar"));
				
				reservas.add(reserva);
				
			}
			
			return reservas;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		return null;
	
	}

}
