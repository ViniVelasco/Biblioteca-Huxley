package relatorios;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import modelo.ExemplarLivroDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe que gera relatório de livros
 * @author Vinícius Velasco
 *
 */
public class RelatorioDeLivros {
	/**
	 * Método que gera relatório de livros
	 * @throws JRException
	 */
	public void pb_vd_sc_GerarRelatorioDeLivros() throws JRException{
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relatório de Livros.jrxml");
		
		ExemplarLivroDAO livros = new ExemplarLivroDAO();
		
		JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(livros.pb_vd_buscarLivros()));
		
		Date pc_dt_Data = new Date();
		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String dataAtual = pr_df_dateFormat.format(pc_dt_Data);
		
		JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Livros " + dataAtual + ".pdf");
		
		JasperViewer jrviewer = new JasperViewer(print, false);  
		jrviewer.setVisible(true); 
        jrviewer.toFront();
        
		/**
		try {
			JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Emprestimos " + dataAtual +  ".pdf");
			java.awt.Desktop.getDesktop().open( new File( "relatorios/Relatorio De Emprestimos "  + dataAtual + ".pdf" ) ); //Abrindo relatório gerado
	      } catch (JRException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/

	}

}
