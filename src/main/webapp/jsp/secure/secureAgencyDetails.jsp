<%@ include file="/jsp/layout/header.jsp"%>

<script src="<c:url value="/resources/js/web/secure/secureAgencyDetails.js" />"></script>

<div class="container">

	<div class="main" data-ng-controller="agencyDetailsController"  data-ng-init="init('${empty id ? 'New' : id}')">
		<h1>Agency Details, id: {{agency.id}}</h1>
		<spring:url value="/saveAgency" var="saveUrl" />
		<spring:url value="/uploadAgencyPhoto" var="uploadUrl" />
		<spring:url value="/image/" var="imageUrl" />
		
		<form name="detailsForm" novalidate class="form-horizontal" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.name.$dirty && detailsForm.name.$invalid}">Name</label>
				<div class="col-xs-11 col-md-3" data-ng-class="{'has-error': detailsForm.name.$dirty && detailsForm.name.$invalid}">					
					<input type="text" name="name" class="form-control" data-ng-model="agency.name" required />
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.country.$dirty && detailsForm.country.$invalid}">Country</label>
				<div class="col-xs-11 col-md-3"  data-ng-class="{'has-error': detailsForm.country.$dirty && detailsForm.country.$invalid}">					
					<select name="country" class="form-control" data-ng-model="agency.country.id"
        				data-ng-options="country.id as country.name for country in countries" required>
					</select>
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.minPrice.$dirty && detailsForm.minPrice.$invalid}">Min price</label>
				<div class="col-xs-11 col-md-3"  data-ng-class="{'has-error': detailsForm.minPrice.$dirty && detailsForm.minPrice.$invalid}">					
					<input type="number" name="minPrice" class="form-control" data-ng-model="agency.minPrice" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.email.$dirty && detailsForm.email.$invalid}">Email</label>
				<div class="col-xs-11 col-md-3" data-ng-class="{'has-error': detailsForm.email.$dirty && detailsForm.email.$invalid}">					
					<input type="email" name="email" class="form-control" data-ng-model="agency.email" required />
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.phone.$dirty && detailsForm.phone.$invalid}">Phone</label>
				<div class="col-xs-11 col-md-3"  data-ng-class="{'has-error': detailsForm.phone.$dirty && detailsForm.phone.$invalid}">					
					<input type="text" name="phone" class="form-control" data-ng-model="agency.phone" />
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.maxPrice.$dirty && detailsForm.maxPrice.$invalid}">Max price</label>
				<div class="col-xs-11 col-md-3"  data-ng-class="{'has-error': detailsForm.maxPrice.$dirty && detailsForm.maxPrice.$invalid}">					
					<input type="number" name="maxPrice" class="form-control" data-ng-model="agency.maxPrice" />
				</div>
			</div>
			<h4 class="dragAndDropSupported">Drag and drop images below</h4>
			<div data-ng-show="id != null" class="ng-hide" class="form-group dragAndDropSupported">
				<label class="col-sm-1 control-label"></label>
				<div class="col-xs-12 col-md-11">
        			<div class="upload-drop-zone" id="drop-zone">Just drag and drop images here</div>
        			
        			<div class="progress" style="display:none">
		 				<div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
			          	 	<span class="sr-only">0% Complete</span>
			           	</div>
			       	</div>
        		</div>
        	</div>
        	<div class="form-group">
        		<div class="col-sm-2" data-ng-repeat="image in images">
					<div class="thumbnail">
						<a href="${imageUrl}{{image.relatedImageId}}" target="_blank">
							<img data-ng-src="${imageUrl}{{image.id}}" alt="{{image.name}}" />
						</a>
					</div>
					<div class="caption">
						<p>
							<a href="#" class="btn btn-primary"  data-ng-click="deleteImage(image)" role="button">Delete</a>
						</p>
					</div>
				</div>
			</div>
			<div class="form-group">
				<button id="backButton" class="btn btn-primary btn-lg" onclick="document.location='<c:url value="/secure" />';">Back</button>
				<button id="submitButton" data-ng-click="save(agency)" type="submit" data-loading-text="Just a sec..." class="btn btn-primary btn-lg" >Save</button>
			</div>
        </form>
	</div>
	
</div>

<%@ include file="/jsp/layout/footer.jsp"%>