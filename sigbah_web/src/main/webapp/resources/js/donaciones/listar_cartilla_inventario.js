var listaCartillaInventarioCache = new Object();

var tbl_mnt_car_inventario = $('#tbl_mnt_car_inventario');

$(document).ready(function() {

	$('#btn_buscar').click(function(e) {
		e.preventDefault();
	
		listarCartillaInventario(true);
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idCartilla = listaCartillaInventarioCache[index].idCartilla;
				codigo = codigo + idCartilla + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			loadding(true);
			var url = VAR_CONTEXT + '/donaciones/cartilla-inventario/mantenimientoCartillaInventario/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/donaciones/cartilla-inventario/mantenimientoCartillaInventario/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_car_inventario > tbody > tr').length;
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
		
		var codigoAnio = $('#sel_anio').val();
		var idAlmacen = $('#sel_almacen').val();
		var url = VAR_CONTEXT + '/donaciones/cartilla-inventario/exportarExcel/';
		url += verificaParametro(codigoAnio);
		
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
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idCartilla = listaCartillaInventarioCache[index].idCartilla;
				codigo = codigo + idCartilla + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			loadding(true);
			var indicador = '1'; // Reporte Formato A
			var url = VAR_CONTEXT + '/donaciones/cartilla-inventario/exportarPdf/'+codigo+'/'+indicador;
			$.fileDownload(url).done(function(respuesta) {
				loadding(false);	
				if (respuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, mensajeReporteError);
				} else {
					addInfoMessage(null, mensajeReporteExito);
				}
			}).fail(function (respuesta) {
				loadding(false);
				if (respuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, mensajeReporteError);
				} else if (respuesta == NOTIFICACION_VALIDACION) {
					addWarnMessage(null, mensajeReporteValidacion);
				}
			});
		}
	});
	
	$('#href_estados').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var idEstado = '';
		tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_car_inventario.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idCartilla = listaCartillaInventarioCache[index].idCartilla;
				codigo = codigo + idCartilla + '_';
				idEstado = listaCartillaInventarioCache[index].idEstado; 
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			if(idEstado=='0'){
				addWarnMessage(null, 'Esta cartilla está Anulada.');
				return;
			}else if(idEstado=='4'){
				addWarnMessage(null, 'Esta Cartilla no está en trabajo.');
				return;
			}else if(idEstado=='14'){
				addWarnMessage(null, 'Esta Cartilla ya está cerrada.');
				return;
			}
			
			
			loadding(true);			
			var params = { 
				idCartilla : codigo
			};			
			consultarAjax('GET', '/donaciones/cartilla-inventario/obtenerEstadosCartillaInventario', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					$('#hid_cod_cartilla').val(codigo);
					$('#txt_observacion').val('');
					var options = '';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.idEstado+'">'+item.nombreEstado+'</option>';
			        });
			        $('#sel_estado').html(options);
			        
			        if(options == ''){
			        	addWarnMessage(null, 'Usted no tiene asignado el estado que sigue.');
			        }else{
			        	$('#div_gra_estado').modal('show');
			        }
					
				}
				loadding(false);
			});
		}

	});
	
	$('#btn_gra_estado').click(function(e) {
		e.preventDefault();
		
		var idEstado = $('#sel_estado').val();
		
		if (idEstado == ESTADO_ANULADO) {
			
			swal({
				  title: 'Está seguro?',
				  text: 'Está usted seguro?',
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si',
				  cancelButtonText: 'No',
				}).then(function () {
					cambiarEstado();
				  swal(
				    'Anulado!',
				    'Se ha anulado satisfactoriamente.',
				    'success'
				  )
				});
			
//			$.SmartMessageBox({
//				title : 'Está usted seguro ?',
//				content : '',
//				buttons : '[No][Si]'
//			}, function(ButtonPressed) {
//				if (ButtonPressed === 'Si') {
//
//					cambiarEstado();
//					
//				}	
//			});
			
		} else {
			
			cambiarEstado();
			
		}	
	});
	
});

function inicializarDatos() {
	
	$('#li_donaciones').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_inventarios').css('display', 'block');	
	$('#li_reg_donaciones_cartilla').attr('class', 'active');
	$('#li_reg_donaciones_cartilla').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(usuarioBean.codigoAnio);
		$('#sel_almacen').val(usuarioBean.idAlmacen);
		$('#sel_almacen').prop('disabled', true);
		$('#txt_fecha').val(get_date_form()); // Fecha actual del sistema
		if (indicador == '1') { // Retorno
			listarCartillaInventario(true);
		} else {
			listarCartillaInventario(new Object());
		}
	}
}

function listarCartillaInventario(indicador) {
	var params = { 
		codigoAnio : $('#sel_anio').val(),
	};
	if (indicador) {
		loadding(true);
	}
	consultarAjaxSincrono('GET', '/donaciones/cartilla-inventario/listarCartillaInventario', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleCartillaInventario(respuesta);
			if (indicador) {
				loadding(false);
			}
		}
	});
}

function listarDetalleCartillaInventario(respuesta) {

	tbl_mnt_car_inventario.dataTable().fnDestroy();
	
	tbl_mnt_car_inventario.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idCartilla',
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
			data : 'idCartilla',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nroCartilla'
		}, {
			data : 'fechaCartilla'
		}, {
			data : 'responsable'
		}, {
			data : 'itemInventariados'
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
		columnDefs : [
			{ width : '12%', targets : 3 },
			{ width : '12%', targets : 4 },
			{ width : '15%', targets : 5 }
		]
	});
	
	listaCartillaInventarioCache = respuesta;

}

function cambiarEstado() {
	var params = { 
		idCartilla : $('#hid_cod_cartilla').val(),
		idEstado : $('#sel_estado').val(),
		fechaEstado : $('#txt_fecha').val(),
		observacion : $('#txt_observacion').val()
	};
	
	loadding(true);
	
	consultarAjax('POST', '/donaciones/cartilla-inventario/grabarEstadoCartillaInventario', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {				
			listarCartillaInventario(false);
			addSuccessMessage(null, respuesta.mensajeRespuesta);
		}
		$('#div_gra_estado').modal('hide');
		loadding(false);
	});
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}

function  verEstadosUsuario(){
	loadding(false);
	if(indicador_estado=='0'){
		addWarnMessage(null, "Usted no tiene asignado el estado que sigue");
	}else{
		$('#div_estado').modal('show');
	}
	
}