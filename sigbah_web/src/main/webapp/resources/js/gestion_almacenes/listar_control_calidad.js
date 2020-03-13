var listaControlCalidadCache = new Object();

var tbl_mnt_con_calidad = $('#tbl_mnt_con_calidad');
var frm_con_calidad = $('#frm_con_calidad');

$(document).ready(function() {

	frm_con_calidad.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_anio : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Año.'
					}
				}
			},
			sel_ddi : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar DDI.'
					}
				}
			}
		}
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_con_calidad.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var params = { 
				codigoAnio : $('#sel_anio').val(),
				codigoMes : $('#sel_mes').val(),
				idAlmacen : $('#sel_almacen').val(),
				idTipoControl : $('#sel_tip_control').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/gestion-almacenes/control-calidad/listarControlCalidad', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarControlCalidad(respuesta);
				}
				loadding(false);
			});
			
		}
		
	});
	
	inicializarDatos();
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var idEstado = null;
		var fechaEmision = null;
		var anioIngreso = null;
		var mesIngreso = null;
		tbl_mnt_con_calidad.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_calidad.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idControlCalidad = listaControlCalidadCache[index].idControlCalidad;
				codigo = codigo + idControlCalidad + '_';
				idEstado = listaControlCalidadCache[index].idEstado;
				fechaEmision = listaControlCalidadCache[index].fechaEmision;
				anioIngreso = listaControlCalidadCache[index].codigoAnio;
				mesIngreso = listaControlCalidadCache[index].codigoMes;
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
			
			if (idEstado == ESTADO_ANULADO) {
				addWarnMessage(null, mensajeValidacionAnulado);
				return;
			}
			console.log(controlCalidad.codigoMes +"- "+controlCalidad.codigoAnio );
			var mes = $('#hid_mesActivo').val();
		    var anio =  $('#hid_anioActivo').val();  	
		    
		    if (mes != mesIngreso || anio != anioIngreso) {
		    	addWarnMessage(null, mensajeValidacionEdicionAnioMesCerrado);
		    	return;
		    }		
			
			loadding(true);
			var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/mantenimientoControlCalidad/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/mantenimientoControlCalidad/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_con_calidad > tbody > tr').length;
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
		var codigoDdi = $('#sel_ddi').val();
		var codigoAlmacen = $('#sel_almacen').val();
		var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/exportarExcel/';
		url += verificaParametro(codigoAnio) + '/';
		url += verificaParametro(codigoDdi) + '/';
		url += verificaParametro(codigoAlmacen);
		
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
		tbl_mnt_con_calidad.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_calidad.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idControlCalidad = listaControlCalidadCache[index].idControlCalidad;
				codigo = codigo + idControlCalidad + '_';
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
			var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/exportarPdf/'+codigo;
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
	
});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#li_con_calidad').attr('class', 'active');
	$('#li_con_calidad').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(controlCalidad.codigoAnio);
		$('#sel_mes').val(controlCalidad.codigoMes);
		$('#sel_ddi').val(controlCalidad.idDdi);
		$('#sel_almacen').val(controlCalidad.idAlmacen);
		$('#sel_ddi').prop('disabled', true);
		$('#sel_almacen').prop('disabled', true);
		$('#btn_buscar').click();
	}
}

function listarControlCalidad(respuesta) {

	tbl_mnt_con_calidad.dataTable().fnDestroy();
	
	tbl_mnt_con_calidad.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idControlCalidad',
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
			data : 'idControlCalidad',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codigoAnio',
			sClass : 'opc-center'
		}, {
			data : 'nombreMes',
			sClass : 'opc-center'
		}, {
			data : 'nombreDdi'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nroControlCalidad',
			sClass : 'opc-center'
		}, {
			data : 'fechaEmision',
			sClass : 'opc-center'
		}, {
			data : 'tipoControlCalidad'
		}, {
			data : 'nombreEstado',
			sClass : 'opc-center'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
	
	setTimeout(function () {
		centerHeader('#th_ddi');
		centerHeader('#th_almacen');
		centerHeader('#th_tip_control');
	}, 500);
	
	listaControlCalidadCache = respuesta;

}
