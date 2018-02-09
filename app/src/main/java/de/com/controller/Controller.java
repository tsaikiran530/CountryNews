package de.com.controller;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import de.com.AppController;
import de.com.model.api.GsonRequest;
import de.com.model.pojo.CountryAct;

public class Controller {

    private String url = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    private CallbackListener mListener;
    private Context mcontext;

    public Controller(CallbackListener listener, Context context) {
        mListener = listener;
        mcontext = context;
    }

    public void startFetching() {
        GsonRequest<CountryAct> myReq = new GsonRequest<CountryAct>(url,
                CountryAct.class, null,
                OnResponseListener(), // listener for success
                onErrorListener());

        AppController.getInstance().addToRequestQueue(myReq);

    }


    private Response.Listener<CountryAct> OnResponseListener() {
        return new Response.Listener<CountryAct>() {
            @Override
            public void onResponse(CountryAct response) {

                mListener.onFetchComplete(response);
            }
        };
    }

    private Response.ErrorListener onErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mListener.onFetchFailed();
            }
        };
    }

    public interface CallbackListener {

       void onFetchComplete(CountryAct countryAct);

        void onFetchFailed();
    }
}
