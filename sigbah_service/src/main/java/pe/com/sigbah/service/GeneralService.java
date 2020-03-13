package pe.com.sigbah.service;

import java.util.List;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.UbigeoBean;

/**
 * @className: GeneralService.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_GENERAL.
 * @date: 20 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public interface GeneralService {
	
	/**
	 * @param ubigeoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<UbigeoBean> listarDepartamentos(UbigeoBean ubigeoBean) throws Exception;
	
	/**
	 * @param ubigeoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<UbigeoBean> listarProvincia(UbigeoBean ubigeoBean) throws Exception;
	
	/**
	 * @param ubigeoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<UbigeoBean> listarDistrito(UbigeoBean ubigeoBean) throws Exception;
	
	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarAnios() throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarMeses(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarDdi(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarRegion(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarAlmacen(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarUnidadMedida(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEnvase(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstado(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarAlmacenExternoRegion(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarAlmacenExternoLocal(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoMovimiento(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoDocumento(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoCamion(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoControlCalidad(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarProveedor(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPersonal(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPersonalExternoLocal(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPersonalExternoRegion(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarModAlmacen(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarFuenteFinanciamiento(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarMarca(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarDonante(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarMoneda(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPais(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarCategoria(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarDee(ItemBean itemBean) throws Exception;
	
	/**
	 * @param productoBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ProductoBean> listarCatologoProductos(ProductoBean productoBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarMedioTransporte(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEmpresaTransporte(ItemBean itemBean) throws Exception;
	
	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEjecutora() throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarChofer(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarParametro(ItemBean itemBean) throws Exception;
	
	/**
	 * @return Año actual.
	 * @throws Exception
	 */
	public abstract String obtenerAnioActual() throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstadoDonacion(ItemBean itemBean) throws Exception;
	
	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoMovimientoPm() throws Exception;
	
	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarProgramacion() throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarRacion(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarFenomeno(ItemBean itemBean) throws Exception;

	/**
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarMotivoTraslado() throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarOficinas(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPersonalOficina(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTipoProducto(ItemBean itemBean) throws Exception;

	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstadoPedidoCompra(ItemBean itemBean) throws Exception;
	
	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstadoDee(ItemBean itemBean) throws Exception;

	/**
	 * @param itemBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstadoRequerimiento(ItemBean itemBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarProgramacion1(ItemBean itemBean) throws Exception;
	
}
