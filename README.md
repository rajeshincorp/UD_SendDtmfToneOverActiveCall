# SendDtmfToneOverActiveCall

Send DTMF Tone(s) Over An Active Call In Android

First of all thank you for showing interest on the project. The main purpose of this README.md is to discribe

1) What is the purpose of this project?
2) Status report of this project.

What is the purpose of this project : 

The purpose of this project is to build API(s) that we can integrate to our android app that enables us to send DTMF tone(s) over an active
call. We are trying to achieve this in a simple android app for now. 
     
Workflow of the app :  When you launch the app you will be landed to a page where you can enter a phone number and can press call button
to make a call. Pressing call button will launch default InCall app that will make the call for you. Immidiatelly the InCall app will go 
into the background and our custom dialer appears on the screen. Now you can use this custom dialer to send DTMF tone over the active call 
in the background. That's it.

Status report of this project : 

Till now i have found five APIs that seems to helpfull. 

1. android.telecom.Call (Public API)    
2. android.telecom.Phone (Hidden API)  or  ContactsContract.CommonDataKinds.Phone (Public API)
3. android.telecom.InCallService (Public API)
4. android.telecom.InCallAdapter (Hidden API)
5. com.android.internal.telecom.IInCallAdapter (Internal API)

android.telecom.Call | link: http://developer.android.com/reference/android/telecom/Call.html

constructor: 
 
 Call(Phone phone, String telecomCallId, InCallAdapter inCallAdapter) {
        mPhone = phone;
        mTelecomCallId = telecomCallId;
        mInCallAdapter = inCallAdapter;
        mState = STATE_NEW;
  }

usefull method: playDtmfTone(char digit) | link: http://developer.android.com/reference/android/telecom/Call.html#playDtmfTone(char)

android.telecom.Phone 
