package modelo;

import java.util.ArrayList;

/**
 * Classe bean de Cidade, contendo todos os seus atributos.
 * 
 * @author Vinícius Velasco
 */

public class Cidade {
	
	private int pv_in_IDCidade;
	private String pv_st_NomeCidade;
	private int pv_in_IDEstadoReferente;
	public static ArrayList<Cidade> pb_ar_sc_cidades = new ArrayList<Cidade>();
	
	public Estado estadoReferente;
	
	
	
	public int getPv_in_IDEstadoReferente() {
		return pv_in_IDEstadoReferente;
	}
	public void setPv_in_IDEstadoReferente(int pv_in_IDEstadoReferente) {
		this.pv_in_IDEstadoReferente = pv_in_IDEstadoReferente;
	}
	public int getPv_in_IDCidade() {
		return pv_in_IDCidade;
	}
	public void setPv_in_IDCidade(int pv_in_IDCidade) {
		this.pv_in_IDCidade = pv_in_IDCidade;
	}
	public String getPv_st_NomeCidade() {
		return pv_st_NomeCidade;
	}
	public void setPv_st_NomeCidade(String pv_st_NomeCidade) {
		this.pv_st_NomeCidade = pv_st_NomeCidade;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_NomeCidade();
	}
	

}
