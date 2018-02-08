package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
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

public class ContentActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Content");
        setContentView(R.layout.activity_content);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/content.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new ContentWebAppInterface(this), "Android");
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
        //intent.setClass(activity, ContentActivity.class);
        activity.startActivity(intent);
    }

    public void toAR(){
        Intent intent=new Intent();
        intent.setClass(activity, cArticulatorActivity.class);
        activity.startActivity(intent);
    }
    public void toCO(){
        Intent intent=new Intent();
        intent.setClass(activity, cConsonantActivity.class);
        activity.startActivity(intent);
    }
    public void toVO(){
        Intent intent=new Intent();
        intent.setClass(activity, cVowelsActivity.class);
        activity.startActivity(intent);
    }
    public void toTO(){
        Intent intent=new Intent();
        intent.setClass(activity, cTonesActivity.class);
        activity.startActivity(intent);
    }
    public void toOT(){
        Intent intent=new Intent();
        intent.setClass(activity, cOthersActivity.class);
        activity.startActivity(intent);
    }
    public void toGL(){
        Intent intent=new Intent();
        intent.setClass(activity, cGlossaryActivity.class);
        activity.startActivity(intent);
    }
    public void toGA(){
        Intent intent=new Intent();
        intent.setClass(activity, GameActivity.class);
        activity.startActivity(intent);
    }
    public void toCH(){
        Intent intent=new Intent();
        intent.setClass(activity, IPAChartPDFActivity.class);
        activity.startActivity(intent);
    }

}




class ContentWebAppInterface extends WebAppInterface {

    ContentActivity vc;

    public ContentWebAppInterface(Activity c) {
        super(c);
        vc=(ContentActivity)c;
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
    public void toAR(){
        vc.toAR();
    }

    @JavascriptInterface
    public void toCO(){
        vc.toCO();
    }

    @JavascriptInterface
    public void toVO(){
        vc.toVO();
    }

    @JavascriptInterface
    public void toTO(){
        vc.toTO();
    }

    @JavascriptInterface
    public void toOT(){
        vc.toOT();
    }

    @JavascriptInterface
    public void toGL(){
        vc.toGL();
    }

    @JavascriptInterface
    public void toGA(){
        vc.toGA();
    }

    @JavascriptInterface
    public void toCH(){
        vc.toCH();
    }

}