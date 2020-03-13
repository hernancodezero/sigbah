<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gestión de Almacenes</li>
		<li>Stock</li>
		<li>Registro - Cartilla de Inventario</li>
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
	
							<ul id="ul_man_car_inventario" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Datos Generales</a>
								</li>
								<li id="li_productos">
									<a href="#div_productos" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Productos</a>
								</li>
								<li id="li_ajustes">
									<a href="#div_ajustes" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Ajustes</a>
								</li>
								<li id="li_estados">
									<a href="#div_estados" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Estados</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_cartilla" name="hid_cod_cartilla">
									
										<div class="header-form opc-center">	
											<strong>Cartilla de Inventario</strong>
										</div>
										
										<div class="form-group"></div>
										
										<div class="form-group">
											<div class="col-sm-3"></div>
											<label class="col-sm-3 control-label label-bold">N° Cartilla:</label>
											<div class="col-sm-2">
												<input type="text" id="txt_nro_cartilla" class="form-control" disabled>
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
														<label class="col-sm-2 control-label">DDI:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_ddi" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Almacén:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_almacen" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Fecha Emisión:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fec_emision" id="txt_fec_emision" class="datepicker" readonly>
															</label>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_estado" class="form-control" disabled>
														</div>
														
														<label class="col-sm-3 control-label">Responsable del Inventario:</label>
														<div class="col-sm-3 form-group">
															<select id="sel_res_inventario" name="sel_res_inventario" class="form-control">
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
												<a href="#" id="btn_agr_productos" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Agregar todos los productos">
													<i class="fa fa-retweet"></i>
												</a>
												<a href="#" id="href_imprimir_pro" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Imprimir">
													<i class="fa fa-file-pdf-o"></i>
												</a>
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
											
<!-- 												<div class="row"> -->
<!-- 													<div class="col-sm-9 form-group"></div> -->
													
<!-- 													<div class="col-sm-3 opc-center"> -->
<!-- 														<button class="btn btn-primary" type="button" id="btn_agr_productos"> -->
<!-- 															<i class="fa fa-retweet"></i> -->
<!-- 															Agregar todos Productos -->
<!-- 														</button> -->
<!-- 													</div> -->
<!-- 												</div> -->

												<table id="tbl_det_productos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th class="opc-center" >Sel</th>
															<th class="opc-center">Nº</th>
															<th class="opc-center">Producto</th>
															<th class="opc-center">Unidad de Medida</th>
															<th class="opc-center">Nro. Lote</th>
															<th class="opc-center">Nave</th>
															<th class="opc-center">Precio Unitario</th>
															<th class="opc-center">Stock Actual</th>
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
								
								<div class="tab-pane fade" id="div_ajustes">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Productos Inventariados Ajustar</h2>
											
											<div class="jarviswidget-ctrls" role="menu">
												<a href="#" id="href_imprimir_pro_aju" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Imprimir">
													<i class="fa fa-file-pdf-o"></i>
												</a>   
												<a href="#" id="href_pro_ajuste" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Procesar Ajuste">
													<i class="fa fa-retweet"></i>
												</a>
												<a href="#" id="href_aju_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_det_ajustes" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th>Producto</th>
															<th>Unidad de Medida</th>
															<th>Lote</th>
															<th>Stock Sistema</th>
															<th>Stock Físico</th>
															<th>Diferencia</th>
															<th>Tipo</th>
															<th>Nro. Documento Ajuste</th>
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
											<h2 id="h2_det_estados"></h2>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_det_estados" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th>Item</th>
															<th>Estado</th>
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
				<button type="button" id="btn_clo_productos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_productos">Nuevo Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_productos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
						
							<div id="div_pro_det_productos" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-8 form-group">
									<select id="sel_producto" name="sel_producto" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_producto}" var="item">
											<option value="${item.idProducto}_${item.codigoProducto}_${item.nombreUnidad}_${item.nombreEnvase}">${item.nombreProducto}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-2 control-label">Código:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pro_codigo" class="form-control" disabled>
								</div>

								<label class="col-sm-2 control-label">Unidad Medida:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pro_uni_medida" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Envase:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pro_envase" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-2 control-label">Lote:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_pro_lote" name="sel_pro_lote" class="form-control">
									</select>
								</div>

								<label class="col-sm-2 control-label">Cantidad Stock:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pro_can_stock" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Precio Unitario:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_pro_pre_unitario" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 smart-form">
									<section>														
										<label class="control-label">Observaciones:</label>
										<label class="textarea textarea-resizable"> 										
											<textarea rows="3" name="txt_observaciones_producto" id="txt_observaciones_producto" 
												maxlength="500" class="custom-scroll"></textarea> 
										</label>
									</section>
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
<div id="div_det_ajustes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_ajustes" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold">Actualizar Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_ajustes" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_aju_cod_producto" name="hid_aju_cod_producto">
						
							<div class="row">																				
								<label class="col-sm-2 control-label">Producto:</label>
								<div class="col-sm-6 form-group">
									<input type="text" id="txt_producto" class="form-control" disabled>
								</div>

								<label class="col-sm-2 control-label">Código:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_codigo" class="form-control" disabled>
								</div>
								
							</div>
							
							<div class="row">
								<label class="col-sm-2 control-label">Unidad Medida:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_uni_medida" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Envase:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_envase" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Lote:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_lote" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-2 control-label">Stock Sistema:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_sto_sistema" class="form-control" disabled>
								</div>

								<label class="col-sm-2 control-label">Stock Físico:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_sto_fisico" name="txt_aju_sto_fisico" class="form-control only-numbers-format" maxlength="10">
								</div>
								
								<label class="col-sm-2 control-label">Diferencia:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_aju_diferencia" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 smart-form">
									<section>														
										<label class="control-label">Observaciones:</label>
										<label class="textarea textarea-resizable"> 										
											<textarea rows="3" name="txt_observaciones_ajuste" id="txt_observaciones_ajuste" 
												maxlength="500" class="custom-scroll"></textarea> 
										</label>
									</section>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_ajuste">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_ajuste">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var cartillaInventario = JSON.parse('${cartillaInventario}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/mantenimiento_cartilla_inventario.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/validacion_mantenimiento_cartilla_inventario.js"></script>
