package com.urjapawar.jagrukbharat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
private BoomMenuButton boomMenuButtonInActionBar;
    private static MainActivity mainActivity;
    private TextToSpeech tts;
    Locale[] locales;
    final int HINDI=2;
    private static boolean  slctd = false;
    private static boolean eng=true;
    View mCustomView;
    private boolean init = false;
    View topLevelLayout;
    public TextView trans,opt,help;
    Main2Activity ma;
    public int postion;
    Boolean value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        value= bundle.getBoolean("ENG");
        mainActivity=this;
       check();
        trans=(TextView)findViewById(R.id.trans);
        ActionBar mActionBar = this.getSupportActionBar();
            if(mActionBar!=null)
            {
        mActionBar.show();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);}
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        opt=(TextView) findViewById(R.id.formore);
        help=(TextView) findViewById(R.id.forhelp);
        locales = Locale.getAvailableLocales();

        if(value)
        {
            mTitleTextView.setText(R.string.app_name);
            opt.setText(R.string.forMore);
            help.setText(R.string.forhelp);


        }
        else
        {

            mTitleTextView.setText(R.string.hin_app_name);
            opt.setText(R.string.forMore_hin);
            help.setText(R.string.forhelp_hin);
        }


        boomMenuButtonInActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0,0);
        topLevelLayout = findViewById(R.id.top_layout);
        topLevelLayout.setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab!=null){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLevelLayout.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topLevelLayout.setVisibility(View.INVISIBLE);
                    }
                }, 5000);

        }});


        }




       /* if (isFirstTime()) {
            topLevelLayout.setVisibility(View.INVISIBLE);
        }*/
       }
    public void Transform(View view)
    {
       final String text =trans.getText().toString();

        class bgStuff extends AsyncTask<Void, Void, Void> {



            String translatedText = "";



            @Override

            protected Void doInBackground(Void... params) {

                // TODO Auto-generated method stub

                try {



                    translatedText = translate(text);

                } catch (Exception e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                    translatedText = e.toString();

                }



                return null;

            }



            @Override

                protected void onPostExecute(Void result) {

                // TODO Auto-generated method stub

                ((TextView) findViewById(R.id.trans)).setText(translatedText);

                super.onPostExecute(result);

            }}new bgStuff().execute();}
            public String translate(String text)

                    throws Exception {



                Translate.setClientId("Jagruk_bharat"); // Change this

                Translate

                        .setClientSecret("vGpMcq3sAbyIg1z0jbEIu+LEyRkFTgegnRBtKRQzjEI=");
                String translatedText = "";

                // Language ll = selectedLanguage
                translatedText = Translate.execute(text, Language.HINDI);
                return translatedText;
            }


    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
            topLevelLayout.setVisibility(View.VISIBLE);
            topLevelLayout.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    topLevelLayout.setVisibility(View.INVISIBLE);
                    return false;
                }

            });


        }
        return ranBefore;

    }
    public static MainActivity getInstance() {
        return mainActivity;
    }
    public void update(Boolean Eng)
    {
        mainActivity.slctd = true;
        mainActivity.eng=Eng;
    }
    public void check()
    {
        if(!mainActivity.slctd && !mainActivity.eng)
        {
            Intent myIntent = new Intent(this, ChooseLanguage.class);
            startActivity(myIntent);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Use a param to record whether the boom button has been initialized
        // Because we don't need to init it again when onResume()
        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.boom,
                R.drawable.java,
                R.drawable.github
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);
        final String[] subButtonTexts;
if(value){
         subButtonTexts = new String[]{"MainPage", "My Area", "About Us"};}
        else{
        subButtonTexts = new String[]{"मुख्य भाग","मेरा क्षेत्र","हमारे बारे में"};}

        int[][] subButtonColors = new int[3][2];

            subButtonColors[0][1] = ContextCompat.getColor(this, R.color.first);
            subButtonColors[0][0] = Util.getInstance().getPressedColor(subButtonColors[0][0]);
            subButtonColors[1][1] = ContextCompat.getColor(this, R.color.second);
            subButtonColors[1][0] = Util.getInstance().getPressedColor(subButtonColors[0][1]);
            subButtonColors[2][1] = ContextCompat.getColor(this, R.color.third);
            subButtonColors[2][0] = Util.getInstance().getPressedColor(subButtonColors[0][0]);


        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(this,R.drawable.boom),subButtonColors[0], subButtonTexts[0])
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.java), subButtonColors[1], subButtonTexts[1])
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github), subButtonColors[2], subButtonTexts[2])
                .button(ButtonType.HAM)
                .boom(BoomType.HORIZONTAL_THROW_2)
                .place(PlaceType.HAM_3_1)
                .showOrder(OrderType.RANDOM)
                .hideOrder(OrderType.RANDOM)
                .autoDismiss(true)
                .cancelable(true)
                .dim(DimType.DIM_6)
                .clickEffect(ClickEffectType.RIPPLE)
                .subButtonTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                    @Override
                    public void onClick(int buttonIndex) {
                        Toast.makeText(MainActivity.this,"On click"+ subButtonTexts[buttonIndex],Toast.LENGTH_SHORT).show();
                        if(buttonIndex==1)
                        {

                            Intent intent = new Intent(MainActivity.this, Farmer.class);
                            intent.putExtra("ENG", value);
                            startActivity(intent);
                            finish();
                        }
                        if(buttonIndex==0)
                        {

                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra("ENG", value);
                            startActivity(intent);
                            finish();
                        }
                    }
                })
                .init(boomMenuButtonInActionBar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
