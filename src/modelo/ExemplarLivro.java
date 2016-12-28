package modelo;
/**
 * Classe bean de livro, contendo todos os seus atributos.
 * @author Vinícius Velasco
 *
 */
public class ExemplarLivro extends Exemplar{
	
	private String pv_st_ISBN;
	private int pv_in_IDSecao;
	private String pv_st_Editora;
	private String pv_st_Autor;
	
	public Secao secao;
	public ExemplarTipo tipo;
	

	public int getPv_in_IDSecao() {
		return pv_in_IDSecao;
	}
	public void setPv_in_IDSecao(int pv_in_IDSecao) {
		this.pv_in_IDSecao = pv_in_IDSecao;
	}
	public String getPv_st_Editora() {
		return pv_st_Editora;
	}
	public void setPv_st_Editora(String pv_st_Editora) {
		this.pv_st_Editora = pv_st_Editora;
	}
	public String getPv_st_Autor() {
		return pv_st_Autor;
	}
	public void setPv_st_Autor(String pv_st_Autor) {
		this.pv_st_Autor = pv_st_Autor;
	}
	public String getPv_st_ISBN() {
		return pv_st_ISBN;
	}
	public void setPv_st_ISBN(String pv_st_ISBN) {
		this.pv_st_ISBN = pv_st_ISBN;
	}
	
	

}
