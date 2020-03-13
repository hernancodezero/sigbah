$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({  
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
		
			sel_pedidoPor : {
				validators : { 
					notEmpty : {
						message : 'Debe seleccionar pedido por.'
					}
				}
			},
			txt_descripcion : { 
				validators : {
					notEmpty : {
						message : 'Debe ingresar descripción.'
					}
				}
			},
			sel_dee : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar DEE.'
					}
				}
			},
			sel_estado : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Estado.'
					}
				}
			}
			
		}
	});
	
	frm_productos.bootstrapValidator({  
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_cat_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Categoria de Producto.'
					}
				}
			},
			sel_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
					}
				}
			}
			
		}
	});
	
	
	frm_det_documentos.bootstrapValidator({  
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_tipo_doc : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo Documento.'
					}
				}
			},
			txt_num_doc : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Numero Documento.'
					}
				}
			}
			
		}
	});
});
