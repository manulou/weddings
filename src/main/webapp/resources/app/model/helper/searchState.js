"use strict";
var pageInfo_1 = require("./pageInfo");
var searchFilter_1 = require("./searchFilter");
/**
 * Created by Vlad on 04/11/2016.
 */
var SearchState = (function () {
    function SearchState() {
        this.searchFilter = new searchFilter_1.SearchFilter();
        this.pageInfo = new pageInfo_1.PageInfo();
        this.pageInfo.page = 0;
        this.pageInfo.sortField = 'price';
        this.pageInfo.sortDirection = 'asc';
    }
    return SearchState;
}());
exports.SearchState = SearchState;
//# sourceMappingURL=searchState.js.map