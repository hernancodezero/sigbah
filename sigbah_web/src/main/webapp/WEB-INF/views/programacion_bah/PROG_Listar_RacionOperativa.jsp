<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programaci�n</li>
		<li>Raciones</li>
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
						<h2>B�squeda de raciones</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_racion_oper">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_racion_oper" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
							
								<div class="row">
									<label class="col-sm-1 control-label">A�o:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_anio" name="sel_anio" class="form-control">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Mes:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_mes" name="sel_mes" class="form-control">
											<option value="">Todos</option>
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Tipo de Raci�n:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_tipo_racion" name="sel_tipo_racion" class="form-control">
											<option value="">Todos</option>
											<c:forEach items="${lista_racion}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<div class="col-sm-1 opc-center">
										<button class="btn btn-primary" type="button" id="btn_aceptar">
											<i class="fa fa-search"></i>
											Aceptar
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
						<h2>Raciones</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_exp_copiar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Copiar Raci�n">
								<i class="fa fa-files-o"></i>
							</a> 
							<a href="#" id="href_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a>
							<a href="#" id="href_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Editar">
								<i class="fa fa-edit"></i>
							</a>
							<a href="#" id="href_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Nuevo">
								<i class="fa fa-file-o"></i>
							</a>
							<a href="#" id="href_modal" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Composici�n">
								<i class="fa fa-search"></i>
							</a>
							
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_mnt_rac_oper" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>N�</th>
										<th>A�o</th>
										<th>Mes</th>
										<th>N� Raci�n</th>
										<th>Fecha</th>
										<th>Tipo Raci�n</th>
										<th>Nombre de la Raci�n</th>
										<th>N�de D�as</th>
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
<div id="div_composicion" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_documentos">Composici�n de una Raci�n de Alimentos por Persona</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_nue_estado" class="form-horizontal" role="form">
							
							

							<div class="form-group">																				
								<img src="${pageContext.request.contextPath}/resources/img/composicion.png" class="img-responsive">
							</div>
							
							
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="btn_composicion">
					
					Aceptar
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script> var racione = JSON.parse('${racione}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/listar_raciones_operativas.js"></script>
