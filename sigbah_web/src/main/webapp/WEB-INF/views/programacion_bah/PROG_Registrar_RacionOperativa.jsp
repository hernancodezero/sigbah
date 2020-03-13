<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación </li>
		<li>Registro de raciones</li>
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
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales_racion" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_raciones" name="hid_cod_raciones">
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Editar Ración</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Tipo de ración:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_tipo_racion" name="sel_tipo_racion" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_racion}" var="item">
																    <option value="${item.vcodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>	
													<div class="row">	
														<label class="col-sm-2 control-label">Código Ración:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_cod_racion" name="txt_cod_racion" class="form-control" readonly>
														</div>
														<label class="col-sm-2 control-label">Nombre Ración:</label>
														<div class="col-sm-4 form-group">
															<input type="text" id="txt_nom_racion" name="txt_nom_racion" class="form-control" >
														</div>
													</div>	
													
													<div class="row">	
														<label class="col-sm-2 control-label">N° Dias de atención:</label>
														<div class="col-sm-2 form-group">
															<input type="text" id="txt_num_dias" name="txt_num_dias" class="form-control only-numbers-format" >
														</div>
														<label class="col-sm-2 control-label">Fecha:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha" id="txt_fecha" class="datepicker"  >
															</label>
														</div>
														<div class="col-sm-2 opc-center">
															<button class="btn btn-primary" type="button" id="btn_grabar_racion">
																<i class="fa fa-floppy-o"></i>
																Grabar
															</button>
														</div>
													</div>	
													
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
									</div>				
									</form>
										
								</div>
								
							</div>
							<!-- NEW WIDGET START -->
							<article class="col-xs-12 col-sm-12">
					
								<!-- Widget ID (each widget will need unique ID)-->
								<div id="div_tabla_prod"  class="jarviswidget jarviswidget-color-blueLight" >
								
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i> </span>
										<h2>Productos de la Ración</h2>
										
										<div class="jarviswidget-ctrls" role="menu">   
											<a href="#" id="href_exp_excel_prod" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
												data-original-title="Exportar Excel">
												<i class="fa fa-file-excel-o"></i>
											</a> 
											<a href="#" id="href_editar_prod" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
												data-original-title="Editar">
												<i class="fa fa-edit"></i>
											</a>
											<a href="#" id="href_nuevo_prod" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
												data-original-title="Nuevo">
												<i class="fa fa-file-o"></i>
											</a>
											<a href="#" id="href_eliminar_prod" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
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
														<th>Unidad de presentacion (Kg.)</th>
														<th>Kg. Aproximado</th>
													</tr>
												</thead>
												<tfoot>
														<tr>
															<td colspan="4">
																<span class="label-bold" style="float:right;">Total:</span>
															</td>
															<td colspan="1"><span id="sp_tot_gramos"></span></td>	
														</tr>
													</tfoot>
											</table>
				
										</div>
										<!-- end widget content -->
					
									</div>
									<!-- end widget div -->
					
								</div>
								<!-- end widget -->
				
							</article>
							<!-- WIDGET END -->
							<article >
								<div class="row">
									<div class="col-md-12 opc-center">
										<button class="btn btn-default btn_retornar" type="button">
											<i class="fa fa-mail-forward"></i>
											Retornar
										</button>
									</div>
								</div>
							</article>	
	
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
				<h4 class="modal-title label-bold" id="h4_tit_productos">Productos</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_productos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
						
							<div id="div_pro_det_alimentarios" class="row">																				
								<label class="col-sm-3 control-label">Producto:</label>
								<div class="col-sm-5 form-group">
									<select id="sel_producto" name="sel_producto" class="form-control ">
										<c:forEach items="${lista_producto}" var="item">
											<option value="${item.idProducto}">${item.nombreProducto}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="row">&nbsp;</div>

							<div class="row">
								<label class="col-sm-3 control-label">Unidad de presentación (Kg.):</label>
								<div class="col-sm-3 form-group">
									<input type="text" id="txt_uni_pres" name="txt_uni_pres" class="form-control monto-format3" >
								</div>

								<label class="col-sm-3 control-label">Kg. por persona:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_gr_aprox" id="txt_gr_aprox" class="form-control monto-format3" >
								</div>
							</div>
							
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_prod">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default " data-dismiss="modal" id="btn_can_prod">
					<i class="fa fa-mail-forward"></i>
					Retornar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var racion = JSON.parse('${racion}'); </script>
<!-- <script> var lista_racion = JSON.parse('${lista_racion}'); </script> -->
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/mantenimiento_racion.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/validacion_mantenimiento_racion.js"></script>
