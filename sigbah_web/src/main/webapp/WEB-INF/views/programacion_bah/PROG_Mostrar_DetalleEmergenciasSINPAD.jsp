<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación </li>
		<li>Consulta programación BAH</li>
		<li>Registro</li>
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
									
										<input type="hidden" id="hid_cod_con_calidad" name="hid_cod_con_calidad">
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Requerimientos de ayuda humanitaria</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">SINPAD:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_cod_sinpad" class="form-control" readonly>
														</div>
														<div class="col-sm-1 form-group"></div>
														
														<div class="col-sm-6 smart-form">
															<section>
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_desc_sinpad" id="txt_desc_sinpad" 
																		maxlength="500" class="custom-scroll" readonly disabled></textarea> 
																</label>
															</section>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker"  readonly disabled>
															</label>
														</div>
														
														<label class="col-sm-2 control-label">Fenómeno:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_fenomeno" name="txt_fenomeno" class="form-control" readonly>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Ubigeo:</label>
														<div class="col-sm-3 form-group">
															<input type="text" id="txt_ubigeo" name="txt_ubigeo" class="form-control" readonly >
														</div>
													</div>
													
													
													<div class="row">
														<div class="col-sm-12 header-form">	
															<strong>Localidades</strong>
														</div>
													</div>
													
													<div class="row"><div class="col-sm-12">&nbsp;</div></div>
													<!-- widget content -->
													<div class="widget-body">
		
														<table id="tbl_det_localidades" class="table table-bordered table-hover tbl-responsive">
															<thead>			                
																<tr>
																	<th>Nº</th>
																	<th>Región</th>
																	<th>Provincia</th>
																	<th>Distrito</th>
																	<th>Localidad</th>
																	<th>Fam. Afect.</th>
																	<th>Fam. Dam.</th>
																	<th>Pers. Afec.</th>
																	<th>Pers. Dam.</th>
																</tr>
															</thead>
														</table>
		
													</div>
													<!-- end widget content -->
								
													<div class="row">
														<div class="col-sm-12 header-form">	
															<strong>Alimentos</strong>
														</div>
													</div>
													<div class="row"><div class="col-sm-12">&nbsp;</div></div>
													<!-- widget content -->
													<div class="widget-body">
		
														<table id="tbl_det_alimentarios" class="table table-bordered table-hover tbl-responsive">
															<thead>			                
																<tr>
																	<th>Nº</th>
																	<th>Producto</th>
																	<th>Nro. de raciones</th>
																</tr>
															</thead>
														</table>
		
													</div>
													<!-- end widget content -->
													<div class="row">
														<div class="col-sm-12 header-form">	
															<strong>Bienes No Alimentarios</strong>
														</div>
													</div>
													<div class="row"><div class="col-sm-12">&nbsp;</div></div>
													<!-- widget content -->
													<div class="widget-body">
		
														<table id="tbl_det_no_alimentarios" class="table table-bordered table-hover tbl-responsive">
															<thead>			                
																<tr>
																	<th>Nº</th>
																	<th>Categoria de Producto</th>
																	<th>Producto</th>
																	<th>Cantidad</th>
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
<div id="div_det_alimentarios" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Nuevo Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_alimentarios" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
						
							<div id="div_pro_det_alimentarios" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-5 form-group">
									<select id="sel_producto" name="sel_producto" class="form-control ">
										<c:forEach items="${lista_producto}" var="item">
											<option value="${item.idProducto}_${item.nombreUnidadMedida}">${item.nombreProducto}</option>
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

								<label class="col-sm-3 control-label">Fecha Vencimiento:</label>
								<div class="col-sm-3 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" name="txt_fec_vencimiento" id="txt_fec_vencimiento" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-3 control-label">Cantidad de Lote:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_can_lote" id="txt_can_lote" class="form-control monto-format" maxlength="10">
								</div>

								<label class="col-sm-3 control-label">Cantidad de Muestra:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_can_muestra" id="txt_can_muestra" class="form-control monto-format" maxlength="10">
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-4 header-modal-form opc-center">
									<strong>Verificacion Fisica del Envase</strong>
								</div>
								<div class="col-sm-8 header-modal-form opc-center">
									<strong>Verificación Física del Producto</strong>
								</div>
							</div>
							
							<div class="form-group"></div>
							
							<div class="row">																				
								<label class="col-sm-2 control-label">Primario:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_primario" name="sel_primario" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Olor:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_olor" name="sel_olor" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Textura:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_textura" name="sel_textura" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
									</select>
								</div>
							</div>
							
							<div class="row">																				
								<label class="col-sm-2 control-label">Secundario:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_secundario" name="sel_secundario" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Color:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_color" name="sel_color" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Sabor:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_sabor" name="sel_sabor" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
									</select>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_alimentario">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_alimentario">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_det_no_alimentarios" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_no_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_no_alimentarios">Nuevo Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_no_alimentarios" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_no_producto" name="hid_cod_no_producto">
							
							<div class="row">																				
								<label class="col-sm-3 control-label">Categoría de Producto:</label>
								<div class="col-sm-3 form-group">
									<select id="sel_no_cat_producto" name="sel_no_cat_producto" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_categoria}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
							<div id="div_pro_det_no_alimentarios" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-6 form-group">
									<select id="sel_no_producto" name="sel_no_producto" class="form-control">
									</select>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>									

							<div class="row">
								<label class="col-sm-3 control-label">Unidad Medida:</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_no_uni_medida" class="form-control" disabled>
								</div>

								<label class="col-sm-3 control-label">Fecha Vencimiento:</label>
								<div class="col-sm-3 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" name="txt_no_fec_vencimiento" id="txt_no_fec_vencimiento" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-3 control-label">Cantidad de Lote:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_no_can_lote" id="txt_no_can_lote" class="form-control monto-format" maxlength="10">
								</div>

								<label class="col-sm-3 control-label">Cantidad de Muestra:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_no_can_muestra" id="txt_no_can_muestra" class="form-control monto-format" maxlength="10">
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-4 header-modal-form opc-center">
									<strong>Verificacion Fisica del Envase</strong>
								</div>
								<div class="col-sm-8 header-modal-form opc-center">
									<strong>Verificación Física del Producto</strong>
								</div>
							</div>
							
							<div class="form-group"></div>
							
							<div class="row">																				
								<label class="col-sm-2 control-label">Primario:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_no_primario" name="sel_no_primario" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Técnicas:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_no_tecnicas" name="sel_no_tecnicas" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Cumple</option>
										<option value="2">No Cumple</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
							</div>
							
							<div class="row">																				
								<label class="col-sm-2 control-label">Secundario:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_no_secundario" name="sel_no_secundario" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Bueno</option>
										<option value="2">Malo</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Conformidad:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_no_conformidad" name="sel_no_conformidad" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Cumple</option>
										<option value="2">No Cumple</option>
										<option value="3">No Aplica</option>
									</select>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_no_alimentario">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_no_alimentario">
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

    		
<!-- inline scripts related to this page -->
<script> var cabecera = JSON.parse('${cabecera}'); </script>
<script> var lista_localidad = JSON.parse('${lista_localidad}'); </script>
<script> var lista_alimentaria = JSON.parse('${lista_alimentaria}'); </script>
<script> var lista_no_alimentaria = JSON.parse('${lista_no_alimentaria}'); </script>
<script> var codiRegion = ${codiRegion}; </script>
<script> var codiAnio = ${codiAnio}; </script>
<script> var codiMes = ${codiMes}; </script>
<script> var codiFenomeno = ${codiFenomeno}; </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/mantenimiento_emergencia.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/validacion_mantenimiento_emergencia.js"></script>
