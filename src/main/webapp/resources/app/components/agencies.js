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
var core_1 = require('@angular/core');
var agenciesService_1 = require("./services/agenciesService");
var agenciesList_1 = require("./model/agenciesList");
var app_constants_1 = require("./app.constants");
var pageInfo_1 = require("./model/pageInfo");
var AgenciesComponent = (function () {
    function AgenciesComponent(agenciesService) {
        this.agenciesService = agenciesService;
    }
    AgenciesComponent.prototype.ngOnInit = function () {
        this.agencies = new agenciesList_1.AgenciesList();
        this.agencies.list = [];
        this.pageInfo = new pageInfo_1.PageInfo();
        this.pageInfo.page = 0;
        this.pageInfo.sortField = 'id';
        this.pageInfo.sortDirection = 'asc';
        this.getAllItems();
    };
    AgenciesComponent.prototype.getAllItems = function () {
        var _this = this;
        this.agenciesService
            .getAll(this.pageInfo)
            .subscribe(function (data) { return _this.agencies = data; }, function (error) { return console.log(error); }, function () { return console.log('Get all agencies complete'); });
    };
    AgenciesComponent.prototype.searchAgencies = function (pageNumber) {
        if (pageNumber >= 0 && pageNumber < this.agencies.lastPage) {
            this.pageInfo.page = pageNumber;
            this.getAllItems();
        }
    };
    AgenciesComponent = __decorate([
        core_1.Component({
            selector: 'agencies',
            providers: [agenciesService_1.AgenciesService, app_constants_1.Configuration],
            templateUrl: 'resources/js/angular/app/html/agencies.html'
        }), 
        __metadata('design:paramtypes', [agenciesService_1.AgenciesService])
    ], AgenciesComponent);
    return AgenciesComponent;
}());
exports.AgenciesComponent = AgenciesComponent;
//# sourceMappingURL=agencies.js.map