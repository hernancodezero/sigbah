package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.ProductoBean;
import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.dao.GeneralDao;
import pe.com.sigbah.service.GeneralService;

/**
 * @className: GeneralServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_GENERAL.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Service
public class GeneralServiceImpl implements GeneralService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GeneralDao generalDao;

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarDepartamentos(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarDepartamentos(UbigeoBean ubigeoBean) throws Exception {
		return generalDao.listarDepartamentos(ubigeoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarProvincia(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarProvincia(UbigeoBean ubigeoBean) throws Exception {
		return generalDao.listarProvincia(ubigeoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarDistrito(pe.com.sigbah.common.bean.UbigeoBean)
	 */
	@Override
	public List<UbigeoBean> listarDistrito(UbigeoBean ubigeoBean) throws Exception {
		return generalDao.listarDistrito(ubigeoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarAnios()
	 */
	@Override
	public List<ItemBean> listarAnios() throws Exception {
		return generalDao.listarAnios();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarMeses(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMeses(ItemBean itemBean) throws Exception {
		return generalDao.listarMeses(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarDdi(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDdi(ItemBean itemBean) throws Exception {
		return generalDao.listarDdi(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarRegion(ItemBean itemBean) throws Exception {
		return generalDao.listarRegion(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarAlmacen(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacen(ItemBean itemBean) throws Exception {
		return generalDao.listarAlmacen(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarUnidadMedida(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarUnidadMedida(ItemBean itemBean) throws Exception {
		return generalDao.listarUnidadMedida(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarEnvase(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEnvase(ItemBean itemBean) throws Exception {
		return generalDao.listarEnvase(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarEstado(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstado(ItemBean itemBean) throws Exception {
		return generalDao.listarEstado(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarAlmacenExternoRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacenExternoRegion(ItemBean itemBean) throws Exception {
		return generalDao.listarAlmacenExternoRegion(itemBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarAlmacenExternoLocal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarAlmacenExternoLocal(ItemBean itemBean) throws Exception {
		return generalDao.listarAlmacenExternoLocal(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarTipoMovimiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoMovimiento(ItemBean itemBean) throws Exception {
		return generalDao.listarTipoMovimiento(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarTipoDocumento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoDocumento(ItemBean itemBean) throws Exception {
		return generalDao.listarTipoDocumento(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarTipoCamion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoCamion(ItemBean itemBean) throws Exception {
		return generalDao.listarTipoCamion(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarTipoControlCalidad(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoControlCalidad(ItemBean itemBean) throws Exception {
		return generalDao.listarTipoControlCalidad(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarProveedor(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarProveedor(ItemBean itemBean) throws Exception {
		return generalDao.listarProveedor(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarPersonal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonal(ItemBean itemBean) throws Exception {
		return generalDao.listarPersonal(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarPersonalExternoLocal(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonalExternoLocal(ItemBean itemBean) throws Exception {
		return generalDao.listarPersonalExternoLocal(itemBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarPersonalExternoRegion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonalExternoRegion(ItemBean itemBean) throws Exception {
		return generalDao.listarPersonalExternoRegion(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarModAlmacen(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarModAlmacen(ItemBean itemBean) throws Exception {
		return generalDao.listarModAlmacen(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarFuenteFinanciamiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarFuenteFinanciamiento(ItemBean itemBean) throws Exception {
		return generalDao.listarFuenteFinanciamiento(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarMarca(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMarca(ItemBean itemBean) throws Exception {
		return generalDao.listarMarca(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarDonante(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDonante(ItemBean itemBean) throws Exception {
		return generalDao.listarDonante(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarMoneda(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMoneda(ItemBean itemBean) throws Exception {
		return generalDao.listarMoneda(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarPais(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPais(ItemBean itemBean) throws Exception {
		return generalDao.listarPais(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarCategoria(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarCategoria(ItemBean itemBean) throws Exception {
		return generalDao.listarCategoria(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarDee(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarDee(ItemBean itemBean) throws Exception {
		return generalDao.listarDee(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarCatologoProductos(pe.com.sigbah.common.bean.ProductoBean)
	 */
	@Override
	public List<ProductoBean> listarCatologoProductos(ProductoBean productoBean) throws Exception {
		return generalDao.listarCatologoProductos(productoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarMedioTransporte(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarMedioTransporte(ItemBean itemBean) throws Exception {
		return generalDao.listarMedioTransporte(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarEmpresaTransporte(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEmpresaTransporte(ItemBean itemBean) throws Exception {
		return generalDao.listarEmpresaTransporte(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarEjecutora()
	 */
	@Override
	public List<ItemBean> listarEjecutora() throws Exception {
		return generalDao.listarEjecutora();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarChofer(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarChofer(ItemBean itemBean) throws Exception {
		return generalDao.listarChofer(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.general.GeneralService#listarParametro(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarParametro(ItemBean itemBean) throws Exception {
		return generalDao.listarParametro(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#obtenerAnioActual()
	 */
	@Override
	public String obtenerAnioActual() throws Exception {
		return generalDao.obtenerAnioActual();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarEstadoDonacion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstadoDonacion(ItemBean itemBean) throws Exception {
		return generalDao.listarEstadoDonacion(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarTipoMovimientoPm()
	 */
	@Override
	public List<ItemBean> listarTipoMovimientoPm() throws Exception {
		return generalDao.listarTipoMovimientoPm();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarProgramacion()
	 */
	@Override
	public List<ItemBean> listarProgramacion() throws Exception {
		return generalDao.listarProgramacion();
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarFenomeno(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarFenomeno(ItemBean itemBean) throws Exception {
		return generalDao.listarFenomeno(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarRacion(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarRacion(ItemBean itemBean) throws Exception {
		return generalDao.listarRacion(itemBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarMotivoTraslado()
	 */
	@Override
	public List<ItemBean> listarMotivoTraslado() throws Exception {
		return generalDao.listarMotivoTraslado();
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarOficinas(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarOficinas(ItemBean itemBean) throws Exception {
		return generalDao.listarOficinas(itemBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarPersonalOficina(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarPersonalOficina(ItemBean itemBean) throws Exception {
		return generalDao.listarPersonalOficina(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarTipoProducto(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarTipoProducto(ItemBean itemBean) throws Exception {
		return generalDao.listarTipoProducto(itemBean);
	}
	
	@Override
	public List<ItemBean> listarEstadoPedidoCompra(ItemBean itemBean) throws Exception {
		return generalDao.listarEstadoPedidoCompra(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarEstadoDee(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstadoDee(ItemBean itemBean) throws Exception {
		return generalDao.listarEstadoDee(itemBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.GeneralService#listarEstadoRequerimiento(pe.com.sigbah.common.bean.ItemBean)
	 */
	@Override
	public List<ItemBean> listarEstadoRequerimiento(ItemBean itemBean) throws Exception {
		return generalDao.listarEstadoRequerimiento(itemBean);
	}
	
	@Override
	public List<ItemBean> listarProgramacion1(ItemBean itemBean) throws Exception {
		return generalDao.listarProgramacion1(itemBean);
	}

}
