package modelo;
/**
 * Classe bean de filme.
 * @author Vinícius Velasco
 *
 */
public class ExemplarFilme extends Exemplar{
	
	private int pv_in_IDSecao;
	private int pv_in_IDFilme;
	private String pv_st_NomeDiretor;
	private String pv_st_Duracao;
	private String pv_st_pais_origem;
	
	public Secao secao;
	public ExemplarTipo tipo;
	
	public int getPv_in_IDSecao() {
		return pv_in_IDSecao;
	}
	public void setPv_in_IDSecao(int pv_in_IDSecao) {
		this.pv_in_IDSecao = pv_in_IDSecao;
	}
	
	public String getPv_st_NomeDiretor() {
		return pv_st_NomeDiretor;
	}
	public void setPv_st_NomeDiretor(String pv_st_NomeDiretor) {
		this.pv_st_NomeDiretor = pv_st_NomeDiretor;
	}
	public String getPv_st_Duracao() {
		return pv_st_Duracao;
	}
	public void setPv_st_Duracao(String pv_st_Duracao) {
		this.pv_st_Duracao = pv_st_Duracao;
	}
	public String getPv_st_pais_origem() {
		return pv_st_pais_origem;
	}
	public void setPv_st_pais_origem(String pv_st_pais_origem) {
		this.pv_st_pais_origem = pv_st_pais_origem;
	}
	public int getPv_in_IDFilme() {
		return pv_in_IDFilme;
	}
	public void setPv_in_IDFilme(int pv_in_IDFilme) {
		this.pv_in_IDFilme = pv_in_IDFilme;
	}
	
	

}
