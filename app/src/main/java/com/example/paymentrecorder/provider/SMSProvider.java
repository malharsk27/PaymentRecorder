package com.example.paymentrecorder.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSProvider extends BroadcastReceiver {

    private static final String TAG = SMSProvider.class.getSimpleName();
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (SMS_RECEIVED.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (null == pdus || pdus.length == 0) {
                    return;
                }

                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], bundle.getString("format"));
                    sb.append(messages[i].getMessageBody());
                }

                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();

                Log.d(TAG, "onReceive: " + "SMS from " + sender + " with body " + message);
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
