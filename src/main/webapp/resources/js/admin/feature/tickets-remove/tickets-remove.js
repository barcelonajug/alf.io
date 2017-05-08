(function() {
    'use strict';

    angular.module('adminApplication').component('ticketsRemove', {
        controller: ['AdminReservationService', 'EventService', TicketsRemoveCtrl],
        templateUrl: '../resources/js/admin/feature/tickets-remove/tickets-remove.html',
        bindings: {
            event: '<',
            reservationId: '<',
            ticketId:'<',
            onSuccess: '&',
            onCancel:'&'
        }
    });


    function TicketsRemoveCtrl(AdminReservationService, EventService) {
        var ctrl = this;

        ctrl.confirmRemove = confirmRemove;

        ctrl.$onInit = function() {
            ctrl.toRefund = {};
            AdminReservationService.paymentInfo(ctrl.event.shortName, ctrl.reservationId).then(function(res) {
                ctrl.paymentInfo = res.data.data;
            });

            AdminReservationService.getTicket(ctrl.event.shortName, ctrl.reservationId, ctrl.ticketId).then(function(res) {
                ctrl.ticket = res.data.data;
            })
        };

        function confirmRemove() {
            ctrl.submitted = true;
            return EventService.removeTickets(ctrl.event.shortName, ctrl.reservationId, [ctrl.ticketId], ctrl.toRefund).then(function() {
                ctrl.onSuccess();
            }).finally(function() {
                ctrl.submitted = false;
            });
        }
    }
})();