package com.sunzn.receptor;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class CustomView extends FrameLayout implements DefaultLifecycleObserver {

    private final String TAG = "CustomView";

    private boolean mAttached;

    IntentFilter mFilter = new IntentFilter();

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mFilter.addAction(Intent.ACTION_SCREEN_ON);
        mFilter.addAction(Intent.ACTION_SCREEN_OFF);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        regReceiver();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        unrReceiver();
    }

    private void regReceiver() {
        if (!mAttached) {
            getContext().registerReceiver(mReceiver, mFilter);
            mAttached = true;
        }
    }

    private void unrReceiver() {
        if (mAttached) {
            getContext().unregisterReceiver(mReceiver);
            mAttached = false;
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                Log.d(TAG, "SCREEN_OFF");
            } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
                Log.d(TAG, "SCREEN_ON");
            }
        }
    };

}
