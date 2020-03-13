<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Salidas</li>
		<li>Guia de Remisión Edición</li>
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
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down"></i> 
									Datos Generales</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_gui_remision" name="hid_cod_gui_remision">
										<input type="hidden" id="hid_cod_ord_salida" name="hid_cod_ord_salida">
										<input type="hidden" id="hid_cod_anio" name="hid_cod_anio">
										<input type="hidden" id="hid_cod_mes" name="hid_cod_mes">
										<input type="hidden" id="hid_cod_ddi" name="hid_cod_ddi">
										<input type="hidden" id="hid_cod_almacen" name="hid_cod_almacen">
										<input type="hidden" id="hid_fec_salida" name="hid_fec_salida">
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
														<label class="col-sm-2 control-label">Nº Orden Salida:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_nro_ord_salida" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_fecha" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Tipo de Movimiento:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_tip_movimiento" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">DDI:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_ddi" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Almacén:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_almacen" class="form-control" disabled>
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
												<h2>Guia de Remisión</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Nº Guia de Remisión:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_nro_gui_remision" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Motivo Traslado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_mot_traslado" name="sel_mot_traslado" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_motivo_traslado}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																<c:forEach items="${lista_estado}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Fecha de Emisión:</label>
														<div class="col-sm-3 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fec_emision_n" id="txt_fec_emision_n" class="datepicker">
															</label>
														</div>
													</div>
													<br>
													<div class="row">
														<label class="col-sm-2 control-label">Fecha de Traslado:</label>
														<div class="col-sm-3 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fec_traslado_n" id="txt_fec_traslado_n" class="datepicker" >
															</label>
														</div>
													</div>
													<br>
													<div class="row">
														<label class="col-sm-3 control-label">Observaciones:</label>
														<div class="col-sm-9 smart-form">
															<section>														
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_obs_gui_remision" id="txt_obs_gui_remision" 
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
										
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Manifiesto de Carga</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Nº Manifiesto de Carga:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_nro_man_carga" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label">Observaciones:</label>
														<div class="col-sm-9 smart-form">
															<section>														
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_obs_man_carga" id="txt_obs_man_carga" 
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
										
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Acta de Entrega</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-3 control-label">Nº Acta de Entrega:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_nro_act_entrega" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Fecha de Recepción:</label>
														<div class="col-sm-3 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fec_recepcion" id="txt_fec_recepcion" class="datepicker" readonly>
															</label>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-3 control-label">Observaciones:</label>
														<div class="col-sm-9 smart-form">
															<section>														
																<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_obs_act_entrega" id="txt_obs_act_entrega" 
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

<!-- inline scripts related to this page -->
<script> var guiaRemision = JSON.parse('${guiaRemision}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/mantenimiento_guia_remision.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/validacion_mantenimiento_guia_remision.js"></script>
