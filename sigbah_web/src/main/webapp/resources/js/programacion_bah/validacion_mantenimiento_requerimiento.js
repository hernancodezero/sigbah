$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			txt_descripcion : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar decripción.'
					}
				}
			},
			txt_fecha_requerimiento : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha.'
					}
				}
			},
			sel_region : { 
				validators : {
					notEmpty : {
						message : 'Debe seleccionar región.'
					}
				}
			},
			sel_fenomeno : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Fenómeno.'
					}
				}
			},
			rb_req_sinpad : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Requerimiento SINPAD.'
					}
				}
			}
			,
			sel_estado : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Estado.'
					}
				}
			}
			
			
		}
	});
	
	
	frm_afecta_damni.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			txt_fam_afec : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fam. Afec.'
					}
				}
			},
			txt_fam_dam : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fam. Dam.'
					}
				}
			},
			txt_per_afec : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Pers. Afec.'
					}
				}
			},
			txt_per_dam : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Pers. Dam.'
					}
				}
			}
		}
	});
	
});
