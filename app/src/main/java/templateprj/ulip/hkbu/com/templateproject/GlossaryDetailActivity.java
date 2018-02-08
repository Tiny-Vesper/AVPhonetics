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
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

@SuppressLint("SetJavaScriptEnabled")

public class GlossaryDetailActivity extends AppCompatActivity {

    Activity activity;
    WebView webV;
    ArrayList glossaryList=new ArrayList();
    String jStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Title or not.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Glossary");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#312223"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_glossary_detail);

        activity=this;

        try{
            jStr=DeviceAPI.getJSONStringFromAssets("web/glossary.json");
            glossaryList=(ArrayList)DeviceAPI.getListOrMapFromJSON(new JSONArray(jStr));
        }catch(Exception e){}

        webV=(WebView)findViewById(R.id.webV);
        WebSettings webSettings = webV.getSettings();
        webV.loadUrl("file:///android_asset/web/glossary.html");
        webV.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webV.setVerticalScrollBarEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.addJavascriptInterface(new GlossaryDetailWebAppInterface(this), "Android");
        webV.setLongClickable(false);
        webV.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                try{
                    webV.loadUrl("javascript:setJson("+jStr+",\""+GlobalValue.viewingGlossary+"\")");
                }catch(Exception e){}
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)finish();
        return true;
    }

    public void sToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void toNext(int index){
        Intent intent=new Intent();
        GlobalValue.viewingGlossary=((Map<String,String>)(glossaryList.get(index))).get("name");
        intent.setClass(activity, GlossaryDetailActivity.class);
        activity.startActivity(intent);
    }

}




class GlossaryDetailWebAppInterface extends WebAppInterface {

    GlossaryDetailActivity vc;

    public GlossaryDetailWebAppInterface(Activity c) {
        super(c);
        vc=(GlossaryDetailActivity)c;
    }

    @JavascriptInterface
    public void sToast(String str){
        vc.sToast(str);
    }

    @JavascriptInterface
    public void toNext(int index){
        vc.toNext(index);
    }

}