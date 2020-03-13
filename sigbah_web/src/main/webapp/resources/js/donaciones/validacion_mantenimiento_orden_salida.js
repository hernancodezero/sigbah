$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			txt_fecha : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha.'
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
			sel_tip_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo Movimiento.'
					}
				}
			},
			sel_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo Movimiento.'
					}
				}
			},
			sel_nro_pro_manifiesto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar N° Proyecto de Manifiesto.'
					}
				}
			},
			
			// Solicitud de Salida
			sel_solicitada : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Solicitada por.'
					}
				}
			},
			sel_responsable : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Responsable.'
					}
				}
			},
			
			// Datos del Destino
			sel_ddi : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar DDI.'
					}
				}
			},
			rb_tie_ate_gobierno : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Atención.'
					}
				}
			},
			
			sel_gore : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar GORE.'
					}
				}
			},
			sel_departamento : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Departamento.'
					}
				}
			},
			sel_provincia : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Provincia.'
					}
				}
			},
			sel_distrito : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Distrito.'
					}
				}
			},
			sel_alm_destino : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Almacen Destino.'
					}
				}
			},
			sel_res_recepcion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Responsable Recepción.'
					}
				}
			},
			
			// Datos del Transporte
			sel_med_transporte : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Medio de Transporte.'
					}
				}
			},
			sel_emp_transporte : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Empresa de Transporte.'
					}
				}
			},
			txt_nro_placa : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar N° de Placa.'
					}
				}
			},
			txt_fec_entrega : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha de Entrega.'
					}
				}
			},
//			sel_chofer : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Chofer.'
//					}
//				}
//			}
			
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
			txt_can_lote : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Cantidad de Lote.'
					}
				}
			},
			txt_cantidad : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Cantidad.'
					}
				}
			},
			txt_pre_unitario : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Precio Unitario.'
					}
				}
			}
		}
	});
	
	frm_det_documentos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_tip_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo Documento.'
					}
				}
			},
			txt_nro_documento : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar N° Documento.'
					}
				}
			},
			txt_doc_fecha : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha.'
					}
				}
			},
			txt_lee_sub_archivo : {
				validators : {
					notEmpty : {
						message : 'Debe cargar el Archivo.'
					}
				}
			}
		}
	});
	
});
