package controle;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.ExemplarFilme;
import modelo.ExemplarFilmeDAO;
import modelo.ExemplarLivro;
import modelo.ExemplarLivroDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe controladora da tela Consultar Filme
 * @author Viniciusbras
 *
 */
public class ConsultarFilmesControle implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<ExemplarFilme> tableConsultarFilmes;

    @FXML
    private TableColumn<ExemplarFilme, Integer> columnIDFilme;

    @FXML
    private TableColumn<ExemplarFilme, String> columnTitulo;

    @FXML
    private TableColumn<ExemplarFilme, Integer> columnQuantidade;

    @FXML
    private TableColumn<ExemplarFilme, String> columnIdioma;

    @FXML
    private TableColumn<ExemplarFilme, String> columnNomeDiretor;

    @FXML
    private TableColumn<ExemplarFilme, String> columnDuracao;

    @FXML
    private TableColumn<ExemplarFilme, String> columnPaisOrigem;

    @FXML
    private TableColumn<ExemplarFilme, Integer> columnIDExemplar;
    
    @FXML
    private TableColumn<ExemplarLivro, Integer> columnEmEmprestimo;

    @FXML
    private ComboBox<Secao> comboBoxSecao;

    @FXML
    private Label lblFiltragem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnAlterar;
    
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnTraduzir;
    
    public static ObservableList<ExemplarFilme> filmeSelecionado;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	comboBoxSecao.setPromptText(bundle.getString("Secao"));
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	lblFiltragem.setText(bundle.getString("SelecioneSecao"));
    	columnTitulo.setText(bundle.getString("Título"));
    	columnQuantidade.setText(bundle.getString("Quantidade"));
    	columnIdioma.setText(bundle.getString("Idioma"));
    	columnNomeDiretor.setText(bundle.getString("NomeDiretor"));
    	columnDuracao.setText(bundle.getString("Duração"));
    	columnPaisOrigem.setText(bundle.getString("PaisOrigem"));
    	columnEmEmprestimo.setText(bundle.getString("QuantidadeEmEmprestimo"));

    }

    /**
     * Método que chama a tela de alterar filmes
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if (tableConsultarFilmes.getSelectionModel().getSelectedItems().isEmpty()) {
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
		} else {
			
			filmeSelecionado = tableConsultarFilmes.getSelectionModel().getSelectedItems();
			
			Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarExemplarFilme.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarFilmeControle.pb_sg_sc_AlterarFilme = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));
				palco.setTitle("Alterar Livros");
				palco.showAndWait();

				pr_vd_AtualizaTabela();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

    }
    
    /**
     * Método que deleta um filme
     * @param event
     */
    @FXML
    void btnDeletar(ActionEvent event) {
    	
    	if(tableConsultarFilmes.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    	} else {
    		
    		filmeSelecionado = tableConsultarFilmes.getSelectionModel().getSelectedItems();
    		
    		ExemplarFilme filme = new ExemplarFilme();
    		ExemplarFilmeDAO filmeDAO = new ExemplarFilmeDAO();
    		filme.setPv_in_IdExemplar(filmeSelecionado.get(0).getPv_in_IdExemplar());
    		
    		if (Mensagem.pb_bo_sc_AvisoApagandoDado()) {
    			
    			if(filmeDAO.pb_bo_VerificaEmprestimosFilme(filme)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_MovieAssociedWithLoan();
    	    		} else {
    	    			Mensagem.vd_sc_FilmeAssociadoEmprestimos();
    	    		}
    				
    			} else if(filmeDAO.pb_bo_VerificaReservas(filme)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_MovieAsssociedReserve();
    	    		} else {
    				Mensagem.vd_sc_FilmeAssociadoReserva();
    	    		}
    				
    			} else {
    				
    				filmeDAO.pb_vd_ApagarFilme(filme);
    				filmeDAO.pb_vd_ApagarExemplarBase(filme);
    				
    				pr_vd_Limpar();
					pr_vd_configuraColunas();
					pr_vd_AtualizaTabela();
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_MovieDeleteSucess();
		    		} else {
					Mensagem.vd_sc_FilmeApagarSucesso();
		    		}
    				
    			}
    		}
    	}

    }

    /**
     * Método que pesquisa pela filtragem
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	
    	if(comboBoxSecao.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_ConsultBooksFiltersEmpty();
    		} else {
    		Mensagem.vd_sc_ConsultaLivrosFiltragemVazia();
    		}
    	} else {
    		
    		pr_vd_configuraColunas();
    		pr_vd_AtualizaTabelaFiltrada();
    		
    	}

    }
    
    /**
     * Método que inicializa a tela e carrega a tabela.
     */
    @Override
   	public void initialize(URL url, ResourceBundle rb) {
       	
       	SecaoDAO secaoDAO = new SecaoDAO();
       	secaoDAO.pb_vd_BuscaSecoes(Secao.pb_ar_sc_secoes);
       	comboBoxSecao.getItems().addAll(Secao.pb_ar_sc_secoes);
       	
       	pr_vd_configuraColunas();
       	pr_vd_AtualizaTabela();
       	
       	
       	
     }
    
    /**
     * Método que configura as colunas
     */
    void pr_vd_configuraColunas() {
    	
    	columnTitulo.setCellValueFactory(new PropertyValueFactory<>("pv_st_Titulo"));
    	columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("pv_in_Quantidade"));
    	columnIdioma.setCellValueFactory(new PropertyValueFactory<>("pv_st_Idioma"));
    	columnIDFilme.setCellValueFactory(new PropertyValueFactory<>("pv_in_IDFilme"));
    	columnNomeDiretor.setCellValueFactory(new PropertyValueFactory<>("pv_st_NomeDiretor"));
    	columnDuracao.setCellValueFactory(new PropertyValueFactory<>("pv_st_Duracao"));
    	columnPaisOrigem.setCellValueFactory(new PropertyValueFactory<>("pv_st_pais_origem"));
    	columnIDExemplar.setCellValueFactory(new PropertyValueFactory<>("pv_in_IdExemplar"));
    	columnEmEmprestimo.setCellValueFactory(new PropertyValueFactory<>("pv_in_QuantidadeEmEmprestimo"));
    	
    }
    
    /**
     * Método que limpa a tabela
     */
    void pr_vd_Limpar(){
    	tableConsultarFilmes.getSelectionModel().select(null);
    }
    
    /**
     * Método que atualiza a tabela
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabela(){
       	
       	ExemplarFilmeDAO exemplarFilmeDAO = new ExemplarFilmeDAO();
       	tableConsultarFilmes.getItems().setAll(exemplarFilmeDAO.pb_vd_buscarFilmes());
       	
       	pr_vd_Limpar();
       	
       	
       	
   }
    
    /**
     * Métood que atualiza a tabela por filtragem
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaFiltrada(){
       	
       	ExemplarFilme exemplar = new ExemplarFilme();
       	exemplar.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());
       	
       	ExemplarFilmeDAO exemplarFilmeDAO = new ExemplarFilmeDAO();
       	tableConsultarFilmes.getItems().setAll(exemplarFilmeDAO.pb_vd_buscarFilmesFiltrado(exemplar));
       	
       	pr_vd_Limpar();

       }

}
