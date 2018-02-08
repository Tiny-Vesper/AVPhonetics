package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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

public class IPAChartPDFActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("IPA Chart");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#020202"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_ipachart_pdf);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/ipachart.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);

        //added for native browser zoom
        webV.getSettings().setBuiltInZoomControls(true);
        webV.getSettings().setDisplayZoomControls(false);
        webV.setInitialScale(80);
        //////

        webV.addJavascriptInterface(new IPAChartPDFWebAppInterface(this), "Android");
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
        //intent.setClass(activity, IPAChartPDFActivity.class);
        activity.startActivity(intent);
    }


    public void toConsonants(String in_){
        Intent intent=new Intent();
        GlobalValue.consonant_id = in_;
        intent.setClass(activity, cConsonantContentActivity.class);
        activity.startActivity(intent);
    }

    public void toVowels(){
        Intent intent=new Intent();
        intent.setClass(activity, cVowelsActivity.class);
        activity.startActivity(intent);
    }
}




class IPAChartPDFWebAppInterface extends WebAppInterface {

    IPAChartPDFActivity vc;

    public IPAChartPDFWebAppInterface(Activity c) {
        super(c);
        vc=(IPAChartPDFActivity)c;
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
    public void toConsonants(String in_){
        vc.toConsonants(in_);
    }

    @JavascriptInterface
    public void toVowels(){
        vc.toVowels();
    }

}