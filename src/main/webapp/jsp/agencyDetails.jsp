<%@ include file="/jsp/layout/header.jsp"%>

<script src="<c:url value="/resources/js/web/agencyDetails.js" />"></script>

<div class="container">

	<div class="main" data-ng-controller="agencyDetailsController" data-ng-init="init('${empty id ? 'unknown' : id}')">
		<spring:url value="/image/" var="imageUrl" />

		<h1>{{agency.name}}</h1>

		<div class="row">
			<div class="col-md-4">
				<label class="control-label">Country:</label>
				{{agency.country.name}}
			</div>
			<div class="col-md-4">
				<label class="control-label">Email:</label>
				{{agency.email}}
			</div>
			<div class="col-md-4">
				<label class="control-label">Phone:</label>
				{{agency.phone}}
			</div>
		</div>
		<div class="row" data-ng-repeat-start="package in agency.packages">
			<div class="col-md-6 text-left">
				<h2>{{package.name}}</h2>
			</div>
			<div class="col-md-6 text-right">
				<h2>{{package.price}} EUR</h2>
			</div>
		</div>
		<div class="row">
			<div class="categories">
				<div class="col-md-3 col-xs-12 col-sm-4 category" data-ng-repeat="category in categories">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">{{category.name}}</h3>
						</div>
						<div class="panel-body">
							<ul class="list-group text-left">
								<li class="list-group-item" data-ng-repeat="packageAttribute in package.attributes" data-ng-if="packageAttribute.category.id == category.id">
									<span class="progress-bar-success badge">
										<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
									</span>
									{{packageAttribute.attribute.name}}
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr data-ng-repeat-end class="col-md-12"></hr>
		<div class="col-sm-2" data-ng-repeat="image in images">
			<div class="thumbnail">
				<a href="${imageUrl}{{image.relatedImageId}}" target="_blank">
					<img data-ng-src="${imageUrl}{{image.id}}" alt="{{image.name}}" />
				</a>
			</div>
		</div>
	</div>
	<div class="col-md-12 text-center">
		<button id="backButton" class="btn btn-primary btn-lg" onclick="document.location='<c:url value="/" />';">Back</button>
	</div>
	
</div>

<%@ include file="/jsp/layout/footer.jsp"%>