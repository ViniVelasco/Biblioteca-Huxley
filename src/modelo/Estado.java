package modelo;

import java.util.ArrayList;
/**
 * Classe bean do Estado, contendo todos os seus atributos
 * @author Vinícius Velasco
 *
 */
public class Estado {

	private int pv_in_IDEstado;
	private String pv_st_NomeEstado;
	public static ArrayList<Estado> pb_ar_sc_estados = new ArrayList<Estado>();
	
	public int getPv_in_IDEstado() {
		return pv_in_IDEstado;
	}
	public void setPv_in_IDEstado(int pv_in_IDEstado) {
		this.pv_in_IDEstado = pv_in_IDEstado;
	}
	public String getPv_st_NomeEstado() {
		return pv_st_NomeEstado;
	}
	public void setPv_st_NomeEstado(String pv_st_NomeEstado) {
		this.pv_st_NomeEstado = pv_st_NomeEstado;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_NomeEstado();
	}
	
}
