<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Donaciones</li>
		<li>Reportes</li>
		<li>Órdenes de Salida</li>
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
						<h2>Filtros del Reporte</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_rep_almacen" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
							
								<div class="row">
									<label class="col-sm-2 control-label">Año:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_anio" name="sel_anio" class="form-control">
											<c:forEach items="${lista_anio}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Mes Inicio:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_mes_inicio" name="sel_mes_inicio" class="form-control">
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-2 control-label">Mes Fin:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_mes_fin" name="sel_mes_fin" class="form-control">
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
								</div>
									
								<div class="row">
									<label class="col-sm-2 control-label">Tipo de Movimiento:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_tip_movimiento" name="sel_tip_movimiento" class="form-control">
										</select>
									</div>
																		
									<div class="col-sm-3 form-group opc-center">
										<label class="checkbox">
											<input type="checkbox" name="chk_inc_producto" id="chk_inc_producto"><i></i>
											Incluir Productos
										</label>
									</div>
									
									<div class="col-sm-5 form-group">
										<select id="sel_producto" name="sel_producto" class="form-control">
											<option value="0">Todos</option>
											<c:forEach items="${lista_producto}" var="item">
											    <option value="${item.idProducto}_${item.nombreProducto}">${item.nombreProducto}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="row" style="display:none">
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-2 form-group">
										<input type="text" id="txt_nro_kardex" class="form-control" disabled>
									</div>
																		
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-2 form-group">
										<select id="sel_nro_bincard" name="sel_nro_bincard" class="form-control">
										</select>
									</div>
								</div>			
								
								<button class="btn btn-success" type="button" id="btn_exp_pdf">
												<i class="fa fa-file-pdf-o"></i>
												Exportar PDF
											</button>
											
											<button class="btn btn-success" type="button" id="btn_exp_excel">
												<i class="fa fa-file-excel-o"></i>
												Exportar Excel
								</button>					
								
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
				<div class="jarviswidget" style="display:none">
					<header>
						<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
						<h2>Exportar</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_tip_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_tip_reporte" class="smart-form">
							
								<div class="row">
									<div class="col col-2"></div>
									
									<div class="col col-4 form-group" style="display:none">
										<label class="radio">
											<input type="radio" name="rb_tip_reporte" value="1"><i></i>
											1.- Reporte de Proyectos de Manifiesto
										</label>
										
										<label class="radio">
											<input type="radio" name="rb_tip_reporte" value="2"><i></i>
											2.- Reporte de Ordenes de Salida
										</label>
										
										<label class="radio">
											<input type="radio" name="rb_tip_reporte" value="3"><i></i>
											3.- Reporte de Ordenes de Ingreso
										</label>
										
										<label class="radio">
											<input type="radio" name="rb_tip_reporte" value="4"><i></i>
											4.- Reporte de Guias de Remision
										</label>
										
										<label class="radio">
											<input type="radio" name="rb_tip_reporte" value="5"><i></i>
											5.- Reporte de Kardex y Bincard
										</label>
									</div>
									
									<div class="row">
									
									
									<button class="btn btn-primary" type="button" id="btn_grabar">
														<i class="fa fa-floppy-o"></i>
														Grabar
													</button>
										
											<button class="btn btn-success" type="button" id="btn_exp_pdf">
												<i class="fa fa-file-pdf-o"></i>
												Exportar PDF
											</button>
											
											<button class="btn btn-success" type="button" id="btn_exp_excel">
												<i class="fa fa-file-excel-o"></i>
												Exportar Excel
											</button>
				
									</div>
										
									
									<div class="col col-4"></div>
								</div>								
								
							</form>
		
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

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath}/resources/js/donaciones/listar_reportes_salida.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/donaciones/validacion_reportes_donacion.js"></script>