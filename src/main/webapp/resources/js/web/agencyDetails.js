weddingsApp.controller('agencyDetailsController', function ($scope, $rootScope, $http, ContextService) {
	var contextPath = ContextService.path;
	
	$scope.init = function(id) {
		$scope.id = parseInt(id);
	}
	
	$http.get(contextPath + 'getCountries').success(function(data) {
	    $scope.countries = data;
	    $http.get(contextPath + 'getAgency/' + $scope.id).success(function(data) {
		    $scope.agency = data;
		});
	});
});