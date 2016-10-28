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
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
var agencies_1 = require('./components/agencies');
var agencyDetails_1 = require("./components/agencyDetails");
var app_routes_1 = require("./app.routes");
var wedding_1 = require("./wedding");
var secureAgencies_1 = require("./components/secureAgencies");
var navigation_1 = require("./components/navigation");
var editAgency_1 = require("./components/editAgency");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule, forms_1.FormsModule, http_1.HttpModule, router_1.RouterModule.forRoot(app_routes_1.WeddingAppRoutes)],
            declarations: [wedding_1.WeddingAppComponent, navigation_1.NavigationComponent, agencies_1.AgenciesComponent, agencyDetails_1.AgencyDetailsComponent, secureAgencies_1.SecureAgenciesComponent, editAgency_1.EditAgencyComponent],
            bootstrap: [wedding_1.WeddingAppComponent, navigation_1.NavigationComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map