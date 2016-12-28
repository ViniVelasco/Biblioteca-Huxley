package controle;

import java.net.URL;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import modelo.Emprestimo;
import modelo.EmprestimoDAO;

/**
 * Classe que controla a tela de consultar empréstimo
 * @author Vinícius Velasco
 *
 */
public class ConsultarEmprestimoControle implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Emprestimo> tableConsultarEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> columnCPFCliente;

    @FXML
    private TableColumn<Emprestimo, String> columnCPFFuncionario;

    @FXML
    private TableColumn<Emprestimo, Integer> columnidExemplar;

    @FXML
    private TableColumn<Emprestimo, String> columnNomeDoExemplar;

    @FXML
    private TableColumn<Emprestimo, Date> columnDataInicio;

    @FXML
    private TableColumn<Emprestimo, Date> columnDataPrazo;

    @FXML
    private TableColumn<Emprestimo, String> columnStatus;

    @FXML
    private TableColumn<Emprestimo, Date> columnDataEntregue;

    @FXML
    private TableColumn<Emprestimo, Double> columnMulta;

    @FXML
    private ComboBox<String> comboBoxTipoPesquisa;

    @FXML
    private Label lblFiltragem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField textPesquisa;

    @FXML
    private Text lblPesquisa;
    
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
    	comboBoxTipoPesquisa.getItems().clear();
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	comboBoxTipoPesquisa.setPromptText(bundle.getString("SelecionePesquisa"));
    	columnCPFCliente.setText(bundle.getString("CPFDoCliente"));
    	columnCPFFuncionario.setText(bundle.getString("CPFFuncionario"));
    	columnNomeDoExemplar.setText(bundle.getString("NomeExemplar"));
    	columnDataInicio.setText(bundle.getString("DataInicio"));
    	columnDataPrazo.setText(bundle.getString("PrazoEmprestimo"));
    	columnDataEntregue.setText(bundle.getString("DataEntrega"));
    	columnMulta.setText(bundle.getString("Multa"));
    	comboBoxTipoPesquisa.getItems().addAll(bundle.getString("PesquisarCPFCliente"), bundle.getString("EmprestimosFinalizados"), bundle.getString("EmprestimosAbertos"));
    	
    	pc_bo_VerificadorTraducao = true;
    	
    	selecionaTipoPesquisa(event);

    }

    /**
     * Método que realiza a pesquisa
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		
           	pr_vd_AtualizaTabela();
    		
    	} else {
    		
    		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por CPF do Cliente" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarCPFCliente")){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_AtualizaTabela();
    				
    			} else {
    				
    				if(ValidacoesdeNegocio.pb_bo_static_CPFValido(textPesquisa.getText()) == false){
    					if(pc_bo_VerificadorTraducao){
    		    			Mensagens_EN_US.vd_sc_WrongCPF();
    		    		} else {
						Mensagem.vd_sc_CPFInválido();
    		    		}
						
					} else {
						
						pr_vd_AtualizaTabelaFiltragemCPF(textPesquisa.getText());
						
					}
    				
    			}
    			
    		} else if(comboBoxTipoPesquisa.getValue() == "Pesquisar por empréstimos finalizados" || comboBoxTipoPesquisa.getValue() == bundle.getString("EmprestimosFinalizados")){
    			
    			pr_vd_AtualizaTabelaFiltragemFinalizados();
    			
    		} else {
    			pr_vd_AtualizaTabelaFiltragemEmAberto();
    		}
    		
    	}

    }

    /**
     * Método que controla o tipo de pesquisa
     * @param event
     */
    @FXML
    void selecionaTipoPesquisa(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == "Pesquisar por CPF do Cliente" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarCPFCliente")){
    		
    		if(pc_bo_VerificadorTraducao){
    			
    			lblPesquisa.setText(bundle.getString("PesquisarCPFCliente"));
        		lblPesquisa.setVisible(true);
        		
        		lblPesquisa.setFill(Color.web("#ff0000"));
    			
    		}else if(comboBoxTipoPesquisa.getSelectionModel().isEmpty()){
        		
        	} 
    		else {
    	
    		
    		lblPesquisa.setText("Pesquisar por CPF do Cliente");
    		lblPesquisa.setVisible(true);
    		
    		lblPesquisa.setFill(Color.web("#ff0000"));
    		
    		}
    		
    	} else if(comboBoxTipoPesquisa.getSelectionModel().isEmpty()){
    		
    	} else if(comboBoxTipoPesquisa.getValue() == "Pesquisar por empréstimos finalizados" || comboBoxTipoPesquisa.getValue() ==  bundle.getString("EmprestimosFinalizados")){
    		lblPesquisa.setVisible(false);
    		lblPesquisa.setText("");
    	} else if(comboBoxTipoPesquisa.getValue() == "Pesquisar por empréstimos em aberto" || comboBoxTipoPesquisa.getValue() ==  bundle.getString("EmprestimosAbertos")){
    		lblPesquisa.setVisible(false);
    	}
    	

    }
    
    /**
     * Método que configura colunas
     */
    public void pb_vd_ConfiguraColunas(){
    	columnCPFCliente.setCellValueFactory((new PropertyValueFactory<>("pv_st_CPFCliente")));
		columnCPFFuncionario.setCellValueFactory((new PropertyValueFactory<>("pv_st_CPFFuncionario")));
		columnidExemplar.setCellValueFactory((new PropertyValueFactory<>("pv_in_IDExemplar")));
		columnNomeDoExemplar.setCellValueFactory((new PropertyValueFactory<>("pv_st_NomeExemplar")));
		columnDataInicio.setCellValueFactory((new PropertyValueFactory<>("pv_dt_DataEmprestimo")));
		columnDataPrazo.setCellValueFactory((new PropertyValueFactory<>("pv_dt_DataPrazo")));
		columnStatus.setCellValueFactory((new PropertyValueFactory<>("pv_st_Status")));
		columnDataEntregue.setCellValueFactory((new PropertyValueFactory<>("pv_dt_DataEntrega")));
		columnMulta.setCellValueFactory((new PropertyValueFactory<>("pv_db_multa")));
    }

    /**
     * Método que inicializa a tela e carrega a combo box
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		comboBoxTipoPesquisa.getItems().addAll("Pesquisar por CPF do Cliente", "Pesquisar por empréstimos finalizados", "Pesquisar por empréstimos em aberto");
		
		
		pb_vd_ConfiguraColunas();
       	pr_vd_AtualizaTabela();
		
	}
	
	/**
	 * Método que limpa a tela
	 */
	void pr_vd_Limpar() {
		  tableConsultarEmprestimos.getSelectionModel().select(null);
	}
	
	/**
	 * Método que atualiza a tabela
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabela() {

		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		tableConsultarEmprestimos.getItems().setAll(emprestimoDAO.pb_ar_BuscarEmprestimo());

		pr_vd_Limpar();

	}
	
	/**
	 * Método que atualiza a tabela pelo CPF
	 * @param CPF
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabelaFiltragemCPF(String CPF) {

		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setPv_st_CPFCliente(CPF);
		tableConsultarEmprestimos.getItems().setAll(emprestimoDAO.pb_ar_BuscarEmprestimoPorCPFCliente(emprestimo));

		pr_vd_Limpar();

	}
	
	/**
	 * Método que atualiza a tabela por empréstimos em aberto
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabelaFiltragemEmAberto() {

		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		Emprestimo emprestimo = new Emprestimo();
		tableConsultarEmprestimos.getItems().setAll(emprestimoDAO.pb_ar_BuscarEmprestimoEmAberto());

		pr_vd_Limpar();

	}
	
	/*
	 * Método que atualiza a tabela por empréstimos finalizados
	 */
	@SuppressWarnings("unchecked")
	void pr_vd_AtualizaTabelaFiltragemFinalizados() {

		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		Emprestimo emprestimo = new Emprestimo();
		tableConsultarEmprestimos.getItems().setAll(emprestimoDAO.pb_ar_BuscarEmprestimoFinalizados());

		pr_vd_Limpar();

	}

}
