package modelo;
/**
 * Classe bean de reserva, contendo todos os atributos.
 * @author Vinícius Velasco
 *
 */
public class Reserva {
	
	private int pv_in_IDReserva;
	private String pv_st_CPF;
	private String pv_st_Status;
	private int pv_in_IDExemplar;
	private String pv_st_NomeExemplar;
	
	
	
	public int getPv_in_IDExemplar() {
		return pv_in_IDExemplar;
	}
	public void setPv_in_IDExemplar(int pv_in_IDExemplar) {
		this.pv_in_IDExemplar = pv_in_IDExemplar;
	}
	public String getPv_st_NomeExemplar() {
		return pv_st_NomeExemplar;
	}
	public void setPv_st_NomeExemplar(String pv_st_NomeExemplar) {
		this.pv_st_NomeExemplar = pv_st_NomeExemplar;
	}
	public int getPv_in_IDReserva() {
		return pv_in_IDReserva;
	}
	public void setPv_in_IDReserva(int pv_in_IDReserva) {
		this.pv_in_IDReserva = pv_in_IDReserva;
	}
	public String getPv_st_CPF() {
		return pv_st_CPF;
	}
	public void setPv_st_CPF(String pv_st_CPF) {
		this.pv_st_CPF = pv_st_CPF;
	}
	public String getPv_st_Status() {
		return pv_st_Status;
	}
	public void setPv_st_Status(String pv_st_Status) {
		this.pv_st_Status = pv_st_Status;
	}
	
	@Override
	public String toString(){
		return "CPF cliente: " + this.pv_st_CPF + " Nome do Exemplar: " + this.pv_st_NomeExemplar + " Status: " + this.getPv_st_Status();
	}
	
	

}
