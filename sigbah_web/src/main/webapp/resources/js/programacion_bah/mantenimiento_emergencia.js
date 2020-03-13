var listaAlimentariosCache = new Object();
var listaNoAlimentariosCache = new Object();
var listaLocalidadesCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');

var tbl_det_localidades = $('#tbl_det_localidades');

var tbl_det_alimentarios = $('#tbl_det_alimentarios');
//var frm_det_alimentarios = $('#frm_det_alimentarios');

var tbl_det_no_alimentarios = $('#tbl_det_no_alimentarios');
//var frm_det_no_alimentarios = $('#frm_det_no_alimentarios');

//var tbl_det_documentos = $('#tbl_det_documentos');
//var frm_det_documentos = $('#frm_det_documentos');

$(document).ready(function() {
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	
	inicializarDatos();
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/emergencia/inicio/1';
		$(location).attr('href', url);
		
	/*		
		var params = { 
				codAnio : codiAnio,
				codMes : codiMes,
				idRegion : codiRegion,
				idFenomeno : codiFenomeno
			};
			  
			loadding(true);
			
			consultarAjax('GET', '/programacion-bath/emergencia/listarEmergencias', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarEmergencia(respuesta);
				}
				loadding(false);
			});
			*/
			
	});
	

	
	$('#sel_producto').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {
			var arr = codigo.split('_');
			if (arr.length > 1) {
				$('#txt_uni_medida').val(arr[1]);
			} else {
				$('#txt_uni_medida').val('');
			}			
		} else {
			$('#txt_uni_medida').val('');
		}
	});
	   

	
});

function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_emer_sinpad').attr('class', 'active');
	$('#li_emer_sinpad').closest('li').children('a');
	
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {

		$('#txt_cod_sinpad').val(cabecera.idEmergencia);
		$('#txt_desc_sinpad').val(cabecera.nombreEmergencia);
		$('#txt_fecha').val(cabecera.fechaEmergencia);
		$('#txt_fenomeno').val(cabecera.descFenomeno);
		$('#txt_ubigeo').val(cabecera.ubigeo);
		
		listarDetalleLocalidad(lista_localidad);
		listarDetalleAlimentarios(lista_alimentaria);
		listarDetalleNoAlimentarios(lista_no_alimentaria);
	}
}

function listarDetalleLocalidad(respuesta) {
	
	tbl_det_localidades.dataTable().fnDestroy();
	tbl_det_localidades.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'idEmergencia',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'desDepartamento'
		}, {
			data : 'desProvincia'
		}, {
			data : 'desDistrito' 
		}, {
			data : 'desCentroPoblado'
		}, {
			data : 'famAfectado'
		} , {
			data : 'famDamnificado'
		}, {
			data : 'persoAfectado'
		}, {
			data : 'persoDamnificado'
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	   
	listaLocalidadesCache = respuesta;
}

function listarDetalleAlimentarios(respuesta) {

	tbl_det_alimentarios.dataTable().fnDestroy();
	
	tbl_det_alimentarios.dataTable({
		data : respuesta,
		columns : [ {	
			data : 'idEmergencia',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'tipoProducto'
		}, {
			data : 'cantidad'
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaAlimentariosCache = respuesta;

}

function listarDetalleNoAlimentarios(respuesta) {

	tbl_det_no_alimentarios.dataTable().fnDestroy();
	
	tbl_det_no_alimentarios.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idEmergencia',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'categoriaProducto'
		}, {
			data : 'tipoProducto'
		}, {
			data : 'cantidad'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaNoAlimentariosCache = respuesta;

}





function cargarProductoNoAlimentario(idCategoria, codigoProducto) {
	var params = { 
		idCategoria : idCategoria
	};			
	loadding(true);
	consultarAjax('GET', '/gestion-almacenes/control-calidad/listarProductoXCategoria', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '';
	        $.each(respuesta, function(i, item) {
	            options += '<option value="'+item.idProducto+'_'+item.nombreUnidadMedida+'">'+item.nombreProducto+'</option>';
	        });
	        $('#sel_no_producto').html(options);
	        if (codigoProducto != null) {
	        	$('#sel_no_producto').val(codigoProducto);
				$('#sel_no_producto').select2();
				$('#sel_no_producto').select2({
					  dropdownParent: $('#div_pro_det_no_alimentarios')
				});	        	
	        } else {
	        	frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'sel_no_producto');
	        }
		}
		loadding(false);		
	});
}

function cargarTipoControl(val_tip_control) {
	if (val_tip_control == '1') { // Ingreso por Compra de productos
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
	} else if (val_tip_control == '2') { // Control Interno
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
	} else if (val_tip_control == '3') { // Ingreso por Transferencias de Almacén
		$('#sel_ori_almacen').prop('disabled', false);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
	} else if (val_tip_control == '4') { // Salida de Productos por Emergencia
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
	} else if (val_tip_control == '5') { // Ingreso por Donación
		$('#sel_ori_almacen').prop('disabled', true);
		$('#sel_proveedor').prop('disabled', false);
		$('#sel_emp_transporte').prop('disabled', false);
		$('#sel_chofer').prop('disabled', false);
		$('#txt_nro_placa').prop('disabled', false);
	} else if (val_tip_control == '6') { // Salidas por Transferencias a Almacén
		$('#sel_ori_almacen').prop('disabled', false);
		$('#sel_proveedor').prop('disabled', true);
		$('#sel_emp_transporte').prop('disabled', true);
		$('#sel_chofer').prop('disabled', true);
		$('#txt_nro_placa').prop('disabled', true);
	}
}

function listarEmergencia(respuesta) {

	tbl_mnt_emer_sinpad.dataTable().fnDestroy();
	
	tbl_mnt_emer_sinpad.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idEmergencia',
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
			data : 'idEmergencia',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'codAnio'
		}, {
			data : 'nombreMes'
		}, {
			data : 'fecha'
		}, {
			data : 'idEmergencia'
		}, {
			data : 'descFenomeno'
		}, {
			data : 'nombreEmergencia'
		}, {
			data : 'desDepartamento'
		}, {
			data : 'desProvincia'
		}, {
			data : 'desDistrito'
		}, {
//			data : 'poblacionInei'
//		}, {
			data : 'famAfectado',
			sClass : 'opc-right'
				
		}, {
			data : 'famDamnificado',
			sClass : 'opc-right'
		}, {
			data : 'totalFam',
			sClass : 'opc-right'
		}, {
			data : 'persoAfectado',
			sClass : 'opc-right'
		}, {
			data : 'persoDamnificado',
			sClass : 'opc-right'
		}, {
			data : 'totalPerso',
			sClass : 'opc-right'
		} ],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : true, 
		paging : false,
		ordering : false,
		info : false,
		'footerCallback' : function ( row, data, start, end, display ) {
			var api = this.api(), data;	 
			
			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
			};
 			
			total_fam_afec = api.column(11, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			total_fam_dam = api.column(12, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_fam = api.column(13, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per_afec = api.column(14, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per_dam = api.column(15, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			total_per = api.column(16, { page: 'current'} ).data().reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$('#sp_tot_fam_afec').html(parseFloat(total_fam_afec).toFixed(0));
			$('#sp_tot_fam_dam').html(parseFloat(total_fam_dam).toFixed(0));
			$('#sp_tot_fam').html(parseFloat(total_fam).toFixed(0));
			$('#sp_tot_per_afec').html(parseFloat(total_per_afec).toFixed(0));
			$('#sp_tot_per_dam').html(parseFloat(total_per_dam).toFixed(0));
			$('#sp_tot_per').html(parseFloat(total_per).toFixed(0));
		
		},
		iDisplayLength : 15, 
//		aLengthMenu : [
//			[15, 50, 100],
//			[15, 50, 100]
//		],
		columnDefs : [
			{ width : '15%', targets : 3 },
			{ width : '15%', targets : 4 },
			{ width : '15%', targets : 5 },
			{ width : '18%', targets : 7 }
		]
	});
	
	listaEmergenciaSinpadCache = respuesta;

}
