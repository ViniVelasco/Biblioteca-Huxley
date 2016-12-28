package relatorios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import modelo.Emprestimo;
import modelo.EmprestimoDAO;
import modelo.ExemplarFilmeDAO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe que gera o relat�rio de empr�stimos.
 * @author Vin�cius Velasco
 *
 */
public class RelatorioDeEmprestimos {
	/**
	 * M�todo que gera o relat�rio de todos empr�stimos.
	 * @throws JRException
	 */
	public void pb_vd_sc_GerarRelatorioDeEmprestimos() throws JRException{
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relat�rio de Empr�stimos.jrxml");
		
		EmprestimoDAO emprestimos = new EmprestimoDAO();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(emprestimos.pb_ar_BuscarTodosEmprestimosRelatorio());
		
		Map parameters = new HashMap();
		parameters.put("ListaEmprestimo", beanColDataSource);
		
		
		Date pc_dt_Data = new Date();
		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String dataAtual = pr_df_dateFormat.format(pc_dt_Data);
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters, beanColDataSource);
		
		JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Empr�stimos " + dataAtual + ".pdf");
		
		JasperViewer jrviewer = new JasperViewer(print, false);  
		jrviewer.setVisible(true); 
        jrviewer.toFront();
        
		/**
		try {
			JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Emprestimos " + dataAtual +  ".pdf");
			java.awt.Desktop.getDesktop().open( new File( "relatorios/Relatorio De Emprestimos "  + dataAtual + ".pdf" ) ); //Abrindo relat�rio gerado
	      } catch (JRException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	   }
	   
	
	/**
	 * M�todo que gera o relat�rio de todos os empr�stimos em aberto
	 * @throws JRException
	 */
	public void pb_vd_sc_GerarRelatorioDeEmprestimosEmAberto() throws JRException{
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relat�rio de Empr�stimos Em Aberto.jrxml");
		
		EmprestimoDAO emprestimos = new EmprestimoDAO();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(emprestimos.pb_ar_BuscarEmprestimosEmAbertoRelatorio());
		
		Map parameters = new HashMap();
		parameters.put("ListaEmprestimo", beanColDataSource);
		
		
		Date pc_dt_Data = new Date();
		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String dataAtual = pr_df_dateFormat.format(pc_dt_Data);
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters, beanColDataSource);
		
		JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Empr�stimos Em Aberto " + dataAtual + ".pdf");
		
		JasperViewer jrviewer = new JasperViewer(print, false);  
		jrviewer.setVisible(true); 
        jrviewer.toFront();
        
		/**
		try {
			JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Emprestimos " + dataAtual +  ".pdf");
			java.awt.Desktop.getDesktop().open( new File( "relatorios/Relatorio De Emprestimos "  + dataAtual + ".pdf" ) ); //Abrindo relat�rio gerado
	      } catch (JRException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	   }
	
	/**
	 * M�todo que gera o relat�rio de empr�stimos finalizados.
	 * @throws JRException
	 */
	public void pb_vd_sc_GerarRelatorioDeEmprestimosEmFinalizados() throws JRException{
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relat�rio de Empr�stimos Finalizados.jrxml");
		
		EmprestimoDAO emprestimos = new EmprestimoDAO();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(emprestimos.pb_ar_BuscarEmprestimosFinalizadosRelatorio());
		
		Map parameters = new HashMap();
		parameters.put("ListaEmprestimo", beanColDataSource);
		
		
		Date pc_dt_Data = new Date();
		DateFormat pr_df_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String dataAtual = pr_df_dateFormat.format(pc_dt_Data);
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters, beanColDataSource);
		
		JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Empr�stimos Finalizados " + dataAtual + ".pdf");
		
		JasperViewer jrviewer = new JasperViewer(print, false);  
		jrviewer.setVisible(true); 
        jrviewer.toFront();
        
		/**
		try {
			JasperExportManager.exportReportToPdfFile(print,"relatorios/Relatorio De Emprestimos " + dataAtual +  ".pdf");
			java.awt.Desktop.getDesktop().open( new File( "relatorios/Relatorio De Emprestimos "  + dataAtual + ".pdf" ) ); //Abrindo relat�rio gerado
	      } catch (JRException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		**/
	}

}
	


