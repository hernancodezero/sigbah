package pe.com.sigbah.web.report.gestion_almacenes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
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

import pe.com.sigbah.common.bean.DocumentoIngresoBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: ReporteOrdenIngreso.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteOrdenIngreso implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteOrdenIngreso.class.getName());
	
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
	public HSSFWorkbook generaReporteExcelOrdenIngreso(List<OrdenIngresoBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE ORDEN DE INGRESO");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 6500);
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
	        
	        row1.createCell(3).setCellValue("DDI");
	        row1.getCell(3).setCellStyle(style_header);
	        
	        row1.createCell(4).setCellValue("Almacén");
	        row1.getCell(4).setCellStyle(style_header);
	        
	        row1.createCell(5).setCellValue("N° Orden de Ingreso");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("Fecha");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("Tipo de Movimiento");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("Estado");
	        row1.getCell(8).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (OrdenIngresoBean ingreso : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(ingreso.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(ingreso.getNombreDdi());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(ingreso.getNombreAlmacen());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(ingreso.getNroOrdenIngreso());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(ingreso.getFechaEmision());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(ingreso.getNombreMovimiento());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(ingreso.getNombreEstado());
		        rows.getCell(8).setCellStyle(style_cell);
	            
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
	 * @param ordenIngreso
	 * @param listaProducto
	 * @param listaDocumento
	 * @throws Exception 
	 */
	public void generaPDFReporteIngresos(String ruta, OrdenIngresoBean ordenIngreso, List<ProductoIngresoBean> listaProducto, 
										 List<DocumentoIngresoBean> listaDocumento) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f2_1 = {60, 40};
			
			float[] f2_2 = {40, 60};
			
			float[] f4_1 = {15, 25, 35, 25};
			
			float[] f4_2 = {10, 30, 30, 30};
			
			float[] f7 = {30, 2, 20, 2, 25, 2, 19};

			float[] f8 = {5, 30, 10, 10, 10, 10, 10, 15};

			Paragraph p     = null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
			
			BaseColor header = new BaseColor(242, 242, 242);
			   
			
			table = new PdfPTable(3);
			table.setWidths(ftit);
			
			String path = ruta.substring(0, ruta.indexOf("resources"));			
			Image img = Image.getInstance(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			cell = new PdfPCell(img, true);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			StringBuilder nroOrdenIngreso = new StringBuilder();
			nroOrdenIngreso.append(ordenIngreso.getCodigoAnio());
			nroOrdenIngreso.append(Constantes.SEPARADOR);
			nroOrdenIngreso.append(ordenIngreso.getCodigoDdi());
			nroOrdenIngreso.append(Constantes.SEPARADOR);
			nroOrdenIngreso.append(ordenIngreso.getNroOrdenIngreso());
			
			p = new Paragraph("Orden de Ingreso N° ".concat(nroOrdenIngreso.toString()), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
						
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
						
			table = new PdfPTable(4);
			table.setWidths(f4_1);
			
			p = new Paragraph("Año : ".concat(ordenIngreso.getCodigoAnio()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("DDI : ".concat(ordenIngreso.getNombreDdi()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("Almacén : ".concat(getString(ordenIngreso.getNombreAlmacen())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph(Constantes.FECHA_ENCABEZADO_REPORTE.concat(ordenIngreso.getFechaEmision()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea

			
			table = new PdfPTable(2);
			table.setWidths(f2_1);
			
			p = new Paragraph("Tipo de Movimiento : ".concat(ordenIngreso.getNombreMovimiento()), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("Almacen Origen : ".concat(getString(ordenIngreso.getNombreAlmacenProcedencia())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(2);
			table.setWidths(f2_2);
			
			p = new Paragraph("N° Orden de Compra : ".concat(getString(ordenIngreso.getNroOrdenCompra())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("Concepto : ".concat(getString(ordenIngreso.getConcepto())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Nombre Proveedor : ".concat(getString(ordenIngreso.getRazonSocial())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Responsable Recepción : ".concat(getString(ordenIngreso.getResponsable())), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			table = new PdfPTable(4);
			table.setWidths(f4_2);
			
			p = new Paragraph("N°", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Documentos Adjuntos", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Nro", negrita);
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
			
			for (DocumentoIngresoBean documento : listaDocumento) {
			
				table = new PdfPTable(4);
				table.setWidths(f4_2);
				
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
			
			p = new Paragraph("Precio Unitario", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Importe Total", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Fecha Vencimiento", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			
			
			int row_pro = 1;
			
			for (ProductoIngresoBean producto : listaProducto) {
			
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
				
				p = new Paragraph(getString(producto.getFechaVencimiento()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			
			p = new Paragraph(ordenIngreso.getObservacion(), normal);
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
			
			
			table = new PdfPTable(7);
			table.setWidths(f7);
			
			p = new Paragraph("Responsable Abastecimiento ", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Jefe Almacén", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Responsable Almacén", normal);
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