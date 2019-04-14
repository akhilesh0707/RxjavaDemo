package com.aqube.rxjava.observables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aqube.rxjava.R;
import com.aqube.rxjava.util.Constants;
import com.aqube.rxjava.util.User;
import com.aqube.rxjava.util.UserUtil;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompletableActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completable);
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

    private void getData() {
        deleteUser(UserUtil.getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        textViewDisplay.append("onSubscribe");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }

                    @Override
                    public void onComplete() {
                        textViewDisplay.append("User delete successfully");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }

                    @Override
                    public void onError(Throwable e) {
                        textViewDisplay.append("onError: " + e.getMessage());
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }
                });
    }

    /**
     * Assume we are calling delete user api
     *
     * @param user : Delete user
     * @return : Completable
     */
    private Completable deleteUser(User user) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Thread.sleep(1000);
                    emitter.onComplete();
                }
            }
        });
    }
}
