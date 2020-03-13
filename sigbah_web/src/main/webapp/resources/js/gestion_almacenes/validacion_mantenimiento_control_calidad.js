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
			
			sel_tip_control : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Control.'
					}
				}
			},
			sel_nro_ord_compra : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Nº orden Compra.'
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
			
			// Datos del Origen / Destino - Reponsables
			sel_ori_almacen : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Almacén.'
					}
				}
			},
			sel_ori_en_almacen : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Encargado de Almacén.'
					}
				}
			},
			sel_inspector : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Inspector.'
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
			
			// Datos del Transporte
			sel_emp_transporte : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Empresa de Transporte.'
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
			
			// Productos
			rb_tip_bien : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Tipo de Bien.'
					}
				}
			}
			
		}
	});
	
	frm_det_alimentarios.bootstrapValidator({
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
			txt_fec_vencimiento : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
                				return { valid: false, message: 'Debe ingresar Fecha Vencimiento.' }
                			}
		                	var fechaRegistro = $('#txt_fecha').val();
	                		if (comparafecha(value, fechaRegistro) != 1) {
	                		    return { valid: false, message: 'La fecha de vencimiento debe ser mayor a la fecha de ingreso.' }
	                		}
		            		return true;
		                }
		            }
				}
			},
			txt_can_lote : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Cantidad de Lote.' }
	            			}
		                	var cantidadMuestra = $('#txt_can_muestra').val();
	                		if (!esnulo(cantidadMuestra)) {
	                			frm_det_alimentarios.bootstrapValidator('revalidateField', 'txt_can_muestra');
	                		}
		            		return true;
		                }
		            }
				}
			},
			txt_can_muestra : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Cantidad de Muestra.' }
	            			}
		                	var cantidadLote = $('#txt_can_lote').val();
	                		if (!esnulo(cantidadLote)) {
	                			var cantidadMuestra = parseFloat(formatMonto(value));
	                			cantidadLote = parseFloat(formatMonto(cantidadLote));
	                			if (cantidadMuestra > cantidadLote) {
	                				return { valid: false, message: 'La cantidad de la muestra siempre debe ser menor o igual a la cantidad del lote.' }
	                			}
	                		}
		            		return true;
		                }
		            }
				}
			},
			sel_primario : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Primario.'
					}
				}
			},
			sel_olor : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Olor.'
					}
				}
			},
			sel_textura : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Textura.'
					}
				}
			},
			sel_secundario : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Secundario.'
					}
				}
			},
			sel_color : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Color.'
					}
				}
			},
			sel_sabor : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Sabor.'
					}
				}
			}
		}
	});
	
	frm_det_no_alimentarios.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_no_cat_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Categoria de Producto.'
					}
				}
			},
			sel_no_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
					}
				}
			},
			txt_no_fec_vencimiento : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	var fechaRegistro = $('#txt_fecha').val();
	                		if (!esnulo(value)) {
	                		    if (comparafecha(value, fechaRegistro) != 1) {
	                		    	return { valid: false, message: 'La fecha de vencimiento debe ser mayor a la fecha de ingreso.' }	                		    	
	                		    }
	                		}
		            		return true;
		                }
		            }
				}
			},
			txt_no_can_lote : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Cantidad de Lote.' }
	            			}
		                	var cantidadMuestra = $('#txt_no_can_muestra').val();
	                		if (!esnulo(cantidadMuestra)) {
	                			frm_det_no_alimentarios.bootstrapValidator('revalidateField', 'txt_no_can_muestra');
	                		}
		            		return true;
		                }
		            }
				}
			},
			txt_no_can_muestra : {
				validators : {
					callback: {
		                callback: function(value, validator, field) {
		                	if (esnulo(value)) {
	            				return { valid: false, message: 'Debe ingresar Cantidad de Muestra.' }
	            			}
		                	var cantidadLote = $('#txt_no_can_lote').val();
	                		if (!esnulo(cantidadLote)) {
	                			var cantidadMuestra = parseFloat(formatMonto(value));
	                			cantidadLote = parseFloat(formatMonto(cantidadLote));
	                			if (cantidadMuestra > cantidadLote) {
	                				return { valid: false, message: 'La cantidad de la muestra siempre debe ser menor o igual a la cantidad del lote.' }
	                			}
	                		}
		            		return true;
		                }
		            }
				}
			},
			sel_no_primario : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Primario.'
					}
				}
			},
			sel_no_tecnicas : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Técnicas.'
					}
				}
			},
			sel_no_secundario : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Secundario.'
					}
				}
			},
			sel_no_conformidad : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Conformidad.'
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
