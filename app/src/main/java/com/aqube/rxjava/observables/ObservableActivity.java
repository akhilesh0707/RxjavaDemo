package com.aqube.rxjava.observables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aqube.rxjava.R;
import com.aqube.rxjava.util.User;
import com.aqube.rxjava.util.UserUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        textViewDisplay.append("onSubscribe");
                        textViewDisplay.append("\n");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        textViewDisplay.append(user.getName());
                        textViewDisplay.append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        textViewDisplay.append("onError: " + e.getMessage());
                        textViewDisplay.append("\n");
                    }

                    @Override
                    public void onComplete() {
                        textViewDisplay.append("onComplete");
                        textViewDisplay.append("\n");
                    }
                });
    }

    private Observable<User> getUserObservable() {
        final List<User> userList = UserUtil.getUserList();
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) {
                for (User user : userList) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(user);
                    }
                }
                // all notes are emitted
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }
}
