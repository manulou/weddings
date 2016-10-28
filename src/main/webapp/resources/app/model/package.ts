import {PackageAttribute} from "./packageAttribute";
/**
 * Created by Vlad on 17/10/2016.
 */
export class Package {
    public id : number;
    public name : string;
    public price : number;
    public attributes : PackageAttribute[];
}