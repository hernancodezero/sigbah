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
			sel_ori_donacion : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Procedencia de Donación.'
					}
				}
			},
			sel_dee : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar El N° Dee.'
					}
				}
			},
			

			sel_ori_pais : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar País de Procedencia.'
					}
				}
			},
			sel_donante : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Donante.'
					}
				}
			},
			sel_oficina : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Oficina.'
					}
				}
			},
			
			// Datos de Proveedor
			sel_personal_oficina : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Personal Oficina.'
					}
				}
			},
			
			txt_finalidad : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Finalidad.'
					}
				}
			}
			
		}
	});
	
//	frm_det_alimentarios.bootstrapValidator({
//		framework : 'bootstrap',
//		excluded : [':disabled', ':hidden'],
//		fields : {
//			sel_producto : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Producto.'
//					}
//				}
//			},
//			txt_fec_vencimiento : {
//				validators : {
//					notEmpty : {
//						message : 'Debe ingresar Fecha Vencimiento.'
//					}
//				}
//			},
//			txt_can_lote : {
//				validators : {
//					notEmpty : {
//						message : 'Debe ingresar Cantidad de Lote.'
//					}
//				}
//			},
//			txt_can_muestra : {
//				validators : {
//					notEmpty : {
//						message : 'Debe ingresar Cantidad de Muestra.'
//					}
//				}
//			},
//			sel_primario : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Primario.'
//					}
//				}
//			},
//			sel_olor : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Olor.'
//					}
//				}
//			},
//			sel_textura : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Textura.'
//					}
//				}
//			},
//			sel_secundario : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Secundario.'
//					}
//				}
//			},
//			sel_color : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Color.'
//					}
//				}
//			},
//			sel_sabor : {
//				validators : {
//					notEmpty : {
//						message : 'Debe seleccionar Sabor.'
//					}
//				}
//			}
//		}
//	});
	
	frm_det_productos.bootstrapValidator({
		framework : 'bootstrap',
		excluded : [':disabled', ':hidden'],
		fields : {
			sel_cat_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Categoria de Producto.'
					}
				}
			},
			sel_lis_producto : {
				validators : {
					notEmpty : {
						message : 'Debe seleccionar Producto.'
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
			sel_monedas : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Tipo de Moneda.'
					}
				}
			},
			txt_imp_origen : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Importe Origen.'
					}
				}
			},
			txt_imp_soles : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Importe en Soles.'
					}
				}
			},
			txt_imp_dolares : {
				validators : {
					notEmpty : {
						message : 'Debe ingresar Importe en Dolares.'
					}
				}
			}
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
