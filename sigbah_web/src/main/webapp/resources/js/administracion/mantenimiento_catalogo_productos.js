var frm_dat_generales = $('#frm_dat_generales');

$(document).ready(function() {
	
	$("#txt_cod_siga").mask("99.99.9999.9999");
//	$("#txt_largo").mask("999.999");
//	$("#txt_ancho").mask("999.999");
//	$("#txt_alto").mask("999.999");
	inicializarDatos();
	
	
	$('#btn_grabar').click(function(e) {
		e.preventDefault();

		var bootstrapValidator = frm_dat_generales.data('bootstrapValidator');
		
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			
			var params = {
					idProducto : $('#hid_id_catalogo_producto').val(),
					codigoProducto : $('#txt_cod_producto').val(),
					nombreProducto : $('#txt_nombre').val(),
					codigoSiga: $('#txt_cod_siga').val(), 
					idCategoria: $('#sel_categoría').val(), 
					idEnvase : $('#sel_tipo_envase').val(), 
					idUnidadMedida :  $('#sel_unidad_medida').val(), 
					observacion : $('#txt_observaciones').val(), 
					dimLargo : $('#txt_largo').val(), 
					dimAncho : $('#txt_ancho').val(), 
					dimAlto :  $('#txt_alto').val(), 
					pesoNeto :  $('#txt_peso_neto').val(), 
					pesoBruto :  $('#txt_peso_bruto').val(), 
					estado :  $('#sel_estado').val(),
					tipoEnvaseSec : $('#sel_env_secundario').val(),
					unidadEnvaseSec : $('#txt_uni_env').val(),
					descripcionEnvaseSec : $('#txt_des_env').val()

			};
			
			loadding(true);
			
			consultarAjax('POST', '/administracion/catalogo-productos/grabarCatalogoProducto', params, function(respuesta) {
				if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
					addErrorMessage(null, respuesta.mensajeRespuesta);
				} else {
					var codigo=$('#hid_id_catalogo_producto').val();
					if (!esnulo(codigo)) {
						
						addSuccessMessage(null, respuesta.mensajeRespuesta);
						
					} else {
						
						$('#hid_id_catalogo_producto').val(respuesta.idProducto);

						addSuccessMessage(null, 'Se genero el Producto: '+respuesta.idProducto);
						
					}
					
				}
				loadding(false);
			});			
		}
		
	});
	
	$('.btn_retornar').click(function(e) {
		e.preventDefault();

		loadding(true);					
		var url = VAR_CONTEXT + '/administracion/catalogo-productos/inicio';
		$(location).attr('href', url);
		
	});
	
	
	$('#txt_largo').change(function() {
		if(!esnulo($(this).val())){
			$("#txt_largo").val(parseFloat($("#txt_largo").val(),10).toFixed(4));
			var largo =  $("#txt_largo").val();
			var alto = $('#txt_alto').val();
			var ancho = $('#txt_ancho').val();
			var unidades =  $("#txt_uni_env").val();
			
			if(largo!='0'){
				if (!esnulo(largo) && !esnulo(alto) && !esnulo(ancho) && !esnulo(unidades) && (unidades!=0 || unidades!='0')) {
					var imp_total = (parseFloat(largo) * parseFloat(alto) * parseFloat(ancho))/(parseFloat(unidades));
					$('#txt_volumen').val(formatMontoAll3(imp_total));
				} else {
					$('#txt_volumen').val('');
				}
			}else{
				addWarnMessage(null, 'El largo no debe ser 0.');
		    	$('#txt_largo').val('');
		    	$('#'+$(this).attr('id')).focus();
			}		
		}else{
			$('#txt_volumen').val('');
		}
		frm_dat_generales.bootstrapValidator('revalidateField', 'txt_largo');
	});
	
	$('#txt_alto').change(function() {
		if(!esnulo($(this).val())){
			$("#txt_alto").val(parseFloat($("#txt_alto").val(),10).toFixed(4));
			var alto =  $("#txt_alto").val();
			var largo = $('#txt_largo').val();
			var ancho = $('#txt_ancho').val();
			var unidades =  $("#txt_uni_env").val();
			
			if(alto!='0'){
				if (!esnulo(largo) && !esnulo(alto) && !esnulo(ancho) && !esnulo(unidades) && (unidades!=0 || unidades!='0')) {
					var imp_total = (parseFloat(largo) * parseFloat(alto) * parseFloat(ancho))/(parseFloat(unidades));
					$('#txt_volumen').val(formatMontoAll3(imp_total));
				} else {
					$('#txt_volumen').val('');
				}
			}else{
				addWarnMessage(null, 'El alto no debe ser 0.');
		    	$('#txt_largo').val('');
		    	$('#'+$(this).attr('id')).focus();
			}		
		}else{
			$('#txt_volumen').val('');
		}
		frm_dat_generales.bootstrapValidator('revalidateField', 'txt_alto');
	});
	
	$('#txt_ancho').change(function() {
		if(!esnulo($(this).val())){
			$("#txt_ancho").val(parseFloat($("#txt_ancho").val(),10).toFixed(4));
			
			var ancho =  $("#txt_ancho").val();
			var alto = $('#txt_alto').val();
			var largo = $('#txt_largo').val();
			var unidades =  $("#txt_uni_env").val();
			
			if(ancho!='0'){
				if (!esnulo(largo) && !esnulo(alto) && !esnulo(ancho) && !esnulo(unidades) && (unidades!=0 || unidades!='0')) {
					var imp_total = (parseFloat(largo) * parseFloat(alto) * parseFloat(ancho))/(parseFloat(unidades));
					$('#txt_volumen').val(formatMontoAll3(imp_total));
				} else {
					$('#txt_volumen').val('');
				}
			}else{
				addWarnMessage(null, 'El ancho no debe ser 0.');
		    	$('#txt_ancho').val('');
		    	$('#'+$(this).attr('id')).focus();
			}	
		}else{
			$('#txt_volumen').val('');
		}
		frm_dat_generales.bootstrapValidator('revalidateField', 'txt_ancho');
	});
	
	$('#txt_uni_env').change(function() {
		if(!esnulo($(this).val())){
			$("#txt_uni_env").val(parseFloat($("#txt_uni_env").val(),10).toFixed(0));
			var unidades =  $("#txt_uni_env").val();
			var ancho =  $("#txt_ancho").val();
			var alto = $('#txt_alto').val();
			var largo = $('#txt_largo').val();
			
			if(ancho!='0'){
				if (!esnulo(unidades) && !esnulo(alto) && !esnulo(ancho) && !esnulo(largo) && (unidades!=0 || unidades!='0')) {
					var imp_total = (parseFloat(largo) * parseFloat(alto) * parseFloat(ancho))/(parseFloat(unidades));
					$('#txt_volumen').val(formatMontoAll3(imp_total));
				} else {
					$('#txt_volumen').val('');
				}
			}else{
				addWarnMessage(null, 'Unidad por envase no debe ser 0.');
		    	$('#txt_uni_env').val('');
		    	$('#'+$(this).attr('id')).focus();
			}	
		}else{
			$('#txt_volumen').val('');
		}
		frm_dat_generales.bootstrapValidator('revalidateField', 'txt_uni_env');
	});

	
//	var cantidad = $(this).val();
//	var pre_unitario = $('#txt_pre_unitario').val();
//	if (!esnulo(cantidad) && !esnulo(pre_unitario)) {
//		var imp_total = parseFloat(cantidad) * parseFloat(pre_unitario);
//		$('#txt_imp_total').val(formatMontoAll(imp_total));
//	} else {
//		$('#txt_imp_total').val('');
//	}
	
});

function inicializarDatos() {
		
  	$('#li_administracion').addClass('active');
	$('#ul_administracion').css('display', 'block');
	$('#ul_adm_catalogo').css('display', 'block');
	$('#li_adm_catalogo').attr('class', 'active');
	$('#li_adm_catalogo').closest('li').children('a');
	
	if (codigoRespuesta == NOTIFICACION_ERROR) {
		addErrorMessage(null, mensajeRespuesta);
	} else {
		

		if (!esnulo(catalogo_producto.idProducto)) {
			$('#hid_id_catalogo_producto').val(catalogo_producto.idProducto);	

			$('#txt_cod_producto').val(catalogo_producto.codigoProducto);
			$('#txt_cod_siga').val(catalogo_producto.codigoSiga);
			
			$('#sel_estado').val(catalogo_producto.estado);
			$('#sel_categoría').val(catalogo_producto.idCategoria);
			$('#txt_nombre').val(catalogo_producto.nombreProducto);
			$('#sel_unidad_medida').val(catalogo_producto.idUnidadMedida);
			$('#sel_tipo_envase').val(catalogo_producto.idEnvase);
			$('#txt_peso_neto').val(catalogo_producto.pesoNeto);
			$('#txt_peso_bruto').val(catalogo_producto.pesoBruto);
			var largo=catalogo_producto.dimLargo;
			var alto=catalogo_producto.dimAlto;
			var ancho=catalogo_producto.dimAncho;
			var unidades =catalogo_producto.unidadEnvaseSec;
			$('#txt_largo').val(catalogo_producto.dimLargo);
			$('#txt_alto').val(catalogo_producto.dimAlto);
			$('#txt_ancho').val(catalogo_producto.dimAncho);
			
			$('#sel_env_secundario').val(catalogo_producto.tipoEnvaseSec);
			$('#txt_uni_env').val(catalogo_producto.unidadEnvaseSec);
			$('#txt_des_env').val(catalogo_producto.descripcionEnvaseSec);

			if(esnulo(largo) || esnulo(alto) || esnulo(ancho) || unidades==0 || unidades=='0'){
				$('#txt_volumen').val('');
			}else{
				
				
				var volumen = (parseFloat(largo) * parseFloat(alto) * parseFloat(ancho))/(parseFloat(unidades));
				$('#txt_volumen').val(formatMontoAll3(volumen));
			}
			
			
			$('#txt_observaciones').val(catalogo_producto.observacion);
			
		} else {
			
			$('#hid_id_catalogo_producto').val('');	
			$('#txt_cod_producto').val(catalogo_producto.codigoProducto);
			$('#txt_cod_siga').val('');
			
			$('#sel_estado').val('A');
			$('#sel_categoría').val('');
			$('#txt_nombre').val('');
			$('#sel_unidad_medida').val('');
			$('#sel_tipo_envase').val('');
			$('#txt_peso_neto').val('');
			$('#txt_peso_bruto').val('');
			
			$('#sel_env_secundario').val('');
			$('#txt_uni_env').val('');
			$('#txt_des_env').val('');
		}



	}


}

