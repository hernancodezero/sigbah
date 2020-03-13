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
	$('#txt_fec_llegada').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		var fechaRegistro = $('#txt_fecha').val();
		if (!esnulo(fecha)) {
			
		    if (comparafecha(fecha, fechaRegistro)=='1') {
		    	addWarnMessage(null, 'La fecha de llegada debe ser menor o igual a la fecha de registro.');
		    	$('#txt_fec_llegada').val('');
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
//	$('#txt_fecha').datepicker().on('changeDate', function(e) {
//		e.preventDefault();
//		var fecha = $(this).val();
//		var fecha2 = $('#txt_fec_llegada').val();
//		if (!esnulo(fecha)) {
//			console.log(comparafecha(fecha2, fecha));
//		    if (comparafecha(fecha2, fecha)=='1') {
//		    	addWarnMessage(null, 'La fecha de llegada debe ser menor o igual a la fecha de registro.');
//		    	$('#txt_fecha').val('');
//		    	$('#'+$(this).attr('id')).focus();
//		    } else {
//		    	
//		    }
//		}
//		frm_dat_generales.bootstrapValidator('revalidateField', 'txt_fecha');	
//	});
	
	$('#txt_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		var fecha2 = $('#txt_fec_llegada').val();
		if (!esnulo(fecha)) {
			var mes = fecha.substring(3, 5);
		    var anio = fecha.substring(6, 10);	
		    if (mes != donaciones.mes || anio != donaciones.anio) {
		    	$('#hid_val_fec_trabajo').val('0');
		    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
		    	$('#txt_fecha').val('');
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	
		    	if (comparafecha(fecha2, fecha)=='1') {
			    	addWarnMessage(null, 'La fecha de llegada debe ser menor o igual a la fecha de registro.');
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
			
			cargarTipoMovimiento(val_tip_movimiento, true,"");
			
			$('#sel_cod_donacion').val('');
			$('#txt_tipo_donacion').val('');
			$('#txt_donante').val('');
			$('#txt_representante').val('');
			$('#sel_control_calidad').val('0');
			$('#sel_almacen').val('');
			//$('#sel_med_transporte').val('');
			$('#sel_emp_transporte').val('');
			$('#txt_fec_llegada').val('');
			$('#sel_chofer').val('');
			$('#txt_nro_placa').val('');
			$('#sel_responsable').val('');
			
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_cod_donacion');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_tipo_donacion');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_donante');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_representante');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_control_calidad');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_almacen');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_med_transporte');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_fec_llegada');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_nro_placa');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_responsable');

		}
	});
	
	$('#sel_anio').change(function() {
		var val_anio = $(this).val();		
		if (!esnulo($('#sel_movimiento').val())) {
			
			frm_dat_generales.data('bootstrapValidator').resetForm();
						
			listarCodigoDonacion($('#sel_movimiento').val(), val_anio, $('#sel_ddi').val(),"");
		}
	});
	
	$('#sel_ddi').change(function() {
		var val_ddi = $(this).val();		
		if (!esnulo($('#sel_movimiento').val())) {
			
			frm_dat_generales.data('bootstrapValidator').resetForm();
						
			listarCodigoDonacion($('#sel_movimiento').val(), $('#sel_anio').val(), val_ddi,"");
		}
	});
	
	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		if ($('#hid_val_fec_trabajo').val() == '0') {
	    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
	    	return;
		}
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			var codigo = $('#hid_id_ingreso').val();
		//	var tipoBien = $('input[name="rb_tip_bien"]:checked').val();
			var idDonante = null;
			var val_donante = $('#sel_donante').val();
			if (!esnulo(val_donante)) {
				var arr = val_donante.split('_');
				idDonante = arr[0];
			}
			
			var idDonacion = null;
			var val_donacion = $('#sel_cod_donacion').val();
			if (!esnulo(val_donacion)) {
				var arr = val_donacion.split('_');
				idDonacion = arr[0];
			}
			
			var idControlCalidad = null;
			var flagControlCalidad = null;
			var selecControlCalidad = $('#sel_control_calidad').val();
			if(selecControlCalidad==='0'){
				idControlCalidad='';
				flagControlCalidad='0';
			}else{
				idControlCalidad=$('#sel_control_calidad').val();
				flagControlCalidad='1';
			}
			
			var idIngreso = $('#hid_id_ingreso').val();
			var nroOrdenIn = null;
			if(idIngreso == "" || idIngreso==0){
				nroOrdenIn='';
			}else{
				nroOrdenIn=$('#txt_nro_ingreso').val();
			}

			
			var params = {
					idIngreso : $('#hid_id_ingreso').val(),
					codigoAnio : $('#txt_anio').val(),
					//codigoMes : $('#hid_id_donacion').val(),
					fechaEmision : $('#txt_fecha').val(),
					idDonacion : idDonacion, 
					idMedTransporte: $('#sel_med_transporte').val(),
					idTipoMovimiento : $('#sel_movimiento').val(),
					//codAlmacen :  $('#sel_oficina').val(), 
					//idAlmacen :  $('#sel_tip_persona').val(), 
					idAlmacenProcedencia : $('#sel_almacen').val(), 
					codigoDdi : $('#txt_codDdi').val(),
					nroOrdenIngreso :  nroOrdenIn, 
					idControlCalidad :  idControlCalidad, 
					idChofer :  $('#sel_chofer').val(), 
					nroPlaca :  $('#txt_nro_placa').val(), 
					observacion   :  $('#txt_observaciones').val(), 
					idEstado   :  $('#sel_estado').val(), 
					flagControlCalidad : flagControlCalidad,
					idEmpresaTrans : $('#sel_emp_transporte').val(),
					idResponsable : $('#sel_responsable').val(),
					fechaLlegada : $('#txt_fec_llegada').val(),
					idSalida : $('#sel_salida').val()

			};
			
			loadding(true);
			
			consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/grabarDonacionesIngreso', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					$('#hid_id_donacion').val(respuesta.idDonacion);
					if (!esnulo(codigo)) {
						
						addSuccessMessage(null, respuesta.mensajeRespuesta);
						
					} else {
						
						$('#hid_id_donacion').val(respuesta.idDonacion);
						$('#hid_id_ingreso').val(respuesta.idIngreso);
						$('#txt_nro_ingreso').val(respuesta.nroOrdenIngreso);
						
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
						cargarProductosAlGrabar(respuesta.idDonacion);
						addSuccessMessage(null, 'Se genero el N° de Ingreso: '+respuesta.codIngreso);
						
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
			$('#hid_cod_documento').val(obj.idDocumentoIngreso);			
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
				var idDocumentoDonacion = listaDocumentosCache[index].idDocumentoIngreso;
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
						idIngreso : $('#hid_id_ingreso').val()
					};
			
					consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/eliminarDocumentoDonacionIngreso', params, function(respuesta) {
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
//						idIngreso : $('#hid_id_ingreso').val()
//					};
//			
//					consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/eliminarDocumentoDonacionIngreso', params, function(respuesta) {
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
		$('#sel_cat_producto').prop('disabled', false);
		var idDonacion = null;
		var val_donacion = $('#sel_cod_donacion').val();
		if (!esnulo(val_donacion)) {
			var arr = val_donacion.split('_');
			idDonacion = arr[0];
		}
		cargarProductosAlGrabar(idDonacion);
		$('#div_det_productos').modal('show');
		
	});
	
	$('#href_pro_editar').click(function(e) {
		
		e.preventDefault();
		var bootstrapValidator = frm_det_productos.data('bootstrapValidator');

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
			//limpiarFormularioProducto();
			
			$('#hid_cod_producto').val(obj.idIngresoDet);
			
			$('#sel_cat_producto').val(obj.idProducto+'_'+obj.unidadMedida+'_'+parseFloat(obj.precioUnitario)+'_'+obj.fecVencimiento);
			
			$('#sel_cat_producto').prop('disabled', true);	
			cargarProducto(obj.idCategoria, obj.idProducto+'_'+obj.unidadMedida+'_'+obj.precioUnitario+'_'+obj.fecVencimiento, obj.nombreProducto);
			verCantidad($('#sel_movimiento').val(), obj.idProducto);
			$('#txt_uni_medida').val(obj.unidadMedida);
			$('#txt_fec_vencimiento').val(obj.fecVencimiento);
			$('#txt_cantidad').val(obj.cantidad);
			$('#sel_monedas').val(obj.idMoneda);
			$('#txt_imp_origen').val(obj.monOrigen);
			$('#txt_imp_soles').val(obj.monSoles);
			$('#txt_precio').val(obj.precioUnitario);
			$('#txt_imp_total').val(obj.importeTotal);
			
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
				var idDetalleIngreso = listaProductosCache[index].idIngresoDet;
				codigo = codigo + idDetalleIngreso + '_';
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
						idIngresoDet : codigo
					};
			
					consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/eliminarProductoDonacion', params, function(respuesta) {
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
//						idIngresoDet : codigo
//					};
//			
//					consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/eliminarProductoDonacion', params, function(respuesta) {
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
			var val_producto = $('#sel_cat_producto').val();
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
				idIngreso : $('#hid_id_ingreso').val(),	
				idIngresoDet : $('#hid_cod_producto').val(),
				idProducto : idProducto,
				//idDonacion : $('#hid_id_donacion').val(),
				cantidad : $('#txt_cantidad').val(),
				precioUnitario : $('#txt_precio').val(),
				importeTotal : $('#txt_imp_total').val(),
				fecVencimiento : $('#txt_fec_vencimiento').val(),
				idDonacion : $('#hid_id_donacion').val()
				//monOrigen : formatMonto($('#txt_imp_total').val())
			};

			loadding(true);
			
			consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/grabarProductoDonacionIngreso', params, function(respuesta) {
				$('#div_det_productos').modal('hide');
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProductoDonacion(true);					
					addSuccessMessage(null, respuesta.mensajeRespuesta);					
				}
				frm_det_productos.data('bootstrapValidator').resetForm();
			});
			}else{
				addWarnMessage(null, "Producto ya agregado.");
			}
			
		}
		
	});
	
//	$('#sel_cat_producto').change(function() {
//		var idCategoria = $(this).val();		
//		if (!esnulo(idCategoria)) {					
//			cargarProductos(idCategoria, null);
//		} else {
//			$('#sel_no_producto').html('');
//			frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_no_producto');
//		}
//	});
//	
//	function cargarProductos(idCategoria, codigoProducto) {
//		var params = { 
//			idCategoria : idCategoria
//		};			
//		loadding(true);
//		consultarAjax('GET', '/donaciones/registro-donaciones/listarProductosXCategoria', params, function(respuesta) {
//			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//				addErrorMessage(null, respuesta.mensajeRespuesta);
//			} else {
//				var options = '';
//		        $.each(respuesta, function(i, item) {
//		            options += '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'">'+item.nombreProducto+'</option>';
//		        });
//		        $('#sel_producto').html(options);
////		        if (codigoProducto != null) {
////		        	$('#sel_producto').val(codigoProducto);
////					$('#sel_producto').select2();
////					$('#sel_producto').select2({
////						  dropdownParent: $('#frm_det_productos')
////					});	        	
////		        } else {
////		        	frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
////		        }
//			}
//			loadding(false);		
//		});
//	}
	
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

	$('#sel_salida').change(function() {
		
		var codigo = $(this).val();		
		cargarDatosTransporte(codigo);
		
	});
	
	$('#sel_med_transporte').change(function() {
		
		var codigo = $(this).val();		
		medTransporteDon = codigo;
		cargarEmpresaChofer(codigo);
		
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
					var options = '';
					options += '<option value="">Seleccione</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_chofer').html(options);
				}
				loadding(false);
				//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
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
				verCantidad($('#sel_movimiento').val(), arr[0]);
				$('#txt_uni_medida').val(arr[1]);
				$('#txt_precio').val(arr[2]);
				$('#txt_fec_vencimiento').val(arr[3]);
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_precio').val('');
				$('#txt_fec_vencimiento').val('');
			}			
		} else {
			$('#txt_uni_medida').val('');
			$('#txt_precio').val('');
			$('#txt_fec_vencimiento').val('');
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
		
		if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
			var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
			$('#txt_imp_total').val(formatMontoAll(imp_total));
		} else {
			$('#txt_imp_total').val('');
		}
//		frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
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
		
  	$('#li_reg_donaciones_ingresos').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#li_reg_orden_ingresos').attr('class', 'active');
	$('#li_reg_orden_ingresos').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_cod_ingreso').val(donaciones.codIngreso);
		$('#txt_nro_ingreso').val(donaciones.nroOrdenIngreso);
		$('#txt_cod_id').val(donaciones.codigoDonacion);
		$('#txt_anio').val(donaciones.codigoAnio);
		$('#txt_ddi').val(donaciones.nombreDdi);
		$('#txt_codDdi').val(donaciones.codigoDdi);
		$('#txt_idDdi').val(donaciones.idDdi);
		$('#txt_fecha').val(donaciones.fechaEmision);
		//$('#txt_dee').val(donaciones.nombreDdi);
		
		if (!esnulo(donaciones.idIngreso)) {
			
			$('#hid_id_ingreso').val(donaciones.idIngreso);	
			$('#txt_cod_ingreso').val(donaciones.textoCodigo);
			
			
			$('#hid_id_donacion').val(donaciones.idDonacion);	
			
//			$('#txt_fecha').val(donaciones.fechaEmision);
//			$('#txt_tipo_donacion').val(donaciones.nombreTipoDonacion);
//			$('#txt_donante').val(donaciones.nombreDonante);
//			$('#txt_representante').val(donaciones.representante);
//
//			$('#txt_estado_donacion').val(donaciones.textoCodigo);
//
//			$('#sel_dee').val(donaciones.idDee);
//			$('#sel_tip_donacion').val(donaciones.tipoDonante);
//			$('#sel_ori_donacion').val(donaciones.tipoOrigenDonante);
//			$('#sel_ori_pais').val(donaciones.idPaisDonante);
//			$('#sel_tip_persona').val(donaciones.idDonante);
//			$('#sel_donante').val(donaciones.idDonante);
//			$('#sel_oficina').val(donaciones.idOficina);
//			$('#sel_personal_oficina').val(donaciones.idPersonal);
			
			////////////////////////////////////////////
			
//			$('#sel_oficina').val(donaciones.idEstado);
			$('#sel_movimiento').val(donaciones.idTipoMovimiento);
//			$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
//			$('#sel_control_calidad').val(donaciones.idControlCalidad);
//			$('#sel_almacen').val(donaciones.idAlmacenProcedencia);
//			$('#sel_chofer').val(donaciones.idChofer);
//			$('#txt_fec_llegada').val(donaciones.fechaLlegada);
//			$('#txt_nro_placa').val(donaciones.nroPlaca);
//			$('#sel_responsable').val(donaciones.idResponsable);
//			$('#sel_med_transporte').val(donaciones.idMedTransporte);
//			$('#sel_salida').val(donaciones.idSalida);
			
			var tipMov = donaciones.idTipoMovimiento;
			medTransporteDon = donaciones.idMedTransporte;
			//////////PARA TIP MOV
			//donaciones
			if (tipMov == '11' || tipMov == '12' || tipMov == '13') {
				$('#sel_anio').val(donaciones.anioAceptacion);
				$('#sel_ddi').val(donaciones.idDdiAceptacion);
				$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
				$('#txt_tipo_donacion').val(donaciones.nombreTipoDonacion);
				$('#txt_donante').val(donaciones.nombreDonante);
				$('#txt_representante').val(donaciones.representante);
				$('#sel_control_calidad').val(donaciones.idControlCalidad);
				$('#sel_med_transporte').val(donaciones.idMedTransporte);
				listarEmpresaChofer(donaciones.idMedTransporte,donaciones.idEmpresaTrans,donaciones.idChofer);
				$('#sel_responsable').val(donaciones.idResponsable);
				$('#txt_fec_llegada').val(donaciones.fechaLlegada);
				$('#txt_nro_placa').val(donaciones.nroPlaca);
				 
				mostrarTransporte(true);
				//TRANSFERENCIA INTERNA, TRANSFERENCIA ENTRE ALMACENES
			} else if (tipMov == '4' || tipMov == '3') {
				$('#sel_control_calidad').val(donaciones.idControlCalidad);
				$('#sel_almacen').val(donaciones.idAlmacenProcedencia);
				listarOpcionSalida(donaciones.idAlmacenProcedencia, donaciones.idSalida);
				console.log("MEDIO "+donaciones.idMedTransporte);
				$('#sel_med_transporte').val(donaciones.idMedTransporte);
				listarEmpresaChofer(donaciones.idMedTransporte,donaciones.idEmpresaTrans,donaciones.idChofer);
				$('#sel_responsable').val(donaciones.idResponsable);
				$('#txt_fec_llegada').val(donaciones.fechaLlegada);
				$('#txt_nro_placa').val(donaciones.nroPlaca);
				mostrarTransporte(true);
				if(tipMov == '3'){
					$('#sel_med_transporte').prop('disabled', false);
				}
				//INVENTARIO INICIAL
			} else if (tipMov == '7') {
				
				$('#sel_cod_donacion').val(donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
				$('#txt_tipo_donacion').val(donaciones.nombreTipoDonacion);
				$('#txt_donante').val(donaciones.nombreDonante);
				$('#txt_representante').val(donaciones.representante);
				mostrarTransporte(false);
				//AJUSTES POR INVENTARIO, AJUSTES POR IMPORTE 
			} else if (tipMov == '1' || tipMov == '9') {
				mostrarTransporte(false);


			}
			
			////////////////////

			
			
			$('#txt_observaciones').val(donaciones.observacion.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			////////////////////////////////////////0//
			
			listarProductoDonacion(false);
			listarDocumentoDonacion(false);
			//listarEstadosDonacion(new Object());
			cargarTipoMovimiento(tipMov, true,donaciones.idDonacion+"_"+donaciones.nombreTipoDonacion+"_"+donaciones.nombreDonante+"_"+donaciones.representante);
			
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
			mostrarTransporte(true);
			cargarEmpresaChofer('1');
			$('#sel_anio').val(donaciones.anioAceptacion);
			$('#sel_ddi').val(donaciones.idDdiAceptacion);
			$('#sel_control_calidad').val('0');
			$('#li_productos').addClass('disabled');
			$('#li_documentos').addClass('disabled');
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
			
 
			
	//		listarDetalleDocumentos(new Object());

		}
		$('.btn_retornar').click(function(e) {
			e.preventDefault();

			loadding(true);					
			var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/inicio';
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
		idIngreso : $('#hid_id_ingreso').val()
	};			
	consultarAjaxSincrono('GET', '/donacionesIngreso/registro-donacionesIngreso/listarProductoDonacion', params, function(respuesta) {
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
			data : 'idIngresoDet',
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
			data : 'idIngresoDet',
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
			data : 'fecVencimiento'
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

function cargarProducto(idCategoria, codigoProducto, nombreProducto) {

	var options = '<option value="">Seleccione</option>';
//    $.each(respuesta, function(i, item) {
        options += '<option value="'+codigoProducto+'">'+nombreProducto+'</option>';
//    });
    $('#sel_cat_producto').html(options);
    if (codigoProducto != null) {
    	$('#sel_cat_producto').val(codigoProducto);
//				cargarLote(codigoProducto, codigoLote);				
    } else {
    	
		frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
    }
    $('#sel_cat_producto').select2();
	$('#sel_cat_producto').select2({
		  dropdownParent: $('#div_det_productos')
	});

loadding(false);		
	
}

function listarDocumentoDonacion(indicador) {
	var params = { 
		idIngreso : $('#hid_id_ingreso').val()
	};			
	
	consultarAjaxSincrono('GET', '/donacionesIngreso/registro-donacionesIngreso/listarDocumentoDonacionIngreso', params, function(respuesta) {
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
			data : 'idDocumentoIngreso',
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
			idDocumentoIngreso : $('#hid_cod_documento').val(),
			idIngreso: $('#hid_id_ingreso').val(),
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
	consultarAjax('POST', '/donacionesIngreso/registro-donacionesIngreso/grabarDocumentoDonacionIngreso', params, function(respuesta) {
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
		loadding(true);
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
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
			
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
				//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			});
			
		});
	} else {
		$('#sel_emp_transporte').html('');
	}
}

function listarCodigoDonacion(tipo, anio, ddi,val ){
	
	if (!esnulo(tipo)) {						
		var params = { 
			icodigo : ddi,
			descripcion : anio,
			vcodigoParam2 : tipo
		};			
		loadding(true);
		consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarCodigoDonacion', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '<option value="">Seleccione</option>';
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.idDonacion+'_'+item.tipoDonante+'_'+item.nombreDonante+'_'+item.representante+'">'+item.codigoDonacion+'</option>';
		        });
		        $('#sel_cod_donacion').html(options);
		        $('#txt_tipo_donacion').val('');
				$('#txt_donante').val('');
				$('#txt_representante').val('');
				console.log("VALOR "+val);
				if(!esnulo(val)){
					$('#sel_cod_donacion').val(val);
					
					if (!esnulo(val)) {
						var arr = val.split('_');
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

				}
//		        $('#sel_cod_donacion').val(idEmpresa);
			}
			loadding(false);
			
		});
	} else {
		$('#sel_cod_donacion').html('');
	}
}

function listarEmpresaChoferNuevo(idMedio, idEmpresa, idChofer ){
	var codigo = idMedio;
	if (!esnulo(codigo)) {						
		var params = { 
			icodigoParam2 : codigo
		};			
		loadding(true);
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
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
			
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
				//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
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

function cargarTipoMovimiento(val_tip_movimiento, indicador, val) {
	
	$('#div_det_nro_ord_compra').hide();
	
	$('#txt_det_ord_compra').val('');
	//DONACIONES

	if (val_tip_movimiento == '11' || val_tip_movimiento == '12' || val_tip_movimiento == '13') {
		
		$('#sel_anio').prop('disabled', false);
		$('#sel_ddi').prop('disabled', false);
		
		$('#sel_cod_donacion').prop('disabled', false);
		$('#txt_tipo_donacion').prop('disabled', false);
		$('#txt_donante').prop('disabled', false);
		$('#txt_representante').prop('disabled', false);
		
		$('#sel_control_calidad').prop('disabled', false);
		
		$('#sel_almacen').prop('disabled', true);
		$('#sel_salida').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);//siempre
		$('#sel_med_transporte').val('1');
		$('#sel_emp_transporte').prop('disabled', false);
		$('#txt_fec_llegada').prop('disabled', false);
		if ($('#txt_fec_llegada').hasClass('mod-readonly')) {
			$('#txt_fec_llegada').removeClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		
		$('#sel_responsable').prop('disabled', false);
		listarCodigoDonacion(val_tip_movimiento, $('#sel_anio').val(),$('#sel_ddi').val(),val);
		
		//TRANSFERENCIA INTERNA, TRANSFERENCIA ENTRE ALMACENES
	} else if (val_tip_movimiento == '4' || val_tip_movimiento == '3') {
		
		$('#sel_anio').prop('disabled', true);
		$('#sel_ddi').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', true);
		$('#txt_tipo_donacion').prop('disabled', true);
		$('#txt_tipo_donacion').prop('readonly', true);
		$('#txt_donante').prop('disabled', true);
		$('#txt_donante').prop('readonly', true);
		$('#txt_representante').prop('disabled', true);
		$('#txt_representante').prop('readonly', true);
		
		$('#sel_control_calidad').prop('disabled', false);
		
		$('#sel_almacen').prop('disabled', false);
		$('#sel_salida').prop('disabled', false);
		
		$('#sel_med_transporte').prop('disabled', false);//siempre
//		$('#sel_med_transporte').val('1');
		$('#sel_emp_transporte').prop('disabled', false);
		$('#txt_fec_llegada').prop('disabled', false);
		if ($('#txt_fec_llegada').hasClass('mod-readonly')) {
			$('#txt_fec_llegada').removeClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		
		$('#sel_responsable').prop('disabled', false);
		listarCodigoDonacion(val_tip_movimiento, "0", "0" ,"");
		//INVENTARIO INICIAL
	} else if (val_tip_movimiento == '7') {
		$('#sel_anio').prop('disabled', true);
		$('#sel_ddi').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', false);
		$('#txt_tipo_donacion').prop('disabled', false);
		$('#txt_donante').prop('disabled', false);
		$('#txt_representante').prop('disabled', false);
		
		$('#sel_control_calidad').prop('disabled', true);
		
		$('#sel_almacen').prop('disabled', true);
		$('#sel_salida').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_med_transporte').val('');
		$('#sel_emp_transporte').prop('disabled', true);
		$('#txt_fec_llegada').prop('disabled', true);
		if (!$('#txt_fec_llegada').hasClass('mod-readonly')) {
			$('#txt_fec_llegada').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		
		$('#sel_responsable').prop('disabled', true);
		listarCodigoDonacion(val_tip_movimiento, "0", "0" ,val);
		//AJUSTES POR INVENTARIO, AJUSTES POR IMPORTE 
	} else if (val_tip_movimiento == '1' || val_tip_movimiento == '9') {
		$('#sel_anio').prop('disabled', true);
		$('#sel_ddi').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', true);
		$('#txt_tipo_donacion').prop('disabled', true);
		$('#txt_donante').prop('disabled', true);
		$('#txt_representante').prop('disabled', true);
		
		$('#sel_control_calidad').prop('disabled', true);
		
		$('#sel_almacen').prop('disabled', true);
		$('#sel_salida').prop('disabled', true);
		
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_med_transporte').val('');
		$('#txt_fec_llegada').prop('disabled', true);
		if (!$('#txt_fec_llegada').hasClass('mod-readonly')) {
			$('#txt_fec_llegada').addClass('mod-readonly');
		}
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		
		$('#sel_responsable').prop('disabled', true);
		listarCodigoDonacion(val_tip_movimiento, "0", "0","" );
	}
	$('#txt_tipo_donacion').prop('readonly', true);
	$('#txt_donante').prop('readonly', true);
	$('#txt_representante').prop('readonly', true);
}

function cargarProductosAlGrabar(donacion){
	var donacionid = donacion ; 
	var tipoMovimiento = $('#sel_movimiento').val();
		var params = { 
			idDonacion : donacionid,
			idTipoMovimiento : tipoMovimiento
		};			

		loadding(true);
		consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarProductosAlGrabar', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				options += '<option value="">Seleccione</option>';
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.idProducto+'_'+item.unidadMedida+'_'+item.precio+'_'+item.fecVencimiento+'">'+item.nombreProducto+'</option>';
		        });
		        $('#sel_cat_producto').html(options);
			}
			loadding(false);
			//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
		});	
		
		
}

function mostrarTransporte(indicador){
	if(indicador){
	
		if(medTransporteDon=='1' || medTransporteDon=='2' || medTransporteDon=='3' || medTransporteDon=='4'){
			$('#sel_med_transporte').val(medTransporteDon);
		
		}else{
//			$('#sel_med_transporte').val('1');
			console.log("VALOR2 "+medTransporteDon);
		}
		
		$('#sel_med_transporte').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
	}else{
		$('#sel_med_transporte').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
	}
}

function cargarEmpresaChofer(codigo){
	if(codigo=='1' || codigo=='2' || codigo=='3' || codigo=='4'){
		
		mostrarTransporte(true);
		if (!esnulo(codigo)) {						
			var params = { 
				icodigoParam2 : codigo
			};			
			loadding(true);
			consultarAjax('GET', '/gestion-almacenes/orden-ingreso/listarEmpresaTransporte', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '';
					options += '<option value="">Seleccione</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_emp_transporte').html(options);
				}
				loadding(false);
				//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
				
				consultarAjax('GET', '/gestion-almacenes/control-calidad/listarChofer', {icodigo : $('#sel_emp_transporte').val()}, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						var options = '';
						options += '<option value="">Seleccione</option>';
				        $.each(respuesta, function(i, item) {
				            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
				        });
				        $('#sel_chofer').html(options);
					}
					//frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
				});
				
			});
		}else{
			mostrarTransporte(false);
		}
	} else {
		$('#sel_emp_transporte').html('');
	}
}

function cargarDatosTransporte(codigo){
//	if(codigo=='1'){
		//mostrarTransporte(true);
//		if (!esnulo(codigo)) {						
			var params = { 
				icodigo : $('#sel_salida').val()
			};			
			loadding(true);
			consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarDatosTransporte', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var idMedio = respuesta.icodigo;
					var idEmpresa = respuesta.icodigoParam2;
					var nombreEmpresa = respuesta.icodigoParam2;
					var idChofer = respuesta.icodigoParam3;
					var nombreChofer = respuesta.icodigoParam2;
					var placa = respuesta.descripcion;
					
					$('#sel_med_transporte').val(idMedio);
					listarEmpresaChoferNuevo(idMedio,idEmpresa,idChofer);
					$('#txt_nro_placa').val(placa);
//					mostrarTransporte(true);
//					options += '<option value="">Seleccione</option>';
//			        $.each(respuesta, function(i, item) {
//			            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
//			        });
//			        $('#sel_emp_transporte').html(options);
				}
				
				
			});
//		}else{
//			mostrarTransporte(false);
//		}
//	} else {
//		$('#sel_emp_transporte').html('');
//	}
}

function verCantidad(tipMov, idProducto){
	if (tipMov == '11' || tipMov == '12' || tipMov == '13'){
		var params = { 
				idTipoMovimiento :  tipMov,
				idIngreso :  $('#hid_id_ingreso').val(),
				idProducto : idProducto
			};			
			loadding(true);
			consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/buscarCantidadProducto', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '';
			        $.each(respuesta, function(i, item) {
			            options = item.cantidad;
			        });
			        $('#ver_cantidad').show();
			        $('#txt_cantidad_propuesta').val(options);
			       
				}
				loadding(false);		
			});
	}else{
		$('#txt_cantidad_propuesta').val('');
		$('#ver_cantidad').hide();
	}
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}