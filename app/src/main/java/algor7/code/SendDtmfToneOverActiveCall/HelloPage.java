package algor7.code.SendDtmfToneOverActiveCall;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.internal.telecom.IInCallAdapter;

//This is DTMF dialer activity
public class HelloPage extends Activity implements View.OnClickListener {

    private Button DTMF_b1, DTMF_b2, DTMF_b3, DTMF_b4, DTMF_b5, DTMF_b6, DTMF_b7, DTMF_b8, DTMF_b9, DTMF_b0, DTMF_bS, DTMF_bP;
    private TextView DTMFToneDialedTextView;
    private String DTMFToneDialedNumber = "";
    private String callId;
    private IInCallAdapter mIInCallAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hello_page);

        mIInCallAdapter = (IInCallAdapter) this;

        DTMF_b1 = (Button) findViewById(R.id.id_playDTMF1);
        DTMF_b2 = (Button) findViewById(R.id.id_playDTMF2);
        DTMF_b3 = (Button) findViewById(R.id.id_playDTMF3);
        DTMF_b4 = (Button) findViewById(R.id.id_playDTMF4);
        DTMF_b5 = (Button) findViewById(R.id.id_playDTMF5);
        DTMF_b6 = (Button) findViewById(R.id.id_playDTMF6);
        DTMF_b7 = (Button) findViewById(R.id.id_playDTMF7);
        DTMF_b8 = (Button) findViewById(R.id.id_playDTMF8);
        DTMF_b9 = (Button) findViewById(R.id.id_playDTMF9);
        DTMF_bS = (Button) findViewById(R.id.id_playDTMFStar);
        DTMF_b0 = (Button) findViewById(R.id.id_playDTMF0);
        DTMF_bP = (Button) findViewById(R.id.id_playDTMFPound);

        callId = MainActivity.telecomCallId;  //Getting telecom Call ID from a static variable in a MainActivity

        DTMFToneDialedTextView = (TextView) findViewById(R.id.id_DTMFToneDialedKey);

        DTMF_b1.setOnClickListener(this);
        DTMF_b2.setOnClickListener(this);
        DTMF_b3.setOnClickListener(this);
        DTMF_b4.setOnClickListener(this);
        DTMF_b5.setOnClickListener(this);
        DTMF_b6.setOnClickListener(this);
        DTMF_b7.setOnClickListener(this);
        DTMF_b8.setOnClickListener(this);
        DTMF_b9.setOnClickListener(this);
        DTMF_bS.setOnClickListener(this);
        DTMF_b0.setOnClickListener(this);
        DTMF_bP.setOnClickListener(this);

    }

    //This method updated the screen what we dial
    public void dialedNumberUpdater(String key) {

        DTMFToneDialedNumber = DTMFToneDialedNumber + key;
        DTMFToneDialedTextView.setText(DTMFToneDialedNumber);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_playDTMF1:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '1');
                    dialedNumberUpdater("1");
                } catch (Exception e) {
                    dialedNumberUpdater("w");  //updating 'w' into the screen so that user can see it is not working.
                }
                break;

            case R.id.id_playDTMF2:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '2');
                    dialedNumberUpdater("2");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF3:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '3');
                    dialedNumberUpdater("3");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF4:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '4');
                    dialedNumberUpdater("4");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF5:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '5');
                    dialedNumberUpdater("5");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF6:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '6');
                    dialedNumberUpdater("6");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF7:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '7');
                    dialedNumberUpdater("7");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF8:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '8');
                    dialedNumberUpdater("8");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF9:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '9');
                    dialedNumberUpdater("9");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMFStar:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '*');
                    dialedNumberUpdater("*");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMF0:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '0');
                    dialedNumberUpdater("0");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

            case R.id.id_playDTMFPound:
                try {
                    mIInCallAdapter.playDtmfTone(callId, '#');
                    dialedNumberUpdater("#");
                } catch (Exception e) {
                    dialedNumberUpdater("w");
                }
                break;

        }

    }

}

