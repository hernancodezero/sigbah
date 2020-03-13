var listaProyectoManifiestoCache = new Object();

var tbl_mnt_pro_manifiesto = $('#tbl_mnt_pro_manifiesto');
var frm_pro_manifiesto = $('#frm_pro_manifiesto');

$(document).ready(function() {
	
	frm_pro_manifiesto.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_anio : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Año.'
					}
				}
			}
		}
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_pro_manifiesto.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var params = { 
				codigoAnio : $('#sel_anio').val(),
				codigoMes : $('#sel_mes').val(),
				idAlmacen : $('#sel_almacen').val(),
				codigoMovimiento : $('#sel_tip_movimiento').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/gestion-almacenes/proyecto-manifiesto/listarProyectoManifiesto', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarProyectoManifiesto(respuesta);
				}
				loadding(false);
			});
			
		}
		
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var idEstado = null;
		var anioIngreso = null;
		var mesIngreso = null;
		tbl_mnt_pro_manifiesto.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_pro_manifiesto.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProyectoManifiesto = listaProyectoManifiestoCache[index].idProyectoManifiesto;
				codigo = codigo + idProyectoManifiesto + '_';
				idEstado = listaProyectoManifiestoCache[index].idEstado;
				anioIngreso = listaProyectoManifiestoCache[index].codigoAnio;
				mesIngreso = listaProyectoManifiestoCache[index].codigoMes;
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
			
			var mes = $('#hid_mesActivo').val();
		    var anio =  $('#hid_anioActivo').val();    

		    if (mes != mesIngreso || anio != anioIngreso) {
		    	addWarnMessage(null, mensajeValidacionEdicionAnioMesCerrado);
		    	return;
		    }
			
			loadding(true);
			var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/mantenimientoProyectoManifiesto/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/mantenimientoProyectoManifiesto/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_pro_manifiesto > tbody > tr').length;
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
		var codigoMes = $('#sel_mes').val();
		var idAlmacen = $('#sel_almacen').val();
		var codigoMovimiento = $('#sel_tip_movimiento').val();
		var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/exportarExcel/';
		url += verificaParametro(codigoAnio) + '/';
		url += verificaParametro(codigoMes) + '/';
		url += verificaParametroInt(idAlmacen) + '/';
		url += verificaParametro(codigoMovimiento);
		
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
		tbl_mnt_pro_manifiesto.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_pro_manifiesto.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProyectoManifiesto = listaProyectoManifiestoCache[index].idProyectoManifiesto;
				codigo = codigo + idProyectoManifiesto + '_';
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
			var url = VAR_CONTEXT + '/gestion-almacenes/proyecto-manifiesto/exportarPdf/'+codigo;
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
	$('#ul_alm_salidas').css('display', 'block');	
	$('#li_man_carga').attr('class', 'active');
	$('#li_man_carga').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(proyectoManifiesto.codigoAnio);
		$('#sel_mes').val(proyectoManifiesto.codigoMes);
		$('#sel_almacen').val(proyectoManifiesto.idAlmacen);
		$('#sel_almacen').prop('disabled', true);
		$('#btn_buscar').click();
	}
}

function listarProyectoManifiesto(respuesta) {

	tbl_mnt_pro_manifiesto.dataTable().fnDestroy();
	
	tbl_mnt_pro_manifiesto.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idProyectoManifiesto',
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
			data : 'idProyectoManifiesto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codigoAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nroProyectoManifiesto'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nroProgramacion'
		}, {
			data : 'nombreMovimiento'
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
		columnDefs : [
  			{ width : '10%', targets : 3 },
  			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 7 },
			{ width : '15%', targets : 8 }
  		]
	});
	
	listaProyectoManifiestoCache = respuesta;

}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}