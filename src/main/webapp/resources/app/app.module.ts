import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import {AgencyDetailsComponent} from "./components/agencyDetails";
import {AppRoutingModule} from "./app.routes";
import {WeddingAppComponent} from "./wedding";
import {SecureAgenciesComponent} from "./components/secureAgencies";
import {NavigationComponent} from "./components/navigation";
import {EditAgencyComponent} from "./components/editAgency";
import {PackageSearchComponent} from "./components/packageSearch";

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpModule, AppRoutingModule ],
    declarations: [ WeddingAppComponent, NavigationComponent, PackageSearchComponent, AgencyDetailsComponent, SecureAgenciesComponent, EditAgencyComponent ],
    bootstrap:    [ WeddingAppComponent, NavigationComponent ]
})
export class AppModule { }