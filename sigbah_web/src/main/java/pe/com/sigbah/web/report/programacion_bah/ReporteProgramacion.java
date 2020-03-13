package pe.com.sigbah.web.report.programacion_bah;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pe.com.sigbah.common.bean.ProductoAlimentoBean;
import pe.com.sigbah.common.bean.ProductoNoAlimentarioProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionAlimentoBean;
import pe.com.sigbah.common.bean.ProgramacionBean;
import pe.com.sigbah.common.bean.ProgramacionNoAlimentarioBean;
import pe.com.sigbah.common.bean.RacionOperativaBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.Utils;

/**
 * @className: ReporteProgramacion.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteProgramacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteProgramacion.class.getName());
	
	/**
	 * @param lista
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelProgramacion(List<ProgramacionBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE PROGRAMACIONES");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 4000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 6000);
	        sheet.setColumnWidth(6, 9000);
			sheet.setColumnWidth(7, 9000);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(10, 6000);
	        
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
	        
	        row1.createCell(1).setCellValue("Nº");
	        row1.getCell(1).setCellStyle(style_header);
	        
	        row1.createCell(2).setCellValue("Año");
	        row1.getCell(2).setCellStyle(style_header);
	        
	        row1.createCell(3).setCellValue("Mes");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        row1.createCell(4).setCellValue("N° Programación");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("Fecha Programación");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Fenómeno");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Emergencia");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("N° DEE");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        row1.createCell(9).setCellValue("Estado");
	        row1.getCell(9).setCellStyle(style_header);
	        
	        row1.createCell(10).setCellValue("Región Destino");
	        row1.getCell(10).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (ProgramacionBean programacion : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(programacion.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(programacion.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(programacion.getNroProgramacion());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(programacion.getFechaProgramacion());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(programacion.getNombreFenomeno());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(programacion.getNombreProgramacion());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(programacion.getNroDee());
		        rows.getCell(8).setCellStyle(style_cell);
		        
		        rows.createCell(9).setCellValue(programacion.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(programacion.getNombreRegion());
		        rows.getCell(10).setCellStyle(style_cell);
	            
	            row++;	
	        }
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}
	
	/**
	 * @param workbook
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
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

	/**
	 * @param listaProgramacionAlimento 
	 * @param arrIdProducto
	 * @param arrNombreProducto
	 * @param arrUnidadProducto 
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelAlimento(List<ProgramacionAlimentoBean> listaProgramacionAlimento,
												   List<Integer> arrIdProducto, 
												   List<String> arrNombreProducto, 
												   List<BigDecimal> arrUnidadProducto) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE ALIMENTOS");
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 6000);
	        sheet.setColumnWidth(6, 6000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(9, 6000);
			sheet.setColumnWidth(10, 6000);
			sheet.setColumnWidth(11, 6000);
			sheet.setColumnWidth(12, 6000);
			sheet.setColumnWidth(13, 6000);
			sheet.setColumnWidth(14, 6000);
			sheet.setColumnWidth(15, 6000);
			sheet.setColumnWidth(16, 6000);
			sheet.setColumnWidth(17, 6000);
			sheet.setColumnWidth(18, 6000);
			sheet.setColumnWidth(19, 6000);
			sheet.setColumnWidth(20, 6000);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
	        
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
	        
	        row1.createCell(1).setCellValue("Nº");
	        row1.getCell(1).setCellStyle(style_header);
	        
	        row1.createCell(2).setCellValue("Departamento");
	        row1.getCell(2).setCellStyle(style_header);
	        
	        row1.createCell(3).setCellValue("Provincia");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        row1.createCell(4).setCellValue("Distrito");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("Pers. Afect.");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Pers. Dam.");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Total Pers.");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Total Raciones");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        int row1_9 = 9;
	        for (String producto : arrNombreProducto) {
	        	row1.createCell(row1_9).setCellValue(producto);
		        row1.getCell(row1_9).setCellStyle(style_header);
		        row1_9++;
	        }
	        
	        row1.createCell(row1_9).setCellValue("Total (TM)");
	        row1.getCell(row1_9).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_2.setFont(font_norm);	        
	        style_cell_2.setBorderBottom((short) 1);
	        style_cell_2.setBorderLeft((short) 1);	        
	        style_cell_2.setBorderRight((short) 1);
	        style_cell_2.setBorderTop((short) 1);	        

	        for (ProgramacionAlimentoBean alimento : listaProgramacionAlimento) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(alimento.getDepartamento());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(alimento.getProvincia());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(alimento.getDistrito());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(dec_form.format(alimento.getPersAfect()));
		        rows.getCell(5).setCellStyle(style_cell_2);
		        
		        rows.createCell(6).setCellValue(dec_form.format(alimento.getPersDam()));
		        rows.getCell(6).setCellStyle(style_cell_2);
		        
		        rows.createCell(7).setCellValue(dec_form.format(alimento.getTotalPers()));
		        rows.getCell(7).setCellStyle(style_cell_2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(alimento.getTotalRaciones()));
		        rows.getCell(8).setCellStyle(style_cell_2);
		        
		        int row2_9 = 9;
		        for (ProductoAlimentoBean producto : alimento.getListaProducto()) {
		        	rows.createCell(row2_9).setCellValue(dec_form.format(producto.getUnidad()));
			        rows.getCell(row2_9).setCellStyle(style_cell_2);
			        row2_9++;
		        }

		        rows.createCell(row2_9).setCellValue(dec_form.format(alimento.getTotalTm()));
		        rows.getCell(row2_9).setCellStyle(style_cell_2);
	            
	            row++;	
	        }
	        
	        HSSFRow row_total  = sheet.createRow((short) row + 1);
        	
	        row_total.createCell(4).setCellValue("Total:");
	        row_total.getCell(4).setCellStyle(style_cell_2);

	        int row3_5 = 5;
//	        for (BigDecimal unidad : arrUnidadProducto) {
//	        	row_total.createCell(row3_5).setCellValue(dec_form.format(unidad));
//		        row_total.getCell(row3_5).setCellStyle(style_cell_2);
//		        row3_5++;
//	        }

            row++;
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param listaProgramacionNoAlimentario
	 * @param arrIdProducto
	 * @param arrNombreProducto
	 * @param arrUnidadProducto
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelNoAlimentario(List<ProgramacionNoAlimentarioBean> listaProgramacionNoAlimentario, 
														List<Integer> arrIdProducto,
														List<String> arrNombreProducto, 
														List<BigDecimal> arrUnidadProducto) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE NO ALIMENTARIOS");
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 6000);
	        sheet.setColumnWidth(3, 6000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 6000);
	        sheet.setColumnWidth(6, 6000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(9, 6000);
			sheet.setColumnWidth(10, 6000);
			sheet.setColumnWidth(11, 6000);
			sheet.setColumnWidth(12, 6000);
			sheet.setColumnWidth(13, 6000);
			sheet.setColumnWidth(14, 6000);
			sheet.setColumnWidth(15, 6000);
			sheet.setColumnWidth(16, 6000);
			sheet.setColumnWidth(17, 6000);
			sheet.setColumnWidth(18, 6000);
			sheet.setColumnWidth(19, 6000);
			sheet.setColumnWidth(20, 6000);
			sheet.setColumnWidth(21, 6000);
			sheet.setColumnWidth(22, 6000);
			sheet.setColumnWidth(23, 6000);
			sheet.setColumnWidth(24, 6000);
			sheet.setColumnWidth(25, 6000);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
	        
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
	        
	        row1.createCell(1).setCellValue("Nº");
	        row1.getCell(1).setCellStyle(style_header);
	        
	        row1.createCell(2).setCellValue("Departamento");
	        row1.getCell(2).setCellStyle(style_header);
	        
	        row1.createCell(3).setCellValue("Provincia");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        row1.createCell(4).setCellValue("Distrito");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("Fam. Afect.");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Fam. Dam.");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Total Fam.");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Pers. Afect.");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        row1.createCell(9).setCellValue("Pers. Dam.");
	        row1.getCell(9).setCellStyle(style_header);
	        
	        row1.createCell(10).setCellValue("Total Pers.");
	        row1.getCell(10).setCellStyle(style_header);
	        
	        int row1_11 = 11;
	        for (String producto : arrNombreProducto) {
	        	row1.createCell(row1_11).setCellValue(producto);
		        row1.getCell(row1_11).setCellStyle(style_header);
		        row1_11++;
	        }
	        
//	        row1.createCell(row1_11).setCellValue("Total (TM)");
//	        row1.getCell(row1_11).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_2.setFont(font_norm);	        
	        style_cell_2.setBorderBottom((short) 1);
	        style_cell_2.setBorderLeft((short) 1);	        
	        style_cell_2.setBorderRight((short) 1);
	        style_cell_2.setBorderTop((short) 1);

	        for (ProgramacionNoAlimentarioBean alimento : listaProgramacionNoAlimentario) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(alimento.getDepartamento());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(alimento.getProvincia());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(alimento.getDistrito());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(dec_form.format(alimento.getFamAfect()));
		        rows.getCell(5).setCellStyle(style_cell_2);
		        
		        rows.createCell(6).setCellValue(dec_form.format(alimento.getFamDam()));
		        rows.getCell(6).setCellStyle(style_cell_2);
		        
		        rows.createCell(7).setCellValue(dec_form.format(alimento.getTotalFam()));
		        rows.getCell(7).setCellStyle(style_cell_2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(alimento.getPersAfect()));
		        rows.getCell(8).setCellStyle(style_cell_2);
		        
		        rows.createCell(9).setCellValue(dec_form.format(alimento.getPersDam()));
		        rows.getCell(9).setCellStyle(style_cell_2);
		        
		        rows.createCell(10).setCellValue(dec_form.format(alimento.getTotalPers()));
		        rows.getCell(10).setCellStyle(style_cell_2);
		        
		        int row2_11 = 11;
		        for (ProductoAlimentoBean producto : alimento.getListaProducto()) {
		        	rows.createCell(row2_11).setCellValue(dec_form.format(producto.getUnidad()));
			        rows.getCell(row2_11).setCellStyle(style_cell_2);
			        row2_11++;
		        }

//		        rows.createCell(row2_11).setCellValue(getString(alimento.getTotalTm()));
//		        rows.getCell(row2_11).setCellStyle(style_cell);
	            
	            row++;	
	        }
	        
	        HSSFRow row_total  = sheet.createRow((short) row + 1);
        	
	        row_total.createCell(4).setCellValue("Total:");
	        row_total.getCell(4).setCellStyle(style_cell_2);

	        int row3_5 = 5;
	        for (BigDecimal unidad : arrUnidadProducto) {
	        	row_total.createCell(row3_5).setCellValue(dec_form.format(unidad));
		        row_total.getCell(row3_5).setCellStyle(style_cell_2);
		        row3_5++;
	        }

            row++;
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta 
	 * @param programacion
	 * @param listaRacion
	 * @param listaAlimento
	 * @param listaTotalProductoAlimento 
	 * @param listaProductoNoAlimentario
	 * @param listaNoAlimentario
	 * @throws Exception 
	 */
	public void generaPDFReporteProgramacion(String ruta, 
											 ProgramacionBean programacion,
											 List<RacionOperativaBean> listaRacion, 
											 List<ProgramacionAlimentoBean> listaAlimento,
											 List<ProgramacionAlimentoBean> listaTotalProductoAlimento, 
											 List<ProductoNoAlimentarioProgramacionBean> listaProductoNoAlimentario,
											 List<ProgramacionNoAlimentarioBean> listaNoAlimentario) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			BaseColor header = new BaseColor(242, 242, 242);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
            DecimalFormat dec_form_1 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL_1, dec_for_symbols);
            DecimalFormat dec_form_2 = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			   
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(ftit);
			
			String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));			
			Image img = Image.getInstance(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			cell = new PdfPCell(img, true);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append(Constantes.TITULO_ENCABEZADO_REPORTE);
			det_encabezado.append(Constantes.SALTO_LINEA_PARRAFO);
			det_encabezado.append(Constantes.FECHA_ENCABEZADO_REPORTE);
			Date fecha_hora = Calendar.getInstance().getTime();
			det_encabezado.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));
			det_encabezado.append(Constantes.SALTO_LINEA_PARRAFO);
			det_encabezado.append(Constantes.HORA_ENCABEZADO_REPORTE);
			det_encabezado.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
			p = new Paragraph(det_encabezado.toString(), encabezado);
			pdet = new Paragraph(Constantes.ESPACIO_ENCABEZADO_REPORTE_PDF, hide);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Programación de Requerimiento", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin	
			
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("N° ".concat(programacion.getNroProgramacion()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Fecha: ".concat(programacion.getFechaProgramacion()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Nombre : ", negrita);
			pdet = new Paragraph(programacion.getNombreProgramacion(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			

			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("N° DEE: ", negrita);
			pdet = new Paragraph(programacion.getNroDee(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			

			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Región Destino : ", negrita);
			pdet = new Paragraph(programacion.getNombreRegion(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("N° Requerimiento : ", negrita);
			pdet = new Paragraph(programacion.getCodRequerimiento(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("N° Ración : ", negrita);
			pdet = new Paragraph(programacion.getCodRacion(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			if (programacion.getTipoAtencion().equals(Constantes.ONE_INT) || 
					programacion.getTipoAtencion().equals(Constantes.THREE_INT)) { // Alimentos ó Ambos
				
				// float[] f9 = {5, 25, 10, 10, 10, 10, 10, 10, 10};
				
				// Bloque Inicio
				table = new PdfPTable(1);
				table.setWidths(f1);
				
				p = new Paragraph("Alimentos", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBackgroundColor(header);
				table.addCell(cell);
				
				document.add(table);
				// Bloque Fin
				
				
				document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
				
			
				int can_alimentos = 9 + listaRacion.size();
				
				// Bloque Inicio
				table = new PdfPTable(can_alimentos);
//				table.setWidths(f9);
				
				p = new Paragraph("N°", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Región", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Provincia", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Distrito", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Pers. Afect.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Pers. Dam.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Total Pers.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Total Raciones", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
								
				for (RacionOperativaBean racion : listaRacion) {
			        p = new Paragraph(racion.getNombreProducto(), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
		        }
				System.out.println("inicio");
				p = new Paragraph("Total (TM)", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);
				// Bloque Fin
				
				
				// Bloque Inicio
				int row_pro = 1;
				if (!Utils.isEmpty(listaAlimento) ) {
					
					BigDecimal cantitadTotalPersAfect = BigDecimal.ZERO;
					BigDecimal cantitadTotalPersDam = BigDecimal.ZERO;
					BigDecimal cantitadTotalTotalPers = BigDecimal.ZERO;
					BigDecimal cantitadTotalTotalRaciones = BigDecimal.ZERO;
					BigDecimal cantitadTotalTotalTm = BigDecimal.ZERO;
					//Definimos un array de filas x columnas
					BigDecimal arrayAlimento[][] = new BigDecimal[listaAlimento.size()][can_alimentos];
					List<BigDecimal> arrUnidadProducto = new ArrayList<BigDecimal>();
					List<BigDecimal> arrUnidadMetrica = new ArrayList<BigDecimal>();
					String departamento = Constantes.EMPTY;
					String provincia = Constantes.EMPTY;
					
					for (ProgramacionAlimentoBean alimento : listaAlimento) {
					
						table = new PdfPTable(can_alimentos);
//						table.setWidths(f9);
						
						p = new Paragraph(String.valueOf(row_pro), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						if (!departamento.equals(alimento.getDepartamento())) {
							departamento = alimento.getDepartamento();
						}
						
						p = new Paragraph(departamento, normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//						cell.setRowspan(100);
						table.addCell(cell);
						
						if (!provincia.equals(alimento.getProvincia())) {
							provincia = alimento.getProvincia();
						}
						
						p = new Paragraph(provincia, normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						p = new Paragraph(alimento.getDistrito(), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("inicio1 "+alimento.getPersAfect());
						p = new Paragraph(dec_form.format(alimento.getPersAfect()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("inicio2");
						p = new Paragraph(dec_form.format(alimento.getPersDam()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("inicio3");
						p = new Paragraph(dec_form.format(alimento.getTotalPers()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("inicio4");
						p = new Paragraph(dec_form.format(alimento.getTotalRaciones()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("inicio5");
						int index = 0;
				        for (ProductoAlimentoBean producto : alimento.getListaProducto()) {
					        p = new Paragraph(dec_form.format(producto.getUnidad()), normal);
							cell = new PdfPCell(p);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							table.addCell(cell);
							// Asignamos un valor al array, en la fila 0 columna 1
							arrayAlimento[row_pro - 1][index] = producto.getUnidad() == null ? BigDecimal.ZERO : producto.getUnidad();							
							index++;
				        }						
				        System.out.println("inicio6");
						p = new Paragraph(dec_form.format(alimento.getTotalTm()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						document.add(table);	             
						
						row_pro++;
						System.out.println("inicio7");
						cantitadTotalPersAfect = cantitadTotalPersAfect.add(alimento.getPersAfect() == null ? BigDecimal.ZERO : alimento.getPersAfect());
						cantitadTotalPersDam = cantitadTotalPersDam.add(alimento.getPersDam() == null ? BigDecimal.ZERO : alimento.getPersDam());
						cantitadTotalTotalPers = cantitadTotalTotalPers.add(alimento.getTotalPers() == null ? BigDecimal.ZERO : alimento.getTotalPers());
						cantitadTotalTotalRaciones = cantitadTotalTotalRaciones.add(alimento.getTotalRaciones() == null ? BigDecimal.ZERO : alimento.getTotalRaciones());
						cantitadTotalTotalTm = cantitadTotalTotalTm.add(alimento.getTotalTm() == null ? BigDecimal.ZERO : alimento.getTotalTm());
					}
					
					
					// Bloque Inicio
					table = new PdfPTable(can_alimentos);
//					table.setWidths(f9);
					
					p = new Paragraph("Cantidad Total", normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(4);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("inicio8");
					p = new Paragraph(dec_form.format(cantitadTotalPersAfect), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("inicio9");
					p = new Paragraph(dec_form.format(cantitadTotalPersDam), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("inicio10");
					p = new Paragraph(dec_form.format(cantitadTotalTotalPers), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("inicio11");
					p = new Paragraph(dec_form.format(cantitadTotalTotalRaciones), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("inicio12");
					// Recorremos el array multidimensional
			        for (int j = 0; j < listaRacion.size(); j++) {			        		
			        	BigDecimal cantitadTotalUnidad = BigDecimal.ZERO;
					    for (int i = 0; i < arrayAlimento.length; i++) {			        		
					    	cantitadTotalUnidad = cantitadTotalUnidad.add(arrayAlimento[i][j]);
			        	}
					    arrUnidadProducto.add(cantitadTotalUnidad);
			        }
			        System.out.println("inicio13");
					for (BigDecimal unidad : arrUnidadProducto) {
				        p = new Paragraph(dec_form.format(unidad), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
			            cell.setRowspan(1);
						table.addCell(cell);
			        }
					System.out.println("inicio14");
					p = new Paragraph(dec_form.format(cantitadTotalTotalTm), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					
					document.add(table);
					// Bloque Fin
					
					System.out.println("inicio15");
					if (!Utils.isEmpty(listaTotalProductoAlimento)) {
						for (ProgramacionAlimentoBean alimento : listaTotalProductoAlimento) {
							
							// Bloque Inicio
							table = new PdfPTable(can_alimentos);
//							table.setWidths(f9);
							
							p = new Paragraph("Cantidad Total en Kilogramos", normal);
							cell = new PdfPCell(p);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setColspan(8);
				            cell.setRowspan(1);
							table.addCell(cell);
							System.out.println("inicio16");
							BigDecimal totalTmKilogramo = BigDecimal.ZERO;
							int index = 0;
					        for (ProductoAlimentoBean producto : alimento.getListaProducto()) {					        	
					        	BigDecimal totalKilogramo = producto.getUnidad() == null ? BigDecimal.ZERO : producto.getUnidad();
					        	totalKilogramo = (totalKilogramo.multiply(arrUnidadProducto.get(index))).setScale(1, BigDecimal.ROUND_HALF_UP);
					        	
						        p = new Paragraph(dec_form_1.format(totalKilogramo), normal);
								cell = new PdfPCell(p);
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
								cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
								cell.setColspan(1);
					            cell.setRowspan(1);
								table.addCell(cell);
								
								index++;
								totalTmKilogramo = totalTmKilogramo.add(totalKilogramo);
								arrUnidadMetrica.add(totalKilogramo);
					        }
					        System.out.println("inicio18");
							p = new Paragraph(dec_form_1.format(totalTmKilogramo), normal);
							cell = new PdfPCell(p);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setColspan(1);
				            cell.setRowspan(1);
							table.addCell(cell);
							
							document.add(table);
							
							// Bloque Fin
						}
						
						// Bloque Inicio
						table = new PdfPTable(can_alimentos);
//						table.setWidths(f9);
						
						p = new Paragraph("Cantidad Total en Toneladas Metricas", normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(8);
			            cell.setRowspan(1);
						table.addCell(cell);
						
						BigDecimal totalTmMetrica = BigDecimal.ZERO;
						for (BigDecimal unidad : arrUnidadMetrica) {
				        	BigDecimal totalMetrica = (unidad.divide(new BigDecimal(1000))).setScale(2, BigDecimal.ROUND_HALF_UP);
				        	
					        p = new Paragraph(dec_form_2.format(totalMetrica), normal);
							cell = new PdfPCell(p);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setColspan(1);
				            cell.setRowspan(1);
							table.addCell(cell);
							
							totalTmMetrica = totalTmMetrica.add(totalMetrica);
				        }
	
						p = new Paragraph(dec_form_2.format(totalTmMetrica), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
			            cell.setRowspan(1);
						table.addCell(cell);
						
						document.add(table);
						
						// Bloque Fin
						
						
						// Salto de pagina
						document.newPage();
						
					}
					
					
				}
				// Bloque Fin
				
				document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
				
	    	}
			
			if (programacion.getTipoAtencion().equals(Constantes.TWO_INT) || 
					programacion.getTipoAtencion().equals(Constantes.THREE_INT)) { // No Alimentarios ó Ambos
				
				// float[] f9 = {5, 25, 10, 10, 10, 10, 10, 10, 10};
				
				// Bloque Inicio
				table = new PdfPTable(1);
				table.setWidths(f1);
				
				p = new Paragraph("Bienes No Alimentarios", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setBackgroundColor(header);
				table.addCell(cell);
				
				document.add(table);
				// Bloque Fin
				
				
				document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
				
			
				int can_no_alimentarios = 10 + listaProductoNoAlimentario.size();
				
				// Bloque Inicio
				table = new PdfPTable(can_no_alimentarios);
//				table.setWidths(f10);
				
				p = new Paragraph("N°", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Región", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Provincia", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Distrito", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Fam. Afect.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Fam. Dam.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Total Fam.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Pers. Afect.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Pers. Dam.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph("Total Pers.", negrita);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
								
				for (ProductoNoAlimentarioProgramacionBean producto : listaProductoNoAlimentario) {
			        p = new Paragraph(producto.getNombreProducto(), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
		        }

				document.add(table);
				// Bloque Fin
				
				
				// Bloque Inicio
				int row_pro = 1;
				if (!Utils.isEmpty(listaNoAlimentario) ) {
					
					BigDecimal cantitadTotalFamAfect = BigDecimal.ZERO;
					BigDecimal cantitadTotalFamDam = BigDecimal.ZERO;
					BigDecimal cantitadTotalTotalFam = BigDecimal.ZERO;
					BigDecimal cantitadTotalPersAfect = BigDecimal.ZERO;
					BigDecimal cantitadTotalPersDam = BigDecimal.ZERO;
					BigDecimal cantitadTotalTotalPers = BigDecimal.ZERO;
					//Definimos un array de filas x columnas
					BigDecimal arrayNoAlimento[][] = new BigDecimal[listaNoAlimentario.size()][can_no_alimentarios];
					List<BigDecimal> arrUnidadProducto = new ArrayList<BigDecimal>();
					System.out.println("a");
					for (ProgramacionNoAlimentarioBean noAlimentario : listaNoAlimentario) {
					
						table = new PdfPTable(can_no_alimentarios);
//						table.setWidths(f10);
						
						p = new Paragraph(String.valueOf(row_pro), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
					
						p = new Paragraph(noAlimentario.getDepartamento(), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						p = new Paragraph(noAlimentario.getProvincia(), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						p = new Paragraph(noAlimentario.getDistrito(), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("b");
						p = new Paragraph(dec_form.format(noAlimentario.getFamAfect()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("c");
						p = new Paragraph(dec_form.format(noAlimentario.getFamDam()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("d");
						p = new Paragraph(dec_form.format(noAlimentario.getTotalFam()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("e");
						p = new Paragraph(dec_form.format(noAlimentario.getPersAfect()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("f");
						p = new Paragraph(dec_form.format(noAlimentario.getPersDam()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("g");
						p = new Paragraph(dec_form.format(noAlimentario.getTotalPers()), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						System.out.println("h");
						int index = 0;
				        for (ProductoAlimentoBean producto : noAlimentario.getListaProducto()) {
					        p = new Paragraph(dec_form.format(producto.getUnidad()), normal);
							cell = new PdfPCell(p);
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							table.addCell(cell);
							// Asignamos un valor al array, en la fila 0 columna 1
							arrayNoAlimento[row_pro - 1][index] = producto.getUnidad() == null ? BigDecimal.ZERO : producto.getUnidad();							
							index++;
				        }						

						document.add(table);	             
						
						row_pro++;
						System.out.println("i");
						cantitadTotalFamAfect = cantitadTotalFamAfect.add(noAlimentario.getFamAfect() == null ? BigDecimal.ZERO : noAlimentario.getFamAfect());
						cantitadTotalFamDam = cantitadTotalFamDam.add(noAlimentario.getFamDam() == null ? BigDecimal.ZERO : noAlimentario.getFamDam());
						cantitadTotalTotalFam = cantitadTotalTotalFam.add(noAlimentario.getTotalFam() == null ? BigDecimal.ZERO : noAlimentario.getTotalFam());
						cantitadTotalPersAfect = cantitadTotalPersAfect.add(noAlimentario.getPersAfect() == null ? BigDecimal.ZERO : noAlimentario.getPersAfect());
						cantitadTotalPersDam = cantitadTotalPersDam.add(noAlimentario.getPersDam() == null ? BigDecimal.ZERO : noAlimentario.getPersDam());
						cantitadTotalTotalPers = cantitadTotalTotalPers.add(noAlimentario.getTotalPers() == null ? BigDecimal.ZERO : noAlimentario.getTotalPers());
					}
					System.out.println("j");
					
					// Bloque Inicio
					table = new PdfPTable(can_no_alimentarios);
//					table.setWidths(f10);
					System.out.println("k");
					p = new Paragraph("Cantidad Total", normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(4);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("1");
					p = new Paragraph(dec_form.format(cantitadTotalFamAfect), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("2");
					p = new Paragraph(dec_form.format(cantitadTotalFamDam), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("3");
					p = new Paragraph(dec_form.format(cantitadTotalTotalFam), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("4");
					p = new Paragraph(dec_form.format(cantitadTotalPersAfect), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("5");
					p = new Paragraph(dec_form.format(cantitadTotalPersDam), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("6");
					p = new Paragraph(dec_form.format(cantitadTotalTotalPers), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setColspan(1);
		            cell.setRowspan(1);
					table.addCell(cell);
					System.out.println("7");
					// Recorremos el array multidimensional
			        for (int j = 0; j < listaProductoNoAlimentario.size(); j++) {			        		
			        	BigDecimal cantitadTotalUnidad = BigDecimal.ZERO;
					    for (int i = 0; i < arrayNoAlimento.length; i++) {			        		
					    	cantitadTotalUnidad = cantitadTotalUnidad.add(arrayNoAlimento[i][j]);
			        	}
					    arrUnidadProducto.add(cantitadTotalUnidad);
			        }
			        System.out.println("8");	
					for (BigDecimal unidad : arrUnidadProducto) {
				        p = new Paragraph(dec_form.format(unidad), normal);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setColspan(1);
			            cell.setRowspan(1);
						table.addCell(cell);
			        }
					System.out.println("9");
					document.add(table);
					// Bloque Fin
					
				}
				// Bloque Fin
				
				
	    	}
				
			
		} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
    
}