package modelo;


import java.sql.Date;
/**
 * Classe bean de funcionário.
 * @author Vinícius Velasco
 *
 */
public class Funcionario {
	
	private String pv_st_CPF;
	private int pv_in_IdCidade;
	private Date pv_dt_dataNascimento;
	private int pv_in_IdEstado;
	private int pv_in_IdSexo;
	private int pv_in_EstadoCivil;
	private String pv_st_Endereco;
	private String pv_st_Telefone;
	private int pv_in_IdTipoFuncionario;
	private String pv_st_Login;
	private String pv_st_Senha;
	private String pv_st_Nome;
	private static String pv_st_sc_CPF_Logado;
	private String pv_st_DataNascimentoFormatada;
	
	public Sexo sexo;
	public EstadoCivil estadoCivil;
	public Cidade cidade;
	public Estado estado;
	public FuncionarioTipo tipo;


	public String getPv_st_DataNascimentoFormatada() {
		return pv_st_DataNascimentoFormatada;
	}



	public void setPv_st_DataNascimentoFormatada(String pv_st_DataNascimentoFormatada) {
		this.pv_st_DataNascimentoFormatada = pv_st_DataNascimentoFormatada;
	}



	public int getPv_in_IdCidade() {
		return pv_in_IdCidade;
	}



	public void setPv_in_IdCidade(int pv_in_IdCidade) {
		this.pv_in_IdCidade = pv_in_IdCidade;
	}



	public Date getPv_dt_dataNascimento() {
		return pv_dt_dataNascimento;
	}



	public void setPv_dt_dataNascimento(Date pv_dt_dataNascimento) {
		this.pv_dt_dataNascimento = pv_dt_dataNascimento;
	}



	public int getPv_in_IdEstado() {
		return pv_in_IdEstado;
	}



	public void setPv_in_IdEstado(int pv_in_IdEstado) {
		this.pv_in_IdEstado = pv_in_IdEstado;
	}



	public int getPv_in_IdSexo() {
		return pv_in_IdSexo;
	}



	public void setPv_in_IdSexo(int pv_in_IdSexo) {
		this.pv_in_IdSexo = pv_in_IdSexo;
	}



	public int getPv_in_EstadoCivil() {
		return pv_in_EstadoCivil;
	}



	public void setPv_in_EstadoCivil(int pv_in_EstadoCivil) {
		this.pv_in_EstadoCivil = pv_in_EstadoCivil;
	}



	public String getPv_st_Endereco() {
		return pv_st_Endereco;
	}



	public void setPv_st_Endereco(String pv_st_Endereco) {
		this.pv_st_Endereco = pv_st_Endereco;
	}

	public int getPv_in_IdTipoFuncionario() {
		return pv_in_IdTipoFuncionario;
	}



	public void setPv_in_IdTipoFuncionario(int pv_in_IdTipoFuncionario) {
		this.pv_in_IdTipoFuncionario = pv_in_IdTipoFuncionario;
	}



	public String getPv_st_Login() {
		return pv_st_Login;
	}



	public void setPv_st_Login(String pv_st_Login) {
		this.pv_st_Login = pv_st_Login;
	}



	public String getPv_st_Senha() {
		return pv_st_Senha;
	}



	public void setPv_st_Senha(String pv_st_Senha) {
		this.pv_st_Senha = pv_st_Senha;
	}



	public String getPv_st_Nome() {
		return pv_st_Nome;
	}



	public void setPv_st_Nome(String pv_st_Nome) {
		this.pv_st_Nome = pv_st_Nome;
	}



	public String getPv_st_CPF() {
		return pv_st_CPF;
	}



	public void setPv_st_CPF(String pv_st_CPF) {
		this.pv_st_CPF = pv_st_CPF;
	}



	public static String getPv_st_sc_CPF_Logado() {
		return pv_st_sc_CPF_Logado;
	}



	public static void setPv_st_sc_CPF_Logado(String pv_st_sc_CPF_Logado) {
		Funcionario.pv_st_sc_CPF_Logado = pv_st_sc_CPF_Logado;
	}



	public String getPv_st_Telefone() {
		return pv_st_Telefone;
	}



	public void setPv_st_Telefone(String pv_st_Telefone) {
		this.pv_st_Telefone = pv_st_Telefone;
	}

}
