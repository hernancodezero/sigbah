var listaDonacionesCache = new Object();
var listaRegionesCache = new Object();

var tbl_mnt_con_donaciones = $('#tbl_mnt_con_donaciones');
var tbl_det_estados = $('#tbl_det_estados');
var tbl_regiones = $('#tbl_regiones');
var frm_con_donaciones = $('#frm_con_donaciones');
var frm_nue_estado = $('#frm_nue_estado');


$(document).ready(function() {
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	frm_con_donaciones.bootstrapValidator({
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
	
	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();
		
		loadding(true);					
		var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/mantenimientoDonaciones/0';
		
	
		$(location).attr('href', url);
		
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
	
		var bootstrapValidator = frm_con_donaciones.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var params = { 
				codigoAnio : $('#sel_anio').val(),
				codigoMes : $('#sel_mes').val(),
				idMovimiento : $('#sel_movimiento').val() 
			};
			
			loadding(true);
		
			consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarDonaciones', params, function(respuesta) {
			
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarDonacionIngreso(respuesta);
				}
				loadding(false);
			});
			
		}
		
	});
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
			var estado = ''
			tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				 estado = listaDonacionesCache[index].idEstado;
				var idIngreso = listaDonacionesCache[index].idIngreso;
				codigo = codigo + idIngreso + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			console.log("ESTADO: "+estado);
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			var obj = listaDonacionesCache[indices[0]];
			if(obj.nombreEstado=='Anulado'){
				addWarnMessage(null, "Esta donación está anulada");
			}else{
				loadding(true);
				var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/mantenimientoDonaciones/';
				$(location).attr('href', url + codigo);
			}
		}
		
	});
	
	
	$('#sel_estados_donacion').change(function() {
		var codigo = $(this).val();		
		
			if (codigo ==='2') {
				$('#divRegiones').show();
			} else {
				$('#divRegiones').hide();
			}			

	});
	
	$('#sel_region_donacion').change(function() {
		var codigo = $(this).val();		
		var idDonacion = $('#hid_est_documento').val();
		if (!esnulo(codigo)) {						
			var params = { 
				idRegion : codigo,
				idDonacion : idDonacion
			};			
			loadding(true);
			consultarAjax('POST', '/donaciones/registro-donaciones/insertarRegionXDonacion', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarRegionDonacion(idDonacion, true);
				}
				loadding(false);
				//frm_nue_estado.bootstrapValidator('revalidateField', 'sel_region_donacion');
			});
		} else {
			//$('#sel_personal_oficina').html('');
			//frm_nue_estado.bootstrapValidator('revalidateField', 'sel_region_donacion');
		}
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_con_donaciones > tbody > tr').length;
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
		
		var tab_text="<table border='2px'>";
	    var textRange; var j=0;
	    tab = document.getElementById('tbl_mnt_con_donaciones'); // id of table

	    for(j = 0 ; j < tab.rows.length ; j++) 
	    {     
	        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	    }

	    tab_text=tab_text+"</table>";
	    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
	    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
	    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

	    var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE "); 

	    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
	    {
	        txtArea1.document.open("txt/html","replace");
	        txtArea1.document.write(tab_text);
	        txtArea1.document.close();
	        txtArea1.focus(); 
	        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
	    }  
	    else                 //other browser not tested on IE 11
	        sa = window.open('data:application/vnd.ms-excel,' + escape(tab_text));  
	    loadding(false);
	    addInfoMessage(null, mensajeReporteExito);
	    return (sa);

	});
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var tipoDonacion = '';
			tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idDonacion = listaDonacionesCache[index].idIngreso;
				codigo = codigo + idDonacion + '_';
				
				var idTipoDon = listaDonacionesCache[index].idMovimiento;
				tipoDonacion = tipoDonacion + idTipoDon + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (!esnulo(tipoDonacion)) {
			tipoDonacion = tipoDonacion.substring(0, tipoDonacion.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			console.log("TIPO yDONACION: "+tipoDonacion);
			if(tipoDonacion=='11' || tipoDonacion=='12' || tipoDonacion=='13'){
				
				$('#hid_codigo_in').val(codigo);
				$('#hid_codigo_tipo').val(tipoDonacion);
				$('#chk_gui_remision').prop('checked', false);
				$('#chk_man_carga').prop('checked', false);
				$('#chk_act_ent_recepcion').prop('checked', false);
				$('#div_imp_pdf').modal('show');
			}else{

				loadding(true);
				var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/exportarPdf/'+codigo;
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
		}
	});
	
	$('#btn_exportar').click(function(e) {
		e.preventDefault();
		var codigo = $('#hid_codigo_in').val();
		var tipo = $('#hid_codigo_tipo').val();
		var ind_gui = $('#chk_gui_remision').is(':checked') ? '1' : '0';
		var ind_man = $('#chk_man_carga').is(':checked') ? '1' : '0';
		var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/exportarPdfActa/'+codigo+'/'+tipo+'/'+ind_gui+'/'+ind_man;
		
		if ((ind_gui == '0' && ind_man == '0') || 
				(ind_gui == '1' && ind_man == '1') || 
				(ind_gui == '0' && ind_man == '0') ) {
			addWarnMessage(null, 'Debe de Seleccionar un tipo de reporte.');
			return;
		}
		loadding(true);
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
	});
	
	function cargarEstados() {
		e.preventDefault();
	
		var bootstrapValidator = frm_con_donaciones.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var params = { 
				codigoAnio : $('#sel_anio').val(),
				codigoDdi : $('#txt_cod_ddi').val(),
				codigoMes : $('#sel_mes').val(),
				codigoEstado : $('#sel_estado').val()
			};
			
			loadding(true);
		
			consultarAjax('GET', '/donaciones/registro-donaciones/listarDonaciones', params, function(respuesta) {
			
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarEstados(respuesta);
				}
				loadding(false);
			});
			
		}
		
	}
	
	$('#href_estado').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
			tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
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
//			loadding(true);
			$('#h4_tit_no_alimentarios').html('Nuevo Documento');
			$('#frm_det_documentos').trigger('reset');
			
			$('#sel_estados_donacion').val(0);
			$('#divRegiones').hide();
			$('#hid_cod_documento').val('');
			$('#hid_cod_act_alfresco').val('');
			$('#hid_cod_ind_alfresco').val('');
			$('#txt_sub_archivo').val(null);
			listarRegionDonacion(codigo, true);
			$('#hid_est_documento').val(codigo);
			$('#div_estado').modal('show');
			
		}
		
	});
	
	$('#btn_act_estado').click(function(e) {
		e.preventDefault();
	
		var msg = '';

		msg = 'Está seguro de actualizar el estado de la donación ?';

		
		$.SmartMessageBox({
			title : msg,
			content : '',
			buttons : '[Cancelar][Aceptar]'
		}, function(ButtonPressed) {
			if (ButtonPressed === 'Aceptar') {

				loadding(true);
				
				var params = { 
					idDonacion : $('#hid_est_documento').val(),
					idEstado : $('#sel_estados_donacion').val()
				};
		
				consultarAjax('POST', '/donaciones/registro-donaciones/actualizarEstadoDonacion', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						loadding(false);
						//listarDocumentoDonacion(true);
						addSuccessMessage(null, respuesta.mensajeRespuesta);							
					}
				});
				
			}	
		});
			
		
		
	});
	
	
});

function listarRegionDonacion(codigo, indicador) {
	var params = { 
		idDonacion : codigo
	};			
	consultarAjaxSincrono('GET', '/donaciones/registro-donaciones/listarRegionDonacion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleRegion(respuesta);
			if (indicador) {
				loadding(false);
			}
			if (respuesta != null && respuesta.length > 0) {
				$('#sel_tip_movimiento').prop('disabled', true);
			} else {
				$('#sel_tip_movimiento').prop('disabled', false);
			}
		}
	});
}

function listarDetalleRegion(respuesta) {

	tbl_regiones.dataTable().fnDestroy();
	
	tbl_regiones.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idDonacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreRegion'
		},{
			data : 'idRegion',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" class="btn btn-danger" onclick="eliminarRegion('+data+')">'+
								'<i class="fa fa-trash-o"></i>'+
						   '</button>';	
				} else {
					return '';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaRegionesCache = respuesta;

}

function eliminarRegion(codigo) {
	var msg = '';

		msg = 'Está seguro de eliminar la región ?';

	var idDonacion = $('#hid_est_documento').val();
	$.SmartMessageBox({
		title : msg,
		content : '',
		buttons : '[Cancelar][Aceptar]'
	}, function(ButtonPressed) {
		if (ButtonPressed === 'Aceptar') {

			loadding(true);
			
			var params = { 
				idRegion : codigo,
				idDonacion : idDonacion
			};
	
			consultarAjax('POST', '/donaciones/registro-donaciones/eliminarRegionDonacion', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					loadding(false);
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarRegionDonacion(idDonacion,true);
					addSuccessMessage(null, respuesta.mensajeRespuesta);							
				}
			});
			
		}	
	});
		
	
	
}

function inicializarDatos() {
	
  	$('#li_reg_donaciones_ingresos').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#li_reg_orden_ingresos').attr('class', 'active');
	$('#li_reg_orden_ingresos').closest('li').children('a');
	$('#divRegiones').hide();
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(donaIngreso.codigoAnio);
		listarTablaDonaciones();	
		
	}
}

function listarTablaDonaciones() {

	var params = { 
		codigoAnio : $('#sel_anio').val(),
		codigoMes : $('#sel_mes').val(),
		idMovimiento : $('#sel_movimiento').val() 
	};
	
	loadding(true);

	consultarAjax('GET', '/donacionesIngreso/registro-donacionesIngreso/listarDonaciones', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDonacionIngreso(respuesta);
		}
		loadding(false);
	});

}

function listarDonacionIngreso(respuesta) {

	tbl_mnt_con_donaciones.dataTable().fnDestroy();
	
	tbl_mnt_con_donaciones.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idIngreso',
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
			data : 'codigoAnio'
		}, {
			data : 'codigoMes'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nroOrdenIngreso'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nombreMovimiento'
		}, {
			data : 'nombreAlmacenProcedencia'
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
		]
	});
	
	listaDonacionesCache = respuesta;

}

function listarEstados(respuesta) {

	tbl_det_estados.dataTable().fnDestroy();
	
	tbl_det_estados.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDonacion',
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
			data : 'codigoAnio'
		}, {
			data : 'codigoDdi'
		}, {
			data : 'codigoDonacion'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nombrePais'
		}, {
			data : 'nombreDonante'
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
		]
	});
	
	listaDonacionesCache = respuesta;

}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}
