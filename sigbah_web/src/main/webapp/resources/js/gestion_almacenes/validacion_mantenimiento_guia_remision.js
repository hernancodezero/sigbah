$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			sel_mot_traslado : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Motivo Traslado.'
					}
				}
			},
			sel_estado : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Estado.'
					}
				}
			},
			txt_fec_emision_n : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	
		                	var fec_salida = $('#hid_fec_salida').val();
		                	if ( (esnulo(value))) { 
	            				return { valid: false, message: 'Debe ingresar Fecha Emisión.' }
	            			}

            				if (comparafecha(value, fec_salida) == 2 ) {
	                		   
	                			return { valid: false, message: 'La fecha de emisión debe ser mayor o igual a la fecha de emisión de la orden de salida.' }
	                		}
		            		return true;
		                }
		            }
				}
			},
			
			txt_fec_traslado_n : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	
		                	var fec_emision = $('#txt_fec_emision_n').val();
		                	if ( !(esnulo(value))) { 
		                		if (comparafecha(value, fec_emision) == 2 ) {
		                		 
		                			return { valid: false, message: 'La fecha de traslado debe ser mayor o igual a la fecha de emisión.' }
		                		}
	            			}

		            		return true;
		                }
		            }
				}
			}
			
		}
	});
	
});
