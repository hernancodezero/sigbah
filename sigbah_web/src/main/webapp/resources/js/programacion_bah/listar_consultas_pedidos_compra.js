var listarProgramacionCache = new Object();

var tbl_mnt_consul_prog = $('#tbl_mnt_consul_prog');
var frm_consul_prog = $('#frm_consul_prog');
//var region='';


var tbl_mnt_consul_ped = $('#tbl_mnt_consul_ped');  
var tbl_mnt_consul_ped2 = $('#tbl_mnt_consul_ped2');
var tbl_ali = $('#tbl_ali');
var tbl_no_ali = $('#tbl_no_ali');
var tbl_ali2 = $('#tbl_ali2');
var tbl_no_ali2 = $('#tbl_no_ali2');
$(document).ready(function() {
	


	$('#btn_aceptar').click(function(e) {
		var params = { 
				codigoAnio : $('#sel_anio').val(),
				idDdi :$('#sel_ddi').val(),
				motivo : $('#sel_motivo').val(),
				idEstado : $('#sel_estado').val()
			};
		loadding(true);
		if($('#sel_ddi').val()==99){
				$('#consul_ped').show();
				$('#consul_ped2').hide();
				consultarAjax('GET', '/programacion-bath/consulta-pedido/listarConsultaPedidosCompra', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						listarPedidosCompra(respuesta);
						
					}
					loadding(false);
				});
		}else{
			$('#consul_ped').hide();
			$('#consul_ped2').show();
			consultarAjax('GET', '/programacion-bath/consulta-pedido/listarConsultaPedidosCompra2', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarPedidosCompra2(respuesta);
					
				}
				loadding(false);
			});
		}
		
	});
	
	inicializarDatos();
	

	
//	$('#href_exp_excel').click(function(e) {
//		e.preventDefault();
//		
//		var row = $('#tbl_mnt_emer_sinpad > tbody > tr').length;
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
//		var codAnio = $('#sel_anio').val();
//		var codMes = $('#sel_mes').val();
//		var codRegion = $('#sel_region').val();
//		var codFenomeno = $('#sel_fenomeno').val();
//		
//		var url = VAR_CONTEXT + '/programacion-bath/emergencia/exportarExcel/';
//		url += verificaParametro(codAnio) + '/';
//		url += verificaParametro(codMes) + '/';
//		url += verificaParametro(codRegion) + '/';
//		url += verificaParametro(codFenomeno);
//		
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
	
//	$('#href_imprimir').click(function(e) {
//		e.preventDefault();
//
//		var indices = [];
//		var codigo = '';
//		tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
//			if (tbl_mnt_emer_sinpad.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
//				indices.push(index);				
//				// Verificamos que tiene mas de un registro marcado y salimos del bucle
//				if (!esnulo(codigo)) {
//					return false;
//				}
//				var idEmergencia = listaEmergenciaSinpadCache[index].idEmergencia;
//				codigo = codigo + idEmergencia + '_';
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
//			var url = VAR_CONTEXT + '/programacion-bath/emergencia/exportarPdf/'+codigo;
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
	
	
	tbl_mnt_consul_ped2.on('click', '.btn_mod_pedido', function(e) {
		e.preventDefault();
		
		var idPedidox = $(this).attr('id');
		
		var params = { 
				idPedido:idPedidox
			};
		
			consultarAjax('GET', '/programacion-bath/consulta-pedido/mostrarProductosxPedido', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					
					var cabecera= respuesta.lstCabecera[0];
					$('#txt_anio2').val(cabecera.codAnio);
					$('#txt_ddi2').val(cabecera.nomDdi);
					$('#txt_motivo2').val(cabecera.motivo);
					$('#txt_estado2').val(cabecera.estado);
					listarAlimentarios2(respuesta.lstAlimentaria);
					listarNoAlimentarios2(respuesta.lstNoAlimentaria);
					
					$('#div_modal_pedido').modal('show');
				}
			});
	});
	
	tbl_mnt_consul_ped.on('click', '.btn_mod_ddi', function(e) {//whr 
		e.preventDefault();
		
		var idDdix = $(this).attr('id');
//		var name = $(this).attr('name');
		
		var params = { 
				codAnio:$('#sel_anio').val(),
				idMotivo:$('#sel_motivo').val(),
				idEstado:$('#sel_estado').val(),
				idDdi:idDdix
			};
		
			consultarAjax('GET', '/programacion-bath/consulta-pedido/mostrarProductos', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					
					var cabecera= respuesta.lstCabecera[0];
					$('#txt_anio').val(cabecera.codAnio);
					$('#txt_ddi').val(cabecera.nomDdi);
					$('#txt_motivo').val(cabecera.motivo);
					$('#txt_estado').val(cabecera.estado);
					listarAlimentarios(respuesta.lstAlimentaria);
					listarNoAlimentarios(respuesta.lstNoAlimentaria);
					
					$('#div_det_prod').modal('show');
				}
			});
	});
	
	
	
});



function listarAlimentarios(respuesta) {
	tbl_ali.dataTable().fnDestroy();
	tbl_ali.dataTable({
		data : respuesta,
		columns : [ 
			{
			data : 'nomProducto',
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
			data : 'nomProducto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nomProducto'
		}, {
			data : 'nomUnidadMedida'
		}, {
			data : 'totalCantidad',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'totalToneladasAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		},{
			data : 'totalSolesAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 			
//			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
//				return intVal(a) + intVal(b);
//			}, 0 );
			
			total_tm = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_s = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
//			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_tm').html(formatMontoAll(parseFloat(total_tm).toFixed(2)));
			$('#sp_tot_s').html(formatMontoAll(parseFloat(total_s).toFixed(2)));
		
		},
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 }
		]
	});
}


function listarNoAlimentarios(respuesta) {

	tbl_no_ali.dataTable().fnDestroy();
	
	tbl_no_ali.dataTable({
		data : respuesta,
		columns : [ 
			{
				data : 'nomCategoria',
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
			data : 'nomCategoria',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nomCategoria'
		},{
			data : 'nomProducto'
		}, {
			data : 'nomUnidadMedida'
		}, {
			data : 'totalCantidad',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'totalToneladasAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		},{
			data : 'totalSolesAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 			
//			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
//				return intVal(a) + intVal(b);
//			}, 0 );
			
			total_no_ts = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_no_s = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
//			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_no_tm').html(formatMontoAll(parseFloat(total_no_ts).toFixed(2)));
			$('#sp_tot_no_s').html(formatMontoAll(parseFloat(total_no_s).toFixed(2)));
		
		},
		iDisplayLength : 15,
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 }
		]
	});
}


function listarAlimentarios2(respuesta) {
	tbl_ali2.dataTable().fnDestroy();
	tbl_ali2.dataTable({
		data : respuesta,
		columns : [ 
			{
			data : 'nomProducto',
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
			data : 'nomProducto',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nomProducto'
		}, {
			data : 'nomUnidadMedida'
		}, {
			data : 'totalCantidad',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'totalToneladasAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		},{
			data : 'totalSolesAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 			
//			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
//				return intVal(a) + intVal(b);
//			}, 0 );
			
			total_tm = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_s = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
//			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_tm2').html(formatMontoAll(parseFloat(total_tm).toFixed(2)));
			$('#sp_tot_s2').html(formatMontoAll(parseFloat(total_s).toFixed(2)));
		
		},
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 }
		]
	});
}


function listarNoAlimentarios2(respuesta) {

	tbl_no_ali2.dataTable().fnDestroy();
	
	tbl_no_ali2.dataTable({
		data : respuesta,
		columns : [ 
			{
				data : 'nomCategoria',
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
			data : 'nomCategoria',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nomCategoria'
		},{
			data : 'nomProducto'
		}, {
			data : 'nomUnidadMedida'
		}, {
			data : 'totalCantidad',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
		}, {
			data : 'totalToneladasAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		},{
			data : 'totalSolesAli',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 			
//			total_Cant = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
//				return intVal(a) + intVal(b);
//			}, 0 );
			
			total_no_ts = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_no_s = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
//			$('#sp_tot_cantidad').html(parseFloat(total_Cant).toFixed(0));
			$('#sp_tot_no_tm2').html(formatMontoAll(parseFloat(total_no_ts).toFixed(2)));
			$('#sp_tot_no_s2').html(formatMontoAll(parseFloat(total_no_s).toFixed(2)));
		
		},
		iDisplayLength : 15,
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 }
		]
	});
}


function inicializarDatos() {
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#ul_consultas_pro').css('display', 'block');	
	$('#li_con_ped').attr('class', 'active');
	$('#li_con_ped').closest('li').children('a');
	
	$('#consul_ped').show();
	$('#consul_ped2').hide();
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {		
//		if (indicador == '1') { // Retorno
			$('#btn_aceptar').click();
//			$('#sel_region').val(region);
//		} else {
//			$('#divmes').show();
//			$('#divdee').hide();
////			listarProgramacion(new Object());
//		}
	}
}

function listarPedidosCompra(respuesta) {

	tbl_mnt_consul_ped.dataTable().fnDestroy();
	
	tbl_mnt_consul_ped.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idDdi',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}
		
		, {
			data : 'codigoAnio'
		}, {
			data : 'nomDdi',
			render: function(data, type, row) { 
				if (data != null) {
					return '<button type="button" id="'+row.idDdi+'" r_codigoAnio="'+row.codigoAnio+'"'+ 
						   'class="btn btn-link input-sm btn_mod_ddi">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'motivo'
		}, {
			data : 'totalTonAlimento',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
			
		}, {
			data : 'totalSolesAlimento',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalTonBna',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalSolesBna',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 
			total_inei = api.column(4, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_afec = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_dam = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_fam = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			$('#sp_tot_aliTM').html(formatMontoAll(parseFloat(total_inei).toFixed(2)));
			$('#sp_tot_aliSol').html(formatMontoAll(parseFloat(total_fam_afec).toFixed(2)));
			$('#sp_tot_noAliTM').html(formatMontoAll(parseFloat(total_fam_dam).toFixed(2)));
			$('#sp_tot_noAliSol').html(formatMontoAll(parseFloat(total_fam).toFixed(2)));
			
		},

		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 2 },
			{ width : '15%', targets : 3 },  
			{ width : '13%', targets : 4 },
			{ width : '13%', targets : 5 },
			{ width : '13%', targets : 6 },
			{ width : '13%', targets : 7 }
		]
	});
	
	listarProgramacionCache = respuesta;
}
	
function listarPedidosCompra2(respuesta) {

	tbl_mnt_consul_ped2.dataTable().fnDestroy();
	
	tbl_mnt_consul_ped2.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idDdi',
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
			data : 'idDdi',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}
		
		, {
			data : 'codigoAnio'
		}, {
			data : 'nomDdi'
		}, {
			data : 'motivo'
		}, {
			data : 'numPedido',
			render: function(data, type, row) { 
				if (data != null) {
					return '<button type="button" id="'+row.idPedidoCompra+'" name="'+row.numPedido+'"'+ 
						   'class="btn btn-link input-sm btn_mod_pedido">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		}, {
			data : 'totalTonAlimento',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
			
		}, {
			data : 'totalSolesAlimento',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalTonBna',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
		}, {
			data : 'totalSolesBna',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 2)
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
 
			total_inei = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_afec = api.column(7, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_dam = api.column(8, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_fam = api.column(9, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			$('#sp_tot_aliTM2').html(formatMontoAll(parseFloat(total_inei).toFixed(2)));
			$('#sp_tot_aliSol2').html(formatMontoAll(parseFloat(total_fam_afec).toFixed(2)));
			$('#sp_tot_noAliTM2').html(formatMontoAll(parseFloat(total_fam_dam).toFixed(2)));
			$('#sp_tot_noAliSol2').html(formatMontoAll(parseFloat(total_fam).toFixed(2)));
			
		},
		iDisplayLength : 15, 
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '13%', targets : 6 },
			{ width : '13%', targets : 7 },
			{ width : '13%', targets : 8 },
			{ width : '13%', targets : 9 }
		]
	});
	
	listarProgramacionCache = respuesta;
}
