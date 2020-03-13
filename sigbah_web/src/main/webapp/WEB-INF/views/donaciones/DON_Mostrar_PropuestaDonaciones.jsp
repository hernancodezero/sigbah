<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Registro de Donaciones</li>
	</ol>
	<!-- end breadcrumb -->
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
								
								<li id="li_documentos">
									<a href="#div_documentos" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Documentos</a>
								</li>
								
								<li id="li_productos">
									<a href="#div_productos" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Productos</a>
								</li>
								
								<li id="li_estados">
									<a href="#div_estados" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Estados</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_id_donacion" name="hid_id_donacion">								
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
														<label class="col-sm-2 control-label">Código de Donacion:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_cod_donacion" class="form-control"  disabled>
															<input type="hidden" id="txt_cod_id" name="txt_cod_id">
														</div>
														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker" readonly>
															</label>
														</div>
														
														<label class="col-sm-2 control-label">Estado de la Donación:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control" disabled>
																<c:forEach items="${lista_estado}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Año:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_anio" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">DDI:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_ddi" class="form-control" disabled>
															<input type="hidden" id="txt_codDdi" name="txt_codDdi">
															<input type="hidden" id="txt_idDdi" name="txt_idDdi">
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Tipo de Donación:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_tip_donacion" name="sel_tip_donacion" class="form-control">
																<option value="1">Bienes de Ayuda Humanitaria</option>
																<option value="2">Proyectos</option>
																<option value="3">Fortalecimiento Institucional</option>
															</select>
														</div>
													</div>	
													
													<div class="row">
														<label class="col-sm-2 control-label">Nº DEE:</label>
														<div class="col-sm-3 smart-form form-group">
															<select id="sel_dee" name="sel_dee" class="select2 form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_dee}" var="item">
																    <option value="${item.icodigo}_${item.descripcion}">${item.descripcionCorta}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-sm-1 form-group"></div> 
														<div class="col-sm-6 form-group">
															<textarea rows="3" name="txt_dee" id="txt_dee" class="form-control" disabled></textarea>
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
												<h2>Datos del Donante</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Procedencia Donación:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_ori_donacion" name="sel_ori_donacion" class="form-control">
																<option value="">Seleccione</option>
																<option value="1">Nacional</option>
																<option value="2">Internacional</option> 
															</select>
														</div>
														
														<label class="col-sm-3 control-label">Pais Procedencia:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_ori_pais" name="sel_ori_pais" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_proce_pais}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label">Tipo de Persona:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_tip_persona" name="sel_tip_persona" class="form-control">
																	<option value="1">Persona Natural</option>
																	<option value="2">Persona Juridica</option> 
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label">Donante:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_donante" name="sel_donante" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_donadores}" var="item">
																    <option value="${item.icodigo}_${item.descripcion}">${item.descripcionCorta}</option>
																</c:forEach>
															</select>
														</div>
														<label class="col-sm-2 control-label">Representante:</label>
														<div class="col-sm-4 form-group">
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
												<h2>Finalidad</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<div class="col-sm-1 form-group">
															
														</div>
														<div class="col-sm-11 form-group">
															<textarea rows="3" name="txt_finalidad" id="txt_finalidad" class="form-control" placeholder="Ingresar la finalidad de la donación"></textarea>
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
												<h2>Evaluado Por</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row"> 
														<div class="col-sm-1 form-group">
															
														</div>
														<div class="col-sm-6 form-group">
															<select id="sel_oficina" name="sel_oficina" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_oficinas}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div> 
													    <div class="col-sm-1 form-group"></div> 
														<div class="col-sm-4 form-group">
															<select id="sel_personal_oficina" name="sel_personal_oficina" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_personal_oficina}" var="item">
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
												<h2>Datos para la solicitud de Aprobación</h2>
											</header>
								
											<!-- widget div-->
											<div> 
												<!-- widget content -->
												<div class="widget-body">
								 
												    <div class="row">
													 	<div class="col-sm-1 form-group">
															
														</div>
														<div class="col-sm-11 form-group">
															<textarea rows="5" name="txt_a" id="txt_a" class="form-control" placeholder="Texto 1: Ingresar el objeto de la calificación"></textarea>
														</div>
													</div>
													<div class="row">
												 		<div class="col-sm-1 form-group">
															
														</div>
														<div class="col-sm-11 form-group">
															<textarea rows="5" name="txt_b" id="txt_b" class="form-control" placeholder="Texto 1: Ingresar el texto de la aceptación o negación de la donación"></textarea>
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
											
												<label class="col-sm-2 control-label">Documentos de la Donación:</label>
												<div class="col-sm-2 form-group">
													<input type="text" id="txt_codigo_cod_pro" class="form-control" disabled>
												</div>
												
												<table id="tbl_det_documentos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th>Tipo Documento</th>
															<th>Nro Documento</th>
															<th>Fecha</th>
															<th>Descripción</th>
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
												<label class="col-sm-2 control-label">Productos de la Donación:</label>
												<div class="col-sm-2 form-group">
													<input type="text" id="txt_codigo_cod_doc" class="form-control" disabled>
												</div>
												<table id="tbl_det_productos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th>Producto</th>
															<th>Unidad de Medida</th>
															<th>Cantidad</th>
															<th>Moneda Origen</th>
															<th>Importe Moneda Origen</th>
															<th>Importe Soles</th>
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
								
								<div class="tab-pane fade" id="div_estados">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Estados</h2>

										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">
												<label class="col-sm-2 control-label">Estados de la Donación:</label>
												<div class="col-sm-2 form-group">
													<input type="text" id="txt_codigo_cod_est" class="form-control" disabled>
												</div>
<!-- 												<div id="contTablaEstados"> -->
<%-- 													${tabla_estados} --%>
<!-- 												</div> -->
												<table id="tbl_det_estados" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th>Nº</th>
															<th>Estado</th>
															<th>Descripción</th>
															<th>Fecha</th>
															<th>Usuario</th>

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
								
								<div class="tab-pane fade" id="div_alimentarios">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Detalle de Productos</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_ali_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Nuevo">
													<i class="fa fa-file-o"></i>
												</a>
												<a href="#" id="href_ali_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
												<a href="#" id="href_ali_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_det_alimentarios" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Producto</th>
															<th>Unidad de Medida</th>
															<th>Lote</th>
															<th>Fecha Vencimiento</th>
															<th>Cantidad de Lote</th>
															<th>Cantidad para la Muestra</th>
															<th>Envase Primario</th>
															<th>Envase Secundario</th>
															<th>Olor</th>
															<th>Color</th>
															<th>Textura</th>
															<th>Sabor</th>
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
								
								<div class="tab-pane fade" id="div_no_alimentarios">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Detalle de Productos</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_no_ali_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Nuevo">
													<i class="fa fa-file-o"></i>
												</a>
												<a href="#" id="href_no_ali_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
												<a href="#" id="href_no_ali_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_det_no_alimentarios" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Producto</th>
															<th>Unidad de Medida</th>
															<th>Lote</th>
															<th>Fecha Vencimiento</th>
															<th>Cantidad de Lote</th>
															<th>Cantidad para la Muestra</th>
															<th>Envase Primario</th>
															<th>Especif. Técnicas</th>
															<th>Conformidad</th>
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
										<textarea rows="3" name="txt_descripcion_pro" id="txt_descripcion_pro" class="form-control"></textarea>
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
								<div class="col-sm-8 form-group">
									<select id="sel_lis_producto" name="sel_lis_producto" class="form-control">
									</select>
								</div>

							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-3 control-label">Unidad Medida:</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_uni_medida_pro" class="form-control" disabled>
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
								<label class="col-sm-3 control-label">Cantidad:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_cantidad" id="txt_cantidad" class="form-control monto-format" maxlength="10">
								</div>
							</div>
							
							<div class="row">

								<label class="col-sm-3 control-label">Moneda Origen:</label>
								<div class="col-sm-3 form-group">
									<select id="sel_monedas" name="sel_monedas" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_monedas}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
								
								<label class="col-sm-3 control-label">Importe en Moneda Origen:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_imp_origen" id="txt_imp_origen" class="form-control monto-format" maxlength="10">
								</div>
							</div>
							
							<div class="row">

								<label class="col-sm-3 control-label">Importe en Soles:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_imp_soles" id="txt_imp_soles" class="form-control monto-format" maxlength="10">
								</div>
								
								<label class="col-sm-3 control-label">Importe en Dolares:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_imp_dolares" id="txt_imp_dolares" class="form-control monto-format" maxlength="10">
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
<script src="${pageContext.request.contextPath}/resources/js/donaciones/mantenimiento_donaciones.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/validacion_mantenimiento_donaciones.js"></script>