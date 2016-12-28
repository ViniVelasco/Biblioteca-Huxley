package modelo;

import java.util.ArrayList;
/**
 * Bean do Tipo de Cliente, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class ClienteTipo {
	
	private int pv_in_IDTipo;
	private String pv_st_Descricao;
	public static ArrayList<ClienteTipo> pb_ar_sc_tipos = new ArrayList<ClienteTipo>();
	
	public int getPv_in_IDTipo() {
		return pv_in_IDTipo;
	}
	public void setPv_in_IDTipo(int pv_in_IDTipo) {
		this.pv_in_IDTipo = pv_in_IDTipo;
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
