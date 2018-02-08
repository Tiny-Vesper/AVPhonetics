package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("SetJavaScriptEnabled")

public class cTonesActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;
    MediaPlayer player = new MediaPlayer();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tones");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6f6263"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_c_tones);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/tones.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new cTonesWebAppInterface(this), "Android");
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
        //intent.setClass(activity, cTonesActivity.class);
        activity.startActivity(intent);
    }
    public void play(String in_){
       try {
            player.stop();
            player.reset();
            AssetFileDescriptor f = getAssets().openFd("web/tones/" + in_ + ".wav");
            player.setDataSource(f.getFileDescriptor(), f.getStartOffset(), f.getLength());
            player.prepare();
            player.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("PLAY (tone)", ":" + in_);
    }




}




class cTonesWebAppInterface extends WebAppInterface {

    cTonesActivity vc;

    public cTonesWebAppInterface(Activity c) {
        super(c);
        vc=(cTonesActivity)c;
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
    public void play(String in_){
        vc.play(in_);
    }

}