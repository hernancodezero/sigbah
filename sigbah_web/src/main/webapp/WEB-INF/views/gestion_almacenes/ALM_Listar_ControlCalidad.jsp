<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gesti�n de Almacenes</li>
		<li>Control de Calidad</li>
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
						<h2>B�squeda de Controles de Calidad</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_con_calidad" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
								<input type="hidden" id="hid_anioActivo" name="hid_anioActivo" value="${anioActivo}">
								<input type="hidden" id="hid_mesActivo" name="hid_mesActivo" value="${mesActivo}">
							
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
									<div class="col-sm-2">
										<select id="sel_mes" name="sel_mes" class="form-control">
											<option value="%">Todos</option>
											<c:forEach items="${lista_mes}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Almac�n:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_almacen" name="sel_almacen" class="form-control">
											<c:forEach items="${lista_almacen}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Tipo de Control:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_tip_control" name="sel_tip_control" class="form-control">
											<option value="0">Todos</option>
											<c:forEach items="${lista_tipo_control}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<div class="col-sm-2 opc-right">
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
						<h2>Relaci�n de Controles de Calidad</h2>
						
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

							<table id="tbl_mnt_con_calidad" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>N�</th>
										<th class="opc-center">A�o</th>
										<th class="opc-center">Mes</th>
										<th id="th_ddi">DDI</th>
										<th id="th_almacen">Almac�n</th>
										<th class="opc-center">N� Reporte de Control de Calidad</th>
										<th class="opc-center">Fecha</th>
										<th id="th_tip_control">Tipo de Control</th>
										<th class="opc-center">Estado</th>
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

<!-- inline scripts related to this page -->
<script> var controlCalidad = JSON.parse('${controlCalidad}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/listar_control_calidad.js"></script>
