cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/cordova-plugin-device/www/device.js",
        "id": "cordova-plugin-device.device",
        "pluginId": "cordova-plugin-device",
        "clobbers": [
            "device"
        ]
    },
    {
        "file": "plugins/cordova-plugin-device/src/browser/DeviceProxy.js",
        "id": "cordova-plugin-device.DeviceProxy",
        "pluginId": "cordova-plugin-device",
        "runs": true
    },
    {
        "file": "plugins/cordova-plugin-dialogs/www/notification.js",
        "id": "cordova-plugin-dialogs.notification",
        "pluginId": "cordova-plugin-dialogs",
        "merges": [
            "navigator.notification"
        ]
    },
    {
        "file": "plugins/cordova-plugin-dialogs/www/browser/notification.js",
        "id": "cordova-plugin-dialogs.notification_browser",
        "pluginId": "cordova-plugin-dialogs",
        "merges": [
            "navigator.notification"
        ]
    },
    {
        "file": "plugins/cordova-plugin-globalization/www/GlobalizationError.js",
        "id": "cordova-plugin-globalization.GlobalizationError",
        "pluginId": "cordova-plugin-globalization",
        "clobbers": [
            "window.GlobalizationError"
        ]
    },
    {
        "file": "plugins/cordova-plugin-globalization/www/globalization.js",
        "id": "cordova-plugin-globalization.globalization",
        "pluginId": "cordova-plugin-globalization",
        "clobbers": [
            "navigator.globalization"
        ]
    },
    {
        "file": "plugins/cordova-plugin-globalization/www/browser/moment.js",
        "id": "cordova-plugin-globalization.moment",
        "pluginId": "cordova-plugin-globalization",
        "runs": true
    },
    {
        "file": "plugins/cordova-plugin-globalization/src/browser/GlobalizationProxy.js",
        "id": "cordova-plugin-globalization.GlobalizationProxy",
        "pluginId": "cordova-plugin-globalization",
        "runs": true
    },
    {
        "file": "plugins/cordova-plugin-mfp/src/browser/bootstrap.js",
        "id": "cordova-plugin-mfp.mfp",
        "pluginId": "cordova-plugin-mfp",
        "runs": true
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.3",
    "cordova-plugin-device": "2.0.2",
    "cordova-plugin-dialogs": "2.0.1",
    "cordova-plugin-globalization": "1.11.0",
    "cordova-plugin-mfp": "8.0.2018090311",
    "cordova-plugin-mfp-jsonstore": "8.0.2018080605"
}
// BOTTOM OF METADATA
});