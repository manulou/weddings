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
var AgenciesService = (function () {
    function AgenciesService(_http, configuration) {
        var _this = this;
        this._http = _http;
        this.configuration = configuration;
        this.getAll = function (pageInfo) {
            return _this.findAgencies(pageInfo, _this.configuration.ServerWithApiUrl);
        };
        this.getAllSecure = function (pageInfo) {
            return _this.findAgencies(pageInfo, _this.configuration.SecureServerWithApiUrl);
        };
        this.findAgencies = function (pageInfo, actionUrl) {
            var params = new http_1.URLSearchParams();
            params.set('page', pageInfo.page.toString());
            params.set('sortField', pageInfo.sortField);
            params.set('sortDirection', pageInfo.sortDirection);
            return _this._http.get(actionUrl + 'searchAgencies', { search: params })
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.get = function (id) {
            return _this._http.get(_this.configuration.ServerWithApiUrl + 'getAgency/' + id)
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.getBySeolink = function (seolink) {
            return _this._http.get(_this.configuration.ServerWithApiUrl + 'getAgencyBySeolink/' + seolink)
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.save = function (agency) {
            return _this._http.post(_this.configuration.SecureServerWithApiUrl + 'saveAgency', agency)
                .map(function (response) { return response.json(); }).catch(_this.handleError);
        };
        this.delete = function (id) {
            return _this._http.delete(_this.configuration.SecureServerWithApiUrl + 'deleteAgency/' + id).catch(_this.handleError);
        };
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }
    AgenciesService.prototype.handleError = function (error) {
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || 'Server error');
    };
    AgenciesService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, app_constants_1.Configuration])
    ], AgenciesService);
    return AgenciesService;
}());
exports.AgenciesService = AgenciesService;
//# sourceMappingURL=agenciesService.js.map