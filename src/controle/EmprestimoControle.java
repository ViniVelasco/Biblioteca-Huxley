package controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import modelo.ClienteDAO;
import modelo.Emprestimo;
import modelo.EmprestimoDAO;
import modelo.Exemplar;
import modelo.ExemplarDAO;
import modelo.ExemplarTipo;
import modelo.ExemplarTipoDAO;
import modelo.Funcionario;
import modelo.ReservaDAO;
import modelo.Secao;
import modelo.SecaoDAO;

/**
 * Classe que controla a tela de Empréstimo
 * @author Vinícius Velasco
 *
 */
public class EmprestimoControle {
	
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<Cliente> comboBoxClientes;

    @FXML
    private ComboBox<Exemplar> comboBoxExemplares;

    @FXML
    private DatePicker datePickerDataEmprestimo;

    @FXML
    private Label lblData;

    @FXML
    private Button btnEfetuarEmprestimo;

    @FXML
    private DatePicker datePickerDataPrazo;

    @FXML
    private Label lblPrazo;
    
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
    	comboBoxClientes.setPromptText(bundle.getString("Clientes"));
    	comboBoxExemplares.setPromptText(bundle.getString("Exemplares"));
    	btnEfetuarEmprestimo.setText(bundle.getString("EfetuarEmprestimo"));
    	
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * Método que efetuar um empréstimo
     * @param event
     * @throws ParseException
     */
    @FXML
    void btnEfetuarEmprestimoClick(ActionEvent event) throws ParseException {
    	
    	
    	
    	if(comboBoxExemplares.getValue() == null || comboBoxClientes.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    		Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    	} else if (datePickerDataEmprestimo.getValue() == null || datePickerDataPrazo == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_DatesNotSelected();
    		} else {
    		Mensagem.vd_sc_DatasNaoSelecionadas();
    		}
    	} else {
    		String pr_st_dataEmprestimo = datePickerDataEmprestimo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		String pr_st_dataPrazo = datePickerDataPrazo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		
    		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		
    		Date pr_dt_comparaEmprestimo = pr_df_dateFormat.parse(pr_st_dataEmprestimo);
    		Date pr_dt_comparaPrazo = pr_df_dateFormat.parse(pr_st_dataPrazo);
    		
    		//int pr_in_comparadorDatas = pr_st_dataEmprestimo.compareTo(pr_st_dataPrazo);
    		//int pr_in_comparadorDatasPrazo = pr_st_dataPrazo.compareTo(pr_st_dataEmprestimo);
    		
    		EmprestimoDAO emDAO = new EmprestimoDAO();
        	Exemplar exemplar = new Exemplar();
        	exemplar.setPv_in_IdExemplar(comboBoxExemplares.getValue().getPv_in_IdExemplar());
        	
    		
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
        			Mensagens_EN_US.vd_sc_DateLoanBig();
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
    			
				try {
					Emprestimo emprestimo = new Emprestimo();
	    			
	    			
	    			
	    			Date pr_dt_dataEmprestimo = pr_df_dateFormat.parse(pr_st_dataEmprestimo);;
	    			Date pr_dt_dataPrazo = pr_df_dateFormat.parse(pr_st_dataPrazo);
	    			
					java.sql.Date pr_dts_dataEmprestimo = new java.sql.Date(pr_dt_dataEmprestimo.getTime());
					java.sql.Date pr_dts_dataPrazo = new java.sql.Date(pr_dt_dataPrazo.getTime());
					
					emprestimo.setPv_dt_DataEmprestimo(pr_dts_dataEmprestimo);
					emprestimo.setPv_dt_DataPrazo(pr_dts_dataPrazo);
					
					Cliente cliente = new Cliente();
					cliente.setPv_st_CPF(comboBoxClientes.getValue().getPv_st_CPF());
					
					exemplar.setPv_in_IdExemplar(comboBoxExemplares.getValue().getPv_in_IdExemplar());
					
					Funcionario funcionario = new Funcionario();
					funcionario.setPv_st_CPF(Funcionario.getPv_st_sc_CPF_Logado());
					
					ExemplarDAO exemplarDAO = new ExemplarDAO();
					
					emDAO.pb_vd_EfetuarEmprestimo(emprestimo, cliente, exemplar, funcionario);
					exemplarDAO.pb_vd_AdicionaQuantidadeEmprestimo(exemplar);
					
					anchorPane.setVisible(false);
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_LoanSucess();
		    		} else {
					Mensagem.vd_sc_EmprestimoSucesso();
		    		}
					
					
				} catch (ParseException e) {

				}
    			
    			
    			
    		}
    	}

    }
    
    /**
     * Método que inicializa a tela e carrega uma combo box.
     */
    @FXML
    public void initialize() {
    	
    	ExemplarDAO exemplarDAO = new ExemplarDAO();
    	ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
    	exemplarDAO.pb_vd_BuscaExemplares(exemplares);
   
    	comboBoxExemplares.getItems().addAll(exemplares);
    	
    	ClienteDAO cliente = new ClienteDAO();
    	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    	cliente.pb_vd_BuscaClientes(clientes);
    	
    	comboBoxClientes.getItems().addAll(clientes);
    	
    }

}
