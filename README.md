# SendDtmfToneOverActiveCall

Send DTMF Tone(s) Over An Active Call In Android

First of all thank you for showing interest on the project. This README.md contains

<b>Introduction</b>
<b>Workflow Of The App</b>
<b>Status Report Of The Project</b>
<br><br>


INTRODUCTION :
The purpose of this project is to build API(s) that we can integrate to our android app that enables us to send DTMF tone(s) over an active call. I am trying to achieve this in a simple android app. 



WORKFLOW OF THE APP :
When you launch the app you will be landed to a page where you can enter a phone number and can press call button to make a call. Pressing call button will launch default InCall app[or Telephone app] that will make the call for you. Immediately the InCall app will go into the background and our custom dialer will appear on the screen. Now you will be able to use this custom dialer to send DTMF tone over the active call in the background. That's it.

So, we can divide the project into two parts
1) Sending the call into the background                ---- Achieved.
2) Sending the DTMF tone over an active call           ---- Not Achieved Yet.



STATUS REPORT OF THE PROJECT : 

Till now i have found five APIs that seems helpfull. 

1. android.telecom.Call  (Public API)    
2. android.telecom.Phone (Hidden API)  or  ContactsContract.CommonDataKinds.Phone (Public API)
3. android.telecom.InCallAdapter               (Hidden API)
4. com.android.internal.telecom.IInCallAdapter (Internal API)
5. android.telecom.InCallService               (Public API)


android.telecom.Call 
[type][Public API]
[doc][http://developer.android.com/reference/android/telecom/Call.html]
[download][friper.in/APIs/Call.java]
[constructor]

Call(Phone phone, String telecomCallId, InCallAdapter inCallAdapter) {
        mPhone = phone;
        mTelecomCallId = telecomCallId;
        mInCallAdapter = inCallAdapter;
        mState = STATE_NEW;
}
 
[usefull method][http://developer.android.com/reference/android/telecom/Call.html#playDtmfTone(char)]



android.telecom.Phone 
[type][Hidden API]
[doc][unavailable]
[download][friper.in/APIs/Phone.java]
[constructor]

Phone(InCallAdapter inCallAdapter) {
        this.mInCallAdapter = inCallAdapter;
}


 
android.telecom.InCallAdapter 
[type][Hidden API]
[doc][unavailable]
[download][friper.in/APIs/InCallAdapter]
[constructor]
  
public InCallAdapter(IInCallAdapter adapter) {
        mAdapter = adapter;
}



com.android.internal.telecom.IInCallAdapter
[type][Internal API]
[doc][unavailable]
[download][friper.in/APIs/IInterface.java]


android.telecom.InCallService
[type][Public API]
[doc][http://developer.android.com/reference/android/telecom/InCallService.html]
[download][friper.in/InCallService]


After tracking control flow of 'playDtmfTone(char digit)', I have reached to a conclusion that interface IInCallAdapter is playing a key role here and acting as a bridge between these APIs and InCall App. So i guess we can use this 'IInCallAdapter' interface to communicate with InCall App and can triger DTMF tones over an active call. But i don't know the package name used by InCall App. Hence unable to use this IInCallAdapter interface. IInCallAdapter extends IInterface[friper.in/APIs/IInterface.java] which returns an instance of IBinder, means interprocess communication is going here using a Binder.


This is a snippet of the Android Studio Logcat when we make a phone call and dial DTMF tones using InCall app.

01-31 14:47:00.466     539-1014/? p/Telecom﹕ Call: Send playDtmfTone to connection service for call [256700921, ACTIVE, com.android.phone/com.android.services.telephony.TelephonyConnectionService, tel:**********, A, childs(0), has_parent(false), [[Capabilities: CAPABILITY_HOLD CAPABILITY_SUPPORT_HOLD CAPABILITY_MUTE]]]
01-31 14:47:00.466     539-1014/? I/Telecom﹕ Event: Call 2: START_DTMF, [c1dfd96eea8cc2b62785275bca38ac261256e278]

I searched a lot but didn't find com.android.phone & com.android.services.telephony.TelephonyConnectionService in my android device's system directory. Still searching.. 

Please also check this conversation on Stack Overflow [http://stackoverflow.com/questions/35073211/getting-java-lang-classcastexception-while-sending-dtmf-tones-over-an-active-cal]
