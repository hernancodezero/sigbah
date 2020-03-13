var listaCierreStockCache = new Object();

var tbl_mnt_cie_stock = $('#tbl_mnt_cie_stock');

$(document).ready(function() {

	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var params = { 
			codigoAnio : $('#sel_anio').val(),
			idAlmacen : $('#sel_almacen').val()
		};
		
		loadding(true);
		
		consultarAjax('GET', '/gestion-almacenes/cierre-stock/listarCierreStock', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarCierreStock(respuesta);
			}
			loadding(false);
		});
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		var codigoMes = '';
		var idAlmacen = '';
		tbl_mnt_cie_stock.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_cie_stock.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var codigoAnio = listaCierreStockCache[index].codigoAnio;
				codigo = codigo + codigoAnio + '_';
				codigoMes = listaCierreStockCache[index].codigoMes;
				idAlmacen = listaCierreStockCache[index].idAlmacen;
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
			var url = VAR_CONTEXT + '/gestion-almacenes/cierre-stock/mantenimientoCierreStock/';
			$(location).attr('href', url + codigo + '/' + codigoMes + '/' + idAlmacen);
		}
		
	});
	
});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_inventarios').css('display', 'block');	
	$('#li_cie_mensual').attr('class', 'active');
	$('#li_cie_mensual').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_anio').val(usuarioBean.codigoAnio);
		$('#sel_almacen').val(usuarioBean.idAlmacen);
		$('#sel_almacen').prop('disabled', true);
//		if (indicador == '1') { // Retorno
			$('#btn_buscar').click();
//		} else {
//			listarCierreStock(new Object());
//		}
	}
}

function listarCierreStock(respuesta) {

	tbl_mnt_cie_stock.dataTable().fnDestroy();
	
	tbl_mnt_cie_stock.dataTable({
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
			data : 'nombreAlmacen'
		}, {
			data : 'nombreMes'
		}, {
			data : 'responsable'
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
		columnDefs : [
			{ width : '20%', targets : 2 },
			{ width : '15%', targets : 3 },
			{ width : '30%', targets : 4 },
			{ width : '20%', targets : 5 }
		]
	});
	
	listaCierreStockCache = respuesta;

}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}