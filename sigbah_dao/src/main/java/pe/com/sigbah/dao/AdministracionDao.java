package pe.com.sigbah.dao;

import java.util.List;

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

/**
 * @className: AdministracionDao.java
 * @description: Clase que contiene el consumo de los procedimientos del package BAH_PKG_ADMINISTRACION.
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public interface AdministracionDao {
	
	/**
	 * @param usuarioBean
	 * @return datos del usuario.
	 * @throws Exception
	 */
	public abstract DetalleUsuarioBean obtenerDatosUsuario(UsuarioBean usuarioBean) throws Exception;
	
	/**
	 * @param idUsuario
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<ModuloBean> listarModuloUsuario(Integer idUsuario) throws Exception;
	
	
	/**
	 * @param estadoUsuarioBean
	 * @return Lista de registros.
	 * @throws Exception
	 */
	public abstract List<EstadoUsuarioBean> listarEstadoUsuario(EstadoUsuarioBean estadoUsuarioBean) throws Exception;

	/**
	 * @param idAlmacen
	 * @return
	 * @throws Exception
	 */
	public abstract CierreStockBean obtenerMesTrabajo(Integer idAlmacen) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTablasGenerales() throws Exception;

	/**
	 * @param idTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeAlmacenBean> listarTablaAlmacen(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeAlmacenExternoBean> listarTablaAlmacenExterno(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param maeAlmacenExternoBean
	 * @return
	 * @throws Exception 
	 */
	public abstract MaeAlmacenExternoBean obtenerCodigoAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean) throws Exception;

	/**
	 * @param maeAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract MaeAlmacenBean insertarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception;

	/**
	 * @param maeAlmacenExternoBean
	 * @return
	 * @throws Exception
	 */
	public abstract MaeAlmacenExternoBean insertarRegistroAlmacenExterno(MaeAlmacenExternoBean maeAlmacenExternoBean, String control) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeAniosBean> listarTablaAnios(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeCategoriaBean> listarTablaCategoria(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeChoferBean> listarTablaChofer(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeDistritoIneiBean> listarTablaDistritoInei(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeDonanteBean> listarTablaDonante(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeEmpresaTransporteBean> listarTablaEmpresaTransporte(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeEnvaseBean> listarTablaEnvase(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeEstadoBean> listarTablaEstado(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeEstadoDonacionBean> listarTablaEstadoDonacion(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeEstadoProgramacionBean> listarTablaEstadoProgramacion(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeMedioTransporteBean> listarTablaMedioTransporte(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeModAlmacenBean> listarTablaModAlmacen(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeMonedaBean> listarTablaMoneda(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeMotivoTrasladoBean> listarTablaMotivoTraslado(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeOficinaBean> listarTablaOficina(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaePaisBean> listarTablaPais(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeParametroBean> listarTablaParametro(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaePersonalBean> listarTablaPersonal(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaePersonalExternoBean> listarTablaPersonalExterno(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeRegionBean> listarTablaRegion(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeTipCamionBean> listarTablaTipCamion(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeTipControlCalidadBean> listarTablaTipControlCalidad(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeTipDocumentoBean> listarTablaTipDocumento(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeTipMovimientoBean> listarTablaTipMovimiento(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeUnidadMedidaBean> listarTablaUnidadMedida(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeDdiBean> listarTablaDdi(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param maeAniosBean
	 * @return
	 * @throws Exception
	 */
	public abstract MaeAniosBean insertarRegistroAnio(MaeAniosBean maeAniosBean) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> obtenerCodigoDdi() throws Exception;

	/**
	 * @param maeDdiBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeDdiBean insertarRegistroDdi(MaeDdiBean maeDdiBean, String control) throws Exception;

	/**
	 * @param maeDonanteBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeDonanteBean insertarRegistroDonante(MaeDonanteBean maeDonanteBean, String control) throws Exception;

	/**
	 * @param maeEmpresaTransporteBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeEmpresaTransporteBean insertarRegistroEmpresaTransportes(MaeEmpresaTransporteBean maeEmpresaTransporteBean,
			String control) throws Exception;

	/**
	 * @param maeEnvaseBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeEnvaseBean insertarRegistroEnvase(MaeEnvaseBean maeEnvaseBean, String control) throws Exception;

	/**
	 * @param maeModAlmacenBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeModAlmacenBean insertarRegistroModAlmacen(MaeModAlmacenBean maeModAlmacenBean, String control) throws Exception;

	/**
	 * @param maeOficinaBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeOficinaBean insertarRegistroOficina(MaeOficinaBean maeOficinaBean, String control) throws Exception;

	/**
	 * @param maePersonalBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaePersonalBean insertarRegistroPersonal(MaePersonalBean maePersonalBean, String control) throws Exception;

	/**
	 * @param maePersonalExternoBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaePersonalExternoBean insertarRegistroPersonalExterno(MaePersonalExternoBean maePersonalExternoBean,
			String control) throws Exception;

	/**
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> obtenerRegionXDdi(Integer idDdi) throws Exception;

	/**
	 * @param maeTipCamionBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeTipCamionBean insertarRegistroTipoCamion(MaeTipCamionBean maeTipCamionBean, String control) throws Exception;

	/**
	 * @param maeTipControlCalidadBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeTipControlCalidadBean insertarRegistroControlCalidad(MaeTipControlCalidadBean maeTipControlCalidadBean,
			String control) throws Exception;

	/**
	 * @param maeTipDocumentoBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeTipDocumentoBean insertarRegistroDocumentos(MaeTipDocumentoBean maeTipDocumentoBean, String control)
			throws Exception;

	/**
	 * @param maeUnidadMedidaBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeUnidadMedidaBean insertarRegistroUnidadMedida(MaeUnidadMedidaBean maeUnidadMedidaBean, String control)
			throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTablas() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarTablasDdi() throws Exception;

	/**
	 * @param idDdi
	 * @return
	 * @throws Exception
	 */
	public abstract List<UsuarioBean> listarUsuarios(UsuarioBean user) throws Exception;

	/**
	 * @param idusuario
	 * @return
	 * @throws Exception
	 */
	public abstract UsuarioBean obtenerUsuarioXId(Integer idusuario) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<AlmacenBean> ListaAlmacenesXUsuario(Integer idUsuario) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public abstract UsuarioBean insertarUsuario(UsuarioBean user) throws Exception;

	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public abstract UsuarioBean actualizarUsuario(UsuarioBean user) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<RolBean> listarRoles(Integer idUsuario) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<AlmacenBean> ListaAlmacenesXUsuarioDdi(AlmacenBean almacenBean) throws Exception;

	/**
	 * @param almacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract AlmacenBean insertarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception;

	/**
	 * @param almacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract AlmacenBean eliminarAlmacenXUsuario(AlmacenBean almacenBean) throws Exception;

	/**
	 * @param rolBean
	 * @return
	 * @throws Exception
	 */
	public abstract RolBean insertarRolXUsuario(RolBean rolBean) throws Exception;

	/**
	 * @param rolBean
	 * @return
	 * @throws Exception
	 */
	public abstract RolBean eliminarRolXUsuario(RolBean rolBean) throws Exception;

	/**
	 * @param idRol
	 * @return
	 * @throws Exception
	 */
	public abstract List<RolMenuBean> mostrarRoles(Integer idRol) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<RolMenuBean> ListaMenuRoles(Integer idRol) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<RolMenuBean> ListaMenuNuevo() throws Exception;

	/**
	 * @param rolBean
	 * @return
	 * @throws Exception
	 */
	public abstract RolBean insertarRol(RolBean rolBean) throws Exception;

	/**
	 * @param rolBean
	 * @return
	 * @throws Exception
	 */
	public abstract RolBean actualizarRol(RolBean rolBean) throws Exception;

	/**
	 * @param rolBean
	 * @return
	 * @throws Exception
	 */
	public abstract RolMenuBean InsertarActualizarMenuRol(RolMenuBean rolBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CatalogoProductoBean> listarCatalogoProductosXCat(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CatalogoProductoBean> listarCatalogoProductosXNom(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract CatalogoProductoBean obtenerCatalogoProductoXId(Integer idProducto) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract CatalogoProductoBean insertarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract CatalogoProductoBean actualizarCatalogoProducto(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract CatalogoProductoBean generaCodigoProducto() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarGrupoProducto() throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarClaseProducto(ItemBean itemBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarFamiliaProducto(ItemBean itemBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CatalogoProductoBean> listarSigaXNombre(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CatalogoProductoBean> listarSigaXGrupo(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param catalogoProductoBean
	 * @return
	 * @throws Exception
	 */
	public abstract CatalogoProductoBean insertarProductoSiga(CatalogoProductoBean catalogoProductoBean) throws Exception;

	/**
	 * @param maeAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract MaeAlmacenBean obtenerCodigoAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception;

	/**
	 * @param maeAlmacenBean
	 * @return
	 * @throws Exception
	 */
	public abstract MaeAlmacenBean actualizarRegistroAlmacen(MaeAlmacenBean maeAlmacenBean) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<EstadosXUsuarioBean> listarEstadosDonacion(Integer idUsuario) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<EstadosXUsuarioBean> listarEstadosProgramacion(Integer idUsuario) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<EstadosXUsuarioBean> listarEstadosInventario(Integer idUsuario) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean insertarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean eliminarDonacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean insertarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean eliminarProgramacionXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean insertarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param estadosXUsuarioBean
	 * @return
	 * @throws Exception
	 */
	public abstract EstadosXUsuarioBean eliminarInventarioXUsuario(EstadosXUsuarioBean estadosXUsuarioBean) throws Exception;

	/**
	 * @param idAlmacen
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public abstract CierreStockBean mesTrabajoActivo(Integer idAlmacen, String tipo) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeAlmacenExternoBean> listarTablaAlmacenExterno1(ItemBean itemBean) throws Exception;

	/**
	 * @param maeChoferBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeChoferBean insertarRegistroChofer(MaeChoferBean maeChoferBean, String control) throws Exception;

	/**
	 * @param correoBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<CorreoBean> listarCorreos(CorreoBean correoBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarEstadosModulo(ItemBean itemBean) throws Exception;

	/**
	 * @param correoBean
	 * @return
	 * @throws Exception
	 */
	public abstract CorreoBean insertarDestinatario(CorreoBean correoBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarHijosMenu(ItemBean itemBean) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<ItemBean> listarPadresMenu(ItemBean itemBean) throws Exception;

	/**
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public abstract List<RolMenuBean> mostrarMenuUsuario(Integer idUsuario) throws Exception;

	/**
	 * @param itemBean
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeDistritoIneiBean> listarTablaPoblacionInei(ItemBean itemBean) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public abstract List<UsuarioBean> listarUsuariosCorreo() throws Exception;

	/**
	 * @param maeCorrelativoBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeCorrelativoBean insertarActualizarCorrelativo(MaeCorrelativoBean maeCorrelativoBean, String control)
			throws Exception;

	/**
	 * @param idAlmacen
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeCorrelativoBean> listarCorrelativo(Integer idAlmacen) throws Exception;

	/**
	 * @param idAlmacen
	 * @param usua
	 * @return
	 * @throws Exception
	 */
	public abstract ItemBean valoresIniciales(Integer idAlmacen, String usua) throws Exception;

	/**
	 * @param idDdi
	 * @param nomTabla
	 * @return
	 * @throws Exception
	 */
	public abstract List<MaeMarcaBean> listarTablaMarca(Integer idDdi, String nomTabla) throws Exception;

	/**
	 * @param maeMarcaBean
	 * @param control
	 * @return
	 * @throws Exception
	 */
	public abstract MaeMarcaBean insertarRegistroMarca(MaeMarcaBean maeMarcaBean, String control) throws Exception;

	/**
	 * @param idAlmacen
	 * @return
	 * @throws Exception
	 */
	public abstract MaeCorrelativoBean validaVigencia(Integer idAlmacen) throws Exception;



}
