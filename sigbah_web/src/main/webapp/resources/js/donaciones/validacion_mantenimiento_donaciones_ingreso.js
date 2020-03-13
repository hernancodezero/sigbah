$(document).ready(function() {

	frm_dat_generales.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			// Datos Generales
			txt_fecha : {
				validators : {
					notEmpty : {
						message : 'Debe Ingresar Fecha.'
					}
				}
			},
			
			sel_movimiento : {
				validators : {
					notEmpty : {
						message : 'Debe Seleccionar Tipo de Movimiento.'
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
			sel_cod_donacion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Donación.'
					}
				}
			},
			sel_control_calidad : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Control.'
					}
				}
			},
			
		
			sel_almacen : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Almacén.'
					}
				}
			},
			
			sel_salida : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Orden de Salida.'
					}
				}
			},
			
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
			
			
			txt_fec_llegada : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha Llegada.'
					}
				}
			},
			
			
			sel_chofer : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Chofer.'
					}
				}
			},

			txt_nro_placa : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar N° de Placa.'
					}
				}
			},
			
			sel_responsable : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Responsable de Recepción.'
					}
				}
			}
			
		}
	});
	
	frm_det_productos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_cat_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
					}
				}
			},

			txt_cantidad : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Cantidad.' }
	            			}
		                	var cantidadStock = $('#txt_cantidad_propuesta').val();
	                		if (!esnulo(cantidadStock)) {
	                			cantidadStock = parseFloat(formatMonto(cantidadStock));
	                			var cantidad = parseFloat(formatMonto(value));
	                			if (cantidad > cantidadStock) {	                			
	                				return { valid: false, message: 'La cantidad no puede ser mayor a la cantidad propuesta.' }
	                			}
	                		}
		            		return true;
		                }
		            }
				}
			}
//			txt_fec_vencimiento : {
//				validators : {
//					notEmpty : {
//						message : 'Debe ingresar Fecha.'
//					}
//				}
//			}
		}
	});
//	
	frm_det_documentos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_tipo_documento : {
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
