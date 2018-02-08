package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("SetJavaScriptEnabled")

public class cVowelsActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vowels");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#b1695a"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_c_vowels);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/vowels.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new cVowelsWebAppInterface(this), "Android");
        webV.setLongClickable(false);
    }

    @Override
    public void onBackPressed(){finish();}

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)finish();
        return true;
    }

    public void sToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void toNext(){
        Intent intent=new Intent();
        //intent.setClass(activity, cVowelsActivity.class);
        activity.startActivity(intent);
    }

    public void toVS(){
        Intent intent=new Intent();
        intent.setClass(activity, cVowelsSoundActivity.class);
        activity.startActivity(intent);
    }
}




class cVowelsWebAppInterface extends WebAppInterface {

    cVowelsActivity vc;


    public cVowelsWebAppInterface(Activity c) {
        super(c);
        vc=(cVowelsActivity)c;
    }

    @JavascriptInterface
    public void sToast(String str){
        vc.sToast(str);
    }

    @JavascriptInterface
    public void toNext(){
        vc.toNext();
    }

    @JavascriptInterface
    public void toVS(){
        vc.toVS();
    }

}