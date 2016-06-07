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
    	
    	$http.get(contextPath + 'secure/getAgency/' + $scope.id).success(function(data) {
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
		$http.delete(contextPath + 'secure/deleteImage/' + image.id).success(function() {
			for (var i = $scope.images.length - 1; i >= 0; i--) {
				if($scope.images[i].id === image.id) {
					$scope.images.splice(i, 1);
				}
			}
		});
	};

	$scope.addPackage = function(newPackage) {
		if(newPackage.name != '') {
			$scope.agency.packages.push(newPackage);
			$scope.newPackage = {};
			$scope.newPackage.price = 0;
		}
	};

	$scope.deletePackage = function(pkg) {
		if (confirm('Are you sure you want to delete package "' + pkg.name + '"?')) {
			if (pkg.id) {
				$http.delete(contextPath + 'secure/deletePackage/' + pkg.id).success(function () {
					for (var i = $scope.agency.packages.length - 1; i >= 0; i--) {
						if ($scope.agency.packages[i].id === pkg.id) {
							$scope.agency.packages.splice(i, 1);
						}
					}
				});
			} else {
				$scope.agency.packages.splice($scope.agency.packages.length - 1, 1);
			}
		}
	};

	$scope.addCategory = function(newCategory) {
		if(newCategory.name != '') {
			for (var i = 0; i < $scope.categories.length; i++) {
				if ($scope.categories[i].name == newCategory.name) {
					showError('Category already exists');
					return;
				}
			}
			$http.post(contextPath + 'secure/saveCategory', newCategory).success(function(data) {
				$scope.categories.push(data);
				$scope.newCategory = {};
			});
		}
	};

	$scope.addAttribute = function(newAttribute, pkg, category) {
		if(newAttribute.attribute.name != '') {
			if (angular.isUndefined(pkg.attributes)) {
				pkg.attributes = new Array();
			}
			newAttribute.category = {};
			newAttribute.category.id = category.id;
			newAttribute.attribute.categoryId = category.id;
			for (var i = 0; i < $scope.attributes.length; i++) {
				if ($scope.attributes[i].name == newAttribute.attribute.name) {
					newAttribute.attribute.id = $scope.attributes[i].id;
					break;
				}
			}
			if (angular.isUndefined(newAttribute.attribute.id)) {
				$http.post(contextPath + 'secure/saveAttribute', newAttribute.attribute).success(function(data) {
					$scope.attributes.push(data);
					newAttribute.attribute.id = data.id;
				});
			}
			pkg.attributes.push(newAttribute);
			$scope.newAttribute = {};
			$scope.newAttribute.attribute = {};
		}
	};

	$scope.packageHasAttribute = function(pkg, attribute) {
		if (angular.isUndefined(pkg.attributes)) {
			pkg.attributes = new Array();
		}
		for (var i = 0; i < pkg.attributes.length; i++) {
			if (pkg.attributes[i].attribute.id == attribute.id) {
				return true;
			}
		}
		return false;
	};

	$scope.toggleAttributeInPackage = function(attribute, pkg, category) {
		if (angular.isUndefined(pkg.attributes)) {
			pkg.attributes = new Array();
		}
		var found = false;
		for (var i = 0; i < pkg.attributes.length; i++) {
			if (pkg.attributes[i].attribute.id == attribute.id) {
				pkg.attributes.splice(i, 1);
				found = true;
				break;
			}
		}
		if (!found) {
			var newAttribute = {};
			newAttribute.category = category;
			newAttribute.attribute = attribute;
			pkg.attributes.push(newAttribute);
		}
		return false;
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