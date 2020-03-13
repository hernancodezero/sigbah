
var listaRolesCache = new Object();

var tbl_roles = $('#tbl_roles');
;
var frm_rol = $('#frm_rol');


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
        alert("Checked: " + data.node.id);
        alert("Parent: " + data.node.parent); 
        //alert(JSON.stringify(data));
    });
//	$("#tree").jstree({
//        "checkbox": {
//            "keep_selected_style": false
//        },
//            "plugins": ["checkbox"]
//    });
	inicializarDatos();
	
	frm_rol.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			txt_nombre : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre de Rol.'
					}
				}
			}
		}
	});

	
	$('#href_nuevo').click(function(e) {
		e.preventDefault();
		

		$('#h4_tit_rol').html('Nuevo Rol');
		$('#hid_id_rol').val('');
		$('#txt_nombre').val('');
		$('#div_menu_rol').html('');
		$('#div_rol').modal('show');
				
		
		
	});
	
	$('#btn_buscar').click(function(e) {
		e.preventDefault();
	
		var bootstrapValidator = frm_usuarios.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			listarTablaUsuarios();	
		}
		
	});
	
	$('#href_editar').click(function(e) {
		e.preventDefault();

		var indices = [];
		var codigo = ''
			var estado = ''
			tbl_roles.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_roles.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				// Verificamos que tiene mas de un registro marcado y salimos del bucle
				if (!esnulo(codigo)) {
					return false;
				}
				 estado = listaRolesCache[index].idRol;
				var idUsuario = listaRolesCache[index].idRol;
				codigo = codigo + idUsuario + '_';
			}
		});
		
		if (!esnulo(codigo)) {
			console.log("ESTADO: "+estado);
			codigo = codigo.substring(0, codigo.length - 1);
		}
		
		if (indices.length == 0) {
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
		} else {
				var params = { 
					idRol : codigo
				};
				
				loadding(true);

				consultarAjax('GET', '/administracion/roles/listaMenuRoles', params, function(respuesta) {
				
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						console.log(respuesta);
						$('#h4_tit_rol').html('Actualizar Rol');
						$('#hid_id_rol').val(codigo);
						$('#txt_nombre').val(respuesta.descripcionCorta);
						$('#div_menu_rol').html(respuesta.descripcion);
						$('#div_menu_rol').show();
						$('#div_rol').modal('show');
						
						$('#myjstree1').jstree({ 
							"themes" : { "stripes" : true },
							"checkbox": {
					            "keep_selected_style": false
					        },
					        "plugins": ["checkbox"]
						});
						

					}
					loadding(false);
				});
			
			
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
			addWarnMessage(null, 'Debe de Seleccionar por lo menos un Registro');
		} else if (indices.length > 1) {
			addWarnMessage(null, 'Debe de Seleccionar solo un Registro');
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
	
	$('#btn_gra_rol').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_rol.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			
			var params = {
				idRol : $('#hid_id_rol').val(),
				nombreRol : $('#txt_nombre').val(),
				flagActivo: '1'

			};
			
			loadding(true);
			
			consultarAjax('POST', '/administracion/roles/grabarRol', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {

					$('#hid_id_rol').val(respuesta.idRol);
					verMenuNuevo(respuesta.idRol);
					addSuccessMessage(null, 'Se actualizaron los datos para este rol');
					listarTablaRoles();	

				}
				loadding(false);
			});			
		}
		
	});
	
	
});



function inicializarDatos() {
	
  	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#ul_adm_seguridad').css('display', 'block');
	$('#li_adm_roles').attr('class', 'active');
	$('#li_adm_roles').closest('li').children('a');
	$('#divRegiones').hide();
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		listarTablaRoles();	
		
	}
}

function listarTablaRoles() {

	var params = { 
		idRol : ''
	};
	
	loadding(true);

	consultarAjax('GET', '/administracion/roles/listaRoles', params, function(respuesta) {
	
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarRoles(respuesta);
		}
		loadding(false);
	});

}

function listarRoles(respuesta) {

	tbl_roles.dataTable().fnDestroy();
	
	tbl_roles.dataTable({
		data : respuesta,
		columns : [ {
			data : 'idRol',
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
			data : 'idRol',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				return row;											
			}
		}, {
			data : 'idRol'
		}, {
			data : 'nombreRol'
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
	
	listaRolesCache = respuesta;

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

function actualizarRol(idPadre, idMenu, flag){
	console.log("DATOS: "+idPadre+" - "+idMenu);
	var activo=null;

	
	if(!esnulo(flag)){
		if(flag=='1'){
			activo='0';
		}else if(flag=='0'){
			activo='1';
		}
	}else{
		activo='1';
	}
	
	var params = {
		idRol : $('#hid_id_rol').val(),
		idMenu : idMenu,
		idPadre : idPadre, 
		flagActivo: activo

	};
	
	loadding(true);
	
	consultarAjax('POST', '/administracion/roles/grabarMenu', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			verMenuNuevo($('#hid_id_rol').val());
			console.log("EXITOSO");

		}
		loadding(false);
	});			

}

