/**
 * Created by Amritansh on 4/9/2017.
 */
(function () {
    angular
        .module("Cleanhood")
        .controller("Controller", Controller);

    function Controller(Service){
        var vm=this;
        function init() {
            Service
                .findAllEvents
                .success(renderEvents)
                .error(function () {
                    vm.error="Could Not Update Events";
                });
        }
        init();

        function renderEvents(Events) {
            vm.Events=Events;
        }
        var socket = io();
        socket.on("event", function (newEvent) {
           vm.newEvent=newEvent;
        });
    }
})();