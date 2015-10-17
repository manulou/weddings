<%@ include file="/jsp/layout/header.jsp"%>

<script src="<c:url value="/resources/js/web/secure/secureAgencies.js" />"></script>

<div class="container">

	<div class="table-responsive">
		<table data-ng-controller="agencyListController" class="table table-hover">
			<tr>
				<th>Name</th>
				<th>Country</th>
				<th></th>
			</tr>
			<tr data-ng-repeat="agency in agencies.list">
				<td>{{agency.name}}</td>
				<td>{{agency.country}}</td>
				<td>
					<a href="<c:url value="/secure/agency/" />{{agency.id}}">Details</a> |
					<a href data-ng-click="deleteAgency(agency)">Delete</a> 
				</td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<td>
					<a href="<c:url value="/secure/agency/new" />">New Agency</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="pull-right">
                        <ul class="pagination">
                            <li data-ng-class="{disabled: agencies.currentPage == 1}">
                                <a href data-ng-click="searchAgencies(agencies.currentPage - 1)">« Prev</a>
                            </li>
                            <li>
                                <a href>{{agencies.currentPage}}</a>
                            </li>
                            <li data-ng-class="{disabled: agencies.currentPage == agencies.lastPage}">
                                <a href data-ng-click="searchAgencies(agencies.currentPage + 1)">Next »</a>
                            </li>
                        </ul>
                    </div>
				</td>
			</tr>
		</table>
	</div>
</div>

<%@ include file="/jsp/layout/footer.jsp"%>