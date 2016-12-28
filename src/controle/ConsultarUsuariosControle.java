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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 * Classe controladora da tela consultar usu�rios
 * @author Vin�cius Velasco
 *
 */
public class ConsultarUsuariosControle implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Cliente> tableConsultarUsuarios;

    @FXML
    private TableColumn<Cliente, String> columnCPF;

    @FXML
    private TableColumn<Cliente, String> columnNome;

    @FXML
    private TableColumn<Cliente, String> columnEndereco;

    @FXML
    private TableColumn<Cliente, Date> columnDataNascimento;

    @FXML
    private TableColumn<Cliente, Integer> columnTipoCliente;

    @FXML
    private TableColumn<Cliente, String> columnTelefone;

    @FXML
    private TableColumn<Cliente, String> columnLogin;

    @FXML
    private TableColumn<Cliente, String> columnSenha;

    @FXML
    private TableColumn<Cliente, String> columnNomeCidade;

    @FXML
    private TableColumn<Cliente, String> columnNomeEstado;

    @FXML
    private TableColumn<Cliente, String> columnSexo;

    @FXML
    private TableColumn<Cliente, String> columnEstadoCivil;

    @FXML
    private TableColumn<Cliente, Integer> idSexo;

    @FXML
    private TableColumn<Cliente, Integer> idEstadoCivil;

    @FXML
    private TableColumn<Cliente, Integer> idEstado;

    @FXML
    private TableColumn<Cliente, Integer> idCidade;
    
    @FXML
    private TableColumn<Cliente, String> columnTipoClienteNome;

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
    private Text lblPesquisa;
    
    @FXML
    private TextField textPesquisa;
    
    @FXML
    private Button btnTraduzir;
    
    public static ObservableList<Cliente> usuarioSelecionado;
    
    boolean pc_bo_VerificadorTraducao = false;
    
    /**
     * M�todo que traduz a tela
     * @param event
     */
    @FXML
    void btnTraduzirClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	comboBoxTipoPesquisa.getItems().clear();
    	comboBoxTipoPesquisa.setPromptText(bundle.getString("SelecionePesquisa"));
    	btnAlterar.setText(bundle.getString("Alterar"));
    	btnDeletar.setText(bundle.getString("Deletar"));
    	btnPesquisar.setText(bundle.getString("Pesquisar"));
    	columnNome.setText(bundle.getString("Nome"));
    	columnEndereco.setText(bundle.getString("Endere�o"));
    	lblFiltragem.setText(bundle.getString("SelecioneSecao"));
    	columnDataNascimento.setText(bundle.getString("DataNascimento"));
    	columnTelefone.setText(bundle.getString("Telefone"));
    	columnSenha.setText(bundle.getString("Senha"));
    	columnNomeCidade.setText(bundle.getString("NomeDaCidade"));
    	columnNomeEstado.setText(bundle.getString("NomeDoEstado"));
    	columnSexo.setText(bundle.getString("Sexo"));
    	columnEstadoCivil.setText(bundle.getString("EstadoCivil"));
    	columnTipoClienteNome.setText(bundle.getString("TipoCliente"));
    	comboBoxTipoPesquisa.getItems().addAll(bundle.getString("PesquisarNome"), bundle.getString("PesquisarCPF"));
    	pc_bo_VerificadorTraducao = true;

    }

    /**
     * M�todo que chama a tela de alterar usu�rios
     * @param event
     */
    @FXML
    void btnAlterarClick(ActionEvent event) {

    	if (tableConsultarUsuarios.getSelectionModel().getSelectedItems().isEmpty()) {
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
			Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
			
		} else {
			
			usuarioSelecionado = tableConsultarUsuarios.getSelectionModel().getSelectedItems();
			
			Stage palco = new Stage();
			try {
				URL arquivoFXML = getClass().getResource("/visao/alterarUsuarios.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				AlterarUsuariosControle.pb_sg_sc_AlterarUsuarios = palco;
				palco.initStyle(StageStyle.UNDECORATED);
				palco.setScene(new Scene(fxmlParent));
				palco.setTitle("Alterar Usu�rios");
				palco.showAndWait();

				pr_vd_AtualizaTabela();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    	
    }

    /**
     * M�todo que deleta um usu�rio
     * @param event
     */
    @FXML
    void btnDeletar(ActionEvent event) {
    	
    	if(tableConsultarUsuarios.getSelectionModel().getSelectedItems().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_LineNotSelected();
    		} else {
    		Mensagem.vd_sc_LinhaNaoSelecionada();
    		}
    		
    	} else {
    		
    		usuarioSelecionado = tableConsultarUsuarios.getSelectionModel().getSelectedItems();
    		Cliente cliente = new Cliente();
    		ClienteDAO clienteDAO = new ClienteDAO();
    		cliente.setPv_st_CPF(usuarioSelecionado.get(0).getPv_st_CPF());
    		
    		if (Mensagem.pb_bo_sc_AvisoApagandoDado()) {
    			
    			if(clienteDAO.pb_bo_VerificaEmprestimosUsuario(cliente)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_UserAssociedWithLoans();
    	    		} else {
    				Mensagem.vd_sc_UsuarioAssociadoEmprestimos();
    	    		}
    				
    			} else if (clienteDAO.pb_bo_VerificaReservasCliente(cliente)){
    				if(pc_bo_VerificadorTraducao){
    	    			Mensagens_EN_US.vd_sc_UserAssociedWithReserves();
    	    		} else {
    				Mensagem.vd_sc_UsuarioAssociadoReserva();
    	    		}
    			} else {
    				
    				clienteDAO.pb_vd_DeleteClientes(cliente);
    				
    				pr_vd_Limpar();
					pr_vd_configuraColunas();
					pr_vd_AtualizaTabela();
					
					if(pc_bo_VerificadorTraducao){
		    			Mensagens_EN_US.vd_sc_DeleteUserSucess();
		    		} else {
					Mensagem.vd_sc_UsuarioApagarSucesso();
		    		}
    				
    			}
    		}
    		
    	}

    }

    /**
     * M�todo que pesquisa por filtragem
     * @param event
     */
    @FXML
    void btnPesquisarClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_FilterNotSelected();
    		} else {
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    		}
    	} else {
    		
    		if(comboBoxTipoPesquisa.getValue() == "Pesquisar por parte do nome ou nome completo" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarNome")){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_configuraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    				
    					if(ValidacoesdeNegocio.ChecarNome(textPesquisa.getText()) == false){
    						if(pc_bo_VerificadorTraducao){
    			    			Mensagens_EN_US.vd_sc_InvalidNames();
    			    		} else {
    						Mensagem.vd_sc_NomeInvalidos();
    			    		}
    						
    					} else {
    					pr_vd_configuraColunas();
    					pr_vd_AtualizaTabelaPesquisaPorNome(textPesquisa.getText());
    					}
    					
    			}
    			
    		} else {
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pr_vd_configuraColunas();
					pr_vd_AtualizaTabela();
    				
    			} else {
    					if(ValidacoesdeNegocio.pb_bo_static_CPFValido(textPesquisa.getText()) == false){
    						if(pc_bo_VerificadorTraducao){
    			    			Mensagens_EN_US.vd_sc_WrongCPF();
    			    		} else {
    						Mensagem.vd_sc_CPFInv�lido();
    			    		}
    						
    					} else{
    						pr_vd_configuraColunas();
    						pr_vd_AtualizaTabelaPesquisaPorCPF(textPesquisa.getText());
    					}
    			}
    			
    		}
    		
    	}

    }
    
    /**
     * M�todo que configura colunas
     */
    void pr_vd_configuraColunas() {
    	
    	columnCPF.setCellValueFactory((new PropertyValueFactory<>("pv_st_CPF")));
    	columnNome.setCellValueFactory((new PropertyValueFactory<>("pv_st_Nome")));
    	columnEndereco.setCellValueFactory((new PropertyValueFactory<>("pv_st_Endereco")));
    	columnDataNascimento.setCellValueFactory((new PropertyValueFactory<>("pv_dt_DataNascimentoSQL")));
    	columnTipoCliente.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdTipoCliente")));
    	columnTelefone.setCellValueFactory((new PropertyValueFactory<>("pv_st_Telefone")));
    	columnLogin.setCellValueFactory((new PropertyValueFactory<>("pv_st_Login")));
    	columnSenha.setCellValueFactory((new PropertyValueFactory<>("pv_st_Senha")));
    	
    	columnNomeCidade.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
      		 @Override
 	        public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
 	            return new SimpleStringProperty(c.getValue().cidade.getPv_st_NomeCidade()); 
 	        }
     	});
    	
    	columnNomeEstado.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
      		@Override
 	       public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
 	           return new SimpleStringProperty(c.getValue().estado.getPv_st_NomeEstado()); 
 	        }
     });
    	
    	columnSexo.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
    		 @Override //Sobescrita do m�todo call para habilit�-lo a acessar um objeto dentro do Cliente
    	        public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
    	            return new SimpleStringProperty(c.getValue().sexo.getPv_st_nomeSexo()); //Usando SimpleStringProperty do javaFX para acessar um valor String dentro do objeto sexo que est� dentro do cliente  
    	        }
    	});
    	
    	columnEstadoCivil.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
   		 @Override
	        public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
	            return new SimpleStringProperty(c.getValue().estadoCivil.getPv_st_Descricao()); 
	        }
    	});
    	
    	columnTipoClienteNome.setCellValueFactory(new Callback<CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
     		 @Override
	        public ObservableValue<String> call(CellDataFeatures<Cliente, String> c) {
	            return new SimpleStringProperty(c.getValue().tipo.getPv_st_Descricao()); 
	        }
    	});
    	
    	idSexo.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdSexo")));
    	idEstadoCivil.setCellValueFactory((new PropertyValueFactory<>("pv_in_EstadoCivil")));
    	idCidade.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdCidade")));
    	idEstado.setCellValueFactory((new PropertyValueFactory<>("pv_in_IdEstado")));
    	
    	//columnSexo.setCellValueFactory((new PropertyValueFactory<>("pv_st_DescricaoSexo")));
    	
    }
    
    /**
     * M�todo que limpa a tabela
     */
    void pr_vd_Limpar() {
		tableConsultarUsuarios.getSelectionModel().select(null);
	}
    
    /**
     * M�todo que inicializa a tela e carrega a tabela
     */
    @Override
   	public void initialize(URL url, ResourceBundle rb) {
       	
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por parte do nome ou nome completo");
    	comboBoxTipoPesquisa.getItems().add("Pesquisar por CPF");
    	
       	pr_vd_configuraColunas();
       	pr_vd_AtualizaTabela();
       	
       	
       	
     }
    
    /**
     * M�todo que atualiza a tabela
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabela(){
       	
       	ClienteDAO clienteDAO = new ClienteDAO();
       	tableConsultarUsuarios.getItems().setAll(clienteDAO.pb_vd_BuscaTodosClientes());
       	
       	pr_vd_Limpar();
       	
   }
    
    /**
     * M�todo que controla o tipo de pesquisa
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
     * M�todo que atualiza a tabela por nome
     * @param nome
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorNome(String nome){
       	
       	Cliente cliente = new Cliente();
       	cliente.setPv_st_Nome(nome);
       	ClienteDAO clienteDAO = new ClienteDAO();
       	tableConsultarUsuarios.getItems().setAll(clienteDAO.pb_vd_BuscaTodosClientesPorNome(cliente));
       	
       	pr_vd_Limpar();

       }
    
    /**
     * M�todo atualiza a tabela por CPF.
     * @param CPF
     */
    @SuppressWarnings("unchecked")
   	void pr_vd_AtualizaTabelaPesquisaPorCPF(String CPF){
       	
       	Cliente cliente = new Cliente();
       	cliente.setPv_st_CPF(CPF);
       	ClienteDAO clienteDAO = new ClienteDAO();
       	tableConsultarUsuarios.getItems().setAll(clienteDAO.pb_vd_BuscaTodosClientesPorCPF(cliente));
       	
       	pr_vd_Limpar();

       }
    
}
