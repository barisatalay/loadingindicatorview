package com.atalay.loadingindicatorview.View;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import com.atalay.loadingindicatorview.Model.LoadingType;
import com.atalay.loadingindicatorview.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by baris on 7.03.2017.
 */

public class LoadingIndicatorView {
    private static LoadingIndicatorView mInstance;
    private static Dialog dialog;
    private static boolean isActive;
    private static TextView message;
    private static String messageText;
    private static Activity activeScreen;

    public static LoadingIndicatorView show(Activity mActivity, String messageText, LoadingType loadingType){
        activeScreen = mActivity;

        mInstance.messageText = messageText;

        prepareScreen(loadingType);
        return mInstance;
    }

    public static LoadingIndicatorView show(Activity mActivity, int resourceId, LoadingType loadingType){
        activeScreen = mActivity;
        messageText = activeScreen.getString(resourceId).toString();


        prepareScreen(loadingType);
        return mInstance;
    }

    /*
    * Default false
    * */
    public static LoadingIndicatorView setCancelable(boolean value){
        if(dialog != null)
            dialog.setCancelable(value);

        return mInstance;
    }

    private static void prepareScreen(LoadingType loadingType){
        dialog = new Dialog(activeScreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        isActive = true;
        dialog.setCancelable(false);

        if(message == null)
            message = (TextView) dialog.findViewById(R.id.loading_text);

        message.setText(messageText);

        ((AVLoadingIndicatorView) dialog.findViewById(R.id.loading_indicator)).setIndicator(loadingType.toString());

        dialog.show();
    }

    public static void hide(){
        if(dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        isActive = false;
        activeScreen = null;
    }

    public static boolean isActive() {
        return isActive;
    }

    public static void changeText( String messageText) {
        if(isActive)
            if(message != null)
                message.setText(messageText);
    }

    public static void changeText( int resourceId) {
        if(isActive)
            if(message != null)
                message.setText(activeScreen.getString(resourceId).toString());
    }
}
