package modelo;

import java.util.ArrayList;

/**
 * Classe bean de Seção, contento todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class Secao {
	
	private String pv_st_Descricao;
	private int pv_in_IdSecao;
	public static ArrayList<Secao> pb_ar_sc_secoes = new ArrayList<Secao>();
	
	public String getPv_st_Descricao() {
		return pv_st_Descricao;
	}
	public void setPv_st_Descricao(String pv_st_Descricao) {
		this.pv_st_Descricao = pv_st_Descricao;
	}
	public int getPv_in_IdSecao() {
		return pv_in_IdSecao;
	}
	public void setPv_in_IdSecao(int pv_in_IdSecao) {
		this.pv_in_IdSecao = pv_in_IdSecao;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_Descricao();
	}
	


}
