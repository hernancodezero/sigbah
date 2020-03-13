package pe.com.sigbah.web.report.gestion_almacenes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

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

import pe.com.sigbah.common.bean.BincardAlmacenBean;
import pe.com.sigbah.common.bean.DetalleGuiaRemisionBean;
import pe.com.sigbah.common.bean.GuiaRemisionBean;
import pe.com.sigbah.common.bean.KardexAlmacenBean;
import pe.com.sigbah.common.bean.OrdenIngresoBean;
import pe.com.sigbah.common.bean.OrdenSalidaBean;
import pe.com.sigbah.common.bean.ProductoIngresoBean;
import pe.com.sigbah.common.bean.ProductoProyectoManifiestoBean;
import pe.com.sigbah.common.bean.ProductoSalidaBean;
import pe.com.sigbah.common.bean.ProyectoManifiestoBean;
import pe.com.sigbah.common.util.Constantes;
import pe.com.sigbah.common.util.DateUtil;

/**
 * @className: ReporteAlmacen.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ReporteAlmacen implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log LOGGER = LogFactory.getLog(ReporteAlmacen.class.getName());
	
	/**
	 * @param workbook
	 * @param r
	 * @param g
	 * @param b
	 * @return Objeto.
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
	 * @param mes
	 * @return descripcion del mes
	 */
	private static String getMes(String mes) {
    	String descripcionMes = null;
    	int codigoMes = Integer.valueOf(mes);
    	switch (codigoMes) {
			case 1:
				descripcionMes = "Enero";
				break;
			case 2:
				descripcionMes = "Febrero";
				break;
			case 3:
				descripcionMes = "Marzo";
				break;
			case 4:
				descripcionMes = "Abril";
				break;
			case 5:
				descripcionMes = "Mayo";
				break;
			case 6:
				descripcionMes = "Junio";
				break;
			case 7:
				descripcionMes = "Julio";
				break;
			case 8:
				descripcionMes = "Agosto";
				break;
			case 9:
				descripcionMes = "Setiembre";
				break;
			case 10:
				descripcionMes = "Octubre";
				break;
			case 11:
				descripcionMes = "Noviembre";
				break;
			case 12:
				descripcionMes = "Diciembre";
				break;
			default: 
				descripcionMes = Constantes.EMPTY;
            	break;
		}    	
    	return descripcionMes;
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
	 * @param campo - Valor del parámetro a evaluar, tipo BigDecimal.
	 * @return valor - Valor de la campo sino cero.
	 */
	private static BigDecimal getBigDecimal(BigDecimal campo) {
		if (campo != null) {
			return campo;
		}
		return BigDecimal.ZERO; 	
	}

	/**
	 * @param ruta
	 * @param proyectoManifiestoBean 
	 * @param listaProyectoManifiesto
	 * @throws Exception 
	 */
	public void generaPDFReporteProyectoManifiesto(String ruta, 
												   ProyectoManifiestoBean proyectoManifiestoBean, 
												   List<ProyectoManifiestoBean> listaProyectoManifiesto) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f9 = {5, 10, 12, 13, 15, 15, 10, 10, 10};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
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
			
			p = new Paragraph("REPORTE DE PROYECTO DE MANIFIESTO", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaProyectoManifiesto.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(proyectoManifiestoBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaProyectoManifiesto.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(proyectoManifiestoBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(proyectoManifiestoBean.getCodigoMesFin()), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph("Todos", normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(9);
			table.setWidths(f9);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO PROY. DE MANIFIESTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO PROGRAMACION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("DESTINO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("IMPORTE TOTAL", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (ProyectoManifiestoBean proyecto : listaProyectoManifiesto) {
			
				table = new PdfPTable(9);
				table.setWidths(f9);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(proyecto.getFechaEmision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNroProyectoManifiesto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNroProgramacion(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNombreMovimiento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNombreAlmacenDestino(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(proyecto.getVolumenTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(proyecto.getPesoTotalKgr())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNombreEstado(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
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
	 * @param productoProyectoManifiestoBean 
	 * @param listaDetalleProyectoManifiesto
	 * @throws Exception 
	 */
	public void generaPDFReporteDetalleProyectoManifiesto(String ruta, 
														  ProductoProyectoManifiestoBean productoProyectoManifiestoBean, 
														  List<ProductoProyectoManifiestoBean> listaDetalleProyectoManifiesto) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f11 = {5, 8, 10, 11, 10, 8, 16, 8, 8, 8, 8};
			
			BaseColor header = new BaseColor(242, 242, 242);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			   
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
			
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE PROYECTO DE MANIFIESTO");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append("DETALLE POR PRODUCTO");
			p = new Paragraph(det_titulo.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaDetalleProyectoManifiesto.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(productoProyectoManifiestoBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaDetalleProyectoManifiesto.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(productoProyectoManifiestoBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(productoProyectoManifiestoBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(11);
			table.setWidths(f11);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO PROY. MANIFIESTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO PROGRAMACION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("VOLUMEN M3", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal volumenSubTotal = BigDecimal.ZERO;
			BigDecimal pesoSubTotal = BigDecimal.ZERO;
			String nroProyectoManifiesto = Constantes.EMPTY;
			for (ProductoProyectoManifiestoBean proyecto : listaDetalleProyectoManifiesto) {
			
				table = new PdfPTable(11);
				table.setWidths(f11);

				if (!nroProyectoManifiesto.equals(proyecto.getNroProyectoManifiesto())) {
					
					if (count > 1) {
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("TOTAL PROYECTO", negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(cantidadSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(volumenSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(pesoSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cantidadSubTotal = BigDecimal.ZERO;
						volumenSubTotal = BigDecimal.ZERO;
						pesoSubTotal = BigDecimal.ZERO;
							
						document.add(table);
						
						table = new PdfPTable(11);
						table.setWidths(f11);
						
					}
						
					row++;
				
					nroProyectoManifiesto = proyecto.getNroProyectoManifiesto();
				
					p = new Paragraph(String.valueOf(row), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
					p = new Paragraph(proyecto.getFechaEmision(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(nroProyectoManifiesto, normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(proyecto.getNroProgramacion(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(proyecto.getNombreMovimiento(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(proyecto.getNombreEstado(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					ind_primero = true;
				
				} else {
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);					
					
				}
				
				p = new Paragraph(proyecto.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(proyecto.getNombreUnidad(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(proyecto.getCantidad())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(proyecto.getVolumenTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(proyecto.getPesoTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(proyecto.getCantidad()));
				volumenSubTotal = volumenSubTotal.add(getBigDecimal(proyecto.getVolumenTotal()));
				pesoSubTotal = pesoSubTotal.add(getBigDecimal(proyecto.getPesoTotal()));
				count++;
				
				ind_primero = false;

				document.add(table);
				
				// Subtotal del ultimo registro
				if (count == listaDetalleProyectoManifiesto.size() + 1) {
					
					table = new PdfPTable(11);
					table.setWidths(f11);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("TOTAL PROYECTO", negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(cantidadSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(volumenSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(pesoSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					document.add(table);
					
				}
				
				
			}
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
	 * @param ordenSalidaBean 
	 * @param listaOrdenSalida
	 * @throws Exception 
	 */
	public void generaPDFReporteOrdenSalida(String ruta, 
											OrdenSalidaBean ordenSalidaBean, 
											List<OrdenSalidaBean> listaOrdenSalida) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f9 = {5, 10, 12, 13, 15, 15, 10, 10, 10};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
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
			
			p = new Paragraph("REPORTE DE ORDENES DE SALIDA", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaOrdenSalida.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(ordenSalidaBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaOrdenSalida.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(ordenSalidaBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(ordenSalidaBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph("Todos", normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(9);
			table.setWidths(f9);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN DE SALIDA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO GUIA REMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("DESTINO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("IMPORTE TOTAL", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (OrdenSalidaBean ordenSalida : listaOrdenSalida) {
			
				table = new PdfPTable(9);
				table.setWidths(f9);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(ordenSalida.getFechaEmision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenSalida.getNroOrdenSalida(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenSalida.getNroGuiaRemision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenSalida.getNombreMovimiento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenSalida.getNombreAlmacenDestino(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(ordenSalida.getImporteTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(ordenSalida.getPesoTotalKgr())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenSalida.getNombreEstado(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
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
	 * @param productoSalidaBean 
	 * @param listaDetalleOrdenSalida
	 * @throws Exception 
	 */
	public void generaPDFReporteDetalleOrdenSalida(String ruta, 
												   ProductoSalidaBean productoSalidaBean, 
												   List<ProductoSalidaBean> listaDetalleOrdenSalida) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f11 = {5, 8, 10, 11, 10, 7, 17, 8, 8, 8, 8};
			
			BaseColor header = new BaseColor(242, 242, 242);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
            DecimalFormat dec_form6 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL6, dec_for_symbols);
			   
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
			
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE ORDENES DE SALIDA");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append("DETALLE POR PRODUCTO");
			p = new Paragraph(det_titulo.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaDetalleOrdenSalida.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(productoSalidaBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaDetalleOrdenSalida.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(productoSalidaBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(productoSalidaBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(11);
			table.setWidths(f11);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN SALIDA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO GUIA REMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRECIO UNIT. PROMEDIO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("IMPORTE TOTAL", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal precioUnitarioSubTotal = BigDecimal.ZERO;
			BigDecimal importeSubTotal = BigDecimal.ZERO;
			String nroOrdenSalida = Constantes.EMPTY;
			for (ProductoSalidaBean producto : listaDetalleOrdenSalida) {
			
				table = new PdfPTable(11);
				table.setWidths(f11);

				if (!nroOrdenSalida.equals(producto.getNroOrdenSalida())) {
					
					if (count > 1) {
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("TOTAL ORDEN", negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("", negrita);//dec_form.format(cantidadSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("", negrita);//dec_form.format(precioUnitarioSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(importeSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cantidadSubTotal = BigDecimal.ZERO;
						precioUnitarioSubTotal = BigDecimal.ZERO;
						importeSubTotal = BigDecimal.ZERO;
							
						document.add(table);
						
						table = new PdfPTable(11);
						table.setWidths(f11);
						
					}
						
					row++;
				
					nroOrdenSalida = producto.getNroOrdenSalida();
				
					p = new Paragraph(String.valueOf(row), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
					p = new Paragraph(producto.getFechaEmision(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(nroOrdenSalida, normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNroGuiaRemision(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNombreMovimiento(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNombreEstado(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
				} else {
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);					
					
				}
				
				p = new Paragraph(producto.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(producto.getNombreUnidad(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(producto.getCantidad())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal(producto.getPrecioUnitario())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(producto.getImporteTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(producto.getCantidad()));
				precioUnitarioSubTotal = precioUnitarioSubTotal.add(getBigDecimal(producto.getPrecioUnitario()));
				importeSubTotal = importeSubTotal.add(getBigDecimal(producto.getImporteTotal()));
				count++;
				
				ind_primero = false;

				document.add(table);
				
				// Subtotal del ultimo registro
				if (count == listaDetalleOrdenSalida.size() + 1) {
					
					table = new PdfPTable(11);
					table.setWidths(f11);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("TOTAL ORDEN", negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					table.addCell(cell);
					
					p = new Paragraph("", negrita);//dec_form.format(cantidadSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("", negrita);//dec_form.format(precioUnitarioSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(importeSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					document.add(table);
					
				}
				
				
			}
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
	 * @param ordenIngresoBean 
	 * @param listaOrdenIngreso
	 * @throws Exception 
	 */
	public void generaPDFReporteOrdenIngreso(String ruta, 
											 OrdenIngresoBean ordenIngresoBean, 
											 List<OrdenIngresoBean> listaOrdenIngreso) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f9 = {5, 10, 12, 13, 15, 15, 10, 10, 10};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
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
			
			p = new Paragraph("REPORTE DE ORDENES DE INGRESO", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaOrdenIngreso.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(ordenIngresoBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaOrdenIngreso.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(ordenIngresoBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(ordenIngresoBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph("Todos", normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(9);
			table.setWidths(f9);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN DE INGRESO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN DE COMPRA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ORIGEN", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("IMPORTE TOTAL", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (OrdenIngresoBean ordenIngreso : listaOrdenIngreso) {
			
				table = new PdfPTable(9);
				table.setWidths(f9);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(ordenIngreso.getFechaEmision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenIngreso.getNroOrdenIngreso(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenIngreso.getNroOrdenCompra(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenIngreso.getNombreMovimiento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenIngreso.getNombreAlmacenProcedencia(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(ordenIngreso.getImporteTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(ordenIngreso.getPesoTotalKgr())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(ordenIngreso.getNombreEstado(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
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
	 * @param productoIngresoBean 
	 * @param listaDetalleOrdenIngreso
	 * @throws Exception 
	 */
	public void generaPDFReporteDetalleOrdenIngreso(String ruta, 
													ProductoIngresoBean productoIngresoBean, 
													List<ProductoIngresoBean> listaDetalleOrdenIngreso) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f11 = {5, 8, 10, 11, 10, 7, 17, 8, 8, 8, 8};
			
			BaseColor header = new BaseColor(242, 242, 242);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
            DecimalFormat dec_form6 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL6, dec_for_symbols);
			   
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
			
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE ORDENES DE INGRESO");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append("DETALLE POR PRODUCTO");
			p = new Paragraph(det_titulo.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaDetalleOrdenIngreso.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(productoIngresoBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaDetalleOrdenIngreso.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(productoIngresoBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(productoIngresoBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(11);
			table.setWidths(f11);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN INGRESO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN COMPRA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRECIO UNITARIO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("IMPORTE TOTAL", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal precioUnitarioSubTotal = BigDecimal.ZERO;
			BigDecimal importeSubTotal = BigDecimal.ZERO;
			String nroOrdenIngreso = Constantes.EMPTY;
			for (ProductoIngresoBean producto : listaDetalleOrdenIngreso) {
			
				table = new PdfPTable(11);
				table.setWidths(f11);

				if (!nroOrdenIngreso.equals(producto.getNroOrdenIngreso())) {
					
					if (count > 1) {
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("TOTAL ORDEN", negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						table.addCell(cell);
						
						p = new Paragraph(dec_formc.format(cantidadSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("",negrita);//dec_form.format(precioUnitarioSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(importeSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cantidadSubTotal = BigDecimal.ZERO;
						precioUnitarioSubTotal = BigDecimal.ZERO;
						importeSubTotal = BigDecimal.ZERO;
							
						document.add(table);
						
						table = new PdfPTable(11);
						table.setWidths(f11);
						
					}
						
					row++;
				
					nroOrdenIngreso = producto.getNroOrdenIngreso();
				
					p = new Paragraph(String.valueOf(row), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
					p = new Paragraph(producto.getFechaEmision(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(nroOrdenIngreso, normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNroOrdenCompra(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNombreMovimiento(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(producto.getNombreEstado(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
				} else {
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);					
					
				}
				
				p = new Paragraph(producto.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(producto.getNombreUnidad(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(producto.getCantidad())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal(producto.getPrecioUnitario())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(producto.getImporteTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(producto.getCantidad()));
				precioUnitarioSubTotal = precioUnitarioSubTotal.add(getBigDecimal(producto.getPrecioUnitario()));
				importeSubTotal = importeSubTotal.add(getBigDecimal(producto.getImporteTotal()));
				count++;
				
				ind_primero = false;

				document.add(table);
				
				// Subtotal del ultimo registro
				if (count == listaDetalleOrdenIngreso.size() + 1) {
					
					table = new PdfPTable(11);
					table.setWidths(f11);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("TOTAL ORDEN", negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					table.addCell(cell);
					
					p = new Paragraph(dec_formc.format(cantidadSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("",negrita);//dec_form.format(precioUnitarioSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(importeSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					document.add(table);
					
				}
				
				
			}
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
	 * @param guiaRemisionBean 
	 * @param listaGuiaRemision
	 * @throws Exception 
	 */
	public void generaPDFReporteGuiaRemision(String ruta, 
											 GuiaRemisionBean guiaRemisionBean, 
											 List<GuiaRemisionBean> listaGuiaRemision) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f9 = {5, 10, 12, 13, 15, 15, 10, 10, 10};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
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
			
			p = new Paragraph("REPORTE DE GUIA DE REMISION", titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaGuiaRemision.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(guiaRemisionBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaGuiaRemision.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(guiaRemisionBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(guiaRemisionBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph("Todos", normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(9);
			table.setWidths(f9);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO ORDEN SALIDA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO GUIA REMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("NRO MANIFIESTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("DESTINO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (GuiaRemisionBean guiaRemision : listaGuiaRemision) {
			
				table = new PdfPTable(9);
				table.setWidths(f9);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(guiaRemision.getFechaEmision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNroOrdenSalida(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNroGuiaRemision(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNroManifiestoCarga(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNombreMovimiento(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNombreAlmacenDestino(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(guiaRemision.getPesoTotalKgr())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(guiaRemision.getNombreEstado(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
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
	 * @param detalleGuiaRemisionBean 
	 * @param listaDetalleGuiaRemision
	 * @throws Exception 
	 */
	public void generaPDFReporteDetalleGuiaRemision(String ruta, 
													DetalleGuiaRemisionBean detalleGuiaRemisionBean, 
													List<DetalleGuiaRemisionBean> listaDetalleGuiaRemision) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte
			float[] ftit = {20, 15, 65};			
			
			float[] f1 = {100};
			
			float[] f3 = {30, 30, 40};

			float[] f11 = {5, 8, 10, 11, 10, 9, 7, 16, 8, 8, 8};
			
			BaseColor header = new BaseColor(242, 242, 242);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font hide = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.WHITE);
			Font encabezado = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);
			   
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
			
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE GUIA DE REMISION");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append("DETALLE POR PRODUCTO");
			p = new Paragraph(det_titulo.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("DDI: ", negrita);
			pdet = new Paragraph(listaDetalleGuiaRemision.get(0).getNombreDdi(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("AÑO: ", negrita);
			pdet = new Paragraph(detalleGuiaRemisionBean.getCodigoAnio(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaDetalleGuiaRemision.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("MES INICIO: ", negrita);
			pdet = new Paragraph(getMes(detalleGuiaRemisionBean.getCodigoMesInicio()), normal);
			p.add(pdet);			
			pdet = new Paragraph("   MES FIN: ", negrita);
			p.add(pdet);			
			pdet = new Paragraph(getMes(detalleGuiaRemisionBean.getCodigoMesFin()), normal);
			p.add(pdet);			
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(11);
			table.setWidths(f11);
			
			p = new Paragraph("ITEM", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("FECHA EMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("N° ORDEN SALIDA", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("N° GUIA REMISION", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("N° MANIFIESTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("TIPO MOVIMIENTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("ESTADO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PRODUCTO", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("CANTIDAD", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("PESO TOTAL KGR", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal pesoSubTotal = BigDecimal.ZERO;
			String nroGuiaRemision = Constantes.EMPTY;
			for (DetalleGuiaRemisionBean guia : listaDetalleGuiaRemision) {
			
				table = new PdfPTable(11);
				table.setWidths(f11);

				if (!nroGuiaRemision.equals(guia.getNroGuiaRemision())) {
					
					if (count > 1) {
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("TOTAL ORDEN", negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cell = new PdfPCell();
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph("",negrita);//dec_form.format(cantidadSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						p = new Paragraph(dec_form.format(pesoSubTotal), negrita);
						cell = new PdfPCell(p);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
						table.addCell(cell);
						
						cantidadSubTotal = BigDecimal.ZERO;
						pesoSubTotal = BigDecimal.ZERO;
							
						document.add(table);
						
						table = new PdfPTable(11);
						table.setWidths(f11);
						
					}
						
					row++;
				
					nroGuiaRemision = guia.getNroGuiaRemision();
				
					p = new Paragraph(String.valueOf(row), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
				
					p = new Paragraph(guia.getFechaEmision(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(guia.getNroOrdenSalida(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(nroGuiaRemision, normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(guia.getNroManifiestoCarga(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(guia.getNombreMovimiento(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					p = new Paragraph(guia.getNombreEstado(), normal);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
					table.addCell(cell);
					
					ind_primero = true;
				
				} else {
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					table.addCell(cell);
					
				}
				
				p = new Paragraph(guia.getNombreProducto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(guia.getUnidadMedida(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(guia.getCantidad())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(guia.getPesoTotal())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (ind_primero) {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
				} else {
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
				}
				table.addCell(cell);
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(guia.getCantidad()));
				pesoSubTotal = pesoSubTotal.add(getBigDecimal(guia.getPesoTotal()));
				count++;
				
				ind_primero = false;

				document.add(table);
				
				// Subtotal del ultimo registro
				if (count == listaDetalleGuiaRemision.size() + 1) {
					
					table = new PdfPTable(11);
					table.setWidths(f11);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("TOTAL ORDEN", negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph("",negrita);//dec_form.format(cantidadSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					p = new Paragraph(dec_form.format(pesoSubTotal), negrita);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					table.addCell(cell);
					
					document.add(table);
					
				}
				
				
			}
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
	 * @param kardexAlmacenBean 
	 * @param listaKardexAlmacen
	 * @throws Exception 
	 */
	public void generaPDFReporteKardexAlmacen(String ruta, 
											  KardexAlmacenBean kardexAlmacenBean, 
											  List<KardexAlmacenBean> listaKardexAlmacen) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4.rotate(), 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte	
			float[] f1 = {100};
			
			float[] f3 = {40, 30, 30};
			
			float[] f5 = {15, 20, 30, 20, 15};

			float[] f11 = {5, 8, 15, 8, 8, 8, 9, 10, 10, 10, 9};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
            DecimalFormat dec_form6 = new DecimalFormat(Constantes.EXPRESION_MONEDA_DECIMAL6, dec_for_symbols);
            DecimalFormat dec_formc = new DecimalFormat(Constantes.EXPRESION_CANTIDAD, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append("KARDEX N° ");
//			det_encabezado.append(kardexAlmacenBean.getIdAlmacen());
//			det_encabezado.append(Constantes.SEPARADOR);
			det_encabezado.append(listaKardexAlmacen.get(0).getNroKardex());
			p = new Paragraph(det_encabezado.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph(listaKardexAlmacen.get(0).getNombreProducto(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD MEDIDA: ", negrita);
			pdet = new Paragraph(listaKardexAlmacen.get(0).getNombreUnidad(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			StringBuilder det_mes_anio = new StringBuilder();
			det_mes_anio.append("MES: ");
			det_mes_anio.append(getMes(kardexAlmacenBean.getCodigoMes()));
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(Constantes.SEPARADOR);
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(kardexAlmacenBean.getCodigoAnio());
			p = new Paragraph(det_mes_anio.toString(), negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaKardexAlmacen.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(11);
			table.setWidths(f11);
			
			p = new Paragraph("Item", negrita);
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
			
			p = new Paragraph("Documento", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Ingresos", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Salidas", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Saldo", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Precio Compra (S/.)", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Precio Promedio (S/.)", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Importe Valorado (S/.)", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Motivo", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Nro Orden", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (KardexAlmacenBean kardex : listaKardexAlmacen) {
			
				table = new PdfPTable(11);
				table.setWidths(f11);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(kardex.getFecha(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(kardex.getConcepto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(kardex.getCantidadIngresos())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(kardex.getCantidadSalidas())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_formc.format(getBigDecimal(kardex.getCantidadSaldo())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal(kardex.getPrecioCompra())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form6.format(getBigDecimal(kardex.getPrecioPromedio())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(kardex.getImporteValorizado())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(kardex.getMotivo()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(kardex.getNroOrden()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio
			table = new PdfPTable(5);
			table.setWidths(f5);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Jefe de Almacén", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Jefe DDI", normal);
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
	 * @param ruta
	 * @param bincardAlmacenBean 
	 * @param listaBincardAlmacen
	 * @throws Exception 
	 */
	public void generaPDFReporteBincardAlmacen(String ruta, 
											   BincardAlmacenBean bincardAlmacenBean, 
											   List<BincardAlmacenBean> listaBincardAlmacen) throws Exception {
		Document document = null;
		try {
			document = new Document(PageSize.A4, 0, 0, 20, 20);
			PdfWriter.getInstance(document, new FileOutputStream(ruta));    
			
			document.open();
			 
			// Considerar que cada campo en array es una columna table de tu reporte	
			float[] f1 = {100};
			
			float[] f3 = {55, 25, 20};
			
			float[] f5 = {15, 20, 30, 20, 15};

			float[] f8 = {5, 10, 25, 10, 10, 10, 20, 10};
			
			BaseColor header = new BaseColor(242, 242, 242);
			
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);

			Paragraph p     = null;
			Paragraph pdet 	= null;
			PdfPTable table = null;
			PdfPCell cell   = null;

			Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
			Font normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);			
			Font negrita = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
			   
			
			// Bloque Inicio
			table = new PdfPTable(1);
			table.setWidths(f1);
			
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append("BINCARD N° ");
//			det_encabezado.append(bincardAlmacenBean.getIdAlmacen());
//			det_encabezado.append(Constantes.SEPARADOR);
			det_encabezado.append(listaBincardAlmacen.get(0).getNroBincard());
			p = new Paragraph(det_encabezado.toString(), titulo);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin			
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("PRODUCTO: ", negrita);
			pdet = new Paragraph(listaBincardAlmacen.get(0).getNombreProducto(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			p = new Paragraph("UNIDAD MEDIDA: ", negrita);
			pdet = new Paragraph(listaBincardAlmacen.get(0).getNombreUnidad(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			StringBuilder det_mes_anio = new StringBuilder();
			det_mes_anio.append("MES: ");
			det_mes_anio.append(getMes(bincardAlmacenBean.getCodigoMes()));
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(Constantes.SEPARADOR);
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(bincardAlmacenBean.getCodigoAnio());
			p = new Paragraph(det_mes_anio.toString(), negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			table = new PdfPTable(3);
			table.setWidths(f3);
			
			p = new Paragraph("ALMACEN: ", negrita);
			pdet = new Paragraph(listaBincardAlmacen.get(0).getNombreAlmacen(), normal);
			p.add(pdet);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin

			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea	

			
			// Bloque Inicio
			table = new PdfPTable(8);
			table.setWidths(f8);
			
			p = new Paragraph("Item", negrita);
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
			
			p = new Paragraph("Documento", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Ingresos", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Salidas", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Saldo", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Motivo", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			p = new Paragraph("Nro Orden", negrita);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(header);
			table.addCell(cell);
			
			document.add(table);
			// Bloque Fin
			
			
			// Bloque Inicio
			int row = 1;
			
			for (BincardAlmacenBean bincard : listaBincardAlmacen) {
			
				table = new PdfPTable(8);
				table.setWidths(f8);
				
				p = new Paragraph(String.valueOf(row), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			
				p = new Paragraph(bincard.getFecha(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(bincard.getConcepto(), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(bincard.getCantidadIngresos())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(bincard.getCantidadSalidas())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(dec_form.format(getBigDecimal(bincard.getCantidadSaldo())), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(bincard.getMotivo()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				p = new Paragraph(getString(bincard.getNroOrden()), normal);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
				
				document.add(table);	             
				
				row++;
			}
			// Bloque Fin
			
			
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			document.add(new Paragraph(Constantes.ESPACIO)); // Salto de linea
			
			
			// Bloque Inicio
			table = new PdfPTable(5);
			table.setWidths(f5);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Jefe de Almacén", normal);
			cell = new PdfPCell(p);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
			
			p = new Paragraph("Jefe DDI", normal);
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
	 * @param ruta
	 * @param proyectoManifiestoBean
	 * @param listaProyectoManifiesto
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteProyectoManifiesto(String ruta,
															 ProyectoManifiestoBean proyectoManifiestoBean, 
															 List<ProyectoManifiestoBean> listaProyectoManifiesto) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("PROYECTO DE MANIFIESTO");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 4000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(9).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(9).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(9).setCellValue(det_fecha.toString());
	        row2.getCell(9).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(9).setCellValue(det_hora.toString());
	        row3.getCell(9).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			row5.createCell(1).setCellValue("REPORTE DE PROYECTO DE MANIFIESTO");
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaProyectoManifiesto.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaProyectoManifiesto.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(proyectoManifiestoBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = proyectoManifiestoBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaProyectoManifiesto.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaProyectoManifiesto.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(proyectoManifiestoBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(proyectoManifiestoBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO PROY. DE MANIFIESTO");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO PROGRAMACION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("DESTINO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("IMPORTE TOTAL");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("PESO TOTAL KGR");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("ESTADO");
	        row10.getCell(9).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 11;
	        int count = 1;
	        for (ProyectoManifiestoBean proyecto : listaProyectoManifiesto) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(proyecto.getFechaEmision());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(proyecto.getNroProyectoManifiesto());
		        rows.getCell(3).setCellStyle(style_cell_f3);
		        
		        rows.createCell(4).setCellValue(proyecto.getNroProgramacion());
		        rows.getCell(4).setCellStyle(style_cell_f3);
		        
		        rows.createCell(5).setCellValue(proyecto.getNombreMovimiento());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(proyecto.getNombreAlmacenDestino());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(dec_form.format(getBigDecimal(proyecto.getVolumenTotal())));
		        rows.getCell(7).setCellStyle(style_cell_f2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(getBigDecimal(proyecto.getPesoTotalKgr())));
		        rows.getCell(8).setCellStyle(style_cell_f2);
		        
		        rows.createCell(9).setCellValue(proyecto.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell_f3);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param productoProyectoManifiestoBean
	 * @param listaDetalleProyectoManifiesto
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteDetalleProyectoManifiesto(String ruta,
																	ProductoProyectoManifiestoBean productoProyectoManifiestoBean,
																	List<ProductoProyectoManifiestoBean> listaDetalleProyectoManifiesto) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("PROYECTO DE MANIFIESTO");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 11000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);
	        style_tit_cabecera.setWrapText(true);
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell.setFont(font_norm);
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f2.setFont(font_bold);
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);
	        style_cell_f2.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f3.setFont(font_norm);
	        style_cell_f3.setBorderTop((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);
	        style_cell_f3.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        style_cell_f4.setBorderLeft((short) 1);
	        style_cell_f4.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f5 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f5.setFont(font_norm);
	        style_cell_f5.setBorderLeft((short) 1);
	        style_cell_f5.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f6 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f6.setFont(font_norm);
	        style_cell_f6.setBorderTop((short) 1);
	        style_cell_f6.setBorderLeft((short) 1);
	        style_cell_f6.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f7 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f7.setFont(font_norm);
	        style_cell_f7.setBorderLeft((short) 1);
	        style_cell_f7.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f8 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f8.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f8.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f8.setFont(font_norm);
	        style_cell_f8.setBorderTop((short) 1);
	        style_cell_f8.setBorderLeft((short) 1);
	        style_cell_f8.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f9 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f9.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f9.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f9.setFont(font_norm);
	        style_cell_f9.setBorderLeft((short) 1);
	        style_cell_f9.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f10 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f10.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f10.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f10.setFont(font_bold);
	        style_cell_f10.setBorderBottom((short) 1);
	        style_cell_f10.setBorderLeft((short) 1);
	        style_cell_f10.setBorderRight((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(11).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(11).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(11).setCellValue(det_fecha.toString());
	        row2.getCell(11).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(11).setCellValue(det_hora.toString());
	        row3.getCell(11).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE PROYECTO DE MANIFIESTO ");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append(" DETALLE POR PRODUCTO");
			row5.createCell(1).setCellValue(det_titulo.toString());
	        row5.getCell(1).setCellStyle(style_tit_cabecera);
	        row5.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaDetalleProyectoManifiesto.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaDetalleProyectoManifiesto.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(productoProyectoManifiestoBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = productoProyectoManifiestoBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaDetalleProyectoManifiesto.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaDetalleProyectoManifiesto.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(productoProyectoManifiestoBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(productoProyectoManifiestoBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO PROY. MANIFIESTO");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO PROGRAMACION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("ESTADO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("PRODUCTO");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("UNIDAD");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("CANTIDAD");
	        row10.getCell(9).setCellStyle(style_header);
			
	        row10.createCell(10).setCellValue("VOLUMEN M3");
	        row10.getCell(10).setCellStyle(style_header);
			
	        row10.createCell(11).setCellValue("PESO TOTAL KGR");
	        row10.getCell(11).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
			int hrow = 11;
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal volumenSubTotal = BigDecimal.ZERO;
			BigDecimal pesoSubTotal = BigDecimal.ZERO;
			String nroProyectoManifiesto = Constantes.EMPTY;
	        for (ProductoProyectoManifiestoBean proyecto : listaDetalleProyectoManifiesto) {
	        	
	        	HSSFRow rows = sheet.createRow((short) hrow);
				
				if (!nroProyectoManifiesto.equals(proyecto.getNroProyectoManifiesto())) {
					
					if (count > 1) {
				
						rows.createCell(1).setCellValue(Constantes.EMPTY);
						rows.getCell(1).setCellStyle(style_cell_f2);
						
						rows.createCell(2).setCellValue(Constantes.EMPTY);
						rows.getCell(2).setCellStyle(style_cell_f2);
						
						rows.createCell(3).setCellValue(Constantes.EMPTY);
						rows.getCell(3).setCellStyle(style_cell_f2);
						
						rows.createCell(4).setCellValue(Constantes.EMPTY);
						rows.getCell(4).setCellStyle(style_cell_f2);
						
						rows.createCell(5).setCellValue(Constantes.EMPTY);
						rows.getCell(5).setCellStyle(style_cell_f2);
						
						rows.createCell(6).setCellValue(Constantes.EMPTY);
						rows.getCell(6).setCellStyle(style_cell_f2);
						
						rows.createCell(7).setCellValue("TOTAL PROYECTO");
						rows.getCell(7).setCellStyle(style_cell_f2);
						
						rows.createCell(8).setCellValue(Constantes.EMPTY);
						rows.getCell(8).setCellStyle(style_cell_f2);
						
						rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
						rows.getCell(9).setCellStyle(style_cell_f10);
						
						rows.createCell(10).setCellValue(dec_form.format(volumenSubTotal));
						rows.getCell(10).setCellStyle(style_cell_f10);
						
						rows.createCell(11).setCellValue(dec_form.format(pesoSubTotal));
						rows.getCell(11).setCellStyle(style_cell_f10);
						
						cantidadSubTotal = BigDecimal.ZERO;
						volumenSubTotal = BigDecimal.ZERO;
						pesoSubTotal = BigDecimal.ZERO;
						hrow++;
						
						rows = sheet.createRow((short) hrow);
						
					}
						
					row++;
				
					nroProyectoManifiesto = proyecto.getNroProyectoManifiesto();
					
					rows.createCell(1).setCellValue(row);
					rows.getCell(1).setCellStyle(style_cell_f3);
					
					rows.createCell(2).setCellValue(proyecto.getFechaEmision());
					rows.getCell(2).setCellStyle(style_cell_f3);
					
					rows.createCell(3).setCellValue(nroProyectoManifiesto);
					rows.getCell(3).setCellStyle(style_cell_f3);
					
					rows.createCell(4).setCellValue(proyecto.getNroProgramacion());
					rows.getCell(4).setCellStyle(style_cell_f3);
					
					rows.createCell(5).setCellValue(proyecto.getNombreMovimiento());
					rows.getCell(5).setCellStyle(style_cell_f4);
					
					rows.createCell(6).setCellValue(proyecto.getNombreEstado());
					rows.getCell(6).setCellStyle(style_cell_f3);
				
					ind_primero = true;
				
				} else {
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f5);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f5);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f5);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f5);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f5);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f5);				
				
				}
				
				rows.createCell(7).setCellValue(proyecto.getNombreProducto());
				if (ind_primero) {
					rows.getCell(7).setCellStyle(style_cell_f4);
				} else {
					rows.getCell(7).setCellStyle(style_cell_f5);
				}				
				
				rows.createCell(8).setCellValue(proyecto.getNombreUnidad());
				if (ind_primero) {
					rows.getCell(8).setCellStyle(style_cell_f6);
				} else {
					rows.getCell(8).setCellStyle(style_cell_f7);
				}
				
				rows.createCell(9).setCellValue(dec_form.format(getBigDecimal(proyecto.getCantidad())));
				if (ind_primero) {
					rows.getCell(9).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(9).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(10).setCellValue(dec_form.format(getBigDecimal(proyecto.getVolumenTotal())));
				if (ind_primero) {
					rows.getCell(10).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(10).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(proyecto.getPesoTotal())));
				if (ind_primero) {
					rows.getCell(11).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(11).setCellStyle(style_cell_f9);
				}
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(proyecto.getCantidad()));
				volumenSubTotal = volumenSubTotal.add(getBigDecimal(proyecto.getVolumenTotal()));
				pesoSubTotal = pesoSubTotal.add(getBigDecimal(proyecto.getPesoTotal()));
				count++;
				
				ind_primero = false;
				
				// Subtotal del ultimo registro
				if (count == listaDetalleProyectoManifiesto.size() + 1) {
				
					hrow++;
						
					rows = sheet.createRow((short) hrow);
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f2);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f2);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f2);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f2);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f2);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f2);
					
					rows.createCell(7).setCellValue("TOTAL PROYECTO");
					rows.getCell(7).setCellStyle(style_cell_f2);
					
					rows.createCell(8).setCellValue(Constantes.EMPTY);
					rows.getCell(8).setCellStyle(style_cell_f2);
					
					rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
					rows.getCell(9).setCellStyle(style_cell_f10);
					
					rows.createCell(10).setCellValue(dec_form.format(volumenSubTotal));
					rows.getCell(10).setCellStyle(style_cell_f10);
					
					rows.createCell(11).setCellValue(dec_form.format(pesoSubTotal));
					rows.getCell(11).setCellStyle(style_cell_f10);
				
				}
	            
	            hrow++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param ordenSalidaBean
	 * @param listaOrdenSalida
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteOrdenSalida(String ruta, 
													  OrdenSalidaBean ordenSalidaBean,
													  List<OrdenSalidaBean> listaOrdenSalida) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("ORDEN DE SALIDA");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 4000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(9).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(9).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(9).setCellValue(det_fecha.toString());
	        row2.getCell(9).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(9).setCellValue(det_hora.toString());
	        row3.getCell(9).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			row5.createCell(1).setCellValue("REPORTE DE ORDENES DE SALIDA");
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaOrdenSalida.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaOrdenSalida.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(ordenSalidaBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = ordenSalidaBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaOrdenSalida.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaOrdenSalida.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(ordenSalidaBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(ordenSalidaBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN DE SALIDA");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO GUIA REMISION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("DESTINO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("IMPORTE TOTAL");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("PESO TOTAL KGR");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("ESTADO");
	        row10.getCell(9).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 11;
	        int count = 1;
	        for (OrdenSalidaBean ordenSalida : listaOrdenSalida) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(ordenSalida.getFechaEmision());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(ordenSalida.getNroOrdenSalida());
		        rows.getCell(3).setCellStyle(style_cell_f3);
		        
		        rows.createCell(4).setCellValue(ordenSalida.getNroGuiaRemision());
		        rows.getCell(4).setCellStyle(style_cell_f3);
		        
		        rows.createCell(5).setCellValue(ordenSalida.getNombreMovimiento());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(ordenSalida.getNombreAlmacenDestino());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(dec_form.format(getBigDecimal(ordenSalida.getImporteTotal())));
		        rows.getCell(7).setCellStyle(style_cell_f2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(getBigDecimal(ordenSalida.getPesoTotalKgr())));
		        rows.getCell(8).setCellStyle(style_cell_f2);
		        
		        rows.createCell(9).setCellValue(ordenSalida.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell_f3);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param productoSalidaBean
	 * @param listaDetalleOrdenSalida
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteDetalleOrdenSalida(String ruta, 
															 ProductoSalidaBean productoSalidaBean,
															 List<ProductoSalidaBean> listaDetalleOrdenSalida) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("ORDEN DE SALIDA");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 11000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 6000);
			sheet.setColumnWidth(11, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);
			style_tit_cabecera.setWrapText(true);
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell.setFont(font_norm);
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f2.setFont(font_bold);
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);
	        style_cell_f2.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f3.setFont(font_norm);
	        style_cell_f3.setBorderTop((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);
	        style_cell_f3.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        style_cell_f4.setBorderLeft((short) 1);
	        style_cell_f4.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f5 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f5.setFont(font_norm);
	        style_cell_f5.setBorderLeft((short) 1);
	        style_cell_f5.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f6 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f6.setFont(font_norm);
	        style_cell_f6.setBorderTop((short) 1);
	        style_cell_f6.setBorderLeft((short) 1);
	        style_cell_f6.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f7 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f7.setFont(font_norm);
	        style_cell_f7.setBorderLeft((short) 1);
	        style_cell_f7.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f8 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f8.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f8.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f8.setFont(font_norm);
	        style_cell_f8.setBorderTop((short) 1);
	        style_cell_f8.setBorderLeft((short) 1);
	        style_cell_f8.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f9 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f9.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f9.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f9.setFont(font_norm);
	        style_cell_f9.setBorderLeft((short) 1);
	        style_cell_f9.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f10 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f10.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f10.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f10.setFont(font_bold);
	        style_cell_f10.setBorderBottom((short) 1);
	        style_cell_f10.setBorderLeft((short) 1);
	        style_cell_f10.setBorderRight((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(11).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(11).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(11).setCellValue(det_fecha.toString());
	        row2.getCell(11).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(11).setCellValue(det_hora.toString());
	        row3.getCell(11).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE ORDEN DE SALIDA ");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append(" DETALLE POR PRODUCTO");
			row5.createCell(1).setCellValue(det_titulo.toString());
	        row5.getCell(1).setCellStyle(style_tit_cabecera);
			row5.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaDetalleOrdenSalida.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaDetalleOrdenSalida.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(productoSalidaBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = productoSalidaBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaDetalleOrdenSalida.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaDetalleOrdenSalida.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(productoSalidaBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(productoSalidaBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN SALIDA");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO GUIA REMISION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("ESTADO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("PRODUCTO");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("UNIDAD");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("CANTIDAD");
	        row10.getCell(9).setCellStyle(style_header);
			
	        row10.createCell(10).setCellValue("PRECIO UNIT. PROMEDIO");
	        row10.getCell(10).setCellStyle(style_header);
			
	        row10.createCell(11).setCellValue("IMPORTE TOTAL");
	        row10.getCell(11).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
			int hrow = 11;
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal precioUnitarioSubTotal = BigDecimal.ZERO;
			BigDecimal importeSubTotal = BigDecimal.ZERO;
			String nroOrdenSalida = Constantes.EMPTY;
	        for (ProductoSalidaBean producto : listaDetalleOrdenSalida) {
	        	
	        	HSSFRow rows = sheet.createRow((short) hrow);
				
				if (!nroOrdenSalida.equals(producto.getNroOrdenSalida())) {
					
					if (count > 1) {
				
						rows.createCell(1).setCellValue(Constantes.EMPTY);
						rows.getCell(1).setCellStyle(style_cell_f2);
						
						rows.createCell(2).setCellValue(Constantes.EMPTY);
						rows.getCell(2).setCellStyle(style_cell_f2);
						
						rows.createCell(3).setCellValue(Constantes.EMPTY);
						rows.getCell(3).setCellStyle(style_cell_f2);
						
						rows.createCell(4).setCellValue(Constantes.EMPTY);
						rows.getCell(4).setCellStyle(style_cell_f2);
						
						rows.createCell(5).setCellValue(Constantes.EMPTY);
						rows.getCell(5).setCellStyle(style_cell_f2);
						
						rows.createCell(6).setCellValue(Constantes.EMPTY);
						rows.getCell(6).setCellStyle(style_cell_f2);
						
						rows.createCell(7).setCellValue("TOTAL ORDEN");
						rows.getCell(7).setCellStyle(style_cell_f2);
						
						rows.createCell(8).setCellValue(Constantes.EMPTY);
						rows.getCell(8).setCellStyle(style_cell_f2);
						
						rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
						rows.getCell(9).setCellStyle(style_cell_f10);
						
						rows.createCell(10).setCellValue(dec_form.format(precioUnitarioSubTotal));
						rows.getCell(10).setCellStyle(style_cell_f10);
						
						rows.createCell(11).setCellValue(dec_form.format(importeSubTotal));
						rows.getCell(11).setCellStyle(style_cell_f10);
						
						cantidadSubTotal = BigDecimal.ZERO;
						precioUnitarioSubTotal = BigDecimal.ZERO;
						importeSubTotal = BigDecimal.ZERO;
						hrow++;
						
						rows = sheet.createRow((short) hrow);
						
					}
						
					row++;
				
					nroOrdenSalida = producto.getNroOrdenSalida();
					
					rows.createCell(1).setCellValue(row);
					rows.getCell(1).setCellStyle(style_cell_f3);
					
					rows.createCell(2).setCellValue(producto.getFechaEmision());
					rows.getCell(2).setCellStyle(style_cell_f3);
					
					rows.createCell(3).setCellValue(nroOrdenSalida);
					rows.getCell(3).setCellStyle(style_cell_f3);
					
					rows.createCell(4).setCellValue(producto.getNroGuiaRemision());
					rows.getCell(4).setCellStyle(style_cell_f3);
					
					rows.createCell(5).setCellValue(producto.getNombreMovimiento());
					rows.getCell(5).setCellStyle(style_cell_f4);
					
					rows.createCell(6).setCellValue(producto.getNombreEstado());
					rows.getCell(6).setCellStyle(style_cell_f3);
				
					ind_primero = true;
				
				} else {
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f5);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f5);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f5);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f5);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f5);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f5);				
				
				}
				
				rows.createCell(7).setCellValue(producto.getNombreProducto());
				if (ind_primero) {
					rows.getCell(7).setCellStyle(style_cell_f4);
				} else {
					rows.getCell(7).setCellStyle(style_cell_f5);
				}				
				
				rows.createCell(8).setCellValue(producto.getNombreUnidad());
				if (ind_primero) {
					rows.getCell(8).setCellStyle(style_cell_f6);
				} else {
					rows.getCell(8).setCellStyle(style_cell_f7);
				}
				
				rows.createCell(9).setCellValue(dec_form.format(getBigDecimal(producto.getCantidad())));
				if (ind_primero) {
					rows.getCell(9).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(9).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(10).setCellValue(dec_form.format(getBigDecimal(producto.getPrecioUnitario())));
				if (ind_primero) {
					rows.getCell(10).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(10).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(producto.getImporteTotal())));
				if (ind_primero) {
					rows.getCell(11).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(11).setCellStyle(style_cell_f9);
				}
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(producto.getCantidad()));
				precioUnitarioSubTotal = precioUnitarioSubTotal.add(getBigDecimal(producto.getPrecioUnitario()));
				importeSubTotal = importeSubTotal.add(getBigDecimal(producto.getImporteTotal()));
				count++;
				
				ind_primero = false;
				
				// Subtotal del ultimo registro
				if (count == listaDetalleOrdenSalida.size() + 1) {
				
					hrow++;
						
					rows = sheet.createRow((short) hrow);
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f2);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f2);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f2);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f2);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f2);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f2);
					
					rows.createCell(7).setCellValue("TOTAL ORDEN");
					rows.getCell(7).setCellStyle(style_cell_f2);
					
					rows.createCell(8).setCellValue(Constantes.EMPTY);
					rows.getCell(8).setCellStyle(style_cell_f2);
					
					rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
					rows.getCell(9).setCellStyle(style_cell_f10);
					
					rows.createCell(10).setCellValue(dec_form.format(precioUnitarioSubTotal));
					rows.getCell(10).setCellStyle(style_cell_f10);
					
					rows.createCell(11).setCellValue(dec_form.format(importeSubTotal));
					rows.getCell(11).setCellStyle(style_cell_f10);
				
				}
	            
	            hrow++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param ordenIngresoBean
	 * @param listaOrdenIngreso
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteOrdenIngreso(String ruta, 
													   OrdenIngresoBean ordenIngresoBean,
													   List<OrdenIngresoBean> listaOrdenIngreso) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("ORDEN DE INGRESO");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 4000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(9).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(9).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(9).setCellValue(det_fecha.toString());
	        row2.getCell(9).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(9).setCellValue(det_hora.toString());
	        row3.getCell(9).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			row5.createCell(1).setCellValue("REPORTE DE ORDENES DE INGRESO");
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaOrdenIngreso.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaOrdenIngreso.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(ordenIngresoBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = ordenIngresoBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaOrdenIngreso.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaOrdenIngreso.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(ordenIngresoBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(ordenIngresoBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN DE INGRESO");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO ORDEN DE COMPRA");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("ORIGEN");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("IMPORTE TOTAL");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("PESO TOTAL KGR");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("ESTADO");
	        row10.getCell(9).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 11;
	        int count = 1;
	        for (OrdenIngresoBean ordenIngreso : listaOrdenIngreso) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(ordenIngreso.getFechaEmision());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(ordenIngreso.getNroOrdenIngreso());
		        rows.getCell(3).setCellStyle(style_cell_f3);
		        
		        rows.createCell(4).setCellValue(ordenIngreso.getNroOrdenCompra());
		        rows.getCell(4).setCellStyle(style_cell_f3);
		        
		        rows.createCell(5).setCellValue(ordenIngreso.getNombreMovimiento());
		        rows.getCell(5).setCellStyle(style_cell);
		        
		        rows.createCell(6).setCellValue(ordenIngreso.getNombreAlmacenProcedencia());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(dec_form.format(getBigDecimal(ordenIngreso.getImporteTotal())));
		        rows.getCell(7).setCellStyle(style_cell_f2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(getBigDecimal(ordenIngreso.getPesoTotalKgr())));
		        rows.getCell(8).setCellStyle(style_cell_f2);
		        
		        rows.createCell(9).setCellValue(ordenIngreso.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell_f3);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param productoIngresoBean
	 * @param listaDetalleOrdenIngreso
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteDetalleOrdenIngreso(String ruta, 
															  ProductoIngresoBean productoIngresoBean,
															  List<ProductoIngresoBean> listaDetalleOrdenIngreso) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("ORDEN DE INGRESO");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 11000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);
			style_tit_cabecera.setWrapText(true);
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell.setFont(font_norm);
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f2.setFont(font_bold);
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);
	        style_cell_f2.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f3.setFont(font_norm);
	        style_cell_f3.setBorderTop((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);
	        style_cell_f3.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        style_cell_f4.setBorderLeft((short) 1);
	        style_cell_f4.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f5 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f5.setFont(font_norm);
	        style_cell_f5.setBorderLeft((short) 1);
	        style_cell_f5.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f6 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f6.setFont(font_norm);
	        style_cell_f6.setBorderTop((short) 1);
	        style_cell_f6.setBorderLeft((short) 1);
	        style_cell_f6.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f7 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f7.setFont(font_norm);
	        style_cell_f7.setBorderLeft((short) 1);
	        style_cell_f7.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f8 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f8.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f8.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f8.setFont(font_norm);
	        style_cell_f8.setBorderTop((short) 1);
	        style_cell_f8.setBorderLeft((short) 1);
	        style_cell_f8.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f9 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f9.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f9.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f9.setFont(font_norm);
	        style_cell_f9.setBorderLeft((short) 1);
	        style_cell_f9.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f10 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f10.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f10.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f10.setFont(font_bold);
	        style_cell_f10.setBorderBottom((short) 1);
	        style_cell_f10.setBorderLeft((short) 1);
	        style_cell_f10.setBorderRight((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(11).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(11).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(11).setCellValue(det_fecha.toString());
	        row2.getCell(11).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(11).setCellValue(det_hora.toString());
	        row3.getCell(11).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE ORDENES DE INGRESO ");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append(" DETALLE POR PRODUCTO");
			row5.createCell(1).setCellValue(det_titulo.toString());
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
			row5.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaDetalleOrdenIngreso.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaDetalleOrdenIngreso.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(productoIngresoBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = productoIngresoBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaDetalleOrdenIngreso.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaDetalleOrdenIngreso.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(productoIngresoBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(productoIngresoBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN INGRESO");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO ORDEN COMPRA");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("ESTADO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("PRODUCTO");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("UNIDAD");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("CANTIDAD");
	        row10.getCell(9).setCellStyle(style_header);
			
	        row10.createCell(10).setCellValue("PRECIO UNITARIO");
	        row10.getCell(10).setCellStyle(style_header);
			
	        row10.createCell(11).setCellValue("IMPORTE TOTAL");
	        row10.getCell(11).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
			int hrow = 11;
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal precioUnitarioSubTotal = BigDecimal.ZERO;
			BigDecimal importeSubTotal = BigDecimal.ZERO;
			String nroOrdenIngreso = Constantes.EMPTY;
			for (ProductoIngresoBean producto : listaDetalleOrdenIngreso) {
	        	
	        	HSSFRow rows = sheet.createRow((short) hrow);
				
				if (!nroOrdenIngreso.equals(producto.getNroOrdenIngreso())) {
					
					if (count > 1) {
				
						rows.createCell(1).setCellValue(Constantes.EMPTY);
						rows.getCell(1).setCellStyle(style_cell_f2);
						
						rows.createCell(2).setCellValue(Constantes.EMPTY);
						rows.getCell(2).setCellStyle(style_cell_f2);
						
						rows.createCell(3).setCellValue(Constantes.EMPTY);
						rows.getCell(3).setCellStyle(style_cell_f2);
						
						rows.createCell(4).setCellValue(Constantes.EMPTY);
						rows.getCell(4).setCellStyle(style_cell_f2);
						
						rows.createCell(5).setCellValue(Constantes.EMPTY);
						rows.getCell(5).setCellStyle(style_cell_f2);
						
						rows.createCell(6).setCellValue(Constantes.EMPTY);
						rows.getCell(6).setCellStyle(style_cell_f2);
						
						rows.createCell(7).setCellValue("TOTAL ORDEN");
						rows.getCell(7).setCellStyle(style_cell_f2);
						
						rows.createCell(8).setCellValue(Constantes.EMPTY);
						rows.getCell(8).setCellStyle(style_cell_f2);
						
						rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
						rows.getCell(9).setCellStyle(style_cell_f10);
						
						rows.createCell(10).setCellValue(dec_form.format(precioUnitarioSubTotal));
						rows.getCell(10).setCellStyle(style_cell_f10);
						
						rows.createCell(11).setCellValue(dec_form.format(importeSubTotal));
						rows.getCell(11).setCellStyle(style_cell_f10);
						
						cantidadSubTotal = BigDecimal.ZERO;
						precioUnitarioSubTotal = BigDecimal.ZERO;
						importeSubTotal = BigDecimal.ZERO;
						hrow++;
						
						rows = sheet.createRow((short) hrow);
						
					}
						
					row++;
				
					nroOrdenIngreso = producto.getNroOrdenIngreso();
					
					rows.createCell(1).setCellValue(row);
					rows.getCell(1).setCellStyle(style_cell_f3);
					
					rows.createCell(2).setCellValue(producto.getFechaEmision());
					rows.getCell(2).setCellStyle(style_cell_f3);
					
					rows.createCell(3).setCellValue(nroOrdenIngreso);
					rows.getCell(3).setCellStyle(style_cell_f3);
					
					rows.createCell(4).setCellValue(producto.getNroOrdenCompra());
					rows.getCell(4).setCellStyle(style_cell_f3);
					
					rows.createCell(5).setCellValue(producto.getNombreMovimiento());
					rows.getCell(5).setCellStyle(style_cell_f4);
					
					rows.createCell(6).setCellValue(producto.getNombreEstado());
					rows.getCell(6).setCellStyle(style_cell_f3);
				
					ind_primero = true;
				
				} else {
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f5);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f5);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f5);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f5);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f5);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f5);				
				
				}
				
				rows.createCell(7).setCellValue(producto.getNombreProducto());
				if (ind_primero) {
					rows.getCell(7).setCellStyle(style_cell_f4);
				} else {
					rows.getCell(7).setCellStyle(style_cell_f5);
				}				
				
				rows.createCell(8).setCellValue(producto.getNombreUnidad());
				if (ind_primero) {
					rows.getCell(8).setCellStyle(style_cell_f6);
				} else {
					rows.getCell(8).setCellStyle(style_cell_f7);
				}
				
				rows.createCell(9).setCellValue(dec_form.format(getBigDecimal(producto.getCantidad())));
				if (ind_primero) {
					rows.getCell(9).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(9).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(10).setCellValue(dec_form.format(getBigDecimal(producto.getPrecioUnitario())));
				if (ind_primero) {
					rows.getCell(10).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(10).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(producto.getImporteTotal())));
				if (ind_primero) {
					rows.getCell(11).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(11).setCellStyle(style_cell_f9);
				}
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(producto.getCantidad()));
				precioUnitarioSubTotal = precioUnitarioSubTotal.add(getBigDecimal(producto.getPrecioUnitario()));
				importeSubTotal = importeSubTotal.add(getBigDecimal(producto.getImporteTotal()));
				count++;
				
				ind_primero = false;
				
				// Subtotal del ultimo registro
				if (count == listaDetalleOrdenIngreso.size() + 1) {
				
					hrow++;
						
					rows = sheet.createRow((short) hrow);
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f2);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f2);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f2);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f2);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f2);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f2);
					
					rows.createCell(7).setCellValue("TOTAL ORDEN");
					rows.getCell(7).setCellStyle(style_cell_f2);
					
					rows.createCell(8).setCellValue(Constantes.EMPTY);
					rows.getCell(8).setCellStyle(style_cell_f2);
					
					rows.createCell(9).setCellValue(dec_form.format(cantidadSubTotal));
					rows.getCell(9).setCellStyle(style_cell_f10);
					
					rows.createCell(10).setCellValue(dec_form.format(precioUnitarioSubTotal));
					rows.getCell(10).setCellStyle(style_cell_f10);
					
					rows.createCell(11).setCellValue(dec_form.format(importeSubTotal));
					rows.getCell(11).setCellStyle(style_cell_f10);
				
				}
	            
	            hrow++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param guiaRemisionBean
	 * @param listaGuiaRemision
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteGuiaRemision(String ruta, 
													   GuiaRemisionBean guiaRemisionBean,
													   List<GuiaRemisionBean> listaGuiaRemision) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("GUIA DE REMISION");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 7000);
			sheet.setColumnWidth(7, 7000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 4000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(9).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(9).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(9).setCellValue(det_fecha.toString());
	        row2.getCell(9).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(9).setCellValue(det_hora.toString());
	        row3.getCell(9).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			row5.createCell(1).setCellValue("REPORTE DE GUIA DE REMISION");
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaGuiaRemision.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaGuiaRemision.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(guiaRemisionBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = guiaRemisionBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaGuiaRemision.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaGuiaRemision.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(guiaRemisionBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(guiaRemisionBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN SALIDA");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO GUIA REMISION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("NRO MANIFIESTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("DESTINO");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("PESO TOTAL KGR");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("ESTADO");
	        row10.getCell(9).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 11;
	        int count = 1;
	        for (GuiaRemisionBean guiaRemision : listaGuiaRemision) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(guiaRemision.getFechaEmision());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(guiaRemision.getNroOrdenSalida());
		        rows.getCell(3).setCellStyle(style_cell_f3);
		        
		        rows.createCell(4).setCellValue(guiaRemision.getNroGuiaRemision());
		        rows.getCell(4).setCellStyle(style_cell_f3);
		        
		        rows.createCell(5).setCellValue(guiaRemision.getNroManifiestoCarga());
		        rows.getCell(5).setCellStyle(style_cell_f3);
		        
		        rows.createCell(6).setCellValue(guiaRemision.getNombreMovimiento());
		        rows.getCell(6).setCellStyle(style_cell);
		        
		        rows.createCell(7).setCellValue(guiaRemision.getNombreAlmacenDestino());
		        rows.getCell(7).setCellStyle(style_cell);
		        
		        rows.createCell(8).setCellValue(dec_form.format(getBigDecimal(guiaRemision.getPesoTotalKgr())));
		        rows.getCell(8).setCellStyle(style_cell_f2);
		        
		        rows.createCell(9).setCellValue(guiaRemision.getNombreEstado());
		        rows.getCell(9).setCellStyle(style_cell_f3);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param detalleGuiaRemisionBean
	 * @param listaDetalleGuiaRemision
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteDetalleGuiaRemision(String ruta,
															  DetalleGuiaRemisionBean detalleGuiaRemisionBean, 
															  List<DetalleGuiaRemisionBean> listaDetalleGuiaRemision) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("GUIA DE REMISION");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 7000);
			sheet.setColumnWidth(7, 4000);
			sheet.setColumnWidth(8, 11000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 9));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);
			style_tit_cabecera.setWrapText(true);
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell.setFont(font_norm);
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f2.setFont(font_bold);
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);
	        style_cell_f2.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f3.setFont(font_norm);
	        style_cell_f3.setBorderTop((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);
	        style_cell_f3.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        style_cell_f4.setBorderLeft((short) 1);
	        style_cell_f4.setBorderRight((short) 1);
			
			HSSFCellStyle style_cell_f5 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style_cell_f5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f5.setFont(font_norm);
	        style_cell_f5.setBorderLeft((short) 1);
	        style_cell_f5.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f6 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f6.setFont(font_norm);
	        style_cell_f6.setBorderTop((short) 1);
	        style_cell_f6.setBorderLeft((short) 1);
	        style_cell_f6.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f7 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f7.setFont(font_norm);
	        style_cell_f7.setBorderLeft((short) 1);
	        style_cell_f7.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f8 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f8.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f8.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f8.setFont(font_norm);
	        style_cell_f8.setBorderTop((short) 1);
	        style_cell_f8.setBorderLeft((short) 1);
	        style_cell_f8.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f9 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f9.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f9.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f9.setFont(font_norm);
	        style_cell_f9.setBorderLeft((short) 1);
	        style_cell_f9.setBorderRight((short) 1);
	        
	        HSSFCellStyle style_cell_f10 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f10.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f10.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style_cell_f10.setFont(font_bold);
	        style_cell_f10.setBorderBottom((short) 1);
	        style_cell_f10.setBorderLeft((short) 1);
	        style_cell_f10.setBorderRight((short) 1);
	        
	        
	        // Bloque Inicio
	        String path = ruta.substring(0, ruta.indexOf(Constantes.REPORT_PATH_RESOURCES));
	        InputStream is = new FileInputStream(path.concat(Constantes.IMAGE_INDECI_REPORT_PATH));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			is.close();
			CreationHelper helper = wb.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(1);
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			pict.resize();
	        
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.createCell(11).setCellValue(Constantes.TITULO_ENCABEZADO_REPORTE);
	        row1.getCell(11).setCellStyle(style_cabecera);
	        
	        HSSFRow row2 = sheet.createRow((short) 2);
	        StringBuilder det_fecha = new StringBuilder();
	        Date fecha_hora = Calendar.getInstance().getTime();
	        det_fecha.append(Constantes.FECHA_ENCABEZADO_REPORTE);
	        det_fecha.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_FECHA, fecha_hora));	        
			row2.createCell(11).setCellValue(det_fecha.toString());
	        row2.getCell(11).setCellStyle(style_cabecera);
	        
	        StringBuilder det_hora = new StringBuilder();
	        det_hora.append(Constantes.HORA_ENCABEZADO_REPORTE);
	        det_hora.append(DateUtil.obtenerFechaFormateada(Constantes.FORMATO_HORA, fecha_hora));
	        det_hora.append(Constantes.ESPACIO_ENCABEZADO_REPORTE_EXCEL);
	        HSSFRow row3 = sheet.createRow((short) 3);
			row3.createCell(11).setCellValue(det_hora.toString());
	        row3.getCell(11).setCellStyle(style_cabecera);
			// Bloque Fin
			
			
	        // Bloque Inicio
	        HSSFRow row5 = sheet.createRow((short) 5);
			StringBuilder det_titulo = new StringBuilder();
			det_titulo.append("REPORTE DE GUIA DE REMISION ");
			det_titulo.append(Constantes.SALTO_LINEA_PARRAFO);
			det_titulo.append(" DETALLE POR PRODUCTO");
			row5.createCell(1).setCellValue(det_titulo.toString());
	        row5.getCell(1).setCellStyle(style_tit_cabecera);	
			row5.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row7 = sheet.createRow((short) 7);
	        
	        HSSFRichTextString for_rep_ddi = new HSSFRichTextString("DDI: ");
	        HSSFRichTextString for_des_ddi = new HSSFRichTextString(listaDetalleGuiaRemision.get(0).getNombreDdi());
	        RichTextString det_ddi = new HSSFRichTextString(for_rep_ddi.getString() + for_des_ddi.getString());
	        int tam_ddi = listaDetalleGuiaRemision.get(0).getNombreDdi().length();
	        det_ddi.applyFont(0, 5, font_bold);
	        det_ddi.applyFont(5, tam_ddi + 5, font_norm);	        
			row7.createCell(1).setCellValue(det_ddi);	
			
			HSSFRichTextString for_rep_anio = new HSSFRichTextString("AÑO: ");
	        HSSFRichTextString for_des_anio = new HSSFRichTextString(detalleGuiaRemisionBean.getCodigoAnio());
	        RichTextString det_anio = new HSSFRichTextString(for_rep_anio.getString() + for_des_anio.getString());
	        int tam_anio = detalleGuiaRemisionBean.getCodigoAnio().length();
	        det_anio.applyFont(0, 5, font_bold);
	        det_anio.applyFont(5, tam_anio + 5, font_norm);	        
			row7.createCell(4).setCellValue(det_anio);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row8 = sheet.createRow((short) 8);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaDetalleGuiaRemision.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaDetalleGuiaRemision.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row8.createCell(1).setCellValue(det_alm);	
			
			HSSFRichTextString for_rep_mes_ini = new HSSFRichTextString("MES INICIO: ");
			String mesInicio = getMes(detalleGuiaRemisionBean.getCodigoMesInicio());
	        HSSFRichTextString for_des_mes_ini = new HSSFRichTextString(mesInicio);
	        HSSFRichTextString for_rep_mes_fin = new HSSFRichTextString("   MES FIN: ");
	        String mesFin = getMes(detalleGuiaRemisionBean.getCodigoMesFin());
	        HSSFRichTextString for_des_mes_fin = new HSSFRichTextString(mesFin);
	        RichTextString det_mes = new HSSFRichTextString(for_rep_mes_ini.getString() + for_des_mes_ini.getString() + 
	        												for_rep_mes_fin.getString() + for_des_mes_fin.getString());
	        det_mes.applyFont(0, 12, font_bold);
	        det_mes.applyFont(12, mesInicio.length() + 12, font_norm);
	        det_mes.applyFont(mesInicio.length() + 12, mesInicio.length() + 24, font_bold);
	        det_mes.applyFont(mesInicio.length() + 24, mesInicio.length() + mesFin.length() + 24, font_norm);
	        row8.createCell(4).setCellValue(det_mes);	
	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row10 = sheet.createRow((short) 10);
	        
	        row10.createCell(1).setCellValue("ITEM");
	        row10.getCell(1).setCellStyle(style_header);
	        
	        row10.createCell(2).setCellValue("FECHA EMISION");
	        row10.getCell(2).setCellStyle(style_header);
	        
	        row10.createCell(3).setCellValue("NRO ORDEN SALIDA");
	        row10.getCell(3).setCellStyle(style_header);
	        
	        row10.createCell(4).setCellValue("NRO GUIA REMISION");
	        row10.getCell(4).setCellStyle(style_header);
	        
	        row10.createCell(5).setCellValue("NRO MANIFIESTO");
	        row10.getCell(5).setCellStyle(style_header);
	        
	        row10.createCell(6).setCellValue("TIPO MOVIMIENTO");
	        row10.getCell(6).setCellStyle(style_header);
	        
	        row10.createCell(7).setCellValue("ESTADO");
	        row10.getCell(7).setCellStyle(style_header);
	        
	        row10.createCell(8).setCellValue("PRODUCTO");
	        row10.getCell(8).setCellStyle(style_header);
	        
	        row10.createCell(9).setCellValue("UNIDAD");
	        row10.getCell(9).setCellStyle(style_header);
			
	        row10.createCell(10).setCellValue("CANTIDAD");
	        row10.getCell(10).setCellStyle(style_header);
			
	        row10.createCell(11).setCellValue("PESO TOTAL KGR");
	        row10.getCell(11).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
			int hrow = 11;
			int row = 0;
			int count = 1;
			boolean ind_primero = false;
			BigDecimal cantidadSubTotal = BigDecimal.ZERO;
			BigDecimal pesoSubTotal = BigDecimal.ZERO;
			String nroGuiaRemision = Constantes.EMPTY;
			for (DetalleGuiaRemisionBean guia : listaDetalleGuiaRemision) {
	        	
	        	HSSFRow rows = sheet.createRow((short) hrow);
				
				if (!nroGuiaRemision.equals(guia.getNroGuiaRemision())) {
					
					if (count > 1) {
				
						rows.createCell(1).setCellValue(Constantes.EMPTY);
						rows.getCell(1).setCellStyle(style_cell_f2);
						
						rows.createCell(2).setCellValue(Constantes.EMPTY);
						rows.getCell(2).setCellStyle(style_cell_f2);
						
						rows.createCell(3).setCellValue(Constantes.EMPTY);
						rows.getCell(3).setCellStyle(style_cell_f2);
						
						rows.createCell(4).setCellValue(Constantes.EMPTY);
						rows.getCell(4).setCellStyle(style_cell_f2);
						
						rows.createCell(5).setCellValue(Constantes.EMPTY);
						rows.getCell(5).setCellStyle(style_cell_f2);
						
						rows.createCell(6).setCellValue(Constantes.EMPTY);
						rows.getCell(6).setCellStyle(style_cell_f2);
						
						rows.createCell(7).setCellValue(Constantes.EMPTY);
						rows.getCell(7).setCellStyle(style_cell_f2);
						
						rows.createCell(8).setCellValue("TOTAL ORDEN");
						rows.getCell(8).setCellStyle(style_cell_f2);
						
						rows.createCell(9).setCellValue(Constantes.EMPTY);
						rows.getCell(9).setCellStyle(style_cell_f2);
						
						rows.createCell(10).setCellValue(dec_form.format(cantidadSubTotal));
						rows.getCell(10).setCellStyle(style_cell_f10);
						
						rows.createCell(11).setCellValue(dec_form.format(pesoSubTotal));
						rows.getCell(11).setCellStyle(style_cell_f10);
						
						cantidadSubTotal = BigDecimal.ZERO;
						pesoSubTotal = BigDecimal.ZERO;
						hrow++;
						
						rows = sheet.createRow((short) hrow);
						
					}
						
					row++;
				
					nroGuiaRemision = guia.getNroGuiaRemision();
					
					rows.createCell(1).setCellValue(row);
					rows.getCell(1).setCellStyle(style_cell_f3);
					
					rows.createCell(2).setCellValue(guia.getFechaEmision());
					rows.getCell(2).setCellStyle(style_cell_f3);
					
					rows.createCell(3).setCellValue(guia.getNroOrdenSalida());
					rows.getCell(3).setCellStyle(style_cell_f3);
					
					rows.createCell(4).setCellValue(nroGuiaRemision);
					rows.getCell(4).setCellStyle(style_cell_f3);
					
					rows.createCell(5).setCellValue(guia.getNroManifiestoCarga());
					rows.getCell(5).setCellStyle(style_cell_f3);
					
					rows.createCell(6).setCellValue(guia.getNombreMovimiento());
					rows.getCell(6).setCellStyle(style_cell_f4);
					
					rows.createCell(7).setCellValue(guia.getNombreEstado());
					rows.getCell(7).setCellStyle(style_cell_f3);
				
					ind_primero = true;
				
				} else {
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f5);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f5);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f5);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f5);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f5);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f5);

					rows.createCell(7).setCellValue(Constantes.EMPTY);
					rows.getCell(7).setCellStyle(style_cell_f5);	
				
				}
				
				rows.createCell(8).setCellValue(guia.getNombreProducto());
				if (ind_primero) {
					rows.getCell(8).setCellStyle(style_cell_f4);
				} else {
					rows.getCell(8).setCellStyle(style_cell_f5);
				}				
				
				rows.createCell(9).setCellValue(guia.getUnidadMedida());
				if (ind_primero) {
					rows.getCell(9).setCellStyle(style_cell_f6);
				} else {
					rows.getCell(9).setCellStyle(style_cell_f7);
				}

				rows.createCell(10).setCellValue(dec_form.format(getBigDecimal(guia.getCantidad())));
				if (ind_primero) {
					rows.getCell(10).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(10).setCellStyle(style_cell_f9);
				}
				
				rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(guia.getPesoTotal())));
				if (ind_primero) {
					rows.getCell(11).setCellStyle(style_cell_f8);
				} else {
					rows.getCell(11).setCellStyle(style_cell_f9);
				}
				
				cantidadSubTotal = cantidadSubTotal.add(getBigDecimal(guia.getCantidad()));
				pesoSubTotal = pesoSubTotal.add(getBigDecimal(guia.getPesoTotal()));
				count++;
				
				ind_primero = false;
				
				// Subtotal del ultimo registro
				if (count == listaDetalleGuiaRemision.size() + 1) {
				
					hrow++;
						
					rows = sheet.createRow((short) hrow);
				
					rows.createCell(1).setCellValue(Constantes.EMPTY);
					rows.getCell(1).setCellStyle(style_cell_f2);
					
					rows.createCell(2).setCellValue(Constantes.EMPTY);
					rows.getCell(2).setCellStyle(style_cell_f2);
					
					rows.createCell(3).setCellValue(Constantes.EMPTY);
					rows.getCell(3).setCellStyle(style_cell_f2);
					
					rows.createCell(4).setCellValue(Constantes.EMPTY);
					rows.getCell(4).setCellStyle(style_cell_f2);
					
					rows.createCell(5).setCellValue(Constantes.EMPTY);
					rows.getCell(5).setCellStyle(style_cell_f2);
					
					rows.createCell(6).setCellValue(Constantes.EMPTY);
					rows.getCell(6).setCellStyle(style_cell_f2);
					
					rows.createCell(7).setCellValue(Constantes.EMPTY);
					rows.getCell(7).setCellStyle(style_cell_f2);
					
					rows.createCell(8).setCellValue("TOTAL ORDEN");
					rows.getCell(8).setCellStyle(style_cell_f2);
					
					rows.createCell(9).setCellValue(Constantes.EMPTY);
					rows.getCell(9).setCellStyle(style_cell_f2);
					
					rows.createCell(10).setCellValue(dec_form.format(cantidadSubTotal));
					rows.getCell(10).setCellStyle(style_cell_f10);
					
					rows.createCell(11).setCellValue(dec_form.format(pesoSubTotal));
					rows.getCell(11).setCellStyle(style_cell_f10);
				
				}
	            
	            hrow++;
	        }
	        // Bloque Fin
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	/**
	 * @param ruta
	 * @param kardexAlmacenBean 
	 * @param listaKardexAlmacen
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteKardexAlmacen(String ruta, 
														KardexAlmacenBean kardexAlmacenBean, 
														List<KardexAlmacenBean> listaKardexAlmacen) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("KARDEX");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 7000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(9, 6000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 11));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        HSSFRow row1 = sheet.createRow((short) 1);
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append("KARDEX N° ");
			det_encabezado.append(kardexAlmacenBean.getIdAlmacen());
			det_encabezado.append(Constantes.SEPARADOR);
			det_encabezado.append(listaKardexAlmacen.get(0).getNroKardex());
			row1.createCell(1).setCellValue(det_encabezado.toString());
	        row1.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row3 = sheet.createRow((short) 3);
	        
	        HSSFRichTextString for_rep_pro = new HSSFRichTextString("PRODUCTO: ");
	        HSSFRichTextString for_des_pro = new HSSFRichTextString(listaKardexAlmacen.get(0).getNombreProducto());
	        RichTextString det_pro = new HSSFRichTextString(for_rep_pro.getString() + for_des_pro.getString());
	        int tam_pro = listaKardexAlmacen.get(0).getNombreProducto().length();
	        det_pro.applyFont(0, 10, font_bold);
	        det_pro.applyFont(10, tam_pro + 10, font_norm);	        
			row3.createCell(1).setCellValue(det_pro);	
			
			HSSFRichTextString for_rep_uni = new HSSFRichTextString("UNIDAD MEDIDA: ");
	        HSSFRichTextString for_des_uni = new HSSFRichTextString(listaKardexAlmacen.get(0).getNombreUnidad());
	        RichTextString det_uni = new HSSFRichTextString(for_rep_uni.getString() + for_des_uni.getString());
	        int tam_uni = listaKardexAlmacen.get(0).getNombreUnidad().length();
	        det_uni.applyFont(0, 15, font_bold);
	        det_uni.applyFont(15, tam_uni + 15, font_norm);	        
			row3.createCell(6).setCellValue(det_uni);
			
			StringBuilder det_mes_anio = new StringBuilder();
			det_mes_anio.append("MES: ");
			det_mes_anio.append(getMes(kardexAlmacenBean.getCodigoMes()));
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(Constantes.SEPARADOR);
			det_mes_anio.append(Constantes.ESPACIO);
			det_mes_anio.append(kardexAlmacenBean.getCodigoAnio());
			row3.createCell(11).setCellValue(det_mes_anio.toString());			
			row3.getCell(11).setCellStyle(style_tit_cabecera);
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row4 = sheet.createRow((short) 4);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaKardexAlmacen.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaKardexAlmacen.get(0).getNombreAlmacen().length();
	        det_alm.applyFont(0, 9, font_bold);
	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row4.createCell(1).setCellValue(det_alm);	

	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row6 = sheet.createRow((short) 6);
	        
	        row6.createCell(1).setCellValue("Item");
	        row6.getCell(1).setCellStyle(style_header);
	        
	        row6.createCell(2).setCellValue("Fecha");
	        row6.getCell(2).setCellStyle(style_header);
	        
	        row6.createCell(3).setCellValue("Documento");
	        row6.getCell(3).setCellStyle(style_header);
	        
	        row6.createCell(4).setCellValue("Ingresos");
	        row6.getCell(4).setCellStyle(style_header);
	        
	        row6.createCell(5).setCellValue("Salidas");
	        row6.getCell(5).setCellStyle(style_header);
	        
	        row6.createCell(6).setCellValue("Saldo");
	        row6.getCell(6).setCellStyle(style_header);
	        
	        row6.createCell(7).setCellValue("Precio Compra (S/.)");
	        row6.getCell(7).setCellStyle(style_header);
	        
	        row6.createCell(8).setCellValue("Precio Promedio (S/.)");
	        row6.getCell(8).setCellStyle(style_header);
	        
	        row6.createCell(9).setCellValue("Importe Valorado (S/.)");
	        row6.getCell(9).setCellStyle(style_header);
			
			row6.createCell(10).setCellValue("Motivo");
	        row6.getCell(10).setCellStyle(style_header);
			
			row6.createCell(11).setCellValue("Nro Orden");
	        row6.getCell(11).setCellStyle(style_header);
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 7;
	        int count = 1;
	        for (KardexAlmacenBean kardex : listaKardexAlmacen) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(kardex.getFecha());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(kardex.getConcepto());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(dec_form.format(getBigDecimal(kardex.getCantidadIngresos())));
		        rows.getCell(4).setCellStyle(style_cell_f2);
		        
		        rows.createCell(5).setCellValue(dec_form.format(getBigDecimal(kardex.getCantidadSalidas())));
		        rows.getCell(5).setCellStyle(style_cell_f2);
		        
		        rows.createCell(6).setCellValue(dec_form.format(getBigDecimal(kardex.getCantidadSaldo())));
		        rows.getCell(6).setCellStyle(style_cell_f2);
		        
		        rows.createCell(7).setCellValue(dec_form.format(getBigDecimal(kardex.getPrecioCompra())));
		        rows.getCell(7).setCellStyle(style_cell_f2);
		        
		        rows.createCell(8).setCellValue(dec_form.format(getBigDecimal(kardex.getPrecioPromedio())));
		        rows.getCell(8).setCellStyle(style_cell_f2);
				
				rows.createCell(9).setCellValue(dec_form.format(getBigDecimal(kardex.getImporteValorizado())));
		        rows.getCell(9).setCellStyle(style_cell_f2);
		        
		        rows.createCell(10).setCellValue(getString(kardex.getMotivo()));
		        rows.getCell(10).setCellStyle(style_cell);
				
				rows.createCell(11).setCellValue(getString(kardex.getNroOrden()));
		        rows.getCell(11).setCellStyle(style_cell_f3);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
			HSSFRow rows = sheet.createRow((short) row + 6);
			
			rows.createCell(3).setCellValue("Jefe de Almacén");
		    rows.getCell(3).setCellStyle(style_cell_f4);
				
			rows.createCell(7).setCellValue("Jefe DDI");
		    rows.getCell(7).setCellStyle(style_cell_f4);	
			
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}

	
	
	public HSSFWorkbook generaExcelReporteBincardAlmacen(String ruta, 
			 BincardAlmacenBean bincardAlmacenBean, 
			 List<BincardAlmacenBean> listaBincardAlmacen) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
		HSSFSheet sheet = wb.createSheet("BINCARD");
		
		sheet.setColumnWidth(0, 500);
		sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 7000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 11));
		
		DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
		
		HSSFFont font_bold = wb.createFont();
		font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFFont font_norm = wb.createFont();
		font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
		style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style_cabecera.setFont(font_norm);
		
		HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
		style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_tit_cabecera.setFont(font_bold);     
		
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
		
		HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
		style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style_cell.setFont(font_norm);	        
		style_cell.setBorderBottom((short) 1);
		style_cell.setBorderLeft((short) 1);	        
		style_cell.setBorderRight((short) 1);
		style_cell.setBorderTop((short) 1);
		
		HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
		style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style_cell_f2.setFont(font_norm);	        
		style_cell_f2.setBorderBottom((short) 1);
		style_cell_f2.setBorderLeft((short) 1);	        
		style_cell_f2.setBorderRight((short) 1);
		style_cell_f2.setBorderTop((short) 1);
		
		HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
		style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_cell_f3.setFont(font_norm);	        
		style_cell_f3.setBorderBottom((short) 1);
		style_cell_f3.setBorderLeft((short) 1);	        
		style_cell_f3.setBorderRight((short) 1);
		style_cell_f3.setBorderTop((short) 1);
		
		HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
		style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		style_cell_f4.setFont(font_norm);
		style_cell_f4.setBorderTop((short) 1);
		
		
		// Bloque Inicio
		HSSFRow row1 = sheet.createRow((short) 1);
		StringBuilder det_encabezado = new StringBuilder();
		det_encabezado.append("BINCARD N° ");
		det_encabezado.append(bincardAlmacenBean.getIdAlmacen());
		det_encabezado.append(Constantes.SEPARADOR);
		det_encabezado.append(listaBincardAlmacen.get(0).getNroBincard());
		row1.createCell(1).setCellValue(det_encabezado.toString());
		row1.getCell(1).setCellStyle(style_tit_cabecera);	
		// Bloque Fin
		
		
		// Bloque Inicio
		HSSFRow row3 = sheet.createRow((short) 3);
		
		HSSFRichTextString for_rep_pro = new HSSFRichTextString("PRODUCTO: ");
		HSSFRichTextString for_des_pro = new HSSFRichTextString(listaBincardAlmacen.get(0).getNombreProducto());
		RichTextString det_pro = new HSSFRichTextString(for_rep_pro.getString() + for_des_pro.getString());
		int tam_pro = listaBincardAlmacen.get(0).getNombreProducto().length();
		det_pro.applyFont(0, 10, font_bold);
		det_pro.applyFont(10, tam_pro + 10, font_norm);	        
		row3.createCell(1).setCellValue(det_pro);	
		
		HSSFRichTextString for_rep_uni = new HSSFRichTextString("UNIDAD MEDIDA: ");
		HSSFRichTextString for_des_uni = new HSSFRichTextString(listaBincardAlmacen.get(0).getNombreUnidad());
		RichTextString det_uni = new HSSFRichTextString(for_rep_uni.getString() + for_des_uni.getString());
		int tam_uni = listaBincardAlmacen.get(0).getNombreUnidad().length();
		det_uni.applyFont(0, 15, font_bold);
		det_uni.applyFont(15, tam_uni + 15, font_norm);	        
		row3.createCell(4).setCellValue(det_uni);
		
		StringBuilder det_mes_anio = new StringBuilder();
		det_mes_anio.append("MES: ");
		det_mes_anio.append(getMes(bincardAlmacenBean.getCodigoMes()));
		det_mes_anio.append(Constantes.ESPACIO);
		det_mes_anio.append(Constantes.SEPARADOR);
		det_mes_anio.append(Constantes.ESPACIO);
		det_mes_anio.append(bincardAlmacenBean.getCodigoAnio());
		row3.createCell(8).setCellValue(det_mes_anio.toString());			
		row3.getCell(8).setCellStyle(style_tit_cabecera);
		// Bloque Fin
		
		
		// Bloque Inicio
		HSSFRow row4 = sheet.createRow((short) 4);
		
		HSSFRichTextString for_rep_alm = new HSSFRichTextString("ALMACEN: ");
		HSSFRichTextString for_des_alm = new HSSFRichTextString(listaBincardAlmacen.get(0).getNombreAlmacen());
		RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
		int tam_alm = listaBincardAlmacen.get(0).getNombreAlmacen().length();
		det_alm.applyFont(0, 9, font_bold);
		det_alm.applyFont(9, tam_alm + 9, font_norm);	        
		row4.createCell(1).setCellValue(det_alm);	
		
		// Bloque Fin
		
		
		// Bloque Inicio	        
		HSSFRow row6 = sheet.createRow((short) 6);
		
		row6.createCell(1).setCellValue("Item");
		row6.getCell(1).setCellStyle(style_header);
		
		row6.createCell(2).setCellValue("Fecha");
		row6.getCell(2).setCellStyle(style_header);
		
		row6.createCell(3).setCellValue("Documento");
		row6.getCell(3).setCellStyle(style_header);
		
		row6.createCell(4).setCellValue("Ingresos");
		row6.getCell(4).setCellStyle(style_header);
		
		row6.createCell(5).setCellValue("Salidas");
		row6.getCell(5).setCellStyle(style_header);
		
		row6.createCell(6).setCellValue("Saldo");
		row6.getCell(6).setCellStyle(style_header);
		
		row6.createCell(7).setCellValue("Motivo");
		row6.getCell(7).setCellStyle(style_header);
		
		row6.createCell(8).setCellValue("Nro Orden");
		row6.getCell(8).setCellStyle(style_header);
		// Bloque Fin
		
		
		// Bloque Inicio
		int row = 7;
		int count = 1;
		for (BincardAlmacenBean bincard : listaBincardAlmacen) {
		
		HSSFRow rows = sheet.createRow((short) row);
		
		rows.createCell(1).setCellValue(count);
		rows.getCell(1).setCellStyle(style_cell_f3);
		
		rows.createCell(2).setCellValue(bincard.getFecha());
		rows.getCell(2).setCellStyle(style_cell_f3);
		
		rows.createCell(3).setCellValue(bincard.getConcepto());
		rows.getCell(3).setCellStyle(style_cell);
		
		rows.createCell(4).setCellValue(dec_form.format(getBigDecimal(bincard.getCantidadIngresos())));
		rows.getCell(4).setCellStyle(style_cell_f2);
		
		rows.createCell(5).setCellValue(dec_form.format(getBigDecimal(bincard.getCantidadSalidas())));
		rows.getCell(5).setCellStyle(style_cell_f2);
		
		rows.createCell(6).setCellValue(dec_form.format(getBigDecimal(bincard.getCantidadSaldo())));
		rows.getCell(6).setCellStyle(style_cell_f2);
		
		rows.createCell(7).setCellValue(getString(bincard.getMotivo()));
		rows.getCell(7).setCellStyle(style_cell);
		
		rows.createCell(8).setCellValue(getString(bincard.getNroOrden()));
		rows.getCell(8).setCellStyle(style_cell_f3);
		
		row++;
		count++;
		}
		// Bloque Fin
		
		HSSFRow rows = sheet.createRow((short) row + 6);
		
		rows.createCell(3).setCellValue("Jefe de Almacén");
		rows.getCell(3).setCellStyle(style_cell_f4);
		
		rows.createCell(6).setCellValue("Jefe DDI");
		rows.getCell(6).setCellStyle(style_cell_f4);	
		
		
		} catch(Exception e) {
		LOGGER.error(e);
		throw new Exception();
		}
		return wb;
	}
	
	/**
	 * @param ruta
	 * @param listaSigaAlmacen
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteSigaAlmacenE(String ruta, 
														 List<OrdenIngresoBean> listaSigaAlmacen, String tipoExportacion) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("SIGA");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 5000);
			sheet.setColumnWidth(13, 5000);
			sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 5000);
			sheet.setColumnWidth(16, 5000);
			sheet.setColumnWidth(17, 5000);
			sheet.setColumnWidth(18, 5000);
			sheet.setColumnWidth(19, 5000);

			
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 20));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        
	     
	        // Bloque Inicio
	        HSSFRow row1 = sheet.createRow((short) 1);
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append("REPORTE SIGA");
			row1.createCell(1).setCellValue(det_encabezado.toString());
	        row1.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	     
	        // Bloque Inicio
	        HSSFRow row3 = sheet.createRow((short) 3);
	        
	        HSSFRichTextString for_rep_pro = new HSSFRichTextString("Año: ");
	        HSSFRichTextString for_des_pro = new HSSFRichTextString(listaSigaAlmacen.get(0).getCodigoAnio());
	        RichTextString det_pro = new HSSFRichTextString(for_rep_pro.getString() + for_des_pro.getString());
	     
	        int tam_pro = listaSigaAlmacen.get(0).getCodigoAnio().length();
	        
//	        det_pro.applyFont(0, 10, font_bold);
	       
//	        det_pro.applyFont(10, tam_pro + 10, font_norm);
	      
			row3.createCell(1).setCellValue(det_pro);	
		
			HSSFRichTextString for_rep_uni = new HSSFRichTextString("Mes: ");
	        HSSFRichTextString for_des_uni = new HSSFRichTextString(listaSigaAlmacen.get(0).getNombreMes());
	        RichTextString det_uni = new HSSFRichTextString(for_rep_uni.getString() + for_des_uni.getString());
	        int tam_uni = listaSigaAlmacen.get(0).getNombreMes().length();
//	        det_uni.applyFont(0, 15, font_bold);
//	        det_uni.applyFont(15, tam_uni + 15, font_norm);	        
			row3.createCell(4).setCellValue(det_uni);
			
			StringBuilder det_mes_anio = new StringBuilder();
			det_mes_anio.append("Tipo de Movimiento: ");
			
			if(tipoExportacion.equals("S")){
				det_mes_anio.append("Salida");
			}else{
				det_mes_anio.append("Entrada");
			}
			
			row3.createCell(8).setCellValue(det_mes_anio.toString());			
			row3.getCell(8).setCellStyle(style_tit_cabecera);
	        // Bloque Fin
	        
			
			// Bloque Inicio
	        HSSFRow row4 = sheet.createRow((short) 4);
	        
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("Almacén: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaSigaAlmacen.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaSigaAlmacen.get(0).getNombreAlmacen().length();
//	        det_alm.applyFont(0, 9, font_bold);
//	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row4.createCell(1).setCellValue(det_alm);	

	        // Bloque Fin
	        

			// Bloque Inicio	        
	        HSSFRow row6 = sheet.createRow((short) 6);
	        
	        row6.createCell(1).setCellValue("Item");
	        row6.getCell(1).setCellStyle(style_header);
	        
	        row6.createCell(2).setCellValue("Año");
	        row6.getCell(2).setCellStyle(style_header);
	        
	        row6.createCell(3).setCellValue("Mes");
	        row6.getCell(3).setCellStyle(style_header);
	        
	        row6.createCell(4).setCellValue("Tipo Movimiento");
	        row6.getCell(4).setCellStyle(style_header);
	        
	        row6.createCell(5).setCellValue("N° Orden Ingreso");
	        row6.getCell(5).setCellStyle(style_header);
	        
	        row6.createCell(6).setCellValue("Fecha");
	        row6.getCell(6).setCellStyle(style_header);
			
			row6.createCell(7).setCellValue("Almacén");
	        row6.getCell(7).setCellStyle(style_header);
			
			row6.createCell(8).setCellValue("Código SIGA");
	        row6.getCell(8).setCellStyle(style_header);
	        
	        row6.createCell(9).setCellValue("Producto");
	        row6.getCell(9).setCellStyle(style_header);
	        
	        row6.createCell(10).setCellValue("Unidad Medida");
	        row6.getCell(10).setCellStyle(style_header);
	        
	        row6.createCell(11).setCellValue("Cantidad");
	        row6.getCell(11).setCellStyle(style_header);
	        
	        row6.createCell(12).setCellValue("Precio");
	        row6.getCell(12).setCellStyle(style_header);
	        
	        row6.createCell(13).setCellValue("Importe Total");
	        row6.getCell(13).setCellStyle(style_header);
	        
	        row6.createCell(14).setCellValue("Marca");
	        row6.getCell(14).setCellStyle(style_header);
	        
	        row6.createCell(15).setCellValue("Fecha Vencimiento");
	        row6.getCell(15).setCellStyle(style_header);
	        
	        row6.createCell(16).setCellValue("Almacén Origen");
	        row6.getCell(16).setCellStyle(style_header);
	        
	        row6.createCell(17).setCellValue("N° Orden Salida");
	        row6.getCell(17).setCellStyle(style_header);
	        
	        row6.createCell(18).setCellValue("Orden Compra");
	        row6.getCell(18).setCellStyle(style_header);
	        
	        row6.createCell(19).setCellValue("Proveedor");
	        row6.getCell(19).setCellStyle(style_header);
	        // Bloque Fin

	        // Bloque Inicio
	        int row = 7;
	        int count = 1;
	        for (OrdenIngresoBean bincard : listaSigaAlmacen) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(bincard.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(bincard.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(bincard.getNombreMovimiento());
		        rows.getCell(4).setCellStyle(style_cell_f2);
		        
		        rows.createCell(5).setCellValue(bincard.getNroOrdenIngreso());
		        rows.getCell(5).setCellStyle(style_cell_f2);
		        
		        rows.createCell(6).setCellValue(bincard.getFechaEmision());
		        rows.getCell(6).setCellStyle(style_cell_f2);
		        
		        rows.createCell(7).setCellValue(bincard.getNombreAlmacen());
		        rows.getCell(7).setCellStyle(style_cell);
				
				rows.createCell(8).setCellValue(bincard.getCodigoSiga());
		        rows.getCell(8).setCellStyle(style_cell_f3);
		        
		        rows.createCell(9).setCellValue(bincard.getNombreProducto());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(bincard.getNombreUnidad());
		        rows.getCell(10).setCellStyle(style_cell);
		        
		        rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(bincard.getCantidad())));
		        rows.getCell(11).setCellStyle(style_cell);
		        
		        rows.createCell(12).setCellValue(dec_form.format(getBigDecimal(bincard.getPrecio())));
		        rows.getCell(12).setCellStyle(style_cell);
		        
		        rows.createCell(13).setCellValue(dec_form.format(getBigDecimal(bincard.getImporte())));
		        rows.getCell(13).setCellStyle(style_cell);
		        
		        rows.createCell(14).setCellValue(bincard.getNombreMarca());
		        rows.getCell(14).setCellStyle(style_cell);
		        
		        rows.createCell(15).setCellValue(bincard.getFechaVencimiento());
		        rows.getCell(15).setCellStyle(style_cell);
		        
		        rows.createCell(16).setCellValue(bincard.getNombreAlmacenOrigen());
		        rows.getCell(16).setCellStyle(style_cell);
		        
		        rows.createCell(17).setCellValue(bincard.getNroOrdenSalida());
		        rows.getCell(17).setCellStyle(style_cell);
		        
		        rows.createCell(18).setCellValue(bincard.getNroOrdenCompra());
		        rows.getCell(18).setCellStyle(style_cell);
		        
		        rows.createCell(19).setCellValue(bincard.getRazonSocial());
		        rows.getCell(19).setCellStyle(style_cell);
	            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
			HSSFRow rows = sheet.createRow((short) row + 6);
			
			rows.createCell(3).setCellValue("Jefe de Almacén");
		    rows.getCell(3).setCellStyle(style_cell_f4);
				
			rows.createCell(6).setCellValue("Jefe DDI");
		    rows.getCell(6).setCellStyle(style_cell_f4);	
			
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}
	
	/**
	 * @param ruta
	 * @param listaSigaAlmacen
	 * @return Objeto.
	 * @throws Exception 
	 */
	public HSSFWorkbook generaExcelReporteSigaAlmacenS(String ruta, 
														 List<OrdenIngresoBean> listaSigaAlmacen, String tipoExportacion) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		try {				
	        HSSFSheet sheet = wb.createSheet("SIGA");
	        
	        sheet.setColumnWidth(0, 500);
	        sheet.setColumnWidth(1, 1500);
	        sheet.setColumnWidth(2, 4000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 6000);
	        sheet.setColumnWidth(5, 7000);
	        sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 5000);
			sheet.setColumnWidth(13, 5000);
			sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 5000);
			sheet.setColumnWidth(16, 5000);
			sheet.setColumnWidth(17, 5000);
			
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 11));
	        
			DecimalFormatSymbols dec_for_symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat dec_form = new DecimalFormat(Constantes.EXPRESION_MONEDA, dec_for_symbols);
			
	        HSSFFont font_bold = wb.createFont();
	        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        
	        HSSFFont font_norm = wb.createFont();
	        font_norm.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

	        HSSFCellStyle style_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_cabecera.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cabecera.setFont(font_norm);
	        
	        HSSFCellStyle style_tit_cabecera = (HSSFCellStyle) wb.createCellStyle();
	        style_tit_cabecera.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_tit_cabecera.setFont(font_bold);     
	        
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
	        
	        HSSFCellStyle style_cell = (HSSFCellStyle) wb.createCellStyle();
	        style_cell.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style_cell.setFont(font_norm);	        
	        style_cell.setBorderBottom((short) 1);
	        style_cell.setBorderLeft((short) 1);	        
	        style_cell.setBorderRight((short) 1);
	        style_cell.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f2 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style_cell_f2.setFont(font_norm);	        
	        style_cell_f2.setBorderBottom((short) 1);
	        style_cell_f2.setBorderLeft((short) 1);	        
	        style_cell_f2.setBorderRight((short) 1);
	        style_cell_f2.setBorderTop((short) 1);
	        
	        HSSFCellStyle style_cell_f3 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style_cell_f3.setFont(font_norm);	        
	        style_cell_f3.setBorderBottom((short) 1);
	        style_cell_f3.setBorderLeft((short) 1);	        
	        style_cell_f3.setBorderRight((short) 1);
	        style_cell_f3.setBorderTop((short) 1);
			
			HSSFCellStyle style_cell_f4 = (HSSFCellStyle) wb.createCellStyle();
	        style_cell_f4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style_cell_f4.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
	        style_cell_f4.setFont(font_norm);
	        style_cell_f4.setBorderTop((short) 1);
	        
	        
	        // Bloque Inicio
	        HSSFRow row1 = sheet.createRow((short) 1);
			StringBuilder det_encabezado = new StringBuilder();
			det_encabezado.append("REPORTE SIGA");
			row1.createCell(1).setCellValue(det_encabezado.toString());
	        row1.getCell(1).setCellStyle(style_tit_cabecera);	
	        // Bloque Fin
			
	        
	        // Bloque Inicio
	        HSSFRow row3 = sheet.createRow((short) 3);
	        
	        HSSFRichTextString for_rep_pro = new HSSFRichTextString("Año: ");
	        HSSFRichTextString for_des_pro = new HSSFRichTextString(listaSigaAlmacen.get(0).getCodigoAnio());
	        RichTextString det_pro = new HSSFRichTextString(for_rep_pro.getString() + for_des_pro.getString());
	        int tam_pro = listaSigaAlmacen.get(0).getCodigoAnio().length();
//	        det_pro.applyFont(0, 10, font_bold);
//	        det_pro.applyFont(10, tam_pro + 10, font_norm);	        
			row3.createCell(1).setCellValue(det_pro);	
			
			HSSFRichTextString for_rep_uni = new HSSFRichTextString("Mes: ");
	        HSSFRichTextString for_des_uni = new HSSFRichTextString(listaSigaAlmacen.get(0).getNombreMes());
	        RichTextString det_uni = new HSSFRichTextString(for_rep_uni.getString() + for_des_uni.getString());
	        int tam_uni = listaSigaAlmacen.get(0).getNombreMes().length();
//	        det_uni.applyFont(0, 15, font_bold);
//	        det_uni.applyFont(15, tam_uni + 15, font_norm);	        
			row3.createCell(4).setCellValue(det_uni);
			
			StringBuilder det_mes_anio = new StringBuilder();
			det_mes_anio.append("Tipo de Movimiento: ");
			if(tipoExportacion.equals("S")){
				det_mes_anio.append("Salida");
			}else{
				det_mes_anio.append("Entrada");
			}
			row3.createCell(8).setCellValue(det_mes_anio.toString());			
			row3.getCell(8).setCellStyle(style_tit_cabecera);
	        // Bloque Fin
	        
	        
			// Bloque Inicio
	        HSSFRow row4 = sheet.createRow((short) 4);
	       
	        HSSFRichTextString for_rep_alm = new HSSFRichTextString("Almacén: ");
	        HSSFRichTextString for_des_alm = new HSSFRichTextString(listaSigaAlmacen.get(0).getNombreAlmacen());
	        RichTextString det_alm = new HSSFRichTextString(for_rep_alm.getString() + for_des_alm.getString());
	        int tam_alm = listaSigaAlmacen.get(0).getNombreAlmacen().length();
//	        det_alm.applyFont(0, 9, font_bold);
//	        det_alm.applyFont(9, tam_alm + 9, font_norm);	        
	        row4.createCell(1).setCellValue(det_alm);	

	        // Bloque Fin
	        
	        
			// Bloque Inicio	        
	        HSSFRow row6 = sheet.createRow((short) 6);
	        
	        row6.createCell(1).setCellValue("Item");
	        row6.getCell(1).setCellStyle(style_header);
	        
	        row6.createCell(2).setCellValue("Año");
	        row6.getCell(2).setCellStyle(style_header);
	        
	        row6.createCell(3).setCellValue("Mes");
	        row6.getCell(3).setCellStyle(style_header);
	        
	        row6.createCell(4).setCellValue("Tipo Movimiento");
	        row6.getCell(4).setCellStyle(style_header);
	        
	        row6.createCell(5).setCellValue("N° Orden Salida");
	        row6.getCell(5).setCellStyle(style_header);
	        
	        row6.createCell(6).setCellValue("Fecha");
	        row6.getCell(6).setCellStyle(style_header);
			
			row6.createCell(7).setCellValue("Almacén");
	        row6.getCell(7).setCellStyle(style_header);
			
			row6.createCell(8).setCellValue("Código SIGA");
	        row6.getCell(8).setCellStyle(style_header);
	        
	        row6.createCell(9).setCellValue("Producto");
	        row6.getCell(9).setCellStyle(style_header);
	        
	        row6.createCell(10).setCellValue("Unidad Medida");
	        row6.getCell(10).setCellStyle(style_header);
	        
	        row6.createCell(11).setCellValue("Cantidad");
	        row6.getCell(11).setCellStyle(style_header);
	        
	        row6.createCell(12).setCellValue("Precio");
	        row6.getCell(12).setCellStyle(style_header);
	        
	        row6.createCell(13).setCellValue("Importe Total");
	        row6.getCell(13).setCellStyle(style_header);
	        
	        row6.createCell(14).setCellValue("Marca");
	        row6.getCell(14).setCellStyle(style_header);
	        
	        row6.createCell(15).setCellValue("Fecha Vencimiento");
	        row6.getCell(15).setCellStyle(style_header);
	        
	        row6.createCell(16).setCellValue("Almacén Destino");
	        row6.getCell(16).setCellStyle(style_header);
	        
	        row6.createCell(17).setCellValue("Destino");
	        row6.getCell(17).setCellStyle(style_header);
	        
	        // Bloque Fin
	       
	        
	        // Bloque Inicio
	        int row = 7;
	        int count = 1;
	        for (OrdenIngresoBean bincard : listaSigaAlmacen) {
	        	
	        	HSSFRow rows = sheet.createRow((short) row);
	        	
	        	rows.createCell(1).setCellValue(count);
		        rows.getCell(1).setCellStyle(style_cell_f3);

		        rows.createCell(2).setCellValue(bincard.getCodigoAnio());
		        rows.getCell(2).setCellStyle(style_cell_f3);
		        
		        rows.createCell(3).setCellValue(bincard.getNombreMes());
		        rows.getCell(3).setCellStyle(style_cell);
		        
		        rows.createCell(4).setCellValue(bincard.getNombreMovimiento());
		        rows.getCell(4).setCellStyle(style_cell_f2);
		        
		        rows.createCell(5).setCellValue(bincard.getNroOrdenSalida());
		        rows.getCell(5).setCellStyle(style_cell_f2);
		        
		        rows.createCell(6).setCellValue(bincard.getFechaEmision());
		        rows.getCell(6).setCellStyle(style_cell_f2);
		        
		        rows.createCell(7).setCellValue(bincard.getNombreAlmacen());
		        rows.getCell(7).setCellStyle(style_cell);
				
				rows.createCell(8).setCellValue(bincard.getCodigoSiga());
		        rows.getCell(8).setCellStyle(style_cell_f3);
		        
		        rows.createCell(9).setCellValue(bincard.getNombreProducto());
		        rows.getCell(9).setCellStyle(style_cell);
		        
		        rows.createCell(10).setCellValue(bincard.getNombreUnidad());
		        rows.getCell(10).setCellStyle(style_cell);
		        
		        rows.createCell(11).setCellValue(dec_form.format(getBigDecimal(bincard.getCantidad())));
		        rows.getCell(11).setCellStyle(style_cell);
		        
		        rows.createCell(12).setCellValue(dec_form.format(getBigDecimal(bincard.getPrecio())));
		        rows.getCell(12).setCellStyle(style_cell);
		        
		        rows.createCell(13).setCellValue(dec_form.format(getBigDecimal(bincard.getImporte())));
		        rows.getCell(13).setCellStyle(style_cell);
		        
		        rows.createCell(14).setCellValue(bincard.getNombreMarca());
		        rows.getCell(14).setCellStyle(style_cell);
		        
		        rows.createCell(15).setCellValue(bincard.getFechaVencimiento());
		        rows.getCell(15).setCellStyle(style_cell);
		        
		        rows.createCell(16).setCellValue(bincard.getNombreAlmacenDestino());
		        rows.getCell(16).setCellStyle(style_cell);
		        
		        rows.createCell(17).setCellValue(bincard.getDestino());
		        rows.getCell(17).setCellStyle(style_cell);
		            
	            row++;
	            count++;
	        }
	        // Bloque Fin
			
			HSSFRow rows = sheet.createRow((short) row + 6);
			
			rows.createCell(3).setCellValue("Jefe de Almacén");
		    rows.getCell(3).setCellStyle(style_cell_f4);
				
			rows.createCell(6).setCellValue("Jefe DDI");
		    rows.getCell(6).setCellStyle(style_cell_f4);	
			
			
    	} catch(Exception e) {
    		LOGGER.error(e);
    		throw new Exception();
    	}
		return wb;
	}
    
}