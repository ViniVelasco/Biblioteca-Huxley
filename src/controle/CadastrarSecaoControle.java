package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe controladora da tela Cadastrar Seção
 * @author Vinícius Velasco
 *
 */
public class CadastrarSecaoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField textNome;

    @FXML
    private Label lblSecao;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    @FXML
    private Button btnTraduzir;

    /**
     * Método que traduz a tela.
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblSecao.setText(bundle.getString("NomeSecao"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que cadastra a seção
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
    	} else if(textNome.getLength() > 24){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LengthSectionName();
    		} else {
    			Mensagem.vd_sc_TamanhoNomeSecao();
    		}
    	} else {
    	
    	Secao secao = new Secao();
    	secao.setPv_st_Descricao(textNome.getText());
    	
    	SecaoDAO secaoDAO = new SecaoDAO();
    	secaoDAO.pb_vd_InserirSecao(secao);
    	
    	anchorPane.setVisible(false);
    	
    	if(pc_bo_VerificadorTraducao){
			Mensagens_EN_US.vd_scRegisterSectionSucess();
		} else {
			Mensagem.vd_sc_CadastrarSecaoSucesso();
		}
    	
    	
    	}

    }

}
