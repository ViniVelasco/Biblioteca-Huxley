package controle;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe de mensagens, contendo todas as mensagens est�ticas.
 * @author Vin�cius Velasco
 *
 */
public class Mensagem {
	
	public static void vd_sc_loginVazio(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Campos Vazios no Login");
		alert.setHeaderText(null);
		alert.setContentText("Os campos de usu�rio e senha n�o podem estar vazios.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CidadeAssociada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Esta cidade cont�m associa��es");
		alert.setHeaderText(null);
		alert.setContentText("A cidade cont�m associa��es, n�o poder� ser apagada..");
		alert.showAndWait();
	}
	
	
	public static void vd_sc_loginIncorreto(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Usu�rio ou Senha Incorretos");
		alert.setHeaderText(null);
		alert.setContentText("Seu usu�rio ou sua senha est�o incorretos.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ComboboxNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("H� caixas sem sela��o no cadastro");
		alert.setHeaderText(null);
		alert.setContentText("N�o � poss�vel continuar com o cadastro, todos os itens das caixas devem ser obrigatoriamente selecionados!");
		alert.showAndWait();
	}
	
	public static void vd_sc_FiltragemNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("H� caixas sem sela��o na pesquisa");
		alert.setHeaderText(null);
		alert.setContentText("Voc� deve selecionar um tipo de pesquisa para fazer, j� estamos mostrando todos os resultados.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CamposVazios(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("H� um ou mais campos vazios no cadastro");
		alert.setHeaderText(null);
		alert.setContentText("N�o � poss�vel continuar com o cadastro, n�o s�o permitidos campos vazios");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApenasNumerosLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("H� campos com valores inv�lidos");
		alert.setHeaderText(null);
		alert.setContentText("O campo de quantidade e ISBN podem conter APENAS n�meros! Verifique.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApenasNumerosFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("H� campos com valores inv�lidos");
		alert.setHeaderText(null);
		alert.setContentText("O campo de quantidade pode conter apenas n�meros! Verifique.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ISBNCadastado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ISBN j� cadastrado");
		alert.setHeaderText(null);
		alert.setContentText("Este ISBN j� foi cadastrado no sistema, o cadastro n�o poder� ser continuado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_EstadoAssociadoComCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("O estado est� associado com uma ou mais cidades");
		alert.setHeaderText(null);
		alert.setContentText("Este estado est� associado com uma ou mais cidades e n�o poder� ser deletado..");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastroLivroSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Livro Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O livro foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarFuncionarioSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Funcion�rio Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcion�rio foi alterado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarLivroSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Livro Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O livro foi alterado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarFilmeSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Filme Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O filme foi alterado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarUsuarioSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Usu�rio Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O usu�rio foi alterado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarEstadoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Estado Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O estado foi alterado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApagarEstadoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Estado apagado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O estado foi apagado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApagarSecaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Se��o apagada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A se��o foi apagado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarSecaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Se��o alterar com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A se��o foi alterada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastroFilmeSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Filme Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O filme foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CamposInvalidosUsuario(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Campos Inv�lidos");
		alert.setHeaderText(null);
		alert.setContentText("H� campos que cont�m valores inv�lidos, o campo de CPF e de Telefone pode conter apenas n�meros!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NomeInvalidos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome inv�lido");
		alert.setHeaderText(null);
		alert.setContentText("O nome que voc� digitou � inv�lido, n�o s�o permitidos n�meros nem carecteres especiais!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CPFCadastado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CPF j� cadastrado");
		alert.setHeaderText(null);
		alert.setContentText("Este CPF j� foi cadastrado no sistema, o cadastro n�o poder� ser continuado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastroUsuariosSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cliente Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O cliente foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastroFuncionarioSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Funcion�rio Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcion�rio foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldFuncionario(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o m�ximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("H� um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n CPF: 11 d�gitos \n Endere�o: 80 caracteres \n Telefone: 20 d�gitos \n Usuario: 40 d�gitos \n Senha: 20 D�gitos");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldExemplarLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o m�ximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("H� um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n T�tulo: 50 caracteres \n Quantidade: 11 d�gitos \n Idioma: 35 d�gitos \n ISBN: 13 d�gitos \n Editora: 20 D�gitos \n Autor: 40 caracteres");
		alert.showAndWait();
	}
	
	public static void vd_sc_LinhaNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("N�o h� linha selecionadas");
		alert.setHeaderText(null);
		alert.setContentText("Para alterar ou excluir voc� deve selecionar uma linha da tabela");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldExemplarFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o m�ximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("H� um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n T�tulo: 50 caracteres \n Quantidade: 11 d�gitos \n Idioma: 35 d�gitos \n Nome Diretor: 40 caracteres \n Duracao: 15 caracteres \n Pa�s de Origem: 45 caracteres");
		alert.showAndWait();
	}
	
	public static void vd_sc_TelefoneInv�lido(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Telefone Inv�lido");
		alert.setHeaderText(null);
		alert.setContentText("O telefone s� pode conter n�meros, nenhum outro caractere permitido!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CPFInv�lido(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CPF Inv�lido");
		alert.setHeaderText(null);
		alert.setContentText("O CPF que voc� digitou � inv�lido!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome da cidade excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho m�ximo de um nome de cidade s�o 45 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeEstado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome do estado excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho m�ximo de um nome de estado s�o 45 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeSecao(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome da se��o excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho m�ximo de um nome de se��o s�o 24 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NomeCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("O nome da cidade n�o pode conter n�meros");
		alert.setHeaderText(null);
		alert.setContentText("O nome da cidade n�o pode conter n�meros!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastrarCidadeSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cidade Cadastrada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A cidade foi cadastrada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastrarEstadoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Estado Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O estado foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CadastrarSecaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Se��o Cadastrada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A se��o foi cadastrada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DatasNaoSelecionadas(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("N�o h� datas selecionadas");
		alert.setHeaderText(null);
		alert.setContentText("Favor, selecionar uma data!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataEmprestimoMaior(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de empr�stimo � maior que a data de prazo");
		alert.setHeaderText(null);
		alert.setContentText("A data de empr�stimo est� incorreta, ela n�o pode ser maior que a data do prazo!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DatasIguais(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de empr�stimo igual a data de prazo");
		alert.setHeaderText(null);
		alert.setContentText("A data de empr�stimo n�o pode ser a mesma data do prazo! N�o s�o permitidos empr�stimos de menos de 1 dia!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataPrazoMenorEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de prazo � menor que a data de empr�stimo");
		alert.setHeaderText(null);
		alert.setContentText("A data de prazo n�o pode ser a menor que a date de empr�stimos!");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmprestimoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Emprestimo Realizado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O empr�stimo foi realizado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ExemplaresAcabaramEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sem unidades dispon�veis para empr�stimo");
		alert.setHeaderText(null);
		alert.setContentText("O empr�stimo n�o pode ser realizado, todas as unidades est�o emprestadas!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ExemplaresAcabaramReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sem unidades dispon�veis para reserva");
		alert.setHeaderText(null);
		alert.setContentText("A reserva n�o pode ser realizado, todas as unidades est�o emprestadas!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataEntregaVaziaEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de Entrega Obrigat�ria");
		alert.setHeaderText(null);
		alert.setContentText("Voc� deve selecionar uma data de entrega.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MultaVerificada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("A multa foi verificada, o campo de multa n�o poder� ser vazio.");
		alert.setHeaderText(null);
		alert.setContentText("Caso deseje isentar da multa utilize o zero no campo.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MultaAplicada(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data de prazo excedida");
		alert.setHeaderText(null);
		alert.setContentText("A multa dever� ser applicada!");
		alert.showAndWait();
	}
	
	public static void vd_sc_SemMulta(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Dentro do prazo");
		alert.setHeaderText(null);
		alert.setContentText("N�o h� a necessidade da aplica��o de uma multa.");
		alert.showAndWait();
	}
	
	public static void vd_sc_DevolucaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Devolu��o Realizada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A devolu��o foi realizada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ReservarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Reserva Realizada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A reserva foi realizada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LivroApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Livro apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O livro foi apagado com sucesso!");
		alert.showAndWait();
	}
	public static void vd_sc_AlterarCidadeSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cidade alterada com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A cidade foi alterada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CidadeApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cidade apagada com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A cidade foi apagada com sucesso!");
		alert.showAndWait();
	}
	public static void vd_sc_FilmeApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Filme apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O filme foi apagado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LivroAssociadoEmprestimos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Empr�stimos realizados com este livro");
		alert.setHeaderText(null);
		alert.setContentText("O livro est� associado a um ou mais empr�stimos, n�o poder� ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UsuarioAssociadoEmprestimos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Us�rio realizou empr�stimos");
		alert.setHeaderText(null);
		alert.setContentText("O usu�rio est� associado a um ou mais empr�stimos, n�o poder� ser apagado nem ter seu CPF modificado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_SecaoAssociadoLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Se��o associada Livro");
		alert.setHeaderText(null);
		alert.setContentText("Esta se��o est� associada com um livro e n�o poder� ser apagada.");
		alert.showAndWait();
	}
	
	public static void vd_sc_SecaoAssociadoFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Se��o associada filme");
		alert.setHeaderText(null);
		alert.setContentText("Esta se��o est� associada com um filme e n�o poder� ser apagada.");
		alert.showAndWait();
	}
	
	public static void vd_sc_LivroAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reservas realizadas com este livro");
		alert.setHeaderText(null);
		alert.setContentText("O livro est� associado a um ou mais reservas, n�o poder� ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FuncionarioAssociadoEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Funcion�rio realizou empr�stimos");
		alert.setHeaderText(null);
		alert.setContentText("Este funcion�rio realizou um ou mais empr�stimos, n�o poder� ser apagado nem ter seu CPF modificado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UsuarioAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Usu�rio realizou reservas");
		alert.setHeaderText(null);
		alert.setContentText("O usu�rio est� associado a um ou mais reservas, n�o poder� ser apagado nem ter seu CPF modificado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CancelarReservarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Reserva cancelada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A reserva foi cancelada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_UsuarioApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Usu�rio apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O usu�rio foi apagado com sucesso.");
		alert.showAndWait();
	}
	
	
	
	public static void vd_sc_FuncionarioApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Funcion�rio apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcion�rio foi apagado com sucesso.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FilmeAssociadoEmprestimos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Empr�stimos realizados com este filme");
		alert.setHeaderText(null);
		alert.setContentText("O filme est� associado a um ou mais empr�stimos, n�o poder� ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FilmeAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reservas realizadas com este filme");
		alert.setHeaderText(null);
		alert.setContentText("O filme est� associado a um ou mais reservas, n�o poder� ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ConsultaLivrosFiltragemVazia(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("A se��o n�o foi selecionada");
		alert.setHeaderText(null);
		alert.setContentText("A se��o n�o foi selecionada, j� estamos exibindo todos os livros cadastrados, caso queira buscar por se��o espec�fica favor selecionar a filtragem.");
		alert.showAndWait();
	}
	
	public static boolean pb_bo_sc_AvisoApagandoDado(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Aten��o, voc� est� prestes a deletar um dado permanentemente");
		alert.setHeaderText(null);
		alert.setContentText("Voc� deseja realmente deletar? Esta � uma a��o permanente.");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		   return false;
		}
		
	}

	
}
