package com.aqube.rxjava.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aqube.rxjava.R;

public class OperatorsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
        initView();
    }

    private void initView() {
        findViewById(R.id.button_operator_map).setOnClickListener(this);
        findViewById(R.id.button_operator_flat_map).setOnClickListener(this);
        findViewById(R.id.button_operator_concat_map).setOnClickListener(this);
        findViewById(R.id.button_operator_switch_map).setOnClickListener(this);
        findViewById(R.id.button_operator_buffer).setOnClickListener(this);
        findViewById(R.id.button_operator_debounce).setOnClickListener(this);
        findViewById(R.id.button_operator_concat).setOnClickListener(this);
        findViewById(R.id.button_operator_merge).setOnClickListener(this);
        findViewById(R.id.button_operator_math_max).setOnClickListener(this);
        findViewById(R.id.button_operator_math_min).setOnClickListener(this);
        findViewById(R.id.button_operator_math_sum).setOnClickListener(this);
        findViewById(R.id.button_operator_math_average).setOnClickListener(this);
        findViewById(R.id.button_operator_math_count).setOnClickListener(this);
        findViewById(R.id.button_operator_math_reduce).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_operator_map:
                break;
            case R.id.button_operator_flat_map:
                break;
            case R.id.button_operator_concat_map:
                break;
            case R.id.button_operator_switch_map:
                break;
            case R.id.button_operator_buffer:
                break;
            case R.id.button_operator_debounce:
                break;
            case R.id.button_operator_concat:
                break;
            case R.id.button_operator_merge:
                break;
            case R.id.button_operator_math_max:
                break;
            case R.id.button_operator_math_min:
                break;
            case R.id.button_operator_math_sum:
                break;
            case R.id.button_operator_math_average:
                break;
            case R.id.button_operator_math_count:
                break;
            default:
        }
    }
}
