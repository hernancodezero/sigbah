<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Catálogo de Productos</li>
		<li>Lista de Productos</li>
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
		
							<form id="frm_catalogo_productos" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">

								<div class="form-group">
									<label class="col-sm-2 control-label">Categoría del Producto:</label>
									<div class="col-sm-3">
										<select id="sel_catalogo_producto" name="sel_catalogo_producto" class="form-control">
											<option value="0">Todos</option>
											<c:forEach items="${lista_categoria}" var="item">
											    <option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<div class="col-sm-1">
										<button class="btn btn-primary" type="button" id="btn_buscar_cat">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
									
									<label class="col-sm-1 control-label">Producto:</label>
									<div class="col-sm-3 form-group">
										<input type="text" name="txt_nombre" id="txt_nombre" class="form-control" maxlength="50">
									</div>
			
									
									<div class="col-sm-1">
										<button class="btn btn-primary" type="button" id="btn_buscar_nom">
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
						<h2>Lista de Catálogo de Productos</h2>
						
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
							<a href="#" id="href_nuevo_sig" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Nuevo SIGA">
								<i class="fa fa-files-o"></i>
							</a>
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_catalogo_producto" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>N°</th>
										<th>Categoría</th>
										<th>ID</th>
										<th>Código SIGBAH</th>
										<th>Código SIGA</th>
										<th>Producto</th>
										<th>Unidad de Medida</th>
										<th>Tipo Envase</th>
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

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath}/resources/js/administracion/listar_catalogo_productos.js"></script>
