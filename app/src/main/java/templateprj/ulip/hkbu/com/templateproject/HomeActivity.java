package templateprj.ulip.hkbu.com.templateproject;
import templateprj.ulip.hkbu.com.templateproject.splash.AboutActivity;
import templateprj.ulip.hkbu.com.templateproject.splash.WTA2Activity;
import templateprj.ulip.hkbu.com.templateproject.splash.WebAppInterface;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


@SuppressLint("SetJavaScriptEnabled")

public class HomeActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;
    private boolean doubleBackToExitPressedOnce;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_1);
        TextView actionBarTV=(TextView)findViewById(R.id.textV);
        actionBarTV.setText("Home");
        setContentView(R.layout.activity_home);

        activity=this;
        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/home.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new HomeWebAppInterface(this), "Android");
        webV.setLongClickable(false);

        /*
        BookmarksHelper.useBookmarks(null);
        Log.e("bello", "1.  " + BookmarksHelper.getBookmarksJSONString(null));
        BookmarksHelper.addBookmarkWithString(null, "chi:20");
        BookmarksHelper.addBookmarkWithString(null, "eng:31");
        Log.e("bello", "2.  " + BookmarksHelper.getBookmarksJSONString(null));
        BookmarksHelper.removeBookmarkAtIndex(null, 1);
        Log.e("bello", "3.  "+BookmarksHelper.getBookmarksJSONString(null));
        if(BookmarksHelper.isAlreadyAddedToBookmarksWithString(null,"chi:20"))Log.e("bello", "4.  yes");
        else Log.e("bello", "4.  no");
        BookmarksHelper.removeAllBookmarks(null);
        Log.e("bello", "5.  "+BookmarksHelper.getBookmarksJSONString(null));
        */

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            DeviceAPI.closeApp();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
        Toast.makeText(this, "Tap BACK again to exit", Toast.LENGTH_SHORT).show();
    }

    public void sToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void toNext(){
        Intent intent=new Intent();
        //intent.setClass(activity, TemplateActivity.class);
        activity.startActivity(intent);
    }

    public void toWTA2(){
        Intent intent=new Intent();
        intent.setClass(activity, WTA2Activity.class);
        activity.startActivity(intent);
    }

    public void toAbout(){
        Intent intent=new Intent();
        intent.setClass(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    public void tocr(){
        Intent intent=new Intent();
        intent.setClass(activity, ContentActivity.class);
        activity.startActivity(intent);
    }

    public void toct(){
        Intent intent=new Intent();
        intent.setClass(activity, ContentActivity.class);
        activity.startActivity(intent);
    }

    public void towl(){
        Intent intent=new Intent();
        intent.setClass(activity, IntroductionActivity.class);
        activity.startActivity(intent);
    }

}




class HomeWebAppInterface extends WebAppInterface {

    HomeActivity vc;

    public HomeWebAppInterface(Activity c) {
        super(c);
        vc=(HomeActivity)c;
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
    public void popabout2(){
        vc.toAbout();
    }

    @JavascriptInterface
    public void popwta2(){

        vc.toWTA2();
    }

    @JavascriptInterface
    public void towl(){

        vc.towl();
    }

    @JavascriptInterface
    public void toct(){

        vc.toct();
    }

    @JavascriptInterface
    public void tocr(){
        vc.tocr();
    }

}