package com.urjapawar.jagrukbharat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

public class ChooseLanguage extends AppCompatActivity {
    ViewFlipper vf;
    GoogleApiClient mGoogleapiCient;
    private RadioGroup language;
    private RadioButton rb;
    private ProgressBar pb;
    private ChooseLanguage cl;
    private boolean isLanguageselected;
    private String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        cl=this;
        isLanguageselected = false;

        language=(RadioGroup)findViewById(R.id.radioGroup);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        vf=(ViewFlipper)findViewById(R.id.viewFlipper);
        vf.setInAnimation(this,R.anim.fade_in);
        vf.setOutAnimation(this,R.anim.fade_out);
        mGoogleapiCient =  new GoogleApiClient
                .Builder( this )
                .enableAutoManage(this, 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi( Places.GEO_DATA_API )
                .addApi( Places.PLACE_DETECTION_API )
                .build();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if( mGoogleapiCient != null )
            mGoogleapiCient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleapiCient != null && mGoogleapiCient.isConnected()) {
            mGoogleapiCient.disconnect();
        }
        super.onStop();
    }
    public void LangChosen(View view)
    {
        int slctd=language.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(slctd);


        cl.lang=rb.getText().toString();
        if(cl.lang.contains("English"))
        {
            Toast.makeText(this,"Eng Chosen", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ChooseLanguage.this, Main2Activity.class);
            intent.putExtra("ENG", true);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this,"Hindi", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ChooseLanguage.this, Main2Activity.class);
            intent.putExtra("ENG", false);
            startActivity(intent);
            finish();
        }
       // Toast.makeText(this,"Reached", Toast.LENGTH_LONG).show();
      //  pb.setVisibility(View.VISIBLE);
        //guessCurrentPlace();



    }
    private void guessCurrentPlace() {
        try {
            // location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace( mGoogleapiCient, null );
            result.setResultCallback( new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult( @NonNull PlaceLikelihoodBuffer  likelyPlaces ) {

                    PlaceLikelihood placeLikelihood = likelyPlaces.get(0);
                    String content = "";
                    if( placeLikelihood != null && placeLikelihood.getPlace() != null && !TextUtils.isEmpty( placeLikelihood.getPlace().getName() ) )
                        content = "Most likely place: " + placeLikelihood.getPlace().getName() + "\n";
                    if( placeLikelihood != null )
                        content += "Percent change of being there: " + (int) ( placeLikelihood.getLikelihood() * 100 ) + "%";

                    //pb.setVisibility(View.GONE);
                    likelyPlaces.release();

                   // startActivity(new Intent(ChooseLanguage.this, MainActivity.class));
                   //finish();
                }
            });}

        catch (SecurityException e) {
            // lets the user know there is a problem with the gps
        }
    }

}
