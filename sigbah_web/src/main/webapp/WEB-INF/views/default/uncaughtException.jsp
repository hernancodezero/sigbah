<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!-- MAIN CONTENT -->
<div id="content">  
	
	<!-- widget grid -->
	<section id="widget-grid" class="">
	
		<!-- row -->
		<div class="row">
		
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		
				<div class="row">
					<div class="col-sm-12">
						<div class="text-center error-box">
							<h1 class="error-text tada animated">
								<i class="fa fa-times-circle text-danger error-icon-shadow"></i> Error 500
							</h1>
							<h2 class="font-xl"><strong>¡Oooops, algo salió mal!</strong></h2>
							<br />
							<p class="lead semi-bold">
								<strong><spring:message code="error_uncaughtexception_problemdescription" /></strong><br><br>
							</p>
						</div>	
					</div>
							
				</div>
		
			</div>
			
		</div>
		<!-- end row -->
		
	</section>
	<!-- end widget grid -->
	
</div>
<!-- END MAIN CONTENT -->
