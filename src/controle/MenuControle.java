package controle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Funcionario;
import modelo.FuncionarioDAO;
import net.sf.jasperreports.engine.JRException;
import relatorios.RelatorioDeFilmes;
import relatorios.RelatorioDeLivros;

/**
 * Classe que controla a tela Menu Controle
 * @author Vinícius Velasco
 *
 */
public class MenuControle {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuLogin;

    @FXML
    private MenuItem menuItemLoginCliente;

    @FXML
    private MenuItem menuItemLoginFuncionario;

    @FXML
    private Menu menuCadastros;

    @FXML
    private MenuItem menuCadastrarLivro;

    @FXML
    private MenuItem menuCadastrarFilme;

    @FXML
    private MenuItem menuCadastrarUsuarios;

    @FXML
    private MenuItem menuCadastrarFuncionarios;

    @FXML
    private Menu menuEmprestimos;

    @FXML
    private MenuItem menuEfetuarEmprestimo;

    @FXML
    private MenuItem menuEfetuarDevolucao;

    @FXML
    private Menu menuReservas;

    @FXML
    private MenuItem menuReservar;

    @FXML
    private MenuItem menuCancelarReserva;

    @FXML
    private MenuItem menuEfetuarReserva;
    
    @FXML
    private MenuItem menuGerarRelatorioFilmes;

    @FXML
    private Menu menuRelatorios;

    @FXML
    private MenuItem menuGerarRelatorioEmprestimos;

    @FXML
    private MenuItem menuGerarRelatorioLivros;

    @FXML
    private Menu menuConsultas;


    @FXML
    private MenuItem menuConsultarLivros;

    @FXML
    private MenuItem menuConsultarFilmes;

    @FXML
    private MenuItem menuConsultarFuncionarios;

    @FXML
    private MenuItem menuConsultarEmprestimos;

    @FXML
    private MenuItem menuConsultarReservas;

    @FXML
    private MenuItem menuConsultarUsuarios;

    @FXML
    private Menu menuTraduzir;
    
    @FXML
    private Menu menuItemSair;

    @FXML
    private MenuItem menuTraduzirIngles;

    @FXML
    private MenuItem menuSair;
    
    @FXML
    private AnchorPane anchorInterno;
    
    @FXML
    private MenuItem menuCadastrarSecao;
    
    @FXML
    private MenuItem menuCadastrarCidade;

    @FXML
    private MenuItem menuCadastrarEstado;
    
    @FXML
    private MenuItem menuConsultarCidades;

    @FXML
    private MenuItem menuConsultarEstados;
    
    @FXML
    private Label lblUsuario;

    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private Label lblSenha;
    
    @FXML
    private MenuItem menuConsultarSecoes;

    @FXML
    private Button btnLoginCliente;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView imgFuncionario;

    @FXML
    private Button btnLoginFuncionario;

    @FXML
    private ImageView imgCliente;
    
    @FXML
    private Label lblLogado;

    @FXML
    private Label lblCPF;
    
    boolean pc_bo_VerificadorTraducao = false;

    /**
     * Método que realiza login do Cliente
     * @param event
     */
    @FXML
    void btnLoginClienteClick(ActionEvent event) {
    	
    	if(textFieldUsuario.getText().isEmpty() || password.getText().isEmpty()){
    		
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_emptyLogin();
    		} else {
    			Mensagem.vd_sc_loginVazio();
    		}
    		
    	} else {
    		
    		String pr_st_Usario = textFieldUsuario.getText();
    		String pr_st_Senha = password.getText();
    		
    		Cliente cliente = new Cliente();
    		cliente.setPv_st_Login(pr_st_Usario);
    		cliente.setPv_st_Senha(pr_st_Senha);
    		
    		ClienteDAO clienteDAO = new ClienteDAO();
    		
    		if(clienteDAO.verificaLogin(cliente)){
    			menuLogin.setVisible(false);
    			
    			menuReservas.setVisible(true);
    			menuEfetuarReserva.setVisible(false);
    			pr_vd_LoginClienteSucesso();
				
				pr_vd_LoginEfetuadoCliente(cliente);
    		} else {
    			if(pc_bo_VerificadorTraducao){
        			Mensagens_EN_US.vd_sc_wrongLogin();
        		} else {
    			Mensagem.vd_sc_loginIncorreto();
        		}
    		}
    		
    	}
    	
    }

    /**
     * Método de login do funcionário
     * @param event
     */
    @FXML
    void btnLoginFuncionarioClick(ActionEvent event) {
    	
    	if(textFieldUsuario.getText().isEmpty() || password.getText().isEmpty()){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_emptyLogin();
    		} else {
    			Mensagem.vd_sc_loginVazio();
    		}
    	} else {
    		
    		String pr_st_Usario = textFieldUsuario.getText();
    		String pr_st_Senha = password.getText();
    		
    		Funcionario funcionario = new Funcionario();
    		funcionario.setPv_st_Senha(pr_st_Senha);
    		funcionario.setPv_st_Login(pr_st_Usario);
    		
    		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    		
    		if(funcionarioDAO.verificaLogin(funcionario)){
    			menuLogin.setVisible(false);
    			if(funcionario.getPv_in_IdTipoFuncionario() == 1){
    				
    				menuCadastros.setVisible(true);
    				menuEmprestimos.setVisible(true);
    				menuReservas.setVisible(true);
    				menuConsultas.setVisible(true);
    				menuCadastrarFuncionarios.setVisible(false);
    				menuConsultarFuncionarios.setVisible(false);
    				menuRelatorios.setVisible(true);
    				menuReservar.setVisible(false);
    				menuCancelarReserva.setVisible(false);
    				
    				
    				pr_vd_LoginFuncionarioSucesso();
    				
    				pr_vd_LoginEfetuado(funcionario);
    			} else if(funcionario.getPv_in_IdTipoFuncionario() == 2){
    				
    				menuCadastros.setVisible(true);
    				menuCadastrarLivro.setVisible(false);
    				menuCadastrarFilme.setVisible(false);
    				menuCadastrarFuncionarios.setVisible(false);
    				menuCadastrarSecao.setVisible(false);
    				menuCadastrarCidade.setVisible(false);
    				menuCadastrarEstado.setVisible(false);
    				menuEmprestimos.setVisible(true);
    				menuReservas.setVisible(true);
    				menuConsultas.setVisible(true);
    				menuConsultarFuncionarios.setVisible(false);
    				menuReservar.setVisible(false);
    				menuCancelarReserva.setVisible(false);
    				
    				pr_vd_LoginFuncionarioSucesso();
    				
    				pr_vd_LoginEfetuado(funcionario);
    				
    			} else if(funcionario.getPv_in_IdTipoFuncionario() == 3){
    				
    				menuCadastros.setVisible(true);
    				menuCadastrarLivro.setVisible(false);
    				menuCadastrarFilme.setVisible(false);
    				menuConsultas.setVisible(true);
    				menuConsultarReservas.setVisible(false);
    				menuConsultarEmprestimos.setVisible(false);
    				menuConsultarLivros.setVisible(false);
    				menuConsultarFilmes.setVisible(false);
    				
    				pr_vd_LoginFuncionarioSucesso();
    				pr_vd_LoginEfetuado(funcionario);
    				
    			}
    			
    		} else {
    			if(pc_bo_VerificadorTraducao){
        			Mensagens_EN_US.vd_sc_wrongLogin();
        		} else {
    			Mensagem.vd_sc_loginIncorreto();
        		}
    		}
    		
    		
    		
    	}
    	
    }
    
    /*
     * Método que configura login do cliente
     */
    void pr_vd_LoginEfetuadoCliente(Cliente cliente){
    	lblLogado.setText("Bem-Vindo(a) " + cliente.getPv_st_Nome());
    	lblCPF.setText("Você está logado(a) com o CPF: " + cliente.getPv_st_CPF());
    	Cliente.setPv_st_sc_CPF_Logado(cliente.getPv_st_CPF());
    }
    
    /**
     * Método que controla login do funcionário
     * @param funcionario
     */
    void pr_vd_LoginEfetuado(Funcionario funcionario){
    	lblLogado.setText("Bem-Vindo(a) " + funcionario.getPv_st_Nome());
    	lblCPF.setText("Você está logado(a) com o CPF: " + funcionario.getPv_st_CPF());
    	Funcionario.setPv_st_sc_CPF_Logado(funcionario.getPv_st_CPF());
    }
    
    /*
     * Método que controla login do cliente
     */
    void pr_vd_LoginClienteSucesso(){
    	lblUsuario.setVisible(false);
    	textFieldUsuario.setVisible(false);
    	lblSenha.setVisible(false);
    	password.setVisible(false);
    	imgCliente.setVisible(false);
    	btnLoginCliente.setVisible(false);
    }
    
    /*
     * Método que controla login do funcionário
     */
    void pr_vd_LoginFuncionarioSucesso(){
    	lblUsuario.setVisible(false);
    	textFieldUsuario.setVisible(false);
    	lblSenha.setVisible(false);
    	password.setVisible(false);
    	imgFuncionario.setVisible(false);
    	btnLoginFuncionario.setVisible(false);
    }
    
    /**
     * Método que chama a tela de Consultar Estados
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarEstadosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarEstado.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }
    
    /**
     * Método que chama a tela de Consultar Cidades
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarCidadesClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarCidade.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Cadastrar Cidade
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarCidadeClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastrarCidade.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);
    }

    /**
     * Método que chama a tela de Cadastrar Estados
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarEstadoClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastrarEstado.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }
    
    /**
     * Método que chama a tela de Cadastrar Seção
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarSecaoClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastrarSecao.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Cadastrar Livros
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarLivroClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastroExemplarLivro.fxml"));
    	a.setLayoutX(250);
    	a.setLayoutY(-20);
        anchorInterno.getChildren().setAll(a);
    }
    
    /**
     * Método que chama a tela de Cadastrar Filme
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarFilmeClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastroExemplarFilme.fxml"));
    	a.setLayoutX(250);
    	a.setLayoutY(-20);
        anchorInterno.getChildren().setAll(a);
    }


    /**
     * Método que chama a tela de Cadastrar Funcionário
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarFuncionariosClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastroFuncionario.fxml"));
    	a.setLayoutX(250);
    	a.setLayoutY(-20);
        anchorInterno.getChildren().setAll(a);
    }

    /**
     * Método que chama a tela de Cadastrar Usuário
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCadastrarUsuariosClick(ActionEvent event) throws IOException {
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/cadastrarUsuarios.fxml"));
    	a.setLayoutX(250);
    	a.setLayoutY(-20);
        anchorInterno.getChildren().setAll(a);
    }

    /**
     * Método que chama a tela de Efetuar Reserva
     * @param event
     * @throws IOException
     */
    @FXML
    void menuEfetuarReservaClick(ActionEvent event) throws IOException {
    	
     	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/EfetuarReserva.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);
    	
    
    }
    
    /**
     * Método que chama a tela de Cancelar Reserva
     * @param event
     * @throws IOException
     */
    @FXML
    void menuCancelarReservaClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/CancelarReserva.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Consultar Livros
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarLivrosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarLivros.fxml"));
    	a.setLayoutX(220);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }
    
    /**
     * Método que chama a tela de Consultar Filmes
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarFilmesClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarFilmes.fxml"));
    	a.setLayoutX(220);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Consultar Empréstimos
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarEmprestimosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarEmprestimos.fxml"));
    	a.setLayoutX(150);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Consultar Funcionários
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarFuncionariosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarFuncionario.fxml"));
    	a.setLayoutX(10);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Consultar Reservas
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarReservasClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/ConsultarReserva.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);


    }

    /**
     * Método que chama a tela de Consultar Usuários
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarUsuariosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarUsuarios.fxml"));
    	a.setLayoutX(10);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Efetuar Devolução
     * @param event
     * @throws IOException
     */
    @FXML
    void menuEfetuarDevolucaoClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/devolucao.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Efetuar Empréstimo
     * @param event
     * @throws IOException
     */
    @FXML
    void menuEfetuarEmprestimoClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/emprestimo.fxml"));
    	a.setLayoutX(275);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Gerar Relatório de Empréstimo
     * @param event
     * @throws IOException
     */
    @FXML
    void menuGerarRelatorioEmprestimosClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/RelatorioEmprestimo.fxml"));
    	a.setLayoutX(275);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }

    /**
     * Método que chama a tela de Login Cliente
     * @param event
     */
    @FXML
    void menuLoginClienteClick(ActionEvent event) {
    	imgFuncionario.setVisible(false);
    	btnLoginFuncionario.setVisible(false);
    	lblUsuario.setVisible(true);
    	textFieldUsuario.setVisible(true);
    	lblSenha.setVisible(true);
    	password.setVisible(true);
    	imgCliente.setVisible(true);
    	btnLoginCliente.setVisible(true);
    }

    /**
     * Método que chama a tela de Login Funcionário
     * @param event
     */
    @FXML
    void menuLoginFuncionarioClick(ActionEvent event) {
    	imgCliente.setVisible(false);
    	btnLoginCliente.setVisible(false);
    	lblUsuario.setVisible(true);
    	textFieldUsuario.setVisible(true);
    	lblSenha.setVisible(true);
    	password.setVisible(true);
    	imgFuncionario.setVisible(true);
    	btnLoginFuncionario.setVisible(true);
    }
    
    /**
     * Método que chama a tela de Gerar Relatório de Livros
     * @param event
     * @throws JRException
     */
    @FXML
    void menuGerarRelatorioLivrosClick(ActionEvent event) throws JRException {
    	
    	RelatorioDeLivros relatorio = new RelatorioDeLivros();
    	relatorio.pb_vd_sc_GerarRelatorioDeLivros();

    }

    /**
     * Método que chama a tela de Reservar
     * @param event
     * @throws IOException
     */
    @FXML
    void menuReservarClick(ActionEvent event) throws IOException {
    	
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/Reservar.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);
   


    }

    /**
     * Método que fecha o programa
     * @param event
     */
    @FXML
    void menuSairClick(ActionEvent event) {
    	
    	System.exit(0);

    }

    /**
     * Método que traduz o menu
     * @param event
     */
    @FXML
    void menuTraduzirInglesClick(ActionEvent event) {
    	menuTraduzir.setVisible(false);
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	lblUsuario.setText(bundle.getString("Usuário"));
    	lblSenha.setText(bundle.getString("Senha"));
    	textFieldUsuario.setPromptText(bundle.getString("PromptUsuario"));
    	password.setPromptText(bundle.getString("PromptSenha"));
    	menuItemLoginCliente.setText(bundle.getString("LoginCliente"));
    	menuItemLoginFuncionario.setText(bundle.getString("LoginFuncionário"));
    	menuCadastros.setText(bundle.getString("Cadastros"));
    	menuCadastrarLivro.setText(bundle.getString("CadastroLivro"));
    	menuCadastrarFilme.setText(bundle.getString("CadastroFilme"));
    	menuCadastrarUsuarios.setText(bundle.getString("CadastroUsuario"));
    	menuCadastrarFuncionarios.setText(bundle.getString("CadastroFuncionários"));
    	menuCadastrarSecao.setText(bundle.getString("CadastroSecao"));
    	menuCadastrarCidade.setText(bundle.getString("CadastroCidade"));
    	menuCadastrarEstado.setText(bundle.getString("CadastroEstado"));
    	menuEmprestimos.setText(bundle.getString("Emprestimos"));
    	menuEfetuarEmprestimo.setText(bundle.getString("Emprestimo"));
    	menuEfetuarDevolucao.setText(bundle.getString("Devolução"));
    	menuReservas.setText(bundle.getString("Reservas"));
    	menuCancelarReserva.setText(bundle.getString("CancelarReserva"));
    	menuEfetuarReserva.setText(bundle.getString("Reservar"));
    	menuRelatorios.setText(bundle.getString("Relatórios"));
    	menuGerarRelatorioEmprestimos.setText(bundle.getString("RelatorioEmprestimos"));
    	menuGerarRelatorioLivros.setText(bundle.getString("RelatorioLivros"));
    	menuGerarRelatorioFilmes.setText(bundle.getString("RelatorioFilmes"));
    	menuConsultas.setText(bundle.getString("Consultas"));
    	menuConsultarLivros.setText(bundle.getString("ConsultarLivros"));
    	menuConsultarFilmes.setText(bundle.getString("ConsultarFilmes"));
    	menuConsultarFuncionarios.setText(bundle.getString("ConsultarFuncionarios"));
    	menuConsultarEmprestimos.setText(bundle.getString("ConsultarEmprestimos"));
    	menuConsultarUsuarios.setText(bundle.getString("ConsultarUsuarios"));
    	menuConsultarCidades.setText(bundle.getString("ConsultarCidades"));
    	menuConsultarReservas.setText(bundle.getString("ConsultarReservas"));
    	menuConsultarEstados.setText(bundle.getString("ConsultarEstados"));
    	menuConsultarSecoes.setText(bundle.getString("ConsultarSeção"));
    	menuItemSair.setText(bundle.getString("Sair"));
    	menuSair.setText(bundle.getString("Sair"));
    	
    	pc_bo_VerificadorTraducao = true;
    	
    }
    
    /**
     * Método que chama a tela de Consultar Seções
     * @param event
     * @throws IOException
     */
    @FXML
    void menuConsultarSecoesClick(ActionEvent event) throws IOException {
    	
    	AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/visao/consultarSecao.fxml"));
    	a.setLayoutX(300);
    	a.setLayoutY(0);
        anchorInterno.getChildren().setAll(a);

    }
    

    /**
     * Método que chama a tela de Gerar Relatórios de Filmes
     * @param event
     * @throws JRException
     */
    @FXML
    void menuGerarRelatorioFilmesClick(ActionEvent event) throws JRException {
    	
    	RelatorioDeFilmes relatorio = new RelatorioDeFilmes();
    	relatorio.pb_vd_sc_GerarRelatorioDeFilmes();
    	

    }

}
