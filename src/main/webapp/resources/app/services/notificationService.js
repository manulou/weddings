"use strict";
var NotificationComponent = (function () {
    function NotificationComponent() {
    }
    NotificationComponent.prototype.showError = function (message) {
        this.hideErrors();
        var html = '<div class="alert alert-danger" role="alert">' +
            '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">&nbsp;</span>' +
            '<span id="errorMessage">' + message + '</span>' +
            '</div>';
        $(html).insertBefore('.main');
    };
    NotificationComponent.prototype.showInfo = function (message) {
        this.hideErrors();
        var html = '<div class="alert alert-success fade in" role="alert">' +
            '<a href="#" class="close" data-dismiss="alert">&times;</a>' +
            '<strong>Success!</strong> <span id="infoMessage">' + message + '</span>' +
            '</div>';
        $(html).insertBefore('.main');
    };
    NotificationComponent.prototype.hideErrors = function () {
        $('.alert').remove();
    };
    return NotificationComponent;
}());
exports.NotificationComponent = NotificationComponent;
//# sourceMappingURL=notificationService.js.map