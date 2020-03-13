<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Consultas</li>
		<li>Historial de Donaciones</li>
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
						<h2>Búsqueda de Historial de Donaciones</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_historial" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
							
								<div class="row">
									<label class="col-sm-1 control-label">Año:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_anio" name="sel_anio" class="form-control">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">DDI:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_ddi" name="sel_ddi" class="form-control">
											<option value="0">Todos</option>
											<c:forEach items="${lista_ddi}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Procedencia:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_tip_donacion" name="sel_tip_donacion" class="form-control">
											<option value="%">Todos</option>
											<option value="1">Nacional</option>
											<option value="2">Internacional</option>

										</select>
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
									
									<div class="col-sm-12 opc-right">
										<button class="btn btn-primary" type="button" id="btn_buscar">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
									
								</div>
								
							</form>
							<div id="dialog" style="display: none">
							</div>
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
						<h2>Relación de Donaciones</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
<!-- 							<a href="#" id="href_imprimir" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 								data-original-title="Imprimir"> -->
<!-- 								<i class="fa fa-file-pdf-o"></i> -->
<!-- 							</a> -->
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_historial" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										
										<th>Nº</th>
										<th>Año</th>
										<th>DDI</th>
										<th>Código Donación</th>
										<th>Procedencia</th>
										<th>País</th>
										<th>Donante</th>
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

<!-- Modal -->
<div id="div_imp_pdf" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold">IMPRIMIR</h4>
				
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form class="form-horizontal" role="form">
							
							<div class="row">
								<label class="col-sm-5 control-label">Guia Remision:</label>
								<div class="col-sm-7 smart-form">
									<label class="checkbox">
										<input type="checkbox" name="chk_gui_remision" id="chk_gui_remision"><i></i>
									</label>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-5 control-label">Manifiesto Carga:</label>
								<div class="col-sm-7 smart-form">
									<label class="checkbox">
										<input type="checkbox" name="chk_man_carga" id="chk_man_carga"><i></i>
									</label>
								</div>
							</div>
							
							<div class="row">
								<label class="col-sm-5 control-label">Acta Entrega / Recepción:</label>
								<div class="col-sm-7 smart-form">
									<label class="checkbox">
										<input type="checkbox" name="chk_act_ent_recepcion" id="chk_act_ent_recepcion"><i></i>
									</label>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_exportar">
					<i class="fa fa-file-pdf-o"></i>
					Exportar
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
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/listar_consulta_historial.js"></script>
