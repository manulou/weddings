weddingsApp.controller('agencyDetailsController', function ($scope, $rootScope, $http) {
	var up = $('#urlPrefix').html();
	
	$scope.init = function(id) {
		$scope.id = parseInt(id);
	}
	
	$http.get(up + 'getCountries').success(function(data) {
	    $scope.countries = data;
	    $http.get(up + 'getAgency/' + $scope.id).success(function(data) {
		    $scope.agency = data;
		});
	});
});