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
			
			sel_mes_fin : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Mes.'
					}					
				}
			},
			
			rb_tipo_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo Movimiento.'
					}					
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
