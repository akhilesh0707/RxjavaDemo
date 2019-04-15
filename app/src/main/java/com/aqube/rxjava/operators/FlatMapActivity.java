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

public class FlatMapActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map);
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
                .flatMap(new Function<User, Observable<User>>() {
                    @Override
                    public Observable<User> apply(User user) throws Exception {
                        return getUserAddressObservable(user);
                    }
                }).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                textViewDisplay.append("onSubscribe");
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onNext(User user) {
                textViewDisplay.append(user.getName() + ", " + user.getAddress());
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onError(Throwable e) {
                textViewDisplay.append("onError");
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                textViewDisplay.append("onComplete");
                textViewDisplay.append(Constants.LINE_SEPARATOR);
            }
        });
    }

    private Observable<User> getUserAddressObservable(final User user) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    user.setAddress(UserUtil.getUserAddress().get(user.getId()));
                    emitter.onNext(user);
                    emitter.onComplete();
                }
            }
        });
    }

    private Observable<User> getUserObservable() {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    for (User user : UserUtil.getUserList()) {
                        emitter.onNext(user);

                    }
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

    }
}
