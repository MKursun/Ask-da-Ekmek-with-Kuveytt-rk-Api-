/*
 *  KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 *   Developed under MIT Licence
 *   Copyright (c) 2018
 *
 *   Author : Fikri Aydemir
 *   Last Modified at : 17.04.2018 17:10
 *
 *
 */

package com.example.askdaekmek.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.askdaekmek.api.ResponseHandlingFacade;
import com.example.askdaekmek.api.util.AccessTokenResponseBean;
import com.example.askdaekmek.util.Constants;

public class AccessTokenResponseBroadcastReceiver<T extends Activity & ResponseHandlingFacade> extends BroadcastReceiver {
    private T mResponseAcceptorActivity;

    public AccessTokenResponseBroadcastReceiver(T responseAcceptorActivity){
        mResponseAcceptorActivity = responseAcceptorActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AccessTokenResponseBean responseBean = (AccessTokenResponseBean)intent.getSerializableExtra(Constants.KT_ACCESS_TOKEN_RETRIEVAL_SERVICE_PAYLOAD);
        mResponseAcceptorActivity.onAccessTokenReceived(responseBean);
    }
}
