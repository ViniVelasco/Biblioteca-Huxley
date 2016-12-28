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
import javafx.stage.Stage;
import modelo.ExemplarLivro;
import modelo.ExemplarLivroDAO;
import modelo.ExemplarTipo;
import modelo.ExemplarTipoDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe que controla a tela alterar livro.
 * @author Vinícius Velasco
 *
 */
public class AlterarLivroControle {
	
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
    private AnchorPane anchorPane;

    @FXML
    private Button Alterar;

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
    private ComboBox<Secao> comboBoxSecao;

    @FXML
    private ComboBox<ExemplarTipo> comboBoxTipoExemplar;
    
    @FXML
    private Button btnFechar;
    
    @FXML
    private Button btnTraduzir;
    
    public static Stage pb_sg_sc_AlterarLivro;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela.
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	pc_bo_VerificadorTraducao = true;
    	Alterar.setText(bundle.getString("Alterar"));
    	btnFechar.setText(bundle.getString("Fechar"));
    	lblTitulo.setText(bundle.getString("Título"));
    	lblQuantidade.setText(bundle.getString("Quantidade"));
    	lblIdioma.setText(bundle.getString("Idioma"));
    	lblEditora.setText(bundle.getString("Editora"));
    	lblAutor.setText(bundle.getString("Autor"));
    	comboBoxSecao.setPromptText(bundle.getString("SeçãoExemplar"));
    	comboBoxTipoExemplar.setPromptText(bundle.getString("TipoExemplar"));

    }


    /**
     * Método que faz a alteração do livro
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(textTitulo.getText().isEmpty() || textQuantidade.getText().isEmpty() || textIdioma.getText().isEmpty() || textISBN.getText().isEmpty() ||  textEditora.getText().isEmpty() || textAutor.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
			Mensagem.vd_sc_CamposVazios();
    		}
			
		} else if (textTitulo.getLength() > 50 || textQuantidade.getLength() > 11 || textIdioma.getLength() > 35 || textISBN.getLength() > 13 || textEditora.getLength() > 30 || textAutor.getLength() > 40) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LenghtCopyBook();
    		} else {
			Mensagem.vd_sc_TamanhoFieldExemplarLivro();
    		}
			
		} else {
    		
    		try {
    			
    			String pr_st_Titulo = textTitulo.getText();
				int pr_in_Quantidade = Integer.parseInt(textQuantidade.getText());
				String pr_st_Idioma = textIdioma.getText();
				Double pr_st_VerificadorDeISBN = Double.parseDouble(textISBN.getText()); //Verifica o ISBN
				String pr_st_ISBN = textISBN.getText();
				String pr_st_Autor = textAutor.getText();
				String pr_st_Editora = textEditora.getText();
				
				ExemplarLivroDAO livroDAO = new ExemplarLivroDAO();
				ExemplarLivro verificaLivro = new ExemplarLivro();
				verificaLivro.setPv_st_ISBN(pr_st_ISBN);
				
				boolean verificador = true;
				
				if(!ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_ISBN().equals(pr_st_ISBN)){
					
					if(livroDAO.pb_vd_buscaISBN(pr_st_ISBN)){
						if(pc_bo_VerificadorTraducao){
			    			Mensagens_EN_US.vd_sc_RegisteredISBN();
			    		} 
						Mensagem.vd_sc_ISBNCadastado();
						verificador = false;
						
					} 
					
				}
				
				if(verificador){
					
					ExemplarLivro livro = new ExemplarLivro();
					livro.setPv_in_IdTipoExemplar(comboBoxTipoExemplar.getValue().getPv_in_IdTipo());
					livro.setPv_in_Quantidade(pr_in_Quantidade);
					livro.setPv_st_Titulo(pr_st_Titulo);
					livro.setPv_st_Idioma(pr_st_Idioma);
					livro.setPv_in_IdExemplar(pr_in_IDExemplar);		
					
					livroDAO.pb_vd_AlterarExemplarBase(livro);
					
					livro.setPv_st_ISBN(pr_st_ISBN);
					livro.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());
					livro.setPv_st_Autor(pr_st_Autor);
					livro.setPv_st_Editora(pr_st_Editora);
					livroDAO.pb_vd_AlterarLivro(livro);
					
					if(pc_bo_VerificadorTraducao){
						
		    			Mensagens_EN_US.vd_sc_UpdateBookSucess();
		    			
		    		} else {
					
		    			Mensagem.vd_sc_AlterarLivroSucesso();
					
		    		}
					AlterarLivroControle.pb_sg_sc_AlterarLivro.close();
					
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
    
    protected int pr_in_IDExemplar;
    
    /**
     * Método que inicializa a tela com as combo box carregadas.
     */
    @FXML
    public void initialize() {
    	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	secaoDAO.pb_vd_BuscaSecoes(Secao.pb_ar_sc_secoes);
    	comboBoxSecao.getItems().addAll(Secao.pb_ar_sc_secoes);
    	
    	ExemplarTipoDAO exemplarTipoDAO = new ExemplarTipoDAO();
    	exemplarTipoDAO.pb_vd_BuscaTipos(ExemplarTipo.pb_ar_sc_secoes);
    	comboBoxTipoExemplar.getItems().addAll(ExemplarTipo.pb_ar_sc_secoes);
    	
    	textTitulo.setText(ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_Titulo());
    	textQuantidade.setText(Integer.toString(ConsultarLivrosControle.livroSelecionado.get(0).getPv_in_Quantidade()));
    	textIdioma.setText(ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_Idioma());
    	textISBN.setText(ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_ISBN());
    	textEditora.setText(ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_Editora());
    	textAutor.setText(ConsultarLivrosControle.livroSelecionado.get(0).getPv_st_Autor());
    	
    	pr_in_IDExemplar = ConsultarLivrosControle.livroSelecionado.get(0).getPv_in_IdExemplar();
    	
    	comboBoxSecao.setValue(ConsultarLivrosControle.livroSelecionado.get(0).secao);
    	comboBoxTipoExemplar.setValue(ConsultarLivrosControle.livroSelecionado.get(0).tipo);
    	
    	

    }
    
    /**
     * Método que fecha a tela
     * @param event
     */
    @FXML
    void btnFecharClick(ActionEvent event) {
    	
    	AlterarLivroControle.pb_sg_sc_AlterarLivro.close();
    	

    }

}
