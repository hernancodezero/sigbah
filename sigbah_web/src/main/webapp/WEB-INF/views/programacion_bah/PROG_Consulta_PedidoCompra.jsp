<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación </li>
		<li>Consultas - Pedidos de compra</li>
	</ol>
	<!-- end breadcrumb -->
</div>
<!-- END RIBBON -->
    
<!-- MAIN CONTENT -->
<div id="content">
	
	<!-- widget grid -->
	<section id="sec_wid_grid" class="">
	
		<!-- row -->
		<div class="row">
		
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
		
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
						<h2>Búsqueda de Pedidos de Compra</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_emer_sinpad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_consul_prog" class="form-horizontal">
							
								<input type="hidden" id="hid_cod_consul_prog" name="hid_cod_consul_prog">
							
	
								<div class="row">
									<label class="col-sm-1 control-label">Año:</label>
									<div class="col-sm-1 form-group">
										<select  id="sel_anio" name="sel_anio" class="form-control" style="padding:0px;">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-1 control-label">DDI:</label>
										<div class="col-sm-2 form-group">
											<select  id="sel_ddi" name="sel_ddi" class="form-control">
											    <option value="99">Nacional</option> 
												<c:forEach items="${lista_ddi}" var="item">
												    <option value="${item.vcodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									<label class="col-sm-1 control-label">Motivo:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_motivo" name="sel_motivo" class="form-control">
<!-- 											<option value="">Seleccione</option> -->
											<option value="1">Reabastecimiento</option>
											<option value="2">Emergencia</option>
										</select>
									</div>
									<label class="col-sm-1 control-label">Estado:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_estado" name="sel_estado" class="form-control">
<!-- 											<option value="">Todos</option> -->
											<option value="1">Pendiente</option>
											<option value="2">Atendido</option>
										</select>
									</div>					
									<div class="col-sm-1 opc-center">
										<button class="btn btn-primary" type="button" id="btn_aceptar">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
								</div>
								
								
							</form>
		
						</div>
						<!-- end widget content -->
		
					</div>
					<!-- end widget div -->
		
				</div>
				<!-- end widget -->
				
			</article>	
		
	
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
	
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget jarviswidget-color-blueLight">
				
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Pedido de compras</h2>
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body" id="consul_ped">

							<table id="tbl_mnt_consul_ped" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										
										<th>Nº</th>
										<th>Año</th>
										<th>DDI</th>
										<th>Motivo</th>
										<th>Alimentos (TM)</th>
										<th>Alimentos (Soles)</th>
										<th>BNA(TM)</th>
										<th>BNA (Soles)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="4">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_aliTM"></span></td>	
										<td colspan="1"><span id="sp_tot_aliSol"></span></td>
										<td colspan="1"><span id="sp_tot_noAliTM"></span></td>
										<td colspan="1"><span id="sp_tot_noAliSol"></span></td>
									</tr>
								</tfoot>
							</table>

						</div>
						
						<div class="widget-body" id="consul_ped2">

							<table id="tbl_mnt_consul_ped2" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Año</th>
										<th>DDI</th>
										<th>Motivo</th>
										<th>Nro. Pedido</th>
										<th>Alimentos (TM)</th>
										<th>Alimentos (Soles)</th>
										<th>BNA(TM)</th>
										<th>BNA (Soles)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_aliTM2"></span></td>	
										<td colspan="1"><span id="sp_tot_aliSol2"></span></td>
										<td colspan="1"><span id="sp_tot_noAliTM2"></span></td>
										<td colspan="1"><span id="sp_tot_noAliSol2"></span></td>
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
	
		</div>	
		<!-- end row -->
	
	</section>
	<!-- end widget grid -->
	
</div>
<!-- END MAIN CONTENT -->
<!-- Modal  por ddi-->
<div id="div_det_prod" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Pedidos de Compra</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							<input type="hidden" id="hid_cod_prod" name="hid_cod_prod">
							<div class="row">
								<div class="form-group">
										<label class="col-sm-2 control-label">Año:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_anio" name="txt_anio"  class="form-control" >
										</div>
										<div class="col-sm-1 form-group"></div>
										<label class="col-sm-2 control-label">DDi:</label>
										<div class="col-sm-4 form-group">
												<input type="text" id="txt_ddi" name="txt_ddi"  class="form-control" >
										</div>
								</div>	
								
							</div>
							<div class="row">
								<div class="form-group">
										<label class="col-sm-2 control-label">Motivo:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_motivo" name="txt_motivo"  class="form-control" >
										</div>
										<div class="col-sm-1 form-group"></div>
										<label class="col-sm-2 control-label">Estado:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_estado" name="txt_estado"  class="form-control" >
										</div>
								</div>	
								
							</div>
						</form>
					</div>
				</div>	
				
			<div class="row">&nbsp;</div>	
			
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
				<div class="jarviswidget jarviswidget-color-blueLight"><!-- Widget ID (each widget will need unique ID)-->
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Alimentos</h2>
						<div class="jarviswidget-ctrls" role="menu">   
<!-- 							<a href="#" id="href_ubi_excel_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 								data-original-title="Exportar Excel"> -->
<!-- 								<i class="fa fa-file-excel-o"></i> -->
<!-- 							</a>  -->
						</div>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_ali" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Producto</th>
										<th>Unidad Medida</th>
										<th>Cantidad</th>
										<th>Total(TM)</th>
										<th>Total(S/.)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="5">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_tm"></span></td>	
										<td colspan="1"><span id="sp_tot_s"></span></td>
									</tr>
								</tfoot>
							</table>
						</div><!-- end widget content -->
					</div><!-- end widget div -->
				</div>
				<div class="jarviswidget jarviswidget-color-blueLight">	
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Bienes No Alimentos</h2>
						<div class="jarviswidget-ctrls" role="menu">   
<!-- 							<a href="#" id="href_ubi_excel_no_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 								data-original-title="Exportar Excel"> -->
<!-- 								<i class="fa fa-file-excel-o"></i> -->
<!-- 							</a>  -->
						</div>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_no_ali" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Categoria</th>
										<th>Producto</th>
										<th>Unidad Medida</th>
										<th>Cantidad</th>
										<th>Total(TM)</th>
										<th>Total(S/.)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_no_tm"></span></td>	
										<td colspan="1"><span id="sp_tot_no_s"></span></td>
									</tr>
								</tfoot>
							</table>
						</div><!-- end widget content -->
					</div><!-- end widget div -->
				</div><!-- end widget -->
			</article>
			<!-- WIDGET END -->
			</div>
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal  por pedido compra -->
<div id="div_modal_pedido" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios2" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios2">Pedidos de Compra</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							<input type="hidden" id="hid_cod_prod2" name="hid_cod_prod2">
							<div class="row">
								<div class="form-group">
										<label class="col-sm-2 control-label">Año:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_anio2" name="txt_anio2"  class="form-control" >
										</div>
										<div class="col-sm-1 form-group"></div>
										<label class="col-sm-2 control-label">DDi:</label>
										<div class="col-sm-4 form-group">
												<input type="text" id="txt_ddi2" name="txt_ddi2"  class="form-control" >
										</div>
								</div>	
								
							</div>
							<div class="row">
								<div class="form-group">
										<label class="col-sm-2 control-label">Motivo:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_motivo2" name="txt_motivo2"  class="form-control" >
										</div>
										<div class="col-sm-1 form-group"></div>
										<label class="col-sm-2 control-label">Estado:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_estado2" name="txt_estado2"  class="form-control" >
										</div>
								</div>	
								
							</div>
						</form>
					</div>
				</div>	
				
			<div class="row">&nbsp;</div>	
			
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
				<div class="jarviswidget jarviswidget-color-blueLight"><!-- Widget ID (each widget will need unique ID)-->
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Alimentos</h2>
						<div class="jarviswidget-ctrls" role="menu">   
<!-- 							<a href="#" id="href_ubi_excel_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 								data-original-title="Exportar Excel"> -->
<!-- 								<i class="fa fa-file-excel-o"></i> -->
<!-- 							</a>  -->
						</div>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_ali2" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Producto</th>
										<th>Unidad Medida</th>
										<th>Cantidad</th>
										<th>Total(TM)</th>
										<th>Total(S/.)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="5">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_tm2"></span></td>	
										<td colspan="1"><span id="sp_tot_s2"></span></td>
									</tr>
								</tfoot>
							</table>
						</div><!-- end widget content -->
					</div><!-- end widget div -->
				</div>
				<div class="jarviswidget jarviswidget-color-blueLight">	
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Bienes No Alimentos</h2>
						<div class="jarviswidget-ctrls" role="menu">   
<!-- 							<a href="#" id="href_ubi_excel_no_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 								data-original-title="Exportar Excel"> -->
<!-- 								<i class="fa fa-file-excel-o"></i> -->
<!-- 							</a>  -->
						</div>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_no_ali2" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Categoria</th>
										<th>Producto</th>
										<th>Unidad Medida</th>
										<th>Cantidad</th>
										<th>Total(TM)</th>
										<th>Total(S/.)</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_no_tm2"></span></td>	
										<td colspan="1"><span id="sp_tot_no_s2"></span></td>
									</tr>
								</tfoot>
							</table>
						</div><!-- end widget content -->
					</div><!-- end widget div -->
				</div><!-- end widget -->
			</article>
			<!-- WIDGET END -->
			</div>
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/listar_consultas_pedidos_compra.js"></script>
