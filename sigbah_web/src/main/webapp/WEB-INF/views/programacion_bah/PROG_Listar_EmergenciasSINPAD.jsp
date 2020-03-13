<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación de BAH</li>
		<li>Emergencias en SINPAD</li>
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
						<h2>Búsqueda de Emergencias en SINPAD</h2>
					</header>
		
					<!-- widget div-->
					<div id="div_wid_bod_emer_sinpad">
		
						<!-- widget content -->
						<div class="widget-body widget-body-50">
		
							<form id="frm_emer_sinpad" class="form-horizontal">
							
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
									
									<label class="col-sm-1 control-label">Región:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_region" name="sel_region" class="form-control">
										   <option value="">Seleccione</option>
											<c:forEach items="${lista_region}" var="item">
											    <option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
								</div>
								<div class="row">
								
									<label class="col-sm-1 control-label">Fenómeno:</label>
									<div class="col-sm-2 form-group">
										<select id="sel_fenomeno" name="sel_fenomeno" class="form-control">
											<option value="0">Todos</option>
											<c:forEach items="${lista_fenomeno}" var="item">
											    <option value="${item.icodigo}">${item.descripcion}</option>
											</c:forEach>
										</select>
									</div>
									
									<div class="col-sm-1 opc-center">
										<button class="btn btn-primary" type="button" id="btn_aceptar">
											<i class="fa fa-search"></i>
											Buscar
										</button>
									</div>
								</div>
								
							</form>
		
<!-- 							<form id="frm_emer_sinpad2" class="form-horizontal"> -->
							
<!-- 								<div class="row"> -->
<!-- 									<label class="col-sm-1 control-label"></label> -->
<!-- 									<div class="col-sm-2  form-group" > -->
<!-- 										<input type="text" id="txt_caja_busqueda" class="form-control"> -->
<!-- 									</div> -->
<!-- 									<div class="col-sm-2 opc-center"> -->
<!-- 										<button class="btn btn-primary" type="button" id="btn_buscar"> -->
<!-- 											<i class="fa fa-search"></i> -->
<!-- 											Buscar -->
<!-- 										</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
<!-- 							</form> -->
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
						<h2>Emergencias de la región</h2>
						
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
								data-original-title="Ver">
								<i class="fa fa-search"></i>
							</a>
							
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body">

							<table id="tbl_mnt_emer_sinpad" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Año</th>
										<th>Mes</th>
										<th>Fecha</th>
										<th>Código SINPAD</th>
										<th>Fenómeno</th>
										<th>Emergencia</th>
										<th>Región</th>
										<th>Provincia</th>
										<th>Distrito</th>
<!-- 										<th>Población INEI</th> -->
										<th>Fam. Afect.</th>
										<th>Fam. Dam</th>
										<th>Total Fam.</th>
										<th>Pers. Afect.</th>
										<th>Pers. Dam.</th>
										<th>Total Pers.</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<td colspan="11">
											<span class="label-bold" style="float:right;">Total:</span>
										</td>
										<td colspan="1"><span id="sp_tot_fam_afec"></span></td>
										<td colspan="1"><span id="sp_tot_fam_dam"></span></td>
										<td colspan="1"><span id="sp_tot_fam"></span></td>
										<td colspan="1"><span id="sp_tot_per_afec"></span></td>
										<td colspan="1"><span id="sp_tot_per_dam"></span></td>
										<td colspan="1"><span id="sp_tot_per"></span></td>
									</tr>
								</tfoot>
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
<script> var emergencia = JSON.parse('${emergencia}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/listar_emergencias_sinpad.js"></script>
