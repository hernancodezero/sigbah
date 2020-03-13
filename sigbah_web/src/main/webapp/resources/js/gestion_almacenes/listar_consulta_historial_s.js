var listaDonacionesCache = new Object();

var tbl_historial = $('#tbl_historial');
var frm_historial = $('#frm_historial');

$(document).ready(function() {
	
	frm_historial.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_mes_ini : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes de Inicio.'
					}
				}
			},
			
			sel_mes_fin : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes de Fin.'
					}
				}
			},
			
			sel_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Movimiento.'
					}
				}
			}
			
			
		}
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_historial.data('bootstrapValidator');
		if ($('#sel_mes_fin').val() < $('#sel_mes_ini').val()) {
	    	addWarnMessage(null, "Mes de inicio no puede ser mayor a mes final");
	    	return;
		}
		
		var bootstrapValidator = frm_historial.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var params = { 
				
				idDdi : $('#sel_ddi').val(),
				idAlmacen : $('#sel_almacen').val(),
				codigoAnio : $('#sel_anio').val(),
				codigoMesInicio : $('#sel_mes_ini').val(),
				codigoMesFin : $('#sel_mes_fin').val(),
				idMovimiento : $('#sel_movimiento').val(),
				nombreProducto : $('#txt_nom_pro').val(),
				idEstado : $('#sel_estado').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/gestion-almacenes/consulta/listarHistorialSalida', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarHistorialDonaciones(respuesta);
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
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			loadding(true);
			var url = VAR_CONTEXT + '/donaciones/guia-remision/mantenimientoGuiaRemision/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_gui_remision > tbody > tr').length;
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
		var idMovimiento = $('#sel_tip_movimiento').val();
		var url = VAR_CONTEXT + '/donaciones/guia-remision/exportarExcel/';
		url += verificaParametro(codigoAnio) + '/';
		url += verificaParametro(codigoMes) + '/';
		url += verificaParametroInt(idMovimiento);
		console.log("ENTRO ");
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
		tbl_historial.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_historial.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idDonacion = listaDonacionesCache[index].idDonacion;
				codigo = codigo + idDonacion + '_';
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
			var url = VAR_CONTEXT + '/donacionesSalida/registro-donacionesSalida/exportarPdf/'+codigo;
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
	
	$('#sel_ddi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarAlmacen(codigo, null);
		} else {
			$('#sel_almacen').html('');
		}
	});
	
});

function inicializarDatos() {

	$('#li_consultas_historial_almacen_s').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_consultas').css('display', 'block');
	$('#li_consultas_historial_almacen_s').attr('class', 'active');
	$('#li_consultas_historial_almacen_s').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
//		$('#sel_anio').val(guiaRemision.codigoAnio);
//		$('#sel_mes').val(guiaRemision.codigoMes);
//		$('#sel_almacen').val(guiaRemision.idAlmacen);
//		$('#sel_almacen').prop('disabled', true);
//		if (indicador == '1') { // Retorno
			$('#btn_buscar').click();
//		} else {
//			listarGuiaRemision(new Object());		
//		}
	}
}

function listarHistorialDonaciones(respuesta) {

	tbl_historial.dataTable().fnDestroy();
	
	tbl_historial.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codigoAnio',
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
			data : 'codigoAnio',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codigoAnio'
		}, {
			data : 'nombreDdi'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nroOrdenIngreso'
		}, {
			data : 'nombreMovimiento'
		}, {
			data : 'nombreProducto'
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
  			{ width : '14%', targets : 5 },
  			{ width : '14%', targets : 6 },
			{ width : '15%', targets : 7 },
			{ width : '14%', targets : 8 }
  		]
	});
	
	listaDonacionesCache = respuesta;

}

function cargarAlmacen(codigo, codigoProvincia) {
	var params = { 
		icodigo : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/donaciones/consulta/listarAlmacen', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="0">Todos</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_almacen').html(options);
			if (codigoProvincia != null) {
				$('#sel_almacen').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}