<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Administración</li>
		<li>Consulta de Tablas</li>
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
						<h2>Búsqueda de Tablas Generales</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_bus_con_calidad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_con_donaciones" class="form-horizontal">
							
								<input type="hidden" id="hid_codigo" name="hid_codigo">

								<div class="form-group">
									<label class="col-sm-2 control-label">Seleccione Tabla:</label>
									<div class="col-sm-4">
										<select id="sel_tablas_generales" name="sel_tablas_generales" class="form-control">
											<option value="">Seleccione</option>
											<c:forEach items="${lista_tablas}" var="item">
											    <option value="${item.icodigo}*${item.descripcionCorta}*${item.vcodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-sm-2">
<!-- 										<button class="btn btn-primary" type="button" id="btn_buscar"> -->
<!-- 											<i class="fa fa-search"></i> -->
<!-- 											Buscar -->
<!-- 										</button> -->
									</div>
								</div>
								<div class="form-group" id="div_busqueda_alm_ext" style="display: none;">
									

									<div id="divRegiones">
														
										<div id="div_gob_destino_bus" class="row" >
											<label class="col-sm-3 control-label">Tipo de Atención:</label>
											
											<div class="col-sm-8 form-group">
												<label class="radio radio-inline">
													<input type="radio" name="rb_tie_ate_gobierno_bus" value="R">
													Gobierno Regional
												</label>
												
												<label class="radio radio-inline">
													<input type="radio" name="rb_tie_ate_gobierno_bus" value="L">
													Gobierno Local
												</label>																		
											</div>
										</div>
										
										<div id="div_gore_destino_bus" class="row" style="display: none;">
<!-- 											<label class="col-sm-3 control-label">GORE:</label> -->
<!-- 											<div class="col-sm-3 form-group"> -->
<!-- 												<select id="sel_gore_bus" name="sel_gore_bus" class="form-control"> -->
<!-- 													<option value="">Seleccione</option> -->
<%-- 													<c:forEach items="${lista_region}" var="item"> --%>
<%-- 														<option value="${item.descripcionCorta}">${item.descripcion}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 											</div> -->
										</div>
										
										<div id="div_ubi_destino_bus" class="row" style="display: none;">
											<label class="col-sm-2 control-label">Departamento:</label>
											<div class="col-sm-2 form-group">
												<select id="sel_departamento_bus" name="sel_departamento_bus" class="form-control">
													<option value="">Seleccione</option>
													<c:forEach items="${lista_departamento}" var="item">
														<option value="${item.coddpto}">${item.nombre}</option>
													</c:forEach>
												</select>
											</div>
											
											<label class="col-sm-2 control-label">Provincia:</label>
											<div class="col-sm-2 form-group">
												<select id="sel_provincia_bus" name="sel_provincia_bus" class="form-control">
												</select>
											</div>
											

										</div>
										
	
									</div>
									
									<div class="form-group">
									<div class="col-sm-10">
									</div>
									<div class="col-sm-2">
										<button class="btn btn-primary" type="button" id="btn_buscar_alm_ext">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
									</div>
									
								</div>
								
								<div class="form-group" id="div_busqueda_catalogo" style="display: none;">
									<div class="form-group">
									<label class="col-sm-2 control-label">Categoría del Producto:</label>
									<div class="col-sm-3" >
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
									<div class="col-sm-3">
										<input type="text" name="txt_nombre" id="txt_nombre" class="form-control" maxlength="50">
									</div>
			
									
									<div class="col-sm-1">
										<button class="btn btn-primary" type="button" id="btn_buscar_nom">
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
						<h2 id="titulo_tabla">Lista de Tabla</h2>
						<div class="jarviswidget-ctrls" role="menu">   
							
								<a href="#" id="href_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
									data-original-title="Exportar Excel">
									<i class="fa fa-file-excel-o"></i>
								</a>
<!-- 								<a href="#" id="href_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 									data-original-title="Nuevo"> -->
<!-- 									<i class="fa fa-file-o"></i> -->
<!-- 								</a> -->
<!-- 								<a href="#" id="href_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 									data-original-title="Editar"> -->
<!-- 									<i class="fa fa-edit"></i> -->
<!-- 								</a> -->
<!-- 								<a href="#" id="href_doc_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom"  -->
<!-- 									data-original-title="Eliminar"> -->
<!-- 									<i class="fa fa-trash-o"></i> -->
<!-- 								</a> -->
						
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">
							<div id="div_tbl_almacen" style="display: none;" class="opc-center">
								<table id="tbl_almacen" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID DDI</th>
											<th class="opc-center" >ID Almacén</th>
											<th class="opc-center">Código Almacén</th>
											<th class="opc-center">Nombre Almacén</th>
											<th class="opc-center">Dirección</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_almacen_externo" align="center" style="display: none;" class="opc-center">
								<table id="tbl_almacen_externo" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID Almacén</th>
											<th class="opc-center">ID Región</th>
											<th class="opc-center">Nombre almacén</th>
											<th class="opc-center">Código Almacén</th>
											<th class="opc-center">Dirección</th>
											<th class="opc-center">RUC</th>
											<th class="opc-center">Tipo Almacén</th>
											<th class="opc-center">Nombre Ubigeo</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_anio" style="display: none;" class="opc-center">
								<table id="tbl_anio" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">Código</th>
											<th class="opc-center">Descripción</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_categoria" style="display: none;" class="opc-center">
								<table id="tbl_categoria" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_chofer" style="display: none;" class="opc-center">
								<table id="tbl_chofer" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">DNI</th>
											<th class="opc-center">Brevete</th>
											<th class="opc-center">Nombre Empresa</th>
											<th class="opc-center">Id Empresa</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_ddi" style="display: none;" class="opc-center">
								<table id="tbl_ddi" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Código</th>
											<th class="opc-center">Nombre</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_distrito_inei" style="display: none;" class="opc-center">
								<table id="tbl_distrito_inei" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">Año</th>
											<th class="opc-center">Distrito</th>
											<th class="opc-center">Departamento</th>
											<th class="opc-center">Provincia</th>
											<th class="opc-center">Distrito</th>
											<th class="opc-center">Población</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_donante" style="display: none;" class="opc-center">
								<table id="tbl_donante" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Nro Documento</th>
											<th class="opc-center">Tipo</th>
											<th class="opc-center">Representante</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_empresa_transporte" style="display: none;" class="opc-center">
								<table id="tbl_empresa_transporte" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Dirección</th>
											<th class="opc-center">Representante</th>
											<th class="opc-center">RUC</th>
											<th class="opc-center">Tipo Transporte</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_envase" style="display: none;" class="opc-center">
								<table id="tbl_envase" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Descripción</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_estado" style="display: none;" class="opc-center">
								<table id="tbl_estado" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_estado_donacion" style="display: none;" class="opc-center">
								<table id="tbl_estado_donacion" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_estado_programacion" style="display: none;" class="opc-center">
								<table id="tbl_estado_programacion" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_medio_transporte" style="display: none;" class="opc-center">
								<table id="tbl_medio_transporte" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_mod_almacen" style="display: none;" class="opc-center">
								<table id="tbl_mod_almacen" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_moneda" style="display: none;" class="opc-center">
								<table id="tbl_moneda" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Código</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Estado</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_motivo_traslado" style="display: none;" class="opc-center">
								<table id="tbl_motivo_traslado" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Estado</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_oficina" style="display: none;" class="opc-center">
								<table id="tbl_oficina" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">DDI</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_pais" style="display: none;" class="opc-center">
								<table id="tbl_pais" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_parametro" style="display: none;" class="opc-center">
								<table id="tbl_parametro" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Tipo</th>
											<th class="opc-center">Valor</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_personal" style="display: none;" class="opc-center">
								<table id="tbl_personal" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">DDI</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Cargo</th>
											<th class="opc-center">Oficina</th>
											<th class="opc-center">Teléfono</th>
											<th class="opc-center">Estado</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_personal_externo" style="display: none;" class="opc-center">
								<table id="tbl_personal_externo" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Cargo</th>
											<th class="opc-center">Teléfono</th>
											<th class="opc-center">Región</th>
											<th class="opc-center">Provincia</th>
											<th class="opc-center">Distrito</th>
											<th class="opc-center">Estado</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_region" style="display: none;" class="opc-center">
								<table id="tbl_region" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Código</th>
											<th class="opc-center">Región</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_tip_camion" style="display: none;" class="opc-center">
								<table id="tbl_tip_camion" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Tonelaje</th>
											<th class="opc-center">Volumen</th>
											<th class="opc-center">Descripción</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_tip_control_calidad" style="display: none;" class="opc-center">
								<table id="tbl_tip_control_calidad" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_tip_documento" style="display: none;" class="opc-center">
								<table id="tbl_tip_documento" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Nombre Corto</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_tip_movimiento" style="display: none;" class="opc-center">
								<table id="tbl_tip_movimiento" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">Ingreso</th>
											<th class="opc-center">Salida</th>

										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_unidad_medida" style="display: none;" class="opc-center">
								<table id="tbl_unidad_medida" class="table table-bordered table-hover tbl-responsive">
									<thead>			                
										<tr>
											<th></th>
											<th class="opc-center">N°</th>
											<th class="opc-center">ID</th>
											<th class="opc-center">Nombre</th>
											<th class="opc-center">NombreCorto</th>
											<th class="opc-center">Estado</th>
										</tr>
									</thead>
								</table>
							</div>
							
							<div id="div_tbl_catalogo_producto" style="display: none;" class="opc-center">
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
<div id="div_almacen" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_almacen">Almacén</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_almacen" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_almacen" name="hid_id_almacen">
							<input type="hidden" id="hid_cod_almacen" name="hid_cod_almacen">
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Nombre Almacén:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_nom_alm" id="txt_nom_alm" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Dirección:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_dir_alm" id="txt_dir_alm" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Características:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_car_alm" id="txt_car_alm" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Nombre Secundario:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_sec_alm" id="txt_sec_alm" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Ddi:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_ddi_alm" name="sel_ddi_alm" class="form-control" disabled>
										<option value="">Seleccione</option>
										<c:forEach items="${lista_ddi}" var="item">
											<option value="${item.vcodigo}_${item.descripcionCorta}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Modalidad Almacén:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_mod_alm" name="sel_mod_alm" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_mod_almacen}" var="item">
											<option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Estado:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_est_alm" name="sel_est_alm" class="form-control">
										<option value="1">Activo</option>
										<option value="0">Inactivo</option>
									</select>
								</div>
							</div>
							
							
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_alm">
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

<!-- Modal -->
<div id="div_almacen_externo" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_almacen_externo">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_almacen_externo" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_almacen_externo" name="hid_id_almacen_externo">
							<input type="hidden" id="hid_cod_almacen_externo" name="hid_cod_almacen_externo">
							<input type="hidden" id="hid_nom_almacen_externo" name="hid_nom_almacen_externo">

							<div class="form-group">																				
								<label class="col-sm-3 control-label">Nombre Almacén:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_nom_almacen_ext" id="txt_nom_almacen_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Dirección:</label>
								<div class="col-sm-6 form-group">
									<input type="text" name="txt_direccion_ext" id="txt_direccion_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">RUC Ubigeo:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_ruc_ext" id="txt_ruc_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<div id="divRegiones">
													
									<div id="div_gob_destino" class="row">
										<label class="col-sm-3 control-label">Tipo de Atención:</label>
										
										<div class="col-sm-8 form-group">
											<label class="radio radio-inline">
												<input type="radio" name="rb_tie_ate_gobierno" value="R">
												Gobierno Regional
											</label>
											
											<label class="radio radio-inline">
												<input type="radio" name="rb_tie_ate_gobierno" value="L">
												Gobierno Local
											</label>																		
										</div>
									</div>
									
									<div id="div_gore_destino" class="row">
										<label class="col-sm-3 control-label">GORE:</label>
										<div class="col-sm-3 form-group">
											<select id="sel_gore" name="sel_gore" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_region}" var="item">
													<option value="${item.descripcionCorta}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div id="div_ubi_destino" class="row">
										<label class="col-sm-2 control-label">Departamento:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_departamento" name="sel_departamento" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_departamento}" var="item">
													<option value="${item.coddpto}">${item.nombre}</option>
												</c:forEach>
											</select>
										</div>
										
										<label class="col-sm-2 control-label">Provincia:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_provincia" name="sel_provincia" class="form-control">
											</select>
										</div>
										
										<label class="col-sm-2 control-label">Distrito:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_distrito" name="sel_distrito" class="form-control">
											</select>
										</div>
									</div>
									
									<div id="div_gore_destino" class="row">
										<label class="col-sm-3 control-label">Código:</label>
										<div class="col-sm-3 form-group">
											<input type="text" name="txt_cod_ext" id="txt_cod_ext" class="form-control" disabled>
										</div>
									</div>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Estado:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_est_alm_ext" name="sel_est_alm_ext" class="form-control form-group">
										<option value="1">Activo</option>
										<option value="0">Inactivo</option>
									</select>
								</div>
							</div>
							
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_alm_ext">
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

<!-- Modal -->
<div id="div_anio" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_anio">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_anio" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_anio" name="hid_id_anio">
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Código Año:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_cod_anio" id="txt_cod_anio" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Descripción:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_des_anio" id="txt_des_anio" class="form-control">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_anio">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_ddi" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_ddi">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_ddi" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_ddi" name="hid_id_ddi">
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Código Ddi:</label>
								<div class="col-sm-3 form-group">
									<input type="text" name="txt_cod_ddi" id="txt_cod_ddi" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-3 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_ddi" id="txt_nom_ddi" class="form-control">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_ddi">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_donante" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_donante">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_donante" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_donante" name="hid_id_donante">
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_nom_donante" id="txt_nom_donante" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">N° Documento:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_doc_donante" id="txt_doc_donante" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Tipo:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_tip_donante" name="sel_tip_donante" class="form-control">
										<option value="">Seleccione</option>
										<option value="1">Persona Natural</option>
										<option value="2">Persona Jurídica</option>
									</select>
								</div>
							</div>	
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Representante:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_rep_donante" id="txt_rep_donante" class="form-control">
								</div>
							</div>
							

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_donante">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_empresa_transporte" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_empresa_transporte">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_empresa_transporte" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_empresa_transporte" name="hid_id_empresa_transporte">
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_emp" id="txt_nom_emp" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Dirección:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_dir_emp" id="txt_dir_emp" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Representante:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_rep_emp" id="txt_rep_emp" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Ruc:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_ruc_emp" id="txt_ruc_emp" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Teléfono:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_tel_emp" id="txt_tel_emp" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Medio Transporte:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_med_emp" name="sel_med_emp" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_medio_transporte}" var="item">
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
				<button type="button" class="btn btn-primary" id="btn_gra_emp">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_envase" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_envase">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_envase" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_envase" name="hid_id_envase">
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_env" id="txt_nom_env" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre Corto:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_des_env" id="txt_des_env" class="form-control">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_env">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_mod_almacen" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_mod_almacen">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_mod_almacen" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_mod_almacen" name="hid_id_mod_almacen">
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_mod_alm" id="txt_nom_mod_alm" class="form-control">
								</div>
							</div>
							
							

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_mod_alm">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_oficina" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_oficina">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_oficina" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_oficina" name="hid_id_oficina">
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_ofi" id="txt_nom_ofi" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Ddi:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_ddi_ofi" name="sel_ddi_ofi" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_ddi}" var="item">
											<option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_ofi">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_personal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_anio" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_personal">Año</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_personal" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_personal" name="hid_id_personal">
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombres:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_per" id="txt_nom_per" class="form-control">
								</div>
							</div>
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Apellidos:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_ape_per" id="txt_ape_per" class="form-control">
								</div>
							</div>
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Cargo:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_car_per" id="txt_car_per" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Oficina:</label>
								<div class="col-sm-8 form-group">
									<select id="sel_ofi_per" name="sel_ofi_per" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_oficinas_ddi}" var="item">
											<option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Teléfono:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_tel_per" id="txt_tel_per" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Estado:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_est_per" name="sel_est_per" class="form-control">
										<option value="1">Activo</option>
										<option value="0">Inactivo</option>
									</select>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_per">
					<i class="fa fa-floppy-o"></i>
					Aceptar
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_anio">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_personal_externo" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_personal_externo">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_personal_externo" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_personal_externo" name="hid_id_personal_externo">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_per_ext" id="txt_nom_per_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Cargo:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_car_per_ext" id="txt_car_per_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Teléfono:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_tel_per_ext" id="txt_tel_per_ext" class="form-control">
								</div>
							</div>
							
							<div class="form-group">
								<div id="divRegiones_per_ext">
													
									<div id="div_gob_destino_per_ext" class="row">
										<label class="col-sm-3 control-label">Tipo de Atención:</label>
										
										<div class="col-sm-8 form-group">
											<label class="radio radio-inline">
												<input type="radio" name="rb_tie_ate_gobierno_per_ext" value="R">
												Gobierno Regional
											</label>
											
											<label class="radio radio-inline">
												<input type="radio" name="rb_tie_ate_gobierno_per_ext" value="L">
												Gobierno Local
											</label>																		
										</div>
									</div>
									
									<div id="div_gore_destino_per_ext" class="row">
										<label class="col-sm-3 control-label">GORE:</label>
										<div class="col-sm-3 form-group">
											<select id="sel_gore_per_ext" name="sel_gore_per_ext" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_region}" var="item">
													<option value="${item.descripcionCorta}">${item.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div id="div_ubi_destino_per_ext" class="row">
										<label class="col-sm-2 control-label">Departamento:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_departamento_per_ext" name="sel_departamento_per_ext" class="form-control">
												<option value="">Seleccione</option>
												<c:forEach items="${lista_departamento}" var="item">
													<option value="${item.coddpto}">${item.nombre}</option>
												</c:forEach>
											</select>
										</div>
										
										<label class="col-sm-2 control-label">Provincia:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_provincia_per_ext" name="sel_provincia_per_ext" class="form-control">
												<option value="">Seleccione</option>
											</select>
										</div>
										
										<label class="col-sm-2 control-label">Distrito:</label>
										<div class="col-sm-2 form-group">
											<select id="sel_distrito_per_ext" name="sel_distrito_per_ext" class="form-control">
												<option value="">Seleccione</option>
											</select>
										</div>
									</div>

								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">Estado:</label>
								<div class="col-sm-4 form-group">
									<select id="sel_est_per_ext" name="sel_est_per_ext" class="form-control">
										<option value="1">Activo</option>
										<option value="0">Inactivo</option>
									</select>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_per_ext">
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

<!-- Modal -->
<div id="div_tipo_camion" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_tipo_camion">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_tipo_camion" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_tipo_camion" name="hid_id_tipo_camion">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Descripción:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_des_tip_cam" id="txt_des_tip_cam" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Tonelaje:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_ton_tip_cam" id="txt_ton_tip_cam" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Volumen:</label>
								<div class="col-sm-4 form-group">
									<input type="text" name="txt_vol_tip_cam" id="txt_vol_tip_cam" class="form-control">
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_tip_cam">
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

<!-- Modal -->
<div id="div_tipo_control_calidad" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_tipo_control_calidad">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_tipo_control_calidad" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_tipo_control_calidad" name="hid_id_tipo_control_calidad">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_tip_con" id="txt_nom_tip_con" class="form-control">
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_tip_con">
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

<!-- Modal -->
<div id="div_tipo_documento" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_tipo_documento">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_tipo_documento" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_tipo_documento" name="hid_id_tipo_documento">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_tip_doc" id="txt_nom_tip_doc" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre Corto:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_cor_tip_doc" id="txt_cor_tip_doc" class="form-control">
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_tip_doc">
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

<!-- Modal -->
<div id="div_unidad_medida" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_unidad_medida">Nuevo Almacén Externo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_unidad_medida" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_id_unidad_medida" name="hid_id_unidad_medida">

							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_nom_uni_med" id="txt_nom_uni_med" class="form-control">
								</div>
							</div>
							
							<div class="form-group">																				
								<label class="col-sm-2 control-label">Nombre Corto:</label>
								<div class="col-sm-8 form-group">
									<input type="text" name="txt_cor_uni_med" id="txt_cor_uni_med" class="form-control">
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_uni_med">
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

<script src="${pageContext.request.contextPath}/resources/js/administracion/listar_tablas.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/administracion/validacion_tablas.js"></script>
