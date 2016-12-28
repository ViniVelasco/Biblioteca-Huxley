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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Funcionario;
import modelo.FuncionarioDAO;
import modelo.Secao;
import modelo.SecaoDAO;
/**
 * Classe controladora da tela Consultar Seção
 * @author Vinícius Velasco
 *
 */
public class ConsultarSecaoControle implements Initializable{

    @FXML
    private TableView<Secao> tableConsultaSecao;

    @FXML
    private TableColumn<Secao, Integer> columnIDSecao;

    @FXML
    private TableColumn<Secao, String> columnNomeSecao;

    @FXML
    private ComboBox<String> comboBoxTipoPesquisa;

    @FXML
    private TextField textPesquisa;

    @FXML
    private Label lblPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnTraduzir;

    public static ObservableList<Secao> secaoSelecionada;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	comboBoxTipoPesquisa.getItems().clear();
    	comboBoxTipoPesquisa.setPromptText(bundle.getString("SelecionePesquisa"));
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	comboBoxTipoPesquisa.getItems().add(bundle.getString("PesquisarNome"));
    	columnNomeSecao.setText(bundle.getString("NomeSecao"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que chama a tela de alterar seção
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	if(tableConsultaSecao.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    		
    	} else {
    		
    		secaoSelecionada = tableConsultaSecao.getSelectionModel().getSelectedItems();
    		
    		Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarSecao.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarSecaoControle.pb_sg_sc_AlterarSecao = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));
				palco.setTitle("Alterar Usuários");
				palco.showAndWait();

				pr_vd_AtualizaTabela();

			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
}



    /**
     * Método que deleta uma seção
     * @param event
     */
    @FXML
    void btnDeletarClick(ActionEvent event) {
    	
    	if(tableConsultaSecao.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    		
    	} else {
    		
    		secaoSelecionada = tableConsultaSecao.getSelectionModel().getSelectedItems();
    		Secao secao = new Secao();
    		SecaoDAO secaoDAO = new SecaoDAO();
    		secao.setPv_in_IdSecao(secaoSelecionada.get(0).getPv_in_IdSecao());
    		
    		if(Mensagem.pb_bo_sc_AvisoApagandoDado()){
    			if(secaoDAO.pb_vd_BuscaAssociacaoFilme(secao)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_SectionAssociedMovie();
    	    		} else {
    				Mensagem.vd_sc_SecaoAssociadoFilme();
    	    		}
    			} else if (secaoDAO.pb_vd_BuscaAssociacaoLivro(secao)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_SectionAssociedBook();
    	    		} else {
    				Mensagem.vd_sc_SecaoAssociadoLivro();
    	    		}
    			} else {
    			
    			secaoDAO.pb_vd_DeletarSecao(secao);
    			
    			pr_vd_ConfiguraColunas();
				pr_vd_AtualizaTabela();
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_DeleteSectionSucess();
	    		} else {
				Mensagem.vd_sc_ApagarSecaoSucesso();
	    		}
    			}
    		}
    		
    	}

    }

    /**
     * Método que pesquisa por filtragem
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    	} else {
    		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por nome ou parte do nome" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNome")){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    				
    				pr_vd_AtualizaTabelaPesquisaPorNome(textPesquisa.getText());

    		}
    			
    	}
    	}
}

    /**
     * Método que controla o tipo de pesquisa
     * @param event
     */
    @FXML
    void comboBoxTIpoPesquisaSelecionado(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == "Pesquisar por nome ou parte do nome" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNome")){
    		
    		if(pc_bo_VerificadorTraducao){
    			
    			
    			lblPesquisa.setText(bundle.getString("PesquisarNome"));
        		lblPesquisa.setVisible(true);
        		
        		lblPesquisa.setTextFill(Color.web("#ff0000"));
    		} else {
    		
    		lblPesquisa.setText("Pesquisar por nome ou parte do nome");
    		lblPesquisa.setVisible(true);
    		
    		lblPesquisa.setTextFill(Color.web("#ff0000"));
    		}
    		
    	} 

    }
    
    /**
     * Método que configura as colunas
     */
    void pr_vd_ConfiguraColunas(){
    	columnNomeSecao.setCellValueFactory((new PropertyValueFactory<>("pv_st_Descricao")));
    	columnIDSecao.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdSecao")));
    }
    
    /**
     * Método que limpa a tabela
     */
    void pr_vd_Limpar() {
 		tableConsultaSecao.getSelectionModel().select(null);
 	}
    
    /**
     * Método que atualiza a tabela
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabela(){
       	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	tableConsultaSecao.getItems().setAll(secaoDAO.pb_vd_BuscaSecoes());
       	pr_vd_Limpar();
       	
   }
    
    /**
     * Método que atualiza a tabela por nome
     * @param nome
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorNome(String nome){
       	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	Secao secao = new Secao();
    	secao.setPv_st_Descricao(nome);
    	tableConsultaSecao.getItems().setAll(secaoDAO.pb_vd_BuscaSecoesPorNome(secao));
       	pr_vd_Limpar();
       	
   }

    /**
     * Método que inicializa a tabela.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		comboBoxTipoPesquisa.getItems().setAll("Pesquisar por nome ou parte do nome");
		pr_vd_ConfiguraColunas();
		pr_vd_AtualizaTabela();
		
	}

}
