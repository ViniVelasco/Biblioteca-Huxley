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
import modelo.Cidade;
import modelo.CidadeDAO;
import modelo.Estado;
import modelo.EstadoDAO;

/**
 * Classe controladora da tela Cadastrar Cidade
 * @author Vinícius Velasco
 *
 */
public class CadastrarCidadeControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField textNome;
    
    @FXML
    private ComboBox<Estado> comboBoxEstado;
    
    @FXML
    private Label lblNome;
    
    @FXML
    private Button btnTraduzir;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela.
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblNome.setText(bundle.getString("NomeDaCidade"));
    	comboBoxEstado.setPromptText(bundle.getString("EstadoDaCidade"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
        pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que cadastra cidade.
     * @param event
     */
    @FXML
    void btnCadastrarClick(ActionEvent event) {
    	
    	if(textNome.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
    		Mensagem.vd_sc_CamposVazios();
    		}
    	} else if(textNome.getLength() > 45){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LengthNameCity();
    		} else {
    			Mensagem.vd_sc_TamanhoNomeCidade();
    		}
    	} else if(ValidacoesdeNegocio.ChecarNome(textNome.getText()) == false){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_NameCitye();
    		} else {
    			Mensagem.vd_sc_NomeCidade();
    		}
    	} else if(comboBoxEstado.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    			Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    		
    	}
    	else {
    		//Iniciando cadastro de cidade
    		Cidade cidade = new Cidade();
    		cidade.setPv_st_NomeCidade(textNome.getText());
    		cidade.setPv_in_IDEstadoReferente(comboBoxEstado.getValue().getPv_in_IDEstado());
    		
    		CidadeDAO cidadeDAO = new CidadeDAO();
    		cidadeDAO.pb_vd_InserirCidade(cidade);
    		
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_RegisterCitySucess();
    		} else {
    			Mensagem.vd_sc_CadastrarCidadeSucesso();
    		}
    		
    		anchorPane.setVisible(false);
    	}
    	

    }
    
    /**
     * Método que carrega combo box de Estado.
     */
    @FXML
    public void initialize(){
    	
    	EstadoDAO estado = new EstadoDAO();
    	estado.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
    	
    	comboBoxEstado.getItems().addAll(Estado.pb_ar_sc_estados);
    	
    	
    }

}
