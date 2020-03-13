var listaRacionCache = new Object();
var tbl_mnt_rac_oper = $('#tbl_mnt_rac_oper');
var frm_racion_oper = $('#frm_racion_oper');

$(document).ready(function() {

	inicializarDatos();
	
	$('#btn_aceptar').click(function(e) {
		e.preventDefault();

		cargarRacion(true);
		
	});
	
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_rac_oper.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_rac_oper.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idRacionOpe = listaRacionCache[index].idRacionOpe; 
				codigo = codigo + idRacionOpe + '_';  
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
			var url = VAR_CONTEXT + '/programacion-bath/racion/mantenimientoRacion/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/racion/mantenimientoRacion/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_modal').click(function(e) {
		e.preventDefault();

		$('#div_composicion').modal('show');
		
	});
	
	$('#href_exp_copiar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var anio = '';
		var ddi = '';
		tbl_mnt_rac_oper.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_rac_oper.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idRacionOpe = listaRacionCache[index].idRacionOpe;
				codigo = codigo + idRacionOpe + '_';
				anio = listaRacionCache[index].codAnio;
				ddi = listaRacionCache[index].idDdi;
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

			var msg = '';
			msg = 'Está seguro que desea copiar esta Ración ?';
			swal({
				  title: 'Está seguro?',
				  text: msg,
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si, copiar!',
				  cancelButtonText: 'No',
				}).then(function () {
					loadding(true);
					var obj = listaRacionCache[indices.length-1];
					var params = { 
							codAnio : anio,
							idDdi : ddi,
							idRacionOpe: codigo	
						};
					loadding(true);
					
					consultarAjax('POST', '/programacion-bath/racion/copiarRacion', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							addSuccessMessage(null, respuesta.mensajeRespuesta);
							cargarRacion(true);
						} 
						loadding(false);
					});
				  swal(
				    'Copiado!',
				    'Se ha copiado la ración.',
				    'success'
				  )
				})

		}
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_rac_oper > tbody > tr').length;
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
		var codMesRacion = $('#sel_mes').val();
		var tipoRacion = $('#sel_tipo_racion').val();
		
		
		var url = VAR_CONTEXT + '/programacion-bath/racion/exportarExcelRaciones/';
		url += verificaParametro(codAnio) + '/';
		url += verificaParametro(codMesRacion) + '/';
		url += verificaParametro(tipoRacion);
		
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
	

	
});

function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_rac_ope').attr('class', 'active');
	$('#li_rac_ope').closest('li').children('a');
	
	$('#sel_anio').val(racione.codAnio);
	if (indicador == '1') { // Retorno
		cargarRacion(true);
	} else {
		cargarRacion(new Object());
	}
	
}

function cargarRacion(indicador) {
	
	var params = { 
		codAnio : $('#sel_anio').val(),
		codMesRacion : $('#sel_mes').val(),
		tipoRacion : $('#sel_tipo_racion').val()
	};
	
		if (indicador) {
			loadding(true);
		}
		
		loadding(true);
		
		consultarAjax('GET', '/programacion-bath/racion/listarRaciones', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarRacion(respuesta);
			}
			loadding(false);
		});
}


function listarRacion(respuesta) {

	tbl_mnt_rac_oper.dataTable().fnDestroy();
	
	
	tbl_mnt_rac_oper.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idRacionOpe',
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
			data : 'idRacionOpe',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codAnio'
		}, {
			data : 'nombreMesRacion'
		}, {
			data : 'codRacion'
		}, {
			data : 'fechaRacion'
		}, {
			data : 'tipoRacion'
		}, {
			data : 'nombreRacion'
		}, {
			data : 'diasAtencion',sClass : 'opc-right'
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
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
	
	listaRacionCache = respuesta;

}
