package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import modelo.Reserva;
import modelo.ReservaDAO;

/**
 * Classe controladora da tela cancelar reserva
 * @author Vinícius Velasco
 *
 */
public class CancelarReservaControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Reserva> comboBoxReservas;

    @FXML
    private Button btnCancelar;
    
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
    	comboBoxReservas.setPromptText(bundle.getString("SelecionarReserva"));
    	btnCancelar.setText(bundle.getString("Cancelar"));
    	pc_bo_VerificadorTraducao = true;

    }


    /**
     * Método que cancela a reserva.
     * @param event
     */
    @FXML
    void btnCancelarClick(ActionEvent event) {
    	
    	int pr_in_IDReserva = comboBoxReservas.getValue().getPv_in_IDReserva();
    	Reserva reserva = new Reserva();
    	reserva.setPv_in_IDReserva(pr_in_IDReserva);
    	ReservaDAO reservaDAO = new ReservaDAO();
    	reservaDAO.pb_vd_CancelarReserva(reserva);
    	if(pc_bo_VerificadorTraducao){
    		Mensagens_EN_US.vd_sc_CancelReservesSucess();
    	} else {
    	Mensagem.vd_sc_CancelarReservarSucesso();
    	}
    	anchorPane.setVisible(false);

    }
    
    /**
     * Método que inicializa a tela e carrega a combo box
     */
    @FXML
    void initialize(){
    	ReservaDAO reservaDAO = new ReservaDAO();
    	comboBoxReservas.getItems().setAll(reservaDAO.pb_ar_BuscarReservasDoUsuario());
    	
    }

}
