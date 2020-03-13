var frm_rep_almacen = $('#frm_rep_almacen');
var frm_tip_reporte = $('#frm_tip_reporte');

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
//		var codigo = $(this).val();		
//		if (!esnulo(codigo)) {
//			var arr = codigo.split('_');
//			if (arr.length > 1) {
//				$('#txt_nro_kardex').val(arr[1]);
//			} else {
//				$('#txt_nro_kardex').val('');
//			}
//			loadding(true);
//			var params = { 
//				idDdi : usuarioBean.idDdi,
//				idAlmacen : usuarioBean.idAlmacen,
//				idProducto : arr[0]
//			};
//			consultarAjax('GET', '/donaciones/reportes/listarStockProductoLote', params, function(respuesta) {
//				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//					addErrorMessage(null, respuesta.mensajeRespuesta);
//				} else {
//					var options = '';
//					if (respuesta.length > 0) {
//						options = '<option value="">Todos</option>';
//					}
//			        $.each(respuesta, function(i, item) {
//			            options += '<option value="'+item.nroLote+'">'+item.lote+'</option>';
//			        });
//			        $('#sel_nro_bincard').html(options);
//				}
//				loadding(false);
//			});			
//			
//		} else {
//			$('#txt_nro_kardex').val('');
//		}
	});
	
	$('#btn_exp_pdf').click(function(e) {
		e.preventDefault();

		var bootstrapValidatorRepAlmacen = frm_rep_almacen.data('bootstrapValidator');
		bootstrapValidatorRepAlmacen.validate();
		var bootstrapValidatorTipReporte = frm_tip_reporte.data('bootstrapValidator');
		bootstrapValidatorTipReporte.validate();
		if (bootstrapValidatorRepAlmacen.isValid() && bootstrapValidatorTipReporte.isValid()) {
			loadding(true);
			var codigoProducto = null;
			if (!esnulo($('#sel_producto').val())) {
				var arr = $('#sel_producto').val().split('_');
				codigoProducto = arr[0];
			}
			var params = { 
				tipoReporte : '2',
				anio : $('#sel_anio').val(),
				mesInicio : $('#sel_mes_inicio').val(),
				mesFin : verificaParametroInt($('#sel_mes_fin').val()),
				tipoMovimiento : verificaParametroInt($('#sel_tip_movimiento').val()),
				flagProducto : $('#chk_inc_producto').is(':checked') ? '1' : '0',
				codigoProducto : verificaParametroInt(codigoProducto),
				nroLote : verificaParametro($('#sel_nro_bincard').val()),
				codigoAlmacen : usuarioBean.idAlmacen
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
		var bootstrapValidatorTipReporte = frm_tip_reporte.data('bootstrapValidator');
		bootstrapValidatorTipReporte.validate();
		if (bootstrapValidatorRepAlmacen.isValid() && bootstrapValidatorTipReporte.isValid()) {
			loadding(true);
			var codigoProducto = null;
			if (!esnulo($('#sel_producto').val())) {
				var arr = $('#sel_producto').val().split('_');
				codigoProducto = arr[0];
			}		
			var params = { 
				tipoReporte : '2',
				anio : $('#sel_anio').val(),
				mesInicio : $('#sel_mes_inicio').val(),
				mesFin : verificaParametroInt($('#sel_mes_fin').val()),
				tipoMovimiento : verificaParametroInt($('#sel_tip_movimiento').val()),
				flagProducto : $('#chk_inc_producto').is(':checked') ? '1' : '0',
				codigoProducto : verificaParametroInt(codigoProducto),
				nroLote : verificaParametro($('#sel_nro_bincard').val()),
				codigoAlmacen : usuarioBean.idAlmacen
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
	
	$('#chk_inc_producto').change(function() {
        if(this.checked) {
        	$('#sel_producto').prop('disabled', false);
        }else{
        	$('#sel_producto').prop('disabled', true);
        }
    });
	
});

function inicializarDatos() {
	
	$('#li_donaciones').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_reportes').css('display', 'block');
	$('#li_don_rep_sal').attr('class', 'active');
	$('#li_don_rep_sal').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(usuarioBean.codigoAnio);		
		$('#sel_producto').val('0');
		$('#sel_producto').prop('disabled', true);
		$('#sel_nro_bincard').prop('disabled', true);
		
		
		var tipoReporte = '2';
//		frm_rep_almacen.data('bootstrapValidator').resetForm();
		if (tipoReporte != '5') { // Los cuatro primeros tipo de reportes
			var params = { 
				tipoReporte : tipoReporte
			};			
			loadding(true);
			$('#sel_producto').val('');
			$('#txt_nro_kardex').val('');
			$('#txt_nro_kardex').hide();
			$('#sel_nro_bincard').html('');
			$('#sel_nro_bincard').hide();
			$('#sel_producto').prop('disabled', true);
//			$('#sel_producto').hide();
			$('#sel_mes_fin').prop('disabled', false);
			$('#sel_tip_movimiento').prop('disabled', false);
			$('#chk_inc_producto').prop('disabled', false);
			if ($('#sel_producto').hasClass('select2-hidden-accessible')) {
				$('#sel_producto').select2('destroy');
			}
			$('#sel_producto').val('0');
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
		
		
		
		
	}
}
