var listaRacionCache = new Object();
var listaProductoCache = new Object();
var frm_dat_generales_racion = $('#frm_dat_generales_racion');
var tbl_mnt_racion_ope = $('#tbl_mnt_racion_ope'); 
 
var frm_det_productos = $('#frm_det_productos');
var tbl_mnt_productos = $('#tbl_mnt_productos'); 

$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();

	$('#btn_grabar_racion').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales_racion.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var codigo = $('#hid_cod_raciones').val();
			var params = {
				idRacionOpe :codigo,
				tipoRacion : $('#sel_tipo_racion').val(),
				codRacion : $('#txt_cod_racion').val(),
				nombreRacion : $('#txt_nom_racion').val(),  
				diasAtencion : $('#txt_num_dias').val(),
				fechaRacion : $('#txt_fecha').val() 
			};
			
			loadding(true);
			
			consultarAjax('POST', '/programacion-bath/racion/grabarRacion', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					
					if (!esnulo(codigo)) {
						
						addSuccessMessage(null, respuesta.mensajeRespuesta);
						
					} else { 
						
						addSuccessMessage(null, 'Se genero la racion el N°  '+respuesta.codRacion);   
						racion.idRacionOpe=respuesta.idRacionOpe;
						$('#div_tabla_prod').show();
						$('#txt_cod_racion').val(respuesta.codRacion);

						$('#btn_grabar_racion').attr("disabled", true); 

						
					}
					
				}
				loadding(false);
			});			
		}
		
	});


	
	$('#btn_gra_prod').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_det_productos.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
		
			var idProducto = $('#sel_producto').val();
			var indicaProducto = true;
			console.log("IDPRODUCTOEDITAR: "+$('#hid_cod_producto').val());
			tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]')[index]) {
					var fkIdProducto = listaProductoCache[index].fkIdProducto;
					console.log("IDPRODUCTO: "+fkIdProducto);
					if (idProducto == fkIdProducto) {
						indicaProducto=false;
					}
				}
			});
			
			if(indicaProducto || !esnulo($('#hid_cod_producto').val())){
				var codigo = $('#hid_cod_producto').val();
				var params = {
						idDetaRacion : codigo,
						fkIdProducto : $('#sel_producto').val(),
						pesoUnitarioPres : formatMonto($('#txt_uni_pres').val()),
						cantRacionKg : formatMonto($('#txt_gr_aprox').val()),
						idRacion : racion.idRacionOpe
				};
				
				loadding(true);
				
				consultarAjax('POST', '/programacion-bath/racion/grabarProducto', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						
							addSuccessMessage(null, respuesta.mensajeRespuesta);
							$('#div_det_productos').modal('hide');
							llenarProductos(racion.idRacionOpe);
					}
					loadding(false);
				});	
			}else{
				addWarnMessage(null, "Producto ya agregado.");
			}
			
		}
	});
	

	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/racion/inicio/1';
		$(location).attr('href', url);
		
	});
	

	$('#href_nuevo_prod').click(function(e) {
		e.preventDefault();

		$('#h4_tit_productos').html('Productos');
		frm_det_productos.trigger('reset');
	
		$('#hid_cod_producto').val('');
		$('#div_det_productos').modal('show');
		
	});
	
	
	$('#href_editar_prod').click(function(e) {
		e.preventDefault();

		var indices = [];
		tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaProductoCache[indices[0]];
			$('#hid_cod_producto').val(obj.idDetaRacion);
			$('#sel_producto').val(obj.fkIdProducto);
			$('#txt_uni_pres').val(obj.pesoUnitarioPres);
			$('#txt_gr_aprox').val(obj.cantRacionKg); 
			
			$('#div_det_productos').modal('show');
		}
		
	});


	
	$('#href_exp_excel_prod').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_productos > tbody > tr').length;
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
		
		var idRacionOpe = racion.idRacionOpe;
		var url = VAR_CONTEXT + '/programacion-bath/racion/exportarExcelProducto/';
		url += verificaParametro(idRacionOpe);
		
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

	});

	
	$('#href_eliminar_prod').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_productos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idProducto = listaProductoCache[index].idDetaRacion;
				codigo = codigo + idProducto + '_';
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
							arrIdProducto : codigo
					};
			
					consultarAjax('POST', '/programacion-bath/racion/eliminarProductoRacion', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							llenarProductos(racion.idRacionOpe);
							addSuccessMessage(null, respuesta.mensajeRespuesta);							
						}
					});
				  swal(
					'Eliminado!',
					'Se ha eliminado Satisfactoriamente.',
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
//							arrIdProducto : codigo
//					};
//			
//					consultarAjax('POST', '/programacion-bath/racion/eliminarProductoRacion', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							llenarProductos(racion.idRacionOpe);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);							
//						}
//					});
//					
//				}	
//			});
			
		}
		
	});

	
});

function inicializarDatos() {
	
	$('#div_tabla_prod').hide();//ocultamos la tabla

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		if (!esnulo(racion.idRacionOpe)) {//editar
				$('#hid_cod_raciones').val(racion.idRacionOpe);//usamos paa el listado de detalle productos y documentos
			
				$('#sel_tipo_racion').val(racion.tipoRacion);
				$('#txt_cod_racion').val(racion.codRacion);
				$('#txt_nom_racion').val(racion.nombreRacion);
				$('#txt_num_dias').val(racion.diasAtencion);
				$('#txt_fecha').val(racion.fechaRacion);
				
				llenarProductos(racion.idRacionOpe);
//				listarDocumentoPedidoCompra(false);
				
				$('#div_tabla_prod').show();
			} else {//nuevo
				//inicializar los valores
				$('#txt_cod_racion').val(racion.codRacion);
				$('#txt_fecha').val(racion.fechaRacion);

			}
	}
	
}


function llenarProductos(codigo) {
		
			var params = { 
					idRacion : codigo
			};
			
			loadding(true);
			
			consultarAjax('GET', '/programacion-bath/racion/listarProductos', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
//					addSuccessMessage(null, respuesta.mensajeRespuesta);
					 listarProductos(respuesta);
				}
				loadding(false);
			});
	
}


function listarProductos(respuesta) {

	tbl_mnt_productos.dataTable().fnDestroy();
	tbl_mnt_productos.dataTable({
			data : respuesta,
			columns : [ {
					data : 'fkIdProducto',
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
					data : 'fkIdProducto',
					render : function(data, type, full, meta) {
						var row = meta.row + 1;
						return row;											
					}
				}, {data : 'nombProducto'}, 
				{data : 'pesoUnitarioPres',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 3)}, 
				{data : 'cantRacionKg',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 3)}
			],
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
				
				total_gramos = api.column(4, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);   
				}, 0 );
				
				
				$('#sp_tot_gramos').html(parseFloat(total_gramos).toFixed(2));
				
			},
				
			iDisplayLength : 15,
			aLengthMenu : [
				[15, 50, 100],
				[15, 50, 100]
			],
			columnDefs : [
				{ width : '55%', targets : 2 },
				{ width : '15%', targets : 3 },
				{ width : '15%', targets : 4 }
			]
		});
		
	listaProductoCache = respuesta;

	}


function listarDetalleRequerimiento(respuesta) {

	tbl_mnt_racion_ope.dataTable().fnDestroy();
	tbl_mnt_racion_ope.dataTable({
			data : respuesta,
			columns : [ {
					data : 'idEmergencia',
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
					data : 'idEmergencia',
					render : function(data, type, full, meta) {
						var row = meta.row + 1;
						return row;											
					}
				}, {data : 'desDepartamento'}, 
				{data : 'desProvincia'}, 
				{data : 'desDistrito'}, 
				{data : 'idEmergencia'},
				{data : 'poblacionINEI'},
				{data : 'famAfectado'},
				{data : 'famDamnificado'},
				{data : 'totalFam'},
				{data : 'persoAfectado'},
				{data : 'persoDamnificado'},
				{data : 'totalPerso'},
				{data : 'famAfectadoReal'},
				{data : 'famDamnificadoReal'},
				{data : 'totalFamReal'},
				{data : 'persoAfectadoReal'}, 
				{data : 'persoDamnificadoReal'}, 
				{data : 'totalPersoReal'}
			],
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
				{ width : '15%', targets : 3 },
				{ width : '15%', targets : 4 },
				{ width : '15%', targets : 5 }
			]
		});
		
	listaRacionCache = respuesta;

	}
