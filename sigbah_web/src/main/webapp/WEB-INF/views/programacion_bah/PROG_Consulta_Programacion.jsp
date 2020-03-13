<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación </li>
		<li>Consulta programación de Requerimiento</li>
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
						<h2>Búsqueda de programación de requerimientos</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_emer_sinpad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_consul_prog" class="form-horizontal">
							
								<input type="hidden" id="hid_cod_consul_prog" name="hid_cod_consul_prog">
							
								<div class="row">
									<label class="col-sm-2 control-label">Consultar por:</label>
									<div class="col-sm-3 form-group">
										<select id="sel_tipo" name="sel_tipo" class="form-control">
<!-- 										<option value="">Seleccione</option> -->
										<option value="1" >Meses del año</option>
										<option value="2">Declaratoria del Estado de Emergencias</option>
									</select>
									</div>
								</div>	
	
								<div class="row">
									<label class="col-sm-1 control-label">Año:</label>
									<div class="col-sm-1 form-group">
										<select  id="sel_anio" name="sel_anio" class="form-control" style="padding:0px;">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									<div id="divmes">
										<label class="col-sm-1 control-label">Mes:</label>
										<div class="col-sm-4 form-group">
											<select multiple id="sel_mes" name="sel_mes" class="form-control">
<!-- 												<option value="99">Todos</option>  -->
												<c:forEach items="${lista_mes}" var="item">
												    <option value="${item.vcodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									
											<div class="col-sm-1 checkbox">
											  <label><input type="checkbox" value="" id="checkboxMes">Todos</label>
											</div>
									</div>
									<div id="divdee">
										<label class="col-sm-1 control-label">DEE:</label>
										<div class="col-sm-2 form-group">
											<select multiple id="sel_dee" name="sel_dee" class="form-control">
												<c:forEach items="${lista_dee}" var="item">
												    <option value="${item.icodigo}">${item.descripcionCorta}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<label class="col-sm-1 control-label">Estado:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_estado" name="sel_estado" class="form-control">
											<option value="99">Todos</option> 
											<c:forEach items="${lista_estado}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
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
						<h2 id="titulo_tabla">Programacion por año y mes</h2>
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
							<a href="#" id="href_imprimir" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Imprimir">
								<i class="fa fa-file-pdf-o"></i>
							</a>
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_mnt_consul_prog" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
									
										<th>Nº</th>
										<th>Año</th>
										<th>Mes</th>
										<th>DEE</th>
										<th>N° Prog.</th>
										<th>Fecha Prog.</th>
										<th>Descripción</th>
										<th>Región Destino</th>
										<th>Total alimentos (TM)</th>
										<th>Total BNA(TM)</th>
										<th>Estado</th>
									</tr>
								</thead>
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

<!-- Modal  Agregar Ubigeo-->
<div id="div_det_prod" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Productos</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							<input type="hidden" id="hid_cod_prod" name="hid_cod_prod">
							<div class="row">
								<div class="form-group">
										<label class="col-sm-2 control-label">Programación N°:</label>
										<div class="col-sm-2 form-group">
												<input type="text" id="txt_num_prog" name="txt_num_prog"  class="form-control" >
										</div>
										<div class="col-sm-1 form-group"></div>
										<div class="col-sm-6 form-group">
												<input type="text" id="txt_des_prog" name="txt_des_prog"  class="form-control" >
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
							<a href="#" id="href_ubi_excel_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
						</div>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_ali" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Categoria</th>
										<th>Producto</th>
										<th>U. Medida</th>
										<th>Cantidad</th>
										<th>Kilos</th>
										<th>Toneladas</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
<!-- 										<td colspan="1"><span id="sp_tot_cantidad"></span></td>	 -->
										<td colspan="1"><span id="sp_tot_kilos"></span></td>
										<td colspan="1"><span id="sp_tot_toneladas"></span></td>
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
							<a href="#" id="href_ubi_excel_no_ali" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
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
										<th>U. Medida</th>
										<th>Cantidad</th>
										<th>Kilos</th>
										<th>Toneladas</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="6">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
<!-- 										<td colspan="1"><span id="sp_tot_cantidad2"></span></td>	 -->
										<td colspan="1"><span id="sp_tot_kilos2"></span></td>
										<td colspan="1"><span id="sp_tot_toneladas2"></span></td>
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
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/listar_consultas_programacion.js"></script>
