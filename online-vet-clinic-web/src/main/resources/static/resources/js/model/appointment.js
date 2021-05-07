var app = app || {};

app.appointment = (function() {
    function Appointment(requester) {
        this._requester = requester;
        this._serviceUrl = '/appointment/'
    }

    Appointment.prototype.getBookedAppointmentsForDay = function (date) {
        var queryUrl = this._serviceUrl + 'getForDate?date=' + date;

        return this._requester.get(queryUrl, date);
    };

    return {
        load: function(requester) {
            return new Appointment(requester);
        }
    }
}());