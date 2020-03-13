<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- RIBBON -->
<div id="ribbon">
	<!-- breadcrumb -->
	<ol class="breadcrumb">
		<li>Programación</li>
		<li>Requerimiento</li>
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
				
					<!-- widget div-->
					
						<!-- widget content -->
						<div class="widget-body">
	
							<ul id="ul_man_con_calidad" class="nav nav-tabs bordered">
								<li id="li_dat_generales" class="active">
									<a href="#div_dat_generales" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Datos Generales</a>
								</li>
								<li id="li_damnificados">
									<a href="#div_damnificados" data-toggle="tab"><i class="fa fa-fw fa-lg fa-arrow-circle-o-down "></i> 
									Damnificados</a>
								</li>
							</ul>
	
							<div id="div_tab_content" class="tab-content padding-10">
								<div class="tab-pane fade in active" id="div_dat_generales">
								
									<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_requerimiento" name="hid_cod_requerimiento">
																				
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
												<h2>Requerimientos BAH</h2>
											</header>
								
											<!-- widget div-->
											<div>
								
												<!-- widget content -->
												<div class="widget-body">
								
													<div class="row">
														<label class="col-sm-2 control-label">Año:</label>
														<div class="col-sm-2 form-group">
																<input type="text" id="txt_anio" class="form-control" disabled>
														</div>
														
														<div class="col-sm-1"></div>
														<label class="col-sm-2 control-label">Estado:</label>
														<div class="col-sm-2 form-group">
															<select id="sel_estado" name="sel_estado" class="form-control">
																	<option value="">Seleccione</option>
																	<c:forEach items="${lista_estado}" var="item">
																	    <option value="${item.icodigo}">${item.descripcion}</option>
																	</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Requerimiento Nro.:</label>
														<div class="col-sm-2 form-group">
																<input type="text" id="txt_num_requerimiento" class="form-control" >
														</div>
														<div class="col-sm-1"></div>
														<label class="col-sm-2 control-label">Descripción:</label>
														<div class="col-sm-5 form-group">
																<input type="text" id="txt_descripcion" name="txt_descripcion" class="form-control" >
														</div>
														
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Fecha de requerimiento:</label>
														<div class="col-sm-2 smart-form form-group">
															<label class="input"> 
																<i class="icon-append fa fa-calendar"></i>
																<input type="text" name="txt_fecha_requerimiento" id="txt_fecha_requerimiento" class="datepicker" >
															</label>
														</div>
														<div class="col-sm-1"></div>
														<label class="col-sm-2 control-label">Región destino:</label>
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
														<label class="col-sm-3 control-label">El requerimiento se hace con SINPAD?:</label>
														<div class="col-sm-2 form-group">
															<label class="radio radio-inline">
																<input type="radio" name="rb_req_sinpad" value="1">
																Si
															</label>
															
															<label class="radio radio-inline">
																<input type="radio" name="rb_req_sinpad" value="2">
																No 
															</label>																											
														</div>
												
														<label class="col-sm-2 control-label">Fenómeno:</label>
														<div class="col-sm-5 form-group">
															<select id="sel_fenomeno" name="sel_fenomeno" class="form-control">
																<option value="">Seleccione</option>
																<c:forEach items="${lista_fenomeno}" var="item">
																    <option value="${item.icodigo}">${item.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													
													<div class="row">
														<label class="col-sm-2 control-label">Observaciones:</label>
														<div class="col-sm-6 smart-form form-group">
															<label class="textarea textarea-resizable"> 										
																	<textarea rows="3" name="txt_observaciones" id="txt_observaciones" 
																		maxlength="500" class="custom-scroll"></textarea> 
																</label>
														</div>
													</div>
												</div>
												<!-- end widget content -->
								
											</div>
											<!-- end widget div -->
								
										</div>
										<!-- end widget -->
										
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
								
								<div class="tab-pane fade" id="div_damnificados">
									
									<!-- Widget ID (each widget will need unique ID)-->
									<div class="jarviswidget jarviswidget-color-blueLight">
									
										
										<form id="frm_dat_generales" class="form-horizontal">
									
										<input type="hidden" id="hid_cod_con_calidad" name="hid_cod_con_calidad">
									
										
										
										<div class="form-group"></div>
										
										<div class="form-group">
											
											<label class="col-sm-3 control-label label-bold">N° de requerimiento:</label>
											<div class="col-sm-2">
												<input type="text" id="txt_nro_req" class="form-control" >
											</div>
											<div class="col-sm-5">
												<input type="text" id="txt_des_req" class="form-control" >
											</div>
										</div>		
										<div class="form-actions">
											<div class="row">
												<div class="col-md-12 opc-center">
													<button class="btn btn-primary" type="button" id="btn_agregar_ubigeo">
														<i class="fa fa-floppy-o"></i>
														Agregar ubigeo INEI
													</button>
													
													&nbsp; &nbsp;
													
													<button class="btn btn-primary" type="button" id="btn_agregar_emergencia">
														<i class="fa fa-floppy-o"></i>
														Agregar emergencia del SINPAD
													</button>
												</div>
											</div>
										</div>	
										</form>
										
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i> </span>
											<h2>Afectados y damnificados según distrito</h2>
											
											<div class="jarviswidget-ctrls" role="menu">   
												<a href="#" id="href_afec_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Exportar Excel">
													<i class="fa fa-file-excel-o"></i>
												</a> 
												
												<a href="#" id="href_afec_imprimir" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Imprimir">
													<i class="fa fa-file-pdf-o"></i>
												</a>
												<a href="#" id="btn_pro_editar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Editar">
													<i class="fa fa-edit"></i>
												</a>
												<a href="#" id="href_req_eliminar" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Eliminar">
													<i class="fa fa-trash-o"></i>
												</a>
												<a href="#" id="href_req_subir" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
													data-original-title="Subir">
													<i class="fa fa-upload"></i>
												</a>
											</div>
										</header>
						
										<!-- widget div-->
										<div>
<!-- 												<div class="row"> -->
<!-- 													<div class="col-sm-2 opc-center"> -->
<!-- 														<button class="btn btn-primary" type="button" id="btn_pro_editar"> -->
<!-- 															<i class="fa fa-edit"></i> -->
<!-- 															Editar -->
<!-- 														</button> -->
<!-- 													</div> -->
													
<!-- 												</div>			 -->
											<!-- widget content -->
											<div  class="table-scroll">

												<table id="tbl_det_afectados" class="table table-bordered table-hover tbl-responsive" >
													<thead>		 	                
														<tr> 
															<th></th>
															<th>Nº</th>
															<th>Departamento</th>
															<th>Provincia</th>
															<th>Distrito</th>
															<th>Código SINPAD</th>
															<th>Pob. INEI</th>
															<th>Fam. Afect. SINPAD</th>
															<th>Fam. dam SINPAD</th>
															<th>Total fam. SINPAD</th>
															<th>Pers. afect. SINPAD</th>
															<th>Pers. dam. SINPAD</th>
															<th>Total pers. SINPAD</th>
															<th>Fam. Afect. REAL</th>
															<th>Fam. dam. REAL</th>
															<th>Total fam. REAL</th>
															<th>Pers. afect. REAL</th>
															<th>Pers. dam. REAL</th>
															<th>Total pers. REAL</th>
														</tr>
													</thead>
													<tfoot>
														<tr>
															<td colspan="6">
																<span class="label-bold" style="float:right;">Total:</span>
															</td>
															<td colspan="1"><span id="sp_tot_inei"></span></td>	
															<td colspan="1"><span id="sp_tot_fam_afec"></span></td>
															<td colspan="1"><span id="sp_tot_fam_dam"></span></td>
															<td colspan="1"><span id="sp_tot_fam"></span></td>
															<td colspan="1"><span id="sp_tot_per_afec"></span></td>
															<td colspan="1"><span id="sp_tot_per_dam"></span></td>
															<td colspan="1"><span id="sp_tot_per"></span></td>
															<td colspan="1"><span id="sp_tot_fam_afec_real"></span></td>
															<td colspan="1"><span id="sp_tot_fam_dam_real"></span></td>
															<td colspan="1"><span id="sp_tot_fam_real"></span></td>
															<td colspan="1"><span id="sp_tot_per_afec_real"></span></td>
															<td colspan="1"><span id="sp_tot_per_dam_real"></span></td>
															<td colspan="1"><span id="sp_tot_per_real"></span></td>
															
														</tr>
													</tfoot>
												</table>
												
											</div>
											
<!-- 											<div class="widget-body " > -->
<!-- 						<table id="fixed_hdr1" > -->
<!-- 							<thead> -->
<!-- 								<tr><th>SNo</th><th>Order Number</th><th>Name</th><th>Address</th><th>City</th><th>Zip</th><th>Phone</th><th>Order Date</th><th>Company</th><th>Comments</th></tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<!-- 							<tr><td>1</td><td>996790</td><td>Chloe Ball</td><td>Ap #257-5640 Arcu. Avenue</td><td>Eugene</td><td>26699</td><td>(393) 766-1343</td><td>07/16/12</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>2</td><td>996791</td><td>Ebony Waters</td><td>Ap #985-9134 Arcu. Avenue</td><td>Seward</td><td>25697</td><td>(940) 942-1452</td><td>06/02/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>3</td><td>996792</td><td>Ahmed Rodriquez</td><td>282-3161 Lorem, Rd.</td><td>Hollywood</td><td>33065</td><td>(518) 396-3831</td><td>06/22/10</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>4</td><td>996793</td><td>Igor Baldwin</td><td>P.O. Box 984, 3232 Nullam Ave</td><td>New Brunswick</td><td>19082</td><td>(813) 803-8117</td><td>02/26/11</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>5</td><td>996794</td><td>Bertha Hewitt</td><td>330-2892 Interdum. St.</td><td>Hawthorne</td><td>62390</td><td>(518) 797-6715</td><td>06/15/11</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>6</td><td>996795</td><td>Katelyn Hebert</td><td>1064 Fringilla. Ave</td><td>Rock Springs</td><td>25013</td><td>(695) 380-1814</td><td>06/02/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>7</td><td>996796</td><td>Courtney Lee</td><td>Ap #668-1360 Est, Av.</td><td>Dalton</td><td>64476</td><td>(480) 511-5735</td><td>06/08/10</td><td>Finale</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>8</td><td>996797</td><td>Katell Patrick</td><td>P.O. Box 147, 6533 Proin Avenue</td><td>Latrobe</td><td>70185</td><td>(821) 531-3834</td><td>03/09/11</td><td>Apple Systems</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>9</td><td>996798</td><td>Joshua Dominguez</td><td>281-1223 Ac Rd.</td><td>Commerce</td><td>27741</td><td>(675) 395-5374</td><td>12/16/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>10</td><td>996799</td><td>Sierra Trevino</td><td>Ap #481-7244 Adipiscing Rd.</td><td>Fairbanks</td><td>35181</td><td>(665) 913-2731</td><td>05/24/10</td><td>Microsoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>11</td><td>996800</td><td>Raya Vega</td><td>P.O. Box 748, 4877 Pede Road</td><td>Norwalk</td><td>05538</td><td>(379) 244-7840</td><td>02/08/11</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>12</td><td>996801</td><td>Maxwell Figueroa</td><td>8705 Lorem St.</td><td>Arlington</td><td>60314</td><td>(198) 201-4894</td><td>12/23/11</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>13</td><td>996802</td><td>Daniel Patterson</td><td>P.O. Box 931, 7606 Dui, Street</td><td>Coatesville</td><td>63987</td><td>(871) 124-5670</td><td>09/04/12</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>14</td><td>996803</td><td>Germaine Hill</td><td>741-3835 Accumsan Rd.</td><td>Cape Girardeau</td><td>52698</td><td>(378) 756-3401</td><td>05/12/11</td><td>Adobe</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>15</td><td>996804</td><td>Clinton Joyce</td><td>P.O. Box 273, 2329 Ornare. Road</td><td>Johnson City</td><td>01474</td><td>(986) 604-8372</td><td>07/21/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>16</td><td>996805</td><td>Maya Kirkland</td><td>3129 Euismod Ave</td><td>Effingham</td><td>19513</td><td>(451) 666-1027</td><td>10/30/10</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>17</td><td>996806</td><td>Oren Irwin</td><td>8358 Montes, Rd.</td><td>Arcadia</td><td>31908</td><td>(100) 820-6726</td><td>08/26/12</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>18</td><td>996807</td><td>Cruz Trevino</td><td>4817 Sodales Road</td><td>Covina</td><td>52672</td><td>(355) 944-2951</td><td>04/16/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>19</td><td>996808</td><td>Mercedes Stein</td><td>Ap #732-2018 Fusce Avenue</td><td>Sunbury</td><td>78621</td><td>(682) 798-4376</td><td>12/25/10</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>20</td><td>996809</td><td>Blaine Carpenter</td><td>9943 Phasellus Avenue</td><td>Basin</td><td>72271</td><td>(618) 306-1899</td><td>05/20/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>21</td><td>996790</td><td>Chloe Ball</td><td>Ap #257-5640 Arcu. Avenue</td><td>Eugene</td><td>26699</td><td>(393) 766-1343</td><td>07/16/12</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>22</td><td>996791</td><td>Ebony Waters</td><td>Ap #985-9134 Arcu. Avenue</td><td>Seward</td><td>25697</td><td>(940) 942-1452</td><td>06/02/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>23</td><td>996792</td><td>Ahmed Rodriquez</td><td>282-3161 Lorem, Rd.</td><td>Hollywood</td><td>33065</td><td>(518) 396-3831</td><td>06/22/10</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>24</td><td>996793</td><td>Igor Baldwin</td><td>P.O. Box 984, 3232 Nullam Ave</td><td>New Brunswick</td><td>19082</td><td>(813) 803-8117</td><td>02/26/11</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>25</td><td>996794</td><td>Bertha Hewitt</td><td>330-2892 Interdum. St.</td><td>Hawthorne</td><td>62390</td><td>(518) 797-6715</td><td>06/15/11</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>26</td><td>996795</td><td>Katelyn Hebert</td><td>1064 Fringilla. Ave</td><td>Rock Springs</td><td>25013</td><td>(695) 380-1814</td><td>06/02/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>27</td><td>996796</td><td>Courtney Lee</td><td>Ap #668-1360 Est, Av.</td><td>Dalton</td><td>64476</td><td>(480) 511-5735</td><td>06/08/10</td><td>Finale</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>28</td><td>996797</td><td>Katell Patrick</td><td>P.O. Box 147, 6533 Proin Avenue</td><td>Latrobe</td><td>70185</td><td>(821) 531-3834</td><td>03/09/11</td><td>Apple Systems</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>29</td><td>996798</td><td>Joshua Dominguez</td><td>281-1223 Ac Rd.</td><td>Commerce</td><td>27741</td><td>(675) 395-5374</td><td>12/16/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>30</td><td>996799</td><td>Sierra Trevino</td><td>Ap #481-7244 Adipiscing Rd.</td><td>Fairbanks</td><td>35181</td><td>(665) 913-2731</td><td>05/24/10</td><td>Microsoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>31</td><td>996800</td><td>Raya Vega</td><td>P.O. Box 748, 4877 Pede Road</td><td>Norwalk</td><td>05538</td><td>(379) 244-7840</td><td>02/08/11</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>32</td><td>996801</td><td>Maxwell Figueroa</td><td>8705 Lorem St.</td><td>Arlington</td><td>60314</td><td>(198) 201-4894</td><td>12/23/11</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>33</td><td>996802</td><td>Daniel Patterson</td><td>P.O. Box 931, 7606 Dui, Street</td><td>Coatesville</td><td>63987</td><td>(871) 124-5670</td><td>09/04/12</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>34</td><td>996803</td><td>Germaine Hill</td><td>741-3835 Accumsan Rd.</td><td>Cape Girardeau</td><td>52698</td><td>(378) 756-3401</td><td>05/12/11</td><td>Adobe</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>35</td><td>996804</td><td>Clinton Joyce</td><td>P.O. Box 273, 2329 Ornare. Road</td><td>Johnson City</td><td>01474</td><td>(986) 604-8372</td><td>07/21/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>36</td><td>996805</td><td>Maya Kirkland</td><td>3129 Euismod Ave</td><td>Effingham</td><td>19513</td><td>(451) 666-1027</td><td>10/30/10</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>37</td><td>996806</td><td>Oren Irwin</td><td>8358 Montes, Rd.</td><td>Arcadia</td><td>31908</td><td>(100) 820-6726</td><td>08/26/12</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>38</td><td>996807</td><td>Cruz Trevino</td><td>4817 Sodales Road</td><td>Covina</td><td>52672</td><td>(355) 944-2951</td><td>04/16/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>39</td><td>996808</td><td>Mercedes Stein</td><td>Ap #732-2018 Fusce Avenue</td><td>Sunbury</td><td>78621</td><td>(682) 798-4376</td><td>12/25/10</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>40</td><td>996809</td><td>Blaine Carpenter</td><td>9943 Phasellus Avenue</td><td>Basin</td><td>72271</td><td>(618) 306-1899</td><td>05/20/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>41</td><td>996790</td><td>Chloe Ball</td><td>Ap #257-5640 Arcu. Avenue</td><td>Eugene</td><td>26699</td><td>(393) 766-1343</td><td>07/16/12</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>42</td><td>996791</td><td>Ebony Waters</td><td>Ap #985-9134 Arcu. Avenue</td><td>Seward</td><td>25697</td><td>(940) 942-1452</td><td>06/02/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>43</td><td>996792</td><td>Ahmed Rodriquez</td><td>282-3161 Lorem, Rd.</td><td>Hollywood</td><td>33065</td><td>(518) 396-3831</td><td>06/22/10</td><td>Lycos</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>44</td><td>996793</td><td>Igor Baldwin</td><td>P.O. Box 984, 3232 Nullam Ave</td><td>New Brunswick</td><td>19082</td><td>(813) 803-8117</td><td>02/26/11</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>45</td><td>996794</td><td>Bertha Hewitt</td><td>330-2892 Interdum. St.</td><td>Hawthorne</td><td>62390</td><td>(518) 797-6715</td><td>06/15/11</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>46</td><td>996795</td><td>Katelyn Hebert</td><td>1064 Fringilla. Ave</td><td>Rock Springs</td><td>25013</td><td>(695) 380-1814</td><td>06/02/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>47</td><td>996796</td><td>Courtney Lee</td><td>Ap #668-1360 Est, Av.</td><td>Dalton</td><td>64476</td><td>(480) 511-5735</td><td>06/08/10</td><td>Finale</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>48</td><td>996797</td><td>Katell Patrick</td><td>P.O. Box 147, 6533 Proin Avenue</td><td>Latrobe</td><td>70185</td><td>(821) 531-3834</td><td>03/09/11</td><td>Apple Systems</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>49</td><td>996798</td><td>Joshua Dominguez</td><td>281-1223 Ac Rd.</td><td>Commerce</td><td>27741</td><td>(675) 395-5374</td><td>12/16/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>50</td><td>996799</td><td>Sierra Trevino</td><td>Ap #481-7244 Adipiscing Rd.</td><td>Fairbanks</td><td>35181</td><td>(665) 913-2731</td><td>05/24/10</td><td>Microsoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>51</td><td>996800</td><td>Raya Vega</td><td>P.O. Box 748, 4877 Pede Road</td><td>Norwalk</td><td>05538</td><td>(379) 244-7840</td><td>02/08/11</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>52</td><td>996801</td><td>Maxwell Figueroa</td><td>8705 Lorem St.</td><td>Arlington</td><td>60314</td><td>(198) 201-4894</td><td>12/23/11</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>53</td><td>996802</td><td>Daniel Patterson</td><td>P.O. Box 931, 7606 Dui, Street</td><td>Coatesville</td><td>63987</td><td>(871) 124-5670</td><td>09/04/12</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>54</td><td>996803</td><td>Germaine Hill</td><td>741-3835 Accumsan Rd.</td><td>Cape Girardeau</td><td>52698</td><td>(378) 756-3401</td><td>05/12/11</td><td>Adobe</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>55</td><td>996804</td><td>Clinton Joyce</td><td>P.O. Box 273, 2329 Ornare. Road</td><td>Johnson City</td><td>01474</td><td>(986) 604-8372</td><td>07/21/10</td><td>Google</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>56</td><td>996805</td><td>Maya Kirkland</td><td>3129 Euismod Ave</td><td>Effingham</td><td>19513</td><td>(451) 666-1027</td><td>10/30/10</td><td>Altavista</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>57</td><td>996806</td><td>Oren Irwin</td><td>8358 Montes, Rd.</td><td>Arcadia</td><td>31908</td><td>(100) 820-6726</td><td>08/26/12</td><td>Chami</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>58</td><td>996807</td><td>Cruz Trevino</td><td>4817 Sodales Road</td><td>Covina</td><td>52672</td><td>(355) 944-2951</td><td>04/16/10</td><td>Lavasoft</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>59</td><td>996808</td><td>Mercedes Stein</td><td>Ap #732-2018 Fusce Avenue</td><td>Sunbury</td><td>78621</td><td>(682) 798-4376</td><td>12/25/10</td><td>Borland</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr> -->
<!-- 							<tr><td>60</td><td>996809</td><td>Blaine Carpenter</td><td>9943 Phasellus Avenue</td><td>Basin</td><td>72271</td><td>(618) 306-1899</td><td>05/20/11</td><td>Cakewalk</td><td>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur</td></tr>														</tbody> -->
<!-- 						</table> -->
<!-- 						</div> -->
											
											
											<!-- end widget content -->
						
										</div>
										<!-- end widget div -->
						
									</div>
									<!-- end widget -->
									
									<div class="modal-footer">
<!-- 										<button type="button" class="btn btn-primary" id="btn_gra_afectados"> -->
<!-- 											<i class="fa fa-floppy-o"></i> -->
<!-- 											Grabar -->
<!-- 										</button> -->
										
<!-- 										&nbsp; &nbsp; -->
										
										<button type="button" class="btn btn-default btn_retornar" data-dismiss="modal" id="btn_can_afectados">
											<i class="fa fa-mail-forward"></i>
											Retornar
										</button>
									</div>
									
								</div>
							</div>
	
						</div>
						<!-- end widget content -->
	
					<!-- end widget div -->
	
				
				<!-- end widget -->
			
			</article>	
	
		</div>	
		<!-- end row -->
	
	</section>
	<!-- end widget grid -->
	
</div>
<!-- END MAIN CONTENT -->

<!-- Modal  Agregar UBIGEO INEI-->
<div id="div_det_prog_ubigeo" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Seleccionar distritos según INEI</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
						
							<div class="row">
								<label class="col-sm-1 control-label">Año:</label>
								<div class="col-sm-2 ">
									<select id="sel_anio_ubi" name="sel_anio_ubi" class="form-control">
										<c:forEach items="${lista_anio}" var="item">
										    <option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Departamento:</label>
								<div class="col-sm-2">
									<select id="sel_departamento_ubi" name="sel_departamento_ubi" class="form-control">
										<c:forEach items="${lista_departamento}" var="item">
										    <option value="${item.coddpto}">${item.nombre}</option>
										</c:forEach>
									</select>
								</div>
								  <label class="col-sm-1 control-label">Provincia:</label>	
								<div class="col-sm-2 ">
									<select id="sel_provincia_ubi" name="sel_provincia_ubi" class="form-control">
										<option value="">Todos</option>
									</select>
								</div>																		
								
								<div class="col-sm-2">
									<button class="btn btn-primary" type="button" id="btn_aceptar_ubigeo">
										<i class="fa fa-search"></i>
										Buscar
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>	
				
				<div class="row">&nbsp;</div>	
				<!-- NEW WIDGET START -->
			<article class="col-xs-12 col-sm-12">
	
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget jarviswidget-color-blueLight">
				
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Distritos de la provincia</h2>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body " >

							<table id="tbl_mnt_ubigeo_inei" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
<!-- 										<th><label class="checkbox"><input type="checkbox"><i></i></label></th> -->
										<th><input type="checkbox" id ="mainChkBox"/></th>
										<th>Nº</th>
										<th>Ubigeo</th>
										<th>Provincia</th>
										<th>Distrito</th>
										<th>Población INEI</th>
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
			
			<div class="modal-footer">
				<div class="form-group">
					<label class="col-sm-3 control-label label-bold">Total seleccionado:</label>
					<div class="col-sm-2">
						<input type="text" id="txt_nro_selec_ubi" class="form-control" disabled>
					</div>
				</div>		
				<button type="button" class="btn btn-primary" id="btn_pasar_distrito_ubigeo">
					<i class="fa fa-floppy-o"></i>
					Pasar Distritos Seleccionados al  Requerimiento
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

<!-- Modal  Agregar EMERGENCIAS ACTIVAS-->
<div id="div_det_prog_emerg" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-85-large">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_alimentarios">Emergencias</h4>
			</div>
			
			<div class="modal-body" id="miModal">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_prog_ubigeo" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_producto" name="hid_cod_producto">
						
							<div class="row">
								<label class="col-sm-2 control-label">Año:</label>
								<div class="col-sm-2 form-group">
									<select id="sel_anio_emer" name="sel_anio_emer" class="form-control">
										<c:forEach items="${lista_anio}" var="item">
										    <option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Mes:</label>
								<div class="col-sm-2">
									<select id="sel_mes_emer" name="sel_mes_emer" class="form-control">
										<option value="">Todos</option>
										<c:forEach items="${lista_mes}" var="item">
										    <option value="${item.vcodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
								
								<label class="col-sm-2 control-label">Departamento:</label>
								<div class="col-sm-2">
									<select id="sel_departamento_emer" name="sel_departamento_emer" class="form-control">
										<c:forEach items="${lista_departamento}" var="item">
										    <option value="${item.coddpto}">${item.nombre}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div  class="row">	
							    <label class="col-sm-2 control-label">Provincia:</label>	
								<div class="col-sm-2 form-group">
									<select id="sel_provincia_emer" name="sel_provincia_emer" class="form-control">
										<option value="">Todos</option>
									</select>
								</div>																		
								<label class="col-sm-2 control-label">Fenómeno:</label>
								<div class="col-sm-2">
									<select id="sel_fenomeno_emer" name="sel_fenomeno_emer" class="form-control">
										<option value="0">Todos</option>
										<c:forEach items="${lista_fenomeno}" var="item">
										    <option value="${item.icodigo}">${item.descripcion}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-2">
								<button class="btn btn-primary" type="button" id="btn_aceptar_emer">
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
	
				<!-- Widget ID (each widget will need unique ID)-->
				<div class="jarviswidget jarviswidget-color-blueLight">
				
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>Relación de Emergencias</h2>
						
						<div class="jarviswidget-ctrls" role="menu">   
							<a href="#" id="href_emer_acti_exp_excel" class="button-icon" rel="tooltip" title="" data-placement="bottom" 
								data-original-title="Exportar Excel">
								<i class="fa fa-file-excel-o"></i>
							</a> 
						</div>
					</header>
	
					<!-- widget div-->
					<div>
										
						<!-- widget content -->
						<div class="widget-body " >

							<table id="tbl_mnt_emer_act" class="table table-bordered table-hover tbl-responsive">
								<thead>			                
									<tr>
										<th></th>
										<th>Nº</th>
										<th>Año</th>
										<th>Mes</th>
										<th>Fecha</th>
										<th>Código SINPAD</th>
										<th>Fenómeno</th>
										<th>Nombre Emergencias</th>
										<th>Región</th>
										<th>Provincia</th>
										<th>Distrito</th>
										<th>Fam. afect.</th>
										<th>Fam. Dam</th>
										<th>Total Fam.</th>
										<th>Pers. Afect.</th>
										<th>Pers. Dam.</th>
										<th>Total Pers.</th>
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
			
			<div class="modal-footer">
				<div class="form-group">
					<label class="col-sm-3 control-label label-bold">Total seleccionado:</label>
					<div class="col-sm-2">
						<input type="text" id="txt_nro_selec" class="form-control" disabled>
					</div>
				</div>		
				<button type="button" class="btn btn-primary" id="btn_pasar_distrito">
					<i class="fa fa-floppy-o"></i>
					Pasar Distritos Seleccionados al  Requerimiento
				</button>
				
				&nbsp; &nbsp;
				
				<button type="button" class="btn btn-default" data-dismiss="modal" id="btn_can_emer">
					<i class="fa fa-mail-forward"></i>
					Cancelar
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- MODAL  ACTUALIZAR AFECTADOS/DAMNIFICADOS-->
<div id="div_mod_actualiza_emer" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_no_alimentarios" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold" id="h4_tit_afectados">Afectados y damnificados</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_afecta_damni" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_dam_afec" name="hid_cod_dam_afec">
							<div class="row">
								<div class="form-group">
											
									<label class="col-sm-3 control-label label-bold">N° de requerimiento:</label>
									<div class="col-sm-2">
										<input type="text" id="txt_nro_req_mo" class="form-control" disabled>
									</div>
									<div class="col-sm-5">
										<input type="text" id="txt_des_req_mo" class="form-control" disabled>
									</div>
								</div>	
							</div>
							<div class="row">
								<div class="form-group">
<!-- 											<div class="col-sm-1"></div> -->
									<label class="col-sm-1 control-label label-bold">Departamento:</label>
									<div class="col-sm-2">
										<input type="text" id="txt_m_dto" class="form-control" disabled>
									</div>
									<label class="col-sm-1 control-label label-bold">Provincia:</label>
									<div class="col-sm-3">
										<input type="text" id="txt_m_prov" class="form-control" disabled>
									</div>
									<label class="col-sm-1 control-label label-bold">Distrito:</label>
									<div class="col-sm-3">
										<input type="text" id="txt_m_dist" class="form-control" disabled>
									</div>
								</div>	
							</div>
							<div class="row">				
								<div class="col-sm-3"></div>																
								<label class="col-sm-3 control-label">Fam. Afect. Real:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_fam_afec" id="txt_fam_afec" class="form-control only-numbers-format" >
								</div>
							</div>
							
							<div class="row">		
								<div class="col-sm-3"></div>																		
								<label class="col-sm-3 control-label">Fam. Dam. Real:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_fam_dam" id="txt_fam_dam" class="form-control only-numbers-format" >
								</div>
							</div>
						
							<div class="row">&nbsp;</div>									

							<div class="row">			
								<div class="col-sm-3"></div>																	
								<label class="col-sm-3 control-label">Pers. Afect. Real:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_per_afec" id="txt_per_afec" class="form-control only-numbers-format" >
								</div>
							</div>
							
							<div class="row">				
								<div class="col-sm-3"></div>																
								<label class="col-sm-3 control-label">Pers. Dam. Real:</label>
								<div class="col-sm-2 form-group">
									<input type="text" name="txt_per_dam" id="txt_per_dam" class="form-control only-numbers-format" >
								</div>
							</div>
							
							<div class="row">		
								<div class="col-sm-5"></div>
								<div class="col-sm-2 form-group">
									<button type="button" class="btn btn-primary" id="btn_gra_actualiza_emer" >
										<i class="fa fa-floppy-o"></i>
										Grabar
									</button>
								</div>																		
								
								<div class="col-sm-2 form-group">
									<button type="button" class="btn btn-default" data-dismiss="modal"  id="btn_can_actualiza_emer" >
									<i class="fa fa-mail-forward"></i>
									Cancelar
								</button>
								</div>	
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal -->
<div id="div_requerimiento_subir" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="btn_clo_documentos" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title label-bold1" id="h4_tit_poblacion_inei">Subir Archivo</h4>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<form id="frm_det_documentos" class="form-horizontal" role="form">
							
							<input type="hidden" id="hid_cod_documento" name="hid_cod_documento">
							<input type="hidden" id="hid_cod_act_alfresco" name="hid_cod_act_alfresco">
							<input type="hidden" id="hid_cod_ind_alfresco" name="hid_cod_ind_alfresco">						
							
							<div class="form-group">
								<label class="col-sm-3 control-label">Subir Archivo:</label>
								<div class="col-sm-8 smart-form">
									<div class="input input-file">
										<span id="sp_sub_archivo" class="button">
											<input type="file" id="txt_sub_archivo" name="txt_sub_archivo">
											Cargar
										</span>
										<input type="text" id="txt_lee_sub_archivo" name="txt_lee_sub_archivo" readonly>
									</div>
								</div>								
							</div>
							
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn_gra_per_inei">
					<i class="fa fa-floppy-o"></i>
					Grabar
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
<script> var requerimiento = JSON.parse('${requerimiento}'); </script>
<script> var lista_requerimiento = JSON.parse('${lista_requerimiento}'); </script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/mantenimiento_requerimiento.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/programacion_bah/validacion_mantenimiento_requerimiento.js"></script>
