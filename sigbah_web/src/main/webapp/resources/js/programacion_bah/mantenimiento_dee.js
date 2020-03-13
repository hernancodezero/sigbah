
var listaUbigeosCache = new Object();
var listarDistritosEmergenciaCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var frm_det_prog_ubigeo = $('#frm_det_prog_ubigeo');
var tbl_mnt_distritos = $('#tbl_mnt_distritos'); 
var tbl_mnt_ubigeo = $('#tbl_mnt_ubigeo'); 

$(document).ready(function() {
	
	$('#mainChkBox').change(function(){
		var dat = this.checked;
		tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]').prop('checked', dat);
		});	
//	     $(':checkbox').prop('checked', this.checked);
		var indices = [];
		tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		$('#txt_nro_selec_ubi').val(indices.length);
	});
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();

	$('#btn_grabar_dat_gen').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var fechaini = $('#txt_fecha_ini').val();  
			var fechafin = $('#txt_fecha_fin').val();  
			
			if (comparafecha(fechaini, fechafin)!='2') {
				addErrorMessage(null, "La fecha de Inicio debe ser menor que la fecha final");   
				return;
		    } 
			
			loadding(true);
			var codigo = $('#hid_cod_dee').val();
			console.log("ID DEE "+codigo);
			var params = {
					idDee :codigo,
					numDee :$('#txt_num_dee').val(),
					nomDee : $('#txt_des_dee').val(),
					fechaIni : $('#txt_fecha_ini').val(),  
					fechaFin : $('#txt_fecha_fin').val(),
					plazo : $('#txt_num_dias').val(),
					idEstado : $('#sel_estado').val(),
					motivo : $('#txt_motivo').val(),
					flgProrroga : $('input[name="rb_prorroga"]:checked').val(),
					observacion : $('#txt_observacion').val(),
					nombreArchivo :$('#txt_lee_sub_archivo').val()
			}
			
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
//						frm_det_documentos.data('bootstrapValidator').resetForm();
						loadding(false);
						addErrorMessage(null, mensajeCargaArchivoError);						
					} else {
						
						params.codigoArchivoAlfresco = resArchivo;

						grabarDatosGenerales(params);					
					}
				});
			} else { // Archivo no cargado
				params.codigoArchivoAlfresco = $('#hid_cod_act_alfresco').val();
				grabarDatosGenerales(params);				
			}
		}
	});

	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();
		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/decreto/inicio/1';
		$(location).attr('href', url);
		
	});
	
	$('#fil_sub_archivo').change(function(e) {
		e.preventDefault();
	    var file_name = $(this).val();
	    var file_read = file_name.split('\\').pop();
	    $('#txt_lee_sub_archivo').val($.trim(file_read).replace(/ /g, '_'));
	    if (esnulo($('#hid_cod_dee').val())) {
	    	$('#hid_cod_ind_alfresco').val('1'); // Nuevo registro
	    } else {
	    	$('#hid_cod_ind_alfresco').val('2'); // Registro modificado
	    }
	    frm_dat_generales.bootstrapValidator('revalidateField', 'txt_lee_sub_archivo');	    
	});
	
	$('#href_eli_archivo').click(function(e) {
		e.preventDefault();
		$('#hid_cod_act_alfresco').val('');
		$('#hid_cod_ind_alfresco').val('');
		$('#fil_sub_archivo').val(null);
		$('#txt_lee_sub_archivo').val('');
		
	});
	
	
	$('#href_ubi_nuevo').click(function(e) {
		e.preventDefault();
		frm_det_prog_ubigeo.trigger('reset');
		limpiarFormularioUbigeo();
		$('#hid_cod_producto').val('');//para nuevo
		$('#div_det_ubigeo').modal('show');
		
	});
	
	$('#sel_departamento_ubi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvinciaUbi(codigo, null);
		} else {
			$('#sel_provincia_ubi').html('');
		}
	});
	
	tbl_mnt_ubigeo.on('click', '.checkbox', function(e) {//Contador
		var indices = [];
		tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		$('#txt_nro_selec_ubi').val(indices.length);
	});
	
	$('#btn_aceptar_ubigeo').click(function(e) { 
		e.preventDefault();
		
		
		var bootstrapValidator = frm_det_prog_ubigeo.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var params = { 
					coddpto: $('#sel_departamento_ubi').val(),
					codprov : $('#sel_provincia_ubi').val()
				};
				loadding(true);
				consultarAjax('GET', '/programacion-bath/decreto/listarUbigeo', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarUbigeos(respuesta);
					}
					loadding(false);
				});
		}
		
	});

	$('#btn_pasar_distrito_ubigeo').click(function(e) {
		e.preventDefault();
		var indices = [];
		
		tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ubigeo.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else {
			loadding(true);
			var mensaje='';
			console.log("TAMAÑO "+indices.length);
			for (var i = 0; i < indices.length; i++) {
				var params = { 
						idDee :$('#hid_cod_dee').val(), 
						codDistrito : listaUbigeosCache[indices[i]].coddist
			
					};
				
				consultarAjax('GET', '/programacion-bath/decreto/pasarDistritosUbigeo', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						console.log("XXXX");
						cargarDistritosEmergencia(true);	
						mensaje=	respuesta.mensajeRespuesta;	
					}
					
				});
			}
			if(!esnulo(mensaje)){
				addSuccessMessage(null, mensaje);	
			}
			loadding(false);
		}
	});
	
	
	$('#href_ubi_eliminar').click(function(e) {
		e.preventDefault();
		
		var indices = [];
		var codigo = '';
		tbl_mnt_distritos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_distritos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
				var idDeeDetalle = listarDistritosEmergenciaCache[index].idDeeDetalle;
				codigo = codigo + idDeeDetalle + '_';
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
						arrIdDeeDetalle : codigo
					};
					consultarAjax('POST', '/programacion-bath/decreto/eliminarDistritoEmergencia', params, function(respuesta) {
						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
							loadding(false);
							addErrorMessage(null, respuesta.mensajeRespuesta);
						} else {
							cargarDistritosEmergencia(true);
							addSuccessMessage(null, respuesta.mensajeRespuesta);	
							loadding(false); 
						}
					});
				  swal(
					'Eliminado!',
					'Se eliminó satisfactoriamente.',
					'success'
				  )
				});
			
//			$.SmartMessageBox({
//				title : msg,
//				content : '',
//				buttons : '[Cancelar][Aceptar]'
//			}, function(ButtonPressed) {
//				if (ButtonPressed === 'Aceptar') {
//					loadding(true);
//					var params = { 
//						arrIdDeeDetalle : codigo
//					};
//					consultarAjax('POST', '/programacion-bath/decreto/eliminarDistritoEmergencia', params, function(respuesta) {
//						if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//							loadding(false);
//							addErrorMessage(null, respuesta.mensajeRespuesta);
//						} else {
//							cargarDistritosEmergencia(true);
//							addSuccessMessage(null, respuesta.mensajeRespuesta);	
//							loadding(false); 
//						}
//					});
//				}	
//			});
		}
	});
	

	$('#href_ubi_excel').click(function(e) {
		e.preventDefault();
		var row = $('#tbl_mnt_distritos > tbody > tr').length;
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
	
		var idDeeDetalle = $('#hid_cod_dee').val() ;
		var url = VAR_CONTEXT + '/programacion-bath/decreto/exportarExcelDistritosEmergencia/';
		url += verificaParametro(idDeeDetalle);
		
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

function cargarDistritosEmergencia(indicador) {
	var params = { 
			idDeeDetalle : $('#hid_cod_dee').val() 
	};			
	consultarAjaxSincrono('GET', '/programacion-bath/decreto/listarDistritosEmergencia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDistritosEmergencia(respuesta);
		}
	});
}

function listarDistritosEmergencia(respuesta) {

	tbl_mnt_distritos.dataTable().fnDestroy();
	tbl_mnt_distritos.dataTable({
			data : respuesta,
			columns : [ {
					data : 'idDeeDetalle',
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
					data : 'idDeeDetalle',
					render : function(data, type, full, meta) {
						var row = meta.row + 1;
						return row;											
					}
				}, {data : 'coddist'}, 
				{data : 'desdpto'}, 
				{data : 'desprov'}, 
				{data : 'desdist'}
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
		
	listarDistritosEmergenciaCache = respuesta;

	}

function listarUbigeos(respuesta) {

	tbl_mnt_ubigeo.dataTable().fnDestroy();
	tbl_mnt_ubigeo.dataTable({
			data : respuesta,
			columns : [ {
					data : 'coddist',
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
					data : 'coddist',
					render : function(data, type, full, meta) {
						var row = meta.row + 1;
						return row;											
					}
				}, {data : 'coddist'}, 
				{data : 'desprov'}, 
				{data : 'desdist'} 
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
				{ width : '15%', targets : 4 }
			]
		});
		
	listaUbigeosCache = respuesta;

	}

function cargarProvinciaUbi(codigo, codigoProvincia) {//reutilizamos de requerimiento
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/programacion-bath/requerimiento/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia_ubi').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia_ubi').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function grabarDatosGenerales(params) {
	loadding(true);
	
	consultarAjax('POST', '/programacion-bath/decreto/grabarDecreto', params, function(respuesta) {
		
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			loadding(false);			
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			if (!esnulo($('#hid_cod_dee').val())) { 
				console.log("DESPUES DE EDITAR: "+$('#hid_cod_dee').val());
				addSuccessMessage(null, respuesta.mensajeRespuesta);
				
			} else { 
				
				addSuccessMessage(null, respuesta.mensajeRespuesta);
				dee.idDee=respuesta.idDee;
				
				$('#btn_grabar_dat_gen').attr("disabled", true); //deshabilitamos boton grabar
				$('#hid_cod_dee').val(respuesta.idDee); //usado para el listado de los detalles
				$('#txt_num_dee2').val($('#txt_num_dee').val());
				$('#txt_des_dee2').val($('#txt_des_dee').val());
				$('#li_ubigeo').attr('class', ''); //activamos tab ubigeo
				$('#li_ubigeo').closest('li').children('a').attr('data-toggle', 'tab');
				console.log("DESPUES DE GRABAR: "+$('#hid_cod_dee').val());
			}
		}
		loadding(false);
	});
}

function inicializarDatos() {
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_dec_emer').attr('class', 'active');
	$('#li_dec_emer').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		if (!esnulo(dee.idDee)) {//editar
			$('#hid_cod_dee').val(dee.idDee);//usamos paa el listado de detalle productos y documentos
			
			$('#txt_num_dee').val(dee.numDee);
			$('#txt_des_dee').val(dee.nomDee);
			$('#txt_fecha_ini').val(dee.fechaIni);
			$('#txt_fecha_fin').val(dee.fechaFin);
			$('#txt_num_dias').val(dee.numDias);
			$('#sel_estado').val(dee.idEstado);
			$('#txt_motivo').val(dee.motivo);
			$('input[name=rb_prorroga][value="'+dee.flgProrroga+'"]').prop('checked', true);
			$('#txt_observacion').val(dee.observacion);
			$('#txt_lee_sub_archivo').val(dee.nombreArchivo);
			
			
			$('#txt_num_dee2').val(dee.numDee);
			$('#txt_des_dee2').val(dee.nomDee);
			
			cargarDistritosEmergencia(true);
			
			$('#li_ubigeo').attr('class', '');
			$('#li_ubigeo').closest('li').children('a').attr('data-toggle', 'tab');
			
		} else {//nuevo
			//inicializar los valores
			$('#txt_fecha_ini').val(dee.fechaIni);
			$('#txt_fecha_fin').val(dee.fechaFin);
			
			
			$('#li_ubigeo').addClass('disabled'); //Desactivamos tab ubigeo
			$('#li_ubigeo').closest('li').children('a').removeAttr('data-toggle');
			

		}
	}
	
}



function limpiarFormularioUbigeo() {
//	$('#sel_cat_producto').val('');
//	$('#sel_producto').val('');
//	$('#txt_unidad_med').val('');
//	$('#txt_cantidad').val('');
//	$('#txt_pre_unitario').val('');
//	$('#txt_imp_total').val('');
}		
