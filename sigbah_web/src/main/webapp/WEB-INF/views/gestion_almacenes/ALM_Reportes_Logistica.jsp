<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gestión de Almacenes</li>
		<li>Reportes</li>
		<li>Kardex y Bincard</li>
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
						<h2>Filtros de Reporte</h2>
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
									
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-2 form-group" style="display:none">
										<select id="sel_mes_fin" name="sel_mes_fin" class="form-control">
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
								</div>
									
								<div class="row">
									<label class="col-sm-1 control-label"></label>
									<div class="col-sm-1 form-group" style="display:none">
										<select id="sel_tip_movimiento" name="sel_tip_movimiento" class="form-control">
										</select>
									</div>
																		
									<div class="col-sm-1 form-group opc-center" style="display:none">
										<label class="checkbox">
											<input type="checkbox" name="chk_inc_producto" id="chk_inc_producto"><i></i>
											Incluir Productos
										</label>
									</div>
									</div>
									
									<div class="row">
										<label class="col-sm-2 control-label">Producto:</label>
										<div class="col-sm-6 form-group">
										<select id="sel_producto" name="sel_producto" class="form-control">
											<c:forEach items="${lista_producto}" var="item">
											    <option value="${item.idProducto}_${item.nroKardex}">${item.nombreProducto}</option>
											</c:forEach>
										</select>
										</div>
									
								</div>
								
								<div class="row">
									<label class="col-sm-2 control-label">Kardex N°:</label>
									<div class="col-sm-2 form-group">
										<input type="text" id="txt_nro_kardex" class="form-control" disabled>
									</div>
																		
									<label class="col-sm-2 control-label">Bincard N°:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_nro_bincard" name="sel_nro_bincard" class="form-control">
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-10">
									</div>
									<div class="col-sm-2">
											<button class="btn btn-primary" type="button" id="btn_buscar">
												<i class="fa fa-search"></i>
												Buscar
											</button>
									</div>
								</div>			
<!-- 								<button class="btn btn-success" type="button" id="btn_exp_pdf"> -->
<!-- 												<i class="fa fa-file-pdf-o"></i> -->
<!-- 												Exportar PDF -->
<!-- 											</button>		 -->
											
<!-- 											<button class="btn btn-success" type="button" id="btn_exp_excel"> -->
<!-- 												<i class="fa fa-file-excel-o"></i> -->
<!-- 												Exportar Excel -->
<!-- 											</button>			 -->
								
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
						<h2>Kardex</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="btn_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
							<a href="#" id="btn_exp_pdf" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Imprimir">
								<i class="fa fa-file-pdf-o"></i>
							</a>

						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_repo_kardex" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th>Item</th>
										<th>Fecha</th>
										<th>Documento</th>
										<th>Ingresos</th>
										<th>Salidas</th>
										<th>Saldo</th>
										<th>Precio Compra(S/.)</th>
										<th>Precio Promedio(S/.)</th>
										<th>Importe Valorado(S/.)</th>
										<th>Motivo</th>
										<th>N° Orden</th>
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
		
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
		
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget" style="display:none">
					<header>
						<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
						<h2>Tipos de Reporte</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_tip_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_tip_reporte" class="smart-form">
							
								<div class="row">
									<div class="col col-2"></div>
									
									<div class="col col-4 form-group">
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
									
									<div class="col col-2">
										<footer class="footer-white">
											<button class="btn btn-success" type="button" id="btn_exp_pdf">
												<i class="fa fa-file-pdf-o"></i>
												Exportar PDF
											</button>
										</footer>
										<footer class="footer-white">
											<button class="btn btn-success" type="button" id="btn_exp_excel">
												<i class="fa fa-file-excel-o"></i>
												Exportar Excel
											</button>
										</footer>										
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
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/listar_reportes_almacen.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/validacion_reportes_almacen.js"></script>