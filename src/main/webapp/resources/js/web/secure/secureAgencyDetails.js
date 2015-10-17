var controller = weddingsApp.controller('agencyDetailsController', function ($scope, $rootScope, $http) {
	var up = $('#urlPrefix').html();
	
	$scope.init = function(id) {
		if (id != 'New') {
			$scope.id = parseInt(id);
		}
	}
	
	$http.get(up + 'getCountries').success(function(data) {
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
    	
    	$http.get(up + 'getAgency/' + $scope.id).success(function(data) {
		    $scope.agency = data;
		});
	    $http.get(up + '/agency/' + $scope.id + '/thumbnails').success(function(data) {
		    $scope.images = data;
		});
	}
	
	$scope.save = function(agency) {
		if($scope.detailsForm.$valid) {
			var newAgency = true;
			if (!angular.isUndefined($scope.id)) {
				agency.id = $scope.id;
				newAgency = false;
			}
			$http.post(up + 'secure/saveAgency', agency).success(function(data) {
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
		$http.get(up + 'secure/deleteImage/' + image.id).success(function() {
			for (var i = $scope.images.length - 1; i >= 0; i--) {
			    if($scope.images[i].id === image.id) {
			    	$scope.images.splice(i, 1);
			    }
			}
		});
	};
	
	$scope.upload = function(file) {
		if (angular.isUndefined($scope.id)) {
			return;
		}
		var formData = new FormData();
        formData.append('file', file);
        $('#drop-zone').html('Uploading, please wait...');
		$http.post(up + 'secure/uploadImage/' + $scope.id, formData, {
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