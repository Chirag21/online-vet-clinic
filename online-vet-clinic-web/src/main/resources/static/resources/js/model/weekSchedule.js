var app = app || {};

app.weekSchedule = (function() {
    function WeekSchedule(requester) {
        this._requester = requester;
        this._serviceUrl = '/schedule/'
    }

    WeekSchedule.prototype.getWeekSchedule = function () {
        var queryUrl = this._serviceUrl + 'week';

        return this._requester.get(queryUrl);
    };

    return {
        load: function(requester) {
            return new WeekSchedule(requester);
        }
    }
}());
