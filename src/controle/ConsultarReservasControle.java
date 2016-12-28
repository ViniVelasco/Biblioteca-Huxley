package controle;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Reserva;
import modelo.ReservaDAO;

/**
 * Classe que controla a tela de Consultar Reservas
 * @author Vinícius velasco
 *
 */
public class ConsultarReservasControle {

    @FXML
    private ListView<Reserva> listReservas;

    @FXML
    private ComboBox<String> comboBoxTipoPesquisa;

    @FXML
    private TextField textPesquisa;

    @FXML
    private Label lblPesquisa;

    @FXML
    private Button btnPesquisar;
    
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
    	comboBoxTipoPesquisa.getItems().addAll(bundle.getString("PesquisarReservasPorCPF"), bundle.getString("PesquisarPorNomeExemplar"));

    	pc_bo_VerificadorTraducao = true;
    }

    /**
     * Método que pesquisa por filtragem
     * @param event
     */
    @SuppressWarnings("unchecked")
	@FXML
    void btnPesquisarClick(ActionEvent event) {
    	
    	Locale enUS = new Locale("en","US");
    	ResourceBundle bundle = ResourceBundle.getBundle("controle.message", enUS);
    	
    	if(comboBoxTipoPesquisa.getValue() == null){
    		if(pc_bo_VerificadorTraducao){
    			Mensagens_EN_US.vd_sc_ConsultBooksFiltersEmpty();
    		} else {
    		Mensagem.vd_sc_FiltragemNaoSelecionada();
    		}
    	} else {
    		
    		if(comboBoxTipoPesquisa.getValue() == "Pesquizar Por Nome do Exemplar" || comboBoxTipoPesquisa.getValue() == bundle.getString("PesquisarPorNomeExemplar")){
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pb_vd_Pesquisar();
    				
    			} else {
    				
    				pb_vd_PesquisaPorNomeExemplar(textPesquisa.getText());

    					
    			}
    			
    		} else {
    			
    			if(textPesquisa.getText().isEmpty()){
    				
    				pb_vd_Pesquisar();
    				
    			} else {
    					if(ValidacoesdeNegocio.pb_bo_static_CPFValido(textPesquisa.getText()) == false){
    						if(pc_bo_VerificadorTraducao){
    			    			Mensagens_EN_US.vd_sc_WrongCPF();
    			    		} else {
    						Mensagem.vd_sc_CPFInválido();
    			    		}
    						
    					} else{
    						pb_vd_PesquisaPorCPFCliente(textPesquisa.getText());
    					}
    			}
    			
    		}
    		
    	}
    
}
    	
    	

    
    /**
     * Método que inicializa a tela e carrega a tabela
     */
    @FXML
    void initialize(){
    	
    	pb_vd_Pesquisar();
    	
    	comboBoxTipoPesquisa.getItems().addAll("Pesquisar Reservas por CPF", "Pesquizar Por Nome do Exemplar");
    	
    }
    
    /**
     * Método que pesquisa as reservas
     */
    public void pb_vd_Pesquisar(){
    	
    	listReservas.getItems().clear();
    	ReservaDAO reservaDAO = new ReservaDAO();
    	listReservas.getItems().addAll(reservaDAO.pb_ar_BuscarReservas());
    	
    }
    /**
     * Método que pesquisa as reservas por nome
     * @param nome
     */
    public void pb_vd_PesquisaPorNomeExemplar(String nome){
    	
    	listReservas.getItems().clear();
    	Reserva reserva = new Reserva();
    	reserva.setPv_st_NomeExemplar(nome);
    	ReservaDAO reservaDAO = new ReservaDAO();
    	listReservas.getItems().addAll(reservaDAO.pb_ar_BuscarReservasPorParteNome(reserva));
    	
    }
    
    /**
     * Método que pesquisa as reservas por CPF
     * @param CPF
     */
    public void pb_vd_PesquisaPorCPFCliente(String CPF){
    	
    	listReservas.getItems().clear();
    	Reserva reserva = new Reserva();
    	reserva.setPv_st_CPF(CPF);
    	ReservaDAO reservaDAO = new ReservaDAO();
    	listReservas.getItems().addAll(reservaDAO.pb_ar_BuscarReservasPorParteCPF(reserva));
    	
    }

}
