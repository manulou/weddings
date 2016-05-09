weddingsApp.controller('agencyListController', function ($scope, $http, ContextService) {
	
	var contextPath = ContextService.path;
	
	$scope.searchAgencies = function (page) {
		if (page < 0) {
			page = 0;
		}
		if (page >= $scope.agencies.lastPage) {
			page = $scope.agencies.lastPage - 1;
		}
        $http({
            url: contextPath + 'secure/searchAgencies',
            method: 'GET',
            params: {
                page: page,
                sortField: $scope.sortInfo.field[0],
                sortDirection: $scope.sortInfo.direction[0]
            }
        }).success(function (data) {
            $scope.agencies = data;
        });
    };
    
    $scope.deleteAgency = function (agency) {
    	if (confirm('Are you sure you want to delete the agency ' + agency.name + '?')) {
	        $http({
	            url: contextPath + 'secure/deleteAgency/' + agency.id,
	            method: 'GET'
	        }).success(function (data) {
	        	$scope.searchAgencies($scope.agencies.currentPage);
	        });
    	}
    };
 
    // Do something when the grid is sorted.
    // The grid throws the ngGridEventSorted that gets picked up here and assigns the sortInfo to the scope.
    // This will allow to watch the sortInfo in the scope for changed and refresh the grid.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });
 
    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.searchAgencies($scope.agencies.currentPage);
    }, true);
 
    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {field: ['id'], direction: ['asc']};
    $scope.agencies = {currentPage : 0};
});