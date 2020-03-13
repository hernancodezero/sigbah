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
			
			// Datos Orden de Compra
			sel_nro_ord_compra : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Nº orden Compra.'
					}
				}
			},
			sel_com_por : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Compra Por.'
					}
				}
			},
			
			// Datos Control de Calidad
			rb_tie_nro_rep_con_calidad : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tiene N° Rep. Control Calidad.'
					}
				}
			},
			sel_nro_con_calidad : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar N° Control de Calidad.'
					}
				}
			},
			
			// Datos de Proveedor
			sel_proveedor : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Proveedor.'
					}
				}
			},
			
			// Datos de la Procedencia
			sel_almacen : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Almacen.'
					}
				}
			},
			
			sel_salida : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Orden Salida.'
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
			txt_fec_llegada : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Fecha de Llegada.'
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
						message : 'Debe ingresar N° de Placa.'
					}
				}
			},
			
			// Responsable de Recepción
			sel_responsable : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Responsable.'
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
						message : 'Debe seleccionar Lote.'
					}
				}
			},
			txt_fec_vencimiento : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	var categoriaProducto = $('#sel_cat_producto').val();
		                	var catProducto = $('#sel_producto').val();
		                	var arr = catProducto.split('_');
		                	if ( (esnulo(value) && categoriaProducto == '5') || (esnulo(value) && arr[5] == '5')) { // Alimentos
	            				return { valid: false, message: 'Debe ingresar Fecha Vencimiento.' }
	            			}
//		                	var fechaRegistro = $('#txt_fecha').val();
//	                		if (comparafecha(value, fechaRegistro) != 1) {
//	                		    return { valid: false, message: 'La fecha de vencimiento debe ser mayor a la fecha de ingreso.' }
//	                		}
		            		return true;
		                }
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
