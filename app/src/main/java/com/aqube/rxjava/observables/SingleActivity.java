package com.aqube.rxjava.observables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aqube.rxjava.R;
import com.aqube.rxjava.util.Constants;
import com.aqube.rxjava.util.User;
import com.aqube.rxjava.util.UserUtil;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
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
        getSingleUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        textViewDisplay.append("onSubscribe");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }

                    @Override
                    public void onSuccess(User user) {
                        textViewDisplay.append("onSuccess");
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                        textViewDisplay.append(user.getName());
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }

                    @Override
                    public void onError(Throwable e) {
                        textViewDisplay.append("onError : " + e.getMessage());
                        textViewDisplay.append(Constants.LINE_SEPARATOR);
                    }
                });
    }

    private Single<User> getSingleUser() {
        return Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(SingleEmitter<User> emitter) throws Exception {
               emitter.onSuccess(UserUtil.getUser());
            }
        });
    }

}
