<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación</li>
		<li>Pedido de compra</li>
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
				<div>

					<!-- widget div-->
					<div>
	
						<!-- widget content -->
						<div class="widget-body">
	
							<ul id="ul_man_con_calidad" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Registrar pedidos de compra</a>
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
									
										<input type="hidden" id="hid_cod_ped_compra" name="hid_cod_ped_compra">
	
											<!-- widget div-->
											<div>
							
												<!-- widget content -->
												<div class="widget-body">
												<div class="form-group"></div>
													<div class="row">
														
														<label class="col-sm-2 control-label">Nro. pedido de compra:</label>
														<div class="col-sm-2 form-group">
																<input type="text" id="txt_num_pedido" name="txt_num_pedido"  class="form-control" >
														</div>
													</div>	
													
<!-- 													<div class="row"> -->
<!-- 														<label class="col-sm-1 control-label"></label> -->
<!-- 														<label class="col-sm-4 control-label " id="subtitulo">Datos generales de pedido</label> -->
														
<!-- 													</div>	 -->
													<div class="form-group"></div>
													<div class="row">
														<label class="col-sm-2 control-label">Fecha de pedido:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha_pedido" id="txt_fecha_pedido" class="datepicker" >
															</label>
														</div>
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																		<option value="">Seleccione</option>
																	<c:forEach items="${lista_estado}" var="item">
																	    <option value="${item.icodigo}">${item.descripcion}</option>
																	</c:forEach>
															</select>
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">Pedido por:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_pedidoPor" name="sel_pedidoPor" class="form-control">
																<option value="">Seleccione</option>
																<option value="1">Reabastecimiento</option>
																<option value="2">Emergencia</option>
															</select>
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">Descripción:</label>
														<div class="col-sm-6 form-group">
																<input type="text" id="txt_descripcion" name="txt_descripcion" class="form-control" >
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">DEE:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_dee" name="sel_dee" class="form-control">
																<option value="0">Seleccione</option>
																<c:forEach items="${lista_dee}" var="item">
																    <option value="${item.icodigo}">${item.descripcionCorta}</option>
																</c:forEach>
															</select>
														</div>
													</div>
										
												</div>
												<!-- end widget content -->
								
<!-- 											</div> -->
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
										
										<div class="form-actions">
											<div class="row">
												<div class="col-md-12 opc-center">
													<button class="btn btn-primary" type="button" id="btn_grabar_dat_gen">
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
											<h2>Productos</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_prod_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Nuevo">
													<i class="fa fa-file-o"></i>
												</a>
												<a href="#" id="href_prod_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
												<a href="#" id="href_prod_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
															
											<!-- widget content -->
											<div class="widget-body">

												<table id="tbl_mnt_productos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th>Producto</th>
															<th>Unidad de medida</th>
															<th>Cantidad</th>
															<th>Precio unitario (S/)</th>
															<th>Importe total (S/)</th>
														</tr>
													</thead>
												</table>

											</div>
											<!-- end widget content -->
						
										</div>
										<!-- end widget div -->
						
									</div>
									<!-- end widget -->
									
									<div class="modal-footer">
										<button type="button" class="btn btn-default btn_retornar" data-dismiss="modal" id="btn_can_afectados">
											<i class="fa fa-mail-forward"></i>
											Retornar
										</button>
									</div>
									
								</div>
								
								<div class="tab-pane fade" id="div_documentos">
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
										
										
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Documentos</h2>
											
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

												<table id="tbl_mnt_documentos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th> 
															<th>Tipo Documento</th>
															<th>N° Documento</th>
															<th>Fecha</th>
															<th>Nombre de Archivo</th>
														</tr>
													</thead>
												</table>

											</div>
											<!-- end widget content -->
						
										</div>
										<!-- end widget div -->
						
									</div>
									<!-- end widget -->
									
									<div class="modal-footer">
										<button type="button" class="btn btn-default btn_retornar" data-dismiss="modal" id="btn_can_afectados">
											<i class="fa fa-mail-forward"></i>
											Retornar
										</button>
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

<!-- Modal  Agregar Productos-->
<div id="div_det_productos" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Productos</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<form id="frm_productos" class="form-horizontal">
											<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
											<div class="form-group"></div>
											<div class="row">
												<label class="col-sm-2 control-label" >Pedido de compra:</label>
												<div class="col-sm-6 form-group">
														<input type="text" id="txt_desc_pedido" name="txt_desc_pedido"  class="form-control" disabled>
												</div>
											</div>	
											
												<hr style=" border-color: blue;" width="100%" size="20" align="center"/>
												
											
											
											<div class="form-group"></div>
											<div class="row">
												<label class="col-sm-2 control-label">Categoria de producto:</label>
												<div class="col-sm-2 form-group">
													<select id="sel_cat_producto" name="sel_cat_producto" class="form-control">
														<option value="">Seleccione</option>
														<c:forEach items="${lista_categoria}" var="item">
														    <option value="${item.icodigo}">${item.descripcion}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div id="div_pro_det_productos" class="row">
												<label class="col-sm-2 control-label">Producto:</label>
												<div class="col-sm-6 form-group">
													<select id="sel_lis_producto" name="sel_lis_producto" class="form-control">
														</select>
												</div>
													
											</div>
											<div class="row">
												<label class="col-sm-2 control-label">Unidad de Medida:</label>
												<div class="col-sm-2 form-group">
														<input type="text" id="txt_unidad_med" name="txt_unidad_med" class="form-control" disabled>
												</div>
											</div>
											<div class="row">
												<label class="col-sm-2 control-label">Cantidad:</label>
												<div class="col-sm-2 form-group">
														<input type="text" id="txt_cantidad" name="txt_cantidad" class="form-control" >
												</div>
												<label class="col-sm-2 control-label">Precio Unitario (S/):</label>
												<div class="col-sm-2 form-group">
														<input type="text" id="txt_pre_unitario" name="txt_pre_unitario" class="form-control" >
												</div>
												<label class="col-sm-2 control-label">Importe Total:</label>
												<div class="col-sm-2 form-group">
														<input type="text" id="txt_imp_total" name="txt_imp_total" class="form-control monto-format" disabled>
												</div>
											</div>
<!-- 											<div class="row"> -->
<!-- 												<div class="form-actions"> -->
													
<!-- 														<div class="col-md-12 opc-center"> -->
<!-- 															<button class="btn btn-primary" type="button" id="btn_grabar_prod"> -->
<!-- 																<i class="fa fa-floppy-o"></i> -->
<!-- 																Grabar -->
<!-- 															</button> -->
															
														
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div>	 -->
										</form>
				</div>	
				
			<!-- WIDGET END -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_grabar_prod">
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

<!-- Modal  Agregar EMERGENCIAS ACTIVAS-->
<div id="div_det_docu" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_documentos">Documentos</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<form id="frm_det_documentos" class="form-horizontal">
							<input type="hidden" id="hid_cod_documento" name="hid_cod_documento">
							<input type="hidden" id="hid_cod_act_alfresco" name="hid_cod_act_alfresco">
							<input type="hidden" id="hid_cod_ind_alfresco" name="hid_cod_ind_alfresco">
							
											<div class="form-group"></div>
											<div class="row">
<!-- 													<label class="col-sm-2 control-label"></label> -->
													<label class="col-sm-2 control-label" >Pedido de compra:</label>
													<div class="col-sm-6 form-group">
															<input type="text" id="txt_desc_pedido_doc" name="txt_desc_pedido_doc"  class="form-control" disabled>
													</div>
											</div>	
												<hr style=" border-color: blue;" width="100%" size="20" align="center"/>	
											<div class="form-group"></div>
											
											<div class="row">
												<label class="col-sm-2 control-label">Tipo Documento:</label>
												<div class="col-sm-4 form-group">
													<select id="sel_tipo_doc" name="sel_tipo_doc" class="form-control">
														<option value="">Seleccione</option>
														<c:forEach items="${lista_tipo_doc}" var="item">
														    <option value="${item.vcodigo}">${item.descripcion}</option>
														</c:forEach>
													</select>
												</div>
												
												<label class="col-sm-2 control-label">N° Documento:</label>
												<div class="col-sm-3 form-group">
														<input type="text" id="txt_num_doc" name="txt_num_doc" class="form-control" >
												</div>
											</div>
											<div class="row">	
												<label class="col-sm-2 control-label">Fecha:</label>
												<div class="col-sm-2 smart-form form-group">
													<label class="input"> 
														<i class="icon-append fa fa-calendar"></i>
														<input type="text" name="txt_fecha_doc" id="txt_fecha_doc" class="datepicker" >
													</label>
												</div>
												
											
												
													<label class="col-sm-2 control-label">Subir Archivo:</label>
													<div class="col-sm-5 smart-form">
														<div class="input input-file">
															<span id="sp_sub_archivo" class="button">
																<input type="file" id="fil_sub_archivo" name="fil_sub_archivo">
																Cargar
															</span>
															<input type="text" id="txt_lee_sub_archivo" name="txt_lee_sub_archivo" readonly>
														</div>
													</div>
													<div class="col-sm-1">
														<a href="#" id="href_eli_archivo" class="btn btn-default txt-color-red" rel="tooltip" 
															data-placement="right" data-original-title="Eliminar Archivo" data-html="true">
															<i class="fa fa-eraser fa-lg"></i>
														</a>
													</div>					
												
											</div>
											
<!-- 										<div class="form-actions"> -->
<!-- 											<div class="row"> -->
<!-- 												<div class="col-md-12 opc-center"> -->
<!-- 													<button class="btn btn-primary" type="button" id="btn_gra_documento"> -->
<!-- 														<i class="fa fa-floppy-o"></i> -->
<!-- 														Grabar -->
<!-- 													</button> -->
													
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div>	 -->
									</form>
				</div>	
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_documento">
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
<script> var pedido = JSON.parse('${pedido}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/mantenimiento_pedido_compra.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/validacion_mantenimiento_pedidos_compra.js"></script>
