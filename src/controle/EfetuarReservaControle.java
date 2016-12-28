package controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import modelo.Cliente;
import modelo.Emprestimo;
import modelo.EmprestimoDAO;
import modelo.Exemplar;
import modelo.ExemplarDAO;
import modelo.Funcionario;
import modelo.Reserva;
import modelo.ReservaDAO;

/**
 * Classe que controla a tela Efetuar Reserva
 * @author Vinícius Velasco
 *
 */
public class EfetuarReservaControle {

    @FXML
    private ComboBox<Reserva> comboBoxReservas;

    @FXML
    private Button btnEfetuarReserva;

    @FXML
    private DatePicker datePickerDataEmprestimo;

    @FXML
    private Label lblData;

    @FXML
    private DatePicker datePickerDataPrazo;

    @FXML
    private Label lblPrazo;
    
    @FXML
    private AnchorPane anchorPane;

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
    	lblPrazo.setText(bundle.getString("PrazoEmprestimo"));
    	lblData.setText(bundle.getString("DataEmprestimo"));
    	comboBoxReservas.setPromptText(bundle.getString("SelecionarReserva"));
    	btnEfetuarReserva.setText(bundle.getString("Efetuar"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que efetua a reserva
     * @param event
     */
    @FXML
    void btnEfetuarReservaClick(ActionEvent event) {
    	
    	if (datePickerDataEmprestimo.getValue() == null || datePickerDataPrazo == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_DatesNotSelected();
    		} else {
    		Mensagem.vd_sc_DatasNaoSelecionadas();
    		}
    	} else if(comboBoxReservas.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    		Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    	} else {
    		String pr_st_CPF = comboBoxReservas.getValue().getPv_st_CPF();
    		int pr_in_IDExemplar = comboBoxReservas.getValue().getPv_in_IDExemplar();
    		int pr_in_IDReserva = comboBoxReservas.getValue().getPv_in_IDReserva();
    		String pr_st_Status = comboBoxReservas.getValue().getPv_st_Status();
    		
    		String pr_st_dataEmprestimo = datePickerDataEmprestimo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		String pr_st_dataPrazo = datePickerDataPrazo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		
    		try {
    			
				Date pr_dt_comparaEmprestimo = pr_df_dateFormat.parse(pr_st_dataEmprestimo);
				Date pr_dt_comparaPrazo = pr_df_dateFormat.parse(pr_st_dataPrazo);
				
				ReservaDAO reservaDAO = new ReservaDAO();
				EmprestimoDAO emDAO = new EmprestimoDAO();
				Exemplar exemplar = new Exemplar();
				exemplar.setPv_in_IdExemplar(pr_in_IDExemplar);
				
				if(ValidacoesdeNegocio.comparaData(pr_dt_comparaEmprestimo, pr_dt_comparaPrazo) > 0){
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_DateLoanBig();
		    		} else {
	    			Mensagem.vd_sc_DataEmprestimoMaior();
		    		}
	    		} else if(ValidacoesdeNegocio.comparaData(pr_dt_comparaEmprestimo, pr_dt_comparaPrazo) == 0){
	    			if(pc_bo_VerificadorTraducao){
	        			Mensagens_EN_US.vd_sc_SameDates();
	        		} else {
	    			Mensagem.vd_sc_DatasIguais();
	        		}
	    		}  else if(ValidacoesdeNegocio.comparaData(pr_dt_comparaEmprestimo, pr_dt_comparaPrazo) > 0){
	    			if(pc_bo_VerificadorTraducao){
	        			Mensagens_EN_US.vd_sc_DeadlineSmallerLoan();
	        		} else {
	    			Mensagem.vd_sc_DataPrazoMenorEmprestimo();
	        		}
	    		} else if(emDAO.pb_vd_VerificaQuantidadeEmEmprestimo(exemplar)){
	    			if(pc_bo_VerificadorTraducao){
	        			Mensagens_EN_US.vd_sc_OutOfCopys();
	        		} else {
	    			Mensagem.vd_sc_ExemplaresAcabaramEmprestimo();
	        		}
	    		} else {
	    			
	    			Emprestimo emprestimo = new Emprestimo();

	    			Date pr_dt_dataEmprestimo = pr_df_dateFormat.parse(pr_st_dataEmprestimo);;
	    			Date pr_dt_dataPrazo = pr_df_dateFormat.parse(pr_st_dataPrazo);
	    			
					java.sql.Date pr_dts_dataEmprestimo = new java.sql.Date(pr_dt_dataEmprestimo.getTime());
					java.sql.Date pr_dts_dataPrazo = new java.sql.Date(pr_dt_dataPrazo.getTime());
					
					emprestimo.setPv_dt_DataEmprestimo(pr_dts_dataEmprestimo);
					emprestimo.setPv_dt_DataPrazo(pr_dts_dataPrazo);
					
					Cliente cliente = new Cliente();
					cliente.setPv_st_CPF(pr_st_CPF);
					
					Funcionario funcionario = new Funcionario();
					funcionario.setPv_st_CPF(Funcionario.getPv_st_sc_CPF_Logado());
					
					ExemplarDAO exemplarDAO = new ExemplarDAO();
					emDAO.pb_vd_EfetuarEmprestimo(emprestimo, cliente, exemplar, funcionario);
					exemplarDAO.pb_vd_AdicionaQuantidadeEmprestimo(exemplar);
					
					//configurar reserva
					Reserva reserva = new Reserva();
					reserva.setPv_in_IDReserva(pr_in_IDReserva);
					reservaDAO.pb_ar_EfetuarReserva(reserva);
					
					anchorPane.setVisible(false);
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_ReserveSucess();
		    		} else {
					Mensagem.vd_sc_EmprestimoSucesso();
		    		}
					
	    			
	    		}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}

    }
    
    /**
     * Método que inicializa a tela
     */
    @FXML
    void initialize(){
    	ReservaDAO reservas = new ReservaDAO();
    	comboBoxReservas.getItems().addAll(reservas.pb_ar_BuscarReservasEmAberto());
    }

}
