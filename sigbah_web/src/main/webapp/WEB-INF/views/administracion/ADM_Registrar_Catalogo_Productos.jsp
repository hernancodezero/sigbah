<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Catálogo de Productos</li>
		<li>Registrar Catálogo de Productos</li>
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
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_id_catalogo_producto" name="hid_id_catalogo_producto">	
																		
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Datos del Producto</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">				
													
													<div class="row">
														<label class="col-sm-2 control-label">Código:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_cod_producto" id="txt_cod_producto" class="form-control">
														</div>

														<label class="col-sm-2 control-label">Código SIGA:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_cod_siga" id="txt_cod_siga" class="form-control">
														</div>
														
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																<option value="A">Activo</option>
																<option value="I">Anulado</option>
															</select>
														</div>
													</div>
													
													
													<div class="row">
														<label class="col-sm-2 control-label">Categoría:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_categoría" name="sel_categoría" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_categoria}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-2 control-label">Nombre:</label>
														<div class="col-sm-6 form-group">
															<input type="text" name="txt_nombre" id="txt_nombre" class="form-control upperValue">
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Unidad de Medida:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_unidad_medida" name="sel_unidad_medida" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_unidad}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														
														<label class="col-sm-2 control-label">Tipo Envase:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_tipo_envase" name="sel_tipo_envase" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_envase}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Peso Unit. Neto Kg:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_peso_neto" id="txt_peso_neto" class="form-control">
														</div>
														
														<label class="col-sm-2 control-label">Peso Unit. Bruto Kg:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_peso_bruto" id="txt_peso_bruto" class="form-control">
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
												<h2>Tipo de Embalaje del Envase Secundario</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Envase:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_env_secundario" name="sel_env_secundario" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_envase}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">N° de Unidades por Envase:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_uni_env" id="txt_uni_env" class="form-control" >
														</div>	
													</div>
													<div class="row">	
														<label class="col-sm-2 control-label">Descripción:</label>
														<div class="col-sm-4 form-group">
															<input type="text" name="txt_des_env" id="txt_des_env" class="form-control"  >
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
												<h2>Dimensiones del Envase Secundario (metros)</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Largo:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_largo" id="txt_largo" class="form-control" maxlength="10">
														</div>	
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Alto:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_alto" id="txt_alto" class="form-control" maxlength="10">
														</div>	
														
														<label class="col-sm-2 control-label">Volúmen Unitario m3:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_volumen" id="txt_volumen" class="form-control" maxlength="10" disabled>
														</div>	
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Ancho:</label>
														<div class="col-sm-2 form-group">
															<input type="text" name="txt_ancho" id="txt_ancho" class="form-control" maxlength="10">
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
												<h2>Observación</h2>
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


<!-- inline scripts related to this page -->
<script> var catalogo_producto = JSON.parse('${catalogo_producto}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/mask/jquery.maskedinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/mantenimiento_catalogo_productos.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/validacion_catalogo_productos.js"></script>