<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Seguridad</li>
		<li>Lista de Correos</li>
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
						<h2>Correos</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_correos" class="form-horizontal">
								<input type="hidden" id="hid_codigo" name="hid_codigo">
								<div class="form-group">
									<label class="col-sm-1 control-label">Módulo:</label>
									<div class="col-sm-2">
										<select id="sel_modulo" name="sel_modulo" class="form-control">
											<option value="DONACION">Donación</option>
											<option value="PROGRAMACION">Programación</option>
											<option value="INVENTARIOS">Inventario</option>
										</select>
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
						<h2>Lista de Correos</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
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
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_correos" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th class="opc-center"></th>
										<th class="opc-center">N°</th>
										<th class="opc-center">Módulo</th>
										<th class="opc-center">Estado</th>
										<th class="opc-center">Nombre</th>
										<th class="opc-center">Usuario</th>
										<th class="opc-center">Correo</th>
										<th class="opc-center">Cargo</th>
										<th class="opc-center">Activo?</th>
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
<div id="div_rol" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_rol">Rol</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_correo_act" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_correo" name="hid_id_correo">
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Módulo:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_modulo_nue" name="sel_modulo_nue" class="form-control">
										<option value="">Seleccione</option>
										<option value="DONACION">Donación</option>
										<option value="PROGRAMACION">Programación</option>
										<option value="INVENTARIOS">Inventario</option>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Estado:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_estado_mod" name="sel_estado_mod" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_estado_modulo}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Destinatario:</label>
								<div class="col-sm-8 form-group">
									<select id="sel_destinatario" name="sel_destinatario" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_usuarios}" var="item">
										    <option value="${item.idUsuario}_${item.email}_${item.cargo}">${item.nombreUsuario}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Correo:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_correo" id="txt_correo" class="form-control" maxlength="50" disabled>
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Cargo:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_cargo" id="txt_cargo" class="form-control" maxlength="50" disabled>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Activo?:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_activo" name="sel_activo" class="form-control">
										<option value="1">Si</option>
										<option value="0">No</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								
									
								<div class="col-sm-2">	
								</div>	
								<div class="col-sm-8" id="div_menu_rol" style="display: none;">
									
								</div>

							
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_correo">
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
<script src="${pageContext.request.contextPath}/resources/js/jstree/jstree.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jstree/themes/default/style.min.css" />
<script src="${pageContext.request.contextPath}/resources/js/administracion/listar_correos.js"></script>
