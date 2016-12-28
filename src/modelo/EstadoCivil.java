package modelo;

import java.util.ArrayList;
/**
 * Classe bean do Estado Civil, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class EstadoCivil {
	
	private int pv_in_IDEstadoCivil;
	private String pv_st_Descricao;
	public static ArrayList<EstadoCivil> pb_ar_sc_estadoCivil = new ArrayList<EstadoCivil>();
	
	public int getPv_in_IDEstadoCivil() {
		return pv_in_IDEstadoCivil;
	}
	public void setPv_in_IDEstadoCivil(int pv_in_IDEstadoCivil) {
		this.pv_in_IDEstadoCivil = pv_in_IDEstadoCivil;
	}
	public String getPv_st_Descricao() {
		return pv_st_Descricao;
	}
	public void setPv_st_Descricao(String pv_st_Descricao) {
		this.pv_st_Descricao = pv_st_Descricao;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_Descricao();
	}
	
	

}
