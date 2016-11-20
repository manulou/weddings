
function handleProgress(e) {
	if (e.lengthComputable) {  
    	var percentComplete = Math.round(e.loaded / e.total * 100);
    	var percentString = String(percentComplete);
        $('.progress-bar').attr('aria-valuenow', percentString);
        $('.progress-bar').css('width', percentString + '%');
        $('.sr-only').html(percentString + "% Complete");
	}
}

function showError(message) {
    hideErrors();
    var html = '<div class="alert alert-danger" role="alert">' +
        '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span>' +
        '<span id="errorMessage">' + message + '</span>' +
        '</div>';
    $(html).insertBefore('.main');
}

function showInfo(message) {
    hideErrors();
    var html = '<div class="alert alert-success fade in" role="alert">' +
        '<a href="#" class="close" data-dismiss="alert">&times;</a>' +
        '<strong>Success!</strong> <span id="infoMessage">' + message + '</span>' +
        '</div>';
    $(html).insertBefore('.main');
}

function hideErrors() {
    $('.alert').remove();
}

function initMasonry(categories) {
    categories.masonry({itemSelector: '.category', percentPosition: true, columnWidth: '.category' });
}

function togglePackage(tr) {
    var categoriesDiv = $(tr).parent().parent().parent().next();
    if (categoriesDiv.hasClass('hidden')) {
        categoriesDiv.removeClass('hidden');
    } else {
        categoriesDiv.addClass('hidden');
    }

    initMasonry(categoriesDiv.find('.categories'));
    return false;
}
