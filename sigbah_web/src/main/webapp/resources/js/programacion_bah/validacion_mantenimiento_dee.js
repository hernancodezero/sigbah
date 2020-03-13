$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({ 
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			
			txt_num_dee : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar N° DEE.'
					}
				}
			},
			txt_des_dee : { 
				validators : {
					notEmpty : {
						message : 'Debe ingresar Des. DEE.'
					}
				}
			},
			txt_fecha_ini : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar fecha inicio.'
					}
				}
			},
			txt_fecha_fin : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar fecha fin.'
					}
				}
			},
			txt_num_dias : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar número de días'
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
			txt_motivo : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar motivo.'
					}
				}
			},
			flgProrroga : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar tiene prorroga.'
					}
				}
			}
			
			
		}
	});
	
	
	frm_det_prog_ubigeo.bootstrapValidator({ 
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			
			sel_departamento_ubi : {
				validators : {
					notEmpty : {
						message : 'Seleccione Departamento.'
					}
				}
			},
			sel_provincia_ubi : { 
				validators : {
					notEmpty : {
						message : 'Seleccione Provincia.'
					}
				}
			}
			
		}
	});
	
	
});
