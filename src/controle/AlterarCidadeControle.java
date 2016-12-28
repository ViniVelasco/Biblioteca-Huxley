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
import javafx.stage.Stage;
import modelo.Cidade;
import modelo.CidadeDAO;
import modelo.Estado;
import modelo.EstadoDAO;

/**
 * Classe de controle da tela alterar cidade.
 * @author Vinícius Velasco
 *
 */
public class AlterarCidadeControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAlterar;

    @FXML
    private TextField textNome;

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private Button btnFechar;
    
    public static Stage pb_sg_sc_AlterarCidade;
    
    @FXML
    private Button btnTraduzir;
    
    @FXML
    private Label lblNome;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * Método que traduz a tela.
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	pc_bo_VerificadorTraducao = true;
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblNome.setText(bundle.getString("NomeDaCidade"));
    	comboBoxEstado.setPromptText(bundle.getString("EstadoDaCidade"));
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnFechar.setText(bundle.getString("Fechar"));

    }

    /**
     * Método que faz a alteração da cidade.
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
    		
    	}
    	else {
    		Cidade cidade = new Cidade();
    		cidade.setPv_st_NomeCidade(textNome.getText());
    		cidade.setPv_in_IDEstadoReferente(comboBoxEstado.getValue().getPv_in_IDEstado());
    		cidade.setPv_in_IDCidade(ConsultarCidadeControle.cidadeselecionada.get(0).getPv_in_IDCidade());
    		
    		CidadeDAO cidadeDAO = new CidadeDAO();
    		cidadeDAO.pb_vd_AlterarCidade(cidade);
    		
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_UpdateCitySucess();
    		} else {
    			Mensagem.vd_sc_AlterarCidadeSucesso();
    		}
    		
    		AlterarCidadeControle.pb_sg_sc_AlterarCidade.close();
    	}

    }

    /**
     * Método que fecha a tela.
     * @param event
     */
    @FXML
    void btnFecharClick(ActionEvent event) {
    	
    	AlterarCidadeControle.pb_sg_sc_AlterarCidade.close();

    }
    
    /**
     * Método que inicializa a tela com as combo box carregadas.
     */
    @FXML
    void initialize(){
    	
    	EstadoDAO estado = new EstadoDAO();
    	estado.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
    	
    	comboBoxEstado.getItems().addAll(Estado.pb_ar_sc_estados);
    	comboBoxEstado.setValue(ConsultarCidadeControle.cidadeselecionada.get(0).estadoReferente);
    	textNome.setText(ConsultarCidadeControle.cidadeselecionada.get(0).getPv_st_NomeCidade());
    	
    }

}
