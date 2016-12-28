package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Estado;
import modelo.EstadoDAO;

/**
 * Classe controladora da tela Cadastrar Estado
 * @author Vinícius Velasco
 *
 */
public class CadastrarEstadoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField textNome;

    @FXML
    private Label lblEstado;

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
    	lblEstado.setText(bundle.getString("NomeDoEstado"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que cadastra estado.
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
    			Mensagens_EN_US.vd_sc_LenghtStateName();
    		} else {
    		Mensagem.vd_sc_TamanhoNomeEstado();
    		}
    	} else {
    		
    		Estado estado = new Estado();
    		estado.setPv_st_NomeEstado(textNome.getText());
    		
    		EstadoDAO estadoDAO = new EstadoDAO();
    		estadoDAO.pb_vd_InserirEstado(estado);
    		
    		anchorPane.setVisible(false);
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_RegisterStateSucess();
    		} else {
    			Mensagem.vd_sc_CadastrarEstadoSucesso();
    		}
    		
    	
    	}

    }

}
