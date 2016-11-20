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
var agenciesService_1 = require("../services/agenciesService");
var app_constants_1 = require("../app.constants");
var router_1 = require("@angular/router");
var categoriesService_1 = require("../services/categoriesService");
var attributesService_1 = require("../services/attributesService");
var imagesService_1 = require("../services/imagesService");
var AgencyDetailsComponent = (function () {
    function AgencyDetailsComponent(agenciesService, categoriesService, attributesService, imagesService, changeDetector, route) {
        this.agenciesService = agenciesService;
        this.categoriesService = categoriesService;
        this.attributesService = attributesService;
        this.imagesService = imagesService;
        this.changeDetector = changeDetector;
        this.route = route;
    }
    AgencyDetailsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.categoriesService.getAll()
            .subscribe(function (categories) { return _this.categories = categories; });
        this.attributesService.getAll()
            .subscribe(function (attributes) { return _this.attributes = attributes; });
        this.activePackage = this.route.snapshot.queryParams['package'];
        this.agenciesService.getBySeolink(this.route.snapshot.params['seolink'])
            .subscribe(function (agency) {
            _this.agency = agency;
            if (_this.activePackage) {
                _this.changeDetector.detectChanges();
                initMasonryForCurrentPackage(_this.activePackage);
            }
            _this.imagesService.getThumbnails(_this.agency.id)
                .subscribe(function (images) {
                _this.images = images;
                _this.changeDetector.detectChanges();
                initFotorama();
            });
        });
    };
    AgencyDetailsComponent.prototype.hasAttributesForCategory = function (weddingPackage, category) {
        for (var i = 0; i < weddingPackage.attributes.length; i++) {
            if (weddingPackage.attributes[i].category.id == category.id) {
                return true;
            }
        }
        return false;
    };
    AgencyDetailsComponent = __decorate([
        core_1.Component({
            selector: 'agencyDetails',
            providers: [agenciesService_1.AgenciesService, categoriesService_1.CategoriesService, attributesService_1.AttributesService, imagesService_1.ImagesService, app_constants_1.Configuration],
            templateUrl: 'resources/app/html/agencyDetails.html'
        }), 
        __metadata('design:paramtypes', [agenciesService_1.AgenciesService, categoriesService_1.CategoriesService, attributesService_1.AttributesService, imagesService_1.ImagesService, core_1.ChangeDetectorRef, router_1.ActivatedRoute])
    ], AgencyDetailsComponent);
    return AgencyDetailsComponent;
}());
exports.AgencyDetailsComponent = AgencyDetailsComponent;
function initFotorama() {
    $('.fotorama').find('a').each(function () {
        $(this).prop('data-thumb', $(this).prop('title'));
    });
    $('.fotorama').fotorama();
}
function initMasonryForCurrentPackage(packageId) {
    initMasonry($('#packageRow' + packageId).find('.categories'));
}
//# sourceMappingURL=agencyDetails.js.map