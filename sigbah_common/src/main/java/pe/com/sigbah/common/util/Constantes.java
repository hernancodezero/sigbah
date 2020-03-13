package pe.com.sigbah.common.util;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: Constantes.java
 * @description: Clase que guarda las constantes del sistema.
 * @date: 18/02/2015 22:26:30
 * @author: Hernando Huaman.
 */
@SuppressWarnings("javadoc")
public final class Constantes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* USER STATUS */
	public static final String USER_COOKIE_ID = "Usuario";
    public static final String USER_ACCESS_COOKIE_ID = "Acceso";
    public static final String SESSION_SATTUS = "State";
    public static final int TI_IDENTIFICACION = 0;

	/* MANEJO DE ERRORES */
    public static final String COD_VALIDACION_GENERAL = "02";
	public static final String COD_EXITO_GENERAL = "01";
	public static final String COD_ERROR_GENERAL = "00";

	/* DATOS GENERALES */
	public static final String EMPTY = "";
	public static final short ZERO_SHORT = 0;
	public static final int ZERO_INT = 0;
	public static final long ZERO_LONG = 0;
	public static final String ZERO_STRING = "0";
	public static final short ONE_SHORT = 1;
	public static final int ONE_INT = 1;
	public static final String ONE_STRING = "1";
	public static final short TWO_SHORT = 2;
	public static final int TWO_INT = 2;
	public static final String TWO_STRING = "2";
	public static final short THREE_SHORT = 3;
	public static final int THREE_INT = 3;
	public static final String THREE_STRING = "3";
	public static final short FOUR_SHORT = 4;
	public static final int FOUR_INT = 4;
	public static final String FOUR_STRING = "4";
	public static final short FIVE_SHORT = 5;
	public static final int FIVE_INT = 5;
	public static final String FIVE_STRING = "5";
	public static final short SIX_SHORT = 6;
	public static final int SIX_INT = 6;
	public static final String SIX_STRING = "6";
	public static final short SEVEN_SHORT = 7;
	public static final int SEVEN_INT = 7;
	public static final String SEVEN_STRING = "7";
	public static final short EIGHT_SHORT = 8;
	public static final int EIGHT_INT = 8;
	public static final short NINE_SHORT = 9;
	public static final int NINE_INT = 9;
	public static final short TEN_SHORT = 10;
	public static final String CONDICION_Y = "and";
	public static final String CONDICION_O = "or";	
	public static final String CONDICION_I = "i";
	public static final String CONDICION_SI = "si";
	public static final String ESPACIO = " ";
	public static final BigDecimal IGV = BigDecimal.valueOf(0.18);
	public static final String EXPRESION_MONEDA = "###,###,##0.00";
	public static final String EXPRESION_MONEDA_DECIMAL_1 = "###,###,##0.0";
	public static final String EXPRESION_MONEDA_DECIMAL = "###,###,##0.000";
	public static final String EXPRESION_CANTIDAD = "###,###,##0";
	public static final String EXPRESION_MONEDA_DECIMAL6 = "###,###,##0.000000";
	
	/* CODIGOS SISTEMA SIG_BAH */
	public static final String CODIGO_MODULO_SIG_BAH = "SIG_BAH";
	public static final String ESQUEMA_SINPAD = "SINPAD";
	public static final String PACKAGE_ADMINISTRACION = "BAH_PKG_ADMINISTRACION";
	public static final String PACKAGE_DONACION = "BAH_PKG_DONACION";
	public static final String PACKAGE_GENERAL = "BAH_PKG_GENERAL";
	public static final String PACKAGE_LOGISTICA = "BAH_PKG_LOGISTICA";
	public static final String PACKAGE_PROGRAMACION = "BAH_PKG_PROGRAMACION";
	public static final String PACKAGE_STOCK = "BAH_PKG_STOCK";
	public static final String CODIGO_TIPO_ALMACEN = "A";
	public static final String CODIGO_TIPO_DONACION = "D";	
	public static final String TIPO_ORIGEN_ALMACENES = "I";
	public static final String TIPO_ORIGEN_DONACIONES = "D";	
	public static final String CODIGO_DDI_INDECI_CENTRAL = "27";	
	public static final int COD_GENERADO = 13;
	public static final String DES_GENERADO = "Generado";
	public static final String MODULO_DONACION = "DONACION";
	public static final String MODULO_PROGRAMACION = "PROGRAMACION";
	public static final String MODULO_INVENTARIO = "INVENTARIO";
	
	/* PARAMETROS REPORTE SIG_BAH */
	public static final String TITULO_ENCABEZADO_REPORTE = "Sistema de Gestion de Bienes de Ayuda Humanitaria - SIGBAH v1.0";
	public static final String FECHA_ENCABEZADO_REPORTE = "Fecha : ";
	public static final String HORA_ENCABEZADO_REPORTE = "Hora : ";
	public static final String ESPACIO_ENCABEZADO_REPORTE_PDF = "        .";
	public static final String ESPACIO_ENCABEZADO_REPORTE_EXCEL = "        ";

	/* ESTADOS SIHBAH */
	public static final int ESTADO_INACTIVO = 0;
	public static final int ESTADO_ACTIVO = 1;
	public static final int ESTADO_ELIMINADO = 2;
	
	public static final String FLAG_ACTIVO = "S";
	public static final String FLAG_INACTIVO = "N";
	
	public static final boolean ESTADO_ACTIVO_TRUE = true;
	public static final boolean ESTADO_ACTIVO_FALSE = false;

	public static final boolean ULTIMO_SECUENCIA_TRUE = true;
	public static final boolean ULTIMO_SECUENCIA_FALSE = false;

	/* ESTADOS MIGRACION DE DATOS */
	public static final boolean MIGRADO_TRUE = true;
	public static final boolean MIGRADO_FALSE = false;

	/* RESPUESTAS */
	public static final String RESPUESTA_SI = "Si";
	public static final String RESPUESTA_NO = "No";
	public static final String ACCION_CORRECTA_JSON = "{\"codigoRespuesta\":\"01\"}";
	public static final String ACCION_FALLIDA_JSON = "{\"codigoRespuesta\":\"00\"}";
	
	/* PARAMETROS CONFIGURACION ALFRESCO  */
	public static final String FICHERO_CONFIGURACION = new StringBuilder("WEB-INF")
			.append(File.separator).append("i18n").append(File.separator)
			.append("config.properties").toString();
	
	public static final String CODIGO_ERROR_401 = "401";
	public static final String CODIGO_ERROR_403 = "403";
	public static final String CODIGO_ERROR_404 = "404";
	public static final String CODIGO_ERROR_500 = "500";
	public static final Integer TICKET_SIZE = 47;
	public static final Integer ID_ALFRESCO_SIZE = 36;

	
	/* UNIDADES DE MEDIDA */
	public static final String UNIDAD_MEDIDA_GLOBAL = "gbl";	

	/* FECHA Y HORA */
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_DB = "yyyy-MM-dd";
	public static final String FORMATO_FECHA_HORA_DB = "EE MMM dd hh:mm:ss z yyyy";
	public static final String FORMATO_HORA = "HH:mm";
	public static final int DIA_ULTIMA_HORA = 23;
	public static final int DIA_ULTIMO_SEGUNDO = 59;
	public static final long L_23_HOURS_59 = 86400000;
	public static final String COD_FORMATO_FECHA = "104";

	/* PROYECTO */
	public static final BigDecimal AVANCE_CERO_POR_CIENTO = BigDecimal.valueOf(0);
	public static final BigDecimal AVANCE_CIEN_POR_CIENTO = BigDecimal.valueOf(100);

	/* REPORTES */	
	public static final String REPORT_PATH_RESOURCES = "resources";
			
	public static final String REPORT_PATH_ADMINISTRACION = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("administracion").append(File.separator).append("jrxml")
			.append(File.separator).toString();
	
	public static final String REPORT_PATH_ALMACENES = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("gestion_almacenes").append(File.separator).append("jrxml")
			.append(File.separator).toString();
	
	public static final String REPORT_PATH_DONACIONES = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("donaciones").append(File.separator).append("jrxml")
			.append(File.separator).toString();
	
	public static final String REPORT_PATH_PROGRAMACION = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("programacion_bah").append(File.separator).append("jrxml")
			.append(File.separator).toString();
	
	public static final String REPORT_JASPER_PATH_ADMINISTRACION = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("administracion").append(File.separator).append("jasper")
			.append(File.separator).toString();
	
	public static final String REPORT_JASPER_PATH_ALMACENES = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("gestion_almacenes").append(File.separator).append("jasper")
			.append(File.separator).toString();
	
	public static final String REPORT_JASPER_PATH_DONACIONES = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("donaciones").append(File.separator).append("jasper")
			.append(File.separator).toString();
	
	public static final String REPORT_JASPER_PATH_PROGRAMACION = new StringBuilder("resources")
			.append(File.separator).append("report").append(File.separator)
			.append("programacion_bah").append(File.separator).append("jasper")
			.append(File.separator).toString();
	
	public static final String IMAGE_INDECI_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("logo-indeci.png").toString();
	
	public static final String IMAGE_INDECI2_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("logo-indeci2.png").toString();
	
	public static final String IMAGE_WFP_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("logo-wfp.png").toString();
	
	public static final String IMAGE_CHECK_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("check.png").toString();
	
	public static final String IMAGE_CHECK_MIN_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("check-min.png").toString();
	
	public static final String IMAGE_INPUT_CHECK_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("checkbox_unchecked.png").toString();
	
	public static final String IMAGE_INPUT_CHECKED_REPORT_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("checkbox_checked.png").toString();
	
	public static final String IMAGE_INPUT_ANULADO_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("anulado.png").toString();
	
	public static final String IMAGE_INPUT_NOANULADO_PATH = new StringBuilder("resources")
			.append(File.separator).append("img").append(File.separator)
			.append("vacio.png").toString();
	
	public static final String UPLOAD_PATH_FILE_TEMP = new StringBuilder("resources")
			.append(File.separator).append("upload").append(File.separator).toString();

	public static final short TIPO_REPORTE_DETALLADO = 1;
	public static final short TIPO_REPORTE_ESTADISTICO = 2;
	public static final short CODIGO_FORMATO_REPORTE_PDF = 1;
	public static final short CODIGO_FORMATO_REPORTE_XLS = 2;

	/* EXTENSIONES DE ARCHIVO */
	public static final String EXTENSION_FORMATO_PDF = ".pdf";
	public static final String EXTENSION_FORMATO_XLS = ".xls";
	public static final String EXTENSION_FORMATO_DOC = ".doc";
	public static final String EXTENSION_FORMATO_DOCX = ".docx";
	public static final String EXTENSION_FORMATO_JPG = ".jpg";
	public static final String EXTENSION_FORMATO_PNG = ".png";
	public static final String EXTENSION_FORMATO_GIF = ".gif";
	public static final String EXTENSION_FORMATO_RPT = ".rpt";
	public static final String EXTENSION_FORMATO_TXT = ".TXT";
	public static final String EXTENSION_FORMATO_PS4 = ".ps4";
	public static final String EXTENSION_FORMATO_4TA = ".4ta";

	/* MEDIA TYPE */
	public static final String MIME_APPLICATION_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public static final String MIME_APPLICATION_DOC = "application/msword";
	public static final String MIME_APPLICATION_PDF = "application/pdf";
	public static final String MIME_APPLICATION_XLS = "application/vnd.ms-excel";
	public static final String MIME_APPLICATION_TXT = "text/plain";
	public static final String MIME_IMAGE_JPG = "image/jpeg";
	public static final String MIME_IMAGE_PNG = "image/png";
	public static final String MIME_IMAGE_GIF = "image/gif";
	
	/* CODIGOS PARA MANEJO DE CADENAS */
	public static final String COD_CADENA_CORTADA = "...(*)";
	public static final String PUNTO = ".";
	public static final String DOS_PUNTOS = ":";
	public static final String SEPARADOR = "-";
	public static final String DIVISOR = "/";
	public static final String PORCENTAJE = "%";
	public static final String EXPRESION_OR = "||";
	public static final String EXPRESION_AND = "&&";
	public static final String UNDERLINE = "_";
	public static final String PIPELINE = "|";
	public static final String SALTO_LINEA = "\r\n";
	public static final String SALTO_LINEA_PARRAFO = "\n";
	public static final String IMAGEN_NO_DISPONIBLE	= "no-disponible.png";
	
	/* CODIGOS PARA MESES */
	public static final short ENERO = 1;
	public static final short FEBRERO = 2;
	public static final short MARZO = 3;
	public static final short ABRIL = 4;
	public static final short MAYO = 5;
	public static final short JUNIO = 6;
	public static final short JULIO = 7;
	public static final short AGOSTO = 8;
	public static final short SEPTIEMBRE = 9;
	public static final short OCTUBRE = 10;
	public static final short NOVIEMBRE = 11;
	public static final short DICIEMBRE = 12;
	
	/* CODIGO PARA USUARIO VISITANTE Y MENU */
	public static final short CODIGO_USUARIO_VISITANTE = 2;
	public static final short CODIGO_ROL_VISITANTE = 2;
	public static final int CODIGO_MENU_CATEGORIA = 4;
	
	/* CODIGO USUARIO TEMPORAL */
	public static final String CODIGO_USUARIO = "admin";
	public static final int CODIGO_SEDE_CENTRAL = 1;
	
	/* RUTA PDF */
	public static final String PDF_PATH_GUIA_DON = new StringBuilder("resources")
			.append(File.separator).append("pdf").append(File.separator)
			.append("guia").append(File.separator).append("donacion")
			.append(File.separator).toString();
	public static final String PDF_PATH_HIS_DON = new StringBuilder("resources")
			.append(File.separator).append("pdf").append(File.separator)
			.append("historial").append(File.separator).append("donacion")
			.append(File.separator).toString();
	
	public static final String PDF_PATH_GUIA_ALM = new StringBuilder("resources")
			.append(File.separator).append("pdf").append(File.separator)
			.append("guia").append(File.separator).append("almacen")
			.append(File.separator).toString();
	
}
