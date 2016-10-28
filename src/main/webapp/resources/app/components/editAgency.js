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
var agency_1 = require("../model/agency");
var agenciesService_1 = require("../services/agenciesService");
var app_constants_1 = require("../app.constants");
var router_1 = require("@angular/router");
var categoriesService_1 = require("../services/categoriesService");
var attributesService_1 = require("../services/attributesService");
var category_1 = require("../model/category");
var attribute_1 = require("../model/attribute");
var package_1 = require("../model/package");
var imagesService_1 = require("../services/imagesService");
var countriesService_1 = require("../services/countriesService");
var forms_1 = require("@angular/forms");
var packageAttribute_1 = require("../model/packageAttribute");
var packagesService_1 = require("../services/packagesService");
var notificationService_1 = require("../services/notificationService");
var EditAgencyComponent = (function () {
    function EditAgencyComponent(agenciesService, categoriesService, attributesService, imagesService, countriesService, packagesService, notifier, route) {
        this.agenciesService = agenciesService;
        this.categoriesService = categoriesService;
        this.attributesService = attributesService;
        this.imagesService = imagesService;
        this.countriesService = countriesService;
        this.packagesService = packagesService;
        this.notifier = notifier;
        this.route = route;
    }
    EditAgencyComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.agency = new agency_1.Agency();
        var id = this.route.snapshot.params['id'];
        this.newAgency = (id === 'new');
        this.newPackage = new package_1.Package();
        this.newPackage.price = 0;
        this.newCategory = new category_1.Category();
        this.newAttribute = new packageAttribute_1.PackageAttribute();
        this.newAttribute.attribute = new attribute_1.Attribute();
        if (!this.newAgency) {
            this.id = this.route.snapshot.params['id'];
            this.imagesService.getThumbnails(id)
                .subscribe(function (images) { return _this.images = images; });
            this.agenciesService.get(id)
                .subscribe(function (agency) {
                _this.agency = agency;
            });
        }
        else {
            this.images = [];
        }
        this.categoriesService.getAll()
            .subscribe(function (categories) { return _this.categories = categories; });
        this.attributesService.getAll()
            .subscribe(function (attributes) { return _this.attributes = attributes; });
        this.countriesService.getAll()
            .subscribe(function (countries) { return _this.countries = countries; });
    };
    EditAgencyComponent.prototype.isNewAgency = function () {
        return this.newAgency;
    };
    EditAgencyComponent.prototype.save = function (agency) {
        var _this = this;
        if (this.agencyForm.valid) {
            var newAgency = true;
            this.agenciesService.save(this.agency)
                .subscribe(function (agency) {
                _this.agency = agency;
                _this.notifier.showInfo('Agency was saved successfully!');
            });
        }
    };
    ;
    EditAgencyComponent.prototype.deleteImage = function (imageToDelete) {
        var _this = this;
        this.imagesService.delete(imageToDelete.id).subscribe(function () {
            for (var i = _this.images.length - 1; i >= 0; i--) {
                var image = _this.images[i];
                if (image.id === imageToDelete.id) {
                    _this.images.splice(i, 1);
                }
            }
        });
    };
    ;
    EditAgencyComponent.prototype.addPackage = function (newPackage) {
        if (newPackage.name != '') {
            this.agency.packages.push(newPackage);
            this.newPackage = new package_1.Package();
            this.newPackage.price = 0;
        }
    };
    ;
    EditAgencyComponent.prototype.deletePackage = function (pkg) {
        var _this = this;
        if (confirm('Are you sure you want to delete package "' + pkg.name + '"?')) {
            if (pkg.id) {
                this.packagesService.delete(pkg.id).subscribe(function () {
                    for (var i = _this.agency.packages.length - 1; i >= 0; i--) {
                        if (_this.agency.packages[i].id === pkg.id) {
                            _this.agency.packages.splice(i, 1);
                        }
                    }
                });
            }
            else {
                this.agency.packages.splice(this.agency.packages.length - 1, 1);
            }
        }
    };
    ;
    EditAgencyComponent.prototype.addCategory = function (newCategory) {
        var _this = this;
        if (newCategory.name != '') {
            for (var _i = 0, _a = this.categories; _i < _a.length; _i++) {
                var cat = _a[_i];
                if (cat.name == newCategory.name) {
                    this.notifier.showError('Category already exists');
                    return;
                }
            }
            this.categoriesService.save(newCategory).subscribe(function (category) {
                _this.categories.push(category);
                _this.newCategory = new category_1.Category();
            });
        }
    };
    ;
    EditAgencyComponent.prototype.addAttribute = function (newAttribute, pkg, category) {
        var _this = this;
        if (newAttribute.attribute.name != '') {
            if (typeof pkg.attributes === "undefined") {
                pkg.attributes = [];
            }
            newAttribute.category = new category_1.Category;
            newAttribute.category.id = category.id;
            newAttribute.attribute.categoryId = category.id;
            for (var _i = 0, _a = this.attributes; _i < _a.length; _i++) {
                var attr = _a[_i];
                if (attr.name == newAttribute.attribute.name) {
                    newAttribute.attribute.id = attr.id;
                    break;
                }
            }
            if (typeof newAttribute.attribute.id === "undefined") {
                this.attributesService.save(this.newAttribute.attribute).subscribe(function (attribute) {
                    _this.attributes.push(attribute);
                    newAttribute.attribute.id = attribute.id;
                });
            }
            pkg.attributes.push(newAttribute);
            this.newAttribute = new packageAttribute_1.PackageAttribute();
            this.newAttribute.attribute = new attribute_1.Attribute();
        }
    };
    ;
    EditAgencyComponent.prototype.packageHasAttribute = function (pkg, attribute) {
        if (typeof pkg.attributes === "undefined") {
            pkg.attributes = [];
        }
        for (var i = 0; i < pkg.attributes.length; i++) {
            if (pkg.attributes[i].attribute.id == attribute.id) {
                return true;
            }
        }
        return false;
    };
    ;
    EditAgencyComponent.prototype.toggleAttributeInPackage = function (attribute, pkg, category) {
        if (typeof pkg.attributes === "undefined") {
            pkg.attributes = [];
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
            var newAttribute = new packageAttribute_1.PackageAttribute();
            newAttribute.category = category;
            newAttribute.attribute = attribute;
            pkg.attributes.push(newAttribute);
        }
    };
    ;
    __decorate([
        core_1.ViewChild('agencyForm'), 
        __metadata('design:type', forms_1.NgForm)
    ], EditAgencyComponent.prototype, "agencyForm", void 0);
    EditAgencyComponent = __decorate([
        core_1.Component({
            selector: 'editAgency',
            providers: [agenciesService_1.AgenciesService, categoriesService_1.CategoriesService, attributesService_1.AttributesService, imagesService_1.ImagesService, countriesService_1.CountriesService, packagesService_1.PackagesService, notificationService_1.NotificationComponent, app_constants_1.Configuration],
            templateUrl: 'resources/app/html/editAgency.html'
        }), 
        __metadata('design:paramtypes', [agenciesService_1.AgenciesService, categoriesService_1.CategoriesService, attributesService_1.AttributesService, imagesService_1.ImagesService, countriesService_1.CountriesService, packagesService_1.PackagesService, notificationService_1.NotificationComponent, router_1.ActivatedRoute])
    ], EditAgencyComponent);
    return EditAgencyComponent;
}());
exports.EditAgencyComponent = EditAgencyComponent;
//# sourceMappingURL=editAgency.js.map