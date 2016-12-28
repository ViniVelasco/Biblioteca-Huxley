package controle;

import java.net.URL;
import java.util.ArrayList;
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
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Estado;
import modelo.EstadoDAO;
import modelo.FuncionarioDAO;

/**
 * Classe que controla a tela de Consultar Estado
 * @author Vinícius Velasco
 *
 */
public class ConsultarEstadoControle implements Initializable {

	@FXML
	private TableView<Estado> tableConsultaEstado;

	@FXML
	private TableColumn<Estado, Integer> columnIDEstado;

	@FXML
	private TableColumn<Estado, String> columnNomeEstado;

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
	
	public static ObservableList<Estado> estadoSelecionado;
	
	boolean pc_bo_VerificadorTraducao = false;
	
	/**
	 * Método que traduz a tela
	 * @param event
	 */
	@FXML
	void btnTraduzirClick(ActionEvent event) {
		
		comboBoxTipoPesquisa.getItems().clear();
		Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	comboBoxTipoPesquisa.setPromptText(bundle.getString("SelecionePesquisa"));
    	columnNomeEstado.setText(bundle.getString("NomeDoEstado"));
    	pc_bo_VerificadorTraducao = true;
    	
    	comboBoxTipoPesquisa.getItems().setAll(bundle.getString("PesquisarNomeEstado"));
    	
    	comboBoxTIpoPesquisaSelecionado(event);

	}


	/**
	 * Método que chama a tela de alteração de Estado.
	 * @param event
	 */
	@FXML
	void btnAlterarClick(ActionEvent event) {
		
		if(tableConsultaEstado.getSelectionModel().getSelectedItems().isEmpty()){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    	} else {
    		
    		estadoSelecionado = tableConsultaEstado.getSelectionModel().getSelectedItems();
    		

    		Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarEstado.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarEstadoControle.pb_sg_sc_AlterarEstado = palco;
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
	 * Método que deleta o Estado.
	 * @param event
	 */
	@FXML
	void btnDeletarClick(ActionEvent event) {
		
		if (tableConsultaEstado.getSelectionModel().getSelectedItems().isEmpty()) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
			
		} else {
			
			estadoSelecionado = tableConsultaEstado.getSelectionModel().getSelectedItems();
			
			Estado estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();
			estado.setPv_in_IDEstado(ConsultarEstadoControle.estadoSelecionado.get(0).getPv_in_IDEstado());
			
			if (Mensagem.pb_bo_sc_AvisoApagandoDado()) {
				if(estadoDAO.pb_vd_VerificaAssociacaoEstado(estado)){
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_StateAssociedWithCity();
		    		} else {
		    			Mensagem.vd_sc_EstadoAssociadoComCidade();
		    		}
					
				} else {
					
					estadoDAO.pb_vd_DeletarEstado(estado);
					
					pr_vd_Limpar();
					pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_DeleteStateSucess();
		    		} else {
					Mensagem.vd_sc_ApagarEstadoSucesso();
		    		}
					
				}
			}
			
		}

	}

	@FXML
	void btnPesquisarClick(ActionEvent event) {
		
		if(comboBoxTipoPesquisa.getValue() == null){
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    	} else {
    		if(textPesquisa.getText().isEmpty()){
				
				pr_vd_ConfiguraColunas();
				pr_vd_AtualizaTabela();
				
			} else {
				pr_vd_ConfiguraColunas();
				pr_vd_AtualizaTabelaFiltrada(textPesquisa.getText());
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
		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por nome ou parte do nome do estado" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNomeEstado")){
		
		if(pc_bo_VerificadorTraducao){
			
			lblPesquisa.setText(bundle.getString("PesquisarNomeEstado"));
			lblPesquisa.setVisible(true);
    		lblPesquisa.setTextFill(Color.web("#ff0000"));
		}else if(comboBoxTipoPesquisa.getSelectionModel().isEmpty()){
			

    		
    	} else {
			lblPesquisa.setText("Pesquisar por nome ou parte do nome do Estado");
			lblPesquisa.setVisible(true);
			lblPesquisa.setTextFill(Color.web("#ff0000"));
		}
		}

	}

	/**
	 * Método que inicializa a tela e carrega a tabela
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pr_vd_ConfiguraColunas();
		pr_vd_AtualizaTabela();

	}

	/**
	 * Método que limpa a tabela
	 */
	void pr_vd_Limpar() {
		tableConsultaEstado.getSelectionModel().select(null);
	}

	/**
	 * Método que configura a coluna
	 */
	void pr_vd_ConfiguraColunas() {
		columnIDEstado.setCellValueFactory(new PropertyValueFactory<>("pv_in_IDEstado"));
		columnNomeEstado.setCellValueFactory(new PropertyValueFactory<>("pv_st_NomeEstado"));
		
		comboBoxTipoPesquisa.getItems().setAll("Pesquisar por nome ou parte do nome do estado");
	}

	/**
	 * Método que atualiza a tabela
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabela() {

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
		tableConsultaEstado.getItems().setAll(Estado.pb_ar_sc_estados);

		pr_vd_Limpar();

	}
	
	/**
	 * Método que atualiza a tabela pro nome
	 * @param nome
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabelaFiltrada(String nome) {
		
		Estado estado = new Estado();
		estado.setPv_st_NomeEstado(nome);
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.pb_vd_BuscaEstadoPorNome(Estado.pb_ar_sc_estados, estado);
		tableConsultaEstado.getItems().setAll(Estado.pb_ar_sc_estados);

		pr_vd_Limpar();

	}

}
