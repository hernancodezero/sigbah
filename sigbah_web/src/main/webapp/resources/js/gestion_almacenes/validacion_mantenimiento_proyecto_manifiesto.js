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
						message : 'Debe seleccionar El manifiesto tiene programación.'
					}
				}
			},
			sel_nro_programacion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar N° Programacion.'
					}
				}
			},
			
			// Datos del Destino
			sel_almacen : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Almacen.'
					}
				}
			}
			
		}
	});
	
	frm_det_pro_vehicular.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			rb_cal_nro_vehiculo : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Calcular en Nro de Vehiculos.'
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
	                				return { valid: false, message: 'La cantidad de Salida debe ser menor al Stock de Almacén.' }
	                			}
	                		}
		            		return true;
		                }
		            }
				}
			},
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
			}
//			txt_lee_sub_archivo : {
//				validators : {
//					notEmpty : {
//						message : 'Debe cargar el Archivo.'
//					}
//				}
//			}
		}
	});
	
});
