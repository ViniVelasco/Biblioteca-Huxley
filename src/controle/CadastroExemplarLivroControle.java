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
import modelo.ExemplarLivro;
import modelo.ExemplarLivroDAO;
import modelo.ExemplarTipo;
import modelo.ExemplarTipoDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe controladora da tela Cadastrar Livro.
 * @author Vinícius Velasco
 *
 */
public class CadastroExemplarLivroControle {
	
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCadastrar;
    @FXML
    private ComboBox<Secao> comboBoxSecao;

    @FXML
    private ComboBox<ExemplarTipo> comboBoxTipoExemplar;

    @FXML
    private TextField textTitulo;

    @FXML
    private TextField textQuantidade;

    @FXML
    private TextField textIdioma;

    @FXML
    private TextField textISBN;

    @FXML
    private TextField textEditora;

    @FXML
    private TextField textAutor;
    
    @FXML
    private Button btnTraduzir;
    
    @FXML
    private Label lblTitulo;
    
    @FXML
    private Label lblQuantidade;

    @FXML
    private Label lblIdioma;
    
    @FXML
    private Label lblEditora;
    
    @FXML
    private Label lblAutor;
    
    @FXML
    private Button btnLimpar;
    
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
    	lblEditora.setText(bundle.getString("Editora"));
    	lblAutor.setText(bundle.getString("Autor"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
    	btnLimpar.setText(bundle.getString("Limpar"));
    	comboBoxSecao.setPromptText(bundle.getString("SeçãoExemplar"));
    	comboBoxTipoExemplar.setPromptText(bundle.getString("TipoExemplar"));
    	
    	pc_bo_VerificadorTraducao = true;
    	
    	
    }
    
    /**
     * Método que cadastra o livro
     * @param event
     */
    @FXML
    void btnCadastrarClick(ActionEvent event) {
    	//System.out.println(comboBoxTipoExemplar.getValue().getPv_in_IdTipo());
    	if(comboBoxSecao.getValue() == null || comboBoxTipoExemplar.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    		Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    		
    		
    	} else if(textTitulo.getText().isEmpty() || textQuantidade.getText().isEmpty() || textIdioma.getText().isEmpty() || textISBN.getText().isEmpty() ||  textEditora.getText().isEmpty() || textAutor.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
			Mensagem.vd_sc_CamposVazios();
    		}
			
		} else if (textTitulo.getLength() > 50 || textQuantidade.getLength() > 11 || textIdioma.getLength() > 35 || textISBN.getLength() > 13 || textEditora.getLength() > 30 || textAutor.getLength() > 40) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LengthCopyMovie();
    		} else {
			Mensagem.vd_sc_TamanhoFieldExemplarLivro();
    		}
			
		}
    	else {
			
			try {
				
				String pr_st_Titulo = textTitulo.getText();
				int pr_in_Quantidade = Integer.parseInt(textQuantidade.getText());
				String pr_st_Idioma = textIdioma.getText();
				Double pr_st_VerificadorDeISBN = Double.parseDouble(textISBN.getText()); //Verifica o ISBN
				String pr_st_ISBN = textISBN.getText();
				String pr_st_Autor = textAutor.getText();
				String pr_st_Editora = textEditora.getText();
				ExemplarLivroDAO livroDAO = new ExemplarLivroDAO();
				
				DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date pr_dt_date = new Date();  
				String pr_st_dataAtual = pr_df_dateFormat.format(pr_dt_date);
				
				if(livroDAO.pb_vd_buscaISBN(pr_st_ISBN)){
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_RegisteredISBN();
		    		} else {
					Mensagem.vd_sc_ISBNCadastado();
		    		}
					
				} else {
					
				//Iniciando o Cadastro do Exemplar
				ExemplarLivro livro = new ExemplarLivro();
				livro.setPv_st_DataCadastro(pr_st_dataAtual);
				livro.setPv_in_IdTipoExemplar(comboBoxTipoExemplar.getValue().getPv_in_IdTipo());
				livro.setPv_in_Quantidade(pr_in_Quantidade);
				livro.setPv_st_Titulo(pr_st_Titulo);
				livro.setPv_st_Idioma(pr_st_Idioma);
				
				
				livroDAO.pb_vd_CadastrarExemplarBase(livro);
				livroDAO.pb_vd_buscaIdExemplarBase(livro); //Busca ID do exemplar à partir da Data ;)
				
				//Finalização do Cadastro do Livro
				livro.setPv_st_ISBN(pr_st_ISBN);
				livro.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());
				livro.setPv_st_Autor(pr_st_Autor);
				livro.setPv_st_Editora(pr_st_Editora);
				livroDAO.pb_vd_CadastrarLivro(livro);
				anchorPane.setVisible(false);
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_RegisterBookSucess();
	    		} else {
				Mensagem.vd_sc_CadastroLivroSucesso();
	    		}
				
				}
				
				
			} catch (NumberFormatException ne){
				if(pc_bo_VerificadorTraducao){
					Mensagens_EN_US.vd_sc_OnlyNumbersBooks();
				} else {
				Mensagem.vd_sc_ApenasNumerosLivro();
				}
			}
			
		
		}
    	
    	

    }

    /**
     * Método que limpa a tela
     * @param event
     */
    @FXML
    void btnLimparClick(ActionEvent event) {
    	textTitulo.clear();
    	textQuantidade.clear();
    	textIdioma.clear();
    	textISBN.clear();
    	textEditora.clear();
    	textAutor.clear();
    }
    
    /**
     * Método que inicializa a tela e carrega as combo boxes.
     */
    @FXML
    public void initialize() {
    	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	secaoDAO.pb_vd_BuscaSecoes(Secao.pb_ar_sc_secoes);
    	/**
    	for(Secao se : Secao.pb_ar_sc_secoes){
    		comboBoxSecao.getItems().add(se.getPv_st_Descricao());
    	}
    	**/
    	
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
