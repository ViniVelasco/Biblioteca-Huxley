package controle;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelo.Cidade;
import modelo.CidadeDAO;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Estado;

/**
 * Classe controladora da tela Consultar Cidade
 * @author Vinícius Velasco
 *
 */
public class ConsultarCidadeControle implements Initializable {

    @FXML
    private TableView<Cidade> tableConsultaCidade;

    @FXML
    private TableColumn<Cidade, Integer> columnIDCidade;

    @FXML
    private TableColumn<Cidade, Integer> columnIDEstado;

    @FXML
    private TableColumn<Cidade, String>columnNomeCidade;

    @FXML
    private TableColumn<Cidade, String> columnNomeEstado;

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

    public static ObservableList<Cidade> cidadeselecionada;
    
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
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	columnNomeCidade.setText(bundle.getString("NomeDaCidade"));
    	columnNomeEstado.setText(bundle.getString("NomeDoEstado"));
    	comboBoxTipoPesquisa.setPromptText(bundle.getString("SelecionePesquisa"));
    	comboBoxTipoPesquisa.getItems().setAll(bundle.getString("PesquisaNome"), bundle.getString("PesquisaNomeEstado"));
    	
    	pc_bo_VerificadorTraducao = true;
    	
    	comboBoxTIpoPesquisaSelecionado(event);
    	
    	

    }
    
    /**
     * Método que inicia a tela de alterar cidade
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(tableConsultaCidade.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    	} else {
    		
    		cidadeselecionada = tableConsultaCidade.getSelectionModel().getSelectedItems();
    		
    		Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarCidade.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarCidadeControle.pb_sg_sc_AlterarCidade = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));

				palco.showAndWait();

				pr_vd_AtualizaTabela();
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	}

    }

    /**
     * Método que deleta uma cidade
     * @param event
     */
    @FXML
    void btnDeletarClick(ActionEvent event) {
    	if(tableConsultaCidade.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    	} else {
    		
    		
    		cidadeselecionada = tableConsultaCidade.getSelectionModel().getSelectedItems();
    		
    		Cidade cidade = new Cidade();
    		CidadeDAO cidadeDAO = new CidadeDAO();
    		cidade.setPv_in_IDCidade(cidadeselecionada.get(0).getPv_in_IDCidade());
    		
    		if (Mensagem.pb_bo_sc_AvisoApagandoDado()) {
    			
    			cidadeDAO.pb_vd_DeletarCidade(cidade);
    			if(pc_bo_VerificadorTraducao){
        			Mensagens_EN_US.vd_sc_DeleteCitySucess();
        		} else if(cidadeDAO.pb_vd_VerificaAssociacaoCidade(cidade)){
        			Mensagem.vd_sc_CidadeAssociada();
        		}
    			else {
    			Mensagem.vd_sc_CidadeApagarSucesso();
        		}
    			
    			pr_vd_Limpar();
				pr_vd_AtualizaTabela();
    			
    		}
    		
    	}
    }

    /**
     * Método que pesquisa a cidade
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    	} else {
    		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por nome ou parte do nome da Cidade" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisaNome") ){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    				
    					if(textPesquisa.getText().isEmpty()){
    						
    						pr_vd_ConfiguraColunas();
    						pr_vd_AtualizaTabela();
    						
    					} else {
    						pr_vd_ConfiguraColunas();
    						pr_vd_AtualizaTabelaPesquisaPorNomeCidade(textPesquisa.getText());
    					}
    					
    			}
    			
    		} else {
    			
    			if(textPesquisa.getText().isEmpty()){
					
					pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
					
				} else {
					pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabelaPesquisaPorNomeEstado(textPesquisa.getText());
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
    	
    	if(comboBoxTipoPesquisa.getValue() == "Pesquisar por nome ou parte do nome da Cidade" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisaNomeEstado")){
    		
    		if(pc_bo_VerificadorTraducao){
    			lblPesquisa.setText(bundle.getString("PesquisaNome"));
    			
        		lblPesquisa.setVisible(true);
        		
        		lblPesquisa.setTextFill(Color.web("#ff0000"));
    			
    		} else {
    		
    		lblPesquisa.setText("Pesquisar por nome ou parte do nome da Cidade");
    		lblPesquisa.setVisible(true);
    		
    		lblPesquisa.setTextFill(Color.web("#ff0000"));
    		
    		}
    		
    	} else if(comboBoxTipoPesquisa.getSelectionModel().isEmpty()){
    		
    	} else {
    		
    		if(pc_bo_VerificadorTraducao){
    			lblPesquisa.setText(bundle.getString("PesquisaNomeEstado"));
    			
        		lblPesquisa.setVisible(true);
        		
        		lblPesquisa.setTextFill(Color.web("#ff0000"));
    			
    		} else {
    		
    		lblPesquisa.setText("Pesquisar por nome ou parte do nome do Estado");
    		lblPesquisa.setVisible(true);
    		lblPesquisa.setTextFill(Color.web("#ff0000"));
    		}
    		
    	}

    }
    
    /**
     *  Método que configura colunas
     */
    void pr_vd_ConfiguraColunas() {
    	columnIDCidade.setCellValueFactory(new PropertyValueFactory<>("pv_in_IDCidade"));
    	columnIDEstado.setCellValueFactory(new PropertyValueFactory<>("pv_in_IDEstadoReferente"));
    	columnNomeCidade.setCellValueFactory(new PropertyValueFactory<>("pv_st_NomeCidade"));
    	columnNomeEstado.setCellValueFactory(new Callback<CellDataFeatures<Cidade, String>, ObservableValue<String>>() {
    		 @Override
	        public ObservableValue<String> call(CellDataFeatures<Cidade, String> c) {
	            return new SimpleStringProperty(c.getValue().estadoReferente.getPv_st_NomeEstado()); 
	        }
   	});
    }
    
    /**
     * Método que limpa a tela
     */
    void pr_vd_Limpar() {
		tableConsultaCidade.getSelectionModel().select(null);
	}
    
    /**
     * Método que atualiza a tabela
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabela(){
       	
        CidadeDAO cidadeDAO = new CidadeDAO();
       	tableConsultaCidade.getItems().setAll(cidadeDAO.pb_vd_BuscaTodasAsCidades());
       	pr_vd_Limpar();
       	
   }
    
    /**
     * Método que inicializa a tela e carrega as combo box.
     */
    @Override
   	public void initialize(URL url, ResourceBundle rb) {
       	
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por nome ou parte do nome da Cidade");
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por nome ou parte do nome do Estado");
    	
    	pr_vd_ConfiguraColunas();
       	pr_vd_AtualizaTabela();
       	
       	
       	
     }
    
    /**
     * Método que atualiza a tabela por pesquisa por nome cidade
     * @param nome
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorNomeCidade(String nome){
       	
    	CidadeDAO cidadeDAO = new CidadeDAO();
    	Cidade cidade = new Cidade();
    	cidade.setPv_st_NomeCidade(nome);
       	tableConsultaCidade.getItems().setAll(cidadeDAO.pb_vd_BuscaTodasAsCidadesPorNome(cidade));
       	pr_vd_Limpar();
       	

       }
    
    /**
     * Método que atualiza a tabela por pesquisa por nome de Estado
     * @param nome
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorNomeEstado(String nome){
       	
    	CidadeDAO cidadeDAO = new CidadeDAO();
    	Cidade cidade = new Cidade();
    	Estado estado = new Estado();
    	estado.setPv_st_NomeEstado(nome);
    	cidade.estadoReferente = estado;
       	tableConsultaCidade.getItems().setAll(cidadeDAO.pb_vd_BuscaTodasAsCidadesPorNomeEstado(cidade));
       	pr_vd_Limpar();

   }
    

}
