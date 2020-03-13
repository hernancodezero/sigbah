$(document).ready(function() {

	frm_dat_generales_racion.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			sel_tipo_racion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Ración.'
					}
				}
			},
			txt_nom_racion : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Nombre Ración.'
					}
				}
			},
			txt_num_dias : { 
				validators : {
					notEmpty : {
						message : 'Debe ingresar numero dias de atención.'
					}
				}
			},
			txt_fecha : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar fecha.'
					}
				}
			}
			
		}
	});
	

	
	frm_det_productos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			sel_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
					}
				}
			},
			txt_uni_pres : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Unidad de presentacion.'
					}
				}
			},
			txt_gr_aprox : {
				validators : {
					notEmpty : {
						message : 'Ingrese la cantidad  en Kg. que entregará a cada persona por día'
					}
				}
			}
			
			
		}
	});
});
