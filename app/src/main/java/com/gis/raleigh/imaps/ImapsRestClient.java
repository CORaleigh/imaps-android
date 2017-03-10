package com.gis.raleigh.imaps;
import com.loopj.android.http.*;

/**
 * Created by grecoj on 3/10/17.
 */

public class ImapsRestClient {
    private static final String BASE_URL = "https://mapstest.raleighnc.gov/api/properties/autocomplete/address/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get (String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void post (String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
