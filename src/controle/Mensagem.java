package controle;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe de mensagens, contendo todas as mensagens estáticas.
 * @author Vinícius Velasco
 *
 */
public class Mensagem {
	
	public static void vd_sc_loginVazio(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Campos Vazios no Login");
		alert.setHeaderText(null);
		alert.setContentText("Os campos de usuário e senha não podem estar vazios.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CidadeAssociada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Esta cidade contém associações");
		alert.setHeaderText(null);
		alert.setContentText("A cidade contém associações, não poderá ser apagada..");
		alert.showAndWait();
	}
	
	
	public static void vd_sc_loginIncorreto(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Usuário ou Senha Incorretos");
		alert.setHeaderText(null);
		alert.setContentText("Seu usuário ou sua senha estão incorretos.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ComboboxNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Há caixas sem selação no cadastro");
		alert.setHeaderText(null);
		alert.setContentText("Não é possível continuar com o cadastro, todos os itens das caixas devem ser obrigatoriamente selecionados!");
		alert.showAndWait();
	}
	
	public static void vd_sc_FiltragemNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Há caixas sem selação na pesquisa");
		alert.setHeaderText(null);
		alert.setContentText("Você deve selecionar um tipo de pesquisa para fazer, já estamos mostrando todos os resultados.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CamposVazios(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Há um ou mais campos vazios no cadastro");
		alert.setHeaderText(null);
		alert.setContentText("Não é possível continuar com o cadastro, não são permitidos campos vazios");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApenasNumerosLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Há campos com valores inválidos");
		alert.setHeaderText(null);
		alert.setContentText("O campo de quantidade e ISBN podem conter APENAS números! Verifique.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ApenasNumerosFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Há campos com valores inválidos");
		alert.setHeaderText(null);
		alert.setContentText("O campo de quantidade pode conter apenas números! Verifique.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ISBNCadastado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ISBN já cadastrado");
		alert.setHeaderText(null);
		alert.setContentText("Este ISBN já foi cadastrado no sistema, o cadastro não poderá ser continuado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_EstadoAssociadoComCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("O estado está associado com uma ou mais cidades");
		alert.setHeaderText(null);
		alert.setContentText("Este estado está associado com uma ou mais cidades e não poderá ser deletado..");
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
		alert.setTitle("Funcionário Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcionário foi alterado com sucesso!");
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
		alert.setTitle("Usuário Alterado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O usuário foi alterado com sucesso!");
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
		alert.setTitle("Seção apagada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A seção foi apagado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_AlterarSecaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Seção alterar com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A seção foi alterada com sucesso!");
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
		alert.setTitle("Campos Inválidos");
		alert.setHeaderText(null);
		alert.setContentText("Há campos que contém valores inválidos, o campo de CPF e de Telefone pode conter apenas números!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NomeInvalidos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome inválido");
		alert.setHeaderText(null);
		alert.setContentText("O nome que você digitou é inválido, não são permitidos números nem carecteres especiais!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CPFCadastado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CPF já cadastrado");
		alert.setHeaderText(null);
		alert.setContentText("Este CPF já foi cadastrado no sistema, o cadastro não poderá ser continuado.");
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
		alert.setTitle("Funcionário Cadastrado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcionário foi cadastrado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldFuncionario(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o máximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("Há um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n CPF: 11 dígitos \n Endereço: 80 caracteres \n Telefone: 20 dígitos \n Usuario: 40 dígitos \n Senha: 20 Dígitos");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldExemplarLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o máximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("Há um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n Título: 50 caracteres \n Quantidade: 11 dígitos \n Idioma: 35 dígitos \n ISBN: 13 dígitos \n Editora: 20 Dígitos \n Autor: 40 caracteres");
		alert.showAndWait();
	}
	
	public static void vd_sc_LinhaNaoSelecionada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Não há linha selecionadas");
		alert.setHeaderText(null);
		alert.setContentText("Para alterar ou excluir você deve selecionar uma linha da tabela");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoFieldExemplarFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Tamanhos excedendo o máximo permitido");
		alert.setHeaderText(null);
		alert.setContentText("Há um ou mais campos que excedem o tamanho permitido de caracteres. \n Verifique a seguir: \n Título: 50 caracteres \n Quantidade: 11 dígitos \n Idioma: 35 dígitos \n Nome Diretor: 40 caracteres \n Duracao: 15 caracteres \n País de Origem: 45 caracteres");
		alert.showAndWait();
	}
	
	public static void vd_sc_TelefoneInválido(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Telefone Inválido");
		alert.setHeaderText(null);
		alert.setContentText("O telefone só pode conter números, nenhum outro caractere permitido!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CPFInválido(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CPF Inválido");
		alert.setHeaderText(null);
		alert.setContentText("O CPF que você digitou é inválido!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome da cidade excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho máximo de um nome de cidade são 45 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeEstado(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome do estado excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho máximo de um nome de estado são 45 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_TamanhoNomeSecao(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nome da seção excede o limite");
		alert.setHeaderText(null);
		alert.setContentText("O tamanho máximo de um nome de seção são 24 caracteres!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NomeCidade(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("O nome da cidade não pode conter números");
		alert.setHeaderText(null);
		alert.setContentText("O nome da cidade não pode conter números!");
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
		alert.setTitle("Seção Cadastrada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A seção foi cadastrada com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DatasNaoSelecionadas(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Não há datas selecionadas");
		alert.setHeaderText(null);
		alert.setContentText("Favor, selecionar uma data!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataEmprestimoMaior(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de empréstimo é maior que a data de prazo");
		alert.setHeaderText(null);
		alert.setContentText("A data de empréstimo está incorreta, ela não pode ser maior que a data do prazo!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DatasIguais(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de empréstimo igual a data de prazo");
		alert.setHeaderText(null);
		alert.setContentText("A data de empréstimo não pode ser a mesma data do prazo! Não são permitidos empréstimos de menos de 1 dia!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataPrazoMenorEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de prazo é menor que a data de empréstimo");
		alert.setHeaderText(null);
		alert.setContentText("A data de prazo não pode ser a menor que a date de empréstimos!");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmprestimoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Emprestimo Realizado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O empréstimo foi realizado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ExemplaresAcabaramEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sem unidades disponíveis para empréstimo");
		alert.setHeaderText(null);
		alert.setContentText("O empréstimo não pode ser realizado, todas as unidades estão emprestadas!");
		alert.showAndWait();
	}
	
	public static void vd_sc_ExemplaresAcabaramReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sem unidades disponíveis para reserva");
		alert.setHeaderText(null);
		alert.setContentText("A reserva não pode ser realizado, todas as unidades estão emprestadas!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DataEntregaVaziaEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Data de Entrega Obrigatória");
		alert.setHeaderText(null);
		alert.setContentText("Você deve selecionar uma data de entrega.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MultaVerificada(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("A multa foi verificada, o campo de multa não poderá ser vazio.");
		alert.setHeaderText(null);
		alert.setContentText("Caso deseje isentar da multa utilize o zero no campo.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MultaAplicada(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data de prazo excedida");
		alert.setHeaderText(null);
		alert.setContentText("A multa deverá ser applicada!");
		alert.showAndWait();
	}
	
	public static void vd_sc_SemMulta(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Dentro do prazo");
		alert.setHeaderText(null);
		alert.setContentText("Não há a necessidade da aplicação de uma multa.");
		alert.showAndWait();
	}
	
	public static void vd_sc_DevolucaoSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Devolução Realizada com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("A devolução foi realizada com sucesso!");
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
		alert.setTitle("Empréstimos realizados com este livro");
		alert.setHeaderText(null);
		alert.setContentText("O livro está associado a um ou mais empréstimos, não poderá ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UsuarioAssociadoEmprestimos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Usário realizou empréstimos");
		alert.setHeaderText(null);
		alert.setContentText("O usuário está associado a um ou mais empréstimos, não poderá ser apagado nem ter seu CPF modificado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_SecaoAssociadoLivro(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Seção associada Livro");
		alert.setHeaderText(null);
		alert.setContentText("Esta seção está associada com um livro e não poderá ser apagada.");
		alert.showAndWait();
	}
	
	public static void vd_sc_SecaoAssociadoFilme(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Seção associada filme");
		alert.setHeaderText(null);
		alert.setContentText("Esta seção está associada com um filme e não poderá ser apagada.");
		alert.showAndWait();
	}
	
	public static void vd_sc_LivroAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reservas realizadas com este livro");
		alert.setHeaderText(null);
		alert.setContentText("O livro está associado a um ou mais reservas, não poderá ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FuncionarioAssociadoEmprestimo(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Funcionário realizou empréstimos");
		alert.setHeaderText(null);
		alert.setContentText("Este funcionário realizou um ou mais empréstimos, não poderá ser apagado nem ter seu CPF modificado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UsuarioAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Usuário realizou reservas");
		alert.setHeaderText(null);
		alert.setContentText("O usuário está associado a um ou mais reservas, não poderá ser apagado nem ter seu CPF modificado.");
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
		alert.setTitle("Usuário apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O usuário foi apagado com sucesso.");
		alert.showAndWait();
	}
	
	
	
	public static void vd_sc_FuncionarioApagarSucesso(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Funcionário apagado com sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O funcionário foi apagado com sucesso.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FilmeAssociadoEmprestimos(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Empréstimos realizados com este filme");
		alert.setHeaderText(null);
		alert.setContentText("O filme está associado a um ou mais empréstimos, não poderá ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_FilmeAssociadoReserva(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reservas realizadas com este filme");
		alert.setHeaderText(null);
		alert.setContentText("O filme está associado a um ou mais reservas, não poderá ser apagado.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ConsultaLivrosFiltragemVazia(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("A seção não foi selecionada");
		alert.setHeaderText(null);
		alert.setContentText("A seção não foi selecionada, já estamos exibindo todos os livros cadastrados, caso queira buscar por seção específica favor selecionar a filtragem.");
		alert.showAndWait();
	}
	
	public static boolean pb_bo_sc_AvisoApagandoDado(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Atenção, você está prestes a deletar um dado permanentemente");
		alert.setHeaderText(null);
		alert.setContentText("Você deseja realmente deletar? Esta é uma ação permanente.");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		   return false;
		}
		
	}

	
}
