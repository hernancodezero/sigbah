<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Lista Catálogo de Productos</li>
		<li>Registrar Producto SIGA</li>
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
						<h2>Búsqueda de Productos</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_siga" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
							
								<div class="row">
									<label class="col-sm-2 control-label">Buscar por:</label>
									<div class="col-sm-3 form-group">
										<select id="sel_buscar" name="sel_buscar" class="form-control">
										    <option value="1">Nombre del Producto</option>
											<option value="2">Grupo, Clase y Familia</option>
										</select>
									</div>
								</div>
								
								
								<div class="row" id="div_buscar_nombre" style="display: none;">
									<label class="col-sm-2 control-label">Nombre:</label>
									<div class="col-sm-8 form-group">
										<input type="text" name="txt_nombre" id="txt_nombre" class="form-control" maxlength="50">
									</div>
									
									<div class="col-sm-2 opc-center">
										<button class="btn btn-primary" type="button" id="btn_buscar_nom">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
								</div>
								
								<div id="div_buscar_grupo" style="display: none;">	
									<div class="row">
										<label class="col-sm-2 control-label">Grupo:</label>
										<div class="col-sm-8 form-group">
											<select id="sel_grupo" name="sel_grupo" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_grupo}" var="item">
												    <option value="${item.vcodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row">	
										<label class="col-sm-2 control-label">Clase:</label>
										<div class="col-sm-8 form-group">
											<select id="sel_clase" name="sel_clase" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_clase}" var="item">
												    <option value="${item.vcodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row">		
										<label class="col-sm-2 control-label">Familia:</label>
										<div class="col-sm-8 form-group">
											<select id="sel_familia" name="sel_familia" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_familia}" var="item">
												    <option value="${item.vcodigo}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
										
										<div class="col-sm-2 opc-center">
											<button class="btn btn-primary" type="button" id="btn_buscar_gru">
												<i class="fa fa-search"></i>
												Buscar
											</button>
										</div>
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
						<h2>Relación de Productos</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   

							<a href="#" id="href_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Editar">
								<i class="fa fa-edit"></i>
							</a>

						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_productos_siga" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nombre del producto</th>
										<th>Código SIGA</th>
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

			</article>
			<!-- WIDGET END -->
	
		</div>	
		<!-- end row -->
	
	</section>
	<!-- end widget grid -->
	
</div>
<!-- END MAIN CONTENT -->

<!-- Modal -->
<div id="div_agregar_siga" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_unidad_medida">Agregar Producto</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_producto_siga" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_siga" name="hid_id_siga">
							<input type="hidden" id="hid_grupo_bien" name="hid_grupo_bien">
							<input type="hidden" id="hid_clase_bien" name="hid_clase_bien">
							<input type="hidden" id="hid_familia_bien" name="hid_familia_bien">
							<input type="hidden" id="hid_item_bien" name="hid_item_bien">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Producto:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_pro" id="txt_nom_pro" class="form-control" disabled>
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Categoría del Producto:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_categoria" name="sel_categoria" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_categoria}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_grabar_siga">
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
<script> var siga = JSON.parse('${siga}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/listar_catalogo_productos_siga.js"></script>
