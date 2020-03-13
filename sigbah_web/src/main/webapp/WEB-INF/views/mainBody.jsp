<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">

	<tiles:insertAttribute name="regionLeft" ignore="true" />
	
	<div class="main-content">
		
		<tiles:insertTemplate template="/WEB-INF/views/principal.jsp" flush="true"></tiles:insertTemplate>
		
	</div><!-- /.main-content -->
	
	<tiles:insertAttribute name="regionBottom" ignore="true" />
	
</div><!-- /.main-container -->