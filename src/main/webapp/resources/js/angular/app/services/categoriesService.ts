/**
 * Created by Vlad on 16/10/2016.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers, URLSearchParams} from '@angular/http';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Rx';
import {AgenciesList} from '../model/agenciesList';
import {Configuration} from '../app.constants';
import {PageInfo} from "../model/pageInfo";
import {Agency} from "../model/agency";

@Injectable()
export class AgenciesService {

    private actionUrl: string;
    private headers: Headers;

    constructor(private _http: Http, private configuration: Configuration) {
        this.actionUrl = configuration.ServerWithApiUrl;

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }

    public getAll = (pageInfo: PageInfo): Observable<AgenciesList> => {
        let params = new URLSearchParams();
        params.set('page', pageInfo.page.toString());
        params.set('sortField', pageInfo.sortField);
        params.set('sortDirection', pageInfo.sortDirection);
        return this._http.get(this.actionUrl + 'searchAgencies', { search : params })
            .map(mapAgencies).catch(this.handleError);
    }

    public get = (id: number): Observable<Agency> => {
        return this._http.get(this.actionUrl + 'getAgency/' + id)
            .map(mapAgency).catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}

function mapAgencies(response:Response): AgenciesList {
    let data = response.json();
    let agencies = data.list.map(toAgency)
    let agenciesList = <AgenciesList>({
        currentPage : data.currentPage,
        lastPage : data.lastPage,
        pageSize : data.pageSize,
        totalResults : data.totalResults,

        sortField : data.sortField,
        sortDirection : data.sortDirection,
        list : agencies
    });
    return agenciesList;
}

function mapAgency(response:Response): Agency {
    return toAgency(response.json());
}

function toAgency(r:any): Agency{
    let agency = <Agency>({
        id: r.id,
        name: r.name,
        countryName: r.country.name,
        email: r.email,
        phone: r.phone
    });
    console.debug('Parsed agency:', agency);
    return agency;
}