<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Seguridad</li>
		<li>Registrar Usuario</li>
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
								
									<form id="frm_usuarios" class="frm_usuarios">
									
										<input type="hidden" id="hid_id_usuario" name="hid_id_usuario">

																		
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Datos</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">				
													
													<div class="row">
										
														<label class="col-sm-2 control-label">Usuario:</label>
														<div class="col-sm-3 form-group">
															<input type="text" name="txt_usuario" id="txt_usuario" class="form-control" maxlength="50">
														</div>

														<label class="col-sm-2 control-label">Password:</label>
														<div class="col-sm-3 form-group">
															<input type="text" name="txt_password" id="txt_password" class="form-control" maxlength="50">
														</div>
													</div>
													<div class="row">	
														<label class="col-sm-2 control-label">Nombre:</label>
														<div class="col-sm-8 form-group">
															<input type="text" name="txt_nombre" id="txt_nombre" class="form-control" maxlength="50">
														</div>
													</div>	
													<div class="row">
														<label class="col-sm-2 control-label">Cargo:</label>
														<div class="col-sm-8 form-group">
															<input type="text" name="txt_cargo" id="txt_cargo" class="form-control" maxlength="50">
														</div>
													</div>
													<div class="row">	
														<label class="col-sm-2 control-label">Correo:</label>
														<div class="col-sm-8 form-group">
															<input type="text" name="txt_correo" id="txt_correo" class="form-control" maxlength="50">
														</div>
													</div>	
													<div class="row">
														<label class="col-sm-2 control-label">Estado:</label>
										
														<div class="col-sm-8 form-group">
																<label class="col-sm-3 control-label">
																<input class="col-sm-2" type="radio" name="rb_estado" value="1">
																Activo</label>
															
																<label class="col-sm-3 control-label">
																<input  class="col-sm-2" type="radio" name="rb_estado" value="0">
																Inactivo</label>
																																	
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
												<h2>Roles del Usuario</h2>
											</header>
								
											<!-- widget div-->
											<div>
											
													<div class="form-group">
														<div id="div_tbl_rol" style="display: none;">
															<table id="tbl_roles" class="table table-bordered table-hover tbl-responsive">
																<thead>			                
																	<tr>
																		<th>N°</th>
																		<th>Rol</th>
																		<th>Asignar</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
								
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
										
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Almacenes</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
													<div class="form-group">
														<label class="col-sm-1 control-label">DDI:</label>
														<div class="col-sm-2">
															<select id="sel_ddi" name="sel_ddi" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_ddi}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<div id="div_tbl_almacen" style="display: none;">
															<table id="tbl_almacenes" class="table table-bordered table-hover tbl-responsive">
																<thead>			                
																	<tr>
																		<th>N°</th>
																		<th>Almacén</th>
																		<th>Asignar</th>
																	</tr>
																</thead>
															</table>
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
												<h2>Estados de Programación</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">

													<div class="form-group">
														<div id="div_tbl_estado_programacion" style="display: none;">
															<table id="tbl_estado_programacion" class="table table-bordered table-hover tbl-responsive">
																<thead>			                
																	<tr>
																		<th>N°</th>
																		<th>Estado</th>
																		<th>Asignar</th>
																	</tr>
																</thead>
															</table>
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
												<h2>Estados de Donación</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">

													<div class="form-group">
														<div id="div_tbl_estado_donacion" style="display: none;">
															<table id="tbl_estado_donacion" class="table table-bordered table-hover tbl-responsive">
																<thead>			                
																	<tr>
																		<th>N°</th>
																		<th>Estado</th>
																		<th>Asignar</th>
																	</tr>
																</thead>
															</table>
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
												<h2>Estados de Inventario</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">

													<div class="form-group">
														<div id="div_tbl_estado_inventario" style="display: none;">
															<table id="tbl_estado_inventario" class="table table-bordered table-hover tbl-responsive">
																<thead>			                
																	<tr>
																		<th>N°</th>
																		<th>Estado</th>
																		<th>Asignar</th>
																	</tr>
																</thead>
															</table>
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
<div id="div_rol" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_rol">Rol</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_rol" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_unidad_medida" name="hid_id_unidad_medida">

							<div class="form-group">																			
								
								<div id="div_menu_rol">
									
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_documento">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var usuario = JSON.parse('${usuario}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/mantenimiento_usuarios.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/validacion_usuarios.js"></script>