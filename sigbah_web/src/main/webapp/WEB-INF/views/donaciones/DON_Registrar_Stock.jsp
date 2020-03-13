<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Stock</li>
		<li>Maestro de Stock</li>
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
	
							<ul id="ul_man_ord_salida" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Datos Generales</a>
								</li>
								<li id="li_det_lotes">
									<a href="#div_det_lotes" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Detalle de Lotes</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">

										<div class="header-form opc-center">	
											<strong>Stock de Productos</strong>
										</div>
										
										<div class="form-group"></div>
				
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
															<input type="text" id="txt_codigo" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Código SIGA:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_cod_siga" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_estado" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Categoría:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_categoria" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Nombre:</label>
														<div class="col-sm-6 form-group">
															<input type="text" id="txt_nom_producto" class="form-control" disabled>
														</div>
														
														
													</div>
								
													<div class="row">
													
														<label class="col-sm-2 control-label">Unidad de Medida:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_uni_medida" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Tipo Envase:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_env_primario" class="form-control" disabled>
														</div>
	
													</div>
													
													<div class="row">
														

														<label class="col-sm-2 control-label">Peso Unit. Neto Kg:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_pes_uni_neto" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Peso Unit. Bruto Kg:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_pes_uni_bruto" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
													
<!-- 														<label class="col-sm-2 control-label">Almacén:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_almacen" class="form-control" disabled> -->
<!-- 														</div> -->

<!-- 														<label class="col-sm-6 control-label">Nro Kardexxxxx:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_nro_kardex" class="form-control" disabled> -->
<!-- 														</div> -->
														

<!-- 														<label class="col-sm-2 control-label">Código:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_codigo" class="form-control" disabled> -->
<!-- 														</div> -->
													</div>
														
													<div class="row">
<!-- 														<label class="col-sm-2 control-label">Categoría:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_categoria" class="form-control" disabled> -->
<!-- 														</div> -->

														
														
<!-- 														<label class="col-sm-2 control-label">Unidad Medida:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_uni_medida" class="form-control" disabled> -->
<!-- 														</div> -->
													</div>
													
													<div class="row">
														

<!-- 														<label class="col-sm-2 control-label">Peso Unit. Neto Kg:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_pes_uni_neto" class="form-control" disabled> -->
<!-- 														</div> -->
														
<!-- 														<label class="col-sm-2 control-label">Peso Unit. Bruto Kg:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_pes_uni_bruto" class="form-control" disabled> -->
<!-- 														</div> -->
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
												<h2>Tipos de Embalaje del Envase Secundario</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">		
													
												
													
													
													
													<div class="row">
														<label class="col-sm-2 control-label">Envase:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_env_secundario" name="sel_env_secundario" class="form-control" disabled>
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
															<input type="text" id="txt_uni_envase" class="form-control only-numbers-format" maxlength="10" disabled>
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">Descripción:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_des_env_sec" class="form-control alphaNumeric" maxlength="50" disabled>
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
															<input type="text" id="txt_largo" class="form-control" disabled>
														</div>

<!-- 														<label class="col-sm-4 control-label">Volumen Unitario:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_vol_uni_m3" class="form-control" disabled> -->
<!-- 														</div> -->
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Alto:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_alto" class="form-control" disabled>
														</div>
														
														<label class="col-sm-4 control-label">Volumen Unitario (m³):</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_vol_uni_m3" class="form-control" disabled>
														</div>

<!-- 														<label class="col-sm-4 control-label">Volumen Total:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_vol_tot_m3" class="form-control" disabled> -->
<!-- 														</div> -->
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Ancho:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_ancho" class="form-control" disabled>
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
												<h2>Stock</h2>
											</header>
										
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
												
													<div class="row">
														<label class="col-sm-2 control-label">Cantidad:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_stock" class="form-control" disabled>
														</div>

														<label class="col-sm-4 control-label">Precio Unitario:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_pre_promedio" class="form-control" disabled>
														</div>
													</div>

<!-- 													<div class="row"> -->
<!-- 														<label class="col-sm-2 control-label">Stock Seguridad:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_sto_seguridad" class="form-control only-numbers-format" maxlength="10"> -->
<!-- 														</div> -->
<!-- 													</div> -->
													
<!-- 													<div class="row"> -->
<!-- 														<div class="col-sm-1"></div> -->
														
<!-- 														<div class="col-sm-11">	 -->
<!-- 															<strong>Tipo de Embalaje</strong> -->
<!-- 														</div> -->
<!-- 													</div> -->
													
													
													
<!-- 													<div class="row"> -->
<!-- 														<label class="col-sm-2 control-label">Cantidad Envases:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_can_envases" class="form-control" disabled> -->
<!-- 														</div> -->

<!-- 														<label class="col-sm-4 control-label">Unidades Sueltas:</label> -->
<!-- 														<div class="col-sm-2 form-group"> -->
<!-- 															<input type="text" id="txt_uni_sueltas" class="form-control" disabled> -->
<!-- 														</div> -->
<!-- 													</div> -->

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
								
								<div class="tab-pane fade" id="div_det_lotes">
									
									<form class="form-horizontal">

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
														<label class="col-sm-2 control-label">Producto:</label>
														<div class="col-sm-6 form-group">
															<input type="text" id="txt_producto" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Código Producto:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_cod_producto" class="form-control" disabled>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Código SIGA:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_det_cod_siga" class="form-control" disabled>
														</div>

														<label class="col-sm-2 control-label">Stock Total:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_sto_total" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Unidad Medida:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_det_uni_medida" class="form-control" disabled>
														</div>
													</div>
														
													<div class="row">
														<label class="col-sm-2 control-label">Tipo de Envase:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_det_tip_envase" class="form-control" disabled>
														</div>
														
														<label class="col-sm-2 control-label">Precio Promedio:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_det_pre_promedio" class="form-control" disabled>
														</div>
													</div>

												</div>
												<!-- end widget content -->
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->	
									
									</form>								
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Detalle de Lotes</h2>
											
											<div class="jarviswidget-ctrls" role="menu">
												<a href="#" id="href_pro_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_det_lotes" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Item</th>
															<th>Lote</th>
															<th>Stock</th>
															<th>Precio Unitario</th>
															<th>Importe Total</th>
															<th>Fecha de Vencimiento</th>
															<th>Fecha de Producción</th>
															<th>Fecha de Alta</th>
															<th>Planta</th>
															<th>Nave</th>
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
<div id="div_edi_lotes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog  modal-80-large">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold">Editar Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_lotes" class="form-horizontal" role="form">

							<div class="row">																				
								<label class="col-sm-2 control-label">Fecha de Vencimiento:</label>
								<div class="col-sm-2 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" id="txt_fec_vencimiento" class="datepicker" readonly>
									</label>
								</div>
								
								<label class="col-sm-2 control-label">Fecha de Produción:</label>
								<div class="col-sm-2 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" id="txt_fec_produccion" class="datepicker" readonly>
									</label>
								</div>
								
								<label class="col-sm-2 control-label">Fecha de Alta:</label>
								<div class="col-sm-2 smart-form form-group">
									<label class="input"> 
										<i class="icon-append fa fa-calendar"></i>
										<input type="text" id="txt_fec_alta" class="datepicker" readonly>
									</label>
								</div>
							</div>
							
							<div class="row"><div class="col-sm-12">&nbsp;</div></div>
						
							<div class="row">																				
								<label class="col-sm-2 control-label">Cantidad:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_lot_cantidad" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Precio Unitario (S/.):</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_lot_pre_unitario" class="form-control" disabled>
								</div>
								
								<label class="col-sm-2 control-label">Importe Total (S/.):</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_lot_imp_total" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">																				
								<label class="col-sm-2 control-label">Lote:</label>
								<div class="col-sm-2 form-group">
									<input type="text" id="txt_lote" class="form-control" disabled>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-1"></div>
													
								<div class="col-sm-4">	
									<strong>Ubicación</strong>
								</div>
								
								<div class="col-sm-2"></div>
								
								<div class="col-sm-5">	
									<strong>Código de Barras</strong>
								</div>
							</div>
							
							<div class="row"><div class="col-sm-12">&nbsp;</div></div>
							
							<div class="row">
								<div class="col-sm-8 form-group">
									<div class="row">
									 	<label class="col-sm-3 control-label">Planta:</label>
										<div class="col-sm-3 form-group">
											<input type="text" id="txt_lot_planta" class="form-control alphaNumeric" maxlength="25">
										</div>							
									
										<label class="col-sm-2 control-label">Marca:</label>
										<div class="col-sm-3 form-group">
											<select id="sel_lot_marca" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_marca}" var="item">
												    <option value="${item.icodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div class="row">
									 	<label class="col-sm-3 control-label">Nave:</label>
										<div class="col-sm-3 form-group">
											<input type="text" id="txt_lot_nave" class="form-control alphaNumeric" maxlength="25">
										</div>
									</div>
									
									<div class="row">
									 	<label class="col-sm-3 control-label">Área:</label>
										<div class="col-sm-3 form-group">
											<input type="text" id="txt_lot_area" class="form-control alphaNumeric" maxlength="25">
										</div>
									</div>
								</div>
							
								<div class="col-sm-4 form-group">
									<div class="row">
										<div class="col-sm-12">
										 	<img src='http://barcode.tec-it.com/barcode.ashx?translate-esc=off&data=127010015501&code=EAN13&unit=Fit&dpi=96&imagetype=Gif&rotation=0&color=000000&bgcolor=FFFFFF&qunit=Mm&quiet=0' 
											alt='Barcode Generator TEC-IT' class="img-responsive" />
										</div>
									</div>									
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_lote">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var stockAlmacen = JSON.parse('${stockAlmacen}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/mantenimiento_stock_donacion.js"></script>
