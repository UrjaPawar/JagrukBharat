package com.urjapawar.jagrukbharat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RestAdapter adapterdev;
    public static final String ROOT_URL = "http://139.59.13.182:8000/api";
    private boolean eng;
    static Main2Activity main2Activity;
    ArrayAdapter<String> dataAdapter;
    JSONObject obj;
    List<String> categories;
    TextView tv,jv;
    String x;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=(TextView)findViewById(R.id.choose_city);
        jv=(TextView)findViewById(R.id.json);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Bundle bundle = getIntent().getExtras();
        x="";
        eng= bundle.getBoolean("ENG");
        if(eng)
        {
               tv.setText("Choose City");
        }
        else
        {
            tv.setText("अपना क्षेत्र चुने");
        }
        adapterdev = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build();
        // Spinner click listener
        main2Activity=this;
        spinner.setOnItemSelectedListener(this);

        flag=false;
         categories = new ArrayList<>();
        categories.add("gujrat");
        categories.add("madhya-pradesh");
        categories.add("chhattisgarh");
        categories.add("maharashtra");
        categories.add("uttar-pradesh");
        categories.add("himachal-pradesh");
        categories.add("odisha");
        categories.add("haryana");
        categories.add("karnataka");
        categories.add("rajasthan");
        categories.add("andhra-pradesh");
        categories.add("bihar");
        categories.add("jharkhand");
        List<String> categories_hindi = new ArrayList<String>();
        categories_hindi.add("गुजरात");
        categories_hindi.add("मध्यप्रदेश");
        categories_hindi.add("छत्तीसगढ़");
        categories_hindi.add("महाराष्ट्र");
        categories_hindi.add("उत्तर प्रदेश");
        categories_hindi.add("हिमाचल प्रदेश");
        categories_hindi.add("ओडिशा");
        categories_hindi.add("हरियाणा");
        categories_hindi.add("कर्नाटक");
        categories_hindi.add("राजस्थान");
        categories_hindi.add("आन्ध्र प्रदेश");
        categories_hindi.add("बिहार");
        categories_hindi.add("झारखंड");

        // Creating adapter for spinner
        if(eng){
         dataAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);}
        else{ dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories_hindi);}

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    public static Main2Activity getInstance() {
        return main2Activity;
    }
    public void english(boolean f)
    {
        eng=f;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + categories.get(position), Toast.LENGTH_LONG).show();

       pushState(categories.get(position));
    }
    public void pushState(String item) {
        {

            StateAPI api = adapterdev.create(StateAPI.class);

            //Defining the method insertuser of our interface
            api.insertTask(
                item,
                    //Creating an anonymous callback
                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {
                            //On success we will read the server's output using bufferedreader
                            //Creating a bufferedreader object
                            BufferedReader reader = null;

                            //An string to store output from the server
                            String output = "";

                            try {
                                //Initializing buffered reader
                                reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                                //Reading the output in the string
                                output = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            //Displaying the output as a toast

                             x = stripHtml(output);

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            //If any error occured displaying the error as toast
                            //Toast.makeText(Main2Activity.this, error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }

            );
            String data = "";
            try {

                int k=0;
                String x="";
                while(true)
                {
                    int l=x.indexOf("title",k);
                    int m=x.indexOf("description",k);
                    int m1=x.indexOf("date",k);

                    Toast.makeText(this,Integer.toString(l),Toast.LENGTH_LONG).show();
                    int k1;
                    if(l==-1)
                        break;
                    String title="";
                    String desc="";
                    for( k1 = l+5; k1<m; k1++)
                    {
                       title+=x.charAt(k1);
                    }
                    for(k1=m+7;k1<m1;++k1)
                    {
                        desc+=x.charAt(k1);
                    }
                    k=k1;
                    Toast.makeText(this,title+" "+desc,Toast.LENGTH_LONG).show();
                }
                try
                {
                    JSONArray jsonarray = new JSONArray(x);

                }
                catch(Exception e)
                {
//                    Toast.makeText(this,"IMP:" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
                JSONArray jsonarray = new JSONArray(x);
               for(int i=0; i<jsonarray.length(); i++){
  //                  Toast.makeText(this,"IMP:" + "entered",Toast.LENGTH_LONG).show();
                    JSONObject obj = jsonarray.getJSONObject(i);

                    String name = obj.getString("fields");
    //                Toast.makeText(this,"IMP:" +name,Toast.LENGTH_LONG).show();
                    System.out.println(name);
                   // System.out.println(url);
                }


                Toast.makeText(this,data,Toast.LENGTH_LONG).show();
                }
             catch (JSONException e) {e.printStackTrace();}
            flag=true;
        }}
    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    }

