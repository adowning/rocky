cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "cordova-plugin-device.device",
    "file": "plugins/cordova-plugin-device/www/device.js",
    "pluginId": "cordova-plugin-device",
    "clobbers": [
      "device"
    ]
  },
  {
    "id": "cordova-plugin-dialogs.notification",
    "file": "plugins/cordova-plugin-dialogs/www/notification.js",
    "pluginId": "cordova-plugin-dialogs",
    "merges": [
      "navigator.notification"
    ]
  },
  {
    "id": "cordova-plugin-dialogs.notification_android",
    "file": "plugins/cordova-plugin-dialogs/www/android/notification.js",
    "pluginId": "cordova-plugin-dialogs",
    "merges": [
      "navigator.notification"
    ]
  },
  {
    "id": "cordova-plugin-globalization.GlobalizationError",
    "file": "plugins/cordova-plugin-globalization/www/GlobalizationError.js",
    "pluginId": "cordova-plugin-globalization",
    "clobbers": [
      "window.GlobalizationError"
    ]
  },
  {
    "id": "cordova-plugin-globalization.globalization",
    "file": "plugins/cordova-plugin-globalization/www/globalization.js",
    "pluginId": "cordova-plugin-globalization",
    "clobbers": [
      "navigator.globalization"
    ]
  },
  {
    "id": "cordova-plugin-mfp.mfp",
    "file": "plugins/cordova-plugin-mfp/bootstrap.js",
    "pluginId": "cordova-plugin-mfp",
    "runs": true
  },
  {
    "id": "cordova-plugin-mfp-jsonstore.jsonstore",
    "file": "plugins/cordova-plugin-mfp-jsonstore/bootstrap.js",
    "pluginId": "cordova-plugin-mfp-jsonstore",
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
  "cordova-plugin-okhttp": "2.0.0",
  "cordova-plugin-mfp": "8.0.2018090311",
  "cordova-plugin-mfp-jsonstore": "8.0.2018080605"
};
// BOTTOM OF METADATA
});