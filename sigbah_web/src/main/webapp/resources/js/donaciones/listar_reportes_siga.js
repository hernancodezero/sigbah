var frm_rep_almacen = $('#frm_rep_almacen');
var frm_tip_reporte = $('#frm_tip_reporte');
var tbl_siga_e = $('#tbl_siga_e');
var tbl_siga_s = $('#tbl_siga_s');
var listaRepoSigaECache = new Object();
var listaRepoSigaSCache = new Object();
$(document).ready(function() {
	
	inicializarDatos();
	
	$('input[type=radio][name=rb_tip_reporte]').change(function() {
		var tipoReporte = this.value;
		frm_rep_almacen.data('bootstrapValidator').resetForm();
		if (tipoReporte != '5') { // Los cuatro primeros tipo de reportes
			var params = { 
				tipoReporte : tipoReporte
			};			
			loadding(true);
			$('#sel_producto').val('');
			$('#txt_nro_kardex').val('');
			$('#sel_nro_bincard').html('');
			$('#sel_producto').prop('disabled', true);
			$('#sel_nro_bincard').prop('disabled', true);
			$('#sel_mes_fin').prop('disabled', false);
			$('#sel_tip_movimiento').prop('disabled', false);
			$('#chk_inc_producto').prop('disabled', false);
			if ($('#sel_producto').hasClass('select2-hidden-accessible')) {
				$('#sel_producto').select2('destroy');
			}
			consultarAjax('GET', '/donaciones/reportes/listarTipoMovimiento', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '<option value="">Todos</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_tip_movimiento').html(options);
				}
				loadding(false);
			});
		} else { // Reporte de Kardex y Bincard
			$('#sel_mes_fin').val('');
			$('#sel_tip_movimiento').html('');
			$('#sel_producto').val($('#sel_producto option:first').val());
			$('#sel_producto').prop('disabled', false);
			$('#sel_nro_bincard').prop('disabled', false);			
			$('#sel_mes_fin').prop('disabled', true);
			$('#sel_tip_movimiento').prop('disabled', true);
			$('#chk_inc_producto').prop('disabled', true);
			$('#sel_producto').select2();
		}
		
    });
	
	$('#sel_mes_fin').change(function() {
		frm_rep_almacen.bootstrapValidator('revalidateField', 'sel_mes_inicio');
	});
	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();	
		cargarKardex(codigo);
		
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();

		var bootstrapValidatorRepAlmacen = frm_rep_almacen.data('bootstrapValidator');
		bootstrapValidatorRepAlmacen.validate();

		if (bootstrapValidatorRepAlmacen.isValid()) {
			loadding(true);
			
			var flag=null;
			if($('input[name=rb_tipo_movimiento]:checked').val()=='E'){
				flag='E';
			}else{
				if($('input[name=rb_tipo_movimiento]:checked').val()=='S'){
					flag='S';
				}
			}
			var params = { 
				tipoExportacion : flag,
				codigoAnio : $('#sel_anio').val(),
				codigoMes : $('#sel_mes').val()
			};	
			
			loadding(true);
			
			consultarAjax('GET', '/donaciones/reportes/exportarRepoSiga', params, function(respuesta) {
			
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					if(flag=="E"){
						$('#div_siga_e').show();
						$('#div_siga_s').hide();
						listarRepoSigaE(respuesta);
					}else{
						$('#div_siga_s').show();
						$('#div_siga_e').hide();
						listarRepoSigaS(respuesta);
					}
					
				}
				loadding(false);
			});
		}
	});
	
	$('#btn_exp_pdf').click(function(e) {
		e.preventDefault();

		var bootstrapValidatorRepAlmacen = frm_rep_almacen.data('bootstrapValidator');
		bootstrapValidatorRepAlmacen.validate();

		if (bootstrapValidatorRepAlmacen.isValid()) {
			loadding(true);
			var flag=null;
			if($('input[name=rb_tipo_movimiento]:checked').val()=='E'){
				flag='E';
			}else{
				if($('input[name=rb_tipo_movimiento]:checked').val()=='S'){
					flag='S';
				}
			}
			var params = { 
				tipoReporte : '6',
				tipoMovimiento : flag,
				anio : $('#sel_anio').val(),
				mesInicio : $('#sel_mes').val()
			};	
			
			var url = VAR_CONTEXT + '/donaciones/reportes/exportarPdf';
			$.fileDownload(url, {
			    httpMethod : 'GET',
			    data : params,
			    successCallback : function (respuesta, url) {
			    	loadding(false);	
					if (respuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, mensajeReporteError);
					} else if (respuesta == NOTIFICACION_VALIDACION) {
						addWarnMessage(null, mensajeReporteValidacion);
					} else {
						addInfoMessage(null, mensajeReporteExito);
					}
			    },
			    failCallback : function (respuesta, url) {
			    	loadding(false);
					if (respuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, mensajeReporteError);
					} else if (respuesta == NOTIFICACION_VALIDACION) {
						addWarnMessage(null, mensajeReporteValidacion);
					} else {
						addInfoMessage(null, mensajeReporteExito);
					}
			    }
			});
		}
	});
	
	$('#btn_exp_excel').click(function(e) {
		e.preventDefault();

		var bootstrapValidatorRepAlmacen = frm_rep_almacen.data('bootstrapValidator');
		bootstrapValidatorRepAlmacen.validate();

		if (bootstrapValidatorRepAlmacen.isValid()) {
			loadding(true);
			var flag=null;
			if($('input[name=rb_tipo_movimiento]:checked').val()=='E'){
				flag='E';
			}else{
				if($('input[name=rb_tipo_movimiento]:checked').val()=='S'){
					flag='S';
				}
			}
			var params = { 
				tipoReporte : '6',
				flagProducto : flag,
				anio : $('#sel_anio').val(),
				mesInicio : $('#sel_mes').val(),
				tipoMovimiento : '1',
				mesFin : $('#sel_mes').val(),
				codigoProducto : '1',
				nroLote : "1",
				codigoAlmacen : '1'
			};	
			
			var url = VAR_CONTEXT + '/donaciones/reportes/exportarExcel';
			$.fileDownload(url, {
			    httpMethod : 'GET',
			    data : params,
			    successCallback : function (respuesta, url) {
			    	loadding(false);	
					if (respuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, mensajeReporteError);
					} else if (respuesta == NOTIFICACION_VALIDACION) {
						addWarnMessage(null, mensajeReporteValidacion);
					} else {
						addInfoMessage(null, mensajeReporteExito);
					}
			    },
			    failCallback : function (respuesta, url) {
			    	loadding(false);
					if (respuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, mensajeReporteError);
					} else if (respuesta == NOTIFICACION_VALIDACION) {
						addWarnMessage(null, mensajeReporteValidacion);
					} else {
						addInfoMessage(null, mensajeReporteExito);
					}
			    }
			});
		}
	
	});
});
function inicializarDatos() {
	
	$('#li_donaciones').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_reportes').css('display', 'block');
	$('#li_don_rep_sig').attr('class', 'active');
	$('#li_don_rep_sig').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(usuarioBean.codigoAnio);		
		$('#sel_producto').val('');
		$('#sel_producto').prop('disabled', true);
		$('#sel_nro_bincard').prop('disabled', true);
		var tipoReporte = '5';
		if (tipoReporte != '5') { // Los cuatro primeros tipo de reportes
			var params = { 
				tipoReporte : tipoReporte
			};			
			loadding(true);
			$('#sel_producto').val('');
			$('#txt_nro_kardex').val('');
			$('#sel_nro_bincard').html('');
			$('#sel_producto').prop('disabled', true);
			$('#sel_nro_bincard').prop('disabled', true);
			$('#sel_mes_fin').prop('disabled', false);
			$('#sel_tip_movimiento').prop('disabled', false);
			$('#chk_inc_producto').prop('disabled', false);
			if ($('#sel_producto').hasClass('select2-hidden-accessible')) {
				$('#sel_producto').select2('destroy');
			}
			consultarAjax('GET', '/donaciones/reportes/listarTipoMovimiento', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var options = '<option value="">Todos</option>';
			        $.each(respuesta, function(i, item) {
			            options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			        });
			        $('#sel_tip_movimiento').html(options);
				}
				loadding(false);
			});
		} else { // Reporte de Kardex y Bincard
			$('#sel_mes_fin').val('');
			$('#sel_tip_movimiento').html('');
			$('#sel_producto').val($('#sel_producto option:first').val());
			$('#sel_producto').prop('disabled', false);
			$('#sel_nro_bincard').prop('disabled', false);			
			$('#sel_mes_fin').prop('disabled', true);
			$('#sel_tip_movimiento').prop('disabled', true);
			$('#chk_inc_producto').prop('disabled', true);
			cargarKardex($('#sel_producto').val());
			$('#sel_producto').select2();
		}
		
		
	}
	
	
}

function cargarKardex(codigo){
	if (!esnulo(codigo)) {
		var arr = codigo.split('_');
		if (arr.length > 1) {
			$('#txt_nro_kardex').val(arr[1]);
		} else {
			$('#txt_nro_kardex').val('');
		}
		loadding(true);
		var params = { 
			idDdi : usuarioBean.idDdi,
			idAlmacen : usuarioBean.idAlmacen,
			idProducto : arr[0]
		};
		consultarAjax('GET', '/gestion-almacenes/reporte-almacen/listarStockProductoLote', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				var options = '';
				if (respuesta.length > 0) {
					options = '<option value="">Todos</option>';
				}
		        $.each(respuesta, function(i, item) {
		            options += '<option value="'+item.nroLote+'">'+item.lote+'</option>';
		        });
		        $('#sel_nro_bincard').html(options);
			}
			loadding(false);
		});			
		
	} else {
		$('#txt_nro_kardex').val('');
	}
}

function listarRepoSigaE(respuesta) {

	tbl_siga_e.dataTable().fnDestroy();
	
	tbl_siga_e.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codigoAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'nombreMovimiento'			
		}, {
			data : 'nroOrdenIngreso'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'codigoSiga'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidad',
			sClass : 'opc-center'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'precio',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 6)
		}, {
			data : 'importe',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'nombreMarca'
		}, {
			data : 'fechaVencimiento'
		}, {
			data : 'nombreAlmacenOrigen'
		}, {
			data : 'nroOrdenSalida'
		}, {
			data : 'nroOrdenCompra'
		}, {
			data : 'razonSocial'
		}  ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,

	});
	
	listaRepoSigaECache = respuesta;

}

function listarRepoSigaS(respuesta) {

	tbl_siga_s.dataTable().fnDestroy();
	
	tbl_siga_s.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codigoAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'nombreMovimiento'			
		}, {
			data : 'nroOrdenSalida'
		}, {
			data : 'fechaEmision'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'codigoSiga'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidad',
			sClass : 'opc-center'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'precio',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 6)
		}, {
			data : 'importe',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'nombreMarca'
		}, {
			data : 'fechaVencimiento'
		}, {
			data : 'nombreAlmacenDestino'
		}, {
			data : 'destino'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,

	});
	
	listaRepoSigaSCache = respuesta;

}
