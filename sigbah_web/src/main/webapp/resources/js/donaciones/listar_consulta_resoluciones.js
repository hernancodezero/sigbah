var listaDonacionesCache = new Object();
var listaRegionesCache = new Object();

var tbl_productos = $('#tbl_productos');

var tbl_det_estados = $('#tbl_det_estados');
var tbl_regiones = $('#tbl_regiones');
var frm_con_donaciones = $('#frm_con_donaciones');
var frm_nue_estado = $('#frm_nue_estado');

var indicadorExcel='0';

$(document).ready(function() {
	
	inicializarDatos();
	 
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
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		listarTablaProductos()
		
	});
	
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
	
		var row = $('#tbl_productos > tbody > tr').length;
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
	    tab = document.getElementById("tbl_productos"); // id of table

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
	
	$('#href_imprimir').click(function(e) {
		e.preventDefault();
		var anio = $('#sel_anio').val();
		var tipo = $('#sel_ori_donacion').val();
		loadding(true);
		var url = VAR_CONTEXT + '/donaciones/consulta/exportarPdfResolucion/'+anio+'/'+tipo;
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
	
	
	$('#sel_ddi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarAlmacen(codigo, null);
		} else {
			$('#sel_almacen').html('');
		}
	});
	
	tbl_productos.on('click', '.btn_exp_doc', function(e) {
		e.preventDefault();
		
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		if (!esnulo(id) && !esnulo(name)) {
			descargarDocumento(id, name);
		} else {
			addInfoMessage(null, mensajeValidacionDocumento);
		}
		
	});
	
});



function inicializarDatos() {
	
  	$('#li_consultas_resoluciones').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_consultas').css('display', 'block');
	$('#li_consultas_resoluciones').attr('class', 'active');
	$('#li_consultas_resoluciones').closest('li').children('a');

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		listarTablaProductos();	

		
	}
}


function listarProductos(respuesta) {

	tbl_productos.dataTable().fnDestroy();
	
	tbl_productos.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'nombreSistema',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nroRj',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" id="'+row.codigoAlfrescoRj+'" name="'+row.nombreArchivo+'"'+ 
						   'class="btn btn-link input-sm btn_exp_doc">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'fechaRj',
			sClass : 'opc-center',
		}, {
			data : 'observacion'
			
		}, {
			data : 'nombreDonante',
			sClass : 'opc-center',
		}, {
			data : 'codigoDonacion',
			sClass : 'opc-center',
		}, {
			data : 'importeMonedaOrigen',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'nroDocInafectacion',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" id="'+row.codigoAlfrescoRj+'" name="'+row.nombreArchivo+'"'+ 
						   'class="btn btn-link input-sm btn_exp_doc">'+data+'</button>';
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
		info : false,
		columnDefs : [{ width : '5%', targets : 0 },
		              { width : '10%', targets : 1 },
		              { width : '10%', targets : 2 },
		              { width : '30%', targets : 3 },
		              { width : '15%', targets : 4 },
		    			{ width : '10%', targets : 5 },
		    			{ width : '10%', targets : 6 },
		    			{ width : '10%', targets : 7 }
		    	
		  			
		    		]
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

function listarStockDdi(respuesta) {

	tbl_stock_ddi.dataTable().fnDestroy();
	
	tbl_stock_ddi.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'nombreCategoria',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreDdi'
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'importeTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		'footerCallback' : function ( row, data, start, end, display ) {
			var api = this.api(), data;	 
			
			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
			};
	
			// total_page_peso over this page
			var total_page_peso = api.column(5).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
	
			// Update footer
			$('#sp_imp_ddi').html(formatMontoAll(parseFloat(total_page_peso).toFixed(2)));
		}
	});
	
	listaDonacionesCache = respuesta;

}

function listarStockAlmacen(respuesta) {

	tbl_stock_almacen.dataTable().fnDestroy();
	
	tbl_stock_almacen.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'nombreCategoria',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreDdi'
		}, {
			data : 'nombreAlmacen'
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'cantidad',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'importeTotal',
			sClass : 'opc-right',
			render: $.fn.dataTable.render.number( ',', '.', 2)
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false,
		'footerCallback' : function ( row, data, start, end, display ) {
			var api = this.api(), data;	 
			
			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
			};
	
			// total_page_peso over this page
			var total_page_peso = api.column(6).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
	
			// Update footer
			$('#sp_imp_almacen').html(formatMontoAll(parseFloat(total_page_peso).toFixed(2)));
		}
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

	var anio = $('#sel_anio').val();
	var tipo = $('#sel_ori_donacion').val();
	console.log("DATA: "+anio+" - "+tipo);
//	cargarTitulo( idDdi, idAlmacen);
	
		var params = { 
			codigoAnio : $('#sel_anio').val(),
			tipoDonacion : $('#sel_ori_donacion').val()
		};
		
		loadding(true);
	
		consultarAjax('GET', '/donaciones/consulta/listarResoluciones', params, function(respuesta) {
		
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {

				listarProductos(respuesta);

				
			}
			
			loadding(false);
		});
		
//	}
}

function cargarTitulo(idDdi, idAlmacen){
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var fecha = (day<10 ? '0' : '')+day + '/' +
	    (month<10 ? '0' : '') + month + '/' +
	     + d.getFullYear();
	if(idDdi=='0' && idAlmacen=='0'){
		$('#txt_titulo').html("Stock al "+fecha+" A nivel Nacional");
		$('#hid_titulo').val("Stock al "+fecha+" A nivel Nacional");
	}else if(idDdi!='0' && idAlmacen=='0'){
		$('#txt_titulo').html("Stock al "+fecha+" de la DDI: "+$("#sel_ddi option:selected").text());
		$('#hid_titulo').val("Stock al "+fecha+" de la DDI: "+$("#sel_ddi option:selected").text())
	}else if(idDdi!='0' && idAlmacen!='0'){
		$('#txt_titulo').html("Stock al "+fecha+" de la DDI: "+$("#sel_ddi option:selected").text()+" y Almacén: "+$("#sel_almacen option:selected").text());
		$('#hid_titulo').val("Stock al "+fecha+" de la DDI: "+$("#sel_ddi option:selected").text()+" y Almacén: "+$("#sel_almacen option:selected").text())
	}
		
	
}

function llamarTabla(){
	 $('#tbl_stock_todos').DataTable( {
		 'footerCallback' : function ( row, data, start, end, display ) {
				var api = this.api(), data;	 
				
				// Remove the formatting to get integer data for summation
				var intVal = function ( i ) {
					return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
				};
	 
				// total_page_peso over this page
				var total_page_peso = api.column(4, { page: 'current'}).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				
				// total_page_peso over this page
				total_page_volumen = api.column(4, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );

				// Update footer
				console.log("ENTRO TOTAL");
				$('#sp_tot_peso').html(formatMontoAll(parseFloat(total_page_peso).toFixed(2)));
				//$('#sp_tot_volumen').html(formatMontoAll(parseFloat(total_page_volumen).toFixed(2)));
			}
	    } );
}

function descargarDocumento(codigo, nombre) {	
	loadding(true);
	var url = VAR_CONTEXT + '/common/archivo/exportarArchivo/'+codigo+'/'+nombre+'/';	
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
}