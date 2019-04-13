package com.aqube.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_observables).setOnClickListener(this);
        findViewById(R.id.button_operators).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_observables:
                startActivity(new Intent(MainActivity.this, ObservablesActivity.class));
                break;
            case R.id.button_operators:
                startActivity(new Intent(MainActivity.this, OperatorsActivity.class));
                break;
            default:
        }
    }
}
