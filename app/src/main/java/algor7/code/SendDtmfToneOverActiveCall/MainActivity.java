package algor7.code.SendDtmfToneOverActiveCall;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText phoneNo;   //This stores the phone number we type in the edit text field
    private Button callBtn;     //Call button
    public static String telecomCallId;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 343;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        phoneNo = (EditText) findViewById(R.id.id_phoneNumber);
        callBtn = (Button) findViewById(R.id.id_callButton);

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CALL_PHONE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        //Listen to Call button when pressed
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!phoneNo.getText().toString().isEmpty()) {

                    telecomCallId = phoneNo.getText().toString();

                    //Make phone call
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + phoneNo.getText().toString()));
                    startActivity(i);

                    //Listen to the phone call states
                    TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    TelephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);

                    //Close this activity
                    finish();

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    class TeleListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    // CALL_STATE_IDLE;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // CALL_STATE_OFFHOOK;

                    Handler handlerHome = new Handler();
                    handlerHome.postDelayed(new Runnable() {
                        public void run() {


                            //Calling home page
                            Intent intent1 = new Intent(Intent.ACTION_MAIN);
                            intent1.addCategory(Intent.CATEGORY_HOME);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent1);

                            //Calling the DTMF dialer activity
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClassName("algor7.code.backgroundcallingapp", "algor7.code.backgroundcallingapp.HelloPage");
                            startActivity(intent);

                        }
                    }, 3000); // 3000 = 3 seconds. When we make a call through this app the dialer waits for 3 seconds before launching itself
                    //We can set it to zero if we don't want the DTMF dialer activity to wait

                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // CALL_STATE_RINGING
                    break;
                default:
                    break;
            }
        }
    }
}
