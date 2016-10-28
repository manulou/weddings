function uploadFile(file, uploadUrl) {
	hideErrors();
	var fileName;
    var fileSize;
    var fileType;
    if ('name' in file) {
        fileName = file.name;
    } else {
        fileName = file.fileName;
    }
    if ('size' in file) {
        fileSize = file.size;
    } else {
        fileSize = file.fileSize;
    }
    if ('type' in file) {
        fileType = file.type;
    } else {
        fileType = file.fileType;
    }
    if(fileName.length < 1) {
    } else if(fileSize > 20000000) {
    	showError('Uploaded file ' + fileName + ' is too big, the max allowed size is 20 000 000 bytes');
    } else if(fileType != 'application/pdf') {
        showError('Uploaded file ' + fileName + ' is not a pdf file');
    } else { 
    	$('.progress').show();
        var formData = new FormData();
        formData.append('file', file);
        $.ajax({
            url: uploadUrl,
            type: 'POST',
            xhr: function() {
                myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload){ // if upload property exists
                    myXhr.upload.addEventListener('progress', handleProgress, false);
                }
                return myXhr;
            },
            //Ajax events
            success: completeHandler = function(data) {
                if (data.status == 'OK') {
                	$('#uploadedFile').html(data.fileName);
                	$('#strings_found').html(data.stringsFound.length)
                	$('.js-upload-finished').show();
                	$('.progress').hide();
                	$('#legacyUploadField').hide();
                	
                	$('#find').typeahead('destroy');
                	
                	var stringsEngine = new Bloodhound({
            		  datumTokenizer: Bloodhound.tokenizers.whitespace,
            		  queryTokenizer: Bloodhound.tokenizers.whitespace,
            		  local: data.stringsFound
            		});
                	
                	$('#find').typeahead({
            		  hint: true,
            		  highlight: true,
            		  minLength: 1
            		},
            		{
            		  name: 'stringsEngine',
            		  source: stringsEngine
            		});
                } else {
                	showError(data.errorMessage);
                }
            },
            error: errorHandler = function() {
            	showError('Communication error with the server. Please try again in a few minutes');
            },
            // Form data
            data: formData,
            dataType:'json',
            cache: false,
            contentType: false,
            processData: false
        }, 'json');
    }
}

function handleProgress(e) {
	if (e.lengthComputable) {  
    	var percentComplete = Math.round(e.loaded / e.total * 100);
    	var percentString = String(percentComplete);
        $('.progress-bar').attr('aria-valuenow', percentString);
        $('.progress-bar').css('width', percentString + '%');
        $('.sr-only').html(percentString + "% Complete");
	}
}



function validate() {
	hideErrors();
	if ($('#find').val() == '') {
		showError('The text you want to replace cannot be empty');
		$('#findField').addClass('has-error');
		return false;
	}
	if($("#pdfFile").val() == '' && $('#uploadedFile').html() == '') {
		showError('Please upload your pdf file');
		$('#legacyUploadField').addClass('has-error');
		return false;
	}
	return true;
}