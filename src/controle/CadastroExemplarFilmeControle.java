package controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.ExemplarFilme;
import modelo.ExemplarFilmeDAO;
import modelo.ExemplarTipo;
import modelo.ExemplarTipoDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe controladora da tela Cadastrar Filme
 * @author Vinícius Velasco
 *
 */
public class CadastroExemplarFilmeControle {
	
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
    private Button btnCadastrar;

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
    private Button btnLimpar;
    

    @FXML
    private Button btnTraduzir;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblTitulo.setText(bundle.getString("Título"));
    	lblQuantidade.setText(bundle.getString("Quantidade"));
    	lblIdioma.setText(bundle.getString("Idioma"));
    	lblDuracao.setText(bundle.getString("Duração"));
    	lblDiretor.setText(bundle.getString("Diretor"));
    	lblPaisOrigem.setText(bundle.getString("PaisOrigem"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
    	btnLimpar.setText(bundle.getString("Limpar"));
    	comboBoxSecao.setPromptText(bundle.getString("SeçãoExemplar"));
    	comboBoxTipoExemplar.setPromptText(bundle.getString("TipoExemplar"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que cadastra o filme.
     * @param event
     */
    @FXML
    void btnCadastrarClick(ActionEvent event) {
    	if(comboBoxSecao.getValue() == null || comboBoxTipoExemplar.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    		Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    		
    		
    	} else if(textTitulo.getText().isEmpty() || textQuantidade.getText().isEmpty() || textIdioma.getText().isEmpty() || textDiretor.getText().isEmpty() ||  textDuracao.getText().isEmpty() || textPaisOrigem.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
			Mensagem.vd_sc_CamposVazios();
    		}
			
		} else if (textTitulo.getLength() > 50 || textQuantidade.getLength() > 11 || textIdioma.getLength() > 35  || textDiretor.getLength() > 40 || textDuracao.getLength() > 15 || textPaisOrigem.getLength() > 45) {
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
				
				DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date pr_dt_date = new Date();  
				String pr_st_dataAtual = pr_df_dateFormat.format(pr_dt_date);
				
				ExemplarFilmeDAO filmeDAO = new ExemplarFilmeDAO();
				ExemplarFilme filme = new ExemplarFilme();
				
				//Iniciando Cadastro do Exemplar
				filme.setPv_in_IdTipoExemplar(comboBoxTipoExemplar.getValue().getPv_in_IdTipo());
				filme.setPv_in_Quantidade(pr_in_Quantidade);
				filme.setPv_st_DataCadastro(pr_st_dataAtual);
				filme.setPv_st_Idioma(pr_st_Idioma);
				filme.setPv_st_Titulo(pr_st_Titulo);
				
				filmeDAO.pb_vd_CadastrarExemplarBase(filme);
				filmeDAO.pb_vd_buscaIdExemplarBase(filme);
				
				//Finalização do Cadastro do Filme
				filme.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());
				filme.setPv_st_Duracao(pr_st_Duracao);
				filme.setPv_st_NomeDiretor(pr_st_Diretor);
				filme.setPv_st_pais_origem(pr_st_PaisOrigem);
				filmeDAO.pb_vd_CadastrarFilme(filme);
				anchorPane.setVisible(false);
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_MovieRegisterSucess();
	    		} else {
				Mensagem.vd_sc_CadastroFilmeSucesso();
	    		}
				
			} catch (NumberFormatException ne){
				if(pc_bo_VerificadorTraducao){
					Mensagens_EN_US.vd_sc_OnlyNumbersMovies();
				} else {
				Mensagem.vd_sc_ApenasNumerosFilme();
				}
			}
		
		}

    }

    /**
     * Método que limpa a tela.
     * @param event
     */
    @FXML
    void btnLimparClick(ActionEvent event) {
    	textTitulo.clear();
    	textQuantidade.clear();
    	textIdioma.clear();
    	textDiretor.clear();
    	textDuracao.clear();
    	textPaisOrigem.clear();
    	
    }
    
    /**
     * Método que inicializa a tela com as combo boxes carregas
     */
    @FXML
    public void initialize() {
    	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	secaoDAO.pb_vd_BuscaSecoes(Secao.pb_ar_sc_secoes);
    
    	for(Secao se : Secao.pb_ar_sc_secoes){
    		comboBoxSecao.getItems().add(se);
    	}
    	
    	ExemplarTipoDAO exemplarTipoDAO = new ExemplarTipoDAO();
    	exemplarTipoDAO.pb_vd_BuscaTipos(ExemplarTipo.pb_ar_sc_secoes);
    	
    	for(ExemplarTipo et : ExemplarTipo.pb_ar_sc_secoes){
    		comboBoxTipoExemplar.getItems().add(et); //Sobrescrita do método ToString() transformando a referência ao nome do objeto na sua descrição.
    	}
    	
    }

}
