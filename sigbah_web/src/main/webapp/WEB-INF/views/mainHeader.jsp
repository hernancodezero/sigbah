<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- #HEADER -->
<header id="header" style="background-color:#FFFFFF;">

	<div id="logo-group">
		<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-12">
					<img src="${pageContext.request.contextPath}/resources/img/logo-indeci.png" class="img-responsive">
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="project-context hidden-xs">	
		<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-2">
					<img src="${pageContext.request.contextPath}/resources/img/logo-wfp.bmp" class="img-responsive">
				</div>
				<div class="col-sm-4 div-titulo">
					<spring:message code="application_name" />
				</div>
				<div class="col-sm-5 div-usuario">
					Usuario: ${usuarioBean.nombreUsuario} <br> ${usuarioBean.nombreDdi}
					<div style="text-transform: capitalize;">
					 ${usuarioBean.nombreAlmacen}
				</div>
				</div>
				
			</div>
		</div>	
	</div>
	
	<!-- #TOGGLE LAYOUT BUTTONS -->
	<!-- pulled right: nav area -->
	<div class="pull-right">
		<!-- collapse menu button -->
		<div id="hide-menu" class="btn-header pull-right">
			<span>
				<a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu">
					<i class="fa fa-reorder"></i>
				</a>
			</span>
		</div>
		<!-- end collapse menu -->

		<!-- logout button -->
		<div id="logout" class="btn-header pull-right">
			<span> 
				<a href="${pageContext.request.contextPath}/logout/inicio" title="Cerrar Sesión">
					Salir
					<i class="fa fa-sign-out"></i>
				</a> 
			</span>
		</div>
		<!-- end logout button -->

	</div>
	<!-- end pulled right: nav area -->

</header>
<!-- END HEADER -->