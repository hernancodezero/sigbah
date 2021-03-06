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

import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: ReporteRequerimiento.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: JHuaman.
 */
public class ReporteRequerimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteRequerimiento.class.getName());
	
	/**
	 * @param lista
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelRequerimiento(List<RequerimientoBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE REQUERIMIENTO");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 8000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 4000);
	        
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
	        
	        row1.createCell(5).setCellValue("Nro. Requerimiento");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Descripción");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Fenómeno");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Región Destino");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (RequerimientoBean requerimiento : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(requerimiento.getCodAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(requerimiento.getNomMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(requerimiento.getFechaRequerimiento());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(requerimiento.getNumRequerimiento());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(requerimiento.getNomRequerimiento());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(requerimiento.getDescFenomeno());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(requerimiento.getNomRegion());
		        rows.getCell(8).setCellStyle(style_cell);
		        
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