package com.gis.raleigh.imaps;

import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.AutoCompleteTextView;
import org.json.*;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.*;
import android.os.Bundle;
import android.widget.EditText;


public class SearchActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> listItems;
    String type = "address";
    AutoCompleteTextView actv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        listItems = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setAdapter(arrayAdapter);

        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RequestParams params = new RequestParams();

                ////params.put("f", "json");
                //params.put("type", type);
               // params.put("input", et.getText().toString().toUpperCase());

                ImapsRestClient.get(s.toString().toUpperCase(), params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        JSONArray results;
                        //ArrayList<String> listItems = new ArrayList<String>();
                        try {
                            results = response.getJSONArray("");
                            arrayAdapter.clear();
                            for(int i = 0; i < results.length(); i++) {
                                arrayAdapter.add(results.getJSONObject(i).getString("value"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }

                });

            }
        });
    }
}
