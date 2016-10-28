import {Component, OnInit} from '@angular/core';
import {Agency} from "./model/agency";
import {AgenciesService} from "./services/agenciesService";
import {AgenciesList} from "./model/agenciesList";
import {Configuration} from "./app.constants";
import {PageInfo} from "./model/pageInfo";
@Component({
    selector: 'agencies',
    providers: [AgenciesService, Configuration],
    templateUrl: 'resources/js/angular/app/html/agencies.html'
})
export class AgenciesComponent implements OnInit {
    public agencies: AgenciesList;
    public pageInfo : PageInfo;

    constructor(private agenciesService: AgenciesService) {}

    ngOnInit() {
        this.agencies = new AgenciesList();
        this.agencies.list = [];

        this.pageInfo = new PageInfo();
        this.pageInfo.page = 0;
        this.pageInfo.sortField = 'id';
        this.pageInfo.sortDirection = 'asc';
        this.getAllItems();
    }

    public getAllItems(): void {
        this.agenciesService
            .getAll(this.pageInfo)
            .subscribe((data:AgenciesList) => this.agencies = data,
                error => console.log(error),
                () => console.log('Get all agencies complete'));
    }

    private searchAgencies(pageNumber : number): void {
        this.pageInfo.page = pageNumber;
        this.getAllItems();
    }
}