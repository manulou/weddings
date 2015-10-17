<%@ include file="/jsp/layout/header.jsp"%>

<script src="<c:url value="/resources/js/web/agencyDetails.js" />"></script>

<div class="container">

	<div class="main" data-ng-controller="agencyDetailsController"  data-ng-init="init('${empty id ? 'New' : id}')">
		<h1>Agency Details, id: {{agency.id}}</h1>
		<spring:url value="/saveAgency" var="saveUrl" />
		<spring:url value="/uploadAgencyPhoto" var="uploadUrl" />
		
		<form name="detailsForm" novalidate class="form-horizontal" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.name.$dirty && detailsForm.name.$invalid}">Name</label>
				<div class="col-xs-11 col-md-3" data-ng-class="{'has-error': detailsForm.name.$dirty && detailsForm.name.$invalid}">					
					<input type="text" name="name" class="form-control" data-ng-model="agency.name" required />
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.country.$dirty && detailsForm.country.$invalid}">Country</label>
				<div class="col-xs-11 col-md-3"  data-ng-class="{'has-error': detailsForm.country.$dirty && detailsForm.country.$invalid}">					
					<select name="country" class="form-control" data-ng-model="agency.country.id"
        				data-ng-options="name for id in countries" required>
					</select>
				</div>
			</div>
			<div class="form-group">
				<button id="backButton" class="btn btn-primary btn-lg" onclick="document.location='<c:url value="/" />';">Back</button>
			</div>
        </form>
	</div>
	
</div>

<%@ include file="/jsp/layout/footer.jsp"%>