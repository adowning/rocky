package com.andrews.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

//import com.andrews.app.tracker.DBAdapter;

import com.ibm.MFPApplication;
import com.worklight.androidgap.api.WLInitWebFrameworkListener;
import com.worklight.androidgap.api.WLInitWebFrameworkResult;
import com.worklight.wlclient.api.WLAccessTokenListener;
import com.worklight.wlclient.api.WLAuthorizationManager;
import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResourceRequest;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;
import com.worklight.wlclient.auth.AccessToken;
public class MainActivity extends CordovaActivity implements WLInitWebFrameworkListener {
//	private DBAdapter adapter;
	WLClient WL;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		adapter = new DBAdapter(this);
        if (!((MFPApplication)this.getApplication()).hasCordovaSplashscreen()) {
//            WL.getInstance().showSplashScreen(this);
        }

        init();

		WL.getInstance().initializeWebFramework(getApplicationContext(), this);
	}

	/**
	 * The IBM MobileFirst Platform calls this method after its initialization is complete and web resources are ready to be used.
	 */
 	public void onInitWebFrameworkComplete(WLInitWebFrameworkResult result){
		if (result.getStatusCode() == WLInitWebFrameworkResult.SUCCESS) {
            super.loadUrl(WL.getInstance().getMainHtmlFilePath());
		} else {
			handleWebFrameworkInitFailure(result);
		}
	}

	private void handleWebFrameworkInitFailure(WLInitWebFrameworkResult result){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setNegativeButton(R.string.close, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which){
				finish();
			}
		});

		alertDialogBuilder.setTitle(R.string.error);
		alertDialogBuilder.setMessage(result.getMessage());
		alertDialogBuilder.setCancelable(false).create().show();
	}
}
