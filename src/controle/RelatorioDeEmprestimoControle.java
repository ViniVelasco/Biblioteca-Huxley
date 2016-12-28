package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import relatorios.RelatorioDeEmprestimos;
/**
 * Classe que conrola a tela de relat�rio de empr�stimos
 * @author Vin�cius Velasco
 *
 */
public class RelatorioDeEmprestimoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> comboBoxTipoRelatorio;

    @FXML
    private Button btnGerar;

    @FXML
    private Button btnTraduzir;
    
    boolean pc_bo_VerificadorTraducao = false;

    /**
     * M�todo que controla o tipo de relat�rio
     * @param event
     */
    @FXML
    void SelecionaTipoRelatorio(ActionEvent event) {

    }

    /**
     * M�todo que gera o relat�rio
     * @param event
     * @throws JRException
     */
    @FXML
    void btnGerarClick(ActionEvent event) throws JRException {
    	if(comboBoxTipoRelatorio.getSelectionModel().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    		Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    	} else {
    	RelatorioDeEmprestimos relatorios = new RelatorioDeEmprestimos();
    	if(comboBoxTipoRelatorio.getSelectionModel().getSelectedIndex() == 0){
    	relatorios.pb_vd_sc_GerarRelatorioDeEmprestimos();
    	} else if(comboBoxTipoRelatorio.getSelectionModel().getSelectedIndex() == 1){
    		relatorios.pb_vd_sc_GerarRelatorioDeEmprestimosEmAberto();
    	} else {
    		relatorios.pb_vd_sc_GerarRelatorioDeEmprestimosEmFinalizados();
    	}
    	}
    }

    /**
     * M�todo que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	btnGerar.setText(bundle.getString("Gerar"));
    	comboBoxTipoRelatorio.getItems().clear();
    	comboBoxTipoRelatorio.setPromptText(bundle.getString("SelecioneTipoRelatorio"));
    	comboBoxTipoRelatorio.getItems().addAll(bundle.getString("TodosEmprestimos"), bundle.getString("EmprestimosAbertos"), bundle.getString("EmprestimosFinalizados"));
    	
    	pc_bo_VerificadorTraducao = true;
    }
    
    /**
     * M�todo que inicializa a tela e carrega a combo box
     */
    @FXML
    void initialize(){
    	comboBoxTipoRelatorio.getItems().addAll("Todos os empr�stimos", "Empr�stimos em Aberto", "Empr�stimos Finalizados");
    }

}
