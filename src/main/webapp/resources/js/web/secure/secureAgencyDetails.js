var controller = weddingsApp.controller('agencyDetailsController', function ($scope, $rootScope, $http, ContextService) {
	var contextPath = ContextService.path;
	
	$scope.init = function(id) {
		if (id != 'New') {
			$scope.id = parseInt(id);
		}
	}
	
	$http.get(contextPath + 'getCountries').success(function(data) {
	    $scope.countries = data;
	    if (!angular.isUndefined($scope.id)) {
	    	$scope.initAgency();
		}
	});
	
	$scope.initAgency = function() {
		var dropZone = $('#drop-zone')[0];
		
		var startUpload = function(files) {
    		for (var i = 0; i < event.dataTransfer.files.length; i++) {
    	        var file = event.dataTransfer.files[i];
    	        $scope.upload(file);
    		}
    	}

    	dropZone.ondrop = function(e) {
    	    e.preventDefault();
    	    this.className = 'upload-drop-zone';

    	    startUpload(e.dataTransfer.files);
    	}

    	dropZone.ondragover = function() {
    	    this.className = 'upload-drop-zone drop';
    	    return false;
    	}

    	dropZone.ondragleave = function() {
    	    this.className = 'upload-drop-zone';
    	    return false;
    	}
    	
    	$http.get(contextPath + 'getAgency/' + $scope.id).success(function(data) {
		    $scope.agency = data;
		});
	    $http.get(contextPath + 'agency/' + $scope.id + '/thumbnails').success(function(data) {
			$scope.images = data;
		});
		$http.get(contextPath + 'getCategories').success(function(data) {
			$scope.categories = data;
		});
		$http.get(contextPath + 'getAttributes').success(function(data) {
			$scope.attributes = data;
		});
		$scope.newPackage = {};
		$scope.newPackage.price = 0;
		$scope.newCategory = {};
		$scope.newAttribute = {};
		$scope.newAttribute.attribute = {};
	}
	
	$scope.save = function(agency) {
		if($scope.detailsForm.$valid) {
			var newAgency = true;
			if (!angular.isUndefined($scope.id)) {
				agency.id = $scope.id;
				newAgency = false;
			}
			$http.post(contextPath + 'secure/saveAgency', agency).success(function(data) {
			    $scope.agency = data;
			    $scope.id = $scope.agency.id;
			    if (newAgency) {
			    	$scope.initAgency();
			    }
			    showInfo('Agency was saved successfully!');
			});
		}
	};

	$scope.save = function(agency) {
		if($scope.detailsForm.$valid) {
			var newAgency = true;
			if (!angular.isUndefined($scope.id)) {
				agency.id = $scope.id;
				newAgency = false;
			}
			$http.post(contextPath + 'secure/saveAgency', agency).success(function(data) {
				$scope.agency = data;
				$scope.id = $scope.agency.id;
				if (newAgency) {
					$scope.initAgency();
				}
				showInfo('Agency was saved successfully!');
			});
		}
	};
	
	$scope.deleteImage = function(image) {
		$http.get(contextPath + 'secure/deleteImage/' + image.id).success(function() {
			for (var i = $scope.images.length - 1; i >= 0; i--) {
				if($scope.images[i].id === image.id) {
					$scope.images.splice(i, 1);
				}
			}
		});
	};

	$scope.addPackage = function(newPackage) {
		$scope.detailsForm.newPackageName.$dirty = true;
		if($scope.detailsForm.newPackageName.$valid && $scope.detailsForm.newPackagePrice.$valid) {
			$scope.agency.packages.push(newPackage);
			$scope.newPackage = {};
			$scope.newPackage.price = 0;
			$scope.detailsForm.newPackageName.$dirty = false;
		}
	};

	$scope.addCategory = function(newCategory) {
		$scope.detailsForm.newCategoryName.$dirty = true;
		if($scope.detailsForm.newCategoryName.$valid) {
			$scope.categories.push(newCategory);
			$scope.newCategory = {};
			$scope.detailsForm.newCategoryName.$dirty = false;
		}
	};

	$scope.addAttribute = function(newAttribute, pkg, category) {
		$scope.detailsForm.newAttributeName.$dirty = true;
		if($scope.detailsForm.newAttributeName.$valid) {
			newAttribute.category = {};
			newAttribute.category.id = category.id;
			for (var i = 0; i < $scope.attributes.length; i++) {
				if ($scope.attributes[i].name == newAttribute.attribute.name) {
					newAttribute.attribute.id = $scope.attributes[i].id;
					break;
				}
			}
			// TODO: handle IDs, 
			if (angular.isUndefined(pkg.attributes)) {
				pkg.attributes = new Array();
			}
			pkg.attributes.push(newAttribute);
			$scope.newAttribute = {};
			$scope.newAttribute.attribute = {};
			$scope.attributes.push(newAttribute.attribute);
			$scope.detailsForm.newAttributeName.$dirty = false;
		}
	};
	
	$scope.upload = function(file) {
		if (angular.isUndefined($scope.id)) {
			return;
		}
		var formData = new FormData();
        formData.append('file', file);
        $('#drop-zone').html('Uploading, please wait...');
		$http.post(contextPath + 'secure/uploadImage/' + $scope.id, formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function(data) {
        	$scope.images.push(data.data);
        	
        	$('.progress').hide();
        	$('#drop-zone').html('Just drag and drop images here');
		    showInfo('Image was uploaded successfully!');
		}, function(data) {
        	$('#drop-zone').html('Just drag and drop images here');
		    showError('Error uploading images!');
		});
	};
});