var listarProgramacionCache = new Object();

var tbl_mnt_consul_prog = $('#tbl_mnt_consul_prog');
var frm_consul_prog = $('#frm_consul_prog');
//var region='';


var tbl_ali = $('#tbl_ali');
var tbl_no_ali = $('#tbl_no_ali');
$(document).ready(function() {
	
	$("#sel_mes").select2({
		  placeholder: "Seleccione Mes",
		  allowClear: true,
		  multiple: true
		});
	
	$("#checkboxMes").click(function(){
	    if($("#checkboxMes").is(':checked') ){
	        $("#sel_mes > option").prop("selected","selected");
	        $("#sel_mes").trigger("change");
	    }else{
	        $("#sel_mes > option").removeAttr("selected");
	         $("#sel_mes").trigger("change");
	     }
	});
	

	$("#sel_dee").select2({ 
		  placeholder: "Seleccione DEE"
		});

	$('#sel_tipo').change(function() {
		var codigo = $(this).val();		
		
		if (codigo=='1') {		
			$('#titulo_tabla').html('Programación por año y mes');
			$('#divmes').show();
			$('#divdee').hide();
		} else {
			$('#titulo_tabla').html('Programación por año y DEE');
			$('#divmes').hide();
			$('#divdee').show();
		}
	});
	
	$('#btn_aceptar').click(function(e) {
		var codigo = $('#sel_tipo').val();		
		if (codigo=='1') {		
			
			var cadena = $('#sel_mes').val();
			var meses = '';
			
			for (var i=0; i<cadena.length; i++) {
			   meses =meses+ cadena[i]+ ',';
			}
			//For in
//			for (i in cadena) {
//				msgForIn = msgForIn + dato[i] + ' - '; 
//			}
			 console.log(meses);
			var params = { 
					codigoAnio : $('#sel_anio').val(),
					codigoMes : meses,
					nombreMes : cadena,
					idEstado : $('#sel_estado').val()
				};
				
				loadding(true);
				
				consultarAjax('GET', '/programacion-bath/consulta-programacion/listarProg_x_mes', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarProgramacion(respuesta);
						
					}
					loadding(false);
				});
		}else{
			var cadenaDee = $('#sel_dee').val();
			if(cadenaDee.length>5){
				addErrorMessage(null, "Solo se puede seleccionar hasta 5 DEE");
				return;
			}
			var dees = '';
			
			for (var i=0; i<cadenaDee.length; i++) {
				dees = dees + cadenaDee[i] + ','; 
			}
			
			var params = { 
					codigoAnio : $('#sel_anio').val(),
					nroDee : dees,
					idEstado : $('#sel_estado').val()
				};
				loadding(true);
				
				consultarAjax('GET', '/programacion-bath/consulta-programacion/listarProg_x_dee', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarProgramacion(respuesta);
						
					}
					loadding(false);
				});
		}
			
		
		
	});
	
	inicializarDatos();
	

	
	$('#href_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_mnt_consul_prog > tbody > tr').length;
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
	    tab = document.getElementById('tbl_mnt_consul_prog'); // id of table

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

		var codigo = $('#sel_tipo').val();		
		if (codigo=='1') {		
			
		}else{
			var cadenaDee = $('#sel_dee').val();
			if(cadenaDee.length>5){
				addErrorMessage(null, "Solo se puede seleccionar hasta 5 DEE");
				return;
			}
			var dees = '';
			
			for (var i=0; i<cadenaDee.length; i++) {
				dees = dees + cadenaDee[i] + ','; 
			}
			
//			var params = { 
//					codigoAnio : $('#sel_anio').val(),
//					nroDee : dees,
//					idEstado : $('#sel_estado').val()
//				};
//				loadding(true);
//				
//				consultarAjax('GET', '/programacion-bath/consulta-programacion/listarProg_x_deePDF', params, function(respuesta) {
//					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//						addErrorMessage(null, respuesta.mensajeRespuesta);
//					} else {
//						listarProgramacion(respuesta);
//						
//					}
//					loadding(false);
//				});
				
				var codAnio=$('#sel_anio').val();
				var nroDee=dees;
				var idEstado=$('#sel_estado').val();
				loadding(true);	
				var url = VAR_CONTEXT + '/programacion-bath/consulta-programacion/listarProg_x_deePDF/';
				url += verificaParametro(codAnio) + '/';
				url += verificaParametro(nroDee) + '/';
				url += verificaParametro(idEstado);
				
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
			
	});
	
	tbl_mnt_consul_prog.on('click', '.btn_mod_prod', function(e) {//whr 
		e.preventDefault();
		
		var idProg = $(this).attr('id');
		var numeroDee = $(this).attr('numDee');
		var nombrepro = $(this).attr('nombreProg');
//		if (!esnulo(id) && !esnulo(name)) {
//			console.log(id+"- nombre:"+name);
//		} else {
//			addInfoMessage(null, mensajeValidacionDocumento);
//		}
		
		var params = { 
				idProgramacion : idProg
			};
			
			consultarAjax('GET', '/programacion-bath/consulta-programacion/mostrarProductos', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					console.log(respuesta);
					listarAlimentarios(respuesta.lstAlimentaria);
					listarNoAlimentarios(respuesta.lstNoAlimentaria);
					 $('#txt_num_prog').val(numeroDee);
					$('#txt_des_prog').val(nombrepro);
					$('#div_det_prod').modal('show');
				}
			});
	});
	
	$('#href_ubi_excel_ali').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_ali > tbody > tr').length;
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
	    tab = document.getElementById('tbl_ali'); // id of table

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
	
	$('#href_ubi_excel_no_ali').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_no_ali > tbody > tr').length;
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
	    tab = document.getElementById('tbl_no_ali'); // id of table

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
	
});
function listarAlimentarios(respuesta) {

	tbl_ali.dataTable().fnDestroy();
	
	tbl_ali.dataTable({
		data : respuesta,
		columns : [ 
			{
			data : 'idProgramacion',
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
		},
		{	
			data : 'idProgramacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidadMedida'
		}, {
			data : 'nroUnidades',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		},{
			data : 'totalKilos',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalToneladas',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}],
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
 			
			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_Kilo = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_Tone = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_kilos').html(parseFloat(total_Kilo).toFixed(0));
			$('#sp_tot_toneladas').html(parseFloat(total_Tone).toFixed(0));
		
		},
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
}
function listarNoAlimentarios(respuesta) {

	tbl_no_ali.dataTable().fnDestroy();
	
	tbl_no_ali.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idProgramacion',
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
		},
		 {	
			data : 'idProgramacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombreCategoria'
		}, {
			data : 'nombreProducto'
		}, {
			data : 'nombreUnidadMedida'
		}, {
			data : 'nroUnidades',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'totalKilos',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalToneladas',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}],
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
 			
			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_Kilo = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_Tone = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_kilos').html(parseFloat(total_Kilo).toFixed(2));
			$('#sp_tot_toneladas').html(parseFloat(total_Tone).toFixed(2));
		
		},
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
}


function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#ul_consultas_pro').css('display', 'block');	
	$('#li_con_prog').attr('class', 'active');
	$('#li_con_prog').closest('li').children('a');
	

	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {		
		if (indicador == '1') { // Retorno
			$('#btn_aceptar').click();
//			$('#sel_region').val(region);
		} else {
			$('#divmes').show();
			$('#divdee').hide();
//			listarProgramacion(new Object());
		}
	}
}

function listarProgramacion(respuesta) {

	tbl_mnt_consul_prog.dataTable().fnDestroy();
	
	tbl_mnt_consul_prog.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'idProgramacion',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}
		
		, {
			data : 'codigoAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'nroDee'
		}, {
			data : 'nroProgramacion',
			render: function(data, type, row) { 
				if (data != null) {
					return '<button type="button" id="'+row.idProgramacion+'"numDee="'+row.nroProgramacion+'"nombreProg="'+row.nombreProgramacion+'"'+
						   'class="btn btn-link input-sm btn_mod_prod">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'fechaProgramacion'
		}, {
			data : 'nombreProgramacion'
		}, {
			data : 'nombreRegion'
		}, {
			data : 'totalAlimento',
			sClass : 'opc-right'
		}, {
			data : 'totalBna',
			sClass : 'opc-right'
		}, {
			data : 'nombreEstado'
				
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false, 
		paging : false,
		ordering : false,
		info : false,
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 },
			{ width : '18%', targets : 8 }
		]
	});
	
	listarProgramacionCache = respuesta;
}

