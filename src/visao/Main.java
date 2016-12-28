package visao;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Classe principal,contendo o método main.
 * @author Vinícius Velasco
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage palco) {
		try {
			URL arquivoFXML = getClass().getResource("Menu.fxml");
			Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			 palco.setScene(new Scene(fxmlParent));
			 palco.setTitle("Biblioteca Huxley");
			 palco.setResizable(false);
			 palco.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
