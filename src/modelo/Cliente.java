package modelo;

import java.sql.Date;
/**
 * Classe bean de cliente, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class Cliente {
	
	private String pv_st_CPF;
	private int pv_in_IdCidade;
	private String pv_st_dataNascimento;
	private int pv_in_IdEstado;
	private int pv_in_IdSexo;
	private int pv_in_EstadoCivil;
	private String pv_st_Endereco;
	private String pv_st_Telefone;
	private int pv_in_IdTipoCliente;
	private String pv_st_Login;
	private String pv_st_Senha;
	private String pv_st_Nome;
	private static String pv_st_sc_CPF_Logado;
	private Date pv_dt_DataNascimentoSQL;
	
	public Sexo sexo;
	public EstadoCivil estadoCivil;
	public Cidade cidade;
	public Estado estado;
	public ClienteTipo tipo;
	


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
		Cliente.pv_st_sc_CPF_Logado = pv_st_sc_CPF_Logado;
	}


	public int getPv_in_IdCidade() {
		return pv_in_IdCidade;
	}


	public void setPv_in_IdCidade(int pv_in_IdCidade) {
		this.pv_in_IdCidade = pv_in_IdCidade;
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

	public String getPv_st_Telefone() {
		return pv_st_Telefone;
	}


	public void setPv_st_Telefone(String pv_st_Telefone) {
		this.pv_st_Telefone = pv_st_Telefone;
	}


	public int getPv_in_IdTipoCliente() {
		return pv_in_IdTipoCliente;
	}


	public void setPv_in_IdTipoCliente(int pv_in_IdTipoCliente) {
		this.pv_in_IdTipoCliente = pv_in_IdTipoCliente;
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


	public String getPv_st_dataNascimento() {
		return pv_st_dataNascimento;
	}


	public void setPv_st_dataNascimento(String pv_st_dataNascimento) {
		this.pv_st_dataNascimento = pv_st_dataNascimento;
	}


	public Date getPv_dt_DataNascimentoSQL() {
		return pv_dt_DataNascimentoSQL;
	}


	public void setPv_dt_DataNascimentoSQL(Date pv_dt_DataNascimentoSQL) {
		this.pv_dt_DataNascimentoSQL = pv_dt_DataNascimentoSQL;
	}
	
	@Override
	public String toString(){
		return ("Nome: " + this.getPv_st_Nome() + " CPF: " + this.getPv_st_CPF());
	}



}
