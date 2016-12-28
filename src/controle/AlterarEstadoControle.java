package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Estado;
import modelo.EstadoDAO;

/**
 * Classe controladora da tela Alterar Estado.
 * @author Vinícius Velasco
 *
 */
public class AlterarEstadoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAlterar;

    @FXML
    private TextField textNome;

    @FXML
    private Label lblEstado;

    @FXML
    private Button btnSair;
    
    public static Stage pb_sg_sc_AlterarEstado;
    
    @FXML
    private Button btnTraduzir;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	pc_bo_VerificadorTraducao = true;
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnSair.setText(bundle.getString("Fechar"));
    	lblEstado.setText(bundle.getString("NomeDoEstado"));

    }

    /**
     * Método que faz a alteração do estado.
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(textNome.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
    			Mensagem.vd_sc_CamposVazios();
    		}
    	} else if(textNome.getLength() > 45){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LenghtStateName();;
    		} else {
    			Mensagem.vd_sc_TamanhoNomeEstado();
    		}
    	} else if(ValidacoesdeNegocio.ChecarNome(textNome.getText()) == false){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_InvalidNames();
    		} else {
    		Mensagem.vd_sc_NomeInvalidos();
    		}
    	}
    	else {
    		Estado estado = new Estado();
    		estado.setPv_st_NomeEstado(textNome.getText());
    		estado.setPv_in_IDEstado(ConsultarEstadoControle.estadoSelecionado.get(0).getPv_in_IDEstado());
    		
    		EstadoDAO estadoDAO = new EstadoDAO();
    		estadoDAO.pb_vd_AlterarEstado(estado);
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_StateUpdateSucess();;
    		}  else {
    		Mensagem.vd_sc_AlterarEstadoSucesso();
    		}
    		AlterarEstadoControle.pb_sg_sc_AlterarEstado.close();
    	}

    }

    /**
     * Método que fecha a tela.
     * @param event
     */
    @FXML
    void btnSairClick(ActionEvent event) {
    	
    	AlterarEstadoControle.pb_sg_sc_AlterarEstado.close();

    }
    
    /**
     * Método que inicializa a tela com a combo box carrega.
     */
    @FXML 
    void initialize() {
    	
    	textNome.setText(ConsultarEstadoControle.estadoSelecionado.get(0).getPv_st_NomeEstado());
    	
    }
}
