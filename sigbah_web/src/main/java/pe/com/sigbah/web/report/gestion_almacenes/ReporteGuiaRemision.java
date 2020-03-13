package pe.com.sigbah.web.report.gestion_almacenes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.crosstabs.fill.JRPercentageCalculatorFactory.BigDecimalPercentageCalculator;
import pe.com.sigbah.common.bean.DetalleActaEntregaBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.DetalleManifiestoCargaBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;
import pe.com.sigbah.common.util.Utils;

/**
 * @className: ReporteGuiaRemision.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteGuiaRemision implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteGuiaRemision.class.getName());
	
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
	public HSSFWorkbook generaReporteExcelGuiaRemision(List<GuiaRemisionBean> lista) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("REGISTRO DE PROYECTO MANIFIESTO");
	        
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 2000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 6000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(9, 6000);
	        
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
	        
	        row1.createCell(5).setCellValue("N° Orden de Salida");
	        row1.getCell(5).setCellStyle(style_header);
	        
	        row1.createCell(6).setCellValue("N° Guia de Remisión");
	        row1.getCell(6).setCellStyle(style_header);
	        
	        row1.createCell(7).setCellValue("N° Manifiesto de Carga");
	        row1.getCell(7).setCellStyle(style_header);
	        
	        row1.createCell(8).setCellValue("N° Acta de Entrega Recepción");
	        row1.getCell(8).setCellStyle(style_header);

	        row1.createCell(9).setCellValue("Tipo de Movimiento");
	        row1.getCell(9).setCellStyle(style_header);
	        
	        row1.createCell(10).setCellValue("Estado");
	        row1.getCell(10).setCellStyle(style_header);
	       
	        int row = 1;

	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        for (GuiaRemisionBean ingreso : lista) {
	        	
	        	HSSFRow rows  = sheet.createRow((short) row + 1);
	        	
	        	rows.createCell(1).setCellValue(row);
		        rows.getCell(1).setCellStyle(style_cell);

		        rows.createCell(2).setCellValue(ingreso.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell);
		        
		        rows.createCell(3).setCellValue(ingreso.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(ingreso.getFechaEmision());
		        rows.getCell(4).setCellStyle(style_cell);
		        
		        rows.createCell(5).setCellValue(ingreso.getNroGuiaRemision());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(ingreso.getNroGuiaRemision());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(ingreso.getNroManifiestoCarga());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(ingreso.getNroActaEntregaRecepcion());
		        rows.getCell(8).setCellStyle(style_cell);
		        
		        rows.createCell(9).setCellValue(ingreso.getNombreMovimiento());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(ingreso.getNombreEstado());
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
	 * @param ruta
	 * @param listaGuiaRemision
	 * @throws Exception 
	 */
	public void generaPDFReporteGuiaRemision(String ruta, List<DetalleGuiaRemisionBean> listaGuiaRemision) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4);
			document.setMargins(5, 5, 10, 10);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
//			float[] ftit = {15, 15, 10, 60};			
			
			float[] f1 = {100};
			
			float[] f2 = {50, 50};
			
			float[] f4 = {15, 2, 55, 28};
			
			float[] f5 = {10, 30, 20, 20, 20};

			float[] f6 = {10, 20, 10, 30, 10, 20};

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPTable table_det = null;
			PdfPTable table_tra = null;
			PdfPCell cell   = null;
			PdfPCell cell_det = null;
			PdfPCell cell_tra = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
//			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			
			
			DetalleGuiaRemisionBean guiaRemision = null;
			if (!Utils.isEmpty(listaGuiaRemision)) {
				guiaRemision = listaGuiaRemision.get(0);
			} else {
				guiaRemision = new DetalleGuiaRemisionBean();
			}
			   
			
			table = new PdfPTable(4);
			table.setWidths(f4);
			
			String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));			
			Image img_ind = Image.getInstance(path.concat(Constantes.IMAGE_INDECI2_REPORT_PATH));
			cell = new PdfPCell(img_ind);
			cell.setBorder(PdfPCell.NO_BORDER);
			cell.setFixedHeight(35f);
			table.addCell(cell);	
			

			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			

			
			PdfPTable table_tit = new PdfPTable(1);
			table_tit.setWidths(f1);
			
			p = new Paragraph("INSTITUTO NACIONAL DE DEFENSA CIVIL", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tit.addCell(cell);
			
			p = new Paragraph("SEDE: ".concat(getString(guiaRemision.getSede())), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tit.addCell(cell);
			
			p = new Paragraph(Constantes.ESPACIO, encabezado);
			cell = new PdfPCell(p);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tit.addCell(cell);
			
			p = new Paragraph("Domicilio Fiscal: Calle Ricardo Angulo Ramirez Nro 869 Urb. Corpac - San Isidro - Lima", encabezado);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tit.addCell(cell);
			
			p = new Paragraph(guiaRemision.getDireccionPartida(), encabezado);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tit.addCell(cell);
			

			cell_det = new PdfPCell(table_tit);
			cell_det.setBorderColor(BaseColor.WHITE);
			table.addCell(cell_det);
			
			
			
			StringBuilder det_ruc = new StringBuilder();
			det_ruc.append("R.U.C. N° 20135890031");
			det_ruc.append(Constantes.SALTO_LINEA_PARRAFO);
			det_ruc.append("GUIA DE REMISION");
			det_ruc.append(Constantes.SALTO_LINEA_PARRAFO);
			det_ruc.append("REMITENTE");
			det_ruc.append(Constantes.SALTO_LINEA_PARRAFO);
			det_ruc.append("N° ");
			det_ruc.append(guiaRemision.getNroGuiaEmision());
			p = new Paragraph(det_ruc.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			document.add(table);
			
			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			
			
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			p = new Paragraph("PUNTO DE PARTIDA: ", negrita);
			pdet = new Paragraph(guiaRemision.getPuntoPartida(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(24f);
			table.addCell(cell);
			
			p = new Paragraph("PUNTO DE LLEGADA: ", negrita);
			pdet = new Paragraph(guiaRemision.getPuntoLlegada(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(24f);
			table.addCell(cell);
			
			document.add(table);
			
			
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			
			table_det = new PdfPTable(1);
			table_det.setWidths(f1);
			
			p = new Paragraph("FECHA EMISION: ", negrita);
			pdet = new Paragraph(guiaRemision.getFechaEmision(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("FECHA INICIO DE TRASLADO: ", negrita);
			pdet = new Paragraph(guiaRemision.getFechaInicioTraslado(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			
			cell_det = new PdfPCell(table_det);
			cell_det.setFixedHeight(38f);
			table.addCell(cell_det);
			
			
			table_det = new PdfPTable(1);
			table_det.setWidths(f1);
			
			p = new Paragraph("RAZON SOCIAL DEL DESTINATARIO: ", negrita);
			pdet = new Paragraph(guiaRemision.getRazonSocialDestino(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("N° DE RUC: ", negrita);
			pdet = new Paragraph(guiaRemision.getRucDestino(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			
			cell_det = new PdfPCell(table_det);
			cell_det.setFixedHeight(38f);
			table.addCell(cell_det);
			
			
			document.add(table);
			// Bloque Fin 
			
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			
			table_det = new PdfPTable(1);
			table_det.setWidths(f1);
			
			p = new Paragraph("UNIDAD DE TRANSPORTE Y CONDUCTOR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("CHOFER: ", negrita);
			pdet = new Paragraph(guiaRemision.getNombreChofer(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("N° DE PLACA: ", negrita);
			pdet = new Paragraph(guiaRemision.getNroPlaca(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("N° DE LICENCIA DE CONDUCIR: ", negrita);
			pdet = new Paragraph(guiaRemision.getNroLicenciaConducir(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			
			cell_det = new PdfPCell(table_det);
			cell_det.setFixedHeight(60f);
			table.addCell(cell_det);
			
			
			table_det = new PdfPTable(1);
			table_det.setWidths(f1);
			
			p = new Paragraph("EMPRESA DE TRANSPORTES", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("NOMBRE O RAZON SOCIAL: ", negrita);
			pdet = new Paragraph(guiaRemision.getEmpresaTransporte(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			p = new Paragraph("N° RUC: ", negrita);
			pdet = new Paragraph(guiaRemision.getRucEmpresaTransporte(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			
			cell_det = new PdfPCell(table_det);
			cell_det.setFixedHeight(60f);
			table.addCell(cell_det);
			
			
			document.add(table);
			// Bloque Fin 
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			

			// Bloque Inicio 
			table = new PdfPTable(5);
			table.setWidths(f5);
			
			p = new Paragraph("N°", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("DESCRIPCION", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD DE MEDIDA", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin 
			
			
			// Bloque Inicio 
			int i = 1;
			for (DetalleGuiaRemisionBean guia : listaGuiaRemision) {
				
				table = new PdfPTable(5);
				table.setWidths(f5);
				
				p = new Paragraph(getString(i), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(guia.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(guia.getCantidad()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(guia.getUnidadMedida()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(guia.getPesoTotal()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);
				i++;
			}
			// Bloque Fin 
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			
			table_det = new PdfPTable(1);
			table_det.setWidths(f1);
			
			p = new Paragraph("Motivo de Traslado", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell);
			
			
			
			
			table_tra = new PdfPTable(6);
			table_tra.setWidths(f6);
			
			
			Image img_unchek = Image.getInstance(path.concat(Constantes.IMAGE_INPUT_CHECK_REPORT_PATH));
			Image img_cheked = Image.getInstance(path.concat(Constantes.IMAGE_INPUT_CHECKED_REPORT_PATH));
			
			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.ONE_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Devolución", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			

			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.FOUR_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Traslado de bienes para transformación", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			
			
			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.SEVEN_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Importación", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			
			
			
			
			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.TWO_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Consignación", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			

			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.FIVE_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Traslado entre establecimientos de la misma empresa", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			
			
			if (!Utils.isNullInteger(guiaRemision.getIdMotivoTraslado()) && guiaRemision.getIdMotivoTraslado().equals(Constantes.EIGHT_INT)) {
				cell = new PdfPCell(img_cheked);
			} else {
				cell = new PdfPCell(img_unchek);
			}
			cell.setBorder(PdfPCell.NO_BORDER);
//			cell.setFixedHeight(10f);
			table_tra.addCell(cell);			
			
			p = new Paragraph("Zona primaria", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table_tra.addCell(cell);
			
			
			
			
			
			
			
			
			
			
			cell_tra = new PdfPCell(table_tra);
			cell_tra.setBorder(PdfPCell.NO_BORDER);
			table_det.addCell(cell_tra);
			

			cell_det = new PdfPCell(table_det);
			cell_det.setFixedHeight(50f);
			table.addCell(cell_det);
			
			
			document.add(table);
			// Bloque Fin 
			

			
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
	 * @param ruta
	 * @param listaManifiestoCarga
	 * @throws Exception 
	 */
	public void generaPDFReporteManifiestoCarga(String ruta, List<DetalleManifiestoCargaBean> listaManifiestoCarga) throws Exception {
		Document document = null;
		try {
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            
            DecimalFormat dec_form3 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL, dec_for_symbols);
            DecimalFormat dec_form6 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL6, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
			
			document = new Document(PageSize.A4, 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f2 = {50, 50};
			
			float[] f3 = {40, 20, 40};

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
			
			DetalleManifiestoCargaBean manifiestoCarga = null;
			if (!Utils.isEmpty(listaManifiestoCarga)) {
				manifiestoCarga = listaManifiestoCarga.get(0);
			} else {
				manifiestoCarga = new DetalleManifiestoCargaBean();
			}
			   
			// Bloque Inicio 
			table = new PdfPTable(3);
			table.setWidths(ftit);
			
			String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));			
			Image img = Image.getInstance(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			cell = new PdfPCell(img, true);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
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
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin 
			
			
			// Bloque Inicio 
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("MANIFIESTO DE CARGA N° ".concat(manifiestoCarga.getNroManifiestoCarga()), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin 
			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
						
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			p = new Paragraph("DE: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getAlmacenOrigen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("REFERENCIA: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getReferencia(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			p = new Paragraph("FECHA: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getFechaEmision(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("TIPO DE TRANSPORTE: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getTipoTransporte(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			
			p = new Paragraph("DESTINO: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getAlmacenDestino(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("MARCA Y PLACA: ", negrita);
			pdet = new Paragraph(manifiestoCarga.getNroPlacaVehiculo(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea

			
			
			// Bloque Inicio 
			table = new PdfPTable(2);
			table.setWidths(f2);
			

			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("CHOFER: ", negrita);
			pdet = new Paragraph(getString(manifiestoCarga.getNombreChofer()), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
	
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(8);
			table.setWidths(f8);
			
			p = new Paragraph("N°", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Productos", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("U. Medida", negrita);
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
			
			p = new Paragraph("Peso Unit. Bruto", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Total Peso Bruto", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Volumen Unit. m3", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Total Volumen", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio 
			int row = 1;
			BigDecimal pesoTotal = BigDecimal.ZERO;
			BigDecimal volumenTotal = BigDecimal.ZERO;
			for (DetalleManifiestoCargaBean manifiesto : listaManifiestoCarga) {
			
				table = new PdfPTable(8);
				table.setWidths(f8);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(manifiesto.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(manifiesto.getNombreUnidad(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal((manifiesto.getCantidad()))),normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form3.format(getBigDecimal((manifiesto.getPesoUnitario()))),normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form3.format(getBigDecimal((manifiesto.getPesoTotal()))),normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal((manifiesto.getVolumenUnitario()))),normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal((manifiesto.getVolumenTotal()))),normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				document.add(table);	             
				
				row++;
				pesoTotal = pesoTotal.add(getBigDecimal(manifiesto.getPesoTotal()));
				volumenTotal = volumenTotal.add(getBigDecimal(manifiesto.getVolumenTotal()));
			}	
			// Bloque Fin
			
			
			// Bloque Inicio 
			table = new PdfPTable(8);
			table.setWidths(f8);
			
			cell = new PdfPCell();
			table.addCell(cell);
		
			cell = new PdfPCell();
			table.addCell(cell);
			
			cell = new PdfPCell();
			table.addCell(cell);
			
			cell = new PdfPCell();
			table.addCell(cell);
			
			p = new Paragraph("Total", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph(getString(pesoTotal), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			table.addCell(cell);
			
			p = new Paragraph(getString(volumenTotal), normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
		
			document.add(table);
			
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("Entregue Conforme", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			p = new Paragraph("Recibi Conforme", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			document.add(table);
			
			// Bloque Fin
			
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
	 * @param ruta
	 * @param listaActaEntrega
	 * @throws Exception 
	 */
	public void generaPDFReporteActaEntrega(String ruta, List<DetalleActaEntregaBean> listaActaEntrega) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4, 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
			DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {35, 30, 35};

			float[] f4 = {10, 45, 25, 20};

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);

			DetalleActaEntregaBean actaEntrega = null;
			if (!Utils.isEmpty(listaActaEntrega)) {
				actaEntrega = listaActaEntrega.get(0);
			} else {
				actaEntrega = new DetalleActaEntregaBean();
			}
			   
			// Bloque Inicio 
			table = new PdfPTable(3);
			table.setWidths(ftit);
			
			String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));			
			Image img = Image.getInstance(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			cell = new PdfPCell(img, true);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
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
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin 
			
			
			// Bloque Inicio 
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("ACTA DE ENTREGA Y RECEPCION N° ".concat(actaEntrega.getNroActa()), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin 
			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
						
			
			// Bloque Inicio 
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Con fecha ", normal);
//			pdet = new Paragraph(actaEntrega.getFechaEmisionActa(), negrita);
			pdet = new Paragraph("         /         /             ", negrita);
			p.add(pdet);
			pdet = new Paragraph(" se procede a la entrega de Bienes de Ayuda Humanitaria a la ", normal);
			p.add(pdet);
			pdet = new Paragraph(actaEntrega.getAlmacenDestino1(), negrita);
			p.add(pdet);
			pdet = new Paragraph(" , los artículos que a continuación se detallan.", normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(4);
			table.setWidths(f4);
			
			p = new Paragraph("N°", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("DESCRIPCION DE ARTICULOS", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD DE MEDIDA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);

			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio 
			int row = 1;
			for (DetalleActaEntregaBean acta : listaActaEntrega) {
			
				table = new PdfPTable(4);
				table.setWidths(f4);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(acta.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(acta.getUnidadMedida(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal((acta.getCantidad()))), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				document.add(table);	             
				
				row++;
			}	
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			p = new Paragraph("Acto seguido se procede a firmar el presente documento en señal de haber recibido conforme los artículos señalados en la Guía de Remisión Nro ", normal);
			pdet = new Paragraph(actaEntrega.getNroGuiaRemision(), negrita);
			p.add(pdet);
			pdet = new Paragraph(" de fecha ", normal);
			p.add(pdet);
			pdet = new Paragraph(actaEntrega.getFechaEmisionGuia(), negrita);
			p.add(pdet);
			pdet = new Paragraph(" del ", normal);
			p.add(pdet);
			pdet = new Paragraph(actaEntrega.getAlmacenDestino2(), negrita);
			p.add(pdet);
			pdet = new Paragraph(".", normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ENTREGUE CONFORME", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			p = new Paragraph("RECIBI CONFORME", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			document.add(table);
			
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio 
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("JEFE ALMACEN", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);

			document.add(table);
			
			// Bloque Fin
			
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
	
	/**
	 * Retorna el valor parseado.
	 * @param campo - Valor del parámetro a evaluar, tipo String.
	 * @return date - Valor long sino retorna cero.
	 */
	public static BigDecimal getBigDecimal(BigDecimal campo) {
		if (campo == null) {
			campo = BigDecimal.ZERO;
		}
		return campo; 	
	}

}