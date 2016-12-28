package relatorios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.ExemplarFilmeDAO;
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
 * Classe que gera relatório de filmes.
 * @author Vinícius Velasco
 *
 */
public class RelatorioDeFilmes {
	/**
	 * Método que gera relatório de filmes.
	 * @throws JRException
	 */
	public void pb_vd_sc_GerarRelatorioDeFilmes() throws JRException{
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relatório de Filmes.jrxml");
		
		ExemplarFilmeDAO filmes = new ExemplarFilmeDAO();
		
		JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(filmes.pb_vd_buscarFilmes()));
		
		Date pc_dt_Data = new Date();
		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String dataAtual = pr_df_dateFormat.format(pc_dt_Data);
		
		JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Filmes " + dataAtual + ".pdf");
		
		JasperViewer jrviewer = new JasperViewer(print, false);  
		jrviewer.setVisible(true); 
        jrviewer.toFront();
        
		//try {
			//java.awt.Desktop.getDesktop().open( new File( "relatorios/Relatorio De Filmes "  + dataAtual + ".pdf" ) ); //Abrindo relatório gerado
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		//System.out.println("Relatorio Gerado");

	}

}
