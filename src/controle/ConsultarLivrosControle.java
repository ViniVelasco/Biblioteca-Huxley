package controle;

import java.net.URL;
import java.util.ArrayList;
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
import modelo.Exemplar;
import modelo.ExemplarLivro;
import modelo.ExemplarLivroDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * CLasse controladora de Consultar Livros
 * @author Vinícius Velasco
 *
 */
public class ConsultarLivrosControle implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TableView<ExemplarLivro> tableConsultarLivros;

	@FXML
	private TableColumn<ExemplarLivro, String> columnISBN;

	@FXML
	private TableColumn<ExemplarLivro, String> columnTitulo;

	@FXML
	private TableColumn<ExemplarLivro, Integer> columnQuantidade;

	@FXML
	private TableColumn<ExemplarLivro, String> columnIdioma;

	@FXML
	private TableColumn<ExemplarLivro, String> columnAutor;

	@FXML
	private TableColumn<ExemplarLivro, String> columnEditora;

	@FXML
	private TableColumn<ExemplarLivro, Integer> columnEmprestimo;

	@FXML
	private TableColumn<ExemplarLivro, Integer> columnIDExemplar;

	@FXML
	private Label lblFIltragem;

	@FXML
	private Button btnPesquisar;

	@FXML
	private ComboBox<Secao> comboBoxSecao;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnDeletar;
	
	@FXML
	private Button btnTraduzir;
	
    boolean pc_bo_VerificadorTraducao = false;

	public static ObservableList<ExemplarLivro> livroSelecionado;
	
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
    	lblFIltragem.setText(bundle.getString("SelecioneSecao"));
    	columnTitulo.setText(bundle.getString("Título"));
    	columnQuantidade.setText(bundle.getString("Quantidade"));
    	columnIdioma.setText(bundle.getString("Idioma"));
    	columnAutor.setText(bundle.getString("Autor"));
    	columnEditora.setText(bundle.getString("Editora"));
    	columnEmprestimo.setText(bundle.getString("QuantidadeEmEmprestimo"));
    	
    	pc_bo_VerificadorTraducao = true;
    	

    }

	/**
	 * Método que chama a tela de alterar livros
	 * @param event
	 */
	@FXML
	void btnAlterarClick(ActionEvent event) {

		if (tableConsultarLivros.getSelectionModel().getSelectedItems().isEmpty()) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
		} else {
			livroSelecionado = tableConsultarLivros.getSelectionModel().getSelectedItems();

			Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarExemplarLivro.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarLivroControle.pb_sg_sc_AlterarLivro = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));
				palco.setTitle("Alterar Livros");
				palco.showAndWait(); //Um método que congela a execução deste evento, ele é retornado para atualizar a tabela assim que a outra tela é fechada
				
				pr_vd_AtualizaTabela();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que deleta um livro
	 * @param event
	 */
	@FXML
	void btnDeletarClick(ActionEvent event) {
		if (tableConsultarLivros.getSelectionModel().getSelectedItems().isEmpty()) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
		} else {
			livroSelecionado = tableConsultarLivros.getSelectionModel().getSelectedItems();

			ExemplarLivroDAO dao = new ExemplarLivroDAO();
			ExemplarLivro livro = new ExemplarLivro();
			livro.setPv_in_IdExemplar(livroSelecionado.get(0).getPv_in_IdExemplar());

			if (Mensagem.pb_bo_sc_AvisoApagandoDado()) {

				if (dao.pb_bo_VerificaEmprestimosLivro(livro)) {
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_BookAssociedWithLoans();
		    		} else {
					Mensagem.vd_sc_LivroAssociadoEmprestimos();
		    		}

				} else if (dao.pb_bo_VerificaReservas(livro)) {
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_BookAssociedWithReserve();
		    		} else {
					Mensagem.vd_sc_LivroAssociadoReserva();
		    		}

				} else {

	
					dao.pb_vd_ApagarLivro(livro); 
					dao.pb_vd_ApagarExemplarBase(livro); //O base é deletado por último, pois ele é referência para o exemplar_livro

					pr_vd_Limpar();
					pr_vd_configuraColunas();
					pr_vd_AtualizaTabela();
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_BookAssociedWithReserve();
		    		} else {
					Mensagem.vd_sc_LivroApagarSucesso();
		    		}

				}

			}

		}

	}

	/**
	 * Método que pesquisa um livro por filtragem.
	 * @param event
	 */
	@FXML
	void btnPesquisarClick(ActionEvent event) {

		if (comboBoxSecao.getValue() == null) {
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
	 * Método que inicializa a tela e carrega a tabela
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
	 * Método que configura a coluna
	 */
	void pr_vd_configuraColunas() {

		columnISBN.setCellValueFactory(new PropertyValueFactory<>("pv_st_ISBN"));
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("pv_st_Titulo"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("pv_in_Quantidade"));
		columnIdioma.setCellValueFactory(new PropertyValueFactory<>("pv_st_Idioma"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("pv_st_Autor"));
		columnEditora.setCellValueFactory(new PropertyValueFactory<>("pv_st_Editora"));
		columnEmprestimo.setCellValueFactory(new PropertyValueFactory<>("pv_in_QuantidadeEmEmprestimo"));
		columnIDExemplar.setCellValueFactory(new PropertyValueFactory<>("pv_in_IdExemplar"));

	}

	/**
	 * Método que limpa a tabela
	 */
	void pr_vd_Limpar() {
		tableConsultarLivros.getSelectionModel().select(null);
	}

	/**
	 * Método que atualiza a tabela
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabela() {

		ExemplarLivroDAO exemplarLivroDAO = new ExemplarLivroDAO();
		tableConsultarLivros.getItems().setAll(exemplarLivroDAO.pb_vd_buscarLivros());

		pr_vd_Limpar();

	}

	/**
	 * Método que atualiza a tabela por filtragem
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabelaFiltrada() {

		ExemplarLivro exemplar = new ExemplarLivro();
		exemplar.setPv_in_IDSecao(comboBoxSecao.getValue().getPv_in_IdSecao());

		ExemplarLivroDAO exemplarLivroDAO = new ExemplarLivroDAO();
		tableConsultarLivros.getItems().setAll(exemplarLivroDAO.pb_vd_ConsultaLivrosFiltrados(exemplar));

		pr_vd_Limpar();

	}

}
