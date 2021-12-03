package com.programmingbros.androidbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VersionBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final PendingResult pendingResult = goAsync();
        Task asyncTask = new Task(pendingResult, intent, context);
        asyncTask.execute();
    }

    private static class Task extends AsyncTask<String, Integer, String> implements Response.ErrorListener, Response.Listener<JSONObject> {
        private final PendingResult pendingResult;
        private final Intent intent;
        private final Context ctx;

        private Task(PendingResult pendingResult, Intent intent, Context ctx) {
            this.pendingResult = pendingResult;
            this.intent = intent;
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, Config.CONFIG_URL,
                            null, this, this);
            VolleySingleton.getInstance(this.ctx).addRequestToQueue(jsonObjectRequest);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pendingResult.finish();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.v("RAFA", "Error: " + error.toString());
        }

        @Override
        public void onResponse(JSONObject response) {
            try {
                String version = response.getString("version");
                if (Config.APP_VERSION < Float.parseFloat(version)){

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
