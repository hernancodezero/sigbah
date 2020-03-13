var listaDamnificadosCache = new Object();
var listaEmergenciasActivosCache = new Object();
var listaUbigeosIneiCache = new Object();
var listaDetalleRequerimientoCache = new Object();

var frm_dat_generales = $('#frm_dat_generales');
var tbl_det_afectados = $('#tbl_det_afectados'); 


var frm_det_prog_ubigeo = $('#frm_det_alimentarios');
var tbl_mnt_emer_act = $('#tbl_mnt_emer_act');

var tbl_mnt_ubigeo_inei = $('#tbl_mnt_ubigeo_inei');

var frm_afecta_damni = $('#frm_afecta_damni');

$(document).ready(function() {
	
	$('#mainChkBox').change(function(){
		var dat = this.checked;
		tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]').prop('checked', dat);
		});	
//	     $(':checkbox').prop('checked', this.checked);
		var indices = [];
		tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);
			}
		});
		
		$('#txt_nro_selec_ubi').val(indices.length);
	});

	$('#tbl_det_afectados').on('dblclick','tbody tr', function(evt){
		var dato = $(this).find('td:nth-child(2)').html();
		tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]').prop('checked', false);
		});
		$('#chk_'+dato).prop('checked', true);
		$('#btn_pro_editar').click();
	});

	$('#fixed_hdr1').fxdHdrCol({
		fixedCols: 3,
		width:     '100%',
		height:    400,
		colModal: [
		   { width: 50, align: 'center' },
		   { width: 110, align: 'center' },
		   { width: 170, align: 'left' },
		   { width: 250, align: 'left' },
		   { width: 100, align: 'left' },
		   { width: 70, align: 'left' },
		   { width: 100, align: 'left' },
		   { width: 100, align: 'center' },
		   { width: 90, align: 'left' },
		   { width: 400, align: 'left' }
		]					
	});
	
	$('.datepicker').datepicker({
		autoclose: true,
		todayHighlight: true,
		dateFormat: 'dd/mm/yy',
		clearBtn: true
	});
	

	
	
//	   $('#tbl_det_afectados').dataTable( {
//	        "columnDefs": [
//	            { "type": "numeric-comma", targets: 3 }
//	        ]
//	    } );
	
	inicializarDatos();
	
	$('#href_afec_excel').click(function(e) {
		e.preventDefault();
		var row = $('#tbl_det_afectados > tbody > tr').length;
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
	
		var idRequerimiento = $('#hid_cod_requerimiento').val() ;
		var codAnio = $('#txt_anio').val() ;
		var url = VAR_CONTEXT + '/programacion-bath/requerimiento/exportarExcelAfectados/';
		url += verificaParametro(codAnio)+ '/';
		url += verificaParametro(idRequerimiento);  
		
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

	});
	
	$('#href_req_subir').click(function(e) {
		e.preventDefault();
		$('#div_requerimiento_subir').modal('show');

	});
	
	$('#txt_sub_archivo').change(function(e) {
		e.preventDefault();
	    var file_name = $(this).val();
	    var file_read = file_name.split('\\').pop();
	    $('#txt_lee_sub_archivo').val($.trim(file_read).replace(/ /g, '_'));
	    if (esnulo($('#hid_cod_documento').val())) {
	    	$('#hid_cod_ind_alfresco').val('1'); // Nuevo registro
	    } else {
	    	$('#hid_cod_ind_alfresco').val('2'); // Registro modificado
	    }
//	    frm_det_documentos.bootstrapValidator('revalidateField', 'txt_lee_sub_archivo');	    
	});
	
	$('#btn_gra_per_inei').click(function(e) {
		e.preventDefault();
		var idRequerimiento= $('#hid_cod_requerimiento').val();
			loadding(true);
			
			var params = { 
				idDocumentoSalida : $('#hid_cod_documento').val(),
				idSalida : $('#hid_cod_ord_salida').val(),
				idTipoDocumento : $('#sel_tip_producto').val(),
				nroDocumento : $('#txt_nro_documento').val(),
				fechaDocumento : $('#txt_doc_fecha').val(),
				nombreArchivo : $('#txt_lee_sub_archivo').val()
			};
			
			var cod_ind_alfresco = 1;//$('#hid_cod_ind_alfresco').val();
			if (cod_ind_alfresco == '1' || cod_ind_alfresco == '2') { // Archivo cargado
				var file_name = $('#txt_sub_archivo').val();
				var file_data = null;
				if (!esnulo(file_name) && typeof file_name !== 'undefined') {
					file_data = $('#txt_sub_archivo').prop('files')[0];
				}
				
				var formData = new FormData();
				formData.append('file_doc', file_data);
				// Carpeta contenedor, ubicado en config.properties
		    	formData.append('uploadDirectory', 'params.alfresco.uploadDirectory.almacen');
		    	
				consultarAjaxFile('POST', '/common/archivo/cargarArchivoExcelRequerimiento/'+idRequerimiento, formData, function(resArchivo) {
					if (resArchivo == NOTIFICACION_ERROR) {

						loadding(false);
						addErrorMessage(null, mensajeReporteError);			
					} else {
						addInfoMessage(null, "Se cargó los datos satisfactoriamente");	
						listarDetalleRequerimientoUpload(idRequerimiento);
						loadding(false);
//						params.codigoArchivoAlfresco = resArchivo;
					
						
						$('#div_requerimiento_subir').modal('hide');
//						listarTablasInsertUpdate($('#sel_tablas_generales').val());			
					}
				});
				
			} else { // Archivo no cargado
				
//				params.codigoArchivoAlfresco = $('#hid_cod_act_alfresco').val();
//
//				grabarDetalleDocumento(params);				
			}
		//}
	});

	$('#sel_departamento_emer').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvincia(codigo, null);
		} else {
			$('#sel_provincia_emer').html('');
		}
	});
	$('#sel_departamento_ubi').change(function() {
		var codigo = $(this).val();		
		if (!esnulo(codigo)) {						
			cargarProvinciaUbi(codigo, null);
		} else {
			$('#sel_provincia_ubi').html('');
		}
	});

	
	$('#btn_grabar_dat_gen').click(function(e) {
		e.preventDefault();
		
		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			//		validacion de año
				var anioAct= $('#txt_anio').val();
				var anio = $('#txt_fecha_requerimiento').val().substring(6, 10);
				if(anioAct!=anio){   
					addWarnMessage(null, "El año de la fecha ingresada no coincide  con el año actual");
					return;
				}
				//	fin validacion
			
			var codigo = $('#hid_cod_requerimiento').val();
			
			var numReq = null;
				var numReqConcat = $('#txt_num_requerimiento').val();
				if (!esnulo(numReqConcat)) {
					var arr = numReqConcat.split('-'); 
					numReq = arr[0]; 
				}
			
			
			var params = {
					idRequerimiento:codigo,
				codAnio : $('#txt_anio').val(),
				codMes : requerimiento.codMes,
				fkIdeDdi : requerimiento.fkIdeDdi,
				idDdi : requerimiento.idDdi, 
				fkIdeRegion : $('#sel_region').val(),
				codRequerimiento : requerimiento.codRequerimiento,
				nomRequerimiento :$('#txt_descripcion').val(),
				fechaRequerimiento : $('#txt_fecha_requerimiento').val(),
				flgSinpad : $('input[name="rb_req_sinpad"]:checked').val(),
				fkIdeFenomeno :  $('#sel_fenomeno').val(),
				observacion : $('#txt_observaciones').val(),
				idEstado :  $('#sel_estado').val()
					
			};
			
			loadding(true);
			
			
			consultarAjax('POST', '/programacion-bath/requerimiento/grabarRequerimiento', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					
					if (!esnulo(codigo)) {
						
						addSuccessMessage(null, respuesta.mensajeRespuesta);
						
					} else { 
						
						
						addSuccessMessage(null, 'Se genero el N° Requerimiento '+respuesta.numRequerimiento);
						requerimiento.idRequerimiento=respuesta.idRequerimiento;
						$('#li_damnificados').attr('class', '');
						$('#li_damnificados').closest('li').children('a').attr('data-toggle', 'tab');
						
						$('#txt_nro_req').val(requerimiento.numRequerimiento); 
						$('#txt_des_req').val($('#txt_descripcion').val());
						if($('input:radio[name=rb_req_sinpad]:checked').val()==2){ 
							$('#btn_agregar_emergencia').attr("disabled", true);
						}else{
							$('#btn_agregar_emergencia').attr("disabled", false);
						}
						
						$('#hid_cod_requerimiento').val(respuesta.idRequerimiento);
						$('#txt_num_requerimiento').val(respuesta.numRequerimiento);
						$('#txt_nro_req').val(respuesta.numRequerimiento);
					}
					
				}
				loadding(false);
			});			
		}
		
	});
	
	$('#btn_aceptar_emer').click(function(e) {
		e.preventDefault();
		var params = { 
				codAnio : $('#sel_anio_emer').val(),
				codMes : $('#sel_mes_emer').val(),
				codDpto: $('#sel_departamento_emer').val(),
				codProvincia : $('#sel_provincia_emer').val(),
				idFenomeno : $('#sel_fenomeno_emer').val()
			};
			
			loadding(true);
			
			consultarAjax('GET', '/programacion-bath/requerimiento/listarEmergenciasActivas', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					listarEmergenciasActivas(respuesta);
				}
				loadding(false);
			});
	});
	
	$('#href_emer_acti_exp_excel').click(function(e) {
		e.preventDefault();
		var row = $('#tbl_mnt_emer_act > tbody > tr').length;
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
		
		var codAnio = $('#sel_anio_emer').val();
		var codMes = $('#sel_mes_emer').val();
		var codDpto = $('#sel_departamento_emer').val();
		var codProvincia = $('#sel_provincia_emer').val();
		var idFenomeno = $('#sel_fenomeno_emer').val();
		
		var url = VAR_CONTEXT + '/programacion-bath/requerimiento/exportarExcelEmergenciasActivas/';
		url += verificaParametro(codAnio) + '/';
		url += verificaParametro(codMes) + '/';
		url += verificaParametro(codDpto) + '/';
		url += verificaParametro(codProvincia) + '/';
		url += verificaParametro(idFenomeno);
		
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

	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/programacion-bath/requerimiento/inicio/1';
		$(location).attr('href', url);
		
	});
	
	
	$('#btn_agregar_emergencia').click(function(e) {
		e.preventDefault(); 
		$('#div_det_prog_emerg').modal('show');
		
	});
	
	$('#btn_pasar_distrito').click(function(e) {
		e.preventDefault();
		var indices = [];
		
		tbl_mnt_emer_act.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_emer_act.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else {
			loadding(true);
			var mensaje='';
			for (var i = 0; i < indices.length; i++) {
				var params = { 
						fkIdRequerimiento : $('#hid_cod_requerimiento').val(), 
						idEmergencia : listaEmergenciasActivosCache[indices[i]].idEmergencia,
						codDistrito : listaEmergenciasActivosCache[indices[i]].codDistrito,
						famAfectado : listaEmergenciasActivosCache[indices[i]].famAfectado,
						famDamnificado : listaEmergenciasActivosCache[indices[i]].famDamnificado,
						persoAfectado :listaEmergenciasActivosCache[indices[i]].persoAfectado,
						persoDamnificado : listaEmergenciasActivosCache[indices[i]].persoDamnificado
//						famAfectadoReal : listaEmergenciasActivosCache[index].codAnio,
//						famDamnificadoReal : listaEmergenciasActivosCache[index].codAnio,
//						persoAfectadoReal : listaEmergenciasActivosCache[index].codAnio,
//						persoDamnificadoReal : listaEmergenciasActivosCache[index].codAnio
					};
				
				consultarAjax('GET', '/programacion-bath/requerimiento/pasarDistritos', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						mensaje=	respuesta.mensajeRespuesta;	
						
						cargarRequerimientoDetalle(true);		
					}
					loadding(false);
				});
			}
			if(!esnulo(mensaje)){//muestra solo 1 vez el msj
				addSuccessMessage(null, mensaje);	
			}
		}
});
	
	$('#btn_pasar_distrito_ubigeo').click(function(e) {
		e.preventDefault();
		var indices = [];
		
		tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
			if (tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
				indices.push(index);				
				
			}
		});
		
		if (indices.length == 0) {
			addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
		} else {
			loadding(true);
			var mensaje="";
			for (var i = 0; i < indices.length; i++) {
				var params = { 
						fkIdRequerimiento :$('#hid_cod_requerimiento').val(), 
						codDistrito : listaUbigeosIneiCache[indices[i]].coddist, 
						poblacionINEI : listaUbigeosIneiCache[indices[i]].poblacionInei
			
					};
				
				consultarAjax('GET', '/programacion-bath/requerimiento/pasarDistritosUbigeo', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
						mensaje=	respuesta.mensajeRespuesta;	
						cargarRequerimientoDetalle(true);		
					}
					loadding(false);
				});
			}
			if(!esnulo(mensaje)){
				addSuccessMessage(null, mensaje);	
			}
			
		}
});	
		
		
tbl_mnt_emer_act.on('click', '.checkbox', function(e) {//Contador
	var indices = [];
	tbl_mnt_emer_act.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
		if (tbl_mnt_emer_act.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
			indices.push(index);
		}
	});
	
	$('#txt_nro_selec').val(indices.length);
});

tbl_mnt_ubigeo_inei.on('click', '.checkbox', function(e) {//Contador
	var indices = [];
	tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
		if (tbl_mnt_ubigeo_inei.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
			indices.push(index);
		}
	});
	
	$('#txt_nro_selec_ubi').val(indices.length);
});

$('#btn_agregar_ubigeo').click(function(e) {
	e.preventDefault();
	$('#div_det_prog_ubigeo').modal('show');
	
});

$('#btn_aceptar_ubigeo').click(function(e) { 
	e.preventDefault();
	var params = { 
			codAnio : $('#sel_anio_ubi').val(),
			coddpto: $('#sel_departamento_ubi').val(),
			codprov : $('#sel_provincia_ubi').val()
		};
		
		loadding(true);
		
		consultarAjax('GET', '/programacion-bath/requerimiento/listarUbigeoInei', params, function(respuesta) {
			if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
				addErrorMessage(null, respuesta.mensajeRespuesta);
			} else {
				listarUbigeoInei(respuesta);
			}
			loadding(false);
		});
});


		$('#btn_pro_editar').click(function(e) {
			e.preventDefault();
		
			var indices = [];
			tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
					indices.push(index);
				}
			});
			
			if (indices.length == 0) {
				addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
			} else if (indices.length > 1) {
				addWarnMessage(null, mensajeValidacionSeleccionarSoloUnRegistro);
			} else {
				
				var obj = listaDetalleRequerimientoCache[indices[0]];
				
				$('#h4_tit_afectados').html('Actualizar Afectados y Damnificados');
				
				frm_afecta_damni.trigger('reset');
				
				$('#hid_cod_dam_afec').val(obj.fkIdRequerimientoDamni);
				
				$('#txt_fam_afec').val(obj.famAfectadoReal);
				$('#txt_fam_dam').val(obj.famDamnificadoReal);
				$('#txt_per_afec').val(obj.persoAfectadoReal);
				$('#txt_per_dam').val(obj.persoDamnificadoReal);
				
				$('#txt_m_dto').val(obj.desDepartamento);
				$('#txt_m_prov').val(obj.desProvincia);
				$('#txt_m_dist').val(obj.desDistrito);
				
				$('#txt_nro_req_mo').val(requerimiento.numRequerimiento); 
				$('#txt_des_req_mo').val($('#txt_descripcion').val());
				
				$('#div_mod_actualiza_emer').modal('show');
			}
			
		});

		
		$('#btn_can_actualiza_emer').click(function(e) {
			e.preventDefault();
			frm_afecta_damni.data('bootstrapValidator').resetForm();
		});

		$('#btn_gra_actualiza_emer').click(function(e) {
			e.preventDefault();
			
			var bootstrapValidator = frm_afecta_damni.data('bootstrapValidator');
			bootstrapValidator.validate();
			if (bootstrapValidator.isValid()) {
				
				var params = { 
						//			idEmergencia : $('#hid_cod_dam_afec').val(), replaceAll( text, busca, reemplaza )
									fkIdRequerimiento : $('#hid_cod_dam_afec').val(),   
									famAfectadoReal :replaceAll( $('#txt_fam_afec').val(), ",","" ) ,
									famDamnificadoReal :replaceAll( $('#txt_fam_dam').val(), ",","" ) ,
									persoAfectadoReal :replaceAll( $('#txt_per_afec').val(), ",","" ) , 
									persoDamnificadoReal : replaceAll( $('#txt_per_dam').val(), ",","" )
									
								};
				
				loadding(true);
				
				consultarAjax('POST', '/programacion-bath/requerimiento/actualizarDamnificados', params, function(respuesta) {
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						addErrorMessage(null, respuesta.mensajeRespuesta);
					} else {
							cargarRequerimientoDetalle(true);
							addSuccessMessage(null, respuesta.mensajeRespuesta);	
					}
					loadding(false);
					$('#div_mod_actualiza_emer').modal('hide');
				});			
			}
			
		});

		$('#href_req_eliminar').click(function(e) {
			e.preventDefault();

			var indices = [];
			var codigo = '';
			tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]').each(function(index) {
				if (tbl_det_afectados.DataTable().rows().$('input[type="checkbox"]')[index].checked) {
					indices.push(index);
					var idRequerimiento = listaDetalleRequerimientoCache[index].fkIdRequerimientoDamni;
					codigo = codigo + idRequerimiento + '_';
				}
			});
			
			if (!esnulo(codigo)) {
				codigo = codigo.substring(0, codigo.length - 1);
			}
			
			if (indices.length == 0) {
				addWarnMessage(null, mensajeValidacionSeleccionarRegistro);
			} else {
				var msg = '';
				if (indices.length > 1) {
					msg = mensajeConfirmacionEliminacionVariosRegistros;
				} else {
					msg = mensajeConfirmacionEliminacionSoloUnRegistro;
				}
				
				swal({
					  title: 'Está seguro?',
					  text: msg,
					  type: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'Aceptar',
					  cancelButtonText: 'Cancelar',
					}).then(function () {
						loadding(true);

						var params = { 
								arrIdRequerimiento : codigo
						};
						console.log("idRequerimiento: "+codigo);
						consultarAjax('POST', '/programacion-bath/requerimiento/eliminarRequerimiento', params, function(respuesta) {
							if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
								loadding(false);
								addErrorMessage(null, respuesta.mensajeRespuesta);
							} else {
								cargarRequerimientoDetalle(true);
								addSuccessMessage(null, respuesta.mensajeRespuesta);							
							}
							loadding(false);
						});
					  swal(
						'Eliminado!',
						'Se ha eliminado satisfactoriamente.',
						'success'
					  )
					});
				
//				$.SmartMessageBox({
//					title : msg,
//					content : '',
//					buttons : '[Cancelar][Aceptar]'
//				}, function(ButtonPressed) {
//					if (ButtonPressed === 'Aceptar') {
//		
//						loadding(true);
//
//						var params = { 
//								arrIdRequerimiento : codigo
//						};
//						console.log("idRequerimiento: "+codigo);
//						consultarAjax('POST', '/programacion-bath/requerimiento/eliminarRequerimiento', params, function(respuesta) {
//							if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
//								loadding(false);
//								addErrorMessage(null, respuesta.mensajeRespuesta);
//							} else {
//								cargarRequerimientoDetalle(true);
//								addSuccessMessage(null, respuesta.mensajeRespuesta);							
//							}
//							loadding(false);
//						});
//						
//					}	
//				});
			}

			
		});
		
		$('#href_afec_imprimir').click(function(e) {
			e.preventDefault();

			var indices = [];
			var codigo = $('#hid_cod_requerimiento').val();
			
				
				
				loadding(true);
				var url = VAR_CONTEXT + '/programacion-bath/requerimiento/exportarPdf/'+codigo;

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
		
	
});

function inicializarDatos() {
	
	$('#li_pro_bah').addClass('active');
	$('#ul_pro_bah').css('display', 'block');
	$('#li_req_edan').attr('class', 'active');
	$('#li_req_edan').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		
		$('#txt_num_requerimiento').val(requerimiento.numRequerimiento);
		$('#txt_anio').val(requerimiento.codAnio);
		$('#txt_fecha_requerimiento').val(requerimiento.fechaRequerimiento);
		
		if (!esnulo(requerimiento.idRequerimiento)) {//editar
			
			$('#li_damnificados').attr('class', '');
			$('#li_damnificados').closest('li').children('a').attr('data-toggle', 'tab');
			
			$('#hid_cod_requerimiento').val(requerimiento.idRequerimiento);//usamos paa el listado de detalle requerimientos
			$('#sel_estado').val(requerimiento.idEstado); 
			$('#txt_descripcion').val(requerimiento.nomRequerimiento);
			$('#txt_observaciones').val(requerimiento.observacion);
			$('#sel_region').val(requerimiento.fkIdeRegion);
			$('#sel_fenomeno').val(requerimiento.fkIdeFenomeno);
			$('input[name=rb_req_sinpad][value="'+requerimiento.flgSinpad+'"]').prop('checked', true);
			
			$('#txt_nro_req').val(requerimiento.numRequerimiento); 
			$('#txt_des_req').val(requerimiento.nomRequerimiento);
			
			listarDetalleRequerimiento(lista_requerimiento);

			
		} else {//nuevo
			
			$('#li_damnificados').addClass('disabled'); //whr descomentar de abajo para activar bloqueo de tab
			$('#li_damnificados').closest('li').children('a').removeAttr('data-toggle'); 
			
			$('#txt_descripcion').val(requerimiento.nomRequerimiento);
			$('#txt_observaciones').val(requerimiento.observacion);

			 $('input[name=rb_req_sinpad][value="'+requerimiento.flgSinpad+'"]').prop('checked', true);
			if (requerimiento.flgSinpad == '1') {
				$('#btn_agregar_emergencia').attr("disabled", false);
			} else {
				$('#btn_agregar_emergencia').attr("disabled", true);
			}
			$('#sel_fenomeno').val(requerimiento.fkIdeFenomeno);
			$('#sel_region').val(requerimiento.fkIdeRegion);
			$('#sel_estado').val('1'); 
			$('#txt_nro_req').val(requerimiento.numRequerimiento);
			$('#txt_des_req').val(requerimiento.nomRequerimiento); 
			
			listarDetalleRequerimiento(lista_requerimiento);

		}
		
	}
	
}

function cargarRequerimientoDetalle(indicador) {
	var params = { 
			idRequerimiento : $('#hid_cod_requerimiento').val() ,
			codAnio :  $('#txt_anio').val()
	};			
	consultarAjaxSincrono('GET', '/programacion-bath/requerimiento/listarRequerimientoDetalle', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleRequerimiento(respuesta);
		}
	});
}

function cargarProvincia(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/programacion-bath/requerimiento/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia_emer').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia_emer').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function cargarProvinciaUbi(codigo, codigoProvincia) {
	var params = { 
		coddpto : codigo
	};			
	loadding(true);
	consultarAjax('GET', '/programacion-bath/requerimiento/listarProvincia', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			var options = '<option value="">Seleccione</option>';
			$.each(respuesta, function(i, item) {
				options += '<option value="'+item.codprov+'">'+item.nombre+'</option>';
			});
			$('#sel_provincia_ubi').html(options);
			if (codigoProvincia != null) {
				$('#sel_provincia_ubi').val(codigoProvincia);       	
			}
		}
		loadding(false);
	});
}

function listarEmergenciasActivas(respuesta) {

	tbl_mnt_emer_act.dataTable().fnDestroy();
	
	tbl_mnt_emer_act.dataTable({
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
//				data : 'poblacionInei'
//			}, {
				data : 'famAfectado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
			}, {
				data : 'famDamnificado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
			}, {
				data : 'totalFam',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
			}, {
				data : 'persoAfectado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
			}, {
				data : 'persoDamnificado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
			}, {
				data : 'totalPerso',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)
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
				{ width : '15%', targets : 3 },
				{ width : '15%', targets : 4 },
				{ width : '15%', targets : 5 },
				{ width : '18%', targets : 7 }
			]
		});
		
	listaEmergenciasActivosCache = respuesta;

	}

function listarUbigeoInei(respuesta) {

	tbl_mnt_ubigeo_inei.dataTable().fnDestroy();
	tbl_mnt_ubigeo_inei.dataTable({
			data : respuesta,
			columns : [ {
					data : 'coddpto',
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
					data : 'coddpto',
					render : function(data, type, full, meta) {
						var row = meta.row + 1;
						return row;											
					}
				}, {data : 'coddist'}, 
				{data : 'desprov'}, 
				{data : 'desdist'}, 
				{data : 'poblacionInei',sClass : 'opc-right'} 
			],
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
				{ width : '15%', targets : 3 },
				{ width : '15%', targets : 4 },
				{ width : '15%', targets : 5 }
			]
		});
		
	listaUbigeosIneiCache = respuesta;

	}



function listarDetalleRequerimiento(respuesta) {

	tbl_det_afectados.dataTable().fnDestroy();
	tbl_det_afectados.dataTable({
			
			data : respuesta,

			columns : [ {
					
					data : 'idEmergencia',
					sClass : 'opc-center',
					render: function(data, type, row, meta) {
						var row = meta.row + 1;
						if (data != null) {
							return '<label class="checkbox">'+
										'<input type="checkbox" id="chk_'+row+'" name="ids[]"><i></i>'+
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
				}, {data : 'desDepartamento'}, 
				{data : 'desProvincia'}, 
				{data : 'desDistrito'}, 
				{data : 'idEmergencia'},
				{data : 'poblacionINEI',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'famAfectado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'famDamnificado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'totalFam',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'persoAfectado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'persoDamnificado',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'totalPerso',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'famAfectadoReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'famDamnificadoReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'totalFamReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)},
				{data : 'persoAfectadoReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)}, 
				{data : 'persoDamnificadoReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)}, 
				{data : 'totalPersoReal',sClass : 'opc-right',render: $.fn.dataTable.render.number( ',', '.', 0)}
			],
			language : {
				'url' : VAR_CONTEXT + '/resources/js/Spanish.json'
			},
			bFilter : false,
			paging : false,
			ordering : false,
			info : false,
			fixedHeader: true,
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
				total_per_afec = api.column(10, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_per_dam = api.column(11, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_per = api.column(12, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_fam_afec_real = api.column(13, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				
				total_fam_dam_real = api.column(14, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_fam_real = api.column(15, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_per_afec_real = api.column(16, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_per_dam_real = api.column(17, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );
				total_per_real = api.column(18, { page: 'current'} ).data().reduce( function (a, b) {
					return intVal(a) + intVal(b);
				}, 0 );

				// Update footer
				$('#sp_tot_inei').html(parseFloat(total_inei).toFixed(0));
				$('#sp_tot_fam_afec').html(parseFloat(total_fam_afec).toFixed(0));
				$('#sp_tot_fam_dam').html(parseFloat(total_fam_dam).toFixed(0));
				$('#sp_tot_fam').html(parseFloat(total_fam).toFixed(0));
				$('#sp_tot_per_afec').html(parseFloat(total_per_afec).toFixed(0));
				$('#sp_tot_per_dam').html(parseFloat(total_per_dam).toFixed(0));
				$('#sp_tot_per').html(parseFloat(total_per).toFixed(0));
				$('#sp_tot_fam_afec_real').html(parseFloat(total_fam_afec_real).toFixed(0));
				$('#sp_tot_fam_dam_real').html(parseFloat(total_fam_dam_real).toFixed(0));
				$('#sp_tot_fam_real').html(parseFloat(total_fam_real).toFixed(0));
				$('#sp_tot_per_afec_real').html(parseFloat(total_per_afec_real).toFixed(0));
				$('#sp_tot_per_dam_real').html(parseFloat(total_per_dam_real).toFixed(0));
				$('#sp_tot_per_real').html(parseFloat(total_per_real).toFixed(0));
			
			},
			iDisplayLength : 15,
			aLengthMenu : [
				[15, 50, 100],
				[15, 50, 100]
			],
			columnDefs : [
				{ width : '15%', targets : 3 },
				{ width : '15%', targets : 4 },
				{ width : '15%', targets : 5 }
			]
		});
		
	listaDetalleRequerimientoCache = respuesta;
	
	
	
	
//	$('#tbl_det_afectados').fxdHdrCol({
//		fixedCols:  0,
//		width:     "100%",
//		height:    400,
//		colModal: [
//			   { width: 50, align: 'center' },
//			   { width: 110, align: 'center' },
//			   { width: 170, align: 'left' },
//			   { width: 250, align: 'left' },
//			   { width: 100, align: 'left' },
//			   { width: 70, align: 'left' },
//			   { width: 100, align: 'left' },
//			   { width: 100, align: 'center' },
//			   { width: 90, align: 'left' },
//			   { width: 400, align: 'left' }
//		],
//		sort: true
//	});
	
	

	}
function replaceAll( text, busca, reemplaza ){
	while (text.toString().indexOf(busca) != -1)
	text = text.toString().replace(busca,reemplaza);
	return text;
	}

function listarDetalleRequerimientoUpload(idRequerimiento) {
	var params = { 
		icodigo : idRequerimiento
	};			
	consultarAjaxSincrono('GET', '/programacion-bath/requerimiento/listarDetalleRequerimientoUpload', params, function(respuesta) {
		if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
			addErrorMessage(null, respuesta.mensajeRespuesta);
		} else {
			listarDetalleRequerimiento(respuesta);

		}
	});
}