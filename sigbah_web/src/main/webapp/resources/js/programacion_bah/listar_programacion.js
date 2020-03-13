var listaProgramacionCache = new Object();

var tbl_mnt_programacion = $('#tbl_mnt_programacion');
var frm_programacion = $('#frm_programacion');

var frm_gra_estado = $('#frm_gra_estado');

$(document).ready(function() {
	
	$("#sel_usuario_correo").select2({
		  placeholder: "Seleccione usuario",
		  allowClear: true,
		  multiple: true
		});

	frm_programacion.bootstrapValidator({
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
	
	frm_gra_estado.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_estado : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Estado.'
					}
				}
			}
		}
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_programacion.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			listarProgramacion(true);			
		}
		
	});
	
	inicializarDatos();
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var idEstado= '';
		tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProgramacion = listaProgramacionCache[index].idProgramacion;
				codigo = codigo + idProgramacion + '_';
				idEstado = listaProgramacionCache[index].idEstado;
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
			
			if (idEstado == '0') { // Anulado
				addWarnMessage(null, 'Esta programación esta Anulada');
				return;
			} 
//			else if (idEstado == '4') { // Denegado
//				addWarnMessage(null, 'Esta programacion esta Denegada (No Autorizada)');
//				return;
//			} else if (idEstado == '6') { // Atendida
//				addWarnMessage(null, 'Esta programacion ya esta Atendida');
//				return;
//			}
			
			loadding(true);
			var url = VAR_CONTEXT + '/programacion-bah/programacion/mantenimientoProgramacion/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bah/programacion/mantenimientoProgramacion/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_programacion > tbody > tr').length;
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
		var idDdi = usuarioBean.idDdi;
		var idFenomeno = $('#sel_fenomeno').val();
		var idEstado = $('#sel_estado').val();
		var url = VAR_CONTEXT + '/programacion-bah/programacion/exportarExcel/';
		url += verificaParametro(codigoAnio) + '/';
		url += verificaParametro(codigoMes) + '/';
		url += verificaParametroInt(idDdi) + '/';
		url += verificaParametroInt(idFenomeno) + '/';
		url += verificaParametroInt(idEstado);
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
		tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProgramacion = listaProgramacionCache[index].idProgramacion;
				codigo = codigo + idProgramacion + '_';
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
			var url = VAR_CONTEXT + '/programacion-bah/programacion/exportarPdf/'+codigo;
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
		$('#sel_usuario_correo').val(null).trigger('change');
		$('#btn_gra_estado').prop('disabled', false);
		$('#btn_can_estado').prop('disabled', false);
		var indices = [];
		var codigo = '';
		var nombreProgramacion = '';
		var idEstado = '';
		var nroProgramacion = '';
		tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_programacion.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProgramacion = listaProgramacionCache[index].idProgramacion;
				codigo = codigo + idProgramacion + '_';
				nombreProgramacion = listaProgramacionCache[index].nombreProgramacion;
				idEstado = listaProgramacionCache[index].idEstado;
				nroProgramacion = listaProgramacionCache[index].nroProgramacion;
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
			if (idEstado == '0') { // Anulado
				addWarnMessage(null, 'Esta programación esta Anulada');
				return;
			} else if (idEstado == '3') { // Denegado
				addWarnMessage(null, 'Esta programacion esta Denegada (No Autorizada)');
				return;
			} else if (idEstado == '6') { // Atendida
				addWarnMessage(null, 'Esta programacion ya esta Atendida');
				return;
			}
			var ind_estado = false;
			loadding(true);			
			var params = { 
				idProgramacion : codigo
			};			
			consultarAjax('GET', '/programacion-bah/programacion/obtenerEstadosProgramacion', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					$('#hid_cod_programacion').val(codigo);
					$('#txt_programacion').val(nroProgramacion+" - "+nombreProgramacion);
					$('#hid_nro_programacion').val(nroProgramacion);
					
					$('#txt_observacion').val('');
					var options = '';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.idEstado+'">'+item.nombreEstado+'</option>';
			        });
			        $('#sel_pro_estado').html(options);
			        if (!esnulo($('#sel_pro_estado').val())) {
			        	ind_estado = true;			        	
			        } else {
			        	ind_estado = false;
			        	addWarnMessage(null, 'El usuario no tiene asignado el Estado que sigue.');
			        }
					
				}
				loadding(false);
				if (ind_estado) {
					$('#div_gra_estado').modal('show');
				}
			});
		}

	});
	
	$('#btn_gra_estado').click(function(e) {
		e.preventDefault();

		var bootstrapValidator = frm_gra_estado.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var idEstado = $('#sel_pro_estado').val();
			if (idEstado == ESTADO_ANULADO) {	
				swal({
				  title: 'Está seguro?',
				  text: 'Está seguro que desea cambiar de estado?',
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Si',
				  cancelButtonText: 'No',
				}).then(function () {
				  cambiarEstado();
				  swal(
				    'Actualizado!',
				    'El estado se ha actualizado.',
				    'success'
				  )
				});
				
//				$.SmartMessageBox({
//					title : 'Esa seguro que desea cambiar de estado ?',
//					content : '',
//					buttons : '[No][Si]'
//				}, function(ButtonPressed) {
//					if (ButtonPressed === 'Si') {	
//						cambiarEstado();						
//					}	
//				});				
			} else {				
				cambiarEstado();				
			}
		}
	});
	
	$('#btn_gra_correo').click(function(e) {
		e.preventDefault();

		var cadenUsu = $('#sel_usuario_correo').val();
		if(cadenUsu.length>5){
			addErrorMessage(null, "Sólo se puede enviar a 5 correos");
			return;
		}
		var usua = '';
		
		for (var i=0; i<cadenUsu.length; i++) {
			usua = usua + cadenUsu[i] + ','; 
		}
		
		var params = { 
				idProgramacion : $('#hid_cod_programacion').val(),
				idEstado : $('#sel_pro_estado').val(),
				nombreEstado : $("#sel_pro_estado option:selected").text(),
				fechaProgramacion : $('#txt_fecha').val(),
				nroProgramacion : $('#hid_nro_programacion').val(),
				nombreProgramacion : $('#txt_programacion').val(),
				usuariosCorreo : usua

			};
			loadding(true);
			
			consultarAjax('GET', '/programacion-bah/programacion/enviarCorreoProg', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					
					
				}
				loadding(false);
				$('#div_gra_estado').modal('hide');
				$('#div_correos').hide();
				swal({
					  title: 'Correo(s) enviado(s)',
					  text: '',
					  type: 'success',
					  showCancelButton: false,
					  
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Aceptar',
					  cancelButtonText: 'No',
					  closeOnClickOutside: false,
					}).then(function () {
						
						
					});
			});

		
	});
	
	$('#btn_can_estado, #btn_clo_estado').click(function(e) {
		e.preventDefault();
		frm_gra_estado.data('bootstrapValidator').resetForm();
	});
	
});

function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_programacion').attr('class', 'active');
	$('#li_programacion').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#div_correos').hide();
		$('#sel_anio').val(usuarioBean.codigoAnio);
		$('#sel_fenomeno').select2();
		$('#txt_fecha').val(get_date_form()); // Fecha actual del sistema
//		if (indicador == '1') { // Retorno
			$('#btn_buscar').click();
//		} else {
//			listarDetalleProgramacion(new Object());
//		}
			
	}
}

function listarProgramacion(indicador) {
	var params = { 
		codigoAnio : $('#sel_anio').val(),
		codigoMes : $('#sel_mes').val(),
		idDdi : usuarioBean.idDdi,
		idFenomeno : $('#sel_fenomeno').val(),
		idEstado : $('#sel_estado').val()
	};
	if (indicador) {
		loadding(true);
	}
	consultarAjaxSincrono('GET', '/programacion-bah/programacion/listarProgramacion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleProgramacion(respuesta);
			if (indicador) {
				loadding(false);
			}
		}
	});
}

function listarDetalleProgramacion(respuesta) {

	tbl_mnt_programacion.dataTable().fnDestroy();
	
	tbl_mnt_programacion.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idProgramacion',
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
			data : 'idProgramacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codigoAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'nroProgramacion'
		}, {
			data : 'fechaProgramacion'
		}, {
			data : 'nombreProgramacion'
		}, {
			data : 'nombreFenomeno'
		}, {
			data : 'nroDee'
		}, {
			data : 'nombreEstado'
		}, {
			data : 'nombreRegion'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		columnDefs : [
			{ width : '15%', targets : 6 },
			{ width : '15%', targets : 7 },
			{ width : '15%', targets : 8 }
		]
	});
	
	listaProgramacionCache = respuesta;

}

function cambiarEstado() {
	$('#div_correos').hide();
	var params = { 
		idProgramacion : $('#hid_cod_programacion').val(),
		idEstado : $('#sel_pro_estado').val(),
		fechaEstado : $('#txt_fecha').val(),
		observacion : $('#txt_observacion').val()
	};
	
	loadding(true);
	
	consultarAjax('POST', '/programacion-bah/programacion/grabarEstadoProgramacion', params, function(respuesta) {
//		$('#div_gra_estado').modal('hide');
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {				
			listarProgramacion(false);
			addSuccessMessage(null, respuesta.mensajeRespuesta);
			$('#btn_gra_estado').prop('disabled', true);
			$('#btn_can_estado').prop('disabled', true);
			$("#sel_mes > option").removeAttr("selected");
			$('#div_correos').show();
		}		
		frm_gra_estado.data('bootstrapValidator').resetForm();
		loadding(false);
	});
}