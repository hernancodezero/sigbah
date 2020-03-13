<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gestión de Almacenes</li>
		<li>Salidas</li>
		<li>Proyecto de Manifiesto</li>
		<li>Registro</li>
	</ol>
	<!-- end breadcrumb -->
	<div class="opc-right">
		<ol class="breadcrumb">
			<li id="mes_activo">Mes y año de trabajo activo: ${mes_anio}</li>
		</ol>	
	</div>
</div>
<!-- END RIBBON -->
    
<!-- MAIN CONTENT -->
<div id="content">
	
	<!-- widget grid -->
	<section id="widget-grid" class="">
	
		<!-- row -->
		<div class="row">
		
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
			
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget">

					<!-- widget div-->
					<div>
	
						<!-- widget content -->
						<div class="widget-body">
	
							<ul id="ul_man_pro_manifiesto" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Datos Generales</a>
								</li>
								<li id="li_productos">
									<a href="#div_productos" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Productos</a>
								</li>
								<li id="li_documentos">
									<a href="#div_documentos" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Documentos</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_proyecto" name="hid_cod_proyecto">
										<input type="hidden" id="hid_val_fec_trabajo" name="hid_val_fec_trabajo">
									
										<div class="header-form opc-center">	
											<strong>Proyecto Manifiesto</strong>
										</div>
										
										<div class="form-group"></div>
										
										<div class="form-group">
											<div class="col-sm-3"></div>
											<label class="col-sm-3 control-label label-bold">Nº Proyecto Manifiesto:</label>
											<div class="col-sm-2">
												<input type="text" id="txt_nro_pro_manifiesto" class="form-control" disabled>
											</div>
										</div>												
																
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Datos Generales</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Año:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_anio" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Almacén:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_almacen" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker" readonly>
															</label>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																<c:forEach items="${lista_estado}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-2 control-label">Tipo Movimiento:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_tip_movimiento" name="sel_tip_movimiento" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_tipo_movimiento}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
														
													<div class="row">
														
														<label class="col-sm-4 control-label">El manifiesto tiene programación:</label>
														
														<div class="col-sm-3 form-group">
															<label class="radio radio-inline">
																<input type="radio" name="rb_man_tie_programacion" value="1">
																Si
															</label>
															
															<label class="radio radio-inline">
																<input type="radio" name="rb_man_tie_programacion" value="0">
																No
															</label>																		
														</div>																									
													
														<label class="col-sm-2 control-label">N° Programacion:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_nro_programacion" name="sel_nro_programacion" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_programacion}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
												</div>
												<!-- end widget content -->
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->	
													
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Datos del Destino</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Almacén:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_almacen" name="sel_almacen" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_almacen}" var="item">
																	<option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
												</div>
												<!-- end widget content -->
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
										
										<div class="jarviswidget">
										
											<header>
											</header>
										
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
												
													<div class="row">
														<div class="col-sm-12 smart-form">
															<section>														
																<label class="control-label">Observaciones:</label>
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_observaciones" id="txt_observaciones" 
																		maxlength="500" class="custom-scroll"></textarea> 
																</label>
															</section>
														</div>
													</div>	

												</div>
												<!-- end widget content -->
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
																								
										<div class="form-actions">
											<div class="row">
												<div class="col-md-12 opc-center">
													<button class="btn btn-primary" type="button" id="btn_grabar">
														<i class="fa fa-floppy-o"></i>
														Grabar
													</button>
													
													&nbsp; &nbsp;
													
													<button class="btn btn-default btn_retornar" type="button">
														<i class="fa fa-mail-forward"></i>
														Retornar
													</button>
												</div>
											</div>
										</div>				
												
									</form>
										
								</div>
								
								<div class="tab-pane fade" id="div_productos">
								
									<form id="frm_det_pro_vehicular" class="form-horizontal">
									
										<!-- Widget ID (each widget will need unique ID)-->
										<div class="jarviswidget jarviswidget-color-blueLight">
										
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i> </span>
												<h2>Detalle de Productos</h2>
												
												<div class="jarviswidget-ctrls" role="menu">   
													<a href="#" id="href_pro_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
														data-original-title="Nuevo">
														<i class="fa fa-file-o"></i>
													</a>
													<a href="#" id="href_pro_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
														data-original-title="Editar">
														<i class="fa fa-edit"></i>
													</a>
													<a href="#" id="href_pro_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
														data-original-title="Eliminar">
														<i class="fa fa-trash-o"></i>
													</a>
												</div>
											</header>
							
											<!-- widget div-->
											<div>
																
												<!-- widget content -->
												<div class="widget-body">
	
													<table id="tbl_det_productos" class="table table-bordered table-hover tbl-responsive">
														<thead>			                
															<tr>
																<th></th>
																<th>Nº</th>
																<th>Producto</th>
																<th>Unidad de Medida</th>
																<th id="th_cantidad">Cantidad</th>
																<th id="th_pre_unitario">Peso Unitario</th>
																<th id="th_pes_total">Peso Total Kg</th>
																<th id="th_vol_unitario">Volumen Unitario</th>
																<th id="th_vol_total">Volumen Total m3</th>
																<th id="th_cos_unitario">Costo Unitario (S/.)</th>
																<th id="th_cos_total">Costo Total (S/.)</th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<td colspan="6">
																	<span class="label-bold" style="float:right;">Totales:</span>
																</td>
																<td colspan="1">
																	<span id="sp_tot_peso"></span>
																</td>															
																<td colspan="1"></td>															
																<td colspan="1">
																	<span id="sp_tot_volumen"></span>
																</td>
																<td colspan="2"></td>
															</tr>
														</tfoot>
													</table>
	
												</div>
												<!-- end widget content -->
							
											</div>
											<!-- end widget div -->
							
										</div>
										<!-- end widget -->
										
										<!-- Widget ID (each widget will need unique ID)-->
										<div class="jarviswidget jarviswidget-color-blueLight">
										
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i> </span>
												<h2>Resumen de Vehículos a Usar</h2>
												<div class="jarviswidget-ctrls" role="menu">   
													<a href="#" id="href_exp_excel_vehiculo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
														data-original-title="Exportar Excel">
														<i class="fa fa-file-excel-o"></i>
													</a> 
												</div>
											</header>
							
											<!-- widget div-->
											<div>
																
												<!-- widget content -->
												<div class="widget-body">
												
													<div class="row">														
														<label class="col-sm-3 control-label">Calcular en N° de Vehiculos por:</label>
														
														<div class="col-sm-3 form-group">
															<label class="radio radio-inline">
																<input type="radio" name="rb_cal_nro_vehiculo" value="T">
																Toneladas
															</label>
															
															<label class="radio radio-inline">
																<input type="radio" name="rb_cal_nro_vehiculo" value="V">
																Volumen
															</label>																		
														</div>
													</div>
												
													<div class="row">
														<label class="col-sm-12 label-bold">Tipo de Camiones Disponibles:</label>
													</div>
	
													<table id="tbl_det_vehiculos" class="table table-bordered table-hover tbl-responsive">
														<thead>			                
															<tr>
																<th>Nº</th>
																<th>Activo</th>															
																<th>Tipo Camión</th>
																<th>Volumen</th>
																<th>N° Vehículos Requeridos</th>
															</tr>
														</thead>
													</table>														
	
												</div>
												<!-- end widget content -->
							
											</div>
											<!-- end widget div -->
							
										</div>
										<!-- end widget -->
										
										<div class="form-actions">
											<div class="row">
												<div class="col-md-12 opc-center">
													<button class="btn btn-primary" type="button" id="btn_recalcular">
														<i class="fa fa-retweet"></i>
														Recalcular
													</button>
												
													&nbsp; &nbsp;
												
													<button class="btn btn-default btn_retornar" type="button">
														<i class="fa fa-mail-forward"></i>
														Retornar
													</button>
												</div>
											</div>
										</div>
										
									</form>
									
								</div>
								
								<div class="tab-pane fade" id="div_documentos">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Relación de Documentos</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_doc_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Nuevo">
													<i class="fa fa-file-o"></i>
												</a>
												<a href="#" id="href_doc_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
												<a href="#" id="href_doc_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">
											
												<table id="tbl_det_documentos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th id="th_tip_documento">Tipo Documento</th>
															<th>Nro Documento</th>
															<th>Fecha</th>
														</tr>
													</thead>
												</table>

											</div>
											<!-- end widget content -->
						
										</div>
										<!-- end widget div -->
						
									</div>
									<!-- end widget -->
									
									<div class="form-actions">
										<div class="row">
											<div class="col-md-12 opc-center">
												<button class="btn btn-default btn_retornar" type="button">
													<i class="fa fa-mail-forward"></i>
													Retornar
												</button>
											</div>
										</div>
									</div>
									
								</div>
							</div>
	
						</div>
						<!-- end widget content -->
	
					</div>
					<!-- end widget div -->
	
				</div>
				<!-- end widget -->
			
			</article>	
	
		</div>	
		<!-- end row -->
	
	</section>
	<!-- end widget grid -->
	
</div>
<!-- END MAIN CONTENT -->

<!-- Modal -->
<div id="div_det_productos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_productos">Nuevo Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_productos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
							
							<div class="row">																				
								<label class="col-sm-3 control-label">Categoría de Producto:</label>
								<div class="col-sm-3 form-group">
									<select id="sel_cat_producto" name="sel_cat_producto" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_categoria}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
							<div id="div_pro_det_productos" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-6 form-group">
									<select id="sel_producto" name="sel_producto" class="form-control">
									</select>
									<small id="sma_val_producto" class="control-label label-error">Debe seleccionar Producto.</small>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-2 control-label">Unidad Medida:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_uni_medida" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Cantidad:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_cantidad" id="txt_cantidad" class="form-control monto-format" maxlength="10">
								</div>
								
<!-- 								<label class="col-sm-2 control-label">Importe Total:</label> -->
<!-- 								<div class="col-sm-2 form-group"> -->
<!-- 									<input type="text" name="txt_imp_total" id="txt_imp_total" class="form-control" disabled> -->
<!-- 								</div> -->
							</div>
							
							<div class="row">
								<label class="col-sm-2 control-label">Peso Neto Unitario:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pes_net_unitario" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Volumen Unitario:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_vol_unitario" class="form-control" disabled>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_producto">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_producto">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_det_documentos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_documentos">Nuevo Documento</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_documentos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_documento" name="hid_cod_documento">
							<input type="hidden" id="hid_cod_act_alfresco" name="hid_cod_act_alfresco">
							<input type="hidden" id="hid_cod_ind_alfresco" name="hid_cod_ind_alfresco">
						
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Tipo Documento:</label>
								<div class="col-sm-8">
									<select id="sel_tip_producto" name="sel_tip_producto" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_tipo_documento}" var="item">
											<option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>								

							<div class="form-group">
								<label class="col-sm-3 control-label">N° Documento:</label>
								<div class="col-sm-4">
									<input type="text" name="txt_nro_documento" id="txt_nro_documento" class="form-control upperValue" maxlength="18">
								</div>								
							</div>										

							<div class="form-group">
								<label class="col-sm-3 control-label">Fecha:</label>
								<div class="col-sm-4 smart-form">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" name="txt_doc_fecha" id="txt_doc_fecha" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Subir Archivo:</label>
								<div class="col-sm-7 smart-form">
									<div class="input input-file">
										<span id="sp_sub_archivo" class="button">
											<input type="file" id="fil_sub_archivo" name="fil_sub_archivo">
											Cargar
										</span>
										<input type="text" id="txt_lee_sub_archivo" name="txt_lee_sub_archivo" readonly>
									</div>
								</div>
								<div class="col-sm-2">
									<a href="#" id="href_eli_archivo" class="btn btn-default txt-color-red" rel="tooltip" 
										data-placement="right" data-original-title="Eliminar Archivo" data-html="true">
										<i class="fa fa-eraser fa-lg"></i>
									</a>
								</div>							
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_documento">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_documento">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var proyectoManifiesto = JSON.parse('${proyectoManifiesto}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/mantenimiento_proyecto_manifiesto.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/validacion_mantenimiento_proyecto_manifiesto.js"></script>
