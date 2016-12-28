package modelo;

/**
 * Classe bean de exemplar, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class Exemplar {
	private int pv_in_IdExemplar;
	private String pv_st_Titulo;
	private String pv_st_DataCadastro;
	private int pv_in_Quantidade;
	private int pv_in_QuantidadeEmEmprestimo;
	private String pv_st_Idioma;
	private int pv_in_IdTipoExemplar;
	private String pv_st_DescricaoTipoExemplar;
	
	
	public String getPv_st_DescricaoTipoExemplar() {
		return pv_st_DescricaoTipoExemplar;
	}
	public void setPv_st_DescricaoTipoExemplar(String pv_st_DescricaoTipoExemplar) {
		this.pv_st_DescricaoTipoExemplar = pv_st_DescricaoTipoExemplar;
	}
	public int getPv_in_IdExemplar() {
		return pv_in_IdExemplar;
	}
	public void setPv_in_IdExemplar(int pv_in_IdExemplar) {
		this.pv_in_IdExemplar = pv_in_IdExemplar;
	}
	public String getPv_st_Titulo() {
		return pv_st_Titulo;
	}
	public void setPv_st_Titulo(String pv_st_Titulo) {
		this.pv_st_Titulo = pv_st_Titulo;
	}
	
	public int getPv_in_Quantidade() {
		return pv_in_Quantidade;
	}
	public void setPv_in_Quantidade(int pv_in_Quantidade) {
		this.pv_in_Quantidade = pv_in_Quantidade;
	}
	public int getPv_in_QuantidadeEmEmprestimo() {
		return pv_in_QuantidadeEmEmprestimo;
	}
	public void setPv_in_QuantidadeEmEmprestimo(int pv_in_QuantidadeEmEmprestimo) {
		this.pv_in_QuantidadeEmEmprestimo = pv_in_QuantidadeEmEmprestimo;
	}
	public String getPv_st_Idioma() {
		return pv_st_Idioma;
	}
	public void setPv_st_Idioma(String pv_st_Idioma) {
		this.pv_st_Idioma = pv_st_Idioma;
	}
	public int getPv_in_IdTipoExemplar() {
		return pv_in_IdTipoExemplar;
	}
	public void setPv_in_IdTipoExemplar(int pv_in_IdTipoExemplar) {
		this.pv_in_IdTipoExemplar = pv_in_IdTipoExemplar;
	}
	public String getPv_st_DataCadastro() {
		return pv_st_DataCadastro;
	}
	public void setPv_st_DataCadastro(String pv_st_DataCadastro) {
		this.pv_st_DataCadastro = pv_st_DataCadastro;
	}
	
	@Override
	public String toString(){
		
		return("Nome: " + this.getPv_st_Titulo() +  " Categoria: " + this.getPv_st_DescricaoTipoExemplar());
		
	}

}
