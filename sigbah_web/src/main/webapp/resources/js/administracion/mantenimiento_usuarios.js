var listaAlmacenCache = new Object();
var listaRolCache = new Object();
var listaDonacionCache = new Object();
var listaProgramacionCache = new Object();
var listaInventarioCache = new Object();

var frm_usuarios = $('#frm_usuarios');

var tbl_almacenes = $('#tbl_almacenes');
var tbl_roles = $('#tbl_roles');
var tbl_estado_donacion = $('#tbl_estado_donacion');
var tbl_estado_programacion = $('#tbl_estado_programacion');
var tbl_estado_inventario = $('#tbl_estado_inventario');

$(document).ready(function() {

	
	inicializarDatos();
	
	$('input[type=radio][name=rb_tie_ate_gobierno]').change(function() {
		cargarTipoAtencion(this.value);
    });
	
	$('#sel_ddi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			listarAlmacenes(codigo);
		} else {
			
		}
	});

	$('#btn_grabar').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_usuarios.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {

			var flag = $('input[name="rb_estado"]:checked').val();
			
			var params = {
				idUsuario : $('#hid_id_usuario').val(),
				usuario : $('#txt_usuario').val(),
				password : $('#txt_password').val(),
				nombreUsuario : $('#txt_nombre').val(),
				email : $('#txt_correo').val(),
				cargo : $('#txt_cargo').val(),
				idDdi : $('#sel_ddi').val(),
				flagActivo : flag
			};
			
			loadding(true);
			
			consultarAjax('POST', '/administracion/usuarios/grabarUsuario', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				}else{
					if(respuesta.codigoRespuesta == NOTIFICACION_VALIDACION){
						addErrorMessage(null, respuesta.mensajeRespuesta);
					}else {

						$('#hid_id_usuario').val(respuesta.idUsuario);
						$('#div_tbl_almacen').show();
						$('#div_tbl_rol').show();
						$('#div_tbl_estado_donacion').show();
						$('#div_tbl_estado_programacion').show();
						$('#div_tbl_estado_inventario').show();
						addSuccessMessage(null, 'Se registró el usuario: '+respuesta.idUsuario);

					}
				} 
				
				loadding(false);
			});			
		}
		
	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/administracion/usuarios/inicio';
		$(location).attr('href', url);
		
	});
	

	
});

function inicializarDatos() {
	
	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#ul_adm_seguridad').css('display', 'block');
	$('#li_adm_usuarios').attr('class', 'active');
	$('#li_adm_usuarios').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		console.log("IDUSUARIO: "+usuario.idUsuario);
		if (!esnulo(usuario.idUsuario)) {
			
			$('#hid_id_usuario').val(usuario.idUsuario);		
			
			$('#txt_usuario').val(usuario.usuario);
			$('#txt_password').val(usuario.password);
			$('#txt_nombre').val(usuario.nombreUsuario);
			$('#txt_cargo').val(usuario.cargo);			
			$('#txt_correo').val(usuario.email);			
		
			$('input[name=rb_estado][value="'+usuario.flagActivo+'"]').prop('checked', true);
			$('#sel_ddi').val(usuario.idDdi);
			listarAlmacenes(usuario.idDdi);
			listarRoles();
			listarEstadosDonacion();
			listarEstadosProgramacion();
			listarEstadosInventario();
			$('#div_tbl_almacen').show();
			$('#div_tbl_rol').show();
			$('#div_tbl_estado_donacion').show();
			$('#div_tbl_estado_programacion').show();
			$('#div_tbl_estado_inventario').show();
		} else {
			$('#div_tbl_almacen').hide();
			$('#div_tbl_rol').hide();
			$('#div_tbl_estado_donacion').hide();
			$('#div_tbl_estado_programacion').hide();
			$('#div_tbl_estado_inventario').hide();
			listarAlmacenes();
			listarRoles();
			listarEstadosDonacion();
			listarEstadosProgramacion();
			listarEstadosInventario();
//			cargarTipoMovimiento($('#sel_tip_movimiento').val());
//			
//			listarDetalleProductos(new Object());
//			listarDetalleDocumentos(new Object());

		}
		
	}
		
}

function listarAlmacenes(idDdi) {
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idDdi : idDdi
	};			
	loadding(true);
	consultarAjaxSincrono('GET', '/administracion/usuarios/listarAlmacenes', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleAlmacenes(respuesta);
			loadding(false);

		}
	});
}

function listarDetalleAlmacenes(respuesta) {

	tbl_almacenes.dataTable().fnDestroy();
	var idAlm;
	tbl_almacenes.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idAlmacen',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				idAlm=data;
				return row;											
			}
		}, {
			data : 'nombreAlmacen'
		},{
			data : 'existe',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data =='1') {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoAlmacen('+data+', '+idAlm+')" checked><i></i>'+
					'</label>';	
				} else {
					return '<label class="checkbox" onclick="mantenimientoAlmacen('+data+', '+idAlm+')">'+
					'<input type="checkbox"><i></i>'+
					'</label>';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaAlmacenCache = respuesta;

}

function mantenimientoAlmacen(indicador, idAlmacen){
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idAlmacen : idAlmacen,
		idDdi : $('#sel_ddi').val()
	};	
	if(indicador=='1'){		
		consultarAjaxSincrono('GET', '/administracion/usuarios/eliminarAlmacenes', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleAlmacenes(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}else{
		consultarAjaxSincrono('GET', '/administracion/usuarios/insertarAlmacenes', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleAlmacenes(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}
}

function listarRoles() {
	var params = { 
		idUsuario : $('#hid_id_usuario').val()
	};			
	consultarAjaxSincrono('GET', '/administracion/usuarios/listarRoles', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleRoles(respuesta);
			if (indicador) {
				loadding(false);
			}

		}
	});
}

function listarDetalleRoles(respuesta) {

	tbl_roles.dataTable().fnDestroy();
	var idRo;
	tbl_roles.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idRol',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				idRo=data;
				return row;											
			}
		}, {
			data : 'nombreRol',
			render: function(data, type, row) {
				if (data != null) {
					return '<button type="button" onclick="mostrarRol('+idRo+')"'+ 
						   'class="btn btn-link input-sm btn_exp_doc">'+data+'</button>';
				} else {
					return '';	
				}											
			}
		},{
			data : 'existe',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data =='1') {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoRol('+data+', '+idRo+')" checked><i></i>'+
					'</label>';	
				} else {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoRol('+data+', '+idRo+')"><i></i>'+
					'</label>';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaRolCache = respuesta;

}

function mantenimientoRol(indicador, idRol){
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idRol : idRol
		
	};	
	if(indicador=='1'){		
		consultarAjaxSincrono('GET', '/administracion/usuarios/eliminarRoles', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleRoles(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}else{
		consultarAjaxSincrono('GET', '/administracion/usuarios/insertarRoles', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleRoles(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}
}

function mostrarRol(idRol){
	console.log("IDROL: "+idRol);
	var params = { 
		idRol : idRol
	};	
	
	consultarAjaxSincrono('GET', '/administracion/usuarios/mostrarRoles', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			$('#h4_tit_rol').html('Rol: '+respuesta.descripcionCorta);
			$('#div_menu_rol').html(respuesta.descripcion);
			$('#div_rol').modal('show');

		}
	});

}


function listarEstadosDonacion() {
	var params = { 
		idUsuario : $('#hid_id_usuario').val()
	};			
	consultarAjaxSincrono('GET', '/administracion/usuarios/listarEstadosDonacion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleEstadosDonacion(respuesta);
			if (indicador) {
				loadding(false);
			}

		}
	});
}

function listarDetalleEstadosDonacion(respuesta) {

	tbl_estado_donacion.dataTable().fnDestroy();
	var idUs;
	tbl_estado_donacion.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				idUs=data;
				return row;											
			}
		}, {
			data : 'nombreEstado',

		},{
			data : 'existe',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data =='1') {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoDonacion('+data+', '+idUs+')" checked><i></i>'+
					'</label>';	
				} else {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoDonacion('+data+', '+idUs+')"><i></i>'+
					'</label>';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaDonacionCache = respuesta;

}

function mantenimientoDonacion(indicador, idEstado){
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idEstado : idEstado
		
	};	
	if(indicador=='1'){		
		consultarAjaxSincrono('GET', '/administracion/usuarios/eliminarDonaciones', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosDonacion(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}else{
		consultarAjaxSincrono('GET', '/administracion/usuarios/insertarDonaciones', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosDonacion(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}
}

function listarEstadosProgramacion() {
	var params = { 
		idUsuario : $('#hid_id_usuario').val()
	};			
	consultarAjaxSincrono('GET', '/administracion/usuarios/listarEstadosProgramacion', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleEstadosProgramacion(respuesta);
			if (indicador) {
				loadding(false);
			}

		}
	});
}

function listarDetalleEstadosProgramacion(respuesta) {

	tbl_estado_programacion.dataTable().fnDestroy();
	var idPr;
	tbl_estado_programacion.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				idPr=data;
				return row;											
			}
		}, {
			data : 'nombreEstado',

		},{
			data : 'existe',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data =='1') {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoProgramacion('+data+', '+idPr+')" checked><i></i>'+
					'</label>';	
				} else {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoProgramacion('+data+', '+idPr+')"><i></i>'+
					'</label>';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaProgramacionCache = respuesta;

}

function mantenimientoProgramacion(indicador, idEstado){
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idEstado : idEstado
		
	};	
	if(indicador=='1'){		
		consultarAjaxSincrono('GET', '/administracion/usuarios/eliminarProgramaciones', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosProgramacion(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}else{
		consultarAjaxSincrono('GET', '/administracion/usuarios/insertarProgramaciones', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosProgramacion(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}
}

function listarEstadosInventario() {
	var params = { 
		idUsuario : $('#hid_id_usuario').val()
	};			
	consultarAjaxSincrono('GET', '/administracion/usuarios/listarEstadosInventario', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleEstadosInventario(respuesta);
			if (indicador) {
				loadding(false);
			}

		}
	});
}

function listarDetalleEstadosInventario(respuesta) {

	tbl_estado_inventario.dataTable().fnDestroy();
	var idIn;
	tbl_estado_inventario.dataTable({
		data : respuesta,
		columns : [  {	
			data : 'idEstado',
			render : function(data, type, full, meta) {
				var row = meta.row + 1;
				idIn=data;
				return row;											
			}
		}, {
			data : 'nombreEstado',

		},{
			data : 'existe',
			sClass : 'opc-center',
			render: function(data, type, row) {
				if (data =='1') {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoInventario('+data+', '+idIn+')" checked><i></i>'+
					'</label>';	
				} else {
					return '<label class="checkbox">'+
					'<input type="checkbox" onclick="mantenimientoInventario('+data+', '+idIn+')"><i></i>'+
					'</label>';	
				}											
			}
		}],
		language : {
			'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
		},
		bFilter : false,
		paging : false,
		ordering : false,
		info : false
	});
	
	listaInventarioCache = respuesta;

}

function mantenimientoInventario(indicador, idEstado){
	var params = { 
		idUsuario : $('#hid_id_usuario').val(),
		idEstado : idEstado
		
	};	
	if(indicador=='1'){		
		consultarAjaxSincrono('GET', '/administracion/usuarios/eliminarInventarios', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosInventario(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}else{
		consultarAjaxSincrono('GET', '/administracion/usuarios/insertarInventarios', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarDetalleEstadosInventario(respuesta);
				if (indicador) {
					loadding(false);
				}

			}
		});
	}
}


function limpiarFormularioProducto() {
	$('#sel_cat_producto').val('');
	$('#sel_producto').val('');
	$('#sel_lote').val('');
	$('#txt_uni_medida').val('');
	$('#txt_pes_net_unitario').val('');
	$('#txt_pes_bru_unitario').val('');
	$('#txt_cantidad').val('');
	$('#txt_pre_unitario').val('');
	$('#txt_imp_total').val('');
}

function cargarTipoAtencion(valor) {
	if (valor == 'R') {
		$('#div_gore_destino').show();
		$('#div_ubi_destino').hide();
	} else if (valor == 'L') {
		$('#div_gore_destino').hide();
		$('#div_ubi_destino').show();
	}
}

