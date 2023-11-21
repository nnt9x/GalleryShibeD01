package com.example.galleryshibed01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.PixelCopy;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAnimals;
    private List<Animal> dataSource;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAnimals = findViewById(R.id.lvAnimals);
        // Thu xem listview da chay dc hay chua
        dataSource = new ArrayList<>();
        // Lay du lieu tu internet ?? Web API: HTTP/HTTPS
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://shibe.online/api/shibes?count=20";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Lay du lieu thanh cong
                // Tao ra danh sach
                for (int i = 0; i < response.length(); i++){
                    try {
                        dataSource.add(new Animal("Siba "+i, response.getString(i)));
                        // Thong bao cho adapter du lieu thay doi
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Lay du lieu that bai
                Log.d("API error",error.toString());
                Toast.makeText(MainActivity.this, "API Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonArrayRequest);

        // Tao adapter
        adapter = new Adapter(dataSource, this);
        // Set adapter cho listview
        lvAnimals.setAdapter(adapter);
    }
}