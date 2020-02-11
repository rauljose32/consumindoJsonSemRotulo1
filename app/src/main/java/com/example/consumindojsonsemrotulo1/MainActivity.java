package com.example.consumindojsonsemrotulo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.consumindojsonsemrotulo1.util.Auxiliar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private static final String URL = "https://jsonplaceholder.typicode.com/posts/";
    private List<HashMap<String, String>> listText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listText = new ArrayList();
        lv = findViewById(R.id.listView);

        ObterJson oj = new ObterJson();
        oj.execute();

    }//onCreate

    private class ObterJson extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Download JSON", Toast.LENGTH_LONG).show();
        }//method

        @Override
        protected Void doInBackground(Void... voids) {

            Auxiliar aux = new Auxiliar();
            String jsonString = aux.conectar(URL);

            if (jsonString != null) {

                try {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject l = jsonArray.getJSONObject(i);
                        String userId = l.getString("userId");
                        String id = l.getString("id");
                        String title = l.getString("title");
                        String body = l.getString("body");


                        HashMap<String, String> list = new HashMap<>();
                        list.put("userId", userId);
                        list.put("id",id);
                        list.put("title",title);
                        list.put("body",body);

                        listText.add(list);
                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            return null;
        }//method

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    listText,
                    R.layout.item_lis,
                    new String[]{"userId","id","title","body"},
                    new int[]{R.id.textViewUserId, R.id.textViewId,R.id.textViewTitle,R.id.textViewBody});
            lv.setAdapter(adapter);
        }
    }//inner class
}//class
