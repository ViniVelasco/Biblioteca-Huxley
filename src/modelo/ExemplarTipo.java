package modelo;

import java.util.ArrayList;

/**
 * Classe bean do tipo de exemplar.
 * @author Vinícius Velasco
 *
 */
public class ExemplarTipo {
	
	private String pv_st_Descricao;
	private int pv_in_IdTipo;
	public static ArrayList<ExemplarTipo> pb_ar_sc_secoes = new ArrayList<ExemplarTipo>();
	
	public String getPv_st_Descricao() {
		return pv_st_Descricao;
	}
	public void setPv_st_Descricao(String pv_st_Descricao) {
		this.pv_st_Descricao = pv_st_Descricao;
	}
	public int getPv_in_IdTipo() {
		return pv_in_IdTipo;
	}
	public void setPv_in_IdTipo(int pv_in_IdTipo) {
		this.pv_in_IdTipo = pv_in_IdTipo;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_Descricao();
	}
	
	
	

}
