package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import modelo.Cliente;
import modelo.EmprestimoDAO;
import modelo.Exemplar;
import modelo.ExemplarDAO;
import modelo.Reserva;
import modelo.ReservaDAO;

/**
 * Classe que controla a tela de Reservar
 * @author Vinícius Velasco
 *
 */
public class ReservarControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Exemplar> comboBoxExemplares;

    @FXML
    private Button btnReservar;
    
    @FXML
    private Button btnTraduzir;
    
    boolean pc_bo_VerificadorTraducao = false;
    

    /**
     * Método que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	comboBoxExemplares.setPromptText(bundle.getString("SelecionarExemplares"));
    	btnReservar.setText(bundle.getString("Reservar"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que realiza a reserva
     * @param event
     */
    @FXML
    void btnReservarClick(ActionEvent event) {
    	
    	String pr_st_CPF = Cliente.getPv_st_sc_CPF_Logado();
    	String pr_st_Status = "Em Aberto";
    	int pr_in_IDExemplar = comboBoxExemplares.getValue().getPv_in_IdExemplar();
    	
    	Reserva reserva = new Reserva();
    	reserva.setPv_st_CPF(pr_st_CPF);
    	reserva.setPv_st_Status(pr_st_Status);
    	reserva.setPv_in_IDExemplar(pr_in_IDExemplar);
    	
    	EmprestimoDAO emDAO = new EmprestimoDAO();
    	Exemplar exemplar = new Exemplar();
    	exemplar.setPv_in_IdExemplar(pr_in_IDExemplar);
    	
    	if(emDAO.pb_vd_VerificaQuantidadeEmEmprestimo(exemplar)){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_OutOfCopys();
    		} else {
    		Mensagem.vd_sc_ExemplaresAcabaramReserva();
    		}
    		
    	} else {
    		ReservaDAO reservaDAO = new ReservaDAO();
    		reservaDAO.pb_ar_Reservar(reserva);
    		Mensagem.vd_sc_ReservarSucesso();
    		anchorPane.setVisible(false);
    	}
    	
   

    }
    
    /**
     * Método que inicializa a tela e carrega a combo box.
     */
    @FXML
    void initialize(){
    	ExemplarDAO exemplarDAO = new ExemplarDAO();
    	comboBoxExemplares.getItems().setAll(exemplarDAO.pb_vd_BuscarExemplares());
    }

}
