package com.example.damie.fiestasms.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.damie.fiestasms.model.Participant;
import com.example.damie.fiestasms.model.ParticipantModel;
import com.example.damie.fiestasms.Builder;

/**&
 * Created by damien on 22.03.17.
 */
public class MySMSReceiver extends BroadcastReceiver {


    SmsMessage smsMessage;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            try{
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] msgs = new SmsMessage[pdus.length];
                String msgBody="";
                String msg_from;
                for(int i=0; i<msgs.length; i++){
                    msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                    msg_from = msgs[i].getOriginatingAddress();
                    msgBody = msgs[i].getMessageBody();
                }
                if(msgBody.charAt(0)!='#' )
                    return;
                ParticipantModel model = ((Builder) context.getApplicationContext()).getParticipantModel();
                String[] parts = msgBody.split(";");
                String nom = parts[1];
                String prenom = parts[2];
                String boisson = parts[3];
                model.addParticipant(new Participant(nom, prenom, boisson));
                model.notifyAll();
            }catch(Exception e){
                Log.d("Broadcast :", e.getMessage());
            }
        }



    }
}
