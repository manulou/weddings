/**
 * Created by Vlad on 17/10/2016.
 */
import { AgencyDetailsComponent } from './components/agencyDetails';
import { AgenciesComponent } from './components/agencies';
import { SecureAgenciesComponent } from "./components/secureAgencies";
import {EditAgencyComponent} from "./components/editAgency";

export const WeddingAppRoutes = [
    { path: '', component: AgenciesComponent },
    { path: 'agency/:id', component: AgencyDetailsComponent },
    { path: 'secure', component: SecureAgenciesComponent },
    { path: 'secure/agency/:id', component: EditAgencyComponent },
];