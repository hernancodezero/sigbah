<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gestión de Almacenes</li>
		<li>Ingresos</li>
		<li>NEA</li>
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
	
							<ul id="ul_man_ord_ingreso" class="nav nav-tabs bordered">
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
									
										<input type="hidden" id="hid_cod_ord_ingreso" name="hid_cod_ord_ingreso">
										<input type="hidden" id="hid_val_fec_trabajo" name="hid_val_fec_trabajo">
									
										<div class="header-form opc-center">	
											<strong>Orden Ingreso</strong>
										</div>
										
										<div class="form-group"></div>
										
										<div class="form-group">
											<div class="col-sm-3"></div>
											<label class="col-sm-3 control-label label-bold">Nº Orden Ingreso:</label>
											<div class="col-sm-2">
												<input type="text" id="txt_nro_ord_ingreso" class="form-control" disabled>
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

														<label class="col-sm-2 control-label">DDI:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_ddi" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Almacén:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_almacen" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker" readonly>
															</label>
														</div>
														
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																<c:forEach items="${lista_estado}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-2 control-label">Tipo Movimiento:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_tip_movimiento" name="sel_tip_movimiento" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_tipo_movimiento}" var="item">
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
												<h2>Datos Orden de Compra</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">		
													
													<div class="row">
														<label class="col-sm-2 control-label">Nº orden Compra:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_nro_ord_compra" name="sel_nro_ord_compra" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_orden_compra}" var="item">
																    <option value="${item.nroOrdenCompra}_${item.concepto}_${item.idProveedor}_${item.razonSocial}_${item.responsable}">${item.nroOrdenCompra}</option>
																</c:forEach>
															</select>
															<small id="sma_val_nro_ord_compra" class="control-label label-error">Debe seleccionar Nº orden Compra.</small>
														</div>
													
														<div class="col-sm-7 smart-form">
															<section>														
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="2" id="txt_det_ord_compra" class="form-control custom-scroll mod-readonly" disabled></textarea> 
																</label>
															</section>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Compra Por:</label>
														<div class="col-sm-3 smart-form form-group">
															<select id="sel_com_por" name="sel_com_por" class="form-control">
																<option value="">Seleccione</option>
																<option value="1">Emergencia</option>
																<option value="2">Reabastecimiento</option>
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
												<h2>Datos Control de Calidad</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Tiene N° Rep. Control Calidad:</label>
														
														<div class="col-sm-1"></div>
														
														<div class="col-sm-3 form-group">
															<label class="radio radio-inline">
																<input type="radio" name="rb_tie_nro_rep_con_calidad" value="1">
																Si
															</label>
															
															<label class="radio radio-inline">
																<input type="radio" name="rb_tie_nro_rep_con_calidad" value="0">
																No
															</label>																		
														</div>
														
														<label class="col-sm-2 control-label">N° Control de Calidad:</label>
														<div class="col-sm-3 smart-form form-group">
															<select id="sel_nro_con_calidad" name="sel_nro_con_calidad" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_nro_control_calidad}" var="item">
																    <option value="${item.idControlCalidad}">${item.nroControlCalidad}</option>
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
												<h2>Datos de Proveedor</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body widget-body-50">
								
													<div class="row">
<!-- 														<label class="col-sm-3 control-label">Proveedor:</label> -->
<!-- 														<div class="col-sm-3 form-group"> -->
<!-- 															<select id="sel_proveedor" name="sel_proveedor" class="form-control"> -->
<!-- 																<option value="">Seleccione</option> -->
<%-- 																<c:forEach items="${lista_proveedor}" var="item"> --%>
<%-- 																	<option value="${item.vcodigo}_${item.descripcionCorta}">${item.descripcion}</option> --%>
<%-- 																</c:forEach> --%>
<!-- 															</select> -->
<!-- 														</div> -->
														<input type="hidden" id="hid_id_proveedor" name="hid_id_proveedor">
														<label class="col-sm-3 control-label">Proveedor:</label>
														<div class="col-sm-3 form-group">
															<input type="text" id="sel_proveedor" class="form-control" disabled>
														</div>
														
														<label class="col-sm-3 control-label">Representante:</label>
														<div class="col-sm-3 form-group">
															<input type="text" id="txt_representante" class="form-control" disabled>
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
												<h2>Datos de la Procedencia</h2>
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
														
														<label class="col-sm-3 control-label">N° Orden Salida:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_salida" name="sel_salida" class="form-control">
																
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
												<h2>Datos del Transporte</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Medio de Transporte:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_med_transporte" name="sel_med_transporte" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_medio_transporte}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													
														<label class="col-sm-3 control-label">Empresa de Transporte:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_emp_transporte" name="sel_emp_transporte" class="form-control">
																<c:forEach items="${lista_empresa_transporte}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label">Fecha de Llegada:</label>
														<div class="col-sm-3 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fec_llegada" id="txt_fec_llegada" class="datepicker" readonly>
															</label>
														</div>
														
														<label class="col-sm-3 control-label">Chofer:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_chofer" name="sel_chofer" class="form-control">
																<c:forEach items="${lista_chofer}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<div class="col-sm-6 form-group"></div>
													
														<label class="col-sm-3 control-label">N° de Placa:</label>
														<div class="col-sm-3 form-group">
															<input type="text" name="txt_nro_placa" id="txt_nro_placa" class="form-control upperValue" maxlength="20">
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
												<h2>Responsable de Recepción</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Responsable:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_responsable" name="sel_responsable" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_personal}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
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
															<th id="th_lote">Lote</th>
															<th id="th_cantidad">Cantidad</th>
															<th id="th_pre_unitario">Precio Unitario</th>
															<th id="th_val_total">Valor Total</th>
															<th>Fecha Vencimiento</th>
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
							
							<div class="row" id="div_det_nro_ord_compra">																				
								<label class="col-sm-3 control-label">N° Orden Compra:</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_det_nro_ord_compra" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">																				
								<label class="col-sm-3 control-label">Categoría de Producto:</label>
								<div class="col-sm-3 form-group">
									<select id="sel_cat_producto" name="sel_cat_producto" class="form-control">
										<option value="0">Todos</option>
										<c:forEach items="${lista_categoria}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
							<div id="div_pro_det_productos" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-5 form-group">
									<select id="sel_producto" name="sel_producto" class="form-control">
									</select>
									<small id="sma_val_producto" class="control-label label-error">Debe seleccionar Producto.</small>
								</div>
								
								<label class="col-sm-2 control-label">Lote:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_lote" name="sel_lote" class="form-control">
									</select>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-2 control-label">Unidad Medida:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_uni_medida" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Envase:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_envase" class="form-control" disabled>
								</div>

								<label class="col-sm-2 control-label">Fecha Vencimiento:</label>
								<div class="col-sm-2 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" name="txt_fec_vencimiento" id="txt_fec_vencimiento" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-2 control-label">Cantidad:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_cantidad" id="txt_cantidad" class="form-control monto-format" maxlength="10">
								</div>

								<label class="col-sm-2 control-label">Precio Unitario (S/.):</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_pre_unitario" id="txt_pre_unitario" class="form-control" maxlength="10">
								</div>
								
								<label class="col-sm-2 control-label">Importe Total (S/.):</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_imp_total" id="txt_imp_total" class="form-control" disabled>
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
								<div class="col-sm-8">
									<input type="text" name="txt_nro_documento" id="txt_nro_documento" class="form-control upperValue" maxlength="30">
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
<script> var ordenIngreso = JSON.parse('${ordenIngreso}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/mask/jquery.maskedinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/mantenimiento_orden_ingreso.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/validacion_mantenimiento_orden_ingreso.js"></script>
