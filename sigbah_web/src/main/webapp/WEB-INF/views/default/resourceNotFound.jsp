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
							<br />
							<h1 class="error-text-2 bounceInDown animated"> Error 404 
								<span class="particle particle--c"></span>
								<span class="particle particle--a"></span>
								<span class="particle particle--b"></span>
							</h1>
							<h2 class="font-xl"><strong><i class="fa fa-fw fa-warning fa-lg text-warning">
								</i> Página <u>no</u> encontrada</strong>
							</h2>
							<br />
							<p class="lead">
								<spring:message code="error_resourcenotfound_problemdescription" />
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