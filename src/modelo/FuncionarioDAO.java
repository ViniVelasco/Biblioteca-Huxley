package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Conexao;
/**
 * Classe de persistência do funcionário
 * @author Vinícius Velasco
 *
 */
public class FuncionarioDAO {
	
private Connection pv_co_connection;
	/**
	 * Método construtor da classe que inicia a conexão com o banco de dados.
	 */
	public FuncionarioDAO() {
		this.pv_co_connection = Conexao.getConexao(); //definindo a conexão do DAO para a conexão do banco.
	}
	
	/**
	 * Método que verifica o login
	 * @param funcionario
	 * @return true se correto e false se incorreto.
	 */
	public boolean verificaLogin(Funcionario funcionario){
		String sql = "select login, senha, idTipoFuncionario, nome, CPF from funcionario where login = " + "?" + "&& senha = " + "?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getPv_st_Login());
			stmt.setString(2, funcionario.getPv_st_Senha());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				funcionario.setPv_st_Nome(rs.getString("nome"));
				funcionario.setPv_st_CPF(rs.getString("CPF"));
				funcionario.setPv_in_IdTipoFuncionario(rs.getInt("idTipoFuncionario"));
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
	 * Método que cadastra um funcionário
	 * @param funcionario
	 */
	public void pb_vd_CadastrarFuncionario(Funcionario funcionario) {
		String pr_st_sql = "insert into funcionario (CPF, idEstadoCivil, idSexo, idEstado, idCidade, nome, dataNascimento, idTipoFuncionario, telefone, login, senha, endereco) values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, funcionario.getPv_st_CPF());
			stmt.setInt(2, funcionario.getPv_in_EstadoCivil());
			stmt.setInt(3, funcionario.getPv_in_IdSexo());
			stmt.setInt(4, funcionario.getPv_in_IdEstado());
			stmt.setInt(5, funcionario.getPv_in_IdCidade());
			stmt.setString(6, funcionario.getPv_st_Nome());
			stmt.setDate(7, funcionario.getPv_dt_dataNascimento());
			stmt.setInt(8, funcionario.getPv_in_IdTipoFuncionario());
			stmt.setString(9, funcionario.getPv_st_Telefone());
			stmt.setString(10, funcionario.getPv_st_Login());
			stmt.setString(11, funcionario.getPv_st_Senha());
			stmt.setString(12, funcionario.getPv_st_Endereco());
			
			stmt.executeUpdate();
			stmt.close();
			
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}

	}
	
	/**
	 * Método que busca um CPF;
	 * @param CPF
	 * @return true se encontrando false se não encontrado.
	 */
	public boolean pb_vd_buscaCPF(String CPF){
		
		String sql = "select CPF from funcionario where CPF = " + "(?)";
		
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
	 * Método que busca todos os funcionários
	 * @return ArrayList de funcionários.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosFuncionarios(){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, funcionario.idTipoFuncionario , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, funcionario.idCidade, funcionario.idEstado, funcionario.idEstadoCivil, funcionario.idSexo, tipofuncionario.cargo as 'NomeTipoFuncionario' from funcionario INNER JOIN sexo ON funcionario.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = funcionario.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = funcionario.idCidade INNER JOIN estado ON funcionario.idEstado = estado.idEstado INNER JOIN tipofuncionario ON funcionario.idTipoFuncionario = tipofuncionario.idTipoFuncionario";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setPv_st_Nome(rs.getString("nome"));
				funcionario.setPv_st_CPF(rs.getString("CPF"));
				funcionario.setPv_st_Endereco(rs.getString("endereco"));
				funcionario.setPv_dt_dataNascimento(rs.getDate("dataNascimento"));
				funcionario.setPv_in_IdTipoFuncionario(rs.getInt("idTipoFuncionario"));
				funcionario.setPv_st_Telefone(rs.getString("telefone"));
				funcionario.setPv_st_Login(rs.getString("Login"));
				funcionario.setPv_st_Senha(rs.getString("senha"));
				funcionario.setPv_in_IdCidade(rs.getInt("idCidade"));
				funcionario.setPv_in_IdEstado(rs.getInt("idEstado"));
				funcionario.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				funcionario.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				funcionario.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				funcionario.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				funcionario.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				funcionario.estado = estado;
				
				//Tipo Cliente
				FuncionarioTipo tipo = new FuncionarioTipo();
				tipo.setPv_in_IDTipoFuncionario(rs.getInt("idTipoFuncionario"));
				tipo.setPv_st_Cargo(rs.getString("NomeTipoFuncionario"));
				funcionario.tipo = tipo;
				
				funcionarios.add(funcionario);

				
			}
			rs.close();
			stmt.close();
			return funcionarios;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Método que busca todos os funcionários por nome
	 * @param buscarFuncionario
	 * @return ArrayList de funcionários.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosFuncionariosPorNome(Funcionario buscarFuncionario){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, funcionario.idTipoFuncionario , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, funcionario.idCidade, funcionario.idEstado, funcionario.idEstadoCivil, funcionario.idSexo, tipofuncionario.cargo as 'NomeTipoFuncionario' from funcionario INNER JOIN sexo ON funcionario.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = funcionario.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = funcionario.idCidade INNER JOIN estado ON funcionario.idEstado = estado.idEstado INNER JOIN tipofuncionario ON funcionario.idTipoFuncionario = tipofuncionario.idTipoFuncionario where nome like ?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%"+buscarFuncionario.getPv_st_Nome()+"%");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setPv_st_Nome(rs.getString("nome"));
				funcionario.setPv_st_CPF(rs.getString("CPF"));
				funcionario.setPv_st_Endereco(rs.getString("endereco"));
				funcionario.setPv_dt_dataNascimento(rs.getDate("dataNascimento"));
				funcionario.setPv_in_IdTipoFuncionario(rs.getInt("idTipoFuncionario"));
				funcionario.setPv_st_Telefone(rs.getString("telefone"));
				funcionario.setPv_st_Login(rs.getString("Login"));
				funcionario.setPv_st_Senha(rs.getString("senha"));
				funcionario.setPv_in_IdCidade(rs.getInt("idCidade"));
				funcionario.setPv_in_IdEstado(rs.getInt("idEstado"));
				funcionario.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				funcionario.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				funcionario.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				funcionario.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				funcionario.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				funcionario.estado = estado;
				
				//Tipo Cliente
				FuncionarioTipo tipo = new FuncionarioTipo();
				tipo.setPv_in_IDTipoFuncionario(rs.getInt("idTipoFuncionario"));
				tipo.setPv_st_Cargo(rs.getString("NomeTipoFuncionario"));
				funcionario.tipo = tipo;
				
				funcionarios.add(funcionario);

				
			}
			rs.close();
			stmt.close();
			return funcionarios;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Método que busca todos os funcionários por CPF.
	 * @param buscarFuncionario
	 * @return ArrayList de funcionários.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList pb_vd_BuscaTodosFuncionariosPorCPF(Funcionario buscarFuncionario){
		String sql = "select CPF, nome, endereco, estadocivil.descricao, dataNascimento, funcionario.idTipoFuncionario , telefone, login, senha, sexo.nomeSexo, cidade.nomeCidade, estado.nomeEstado, funcionario.idCidade, funcionario.idEstado, funcionario.idEstadoCivil, funcionario.idSexo, tipofuncionario.cargo as 'NomeTipoFuncionario' from funcionario INNER JOIN sexo ON funcionario.idSexo = sexo.idSexo INNER JOIN estadocivil ON estadocivil.idEstadoCivil = funcionario.idEstadoCivil INNER JOIN cidade ON cidade.idCidade = funcionario.idCidade INNER JOIN estado ON funcionario.idEstado = estado.idEstado INNER JOIN tipofuncionario ON funcionario.idTipoFuncionario = tipofuncionario.idTipoFuncionario where CPF like ?";
		
		try{
			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, "%"+buscarFuncionario.getPv_st_CPF()+"%");
			ResultSet rs = stmt.executeQuery();
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			
			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setPv_st_Nome(rs.getString("nome"));
				funcionario.setPv_st_CPF(rs.getString("CPF"));
				funcionario.setPv_st_Endereco(rs.getString("endereco"));
				funcionario.setPv_dt_dataNascimento(rs.getDate("dataNascimento"));
				funcionario.setPv_in_IdTipoFuncionario(rs.getInt("idTipoFuncionario"));
				funcionario.setPv_st_Telefone(rs.getString("telefone"));
				funcionario.setPv_st_Login(rs.getString("Login"));
				funcionario.setPv_st_Senha(rs.getString("senha"));
				funcionario.setPv_in_IdCidade(rs.getInt("idCidade"));
				funcionario.setPv_in_IdEstado(rs.getInt("idEstado"));
				funcionario.setPv_in_EstadoCivil(rs.getInt("idEstadoCivil"));
				funcionario.setPv_in_IdSexo(rs.getInt("idSexo"));
				
				
				//Sexo
				Sexo sexo = new Sexo();
				sexo.setPv_in_IDSexo(rs.getInt("idSexo"));
				sexo.setPv_st_nomeSexo(rs.getString("nomeSexo"));
				funcionario.sexo = sexo;
				
				//Estado Civil
				EstadoCivil estadoCivil = new EstadoCivil();
				estadoCivil.setPv_in_IDEstadoCivil(rs.getInt("idEstadoCivil"));
				estadoCivil.setPv_st_Descricao(rs.getString("descricao"));
				funcionario.estadoCivil = estadoCivil;
				
				//Cidade
				Cidade cidade = new Cidade();
				cidade.setPv_in_IDCidade(rs.getInt("idCidade"));
				cidade.setPv_st_NomeCidade(rs.getString("nomeCidade"));
				funcionario.cidade = cidade;
				
				//Estado
				Estado estado = new Estado();
				estado.setPv_in_IDEstado(rs.getInt("idEstado"));
				estado.setPv_st_NomeEstado(rs.getString("nomeEstado"));
				funcionario.estado = estado;
				
				//Tipo Cliente
				FuncionarioTipo tipo = new FuncionarioTipo();
				tipo.setPv_in_IDTipoFuncionario(rs.getInt("idTipoFuncionario"));
				tipo.setPv_st_Cargo(rs.getString("NomeTipoFuncionario"));
				funcionario.tipo = tipo;
				
				funcionarios.add(funcionario);

				
			}
			rs.close();
			stmt.close();
			return funcionarios;
			
	} catch(SQLException e){	
		e.printStackTrace();
	}
		return null;
	}
	
	/**
	 * Método que verifica empréstimos de funcionário
	 * @param funcionario
	 * @return true se tem empréstimos false se não tem
	 */
	public boolean pb_bo_VerificaEmprestimosFuncionario(Funcionario funcionario) {
		String sql = "select * from emprestimo where cpfFuncionario = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getPv_st_CPF());
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
	 * Método que altera funcionário
	 * @param funcionario
	 * @param CPFAntigo
	 */
	public void pb_vd_AlterarFuncionaro(Funcionario funcionario, String CPFAntigo) {
		
		String pr_st_sql = "UPDATE funcionario SET CPF = ?, idEstadoCivil = ?, idSexo = ?, idEstado = ?, idCidade = ?, nome = ?, dataNascimento = ?, idTipoFuncionario = ?, telefone = ?, Login = ?, senha = ?, endereco = ? WHERE CPF = ?";
		
		try{
			
			PreparedStatement stmt = pv_co_connection.prepareStatement(pr_st_sql);
			stmt.setString(1, funcionario.getPv_st_CPF());
			stmt.setInt(2, funcionario.getPv_in_EstadoCivil());
			stmt.setInt(3, funcionario.getPv_in_IdSexo());
			stmt.setInt(4, funcionario.getPv_in_IdEstado());
			stmt.setInt(5, funcionario.getPv_in_IdCidade());
			stmt.setString(6, funcionario.getPv_st_Nome());
			stmt.setDate(7, funcionario.getPv_dt_dataNascimento());
			stmt.setInt(8, funcionario.getPv_in_IdTipoFuncionario());
			stmt.setString(9, funcionario.getPv_st_Telefone());
			stmt.setString(10, funcionario.getPv_st_Login());
			stmt.setString(11, funcionario.getPv_st_Senha());
			stmt.setString(12, funcionario.getPv_st_Endereco());
			stmt.setString(13, CPFAntigo);
			
			stmt.executeUpdate();
			stmt.close();
		
		} catch(SQLException e){	
			
			e.printStackTrace();
		
		}
	}
	
	/**
	 * Método que deleta funcionário
	 * @param funcionario
	 */
	public void pb_bo_DeletarFuncionario(Funcionario funcionario) {
		String sql = "DELETE from funcionario where cpf = ?";

		try {

			PreparedStatement stmt = pv_co_connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getPv_st_CPF());
			stmt.executeUpdate();

		} catch (SQLException e) {

			// throw new RuntimeException(e);

		}


	}

}
