/**
 * Created by Vlad on 16/10/2016.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Rx';
import {Configuration} from '../app.constants';

@Injectable()
export class PackagesService {

    private actionUrl: string;
    private headers: Headers;

    constructor(private _http: Http, private configuration: Configuration) {
        this.actionUrl = configuration.SecureServerWithApiUrl;

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }

    public delete = (id: number): Observable<void> => {
        return this._http.delete(this.actionUrl + 'deletePackage/' + id).catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}