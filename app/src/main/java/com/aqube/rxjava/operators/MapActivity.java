package com.aqube.rxjava.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aqube.rxjava.R;
import com.aqube.rxjava.util.Constants;
import com.aqube.rxjava.util.User;
import com.aqube.rxjava.util.UserUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        getUserObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        //Adding user email address
                        user.setEmail(String.format("%s@aqube.com", user.getName()));
                        return user;
                    }
                }).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                textViewDisplay.append("onSubscribe");
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onNext(User user) {
                textViewDisplay.append(user.getName() + " - " + user.getEmail());
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                textViewDisplay.append("onError: " + e.getMessage());
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                textViewDisplay.append("onComplete");
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }
        });
    }

    private Observable<User> getUserObservable() {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for (User user : UserUtil.getUserList()) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(user);
                    }
                }
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

}
