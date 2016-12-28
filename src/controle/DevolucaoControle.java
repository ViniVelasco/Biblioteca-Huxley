package controle;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import modelo.Emprestimo;
import modelo.EmprestimoDAO;
import modelo.Exemplar;
import modelo.ExemplarDAO;
/**
 * Classe controladora da tela Devolução
 * @author Vinícius Velasco
 *
 */
public class DevolucaoControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Emprestimo> comboBoxEmprestimo;

    @FXML
    private TextField textCPFCliente;

    @FXML
    private Label lblCPFCliente;

    @FXML
    private Label lblExemplar;

    @FXML
    private TextField textExemplar;

    @FXML
    private TextField textDataPrazo;

    @FXML
    private Label lblDataPrazo;

    @FXML
    private Label lblDataEntregue;

    @FXML
    private DatePicker datePickerDataEntregue;

    @FXML
    private Button btnDevolucao;

    @FXML
    private Label lblMulta;

    @FXML
    private TextField textMulta;
    
    @FXML
    private Button btnVerificarMulta;
    
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
    	lblDataPrazo.setText(bundle.getString("PrazoEmprestimo"));
    	lblExemplar.setText(bundle.getString("Exemplar"));
    	lblCPFCliente.setText(bundle.getString("CPFCliente"));
    	lblDataEntregue.setText(bundle.getString("DataEntrega"));
    	btnVerificarMulta.setText(bundle.getString("VerificarMulta"));
    	btnDevolucao.setText(bundle.getString("EfetuarDevolução"));
    	comboBoxEmprestimo.setPromptText(bundle.getString("SelecionarEmprestimo"));
    	
    	pc_bo_VerificadorTraducao = true;

    }


    /**
     * Método que faz a devolução
     * @param event
     */
    @FXML
    void btnDevolucaoClick(ActionEvent event) {
    	
    	if(datePickerDataEntregue.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyDateOfDelivery();
    		} else {
    		Mensagem.vd_sc_DataEntregaVaziaEmprestimo();
    		}
    	} else if(pr_bo_Verificador && textMulta.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_VerifiedTicket();
    		} else {
    		Mensagem.vd_sc_MultaVerificada();
    		}
    	}
    	else {
    		
			try {
				String pr_st_dataEntregue = datePickerDataEntregue.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date pr_dt_dataEntregue = pr_df_dateFormat.parse(pr_st_dataEntregue);
				java.sql.Date pr_dts_dataEntrgue = new java.sql.Date(pr_dt_dataEntregue.getTime());
				double pr_db_Multa = 0;
				if(!textMulta.getText().isEmpty()){
					 pr_db_Multa = Double.parseDouble(textMulta.getText());
				}
				
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setMulta(pr_db_Multa);
				emprestimo.setPv_dt_DataEntrega(pr_dts_dataEntrgue);
				emprestimo.setPv_int_IDEmprestimo(comboBoxEmprestimo.getValue().getPv_int_IDEmprestimo());
				
				EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
				emprestimoDAO.pb_vd_EfetuarDevolucao(emprestimo);
				
				Exemplar exemplar = new Exemplar();
				exemplar.setPv_in_IdExemplar(comboBoxEmprestimo.getValue().getPv_in_IDExemplar());
				
				ExemplarDAO exemplarDAO = new ExemplarDAO();
				exemplarDAO.pb_vd_RemoveQuantidadeEmprestimo(exemplar);
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_DevolutionSucess();
	    		} else {
	    			Mensagem.vd_sc_DevolucaoSucesso();
	    		}
				anchorPane.setVisible(false);
			} catch(NumberFormatException ne){
				
				Mensagem.vd_sc_ApenasNumerosLivro();
				
			} catch (ParseException e) {
				
			}
			
			

    		
    	}

    }
    protected boolean pr_bo_Verificador = false;
    
    /**
     * Método que controla o empréstimo selecionado
     * @param event
     */
    @FXML
    void emprestimoSelecionado(ActionEvent event) {
    	datePickerDataEntregue.setDisable(false);
    	btnVerificarMulta.setDisable(false);
    	//btnDevolucao.setDisable(false);
    	//textMulta.setDisable(false);
    	
    	textCPFCliente.setText(comboBoxEmprestimo.getValue().getPv_st_CPFCliente());
    	textExemplar.setText(comboBoxEmprestimo.getValue().getPv_st_NomeExemplar());
    	
    	Date pr_dt_dataPrazo = comboBoxEmprestimo.getValue().getPv_dt_DataPrazo();
    	
    	DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String pr_st_DataFormatada = pr_df_dateFormat.format(pr_dt_dataPrazo);
    	textDataPrazo.setText(pr_st_DataFormatada);
    	
    	

    }
    
    /**
     * Método que verifica a multa da devolução
     * @param event
     * @throws ParseException
     */
    @FXML
    void btnVerificarMulta(ActionEvent event) throws ParseException {
    	
    	if(datePickerDataEntregue.getValue() == null){
    		Mensagem.vd_sc_DataEntregaVaziaEmprestimo();
    	} else {
    		
    		Date pr_dt_dataPrazo = comboBoxEmprestimo.getValue().getPv_dt_DataPrazo();
    		
    		String pr_st_dataEntregue = datePickerDataEntregue.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		
    		Date pr_dr_dataEntregue = pr_df_dateFormat.parse(pr_st_dataEntregue);
    		
    		if(ValidacoesdeNegocio.comparaData(pr_dr_dataEntregue, pr_dt_dataPrazo) > 0){ //Se for igual a zero foi entregue no dia, se for menor foi entregue no prazo.
    			btnDevolucao.setDisable(false);
    	    	textMulta.setDisable(false);
    	    	textMulta.setText("0");
    	    	Mensagem.vd_sc_MultaAplicada();
    	    	pr_bo_Verificador = true;
    	    	lblMulta.setTextFill(Color.web("#ff0000"));  //Settando o valor de pintura do lblMulta para vermelho (hexadecimal)
    		} else {
    			btnDevolucao.setDisable(false);
    			Mensagem.vd_sc_SemMulta();
    		}
    		comboBoxEmprestimo.setDisable(true);
    		datePickerDataEntregue.setDisable(true);
    		btnVerificarMulta.setDisable(true);
    	}

    }
    
    /**
     * Método que inicializa a tela e carrega a combo box.
     */
    @FXML
    public void initialize() {
    	
    	EmprestimoDAO eDAO = new EmprestimoDAO();
    	ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    	eDAO.pb_vd_BuscaEmprestimosEmAberto(emprestimos);
    	
    	comboBoxEmprestimo.getItems().addAll(emprestimos);

    }

}
