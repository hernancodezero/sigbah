var listaProductosCache = new Object();
var listaProductoManifiestoSalidaCache = new Object();
var listaDocumentosCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var tbl_det_productos = $('#tbl_det_productos');
var frm_det_productos = $('#frm_det_productos');

var frm_pro_can_manifiesto = $('#frm_pro_can_manifiesto');

var tbl_det_documentos = $('#tbl_det_documentos');
var frm_det_documentos = $('#frm_det_documentos');

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
		    if (mes != ordenSalida.codigoMes || anio != ordenSalida.codigoAnio) {
		    	$('#hid_val_fec_trabajo').val('0');
		    	addWarnMessage(null, mensajeValidacionAnioMesCerrado);
		    	$('#'+$(this).attr('id')).focus();
		    } else {
		    	$('#hid_val_fec_trabajo').val('1');
		    }
		}
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
	});
	
	$('#txt_fec_entrega').datepicker().on('changeDate', function(e) {
		e.preventDefault();
		frm_dat_generales.bootstrapValidator('revalidateField', $(this).attr('id'));	
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
		}
	});
	
	$('#sel_nro_ord_compra').change(function() {
		var codigo = $(this).val();
		var arr = codigo.split('_');
		if (arr.length > 1) {
			$('#txt_det_ord_compra').val(arr[1]);
		} else {
			$('#txt_det_ord_compra').val('');
		}
	});
	
	$('#sel_tip_movimiento').change(function() {
		var val_tip_movimiento = $(this).val();		
		if (!esnulo(val_tip_movimiento)) {
			
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
			$('#sel_emp_transporte').html('');
			$('#txt_nro_placa').val('');
			$('#txt_fec_entrega').val('');
			$('#sel_chofer').val('');
			
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_ddi');
			frm_dat_generales.bootstrapValidator('revalidateField', 'rb_tie_ate_gobierno');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_gore');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_departamento');
			
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_med_transporte');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_nro_placa');
			frm_dat_generales.bootstrapValidator('revalidateField', 'txt_fec_entrega');
			frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');

		}
	});
	
	$('input[type=radio][name=rb_tie_ate_gobierno]').change(function() {
		cargarTipoAtencion(this.value);
    });
	
	$('input[type=radio][name=rb_man_tie_programacion]').change(function() {
		cargarIndicadorProyecto(this.value);
    });
	
	$('#sel_nro_pro_manifiesto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_requerimiento').val(arr[1]);
			} else {
				$('#txt_requerimiento').val('');
			}			
		} else {
			$('#txt_requerimiento').val('');
		}
	});
	
	$('#sel_ddi').change(function() {
		
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {	
			
			cargarDatosDdiDestino(codigo, null, null);
		} else {
			$('#sel_alm_destino').html('');
			$('#sel_res_recepcion').html('');
		}
		loadding(false);
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
	
	$('#sel_med_transporte').change(function() {
		var codigo = $(this).val();		
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
					if (respuesta.length > 0) {
						options = '<option value="">Seleccione</option>';
				        $.each(respuesta, function(i, item) {
				            options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
				        });
					}
			        $('#sel_emp_transporte').html(options);
				}
				loadding(false);
				frm_dat_generales.bootstrapValidator('revalidateField', 'sel_emp_transporte');
				
				consultarAjax('GET', '/gestion-almacenes/control-calidad/listarChofer', {icodigo : $('#sel_emp_transporte').val()}, function(respuesta) {
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
					frm_dat_generales.bootstrapValidator('revalidateField', 'sel_chofer');
				});
				
			});
		} else {
			$('#sel_emp_transporte').html('');
		}
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
		var url = VAR_CONTEXT + '/gestion-almacenes/orden-salida/inicio';
		$(location).attr('href', url);
		
	});
	
	$('#href_pro_nuevo').click(function(e) {
		e.preventDefault();

		$('#h4_tit_productos').html('Nuevo Producto');
		limpiarFormularioProducto();
		$('#txt_fec_vencimiento').datepicker('setDate', new Date());
		
		$('#sel_cat_producto').prop('disabled', false);
		$('#sel_producto').prop('disabled', false);
		$('#sel_lote').prop('disabled', false);
		cargarProducto($('#sel_cat_producto').val(), null, null);
		$('#sel_producto').html('');
		if ($('#sel_producto').hasClass('select2-hidden-accessible')) {
			$('#sel_producto').select2('destroy');
		}
		
		$('#sel_lote').html('');
		
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
			
			$('#hid_cod_producto').val(obj.idDetalleSalida);
			
			$('#sel_cat_producto').val(obj.idCategoria);
			$('#sel_cat_producto').prop('disabled', true);
			
			var val_producto = obj.idProducto+'_'+obj.nombreUnidad+'_'+obj.pesoUnitarioNeto+'_'+obj.pesoUnitarioBruto;
			cargarProducto(obj.idCategoria, val_producto, obj.nroLote);

			$('#txt_uni_medida').val(obj.nombreUnidad);
			$('#txt_pes_net_unitario').val(obj.pesoUnitarioNeto);
			$('#txt_pes_bru_unitario').val(obj.pesoUnitarioBruto);
			$('#txt_cantidad').val(formatMontoSinComas(obj.cantidad));
			$('#txt_pre_unitario').val(obj.precioUnitario);
			$('#txt_imp_total').val(formatMontoAll(obj.importeTotal));
			
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
				var idDetalleSalida = listaProductosCache[index].idDetalleSalida;
				codigo = codigo + idDetalleSalida + '_';
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
						arrIdDetalleSalida : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/orden-salida/eliminarProductoOrdenSalida', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarProductoOrdenSalida(true);
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
//						arrIdDetalleSalida : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/orden-salida/eliminarProductoOrdenSalida', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarProductoOrdenSalida(true);
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
			var nroLote = null;
			var val_nroLote = $('#sel_lote').val();
			if (!esnulo(val_nroLote)) {
				var arr = val_nroLote.split('_');
				nroLote = arr[0];
			}
			var indControl = null;
			var idDetalleSalida = $('#hid_cod_producto').val();
			if (esnulo(idDetalleSalida)) {
				indControl = 'I'; // I= INSERT
			} else {
				indControl = 'U'; // U= UPDATE
			}
			
			var indicaProducto = true;

			tbl_det_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_det_productos.DataTable().rows().$('input[type="checkbox"]')[index]) {
					var idDetalleSalida1 = listaProductosCache[index].idProducto;
					var numLote = listaProductosCache[index].nroLote;

					if (idProducto == idDetalleSalida1 && nroLote == numLote) {
						indicaProducto=false;
					}
					
				}
			});

			if(indicaProducto || !esnulo(idDetalleSalida)){
			
				var params = { 
					idDetalleSalida : idDetalleSalida,
					idSalida : $('#hid_cod_ord_salida').val(),
					idProducto : idProducto,
					cantidad : formatMonto($('#txt_cantidad').val()),
					precioUnitario : formatMonto($('#txt_pre_unitario').val()),
					importeTotal : formatMonto($('#txt_imp_total').val()),
					nroLote : nroLote,
					indControl : indControl
				};
	
				loadding(true);
				
				consultarAjax('POST', '/gestion-almacenes/orden-salida/grabarProductoOrdenSalida', params, function(respuesta) {
					$('#div_det_productos').modal('hide');
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarProductoOrdenSalida(true);					
						addSuccessMessage(null, respuesta.mensajeRespuesta);					
					}
					frm_det_productos.data('bootstrapValidator').resetForm();
				});
				
			}else{
				addWarnMessage(null, "Producto ya agregado.");
			}
			
		}
		
	});
	
	$('#btn_can_producto').click(function(e) {
		e.preventDefault();
		frm_det_productos.data('bootstrapValidator').resetForm();
	});
	
	$('#sel_cat_producto').change(function() {
		var idCategoria = $(this).val();
		
		if (!esnulo(idCategoria)) {					
			cargarProducto(idCategoria, null, null);
		} else {
			$('#sel_producto').html('');
			frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_producto');
		}
	});
	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
				if (!esnulo(arr[2])) {
					$('#txt_pes_net_unitario').val(arr[2]);
				} else {
					$('#txt_pes_net_unitario').val('');
				}
				if (!esnulo(arr[3])) {
					$('#txt_pes_bru_unitario').val(arr[3]);
				} else {
					$('#txt_pes_bru_unitario').val('');
				}
			} else {
				$('#txt_uni_medida').val('');
				$('#txt_envase').val('');			
				$('#txt_pes_net_unitario').val('');
				$('#txt_pes_bru_unitario').val('');
			}
			cargarLote(arr[0], null);
		} else {
			$('#txt_uni_medida').val('');
			$('#txt_envase').val('');			
			$('#txt_pes_net_unitario').val('');
			$('#txt_pes_bru_unitario').val('');
		}
//		frm_det_productos.bootstrapValidator('revalidateField', 'sel_producto');
	});
	
	$('#sel_lote').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {	
			var arr = codigo.split('_');
			if (arr.length > 1) {
				if (!esnulo(arr[1])) {
					$('#txt_can_stock').val(arr[1]);
				} else {
					$('#txt_can_stock').val('');
				}
				if (!esnulo(arr[2])) {
					$('#txt_pre_unitario').val(formatMontoAll(arr[2]));
				} else {
					$('#txt_pre_unitario').val('');
				}
			} else {
				$('#txt_can_stock').val('');
				$('#txt_pre_unitario').val('');
			}
		} else {
			$('#txt_can_stock').val('');
			$('#txt_pre_unitario').val('');
		}
		frm_det_productos.bootstrapValidator('revalidateField', 'sel_lote');
	});
	
	$('#txt_cantidad').change(function() {
		var cantidad = $(this).val();
		var pre_unitario = $('#txt_pre_unitario').val();
		if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
			var imp_total = parseFloat(formatMonto(cantidad)) * parseFloat(formatMonto(pre_unitario));
			$('#txt_imp_total').val(formatMontoAll(imp_total));
		} else {
			$('#txt_imp_total').val('');
		}
	});
	
	$('#href_agr_pro_manifiesto').click(function(e) {
		e.preventDefault();
		
		var nro_pro_manifiesto = $('#sel_nro_pro_manifiesto').val();
		if (esnulo(nro_pro_manifiesto)) {
			addWarnMessage(null, 'Debe seleccionar el N° de Proyecto de Manifiesto.');
			return;
		}
		
		var row = $('#tbl_det_productos > tbody > tr').length;
		var empty = null;
		$('#tbl_det_productos tr.odd').each(function() {		
			empty = $(this).find('.dataTables_empty').text();
			return false;
		});					
		if (esnulo(empty) && row > 0) {
			addWarnMessage(null, 'Para agregar productos del manifiesto debe eliminar todos los productos.');
			return;
		}
		
		var arr = nro_pro_manifiesto.split('_');
//		$('#txt_nro_pro_manifiesto').val(arr[1]);
		$('#txt_nro_pro_manifiesto').val($("#sel_nro_pro_manifiesto option:selected").text());
		var params = { 
			idProyectoManifiesto : arr[0]
		};		
		
		consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarProductoManifiestoSalida', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				
				var table = $('<table id="tbl_pro_can_manifiesto" />').addClass('table table-bordered table-hover tbl-responsive');
				var row = $('<tr/>');
				row.append($('<th class="table-dinamic" />').text('N°'));
				row.append($('<th class="table-dinamic" />').text('Producto'));
				row.append($('<th class="table-dinamic" />').text('Unidad Medida'));
				row.append($('<th class="table-dinamic" />').text('Cantidad Programada'));
				row.append($('<th class="table-dinamic" />').text('Cantidad Despachada'));
				row.append($('<th class="table-dinamic" />').text('Saldo por Despachar'));
				row.append($('<th class="table-dinamic" />').text('Cantidad Salida'));
				row.append($('<th class="table-dinamic" />').text('Stock Almacén'));		
				row.append($('<th class="table-dinamic" />').text('Volumen Total'));
				row.append($('<th class="table-dinamic" />').text('Peso Total Bruto'));
				table.append(row);
		
				if (respuesta.length > 0) {
					var row_num = 1;
					
					var tot_vol_bruto = 0;
					var tot_pes_bruto = 0;					
					$.each(respuesta, function(index, item) {
						row = $('<tr class="item_pro_man" />');
						row.append($('<td/>').html(row_num));
						row.append($('<td class="opc-table-30" />').html(item.nombreProducto));
						row.append($('<td/>').html(item.nombreUnidad));
						row.append($('<td/>').html(item.cantidadProgramada));
						row.append($('<td/>').html(item.cantidadDespachada));
						row.append($('<td id="td_sal_despachar_'+item.idProducto+'" />').html(item.cantidadPorDespachar));
						var htm_cantidad = '<input type="text" name="txt_can_salida" id="txt_can_salida_'+item.idProducto+'" '+
									   	   'value="'+item.cantidadSalida+'" class="form-control input-xs only-numbers-format" '+
									   	   'onkeypress="validateOnlyNumeric(event)" maxlength="10" '+
									   	   'onchange="validarCantidadSalida('+item.idProducto+')">'+
									   	   '<span id="sp_can_salida_'+item.idProducto+'" class="control-label label-error" />';						
						row.append($('<td/>').html(htm_cantidad));
						row.append($('<td id="td_sto_almacen_'+item.idProducto+'" />').html(item.stockAlmacen));				
						row.append($('<td id="td_vol_total_'+item.idProducto+'" />').html('0.00'));
						row.append($('<td id="td_pes_tot_bruto_'+item.idProducto+'" class="opc-table-10" />').html('0.00'));
						table.append(row);
						row_num++;
					});
					
					row = $('<tr/>');
					row.append($('<td class="opc-right" colspan="8" />').html("Total:"));
					row.append($('<td id="td_tot_vol_bruto" />').html(formatMontoAll(tot_vol_bruto)));
					row.append($('<td id="td_tot_pes_bruto" />').html(formatMontoAll(tot_pes_bruto)));
					table.append(row);
				}
				
				$('#div_pro_can_manifiesto').html(table);
				
				listaProductoManifiestoSalidaCache = verificarListaJson(respuesta);
				
				$('#div_det_pro_manifiesto').modal('show');	
				
			}
		});
			
	});
	
	$('#btn_ace_pro_manifiesto').click(function(e) {
		e.preventDefault();
		var valida = false;
		var estado = false;
		var arrIdProducto = [];
		var arrCantidad = [];
		$.each(listaProductoManifiestoSalidaCache, function(i, item) {
	    	var sp_can_salida = $('#sp_can_salida_'+item.idProducto).html();
	    	if (!esnulo(sp_can_salida)) {
	    		estado = true;
	    		return false;
	    	}
	    	console.log("can "+$('#txt_can_salida_'+item.idProducto).val());
	    	if($('#txt_can_salida_'+item.idProducto).val()=='0'){
	    		addErrorMessage(null, "La cantidad en el producto no puede ser 0");
	    		valida = true;
	    		return false;
	    	}
	    	var cantidad = parseInt(formatMonto($('#txt_can_salida_'+item.idProducto).val()));
	    	if (cantidad > 0) {
	    		arrIdProducto.push(item.idProducto);
	    		arrCantidad.push(cantidad);
	    	}
	    });
		
		if (!estado && !valida) {
			loadding(true);
			var params = { 
				idSalida : $('#hid_cod_ord_salida').val(),
				arrIdProducto : arrIdProducto,
				arrCantidad : arrCantidad
			};			
			consultarAjax('POST', '/gestion-almacenes/orden-salida/grabarProductoManifiestoSalida', params, function(respuesta) {		
				$('#div_det_pro_manifiesto').modal('hide');
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProductoOrdenSalida(true);
					addSuccessMessage(null, respuesta.mensajeRespuesta);					
				}
			});			
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
			
			$('#hid_cod_documento').val(obj.idDocumentoSalida);			
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
				var idDocumentoSalida = listaDocumentosCache[index].idDocumentoSalida;
				codigo = codigo + idDocumentoSalida + '_';
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
						arrIdDocumentoSalida : codigo
					};
			
					consultarAjax('POST', '/gestion-almacenes/orden-salida/eliminarDocumentoOrdenSalida', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							listarDocumentoOrdenSalida(true);
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
//						arrIdDocumentoSalida : codigo
//					};
//			
//					consultarAjax('POST', '/gestion-almacenes/orden-salida/eliminarDocumentoOrdenSalida', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							listarDocumentoOrdenSalida(true);
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
			
			var indControl = null;
			var idDocumentoSalida = $('#hid_cod_documento').val();
			if (esnulo(idDocumentoSalida)) {
				indControl = 'I'; // I= INSERT
			} else {
				indControl = 'U'; // U= UPDATE
			}
			
			var params = { 
				idDocumentoSalida : idDocumentoSalida,
				idSalida : $('#hid_cod_ord_salida').val(),
				idTipoDocumento : $('#sel_tip_producto').val(),
				nroDocumento : $('#txt_nro_documento').val(),
				fechaDocumento : $('#txt_doc_fecha').val(),
				nombreArchivo : $('#txt_lee_sub_archivo').val(),
				indControl : indControl
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
	
});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_salidas').css('display', 'block');	
	$('#li_ord_salida').attr('class', 'active');
	$('#li_ord_salida').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_anio').val(ordenSalida.codigoAnio);
		$('#txt_ddi').val(ordenSalida.nombreDdi);
		$('#txt_almacen').val(ordenSalida.nombreAlmacen);
		
		if (!esnulo(ordenSalida.idSalida)) {
			$('input[name=rb_man_tie_programacion][value="'+ordenSalida.flagProyecto+'"]').prop('checked', true);

			$('#hid_cod_ord_salida').val(ordenSalida.idSalida);		
			$('#txt_nro_ord_salida').val(ordenSalida.nroOrdenSalida);

			$('#txt_fecha').val(ordenSalida.fechaEmision);
			$('#sel_estado').val(ordenSalida.idEstado);
			$('#sel_tip_movimiento').val(ordenSalida.idMovimiento);
			if(ordenSalida.nroProgramacion=='-'){
				ordenSalida.nroProgramacion="";
			}
			var val_nro_pro_manifiesto = ordenSalida.idProyectoManifiesto+'_'+ordenSalida.nroProgramacion+'_'+ordenSalida.idProgramacion;
			console.log("proy "+val_nro_pro_manifiesto);
			$('#sel_nro_pro_manifiesto').val(val_nro_pro_manifiesto);			
			$('#txt_requerimiento').val(ordenSalida.nroProgramacion);			
			cargarIndicadorProyecto($('input[name="rb_man_tie_programacion"]:checked').val());
			$('#sel_solicitada').val(ordenSalida.idSolicitante);
			$('#sel_responsable').val(ordenSalida.idResponsable);
			
			if (!esnulo(ordenSalida.idDdiDestino)) {
				$('#sel_ddi').val(ordenSalida.idDdiDestino);
			}
			
			$('input[name=rb_tie_ate_gobierno][value="'+ordenSalida.flagTipoDestino+'"]').prop('checked', true);
			
			$('#sel_med_transporte').val(ordenSalida.idMedioTransporte);
			$('#sel_emp_transporte').val(ordenSalida.idEmpresaTransporte);
			$('#txt_fec_entrega').val(ordenSalida.fechaEntrega);
			$('#sel_chofer').val(ordenSalida.idChofer);
			$('#txt_nro_placa').val(ordenSalida.nroPlaca);
			$('#txt_observaciones').val(ordenSalida.observacion.replace(/<<E>>/g, "\n").replace(/<<D>>/g, "\"").replace(/<<S>>/g, "\'"));
			
			cargarTipoMovimiento(ordenSalida.idMovimiento);
			
			if (ordenSalida.flagTipoDestino == 'I') {
				
				cargarDatosDdiDestino(ordenSalida.idDdiDestino, ordenSalida.idAlmacenDestino, ordenSalida.idResponsableRecepcion);
			} else if (ordenSalida.flagTipoDestino == 'R') {
				cargarTipoAtencion(ordenSalida.flagTipoDestino);
				if (!esnulo(ordenSalida.codigoRegion)) {
					$('#sel_gore').val(ordenSalida.codigoRegion);
					cargarDatosRegionalDestino(ordenSalida.codigoRegion, ordenSalida.idAlmacenDestinoExt, ordenSalida.idResponsableExt);
				}
			} else if (ordenSalida.flagTipoDestino == 'L') {
				cargarTipoAtencion(ordenSalida.flagTipoDestino);
				if (!esnulo(ordenSalida.codigoUbigeo)) {
					$('#sel_departamento').val(ordenSalida.codigoDepartamento);
					cargarProvincia(ordenSalida.codigoDepartamento, ordenSalida.codigoProvincia);
					cargarDistrito(ordenSalida.codigoProvincia, ordenSalida.codigoUbigeo);
					cargarDatosLocalDestino(ordenSalida.codigoUbigeo, ordenSalida.idAlmacenDestinoExt, ordenSalida.idResponsableExt);
				}
			}
			
			listarProductoOrdenSalida(false);			
			listarDocumentoOrdenSalida(false);
			
			$('#txt_det_nro_ord_compra').val(ordenSalida.nroOrdenCompra);
			
		} else {
			
			$('#sel_estado').prop('disabled', true);
			
			$('#txt_nro_ord_salida').val(ordenSalida.nroOrdenSalida);
			
			$('#li_productos').addClass('disabled');
			$('#li_documentos').addClass('disabled');
			$('#ul_man_ord_ingreso li.disabled a').removeAttr('data-toggle');
			
			$('#txt_fecha').datepicker('setDate', new Date());
			
			var val_nro_pro_manifiesto = $('#sel_nro_pro_manifiesto').val();		
			if (!esnulo(val_nro_pro_manifiesto)) {
				var arr = val_nro_pro_manifiesto.split('_');
				if (arr.length > 1) {
					$('#txt_requerimiento').val(arr[1]);
				} else {
					$('#txt_requerimiento').val('');
				}			
			}
			
			cargarTipoMovimiento($('#sel_tip_movimiento').val());
			
			listarDetalleProductos(new Object());
			listarDetalleDocumentos(new Object());

		}
		
	}
		
}

function listarProductoOrdenSalida(indicador) {
	var params = { 
		idSalida : $('#hid_cod_ord_salida').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarProductoOrdenSalida', params, function(respuesta) {
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
			data : 'idDetalleSalida',
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
			data : 'idDetalleSalida',
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
			data : 'nroLote',
			sClass : 'opc-center'
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
			data : 'pesoNetoTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'pesoBrutoTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
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
		centerHeader('#th_cantidad');
		centerHeader('#th_pre_unitario');
		centerHeader('#th_imp_total');
		centerHeader('#th_pes_net_total');
		centerHeader('#th_pes_bru_total');
	}, 500);
	
	listaProductosCache = respuesta;

}

function listarDocumentoOrdenSalida(indicador) {
	var params = { 
		idSalida : $('#hid_cod_ord_salida').val()
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarDocumentoOrdenSalida', params, function(respuesta) {
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
			data : 'idDocumentoSalida',
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
	consultarAjax('POST', '/gestion-almacenes/orden-salida/grabarDocumentoOrdenSalida', params, function(respuesta) {
		$('#div_det_documentos').modal('hide');
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			loadding(false);			
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDocumentoOrdenSalida(true);
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

function cargarTipoMovimiento(val_tip_movimiento) {
	if (val_tip_movimiento == '3') { // Transferencia entre Almacenes 
		$('#div_ddi_destino').show();
		$('#div_gob_destino').hide();
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').hide();
		
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
	}
}

function cargarProducto(idCategoria, codigoProducto, codigoLote) {
	var params = { 
		idAlmacen : ordenSalida.idAlmacen,
		idCategoria : idCategoria
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/orden-salida/listarProductosStock', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
			if (respuesta.length > 0) {
				options = '<option value="">Seleccione</option>';				
		        $.each(respuesta, function(i, item) {
					var det_option = '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'_'+item.pesoUnitarioNeto+'_'+item.pesoUnitarioBruto+'">';
					det_option = det_option + item.nombreProducto+'</option>';				
		            options += det_option;
		        });
			}
	        $('#sel_producto').html(options);
	        if (codigoProducto != null) {
	        	$('#sel_producto').val(codigoProducto);
	        	$('#sel_producto').prop('disabled', true);
	        	var arr = $('#sel_producto').val().split('_');
				cargarLote(arr[0], codigoLote);      	
	        } else {
	        	if (respuesta.length > 0) {
		        	var arr = $('#sel_producto').val().split('_');
					if (arr.length > 1) {
						$('#txt_uni_medida').val(arr[1]);
						if (!esnulo(arr[2])) {
							$('#txt_pes_net_unitario').val(arr[2]);
						} else {
							$('#txt_pes_net_unitario').val('');
						}
						if (!esnulo(arr[3])) {
							$('#txt_pes_bru_unitario').val(arr[3]);
						} else {
							$('#txt_pes_bru_unitario').val('');
						}
					} else {
						$('#txt_uni_medida').val('');
						$('#txt_pes_net_unitario').val('');
						$('#txt_pes_bru_unitario').val('');
					}
	        	} else {
	        		$('#txt_uni_medida').val('');
					$('#txt_pes_net_unitario').val('');
					$('#txt_pes_bru_unitario').val('');
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

function cargarLote(idProducto, codigoLote) {
	var params = { 
		idAlmacen : ordenSalida.idAlmacen,
		idProducto : idProducto
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/orden-salida/listarLoteProductoSalida', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
			var nroLote = '';
			if (respuesta.length > 0) {
				options = '<option value="">Seleccione</option>';				
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.nroLote+'_'+item.cantidad+'_'+item.precioUnitario+'">'+item.nroLote+'</option>';
		            if (codigoLote != null && codigoLote == item.nroLote) {
		            	nroLote = item.nroLote+'_'+item.cantidad+'_'+item.precioUnitario;
		            }
		        });
			}
	        $('#sel_lote').html(options);
	        if (codigoLote != null) {
	        	$('#sel_lote').val(nroLote);
	        	$('#sel_lote').prop('disabled', true);
	        }	        
	        if (respuesta.length > 0) {
	        	var arr = $('#sel_lote').val().split('_');
				if (arr.length > 1) {
					if (!esnulo(arr[1])) {
						$('#txt_can_stock').val(arr[1]);
					} else {
						$('#txt_can_stock').val('');
					}
					if (!esnulo(arr[2])) {
						$('#txt_pre_unitario').val(formatMontoAll(arr[2]));
					} else {
						$('#txt_pre_unitario').val('');
					}
				} else {
					$('#txt_can_stock').val('');
					$('#txt_pre_unitario').val('');
				}
	        } else {
	        	$('#txt_can_stock').val('');
				$('#txt_pre_unitario').val('');
	        }
		}
		loadding(false);		
	});
}

function limpiarFormularioProducto() {
	$('#sel_cat_producto').val('0');
	$('#sel_producto').val('');
	$('#sel_lote').val('');
	$('#txt_can_stock').val('');
	$('#txt_uni_medida').val('');
	$('#txt_pes_net_unitario').val('');
	$('#txt_pes_bru_unitario').val('');
	$('#txt_cantidad').val('');
	$('#txt_pre_unitario').val('');
	$('#txt_imp_total').val('');
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
	consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarAlmacenDestino', params, function(respuesta) {
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
			$('#sel_alm_destino').html(options);
			if (codigoAlmacen != null) {
	        	$('#sel_alm_destino').val(codigoAlmacen);       	
	        }
		}
	
		
		consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarResponsableRecepcion', {icodigo : codigo}, function(respuesta) {
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
				$('#sel_res_recepcion').html(options);
				if (codigoResponsable != null) {
					$('#sel_res_recepcion').val(codigoResponsable);       	
				}
			}
		});
		
	});
	loadding(false);
}

function cargarDatosRegionalDestino(codigo, codigoAlmacenExtRegion, codigoPersonalExtRegion) {
	var params = { 
		icodigo : codigo
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarAlmacenExtRegion', params, function(respuesta) {
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
		
		consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarPersonalExtRegion', {icodigo : codigo}, function(respuesta) {
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

function cargarDatosLocalDestino(codigo, codigoAlmacenExtLocal, codigoPersonalExtLocal) {
	var params = { 
		vcodigo : codigo
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarAlmacenExtLocal', params, function(respuesta) {
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
		
		consultarAjaxSincrono('GET', '/gestion-almacenes/orden-salida/listarPersonalExtLocal', {vcodigo : codigo}, function(respuesta) {
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

function cargarProvincia(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/orden-salida/listarProvincia', params, function(respuesta) {
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
	consultarAjax('GET', '/gestion-almacenes/orden-salida/listarDistrito', params, function(respuesta) {
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

function validarCantidadSalida(idProducto) {
	var cantidad = $('#txt_can_salida_'+idProducto).val();
	if (!esnulo(cantidad)) {
		$('#sp_can_salida_'+idProducto).html('');
		cantidad = parseFloat(cantidad);
		var sal_despachar = parseFloat(formatMonto($('#td_sal_despachar_'+idProducto).html()));
		var sto_almacen = parseFloat(formatMonto($('#td_sto_almacen_'+idProducto).html()));
		var msj = '';
		if (cantidad > sal_despachar) {
			msj = msj + 'La cantidad de Salida debe ser menor o igual al Saldo por Despachar.<br>';
		}
		if (cantidad > sto_almacen) {
			msj = msj + 'La cantidad de Salida debe ser menor al Stock de Almacén.';
		}

		$('#sp_can_salida_'+idProducto).html(msj);
		
		if (esnulo(msj)) {
			var volumenUnitario = null;
			var pesoUnitarioBruto = null;
			var volumenTotal = 0;
			var pesoTotalBruto = 0;
			$.each(listaProductoManifiestoSalidaCache, function(i, item) {
		    	if (item.idProducto == idProducto) {
		    		volumenUnitario = parseFloat(verificaParametroInt(item.volumenUnitario));
		    		pesoUnitarioBruto = parseFloat(verificaParametroInt(item.pesoUnitarioBruto));
		    		volumenTotal = volumenTotal + parseFloat(cantidad * volumenUnitario);
			    	pesoTotalBruto = pesoTotalBruto + parseFloat(cantidad * pesoUnitarioBruto);
		    	} else {
		    		volumenTotal = volumenTotal + parseFloat(formatMonto($('#td_vol_total_'+item.idProducto).html()));
			    	pesoTotalBruto = pesoTotalBruto + parseFloat(formatMonto($('#td_pes_tot_bruto_'+item.idProducto).html()));
		    	}
		    });				
			$('#td_vol_total_'+idProducto).html(formatMontoAll(cantidad * volumenUnitario));
			$('#td_pes_tot_bruto_'+idProducto).html(formatMontoAll(cantidad * pesoUnitarioBruto));
			
			$('#td_tot_vol_bruto').html(formatMontoAll(volumenTotal));
			$('#td_tot_pes_bruto').html(formatMontoAll(pesoTotalBruto));
		}
	} else {
		$('#sp_can_salida_'+idProducto).html('Debe ingresar Cantidad Salida.');
	}
}

function cargarIndicadorProyecto(valor) {
	if (valor == '1') {
		$('#sel_nro_pro_manifiesto').prop('disabled', false);	
	} else if (valor == '0') {
		$('#sel_nro_pro_manifiesto').prop('disabled', true);
		$('#sel_nro_pro_manifiesto').val('');
	} else {
		$('#sel_nro_pro_manifiesto').prop('disabled', true);
		$('#sel_nro_pro_manifiesto').val('');
	}
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}

function grabarDetalle(indicador){
	var codigo = $('#hid_cod_ord_salida').val();			
	var idResponsableExt = null;
	var idResponsableRecepcion = null;
	var idAlmacenDestino = null;
	var idAlmacenDestinoExt = null;
	var codigoUbigeo = null;
	var flagTipoDestino = $('input[name="rb_tie_ate_gobierno"]:checked').val();
	var flagProyecto = $('input[name="rb_man_tie_programacion"]:checked').val();
	if (esnulo(flagTipoDestino)) {
		flagTipoDestino = 'I';
		idResponsableRecepcion = $('#sel_res_recepcion').val();
		idAlmacenDestino = $('#sel_alm_destino').val();
	} else {
		if (flagTipoDestino == 'R') {
			codigoUbigeo = $('#sel_gore').val();
		} else {
			codigoUbigeo = $('#sel_distrito').val();
		}				
		idResponsableExt = $('#sel_res_recepcion').val();
		idAlmacenDestinoExt = $('#sel_alm_destino').val();
	}
	var indControl = null;
	if (esnulo(codigo)) {
		indControl = 'I'; // I= INSERT
	} else {
		indControl = 'U'; // U= UPDATE
	}
	
	var idProyectoManifiesto = null;
	var idProgramacion = null
	var nro_pro_manifiesto = $('#sel_nro_pro_manifiesto').val();
	if (!esnulo(nro_pro_manifiesto)) {
		var arr = nro_pro_manifiesto.split('_');
		idProyectoManifiesto = arr[0];
		idProgramacion = arr[2];
	}
	
	var params = {
		idSalida : codigo,
		codigoAnio : $('#txt_anio').val(),
		codigoMes : ordenSalida.codigoMes,
		idAlmacen : ordenSalida.idAlmacen,
		codigoDdi : ordenSalida.codigoDdi,
		codigoAlmacen : ordenSalida.codigoAlmacen,
		nroOrdenSalida : $('#txt_nro_ord_salida').val(),
		fechaEmision : $('#txt_fecha').val(),
		codigoUbigeo : codigoUbigeo,
		idProgramacion : idProgramacion,
		idResponsable : $('#sel_responsable').val(),
		idResponsableExt : idResponsableExt,
		idSolicitante : $('#sel_solicitada').val(),
		idResponsableRecepcion : idResponsableRecepcion,
		idProyectoManifiesto : idProyectoManifiesto,
		idMovimiento : $('#sel_tip_movimiento').val(),
		idAlmacenDestino : idAlmacenDestino,
		idAlmacenDestinoExt : idAlmacenDestinoExt,
		idMedioTransporte : $('#sel_med_transporte').val(),
		idEmpresaTransporte : $('#sel_emp_transporte').val(),
		idChofer : $('#sel_chofer').val(),
		nroPlaca : $.trim($('#txt_nro_placa').val()),
		fechaEntrega : $('#txt_fec_entrega').val(),
		flagTipoDestino : flagTipoDestino,
		observacion : $('#txt_observaciones').val(),
		idEstado : $('#sel_estado').val(),
		indControl : indControl,
		flagProyecto : flagProyecto
	};
	
	loadding(true);
	
	consultarAjax('POST', '/gestion-almacenes/orden-salida/grabarOrdenSalida', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			
			if (!esnulo(codigo)) {
				
				addSuccessMessage(null, respuesta.mensajeRespuesta);
				
			} else {
				
				$('#sel_estado').prop('disabled', false);
				$('#hid_cod_ord_salida').val(respuesta.idSalida);
				$('#txt_nro_ord_salida').val(respuesta.codigoSalida);
		
				$('#li_productos').attr('class', '');
				$('#li_productos').closest('li').children('a').attr('data-toggle', 'tab');

				$('#li_documentos').attr('class', '');
				$('#li_documentos').closest('li').children('a').attr('data-toggle', 'tab');
				
				addSuccessMessage(null, 'Se genero el N° Orden de Salida: '+respuesta.codigoSalida);
				
			}
			
		}
		if (indicador) {
			loadding(false);
		} else {
			var url = VAR_CONTEXT + '/gestion-almacenes/orden-salida/inicio';
			$(location).attr('href', url);
		}
	});		
}