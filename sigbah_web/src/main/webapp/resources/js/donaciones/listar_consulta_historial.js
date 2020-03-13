var listaDonacionesCache = new Object();

var tbl_historial = $('#tbl_historial');
var frm_historial = $('#frm_historial');

$(document).ready(function() {
	
	frm_historial.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
//			sel_anio : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Año.'
//					}
//				}
//			}
		}
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_historial.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var params = { 
				codigoAnio : $('#sel_anio').val(),
				idDdi : $('#sel_ddi').val(),
				tipoDonacion : $('#sel_tip_donacion').val(),
				idEstado : $('#sel_estado').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/donaciones/consulta/listarHistorial', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarHistorialDonaciones(respuesta);
				}
				loadding(false);
			});
			
		}
		
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
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_historial > tbody > tr').length;
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
		
		var tab_text="<table border='2px'>";
	    var textRange; var j=0;
	    tab = document.getElementById('tbl_historial'); // id of table

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

		var indices = [];
		var codigo = '';
		tbl_historial.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_historial.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
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
			loadding(true);
			var url = VAR_CONTEXT + '/donaciones/consulta/exportarHistorialPdf/'+codigo;
			$.fileDownload(url).done(function(respuesta) {
					
				console.log("RES: "+respuesta);
				console.log("RESPUESYA: "+respuesta.descripcion);
				if (respuesta.descripcion == NOTIFICACION_ERROR) {
					addErrorMessage(null, mensajeReporteError);
				} else {
					retornarURL();
					console.log("RUTA: "+respuesta.url);
					addInfoMessage(null, mensajeReporteExito);
				}
				loadding(false);
			}).fail(function (respuesta) {
				loadding(false);
				if (respuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, mensajeReporteError);
				} else if (respuesta == NOTIFICACION_VALIDACION) {
					addWarnMessage(null, mensajeReporteValidacion);
				}
			});
//			var params = { 
//					icodigo : codigo
//			};
//			
//			loadding(true);
//			
//			consultarAjax('POST', '/donaciones/consulta/exportarHistorialPdf', params, function(respuesta) {
//				
//				if (respuesta.descripcion == NOTIFICACION_ERROR) {
//					console.log("codigo: "+respuesta.icodigo);
//					console.log("RESPUESYA: "+respuesta.descripcion);
//					console.log("todo: "+respuesta);
//					addErrorMessage(null, mensajeReporteError);
//				} else {
//					console.log("codigo: "+respuesta.icodigo);
//					console.log("RESPUESYA: "+respuesta.descripcion);
//					console.log("todo: "+respuesta);
//					console.log("RUTA: "+respuesta.url);
//					addInfoMessage(null, mensajeReporteExito);
//				}
//				loadding(false);
//			});
			
			
		}
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
	
});

function inicializarDatos() {

	$('#li_consultas_historial').addClass('active');
	$('#ul_donaciones').css('display', 'block');
	$('#ul_don_consultas').css('display', 'block');
	$('#li_consultas_historial').attr('class', 'active');
	$('#li_consultas_historial').closest('li').children('a');
	
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

function listarHistorialDonaciones(respuesta) {

	tbl_historial.dataTable().fnDestroy();
	
	tbl_historial.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idDonacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codigoAnio'
		}, {
			data : 'nombreDdi'
		}, {
			data : 'codigoDonacion',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" id="'+row.idDonacion+'" name="'+row.idDonacion+'"'+ 
						   'class="btn btn-link input-sm btn_exp_doc" onclick="verPdfModal('+row.idDonacion+','+''+');">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'tipoDonacion'
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
		],
		columnDefs : [
  			{ width : '14%', targets : 5 },
  			{ width : '14%', targets : 6 },
			{ width : '15%', targets : 7 }
			
  		]
	});
	
	listaDonacionesCache = respuesta;

}

function retornarURL(titulo){
	console.log("TITULORETORNA: "+titulo);
	var params = { 
			idRol : ''
		};
		
		loadding(true);

		consultarAjax('GET', '/donaciones/consulta/obtenerRuta', params, function(respuesta) {
			console.log(respuesta.descripcion);
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			console.log(respuesta.descripcion);

			setTimeout(function(){
					funcionVer(respuesta.descripcion, titulo);
					}, 1000);

		}
		loadding(false);
	});
}

function funcionVer(nombre, titulo) {
	console.log("TITULO: "+titulo);
	var name = RUTA_PDF+"pdf/historial/donacion/Consulta_Historial.pdf";
	var fileName = '';
		$('#dialog').dialog({
            modal: true,
            title: "Consulta Historial",
            position: { my: 'top', at: 'top+150' },
            width: 'auto',
            height: 'auto',

            closeButton:true,

            open: function () {
                var object = "<object data=\"{FileName}\" type=\"application/pdf\" width=\"650px\" height=\"450px\">";
//                object += "If you are unable to view file, you can download from <a href = \"{FileName}\">here</a>";
                object += "<a target = \"_blank\" href = \"{FileName}\">Click para descargar </a>";
                object += "</object>";
                object = object.replace(/{FileName}/g, "/" + name+fileName);
                $('#dialog').html(object);
     
            }
        });
}

function verPdfModal(codigo, titulo){
	loadding(true);
	var url = VAR_CONTEXT + '/donaciones/consulta/exportarHistorialPdf/'+codigo;
	$.fileDownload(url).done(function(respuesta) {
			
		console.log("RES: "+respuesta);
		console.log("RESPUESYA: "+respuesta.descripcion);
		if (respuesta.descripcion == NOTIFICACION_ERROR) {
			addErrorMessage(null, mensajeReporteError);
		} else {
			console.log("TITULOVER: "+titulo);
//			retornarURL(titulo);
			console.log("RUTA: "+respuesta.url);
			addInfoMessage(null, mensajeReporteExito);
		}
		loadding(false);
	}).fail(function (respuesta) {
		loadding(false);
		if (respuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, mensajeReporteError);
		} else if (respuesta == NOTIFICACION_VALIDACION) {
			addWarnMessage(null, mensajeReporteValidacion);
		}
	});
}

