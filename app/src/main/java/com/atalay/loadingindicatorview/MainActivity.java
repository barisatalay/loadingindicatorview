package com.atalay.loadingindicatorview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.atalay.loadingindicatorlib.Model.LoadingType;
import com.atalay.loadingindicatorlib.View.LoadingIndicatorView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity mActivity;
    private LinearLayout content_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        LoadingIndicatorView.show(mActivity, R.string.loading, LoadingType.BallPulseIndicator);
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            initUi();
        }finally {
            LoadingIndicatorView.hide();
        }
    }

    private void initUi() {
        content_main = (LinearLayout) findViewById(R.id.content_main);

        for(int i=0;i<content_main.getChildCount();i++){
            LinearLayout rowItem = (LinearLayout) content_main.getChildAt(i);

            if(rowItem != null)
                for(int k=0;k<rowItem.getChildCount();k++)
                    if(rowItem.getChildAt(k) instanceof Button){

                        Button button = (Button) rowItem.getChildAt(k);
                        button.setOnClickListener(this);
                        button.setTransformationMethod(null);
                    }
        }
    }

    private void startIndicator(LoadingType loadingType){
        LoadingIndicatorView.show(mActivity, loadingType.toString(), loadingType);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingIndicatorView.hide();
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        Button activeButton = (Button) v;
        startIndicator(LoadingType.valueOf((String) activeButton.getText()));
    }
}
