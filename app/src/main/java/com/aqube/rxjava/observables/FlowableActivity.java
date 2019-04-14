package com.aqube.rxjava.observables;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aqube.rxjava.R;
import com.aqube.rxjava.util.Constants;

import java.util.Random;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class FlowableActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable);
        findViewById(R.id.button_get_data).setOnClickListener(this);
        textViewDisplay = findViewById(R.id.text_view_message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get_data:
                getData();
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @SuppressLint("CheckResult")
    private void getData() {
        getFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .reduce(0, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                })
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        textViewDisplay.append("onSubscribe");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        textViewDisplay.append("onSuccess");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                        textViewDisplay.append(String.valueOf(integer));
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }

                    @Override
                    public void onError(Throwable e) {
                        textViewDisplay.append("onError");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }
                });
    }

    private Flowable<Integer> getFlowable() {
        return Flowable.range(0, new Random().nextInt(500));
    }
}
