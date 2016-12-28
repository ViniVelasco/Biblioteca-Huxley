package modelo;

import java.util.ArrayList;
/**
 * Classe bean de sexo, contendo todos seus atributos.
 * @author Vinícius Velasco
 *
 */
public class Sexo {
	
	private int pv_in_IDSexo;
	private String pv_st_nomeSexo;
	public static ArrayList<Sexo> pb_ar_sc_sexos = new ArrayList<Sexo>();
	
	public int getPv_in_IDSexo() {
		return pv_in_IDSexo;
	}
	public void setPv_in_IDSexo(int pv_in_IDSexo) {
		this.pv_in_IDSexo = pv_in_IDSexo;
	}
	public String getPv_st_nomeSexo() {
		return pv_st_nomeSexo;
	}
	public void setPv_st_nomeSexo(String pv_st_nomeSexo) {
		this.pv_st_nomeSexo = pv_st_nomeSexo;
	}
	
	@Override
	public String toString() {
	   return this.getPv_st_nomeSexo();
	}
	

}
