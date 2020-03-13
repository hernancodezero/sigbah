var listaProductosCache = new Object();
var listaVehiculosCache = new Object();
var listaDocumentosCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var tbl_det_productos = $('#tbl_det_productos');
var frm_det_productos = $('#frm_det_productos');

var tbl_det_vehiculos = $('#tbl_det_vehiculos');
var frm_det_pro_vehicular = $('#frm_det_pro_vehicular');

var tbl_det_documentos = $('#tbl_det_documentos');
var frm_det_documentos = $('#frm_det_documentos');

var ind_nuevo=0;
var ind_sel_pro='';

$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#txt_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		var fecha = $(this).val();
		if (!esnulo(fecha)) {
			var mes = fecha.substring(3, 5);
		    var anio = fecha.substring(6, 10);		    
		    if (mes != proyectoManifiesto.codigoMes || anio != proyectoManifiesto.codigoAnio) {
		    	$('#hid_val_fec_trabajo').val('0');
		    	addWarnMessage(null, 'Está ingresando una fecha con año diferente al mes de cierre.');
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	$('#hid_val_fec_trabajo').val('1');
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_doc_fecha').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_det_documentos.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('input[type=radio][name=rb_man_tie_programacion]').change(function() {
		cargarIndicadorProgramacion(this.value);
    });
	
	$('#sel_nro_programacion').change(function() {
		var codigo = $(this).val();
		var idProyectoManifiesto = $('#hid_cod_proyecto').val();

		if (!esnulo(codigo) && !esnulo(idProyectoManifiesto)) {	
			
			if(ind_nuevo==0){
				console.log("NUEVO");
			}else{
				var params = {
						idProyectoManifiesto : idProyectoManifiesto
					};
					consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/verificarProductosProgramacion', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							var indicador = parseInt(respuesta);
							if (indicador > 0) {	
								
								swal({
									  title: 'Está seguro?',
									  text: 'El sistema eliminará los productos ingresados.<br>Está usted seguro ?',
									  type: 'warning',
									  showCancelButton: true,
									  confirmButtonColor: '#3085d6',
									  cancelButtonColor: '#d33',
									  confirmButtonText: 'Si',
									  cancelButtonText: 'No',
									}).then(function () {
										loadding(true);								
										var params_proy = { 
											idProyectoManifiesto : idProyectoManifiesto,
											idProgramacion : codigo
										};						
										consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/procesarProyectoManifiesto', params_proy, function(respuesta) {
											if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
												loadding(false);
												addErrorMessage(null, respuesta.mensajeRespuesta);
											} else {
												listarProductoProyectoManifiesto(true);
												addSuccessMessage(null, respuesta.mensajeRespuesta);							
											}
										});	
									  swal(
										'Eliminado!',
										'Se ha eliminado satisfactoriamente.',
										'success'
									  )
									}, function (dismiss) {
									  if (dismiss === 'cancel') {
										  $('#sel_nro_programacion').val(ind_sel_pro);
									  }
									});
								
								
//								$.SmartMessageBox({
//									title : 'El sistema eliminará los productos ingresados.<br>Está usted seguro ?',
//									content : '',
//									buttons : '[NO][SI]'
//								}, function(ButtonPressed) {
//									if (ButtonPressed === 'SI') {				
//										loadding(true);								
//										var params_proy = { 
//											idProyectoManifiesto : idProyectoManifiesto,
//											idProgramacion : codigo
//										};						
//										consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/procesarProyectoManifiesto', params_proy, function(respuesta) {
//											if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//												loadding(false);
//												addErrorMessage(null, respuesta.mensajeRespuesta);
//											} else {
//												listarProductoProyectoManifiesto(true);
//												addSuccessMessage(null, respuesta.mensajeRespuesta);							
//											}
//										});							
//									}else{
//										$('#sel_nro_programacion').val(ind_sel_pro);
//									}
//								});
							}else if(indicador == 0){
								loadding(true);								
								var params_proy = { 
									idProyectoManifiesto : idProyectoManifiesto,
									idProgramacion : codigo
								};						
								consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/procesarProyectoManifiesto', params_proy, function(respuesta) {
									if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
										loadding(false);
										addErrorMessage(null, respuesta.mensajeRespuesta);
									} else {
										listarProductoProyectoManifiesto(true);
										addSuccessMessage(null, respuesta.mensajeRespuesta);							
									}
								});
							}
						}
					});
			}
			
			
		}
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
	
	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			if ($('#hid_val_fec_trabajo').val() == '0') {
		    	addWarnMessage(null, 'Está ingresando una fecha con año diferente al mes de cierre.');
		    	return;
			}
			
			grabarDetalle(true);
		}
		
	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/inicio';
		$(location).attr('href', url);
		
	});
	
	$('#href_pro_nuevo').click(function(e) {
		e.preventDefault();

		$('#h4_tit_productos').html('Nuevo Producto');
		limpiarFormularioProducto();
		$('#txt_fec_vencimiento').datepicker('setDate', new Date());
		
		$('#sel_producto').html('');
		if ($('#sel_producto').hasClass('select2-hidden-accessible')) {
			$('#sel_producto').select2('destroy');
		}
		$('#sma_val_producto').hide();
		
		$('#hid_cod_producto').val('');
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
			$('#sma_val_producto').hide();
			
			$('#hid_cod_producto').val(obj.idDetalleProyecto);
			
			$('#sel_cat_producto').val(obj.idCategoria);
			
			cargarProducto(obj.idCategoria, obj.idProducto);

			$('#txt_uni_medida').val(obj.nombreUnidad);
			$('#txt_pes_net_unitario').val(obj.pesoUnitarioBruto);
			$('#txt_vol_unitario').val(obj.volumenUnitario);
			$('#txt_cantidad').val(obj.cantidad);
			
			$('#txt_imp_total').val(obj.pesoTotal);
			
			$('#div_det_productos').modal('show');
		}
		
	});
	
	$('#href_pro_eliminar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_det_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_productos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDetalleProyecto = listaProductosCache[index].idDetalleProyecto;
				codigo = codigo + idDetalleProyecto + '_';
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
						arrIdDetalleProyectoManifiesto : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/eliminarProductoProyectoManifiesto', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoProyectoManifiesto(true);
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
//						arrIdDetalleProyectoManifiesto : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/eliminarProductoProyectoManifiesto', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarProductoProyectoManifiesto(true);
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
			var idDetalleProyecto = $('#hid_cod_producto').val();
			var params = { 
				idDetalleProyecto : idDetalleProyecto,
				idProyectoManifiesto : $('#hid_cod_proyecto').val(),
				idProducto : idProducto,
				cantidad : formatMonto($('#txt_cantidad').val()),
				idAlmacen : proyectoManifiesto.idAlmacen
			};

			loadding(true);
			
			consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/grabarProductoProyectoManifiesto', params, function(respuesta) {
				$('#div_det_productos').modal('hide');
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProductoProyectoManifiesto(true);					
					addSuccessMessage(null, respuesta.mensajeRespuesta);					
				}
				frm_det_productos.data('bootstrapValidator').resetForm();
			});
			
		}
		
	});
	
	$('#btn_can_producto').click(function(e) {
		e.preventDefault();
		frm_det_productos.data('bootstrapValidator').resetForm();
	});
	
	$('#sel_cat_producto').change(function() {
		var idCategoria = $(this).val();		
		if (!esnulo(idCategoria)) {					
			cargarProducto(idCategoria, null);
		} else {
			$('#sma_val_producto').show();
			$('#sel_producto').html('');
//			frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_producto');
		}
	});
	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			$('#sma_val_producto').hide();
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
				if (!esnulo(arr[2])) {
					$('#txt_pes_net_unitario').val(arr[2]);
				} else {
					$('#txt_pes_net_unitario').val('');
				}
				if (!esnulo(arr[3])) {
					$('#txt_vol_unitario').val(arr[3]);
				} else {
					$('#txt_vol_unitario').val('');
				}
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_pes_net_unitario').val('');
				$('#txt_vol_unitario').val('');
			}
		} else {
			$('#sma_val_producto').show();
			$('#txt_uni_medida').val('');
			$('#txt_pes_net_unitario').val('');
			$('#txt_vol_unitario').val('');
		}
//		frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	});
	
	
	$('#txt_cantidad').change(function() {
		var cantidad = $(this).val();
		var pre_unitario = $('#txt_pes_net_unitario').val();
		if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
			var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
			$('#txt_imp_total').val(formatMontoAll(imp_total));
		} else {
			$('#txt_imp_total').val('');
		}
	});
	
	$('#btn_recalcular').click(function(e) {
		e.preventDefault();
		
		var varcero="";
		$("#tbl_det_productos tr").find('td:eq(0)').each(function () {
			 
				var	dat=$(this).parent();
                   if(dat.find("td:eq(5)").html()=='0' || dat.find("td:eq(5)").html()=='0.00' || dat.find("td:eq(7)").html()=='0.00000' || dat.find("td:eq(7)").html()=='0'){
                	   addWarnMessage(null, 'El item '+dat.find("td:eq(1)").html()+', no tiene peso / volumen unitario, vaya al catálogo de productos y regístrelo');
       				   varcero="1";
                   }
       })
		
		var bootstrapValidator = frm_det_pro_vehicular.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid() && esnulo(varcero)) {
			
			if (listaVehiculosCache.length == 0) { // Validacion sin registros
				addWarnMessage(null, mensajeValidacionSinRegistros);
				return;
			}
			
			if ($('#sp_tot_peso').text() == '0.00' && $('#sp_tot_peso').text() == '0.00') { // Validacion del detalle productos
				addWarnMessage(null, 'Registre en el catálogo los pesos y volúmenes del producto.');
				return;
			}

			var indices = [];
			var arrIdDetalleVehicular = [];
			tbl_det_vehiculos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_det_vehiculos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
					indices.push(index);
					arrIdDetalleVehicular.push(listaVehiculosCache[index].idDetalleVehicular);
				} else {
					arrIdDetalleVehicular.push(0);
				}
			});

			if (indices.length == 0) {
				addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
			} else {		
				loadding(true);
				
				var diferencia = 6 - listaVehiculosCache.length;
				if (diferencia > 0) {
					for (i = 0 ; i < diferencia; i++) {
						arrIdDetalleVehicular.push(0);
					}
				}
				var datoLista = arrIdDetalleVehicular;
				console.log("ARRAY: "+datoLista);
				var params = {
					idProyectoManifiesto : $('#hid_cod_proyecto').val(),
					tipoControl : $('input[name="rb_cal_nro_vehiculo"]:checked').val(),
					totalVolumen : $('#sp_tot_volumen').text(),
					totalTonelaje : $('#sp_tot_peso').text(),
					arrIdDetalleVehicular : arrIdDetalleVehicular
				};                     
				
				consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/procesarManifiestoVehiculo', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarProductoProyectoManifiesto(true);
						listarVehiculoProyectoManifiesto(false);
						addSuccessMessage(null, respuesta.mensajeRespuesta);							
					}
				});	
			}
			
		}
		
	});
	
	$('#href_doc_nuevo').click(function(e) {
		e.preventDefault();

		$('#h4_tit_no_alimentarios').html('Nuevo Documento');
		$('#frm_det_documentos').trigger('reset');
		
		$('#txt_doc_fecha').datepicker('setDate', new Date());
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
			
			$('#hid_cod_documento').val(obj.idDocumentoProyecto);			
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
				var idDocumentoProyecto = listaDocumentosCache[index].idDocumentoProyecto;
				codigo = codigo + idDocumentoProyecto + '_';
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
						arrIdDocumentoProyectoManifiesto : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/eliminarDocumentoProyectoManifiesto', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarDocumentoProyectoManifiesto(true);
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
//						arrIdDocumentoProyectoManifiesto : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/eliminarDocumentoProyectoManifiesto', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarDocumentoProyectoManifiesto(true);
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
			
			var idDocumentoProyecto = $('#hid_cod_documento').val();
			
			var params = { 
				idDocumentoProyecto : idDocumentoProyecto,
				idProyectoManifiesto : $('#hid_cod_proyecto').val(),
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
	
	$('#href_exp_excel_vehiculo').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_det_vehiculos > tbody > tr').length;
		var empty = null;
		$('tr.odd').each(function() {		
			empty = $(this).find('.dataTables_empty').text();
			return false;
		});					
		if (!esnulo(empty) || row < 1) {
			addWarnMessage(null, mensajeReporteRegistroValidacion);
			return;
		}

		loadding(true);
		var idProyectoManifiesto = $('#hid_cod_proyecto').val()
		var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/exportarExcelVehiculo/';
		url += verificaParametro(idProyectoManifiesto);
		
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
//		var tab_text="<table border='2px'>";
//	    var textRange; var j=0;
//	    tab = document.getElementById('tbl_det_vehiculos'); // id of table
//
//	    for(j = 0 ; j < tab.rows.length ; j++) 
//	    {     
//	        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
//	    }
//
//	    tab_text=tab_text+"</table>";
//	    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
//	    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
//	    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params
//
//	    var ua = window.navigator.userAgent;
//	    var msie = ua.indexOf("MSIE "); 
//
//	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
//	    {
//	        txtArea1.document.open("txt/html","replace");
//	        txtArea1.document.write(tab_text);
//	        txtArea1.document.close();
//	        txtArea1.focus(); 
//	        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
//	    }  
//	    else                 //other browser not tested on IE 11
//	        sa = window.open('data:application/vnd.ms-excel,' + escape(tab_text));  
//	    loadding(false);
//	    addInfoMessage(null, mensajeReporteExito);
//	    return (sa);

	});
	
});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_salidas').css('display', 'block');	
	$('#li_man_carga').attr('class', 'active');
	$('#li_man_carga').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_nro_pro_manifiesto').val(proyectoManifiesto.nroProyectoManifiesto);
		$('#txt_anio').val(proyectoManifiesto.codigoAnio);
		$('#txt_ddi').val(proyectoManifiesto.nombreDdi);
		$('#txt_almacen').val(proyectoManifiesto.nombreAlmacen);
		
		if (!esnulo(proyectoManifiesto.idProyectoManifiesto)) {
			ind_nuevo=1;
			$('#hid_cod_proyecto').val(proyectoManifiesto.idProyectoManifiesto);		

			$('#txt_fecha').val(proyectoManifiesto.fechaEmision);
			$('#sel_estado').val(proyectoManifiesto.idEstado);
			$('#sel_tip_movimiento').val(proyectoManifiesto.idMovimiento);			
			$('input[name=rb_man_tie_programacion][value="'+proyectoManifiesto.flagProgramacion+'"]').prop('checked', true);			
			$('#sel_nro_programacion').val(proyectoManifiesto.idProgramacion);
			ind_sel_pro=proyectoManifiesto.idProgramacion;
			$('#sel_almacen').val(proyectoManifiesto.idAlmacenDestino);
			$('#txt_observaciones').val(proyectoManifiesto.observacion.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			$('input[name=rb_cal_nro_vehiculo][value="'+proyectoManifiesto.tipoControl+'"]').prop('checked', true);

			listarProductoProyectoManifiesto(false);
			listarVehiculoProyectoManifiesto(false);
			listarDocumentoProyectoManifiesto(false);
			
		} else {
			ind_nuevo=0;
			ind_sel_pro='0'
			$('#li_productos').addClass('disabled');
			$('#li_documentos').addClass('disabled');
			$('#ul_man_ord_ingreso li.disabled a').removeAttr('data-toggle');
			
			$('#txt_fecha').datepicker('setDate', new Date());
			
			cargarIndicadorProgramacion(null);

			listarDetalleProductos(new Object());
			listarDetalleDocumentos(new Object());

		}
		
	}
		
}

function listarProductoProyectoManifiesto(indicador) {
	var params = { 
		idProyectoManifiesto : $('#hid_cod_proyecto').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/proyecto-manifiesto/listarProductoProyectoManifiesto', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleProductos(respuesta);
			if (indicador) {
				loadding(false);
			}
//			if (respuesta != null && respuesta.length > 0) {
//				$('#sel_tip_movimiento').prop('disabled', true);
//			} else {
//				$('#sel_tip_movimiento').prop('disabled', false);
//			}
		}
	});
}

function listarDetalleProductos(respuesta) {

	tbl_det_productos.dataTable().fnDestroy();
	
	tbl_det_productos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDetalleProyecto',
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
			data : 'idDetalleProyecto',
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
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'pesoUnitarioBruto',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'pesoTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'volumenUnitario',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 5)
		}, {
			data : 'volumenTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'costoBruto',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'costoTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		'footerCallback' : function ( row, data, start, end, display ) {
			var api = this.api(), data;	 
			
			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
			};
 
			// total_page_peso over this page
			total_page_peso = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			// total_page_peso over this page
			total_page_volumen = api.column(8, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$('#sp_tot_peso').html(formatMontoAll(parseFloat(total_page_peso).toFixed(2)));
			$('#sp_tot_volumen').html(formatMontoAll(parseFloat(total_page_volumen).toFixed(2)));
		}
	});
	
	setTimeout(function () {
		centerHeader('#th_cantidad');
		centerHeader('#th_pre_unitario');
		centerHeader('#th_pes_total');
		centerHeader('#th_vol_unitario');
		centerHeader('#th_vol_total');
		centerHeader('#th_cos_unitario');
		centerHeader('#th_cos_total');
	}, 500);
	
	listaProductosCache = respuesta;

}

function listarVehiculoProyectoManifiesto(indicador) {
	var params = { 
		idProyectoManifiesto : $('#hid_cod_proyecto').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/proyecto-manifiesto/listarManifiestoVehiculo', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleVehiculos(respuesta);
			if (indicador) {
				loadding(false);
			}
//			if (respuesta != null && respuesta.length > 0) {
//				$('#sel_tip_movimiento').prop('disabled', true);
//			} else {
//				$('#sel_tip_movimiento').prop('disabled', false);
//			}
		}
	});
}

function listarDetalleVehiculos(respuesta) {

	tbl_det_vehiculos.dataTable().fnDestroy();
	
	tbl_det_vehiculos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idProyectoManifiesto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {	
			data : 'flagVehiculo',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data == '0') { // Inactivo
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
						   '</label>';	
				} else { // Activo
					return '<label class="checkbox">'+
								'<input type="checkbox" checked><i></i>'+
						   '</label>';
				}											
			}
		}, {
			data : 'descripcionCamion'
		}, {
			data : 'volumen',
			sClass : 'opc-right',
		}, {
			data : 'cantidadVehiculos',
			sClass : 'opc-right',
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		columnDefs : [
  			{ width : '5%', targets : 0 },
  			{ width : '5%', targets : 1 },
  			{ width : '50%', targets : 2 },
  			{ width : '15%', targets : 3 },
  			{ width : '25%', targets : 4 }
  		]
	});
	
	listaVehiculosCache = verificarListaJson(respuesta);

}

function listarDocumentoProyectoManifiesto(indicador) {
	var params = { 
		idProyectoManifiesto : $('#hid_cod_proyecto').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/proyecto-manifiesto/listarDocumentoProyectoManifiesto', params, function(respuesta) {
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
			data : 'idDocumentoProyecto',
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
			data : 'idDocumentoProyecto',
			render : function(data, type, full, meta) {
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
	consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/grabarDocumentoProyectoManifiesto', params, function(respuesta) {
		$('#div_det_documentos').modal('hide');
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			loadding(false);			
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDocumentoProyectoManifiesto(true);
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

function cargarProducto(idCategoria, codigoProducto) {
	var params = { 
		idCategoria : idCategoria
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/control-calidad/listarProductoXCategoria', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			var val_producto = null;
	        $.each(respuesta, function(i, item) {
	        	var det_option = item.idProducto+'_'+item.nombreUnidadMedida+'_'+item.pesoUnitarioNeto+'_'+item.pesoUnitarioBruto;
	        	options += '<option value="'+det_option+'">'+item.nombreProducto+'</option>';
	            if (codigoProducto == item.idProducto) {
	            	val_producto = det_option;
	            }
	        });
	        $('#sel_producto').html(options);
	        if (codigoProducto != null) {
	        	$('#sel_producto').val(val_producto);      	
	        } else {
	        	var arr = $('#sel_producto').val().split('_');
				if (arr.length > 1) {
					$('#txt_uni_medida').val(arr[1]);
					if (!esnulo(arr[2])) {
						$('#txt_pes_net_unitario').val(arr[2]);
					} else {
						$('#txt_pes_net_unitario').val('');
					}
					if (!esnulo(arr[3])) {
						$('#txt_vol_unitario').val(arr[3]);
					} else {
						$('#txt_vol_unitario').val('');
					}
				} else {
					$('#txt_uni_medida').val('');
					$('#txt_pes_net_unitario').val('');
					$('#txt_vol_unitario').val('');
					$('#sma_val_producto').show();
				}
//				frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	        }
	        $('#sel_producto').select2();
			$('#sel_producto').select2({
				  dropdownParent: $('#div_pro_det_productos')
			});
		}
		loadding(false);		
	});
}

function limpiarFormularioProducto() {
	$('#sel_cat_producto').val('');
	$('#sel_producto').val('');
	$('#txt_uni_medida').val('');
	$('#txt_pes_net_unitario').val('');
	$('#txt_vol_unitario').val('');
	$('#txt_cantidad').val('');
	$('#txt_pre_unitario').val('');
	$('#txt_imp_total').val('');
}

function cargarIndicadorProgramacion(valor) {
	if (valor == '1') {
		$('#sel_nro_programacion').prop('disabled', false);	
	} else if (valor == '0') {
		$('#sel_nro_programacion').prop('disabled', true);
		$('#sel_nro_programacion').val('');
	} else {
		$('#sel_nro_programacion').prop('disabled', true);
		$('#sel_nro_programacion').val('');
	}
}

function grabarDetalle(indicador) {
	
	var codigo = $('#hid_cod_proyecto').val();
	var flagProgramacion = $('input[name="rb_man_tie_programacion"]:checked').val();
	var params = {
		idProyectoManifiesto : codigo,
		codigoAnio : $('#txt_anio').val(),
		codigoDdi : proyectoManifiesto.codigoDdi,
		codigoMes : proyectoManifiesto.codigoMes,
		idAlmacen : proyectoManifiesto.idAlmacen,				
		codigoAlmacen : proyectoManifiesto.codigoAlmacen,
		idAlmacenDestino : $('#sel_almacen').val(),
		nroProyectoManifiesto : $('#txt_nro_pro_manifiesto').val(),
		fechaEmision : $('#txt_fecha').val(),
		flagProgramacion : flagProgramacion,
		idProgramacion : $('#sel_nro_programacion').val(),
		idMovimiento : $('#sel_tip_movimiento').val(),
		idEstado : $('#sel_estado').val(),
		observacion : $('#txt_observaciones').val()
	};
	
	loadding(true);
	
	consultarAjax('POST', '/gestion-almacenes/proyecto-manifiesto/grabarProyectoManifiesto', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			
			if (!esnulo(codigo)) {
				
				if (indicador) {				
					addSuccessMessage(null, respuesta.mensajeRespuesta);
				}
				
			} else {
				
				$('#hid_cod_proyecto').val(respuesta.idProyectoManifiesto);
				$('#txt_nro_pro_manifiesto').val(respuesta.codigoProyectoManifiesto);
		
				$('#li_productos').attr('class', '');
				$('#li_productos').closest('li').children('a').attr('data-toggle', 'tab');

				$('#li_documentos').attr('class', '');
				$('#li_documentos').closest('li').children('a').attr('data-toggle', 'tab');
				
				addSuccessMessage(null, 'Se creó el Proyecto de Manifiesto N° '+respuesta.codigoProyectoManifiesto);
				
				listarVehiculoProyectoManifiesto(false);
				listarProductoProyectoManifiesto(false);
			}
			
		}
		
		if (indicador) {
			loadding(false);
		} else {
			var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/inicio';
			$(location).attr('href', url);
		}
	});	
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}