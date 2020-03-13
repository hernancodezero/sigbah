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
			
			rb_man_tie_programacion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar si tiene Proyecto Manifiesto.'
					}
				}
			},
//			sel_nro_pro_manifiesto : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar N° Proyecto de Manifiesto.'
//					}
//				}
//			},
			
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
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Fecha de Entrega.' }
	            			}
		                	var fechaRegistro = $('#txt_fecha').val();
	                		if (comparafecha(value, fechaRegistro) == 1 || comparafecha(value, fechaRegistro) == 0) {
	                		    return true;
	                		}
	                		return { valid: false, message: 'La fecha de entrega debe ser mayor o igual a la fecha de emisión.' }
		                }
		            }
				}
			},
			sel_chofer : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Chofer.'
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
			sel_lote : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Cantidad de Lote.'
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
		                	var cantidadStock = $('#txt_can_stock').val();
	                		if (!esnulo(cantidadStock)) {
	                			cantidadStock = parseFloat(formatMonto(cantidadStock));
	                			var cantidad = parseFloat(formatMonto(value));
	                			if (cantidad > cantidadStock) {	                			
	                				return { valid: false, message: 'La cantidad no puede ser mayor a la cantidad stock.' }
	                			}
	                		}
		            		return true;
		                }
		            }
				}
			},
			txt_pre_unitario : {
				validators : {
					notEmpty : {
						message : 'El campo Precio Unitario es obligatorio.'
					}
				}
			}
		}
	});
	
//	frm_pro_can_manifiesto.bootstrapValidator({
//		framework : 'bootstrap',
//		excluded : [':disabled', ':hidden'],
//		fields : {
//			txt_can_salida : {
//				validators : {
//					callback: {
//		                callback: function(value, validator, field) {
//		                	if (esnulo(value)) {
//	            				return { valid: false, message: 'Debe ingresar Cantidad Salida.' }
//	            			}
//		                	var cantidadStock = $('#txt_can_stock').val();
//	                		if (!esnulo(cantidadStock)) {
//	                			cantidadStock = parseFloat(formatMonto(cantidadStock));
//	                			var cantidad = parseFloat(formatMonto(value));
//	                			if (cantidad > cantidadStock) {	                			
//	                				return { valid: false, message: 'La cantidad no puede ser mayor a la cantidad stock.' }
//	                			}
//	                		}
//		            		return true;
//		                }
//		            }
//				}
//			}
//		}
//	});
	
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
			}
		}
	});
	
});
