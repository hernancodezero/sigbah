$(document).ready(function() {

	frm_usuarios.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_usuario : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Usuario.'
					}
				}
			},
			
			txt_password : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Contraseña.'
					}
				}
			},
			
			txt_nombre : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Nombre.'
					}
				}
			},
			
			txt_cargo : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Cargo.'
					}
				}
			},
			
	
			txt_correo : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
                				return { valid: false, message: 'Debe ingresar Correo.' }
                			}
		                	
		                	var caract = new RegExp(/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/);

		                    if (caract.test(value) == false){
		                        
		                    	return { valid: false, message: 'Debe ingresar Correo correcto.' }
		                        return false;
		                    }else{
		                       
//		                        $(div).html('');
		                        return true;
		                    }

		                }
		            }
				}
			},
			
			sel_ddi : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Ddi.'
					}
				}
			}
		
			
		}
	});
	
	
	
});
