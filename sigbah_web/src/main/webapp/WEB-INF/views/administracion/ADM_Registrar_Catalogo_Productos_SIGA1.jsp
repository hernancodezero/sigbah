<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administraci�n</li>
		<li>Cat�logo de Productos</li>
		<li>Lista de Cat�logo de Productos SIGA</li>
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
						<h2>B�squeda de Productos</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_con_donaciones" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
								<input type="hidden" id="txt_cod_ddi" name="txt_cod_ddi" value="${txt_cod_ddi}">
								<div class="form-group">
									<label class="col-sm-2 control-label">Grupo:</label>
									<div class="col-sm-4">
										<select id="sel_grupo" name="sel_grupo" class="form-control">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Clase:</label>
									<div class="col-sm-4">
										<select id="sel_clase" name="sel_clase" class="form-control">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Familia:</label>
									<div class="col-sm-4">
										<select id="sel_clase" name="sel_familia" class="sel_familia">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Producto:</label>
									<div class="col-sm-2 form-group">
										<input type="text" name="txt_producto" id="txt_producto" class="form-control upperValue" maxlength="10">
									</div>
			
									
									<div class="col-sm-2">
										<button class="btn btn-primary" type="button" id="btn_buscar">
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
						<h2>Lista de Productos</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
							<a href="#" id="href_imprimir" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Imprimir">
								<i class="fa fa-file-pdf-o"></i>
							</a>
							<a href="#" id="href_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Editar">
								<i class="fa fa-edit"></i>
							</a>
							<a href="#" id="href_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Nuevo">
								<i class="fa fa-file-o"></i>
							</a>
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_producto" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>N�</th>
										<th>Nombre Producto</th>
										<th>C�digo SIGA</th>
										<th>Agregar</th>

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
<div id="div_estado" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_documentos">Estado de la Donaci�n</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_nue_estado" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_est_documento" name="hid_est_documento">

							<div class="form-group">																				
								<label class="col-sm-3 control-label">Nuevo Estado:</label>
								<div class="col-sm-8">
									<select id="sel_estados_donacion" name="sel_estados_donacion" class="form-control">
										<option value="0">Seleccione</option>
										<c:forEach items="${lista_est_donacion}" var="item">
											<option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div id="divRegiones">
									<label class="col-sm-3 control-label">Seleccione Regi�n a Distribuir</label>
									<div class="col-sm-8">
										<select id="sel_region_donacion" name="sel_region_donacion" class="form-control">
											<option value="">Seleccione</option>
											<c:forEach items="${lista_region}" var="item">
												<option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
										
							
									</div class="col-sm-12">
									
									<div>
										<table id="tbl_regiones" class="table table-bordered table-hover tbl-responsive">
											<thead>			                
												<tr>
													<th>N�</th>
													<th>Regi�n</th>
													<th>Eliminar</th>
												</tr>
											</thead>
										</table>
									</div>
									
									
									
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_act_estado">
					<i class="fa fa-floppy-o"></i>
					Aceptar
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
<script src="${pageContext.request.contextPath}/resources/js/administracion/listar_catalogo_productos.js"></script>
