package com.aqube.rxjava.operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
                startActivity(new Intent(OperatorsActivity.this, MapActivity.class));
                break;
            case R.id.button_operator_flat_map:
                startActivity(new Intent(OperatorsActivity.this, FlatMapActivity.class));
                break;
            case R.id.button_operator_concat_map:
                startActivity(new Intent(OperatorsActivity.this, ConcatMapActivity.class));
                break;
            case R.id.button_operator_switch_map:
                startActivity(new Intent(OperatorsActivity.this, SwitchMapActivity.class));
                break;
            case R.id.button_operator_buffer:
                startActivity(new Intent(OperatorsActivity.this, BufferActivity.class));
                break;
            case R.id.button_operator_debounce:
                startActivity(new Intent(OperatorsActivity.this, DebounceActivity.class));
                break;
            case R.id.button_operator_concat:
                startActivity(new Intent(OperatorsActivity.this, ConcatActivity.class));
                break;
            case R.id.button_operator_merge:
                startActivity(new Intent(OperatorsActivity.this, MergeActivity.class));
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
