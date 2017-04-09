/**
 * Created by Amritansh on 4/9/2017.
 */
(function () {
    angular
        .module("Cleanhood")
        .factory("Service", Service);

    function Service($http) {
        var api={
            findAllEvents:findAllEvents
        }
        return api;

        function findAllEvents() {
            return $http.get("/api/event");
        }
    }
})();