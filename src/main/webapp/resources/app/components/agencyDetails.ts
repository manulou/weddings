import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {Agency} from "./model/agency";
import {AgenciesService} from "./services/agenciesService";
import {Configuration} from "./app.constants";
import {ActivatedRoute} from "@angular/router";
import {CategoriesService} from "./services/categoriesService";
import {AttributesService} from "./services/attributesService";
import {Category} from "./model/category";
import {Attribute} from "./model/attribute";
import {Package} from "./model/package";
import {ImagesService} from "./services/imagesService";
import {Image} from "./model/image";
declare var $:any;

@Component({
    selector: 'agencyDetails',
    providers: [AgenciesService, CategoriesService, AttributesService, ImagesService, Configuration],
    templateUrl: 'resources/js/angular/app/html/agencyDetails.html'
})
export class AgencyDetailsComponent implements OnInit {
    public agency : Agency;
    public categories : Category[];
    public attributes : Attribute[];
    public images : Image[];

    constructor(private agenciesService: AgenciesService,
                private categoriesService: CategoriesService,
                private attributesService: AttributesService,
                private imagesService: ImagesService,
                private changeDetector: ChangeDetectorRef,
                private route: ActivatedRoute) {}

    ngOnInit() {
        this.imagesService.getThumbnails(this.route.snapshot.params['id'])
            .subscribe(images => this.images = images);
        this.categoriesService.getAll()
            .subscribe(categories => this.categories = categories);
        this.attributesService.getAll()
            .subscribe(attributes => this.attributes = attributes);
        this.agenciesService.get(this.route.snapshot.params['id'])
            .subscribe(agency => {
                this.agency = agency;
                this.changeDetector.detectChanges();
                initMasonry();
            });
    }

    private hasAttributesForCategory(weddingPackage : Package, category : Category): boolean {
        for (var i = 0; i < weddingPackage.attributes.length; i++) {
            if (weddingPackage.attributes[i].category.id == category.id) {
                return true;
            }
        }
        return false;
    }
}

function initMasonry() {
    $('.categories').masonry({itemSelector: '.category', percentPosition: true, columnWidth: '.category' });
}