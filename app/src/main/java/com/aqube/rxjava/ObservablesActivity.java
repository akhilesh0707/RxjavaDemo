package com.aqube.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ObservablesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observables);

        findViewById(R.id.button_observable).setOnClickListener(this);
        findViewById(R.id.button_flowable).setOnClickListener(this);
        findViewById(R.id.button_single).setOnClickListener(this);
        findViewById(R.id.button_maybe).setOnClickListener(this);
        findViewById(R.id.button_completable).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_observable:
                break;
            case R.id.button_flowable:
                break;
            case R.id.button_single:
                break;
            case R.id.button_maybe:
                break;
            case R.id.button_completable:
                break;
            default:
        }
    }
}
