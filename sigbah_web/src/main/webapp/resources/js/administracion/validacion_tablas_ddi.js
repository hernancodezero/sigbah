$(document).ready(function() {

	frm_almacen.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_dir_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Dirección.'
					}
				}
			},
			txt_car_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Característica.'
					}
				}
			},
			txt_sec_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre Secundario.'
					}
				}
			},
			sel_ddi_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar DDI.'
					}
				}
			},
			sel_mod_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Modalidad Almacén.'
					}
				}
			},
			sel_est_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Modalidad Almacén.'
					}
				}
			}
			
		}
	});
	
	frm_almacen_externo.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_alm_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_direccion_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Dirección.'
					}
				}
			},
			rb_tie_ate_gobierno : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Tipo.'
					}
				}
			},

			sel_gore : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar GORE.'
					}
				}
			},
			sel_departamento : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Departamento.'
					}
				}
			},
			sel_provincia : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Provincia.'
					}
				}
			},
			sel_distrito : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Distrito.'
					}
				}
			}
			
		}
	});
	
	frm_anio.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_cod_ddi : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Código DDI.'
					}
				}
			},
			
			txt_nom_ddi : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			}
			
		}
	});
	
	frm_ddi.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_cod_anio : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Código Año.'
					}
				}
			},
			
			txt_des_anio : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Descripción.'
					}
				}
			}
			
		}
	});
	
	frm_donante.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_donante : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_doc_donante : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Documento.'
					}
				}
			},
			
			sel_tip_donante : {
				validators : {
					notEmpty : {
						message : 'Debe Selecionar Tipo.'
					}
				}
			},
			
			txt_rep_donante : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Representante.'
					}
				}
			}
			
		}
	});
	
	frm_empresa_transporte.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_dir_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Dirección.'
					}
				}
			},
			
			txt_rep_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Representante.'
					}
				}
			},
			
			txt_ruc_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Ruc.'
					}
				}
			},
			
			txt_tel_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Teléfono.'
					}
				}
			},
			
			sel_med_emp : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Medio Transporte.'
					}
				}
			}
			
		}
	});
	
	frm_envase.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_env : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_des_env : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Descripción Corta.'
					}
				}
			}
			
		}
	});
	
	frm_mod_almacen.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_mod_alm : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			}
			
		}
	});
	
	frm_oficina.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_ofi : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			sel_ddi_ofi : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar DDI.'
					}
				}
			}
			
		}
	});
	
	frm_personal.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_per : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombres.'
					}
				}
			},
			
			txt_ape_per : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Apellidos.'
					}
				}
			},
			
			txt_car_per : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Cargo.'
					}
				}
			},
			
			sel_ofi_per : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Oficina.'
					}
				}
			},
			
			txt_tel_per : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Teléfono.'
					}
				}
			}
			
		}
	});
	
	frm_personal_externo.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_car_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Cargo.'
					}
				}
			},
			
			txt_tel_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Teléfono.'
					}
				}
			},
			
			rb_tie_ate_gobierno_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Tipo de Personal.'
					}
				}
			},
			
			sel_gore_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar GORE.'
					}
				}
			},
			
			sel_departamento_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Departamento.'
					}
				}
			},
			
			sel_provincia_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Provincia.'
					}
				}
			},
			
			sel_distrito_per_ext : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Distrito.'
					}
				}
			}
			
		}
	});
	
	frm_tipo_camion.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_des_tip_cam : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Descripción.'
					}
				}
			},
			
			txt_ton_tip_cam : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Tonelaje.'
					}
				}
			},
			
			txt_vol_tip_cam : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Volumen.'
					}
				}
			}
			
		}
	});
	
	frm_tipo_control_calidad.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_tip_con : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			}
			
		}
	});
	
	frm_tipo_documento.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_tip_doc : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			}
			
//			txt_cor_tip_doc : {
//				validators : {
//					notEmpty : {
//						message : 'Debe Ingresar Nombre Corto.'
//					}
//				}
//			}
			
		}
	});
	
	frm_unidad_medida.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_uni_med : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_ncor_uni_med : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre Corto.'
					}
				}
			}
			
		}
	});
	
	frm_chofer.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			sel_emp_transporte : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Empresa de Transporte.'
					}
				}
			},
			
			txt_nom_cho : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre.'
					}
				}
			},
			
			txt_dni_cho : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Dni.'
					}
				}
			},
			
			txt_bre_cho : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Brevete.'
					}
				}
			},
			
			sel_est_cho : {
				validators : {
					notEmpty : {
						message : 'Debe Selecionar Estado'
					}
				}
			}
			
		}
	});
	
	frm_correlativo.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			
			txt_nom_alm_corr : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre del Almacén.'
					}
				}
			},
			
			txt_nro_serie_corr : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Número de Serie.'
					}
				}
			},
			
			txt_nro_inicio_corr : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	
		                	var nro_fin = $('#txt_nro_final_corr').val();
		   
		                	if (zeroFill(value,6)>nro_fin) { 
	            				return { valid: false, message: 'Debe ser menor que número final.' }
	            			}

		            		return true;
		                }
		            }
				}
			},
			
			txt_nro_final_corr : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	
		                	var nro_ini = $('#txt_nro_inicio_corr').val();

		                	if (zeroFill(value,6)<nro_ini) { 
	            				return { valid: false, message: 'Debe ser mayor que número inicial.' }
	            			}

		            		return true;
		                }
		            }
				}
			},
			
			txt_nro_actual_corr : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	var nro_ini = $('#txt_nro_inicio_corr').val();
		                	var nro_fin = $('#txt_nro_final_corr').val();
		                	
		                	if (zeroFill(value,6)>nro_fin || zeroFill(value,6)<nro_ini) { 
	            				return { valid: false, message: 'Debe estar en el rango entre número inicial y número final.' }
	            			}

		            		return true;
		                }
		            }
				}
			},
			
			txt_nro_aut_corr : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Número de Autorización.'
					}
				}
			},
			
			txt_nom_imp_corr : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Nombre de Imprenta.'
					}
				}
			},
			
			txt_ruc_imp_corr : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	console.log(value.length);
		                	var a=value.length;
		                	if (a<11) { 
	            				return { valid: false, message: 'El Ruc debe ser de 11 dígitos.' }
	            			}

		            		return true;
		                }
		            },
		            notEmpty : {
						message : 'Debe Ingresar Ruc de Imprenta.'
					}
				}
			},
			
			txt_fecha_ini_corr : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Fecha de Inicio.'
					}
				}
			},
			
			rb_correlativo : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar si activa o no.'
					}
				}
			}
			
		}
	});
	
	
});

function zeroFill( number, width )
{
  width -= number.toString().length;
  if ( width > 0 )
  {
    return new Array( width + (/\./.test( number ) ? 2 : 1) ).join( '0' ) + number;
  }
  return number + ""; // siempre devuelve tipo cadena
}