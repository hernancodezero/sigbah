$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			
			sel_categoría : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Categoría del Producto.'
					}
				}
			},
			
			txt_nombre : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre del Producto.'
					}
				}
			},
			
			sel_unidad_medida : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Unidad de Medida.'
					}
				}
			},
			
			sel_tipo_envase : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Envase.'
					}
				}
			},
			
			txt_peso_neto : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Peso Neto.'
					}
				}
			},
			
			txt_peso_bruto : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Peso Bruto.'
					}
				}
			}, 
			
			txt_largo : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar el Largo.'
					}
				}
			},
			
			txt_alto : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar el Alto.'
					}
				}
			},
			
			txt_ancho : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar el Ancho.'
					}
				}
			},
			
			sel_env_secundario : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Envase Secundario.'
					}
				}
			},
			
			txt_uni_env : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Unidades por Envase.'
					}
				}
			},
			
			txt_des_env : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Descripción.'
					}
				}
			}
			
		}
	});
	
	
	
});
