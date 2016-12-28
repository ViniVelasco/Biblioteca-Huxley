package modelo;

import java.util.ArrayList;

/**
 * Classe bean de tipo de funcionário, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class FuncionarioTipo {
	
	private int pv_in_IDTipoFuncionario;
	private String pv_st_Cargo;
	public static ArrayList<FuncionarioTipo> pb_ar_sc_tipos = new ArrayList<FuncionarioTipo>();
	
	public int getPv_in_IDTipoFuncionario() {
		return pv_in_IDTipoFuncionario;
	}
	public void setPv_in_IDTipoFuncionario(int pv_in_IDTipoFuncionario) {
		this.pv_in_IDTipoFuncionario = pv_in_IDTipoFuncionario;
	}
	public String getPv_st_Cargo() {
		return pv_st_Cargo;
	}
	public void setPv_st_Cargo(String pv_st_Cargo) {
		this.pv_st_Cargo = pv_st_Cargo;
	}
	
	@Override
	public String toString(){
		return this.pv_st_Cargo;
		
	}

}
