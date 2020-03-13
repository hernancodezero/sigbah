<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación</li>
		<li>Decreto Estado Emergencia</li>
		<li>Registro</li>
	</ol>
	<!-- end breadcrumb -->
</div>
<!-- END RIBBON -->
    
<!-- MAIN CONTENT -->
<div id="content">
	
	<!-- widget grid -->
	<section id="widget-grid" class="">
	
		<!-- row -->
		<div class="row">
		
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
			
				<!-- Widget ID (each widget will need unique ID)-->
				<div>

					<!-- widget div-->
					<div>
	
						<!-- widget content -->
						<div class="widget-body">
	
							<ul id="ul_man_dee" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Datos Generales</a>
								</li>
								<li id="li_ubigeo">
									<a href="#div_ubigeo" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Ubigeo</a>
								</li> 
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_dee" name="hid_cod_dee">
										<input type="hidden" id="hid_cod_act_alfresco" name="hid_cod_act_alfresco">
										<input type="hidden" id="hid_cod_ind_alfresco" name="hid_cod_ind_alfresco">
												<!-- widget content -->
												<div class="widget-body">
												<div class="form-group"></div>
													<div class="row">
<!-- 														<label class="col-sm-4 control-label"></label> -->
														<label class="col-sm-1 control-label">N° DEE:</label>
														<div class="col-sm-2 form-group">
																<input type="text" id="txt_num_dee" name="txt_num_dee"  class="form-control" >
														</div>
														<div class="col-sm-1 form-group"></div>
														<div class="col-sm-8 form-group">
																<input type="text" id="txt_des_dee" name="txt_des_dee"  class="form-control" >
														</div>
													</div>	
													
													<div class="row">
														<label class="col-sm-1 control-label">Fecha Inicio:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha_ini" id="txt_fecha_ini" class="datepicker" >
															</label>
														</div>
														<label class="col-sm-1 control-label">Fecha Fin:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha_fin" id="txt_fecha_fin" class="datepicker" >
															</label>
														</div>
														
														<label class="col-sm-1 control-label">N° Días:</label>
														<div class="col-sm-2 form-group">
																<input type="text" id="txt_num_dias" name="txt_num_dias"   class="form-control only-numbers-format">
														</div>
														
														<label class="col-sm-1 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																	<c:forEach items="${lista_estado}" var="item">
																	    <option value="${item.icodigo}">${item.descripcion}</option>
																	</c:forEach>
															</select>
														</div>
													</div>	
													<div class="row">
														<label class="col-sm-1 control-label">Motivo:</label>
														<div class="col-sm-11 form-group">
																<input type="text" id="txt_motivo" name="txt_motivo"  class="form-control" >
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">Tiene prorroga?:</label>
														<div class="col-sm-2 form-group">
															<label class="radio radio-inline">
																<input type="radio" name="rb_prorroga" value="1">
																Si
															</label>
															
															<label class="radio radio-inline">
																<input type="radio" name="rb_prorroga" value="2">
																No 
															</label>																											
														</div>
													</div>
													<div class="row">
														<label class="col-sm-2 control-label">Observaciones:</label>
														<div class="col-sm-10 form-group">
																<input type="text" id="txt_observacion" name="txt_observacion"  class="form-control" >
														</div>
													</div>
													<div class="row">
													  	<label class="col-sm-2 control-label">Documento de la DEE:</label>
														<div class="col-sm-4 smart-form">
															<div class="input input-file">
																<span id="sp_sub_archivo" class="button">
																	<input type="file" id="fil_sub_archivo" name="fil_sub_archivo">
																	Cargar
																</span>
																<input type="text" id="txt_lee_sub_archivo" name="txt_lee_sub_archivo" readonly>
															</div>
														</div>
														<div class="col-sm-1">
															<a href="#" id="href_eli_archivo" class="btn btn-default txt-color-red" rel="tooltip" 
																data-placement="right" data-original-title="Eliminar Archivo" data-html="true">
																<i class="fa fa-eraser fa-lg"></i>
															</a>
														</div>	
													</div>
													
											</div>
												<!-- end widget content -->
								
<!-- 											</div> -->
											<!-- end widget div -->
								
										
										<div class="form-actions">
											<div class="row">
												<div class="col-md-12 opc-center">
													<button class="btn btn-primary" type="button" id="btn_grabar_dat_gen">
														<i class="fa fa-floppy-o"></i>
														Grabar
													</button>
													
													&nbsp; &nbsp;
													
													<button class="btn btn-default btn_retornar" type="button">
														<i class="fa fa-mail-forward"></i>
														Retornar
													</button>
												</div>
											</div>
										</div>				
												
									</form>
										
								</div>
								
								<div class="tab-pane fade" id="div_ubigeo">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
										<form id="frm_ss" class="form-horizontal">
											<div class="form-group">
													<label class="col-sm-1 control-label">N° DEE:</label>
													<div class="col-sm-2 form-group">
															<input type="text" id="txt_num_dee2" name="txt_num_dee2"  class="form-control" >
													</div>
													<div class="col-sm-1 form-group"></div>
													<div class="col-sm-8 form-group">
															<input type="text" id="txt_des_dee2" name="txt_des_dee2"  class="form-control" >
													</div>
											</div>	
										</form>
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Distritos Declarados en Emergencia</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_ubi_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Exportar Excel">
													<i class="fa fa-file-excel-o"></i>
												</a> 
												<a href="#" id="href_ubi_nuevo" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Nuevo">
													<i class="fa fa-file-o"></i>
												</a>
												<a href="#" id="href_ubi_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
											</div>
										</header>
										<!-- widget content -->
											<div class="table-scroll">

												<table id="tbl_mnt_distritos" class="table table-bordered table-hover tbl-responsive">
													<thead>			                
														<tr>
															<th></th>
															<th>Nº</th>
															<th>Ubigeo</th>
															<th>Departamento</th>
															<th>Provincia</th>
															<th>Distrito</th>
														</tr>
													</thead>
												</table>

											</div>
											<!-- end widget content -->
									</div>
									<!-- end widget -->
									
									<div class="modal-footer">
										<button type="button" class="btn btn-default btn_retornar" data-dismiss="modal" id="btn_can_afectados">
											<i class="fa fa-mail-forward"></i>
											Retornar
										</button>
									</div>
									
								</div>
								
							</div>
	
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

<!-- Modal  Agregar Ubigeo-->
<div id="div_det_ubigeo" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Seleccionar Ubigeo (Distritos)</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							<input type="hidden" id="hid_cod_ubigeo" name="hid_cod_ubigeo">
							<div class="row">
								<label class="col-sm-2 control-label">Departamento:</label>
								<div class="col-sm-2">
									<select id="sel_departamento_ubi" name="sel_departamento_ubi" class="form-control">
										<option value="">Seleccione</option>
										<c:forEach items="${lista_departamento}" var="item">
										    <option value="${item.coddpto}">${item.nombre}</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-1 control-label">Provincia:</label>	
								<div class="col-sm-2 ">
									<select id="sel_provincia_ubi" name="sel_provincia_ubi" class="form-control">
										<option value="">Seleccione</option>
									</select>
								</div>																		
								
								<div class="col-sm-2">
									<button class="btn btn-primary" type="button" id="btn_aceptar_ubigeo">
										<i class="fa fa-search"></i>
										Aceptar
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>	
				
			<div class="row">&nbsp;</div>	
			
			<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
				<div class="jarviswidget jarviswidget-color-blueLight"><!-- Widget ID (each widget will need unique ID)-->
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Distritos de la provincia </h2> <input type="text" id="txt_provincia" name="txt_provincia"  class="form-control"  disabled>
					</header>
	
					<div><!-- widget div-->
						<div class="widget-body " ><!-- widget content -->
							<table id="tbl_mnt_ubigeo" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th><input type="checkbox" id ="mainChkBox"/></th>
										<th>Nº</th>
										<th>Ubigeo</th>
										<th>Provincia</th>
										<th>Distrito</th>
									</tr>
								</thead>
							</table>
						</div><!-- end widget content -->
					</div><!-- end widget div -->
				</div><!-- end widget -->
			</article>
			<!-- WIDGET END -->
			</div>
			
			<div class="modal-footer">
				<div class="form-group">
					<label class="col-sm-3 control-label label-bold">Total seleccionado:</label>
					<div class="col-sm-2">
						<input type="text" id="txt_nro_selec_ubi" class="form-control" disabled>
					</div>
				</div>		
				<button type="button" class="btn btn-primary" id="btn_pasar_distrito_ubigeo">
					<i class="fa fa-floppy-o"></i>
					Pasar Distritos Seleccionados a la DEE
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_distrito_ubigeo">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- inline scripts related to this page -->
<script> var dee = JSON.parse('${dee}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/mantenimiento_dee.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/validacion_mantenimiento_dee.js"></script>
