package modelo;

import java.sql.Date;
/**
 * Classe bean de Empréstimo, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class Emprestimo {

	private int pv_int_IDEmprestimo;
	private String pv_st_CPFFuncionario;
	private String pv_st_CPFCliente;
	private int pv_in_IDExemplar;
	private Date pv_dt_DataEmprestimo;
	private Date pv_dt_DataPrazo;
	private String pv_st_Status;
	private double pv_db_multa;
	private Date pv_dt_DataEntrega;
	private String pv_st_NomeExemplar;
	private String pv_st_NomeCliente;
	
	public int getPv_int_IDEmprestimo() {
		return pv_int_IDEmprestimo;
	}
	public void setPv_int_IDEmprestimo(int pv_int_IDEmprestimo) {
		this.pv_int_IDEmprestimo = pv_int_IDEmprestimo;
	}
	public String getPv_st_CPFFuncionario() {
		return pv_st_CPFFuncionario;
	}
	public void setPv_st_CPFFuncionario(String pv_st_CPFFuncionario) {
		this.pv_st_CPFFuncionario = pv_st_CPFFuncionario;
	}
	public String getPv_st_CPFCliente() {
		return pv_st_CPFCliente;
	}
	public void setPv_st_CPFCliente(String pv_st_CPFCliente) {
		this.pv_st_CPFCliente = pv_st_CPFCliente;
	}
	public int getPv_in_IDExemplar() {
		return pv_in_IDExemplar;
	}
	public void setPv_in_IDExemplar(int pv_in_IDExemplar) {
		this.pv_in_IDExemplar = pv_in_IDExemplar;
	}
	public Date getPv_dt_DataEmprestimo() {
		return pv_dt_DataEmprestimo;
	}
	public void setPv_dt_DataEmprestimo(Date pv_dt_DataEmprestimo) {
		this.pv_dt_DataEmprestimo = pv_dt_DataEmprestimo;
	}
	public Date getPv_dt_DataPrazo() {
		return pv_dt_DataPrazo;
	}
	public void setPv_dt_DataPrazo(Date pv_dt_DataPrazo) {
		this.pv_dt_DataPrazo = pv_dt_DataPrazo;
	}
	public String getPv_st_Status() {
		return pv_st_Status;
	}
	public void setPv_st_Status(String pv_st_Status) {
		this.pv_st_Status = pv_st_Status;
	}
	public double getMulta() {
		return pv_db_multa;
	}
	public void setMulta(double multa) {
		this.pv_db_multa = multa;
	}
	public Date getPv_dt_DataEntrega() {
		return pv_dt_DataEntrega;
	}
	public void setPv_dt_DataEntrega(Date pv_dt_DataEntrega) {
		this.pv_dt_DataEntrega = pv_dt_DataEntrega;
	}
	
	@Override
	public String toString(){
		return ("CPF do Cliente: " + this.pv_st_CPFCliente + " Exemplar: " + this.pv_st_NomeExemplar + " Data Início: " + this.pv_dt_DataEmprestimo);
	}
	public String getPv_st_NomeExemplar() {
		return pv_st_NomeExemplar;
	}
	public void setPv_st_NomeExemplar(String pv_st_NomeExemplar) {
		this.pv_st_NomeExemplar = pv_st_NomeExemplar;
	}
	public String getPv_st_NomeCliente() {
		return pv_st_NomeCliente;
	}
	public void setPv_st_NomeCliente(String pv_st_NomeCliente) {
		this.pv_st_NomeCliente = pv_st_NomeCliente;
	}

}
