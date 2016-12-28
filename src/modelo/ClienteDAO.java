package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persistência de Cliente.
 * Contém métodos de busca, alteração, inserção e exclusão.
 * @author Vinícius Velasco
 *
 */
public class ClienteDAO {
	
	private Connection pv_co_connection;
	/**
	 * Construtor da classe, contendo método que abre a conexão com o banco.
	 */
	public ClienteDAO() {
		this.pv_co_connection = Conexao.getConexao();
	}
	
	/**
	 * Método que realiza a inserção do cliente
	 * @param cliente
	 */
	public void pb_vd_CadastrarCliente(Cliente cliente) {
		String pr_st_sql = "insert into cliente (CPF, idEstadoCivil, idSexo, idEstado, idCidade, nome, dataNascimento, idTipo, telefone, login, senha, endereco) values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, cliente.getPv_st_CPF());
			stmt.setInt(2, cliente.getPv_in_EstadoCivil());
			stmt.setInt(3, cliente.getPv_in_IdSexo());
			stmt.setInt(4, cliente.getPv_in_IdEstado());
			stmt.setInt(5, cliente.getPv_in_IdCidade());
			stmt.setString(6, cliente.getPv_st_Nome());
			stmt.setDate(7, cliente.getPv_dt_DataNascimentoSQL());
			stmt.setInt(8, cliente.getPv_in_IdTipoCliente());
			stmt.setString(9, cliente.getPv_st_Telefone());
			stmt.setString(10, cliente.getPv_st_Login());
			stmt.setString(11, cliente.getPv_st_Senha());
			stmt.setString(12, cliente.getPv_st_Endereco());
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}

	}
	
	/**
	 * Método que verifica o login do cliente
	 * @param cliente
	 * @return retorna true se login correto e false se incorreto.
	 */
	public boolean verificaLogin(Cliente cliente){
		String sql = "select login, senha, idTipo, nome, CPF from cliente where login = " + "?" + "&& senha = " + "?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, cliente.getPv_st_Login());
			stmt.setString(2, cliente.getPv_st_Senha());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				cliente.setPv_st_Nome(rs.getString("nome"));
				cliente.setPv_st_CPF(rs.getString("CPF"));
				cliente.setPv_in_IdTipoCliente(rs.getInt("idTipo"));
				return true;
			}
			
			stmt.close();
			return false;
			
			
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
		return false;
	}
	
	/**
	 * Método que faz a alteração do cliente
	 * @param cliente
	 * @param CPFAntigo
	 */
	public void pb_vd_AlterarCliente(Cliente cliente, String CPFAntigo) {
		String pr_st_sql = "UPDATE cliente set CPF = ?, idEstadoCivil = ?, idSexo = ?, idEstado = ?, idCidade = ?, nome = ?, dataNascimento = ?, idTipo = ?, telefone = ?, login = ?, senha = ?, endereco = ? where CPF = ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, cliente.getPv_st_CPF());
			stmt.setInt(2, cliente.getPv_in_EstadoCivil());
			stmt.setInt(3, cliente.getPv_in_IdSexo());
			stmt.setInt(4, cliente.getPv_in_IdEstado());
			stmt.setInt(5, cliente.getPv_in_IdCidade());
			stmt.setString(6, cliente.getPv_st_Nome());
			stmt.setDate(7, cliente.getPv_dt_DataNascimentoSQL());
			stmt.setInt(8, cliente.getPv_in_IdTipoCliente());
			stmt.setString(9, cliente.getPv_st_Telefone());
			stmt.setString(10, cliente.getPv_st_Login());
			stmt.setString(11, cliente.getPv_st_Senha());
			stmt.setString(12, cliente.getPv_st_Endereco());
			stmt.setString(13, CPFAntigo);
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}

	}
	
	/**
	 * Método que busca o CPF do cliente.
	 * @param CPF
	 * @return retorna true se o CPF for encontrado e false se não for.
	 */
	public boolean pb_vd_buscaCPF(String CPF){
		
		String sql = "select CPF from cliente where CPF = " + "(?)";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, CPF);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				return true;
			}
			rs.close();
			stmt.close();
			return false;
			
		} catch(SQLException e){	
			
			//throw new RuntimeException(e);
			
		}
		return false;
	}
	
	/**
	 * Método que busca todos os clientes.
	 * @param clientes
	 */
	public void pb_vd_BuscaClientes(ArrayList<Cliente> clientes){
		String sql = "select CPF, nome from cliente order by nome";
		clientes.clear();
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setPv_st_Nome(rs.getString("nome"));
				cliente.setPv_st_CPF(rs.getString("CPF"));
				
				clientes.add(cliente);
				
			}
			rs.close();
			stmt.close();
			
	} catch(SQLException e){	
		
		
		
		//throw new RuntimeException(e);
		
	}
}
	/**
	 * Método que deleta um cliente.
	 * @param cliente
	 */
	public void pb_vd_DeleteClientes(Cliente cliente){
		String sql = "DELETE FROM cliente where CPF = ?";
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, cliente.getPv_st_CPF());
			stmt.executeUpdate();
			stmt.close();
			
	} catch(SQLException e){	
		
		
		
		//throw new RuntimeException(e);
		
	}
}
	/**
	 * Método que busca todos os clientes e retorna um Arraylist de Clientes.
	 * @return retorna um ArrayList de clientes.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosClientes(){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, cliente.idTipo , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, cliente.idCidade, cliente.idEstado, cliente.idEstadoCivil, cliente.idSexo, tipocliente.descricao as 'NomeTipoCliente' from cliente INNER JOIN sexo ON cliente.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = cliente.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = cliente.idCidade INNER JOIN estado ON cliente.idEstado = estado.idEstado INNER JOIN tipocliente ON cliente.idTipo = tipocliente.idTipo";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setPv_st_Nome(rs.getString("nome"));
				cliente.setPv_st_CPF(rs.getString("CPF"));
				cliente.setPv_st_Endereco(rs.getString("endereco"));
				cliente.setPv_dt_DataNascimentoSQL(rs.getDate("dataNascimento"));
				cliente.setPv_in_IdTipoCliente(rs.getInt("idTipo"));
				cliente.setPv_st_Telefone(rs.getString("telefone"));
				cliente.setPv_st_Login(rs.getString("Login"));
				cliente.setPv_st_Senha(rs.getString("senha"));
				cliente.setPv_in_IdCidade(rs.getInt("idCidade"));
				cliente.setPv_in_IdEstado(rs.getInt("idEstado"));
				cliente.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				cliente.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				cliente.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				cliente.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				cliente.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				cliente.estado = estado;
				
				//Tipo Cliente
				ClienteTipo tipo = new ClienteTipo();
				tipo.setPv_in_IDTipo(rs.getInt("idTipo"));
				tipo.setPv_st_Descricao(rs.getString("NomeTipoCliente"));
				cliente.tipo = tipo;
				
				clientes.add(cliente);

				
			}
			rs.close();
			stmt.close();
			return clientes;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Método que busca todos os clientes por nome e retorna um ArrayList de clientes.
	 * @param buscacliente
	 * @return retorna um ArrayList de clientes.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosClientesPorNome(Cliente buscacliente){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, cliente.idTipo , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, cliente.idCidade, cliente.idEstado, cliente.idEstadoCivil, cliente.idSexo, tipocliente.descricao as 'NomeTipoCliente' from cliente INNER JOIN sexo ON cliente.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = cliente.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = cliente.idCidade INNER JOIN estado ON cliente.idEstado = estado.idEstado INNER JOIN tipocliente ON cliente.idTipo = tipocliente.idTipo where nome like ?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%" + buscacliente.getPv_st_Nome() + "%");
			

			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setPv_st_Nome(rs.getString("nome"));
				cliente.setPv_st_CPF(rs.getString("CPF"));
				cliente.setPv_st_Endereco(rs.getString("endereco"));
				cliente.setPv_dt_DataNascimentoSQL(rs.getDate("dataNascimento"));
				cliente.setPv_in_IdTipoCliente(rs.getInt("idTipo"));
				cliente.setPv_st_Telefone(rs.getString("telefone"));
				cliente.setPv_st_Login(rs.getString("Login"));
				cliente.setPv_st_Senha(rs.getString("senha"));
				cliente.setPv_in_IdCidade(rs.getInt("idCidade"));
				cliente.setPv_in_IdEstado(rs.getInt("idEstado"));
				cliente.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				cliente.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				cliente.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				cliente.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				cliente.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				cliente.estado = estado;
				
				//Tipo Cliente
				ClienteTipo tipo = new ClienteTipo();
				tipo.setPv_in_IDTipo(rs.getInt("idTipo"));
				tipo.setPv_st_Descricao(rs.getString("NomeTipoCliente"));
				cliente.tipo = tipo;
				
				clientes.add(cliente);

				
			}
			rs.close();
			stmt.close();
			return clientes;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Busca todos os clientes por CPF e retorna um Array List de clientes.
	 * @param buscacliente
	 * @return retorna um ArrayList de clientes.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosClientesPorCPF(Cliente buscacliente){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, cliente.idTipo , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, cliente.idCidade, cliente.idEstado, cliente.idEstadoCivil, cliente.idSexo, tipocliente.descricao as 'NomeTipoCliente' from cliente INNER JOIN sexo ON cliente.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = cliente.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = cliente.idCidade INNER JOIN estado ON cliente.idEstado = estado.idEstado INNER JOIN tipocliente ON cliente.idTipo = tipocliente.idTipo where CPF like ?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%" + buscacliente.getPv_st_CPF() + "%");
			

			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setPv_st_Nome(rs.getString("nome"));
				cliente.setPv_st_CPF(rs.getString("CPF"));
				cliente.setPv_st_Endereco(rs.getString("endereco"));
				cliente.setPv_dt_DataNascimentoSQL(rs.getDate("dataNascimento"));
				cliente.setPv_in_IdTipoCliente(rs.getInt("idTipo"));
				cliente.setPv_st_Telefone(rs.getString("telefone"));
				cliente.setPv_st_Login(rs.getString("Login"));
				cliente.setPv_st_Senha(rs.getString("senha"));
				cliente.setPv_in_IdCidade(rs.getInt("idCidade"));
				cliente.setPv_in_IdEstado(rs.getInt("idEstado"));
				cliente.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				cliente.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				cliente.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				cliente.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				cliente.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				cliente.estado = estado;
				
				//Tipo Cliente
				ClienteTipo tipo = new ClienteTipo();
				tipo.setPv_in_IDTipo(rs.getInt("idTipo"));
				tipo.setPv_st_Descricao(rs.getString("NomeTipoCliente"));
				cliente.tipo = tipo;
				
				clientes.add(cliente);

				
			}
			rs.close();
			stmt.close();
			return clientes;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Método que verifica se o usuário fez empréstimos
	 * @param cliente
	 * @return true quando tem empréstimos e false quando não tem.
	 * 
	 */
	public boolean pb_bo_VerificaEmprestimosUsuario(Cliente cliente) {
		String sql = "select * from emprestimo where cpfCliente = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, cliente.getPv_st_CPF());
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
	 * Método que verifica reservas do cliente.
	 * @param cliente
	 * @return true quando tem reservas e false quando não tem.
	 */
	public boolean pb_bo_VerificaReservasCliente(Cliente cliente) {
		String sql = "select * from reserva where cpfCliente = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, cliente.getPv_st_CPF());
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
