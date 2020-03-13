var listaProductosSigaCache = new Object();

var tbl_productos_siga = $('#tbl_productos_siga');

var frm_catalogo_productos = $('#frm_catalogo_productos');

var frm_producto_siga = $('#frm_producto_siga');


$(document).ready(function() {
	
	inicializarDatos();
	 
	frm_producto_siga.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_categoria : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar categoría.'
					}
				}
			}

		}
	});
	
	$('#sel_buscar').change(function() {
		var codigo = $(this).val();		
		
			if (codigo == '1') {
			
				$('#div_buscar_nombre').show();
				$('#div_buscar_grupo').hide();
			} else {
				$('#div_buscar_nombre').hide();
				$('#div_buscar_grupo').show();
			}			
	
	});
	
	$('#sel_grupo').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarClase(codigo, null);
		} else {
			$('#sel_clase').html('');
		}
	});
	
	$('#sel_clase').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarFamilia(codigo, null);
		} else {
			$('#sel_familia').html('');
		}
	});

	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();
		
		loadding(true);					
		var url = VAR_CONTEXT + '/administracion/catalogo-productos/mantenimientoProductos/0';
		
	
		$(location).attr('href', url);
		
	});
	
	$('#href_nuevo_sig').click(function(e) {
		e.preventDefault();
		
		loadding(true);					
		var url = VAR_CONTEXT + '/administracion/catalogo-productos/mantenimientoProductosSiga/0';
		
	
		$(location).attr('href', url);
		
	});
	
	$('#btn_buscar_nom').click(function(e) {
		e.preventDefault();
	
		listarProductosSigaNom();
		
	});
	
	$('#btn_buscar_gru').click(function(e) {
		e.preventDefault();
	
		listarProductosSigaGru();
		
	});
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
			var estado = ''
				tbl_productos_siga.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_productos_siga.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idSalida = listaProductosSigaCache[index].codigoSiga;
				codigo = codigo + idSalida + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			console.log("ESTADO: "+estado);
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
			var obj = listaProductosSigaCache[indices[0]];
			$('#hid_id_siga').val(obj.codigoSiga);
			$('#txt_nom_pro').val(obj.nombreProducto);
			$('#hid_grupo_bien').val(obj.grupoBien);
			$('#hid_clase_bien').val(obj.claseBien);
			$('#hid_familia_bien').val(obj.familiaBien);
			$('#hid_item_bien').val(obj.itemBien);
			$('#div_agregar_siga').modal('show');
//			loadding(true);
//			var url = VAR_CONTEXT + '/administracion/catalogo-productos/mantenimientoProductos/';
//			$(location).attr('href', url + codigo);
		}
		
	});
	
	$('#btn_grabar_siga').click(function(e) {
		e.preventDefault();

		var bootstrapValidator = frm_producto_siga.data('bootstrapValidator');
		
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			var params = {
					codigoSiga : $('#hid_id_siga').val(),
					nombreProducto : $('#txt_nom_pro').val(),
					grupoBien : $('#hid_grupo_bien').val(),
					claseBien: $('#hid_clase_bien').val(), 
					familiaBien: $('#hid_familia_bien').val(), 
					itemBien : $('#hid_item_bien').val(), 
					idCategoria :  $('#sel_categoria').val(), 
					estado : 'A', 
			};
			
			loadding(true);
			
			consultarAjax('POST', '/administracion/catalogo-productos/grabarCatalogoProductoSIGA', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					if (respuesta.codigoRespuesta == NOTIFICACION_VALIDACION) {
						addSuccessMessage(null, respuesta.mensajeRespuesta);
					}if (respuesta.codigoRespuesta == NOTIFICACION_OK) {
						addSuccessMessage(null, respuesta.mensajeRespuesta);
					}
						
						$('#div_agregar_siga').modal('hide');
				}
				loadding(false);
			});			
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
			addWarnMessage(null, 'No se encuentran registros para generar el reporte.');
			return;
		}

		loadding(true);
		
		var codigoAnio = $('#sel_anio').val();
		var codigoDdi = $('#txt_cod_ddi').val();
		var codigoMes = $('#sel_mes').val();
		var codigoMov = $('#sel_movimiento').val();
		var url = VAR_CONTEXT + '/donacionesIngreso/registro-donacionesIngreso/exportarExcel/';
		url += verificaParametro(codigoAnio) + '/';
		url += verificaParametro(codigoDdi) + '/';
		url += verificaParametro(codigoMes) + '/';
		url += verificaParametro(codigoMov);
		
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
		var codigo = ''
			tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idDonacion = listaDonacionesCache[index].idSalida;
				codigo = codigo + idDonacion + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
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
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/administracion/catalogo-productos/inicio';
		$(location).attr('href', url);
		
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
	
  	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#ul_adm_catalogo').css('display', 'block');
	$('#li_adm_catalogo').attr('class', 'active');
	$('#li_adm_catalogo').closest('li').children('a');

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#div_buscar_nombre').show();
		//listarTablaProductos();	
		
	}
}

function listarProductosSigaNom() {

	var	params = { 
				nombreProducto : $('#txt_nombre').val()
			};
	loadding(true);

	consultarAjax('GET', '/administracion/catalogo-productos/listarProductosSigaNom', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarProductos(respuesta);
		}
		loadding(false);
	});
}

function listarProductosSigaGru(indicador) {
	var params = { 
				grupoBien : $('#sel_grupo').val(),
				claseBien : $('#sel_clase').val(),
				familiaBien : $('#sel_familia').val()
			};
	loadding(true);

	consultarAjax('GET', '/administracion/catalogo-productos/listarProductosSigaGru', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarProductos(respuesta);
		}
		loadding(false);
	});
}

function listarProductos(respuesta) {

	tbl_productos_siga.dataTable().fnDestroy();
	
	tbl_productos_siga.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codigoSiga',
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
			data : 'nombreProducto'
		}, {
			data : 'codigoSiga'
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
	
	listaProductosSigaCache = respuesta;

}

function cargarClase(codigo, codigoProvincia) {
	var params = { 
		vcodigo : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/catalogo-productos/listarClases', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_clase').html(options);
//			if (codigoProvincia != null) {
//				$('#sel_provincia_per_ext').val(codigoProvincia);       	
//			}
		}
		loadding(false);
	});
}

function cargarFamilia(codigo, codigoDistrito) {
	var params = { 
		vcodigo : $('#sel_grupo').val(),
		vcodigoParam2 : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/catalogo-productos/listarFamilias', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_familia').html(options);
//			if (codigoDistrito != null) {
//				$('#sel_distrito_per_ext').val(codigoDistrito);       	
//			}
		
		}
		loadding(false);
	});
}