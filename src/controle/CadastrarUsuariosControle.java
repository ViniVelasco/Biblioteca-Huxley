package controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import modelo.Cidade;
import modelo.CidadeDAO;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.ClienteTipo;
import modelo.ClienteTipoDAO;
import modelo.Estado;
import modelo.EstadoCivil;
import modelo.EstadoCivilDAO;
import modelo.EstadoDAO;
import modelo.Sexo;
import modelo.SexoDAO;

/**
 * Classe que controla a tela de Cadastro de Usuários
 * @author Vinícius Velasco
 *
 */
public class CadastrarUsuariosControle {

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
    private DatePicker datePickerDataNascimento;

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
    private PasswordField passwordSenha;

    @FXML
    private ComboBox<Cidade> comboBoxCidade;

    @FXML
    private ComboBox<Estado> comboBoxEstado;

    @FXML
    private ComboBox<ClienteTipo> comboBoxTipoCliente;
    
    @FXML
    private Label lblCPF;
    
    @FXML
    private Label lblNome;
   
    @FXML
    private Label lblEndereco;

    @FXML
    private Label lblDataNascimento;
    
    @FXML
    private Label lblTelefone;
    
    @FXML
    private Button btnTraduzir;
    
    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblSenha;
    
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
    	comboBoxTipoCliente.setPromptText(bundle.getString("TipoCliente"));
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
     * Método que cadastra o usuário
     * @param event
     */
    @FXML
    void btnCadastrarClick(ActionEvent event) {
    	//Tratar Ano, não pode ser menor que data atual.
    	if(comboBoxEstadoCivil.getValue() == null || comboBoxSexo.getValue() == null || comboBoxCidade.getValue() == null || comboBoxEstado.getValue() == null || comboBoxTipoCliente.getValue() == null){
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
				
				//LocalDate pr_ld_dataNascimento = datePickerDataNascimento.getValue();
				String pr_st_dataNascimento = datePickerDataNascimento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				DateFormat pr_df_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				Date pr_dt_dataNascimento = pr_df_dateFormat.parse(pr_st_dataNascimento);
				java.sql.Date pr_dts_dataNascimento = new java.sql.Date(pr_dt_dataNascimento.getTime());
				

				//Date pr_dt_dataNascimento = pr_df_dateFormat.parse(pr_st_dataNascimento);
				ClienteDAO clienteDAO = new ClienteDAO();
				if(clienteDAO.pb_vd_buscaCPF(pr_st_CPF)){
					Mensagem.vd_sc_CPFCadastado();
				} else {
				
				//Iniciando Cadastro do Cliente
				Cliente cliente = new Cliente();
				cliente.setPv_st_Telefone(pr_st_Telefone);
				cliente.setPv_st_dataNascimento(pr_st_dataNascimento);
				cliente.setPv_in_EstadoCivil(comboBoxEstadoCivil.getValue().getPv_in_IDEstadoCivil());
				cliente.setPv_in_IdCidade(comboBoxCidade.getValue().getPv_in_IDCidade());
				cliente.setPv_in_IdEstado(comboBoxEstado.getValue().getPv_in_IDEstado());
				cliente.setPv_in_IdSexo(comboBoxSexo.getValue().getPv_in_IDSexo());
				cliente.setPv_in_IdTipoCliente(comboBoxTipoCliente.getValue().getPv_in_IDTipo());
				cliente.setPv_st_CPF(pr_st_CPF);
				cliente.setPv_st_Endereco(pr_st_Endereco);
				cliente.setPv_st_Login(pr_st_Usuario);
				cliente.setPv_st_Senha(pr_st_Senha);
				cliente.setPv_st_Nome(pr_st_Nome);
				cliente.setPv_dt_DataNascimentoSQL(pr_dts_dataNascimento);
				
				clienteDAO.pb_vd_CadastrarCliente(cliente);
				anchorPane.setVisible(false);
				
				if(pc_bo_VerificadorTraducao){
	    			Mensagens_EN_US.vd_sc_UserRegisterSucess();
	    		} else {
				Mensagem.vd_sc_CadastroUsuariosSucesso();
	    		}
				
				}
				
			} catch(NumberFormatException | ParseException ne){
				Mensagem.vd_sc_CamposInvalidosUsuario();
			}
			
		}

    }

    /**
     * Método que limpa os campos
     * @param event
     * @throws ParseException
     */
    @FXML
    void btnLimparClick(ActionEvent event) throws ParseException {
    	textCPF.clear();
    	textNome.clear();
    	textEndereco.clear();
    	textTelefone.clear();
    	textUsuario.clear();
    	passwordSenha.clear();

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
    	
    	ClienteTipoDAO tipo = new ClienteTipoDAO();
    	tipo.pb_vd_BuscaTiposCliente(ClienteTipo.pb_ar_sc_tipos);
    	
    	for(ClienteTipo tipos : ClienteTipo.pb_ar_sc_tipos){
    		comboBoxTipoCliente.getItems().add(tipos);
    	}
    	
    	EstadoDAO estado = new EstadoDAO();
    	estado.pb_vd_BuscaEstado(Estado.pb_ar_sc_estados);
    	
    	for(Estado estados : Estado.pb_ar_sc_estados){
    		comboBoxEstado.getItems().add(estados);
    	}
    	
    	
    }
    
    /**
     * Método que controla a combo box de estado
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
