/**
 * Created by Amritansh on 4/9/2017.
 */
(function () {
    angular
        .module("Cleanhood")
        .controller("Controller", Controller);

    function Controller($routeParams,Service){
        var vm=this;
        // vm.userId=$routeParams.uid;
        // vm.websiteId=$routeParams.wid;
        // function init() {
        //     PageService
        //         .findAllPagesForWebsite(vm.websiteId)
        //         .success(renderPageList);
        // }
        // init();
        //
        // function renderPageList(pages) {
        //     vm.pages=pages;
        // }
    }
})();