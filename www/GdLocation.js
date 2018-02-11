var exec = require('cordova/exec');
var gdLocation = function () {};

gdLocation.prototype.start = function (onceFlag, interval, success, error) {
    exec(success, error, 'GdLocation', 'start', [onceFlag, interval]);
};
gdLocation.prototype.stop = function (arg0, success, error) {
    exec(success, error, 'GdLocation', 'stop', [arg0]);
};
gdLocation.prototype.showRoute = function (ttsAppId, startObj, endObj, success, error) {
    exec(success, error, 'GdLocation', 'showRoute', [ttsAppId, startObj, endObj]);
};
gdLocation.prototype.stopRoute = function (stopReason, success, error) {
    exec(success, error, 'GdLocation', 'stopRoute', [stopReason]);
};
gdLocation.prototype.startSpeak = function (ttsAppId, speekContent, success, error) {
    exec(success, error, 'GdLocation', 'startSpeak', [ttsAppId, speekContent]);
};
gdLocation.prototype.startRecord = function (interval, minDis, maxDis, success, error) {
    exec(success, error, 'GdLocation', 'startRecord', [interval, minDis, maxDis]);
};
gdLocation.prototype.stopRecord = function (arg0, success, error) {
    exec(success, error, 'GdLocation', 'stopRecord', [arg0]);
};
gdLocation.prototype.calcNavInfo = function (oneLatLng, twoLatLng, success, error) {
    exec(success, error, 'GdLocation', 'calcNavInfo', [oneLatLng, twoLatLng]);
};

var location = new gdLocation();
module.exports = location;