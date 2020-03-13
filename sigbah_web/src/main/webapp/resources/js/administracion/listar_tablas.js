var listaAlmacen = new Object();
var listaAlmacenExterno = new Object();
var listaAnio = new Object();
var listaCategoria = new Object();
var listaChofer = new Object();
var listaDdi = new Object();
var listaDistritoInei = new Object();
var listaDonante = new Object();
var listaEmpresaTransporte = new Object();
var listaEnvase = new Object();
var listaEstado = new Object();
var listaEstadoDonacion = new Object();
var listaEstadoProgramacion = new Object();
var listaMedioTransporte = new Object();
var listaModAlmacen = new Object();
var listaMoneda = new Object();
var listaMotivoTraslado = new Object();
var listaOficina = new Object();
var listaPais = new Object();
var listaParametro = new Object();
var listaPersonal = new Object();
var listaPersonalExterno = new Object();
var listaRegion = new Object();
var listaTipCamion = new Object();
var listaTipControlCalidad = new Object();
var listaTipDocumento = new Object();
var listaTipMovimiento = new Object();
var listaUnidadMedida = new Object();
var listaCatalogo = new Object();

var tbl_almacen = $('#tbl_almacen');
var tbl_almacen_externo = $('#tbl_almacen_externo');
var tbl_anio = $('#tbl_anio');
var tbl_categoria = $('#tbl_categoria');
var tbl_chofer = $('#tbl_chofer');
var tbl_ddi = $('#tbl_ddi');
var tbl_distrito_inei = $('#tbl_distrito_inei');
var tbl_donante = $('#tbl_donante');
var tbl_empresa_transporte= $('#tbl_empresa_transporte');
var tbl_envase= $('#tbl_envase');
var tbl_estado= $('#tbl_estado');
var tbl_estado_donacion= $('#tbl_estado_donacion');
var tbl_estado_programacion= $('#tbl_estado_programacion');
var tbl_medio_transporte= $('#tbl_medio_transporte');
var tbl_mod_almacen= $('#tbl_mod_almacen');
var tbl_moneda= $('#tbl_moneda');
var tbl_motivo_traslado= $('#tbl_motivo_traslado');
var tbl_oficina= $('#tbl_oficina');
var tbl_pais= $('#tbl_pais');
var tbl_parametro= $('#tbl_parametro');
var tbl_personal= $('#tbl_personal');
var tbl_personal_externo= $('#tbl_personal_externo');
var tbl_region= $('#tbl_region');
var tbl_tip_camion= $('#tbl_tip_camion');
var tbl_tip_control_calidad= $('#tbl_tip_control_calidad');
var tbl_tip_documento= $('#tbl_tip_documento');
var tbl_tip_movimiento= $('#tbl_tip_movimiento');
var tbl_unidad_medida= $('#tbl_unidad_medida');
var tbl_catalogo_producto= $('#tbl_catalogo_producto');

var frm_almacen = $('#frm_almacen');
var frm_almacen_externo = $('#frm_almacen_externo');
var frm_anio = $('#frm_anio');
var frm_ddi = $('#frm_ddi');
var frm_donante = $('#frm_donante');
var frm_empresa_transporte = $('#frm_empresa_transporte');
var frm_envase = $('#frm_envase');
var frm_mod_almacen = $('#frm_mod_almacen');
var frm_oficina = $('#frm_oficina');
var frm_personal = $('#frm_personal');
var frm_personal_externo = $('#frm_personal_externo');
var frm_tipo_camion = $('#frm_tipo_camion');
var frm_tipo_control_calidad = $('#frm_tipo_control_calidad');
var frm_tipo_documento = $('#frm_tipo_documento');
var frm_unidad_medida = $('#frm_unidad_medida');

var nombreTabla = "";
var indicador = "";
var cod = "";
var tablaExcel ="";
var tablaEdit = "";
$(document).ready(function() {

	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
//		var bootstrapValidator = frm_gui_remision.data('bootstrapValidator');
//		bootstrapValidator.validate();
//		if (bootstrapValidator.isValid()) {
//
//			var params = { 
//				codigoAnio : $('#sel_anio').val(),
//				codigoMes : $('#sel_mes').val(),
//				idMovimiento : $('#sel_tip_movimiento').val()
//			};
//			
//			loadding(true);
//			
//			consultarAjax('GET', '/donaciones/guia-remision/listarGuiaRemision', params, function(respuesta) {
//				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//					addErrorMessage(null, respuesta.mensajeRespuesta);
//				} else {
//					listarGuiaRemision(respuesta);
//				}
//				loadding(false);
//			});
//			
//		}
		
	});
	
	inicializarDatos();


	
	
	
	$('#sel_tablas_generales').change(function() {
		limpiarTablas();
		$('#div_busqueda_catalogo').hide();
		$('#div_busqueda_alm_ext').hide();
		$('#titulo_tabla').html("Lista: "+$("#sel_tablas_generales option:selected").text());
		var codigo = $(this).val();
		
		if (!esnulo(codigo)) {
			var arr = codigo.split('*');
			tabla = arr[1];
			menu = arr[2];
		}
		if(tabla=="BAH_MAE_ALMACEN_EXTERNO"){
			$('input[name=rb_tie_ate_gobierno_bus]').prop('checked', false);
			$('#div_gore_destino_bus').hide();
			$('#div_ubi_destino_bus').hide();
			$('#div_busqueda_alm_ext').show();
			cod = codigo;
		}else if(tabla=="BAH_MAE_CATALOGO_PRODUCTO"){
			$('#div_busqueda_catalogo').show();
			cod = codigo;
		}else{
			$('#div_busqueda_catalogo').hide();
			$('#div_busqueda_alm_ext').hide();
			listarTablasInsertUpdate(codigo);
		}
		
	});
	
	
	$('#href_nuevo').click(function(e) {
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		if(indicador=="BAH_MAE_ALMACEN"){
			$('#h4_tit_almacen').html('Nuevo Almacén');
			
			$('#hid_id_almacen').val('');
			$('#hid_cod_almacen').val('');
			$('#txt_nom_alm').val('');
			$('#txt_dir_alm').val('');
			$('#txt_car_alm').val('');
			$('#txt_sec_alm').val('');
			$('#sel_ddi_alm').val('');
			$('#sel_ddi_alm').prop('disabled',false);
			$('#sel_mod_alm').val('');
			$('#sel_est_alm').val('1');
			$('#div_almacen').modal('show');
		}else if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
			$('#h4_tit_almacen_externo').html('Nuevo Almacén Externo');
			
			$('#hid_id_almacen_externo').val('');
			$('#hid_cod_almacen_externo').val('');
			$('#hid_nom_almacen_externo').val('');
			
			$('#txt_nom_almacen_ext').val('');
			$('#txt_direccion_ext').val('');
			$('#txt_ruc_ext').val('');
			$('#txt_nom_ubi_ext').val('');
			$('#txt_cod_ext').val('');
			
			$('input[name=rb_tie_ate_gobierno]').prop('checked', false);
			$('#sel_gore').val('');
			$('#sel_departamento').val('');
			$('#sel_provincia').val('');
			$('#sel_distrito').val('');
			$('#div_gore_destino').hide();
			$('#div_ubi_destino').hide();
			$('#txt_codigo_ext').val('');
			
			$('#div_almacen_externo').modal('show');
		}else if(indicador=="BAH_MAE_ANIO"){
			$('#h4_tit_anio').html('Nuevo Año');
			$('#hid_id_anio').val('');
			$('#txt_cod_anio').val('');
			$('#txt_des_anio').val('');
			
			$('#txt_cod_anio').prop('disabled', false);
			$('#div_anio').modal('show');
		}else if(indicador=="BAH_MAE_DDI"){
			$('#h4_tit_ddi').html('Nuevo DDI');
			$('#hid_id_ddi').val('');
			$('#txt_cod_ddi').val('');
			$('#txt_nom_ddi').val('');
			
			$('#txt_cod_ddi').prop('disabled', true);
			$('#div_ddi').modal('show');
		}else if(indicador=="BAH_MAE_DONANTE"){
			$('#h4_tit_donante').html('Nuevo Donante');
			$('#hid_id_donante').val('');
			$('#txt_nom_donante').val('');
			$('#txt_doc_donante').val('');
			$('#sel_tip_donante').val('');
			$('#txt_rep_donante').val('');
			$('#div_donante').modal('show');
		}else if(indicador=="BAH_MAE_EMP_TRANSPORTE"){
			$('#h4_tit_empresa_transporte').html('Nueva Empresa Transporte');
			$('#hid_id_empresa_transporte').val('');
			$('#txt_nom_emp').val('');
			$('#txt_dir_emp').val('');
			$('#txt_rep_emp').val('');
			$('#txt_ruc_emp').val('');
			$('#txt_tel_emp').val('');
			$('#sel_med_emp').val('');
			$('#div_empresa_transporte').modal('show');
		}else if(indicador=="BAH_MAE_ENVASE"){
			$('#h4_tit_envase').html('Nuevo Envase');
			$('#hid_id_envase').val('');
			$('#txt_nom_env').val('');
			$('#txt_des_env').val('');
			$('#div_envase').modal('show');
		}else if(indicador=="BAH_MAE_MOD_ALMACEN"){
			$('#h4_tit_mod_almacen').html('Nueva Modalidad de Almacén');
			$('#hid_id_mod_almacen').val('');
			$('#txt_nom_mod_alm').val('');
			$('#div_mod_almacen').modal('show');
		}else if(indicador=="BAH_MAE_OFICINA"){
			$('#h4_tit_oficina').html('Nueva Oficina');
			$('#hid_id_oficina').val('');
			$('#txt_nom_ofi').val('');
			$('#sel_ddi_ofi').val('');
			$('#div_oficina').modal('show');
		}else if(indicador=="BAH_MAE_PERSONAL"){
			$('#h4_tit_personal').html('Nuevo Personal');
			$('#hid_id_personal').val('');
			$('#txt_nom_per').val('');
			$('#txt_ape_per').val('');
			$('#txt_car_per').val('');
			$('#sel_ofi_per').val('');
			$('#txt_tel_per').val('');
			$('#sel_est_per').val('1');
			$('#div_personal').modal('show');
		}else if(indicador=="BAH_MAE_PERSONAL_EXTERNO"){
			$('#h4_tit_personal_externo').html('Nuevo Personal Externo');
			$('#hid_id_personal_externo').val('');
			$('#txt_nom_per_ext').val('');
			$('#txt_car_per_ext').val('');
			$('#txt_tel_per_ext').val('');
			$('input[name=rb_tie_ate_gobierno_per_ext]').prop('checked', false);
			$('#sel_gore_per_ext').val('');
			$('#sel_departamento_per_ext').val('');
			$('#sel_provincia_per_ext').val('');
			$('#sel_distrito_per_ext').val('');
			$('#div_gore_destino_per_ext').hide();
			$('#div_ubi_destino_per_ext').hide();
			$('#sel_est_per_ext').val('1');
			$('#div_personal_externo').modal('show');
		}else if(indicador=="BAH_MAE_TIP_CAMION"){
			$('#h4_tit_tipo_camion').html('Nuevo Tipo de Camión');
			$('#hid_id_tipo_camion').val('');
			$('#txt_des_tip_cam').val('');
			$('#txt_ton_tip_cam').val('');
			$('#txt_vol_tip_cam').val('');

			$('#div_tipo_camion').modal('show');
		}else if(indicador=="BAH_MAE_TIP_CONTROL_CALIDAD"){
			$('#h4_tit_tipo_control_calidad').html('Nuevo Tipo de Control de Calidad');
			$('#hid_id_tipo_control_calidad').val('');
			$('#txt_nom_tip_con').val('');
			
			$('#div_tipo_control_calidad').modal('show');
		}else if(indicador=="BAH_MAE_TIP_DOCUMENTO"){
			$('#h4_tit_tipo_documento').html('Nuevo Tipo de Documento');
			$('#hid_id_tipo_documento').val('');
			$('#txt_nom_tip_doc').val('');
			$('#txt_cor_tip_doc').val('');
			
			$('#div_tipo_documento').modal('show');
		}else if(indicador=="BAH_MAE_UNIDAD_MEDIDA"){
			$('#h4_tit_unidad_medida').html('Nueva Unidad de Medida');
			$('#hid_id_unidad_medida').val('');
			$('#txt_nom_uni_med').val('');
			$('#txt_cor_uni_med').val('');
			
			$('#div_unidad_medida').modal('show');
		}
		
		
	});
	
	$('#href_editar').click(function(e) {
		e.preventDefault();
		
		var indices = [];
		var suma=0;
		if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
	
			var checks = document.getElementsByName('ids[]');
			for (var i = 0, j = checks.length; i < j; i++) {
			    if(checks[i].checked == true){
			    		indices.push(i);
			    }
			}
		}else{
			nombreTabla.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (nombreTabla.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
					console.log("INDEX: "+index);
					indices.push(index);
					if(indices.length > 1){
						return false;
					}
				}
			});
		
		}
		
//		var ii;
//		var checks = document.getElementsByName('ids[]');
//		for (var i = 0, j = checks.length; i < j; i++) {
//		    if(checks[i].checked == true){
//		    suma++;
//		    	if(suma==1){
//		    		ii=i;
//		    		indices.push(ii);
//		    	}
//		    }
//		}
		
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
			if(indicador=="BAH_MAE_ALMACEN"){
				$('#h4_tit_almacen').html('Actualizar Almacén');
				//$('#frm_det_alimentarios').trigger('reset');
				$('#hid_id_almacen').val('');
				
				var obj = listaAlmacen[indices[0]];

				$('#hid_id_almacen').val(obj.idAlmacen);
				$('#hid_cod_almacen').val(obj.codAlmacen);
				$('#txt_nom_alm').val(obj.nombreAlmacen);
				$('#txt_dir_alm').val(obj.direccion);
				$('#txt_car_alm').val(obj.caracteristicas);
				$('#txt_sec_alm').val(obj.nombreSecundario);
				$('#sel_ddi_alm').val(obj.idDdi+"_"+obj.codDdi);
				$('#sel_mod_alm').val(obj.idModAlmacen);
				$('#sel_est_alm').val(obj.flgActivo);
				$('#div_almacen').modal('show');
			}else if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
				$('#h4_tit_almacen_externo').html('Actualizar Almacén Externo');

				var obj = listaAlmacenExterno[indices[0]];
				console.log("ID ALMACEN: "+obj.idAlmacenExterno);
				$('#hid_id_almacen_externo').val(obj.idAlmacenExterno);
				cargarTipoAtencion(obj.tipoAlmacen);
				$('#txt_nom_almacen_ext').val(obj.nombreAlmacen);
				$('#txt_direccion_ext').val(obj.direccion);
				$('#txt_ruc_ext').val(obj.rucUbigeo);
				$('#txt_nom_ubi_ext').val(obj.nombreUbigeo);
				$('#txt_cod_ext').val(obj.codAlmacen);
				
				$('input[name=rb_tie_ate_gobierno][value="'+obj.tipoAlmacen+'"]').prop('checked', true);
				if(obj.tipoAlmacen=='R'){
					$('#sel_gore').prop('disabled', false);
					$('#sel_gore').val(obj.codUbigeo);
					$('#sel_provincia').prop('disabled', true);
					$('#sel_distrito').prop('disabled', true);
					$('#sel_departamento').prop('disabled', true);
					$('#div_gore_destino').show();
					$('#div_ubi_destino').hide();
				}else if(obj.tipoAlmacen=='L'){
					$('#sel_gore').prop('disabled', true);
					$('#sel_provincia').prop('disabled', false);
					$('#sel_distrito').prop('disabled', false);
					$('#sel_departamento').prop('disabled', false);
					$('#sel_departamento').val(obj.codigoDepartamento);
					cargarProvincia(obj.codigoDepartamento, obj.codigoProvincia);					
					cargarDistrito(obj.codigoProvincia, obj.codUbigeo);
//					$('#sel_provincia_per_ext').val(obj.codProvincia);
//					$('#sel_distrito_per_ext').val(obj.codUbigeo);
					$('#div_gore_destino').hide();
					$('#div_ubi_destino').show();
				}
				
				$('#div_almacen_externo').modal('show');
			}else if(indicador=="BAH_MAE_ANIO"){
				$('#h4_tit_anio').html('Actualizar Año');
				var obj = listaAnio[indices[0]];
				$('#hid_id_anio').val(obj.codAnio);
				$('#txt_cod_anio').val(obj.codAnio);
				$('#txt_des_anio').val(obj.descripcion);
				
				$('#txt_cod_anio').prop('disabled', true);
				$('#div_anio').modal('show');
			}else if(indicador=="BAH_MAE_DDI"){
				$('#h4_tit_ddi').html('Actualizar DDI');
				var obj = listaDdi[indices[0]];
				$('#hid_id_ddi').val(obj.idDdi);
				$('#txt_cod_ddi').val(obj.codigoDdi);
				$('#txt_nom_ddi').val(obj.nombreDdi);
				
				$('#txt_cod_ddi').prop('disabled', true);
				$('#div_ddi').modal('show');
			}else if(indicador=="BAH_MAE_DONANTE"){
				$('#h4_tit_donante').html('Actualizar Donante');
				var obj = listaDonante[indices[0]];
				$('#hid_id_donante').val(obj.idDonante);
				$('#txt_nom_donante').val(obj.nombreDonante);
				$('#txt_doc_donante').val(obj.nroDocumento);
				if(obj.tipo=='Persona Natural'){
					$('#sel_tip_donante').val(1);
				}else if(obj.tipo=='Persona Jurídica'){
					$('#sel_tip_donante').val(2);
				}else{
					$('#sel_tip_donante').val('');
				}
				$('#txt_rep_donante').val(obj.representante);
				$('#div_donante').modal('show');
			}else if(indicador=="BAH_MAE_EMP_TRANSPORTE"){
				$('#h4_tit_empresa_transporte').html('Actualizar Empresa Transporte');
				var obj = listaEmpresaTransporte[indices[0]];
				$('#hid_id_empresa_transporte').val(obj.idEmpresaTransporte);
				$('#txt_nom_emp').val(obj.nombreEmpresa);
				$('#txt_dir_emp').val(obj.direccion);
				$('#txt_rep_emp').val(obj.representante);
				$('#txt_ruc_emp').val(obj.ruc);
				$('#txt_tel_emp').val(obj.telefono);
				$('#sel_med_emp').val(obj.idMedioTransporte);
				$('#div_empresa_transporte').modal('show');
			}else if(indicador=="BAH_MAE_ENVASE"){
				$('#h4_tit_envase').html('Actualizar Envase');
				var obj = listaEnvase[indices[0]];
				$('#hid_id_envase').val(obj.idEnvase);
				$('#txt_nom_env').val(obj.nombreEnvase);
				$('#txt_des_env').val(obj.descripcion);
				$('#div_envase').modal('show');
			}else if(indicador=="BAH_MAE_MOD_ALMACEN"){
				$('#h4_tit_mod_almacen').html('Actualizar Modalidad de Almacén');
				var obj = listaModAlmacen[indices[0]];
				$('#hid_id_mod_almacen').val(obj.idModAlmacen);
				$('#txt_nom_mod_alm').val(obj.nombreModalidad);
				$('#div_mod_almacen').modal('show');
			}else if(indicador=="BAH_MAE_OFICINA"){
				$('#h4_tit_oficina').html('Actualizar Oficina');
				var obj = listaOficina[indices[0]];
				$('#hid_id_oficina').val(obj.idOficina);
				$('#txt_nom_ofi').val(obj.nombreOficina);
				$('#sel_ddi_ofi').val(obj.idDdi);
				$('#div_oficina').modal('show');
			}else if(indicador=="BAH_MAE_PERSONAL"){
				$('#h4_tit_personal').html('Actualizar Personal');
				var obj = listaPersonal[indices[0]];
				console.log(obj.Oficina);
				$('#hid_id_personal').val(obj.idPersonal);
				$('#txt_nom_per').val(obj.nombres);
				$('#txt_ape_per').val(obj.apellidos);
				$('#txt_car_per').val(obj.cargo);
				$('#sel_ofi_per').val(obj.idOficina);
				$('#txt_tel_per').val(obj.telefono);
				$('#sel_est_per').val(obj.flagActivo);
				$('#div_personal').modal('show');
			}else if(indicador=="BAH_MAE_PERSONAL_EXTERNO"){
				$('#h4_tit_personal_externo').html('Actualizar Personal Externo');
				var obj = listaPersonalExterno[indices[0]];
				$('#hid_id_personal_externo').val(obj.idPersonalExterno);
				$('#txt_nom_per_ext').val(obj.nombres);
				$('#txt_car_per_ext').val(obj.cargo);
				$('#txt_tel_per_ext').val(obj.telefono);
				$('input[name=rb_tie_ate_gobierno_per_ext][value="'+obj.tipoPersonal+'"]').prop('checked', true);
				if(obj.tipoPersonal=='R'){
					$('#sel_gore_per_ext').prop('disabled', false);
					$('#sel_gore_per_ext').val(obj.codUbigeo);
					$('#sel_provincia_per_ext').prop('disabled', true);
					$('#sel_distrito_per_ext').prop('disabled', true);
					$('#sel_departamento_per_ext').prop('disabled', true);
					$('#div_gore_destino_per_ext').show();
					$('#div_ubi_destino_per_ext').hide();
				}else if(obj.tipoPersonal=='L'){
					$('#sel_gore_per_ext').prop('disabled', true);
					$('#sel_provincia_per_ext').prop('disabled', false);
					$('#sel_distrito_per_ext').prop('disabled', false);
					$('#sel_departamento_per_ext').prop('disabled', false);
					$('#sel_departamento_per_ext').val(obj.codDepartamento);
					cargarProvinciaPer(obj.codDepartamento, obj.codProvincia);					
					cargarDistritoPer(obj.codProvincia, obj.codUbigeo);
//					$('#sel_provincia_per_ext').val(obj.codProvincia);
//					$('#sel_distrito_per_ext').val(obj.codUbigeo);
					$('#div_gore_destino_per_ext').hide();
					$('#div_ubi_destino_per_ext').show();
				}
				$('#sel_est_per_ext').val(obj.estado);
				$('#div_personal_externo').modal('show');
			}else if(indicador=="BAH_MAE_TIP_CAMION"){
				$('#h4_tit_tipo_camion').html('Actualizar Tipo de Camión');
				var obj = listaTipCamion[indices[0]];
				$('#hid_id_tipo_camion').val(obj.idTipCamion);
				$('#txt_des_tip_cam').val(obj.descripcion);
				$('#txt_ton_tip_cam').val(obj.tonelaje);
				$('#txt_vol_tip_cam').val(obj.volumen);

				$('#div_tipo_camion').modal('show');
			}else if(indicador=="BAH_MAE_TIP_CONTROL_CALIDAD"){
				$('#h4_tit_tipo_control_calidad').html('Actualizar Tipo de Control de Calidad');
				var obj = listaTipControlCalidad[indices[0]];
				$('#hid_id_tipo_control_calidad').val(obj.idTipControl);
				$('#txt_nom_tip_con').val(obj.nombreTipControl);
				
				$('#div_tipo_control_calidad').modal('show');
			}else if(indicador=="BAH_MAE_TIP_DOCUMENTO"){
				$('#h4_tit_tipo_documento').html('Actualizar Tipo de Documento');
				var obj = listaTipControlCalidad[indices[0]];
				$('#hid_id_tipo_documento').val(obj.idTipDocumento);
				$('#txt_nom_tip_doc').val(obj.nombreDocumento);
				$('#txt_cor_tip_doc').val(obj.nombreCorto);
				
				$('#div_tipo_documento').modal('show');
			}else if(indicador=="BAH_MAE_UNIDAD_MEDIDA"){
				$('#h4_tit_unidad_medida').html('Actualizar Unidad de Medida');
				var obj = listaUnidadMedida[indices[0]];
				$('#hid_id_unidad_medida').val(obj.idUnidad);
				$('#txt_nom_uni_med').val(obj.nombreUnidad);
				$('#txt_cor_uni_med').val(obj.nombreCorto);
				
				$('#div_unidad_medida').modal('show');
			}
			
			
//			
//			
//			var obj = listaProductosCache[indices[0]];
//			
//			$('#h4_tit_productos').html('Actualizar Producto');
//			limpiarFormularioProducto();
//			
//			$('#hid_cod_producto').val(obj.idSalidaDet);
//			
//			$('#sel_cat_producto').val(obj.idCategoria);
//						
//			cargarProducto(obj.idCategoria, obj.idProducto+'_'+obj.unidadMedida+'_'+obj.pesoNetoUnitario+'_'+obj.pesoBrutoUnitario+'_'+obj.idDonacion+'_'+obj.precioUnitario);
//			
//			$('#txt_uni_medida').val(obj.unidadMedida);
//			//$('#txt_fec_vencimiento').val(obj.fecVencimiento);
//			$('#txt_cantidad').val(obj.cantidad);
//			$('#txt_precio').val(obj.precioUnitario);
//			$('#txt_imp_total').val(obj.importeTotal);
//			$('#txt_peso_unitario').val(obj.pesoNetoUnitario);
//			$('#txt_peso_bruto').val(obj.pesoBrutoUnitario);
//			
//			$('#div_det_productos').modal('show');
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		e.preventDefault();
//		var suma = 0;
//		var indices = [];
//		var codigo = '';
//		var tamanio = 0;
//		if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
//			tamanio=-9999;
//			var checks = document.getElementsByName('ids[]');
//			for (var i = 0, j = checks.length; i < j; i++) {
//			    if(checks[i].checked == true){
//			    suma++;
//			    }
//			}
//			$.each(listaAlmacenExterno, function(i, item){
//				if($('#chk_'+item.idAlmacenExterno).is(':checked')){
//					var idAlmacenExterno = item.idAlmacenExterno;
//					indices.push(idAlmacenExterno);
//					tamanio=indices.lenght;
//					if(!esnulo(codigo)){
//						return false;
//					}
//					
//					codigo = codigo + idAlmacenExterno+'_';
//				}
//			});
			
//			
//		}else{
//			
//			suma=-9999;
//			nombreTabla.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
//			if (nombreTabla.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
//				indices.push(index);
//				//tamanio=indices.lenght;
//				// Verificamos que tiene mas de un registro marcado y salimos del bucle
//				if (!esnulo(codigo)) {
//					return false;
//				}
//				
//				if(indicador=="BAH_MAE_ALMACEN"){
//					var idAlmacen = listaAlmacen[index].idAlmacen;
//					codigo = codigo + idAlmacen + '_';
//				}else if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
//					var idAlmacenExterno = listaAlmacenExterno[index].idAlmacenExterno;
//					codigo = codigo + idAlmacenExterno + '_';
//				}
//
//			}
//			});
//		}
//
//		
//		if (!esnulo(codigo)) {
//			codigo = codigo.substring(0, codigo.length - 1);
//		}
//		
//		
//		
//		if (indices.lenght == 0) {
//			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
//		} else if (indices.lenght > 1) {
//			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
//		} else {
//			console.log("ENTRO ");
//			if(indicador=="BAH_MAE_ALMACEN"){
//				$('#h4_tit_almacen').html('Actualizar Almacén');
//				//$('#frm_det_alimentarios').trigger('reset');
//				$('#hid_id_almacen').val('');
//				
//				var obj = listaAlmacen[indices[0]];
//				
//				$('#hid_id_almacen').val(obj.idAlmacen);
//				$('#txt_nom_almacen').val(obj.nombreAlmacen);
//				$('#txt_direccion').val(obj.direccion);
////				$('#txt_caracteristicas').val(obj.idAlmacen);
////				$('#txt_nombre_secundario').val(obj.idAlmacen);
//				
//				console.log("ID: "+obj.idAlmacen);
//				$('#div_almacen').modal('show');
//			}else if(indicador=="BAH_MAE_ALMACEN_EXTERNO"){
//				$('#h4_tit_almacen_externo').html('Actualizar Almacén Externo');
//				//$('#frm_det_alimentarios').trigger('reset');
//				$('#hid_id_almacen_externo').val('');
//				
				
//				 			$("#tbl_almacen_externo tbody tr").each(function (index) {
//					                 var campo1, campo2, campo3;
//					                $(this).children("td").each(function (index2) {
//					                    switch (index2) {
//					                        case 0:
//					                            campo1 = $(this).text();
//					                            break;
//					                       case 1:
//					                            campo2 = $(this).text();
//					                           break;
//					                        case 2:
//					                           campo3 = $(this).text();
//					                           break;
//					                    }
//					                $(this).css("background-color", "#ECF8E0");
//					                });
//					                alert(campo1 + ' - ' + campo2 + ' - ' + campo3);
//					            });
//					       
				
//				
//				
//				
//				var obj = listaAlmacenExterno[indices[0]];
//				
//				$('#hid_id_almacen_externo').val(obj.idAlmacenExterno);
//				cargarTipoAtencion(obj.tipoAlmacen);
//				$('#txt_nom_almacen_ext').val(obj.nombreAlmacen);
//				$('#txt_direccion_ext').val(obj.direccion);
//				$('#txt_ruc_ext').val(obj.nombreUbigeo);
//				$('#txt_codigo_ext').val(obj.codAlmacen);
//				
//				$('#div_almacen_externo').modal('show');
//			}
//			
//			
		}
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		if (tablaExcel=="") {
			addWarnMessage(null, 'No se encuentran registros para generar el reporte.');
			return;
		}
		
		var row = $('#'+tablaExcel+' > tbody > tr').length;
		var empty = null;
		$('tr.odd').each(function() {		
			empty = $(this).find('.dataTables_empty').text();
			return false;
		});					
		if (!esnulo(empty) || row < 1) {
			addWarnMessage(null, 'No se encuentran registros para generar el reporte.');
			return;
		}

		loadding(true);
		
		var tab_text="<table border='2px'>";
	    var textRange; var j=0;
	    tab = document.getElementById(tablaExcel); // id of table

	    for(j = 0 ; j < tab.rows.length ; j++) 
	    {     
	        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	    }

	    tab_text=tab_text+"</table>";
	    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
	    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
	    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE "); 

	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
	    {
	        txtArea1.document.open("txt/html","replace");
	        txtArea1.document.write(tab_text);
	        txtArea1.document.close();
	        txtArea1.focus(); 
	        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
	    }  
	    else                 //other browser not tested on IE 11
	        sa = window.open('data:application/vnd.ms-excel,' + escape(tab_text));  
	    loadding(false);
	    addInfoMessage(null, mensajeReporteExito);
	    return (sa);

	});
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_gui_remision.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_gui_remision.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idGuiaRemision = listaGuiaRemisionCache[index].idGuiaRemision;
				codigo = codigo + idGuiaRemision + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
			$('#hid_codigo').val(codigo);
			$('#chk_gui_remision').prop('checked', false);
			$('#chk_man_carga').prop('checked', false);
			$('#chk_act_ent_recepcion').prop('checked', false);
			$('#div_imp_pdf').modal('show');
		}
	});
	
	$('#btn_exportar').click(function(e) {
		e.preventDefault();
		var codigo = $('#hid_codigo').val();
		var ind_gui = $('#chk_gui_remision').is(':checked') ? '1' : '0';
		var ind_man = $('#chk_man_carga').is(':checked') ? '1' : '0';
		var ind_act = $('#chk_act_ent_recepcion').is(':checked') ? '1' : '0';
		var url = VAR_CONTEXT + '/donaciones/guia-remision/exportarPdf/';
		url = url + codigo + '/' + ind_gui + '/'+ ind_man + '/' + ind_act;
		console.log("RUtass: "+url);		
		if ((ind_gui == '0' && ind_man == '0' && ind_act == '0') || 
				(ind_gui == '1' && ind_man == '1' && ind_act == '1') || 
				(ind_gui == '1' && ind_man == '1' && ind_act == '0') || 
				(ind_gui == '1' && ind_man == '0' && ind_act == '1') || 
				(ind_gui == '0' && ind_man == '1' && ind_act == '1') ) {
			addWarnMessage(null, 'Debe de Seleccionar solo un tipo reporterrrr.');
			return;
		}
		loadding(true);
		$.fileDownload(url).done(function(respuesta) {
//			$('#div_imp_pdf').modal('hide');
			loadding(false);	
			if (respuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, mensajeReporteError);
			} else {
				addInfoMessage(null, mensajeReporteExito);
			}
		}).fail(function (respuesta) {
//			$('#div_imp_pdf').modal('hide');
			loadding(false);
			if (respuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, mensajeReporteError);
			} else if (respuesta == NOTIFICACION_VALIDACION) {
				addWarnMessage(null, mensajeReporteValidacion);
			}
		});

	});
	
	$('#btn_buscar_alm_ext').click(function(e) {
		buscarAlmacenExterno();
	});
	
	$('#btn_gra_alm').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_almacen.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			var sel_ddi=$('#sel_ddi_alm').val();
			var idddi;
			var codddi;
			var arr = sel_ddi.split('_');
			idddi = arr[0];
			codddi = arr[1];
	
			
			var params = { 
				idAlmacen : $('#hid_id_almacen').val(),
				idDdi: idddi,
				codDdi : codddi,
				nombreAlmacen : $('#txt_nom_alm').val(),
				codAlmacen : $('#hid_cod_almacen').val(),
				direccion : $('#txt_dir_alm').val(),
				idModAlmacen : $('#sel_mod_alm').val(),
				caracteristicas : $('#txt_car_alm').val(),
				flgActivo : $('#sel_est_alm').val(),
				nombreSecundario : $('#txt_sec_alm').val()
				
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarAlmacen', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_almacen').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_almacen.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_alm_ext').click(function(e) {
		e.preventDefault();
		console.log("ID "+$('#hid_id_almacen_externo').val());
		var bootstrapValidator = frm_almacen_externo.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			var flag=null;
			var codUbigeo=null;
			var nomUbigeo=null;
			if($('input[name=rb_tie_ate_gobierno]:checked').val()=='R'){
				flag='R';
				codUbigeo=$('#sel_gore').val();
				nomUbigeo = $("#sel_gore option:selected").text();
			}else{
				if($('input[name=rb_tie_ate_gobierno]:checked').val()=='L'){
					flag='L';
					codUbigeo=$('#sel_distrito').val();
					nomUbigeo = $("#sel_gore option:selected").text();
				}
			}
			var params = { 
				idAlmacenExterno :	$('#hid_id_almacen_externo').val(),
				codUbigeo : codUbigeo,
				nombreAlmacen : $('#txt_nom_almacen_ext').val(),
				codAlmacen : $('#txt_cod_ext').val(),
				tipoAlmacen : flag,
				direccion : $('#txt_direccion_ext').val(),
				rucUbigeo : $('#txt_ruc_ext').val(),  
				nombreUbigeo : nomUbigeo,
				flgActivo : $('#sel_est_alm_ext').val()
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarAlmacenExterno', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_almacen_externo').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						//listarTablasInsertUpdate($('#sel_tablas_generales').val());
						buscarAlmacenExterno();
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_almacen_externo.data('bootstrapValidator').resetForm();
				}
			});
		}
	});

	$('#btn_gra_anio').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_anio.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				codAnio : $('#txt_cod_anio').val(),
				descripcion: $('#txt_des_anio').val(),
				nombre : $('#txt_cod_anio').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarAnio', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_anio').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_anio.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_ddi').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_ddi.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idDdi :	$('#hid_id_ddi').val(),
				codigoDdi : $('#txt_cod_ddi').val(),
				nombreDdi: $('#txt_nom_ddi').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarDdi', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_ddi').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_ddi.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_donante').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_donante.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idDonante :	$('#hid_id_donante').val(),
				nombreDonante : $('#txt_nom_donante').val(),
				tipo: $('#sel_tip_donante').val(),
				nroDocumento : $('#txt_doc_donante').val(),
				representante : $('#txt_rep_donante').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarDonante', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_donante').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_donante.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_emp').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_empresa_transporte.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idEmpresaTransporte :	$('#hid_id_empresa_transporte').val(),
				nombreEmpresa : $('#txt_nom_emp').val(),
				direccion: $('#txt_dir_emp').val(),
				representante : $('#txt_rep_emp').val(),
				ruc : $('#txt_ruc_emp').val(),
				telefono : $('#txt_tel_emp').val(),
				idMedioTransporte : $('#sel_med_emp').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarEmpresaTransporte', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_empresa_transporte').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_empresa_transporte.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_env').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_envase.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idEnvase :	$('#hid_id_envase').val(),
				nombreEnvase : $('#txt_nom_env').val(),
				descripcion: $('#txt_des_env').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarEnvase', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_envase').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_envase.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_mod_alm').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_mod_almacen.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idModAlmacen :	$('#hid_id_mod_almacen').val(),
				nombreModalidad : $('#txt_nom_mod_alm').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarModAlmacen', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_mod_almacen').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_mod_almacen.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_ofi').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_oficina.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idOficina :	$('#hid_id_oficina').val(),
				nombreOficina : $('#txt_nom_ofi').val(),
				idDdi : $('#sel_ddi_ofi').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarOficina', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_oficina').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_oficina.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_per').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_personal.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idPersonal :	$('#hid_id_personal').val(),
				nombres : $('#txt_nom_per').val(),
				apellidos : $('#txt_ape_per').val(),
				cargo : $('#txt_car_per').val(),
				idOficina : $('#sel_ofi_per').val(),
				telefono : $('#txt_tel_per').val(),
				flagActivo : $('#sel_est_per').val()
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarPersonal', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_personal').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_personal.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_per_ext').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_personal_externo.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			var flag=null;
			var codUbigeo=null;
			if($('input[name=rb_tie_ate_gobierno_per_ext]:checked').val()=='R'){
				flag='R';
				codUbigeo=$('#sel_gore_per_ext').val();
			}else{
				if($('input[name=rb_tie_ate_gobierno_per_ext]:checked').val()=='L'){
					flag='L';
					codUbigeo=$('#sel_distrito_per_ext').val();
				}
			}
			var params = { 
				idPersonalExterno :	$('#hid_id_personal_externo').val(),
				nombres : $('#txt_nom_per_ext').val(),
				cargo : $('#txt_car_per_ext').val(),
				telefono : $('#txt_tel_per_ext').val(),
				flagActivo : $('#sel_est_per_ext').val(),
				codUbigeo : codUbigeo,
				tipoPersonal : flag
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarPersonalExterno', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_personal_externo').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_personal_externo.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_tip_cam').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_tipo_camion.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idTipCamion :	$('#hid_id_tipo_camion').val(),
				descripcion : $('#txt_des_tip_cam').val(),
				tonelaje : $('#txt_ton_tip_cam').val(),
				volumen : $('#txt_vol_tip_cam').val()
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarTipoCamion', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_tipo_camion').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_tipo_camion.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_tip_con').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_tipo_control_calidad.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idTipControl :	$('#hid_id_tipo_control_calidad').val(),
				nombreTipControl : $('#txt_nom_tip_con').val(),
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarTipoControlCalidad', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_tipo_control_calidad').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_tipo_control_calidad.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_tip_doc').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_tipo_documento.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idTipControl :	$('#hid_id_tipo_documento').val(),
				nombreDocumento : $('#txt_nom_tip_doc').val(),
				nombreCorto : $('#txt_cor_tip_doc').val()
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarTipoDocumento', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_tipo_documento').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_tipo_documento.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	$('#btn_gra_uni_med').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_unidad_medida.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			loadding(true);
			
			var params = { 
				idUnidad :	$('#hid_id_unidad_medida').val(),
				nombreUnidad : $('#txt_nom_uni_med').val(),
				nombreCorto : $('#txt_cor_uni_med').val(),
				flagActivi : 'A'
			};
			loadding(true);
			consultarAjax('POST', '/administracion/tablas-generales/grabarUnidadMedida', params, function(respuesta) {
				if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					$('#div_unidad_medida').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarTablasInsertUpdate($('#sel_tablas_generales').val());				
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_unidad_medida.data('bootstrapValidator').resetForm();
				}
			});
		}
	});
	
	
	
	/////almacenes externos
	$('input[type=radio][name=rb_tie_ate_gobierno_bus]').change(function() {
		cargarTipoAtencionBus(this.value);
    });
	
	$('input[type=radio][name=rb_tie_ate_gobierno]').change(function() {
		cargarTipoAtencion(this.value);
    });
	
	$('#sel_gore').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarCorrelativo(codigo);
		} else {
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
		}
	});
	
	$('#sel_departamento').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvincia(codigo, null);
		} else {
			$('#sel_provincia').html('');
		}
	});
	
	$('#sel_provincia').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarDistrito(codigo, null);
		} else {
			$('#sel_distrito').html('');
		}
	});
	
	$('#sel_distrito').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			//cargarDatosLocalDestino(codigo, null, null);
			cargarCorrelativo(codigo);
		} else {
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
		}
	});
	
	/////Personal Externo

	$('input[type=radio][name=rb_tie_ate_gobierno_per_ext]').change(function() {
		cargarTipoAtencionPer(this.value);
    });
	
	$('#sel_gore_per_ext').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			//cargarCorrelativo(codigo);
		}else{
			$('#sel_gore_per_ext').html('');
		}
	});
	
	$('#sel_departamento_per_ext').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvinciaPer(codigo, null);
		} else {
			$('#sel_provincia_per_ext').html('');
		}
	});
	
	$('#sel_provincia_per_ext').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarDistritoPer(codigo, null);
		} else {
			$('#sel_distrito_per_ext').html('');
		}
	});
	
	$('#sel_departamento_bus').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvinciaBus(codigo, null);
		} else {
			$('#sel_provincia_bus').html('');
		}
	});
	
	$('#btn_buscar_cat').click(function(e) {
		e.preventDefault();
	
		listarTablaProductos();
		
	});
	
	$('#btn_buscar_nom').click(function(e) {
		e.preventDefault();
	
		listarTablaProductosXNombre();
		
	});
	
	
	
});

function inicializarDatos() {

  	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#li_tablas').attr('class', 'active');
	$('#li_tablas').closest('li').children('a');
	limpiarTablas();
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {

	}
}


function mostrarTablaAlmacen(respuesta) {

	tbl_almacen.dataTable().fnDestroy();
	
	tbl_almacen.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idAlmacen',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idAlmacen',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idDdi'
		}, {
			data : 'idAlmacen'
		}, {
			data : 'codAlmacen'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'direccion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],
		columnDefs : [
  			{ width : '14%', targets : 5 },
  			{ width : '14%', targets : 6 },
			{ width : '15%', targets : 6 },
			{ width : '14%', targets : 6 }
  		]
	});
	
	listaAlmacen = respuesta;

}

function mostrarTablaAlmacenExterno(respuesta) {

	tbl_almacen_externo.dataTable().fnDestroy();
	
	tbl_almacen_externo.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idAlmacenExterno',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox" id="chk_'+data+'" name="ids[]"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idAlmacenExterno',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idAlmacenExterno'
		}, {
			data : 'idRegion'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'codAlmacen'
		}, {
			data : 'direccion'
		}, {
			data : 'rucUbigeo'
		}, {
			data : 'tipoAlmacen'
		}, {
			data : 'nombreUbigeo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],
		columnDefs : [
  			{ width : '14%', targets : 5 },
  			{ width : '14%', targets : 6 },
			{ width : '15%', targets : 7 },
			{ width : '14%', targets : 8 }
  		]
	});
	
	listaAlmacenExterno = respuesta;

}

function mostrarTablaAnio(respuesta) {

	tbl_anio.dataTable().fnDestroy();
	
	tbl_anio.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codAnio',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox" id="chk_'+data+'" name="ids[]"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'codAnio',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codAnio'
		}, {
			data : 'descripcion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaAnio = respuesta;

}

function mostrarTablaCategoria(respuesta) {

	tbl_categoria.dataTable().fnDestroy();
	
	tbl_categoria.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idCategoria',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idCategoria',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idCategoria'
		}, {
			data : 'nombreCategoria'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaCategoria = respuesta;

}

function mostrarTablaChofer(respuesta) {

	tbl_chofer.dataTable().fnDestroy();
	
	tbl_chofer.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idChofer',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idChofer',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idChofer'
		}, {
			data : 'nombreChofer'
		}, {
			data : 'nroDni'
		}, {
			data : 'nroBrevete'
		}, {
			data : 'nombreEmpresa'
		}, {
			data : 'idEmpresaTransporte'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaChofer = respuesta;

}

function mostrarTablaDdi(respuesta) {

	tbl_ddi.dataTable().fnDestroy();
	
	tbl_ddi.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDdi',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idDdi',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idDdi'
		}, {
			data : 'codigoDdi'
		}, {
			data : 'nombreDdi'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaDdi = respuesta;

}

function mostrarTablaDistritoInei(respuesta) {

	tbl_distrito_inei.dataTable().fnDestroy();
	
	tbl_distrito_inei.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codAnio',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'codAnio',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codAnio'
		}, {
			data : 'codDistrito'
		}, {
			data : 'desDepartamento'
		}, {
			data : 'desProvincia'
		}, {
			data : 'desDistrito'
		}, {
			data : 'poblacion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaDistritoInei = respuesta;

}

function mostrarTablaDonante(respuesta) {

	tbl_donante.dataTable().fnDestroy();
	
	tbl_donante.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDonante',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idDonante',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idDonante'
		}, {
			data : 'nombreDonante'
		}, {
			data : 'nroDocumento'
		}, {
			data : 'tipo'
		}, {
			data : 'representante'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaDonante = respuesta;

}

function mostrarTablaEmpresaTransporte(respuesta) {

	tbl_empresa_transporte.dataTable().fnDestroy();
	
	tbl_empresa_transporte.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEmpresaTransporte',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idEmpresaTransporte',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idEmpresaTransporte'
		}, {
			data : 'nombreEmpresa'
		}, {
			data : 'direccion'
		}, {
			data : 'representante'
		}, {
			data : 'ruc'
		}, {
			data : 'tipoTransporte'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaEmpresaTransporte = respuesta;

}

function mostrarTablaEnvase(respuesta) {

	tbl_envase.dataTable().fnDestroy();
	
	tbl_envase.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEnvase',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idEnvase',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idEnvase'
		}, {
			data : 'nombreEnvase'
		}, {
			data : 'descripcion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaEnvase = respuesta;

}

function mostrarTablaEstado(respuesta) {

	tbl_estado.dataTable().fnDestroy();
	
	tbl_estado.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEstado',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idEstado'
		}, {
			data : 'nombreEstado'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaEstado = respuesta;

}

function mostrarTablaEstadoDonacion(respuesta) {

	tbl_estado_donacion.dataTable().fnDestroy();
	
	tbl_estado_donacion.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEstado',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idEstado'
		}, {
			data : 'nombreEstado'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaEstadoDonacion = respuesta;

}

function mostrarTablaEstadoProgramacion(respuesta) {

	tbl_estado_programacion.dataTable().fnDestroy();
	
	tbl_estado_programacion.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEstado',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idEstado'
		}, {
			data : 'nombreEstado'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaEstadoProgramacion = respuesta;

}

function mostrarTablaMedioTransporte(respuesta) {

	tbl_medio_transporte.dataTable().fnDestroy();
	
	tbl_medio_transporte.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idMedioTransporte',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idMedioTransporte',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idMedioTransporte'
		}, {
			data : 'nombreMedioTransporte'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaMedioTransporte = respuesta;

}

function mostrarTablaModAlmacen(respuesta) {

	tbl_mod_almacen.dataTable().fnDestroy();
	
	tbl_mod_almacen.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idModAlmacen',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idModAlmacen',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idModAlmacen'
		}, {
			data : 'nombreModalidad'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaModAlmacen = respuesta;

}

function mostrarTablaMoneda(respuesta) {

	tbl_moneda.dataTable().fnDestroy();
	
	tbl_moneda.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idMoneda',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idMoneda',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idMoneda'
		}, {
			data : 'codMoneda'
		}, {
			data : 'nombreMoneda'
		}, {
			data : 'flagActivo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaMoneda = respuesta;

}

function mostrarTablaMotivoTraslado(respuesta) {

	tbl_motivo_traslado.dataTable().fnDestroy();
	
	tbl_motivo_traslado.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idMotivoTraslado',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idMotivoTraslado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idMotivoTraslado'
		}, {
			data : 'nombreMotivo'
		}, {
			data : 'flagActivo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaMotivoTraslado = respuesta;

}

function mostrarTablaOficina(respuesta) {

	tbl_oficina.dataTable().fnDestroy();
	
	tbl_oficina.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idOficina',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {
			data : 'nombreDdi'
		}, {	
			data : 'idOficina',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idOficina'
		}, {
			data : 'nombreOficina'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaOficina = respuesta;

}

function mostrarTablaPais(respuesta) {

	tbl_pais.dataTable().fnDestroy();
	
	tbl_pais.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idPais',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idPais',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idPais'
		}, {
			data : 'nombrePais'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaPais = respuesta;

}

function mostrarTablaParametro(respuesta) {

	tbl_parametro.dataTable().fnDestroy();
	
	tbl_parametro.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idParametro',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idParametro',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idParametro'
		}, {
			data : 'nombreParametro'
		}, {
			data : 'tipo'
		}, {
			data : 'valor'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaParametro = respuesta;

}

function mostrarTablaPersonal(respuesta) {

	tbl_personal.dataTable().fnDestroy();
	
	tbl_personal.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idPersonal',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idPersonal',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idPersonal'
		}, {
			data : 'idDdi'
		}, {
			data : 'nombreCompleto'
		}, {
			data : 'cargo'
		}, {
			data : 'nombreOficina'
		}, {
			data : 'telefono'
		}, {
			data : 'estado'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaPersonal = respuesta;

}

function mostrarTablaPersonalExterno(respuesta) {

	tbl_personal_externo.dataTable().fnDestroy();
	
	tbl_personal_externo.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idPersonalExterno',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idPersonalExterno',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idPersonalExterno'
		}, {
			data : 'nombres'
		}, {
			data : 'cargo'
		}, {
			data : 'telefono'
		}, {
			data : 'region'
		}, {
			data : 'provincia'
		}, {
			data : 'distrito'
		}, {
			data : 'flagActivo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaPersonalExterno = respuesta;

}

function mostrarTablaRegion(respuesta) {

	tbl_region.dataTable().fnDestroy();
	
	tbl_region.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idRegion',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idRegion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idRegion'
		}, {
			data : 'codRegion'
		}, {
			data : 'nombreRegion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaRegion = respuesta;

}

function mostrarTablaTipCamion(respuesta) {

	tbl_tip_camion.dataTable().fnDestroy();
	
	tbl_tip_camion.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idTipCamion',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idTipCamion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idTipCamion'
		}, {
			data : 'tonelaje'
		}, {
			data : 'volumen'
		} , {
			data : 'descripcion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaTipCamion = respuesta;

}

function mostrarTablaTipControlCalidad(respuesta) {

	tbl_tip_control_calidad.dataTable().fnDestroy();
	
	tbl_tip_control_calidad.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idTipControl',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idTipControl',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idTipControl'
		}, {
			data : 'nombreTipControl'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaTipControlCalidad = respuesta;

}

function mostrarTablaTipDocumento(respuesta) {

	tbl_tip_documento.dataTable().fnDestroy();
	
	tbl_tip_documento.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idTipDocumento',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idTipDocumento',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idTipDocumento'
		}, {
			data : 'nombreDocumento'
		}, {
			data : 'nombreCorto'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaTipDocumento = respuesta;

}

function mostrarTablaTipMovimiento(respuesta) {

	tbl_tip_movimiento.dataTable().fnDestroy();
	
	tbl_tip_movimiento.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idTipMovimiento',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idTipMovimiento',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idTipMovimiento'
		}, {
			data : 'nombreMovimiento'
		}, {
			data : 'flagIngreso'
		}, {
			data : 'flagSalida'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaTipMovimiento = respuesta;

}

function mostrarTablaUnidadMedida(respuesta) {

	tbl_unidad_medida.dataTable().fnDestroy();
	
	tbl_unidad_medida.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idUnidad',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}	
		}, {	
			data : 'idUnidad',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idUnidad'
		}, {
			data : 'nombreUnidad'
		}, {
			data : 'nombreCorto'
		}, {
			data : 'flagActivo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		],

	});
	
	listaUnidadMedida = respuesta;

}

function listarProductos(respuesta) {

	tbl_catalogo_producto.dataTable().fnDestroy();
	
	tbl_catalogo_producto.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idProducto',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
							'</label>';	
				} else {
					return '';	
				}											
			}
		}, {	
			data : 'idProducto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'idProducto'
		}, {
			data : 'codigoProducto'
		}, {
			data : 'codigoSiga'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidadMedida'
		}, {
			data : 'nombreEnvase'
		}, {
			data : 'estado'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15,
		aLengthMenu : [
			[15, 50, 100],
			[15, 50, 100]
		]
	});
	
	listaProductosCache = respuesta;

}

//almacenes externos
function cargarTipoAtencionBus(valor) {

	if (valor == 'R') {
		$('#sel_gore_bus').prop('disabled', false);
		$('#sel_provincia_bus').prop('disabled', true);
		$('#sel_departamento_bus').prop('disabled', true);

		$('#div_gore_destino_bus').show();
		$('#div_ubi_destino_bus').hide();
	} else if (valor == 'L') {
		$('#sel_gore_bus').prop('disabled', true);
		$('#sel_provincia_bus').prop('disabled', false);
		$('#sel_departamento_bus').prop('disabled', false);
		$('#div_gore_destino_bus').hide();
		$('#div_ubi_destino_bus').show();
	}
	
}

function cargarTipoAtencion(valor) {
	$('#txt_cod_ext').val('');
	
	if (valor == 'R') {
		$('#sel_gore').prop('disabled', false);
		$('#sel_provincia').prop('disabled', true);
		$('#sel_distrito').prop('disabled', true);
		$('#sel_departamento').prop('disabled', true);

		$('#div_gore_destino').show();
		$('#div_ubi_destino').hide();
	} else if (valor == 'L') {
		$('#sel_gore').prop('disabled', true);
		$('#sel_provincia').prop('disabled', false);
		$('#sel_distrito').prop('disabled', false);
		$('#sel_departamento').prop('disabled', false);
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').show();
	}
	
}

function cargarProvincia(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function cargarDistrito(codigo, codigoDistrito) {
	var params = { 
		coddpto : $('#sel_departamento').val(),
		codprov : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/listarDistrito', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.coddist+'">'+item.nombre+'</option>';
			});
			$('#sel_distrito').html(options);
			if (codigoDistrito != null) {
				$('#sel_distrito').val(codigoDistrito);       	
			}
		
		}
		loadding(false);
	});
}

function cargarProvinciaBus(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia_bus').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia_bus').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function cargarCorrelativo(codigo) {
	var params = { 
		codUbigeo : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/obtenerCodigoAlmacenExterno', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			console.log("CODIGO: "+respuesta.codAlmacen);
			$('#txt_cod_ext').val(respuesta.codAlmacen);       	
		}
		loadding(false);
	});
	
	
}


//personal externo
function cargarTipoAtencionPer(valor) {

	if (valor == 'R') {
		$('#sel_gore_per_ext').prop('disabled', false);
		$('#sel_provincia_per_ext').prop('disabled', true);
		$('#sel_distrito_per_ext').prop('disabled', true);
		$('#sel_departamento_per_ext').prop('disabled', true);

		$('#div_gore_destino_per_ext').show();
		$('#div_ubi_destino_per_ext').hide();
	} else if (valor == 'L') {
		$('#sel_gore_per_ext').prop('disabled', true);
		$('#sel_provincia_per_ext').prop('disabled', false);
		$('#sel_distrito_per_ext').prop('disabled', false);
		$('#sel_departamento_per_ext').prop('disabled', false);
		$('#div_gore_destino_per_ext').hide();
		$('#div_ubi_destino_per_ext').show();
	}
	
}

function cargarProvinciaPer(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia_per_ext').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia_per_ext').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function cargarDistritoPer(codigo, codigoDistrito) {
	var params = { 
		coddpto : $('#sel_departamento_per_ext').val(),
		codprov : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/tablas-generales/listarDistrito', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.coddist+'">'+item.nombre+'</option>';
			});
			$('#sel_distrito_per_ext').html(options);
			if (codigoDistrito != null) {
				$('#sel_distrito_per_ext').val(codigoDistrito);       	
			}
		
		}
		loadding(false);
	});
}


function limpiarTablas() {
	$('#div_tbl_almacen').hide();
	$('#div_tbl_almacen_externo').hide();
	$('#div_tbl_anio').hide();
	$('#div_tbl_categoria').hide();
	$('#div_tbl_chofer').hide();
	$('#div_tbl_ddi').hide();
	$('#div_tbl_distrito_inei').hide();
	$('#div_tbl_donante').hide();
	$('#div_tbl_empresa_transporte').hide();
	$('#div_tbl_envase').hide();
	$('#div_tbl_estado').hide();
	$('#div_tbl_estado_donacion').hide();
	$('#div_tbl_estado_programacion').hide();
	$('#div_tbl_medio_transporte').hide();
	$('#div_tbl_mod_almacen').hide();
	$('#div_tbl_moneda').hide();
	$('#div_tbl_motivo_traslado').hide();
	$('#div_tbl_oficina').hide();
	$('#div_tbl_pais').hide();
	$('#div_tbl_parametro').hide();
	$('#div_tbl_personal').hide();
	$('#div_tbl_personal_externo').hide();
	$('#div_tbl_region').hide();
	$('#div_tbl_tip_camion').hide();
	$('#div_tbl_tip_control_calidad').hide();
	$('#div_tbl_tip_documento').hide();
	$('#div_tbl_tip_movimiento').hide();
	$('#div_tbl_unidad_medida').hide();
	$('#div_tbl_catalogo_producto').hide();
}

function ocultarMenu(){
	$('#href_nuevo').hide();
	$('#href_editar').hide();
	$('#href_doc_eliminar').hide();
}

function mostrarMenu(){
	$('#href_nuevo').show();
	$('#href_editar').show();
	$('#href_doc_eliminar').show();
}

function listarTablasInsertUpdate(codigo){
	var tabla=null;
	var menu=null;
	if (!esnulo(codigo)) {
		var arr = codigo.split('*');
		tabla = arr[1];
		menu = arr[2];
	}
	if (!esnulo(codigo)) {						
		var params = { 
			descripcion : tabla
		};			
		loadding(true);
		consultarAjax('GET', '/administracion/tablas-generales/listarTabla', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				if(tabla=='BAH_MAE_ALMACEN'){
					indicador="BAH_MAE_ALMACEN";
					mostrarTablaAlmacen(respuesta);
					nombreTabla=tbl_almacen;
					tablaExcel = "tbl_almacen";
					$('#div_tbl_almacen').show();
				}else if(tabla=='BAH_MAE_ALMACEN_EXTERNO'){
					indicador="BAH_MAE_ALMACEN_EXTERNO";
					mostrarTablaAlmacenExterno(respuesta);
					nombreTabla=tbl_almacen_externo;
					tablaExcel = "tbl_almacen_externo";
					$('#div_tbl_almacen_externo').show();
				}else if(tabla=='BAH_MAE_ANIO'){
					indicador="BAH_MAE_ANIO";
					mostrarTablaAnio(respuesta);
					nombreTabla=tbl_anio;
					tablaExcel = "tbl_anio";
					$('#div_tbl_anio').show();
				}else if(tabla=='BAH_MAE_CATEGORIA_BAH'){
					indicador="BAH_MAE_CATEGORIA_BAH";
					mostrarTablaCategoria(respuesta);
					nombreTabla=tbl_categoria;
					tablaExcel = "tbl_categoria";
					$('#div_tbl_categoria').show();
				}else if(tabla=='BAH_MAE_CHOFER'){
					indicador="BAH_MAE_CHOFER";
					mostrarTablaChofer(respuesta);
					nombreTabla=tbl_chofer;
					tablaExcel = "tbl_chofer";
					$('#div_tbl_chofer').show();
				}else if(tabla=='BAH_MAE_DDI'){
					indicador="BAH_MAE_DDI";
					mostrarTablaDdi(respuesta);
					nombreTabla=tbl_ddi;
					tablaExcel = "tbl_ddi";
					$('#div_tbl_ddi').show();
				}else if(tabla=='BAH_MAE_DISTRITO_INEI'){
					indicador="BAH_MAE_DISTRITO_INEI";
					mostrarTablaDistritoInei(respuesta);
					nombreTabla=tbl_distrito_inei;
					tablaExcel = "tbl_distrito_inei";
					$('#div_tbl_distrito_inei').show();
				}else if(tabla=='BAH_MAE_DONANTE'){
					indicador="BAH_MAE_DONANTE";
					mostrarTablaDonante(respuesta);
					nombreTabla=tbl_donante;
					tablaExcel = "tbl_donante";
					$('#div_tbl_donante').show();
				}else if(tabla=='BAH_MAE_EMP_TRANSPORTE'){
					indicador="BAH_MAE_EMP_TRANSPORTE";
					mostrarTablaEmpresaTransporte(respuesta);
					nombreTabla=tbl_empresa_transporte;
					tablaExcel = "tbl_empresa_transporte";
					$('#div_tbl_empresa_transporte').show();
				}else if(tabla=='BAH_MAE_ENVASE'){
					indicador="BAH_MAE_ENVASE";
					mostrarTablaEnvase(respuesta);
					nombreTabla=tbl_envase;
					tablaExcel = "tbl_envase";
					$('#div_tbl_envase').show();
				}else if(tabla=='BAH_MAE_ESTADO'){
					indicador="BAH_MAE_ESTADO";
					mostrarTablaEstado(respuesta);
					nombreTabla=tbl_estado;
					tablaExcel = "tbl_estado";
					$('#div_tbl_estado').show();
				}else if(tabla=='BAH_MAE_ESTADO_DONACION'){
					indicador="BAH_MAE_ESTADO_DONACION";
					mostrarTablaEstadoDonacion(respuesta);
					nombreTabla=tbl_estado_donacion;
					tablaExcel = "tbl_estado_donacion";
					$('#div_tbl_estado_donacion').show();
				}else if(tabla=='BAH_MAE_ESTADO_PROGRAMACION'){
					indicador="BAH_MAE_ESTADO_PROGRAMACION";
					mostrarTablaEstadoProgramacion(respuesta);
					nombreTabla=tbl_estado_programacion;
					tablaExcel = "tbl_estado_programacion";
					$('#div_tbl_estado_programacion').show();
				}else if(tabla=='BAH_MAE_MEDIO_TRANSPORTE'){
					indicador="BAH_MAE_MEDIO_TRANSPORTE";
					mostrarTablaMedioTransporte(respuesta);
					nombreTabla=tbl_medio_transporte;
					tablaExcel = "tbl_medio_transporte";
					$('#div_tbl_medio_transporte').show();
				}else if(tabla=='BAH_MAE_MOD_ALMACEN'){
					indicador="BAH_MAE_MOD_ALMACEN";
					mostrarTablaModAlmacen(respuesta);
					nombreTabla=tbl_mod_almacen;
					tablaExcel = "tbl_mod_almacen";
					$('#div_tbl_mod_almacen').show();
				}else if(tabla=='BAH_MAE_MONEDA'){
					indicador="BAH_MAE_MONEDA";
					mostrarTablaMoneda(respuesta);
					nombreTabla=tbl_moneda;
					tablaExcel = "tbl_moneda";
					$('#div_tbl_moneda').show();
				}else if(tabla=='BAH_MAE_MOTIVO_TRASLADO'){
					indicador="BAH_MAE_MOTIVO_TRASLADO";
					mostrarTablaMotivoTraslado(respuesta);
					nombreTabla=tbl_motivo_traslado;
					tablaExcel = "tbl_motivo_traslado";
					$('#div_tbl_motivo_traslado').show();
				}else if(tabla=='BAH_MAE_OFICINA'){
					indicador="BAH_MAE_OFICINA";
					mostrarTablaOficina(respuesta);
					nombreTabla=tbl_oficina;
					tablaExcel = "tbl_oficina";
					$('#div_tbl_oficina').show();
				}else if(tabla=='BAH_MAE_PAIS'){
					indicador="BAH_MAE_PAIS";
					mostrarTablaPais(respuesta);
					nombreTabla=tbl_pais;
					tablaExcel = "tbl_pais";
					$('#div_tbl_pais').show();
				}else if(tabla=='BAH_MAE_PARAMETRO'){
					indicador="BAH_MAE_PARAMETRO";
					mostrarTablaParametro(respuesta);
					nombreTabla=tbl_parametro;
					tablaExcel = "tbl_parametro";
					$('#div_tbl_parametro').show();
				}else if(tabla=='BAH_MAE_PERSONAL'){
					indicador="BAH_MAE_PERSONAL";
					mostrarTablaPersonal(respuesta);
					nombreTabla=tbl_personal;
					tablaExcel = "tbl_personal";
					$('#div_tbl_personal').show();
				}else if(tabla=='BAH_MAE_PERSONAL_EXTERNO'){
					indicador="BAH_MAE_PERSONAL_EXTERNO";
					mostrarTablaPersonalExterno(respuesta);
					nombreTabla=tbl_personal_externo;
					tablaExcel = "tbl_personal_externo";
					$('#div_tbl_personal_externo').show();
				}else if(tabla=='BAH_MAE_REGION'){
					indicador="BAH_MAE_REGION";
					mostrarTablaRegion(respuesta);
					nombreTabla=tbl_region;
					tablaExcel = "tbl_region";
					$('#div_tbl_region').show();
				}else if(tabla=='BAH_MAE_TIP_CAMION'){
					indicador="BAH_MAE_TIP_CAMION";
					mostrarTablaTipCamion(respuesta);
					nombreTabla=tbl_tip_camion;
					tablaExcel = "tbl_tip_camion";
					$('#div_tbl_tip_camion').show();
				}else if(tabla=='BAH_MAE_TIP_CONTROL_CALIDAD'){
					indicador="BAH_MAE_TIP_CONTROL_CALIDAD";
					mostrarTablaTipControlCalidad(respuesta);
					nombreTabla=tbl_tip_control_calidad;
					tablaExcel = "tbl_tip_control_calidad";
					$('#div_tbl_tip_control_calidad').show();
				}else if(tabla=='BAH_MAE_TIP_DOCUMENTO'){
					indicador="BAH_MAE_TIP_DOCUMENTO";
					mostrarTablaTipDocumento(respuesta);
					nombreTabla=tbl_tip_documento;
					tablaExcel = "tbl_tip_documento";
					$('#div_tbl_tip_documento').show();
				}else if(tabla=='BAH_MAE_TIP_MOVIMIENTO'){
					indicador="BAH_MAE_TIP_MOVIMIENTO";
					mostrarTablaTipMovimiento(respuesta);
					nombreTabla=tbl_tip_movimiento;
					tablaExcel = "tbl_tip_movimiento";
					$('#div_tbl_tip_movimiento').show();
				}else if(tabla=='BAH_MAE_UNIDAD_MEDIDA'){
					indicador="BAH_MAE_UNIDAD_MEDIDA";
					mostrarTablaUnidadMedida(respuesta);
					nombreTabla=tbl_unidad_medida;
					tablaExcel = "tbl_unidad_medida";
					$('#div_tbl_unidad_medida').show();
				}
				
				if(menu=='1'){
					mostrarMenu();
				}else{
					ocultarMenu();
				}
				
			}
			loadding(false);
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_personal_oficina');
		});
	} else {
		//$('#sel_personal_oficina').html('');
		//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_personal_oficina');
	}
}


function guardarIndex(index){
	console.log("INDEX SELEC: "+index);
}

function buscarAlmacenExterno(){
	var tabla=null;
	var menu=null;
	if (!esnulo(cod)) {
		var arr = cod.split('*');
		tabla = arr[1];
		menu = arr[2];
	}
	
	var flag=null;
	var codUbigeo=null;
	if($('input[name=rb_tie_ate_gobierno_bus]:checked').val()=='R'){
		flag='R';
	}else{
		if($('input[name=rb_tie_ate_gobierno_bus]:checked').val()=='L'){
			flag='L';
		}
	}
	if (!esnulo(cod)) {						
		var params = { 
			vcodigoParam2 : $('#sel_departamento_bus').val(),
			vcodigoParam3 : $('#sel_provincia_bus').val(),
			vcodigoParam4 : flag
		};			
		loadding(true);
		consultarAjax('GET', '/administracion/tablas-generales/listarTablaAlmacenExterno', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {

				indicador="BAH_MAE_ALMACEN_EXTERNO";
				mostrarTablaAlmacenExterno(respuesta);
				nombreTabla=tbl_almacen_externo;
				tablaExcel ="tbl_almacen_externo";
				$('#div_tbl_almacen_externo').show();
				
				if(menu=='1'){
					mostrarMenu();
				}else{
					ocultarMenu();
				}
				
			}
			loadding(false);
			
		});
	} else {

	}
}

function listarTablaProductos() {
	if (!esnulo(cod)) {
		var arr = cod.split('*');
		tabla = arr[1];
		menu = arr[2];
	}
	indicador="BAH_MAE_CATALOGO_PRODUCTO";
	nombreTabla=tbl_catalogo_producto;
	tablaExcel = "tbl_catalogo_producto";
	var params = { 
		idCategoria : $('#sel_catalogo_producto').val()
	};
	
	loadding(true);

	consultarAjax('GET', '/administracion/catalogo-productos/listarProductos', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarProductos(respuesta);
			$('#div_tbl_catalogo_producto').show();
		}
		loadding(false);
	});
}

function listarTablaProductosXNombre() {
	if (!esnulo(cod)) {
		var arr = cod.split('*');
		tabla = arr[1];
		menu = arr[2];
	}
	indicador="BAH_MAE_CATALOGO_PRODUCTO";
	nombreTabla=tbl_catalogo_producto;
	tablaExcel = "tbl_catalogo_producto";
	var params = { 
		nombreProducto : $('#txt_nombre').val()
	};
	
	loadding(true);

	consultarAjax('GET', '/administracion/catalogo-productos/listarProductosXNombre', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarProductos(respuesta);
			$('#div_tbl_catalogo_producto').show();
		}
		loadding(false);
	});
}


