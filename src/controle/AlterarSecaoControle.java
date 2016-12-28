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
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe que controla a tela alterar seção.
 * @author Vinícius Velasco
 *
 */
public class AlterarSecaoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAlterarSecao;

    @FXML
    private TextField textNome;

    @FXML
    private Label lblSecao;

    @FXML
    private Button btnTraduzir;
    
    @FXML
    private Button btnFechar;

    public static Stage pb_sg_sc_AlterarSecao;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	pc_bo_VerificadorTraducao = true;
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	btnAlterarSecao.setText(bundle.getString("Alterar"));
    	lblSecao.setText(bundle.getString("NomeSecao"));
    	btnFechar.setText(bundle.getString("Fechar"));

    }
    
    /**
     * Método que fecha a tela
     * @param event
     */
    @FXML
    void btnFecharClick(ActionEvent event) {
    	AlterarSecaoControle.pb_sg_sc_AlterarSecao.close();

    }

    /**
     * Método alterar seção
     * @param event
     */
    @FXML
    void btnAlterarSecaoClick(ActionEvent event) {
    	
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
        	secao.setPv_in_IdSecao(ConsultarSecaoControle.secaoSelecionada.get(0).getPv_in_IdSecao());
        	SecaoDAO secaoDAO = new SecaoDAO();
        	secaoDAO.pb_vd_AlterarSecao(secao);
        	
        	if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_UpdateSectionWithSucess();
    		}  else {
    			Mensagem.vd_sc_AlterarSecaoSucesso();
    		}
        	
        	pb_sg_sc_AlterarSecao.close();
    	
    	}

    }

    /**
     * Método que inicializa a tela.
     */
    @FXML
    void initialize(){
    	
    	textNome.setText(ConsultarSecaoControle.secaoSelecionada.get(0).getPv_st_Descricao());
    	
    }
}
