<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header id="header">

	<div id="logo-group">
		<span id="logo"> 
			<img src="${pageContext.request.contextPath}/resources/img/logo-indeci.png" alt="Ingreso al Sistema de Gestión de Bienes de Ayuda Humanitaria - SIGBAH">
		</span>
	</div>
	
	<span class="opc-center"> <br><b>Ingreso al Sistema de Gestión de Bienes de Ayuda Humanitaria - SIGBAH</b> </span>

</header>

<div id="main" role="main">

	<!-- MAIN CONTENT -->
	<div id="content" class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 hidden-xs hidden-sm"></div>

			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
				<div class="well">
					
					<header><strong> Bienvenido, ingresa tu usuario y contraseña aqui. </strong></header>

					<spring:nestedPath path="usuario">
                        <form id="loginForm" class="smart-form client-form" name="loginForm" action="${pageContext.request.contextPath}/login" method="POST" autocomplete="off">
							<fieldset>	
								<section>
									<label class="label">Usuario:</label> 
									<label class="input">
										<i class="icon-append fa fa-user"></i> 
										<input type="text" id="usuario" name="usuario" onchange="checkInput();"/>											
										<b class="tooltip tooltip-top-right">
											<i class="fa fa-user txt-color-teal"></i> 
											Por favor ingrese el usuario
										</b>
									</label>
								</section>
	
								<section>
									<label class="label">Contraseña:</label> 
									<label class="input">
										<i class="icon-append fa fa-lock"></i> 
										<input type="password" id="password" name="password" onchange="checkInput();"> 
										<b class="tooltip tooltip-top-right">
											<i class="fa fa-lock txt-color-teal"></i> 
											Por favor ingrese la contraseña
										</b>
									</label>
								</section>
								<font size="2" color="red" id="mensajeError">${mensajeError}</font>
							</fieldset>
							
							<footer>
								<button type="submit" class="btn btn-primary" id="btn_ingresar">Ingresar</button>
							</footer>
							
							<fieldset id="fie_almacen" class="hide">	
								<section>
									<label class="label">Seleccione el Almacen:</label>
									<label class="select">
										<select id="idAlmacen" name="idAlmacen" class="input-sm">
										</select> 
										<i></i>										
										<b class="tooltip tooltip-top-right">
											<i class="fa fa-user txt-color-teal"></i> 
											Por favor seleccione almacen
										</b>
									</label>
								</section>
							</fieldset>
							
							<footer id="fie_alm_opciones" class="hide">								
								<button type="button" class="btn btn-primary" id="btn_cancelar">Cancelar</button>
								&nbsp; &nbsp;
								<button type="submit" class="btn btn-primary" id="btn_aceptar">Aceptar</button>
							</footer>
						</form>
                    </spring:nestedPath>
				
				</div>

			</div>
		</div>
	</div>

</div>

<script type="text/javascript">

	$(document).ready(function() {
	
		// Validation
		$("#loginForm").validate({
			// Rules for form validation
			rules : {
				usuario : {
					required : true
				},
				password : {
					required : true
				}
			},

			// Messages for form validation
			messages : {
				usuario : {
					required : 'Por favor ingrese el usuario'
				},
				password : {
					required : 'Por favor ingrese la contraseña'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			},
			
			submitHandler: function (form) {
				loadding(true);				
				consultarAjax('POST', '/login', $(form).serialize(), function(respuesta) {
					var url = null;
					if (respuesta.codigoRespuesta == NOTIFICACION_ERROR) {
						loadding(false);
						addErrorMessage(null, respuesta.mensajeRespuesta);

						if ($('#idAlmacen').val() != '') {
							$('#usuario').val('');
							$('#password').val('');
						}
// 						url = '${pageContext.request.contextPath}/login';
// 						$(location).attr('href', url);
					} else {
						if (respuesta == '0') {
							url = '${pageContext.request.contextPath}/login';
							$(location).attr('href', url);
						} else if (respuesta == '1') {
							url = '${pageContext.request.contextPath}/principal/inicio';
							$(location).attr('href', url);
						} else {
							$('#usuario').prop('readonly', true);
							$('#usuario').addClass('mod-readonly')
							$('#password').prop('readonly', true);
							$('#password').addClass('mod-readonly')
							$('#btn_ingresar').prop('disabled', true);
							var options = '';
					        $.each(respuesta, function(i, item) {
					            options += '<option value="'+item.idAlmacen+'">'+item.nombreAlmacen+'</option>';
					        });
					        $('#idAlmacen').html(options);
					        $('#fie_almacen').attr('class', '');
					        $('#fie_alm_opciones').attr('class', '');
					        loadding(false);
						}
					}
					
				});

 	        	return false; // required to block normal submit since you used ajax

	        }
			
		});
		
		$('#btn_cancelar').click(function(e) {
			e.preventDefault();

			loadding(true);					
			var url = '${pageContext.request.contextPath}/login';
			$(location).attr('href', url);
			
		});
		
		
		
	});
	
	function checkInput() {
		$('#mensajeError').html('');
	}
	
</script>