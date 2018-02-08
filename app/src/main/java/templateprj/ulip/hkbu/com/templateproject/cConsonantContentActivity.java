package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
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
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("SetJavaScriptEnabled")

public class cConsonantContentActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;
    MediaPlayer player = new MediaPlayer();
    double vd=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Consonant");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#db9864"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_c_consonant_content);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/consoncontent.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new cConsonantContentWebAppInterface(this), "Android");
        webV.setLongClickable(false);
        webV.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                webV.loadUrl("javascript:setInShow('"+ GlobalValue.consonant_id +"')");
            }
        });
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
        //intent.setClass(activity, cConsonantContentActivity.class);
        activity.startActivity(intent);
    }

    public void closePage(){
        finish();
    }

    public void playSound(String fname){
        fname="web/"+fname;
        //Log.e("bello", "playSound(\"" + fname + "\")");
        DeviceAPI.playSound(fname);
        vd=DeviceAPI.getSoundDuration();
        //Log.e("bello", "vd: " + vd);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        webV.loadUrl("javascript:setSoundDuration(" + vd + ");");
                    }
                });
            }
        }, 50);
    }

    public void replaySound(){
        //Log.e("bello","replaySound()");
        DeviceAPI.replaySound();
    }

}




class cConsonantContentWebAppInterface extends WebAppInterface {

    cConsonantContentActivity vc;

    public cConsonantContentWebAppInterface(Activity c) {
        super(c);
        vc=(cConsonantContentActivity)c;
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
    public void toClose(){
        vc.closePage();
    }

    @JavascriptInterface
    public void playSound(String fname){
        vc.playSound(fname);
    }

    @JavascriptInterface
    public void replaySound(){
        vc.replaySound();
    }


}