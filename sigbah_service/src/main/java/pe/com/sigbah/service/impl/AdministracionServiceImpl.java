package pe.com.sigbah.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sigbah.common.bean.AlmacenBean;
import pe.com.sigbah.common.bean.CatalogoProductoBean;
import pe.com.sigbah.common.bean.CierreStockBean;
import pe.com.sigbah.common.bean.CorreoBean;
import pe.com.sigbah.common.bean.DetalleUsuarioBean;
import pe.com.sigbah.common.bean.EstadoUsuarioBean;
import pe.com.sigbah.common.bean.EstadosXUsuarioBean;
import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.bean.MaeAlmacenBean;
import pe.com.sigbah.common.bean.MaeAlmacenExternoBean;
import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeChoferBean;
import pe.com.sigbah.common.bean.MaeCorrelativoBean;
import pe.com.sigbah.common.bean.MaeDdiBean;
import pe.com.sigbah.common.bean.MaeDistritoIneiBean;
import pe.com.sigbah.common.bean.MaeDonanteBean;
import pe.com.sigbah.common.bean.MaeEmpresaTransporteBean;
import pe.com.sigbah.common.bean.MaeEnvaseBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoDonacionBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeMarcaBean;
import pe.com.sigbah.common.bean.MaeMedioTransporteBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;
import pe.com.sigbah.common.bean.MaeMonedaBean;
import pe.com.sigbah.common.bean.MaeMotivoTrasladoBean;
import pe.com.sigbah.common.bean.MaeOficinaBean;
import pe.com.sigbah.common.bean.MaePaisBean;
import pe.com.sigbah.common.bean.MaeParametroBean;
import pe.com.sigbah.common.bean.MaePersonalBean;
import pe.com.sigbah.common.bean.MaePersonalExternoBean;
import pe.com.sigbah.common.bean.MaeRegionBean;
import pe.com.sigbah.common.bean.MaeTipCamionBean;
import pe.com.sigbah.common.bean.MaeTipControlCalidadBean;
import pe.com.sigbah.common.bean.MaeTipDocumentoBean;
import pe.com.sigbah.common.bean.MaeTipMovimientoBean;
import pe.com.sigbah.common.bean.MaeUnidadMedidaBean;
import pe.com.sigbah.common.bean.ModuloBean;
import pe.com.sigbah.common.bean.RolBean;
import pe.com.sigbah.common.bean.RolMenuBean;
import pe.com.sigbah.common.bean.UsuarioBean;
import pe.com.sigbah.dao.AdministracionDao;
import pe.com.sigbah.service.AdministracionService;

/**
 * @className: AdministracionServiceImpl.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_ADMINISTRACION.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
@Service
public class AdministracionServiceImpl implements AdministracionService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdministracionDao administracionDao;

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.AdministracionService#obtenerDatosUsuario(pe.com.sigbah.common.bean.UsuarioBean)
	 */
	@Override
	public DetalleUsuarioBean obtenerDatosUsuario(UsuarioBean usuarioBean) throws Exception {
		return administracionDao.obtenerDatosUsuario(usuarioBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.AdministracionService#listarModuloUsuario(java.lang.Integer)
	 */
	@Override
	public List<ModuloBean> listarModuloUsuario(Integer idUsuario) throws Exception {
		return administracionDao.listarModuloUsuario(idUsuario);
	}

	/* (non-Javadoc)
	 * @see pe.com.sigbah.service.AdministracionService#listarEstadoUsuario(pe.com.sigbah.common.bean.EstadoUsuarioBean)
	 */
	@Override
	public List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception {
		return administracionDao.listarEstadoUsuario(estadoUsuarioBean);
	}
	
	@Override
	public CierreStockBean obtenerMesTrabajo(Integer idAlmacen) throws Exception {
		return administracionDao.obtenerMesTrabajo(idAlmacen);
	}
	
	@Override
	public List<ItemBean> listarTablasGenerales() throws Exception {
		return administracionDao.listarTablasGenerales();
	}
	
	@Override
	public List<MaeAlmacenBean> listarTablaAlmacen(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaAlmacen(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeAlmacenExternoBean> listarTablaAlmacenExterno(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaAlmacenExterno(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeAlmacenExternoBean> listarTablaAlmacenExterno1(ItemBean itemBean) throws Exception {
		return administracionDao.listarTablaAlmacenExterno1(itemBean);
	}
	
	@Override
	public MaeAlmacenExternoBean obtenerCodigoAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean) throws Exception {
		return administracionDao.obtenerCodigoAlmacenExterno(maeAlmacenExternoBean);
	}
	
	@Override
	public MaeAlmacenBean insertarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		return administracionDao.insertarRegistroAlmacen(maeAlmacenBean);
	}
	
	@Override
	public MaeAlmacenExternoBean insertarRegistroAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean, String control) throws Exception {
		return administracionDao.insertarRegistroAlmacenExterno(maeAlmacenExternoBean, control);
	}
	
	@Override
	public List<MaeAniosBean> listarTablaAnios(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaAnios(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeCategoriaBean> listarTablaCategoria(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaCategoria(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeChoferBean> listarTablaChofer(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaChofer(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeDistritoIneiBean> listarTablaDistritoInei(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaDistritoInei(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeDonanteBean> listarTablaDonante(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaDonante(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeEmpresaTransporteBean> listarTablaEmpresaTransporte(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaEmpresaTransporte(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeEnvaseBean> listarTablaEnvase(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaEnvase(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeEstadoBean> listarTablaEstado(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaEstado(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeEstadoDonacionBean> listarTablaEstadoDonacion(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaEstadoDonacion(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeEstadoProgramacionBean> listarTablaEstadoProgramacion(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaEstadoProgramacion(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeMedioTransporteBean> listarTablaMedioTransporte(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaMedioTransporte(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeModAlmacenBean> listarTablaModAlmacen(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaModAlmacen(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeMonedaBean> listarTablaMoneda(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaMoneda(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeMotivoTrasladoBean> listarTablaMotivoTraslado(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaMotivoTraslado(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeOficinaBean> listarTablaOficina(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaOficina(idDdi, nomTabla);
	}
	
	@Override
	public List<MaePaisBean> listarTablaPais(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaPais(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeParametroBean> listarTablaParametro(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaParametro(idDdi, nomTabla);
	}
	
	@Override
	public List<MaePersonalBean> listarTablaPersonal(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaPersonal(idDdi, nomTabla);
	}
	
	@Override
	public List<MaePersonalExternoBean> listarTablaPersonalExterno(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaPersonalExterno(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeRegionBean> listarTablaRegion(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaRegion(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeTipCamionBean> listarTablaTipCamion(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaTipCamion(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeTipControlCalidadBean> listarTablaTipControlCalidad(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaTipControlCalidad(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeTipDocumentoBean> listarTablaTipDocumento(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaTipDocumento(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeTipMovimientoBean> listarTablaTipMovimiento(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaTipMovimiento(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeUnidadMedidaBean> listarTablaUnidadMedida(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaUnidadMedida(idDdi, nomTabla);
	}
	
	@Override
	public List<MaeDdiBean> listarTablaDdi(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaDdi(idDdi, nomTabla);
	}
	
	@Override
	public MaeAniosBean insertarRegistroAnio(MaeAniosBean maeAniosBean) throws Exception {
		return administracionDao.insertarRegistroAnio(maeAniosBean);
	}
	
	@Override
	public List<ItemBean> obtenerCodigoDdi() throws Exception {
		return administracionDao.obtenerCodigoDdi();
	}
	
	@Override
	public MaeDdiBean insertarRegistroDdi(MaeDdiBean maeDdiBean, String control) throws Exception {
		return administracionDao.insertarRegistroDdi(maeDdiBean, control);
	}
	
	@Override
	public MaeDonanteBean insertarRegistroDonante(MaeDonanteBean maeDonanteBean, String control) throws Exception {
		return administracionDao.insertarRegistroDonante(maeDonanteBean, control);
	}
	
	@Override
	public MaeEmpresaTransporteBean insertarRegistroEmpresaTransportes(MaeEmpresaTransporteBean maeEmpresaTransporteBean, String control) throws Exception {
		return administracionDao.insertarRegistroEmpresaTransportes(maeEmpresaTransporteBean, control);
	}
	
	@Override
	public MaeEnvaseBean insertarRegistroEnvase(MaeEnvaseBean maeEnvaseBean, String control) throws Exception {
		return administracionDao.insertarRegistroEnvase(maeEnvaseBean, control);
	}
	
	@Override
	public MaeModAlmacenBean insertarRegistroModAlmacen(MaeModAlmacenBean maeModAlmacenBean, String control) throws Exception {
		return administracionDao.insertarRegistroModAlmacen(maeModAlmacenBean, control);
	}
	
	@Override
	public MaeOficinaBean insertarRegistroOficina(MaeOficinaBean maeOficinaBean, String control) throws Exception {
		return administracionDao.insertarRegistroOficina(maeOficinaBean, control);
	}
	
	@Override
	public MaePersonalBean insertarRegistroPersonal(MaePersonalBean maePersonalBean, String control) throws Exception {
		return administracionDao.insertarRegistroPersonal(maePersonalBean, control);
	}
	
	@Override
	public MaePersonalExternoBean insertarRegistroPersonalExterno(MaePersonalExternoBean maePersonalExternoBean, String control) throws Exception {
		return administracionDao.insertarRegistroPersonalExterno(maePersonalExternoBean, control);
	}
	
	@Override
	public List<ItemBean> obtenerRegionXDdi(Integer idDdi) throws Exception {
		return administracionDao.obtenerRegionXDdi(idDdi);
	}
	
	@Override
	public MaeTipCamionBean insertarRegistroTipoCamion(MaeTipCamionBean maeTipCamionBean, String control) throws Exception {
		return administracionDao.insertarRegistroTipoCamion(maeTipCamionBean, control);
	}
	
	@Override
	public MaeTipControlCalidadBean insertarRegistroControlCalidad(MaeTipControlCalidadBean maeTipControlCalidadBean, String control) throws Exception {
		return administracionDao.insertarRegistroControlCalidad(maeTipControlCalidadBean, control);
	}
	
	@Override
	public MaeTipDocumentoBean insertarRegistroDocumentos(MaeTipDocumentoBean maeTipDocumentoBean, String control) throws Exception {
		return administracionDao.insertarRegistroDocumentos(maeTipDocumentoBean, control);
	}
	
	@Override
	public MaeUnidadMedidaBean insertarRegistroUnidadMedida(MaeUnidadMedidaBean maeUnidadMedidaBean, String control) throws Exception {
		return administracionDao.insertarRegistroUnidadMedida(maeUnidadMedidaBean, control);
	}
	
	@Override
	public List<ItemBean> listarTablas() throws Exception {
		return administracionDao.listarTablas();
	}
	
	@Override
	public List<ItemBean> listarTablasDdi() throws Exception {
		return administracionDao.listarTablasDdi();
	}
	
	@Override
	public List<UsuarioBean> listarUsuarios(UsuarioBean user) throws Exception {
		return administracionDao.listarUsuarios(user);
	}
	
	@Override
	public UsuarioBean obtenerUsuarioXId(Integer idUsuario) throws Exception {
		return administracionDao.obtenerUsuarioXId(idUsuario);
	}
	
	@Override
	public List<AlmacenBean> ListaAlmacenesXUsuario(Integer idUsuario) throws Exception {
		return administracionDao.ListaAlmacenesXUsuario(idUsuario);
	}
	
	@Override
	public UsuarioBean insertarUsuario(UsuarioBean user) throws Exception {
		return administracionDao.insertarUsuario(user);
	}
	
	@Override
	public UsuarioBean actualizarUsuario(UsuarioBean user) throws Exception {
		return administracionDao.actualizarUsuario(user);
	}
	
	@Override
	public List<RolBean> listarRoles(Integer idUsuario) throws Exception {
		return administracionDao.listarRoles(idUsuario);
	}
	
	@Override
	public List<AlmacenBean> ListaAlmacenesXUsuarioDdi(AlmacenBean almacenBean) throws Exception {
		return administracionDao.ListaAlmacenesXUsuarioDdi(almacenBean);
	}
	
	@Override
	public AlmacenBean insertarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception {
		return administracionDao.insertarAlmacenXUsuario(almacenBean);
	}
	
	@Override
	public AlmacenBean eliminarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception {
		return administracionDao.eliminarAlmacenXUsuario(almacenBean);
	}
	
	@Override
	public RolBean insertarRolXUsuario(RolBean rolBean) throws Exception {
		return administracionDao.insertarRolXUsuario(rolBean);
	}
	
	@Override
	public RolBean eliminarRolXUsuario(RolBean rolBean) throws Exception {
		return administracionDao.eliminarRolXUsuario(rolBean);
	}
	
	@Override
	public List<RolMenuBean> mostrarRoles(Integer idRol) throws Exception {
		return administracionDao.mostrarRoles(idRol);
	}
	
	@Override
	public List<RolMenuBean> ListaMenuRoles(Integer idRol) throws Exception {
		return administracionDao.ListaMenuRoles(idRol);
	}
	
	@Override
	public List<RolMenuBean> ListaMenuNuevo() throws Exception {
		return administracionDao.ListaMenuNuevo();
	}
	
	@Override
	public RolBean insertarRol(RolBean rolBean) throws Exception {
		return administracionDao.insertarRol(rolBean);
	}
	
	@Override
	public RolBean actualizarRol(RolBean rolBean) throws Exception {
		return administracionDao.actualizarRol(rolBean);
	}
	
	@Override
	public RolMenuBean InsertarActualizarMenuRol(RolMenuBean rolBean) throws Exception {
		return administracionDao.InsertarActualizarMenuRol(rolBean);
	}
	
	@Override
	public List<CatalogoProductoBean> listarCatalogoProductosXCat(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.listarCatalogoProductosXCat(catalogoProductoBean);
	}
	
	@Override
	public List<CatalogoProductoBean> listarCatalogoProductosXNom(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.listarCatalogoProductosXNom(catalogoProductoBean);
	}
	
	@Override
	public CatalogoProductoBean obtenerCatalogoProductoXId(Integer idProducto) throws Exception {
		return administracionDao.obtenerCatalogoProductoXId(idProducto);
	}
	
	@Override
	public CatalogoProductoBean insertarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.insertarCatalogoProducto(catalogoProductoBean);
	}
	
	@Override
	public CatalogoProductoBean actualizarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.actualizarCatalogoProducto(catalogoProductoBean);
	}
	
	@Override
	public CatalogoProductoBean generaCodigoProducto() throws Exception {
		return administracionDao.generaCodigoProducto();
	}
	
	@Override
	public List<ItemBean> listarGrupoProducto() throws Exception {
		return administracionDao.listarGrupoProducto();
	}
	
	@Override
	public List<ItemBean> listarClaseProducto(ItemBean itemBean) throws Exception {
		return administracionDao.listarClaseProducto(itemBean);
	}
	
	@Override
	public List<ItemBean> listarFamiliaProducto(ItemBean itemBean) throws Exception {
		return administracionDao.listarFamiliaProducto(itemBean);
	}
	
	@Override
	public List<CatalogoProductoBean> listarSigaXNombre(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.listarSigaXNombre(catalogoProductoBean);
	}
	
	@Override
	public List<CatalogoProductoBean> listarSigaXGrupo(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.listarSigaXGrupo(catalogoProductoBean);
	}
	
	@Override
	public CatalogoProductoBean insertarProductoSiga(CatalogoProductoBean catalogoProductoBean) throws Exception {
		return administracionDao.insertarProductoSiga(catalogoProductoBean);
	}
	
	@Override
	public MaeAlmacenBean obtenerCodigoAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		return administracionDao.obtenerCodigoAlmacen(maeAlmacenBean);
	}
	
	@Override
	public MaeAlmacenBean actualizarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception {
		return administracionDao.actualizarRegistroAlmacen(maeAlmacenBean);
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosDonacion(Integer idUsuario) throws Exception {
		return administracionDao.listarEstadosDonacion(idUsuario);
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosProgramacion(Integer idUsuario) throws Exception {
		return administracionDao.listarEstadosProgramacion(idUsuario);
	}
	
	@Override
	public List<EstadosXUsuarioBean> listarEstadosInventario(Integer idUsuario) throws Exception {
		return administracionDao.listarEstadosInventario(idUsuario);
	}
	
	@Override
	public EstadosXUsuarioBean insertarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.insertarDonacionXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public EstadosXUsuarioBean eliminarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.eliminarDonacionXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public EstadosXUsuarioBean insertarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.insertarProgramacionXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public EstadosXUsuarioBean eliminarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.eliminarProgramacionXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public EstadosXUsuarioBean insertarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.insertarInventarioXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public EstadosXUsuarioBean eliminarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception {
		return administracionDao.eliminarInventarioXUsuario(estadosXUsuarioBean);
	}
	
	@Override
	public CierreStockBean mesTrabajoActivo(Integer idAlmacen, String tipo) throws Exception {
		return administracionDao.mesTrabajoActivo(idAlmacen, tipo);
	}
	
	@Override
	public MaeChoferBean insertarRegistroChofer(MaeChoferBean maeChoferBean, String control) throws Exception {
		return administracionDao.insertarRegistroChofer(maeChoferBean, control);
	}
	
	@Override
	public List<CorreoBean> listarCorreos(CorreoBean correoBean) throws Exception {
		return administracionDao.listarCorreos(correoBean);
	}
	
	@Override
	public List<ItemBean> listarEstadosModulo(ItemBean itemBean) throws Exception {
		return administracionDao.listarEstadosModulo(itemBean);
	}
	
	@Override
	public CorreoBean insertarDestinatario(CorreoBean correoBean) throws Exception {
		return administracionDao.insertarDestinatario(correoBean);
	}
	
	@Override
	public List<ItemBean> listarHijosMenu(ItemBean itemBean) throws Exception {
		return administracionDao.listarHijosMenu(itemBean);
	}
	
	@Override
	public List<ItemBean> listarPadresMenu(ItemBean itemBean) throws Exception {
		return administracionDao.listarPadresMenu(itemBean);
	}
	
	@Override
	public List<RolMenuBean> mostrarMenuUsuario(Integer idUsuario) throws Exception {
		return administracionDao.mostrarMenuUsuario(idUsuario);
	}
	
	@Override
	public List<MaeDistritoIneiBean> listarTablaPoblacionInei(ItemBean itemBean) throws Exception {
		return administracionDao.listarTablaPoblacionInei(itemBean);
	}
	
	@Override
	public List<UsuarioBean> listarUsuariosCorreo() throws Exception {
		return administracionDao.listarUsuariosCorreo();
	}
	
	@Override
	public MaeCorrelativoBean insertarActualizarCorrelativo(MaeCorrelativoBean maeCorrelativoBean, String control) throws Exception {
		return administracionDao.insertarActualizarCorrelativo(maeCorrelativoBean,control);
	}
	
	@Override
	public List<MaeCorrelativoBean> listarCorrelativo(Integer idAlmacen) throws Exception {
		return administracionDao.listarCorrelativo(idAlmacen);
	}
	
	@Override
	public ItemBean valoresIniciales(Integer idAlmacen, String usua) throws Exception {
		return administracionDao.valoresIniciales(idAlmacen, usua);
	}
	
	@Override
	public List<MaeMarcaBean> listarTablaMarca(Integer idDdi, String nomTabla) throws Exception {
		return administracionDao.listarTablaMarca(idDdi, nomTabla);
	}
	
	@Override
	public MaeMarcaBean insertarRegistroMarca(MaeMarcaBean maeMarcaBean, String control) throws Exception {
		return administracionDao.insertarRegistroMarca(maeMarcaBean, control);
	}
	
	@Override
	public MaeCorrelativoBean validaVigencia(Integer idAlmacen) throws Exception {
		return administracionDao.validaVigencia(idAlmacen);
	}
}
