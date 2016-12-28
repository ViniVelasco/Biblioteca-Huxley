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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cidade;
import modelo.CidadeDAO;
import modelo.ClienteDAO;
import modelo.ClienteTipo;
import modelo.ClienteTipoDAO;
import modelo.Estado;
import modelo.EstadoCivil;
import modelo.EstadoCivilDAO;
import modelo.EstadoDAO;
import modelo.Funcionario;
import modelo.FuncionarioDAO;
import modelo.FuncionarioTipo;
import modelo.FuncionarioTipoDAO;
import modelo.Sexo;
import modelo.SexoDAO;

/**
 * Classe controladora da tela alterar funcionário.
 * @author Vinícius Velasco
 *
 */
public class AlterarFuncionarioControle  {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAlterar;

    @FXML
    private TextField textCPF;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textEndereco;

    @FXML
    private TextField textTelefone;
    
    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblSenha;
    
    @FXML
    private Label lblNome;
    
    @FXML
    private Label lblEndereco;
    
    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblDataNascimento;

    @FXML
    private ComboBox<EstadoCivil> comboBoxEstadoCivil;

    @FXML
    private ComboBox<Sexo> comboBoxSexo;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField textUsuario;

    @FXML
    private ComboBox<Cidade> comboBoxCidade;

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<FuncionarioTipo> comboBoxTipoFuncionario;

    @FXML
    private DatePicker datePickerDataNascimento;
    
    @FXML
    private Button btnTraduzir;

    @FXML
    private PasswordField passwordSenha;
    
    public static Stage pb_sg_sc_AlterarFuncionarios;
    
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
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnFechar.setText(bundle.getString("Fechar"));
    	lblNome.setText(bundle.getString("Nome"));
    	lblEndereco.setText(bundle.getString("Endereço"));
    	lblDataNascimento.setText(bundle.getString("DataNascimento"));
    	lblTelefone.setText(bundle.getString("Telefone"));
    	comboBoxEstadoCivil.setPromptText(bundle.getString("EstadoCivil"));
    	comboBoxTipoFuncionario.setPromptText(bundle.getString("TipoFuncionario"));
    	comboBoxSexo.setPromptText(bundle.getString("Sexo"));
    	comboBoxEstado.setPromptText(bundle.getString("Estado"));
    	comboBoxCidade.setPromptText(bundle.getString("Cidade"));
    	lblUsuario.setText(bundle.getString("Login"));
    	lblSenha.setText(bundle.getString("Senha"));

    }


    /**
     * Método que altera o funcionário
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(comboBoxCidade.getValue() == null || comboBoxEstado.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_Comboboxnotselect();
    		} else {
    			Mensagem.vd_sc_ComboboxNaoSelecionada();
    		}
    		
    		
    	} else if(textCPF.getText().isEmpty() || textNome.getText().isEmpty() || textEndereco.getText().isEmpty() || textTelefone.getText().isEmpty() ||  textUsuario.getText().isEmpty() || passwordSenha.getText() == null || datePickerDataNascimento.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_EmptyFields();
    		} else {
    			Mensagem.vd_sc_CamposVazios();
    		}
			
		} else if(textCPF.getLength() > 11 || textEndereco.getLength() > 80 || textTelefone.getLength() > 20 || textNome.getLength() > 40 || textUsuario.getLength() > 40 || passwordSenha.getLength() > 20){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LengthEmployee();
    		} else {
    			Mensagem.vd_sc_TamanhoFieldFuncionario();
    		}
			
		} else if(ValidacoesdeNegocio.pb_bo_static_TelefoneValido(textTelefone.getText()) == false){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_InvalidTelephone();
    		} else {
			Mensagem.vd_sc_TelefoneInválido();
    		}
		} else if(ValidacoesdeNegocio.pb_bo_static_CPFValido(textCPF.getText()) == false){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_WrongCPF();
    		}  else {
			Mensagem.vd_sc_CPFInválido();
    		}
			
		} else if(ValidacoesdeNegocio.ChecarNome(textNome.getText()) == false) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_InvalidNames();
    		} else {
			Mensagem.vd_sc_NomeInvalidos();
    		}
			
		}else if(textCPF.getText().equals("11111111111") || textCPF.getText().length() < 11){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_CPFAlreadyRegistered();
    		} else {
    			Mensagem.vd_sc_CPFInválido();
    		}
		}
		else {
			
			
		try {
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				double pr_db_VerificaCPF = Double.parseDouble(textCPF.getText());
				String pr_st_Nome = textNome.getText();
				String pr_st_Endereco = textEndereco.getText();
				String pr_st_Usuario = textUsuario.getText();
				String pr_st_Senha = passwordSenha.getText();
				Double pr_db_Telefone = Double.parseDouble(textTelefone.getText());
				String pr_st_CPF = textCPF.getText();
				String pr_st_Telefone = textTelefone.getText();
				
				boolean verificador = true;
				
				if(!ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_CPF().equals(pr_st_CPF)){
					
					Funcionario antigoFuncionario = new Funcionario();;
					antigoFuncionario.setPv_st_CPF(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_CPF());
					if(funcionarioDAO.pb_vd_buscaCPF(pr_st_CPF)){
						if(pc_bo_VerificadorTraducao){
			    			Mensagens_EN_US.vd_sc_CPFAlreadyRegistered();
			    		} else {
						Mensagem.vd_sc_CPFCadastado();
			    		}
						verificador = false;
					} else if(funcionarioDAO.pb_bo_VerificaEmprestimosFuncionario(antigoFuncionario)){
						if(pc_bo_VerificadorTraducao){
			    			Mensagens_EN_US.vd_sc_EmployeAssociedLoan();
			    		}  else {
						Mensagem.vd_sc_FuncionarioAssociadoEmprestimo();
			    		}
						verificador = false;
						
					}
					
				}
				
				if(verificador){
					
					String pr_st_dataNascimento = datePickerDataNascimento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					
					Date pr_dt_dataNascimento = pr_df_dateFormat.parse(pr_st_dataNascimento);
					java.sql.Date pr_dts_dataNascimento = new java.sql.Date(pr_dt_dataNascimento.getTime());
					
					Funcionario funcionario = new Funcionario();
					funcionario.setPv_st_Telefone(pr_st_Telefone);
					funcionario.setPv_dt_dataNascimento(pr_dts_dataNascimento);
					funcionario.setPv_in_EstadoCivil(comboBoxEstadoCivil.getValue().getPv_in_IDEstadoCivil());
					funcionario.setPv_in_IdCidade(comboBoxCidade.getValue().getPv_in_IDCidade());
					funcionario.setPv_in_IdEstado(comboBoxEstado.getValue().getPv_in_IDEstado());
					funcionario.setPv_in_IdSexo(comboBoxSexo.getValue().getPv_in_IDSexo());
					funcionario.setPv_in_IdTipoFuncionario(comboBoxTipoFuncionario.getValue().getPv_in_IDTipoFuncionario());
					funcionario.setPv_st_CPF(pr_st_CPF);
					funcionario.setPv_st_Endereco(pr_st_Endereco);
					funcionario.setPv_st_Login(pr_st_Usuario);
					funcionario.setPv_st_Senha(pr_st_Senha);
					funcionario.setPv_st_Nome(pr_st_Nome);
					String pr_st_CPFAntigo = ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_CPF();
				
					
					
					funcionarioDAO.pb_vd_AlterarFuncionaro(funcionario, pr_st_CPFAntigo);
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_EmployeeRegisterSucess();
		    		} else {
					Mensagem.vd_sc_CadastroFuncionarioSucesso();
		    		}
					AlterarFuncionarioControle.pb_sg_sc_AlterarFuncionarios.close();
					
					
				}
				
			
		} catch(NumberFormatException | ParseException ne){
			
			ne.printStackTrace();
			
		}
		}

    }

    /**
     * Método que fecha a tela
     * @param event
     */
    @FXML
    void btnFecharClick(ActionEvent event) {
    	
    	AlterarFuncionarioControle.pb_sg_sc_AlterarFuncionarios.close();

    }

    /**
     * Método que trata a comboBox Selecionada
     * @param event
     */
    @FXML
    void comboBoxEstadoSelecionado(ActionEvent event) {
    	
    	comboBoxCidade.getItems().clear();
    	
    	CidadeDAO cidadeDAO = new CidadeDAO();
    	Cidade cidade = new Cidade();
    	cidade.setPv_in_IDEstadoReferente(comboBoxEstado.getValue().getPv_in_IDEstado());
    	cidadeDAO.pb_vd_BuscaCidades(Cidade.pb_ar_sc_cidades, cidade);
    	
    	comboBoxCidade.setDisable(false);
    	comboBoxCidade.getItems().addAll(Cidade.pb_ar_sc_cidades);
    	comboBoxCidade.getSelectionModel().select(0);

    }
    
    /**
     * Método que inicializa a tela com as combo box carregas
     */
    @FXML
    public void initialize(){
    	
    	SexoDAO sexo = new SexoDAO();
    	sexo.pb_vd_BuscaSexo(Sexo.pb_ar_sc_sexos);
    	
    	EstadoCivilDAO estadoCivil = new EstadoCivilDAO();
    	estadoCivil.pb_vd_BuscaEstadoCivil(EstadoCivil.pb_ar_sc_estadoCivil);
    	
    	FuncionarioTipoDAO tipo = new FuncionarioTipoDAO();
    	tipo.pb_vd_BuscaTiposCliente(FuncionarioTipo.pb_ar_sc_tipos);
    	
    	EstadoDAO estado = new EstadoDAO();
    	estado.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
    	
    	CidadeDAO cidadeDAO = new CidadeDAO();
    	Cidade cidade = new Cidade();
    	cidade.setPv_in_IDEstadoReferente(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_in_IdEstado());
    	cidadeDAO.pb_vd_BuscaCidades(Cidade.pb_ar_sc_cidades, cidade);
    	
    	comboBoxSexo.getItems().addAll(Sexo.pb_ar_sc_sexos);
    	comboBoxEstadoCivil.getItems().addAll(EstadoCivil.pb_ar_sc_estadoCivil);
    	comboBoxTipoFuncionario.getItems().addAll(FuncionarioTipo.pb_ar_sc_tipos);
    	comboBoxEstado.getItems().addAll(Estado.pb_ar_sc_estados);
    	comboBoxCidade.getItems().addAll(Cidade.pb_ar_sc_cidades);
    	
    	textCPF.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_CPF());
    	textNome.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_Nome());
    	textEndereco.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_Endereco());
    	textTelefone.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_Telefone());
    	textUsuario.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_Login());
    	datePickerDataNascimento.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_dt_dataNascimento().toLocalDate());
    	passwordSenha.setText(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).getPv_st_Senha());
    	
    	comboBoxEstadoCivil.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).estadoCivil);
    	comboBoxSexo.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).sexo);
    	comboBoxTipoFuncionario.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).tipo);
    	comboBoxCidade.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).cidade);
    	comboBoxEstado.setValue(ConsultarFuncionarioControle.funcionarioSelecionado.get(0).estado);
	
       	
     }
    

}
