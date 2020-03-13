$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			txt_fec_emision : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha Emisión.'
					}
				}
			},
			sel_res_inventario : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Responsable del Inventario.'
					}
				}
			}			
		}
	});
	
	frm_det_productos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
					}
				}
			},
			sel_pro_lote : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Lote.'
					}
				}
			}
		}
	});
	
	frm_det_ajustes.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			txt_aju_sto_fisico : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Stock Físico.'
					}
				}
			}
		}
	});
	
});
