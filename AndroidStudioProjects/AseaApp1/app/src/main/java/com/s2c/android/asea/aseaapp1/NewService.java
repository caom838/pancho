package com.s2c.android.asea.aseaapp1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.s2c.android.asea.Model.Plan;
import com.s2c.android.asea.Model.Service;
import com.s2c.android.asea.core.AppConfig;
import com.s2c.android.asea.core.AppController;
import com.s2c.android.asea.core.CustomListPlanAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewService extends Activity {

    private ProgressDialog pDialog;
    private List<Plan> planList = new ArrayList<Plan>();
    private ListView listView;
    private CustomListPlanAdapter adapter;
    private Button btnSchedule;
    private EditText txtAddress;
    // Log tag
    private static final String TAG = NewService.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        txtAddress = (EditText) findViewById(R.id.txtDireccion);

        listView = (ListView) findViewById(R.id.lvPlanes);
        adapter = new CustomListPlanAdapter(this, planList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Cargando planes...");
        pDialog.show();

        // changing action bar color
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));

        // Creating volley request obj
        //JsonArrayRequest movieReq = new JsonArrayRequest(AppConfig.URL_REGISTER,new Response.Listener<JSONArray>() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response.toString());
                        hidePDialog(); //hide dialogs
                        try {
                            JSONObject jObj = new JSONObject(response);

                            boolean error = jObj.getBoolean("error");
                            if (!error) {

                                JSONArray plansArry = jObj.getJSONArray("plans");

                                // Parsing json
                                for (int i = 0; i < plansArry.length(); i++) {
                                    try {

                                        JSONObject obj = plansArry.getJSONObject(i);
                                        Plan plan = new Plan();
                                        plan.setName(obj.getString("name"));
                                        plan.setDescription(obj.getString("description"));
                                        plan.setPrice(Double.parseDouble(obj.getString("price")));
                                        plan.setId(obj.getLong("id"));

                                        // adding movie to movies array
                                        planList.add(plan);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();

                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        hidePDialog();
                        Log.e(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();

                    }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "plans");
                 return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);


        // Click event for single list row
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
            }
        });

        //cuando le dén clic al boton de programar
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = txtAddress.getText().toString();
                Long idPlan = listView.getSelectedItemId();

                Intent i = new Intent(NewService.this,Schedule.class);
                Service serv =  new Service();
                serv.setIdPlan(idPlan);
                serv.setAddressService(address);

                i.putExtra("SERVICE_SCHEDULE", serv);
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    /******methods ***/

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
