<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Registro de Orden de Ingreso</li>
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
	
							<ul id="ul_man_con_calidad" class="nav nav-tabs bordered">
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
									
										<input type="hidden" id="hid_id_ingreso" name="hid_id_ingreso">
										<input type="hidden" id="hid_id_donacion" name="hid_id_donacion">
										<input type="hidden" id="hid_val_fec_trabajo" name="hid_val_fec_trabajo">
										<div class="header-form opc-center">	
											<strong>Orden Ingreso</strong>
										</div>
										
										<div class="form-group"></div>
										
										<div class="form-group">
											<div class="col-sm-3"></div>
											<label class="col-sm-3 control-label label-bold">Nº Orden Ingreso:</label>
											<div class="col-sm-2">
												<input type="text" id="txt_cod_ingreso" class="form-control"  disabled>
												<input type="hidden" id="txt_nro_ingreso" name="txt_nro_ingreso">
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
														<label class="col-sm-1 control-label">Año:</label>
														<div class="col-sm-1 form-group">
															<input type="text" id="txt_anio" class="form-control" disabled>
														</div>

<!-- 														<label class="col-sm-1 control-label">DDI:</label> -->
														<div class="col-sm-2 form-group">
<!-- 															<input type="text" id="txt_ddi" class="form-control" disabled> -->
															<input type="hidden" id="txt_codDdi" name="txt_codDdi">
															<input type="hidden" id="txt_idDdi" name="txt_idDdi">
														</div>
														
														<label class="col-sm-1 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker" readonly>
															</label>
														</div>
														
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																<option value="1">Activo</option>
																<option value="0">Anulado</option>
																<c:forEach items="${lista_estado}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Tipo de Movimiento:</label>
														<div class="col-sm-4">
															<select id="sel_movimiento" name="sel_movimiento" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_movimiento}" var="item">
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
												<h2>Datos de la Donación</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
												
												
													<div class="row" id ="div_tipo_11_12_13">
														<label class="col-sm-3 control-label">Año:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_anio" name="sel_anio" class="form-control">
																<c:forEach items="${lista_anio}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-3 control-label">DDI:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_ddi" name="sel_ddi" class="form-control">
																<c:forEach items="${lista_ddi}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
													</div>
								
													<div class="row">
														<label class="col-sm-3 control-label">Código de la Donación:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_cod_donacion" name="sel_cod_donacion" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_codigo_donacion}" var="item">
																    <option value="${item.idDonacion}_${item.tipoDonante}_${item.nombreDonante}_${item.representante}">${item.codigoDonacion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-3 control-label">Tipo de Donación:</label>
														<div class="col-sm-3 form-group">
															<input type="text" id="txt_tipo_donacion" class="form-control" disabled>
														</div>
													</div>
													
													
													<div class="row">
														<label class="col-sm-3 control-label">Donante:</label>
														<div class="col-sm-3 form-group">
															<input type="text" id="txt_donante" class="form-control" disabled>
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
												<h2>Datos Control de Calidad</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														
														<label class="col-sm-3 control-label">N° Reporte de Control de Calidad:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_control_calidad" name="sel_control_calidad" class="form-control">
																<option value="0">Seleccione</option>
																<c:forEach items="${lista_control_calidad}" var="item">
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
												<h2>Datos de la Procedencia</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">

													    <label class="col-sm-3 control-label">Almacén de Procedencia:</label>
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
																<option value="">Seleccione</option>
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
																<option value="">Seleccione</option>
																<c:forEach items="${lista_chofer}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label"></label>
														<div class="col-sm-3 smart-form form-group">

														</div>
													
														<label class="col-sm-3 control-label">N° de Placa:</label>
														<div class="col-sm-3 form-group">
															<input type="text" name="txt_nro_placa" id="txt_nro_placa" class="form-control upperValue" maxlength="10">
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
															<th>Tipo Documento</th>
															<th>Nro Documento</th>
															<th>Fecha</th>
															<th>Nombre Archivo</th>
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
															<th>Cantidad</th>
															<th>Precio Unitario</th>
															<th>Importe Total</th>
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
															<th>Tipo Documento</th>
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
<div id="div_det_documentos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_documentos">Nuevo Documento</h4>
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
									<select id="sel_tipo_documento" name="sel_tipo_documento" class="form-control">
										<c:forEach items="${lista_tipo_documento}" var="item">
											<option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>								

							<div class="form-group">
								<label class="col-sm-3 control-label">N° Documento:</label>
								<div class="col-sm-8">
									<input type="text" name="txt_nro_documento" id="txt_nro_documento" class="form-control" maxlength="30">
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
								<label class="col-sm-3 control-label">Descripción:</label>
								<div class="col-sm-8">
										<textarea rows="3" name="txt_descripcion_doc" id="txt_descripcion_doc" class="form-control"></textarea>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Subir Archivo:</label>
								<div class="col-sm-8 smart-form">
									<div class="input input-file">
										<span id="sp_sub_archivo" class="button">
											<input type="file" id="txt_sub_archivo" name="txt_sub_archivo">
											Cargar
										</span>
										<input type="text" id="txt_lee_sub_archivo" name="txt_lee_sub_archivo" readonly>
									</div>
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

<!-- Modal -->
<div id="div_det_productos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_productos">Nuevo Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_productos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
							
							
							<div class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-8 form-group">
									<select id="sel_cat_producto" name="sel_cat_producto" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_productos_donacion}" var="item">
										    <option value="${item.idProducto}_${item.unidadMedida}_${item.precio}_${item.fecVencimiento}">${item.nombreProducto}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-3 control-label">Unidad Medida:</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_uni_medida" class="form-control" disabled>
								</div>
								<div id="ver_cantidad">
								<label class="col-sm-3 control-label">Cantidad Propuesta:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_cantidad_propuesta" id="txt_cantidad_propuesta" class="form-control" disabled>
								</div>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-3 control-label">Cantidad:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_cantidad" id="txt_cantidad" class="form-control" maxlength="10">
								</div>
								
								<label class="col-sm-3 control-label">Fecha Vencimiento:</label>
								<div class="col-sm-3 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" name="txt_fec_vencimiento" id="txt_fec_vencimiento" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="row">

								<label class="col-sm-3 control-label">Precio:</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_precio" class="form-control" disabled>
								</div>
								
								<label class="col-sm-3 control-label">Importe Total:</label>
								<div class="col-sm-3 form-group">
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

<!-- inline scripts related to this page -->
<script> var donaciones = JSON.parse('${donaciones}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/mantenimiento_donaciones_ingreso.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/validacion_mantenimiento_donaciones_ingreso.js"></script>