var app = app || {};

app.scheduleController = function () {
    function ScheduleController(model, appointment, viewBag) {
        this._model = model;
        this._appointment = appointment;
        this._viewBag = viewBag;
        this.currentMonday = null;
    }

    ScheduleController.prototype.loadSchedule = function () {
        var _this = this;
        this._model.getWeekSchedule()
            .then(function (data) {
                _this._viewBag.showSchedule(data);
                _this.updateAppointments();

                _this.loadBookedAppointments();
            }, function (error) {
                console.log(error);
            });
    };

    ScheduleController.prototype.updateSchedule = function () {
        this.currentMonday = this._viewBag.updateSchedule();
    };

    ScheduleController.prototype.updateAppointments = function () {
        this._viewBag.clearBookedAppointments();

        this._viewBag.updateAppointments();
    };

    ScheduleController.prototype.loadBookedAppointments = function () {
        var _this = this;

        var startDate = this.currentMonday;

        for (var dayOfWeek = 0; dayOfWeek < 7; ++dayOfWeek) {
            this._appointment.getBookedAppointmentsForDay(startDate.toLocaleDateString('en-US'))
                .then(function (data) {
                    _this._viewBag.showBookedAppointmentsForDay(data);
                }, function (error) {
                    console.log(error);
                });

            startDate.setDate(startDate.getDate() + 1);
        }
    };

    return {
        load: function (model, appointment, viewBag) {
            return new ScheduleController(model, appointment, viewBag);
        }
    }
}();