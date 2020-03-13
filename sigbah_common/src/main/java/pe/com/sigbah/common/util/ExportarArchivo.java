package pe.com.sigbah.common.util;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;


/**
 * @className: ExportarArchivo.java
 * @description: Clase utilitaria para la exportación de archivos.
 * @date: 2 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ExportarArchivo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Exporta una lista de registros a PDF.
     * @param jasperFile - Nombre del Archivo jasper.
     * @param parameters - Parametros necesarios para el reporte.
     * @param dataList - Lista con los datos para ser exportados.
     * @return Bye[] - Conjunto de bytes para ser exportados.
     * @throws Exception Manejador de excepciones.
     */
    public byte[] exportPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList) throws Exception {
    	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
    	
    	
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }
    
    public byte[] exportPdfSub(String jasperFile,String jasperFile1, Map<String, Object> parameters, List<?> dataList) throws Exception {
    	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
    	
    	JasperReport subreport = JasperCompileManager.compileReport(jasperFile1);
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        parameters.put("subreportParameter", subreport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }
    
    public byte[] exportPdfSub2(String jasperFile,String jasperFile1,String jasperFile2, Map<String, Object> parameters, List<?> dataList) throws Exception {
    	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
    	
    	JasperReport subreport1 = JasperCompileManager.compileReport(jasperFile1);
    	JasperReport subreport2 = JasperCompileManager.compileReport(jasperFile2);
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        parameters.put("subreporteAlimentos", subreport1);
        parameters.put("subreporteNoAlimentos", subreport2);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }
    
    public byte[] exportPdfHistorial(String jasperFile, Map<String, Object> parameters, List<?> dataList, String ruta1) throws Exception {
    	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        JasperExportManager.exportReportToPdfFile(jasperPrint,ruta1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }
    
    public byte[] exportPdfHistorialSub(String jasperFile, String jasperFilePro,String jasperFileDoc,String jasperFileEnd,String jasperFileInd,String jasperFileIngSal,Map<String, Object> parameters, List<?> dataList, String ruta1) throws Exception {
    	parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
    	JasperReport reportPro = JasperCompileManager.compileReport(jasperFilePro);
    	JasperReport reportDoc = JasperCompileManager.compileReport(jasperFileDoc);
    	JasperReport reportEnd = JasperCompileManager.compileReport(jasperFileEnd);
    	JasperReport reportInd = JasperCompileManager.compileReport(jasperFileInd);
    	JasperReport reportIngSal = JasperCompileManager.compileReport(jasperFileIngSal);
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        parameters.put("subreportePro", reportPro);
        parameters.put("subreporteDoc", reportDoc);
        parameters.put("subreporteEnt", reportEnd);
        parameters.put("subreporteInd", reportInd);
        parameters.put("subreporteIngSal", reportIngSal);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
//        jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "ISO-8859-1");
        JasperExportManager.exportReportToPdfFile(jasperPrint,ruta1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }

    /**
     * Exporta una lista de objetos a excel.
     * 
     * @param jasperFile - Nombre del Archivo jasper.
     * @param parameters - Parametros necesarios para el reporte.
     * @param dataList - Lista con los datos para ser exportados.
     * @param isOnePagePerSheet - Por cada pagina tiene un Shet Excel.
     * @return Conjunto de bytes.
     * @throws Exception Manejador de excepciones.
     */
    public byte[] exportXls(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JExcelApiExporter jExcelApiExporter = new JExcelApiExporter();
        jExcelApiExporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
        jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
        jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
        jExcelApiExporter.setParameter(JExcelApiExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
        jExcelApiExporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
        jExcelApiExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        jExcelApiExporter = null;
        return bytes;
    }

}
