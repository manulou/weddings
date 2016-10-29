import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import {AgenciesComponent}   from './components/agencies';
import {AgencyDetailsComponent} from "./components/agencyDetails";
import {AppRoutingModule} from "./app.routes";
import {WeddingAppComponent} from "./wedding";
import {SecureAgenciesComponent} from "./components/secureAgencies";
import {NavigationComponent} from "./components/navigation";
import {EditAgencyComponent} from "./components/editAgency";

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpModule, AppRoutingModule ],
    declarations: [ WeddingAppComponent, NavigationComponent, AgenciesComponent, AgencyDetailsComponent, SecureAgenciesComponent, EditAgencyComponent ],
    bootstrap:    [ WeddingAppComponent, NavigationComponent ]
})
export class AppModule { }