package pe.com.sigbah.web.report.gestion_almacenes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pe.com.sigbah.common.bean.DocumentoSalidaBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;

/**
 * @className: ReporteOrdenSalida.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteOrdenSalida implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteOrdenSalida.class.getName());
	
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
	 * @param lista
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaReporteExcelOrdenSalida(List<OrdenSalidaBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE ORDEN DE SALIDA");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 7000);
			sheet.setColumnWidth(8, 6000);
	        
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
	        
	        row1.createCell(4).setCellValue("Almacén");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("N° Orden de Salida");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Fecha");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Tipo de Movimiento");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("N° Guia de Remision");
	        row1.getCell(8).setCellStyle(style_header);
	        
	        row1.createCell(9).setCellValue("Estado");
	        row1.getCell(9).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (OrdenSalidaBean ingreso : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(ingreso.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(ingreso.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(ingreso.getNombreAlmacen());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(ingreso.getNroOrdenSalida());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(ingreso.getFechaEmision());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(ingreso.getNombreMovimiento());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(ingreso.getNroGuiaRemision());
		        rows.getCell(8).setCellStyle(style_cell);
		        
		        rows.createCell(9).setCellValue(ingreso.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell);
	            
	            row++;	
	        }
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}
	
	/**
	 * @param ruta
	 * @param ordenSalida
	 * @param listaProducto
	 * @param listaDocumento
	 * @throws Exception 
	 */
	public void generaPDFReporteSalidas(String ruta, OrdenSalidaBean ordenSalida, List<ProductoSalidaBean> listaProducto, 
										List<DocumentoSalidaBean> listaDocumento) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4, 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f5_1 = {5, 25, 30, 20, 20};
			
			float[] f5_2 = {35, 5, 30, 5, 25};

			float[] f8 = {5, 20, 15, 10, 10, 10, 15, 15};

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
			
			BaseColor header = new BaseColor(242, 242, 242);
			   
			
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
			
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("ORDEN DE SALIDA N° ".concat(ordenSalida.getNroOrdenSalida()), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
		
						
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph(ordenSalida.getFechaEmision(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
						
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("DDI : ", negrita);
			pdet = new Paragraph(ordenSalida.getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			

			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("ALMACEN ORIGEN : ", negrita);
			pdet = new Paragraph(ordenSalida.getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			
		
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("TIPO MOVIMIENTO : ", negrita);
			pdet = new Paragraph(ordenSalida.getNombreMovimiento(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
		
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("ALMACEN DESTINO : ", negrita);
			pdet = new Paragraph(ordenSalida.getNombreAlmacenDestino(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			

			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(5);
			table.setWidths(f5_1);
			
			p = new Paragraph("N°", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Tipo Documento", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("N° Documento", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Fecha", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			
			
			int row_doc = 1;
			
			for (DocumentoSalidaBean documento : listaDocumento) {
			
				table = new PdfPTable(5);
				table.setWidths(f5_1);
				
				p = new Paragraph(String.valueOf(row_doc), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(documento.getNombreDocumento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(documento.getNroDocumento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(documento.getFechaDocumento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell);
			
				document.add(table);	             
				
				row_doc++;
			}	
				
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
				
			
			table = new PdfPTable(8);
			table.setWidths(f8);
			
			p = new Paragraph("N°", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Producto", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Unidad Medida", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Envase", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Lote", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Cantidad", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Precio Unitario (S/.)", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Importe Total (S/.)", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			
			
			int row_pro = 1;
			
			for (ProductoSalidaBean producto : listaProducto) {
			
				table = new PdfPTable(8);
				table.setWidths(f8);
				
				p = new Paragraph(String.valueOf(row_pro), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(producto.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(producto.getNombreUnidad(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(producto.getNombreEnvase(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(producto.getNroLote()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(producto.getCantidad()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(producto.getPrecioUnitario()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(producto.getImporteTotal()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row_pro++;
			}

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Observación: ", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph(ordenSalida.getObservacion(), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setFixedHeight(72f);
			table.addCell(cell);
			
			document.add(table);
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(5);
			table.setWidths(f5_2);
			
			p = new Paragraph("Responsable de Almacén", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Recicí Conforme", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			p = new Paragraph("Fecha", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			document.add(table);
			
			
		} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
	
	/**
	 * Retorna el valor parseado.
	 * @param campo - Valor del parámetro a evaluar, tipo Object.
	 * @return valor - Valor de la cadena.
	 */
	private static String getString(Object campo) {
		if (campo != null) {
			if (campo instanceof Integer) {
				return String.valueOf((Integer) campo);
			} else if (campo instanceof Long) {
				return String.valueOf((Long) campo);
			} else if (campo instanceof BigDecimal) {
				return String.valueOf((BigDecimal) campo);
			} else {
				return (String) campo;
			}
		}
		return Constantes.EMPTY; 	
	}
    
}