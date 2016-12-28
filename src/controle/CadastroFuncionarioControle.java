package controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Cidade;
import modelo.CidadeDAO;
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
 * Classe controladora de Cadastrar Funcionário
 * @author Vinícius Velasco
 *
 */
public class CadastroFuncionarioControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField textCPF;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textEndereco;

    @FXML
    private TextField textTelefone;

    @FXML
    private ComboBox<EstadoCivil> comboBoxEstadoCivil;

    @FXML
    private ComboBox<Sexo> comboBoxSexo;

    @FXML
    private Button btnLimpar;

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
    private PasswordField passwordSenha;
    
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
    	lblNome.setText(bundle.getString("Nome"));
    	lblEndereco.setText(bundle.getString("Endereço"));
    	lblDataNascimento.setText(bundle.getString("DataNascimento"));
    	lblTelefone.setText(bundle.getString("Telefone"));
    	comboBoxEstadoCivil.setPromptText(bundle.getString("EstadoCivil"));
    	comboBoxTipoFuncionario.setPromptText(bundle.getString("TipoFuncionario"));
    	comboBoxSexo.setPromptText(bundle.getString("Sexo"));
    	comboBoxEstado.setPromptText(bundle.getString("Estado"));
    	comboBoxCidade.setPromptText(bundle.getString("Cidade"));
    	btnCadastrar.setText(bundle.getString("Cadastrar"));
    	btnLimpar.setText(bundle.getString("Limpar"));
    	lblUsuario.setText(bundle.getString("Login"));
    	lblSenha.setText(bundle.getString("Senha"));

    	pc_bo_VerificadorTraducao = true;
    }


    /**
     * Método que cadastra o Funcionário
     * @param event
     */
    @FXML
    void btnCadastrarClick(ActionEvent event) {
    	
    	if(comboBoxEstadoCivil.getValue() == null || comboBoxSexo.getValue() == null || comboBoxCidade.getValue() == null || comboBoxEstado.getValue() == null || comboBoxTipoFuncionario.getValue() == null){
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
    		} else {
			Mensagem.vd_sc_CPFInválido();
    		}
			
		} else if(ValidacoesdeNegocio.ChecarNome(textNome.getText()) == false) {
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_InvalidNames();
    		} else {
			Mensagem.vd_sc_NomeInvalidos();
    		}
			
		} else if(textCPF.getText().equals("11111111111") || textCPF.getText().length() < 11){
			if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_WrongCPF();
    		} else {
			Mensagem.vd_sc_CPFInválido();
    		}
		}
    	else {
			try {
			double pr_db_VerificaCPF = Double.parseDouble(textCPF.getText());
			String pr_st_Nome = textNome.getText();
			String pr_st_Endereco = textEndereco.getText();
			String pr_st_Usuario = textUsuario.getText();
			String pr_st_Senha = passwordSenha.getText();
			Double pr_db_Telefone = Double.parseDouble(textTelefone.getText());
			String pr_st_CPF = textCPF.getText();
			String pr_st_Telefone = textTelefone.getText();
			
			String pr_st_dataNascimento = datePickerDataNascimento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			Date pr_dt_dataNascimento = pr_df_dateFormat.parse(pr_st_dataNascimento);
			java.sql.Date pr_dts_dataNascimento = new java.sql.Date(pr_dt_dataNascimento.getTime());
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			
			if(funcionarioDAO.pb_vd_buscaCPF(pr_st_CPF)){
				Mensagem.vd_sc_CPFCadastado();
			} else {
				
				//Iniciando Cadastro do Funcionário
				Funcionario funcionario = new Funcionario();
				funcionario.setPv_dt_dataNascimento(pr_dts_dataNascimento);
				funcionario.setPv_in_EstadoCivil(comboBoxEstadoCivil.getValue().getPv_in_IDEstadoCivil());
				funcionario.setPv_in_IdCidade(comboBoxCidade.getValue().getPv_in_IDCidade());
				funcionario.setPv_in_IdEstado(comboBoxEstado.getValue().getPv_in_IDEstado());
				funcionario.setPv_in_IdSexo(comboBoxSexo.getValue().getPv_in_IDSexo());
				funcionario.setPv_in_IdTipoFuncionario(comboBoxTipoFuncionario.getValue().getPv_in_IDTipoFuncionario());
				funcionario.setPv_st_CPF(pr_st_CPF);
				funcionario.setPv_st_DataNascimentoFormatada(pr_st_dataNascimento);
				funcionario.setPv_st_Endereco(pr_st_Endereco);
				funcionario.setPv_st_Login(pr_st_Usuario);
				funcionario.setPv_st_Senha(pr_st_Senha);
				funcionario.setPv_st_Nome(pr_st_Nome);
				funcionario.setPv_st_Telefone(pr_st_Telefone);

				funcionarioDAO.pb_vd_CadastrarFuncionario(funcionario);
				anchorPane.setVisible(false);
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_EmployeeRegisterSucess();
	    		} else {
				Mensagem.vd_sc_CadastroFuncionarioSucesso();
	    		}
			
			}
			
			} catch(NumberFormatException ne){
				Mensagem.vd_sc_CamposInvalidosUsuario();
				
			} catch (ParseException e) {
				
			}
			
		}
    }

    /**
     * Método que limpa a tela
     * @param event
     */
    @FXML
    void btnLimparClick(ActionEvent event) {
    	textNome.clear();
    	textCPF.clear();
    	textUsuario.clear();
    	passwordSenha.clear();
    	textEndereco.clear();
    	textTelefone.clear();
    }
    
    /**
     * Método que inicializa a tela e carrega as combo boxes.
     */
    @FXML
    public void initialize() {
    	
    	SexoDAO sexo = new SexoDAO();
    	sexo.pb_vd_BuscaSexo(Sexo.pb_ar_sc_sexos);
    
    	for(Sexo se : Sexo.pb_ar_sc_sexos){
    		comboBoxSexo.getItems().add(se);
    	}
    	
    	EstadoCivilDAO estadoCivil = new EstadoCivilDAO();
    	estadoCivil.pb_vd_BuscaEstadoCivil(EstadoCivil.pb_ar_sc_estadoCivil);
    	
    	for(EstadoCivil es : EstadoCivil.pb_ar_sc_estadoCivil){
    		comboBoxEstadoCivil.getItems().add(es);
    	}
    	
    	FuncionarioTipoDAO tipo = new FuncionarioTipoDAO();
    	tipo.pb_vd_BuscaTiposCliente(FuncionarioTipo.pb_ar_sc_tipos);
    
    	for(FuncionarioTipo tipos : FuncionarioTipo.pb_ar_sc_tipos){
    		comboBoxTipoFuncionario.getItems().add(tipos);
    	}
    	EstadoDAO estado = new EstadoDAO();
    	estado.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
    	
    	for(Estado estados : Estado.pb_ar_sc_estados){
    		comboBoxEstado.getItems().add(estados);
    	}
    	
    	
    }
    
    /**
     * Método que controla a combo box de estado.
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
    	

    }


}
