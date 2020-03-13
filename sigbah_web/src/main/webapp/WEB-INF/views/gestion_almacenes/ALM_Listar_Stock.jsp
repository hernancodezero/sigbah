<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Gestión de Almacenes</li>
		<li>Stock</li>
		<li>Lista</li>
	</ol>
	<!-- end breadcrumb -->
	<div class="opc-right">
		<ol class="breadcrumb">
			<li id="mes_activo">Mes y año de trabajo activo: ${mes_anio}</li>
		</ol>	
	</div>
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
						<h2>Búsqueda de Stock de Productos de Almacén</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_sto_almacen" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">
							
								<div class="row">

									<label class="col-sm-1 control-label">Almacén:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_almacen" name="sel_almacen" class="form-control">
											<c:forEach items="${lista_almacen}" var="item">
											    <option value="${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
					
									<label class="col-sm-1 control-label">Categoría Producto:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_cat_producto" name="sel_cat_producto" class="form-control">
											<option value="">Todos</option>
											<c:forEach items="${lista_categoria}" var="item">
											    <option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<label class="col-sm-1 control-label">Producto:</label>
									<div class="col-sm-2 form-group">
										<input type="text" id="txt_producto" name="txt_producto" class="form-control">
									</div>
									
									<label class="col-sm-1 control-label">Stock:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_stock" name="sel_stock" class="form-control">
											<option value="0">Todos</option>
											<option value="1">Mayor a Cero</option>
											<option value="2">Igual a Cero</option>
											<option value="3">Negativo</option>
											
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
						<h2>Relación de Stock de Productos de Almacén</h2>
						
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
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_mnt_sto_almacen" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Categoría</th>
										<th>Código</th>
										<th>Producto</th>
										<th>Unidad de Medida</th>
										<th>Tipo Envase</th>
										<th>Precio Unitario Promedio</th>
										<th>Stock</th>
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
<script src="${pageContext.request.contextPath}/resources/js/gestion_almacenes/listar_stock_almacen.js"></script>
