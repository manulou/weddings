/**
 * Created by Vlad on 17/10/2016.
 */
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AgencyDetailsComponent } from './components/agencyDetails';
import { AgenciesComponent } from './components/agencies';
import { SecureAgenciesComponent } from "./components/secureAgencies";
import { EditAgencyComponent } from "./components/editAgency";

const routes: Routes = [
    { path: '', component: AgenciesComponent },
    { path: 'agency/:seolink', component: AgencyDetailsComponent },
    { path: 'secure', component: SecureAgenciesComponent },
    { path: 'secure/agency/:id', component: EditAgencyComponent },
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}