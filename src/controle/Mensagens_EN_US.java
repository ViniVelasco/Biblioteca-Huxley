package controle;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classe de mensagens em inglês, contendo todas as mensagens estáticas.
 * @author Vinícius Velasco
 *
 */
public class Mensagens_EN_US {
	
	public static void vd_sc_emptyLogin(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Null fields in login");
		alert.setHeaderText(null);
		alert.setContentText("The fields user and password can't be null!");
		alert.showAndWait();
	}
	
	public static void vd_sc_wrongLogin(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("User or password is wrong");
		alert.setHeaderText(null);
		alert.setContentText("Your user or password is wrong!");
		alert.showAndWait();
	}
	
	public static void vd_sc_Comboboxnotselect(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Boxes with no selection");
		alert.setHeaderText(null);
		alert.setContentText("It's not possible continue the register, all items in boxes must be selected!");
		alert.showAndWait();
	}
	
	public static void vd_sc_FilterNotSelected(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are filters with not selection");
		alert.setHeaderText(null);
		alert.setContentText("You must select a type of search, we are already showing all results.");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmptyFields(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are empty fields.");
		alert.setHeaderText(null);
		alert.setContentText("It's not possible continue with register, empty fields are not allowed.");
		alert.showAndWait();
	}
	
	public static void vd_sc_OnlyNumbersBooks(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are fields with wrong values");
		alert.setHeaderText(null);
		alert.setContentText("The field quantity and ISBN can only contain numbers! Verify.");
		alert.showAndWait();
	}
	
	public static void vd_sc_OnlyNumbersMovies(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are fields with wrong values");
		alert.setHeaderText(null);
		alert.setContentText("The field quantity can only contain numbers! Verify.");
		alert.showAndWait();
	}
	
	public static void vd_sc_RegisteredISBN(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ISBN already register.");
		alert.setHeaderText(null);
		alert.setContentText("This ISBN is already register in system, the register can't be continued.");
		alert.showAndWait();
	}
	
	public static void vd_sc_StateAssociedWithCity(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("The state are associed with one or more cities.");
		alert.setHeaderText(null);
		alert.setContentText("This state is associed with one or more cities, can't be deleted.");
		alert.showAndWait();
	}
	
	public static void vd_sc_RegisterBookSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Book register with sucess");
		alert.setHeaderText(null);
		alert.setContentText("The book is registered is sucessfull.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UpdateEmployeeSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Employee update sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The employee is update with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_UpdateBookSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Book update sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The book is update with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_UpdateMovieSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie update sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The movie is update with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_UserUpdateSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User update sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The user is update with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_StateUpdateSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("State update with sucess");
		alert.setHeaderText(null);
		alert.setContentText("The State is update with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DeleteStateSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Estado apagado com Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("O estado foi apagado com sucesso!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DeleteSectionSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Section delete sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The section is delete with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_UpdateSectionWithSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Section update sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The section is update with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_MovieRegisterSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie register sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("Movie is registered with sucess.");
		alert.showAndWait();
	}
	
	public static void vd_sc_WrongFieldsUser(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Wrong fields");
		alert.setHeaderText(null);
		alert.setContentText("The are fields with wrong values, the field CPF and Telephone can only contain numbers!");
		alert.showAndWait();
	}
	
	public static void vd_sc_InvalidNames(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Name Invalid");
		alert.setHeaderText(null);
		alert.setContentText("The name you entered is invalid, numbers and special characteres are not allowed!");
		alert.showAndWait();
	}
	
	public static void vd_sc_CPFAlreadyRegistered(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("CPF already registered");
		alert.setHeaderText(null);
		alert.setContentText("This CPF are already registered in system, the register can't continue.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UserRegisterSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Client register sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The cliente is registered with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmployeeRegisterSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Employee Register Sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The employee is registered with sucess.");
		alert.showAndWait();
	}
	
	public static void vd_sc_LengthEmployee(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Length exceeding the allowed");
		alert.setHeaderText(null);
		alert.setContentText("There are one or more fields exceeding the max allowed. \n Verify: \n CPF: 11 caracthers \n Adress: 80 caracthers \n Telephone: 20 caracthers \n User: 40 caracthers \n Password: 20 caracthers");
		alert.showAndWait();
	}
	
	public static void vd_sc_LenghtCopyBook(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Length exceeding the allowed");
		alert.setHeaderText(null);
		alert.setContentText("There are one or more fields exceeding the max allowed. \n Verify: \n Title: 50 caracthers \n Quantity: 11 caracthers \n Language: 35 caracthers \n ISBN: 13 dígitos \n Publisher: 20 caracthers \n Author: 40 caracthers");
		alert.showAndWait();
	}
	
	public static void vd_sc_LineNotSelected(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are no lines selected");
		alert.setHeaderText(null);
		alert.setContentText("To update or delete you must select a line in the table!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LengthCopyMovie(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Length exceeding the allowed");
		alert.setHeaderText(null);
		alert.setContentText("There are one or more fields exceeding the max allowed. \n Verify: \n Title: 50 caracthers \n Quantity: 11 caracthers \n Language: 35 caracthers \n Directors Name: 40 caracthers \n Duration: 15 caracthers \n Country of Origin: 45 caracthers");
		alert.showAndWait();
	}
	
	public static void vd_sc_InvalidTelephone(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid telephone");
		alert.setHeaderText(null);
		alert.setContentText("The telephone can only contain numbers, no one other caracthers allowed!");
		alert.showAndWait();
	}
	
	public static void vd_sc_WrongCPF(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid CPF");
		alert.setHeaderText(null);
		alert.setContentText("The CPF you entered is invalid!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LengthNameCity(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Name of city exceeding the limit.");
		alert.setHeaderText(null);
		alert.setContentText("The max lenght of name of city is 45 caracthers!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LenghtStateName(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Name of State exceeding the limit.");
		alert.setHeaderText(null);
		alert.setContentText("The max lenght of name of State is 45 caracthers!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LengthSectionName(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Name of section exceeding the limit.");
		alert.setHeaderText(null);
		alert.setContentText("The max lenght of name of section is 24 caracthers!!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NameCitye(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("The name of city cannot containt numbers");
		alert.setHeaderText(null);
		alert.setContentText("The name of city cannot containt numbers!");
		alert.showAndWait();
	}
	
	public static void vd_sc_RegisterCitySucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("City registered with sucess");
		alert.setHeaderText(null);
		alert.setContentText("The city is registered with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_RegisterStateSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("State register sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The state is registered with sucess.");
		alert.showAndWait();
	}
	
	public static void vd_scRegisterSectionSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Section register sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The section is registered with sucess.");
		alert.showAndWait();
	}
	
	public static void vd_sc_DatesNotSelected(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("There are not dates selected");
		alert.setHeaderText(null);
		alert.setContentText("Please, select a date.");
		alert.showAndWait();
	}
	
	public static void vd_sc_DateLoanBig(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Date of loan is bigger than date of deadline.");
		alert.setHeaderText(null);
		alert.setContentText("The date of loan is incorrect, they can't be bigger thant deadline.");
		alert.showAndWait();
	}
	
	public static void vd_sc_SameDates(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Date of loan same of date of deadline");
		alert.setHeaderText(null);
		alert.setContentText("The dates can't be the same!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DeadlineSmallerLoan(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Date of deadline is smaller than date of loan.");
		alert.setHeaderText(null);
		alert.setContentText("Date of deadline is smaller than date of loan!");
		alert.showAndWait();
	}
	
	public static void vd_sc_LoanSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Loan sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("Loan is realized with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_OutOfCopys(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No units available for loans");
		alert.setHeaderText(null);
		alert.setContentText("The loan or the reserve can't be realized, all units are borrowed!");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmptyDateOfDelivery(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Date of delivery is required");
		alert.setHeaderText(null);
		alert.setContentText("You must select a date of delivery");
		alert.showAndWait();
	}
	
	public static void vd_sc_VerifiedTicket(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("The ticket is verified, the field of ticket CAN'T be empty");
		alert.setHeaderText(null);
		alert.setContentText("In case of exemption utilize zero in the field.");
		alert.showAndWait();
	}
	
	public static void vd_sc_TicketApplied(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deadline exceeding");
		alert.setHeaderText(null);
		alert.setContentText("The ticket must be applied!");
		alert.showAndWait();
	}
	
	public static void vd_sc_NoTicket(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("In the deadline");
		alert.setHeaderText(null);
		alert.setContentText("The are no necessity of a ticket.");
		alert.showAndWait();
	}
	
	public static void vd_sc_DevolutionSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Devolution sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The devolution is realized with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_ReserveSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Reserve sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The reserve is realized with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_BookDeleteSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Book delete sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("Book deleted with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_UpdateCitySucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("City update sucesfull");
		alert.setHeaderText(null);
		alert.setContentText("The city is update with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_DeleteCitySucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("City delete sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("City deleted with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_MovieDeleteSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Movie delete with sucess");
		alert.setHeaderText(null);
		alert.setContentText("The movie is deleted with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_BookAssociedWithLoans(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Loans realizeds with this book");
		alert.setHeaderText(null);
		alert.setContentText("The book is associed with one or more loans, can't be deleted or have his ISBN modified.");
		alert.showAndWait();
	}
	
	public static void vd_sc_UserAssociedWithLoans(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("User realized loans");
		alert.setHeaderText(null);
		alert.setContentText("The user is associed with one or more loans, can't be deleted or have his CPF modified");
		alert.showAndWait();
	}
	
	public static void vd_sc_SectionAssociedBook(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Section associed with a book");
		alert.setHeaderText(null);
		alert.setContentText("This section is associed with a book and can't be deleted.");
		alert.showAndWait();
	}

	public static void vd_sc_SectionAssociedMovie(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Section associed with a movie");
		alert.setHeaderText(null);
		alert.setContentText("This section is associed with a movie and can't be deleted.");
		alert.showAndWait();
	}
	
	public static void vd_sc_BookAssociedWithReserve(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reserves associed with this book");
		alert.setHeaderText(null);
		alert.setContentText("The book is associed with reserves, and can't be deleted.");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmployeAssociedLoan(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Employee realized loans");
		alert.setHeaderText(null);
		alert.setContentText("The employee is associed with one or more loans, can't be deleted or have his CPF modified");
		alert.showAndWait();
	}
	
	public static void vd_sc_UserAssociedWithReserves(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("User realized reserves");
		alert.setHeaderText(null);
		alert.setContentText("The user realized one or more reserves, can't be deleted or have his CPF modified.");
		alert.showAndWait();
	}
	
	public static void vd_sc_CancelReservesSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Reserve cancel sucessfull");
		alert.setHeaderText(null);
		alert.setContentText("The reserve is canceled with sucess!");
		alert.showAndWait();
	}
	
	public static void vd_sc_DeleteUserSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User delete sucesfull");
		alert.setHeaderText(null);
		alert.setContentText("The user is deleted with sucess");
		alert.showAndWait();
	}
	
	public static void vd_sc_EmployeeDeletedSucess(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Employee deleted with Sucess");
		alert.setHeaderText(null);
		alert.setContentText("The employee is delete with sucess.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MovieAssociedWithLoan(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Movie associed with loans");
		alert.setHeaderText(null);
		alert.setContentText("The movie is associed with loans, can't be deleted.");
		alert.showAndWait();
	}
	
	public static void vd_sc_MovieAsssociedReserve(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Reserves associed with this movie");
		alert.setHeaderText(null);
		alert.setContentText("The movie is associed with reserves, and can't be deleted.");
		alert.showAndWait();
	}
	
	public static void vd_sc_ConsultBooksFiltersEmpty(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("The section is not selected");
		alert.setHeaderText(null);
		alert.setContentText("The section is not selected, we're already showing all books registereds, in case of search by especified section select a filter");
		alert.showAndWait();
	}
	
	public static boolean pb_bo_sc_WarningDeletingInformation(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Atention: You are deleting a information forever");
		alert.setHeaderText(null);
		alert.setContentText("You really want to delete? This is a permanent action!");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		   return false;
		}
		
	}
	
	

}
