
$(document).ready(function() {
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		if ($('#sel_estado').is(':disabled')) {
			grabarDetalleCierreStock(false);
		} else {
			var idEstado = $('#sel_estado').val();
			if (idEstado == '1') { // Pendiente
				grabarDetalleCierreStock(false);
			} else { // Cerrado
				var msg = 'Esta usted seguro de efectuar el cierre del Mes: ';
				msg = msg + $('#txt_mes').val();
				
				swal({
					  title: 'Está seguro?',
					  text: msg,
					  type: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Si',
					  cancelButtonText: 'No',
					}).then(function () {
						grabarDetalleCierreStock(true);
					  swal(
						'Satisfactorio!',
						'Cierre de mes efectuado satisfactoriamente.',
						'success'
					  )
					}, function (dismiss) {
					  if (dismiss === 'cancel') {
						  retonarListadoCierreStock();
					  }
					});
				
//				$.SmartMessageBox({
//					title : msg,
//					content : '',
//					buttons : '[No][Si]'
//				}, function(ButtonPressed) {
//					if (ButtonPressed === 'Si') {
//						grabarDetalleCierreStock(true);						
//					}
//					if (ButtonPressed === 'No') {
//						retonarListadoCierreStock();
//					}
//				});
			}
		}

	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		retonarListadoCierreStock()
		
	});
	
});

function inicializarDatos() {
	
	$('#li_donaciones').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_inventarios').css('display', 'block');	
	$('#li_reg_donaciones_cierre').attr('class', 'active');
	$('#li_reg_donaciones_cierre').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {		
		
		$('#txt_almacen').val(cierreStock.nombreAlmacen);
		$('#txt_mes').val(cierreStock.nombreMes);
		$('#sel_responsable').val(cierreStock.idResponsable);
		$('#txt_observaciones').val(cierreStock.observacion);
		$('#txt_fecha').val(cierreStock.fecha);	
		if (cierreStock.nombreEstado == 'CERRADO') {
			$('#sel_estado').val(cierreStock.flagCierreAlmacen);
			$('#sel_estado').prop('disabled', true);
			$('#sel_responsable').prop('disabled', true);
			$('#txt_observaciones').prop('disabled', true);
			$('#btn_grabar').prop('disabled', true);
			$('#divFecha').show();
		} else if (cierreStock.nombreEstado == 'PENDIENTE') {
			$('#sel_estado').val(cierreStock.flagCierreAlmacen);
			$('#divFecha').hide();
		}

	}
	
}

function grabarDetalleCierreStock(indicador) {
	var params = {
		idAlmacen : cierreStock.idAlmacen,
		codigoAnio : cierreStock.codigoAnio,
		codigoMes : cierreStock.codigoMes,
		flagCierreAlmacen : $('#sel_estado').val(),
		idResponsable : $('#sel_responsable').val(),
		observacion : $('#txt_observaciones').val()
	};
	
	loadding(true);
	
	consultarAjax('POST', '/donaciones/cierre-stock/grabarCierreStock', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {					
			addSuccessMessage(null, respuesta.mensajeRespuesta);
		}
		
		if (indicador) {
			retonarListadoCierreStock()
		} else {
			loadding(false);
		}
	});
}

function retonarListadoCierreStock() {
	loadding(true);					
	var url = VAR_CONTEXT + '/donaciones/cierre-stock/inicio/1';
	$(location).attr('href', url);
}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}
