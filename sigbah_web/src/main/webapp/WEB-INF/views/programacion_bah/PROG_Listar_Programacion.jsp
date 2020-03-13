<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación</li>
		<li>Programación de Requerimientos</li>
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
						<h2>Búsqueda de Programación</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_programacion">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_programacion" class="form-horizontal">
							
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
									
									<label class="col-sm-1 control-label">Mes:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_mes" name="sel_mes" class="form-control">
											<option value="">Todos</option>
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Fenómeno:</label>
									<div class="col-sm-4 form-group">
										<select id="sel_fenomeno" name="sel_fenomeno" class="form-control">
											<option value="99">Todos</option>
											<c:forEach items="${lista_fenomeno}" var="item">
											    <option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
								</div>	
								
								<div class="row">							
									<label class="col-sm-1 control-label">Estado:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_estado" name="sel_estado" class="form-control">
											<option value="99">Todos</option>
											<c:forEach items="${lista_estado}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<div class="col-sm-2 opc-center">
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
						<h2 id="h2_rel_programacion">Relación de Programaciones</h2>
						
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
							<a href="#" id="href_estados" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Cambiar Estado">
								<i class="fa fa-cog"></i>
							</a>
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_mnt_programacion" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th>Sel</th>
										<th>Nº</th>
										<th>Año</th>
										<th>Mes</th>
										<th>N° Programación</th>
										<th>Fecha Programación</th>
										<th>Descripción</th>
										<th>Fenómeno</th>										
										<th>N° DEE</th>
										<th>Estado</th>
										<th>Región Destino</th>
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
<div id="div_gra_estado" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" id="btn_clo_estado" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold">Estado de la Programación</h4>
			</div>
			<div style="max-height:550px;overflow:auto;">
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_gra_estado" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_programacion" name="hid_cod_programacion">
							
							<div class="form-group">																				
								<div class="col-sm-12">
									<input type="text" class="form-control" id="txt_programacion" disabled>
								</div>
							</div>
						
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Estado:</label>
								<div class="col-sm-8">
									<select id="sel_pro_estado" name="sel_pro_estado" class="form-control">
									</select>
								</div>
							</div>																		

							<div class="form-group">
								<label class="col-sm-3 control-label">Fecha:</label>
								<div class="col-sm-4 smart-form">
									<input type="text" id="txt_fecha" class="form-control" disabled>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Observación:</label>
								<div class="col-sm-9 smart-form">
									<section>														
										<label class="textarea textarea-resizable"> 										
											<textarea rows="3" name="txt_observacion" id="txt_observacion" 
												maxlength="500" class="custom-scroll"></textarea> 
										</label>
									</section>
								</div>				
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_estado">
					<i class="fa fa-floppy-o"></i>
					Grabar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" id="btn_can_estado" class="btn btn-default" data-dismiss="modal">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
			
			<div id="div_correos">
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_gra_correos" class="form-horizontal" role="form">
							
							<div class="form-group">																				
								<div class="col-sm-12">
									<input type="text" class="form-control" id="txt_correo" value="Enviar Correo Electrónico" disabled>
									<input type="hidden" id="hid_nro_programacion" name="hid_nro_programacion">
							
								</div>
							</div>
						
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Correo para:</label>
								<div class="col-sm-9">
									<select multiple id="sel_usuario_correo" name="sel_usuario_correo" class="form-control">
										<c:forEach items="${lista_usuario_correos}" var="item">
										    <option value="${item.idUsuario}_${item.email}_${item.usuario}_${item.nombreUsuario}">${item.nombreUsuario}</option>
										</c:forEach>
									</select>
								</div>
							</div>																		
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_correo">
					<i class="fa fa-envelope-o"></i>
					Enviar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" id="btn_can_estado" class="btn btn-default" data-dismiss="modal">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>

			</div>
			
			</div>
			</div>
			
			
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/listar_programacion.js"></script>
