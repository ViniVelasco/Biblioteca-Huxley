package controle;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que controla as validações de negócio
 * @author Vinícius Velasco
 *
 */
public class ValidacoesdeNegocio {
	
	/**
	 * Classe que verifica se um telefone é valido
	 * @param texto
	 * @return true se é válido false se não.
	 */
	  public static boolean pb_bo_static_TelefoneValido(String texto){   
		    Pattern p = Pattern.compile("[0-9]+");   
		    Matcher m = p.matcher(texto);   
		    return m.matches();   
		}  
	  /**
	   * Classe que verifica se um CPF é válido.
	   * @param texto
	   * @return true se é válido false se não.
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
	   * Método que verifica nomes
	   * @param text
	   * @return true se é válido false se não.
	   */
	  public static boolean ChecarNome(String text) {
	         return text.matches("[A-Za-zÀ-ú\\s]+"); // /s = espaços À-ú = alphanum
	         //[^\\d]+
	         //Passa para o método matches a regex
	         //Se tiver número na string irá retornar falso, o + indica que a combinação pode ocorrer mais de uma vez.
	     }

}
