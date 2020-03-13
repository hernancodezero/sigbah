
var listaCorreosCache = new Object();

var tbl_correos = $('#tbl_correos');

var frm_correos = $('#frm_correos');

var frm_correo_act = $('#frm_correo_act');

var flagControl = "";

$(document).ready(function() {
	$('#myjstree1').jstree({ 
		"themes" : { "stripes" : true },
		"checkbox": {
            "keep_selected_style": false
        },
        "plugins": ["checkbox"]
	}).on("check_node.jstree uncheck_node.jstree", function(e, data) {
		  alert(data.node.id + ' ' + data.node.text +
			        (data.node.state.checked ? ' CHECKED': ' NOT CHECKED'))
			})
			
	 $("#myjstree1").bind("changed.jstree",
    function (e, data) {
       
        //alert(JSON.stringify(data));
    });
//	$("#tree").jstree({
//        "checkbox": {
//            "keep_selected_style": false
//        },
//            "plugins": ["checkbox"]
//    });
	inicializarDatos();
	
	frm_correos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_modulo : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar módulo.'
					}
				}
			}
		}
	});
	
	frm_correo_act.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			sel_modulo_nue : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar módulo.'
					}
				}
			},
			
			sel_estado_mod : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar estado.'
					}
				}
			},
			
			sel_destinatario : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar destinatario.'
					}
				}
			},
			
		}
	});

	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();
		

		$('#h4_tit_rol').html('Nuevo Destinatario');
		$('#hid_id_correo').val('I');
		$('#sel_modulo_nue').val('');
		$('#sel_estado_mod').val('');
		$('#sel_destinatario').val('');
		$('#txt_correo').val('');
		$('#txt_cargo').val('');
		$('#sel_activo').val('1');
		$('#sel_modulo_nue').prop('disabled', false);
		$('#sel_estado_mod').prop('disabled', false);
		$('#sel_destinatario').prop('disabled', false);
		$('#div_rol').modal('show');

	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
	
		var bootstrapValidator = frm_correos.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			listarTablaCorreos();	
		}
		
	});
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
			var estado = ''
			tbl_correos.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_correos.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
//				if (!esnulo(codigo)) {
//					return false;
//				}
//				 estado = listaCorreosCache[index].idRol;
//				var idUsuario = listaRolesCache[index].idRol;
//				codigo = codigo + idUsuario + '_';
			}
		});
		
//		if (!esnulo(codigo)) {
//			console.log("ESTADO: "+estado);
//			codigo = codigo.substring(0, codigo.length - 1);
//		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
				
				
				loadding(true);

				$('#h4_tit_rol').html('Actualizar Destinatario');
				
				var obj = listaCorreosCache[indices[0]];
				$('#hid_id_correo').val('U');
				$('#sel_modulo_nue').val(obj.nombre);
				cargarEstados(obj.nombre, obj.idEstado)
//						$('#sel_estado_mod').val(obj.idEstado);
				$('#sel_destinatario').val(obj.idUsuario+'_'+obj.correo+'_'+obj.cargo);
				$('#sel_modulo_nue').prop('disabled', true);
				$('#sel_estado_mod').prop('disabled', true);
				$('#sel_destinatario').prop('disabled', true);
				$('#txt_correo').val(obj.correo);
				$('#txt_cargo').val(obj.cargo);
				$('#sel_activo').val(obj.flag);
				
				$('#div_rol').modal('show');
				
			loadding(false);
			
		}
		
	});
	
	
	
	$('#href_exp_excel').click(function(e) {
		e.preventDefault();
		
		var row = $('#tbl_roles > tbody > tr').length;
		var empty = null;
		$('tr.odd').each(function() {		
			empty = $(this).find('.dataTables_empty').text();
			return false;
		});					
		if (!esnulo(empty) || row < 1) {
			addWarnMessage(null, 'No se encuentran registros para generar el reporte.');
			return;
		}

		loadding(true);
		
		var tab_text="<table border='2px'>";
	    var textRange; var j=0;
	    tab = document.getElementById('tbl_roles'); // id of table

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
		var codigo = ''
			tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_con_donaciones.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				var idDonacion = listaDonacionesCache[index].idSalida;
				codigo = codigo + idDonacion + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
			loadding(true);
			var url = VAR_CONTEXT + '/donacionesSalida/registro-donacionesSalida/exportarPdf/'+codigo;
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
	
	
	$('#btn_gra_correo').click(function(e) {
		e.preventDefault();
		var usua=$('#sel_destinatario').val();
		var bootstrapValidator = frm_correo_act.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var idUser = null;
			if (!esnulo(usua)) {			
				var arr = usua.split('_');
				idUser=arr[0];
			} 
			console.log($('#sel_modulo_nue').val()+"-"+$('#sel_estado_mod').val()+"-"+idUser+"-"+$('#sel_activo').val()+"-"+$('#hid_id_correo').val());
			var params = {
				nombre : $('#sel_modulo_nue').val(),
				idEstado : $('#sel_estado_mod').val(),
				idUsuario: idUser,
				flag : $('#sel_activo').val(),
				control : $('#hid_id_correo').val()

			};
			
			loadding(true);
			
			consultarAjax('POST', '/administracion/correos/grabarCorreos', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {

					addSuccessMessage(null, 'Se registró el destinatario');
					$('#div_rol').modal('hide');
					listarTablaCorreos();	

				}
				loadding(false);
			});			
		}
		
	});
	
	
	$('#sel_modulo_nue').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarEstados(codigo, null);
		} else {
			$('#sel_estado_mod').html('');
		}
	});
	
	$('#sel_destinatario').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {			
			var arr = codigo.split('_');
			$('#txt_correo').val(arr[1]);
			$('#txt_cargo').val(arr[2]);
		} else {
			$('#txt_correo').val('');
			$('#txt_cargo').val('');
		}
	});
	
	
});



function inicializarDatos() {
	
  	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#ul_adm_seguridad').css('display', 'block');
	$('#li_adm_correos').attr('class', 'active');
	$('#li_adm_correos').closest('li').children('a');
	$('#divRegiones').hide();
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		listarTablaCorreos();	
		
	}
}

function listarTablaCorreos() {

	var params = { 
		nombre : $('#sel_modulo').val()
	};
	
	loadding(true);

	consultarAjax('GET', '/administracion/correos/listaCorreos', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarCorreos(respuesta);
		}
		loadding(false);
	});

}

function listarCorreos(respuesta) {

	tbl_correos.dataTable().fnDestroy();
	
	tbl_correos.dataTable({
		data : respuesta,
		columns : [ {
			data : 'nombre',
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
			data : 'nombre',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'nombre'
		}, {
			data : 'nombreEstado'
		}, {
			data : 'nombreUsuario'
		}, {
			data : 'usuario'
		}, {
			data : 'correo'
		}, {
			data : 'cargo'
		}, {
			data : 'nombreFlag'
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
	
	listaCorreosCache = respuesta;

}

function verMenuNuevo(idRol){
		var params = { 
			idRol : idRol
		};
		
		loadding(true);

		consultarAjax('GET', '/administracion/roles/listaMenuRoles', params, function(respuesta) {
		
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				
				$('#div_menu_rol').html(respuesta.descripcion);
				$('#div_menu_rol').show();
				$('#myjstree1').jstree({ 
					"themes" : { "stripes" : true },
					"checkbox": {
			            "keep_selected_style": false
			        },
			        "plugins": ["checkbox"]
				})
				.on("check_node.jstree uncheck_node.jstree", function(e, data) {
  alert(data.node.id + ' ' + data.node.text +
        (data.node.state.checked ? ' CHECKED': ' NOT CHECKED'))
})

			}
			loadding(false);
		});
}

function actualizarRol(idPadre, idMenu){
	console.log("DATOS: "+idPadre+" - "+idMenu);
	
	var params = {
		idRol : $('#hid_id_rol').val(),
		idMenu : idMenu,
		idPadre : idPadre, 
		flagActivo: '1'

	};
	
	loadding(true);
	
	consultarAjax('POST', '/administracion/roles/grabarMenu', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {

			console.log("EXITOSO");

		}
		loadding(false);
	});			

}


function cargarEstados(codigo, codigoEstado) {
	var params = { 
		descripcion : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/administracion/correos/listarEstadosModulo', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.icodigo+'">'+item.descripcion+'</option>';
			});
			$('#sel_estado_mod').html(options);
			if (codigoEstado != null) {
				$('#sel_estado_mod').val(codigoEstado);       	
			}
		}
		loadding(false);
	});
}

