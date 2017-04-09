/**
 * Created by Amritansh on 4/9/2017.
 */
(function () {
    angular
        .module("Cleanhood")
        .config(Configuration);

    function Configuration($routeProvider, $httpProvider) {

        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';

        $routeProvider
            .when("/",{
                templateUrl:"assignment/views/feed.view.client.html",
                controller:"Controller",
                controllerAs:"model"
            });
    }
})();