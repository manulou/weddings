<%@ include file="/jsp/layout/header.jsp"%>

<script src="<c:url value="/resources/js/web/secure/secureAgencyDetails.js" />"></script>

<div class="container">

	<div class="main" data-ng-controller="agencyDetailsController" data-ng-init="init('${empty id ? 'New' : id}')">
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
				<div class="col-sm-4 text-left">
					<input type="checkbox" name="visible" data-ng-model="agency.visible" /> Visible
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
			</div>
			<hr />
			<div class="form-group" data-ng-show="id != null" data-ng-repeat-start="package in agency.packages">
				<h2 class="col-md-12">{{package.name}}</h2>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.packageName.$dirty && detailsForm.packageName.$invalid}">Package name</label>
				<div class="col-xs-11 col-md-3" data-ng-class="{'has-error': detailsForm.packageName.$dirty && detailsForm.packageName.$invalid}">
					<input type="text" name="packageName" class="form-control" data-ng-model="package.name" required />
				</div>
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.packagePrice.$dirty && detailsForm.packagePrice.$invalid}">Price</label>
				<div class="col-xs-11 col-md-3" data-ng-class="{'has-error': detailsForm.packagePrice.$dirty && detailsForm.packagePrice.$invalid}">
					<input type="number" name="packagePrice" class="form-control" data-ng-model="package.price" />
				</div>
				<div class="col-sm-2 text-left">
					<input type="checkbox" name="visible" data-ng-model="package.visible" /> Visible
				</div>
				<div class="col-xs-11 col-md-2 text-left">
					<a href="javascript:;" class="btn btn-danger" data-ng-click="deletePackage(package)" role="button">Delete package</a>
				</div>
			</div>

			<div class="form-group" data-ng-show="id != null" data-ng-repeat-start="category in categories">
				<label class="col-sm-1 control-label" data-ng-class="{'has-error': detailsForm.categoryName.$dirty && detailsForm.categoryName.$invalid}">Category:</label>
				<div class="col-md-3 text-left" data-ng-class="{'has-error': detailsForm.categoryName.$dirty && detailsForm.categoryName.$invalid}">
					<input type="text" name="categoryName" class="form-control" data-ng-model="category.name" required />
				</div>
				<label class="col-sm-1 control-label">New attribute:</label>
				<div class="col-xs-11 col-md-3">
					<input type="text" class="form-control" name="newAttributeName" data-ng-model="newAttribute.attribute.name" />
				</div>
				<div class="col-xs-12 col-md-4 text-left">
					<a href="javascript:;" class="btn btn-primary" data-ng-click="addAttribute(newAttribute, package, category)" role="button">Add attribute</a>
				</div>
			</div>
			<div class="form-group" data-ng-show="id != null">
				<div class="col-sm-2 text-left" data-ng-repeat="attribute in attributes" data-ng-if="attribute.categoryId == category.id">
					<input type="checkbox" data-ng-checked="packageHasAttribute(package, attribute)" data-ng-click="toggleAttributeInPackage(attribute, package, category)" /> {{attribute.name}}
				</div>
			</div>
			<hr data-ng-repeat-end></hr>

			<hr data-ng-repeat-end></hr>
			<div class="form-group" data-ng-show="id != null">
				<label class="col-sm-1 control-label">Package name</label>
				<div class="col-xs-11 col-md-3">
					<input type="text" name="newPackageName" class="form-control" data-ng-model="newPackage.name" />
				</div>
				<label class="col-sm-1 control-label">Price</label>
				<div class="col-xs-11 col-md-3">
					<input type="number" name="newPackagePrice" class="form-control" data-ng-model="newPackage.price" />
				</div>
				<div class="col-xs-11 col-md-4 text-left">
					<a href="javascript:;" class="btn btn-success" data-ng-click="addPackage(newPackage)" role="button">Add package</a>
				</div>
			</div>
			<div class="form-group" data-ng-show="id != null">
				<label class="col-sm-1 control-label">Category name</label>
				<div class="col-xs-11 col-md-3">
					<input type="text" name="newCategoryName" class="form-control" data-ng-model="newCategory.name" />
				</div>
				<div class="col-xs-11 col-md-8 text-left">
					<a href="javascript:;" class="btn btn-success" data-ng-click="addCategory(newCategory)" role="button">Add category</a>
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
							<a href="javascript:;" class="btn btn-danger"  data-ng-click="deleteImage(image)" role="button">Delete</a>
						</p>
					</div>
				</div>
			</div>
			<div class="form-group">
				<button id="backButton" class="btn btn-primary btn-lg" onclick="document.location='<c:url value="/secure" />';">Back</button>
				<button id="submitButton" data-ng-click="save(agency)" type="submit" data-loading-text="Just a sec..." class="btn btn-success btn-lg" >Save</button>
			</div>
        </form>
	</div>
	
</div>

<%@ include file="/jsp/layout/footer.jsp"%>