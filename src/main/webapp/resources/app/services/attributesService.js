"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by Vlad on 16/10/2016.
 */
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
var Rx_1 = require('rxjs/Rx');
var app_constants_1 = require('../app.constants');
var AttributesService = (function () {
    function AttributesService(_http, configuration) {
        var _this = this;
        this._http = _http;
        this.configuration = configuration;
        this.getAll = function () {
            return _this._http.get(_this.actionUrl + 'getAttributes')
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.save = function (attribute) {
            return _this._http.post(_this.configuration.SecureServerWithApiUrl + 'saveAttribute', attribute)
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.actionUrl = configuration.ServerWithApiUrl;
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }
    AttributesService.prototype.handleError = function (error) {
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    AttributesService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, app_constants_1.Configuration])
    ], AttributesService);
    return AttributesService;
}());
exports.AttributesService = AttributesService;
//# sourceMappingURL=attributesService.js.map