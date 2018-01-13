var exec = require('cordova/exec');
var gdLocation = function() {};

gdLocation.prototype.start = function (onceFlag, interval, success, error) {
    exec(success, error, 'GdLocation', 'start', [onceFlag, interval]);
};
gdLocation.prototype.stop = function (arg0, success, error) {
    exec(success, error, 'GdLocation', 'stop', [arg0]);
};
gdLocation.prototype.showRoute = function (startObj, endObj, success, error) {
    exec(success, error, 'GdLocation', 'showRoute', [startObj, endObj]);
};

var location = new gdLocation();
module.exports = location;
