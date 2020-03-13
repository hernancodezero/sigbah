$(document).ready(function() {

	frm_rep_almacen.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_anio : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Año.'
					}
				}
			},
			sel_mes_inicio : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	var mes_inicio = parseInt(value);
		                	var mes_fin = parseInt($('#sel_mes_fin').val());		                	
		                	if (mes_inicio > mes_fin) {
		                		return { valid: false, message: 'El mes Inicio no puede ser mayor que el mes Fin.' }
		                	}
		            		return true;
		                }
		            }
				}
			},
			sel_mes_fin : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes Fin.'
					}					
//					callback: {
//		                callback: function(value, validator, field) {
//		                	var mes_inicio = parseInt($('#sel_mes_inicio').val());
//		                	var mes_fin = parseInt(value);
//		                	if (mes_inicio > mes_fin) {
//		                		return { valid: false, message: 'El mes Inicio no puede ser mayor que el mes Fin.' }
//		                	}
//		            		return true;
//		                }
//		            }
				}
			}	
		}
	});
	
	frm_tip_reporte.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
//			rb_tip_reporte : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar el Tipo de Reporte.'
//					}
//				}
//			}
		}
	});
	
});
