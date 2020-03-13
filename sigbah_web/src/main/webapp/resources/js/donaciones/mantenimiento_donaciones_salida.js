var listaAlimentariosCache = new Object();
var listaProductosCache = new Object();
var listaDocumentosCache = new Object();

var tbl_det_productos = $('#tbl_det_productos');
var tbl_det_documentos = $('#tbl_det_documentos');
var tbl_det_estados = $('#tbl_det_estados');


var frm_dat_generales = $('#frm_dat_generales');
var frm_det_documentos = $('#frm_det_documentos');
var frm_det_productos = $('#frm_det_productos');

var medTransporteDon = "";
$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy'
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#txt_fec_entrega').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		var fechaRegistro = $('#txt_fecha').val();
		if (!esnulo(fecha)) {

		    if (comparafecha(fecha, fechaRegistro)=='2') {
		    	addWarnMessage(null, 'La fecha de entrega debe ser mayor o igual a la fecha de registro.');
		    	$('#txt_fec_entrega').val('');
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
//	$('#txt_fecha').datepicker().on('changeDate', function(e) {
//		e.preventDefault();
//		var fecha = $(this).val();
//		var fecha2 = $('#txt_fec_entrega').val();
//		if (!esnulo(fecha)) {
//			console.log(comparafecha(fecha2, fecha));
//		    if (comparafecha(fecha2, fecha)=='2') {
//		    	addWarnMessage(null, 'La fecha de entrega debe ser mayor o igual a la fecha de registro.');
//		    	$('#txt_fecha').val('');
//		    	$('#'+$(this).attr('id')).focus();
//		    } else {
//		    	
//		    }
//		}
//		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
//	});
	
	$('#txt_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		var fecha2 = $('#txt_fec_entrega').val();
		if (!esnulo(fecha)) {
			var mes = fecha.substring(3, 5);
		    var anio = fecha.substring(6, 10);	
		    console.log(donaciones.mes+donaciones.anio);
		    if (mes != donaciones.mes || anio != donaciones.anio) {
		    	$('#hid_val_fec_trabajo').val('0');
		    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
		    	$('#txt_fecha').val('');
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	
		    	if (comparafecha(fecha2, fecha)=='2') {
		    		addWarnMessage(null, 'La fecha de entrega debe ser mayor o igual a la fecha de registro.');
			    	$('#txt_fecha').val('');
			    	$('#'+$(this).attr('id')).focus();
			    } else {
			    	
			    }
		    	$('#hid_val_fec_trabajo').val('1');
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_doc_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_documentos.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_pro_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_productos.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#sel_movimiento').change(function() {
		var val_tip_movimiento = $(this).val();		
		if (!esnulo(val_tip_movimiento)) {
			
			frm_dat_generales.data('bootstrapValidator').resetForm();
			
			//cargarTipoMovimiento(val_tip_movimiento, true);
			
			$('#sel_cod_donacion').val('');
			$('#txt_tipo_donacion').val('');
			$('#txt_donante').val('');
			$('#txt_representante').val('');
			$('#sel_control_calidad').val('');
			$('#sel_almacen').val('');
			$('#sel_med_transporte').val('');
			$('#sel_emp_transporte').val('');
			$('#txt_fec_llegada').val('');
			$('#sel_chofer').val('');
			$('#txt_nro_placa').val('');
			$('#sel_responsable').val('');
			
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_cod_donacion');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_tipo_donacion');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_donante');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_representante');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_control_calidad');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_almacen');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_med_transporte');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_fec_llegada');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_nro_placa');
//			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_responsable');
			
			frm_dat_generales.data('bootstrapValidator').resetForm();
			
			cargarTipoMovimiento(val_tip_movimiento);
			
			$('#sel_ddi').val('');
			$('input[name=rb_tie_ate_gobierno]').prop('checked', false);
			$('#sel_gore').val('');
			$('#sel_departamento').val('');
			
			$('#sel_provincia').html('');
			$('#sel_distrito').html('');
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
			
			$('#sel_med_transporte').val('');
			//$('#sel_emp_transporte').html('');
			$('#txt_nro_placa').val('');
			$('#txt_fec_entrega').val('');
			$('#sel_chofer').val('');
			
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_ddi');
			frm_dat_generales.bootstrapValidator('revalidateField', 'rb_tie_ate_gobierno');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_gore');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_departamento');
			
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_med_transporte');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_nro_placa');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_fec_entrega');
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
		}
	});
	
	$('input[type=radio][name=rb_tie_ate_gobierno]').change(function() {
		cargarTipoAtencion(this.value);
    });
	
	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		if ($('#hid_val_fec_trabajo').val() == '0') {
	    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
	    	return;
		}

		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			var codigo = $('#hid_id_salida').val();
		//	var tipoBien = $('input[name="rb_tip_bien"]:checked').val();
//			var idDonante = null;
//			var val_donante = $('#sel_donante').val();
//			if (!esnulo(val_donante)) {
//				var arr = val_donante.split('_');
//				idDonante = arr[0];
//			}
//			
//			var idDonacion = null;
//			var val_donacion = $('#sel_cod_donacion').val();
//			if (!esnulo(val_donacion)) {
//				var arr = val_donacion.split('_');
//				idDonacion = arr[0];
//			}
//			
//			var idControlCalidad = null;
//			var flagControlCalidad = null;
//			var selecControlCalidad = $('#sel_control_calidad').val();
//			if(selecControlCalidad==='0'){
//				idControlCalidad='';
//				flagControlCalidad='0';
//			}else{
//				idControlCalidad=$('#sel_control_calidad').val();
//				flagControlCalidad='1';
//			}
//			
//			var idIngreso = $('#hid_id_ingreso').val();
//			var nroOrdenIn = null;
//			if(idIngreso == "" || idIngreso==0){
//				nroOrdenIn='';
//			}else{
//				nroOrdenIn=$('#txt_nro_ingreso').val();
//			}
			var flag=null;
			var codUbigeo=null;
			if($('input[name=rb_tie_ate_gobierno]:checked').val()=='R'){
				flag='R';
				codUbigeo=$('#sel_gore').val();
			}else{
				if($('input[name=rb_tie_ate_gobierno]:checked').val()=='L'){
					flag='L';
					codUbigeo=$('#sel_distrito').val();
				}else{
					flag='I';
					codUbigeo='';
				}
			}
			var mov = $('#sel_movimiento').val();
			var responsable=null;
			var responsable_ext=null;
			var almacen=null;
			var almacen_ext=null;
			if(mov=='3' || mov=='4'){
				almacen=$('#sel_alm_destino').val();
				almacen_ext='';
				responsable=$('#sel_res_recepcion').val();
				responsable_ext='';
			}else{
				almacen='';
				almacen_ext=$('#sel_alm_destino').val();
				responsable='';
				responsable_ext=$('#sel_res_recepcion').val();
			}

			
			var params = {
					idSalida : $('#hid_id_salida').val(),
					codigoAnio : $('#txt_anio').val(),
					//codigoMes : $('#hid_id_donacion').val(),
					//idAlmacen :  $('#sel_tip_persona').val(),
					//codigoDdi :  $('#sel_oficina').val(), 
					//codAlmacen :  $('#sel_oficina').val(), 
					nroOrdenSalida : $('#txt_cod_salida').val(),
					fechaEmision: $('#txt_fecha').val(), 
					codigoUbigeo: codUbigeo,
					idProgramacion : '',
					idResponsable :  $('#sel_responsable').val(), 
					idResponsableExt : responsable_ext, 
					idSolicitante : $('#sel_solicitada').val(), 
					idResponsableRecepcion : responsable,
					idProyectoManifiesto :  '', 
					idTipoMovimiento :  $('#sel_movimiento').val(), 
					idAlmacenDestino :  almacen, 
					idAlmacenDestinoExt :  almacen_ext, 
					idMedTransporte   :  $('#sel_med_transporte').val(), 
					idEmpresaTrans   :  $('#sel_emp_transporte').val(), 
					idChofer : $('#sel_chofer').val(),
					nroPlaca : $('#txt_nro_placa').val(),
					fechaEntrega : $('#txt_fec_entrega').val(),
					flagTipoDestino : flag,
					observacion : $('#txt_observaciones').val(),
					idEstado : $('#sel_estado').val(),
					//usuarioRegistro : $('#sel_salida').val()

			};
			
			loadding(true);
			
			consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/grabarDonacionesSalida', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					$('#hid_id_salida').val(respuesta.idSalida);
					if (!esnulo(codigo)) {
						
						addSuccessMessage(null, respuesta.mensajeRespuesta);
						
					} else {
						
						$('#hid_id_donacion').val(respuesta.idDonacion);
						$('#hid_id_salida').val(respuesta.idSalida);
						
						
						$('#li_documentos').attr('class', '');
						$('#li_documentos').closest('li').children('a').attr('data-toggle', 'tab');
						
						$('#li_productos').attr('class', '');
						$('#li_productos').closest('li').children('a').attr('data-toggle', 'tab');
						
						$('#li_estados').attr('class', '');
						$('#li_estados').closest('li').children('a').attr('data-toggle', 'tab');
//						$('#txt_nro_con_calidad').val(respuesta.nroControlCalidad);
//						
//						if (tipoBien == '1') {					
//							$('#li_alimentarios').attr('class', '');
//							$('#li_alimentarios').closest('li').children('a').attr('data-toggle', 'tab');
//						} else {							
//							$('#li_no_alimentarios').attr('class', '');
//							$('#li_no_alimentarios').closest('li').children('a').attr('data-toggle', 'tab');
//						}
//						$('#li_documentos').attr('class', '');
//						$('#li_documentos').closest('li').children('a').attr('data-toggle', 'tab');
						listarProductoDonacion(false);
						listarDocumentoDonacion(false);
						addSuccessMessage(null, 'Se genero el N° de Salida: '+respuesta.codSalida);
						
					}
					
				}
				loadding(false);
			});			
		}
		
	});
	
	$('#btn_salir').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/inicio';
		$(location).attr('href', url);
		
	});
	
	$('#href_ali_nuevo').click(function(e) {
		e.preventDefault();

		$('#h4_tit_alimentarios').html('Nuevo Producto');
		$('#frm_det_alimentarios').trigger('reset');
		$('#hid_cod_producto').val('');
		$('#div_det_alimentarios').modal('show');
		
	});
	
	$('#href_ali_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var tbl_det_alimentarios = $('#tbl_det_alimentarios').DataTable();
		tbl_det_alimentarios.rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_alimentarios.rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaAlimentariosCache[indices[0]];
			
			$('#h4_tit_alimentarios').html('Actualizar Producto');
			$('#frm_det_alimentarios').trigger('reset');
			
			$('#hid_cod_producto').val(obj.cod_producto);
			
			$('#sel_producto').val(obj.cod_producto);
			$('#sel_producto').select2();
			
			$('#sel_uni_medida').val(obj.cod_producto);
			$('#txt_fec_vencimiento').val(obj.cod_producto);
			$('#txt_can_lote').val(obj.cod_producto);
			$('#txt_can_muestra').val(obj.cod_producto);
			$('#sel_primario').val(obj.cod_producto);
			$('#sel_olor').val(obj.cod_producto);
			$('#sel_textura').val(obj.cod_producto);
			$('#sel_secundario').val(obj.cod_producto);
			$('#sel_color').val(obj.cod_producto);
			$('#sel_sabor').val(obj.cod_producto);
			
			$('#div_det_alimentarios').modal('show');
		}
		
	});
 
	
	$('#href_doc_nuevo').click(function(e) {
		e.preventDefault();
		$('#h4_tit_no_alimentarios').html('Nuevo Documento');
		$('#frm_det_documentos').trigger('reset');
		
		$('#txt_doc_fecha').datepicker('setDate', new Date());
		$('#hid_cod_documento').val('0');
		$('#hid_cod_act_alfresco').val('');
		$('#hid_cod_ind_alfresco').val('');
		$('#txt_descripcion_doc').val('');
		$('#txt_sub_archivo').val(null);
		$('#div_det_documentos').modal('show');
		
	});
	
	$('#href_doc_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var tbl_det_documentos = $('#tbl_det_documentos').DataTable();
		tbl_det_documentos.rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_documentos.rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaDocumentosCache[indices[0]];
			
			$('#h4_tit_documentos').html('Actualizar Documento');
			$('#frm_det_documentos').trigger('reset');
			$('#hid_cod_documento').val(obj.idDocumentoSalida);			
			$('#sel_tipo_documento').val(obj.idTipoDocumento);
			$('#txt_nro_documento').val(obj.nroDocumento);
			$('#txt_doc_fecha').val(obj.fechaDocumento);
			$('#txt_descripcion_doc').val(obj.observacion);
			$('#hid_cod_act_alfresco').val(obj.codAlfresco);
			$('#hid_cod_ind_alfresco').val('');
			$('#txt_lee_sub_archivo').val(obj.nombreArchivo);
			$('#txt_sub_archivo').val(null);
			
			$('#div_det_documentos').modal('show');
		}
		
	});
	
	$('#href_doc_eliminar').click(function(e) {
		e.preventDefault();
		
		var indices = [];
		var codigo = ''
		tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDocumentoDonacion = listaDocumentosCache[index].idDocumentoSalida;
				codigo = codigo + idDocumentoDonacion + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else {
			var msg = '';
			if (indices.length > 1) {
				msg = mensajeConfirmacionEliminacionVariosRegistros;
			} else {
				msg = mensajeConfirmacionEliminacionSoloUnRegistro;
			}
			
			swal({
				  title: 'Está seguro?',
				  text: msg,
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Aceptar',
				  cancelButtonText: 'Cancelar',
				}).then(function () {
					loadding(true);
					
					var params = { 
						arrIdDocumentoDonacion : codigo,
						idSalida : $('#hid_id_salida').val()
					};
			
					consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/eliminarDocumentoDonacionSalida', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarDocumentoDonacion(true);
							addSuccessMessage(null, respuesta.mensajeRespuesta);							
						}
					});
				  swal(
					'Eliminado!',
					'Se ha eliminado satisfactoriamente.',
					'success'
				  )
				});
			
//			$.SmartMessageBox({
//				title : msg,
//				content : '',
//				buttons : '[Cancelar][Aceptar]'
//			}, function(ButtonPressed) {
//				if (ButtonPressed === 'Aceptar') {
//	
//					loadding(true);
//					
//					var params = { 
//						arrIdDocumentoDonacion : codigo,
//						idSalida : $('#hid_id_salida').val()
//					};
//			
//					consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/eliminarDocumentoDonacionSalida', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarDocumentoDonacion(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);							
//						}
//					});
//					
//				}	
//			});
			
		}
		
	});
	
	
	
	$('#href_pro_nuevo').click(function(e) {
		e.preventDefault();
		$('#txt_pro_fecha').datepicker('setDate', new Date());
		$('#h4_tit_no_alimentarios').html('Nuevo Producto');
		$('#frm_det_productos').trigger('reset');
		$('#hid_cod_producto').val('0');
		$('#sel_producto').prop('disabled', false);
		$('#sel_cat_producto').prop('disabled', false);
		limpiarFormularioProducto();
		$('#div_det_productos').modal('show');
		
	});
	
	$('#href_pro_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		tbl_det_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_productos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaProductosCache[indices[0]];
			
			$('#h4_tit_productos').html('Actualizar Producto');
			limpiarFormularioProducto();
			
			$('#hid_cod_producto').val(obj.idSalidaDet);
			
			$('#sel_cat_producto').val(obj.idCategoria);
			$('#sel_cat_producto').prop('disabled', true);			
			
			
			$('#txt_uni_medida').val(obj.unidadMedida);
			//$('#txt_fec_vencimiento').val(obj.fecVencimiento);
			$('#txt_cantidad').val(obj.cantidad);
			$('#txt_precio').val(obj.precioUnitario);
			$('#txt_imp_total').val(obj.importeTotal);
			$('#txt_peso_unitario').val(obj.pesoNetoUnitario);
			$('#txt_peso_bruto').val(obj.pesoBrutoUnitario);
			cargarProductoEdi(obj.idSalidaDet, obj.idProducto+'_'+obj.unidadMedida+'_'+obj.pesoNetoUnitario+'_'+obj.pesoBrutoUnitario);
			$('#div_det_productos').modal('show');
		}
		
	});
	
	$('#href_pro_eliminar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
		tbl_det_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_productos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDetalleSalida = listaProductosCache[index].idSalidaDet;
				codigo = codigo + idDetalleSalida + '_';
			}
		});
		console.log(codigo);
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		console.log(codigo);
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else {
			var msg = '';
			if (indices.length > 1) {
				msg = mensajeConfirmacionEliminacionVariosRegistros;
			} else {
				msg = mensajeConfirmacionEliminacionSoloUnRegistro;
			}
			
			swal({
				  title: 'Está seguro?',
				  text: msg,
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Aceptar',
				  cancelButtonText: 'Cancelar',
				}).then(function () {
					
					loadding(true);
					
					var params = { 
						idSalidaDet : codigo
					};
					console.log("IDSALIDA: "+codigo);
					consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/eliminarProductoDonacion', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoDonacion(true);
							addSuccessMessage(null, respuesta.mensajeRespuesta);							
						}
					});
				  swal(
					'Eliminado!',
					'Se ha eliminado satisfactoriamente.',
					'success'
				  )
				});
			
//			$.SmartMessageBox({
//				title : msg,
//				content : '',
//				buttons : '[Cancelar][Aceptar]'
//			}, function(ButtonPressed) {
//				if (ButtonPressed === 'Aceptar') {
//	
//					loadding(true);
//					
//					var params = { 
//						idSalidaDet : codigo
//					};
//					console.log("IDSALIDA: "+codigo);
//					consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/eliminarProductoDonacion', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarProductoDonacion(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);							
//						}
//					});
//					
//				}	
//			});
			
		}
		
	});
	
	$('#btn_gra_producto').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_det_productos.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var idProducto = null;
			var val_producto = $('#sel_producto').val();
			if (!esnulo(val_producto)) {
				var arr = val_producto.split('_');
				idProducto = arr[0];
			}
			var indicaProducto = true;

			tbl_det_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_det_productos.DataTable().rows().$('input[type="checkbox"]')[index]) {
					var idDetalleSalida = listaProductosCache[index].idProducto;
					if (idProducto == idDetalleSalida) {
						indicaProducto=false;
				    	
					}
					
				}
			});
			
			if(indicaProducto || $('#hid_cod_producto').val()!='0'){
			
				var params = { 
					idSalida : $('#hid_id_salida').val(),	
					idSalidaDet : $('#hid_cod_producto').val(),
					idProducto : idProducto,
					//idDonacion : $('#hid_id_donacion').val(),
					cantidad : ($('#txt_cantidad').val()),
					precioUnitario : $('#txt_precio').val(),
					idProgramacion : '',
					idDonacion : $('#hid_donacion_pro').val()
	//				importeTotal : $('#hid_donacion_pro').val(),
	//				fecVencimiento : $('#txt_fec_vencimiento').val(),
	//				idDonacion : $('#hid_id_donacion').val()
					//monOrigen : formatMonto($('#txt_imp_total').val())
				};
	
				loadding(true);
				
				consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/grabarProductoDonacionSalida', params, function(respuesta) {
					
					if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					}else{
						$('#div_det_productos').modal('hide');
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoDonacion(true);					
							addSuccessMessage(null, respuesta.mensajeRespuesta);					
						}
						frm_det_productos.data('bootstrapValidator').resetForm();
					}
					
				});
			}else{
				addWarnMessage(null, "Producto ya agregado.");
			}
			
		}
		
	});
	
	$('#sel_cat_producto').change(function() {
		var idCategoria = $(this).val();		
		if (!esnulo(idCategoria)) {					
			cargarProductos(idCategoria, null);
		} else {
			$('#sel_producto').html('');
			//frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_no_producto');
		}
	});
	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();	
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
				$('#txt_peso_unitario').val(arr[2]);
				$('#txt_peso_bruto').val(arr[3]);
				$('#hid_donacion_pro').val(arr[4]);
				$('#txt_precio').val(arr[5]);
				$('#txt_stock').val(arr[6]);
				
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_peso_unitario').val('');
				$('#txt_peso_bruto').val('');
				$('#hid_donacion_pro').val('');
				$('#txt_precio').val('');
				$('#txt_stock').val('');
			}	
		} else {
			$('#txt_uni_medida').val('');
			$('#txt_peso_unitario').val('');
			$('#txt_peso_bruto').val('');
			$('#hid_donacion_pro').val('');
			$('#txt_precio').val('');
			$('#txt_stock').val('');
		}
	});
	
	function cargarProductos(idCategoria, codigoProducto) {
		var params = { 
			idCategoria : idCategoria
		};			
		loadding(true);
		consultarAjax('GET', '/donacionesSalida/registro-donacionesSalida/listarProductosXCategoria', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				options += '<option value="">Seleccione</option>';
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.idProducto+'_'+item.unidadMedida+'_'+item.pesoNetoUnitario+'_'+item.pesoBrutoUnitario+'_'+item.idDonacion+'_'+item.precioUnitario+'_'+item.cantidad+'">'+item.nombreProducto+'</option>';
		        });
		        $('#sel_producto').html(options);
		        if (codigoProducto != null) {
		        	$('#sel_producto').val(codigoProducto);
		        	var arr = $('#sel_producto').val().split('_');
					if (arr.length > 1) {
						$('#txt_uni_medida').val(arr[1]);
						$('#txt_peso_unitario').val(arr[2]);
						$('#txt_peso_bruto').val(arr[3]);
						$('#hid_donacion_pro').val(arr[4]);
						$('#txt_precio').val(arr[5]);
						$('#txt_stock').val(arr[6]);

					} else {
						$('#txt_uni_medida').val('');
						$('#txt_peso_unitario').val('');
						$('#txt_peso_bruto').val('');
						$('#hid_donacion_pro').val('');
						$('#txt_precio').val('');
						$('#txt_stock').val('');
					}        	
		        } else {
		        	$('#txt_uni_medida').val('');
					$('#txt_peso_unitario').val('');
					$('#txt_peso_bruto').val('');
					$('#hid_donacion_pro').val('');
					$('#txt_precio').val('');
		        	frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
		        }
			}
			loadding(false);		
		});
	}
	
	$('#sel_ddi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarDatosDdiDestino(codigo, null, null);
		} else {
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
		}
	});
	
	$('#sel_gore').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarDatosRegionalDestino(codigo, null, null);
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
			cargarDatosLocalDestino(codigo, null, null);
		} else {
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
		}
	});
	
	$('#sel_cod_donacion').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_tipo_donacion').val(arr[1]);
				$('#txt_donante').val(arr[2]);
				$('#txt_representante').val(arr[3]);
			} else {
				$('#txt_tipo_donacion').val('');
				$('#txt_donante').val('');
				$('#txt_representante').val('');
			}			
		} else {
			$('#txt_tipo_donacion').val('');
			$('#txt_donante').val('');
			$('#txt_representante').val('');
		}
	});
	
	$('#sel_med_transporte').change(function() {
		var codigo = $(this).val();		
		medTransporteDon = codigo;
		frm_dat_generales.data('bootstrapValidator').resetForm();
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		if (!esnulo(codigo)) {		
			
			if(codigo=='1' || codigo=='2' || codigo=='3' || codigo=='4'){
				$('#sel_emp_transporte').prop('disabled', false);
				$('#sel_chofer').prop('disabled', false);
				$('#txt_nro_placa').prop('disabled', false);
				var params = { 
					icodigoParam2 : codigo
				};			
				loadding(true);
				consultarAjax('GET', '/gestion-almacenes/orden-ingreso/listarEmpresaTransporte', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						var options = '<option value="">Seleccione</option>';
				        $.each(respuesta, function(i, item) {
				            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
				        });
				        $('#sel_emp_transporte').html(options);
					}
					loadding(false);
					frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
					
					consultarAjax('GET', '/gestion-almacenes/control-calidad/listarChofer', {icodigo : $('#sel_emp_transporte').val()}, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							var options = '<option value="">Seleccione</option>';
					        $.each(respuesta, function(i, item) {
					            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
					        });
					        $('#sel_chofer').html(options);
						}
						frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
					});
					
				});
			}else{
				$('#sel_emp_transporte').val('');
				$('#sel_chofer').val('');
				$('#txt_nro_placa').val('');
				$('#sel_emp_transporte').prop('disabled', true);
				$('#sel_chofer').prop('disabled', true);
				$('#txt_nro_placa').prop('disabled', true);
				
			}
		} else {
			$('#sel_emp_transporte').html('');
		}
		
		frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
		frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
		bootstrapValidator.validate();
	});
	
	$('#sel_emp_transporte').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			var params = { 
				icodigo : codigo
			};			
			loadding(true);
			consultarAjax('GET', '/gestion-almacenes/control-calidad/listarChofer', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '<option value="">Seleccione</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_chofer').html(options);
				}
				loadding(false);
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			});
		} else {
			$('#sel_chofer').html('');
		}
	});
	
	$('#sel_donante').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_representante').val(arr[1]);
			} else {
				$('#txt_representante').val('');
			}			
		} else {
			$('#txt_representante').val('');
		}
	});
	
	$('#sel_oficina').change(function() {
		var codigo = $(this).val();		
		var ddi = $(txt_codDdi).val();
		if (!esnulo(codigo)) {						
			var params = { 
				icodigo : codigo,
				icodigoParam2 : ddi
			};			
			loadding(true);
			consultarAjax('GET', '/donaciones/registro-donaciones/listarPersonalOficina', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '<option value="">Seleccione</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_personal_oficina').html(options);
				}
				loadding(false);
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_personal_oficina');
			});
		} else {
			$('#sel_personal_oficina').html('');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_personal_oficina');
		}
	});

	
	$('#sel_cat_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
				$('#txt_precio').val(arr[2]);
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_precio').val('');
			}			
		} else {
			$('#txt_uni_medida').val('');
			$('#txt_precio').val('');
		}
	});
	
	$('#sel_almacen').change(function() {
		var codigo = $(this).val();		
		var ddi = $(txt_codDdi).val();
		if (!esnulo(codigo)) {						
			var params = { 
				icodigo : codigo
			};			
			loadding(true);
			consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarIngresoSalidas', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '<option value="">Seleccione</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_salida').html(options);
				}
				loadding(false);
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_salida');
			});
		} else {
			$('#sel_salida').html('');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_salida');
		}
	});
	
	$('#txt_cantidad').change(function() {	
		var cantidad =  $(this).val();
		var pre_unitario = $('#txt_precio').val();
		
		if(cantidad!='0'){
			if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
				var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
				$('#txt_imp_total').val(formatMontoAll(imp_total));
			} else {
				$('#txt_imp_total').val('');
			}
		}else{
			addWarnMessage(null, 'La cantidad no debe ser 0.');
	    	$('#txt_cantidad').val('');
	    	$('#'+$(this).attr('id')).focus();
		}		
		frm_det_productos.bootstrapValidator('revalidateField', 'txt_cantidad');
	});
	
	$('#txt_precio').change(function() {	
		var cantidad =  $('#txt_cantidad').val();
		var pre_unitario = $(this).val();
		
		if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
			var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
			$('#txt_imp_total').val(formatMontoAll(imp_total));
		} else {
			$('#txt_imp_total').val('');
		}
		frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	});
	
	var cantidad = $(this).val();
	var pre_unitario = $('#txt_pre_unitario').val();
	if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
		var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
		$('#txt_imp_total').val(formatMontoAll(imp_total));
	} else {
		$('#txt_imp_total').val('');
	}
	
});

function inicializarDatos() {
		
	$('#li_reg_donaciones_salidas').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_salidas').css('display', 'block');
	$('#li_reg_donaciones_salidas').attr('class', 'active');
	$('#li_reg_donaciones_salidas').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_cod_salida').val(donaciones.codSalida);
		$('#txt_nro_salida').val(donaciones.nroOrdenSalida);
		$('#txt_cod_id').val(donaciones.codigoDonacion);
		$('#txt_anio').val(donaciones.codigoAnio);
		$('#txt_ddi').val(donaciones.nombreDdi);
		$('#txt_codDdi').val(donaciones.codigoDdi);
		$('#txt_idDdi').val(donaciones.idDdi);
		$('#txt_fecha').val(donaciones.fechaEmision);
		//$('#txt_dee').val(donaciones.nombreDdi);


		if (!esnulo(donaciones.idSalida)) {
			$('#hid_id_salida').val(donaciones.idSalida);	
			
			$('#txt_cod_salida').val(donaciones.nroOrden);
			$('#txt_nro_salida').val(donaciones.nroOrdenSalida);

			////////////////////////////////////////////
			
//			$('#sel_oficina').val(donaciones.idEstado);
			$('#sel_estado').val(donaciones.idEstado);
			$('#sel_movimiento').val(donaciones.idTipoMovimiento);
			
			$('#sel_solicitada').val(donaciones.idSolicitante);
			$('#sel_responsable').val(donaciones.idResponsable);
			
			if (!esnulo(donaciones.idDdiDestino)) {
				$('#sel_ddi').val(donaciones.idDdiDestino);
			}
			
			$('input[name=rb_tie_ate_gobierno][value="'+donaciones.flagTipoDestino+'"]').prop('checked', true);
			
			$('#sel_med_transporte').val(donaciones.idMedTransporte);
			//$('#sel_emp_transporte').val(donaciones.idEmpresaTrans);
			$('#txt_fec_entrega').val(donaciones.fechaEntrega);
			//$('#sel_chofer').val(donaciones.idChofer);
			$('#txt_nro_placa').val(donaciones.nroPlaca);
			medTransporteDon = donaciones.idMedTransporte;
			if(medTransporteDon=='1' || medTransporteDon=='2' || medTransporteDon=='3' || medTransporteDon=='4'){
				$('#sel_emp_transporte').val(donaciones.idEmpresaTrans);
				$('#sel_chofer').val(donaciones.idChofer);
				$('#sel_emp_transporte').prop('disabled', false);
				$('#sel_chofer').prop('disabled', false);
				$('#txt_nro_placa').prop('disabled', false);
			}else{
				$('#sel_emp_transporte').val('');
				$('#sel_chofer').val('');
				$('#txt_nro_placa').val('');
				$('#sel_emp_transporte').prop('disabled', true);
				$('#sel_chofer').prop('disabled', true);
				$('#txt_nro_placa').prop('disabled', true);
			}
			$('#txt_observaciones').val(donaciones.observacion.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			
			cargarTipoMovimiento(donaciones.idTipoMovimiento);
			
			if (donaciones.flagTipoDestino == 'I') {
				cargarDatosDdiDestino(donaciones.idDdiDestino, donaciones.idAlmacenDestino, donaciones.idResponsableRecepcion);
			} else if (donaciones.flagTipoDestino == 'R') {
				cargarTipoAtencion(donaciones.flagTipoDestino);
				if (!esnulo(donaciones.codRegion)) {
					$('#sel_gore').val(donaciones.codRegion);
					cargarDatosRegionalDestino(donaciones.codRegion, donaciones.idAlmacenDestinoExt, donaciones.idResponsableExt);
				}
			} else if (donaciones.flagTipoDestino == 'L') {
				cargarTipoAtencion(donaciones.flagTipoDestino);
				if (!esnulo(donaciones.codigoUbigeo)) {
					$('#sel_departamento').val(donaciones.codDepartamento);
					cargarProvincia(donaciones.codDepartamento, donaciones.codProvincia);
					cargarDistrito(donaciones.codProvincia, donaciones.codigoUbigeo);
					cargarDatosLocalDestino(donaciones.codigoUbigeo, donaciones.idAlmacenDestinoExt, donaciones.idResponsableExt);
				}
			}
			if (!esnulo(donaciones.idMedTransporte)) {
				listarEmpresaChofer(donaciones.idMedTransporte,donaciones.idEmpresaTrans,donaciones.idChofer);
			}
			$('#txt_fec_entrega').val(donaciones.fechaEntrega);
			
//			$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
//			$('#sel_control_calidad').val(donaciones.idControlCalidad);
//			$('#sel_almacen').val(donaciones.idAlmacenProcedencia);
//			$('#sel_chofer').val(donaciones.idChofer);
//			$('#txt_fec_llegada').val(donaciones.fechaLlegada);
//			$('#txt_nro_placa').val(donaciones.nroPlaca);
//			$('#sel_responsable').val(donaciones.idResponsable);
//			$('#sel_med_transporte').val(donaciones.idMedTransporte);
//			$('#sel_salida').val(donaciones.idSalida);
			
//			var tipMov = donaciones.idTipoMovimiento;
//			
//			//////////PARA TIP MOV
//			//donaciones
//			if (tipMov == '11' || tipMov == '12' || tipMov == '13') {
//				$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
//				$('#txt_tipo_donacion').val(donaciones.nombreTipoDonacion);
//				$('#txt_donante').val(donaciones.nombreDonante);
//				$('#txt_representante').val(donaciones.representante);
//				$('#sel_control_calidad').val(donaciones.idControlCalidad);
//				$('#sel_med_transporte').val(donaciones.idMedTransporte);
//				listarEmpresaChofer(donaciones.idMedTransporte,donaciones.idEmpresaTrans,donaciones.idChofer);
//				$('#sel_responsable').val(donaciones.idResponsable);
//				$('#txt_fec_llegada').val(donaciones.fechaLlegada);
//				$('#txt_nro_placa').val(donaciones.nroPlaca);
//				
//				//TRANSFERENCIA INTERNA, TRANSFERENCIA ENTRE ALMACENES
//			} else if (tipMov == '4' || tipMov == '3') {
//				$('#sel_control_calidad').val(donaciones.idControlCalidad);
//				$('#sel_almacen').val(donaciones.idAlmacenProcedencia);
//				listarOpcionSalida(donaciones.idAlmacenProcedencia, donaciones.idSalida);
//				
//				$('#sel_med_transporte').val(donaciones.idMedTransporte);
//				listarEmpresaChofer(donaciones.idMedTransporte,donaciones.idEmpresaTrans,donaciones.idChofer);
//				$('#sel_responsable').val(donaciones.idResponsable);
//				$('#txt_fec_llegada').val(donaciones.fechaLlegada);
//				$('#txt_nro_placa').val(donaciones.nroPlaca);
//				
//				//INVENTARIO INICIAL
//			} else if (tipMov == '7') {
//				console.log(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
//				$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
//				$('#txt_tipo_donacion').val(donaciones.nombreTipoDonacion);
//				$('#txt_donante').val(donaciones.nombreDonante);
//				$('#txt_representante').val(donaciones.representante);
//
//				//AJUSTES POR INVENTARIO, AJUSTES POR IMPORTE 
//			} else if (tipMov == '1' || tipMov == '9') {
//				
//
//
//			}
//			
//			////////////////////
//
//			
//			
//			$('#txt_observaciones').val(donaciones.observacion);
//			////////////////////////////////////////0//
			
			listarProductoDonacion(false);
			listarDocumentoDonacion(false);
			
			//cargarTipoMovimiento(tipMov, true);
			
//			if (controlCalidad.flagTipoBien == '1') {
//				$('#li_no_alimentarios').addClass('disabled');
//				$('#li_no_alimentarios').closest('li').children('a').removeAttr('data-toggle');
//			} else {
//				$('#li_alimentarios').addClass('disabled');
//				$('#li_alimentarios').closest('li').children('a').removeAttr('data-toggle');
//			}
			
//			$('#txt_fecha').val(donaciones.fechaEmision);
//			$('#sel_estado').val(donaciones.idEstado);
//			$('#sel_nro_ord_compra').val(donaciones.nroOrdenCompra);
//			$('#sel_tip_control').val(donaciones.idTipoControl);
//			$('#sel_ori_almacen').val(donaciones.idAlmacenOrigen);
//			$('#sel_ori_en_almacen').val(donaciones.idEncargado);
//			$('#sel_inspector').val(donaciones.idInspector);			
//			var val_idProveedor = donaciones.provRep;
//			$('#sel_proveedor').val(val_idProveedor);
//			var arr = val_idProveedor.split('_');
//			if (arr.length > 1) {
//				$('#txt_representante').val(arr[1]);
//			}
//			$('#sel_emp_transporte').val(controlCalidad.idEmpresaTransporte);
//			$('#sel_chofer').val(controlCalidad.idChofer);
//			$('#txt_nro_placa').val(controlCalidad.nroPlaca);
//			$('input[name=rb_tip_bien][value="'+controlCalidad.flagTipoBien+'"]').prop('checked', true);
//			$('#txt_conclusiones').val(controlCalidad.conclusiones);
//			$('#txt_recomendaciones').val(controlCalidad.recomendaciones);
			
			$('input[name=rb_tip_bien]').prop('disabled', true);
			
//			listarProductoControlCalidad(false);
//			
//			listarDocumentoControlCalidad(false);
			
		} else {
			
			
			
			$('#li_documentos').addClass('disabled');
			$('#li_productos').addClass('disabled');
			$('#ul_man_con_calidad li.disabled a').removeAttr('data-toggle');
			
			$('#txt_fecha').datepicker('setDate', new Date());
			
			var fecha = $('#txt_fecha').val();
			if (!esnulo(fecha)) {
				var mes = fecha.substring(3, 5);
			    var anio = fecha.substring(6, 10);	
			    if (mes != donaciones.mes || anio != donaciones.anio) {
			    	$('#hid_val_fec_trabajo').val('0');
			    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
			    	$('#'+$(this).attr('id')).focus();
			    } else {
			    	$('#hid_val_fec_trabajo').val('1');
			    }
			}
			
			var val_donante = $('#sel_donante').val();		
			if (!esnulo(val_donante)) {
				var arr = val_donante.split('_');
				if (arr.length > 1) {
					$('#txt_representante').val(arr[1]);
				} else {
					$('#txt_representante').val('');
				}			
			}
			
			
			var val_donante = $('#sel_cod_donacion').val();		
			if (!esnulo(val_donante)) {
				var arr = val_donante.split('_');
				if (arr.length > 1) {
					$('#txt_tipo_donacion').val(arr[1]);
					$('#txt_donante').val(arr[2]);
					$('#txt_representante').val(arr[3]);
					
				} else {
					$('#txt_tipo_donacion').val('');
					$('#txt_donante').val('');
					$('#txt_representante').val('');
				}			
			}
			cargarTipoMovimiento($('#sel_movimiento').val());
 
			
	//		listarDetalleDocumentos(new Object());

		}
		$('.btn_retornar').click(function(e) {
			e.preventDefault();

			loadding(true);					
			var url = VAR_CONTEXT + '/donacionesSalida/registro-donacionesSalida/inicio';
			$(location).attr('href', url);
			
		});
		
		
		$('#sel_nro_ord_compra').select2();

	}
	
	
	$('#txt_sub_archivo').change(function(e) {
		e.preventDefault();
	    var file_name = $(this).val();
	    var file_read = file_name.split('\\').pop();
	    $('#txt_lee_sub_archivo').val($.trim(file_read).replace(/ /g, '_'));
	    if (esnulo($('#hid_cod_documento').val())) {
	    	$('#hid_cod_ind_alfresco').val('1'); // Nuevo registro
	    } else {
	    	$('#hid_cod_ind_alfresco').val('2'); // Registro modificado
	    }
	    console.log("ALFRESCO: "+$('#hid_cod_ind_alfresco').val());
	    frm_det_documentos.bootstrapValidator('revalidateField', 'txt_lee_sub_archivo');	    
	});
	
	tbl_det_documentos.on('click', '.btn_exp_doc', function(e) {
		e.preventDefault();
		
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		if (!esnulo(id) && !esnulo(name)) {
			descargarDocumento(id, name);
		} else {
			addInfoMessage(null, mensajeValidacionDocumento);
		}
		
	});

}

function descargarDocumento(codigo, nombre) {	
	loadding(true);
	var url = VAR_CONTEXT + '/common/archivo/exportarArchivo/'+codigo+'/'+nombre+'/';	
	$.fileDownload(url).done(function(respuesta) {
		loadding(false);	
		if (respuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, mensajeReporteError);
		} else {
			addInfoMessage(null, mensajeReporteExito);
		}		
	}).fail(function (respuesta) {
		loadding(false);
		addErrorMessage(null, mensajeReporteError);
	});	
}

function listarEstadosDonacion(respuesta) {

	tbl_det_estados.dataTable().fnDestroy();
	
	tbl_det_estados.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDonacion',
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
			data : 'idDetalleControlCalidad',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidad'
		}, {
			data : 'cantidadLote'
		}, {
			data : 'fechaVencimiento'
		}, {
			data : 'cantidadLote'
		}, {
			data : 'cantidadMuestra'
		}, {
			data : 'valorPrimario'
		}, {
			data : 'valorSecundario'
		}, {
			data : 'valorOlor'
		}, {
			data : 'valorColor'
		}, {
			data : 'valorTextura'
		}, {
			data : 'valorSabor'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaAlimentariosCache = respuesta;

}

function listarProductoDonacion(indicador) {
	var params = { 
		idSalida : $('#hid_id_salida').val()
	};			
	consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarProductoDonacion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleProductos(respuesta);
			if (indicador) {
				loadding(false);
			}
			if (respuesta != null && respuesta.length > 0) {
				$('#sel_tip_movimiento').prop('disabled', true);
			} else {
				$('#sel_tip_movimiento').prop('disabled', false);
			}
		}
	});
}

function listarDetalleProductos(respuesta) {

	tbl_det_productos.dataTable().fnDestroy();
	
	tbl_det_productos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idSalidaDet',
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
			data : 'idSalidaDet',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreProducto'
		}, {
			data : 'unidadMedida'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'precioUnitario',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 6)
		}, {
			data : 'importeTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'pesoBrutoTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'codDonacion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaProductosCache = respuesta;

}

function cargarProducto(idCategoria, codigoProducto) {
	var params = { 
		idProducto : idCategoria,
		idCategoria : idCategoria
	};			
	loadding(true);
	consultarAjax('GET', '/donacionesSalida/registro-donacionesSalida/listarProductosXCategoria', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			console.log("CODIGO: "+codigoProducto);
			var options = '<option value="">Seleccione</option>';
	        $.each(respuesta, function(i, item) {
	        	options += '<option value="'+item.idProducto+'_'+item.unidadMedida+'_'+item.pesoNetoUnitario+'_'+item.pesoBrutoUnitario+'_'+item.idDonacion+'_'+item.precioUnitario+'">'+item.nombreProducto+'</option>';
	        });
	        $('#sel_producto').html(options);
	        if (codigoProducto != null) {
	        	$('#sel_producto').val(codigoProducto);
	        	$('#sel_producto').prop('disabled', true);
//				cargarLote(codigoProducto, codigoLote);				
	        } else {
//	        	var arr = $('#sel_lis_producto').val().split('_');
//				if (arr.length > 1) {
//					$('#txt_uni_medida').val(arr[1]);
//					
//				} else {
//					$('#txt_uni_medida').val('');
//	
//				}
				frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	        }
	        //$('#sel_producto').select2();
//			$('#sel_producto').select2({
//				  dropdownParent: $('#div_pro_det_productos')
//			});
		}
		loadding(false);		
	});
}

function cargarProductoEdi(idSalidaDet, codigoProducto) {
	var params = { 
			idSalidaDet : idSalidaDet,
	};			
	loadding(true);
	consultarAjax('GET', '/donacionesSalida/registro-donacionesSalida/listarProductosXEditar', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			console.log("CODIGO: "+codigoProducto);
			var cantidad = null;
			var stock = null;
			var precio = null;
			var idDona = null;
			var options = '';
	        $.each(respuesta, function(i, item) {
	        	options += '<option value="'+item.idProducto+'_'+item.unidadMedida+'_'+item.pesoNetoUnitario+'_'+item.pesoBrutoUnitario+'">'+item.nombreProducto+'</option>';
	        	cantidad = item.cantidad;
	        	stock = item.stock;
	        	precio = item.precioUnitario;
	        	idDona = item.idDonacion;
	        });
	        $('#sel_producto').html(options);
	        if (codigoProducto != null) {
//	        	$('#sel_producto').val(codigoProducto);
	        	$('#txt_cantidad').val(cantidad);
	        	$('#txt_precio').val(precio);
	        	$('#txt_stock').val(stock);
	        	$('#hid_donacion_pro').val(idDona);
	        	
	        	$('#sel_producto').prop('disabled', true);
//				cargarLote(codigoProducto, codigoLote);				
	        } else {
//	        	var arr = $('#sel_lis_producto').val().split('_');
//				if (arr.length > 1) {
//					$('#txt_uni_medida').val(arr[1]);
//					
//				} else {
//					$('#txt_uni_medida').val('');
//	
//				}
				frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	        }
	        //$('#sel_producto').select2();
//			$('#sel_producto').select2({
//				  dropdownParent: $('#div_pro_det_productos')
//			});
		}
		loadding(false);		
	});
}

function listarDocumentoDonacion(indicador) {
	var params = { 
		idSalida : $('#hid_id_salida').val()
	};			

	consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarDocumentoDonacionSalida', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleDocumentos(respuesta);
			if (indicador) {
				loadding(false);
			}
			if (respuesta != null && respuesta.length > 0) {
				$('#sel_tip_movimiento').prop('disabled', true);
			} else {
				$('#sel_tip_movimiento').prop('disabled', false);
			}
		}
	});
}

function listarDetalleDocumentos(respuesta) {

	tbl_det_documentos.dataTable().fnDestroy();
	
	tbl_det_documentos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDocumentoSalida',
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
			data : 'idTipoDocumento',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreDocumento'
		}, {
			data : 'nroDocumento',
				render: function(data, type, row) {
					if (data != null) {
						return '<button type="button" id="'+row.codigoArchivoAlfresco+'" name="'+row.nombreArchivo+'"'+ 
							   'class="btn btn-link input-sm btn_exp_doc">'+data+'</button>';
					} else {
						return '';	
					}											
				}
		}, {
			data : 'fechaDocumento'
		}, {
			data : 'nombreArchivo'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaDocumentosCache = respuesta;

}

$('#btn_gra_documento').click(function(e) {
	e.preventDefault();
	
	var bootstrapValidator = frm_det_documentos.data('bootstrapValidator');
	bootstrapValidator.validate();
	if (bootstrapValidator.isValid()) {			
		loadding(true);
		
		var params = { 
			idDocumentoSalida : $('#hid_cod_documento').val(),
			idSalida: $('#hid_id_salida').val(),
			idTipoDocumento : $('#sel_tipo_documento').val(),
			nroDocumento : $('#txt_nro_documento').val(),
			fechaDocumento : $('#txt_doc_fecha').val(),
			nombreArchivo : $('#txt_lee_sub_archivo').val(),
			observacion : $('#txt_descripcion_doc').val()
		};
		
		var cod_ind_alfresco = $('#hid_cod_ind_alfresco').val();
		if (cod_ind_alfresco == '1' || cod_ind_alfresco == '2') { // Archivo cargado
			var file_name = $('#txt_sub_archivo').val();
			var file_data = null;
			if (!esnulo(file_name) && typeof file_name !== 'undefined') {
				file_data = $('#txt_sub_archivo').prop('files')[0];
			}
			
			var formData = new FormData();
			formData.append('file_doc', file_data);
			// Carpeta contenedor, ubicado en config.properties
	    	formData.append('uploadDirectory', 'params.alfresco.uploadDirectory.donaciones');
	    	
			consultarAjaxFile('POST', '/common/archivo/cargarArchivo', formData, function(resArchivo) {
				if (resArchivo == NOTIFICACION_ERROR) {
					$('#div_det_documentos').modal('hide');
					frm_det_documentos.data('bootstrapValidator').resetForm();
					loadding(false);
					addErrorMessage(null, mensajeCargaArchivoError);						
				} else {
					
					params.codigoArchivoAlfresco = resArchivo;
					grabarDetalleDocumento(params);					
				}
			});
			
		} else { // Archivo no cargado
			
			params.codAlfresco = $('#hid_cod_act_alfresco').val();

			grabarDetalleDocumento(params);				
		}
	}
	
});

function grabarDetalleDocumento(params) {
	consultarAjax('POST', '/donacionesSalida/registro-donacionesSalida/grabarDocumentoDonacionSalida', params, function(respuesta) {
		$('#div_det_documentos').modal('hide');
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			loadding(false);			
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDocumentoDonacion(true);
			addSuccessMessage(null, respuesta.mensajeRespuesta);	
		}
		frm_det_documentos.data('bootstrapValidator').resetForm();
	});
}


function listarEmpresaChofer(idMedio, idEmpresa, idChofer ){
	var codigo = idMedio;
	if (!esnulo(codigo)) {						
		var params = { 
			icodigoParam2 : codigo
		};			
		//loadding(true);
		consultarAjax('GET', '/gestion-almacenes/orden-ingreso/listarEmpresaTransporte', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
		        });
		        $('#sel_emp_transporte').html(options);
		        $('#sel_emp_transporte').val(idEmpresa);
			}
			loadding(false);
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
			
			consultarAjax('GET', '/gestion-almacenes/control-calidad/listarChofer', {icodigo : $('#sel_emp_transporte').val()}, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_chofer').html(options);
			        $('#sel_chofer').val(idChofer);
				}
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			});
			
		});
	} else {
		$('#sel_emp_transporte').html('');
	}
}

function listarOpcionSalida(idAlmacenProcedencia, idsalida ){
	var codigo = idAlmacenProcedencia;
	if (!esnulo(codigo)) {						
		var params = { 
			icodigo : codigo
		};			
		loadding(true);
		consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarIngresoSalidas', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '<option value="">Seleccione</option>';
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
		        });
		        $('#sel_salida').html(options);
		        $('#sel_salida').val(idsalida);
			}
			loadding(false);
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_salida');
		});
	} else {
		$('#sel_salida').html('');
		frm_dat_generales.bootstrapValidator('revalidateField', 'sel_salida');
	}
}

//function cargarTipoMovimiento(val_tip_movimiento, indicador) {
//	
//	$('#div_det_nro_ord_compra').hide();
//	
//	$('#txt_det_ord_compra').val('');
//	//DONACIONES
//	if (val_tip_movimiento == '11' || val_tip_movimiento == '12' || val_tip_movimiento == '13') {
//		
//		$('#sel_cod_donacion').prop('disabled', false);
//		$('#txt_tipo_donacion').prop('disabled', false);
//		$('#txt_donante').prop('disabled', false);
//		$('#txt_representante').prop('disabled', false);
//		
//		$('#sel_control_calidad').prop('disabled', false);
//		
//		$('#sel_almacen').prop('disabled', true);
//		$('#sel_salida').prop('disabled', true);
//		
//		$('#sel_med_transporte').prop('disabled', false);
//		$('#sel_emp_transporte').prop('disabled', false);
//		$('#txt_fec_llegada').prop('disabled', false);
//		$('#sel_chofer').prop('disabled', false);
//		$('#txt_nro_placa').prop('disabled', false);
//		
//		$('#sel_responsable').prop('disabled', false);
//		
//		
//		//TRANSFERENCIA INTERNA, TRANSFERENCIA ENTRE ALMACENES
//	} else if (val_tip_movimiento == '4' || val_tip_movimiento == '3') {
//		$('#sel_cod_donacion').prop('disabled', true);
//		$('#txt_tipo_donacion').prop('disabled', true);
//		$('#txt_tipo_donacion').prop('readonly', true);
//		$('#txt_donante').prop('disabled', true);
//		$('#txt_donante').prop('readonly', true);
//		$('#txt_representante').prop('disabled', true);
//		$('#txt_representante').prop('readonly', true);
//		
//		$('#sel_control_calidad').prop('disabled', false);
//		
//		$('#sel_almacen').prop('disabled', false);
//		$('#sel_salida').prop('disabled', false);
//		
//		$('#sel_med_transporte').prop('disabled', false);
//		$('#sel_emp_transporte').prop('disabled', false);
//		$('#txt_fec_llegada').prop('disabled', false);
//		$('#sel_chofer').prop('disabled', false);
//		$('#txt_nro_placa').prop('disabled', false);
//		
//		$('#sel_responsable').prop('disabled', false);
//		
//		//INVENTARIO INICIAL
//	} else if (val_tip_movimiento == '7') {
//		$('#sel_cod_donacion').prop('disabled', false);
//		$('#txt_tipo_donacion').prop('disabled', false);
//		$('#txt_donante').prop('disabled', false);
//		$('#txt_representante').prop('disabled', false);
//		
//		$('#sel_control_calidad').prop('disabled', true);
//		
//		$('#sel_almacen').prop('disabled', true);
//		$('#sel_salida').prop('disabled', true);
//		
//		$('#sel_med_transporte').prop('disabled', true);
//		$('#sel_emp_transporte').prop('disabled', true);
//		$('#txt_fec_llegada').prop('disabled', true);
//		if (!$('#txt_fec_llegada').hasClass('mod-readonly')) {
//			$('#txt_fec_llegada').addClass('mod-readonly');
//		}
//		$('#sel_chofer').prop('disabled', true);
//		$('#txt_nro_placa').prop('disabled', true);
//		
//		$('#sel_responsable').prop('disabled', true);
//		
//		//AJUSTES POR INVENTARIO, AJUSTES POR IMPORTE 
//	} else if (val_tip_movimiento == '1' || val_tip_movimiento == '9') {
//		
//		$('#sel_cod_donacion').prop('disabled', true);
//		$('#txt_tipo_donacion').prop('disabled', true);
//		$('#txt_donante').prop('disabled', true);
//		$('#txt_representante').prop('disabled', true);
//		
//		$('#sel_control_calidad').prop('disabled', true);
//		
//		$('#sel_almacen').prop('disabled', true);
//		$('#sel_salida').prop('disabled', true);
//		
//		$('#sel_med_transporte').prop('disabled', true);
//		$('#sel_emp_transporte').prop('disabled', true);
//		$('#txt_fec_llegada').prop('disabled', true);
//		if (!$('#txt_fec_llegada').hasClass('mod-readonly')) {
//			$('#txt_fec_llegada').addClass('mod-readonly');
//		}
//		$('#sel_chofer').prop('disabled', true);
//		$('#txt_nro_placa').prop('disabled', true);
//		
//		$('#sel_responsable').prop('disabled', true);
//
//	}
//	$('#txt_tipo_donacion').prop('readonly', true);
//	$('#txt_donante').prop('readonly', true);
//	$('#txt_representante').prop('readonly', true);
//}

function cargarTipoMovimiento(val_tip_movimiento) {
	if (val_tip_movimiento == '3') { // Transferencia entre Almacenes 
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();
		
		$('#sel_ddi').prop('disabled', false);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', false);	
		$('#sel_alm_destino').prop('disabled', false);
		$('#sel_res_recepcion').prop('disabled', false);
		
		$('#sel_med_transporte').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#txt_fec_entrega').prop('disabled', false);
		if ($('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').removeClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', false);
		
	} else if (val_tip_movimiento == '4') { // Transferencia Interna
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', false);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', false);	
		$('#sel_alm_destino').prop('disabled', false);
		$('#sel_res_recepcion').prop('disabled', false);
		
		$('#sel_med_transporte').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#txt_fec_entrega').prop('disabled', false);
		if ($('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').removeClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', false);
		
	} else if (val_tip_movimiento == '5') { // Atención de Emergencias 
		$('#div_ddi_destino').hide();
		$('#div_gob_destino').show();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', false);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', false);	
		$('#sel_alm_destino').prop('disabled', false);
		$('#sel_res_recepcion').prop('disabled', false);
		
		$('#sel_med_transporte').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#txt_fec_entrega').prop('disabled', false);
		if ($('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').removeClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', false);		
		
	} else if (val_tip_movimiento == '1') { // Ajustes por inventario 
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', true);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', true);	
		$('#sel_alm_destino').prop('disabled', true);
		$('#sel_res_recepcion').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#txt_fec_entrega').prop('disabled', true);
		if (!$('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
		
	} else if (val_tip_movimiento == '9') { // Ajuste por Importe 
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', true);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', true);	
		$('#sel_alm_destino').prop('disabled', true);
		$('#sel_res_recepcion').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#txt_fec_entrega').prop('disabled', true);
		if (!$('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
		
	} else if (val_tip_movimiento == '2') { // Baja de Bienes 
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', true);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', true);	
		$('#sel_alm_destino').prop('disabled', true);
		$('#sel_res_recepcion').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#txt_fec_entrega').prop('disabled', true);
		if (!$('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
		
	} else if (val_tip_movimiento == '10') { // Muestras
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		$('#div_destino_general').show();

		$('#sel_ddi').prop('disabled', true);
		$('input[name=rb_tie_ate_gobierno]').prop('disabled', true);	
		$('#sel_alm_destino').prop('disabled', true);
		$('#sel_res_recepcion').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#txt_fec_entrega').prop('disabled', true);
		if (!$('#txt_fec_entrega').hasClass('mod-readonly')) {
			$('#txt_fec_entrega').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
	}else if(val_tip_movimiento == ''){
		$('#div_destino_general').hide();
		$('#div_ddi_destino').hide();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		
	}
}

function cargarTipoAtencion(valor) {

	if (valor == 'R') {
		$('#div_gore_destino').show();
		$('#div_ubi_destino').hide();
	} else if (valor == 'L') {
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').show();
	}
	
}

function cargarDatosDdiDestino(codigo, codigoAlmacen, codigoResponsable) {
	var params = { 
		icodigo : codigo
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarAlmacenDestino', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_alm_destino').html(options);
			if (codigoAlmacen != null) {
	        	$('#sel_alm_destino').val(codigoAlmacen);       	
	        }
		}
		loadding(false);
		
		consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarResponsableRecepcion', {icodigo : codigo}, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				$.each(respuesta, function(i, item) {
					options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
				});
				$('#sel_res_recepcion').html(options);
				if (codigoResponsable != null) {
					$('#sel_res_recepcion').val(codigoResponsable);       	
				}
			}
		});
		
	});
}

function cargarDatosRegionalDestino(codigo, codigoAlmacenExtRegion, codigoPersonalExtRegion) {
	var params = { 
		icodigo : codigo
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarAlmacenExtRegion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_alm_destino').html(options);
			if (codigoAlmacenExtRegion != null && codigoAlmacenExtRegion != 0) {
	        	$('#sel_alm_destino').val(codigoAlmacenExtRegion);       	
	        } else {
	        	frm_dat_generales.bootstrapValidator('revalidateField', 'sel_alm_destino');
	        }
			
		}
		loadding(false);
		
		consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarPersonalExtRegion', {icodigo : codigo}, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				$.each(respuesta, function(i, item) {
					options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
				});
				$('#sel_res_recepcion').html(options);
				if (codigoPersonalExtRegion != null && codigoPersonalExtRegion != 0) {
					$('#sel_res_recepcion').val(codigoPersonalExtRegion);       	
				} else {
					frm_dat_generales.bootstrapValidator('revalidateField', 'sel_res_recepcion');
				}
			}
		});				
	});
}

function cargarProvincia(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/donacionesSalida/registro-donacionesSalida/listarProvincia', params, function(respuesta) {
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
	consultarAjax('GET', '/donacionesSalida/registro-donacionesSalida/listarDistrito', params, function(respuesta) {
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

function cargarDatosLocalDestino(codigo, codigoAlmacenExtLocal, codigoPersonalExtLocal) {
	var params = { 
		vcodigo : codigo
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarAlmacenExtLocal', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_alm_destino').html(options);
			if (codigoAlmacenExtLocal != null && codigoAlmacenExtLocal != 0) {
	        	$('#sel_alm_destino').val(codigoAlmacenExtLocal);       	
	        } else {
	        	frm_dat_generales.bootstrapValidator('revalidateField', 'sel_alm_destino');
	        }
		}
		loadding(false);
		
		consultarAjaxSincrono('GET', '/donacionesSalida/registro-donacionesSalida/listarPersonalExtLocal', {vcodigo : codigo}, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				$.each(respuesta, function(i, item) {
					options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
				});
				$('#sel_res_recepcion').html(options);
				if (codigoPersonalExtLocal != null && codigoPersonalExtLocal != 0) {
					$('#sel_res_recepcion').val(codigoPersonalExtLocal);       	
				} else {
					frm_dat_generales.bootstrapValidator('revalidateField', 'sel_res_recepcion');
				}
				
			}
		});				
	});
}

function limpiarFormularioProducto() {
	$('#sel_cat_producto').val('');
	$('#sel_producto').val('');
	$('#txt_uni_medida').val('');
	$('#txt_cantidad').val('');
	$('#txt_precio').val('');
	$('#txt_imp_total').val('');
	
	$('#txt_peso_unitario').val('');
	$('#txt_peso_bruto').val('');
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}