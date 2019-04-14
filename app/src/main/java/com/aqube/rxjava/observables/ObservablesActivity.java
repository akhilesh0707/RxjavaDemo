package com.aqube.rxjava.observables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aqube.rxjava.R;

public class ObservablesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observables);
        initView();
    }

    private void initView(){
        // Setting click listeners
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
                startActivity(new Intent(ObservablesActivity.this, ObservableActivity.class));
                break;
            case R.id.button_flowable:
                startActivity(new Intent(ObservablesActivity.this, FlowableActivity.class));
                break;
            case R.id.button_single:
                startActivity(new Intent(ObservablesActivity.this, SingleActivity.class));
                break;
            case R.id.button_maybe:
                startActivity(new Intent(ObservablesActivity.this, MaybeActivity.class));
                break;
            case R.id.button_completable:
                startActivity(new Intent(ObservablesActivity.this, CompletableActivity.class));
                break;
            default:
        }
    }
}