package  org.apache.cordova.echo;
import  org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import  org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import  org.apache.cordova.PluginResult;
import  org.json.JSONArray;
import  org.json.JSONException;
import  org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.EditText;
import android.widget.TextView;

public  class Echo extends CordovaPlugin {
    @Override
    public boolean execute(String action,  JSONArray args, CallbackContext callbackContext) throws JSONException {
            if (action.equals("echo"))  {   //action=echo
                String message = args.getString(0);  //HelloMobileWorld
                message=message;
                //this.echo(message,  callbackContext);
                callbackContext.success(message);
                return true;
            }
            else if(action.equals("alert")){
                this.alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
                return true;
            }
            else{
                callbackContext.error("no echo msg");
                return  false;
            }
    }
    /**
     * Builds and shows a native Android alert with given Strings
     * @param message           The message the alert should display
     * @param title             The title of the alert
     * @param buttonLabel       The label of the button
     * @param callbackContext   The callback context
     */
    public synchronized void alert(final String message, final String title, final String buttonLabel, final CallbackContext callbackContext) {
        final CordovaInterface cordova = this.cordova;
        Runnable runnable = new Runnable() {
            public void run() {
                AlertDialog.Builder dlg = createDialog(cordova);
                dlg.setMessage(message);
                dlg.setTitle(title);
                dlg.setCancelable(true);
                dlg.setPositiveButton(buttonLabel,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                            }
                        });
                dlg.setOnCancelListener(new AlertDialog.OnCancelListener() {
                    public void onCancel(DialogInterface dialog)
                    {
                        dialog.dismiss();
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                });
                changeTextDirection(dlg);
            };
        };
        this.cordova.getActivity().runOnUiThread(runnable);
    }

    @SuppressLint("NewApi")
    private AlertDialog.Builder createDialog(CordovaInterface cordova) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            return new AlertDialog.Builder(cordova.getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        } else {
            return new AlertDialog.Builder(cordova.getActivity());
        }
    }

    @SuppressLint("NewApi")
        private void changeTextDirection(Builder dlg){
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            dlg.create();
            AlertDialog dialog =  dlg.show();
            if (currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                TextView messageview = (TextView)dialog.findViewById(android.R.id.message);
                messageview.setTextDirection(android.view.View.TEXT_DIRECTION_LOCALE);
            }
        }
}