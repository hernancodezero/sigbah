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
import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: ReporteRequerimiento.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: JHuaman.
 */
public class ReporteRequerimientoAfectado implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteRequerimientoAfectado.class.getName());
	
	/**
	 * @param lista
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelRequerimientoAfectado(List<EmergenciaBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE REQUERIMIENTO AFECTADOS");
	        
	        sheet.setColumnWidth(1, 2000);
	        
	        sheet.setColumnWidth(2, 3000);
	        sheet.setColumnWidth(3, 3000);
	        sheet.setColumnWidth(4, 3000);
	        
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 3000);
	        sheet.setColumnWidth(9, 3000);
			sheet.setColumnWidth(10, 3000);
	        sheet.setColumnWidth(11, 3000);
	        sheet.setColumnWidth(12, 3000);
	        sheet.setColumnWidth(13, 4000);
	        sheet.setColumnWidth(14, 4000);
			sheet.setColumnWidth(15, 4000);
			sheet.setColumnWidth(16, 4000);
//			sheet.setColumnWidth(17, 4000);
//			sheet.setColumnWidth(18, 4000);
//			sheet.setColumnWidth(19, 3000);
//			sheet.setColumnWidth(20, 3000);
//	        sheet.setColumnWidth(21, 3000); 12 15 18 21
	        
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
	        
//	        row1.createCell(1).setCellValue("Item");
//	        row1.getCell(1).setCellStyle(style_header);
	        
	        
	        row1.createCell(1).setCellValue("ID_REQ_DAMNIFICADO");
	        row1.getCell(1).setCellStyle(style_header);
	        
	        row1.createCell(2).setCellValue("FK_IDE_REQUERIMIENTO");
	        row1.getCell(2).setCellStyle(style_header);
	        
	        row1.createCell(3).setCellValue("COD_DISTRITO");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        
	        
	        row1.createCell(4).setCellValue("Departamento");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("Provincia");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Distrito");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Código SINPAD");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Pob. INEI");
	        row1.getCell(8).setCellStyle(style_header);
	          
	        row1.createCell(9).setCellValue("Fam. Afec.");
	        row1.getCell(9).setCellStyle(style_header);
	        
	        row1.createCell(10).setCellValue("Fam. damnif.");
	        row1.getCell(10).setCellStyle(style_header);
	        
//	        row1.createCell(12).setCellValue("Total fam.");
//	        row1.getCell(12).setCellStyle(style_header);
	        
	        row1.createCell(11).setCellValue("Pers. afec.");
	        row1.getCell(11).setCellStyle(style_header);
	        
	        row1.createCell(12).setCellValue("Pers. damn.");
	        row1.getCell(12).setCellStyle(style_header);
	        
//	        row1.createCell(15).setCellValue("Total Pers.");
//	        row1.getCell(15).setCellStyle(style_header);
	        
	        row1.createCell(13).setCellValue("Fam. Afec. REAL");
	        row1.getCell(13).setCellStyle(style_header);
	        
	        row1.createCell(14).setCellValue("Fam. damnif. REAL");
	        row1.getCell(14).setCellStyle(style_header);
	        
//	        row1.createCell(18).setCellValue("Total fam. REAL");
//	        row1.getCell(18).setCellStyle(style_header);
	        
	        row1.createCell(15).setCellValue("Pers. afec. REAL");
	        row1.getCell(15).setCellStyle(style_header);
	        
	        row1.createCell(16).setCellValue("Pers. damn. REAL");
	        row1.getCell(16).setCellStyle(style_header);
	        
//	        row1.createCell(21).setCellValue("Total Pers. REAL");
//	        row1.getCell(21).setCellStyle(style_header);
	        
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
	        	
//	        	rows.createCell(1).setCellValue(row);
//		        rows.getCell(1).setCellStyle(style_cell);
		        
		        
		        rows.createCell(1).setCellValue(emergencia.getFkIdRequerimientoDamni());
		        rows.getCell(1).setCellStyle(style_cell);
		        
		        rows.createCell(2).setCellValue(emergencia.getFkIdRequerimiento());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(emergencia.getCodDistrito());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        

		        rows.createCell(4).setCellValue(emergencia.getDesDepartamento());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(emergencia.getDesProvincia());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(emergencia.getDesDistrito());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(emergencia.getIdEmergencia());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(emergencia.getPoblacionINEI());
		        rows.getCell(8).setCellStyle(style_cell);
		        
		        rows.createCell(9).setCellValue(emergencia.getFamAfectado());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(emergencia.getFamDamnificado());
		        rows.getCell(10).setCellStyle(style_cell);
		        
//		        rows.createCell(12).setCellValue(emergencia.getTotalFam());
//		        rows.getCell(12).setCellStyle(style_cell);
		        
		        rows.createCell(11).setCellValue(emergencia.getPersoAfectado());
		        rows.getCell(11).setCellStyle(style_cell);
		        
//		        rows.createCell(11).setCellValue(emergencia.getPoblacionINEI());
//		        rows.getCell(11).setCellStyle(style_cell);
		        
		        rows.createCell(12).setCellValue(emergencia.getPersoDamnificado());
		        rows.getCell(12).setCellStyle(style_cell);
		        
//		        rows.createCell(15).setCellValue(emergencia.getTotalPerso());
//		        rows.getCell(15).setCellStyle(style_cell);
		        
		        rows.createCell(13).setCellValue(emergencia.getFamAfectadoReal());
		        rows.getCell(13).setCellStyle(style_cell);
		        
		        rows.createCell(14).setCellValue(emergencia.getFamDamnificadoReal());
		        rows.getCell(14).setCellStyle(style_cell);
		        
//		        rows.createCell(18).setCellValue(emergencia.getTotalFamReal());
//		        rows.getCell(18).setCellStyle(style_cell);
		        
		        rows.createCell(15).setCellValue(emergencia.getPersoAfectadoReal());
		        rows.getCell(15).setCellStyle(style_cell);
		       
		        rows.createCell(16).setCellValue(emergencia.getPersoDamnificadoReal());
		        rows.getCell(16).setCellStyle(style_cell);
		        
//		        rows.createCell(21).setCellValue(emergencia.getTotalPersoReal());
//		        rows.getCell(21).setCellStyle(style_cell);
		        
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