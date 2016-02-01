# SendDtmfToneOverActiveCall

<H2>Send DTMF Tone(s) Over An Active Call In Android</H2>

<p>First of all thank you for showing interest on the project. This README.md contains</p>
<br>
<b>Introduction</b><br>
<b>Workflow Of The App</b><br>
<b>Status Report Of The Project</b><br>
<br><br>


<h3><u>INTRODUCTION :</u></h3>
<p>The purpose of this project is to build API(s) that we can integrate to our android app that enables us to send DTMF tone(s) over an active call. I am trying to achieve this in a simple android app.</p>
<br><br>


<h3><u>WORKFLOW OF THE APP :</u></h3>
<p>When you will launch the app you will be landed to a page where you can enter a phone number and can press call button to make a phone call. Pressing call button will launch default InCall app[or Telephone app] that will make the phone call for you. Immediately the InCall app will go into the background and our custom dialer will appear on the screen. Now you will be able to use this custom dialer to send DTMF tone over the active phone call in the background. That's it.</p>
<br>
So, we can divide the project into two parts<br>
1) Sending the call into the background                ---- Achieved.<br>
2) Sending the DTMF tone over an active call           ---- Not Achieved Yet.<br>
<br><br>


<h3><u>STATUS REPORT OF THE PROJECT :</u></h3> 

Till now i have found five APIs that seems helpfull. 
<br>
1. android.telecom.Call  (Public API)    <br>
2. android.telecom.Phone (Hidden API)  or  ContactsContract.CommonDataKinds.Phone (Public API) <br>
3. android.telecom.InCallAdapter               (Hidden API) <br>
4. com.android.internal.telecom.IInCallAdapter (Internal API) <br>
5. android.telecom.InCallService               (Public API) <br>
<br><br>

android.telecom.Call <br>
[type][Public API]<br>
[doc][http://developer.android.com/reference/android/telecom/Call.html]<br>
[download][http://friper.in/APIs/Call.java]<br>
[constructor]<br>
<br>
Call(Phone phone, String telecomCallId, InCallAdapter inCallAdapter) {<br>
        mPhone = phone;                  <br>
        mTelecomCallId = telecomCallId;  <br>
        mInCallAdapter = inCallAdapter;  <br>
        mState = STATE_NEW;              <br>
}<br>
<br> 
[usefull method][http://developer.android.com/reference/android/telecom/Call.html#playDtmfTone(char)]
<br><br><br>


android.telecom.Phone<br> 
[type][Hidden API]<br>
[doc][unavailable]<br>
[download][http://friper.in/APIs/Phone.java]<br>
[constructor]<Br>
<Br>
Phone(InCallAdapter inCallAdapter) {<br>
        this.mInCallAdapter = inCallAdapter;<Br>
}
<br><br><br>

 
android.telecom.InCallAdapter<br> 
[type][Hidden API]<br>
[doc][unavailable]<br>
[download][http://friper.in/APIs/InCallAdapter]<br>
[constructor]<br>
  <br>
public InCallAdapter(IInCallAdapter adapter) {<br>
        mAdapter = adapter;<br>
}
<br><br><br>


com.android.internal.telecom.IInCallAdapter<br>
[type][Internal API]<br>
[doc][unavailable]<br>
[download][http://friper.in/APIs/IInterface.java]<br>
<br>
<br>
android.telecom.InCallService<br>
[type][Public API]<br>
[doc][http://developer.android.com/reference/android/telecom/InCallService.html]<br>
[download][http://friper.in/InCallService]<br>
<br>
<br>
<p>After tracking control flow of 'playDtmfTone(char digit)', I have reached to a conclusion that interface IInCallAdapter is playing a key role here and acting as a bridge between these APIs and InCall App. So i guess we can use this 'IInCallAdapter' interface to communicate with InCall App and can triger DTMF tones over an active call. But i don't know the package name used by InCall App. Hence unable to use this IInCallAdapter interface. IInCallAdapter extends IInterface[http://friper.in/APIs/IInterface.java] which returns an instance of IBinder, means interprocess communication is going here using a Binder.</p>
<br>
<br>
<p>This is a snippet of the Android Studio Logcat when we make a phone call and dial DTMF tones using InCall app.</p>
<br>
01-31 14:47:00.466     539-1014/? p/Telecom﹕ Call: Send playDtmfTone to connection service for call [256700921, ACTIVE, com.android.phone/com.android.services.telephony.TelephonyConnectionService, tel:**********, A, childs(0), has_parent(false), [[Capabilities: CAPABILITY_HOLD CAPABILITY_SUPPORT_HOLD CAPABILITY_MUTE]]]<br>
01-31 14:47:00.466     539-1014/? I/Telecom﹕ Event: Call 2: START_DTMF, [c1dfd96eea8cc2b62785275bca38ac261256e278]
<br><br>
<p>I searched a lot but didn't find com.android.phone & com.android.services.telephony.TelephonyConnectionService in my android device's system directory. Still searching..</p> 
<br><br>
<p>Please also check this conversation on Stack Overflow</p> [http://stackoverflow.com/questions/35073211/getting-java-lang-classcastexception-while-sending-dtmf-tones-over-an-active-cal]
