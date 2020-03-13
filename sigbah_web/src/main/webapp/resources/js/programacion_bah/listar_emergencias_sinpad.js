var listaEmergenciaSinpadCache = new Object();

var tbl_mnt_emer_sinpad = $('#tbl_mnt_emer_sinpad');
var frm_emer_sinpad = $('#frm_emer_sinpad');
var region='';
$(document).ready(function() {
	
	
	frm_emer_sinpad.bootstrapValidator({
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
			sel_region : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Región.'
					}
				}
			}
		}
	});
	
	$('#btn_aceptar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_emer_sinpad.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			if(esnulo(region)){
				region=$('#sel_region').val();
			}
			var params = { 
				codAnio : $('#sel_anio').val(),
				codMes : $('#sel_mes').val(),
				idRegion : region,
				idFenomeno : $('#sel_fenomeno').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/programacion-bath/emergencia/listarEmergencias', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarEmergencia(respuesta);
					region=$('#sel_region').val();
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
		var codigoAnio = '';
		tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idEmergencia = listaEmergenciaSinpadCache[index].idEmergencia;
				codigo = codigo + idEmergencia + '_';
				
				var codAnio = listaEmergenciaSinpadCache[index].codAnio;
				codigoAnio = codigoAnio + codAnio + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
			codigoAnio = codigoAnio.substring(0, codigoAnio.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			loadding(true);
			var region=$('#sel_region').val();
			var anio = $('#sel_anio').val();
			var mes = esnulo($('#sel_mes').val())?0:$('#sel_mes').val();
			var fenomeno = $('#sel_fenomeno').val();
			var url = VAR_CONTEXT + '/programacion-bath/emergencia/mantenimientoEmergencia/'+codigo+'/'+codigoAnio+'/'+region+'/'+anio+'/'+mes+'/'+fenomeno;
//			$(location).attr('href', url + codigo);
			$(location).attr('href', url );
			
		}
		
	});
	
//	$('#href_nuevo').click(function(e) {
//		e.preventDefault();
//
//		loadding(true);					
//		var url = VAR_CONTEXT + '/gestion-almacenes/control-calidad/mantenimientoControlCalidad/0';
//		$(location).attr('href', url);
//		
//	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_emer_sinpad > tbody > tr').length;
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
		
		var codAnio = $('#sel_anio').val();
		var codMes = $('#sel_mes').val();
		var codRegion = $('#sel_region').val();
		var codFenomeno = $('#sel_fenomeno').val();
		
		var url = VAR_CONTEXT + '/programacion-bath/emergencia/exportarExcel/';
		url += verificaParametro(codAnio) + '/';
		url += verificaParametro(codMes) + '/';
		url += verificaParametro(codRegion) + '/';
		url += verificaParametro(codFenomeno);
		
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
		var codAnio = '';
		tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idEmergencia = listaEmergenciaSinpadCache[index].idEmergencia;
				codigo = codigo + idEmergencia + '_';
				codAnio = listaEmergenciaSinpadCache[index].codAnio;
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
			var url = VAR_CONTEXT + '/programacion-bath/emergencia/exportarPdf/'+codigo+'/'+codAnio;
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
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_emer_sinpad').attr('class', 'active');
	$('#li_emer_sinpad').closest('li').children('a');
	

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {	
		$('#sel_anio').val(emergencia.codAnio);
		if (indicador == '1') { // Retorno
			$('#btn_aceptar').click();
			$('#sel_region').val(region);
		} else {
			listarEmergencia(new Object());
		}
	}
}

function listarEmergencia(respuesta) {

	tbl_mnt_emer_sinpad.dataTable().fnDestroy();
	
	tbl_mnt_emer_sinpad.dataTable({
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
		}, {
			data : 'codAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'fecha'
		}, {
			data : 'idEmergencia'
		}, {
			data : 'descFenomeno'
		}, {
			data : 'nombreEmergencia'
		}, {
			data : 'desDepartamento'
		}, {
			data : 'desProvincia'
		}, {
			data : 'desDistrito'
		}, {
//			data : 'poblacionInei'
//		}, {
			data : 'famAfectado',
			sClass : 'opc-right'
				
		}, {
			data : 'famDamnificado',
			sClass : 'opc-right'
		}, {
			data : 'totalFam',
			sClass : 'opc-right'
		}, {
			data : 'persoAfectado',
			sClass : 'opc-right'
		}, {
			data : 'persoDamnificado',
			sClass : 'opc-right'
		}, {
			data : 'totalPerso',
			sClass : 'opc-right'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : true, 
		paging : false,
		ordering : false,
		info : false,
		'footerCallback' : function ( row, data, start, end, display ) {
			var api = this.api(), data;	 
			
			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
			};
 			
			total_fam_afec = api.column(11, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_dam = api.column(12, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_fam = api.column(13, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per_afec = api.column(14, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per_dam = api.column(15, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per = api.column(16, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$('#sp_tot_fam_afec').html(parseFloat(total_fam_afec).toFixed(0));
			$('#sp_tot_fam_dam').html(parseFloat(total_fam_dam).toFixed(0));
			$('#sp_tot_fam').html(parseFloat(total_fam).toFixed(0));
			$('#sp_tot_per_afec').html(parseFloat(total_per_afec).toFixed(0));
			$('#sp_tot_per_dam').html(parseFloat(total_per_dam).toFixed(0));
			$('#sp_tot_per').html(parseFloat(total_per).toFixed(0));
		
		},
		iDisplayLength : 15, 
//		aLengthMenu : [
//			[15, 50, 100],
//			[15, 50, 100]
//		],
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
	
	listaEmergenciaSinpadCache = respuesta;

}
