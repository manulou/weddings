"use strict";
/**
 * Created by Vlad on 17/10/2016.
 */
var agencyDetails_1 = require('./components/agencyDetails');
var agencies_1 = require('./components/agencies');
var secureAgencies_1 = require("./components/secureAgencies");
var editAgency_1 = require("./components/editAgency");
exports.WeddingAppRoutes = [
    { path: '', component: agencies_1.AgenciesComponent },
    { path: 'agency/:id', component: agencyDetails_1.AgencyDetailsComponent },
    { path: 'secure', component: secureAgencies_1.SecureAgenciesComponent },
    { path: 'secure/agency/:id', component: editAgency_1.EditAgencyComponent },
];
//# sourceMappingURL=app.routes.js.map