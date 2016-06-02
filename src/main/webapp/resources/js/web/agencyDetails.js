weddingsApp.controller('agencyDetailsController', function ($scope, $rootScope, $http, ContextService) {
	var contextPath = ContextService.path;
	
	$scope.init = function(id) {
		$scope.id = parseInt(id);

		$http.get(contextPath + 'agency/' + $scope.id + '/thumbnails').success(function(data) {
			$scope.images = data;
		});
		$http.get(contextPath + 'getCategories').success(function(data) {
			$scope.categories = data;
		});
		$http.get(contextPath + 'getAttributes').success(function(data) {
			$scope.attributes = data;
		});

		$http.get(contextPath + 'getAgency/' + $scope.id).success(function(data) {
			$scope.agency = data;
		});

		$scope.$watch('agency', function(newValue, oldValue) {
			$scope.$evalAsync(function() { $('.categories').masonry({itemSelector: '.category', percentPosition: true, columnWidth: '.category' }); } );
		});
	}
});
