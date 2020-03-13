var listaStockAlmacenCache = new Object();

var tbl_mnt_sto_almacen = $('#tbl_mnt_sto_almacen');
var frm_sto_almacen = $('#frm_sto_almacen');

$(document).ready(function() {
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();

		var params = { 
			idAlmacen : $('#sel_almacen').val(),
			codigoCategoria : $('#sel_cat_producto').val(),
			nombreProducto : $('#txt_producto').val().toUpperCase(),
			cantidadStock : $('#sel_stock').val()
		};
		
		loadding(true);
		
		consultarAjax('GET', '/gestion-almacenes/stock-almacen/listarStockAlmacen', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarStockAlmacen(respuesta);
			}
			loadding(false);
		});

	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var tipoOrigen = '';
		var idAlmacen = '';		
		var idDdi = '';
		var codigo = '';
		tbl_mnt_sto_almacen.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_sto_almacen.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProducto = listaStockAlmacenCache[index].idProducto;
				codigo = codigo + idProducto + '_';
				tipoOrigen = listaStockAlmacenCache[index].tipoOrigen;
				idAlmacen = listaStockAlmacenCache[index].idAlmacen;
				idDdi = listaStockAlmacenCache[index].idDdi;
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
			var url = VAR_CONTEXT + '/gestion-almacenes/stock-almacen/mantenimientoStockAlmacen/';
			url = url + tipoOrigen + '/' + idAlmacen + '/' + idDdi + '/' + codigo;
			$(location).attr('href', url);
		}
		
	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_sto_almacen > tbody > tr').length;
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
		
		
		var idAlmacen = $('#sel_almacen').val();
		var codigoCategoria = $('#sel_cat_producto').val();
		var nombreProducto = $('#txt_producto').val().toUpperCase();
		var cantidadStock = $('#sel_stock').val();
		var url = VAR_CONTEXT + '/gestion-almacenes/stock-almacen/exportarExcel/';
		url += verificaParametroInt(idAlmacen) + '/';
		url += verificaParametro(codigoCategoria) + '/';
		url += verificaParametro(nombreProducto) + '/';
		url += verificaParametro(cantidadStock);
		
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
		var tipoOrigen = '';
		var idAlmacen = '';		
		var idDdi = '';
		var codigo = '';
		tbl_mnt_sto_almacen.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_sto_almacen.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idProducto = listaStockAlmacenCache[index].idProducto;
				codigo = codigo + idProducto + '_';
				tipoOrigen = listaStockAlmacenCache[index].tipoOrigen;
				idAlmacen = listaStockAlmacenCache[index].idAlmacen;
				idDdi = listaStockAlmacenCache[index].idDdi;
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
			var url = VAR_CONTEXT + '/gestion-almacenes/stock-almacen/exportarPdf/';
			url = url + tipoOrigen + '/' + idAlmacen + '/' + idDdi + '/' + codigo;
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

});

function inicializarDatos() {
	
	$('#li_ges_almacenes').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_inventarios').css('display', 'block');	
	$('#li_sto_productos').attr('class', 'active');
	$('#li_sto_productos').closest('li').children('a');

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		$('#sel_almacen').val(usuarioBean.idAlmacen);
		$('#sel_almacen').prop('disabled', true);
//		if (indicador == '1') { // Retorno
			$('#btn_buscar').click();
//		} else {
//			listarStockAlmacen(new Object());		
//		}
	}
}

function listarStockAlmacen(respuesta) {

	tbl_mnt_sto_almacen.dataTable().fnDestroy();
	
	tbl_mnt_sto_almacen.dataTable({
		data : respuesta,
		columns : [ {
			data : 'codigoProducto',
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
			data : 'codigoProducto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'codigoProducto'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidadMedida'
		}, {
			data : 'nombreEnvase'
		}, {
			data : 'precioUnitarioPromedio',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 6)
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		columnDefs : [
  			{ width : '25%', targets : 4 }
  		]
	});
	
	listaStockAlmacenCache = respuesta;

}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}