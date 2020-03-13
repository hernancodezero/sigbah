var listaAlimentariosCache = new Object();
var listaNoAlimentariosCache = new Object();
var listaDocumentosCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var tbl_det_alimentarios = $('#tbl_det_alimentarios');
var frm_det_alimentarios = $('#frm_det_alimentarios');

var tbl_det_no_alimentarios = $('#tbl_det_no_alimentarios');
var frm_det_no_alimentarios = $('#frm_det_no_alimentarios');

var tbl_det_documentos = $('#tbl_det_documentos');
var frm_det_documentos = $('#frm_det_documentos');

var indicadorAli = '';

$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();
	
	$('#txt_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		if (!esnulo(fecha)) {
			var mes = fecha.substring(3, 5);
		    var anio = fecha.substring(6, 10);		    
		    if (mes != controlCalidad.codigoMes || anio != controlCalidad.codigoAnio) {
		    	$('#hid_val_fec_trabajo').val('0');
		    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	$('#hid_val_fec_trabajo').val('1');
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));
	});
	
	$('#txt_fec_vencimiento').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_alimentarios.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_no_fec_vencimiento').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_no_alimentarios.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_doc_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_documentos.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#sel_estado').change(function() {
		var codigo = $(this).val();
		if (codigo == ESTADO_ANULADO) {
			
			swal({
				  title: 'Está seguro?',
				  text: 'Esta usted seguro de anular ?',
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si',
				  cancelButtonText: 'No',
				}).then(function () {
					grabarDetalle(false);	
				  swal(
					'Anulado!',
					'Se ha anulado satisfactoriamente.',
					'success'
				  )
				}, function (dismiss) {
				  if (dismiss === 'cancel') {
					  $('#sel_estado').val(ESTADO_ACTIVO);
				  }
				});
			
//			$.SmartMessageBox({
//				title : 'Esta usted seguro de anular ?',
//				content : '',
//				buttons : '[NO][SI]'
//			}, function(ButtonPressed) {
//				if (ButtonPressed === 'SI') {				
//					grabarDetalle(false);					
//				}
//				if (ButtonPressed === 'NO') {				
//					$('#sel_estado').val(ESTADO_ACTIVO);				
//				}
//			});
		}
	});
	
	$('#sel_nro_ord_compra').change(function() {
		var codigo = $(this).val();
		var arr = codigo.split('_');
		if (arr.length > 1) {
			$('#txt_det_ord_compra').val(arr[1]);
			$('#hid_id_proveedor').val(arr[2]);
			$('#sel_proveedor').val(arr[3]);
			$('#txt_representante').val(arr[4]);
		} else {
			$('#txt_det_ord_compra').val('');
			$('#hid_id_proveedor').val('');
			$('#sel_proveedor').val('');
			$('#txt_representante').val('');
		}
	});
	
	$('#sel_tip_control').change(function() {
		var val_tip_control = $(this).val();		
		if (!esnulo(val_tip_control)) {
			
			frm_dat_generales.data('bootstrapValidator').resetForm();
			console.log("ENTRO");
			cargarTipoControl(val_tip_control);
			$('#sel_nro_ord_compra').val('');
			 $('#sel_nro_ord_compra').select2();
			$('#sel_ori_almacen').val('');
			$('#sel_ori_en_almacen').val('');
			$('#sel_inspector').val('');
			$('#sel_proveedor').val('');
			$('#hid_id_proveedor').val('');
			$('#txt_representante').val('');
			$('#sel_emp_transporte').val('');
			$('#sel_chofer').val('');
			$('#txt_nro_placa').val('');
			$('#sel_cod_donacion').val('');
			$('#txt_det_ord_compra').val('');
			$('#txt_det_cod_don').val('');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_nro_ord_compra');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_cod_donacion');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_ori_almacen');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_proveedor');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_nro_placa');
			
		}
	});
	
//	$('#sel_proveedor').change(function() {
//		var codigo = $(this).val();		
//		if (!esnulo(codigo)) {
//			var arr = codigo.split('_');
//			if (arr.length > 1) {
//				$('#txt_representante').val(arr[1]);
//			} else {
//				$('#txt_representante').val('');
//			}			
//		} else {
//			$('#txt_representante').val('');
//		}
//	});
	
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
					if (respuesta.length > 0) {
						options = '<option value="">Seleccione</option>';
				        $.each(respuesta, function(i, item) {
				            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
				        });
					}
			        $('#sel_chofer').html(options);
				}
				loadding(false);
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
			});
		} else {
			$('#sel_chofer').html('');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
		}
	});
	
	$('#sel_no_cat_producto').change(function() {
		var idCategoria = $(this).val();		
		if (!esnulo(idCategoria)) {					
//			cargarProductoNoAlimentario(idCategoria, null);
			cargarProductoNoAlimento(idCategoria, null, null);
		} else {
			$('#sel_no_producto').html('');
			frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_no_producto');
		}
	});
	
	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			if ($('#hid_val_fec_trabajo').val() == '0') {
		    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
		    	return;
			}
			grabarDetalle(true);	
		}
		
	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/inicio';
		$(location).attr('href', url);
		
	});
	
	$('#href_ali_nuevo').click(function(e) {
		e.preventDefault();
		indicadorAli='I';
		$('#h4_tit_alimentarios').html('Nuevo Producto');
		frm_det_alimentarios.trigger('reset');
		
//		$('#sel_producto').select2();
//		$('#sel_producto').select2({
//			  dropdownParent: $('#div_pro_det_alimentarios')
//		});
		cargarProductoAlimento('5', null, null);
		
		$('#hid_cod_producto').val('');
		$('#div_det_alimentarios').modal('show');
		
	});
	
	$('#href_ali_editar').click(function(e) {
		e.preventDefault();
		indicadorAli='U';
		var indices = [];
		tbl_det_alimentarios.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_alimentarios.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
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
			frm_det_alimentarios.trigger('reset');
			
			$('#hid_cod_producto').val(obj.idDetalleControlCalidad);
			
//			$('#sel_producto').val(obj.idProducto+'_'+obj.nombreUnidad);
			
//			$('#sel_producto').select2();
//			$('#sel_producto').select2({
//				  dropdownParent: $('#div_pro_det_alimentarios')
//			});
			cargarProductoAlimento('5', obj.idProducto+'_'+obj.nombreUnidad+'_'+obj.cantidadLote, null);
			$('#sel_uni_medida').val(obj.nombreUnidad);
			$('#txt_fec_vencimiento').val(obj.fechaVencimiento);
			$('#txt_can_lote').val(obj.cantidadLote);
			$('#txt_can_muestra').val(obj.cantidadMuestra);
			$('#sel_primario').val(obj.primario);
			$('#sel_olor').val(obj.parOlor);
			$('#sel_textura').val(obj.parTextura);
			$('#sel_secundario').val(obj.secundario);
			$('#sel_color').val(obj.parColor);
			$('#sel_sabor').val(obj.parSabor);
			
			$('#div_det_alimentarios').modal('show');
		}
		
	});
	
	$('#href_ali_eliminar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_det_alimentarios.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_alimentarios.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDetalleControlCalidad = listaAlimentariosCache[index].idDetalleControlCalidad;
				codigo = codigo + idDetalleControlCalidad + '_';
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
						arrIdDetalleControlCalidad : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarProductoControlCalidad', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoControlCalidad(true);
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
//						arrIdDetalleControlCalidad : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarProductoControlCalidad', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarProductoControlCalidad(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);							
//						}
//					});
//					
//				}	
//			});
			
		}
		
	});
	
	$('#btn_gra_alimentario').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_det_alimentarios.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var idProducto = null;
			var val_producto = $('#sel_producto').val();
			if (!esnulo(val_producto)) {
				var arr = val_producto.split('_');
				idProducto = arr[0];
			}			
			var params = { 
				idDetalleControlCalidad : $('#hid_cod_producto').val(),
				idControlCalidad : $('#hid_cod_con_calidad').val(),
				idProducto : idProducto,
				fechaVencimiento : $('#txt_fec_vencimiento').val(),
				cantidadLote : formatMonto($('#txt_can_lote').val()),
				cantidadMuestra : formatMonto($('#txt_can_muestra').val()),
				primario : $('#sel_primario').val(),
				parOlor : $('#sel_olor').val(),
				parTextura : $('#sel_textura').val(),
				secundario : $('#sel_secundario').val(),
				parColor : $('#sel_color').val(),
				parSabor : $('#sel_sabor').val()
			};

			loadding(true);
			
			
			consultarAjax('POST', '/gestion-almacenes/control-calidad/grabarProductoControlCalidad', params, function(respuesta) {
				$('#div_det_alimentarios').modal('hide');
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProductoControlCalidad(true);					
					addSuccessMessage(null, respuesta.mensajeRespuesta);					
				}
				frm_det_alimentarios.data('bootstrapValidator').resetForm();
			});
			
		}
		
	});
	
	$('#btn_can_alimentario, #btn_clo_alimentarios').click(function(e) {
		e.preventDefault();
		frm_det_alimentarios.data('bootstrapValidator').resetForm();
	});
	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
				$('#txt_can_lote').val(arr[2]);
				
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_can_lote').val('');
			}			
		} else {
			$('#txt_uni_medida').val('');
			$('#txt_can_lote').val('');
		}
	});
	
	$('#href_no_ali_nuevo').click(function(e) {
		e.preventDefault();
		indicadorAli='I';
		$('#h4_tit_no_alimentarios').html('Nuevo Producto');
		frm_det_no_alimentarios.trigger('reset');
		var options = '<option value="">Seleccione</option>';

        $('#sel_no_producto').html(options);

		$('#sel_no_producto').select2();
		$('#sel_no_producto').select2({
			  dropdownParent: $('#div_pro_det_no_alimentarios')
		});
		$('#sel_no_cat_producto').val('0');
		cargarProductoNoAlimento($('#sel_no_cat_producto').val(), null, null);
//		cargarProductoNoAlimento($('#sel_no_cat_producto').val(), null, null);
		
		$('#hid_cod_no_producto').val('');
		$('#div_det_no_alimentarios').modal('show');
		
	});
	
	$('#href_no_ali_editar').click(function(e) {
		e.preventDefault();
		indicadorAli='U';
		var indices = [];
		tbl_det_no_alimentarios.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_no_alimentarios.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaNoAlimentariosCache[indices[0]];
			
			$('#h4_tit_no_alimentarios').html('Actualizar Producto');
			frm_det_no_alimentarios.trigger('reset');
			
			$('#hid_cod_no_producto').val(obj.idDetalleControlCalidad);
			
			$('#sel_no_cat_producto').val(obj.idCategoria);
//			cargarProductoNoAlimentario(obj.idCategoria, obj.idProducto+'_'+obj.nombreUnidad);	
			cargarProductoNoAlimento(obj.idCategoria, obj.idProducto+'_'+obj.nombreUnidad+'_'+obj.cantidadLote, null);
			$('#sel_no_uni_medida').val(obj.nombreUnidad);
			$('#txt_no_fec_vencimiento').val(obj.fechaVencimiento);
			$('#txt_no_can_lote').val(obj.cantidadLote);
			$('#txt_no_can_muestra').val(obj.cantidadMuestra);
			$('#sel_no_primario').val(obj.primario);
			$('#sel_no_tecnicas').val(obj.flagEspecTecnicas);
			$('#sel_no_secundario').val(obj.secundario);
			$('#sel_no_conformidad').val(obj.flagConforProducto);
			
			$('#div_det_no_alimentarios').modal('show');
		}
		
	});
	
	$('#href_no_ali_eliminar').click(function(e) {
		e.preventDefault();
		
		var indices = [];
		var codigo = '';
		tbl_det_no_alimentarios.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_no_alimentarios.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDetalleControlCalidad = listaNoAlimentariosCache[index].idDetalleControlCalidad;
				codigo = codigo + idDetalleControlCalidad + '_';
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
						arrIdDetalleControlCalidad : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarProductoControlCalidad', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoControlCalidad(true);
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
//						arrIdDetalleControlCalidad : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarProductoControlCalidad', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarProductoControlCalidad(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);
//						}		
//					});
//					
//				}	
//			});
			
		}
		
	});
	
	$('#btn_gra_no_alimentario').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_det_no_alimentarios.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var idProducto = null;
			var val_producto = $('#sel_no_producto').val();
			if (!esnulo(val_producto)) {
				var arr = val_producto.split('_');
				idProducto = arr[0];
			}
			var params = { 
				idDetalleControlCalidad : $('#hid_cod_no_producto').val(),
				idControlCalidad : $('#hid_cod_con_calidad').val(),
				idProducto : idProducto,
				fechaVencimiento : $('#txt_no_fec_vencimiento').val(),
				cantidadLote : formatMonto($('#txt_no_can_lote').val()),
				cantidadMuestra : formatMonto($('#txt_no_can_muestra').val()),
				primario : $('#sel_no_primario').val(),
				flagEspecTecnicas : $('#sel_no_tecnicas').val(),
				secundario : $('#sel_no_secundario').val(),
				flagConforProducto : $('#sel_no_conformidad').val()
			};
			
			loadding(true);
			
			consultarAjax('POST', '/gestion-almacenes/control-calidad/grabarProductoControlCalidad', params, function(respuesta) {
				$('#div_det_no_alimentarios').modal('hide');
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProductoControlCalidad(true);
					addSuccessMessage(null, respuesta.mensajeRespuesta);	
				}
				frm_det_no_alimentarios.data('bootstrapValidator').resetForm();
			});
			
		}
		
	});
	
	$('#btn_can_no_alimentario, #btn_clo_no_alimentarios').click(function(e) {
		e.preventDefault();
		frm_det_no_alimentarios.data('bootstrapValidator').resetForm();
	});
	
	$('#sel_no_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_no_uni_medida').val(arr[1]);
				$('#txt_no_can_lote').val(arr[2]);
			} else {
				$('#txt_no_uni_medida').val('');
				$('#txt_no_can_lote').val('');
			}			
		} else {
			$('#txt_no_uni_medida').val('');
			$('#txt_no_can_lote').val('');
		}
	});
	
	$('#href_doc_nuevo').click(function(e) {
		e.preventDefault();

		$('#h4_tit_no_alimentarios').html('Nuevo Documento');
		$('#frm_det_documentos').trigger('reset');
		
		$('#hid_cod_documento').val('');
		$('#hid_cod_act_alfresco').val('');
		$('#hid_cod_ind_alfresco').val('');
		$('#fil_sub_archivo').val(null);
		$('#div_det_documentos').modal('show');
		
	});
	
	$('#href_doc_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
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
			
			$('#hid_cod_documento').val(obj.idDocumentoControlCalidad);			
			$('#sel_tip_producto').val(obj.idTipoDocumento);
			$('#txt_nro_documento').val(obj.nroDocumento);
			$('#txt_doc_fecha').val(obj.fechaDocumento);
			$('#hid_cod_act_alfresco').val(obj.codigoArchivoAlfresco);
			$('#hid_cod_ind_alfresco').val('');
			$('#txt_lee_sub_archivo').val(obj.nombreArchivo);
			$('#fil_sub_archivo').val(null);
			
			$('#div_det_documentos').modal('show');
		}
		
	});
	
	$('#href_doc_eliminar').click(function(e) {
		e.preventDefault();
		
		var indices = [];
		var codigo = '';
		tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_documentos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDocumentoControlCalidad = listaDocumentosCache[index].idDocumentoControlCalidad;
				codigo = codigo + idDocumentoControlCalidad + '_';
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
						arrIdDocumentoControlCalidad : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarDocumentoControlCalidad', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarDocumentoControlCalidad(true);
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
//						arrIdDocumentoControlCalidad : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/control-calidad/eliminarDocumentoControlCalidad', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarDocumentoControlCalidad(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);							
//						}
//					});
//					
//				}	
//			});
			
		}
		
	});
	
	$('#btn_gra_documento').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_det_documentos.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {			
			loadding(true);
			
			var params = { 
				idDocumentoControlCalidad : $('#hid_cod_documento').val(),
				idControlCalidad : $('#hid_cod_con_calidad').val(),
				idTipoDocumento : $('#sel_tip_producto').val(),
				nroDocumento : $('#txt_nro_documento').val(),
				fechaDocumento : $('#txt_doc_fecha').val(),
				nombreArchivo : $('#txt_lee_sub_archivo').val()
			};
			
			var cod_ind_alfresco = $('#hid_cod_ind_alfresco').val();
			if (cod_ind_alfresco == '1' || cod_ind_alfresco == '2') { // Archivo cargado
				var file_name = $('#fil_sub_archivo').val();
				var file_data = null;
				if (!esnulo(file_name) && typeof file_name !== 'undefined') {
					file_data = $('#fil_sub_archivo').prop('files')[0];
				}
				
				var formData = new FormData();
				formData.append('file_doc', file_data);
				// Carpeta contenedor, ubicado en config.properties
		    	formData.append('uploadDirectory', 'params.alfresco.uploadDirectory.almacen');
		    	
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
				
				params.codigoArchivoAlfresco = $('#hid_cod_act_alfresco').val();

				grabarDetalleDocumento(params);				
			}
		}
		
	});
	
	$('#btn_can_documento, #btn_clo_documentos').click(function(e) {
		e.preventDefault();
		frm_det_documentos.data('bootstrapValidator').resetForm();
	});
	
	$('#fil_sub_archivo').change(function(e) {
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
	
	$('#href_eli_archivo').click(function(e) {
		e.preventDefault();

		$('#hid_cod_act_alfresco').val('');
		$('#hid_cod_ind_alfresco').val('');
		$('#fil_sub_archivo').val(null);
		$('#txt_lee_sub_archivo').val('');
		
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
	
	$('#sel_cod_donacion').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_det_cod_don').val(arr[1]);
			} else {
				$('#txt_det_cod_don').val('');
			}			
		} else {
			$('#txt_det_cod_don').val('');
		}
	});
	
});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#li_con_calidad').attr('class', 'active');
	$('#li_con_calidad').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_nro_con_calidad').val(controlCalidad.nroControlCalidad);
		$('#txt_anio').val(controlCalidad.codigoAnio);
		$('#txt_ddi').val(controlCalidad.nombreDdi);
		$('#txt_almacen').val(controlCalidad.nombreAlmacen);
		
		if (!esnulo(controlCalidad.idControlCalidad)) {
			
			$('#hid_cod_con_calidad').val(controlCalidad.idControlCalidad);		
			if (controlCalidad.flagTipoBien == '1') {
				$('#li_no_alimentarios').addClass('disabled');
				$('#li_no_alimentarios').closest('li').children('a').removeAttr('data-toggle');
			} else {
				$('#li_alimentarios').addClass('disabled');
				$('#li_alimentarios').closest('li').children('a').removeAttr('data-toggle');
			}
			
			$('#txt_fecha').val(controlCalidad.fechaEmision);
			$('#sel_estado').val(controlCalidad.idEstado);
			console.log(controlCalidad.nroOrdenCompra+'_'+controlCalidad.concepto+'_'+controlCalidad.idProveedor+'_'+controlCalidad.razonSocial+'_'+controlCalidad.representante);
			$('#sel_nro_ord_compra').val(controlCalidad.nroOrdenCompra+'_'+controlCalidad.concepto+'_'+controlCalidad.idProveedor+'_'+controlCalidad.razonSocial+'_'+controlCalidad.representante);
//			console.log("DAT: "+controlCalidad.nroOrdenCompra+'_'+controlCalidad.concepto+'_'+controlCalidad.idProveedor+'_'+controlCalidad.razonSocial+'_'+controlCalidad.representante);
			
			$('#txt_det_ord_compra').val(controlCalidad.concepto);
			
			$('#sel_tip_control').val(controlCalidad.idTipoControl);
			cargarTipoControl(controlCalidad.idTipoControl);
//			console.log("DONA: "+controlCalidad.idDonacion+'_'+controlCalidad.nombreDonante);
			if(controlCalidad.idTipoControl=='5'){
				$('#sel_cod_donacion').val(controlCalidad.idDonacion+'_'+controlCalidad.nombreDonante);
				$('#txt_det_cod_don').val(controlCalidad.nombreDonante);
			}else{
				$('#sel_cod_donacion').val('');
				$('#txt_det_cod_don').val('');
			}
			
			$('#sel_ori_almacen').val(controlCalidad.idAlmacenOrigen);
			$('#sel_ori_en_almacen').val(controlCalidad.idEncargado);
			$('#sel_inspector').val(controlCalidad.idInspector);			
			var val_idProveedor = controlCalidad.provRep;
			$('#sel_proveedor').val(controlCalidad.razonSocial);
			
			$('#txt_representante').val(controlCalidad.representante);
			
			$('#sel_emp_transporte').val(controlCalidad.idEmpresaTransporte);
			$('#sel_chofer').val(controlCalidad.idChofer);
			$('#txt_nro_placa').val(controlCalidad.nroPlaca);
			$('input[name=rb_tip_bien][value="'+controlCalidad.flagTipoBien+'"]').prop('checked', true);
			$('#txt_conclusiones').val(controlCalidad.conclusiones.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			$('#txt_recomendaciones').val(controlCalidad.recomendaciones.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			
			$('input[name=rb_tip_bien]').prop('disabled', true);
			
			
			
			listarProductoControlCalidad(false);
			
			listarDocumentoControlCalidad(false);
			
		} else {
			
			$('#sel_estado').prop('disabled', true);
			
			$('#li_alimentarios').addClass('disabled');
			$('#li_no_alimentarios').addClass('disabled');
			$('#li_documentos').addClass('disabled');
			$('#ul_man_con_calidad li.disabled a').removeAttr('data-toggle');
			
			$('#txt_fecha').datepicker('setDate', new Date());
			
//			var val_proveedor = $('#sel_proveedor').val();		
//			if (!esnulo(val_proveedor)) {
//				var arr = val_proveedor.split('_');
//				if (arr.length > 1) {
//					$('#txt_representante').val(arr[1]);
//				} else {
//					$('#txt_representante').val('');
//				}			
//			}
			
			cargarTipoControl($('#sel_tip_control').val());
			
			listarDetalleAlimentarios(new Object());
			listarDetalleNoAlimentarios(new Object());
			listarDetalleDocumentos(new Object());

		}
		
		$('#sel_nro_ord_compra').select2();

	}
	
}

function listarProductoControlCalidad(indicador) {
	var params = { 
		idControlCalidad : $('#hid_cod_con_calidad').val(),
		flagTipoProducto : $('input[name="rb_tip_bien"]:checked').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/control-calidad/listarProductoControlCalidad', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			if ($('input[name="rb_tip_bien"]:checked').val() == '1') {
				listarDetalleAlimentarios(respuesta);
			} else {
				listarDetalleNoAlimentarios(respuesta);
			}
			if (indicador) {
				loadding(false);
			}
			if (respuesta != null && respuesta.length > 0) {
				$('input[name=rb_tip_bien]').prop('disabled', true);
			} else {
				$('input[name=rb_tip_bien]').prop('disabled', false);
			}
		}
	});
}

function listarDetalleAlimentarios(respuesta) {

	tbl_det_alimentarios.dataTable().fnDestroy();
	
	tbl_det_alimentarios.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDetalleControlCalidad',
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
			data : 'nombreProducto',
			sClass : 'opc-table-20'
		}, {
			data : 'nombreUnidad',
			sClass : 'opc-center'
		}
//		, {
//			data : 'cantidadLote',
//			sClass : 'opc-right'
//		}
		, {
			data : 'fechaVencimiento',
			sClass : 'opc-center'
		}, {
			data : 'cantidadLote',
			sClass : 'opc-right'
		}, {
			data : 'cantidadMuestra',
			sClass : 'opc-right'
		}, {
			data : 'valorPrimario',
			sClass : 'opc-center'
		}, {
			data : 'valorOlor',
			sClass : 'opc-center'
		}, {
			data : 'valorColor',
			sClass : 'opc-center'
		}, {
			data : 'valorTextura',
			sClass : 'opc-center'
		}, {
			data : 'valorSabor',
			sClass : 'opc-center'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : true
	});
	
	setTimeout(function () {
		centerHeader('#th_producto');
		centerHeader('#th_lote');
		centerHeader('#th_can_lote');
		centerHeader('#th_can_muestra');
	}, 500);
	
	listaAlimentariosCache = respuesta;

}

function listarDetalleNoAlimentarios(respuesta) {

	tbl_det_no_alimentarios.dataTable().fnDestroy();
	
	tbl_det_no_alimentarios.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDetalleControlCalidad',
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
			data : 'nombreProducto',
			sClass : 'opc-table-20'
		}, {
			data : 'nombreUnidad',
			sClass : 'opc-center'
		}, {
			data : 'cantidadLote',
			sClass : 'opc-right'
		}, {
			data : 'fechaVencimiento',
			sClass : 'opc-center'
		}, {
			data : 'cantidadLote',
			sClass : 'opc-right'
		}, {
			data : 'cantidadMuestra',
			sClass : 'opc-right'
		}, {
			data : 'valorPrimario',
			sClass : 'opc-center'
		}, {
			data : 'valorEspecTecnicas',
			sClass : 'opc-center'
		}, {
			data : 'valorConforProducto',
			sClass : 'opc-center'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : true
	});
	
	setTimeout(function () {
		centerHeader('#th_no_producto');
		centerHeader('#th_no_lote');
		centerHeader('#th_no_can_lote');
		centerHeader('#th_no_can_muestra');
	}, 500);
	
	listaNoAlimentariosCache = respuesta;

}

function listarDocumentoControlCalidad(indicador) {
	var params = { 
		idControlCalidad : $('#hid_cod_con_calidad').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/control-calidad/listarDocumentoControlCalidad', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleDocumentos(respuesta);
			if (indicador) {
				loadding(false);
			}
		}
	});
}

function listarDetalleDocumentos(respuesta) {

	tbl_det_documentos.dataTable().fnDestroy();
	
	tbl_det_documentos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDocumentoControlCalidad',
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
			data : 'idDocumentoControlCalidad',
			render : function(data, type, full, meta){
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreDocumento'
		}, {
			data : 'nroDocumento',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" id="'+row.codigoArchivoAlfresco+'" name="'+row.nombreArchivo+'"'+ 
						   'class="btn btn-link input-sm btn_exp_doc">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'fechaDocumento',
			sClass : 'opc-center'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : true
	});
	
	setTimeout(function () {
		centerHeader('#th_tip_documento');
	}, 500);
	
	listaDocumentosCache = respuesta;

}

function grabarDetalleDocumento(params) {
	consultarAjax('POST', '/gestion-almacenes/control-calidad/grabarDocumentoControlCalidad', params, function(respuesta) {
		$('#div_det_documentos').modal('hide');
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			loadding(false);			
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDocumentoControlCalidad(true);
			addSuccessMessage(null, respuesta.mensajeRespuesta);	
		}
		frm_det_documentos.data('bootstrapValidator').resetForm();
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

function cargarProductoNoAlimentario(idCategoria, codigoProducto) {
	var params = { 
		idCategoria : idCategoria
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/control-calidad/listarProductoXCategoria', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
	        $.each(respuesta, function(i, item) {
	            options += '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'">'+item.nombreProducto+'</option>';
	        });
	        $('#sel_no_producto').html(options);
	        if (codigoProducto != null) {
	        	$('#sel_no_producto').val(codigoProducto);
				$('#sel_no_producto').select2();
				$('#sel_no_producto').select2({
					  dropdownParent: $('#div_pro_det_no_alimentarios')
				});	        	
	        } else {
	        	frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_no_producto');
	        }
		}
		loadding(false);		
	});
}

function cargarTipoControl(val_tip_control) {
	if (val_tip_control == '1') { // Ingreso por Compra de productos
		$('#sel_nro_ord_compra').prop('disabled', false);
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#sel_cod_donacion').prop('disabled', true);
	} else if (val_tip_control == '2') { // Control Interno
		$('#sel_nro_ord_compra').prop('disabled', true);
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', true);
	} else if (val_tip_control == '3') { // Ingreso por Transferencias de Almacén
		$('#sel_nro_ord_compra').prop('disabled', true);
		$('#sel_ori_almacen').prop('disabled', false);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#sel_cod_donacion').prop('disabled', true);
	} else if (val_tip_control == '4') { // Salida de Productos por Emergencia
		$('#sel_nro_ord_compra').prop('disabled', true);
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', true);
	} else if (val_tip_control == '5') { // Ingreso por Donación
		$('#sel_nro_ord_compra').prop('disabled', true);
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
		$('#sel_cod_donacion').prop('disabled', false);
	} else if (val_tip_control == '6') { // Salidas por Transferencias a Almacén
		$('#sel_nro_ord_compra').prop('disabled', true);
		$('#sel_ori_almacen').prop('disabled', false);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
		$('#sel_cod_donacion').prop('disabled', true);
	}
}

function grabarDetalle(indicador) {
	

	if(!esnulo($('#sel_nro_ord_compra').val())){
			var codigo = $('#sel_nro_ord_compra').val();
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_det_ord_compra').val(arr[1]);
				$('#hid_id_proveedor').val(arr[2]);
				$('#sel_proveedor').val(arr[3]);
				$('#txt_representante').val(arr[4]);
			} else {
				$('#txt_det_ord_compra').val('');
				$('#hid_id_proveedor').val('');
				$('#sel_proveedor').val('');
				$('#txt_representante').val('');
			}
		}
	
	var codigo = $('#hid_cod_con_calidad').val();
	var tipoBien = $('input[name="rb_tip_bien"]:checked').val();
	var idProveedor = null;
	var idProveedor = $('#hid_id_proveedor').val();
	var val_proveedor = $('#sel_proveedor').val();
//	if (!esnulo(val_proveedor)) {
//		var arr = val_proveedor.split('_');
//		idProveedor = arr[0];
//	}
	
	
	
	var val_ord_compra = $('#sel_nro_ord_compra').val();
	var arr_ord_compra = '';
	if (!esnulo(val_ord_compra)) {
		arr_ord_compra = val_ord_compra.split('_');
	}
	
	var idDonacion = null;
	var val_donacion = $('#sel_cod_donacion').val();
	if (!esnulo(val_donacion)) {
		var arr = val_donacion.split('_');
		idDonacion = arr[0];
	}
	
	var params = {
		idControlCalidad : codigo,	
		codigoAnio : $('#txt_anio').val(),
		codigoMes : controlCalidad.codigoMes,
		codigoDdi : controlCalidad.codigoDdi,
		idDdi : controlCalidad.idDdi, 
		idAlmacen : controlCalidad.idAlmacen,
		codigoAlmacen : controlCalidad.codigoAlmacen,
		fechaEmision : $('#txt_fecha').val(),
		idEstado : $('#sel_estado').val(),
		nroOrdenCompra : arr_ord_compra[0],
		idTipoControl : $('#sel_tip_control').val(),
		idAlmacenOrigen : $('#sel_ori_almacen').val(),
		idEncargado : $('#sel_ori_en_almacen').val(),
		idInspector : $('#sel_inspector').val(),
		idProveedor : idProveedor,
		idEmpresaTransporte : $('#sel_emp_transporte').val(),
		idChofer : $('#sel_chofer').val(),
		nroPlaca : $('#txt_nro_placa').val(),
		flagTipoBien : tipoBien,
		conclusiones : $('#txt_conclusiones').val(),
		recomendaciones : $('#txt_recomendaciones').val(),
		idDonacion : idDonacion
	};
	
	loadding(true);
	
	consultarAjax('POST', '/gestion-almacenes/control-calidad/grabarControlCalidad', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			
			if (!esnulo(codigo)) {
				
				if (indicador) {	
					if(respuesta.codigoRespuesta=="03"){
//						addWarnMessage(null, respuesta.mensajeRespuesta);
						swal({
							  title: 'Registre los productos',
  						      html: '<DIV ALIGN=left>'+respuesta.mensajeRespuesta+'</DIV>',

							  type: 'warning',
							  showCancelButton: false,
							  confirmButtonColor: '#3085d6',
							  cancelButtonColor: '#d33',
							  confirmButtonText: 'Aceptar',

							}).then(function () {
								
							}, function (dismiss) {
							 
							});
					}else{
						addSuccessMessage(null, respuesta.mensajeRespuesta);
					}
					
				}
				
			} else {
				
				if(respuesta.codigoRespuesta=="03"){
//					addWarnMessage(null, respuesta.mensajeRespuesta);
					swal({
						  title: 'Registre los productos',
						      html: '<DIV ALIGN=left>'+respuesta.mensajeRespuesta+'</DIV>',

						  type: 'warning',
						  showCancelButton: false,
						  confirmButtonColor: '#3085d6',
						  cancelButtonColor: '#d33',
						  confirmButtonText: 'Aceptar',

						}).then(function () {
							
						}, function (dismiss) {
						 
						});
				}else{
					$('#sel_estado').prop('disabled', false);
					$('#hid_cod_con_calidad').val(respuesta.idControlCalidad);
					$('#txt_nro_con_calidad').val(respuesta.nroControlCalidad);
					
					if (tipoBien == '1') {					
						$('#li_alimentarios').attr('class', '');
						$('#li_alimentarios').closest('li').children('a').attr('data-toggle', 'tab');
					} else {							
						$('#li_no_alimentarios').attr('class', '');
						$('#li_no_alimentarios').closest('li').children('a').attr('data-toggle', 'tab');
					}
					$('#li_documentos').attr('class', '');
					$('#li_documentos').closest('li').children('a').attr('data-toggle', 'tab');

					addSuccessMessage(null, 'Se genero el N° Control de Calidad: '+respuesta.nroControlCalidad);
				}
				
				
				
			}
			
		}
		
		if (indicador) {
			loadding(false);
		} else {
			var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/inicio';
			$(location).attr('href', url);
		}
	});	
}

function cargarProductoAlimento(idCategoria, codigoProducto, codigoLote) {
	
	var val_ord_compra = $('#sel_nro_ord_compra').val();
	var arr_ord_compra = null
	if(!esnulo(val_ord_compra)){
		arr_ord_compra = val_ord_compra.split('_');
	}
	 
	
	var val_ord_compra = $('#sel_nro_ord_compra').val();
	var nroOrdenCompra = null;
	if (!esnulo(val_ord_compra)) {
		var arr_ord_compra = val_ord_compra.split('_');
		nroOrdenCompra = arr_ord_compra[0];
	}
	
	var idDonacion = null;
	var val_donacion = $('#sel_cod_donacion').val();
	if (!esnulo(val_donacion)) {
		var arr = val_donacion.split('_');
		idDonacion = arr[0];
	}
	console.log("IDN "+indicadorAli);
	var params = { 
		tipoOrigen : "I",
		tipoProducto : "A",
		idCategoria : idCategoria,
		idTipoMovimiento :  $('#sel_tip_control').val(),
		idDonacion :  idDonacion,
		ordenCompra : nroOrdenCompra,
		tipoControl : indicadorAli
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/control-calidad/listarProductosXCategoriaControl', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
	        $.each(respuesta, function(i, item) {
	            options += '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'_'+item.cantidad+'">'+item.nombreProducto+'</option>';
	        });
	        $('#sel_producto').html(options);
	        console.log("producto:"+codigoProducto)
	        if (codigoProducto != null) {
	        	console.log("entro "+codigoProducto);
	        	$('#sel_producto').val(codigoProducto);
	        	var arr = $('#sel_producto').val().split('_');
					$('#txt_uni_medida').val(arr[1]);
					$('#txt_can_lote').val(arr[2]);
							
	        } else {
	        	console.log("vacio");
	        	$('#sel_producto').val('');
	        	$('#txt_uni_medida').val('');
	        	$('#txt_can_lote').val('');
	        }
	        $('#sel_producto').select2();
			$('#sel_producto').select2({
				  dropdownParent: $('#div_pro_det_alimentarios')
			});
		}
		loadding(false);		
	});
}

function cargarProductoNoAlimento(idCategoria, codigoProducto, codigoLote) {
	
	var val_ord_compra = $('#sel_nro_ord_compra').val();
	var arr_ord_compra = val_ord_compra.split('_');
	
	var val_ord_compra = $('#sel_nro_ord_compra').val();
	var nroOrdenCompra = null;
	if (!esnulo(val_ord_compra)) {
		var arr_ord_compra = val_ord_compra.split('_');
		nroOrdenCompra = arr_ord_compra[0];
	}
	
	var idDonacion = null;
	var val_donacion = $('#sel_cod_donacion').val();
	if (!esnulo(val_donacion)) {
		var arr = val_donacion.split('_');
		idDonacion = arr[0];
	}
	
	var params = { 
		tipoOrigen : "I",
		tipoProducto : "N",
		idCategoria : idCategoria,
		idTipoMovimiento :  $('#sel_tip_control').val(),
		idDonacion :  idDonacion,
		ordenCompra : nroOrdenCompra,
		tipoControl : indicadorAli
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/control-calidad/listarProductosXCategoriaControl', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
	        $.each(respuesta, function(i, item) {
	            options += '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'_'+item.cantidad+'">'+item.nombreProducto+'</option>';
	        });
	        $('#sel_no_producto').html(options);
	        console.log("producto:"+codigoProducto)
	        if (codigoProducto != null) {
	        	$('#sel_no_producto').val(codigoProducto);
	        	var arr = $('#sel_no_producto').val().split('_');
					$('#txt_no_uni_medida').val(arr[1]);
					$('#txt_no_can_lote').val(arr[2]);
					
							
	        } else {
	        	console.log("vacio");
	        	$('#sel_no_producto').val('');
	        	$('#txt_no_uni_medida').val('');
	        	$('#txt_no_can_lote').val('');
	        }
	        $('#sel_no_producto').select2();
			$('#sel_no_producto').select2({
				  dropdownParent: $('#div_det_no_alimentarios')
			});
		}
		loadding(false);		
	});
}