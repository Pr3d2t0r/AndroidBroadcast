package com.programmingbros.androidbroadcast;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context) {
        if (mInstance == null)
            mInstance = new VolleySingleton(context);
        return mInstance;
    }

    public void addRequestToQueue(Request<?> request) {
        this.mRequestQueue.add(request);
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }
}
