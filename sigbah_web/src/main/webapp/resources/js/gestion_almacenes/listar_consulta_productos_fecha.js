var listaDonacionesCache = new Object();

var tbl_productos = $('#tbl_productos');
var tbl_historial = $('#tbl_historial');
var frm_historial = $('#frm_historial');

$(document).ready(function() {
	
	frm_historial.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_mes_ini : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes de Inicio.'
					}
				}
			},
			
			sel_mes_fin : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes de Fin.'
					}
				}
			},
			
			sel_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Movimiento.'
					}
				}
			}
			
			
		}
	});
	
//	$('#btn_buscar').click(function(e) {
//		e.preventDefault();
//		
//		
//		var bootstrapValidator = frm_historial.data('bootstrapValidator');
//		bootstrapValidator.validate();
//		if (bootstrapValidator.isValid()) {
//
//			var params = { 
//				
//				idDdi : $('#sel_ddi').val(),
//				idAlmacen : $('#sel_almacen').val(),
//				codigoAnio : $('#sel_anio').val(),
//				nombreProducto : $('#txt_nom_pro').val(),
//
//			};
//			
//			loadding(true);
//			
//			consultarAjax('GET', '/gestion-almacenes/consulta/listarProductosFecha', params, function(respuesta) {
//				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//					addErrorMessage(null, respuesta.mensajeRespuesta);
//				} else {
//					listarHistorialProductos(respuesta);
//				}
//				loadding(false);
//			});
//			
//		}
//		
//	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		listarTablaProductos()
		
	});
	
	inicializarDatos();
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = '';
		tbl_mnt_gui_remision.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_gui_remision.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idGuiaRemision = listaGuiaRemisionCache[index].idGuiaRemision;
				codigo = codigo + idGuiaRemision + '_';
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
			var url = VAR_CONTEXT + '/donaciones/guia-remision/mantenimientoGuiaRemision/';
			$(location).attr('href', url + codigo);
		}
		
	});
	
//	$('#href_exp_excel').click(function(e) {
//		e.preventDefault();
//		
//		var row = $('#tbl_productos > tbody > tr').length;
//		var empty = null;
//		$('tr.odd').each(function() {		
//			empty = $(this).find('.dataTables_empty').text();
//			return false;
//		});					
//		if (!esnulo(empty) || row < 1) {
//			addWarnMessage(null, mensajeReporteRegistroValidacion);
//			return;
//		}
//
//		loadding(true);
//		
//		var codigoAnio = $('#sel_anio').val();
//		var codigoMes = $('#sel_mes').val();
//		var idMovimiento = $('#sel_tip_movimiento').val();
//		var url = VAR_CONTEXT + '/donaciones/guia-remision/exportarExcel/';
//		url += verificaParametro(codigoAnio) + '/';
//		url += verificaParametro(codigoMes) + '/';
//		url += verificaParametroInt(idMovimiento);
//		console.log("ENTRO ");
//		$.fileDownload(url).done(function(respuesta) {
//			loadding(false);	
//			if (respuesta == NOTIFICACION_ERROR) {
//				addErrorMessage(null, mensajeReporteError);
//			} else {
//				addInfoMessage(null, mensajeReporteExito);
//			}
//		}).fail(function (respuesta) {
//			loadding(false);
//			addErrorMessage(null, mensajeReporteError);
//		});
//
//	});
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		var nomTabla="";
		nomTabla="tbl_productos";
		var row = $('#'+nomTabla+' > tbody > tr').length;
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

		var cabecera="<b><label class='col-sm-6 control-label'>"+$('#hid_titulo').val()+"</label></b>"
		var tab_text="<table border='2px'>";
	    var textRange; var j=0;
	    tab = document.getElementById(nomTabla); // id of table

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
	    tab_text=cabecera+tab_text;
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
	
//	$('#href_imprimir').click(function(e) {
//		e.preventDefault();
//
//		var indices = [];
//		var codigo = '';
//		tbl_historial.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
//			if (tbl_historial.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
//				indices.push(index);				
//				// Verificamos que tiene mas de un registro marcado y salimos del bucle
//				if (!esnulo(codigo)) {
//					return false;
//				}
//				var idDonacion = listaDonacionesCache[index].idDonacion;
//				codigo = codigo + idDonacion + '_';
//			}
//		});
//		
//		if (!esnulo(codigo)) {
//			codigo = codigo.substring(0, codigo.length - 1);
//		}
//		
//		if (indices.length == 0) {
//			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
//		} else if (indices.length > 1) {
//			addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
//		} else {
//			loadding(true);
//			var url = VAR_CONTEXT + '/donacionesSalida/registro-donacionesSalida/exportarPdf/'+codigo;
//			$.fileDownload(url).done(function(respuesta) {
//				loadding(false);	
//				if (respuesta == NOTIFICACION_ERROR) {
//					addErrorMessage(null, mensajeReporteError);
//				} else {
//					addInfoMessage(null, mensajeReporteExito);
//				}
//			}).fail(function (respuesta) {
//				loadding(false);
//				if (respuesta == NOTIFICACION_ERROR) {
//					addErrorMessage(null, mensajeReporteError);
//				} else if (respuesta == NOTIFICACION_VALIDACION) {
//					addWarnMessage(null, mensajeReporteValidacion);
//				}
//			});
//		}
//	});
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();
		var idDdi = +$('#sel_ddi').val();
		var idAlmacen = $('#sel_almacen').val();
		var nomDdi = $("#sel_ddi option:selected").text();
		var nomAlmacen = $("#sel_almacen option:selected").text();
		loadding(true);
		var url = VAR_CONTEXT + '/gestion-almacenes/consulta/exportarPdfFecha/'+idDdi+'/'+idAlmacen+'/'+nomDdi+'/'+nomAlmacen;
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
	
	$('#btn_exportar').click(function(e) {
		e.preventDefault();
		var codigo = $('#hid_codigo').val();
		var ind_gui = $('#chk_gui_remision').is(':checked') ? '1' : '0';
		var ind_man = $('#chk_man_carga').is(':checked') ? '1' : '0';
		var ind_act = $('#chk_act_ent_recepcion').is(':checked') ? '1' : '0';
		var url = VAR_CONTEXT + '/donaciones/guia-remision/exportarPdf/';
		url = url + codigo + '/' + ind_gui + '/'+ ind_man + '/' + ind_act;
		console.log("RUtass: "+url);		
		if ((ind_gui == '0' && ind_man == '0' && ind_act == '0') || 
				(ind_gui == '1' && ind_man == '1' && ind_act == '1') || 
				(ind_gui == '1' && ind_man == '1' && ind_act == '0') || 
				(ind_gui == '1' && ind_man == '0' && ind_act == '1') || 
				(ind_gui == '0' && ind_man == '1' && ind_act == '1') ) {
			addWarnMessage(null, 'Debe de Seleccionar solo un tipo reporterrrr.');
			return;
		}
		loadding(true);
		$.fileDownload(url).done(function(respuesta) {
//			$('#div_imp_pdf').modal('hide');
			loadding(false);	
			if (respuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, mensajeReporteError);
			} else {
				addInfoMessage(null, mensajeReporteExito);
			}
		}).fail(function (respuesta) {
//			$('#div_imp_pdf').modal('hide');
			loadding(false);
			if (respuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, mensajeReporteError);
			} else if (respuesta == NOTIFICACION_VALIDACION) {
				addWarnMessage(null, mensajeReporteValidacion);
			}
		});

	});
	
	$('#sel_ddi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarAlmacen(codigo, null);
		} else {
			$('#sel_almacen').html('');
		}
	});
	
});

function inicializarDatos() {

	$('#li_consultas_productos_fecha').addClass('active');
	$('#ul_ges_almacenes').css('display', 'block');
	$('#ul_alm_consultas').css('display', 'block');
	$('#li_consultas_productos_fecha').attr('class', 'active');
	$('#li_consultas_productos_fecha').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
//		$('#sel_anio').val(guiaRemision.codigoAnio);
//		$('#sel_mes').val(guiaRemision.codigoMes);
//		$('#sel_almacen').val(guiaRemision.idAlmacen);
//		$('#sel_almacen').prop('disabled', true);
//		if (indicador == '1') { // Retorno
			$('#btn_buscar').click();
//		} else {
//			listarGuiaRemision(new Object());		
//		}
	}
}

function listarHistorialProductos(respuesta) {

	tbl_historial.dataTable().fnDestroy();
	
	tbl_historial.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'nombreDdi'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nroLote'
		}, {
			data : 'cantidad1',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha1'
		}, {
			data : 'cantidad2',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha2'
		}, {
			data : 'cantidad3',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha3'
		}, {
			data : 'cantidad4',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha4'
		}, {
			data : 'cantidad5',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha5'
		}, {
			data : 'cantidad6',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha6'
		}, {
			data : 'cantidad7',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha7'
		}, {
			data : 'cantidad8',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha8'
		}, {
			data : 'cantidad9',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha9'
		}, {
			data : 'cantidad10',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha10'
		}, {
			data : 'cantidad11',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha11'
		}, {
			data : 'cantidad12',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'fecha12'
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
		],
		columnDefs : [
  			{ width : '14%', targets : 5 },
  			{ width : '14%', targets : 6 },
			{ width : '15%', targets : 7 },
			{ width : '14%', targets : 8 }
  		]
	});
	
	listaDonacionesCache = respuesta;

}

function cargarAlmacen(codigo, codigoProvincia) {
	var params = { 
		icodigo : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/donaciones/consulta/listarAlmacen', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="0">Todos</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.vcodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_almacen').html(options);
			if (codigoProvincia != null) {
				$('#sel_almacen').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function listarTablaProductos(){

	var idDdi = $('#sel_ddi').val();
	var idAlmacen = $('#sel_almacen').val();
//	cargarTitulo( idDdi, idAlmacen);
	
		var params = { 
			idDdi : $('#sel_ddi').val(),
			idAlmacen : $('#sel_almacen').val()
		};
		
		loadding(true);
	
		consultarAjax('GET', '/gestion-almacenes/consulta/listarProductos', params, function(respuesta) {
		
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {

				listarProductos(respuesta);

				
			}
			
			loadding(false);
		});
		
//	}
}

function listarProductos(respuesta) {

	tbl_productos.dataTable().fnDestroy();
	
	tbl_productos.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'nombreDdi',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'fechaVencimiento'
		}, {
			data : 'nombreDdi'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nroLote'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
//		'footerCallback' : function ( row, data, start, end, display ) {
//			var api = this.api(), data;	 
//			
//			// Remove the formatting to get integer data for summation
//			var intVal = function ( i ) {
//				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
//			};
//	
//			// total_page_peso over this page
//			var total_page_peso = api.column(4).data().reduce( function (a, b) {
//				return intVal(a) + intVal(b);
//			}, 0 );
//	
//			// Update footer
//			$('#sp_imp_todos').html(formatMontoAll(parseFloat(total_page_peso).toFixed(2)));
//		}
	});
	
	listaDonacionesCache = respuesta;


}