package controle;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que controla as valida��es de neg�cio
 * @author Vin�cius Velasco
 *
 */
public class ValidacoesdeNegocio {
	
	/**
	 * Classe que verifica se um telefone � valido
	 * @param texto
	 * @return true se � v�lido false se n�o.
	 */
	  public static boolean pb_bo_static_TelefoneValido(String texto){   
		    Pattern p = Pattern.compile("[0-9]+");   
		    Matcher m = p.matcher(texto);   
		    return m.matches();   
		}  
	  /**
	   * Classe que verifica se um CPF � v�lido.
	   * @param texto
	   * @return true se � v�lido false se n�o.
	   */
	  public static boolean pb_bo_static_CPFValido(String texto){   
		    Pattern p = Pattern.compile("[0-9]+");   
		    Matcher m = p.matcher(texto);   
		    return m.matches();   
		}  
	  
	  /**
	   * Classe que verifica datas
	   * @param data1
	   * @param data2
	   * @return menor que 1 se Data 1 menor que data dois, maior que 1 se data 1 maior que data dois e zero se datas iguais
	   */
	  public static int comparaData( Date data1, Date data2 ) {
			return data1.compareTo( data2 );
		}
	  
	  /**
	   * M�todo que verifica nomes
	   * @param text
	   * @return true se � v�lido false se n�o.
	   */
	  public static boolean ChecarNome(String text) {
	         return text.matches("[A-Za-z�-�\\s]+"); // /s = espa�os �-� = alphanum
	         //[^\\d]+
	         //Passa para o m�todo matches a regex
	         //Se tiver n�mero na string ir� retornar falso, o + indica que a combina��o pode ocorrer mais de uma vez.
	     }

}
