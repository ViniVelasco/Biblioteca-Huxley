package controle;

import java.net.URL;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Funcionario;
import modelo.FuncionarioDAO;

/**
 * Classe controladora da tela Consultar Funcionários
 * @author Viniciusbras
 *
 */
public class ConsultarFuncionarioControle implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Funcionario> tableConsultarFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> columnCPF;

    @FXML
    private TableColumn<Funcionario, String> columnNome;

    @FXML
    private TableColumn<Funcionario, String> columnEndereco;

    @FXML
    private TableColumn<Funcionario, Date> columnDataNascimento;

    @FXML
    private TableColumn<Funcionario, String> columnTelefone;

    @FXML
    private TableColumn<Funcionario, String> columnLogin;

    @FXML
    private TableColumn<Funcionario, String> columnSenha;

    @FXML
    private TableColumn<Funcionario, String> columnNomeCidade;

    @FXML
    private TableColumn<Funcionario, String> columnNomeEstado;

    @FXML
    private TableColumn<Funcionario, String> columnSexo;

    @FXML
    private TableColumn<Funcionario, String> columnEstadoCivil;

    @FXML
    private TableColumn<Funcionario, Integer> idSexo;

    @FXML
    private TableColumn<Funcionario, Integer> idEstadoCivil;

    @FXML
    private TableColumn<Funcionario, Integer> idEstado;

    @FXML
    private TableColumn<Funcionario, Integer> idCidade;

    @FXML
    private TableColumn<Funcionario, String> columnTipoFuncionario;
    
    @FXML
    private TableColumn<Funcionario, Integer> columnIDTipoFuncionario;

    @FXML
    private ComboBox<String> comboBoxTipoPesquisa;

    @FXML
    private Label lblFiltragem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnDeletar;

    @FXML
    private TextField textPesquisa;

    @FXML
    private Text lblPesquisa;
    
    public static ObservableList<Funcionario> funcionarioSelecionado;
    
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
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	columnNome.setText(bundle.getString("Nome"));
    	columnEndereco.setText(bundle.getString("Endereço"));
    	lblFiltragem.setText(bundle.getString("SelecioneSecao"));
    	columnDataNascimento.setText(bundle.getString("DataNascimento"));
    	columnTelefone.setText(bundle.getString("Telefone"));
    	columnSenha.setText(bundle.getString("Senha"));
    	columnNomeCidade.setText(bundle.getString("NomeDaCidade"));
    	columnNomeEstado.setText(bundle.getString("NomeDoEstado"));
    	columnSexo.setText(bundle.getString("Sexo"));
    	columnEstadoCivil.setText(bundle.getString("EstadoCivil"));
    	columnTipoFuncionario.setText(bundle.getString("TipoFuncionario"));
    	comboBoxTipoPesquisa.getItems().addAll(bundle.getString("PesquisarNome"), bundle.getString("PesquisarCPF"));

    	
    	pc_bo_VerificadorTraducao = true;
    }

    /**
     * Método que chama a tela de alteração do funcionário
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {
    	
    	if(tableConsultarFuncionarios.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    		
    	} else {
    		
    		funcionarioSelecionado = tableConsultarFuncionarios.getSelectionModel().getSelectedItems();
    		
    		Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarFuncionario.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarFuncionarioControle.pb_sg_sc_AlterarFuncionarios = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));
				palco.setTitle("Alterar Usuários");
				palco.showAndWait();

				pr_vd_AtualizaTabela();

			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}

   }

    /**
     * Método que deleta um funcionário
     * @param event
     */
    @FXML
    void btnDeletar(ActionEvent event) {
    	
    	if(tableConsultarFuncionarios.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    		
    	} else {
    		
    		funcionarioSelecionado = tableConsultarFuncionarios.getSelectionModel().getSelectedItems();
    		
    		Funcionario funcionario = new Funcionario();
    		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    		funcionario.setPv_st_CPF(funcionarioSelecionado.get(0).getPv_st_CPF());
    		
    		if(Mensagem.pb_bo_sc_AvisoApagandoDado()){
    			
    			if(funcionarioDAO.pb_bo_VerificaEmprestimosFuncionario(funcionario)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_EmployeAssociedLoan();
    	    		} else {
    				Mensagem.vd_sc_FuncionarioAssociadoEmprestimo();
    	    		}
    				
    			} else {
    				
    				funcionarioDAO.pb_bo_DeletarFuncionario(funcionario);
    				
					pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_EmployeeDeletedSucess();
		    		} else {
					Mensagem.vd_sc_FuncionarioApagarSucesso();
		    		}
					
    			}
    		}
    		
    		
    		
    	}

    }

    /**
     * Método que pesquisa por filtragem
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	
     	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    	} else {
    		
    		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por parte do nome ou nome completo" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNome")){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    				
    					if(ValidacoesdeNegocio.ChecarNome(textPesquisa.getText()) == false){
    						Mensagem.vd_sc_NomeInvalidos();
    						
    					} else {
    						
    					pr_vd_ConfiguraColunas();
    					pr_vd_AtualizaTabelaPesquisaPorNome(textPesquisa.getText());
    					
    					}
    					
    			}
    			
    		} else {
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_ConfiguraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    					if(ValidacoesdeNegocio.pb_bo_static_CPFValido(textPesquisa.getText()) == false){
    						
    						Mensagem.vd_sc_CPFInválido();
    						
    					} else{
    						pr_vd_ConfiguraColunas();
    						pr_vd_AtualizaTabelaPesquisaPorCPF(textPesquisa.getText());
    					}
    			}
    			
    		}
    		
    	}

    }
    
    /**
     * Método atualiza a tabela por nome
     * @param nome
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorNome(String nome){
       	
       	Funcionario funcionario = new Funcionario();
       	funcionario.setPv_st_Nome(nome);
       	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
       	tableConsultarFuncionarios.getItems().setAll(funcionarioDAO.pb_vd_BuscaTodosFuncionariosPorNome(funcionario));
       	
       	pr_vd_Limpar();

   }
    
    /**
     * Método que atualiza a tabela por CPF
     * @param CPF
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorCPF(String CPF){
       	
       	Funcionario funcionario = new Funcionario();
       	funcionario.setPv_st_CPF(CPF);
       	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
       	tableConsultarFuncionarios.getItems().setAll(funcionarioDAO.pb_vd_BuscaTodosFuncionariosPorCPF(funcionario));
       	
       	pr_vd_Limpar();

     }
    
    /**
     * Método que controla o tipo de pesquisa
     * @param event
     */
    @FXML
    void selecionaTipoPesquisa(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);

    	if(comboBoxTipoPesquisa.getValue() == "Pesquisar por parte do nome ou nome completo" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNome")){
    		
    		if(pc_bo_VerificadorTraducao){
    			lblPesquisa.setText(bundle.getString("PesquisarNome"));
    			lblPesquisa.setFill(Color.web("#ff0000"));
    			
    		} else {
    				lblPesquisa.setText("Digite parte do nome ou nome completo");
    		
    				lblPesquisa.setFill(Color.web("#ff0000"));
    		}
    		
    	} else {
    		
    		if(pc_bo_VerificadorTraducao){
    			lblPesquisa.setText(bundle.getString("PesquisarCPF"));
    			lblPesquisa.setFill(Color.web("#ff0000"));
    			
    		} else {
    		
    		lblPesquisa.setText("Digite o CPF ou parte dele");
    		lblPesquisa.setFill(Color.web("#ff0000"));
    		}
    		
    	}
    }
    
    /**
     * Método que configur acolunas
     */
    void pr_vd_ConfiguraColunas(){
    	
    	columnCPF.setCellValueFactory((new PropertyValueFactory<>("pv_st_CPF")));
    	columnNome.setCellValueFactory((new PropertyValueFactory<>("pv_st_Nome")));
    	columnEndereco.setCellValueFactory((new PropertyValueFactory<>("pv_st_Endereco")));
    	columnDataNascimento.setCellValueFactory((new PropertyValueFactory<>("pv_dt_dataNascimento")));
    	columnTelefone.setCellValueFactory((new PropertyValueFactory<>("pv_st_Telefone")));
    	columnLogin.setCellValueFactory((new PropertyValueFactory<>("pv_st_Login")));
    	columnSenha.setCellValueFactory((new PropertyValueFactory<>("pv_st_Senha")));
    	columnIDTipoFuncionario.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdTipoFuncionario")));
    	
    	columnNomeCidade.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
     		 @Override
	        public ObservableValue<String> call(CellDataFeatures<Funcionario, String> f) {
	            return new SimpleStringProperty(f.getValue().cidade.getPv_st_NomeCidade()); 
	        }
    	});
    	
    	columnNomeEstado.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
      		@Override
 	       public ObservableValue<String> call(CellDataFeatures<Funcionario, String> f) {
 	           return new SimpleStringProperty(f.getValue().estado.getPv_st_NomeEstado()); 
 	        }
     });
    	
    	columnSexo.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
   		 @Override //Sobescrita do método call para habilitá-lo a acessar um objeto dentro do Cliente
   	        public ObservableValue<String> call(CellDataFeatures<Funcionario, String> f) {
   	            return new SimpleStringProperty(f.getValue().sexo.getPv_st_nomeSexo()); //Usando SimpleStringProperty do javaFX para acessar um valor String dentro do objeto sexo que está dentro do cliente  
   	        }
   	});
    	
    	columnEstadoCivil.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
      		 @Override
   	        public ObservableValue<String> call(CellDataFeatures<Funcionario, String> f) {
   	            return new SimpleStringProperty(f.getValue().estadoCivil.getPv_st_Descricao()); 
   	        }
       	});
    	
    	columnTipoFuncionario.setCellValueFactory(new Callback<CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
    		@Override
	        public ObservableValue<String> call(CellDataFeatures<Funcionario, String> f) {
	            return new SimpleStringProperty(f.getValue().tipo.getPv_st_Cargo()); 
	        }
   	});
    	
    	idSexo.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdSexo")));
    	idEstadoCivil.setCellValueFactory((new PropertyValueFactory<>("pv_in_EstadoCivil")));
    	idCidade.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdCidade")));
    	idEstado.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdEstado")));
    	
    }
    
    /**
     * Método que limpa a tabela
     */
    void pr_vd_Limpar() {
		tableConsultarFuncionarios.getSelectionModel().select(null);
	}
    
    /**
     * Método que atualiza a tabela
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabela(){
       	
       	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
       	tableConsultarFuncionarios.getItems().setAll(funcionarioDAO.pb_vd_BuscaTodosFuncionarios());
       	pr_vd_Limpar();
       	
   }
    
    /**
     * Método que iniciliaza a tela e carrega a tabela
     */
    @Override
   	public void initialize(URL url, ResourceBundle rb) {
       	
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por parte do nome ou nome completo");
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por CPF");
    	
    	pr_vd_ConfiguraColunas();
       	pr_vd_AtualizaTabela();
       	
       	
       	
     }

}
