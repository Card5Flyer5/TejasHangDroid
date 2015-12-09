package project.tejashangdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSMS extends BroadcastReceiver
{
   final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        final Bundle bundle = intent.getExtras();

        try
        {
            if(bundle != null)
            {
                Log.d("MYLOG", "Bundle: " + bundle);
                //get pdu from bundle
                final Object[] pdus = (Object[]) bundle.get("pdus");
                //get format of bundle
                String format = bundle.getString("format");


                for(int i =0; i<pdus.length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);

                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    //String senderNum = phoneNumber;
                    String message =currentMessage.getDisplayMessageBody();


                    Log.d("MYLOG", "phone: " + phoneNumber + "; message: " + message);

                    //Show alert
                    Toast.makeText(context, "Text Received from " + phoneNumber, Toast.LENGTH_LONG).show();

                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();

                    Log.d("MYLOG", "TextedWord: " + message);
                    editor.putString("TextedWord", message);
                    editor.putString("TexterPhone", phoneNumber);

                    editor.commit();
                }
            }
        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
