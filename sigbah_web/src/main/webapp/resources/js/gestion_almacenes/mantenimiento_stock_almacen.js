var listaLotesCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var tbl_det_lotes = $('#tbl_det_lotes');
var frm_det_lotes = $('#frm_det_lotes');

$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();
	colormesanio("mes_activo", "red");
	$('#btn_grabar').click(function(e) {
		e.preventDefault();

		var params = {
			idAlmacen : stockAlmacen.idAlmacen,
			idDdi : stockAlmacen.idDdi,
			idProducto : stockAlmacen.idProducto,				
			stockSeguridad : formatMonto($('#txt_sto_seguridad').val()),
			codigoAlmacen : usuarioBean.codigoAlmacen,
			idTipoEnvaseSecundario : $('#sel_env_secundario').val(),
			unidadesEnvaseSecundario : formatMonto($('#txt_uni_envase').val()),
			descripcionEnvaseSecundario : $('#txt_des_env_sec').val(),
			observacion : $('#txt_observaciones').val()
		};
		
		loadding(true);
		
		consultarAjax('POST', '/gestion-almacenes/stock-almacen/actualizarStockAlmacen', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				
				addSuccessMessage(null, respuesta.mensajeRespuesta);

			}
			loadding(false);
		});			

	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/gestion-almacenes/stock-almacen/inicio/1';
		$(location).attr('href', url);
		
	});
	
	$('#href_pro_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		tbl_det_lotes.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_det_lotes.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else if (indices.length > 1) {
			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
		} else {
			
			var obj = listaLotesCache[indices[0]];
			
			frm_det_lotes.trigger('reset');
			$('#txt_fec_vencimiento').val(obj.fechaVencimiento);
			$('#txt_fec_produccion').val(obj.fechaProduccion);
			$('#txt_fec_alta').val(obj.fechaAlta);
			$('#txt_lot_cantidad').val(obj.cantidad);
			$('#txt_lot_pre_unitario').val(obj.precioUnitario);
			$('#txt_lot_imp_total').val(obj.importeTotal);			
			$('#txt_lote').val(obj.nroLote);			
			$('#txt_lot_planta').val(obj.planta);
			if (!esnulo(obj.idMarca)) {
				$('#sel_lot_marca').val(obj.idMarca);
			}
			$('#txt_lot_nave').val(obj.nave);
			$('#txt_lot_area').val(obj.area);
			
			$('#div_edi_lotes').modal('show');
		}
		
	});
	
	$('#btn_gra_lote').click(function(e) {
		e.preventDefault();
		
		var params = { 
			idAlmacen : stockAlmacen.idAlmacen,
			idDdi : stockAlmacen.idDdi,
			idProducto : stockAlmacen.idProducto,				
			nroLote : $('#txt_lote').val(),
			fechaProduccion : $('#txt_fec_produccion').val(),
			fechaVencimiento : $('#txt_fec_vencimiento').val(),
			fechaAlta : $('#txt_fec_alta').val(),
			idMarca : $('#sel_lot_marca').val(),
			planta : $('#txt_lot_planta').val(),
			nave : $('#txt_lot_nave').val(),
			area : $('#txt_lot_area').val()
		};

		loadding(true);
		
		consultarAjax('POST', '/gestion-almacenes/stock-almacen/actualizarStockAlmacenLote', params, function(respuesta) {
			$('#div_edi_lotes').modal('hide');
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				loadding(false);
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarStockAlmacenLote(true);					
				addSuccessMessage(null, respuesta.mensajeRespuesta);					
			}
		});
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
		
		$('#txt_almacen').val(stockAlmacen.nombreAlmacen);
		$('#txt_nro_kardex').val(stockAlmacen.nroKardex);
		$('#txt_nom_producto').val(stockAlmacen.nombreProducto);
		$('#txt_codigo').val(stockAlmacen.codigoProducto);
		$('#txt_categoria').val(stockAlmacen.nombreCategoria);
		$('#txt_cod_siga').val(stockAlmacen.codigoSiga);
		$('#txt_uni_medida').val(stockAlmacen.nombreUnidadMedida);		
		$('#txt_env_primario').val(stockAlmacen.nombreEnvasePrimario);
		$('#txt_pes_uni_neto').val(stockAlmacen.pesoUnitarioNeto);
		$('#txt_pes_uni_bruto').val(stockAlmacen.pesoUnitarioBruto);
		$('#txt_largo').val(stockAlmacen.dimLargo);
		$('#txt_vol_uni_m3').val(stockAlmacen.volumenUnitario);
		$('#txt_alto').val(stockAlmacen.dimAlto);
		$('#txt_vol_tot_m3').val(stockAlmacen.volumenTotal);
		$('#txt_ancho').val(stockAlmacen.dimAncho);		
		$('#txt_stock').val(stockAlmacen.cantidadStock);
		$('#txt_pre_promedio').val(stockAlmacen.precioPromedio);
		$('#txt_sto_seguridad').val(stockAlmacen.stockSeguridad);
		if (!esnulo(stockAlmacen.idTipoEnvaseSecundario)) {
			$('#sel_env_secundario').val(stockAlmacen.idTipoEnvaseSecundario);
		}
		$('#txt_des_env_sec').val(stockAlmacen.descripcionEnvaseSecundario);
		$('#txt_uni_envase').val(stockAlmacen.unidadesEnvaseSecundario);
		$('#txt_can_envases').val(stockAlmacen.cantidadEnvases);
		$('#txt_uni_sueltas').val(stockAlmacen.unidadesSueltas);
		$('#txt_observaciones').val(stockAlmacen.observacion);
		
		obtenerProductoStockAlmacen(false);

		listarStockAlmacenLote(false);

	}
		
}

function obtenerProductoStockAlmacen(indicador) {
	if (indicador) {
		loadding(true);
	}
	var params = { 
		idAlmacen : stockAlmacen.idAlmacen,
		idDdi : stockAlmacen.idDdi,
		idProducto : stockAlmacen.idProducto
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/stock-almacen/obtenerProductoStockAlmacen', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {			
			$('#txt_producto').val(respuesta.nombreProducto);
			$('#txt_cod_producto').val(respuesta.codigoProducto);
			$('#txt_det_cod_siga').val(respuesta.codigoSiga);
			$('#txt_sto_total').val(respuesta.cantidadStock);
			$('#txt_det_uni_medida').val(respuesta.unidadMedida);
			$('#txt_det_tip_envase').val(respuesta.nombreEnvase);
			$('#txt_det_pre_promedio').val(respuesta.precioPromedio);			
			if (indicador) {
				loadding(false);
			}
		}
	});
}

function listarStockAlmacenLote(indicador) {
	var params = { 
		idAlmacen : stockAlmacen.idAlmacen,
		idDdi : stockAlmacen.idDdi,
		idProducto : stockAlmacen.idProducto
	};			
	consultarAjaxSincrono('GET', '/gestion-almacenes/stock-almacen/listarStockAlmacenLote', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleAlmacenLote(respuesta);
			if (indicador) {
				loadding(false);
			}
		}
	});
}

function listarDetalleAlmacenLote(respuesta) {

	tbl_det_lotes.dataTable().fnDestroy();
	
	tbl_det_lotes.dataTable({
		data : respuesta,
		columns : [ {
			data : 'nroLote',
			sClass : 'opc-center',
			render : function(data, type, row) {
				if (data != null) {
					return '<label class="checkbox">'+
								'<input type="checkbox"><i></i>'+
						   '</label>';	
				} else {
					return '';	
				}											
			}
		}, {	
			data : 'nroLote',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nroLote'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'precioUnitario',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 6)
		}, {
			data : 'importeTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'fechaVencimiento'
		}, {
			data : 'fechaProduccion'
		}, {
			data : 'fechaAlta'
		}, {
			data : 'planta'
		}, {
			data : 'nave'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : true
	});
	
	listaLotesCache = respuesta;

}

function colormesanio(name,color) {
	  var a = document.getElementById(name);
	  a.style.color = color;
}