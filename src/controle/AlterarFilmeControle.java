package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.ExemplarFilme;
import modelo.ExemplarFilmeDAO;
import modelo.ExemplarLivroDAO;
import modelo.ExemplarTipo;
import modelo.ExemplarTipoDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe controladora da tela Alterar Filme
 * @author Vinícius Velasco
 *
 */
public class AlterarFilmeControle {
	
	@FXML
    private Label lblTitulo;
	
	@FXML
	private Label lblQuantidade;
	
    @FXML
    private Label lblIdioma;
    
    @FXML
    private Label lblDuracao;
    
    @FXML
    private Label lblPaisOrigem;
    
    @FXML
    private Label lblDiretor;	

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAlterar;

    @FXML
    private TextField textTitulo;

    @FXML
    private TextField textQuantidade;

    @FXML
    private TextField textIdioma;

    @FXML
    private TextField textDiretor;

    @FXML
    private TextField textDuracao;

    @FXML
    private TextField textPaisOrigem;

    @FXML
    private ComboBox<Secao> comboBoxSecao;

    @FXML
    private ComboBox<ExemplarTipo> comboBoxTipoExemplar;

    @FXML
    private Button btnFechar;
    
    @FXML
    private Button btnTraduzir;
    
    public static Stage pb_sg_sc_AlterarFilme;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela.
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	pc_bo_VerificadorTraducao = true;
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblTitulo.setText(bundle.getString("Título"));
    	lblQuantidade.setText(bundle.getString("Quantidade"));
    	lblIdioma.setText(bundle.getString("Idioma"));
    	lblDuracao.setText(bundle.getString("Duração"));
    	lblDiretor.setText(bundle.getString("Diretor"));
    	lblPaisOrigem.setText(bundle.getString("PaisOrigem"));
    	comboBoxSecao.setPromptText(bundle.getString("SeçãoExemplar"));
    	comboBoxTipoExemplar.setPromptText(bundle.getString("TipoExemplar"));
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnFechar.setText(bundle.getString("Fechar"));
    	comboBoxSecao.setPromptText(bundle.getString("SeçãoExemplar"));
    	comboBoxTipoExemplar.setPromptText(bundle.getString("TipoExemplar"));
    }

    /**
     * Método que altera o filme.
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(textTitulo.getText().isEmpty() || textQuantidade.getText().isEmpty() || textIdioma.getText().isEmpty() || textDiretor.getText().isEmpty() ||  textDuracao.getText().isEmpty() || textPaisOrigem.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
    		Mensagem.vd_sc_CamposVazios();
    		}
    		
    	}else if (textTitulo.getLength() > 50 || textQuantidade.getLength() > 11 || textIdioma.getLength() > 35  || textDiretor.getLength() > 40 || textDuracao.getLength() > 15 || textPaisOrigem.getLength() > 45) {
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LengthCopyMovie();
    		} else {
    		Mensagem.vd_sc_TamanhoFieldExemplarFilme();
    		}
			
		} else {
			try {
			String pr_st_Titulo = textTitulo.getText();
			int pr_in_Quantidade = Integer.parseInt(textQuantidade.getText());
			String pr_st_Idioma = textIdioma.getText();
			String pr_st_Diretor = textDiretor.getText();
			String pr_st_Duracao = textDuracao.getText();
			String pr_st_PaisOrigem = textPaisOrigem.getText();
			int pr_in_IDExemplar = ConsultarFilmesControle.filmeSelecionado.get(0).getPv_in_IdExemplar();
			
			ExemplarFilmeDAO filmeDAO = new ExemplarFilmeDAO();
			ExemplarFilme filme = new ExemplarFilme();
			
			
			filme.setPv_in_IdTipoExemplar(comboBoxTipoExemplar.getValue().getPv_in_IdTipo());
			filme.setPv_in_Quantidade(pr_in_Quantidade);
			filme.setPv_st_Idioma(pr_st_Idioma);
			filme.setPv_st_Titulo(pr_st_Titulo);
			filme.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());
			filme.setPv_st_Duracao(pr_st_Duracao);
			filme.setPv_st_NomeDiretor(pr_st_Diretor);
			filme.setPv_st_pais_origem(pr_st_PaisOrigem);
			filme.setPv_in_IdExemplar(pr_in_IDExemplar);
			
			filmeDAO.pb_vd_AlterarExemplarBase(filme);
			filmeDAO.pb_vd_AlterarFilme(filme);
			
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_UpdateMovieSucess();
    		} else {
			Mensagem.vd_sc_AlterarFilmeSucesso();
    		}
			AlterarFilmeControle.pb_sg_sc_AlterarFilme.close();
			
			} catch(NumberFormatException e1){
				if(pc_bo_VerificadorTraducao){
					Mensagens_EN_US.vd_sc_OnlyNumbersMovies();
				} else {
				Mensagem.vd_sc_ApenasNumerosFilme();
				}
			}
			
		}

    }

    /**
     * Método que fecha a tela.
     * @param event
     */
    @FXML
    void btnFecharClick(ActionEvent event) {
    	
    	pb_sg_sc_AlterarFilme.close();

    }
    
    /**
     * Método que inicializa a tela com as combo box carregadas.
     */
    @FXML
    public void initialize(){
    	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	secaoDAO.pb_vd_BuscaSecoes(Secao.pb_ar_sc_secoes);
    	comboBoxSecao.getItems().addAll(Secao.pb_ar_sc_secoes);
    	
    	ExemplarTipoDAO exemplarTipoDAO = new ExemplarTipoDAO();
    	exemplarTipoDAO.pb_vd_BuscaTipos(ExemplarTipo.pb_ar_sc_secoes);    	
    	comboBoxTipoExemplar.getItems().addAll(ExemplarTipo.pb_ar_sc_secoes);
    	
    	textTitulo.setText(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_st_Titulo());
    	textQuantidade.setText(Integer.toString(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_in_Quantidade()));
    	textIdioma.setText(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_st_Idioma());
    	textDiretor.setText(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_st_NomeDiretor());
    	textDuracao.setText(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_st_Duracao());
    	textPaisOrigem.setText(ConsultarFilmesControle.filmeSelecionado.get(0).getPv_st_pais_origem());
    	
    	comboBoxSecao.setValue(ConsultarFilmesControle.filmeSelecionado.get(0).secao);
    	comboBoxTipoExemplar.setValue(ConsultarFilmesControle.filmeSelecionado.get(0).tipo);
    }

}
