import {Country} from "./country";
import {Package} from "./package";
/**
 * Created by Vlad on 16/10/2016.
 */
export class Agency {
    public id : number;
    public name : string;
    public country : Country;
    public email : string;
    public phone : string;
    public seolink : string;
    public packages : Package[];
}