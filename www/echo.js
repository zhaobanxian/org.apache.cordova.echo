var cordova=require('cordova');
var echo= {
    sayHello: function (sucess, error, ar) {
        cordova.exec(sucess, error, 'Echo', 'echo', ar);
    },
    /**
     * Open a native alert dialog, with a customizable title and button text.
     *
     * @param {String} message              Message to print in the body of the alert
     * @param {Function} completeCallback   The callback that is called when user clicks on a button.
     * @param {String} title                Title of the alert dialog (default: Alert)
     * @param {String} buttonLabel          Label of the close button (default: OK)
     */
    alert: function(message, completeCallback, title, buttonLabel) {
        var _message = (typeof message === "string" ? message : JSON.stringify(message));
        var _title = (typeof title === "string" ? title : "Alert");
        var _buttonLabel = (buttonLabel && typeof buttonLabel === "string" ? buttonLabel : "OK");
        cordova.exec(completeCallback, null, "Echo", "alert", [_message, _title, _buttonLabel]);
    },
};

module.exports=echo;
//调用的方式
//echo.sayHello(sucess, error, ar)
//acho.alert(message, completeCallback, title, buttonLabel)