var listaPedidoCompraCache = new Object();
var tbl_mnt_ped_compra = $('#tbl_mnt_ped_compra');
var frm_pedi_compra = $('#frm_pedi_compra');

$(document).ready(function() {

	inicializarDatos();
	
	$('#btn_aceptar').click(function(e) {
		e.preventDefault();

		cargarPedidoCompra(true);
			
		
	});
	

	$('#href_nuevo').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/pedido/mantenimientoPedido/0';
		$(location).attr('href', url);
		
	});
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_ped_compra.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ped_compra.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idPedidoCom = listaPedidoCompraCache[index].idPedidoCom;
				codigo = codigo + idPedidoCom + '_';
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
			var url = VAR_CONTEXT + '/programacion-bath/pedido/exportarPdf/'+codigo;

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
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_ped_compra > tbody > tr').length;
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
		var codEstado = $('#sel_estado').val();
		
		
		var url = VAR_CONTEXT + '/programacion-bath/pedido/exportarExcel/';
		url += verificaParametro(codAnio) + '/';
		url += verificaParametro(codMes) + '/';
		url += verificaParametro(codEstado);
		
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
	
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_ped_compra.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ped_compra.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idPedido = listaPedidoCompraCache[index].idPedidoCom;
				codigo = codigo + idPedido + '_';
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
			var url = VAR_CONTEXT + '/programacion-bath/pedido/mantenimientoPedido/';
			$(location).attr('href', url + codigo);
		}
		
	});
	



	

	
});

function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_ped_compra').attr('class', 'active');
	$('#li_ped_compra').closest('li').children('a');
	
	$('#sel_anio').val(pedcompra.codAnio);
	if (indicador == '1') { // Retorno
		cargarPedidoCompra(true);
	} else {
		cargarPedidoCompra(new Object());
	}
	
}

function cargarPedidoCompra(indicador) {
	var params = { 
			codAnio : $('#sel_anio').val(),
			codMes : $('#sel_mes').val(),
			codEstado : $('#sel_estado').val()
		};
	
		if (indicador) {
			loadding(true);
		}
		
		loadding(true);
		
		consultarAjax('GET', '/programacion-bath/pedido/listarPedidosCompra', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarPedidoCompra(respuesta);
			}
			loadding(false);
		});
}

function listarPedidoCompra(respuesta) {

	tbl_mnt_ped_compra.dataTable().fnDestroy();
	
	
	tbl_mnt_ped_compra.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idPedidoCom',
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
			data : 'idPedidoCom',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codAnio'
		}, {
			data : 'nomMes'
		}, {
			data : 'numPedidoCompra'
		}, {
			data : 'fecPedido'
		}, {
			data : 'descripcion'
		}, {
			data : 'dee'
		} , {
			data : 'nomEstado'
		}],
		
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
	
	listaPedidoCompraCache = respuesta;

}
