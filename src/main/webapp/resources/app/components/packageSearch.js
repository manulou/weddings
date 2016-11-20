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
var app_constants_1 = require("../app.constants");
var packagesList_1 = require("../model/packagesList");
var packagesService_1 = require("../services/packagesService");
var countriesService_1 = require("../services/countriesService");
var searchState_1 = require("../model/helper/searchState");
var PackageSearchComponent = (function () {
    function PackageSearchComponent(packagesService, countriesService) {
        this.packagesService = packagesService;
        this.countriesService = countriesService;
    }
    PackageSearchComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.packages = new packagesList_1.PackagesList();
        this.packages.list = [];
        if (!PackageSearchComponent.searchState) {
            PackageSearchComponent.searchState = new searchState_1.SearchState();
        }
        this.searchFilter = PackageSearchComponent.searchState.searchFilter;
        this.pageInfo = PackageSearchComponent.searchState.pageInfo;
        this.countriesService.getAllForFilter()
            .subscribe(function (countries) { return _this.countries = countries; });
        this.search();
    };
    PackageSearchComponent.prototype.search = function () {
        var _this = this;
        PackageSearchComponent.searchState.searchFilter = this.searchFilter;
        PackageSearchComponent.searchState.pageInfo = this.pageInfo;
        this.packagesService
            .findPackages(PackageSearchComponent.searchState.pageInfo, PackageSearchComponent.searchState.searchFilter)
            .subscribe(function (data) { return _this.packages = data; }, function (error) { return console.log(error); }, function () { return console.log('Get all agencies complete'); });
    };
    PackageSearchComponent.prototype.changePage = function (pageNumber) {
        if (pageNumber >= 0 && pageNumber < this.packages.lastPage) {
            this.pageInfo.page = pageNumber;
            this.search();
        }
    };
    PackageSearchComponent.prototype.sort = function (column) {
        if (this.pageInfo.sortField === column) {
            this.flipSortDirection();
        }
        else {
            this.pageInfo.sortDirection = 'asc';
        }
        this.pageInfo.sortField = column;
        this.pageInfo.page = 0;
        this.search();
    };
    PackageSearchComponent.prototype.getSortDirectionArrow = function (column) {
        if (this.pageInfo.sortField === column) {
            if (this.pageInfo.sortDirection === 'asc') {
                return '?';
            }
            else {
                return '?';
            }
        }
        else {
            return '';
        }
    };
    PackageSearchComponent.prototype.flipSortDirection = function () {
        if (this.pageInfo.sortDirection === 'asc') {
            this.pageInfo.sortDirection = 'desc';
        }
        else {
            this.pageInfo.sortDirection = 'asc';
        }
    };
    PackageSearchComponent = __decorate([
        core_1.Component({
            selector: 'packageSearch',
            providers: [packagesService_1.PackagesService, countriesService_1.CountriesService, app_constants_1.Configuration],
            templateUrl: 'resources/app/html/packageSearch.html'
        }), 
        __metadata('design:paramtypes', [packagesService_1.PackagesService, countriesService_1.CountriesService])
    ], PackageSearchComponent);
    return PackageSearchComponent;
}());
exports.PackageSearchComponent = PackageSearchComponent;
//# sourceMappingURL=packageSearch.js.map