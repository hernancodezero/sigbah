package pe.com.sigbah.web.report.programacion_bah;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import pe.com.sigbah.common.bean.EmergenciaBean;

/**
 * @className: ReporteControlCalidad.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteEmergencia implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteEmergencia.class.getName());
	
	/**
	 * @param lista
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelEmergencia(List<EmergenciaBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE EMERGENCIA");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 8000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 1500);
	        sheet.setColumnWidth(10, 2000);
	        sheet.setColumnWidth(11, 5000);
	        sheet.setColumnWidth(12, 5000);
	        sheet.setColumnWidth(13, 8000);
	        sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 6000);
			sheet.setColumnWidth(16, 4000);
//			sheet.setColumnWidth(17, 4000);
	        
			HSSFRow row1 = sheet.createRow((short) 1);
	        
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            
	        
	        HSSFCellStyle style_header = (HSSFCellStyle) wb.createCellStyle();
	        style_header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_header.setFont(font_bold);
	        
	        HSSFColor color = setColor(wb, (byte) 242, (byte)242, (byte) 242);
	        style_header.setFillForegroundColor(color.getIndex());
	        style_header.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        style_header.setBorderBottom((short) 1);
	        style_header.setBorderLeft((short) 1);	        
	        style_header.setBorderRight((short) 1);
	        style_header.setBorderTop((short) 1);
	        
	        row1.createCell(1).setCellValue("Item");
	        row1.getCell(1).setCellStyle(style_header);
	        
	        row1.createCell(2).setCellValue("Año");
	        row1.getCell(2).setCellStyle(style_header);
	        
	        row1.createCell(3).setCellValue("Mes");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        row1.createCell(4).setCellValue("Fecha");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("Código SINPAD");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Fenómeno");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Nombre emergencia");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Región");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        row1.createCell(9).setCellValue("Provincia");
	        row1.getCell(9).setCellStyle(style_header);
	        
	        row1.createCell(10).setCellValue("Distrito");
	        row1.getCell(10).setCellStyle(style_header);
	        
//	        row1.createCell(11).setCellValue("Población INEI");
//	        row1.getCell(11).setCellStyle(style_header);
	        
	        row1.createCell(11).setCellValue("Fam. Afec.");
	        row1.getCell(11).setCellStyle(style_header);
	        
	        row1.createCell(12).setCellValue("Fam. damnif.");
	        row1.getCell(12).setCellStyle(style_header);
	        
	        row1.createCell(13).setCellValue("Total fam.");
	        row1.getCell(13).setCellStyle(style_header);
	        
	        row1.createCell(14).setCellValue("Pers. afec.");
	        row1.getCell(14).setCellStyle(style_header);
	        
	        row1.createCell(15).setCellValue("Pers. damn.");
	        row1.getCell(15).setCellStyle(style_header);
	        
	        row1.createCell(16).setCellValue("Total");
	        row1.getCell(16).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (EmergenciaBean emergencia : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(emergencia.getCodAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(emergencia.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(emergencia.getFecha());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(emergencia.getIdEmergencia());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(emergencia.getDescFenomeno());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(emergencia.getNombreEmergencia());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(emergencia.getDesDepartamento());
		        rows.getCell(8).setCellStyle(style_cell);
		        
		        rows.createCell(9).setCellValue(emergencia.getDesProvincia());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(emergencia.getDesDistrito());
		        rows.getCell(10).setCellStyle(style_cell);
		        
//		        rows.createCell(11).setCellValue(emergencia.getPoblacionINEI());
//		        rows.getCell(11).setCellStyle(style_cell);
		        
		        rows.createCell(11).setCellValue(emergencia.getFamAfectado());
		        rows.getCell(11).setCellStyle(style_cell);
		        
		        rows.createCell(12).setCellValue(emergencia.getFamDamnificado());
		        rows.getCell(12).setCellStyle(style_cell);
		        
		        rows.createCell(13).setCellValue(emergencia.getTotalFam());
		        rows.getCell(13).setCellStyle(style_cell);
		        
		        rows.createCell(14).setCellValue(emergencia.getPersoAfectado());
		        rows.getCell(14).setCellStyle(style_cell);
		        
		        rows.createCell(15).setCellValue(emergencia.getPersoDamnificado());
		        rows.getCell(15).setCellStyle(style_cell);
		        
		        rows.createCell(16).setCellValue(emergencia.getTotalPerso());
		        rows.getCell(16).setCellStyle(style_cell);
	            
	            row++;	
	        }
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}
	
	private HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor= palette.findColor(r, g, b); 
			if (hssfColor == null) {
			    palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
			    hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		 } catch (Exception e) {
			 LOGGER.error(e);
		 }
		 return hssfColor;
	}	
    
}