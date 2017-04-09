/**
 * Created by Amritansh on 4/9/2017.
 */
(function () {
    angular
        .module("Cleanhood")
        .controller("Controller", Controller);

    function Controller(Service){
        var vm=this;
        vm.Events = [];
        vm.numParticipants = numParticipants;

        function init() {
            Service
                .findAllEvents()
                .success(renderEvents)
                .error(function () {
                    vm.error="Could Not Update Events";
                });
            window.setInterval(updateFeed, 5000);
        }
        init();

        function renderEvents(Events) {
            vm.Events=Events;
        }

        function updateFeed() {
            Service
                .findAllEvents()
                .success(renderEvents)
                .error(function () {
                    vm.error="Could Not Update Events";
                });
        }


        function numParticipants(eid) {
            // Service
            //     .numParticipants(eid)
            //     .then(function (participants) {
            //         console.log("sbafjbjabsfjbsaf")
            //         return participants.length;
            //     })
            return Math.floor((Math.random() * 10) + 1);

        }

        // socket.on("event", function (newEvent) {
        //     console.log(newEvent)
        //    vm.Events.push(newEvent);
        // });
    }
})();